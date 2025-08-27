package sdai.com.sis.rednodal.datosdsistema;

import jakarta.enterprise.inject.spi.CDI;
import sdai.com.sis.versiones.VersiondoDElemento;
import sdai.com.sis.xml.BuildDocumentoXML;

/**
 * @date 26/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class VersionadoDatosDSistema implements VersiondoDElemento {

    @Override
    public void getVersion(BuildDocumentoXML bdXML, String entornoAplicacion) {
        ADDatosDSistema adDatosDSistema = CDI.current().select(ADDatosDSistema.class).get();
        DatoDSistema[] datosDSistema = adDatosDSistema.getDatosDSistema(entornoAplicacion);
        for (DatoDSistema datoDSistema : datosDSistema) {
            bdXML.createNode(KDatosDSistema.NOMBRTABLA);
            bdXML.createNode(KDatosDSistema.AtributosDEntidad.CODIGODATO, datoDSistema.getCodigoDDato());
            bdXML.createNode(KDatosDSistema.AtributosDEntidad.DESCRDDATO, datoDSistema.getDescripcionDDato());
            bdXML.closeNode(KDatosDSistema.NOMBRTABLA);
        }
    }

}
