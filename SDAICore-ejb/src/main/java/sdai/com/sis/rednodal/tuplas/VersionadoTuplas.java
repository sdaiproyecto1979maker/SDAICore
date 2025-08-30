package sdai.com.sis.rednodal.tuplas;

import jakarta.enterprise.inject.spi.CDI;
import sdai.com.sis.rednodal.nodos.*;
import sdai.com.sis.versiones.VersiondoDElemento;
import sdai.com.sis.xml.BuildDocumentoXML;

/**
 * @date 26/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class VersionadoTuplas implements VersiondoDElemento {
    
    @Override
    public void getVersion(BuildDocumentoXML bdXML, String entornoAplicacion) {
        ADTuplas adTuplas = CDI.current().select(ADTuplas.class).get();
        Tupla[] tuplas = adTuplas.getTuplas(entornoAplicacion);
        for (Tupla tupla : tuplas) {
            bdXML.createNode(KTuplas.NOMBRTABLA);
            bdXML.createNode(KTuplas.AtributosDEntidad.CODIGTUPLA, tupla.getCodigoDTupla());
            bdXML.createNode(KTuplas.AtributosDEntidad.DESCRTUPLA, tupla.getDescripcionDTupla());
            bdXML.createNode(KNodos.AtributosDEntidad.CODIGONODO, tupla.getNodo().getCodigoDNodo());
            bdXML.closeNode(KTuplas.NOMBRTABLA);
        }
    }
    
}
