package sdai.com.sis.rednodal.nodos;

import jakarta.enterprise.inject.spi.CDI;
import sdai.com.sis.versiones.VersiondoDElemento;
import sdai.com.sis.xml.BuildDocumentoXML;

/**
 * @date 26/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class VersionadoNodos implements VersiondoDElemento {

    @Override
    public void getVersion(BuildDocumentoXML bdXML, String entornoAplicacion) {
        ADNodos adNodos = CDI.current().select(ADNodos.class).get();
        Nodo[] nodos = adNodos.getNodos(entornoAplicacion);
        for (Nodo nodo : nodos) {
            bdXML.createNode(KNodos.NOMBRTABLA);
            bdXML.createNode(KNodos.AtributosDEntidad.CODIGONODO, nodo.getCodigoDNodo());
            bdXML.createNode(KNodos.AtributosDEntidad.DESCRDNODO, nodo.getDescripcionDNodo());
            bdXML.closeNode(KNodos.NOMBRTABLA);
        }
    }

}
