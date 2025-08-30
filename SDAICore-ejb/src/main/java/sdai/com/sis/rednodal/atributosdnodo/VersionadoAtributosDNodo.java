package sdai.com.sis.rednodal.atributosdnodo;

import jakarta.enterprise.inject.spi.CDI;
import sdai.com.sis.rednodal.datosdsistema.KDatosDSistema;
import sdai.com.sis.rednodal.nodos.*;
import sdai.com.sis.versiones.VersiondoDElemento;
import sdai.com.sis.xml.BuildDocumentoXML;

/**
 * @date 26/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class VersionadoAtributosDNodo implements VersiondoDElemento {

    @Override
    public void getVersion(BuildDocumentoXML bdXML, String entornoAplicacion) {
        ADAtributosDNodo adAtributosDNodo = CDI.current().select(ADAtributosDNodo.class).get();
        AtributoDNodo[] atributosDNodo = adAtributosDNodo.getAtributosDNodo(entornoAplicacion);
        for (AtributoDNodo atributoDNodo : atributosDNodo) {
            bdXML.createNode(KAtributosDNodo.NOMBRTABLA);
            bdXML.createNode(KNodos.AtributosDEntidad.CODIGONODO, atributoDNodo.getNodo().getCodigoDNodo());
            bdXML.createNode(KDatosDSistema.AtributosDEntidad.CODIGODATO, atributoDNodo.getDatoDSistema().getCodigoDDato());
            bdXML.closeNode(KAtributosDNodo.NOMBRTABLA);
        }
    }

}
