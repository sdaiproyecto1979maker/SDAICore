package sdai.com.sis.rednodal.atributosdtupla;

import jakarta.enterprise.inject.spi.CDI;
import sdai.com.sis.rednodal.datosdsistema.KDatosDSistema;
import sdai.com.sis.rednodal.tuplas.*;
import sdai.com.sis.versiones.VersiondoDElemento;
import sdai.com.sis.xml.BuildDocumentoXML;

/**
 * @date 26/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class VersionadoAtributosDTupla implements VersiondoDElemento {

    @Override
    public void getVersion(BuildDocumentoXML bdXML, String entornoAplicacion) {
        ADAtributosDTupla adAtributosDTupla = CDI.current().select(ADAtributosDTupla.class).get();
        AtributoDTupla[] atributosDTupla = adAtributosDTupla.getAtributosDTupla(entornoAplicacion);
        for (AtributoDTupla atributoDTupla : atributosDTupla) {
            bdXML.createNode(KAtributosDTupla.NOMBRTABLA);
            bdXML.createNode(KTuplas.AtributosDEntidad.CODIGTUPLA, atributoDTupla.getTupla().getCodigoDTupla());
            bdXML.createNode(KDatosDSistema.AtributosDEntidad.CODIGODATO, atributoDTupla.getDatoDSistema().getCodigoDDato());
            bdXML.createNode(KAtributosDTupla.AtributosDEntidad.VALORATRIB, atributoDTupla.getValorDAtributo());
            bdXML.closeNode(KAtributosDTupla.NOMBRTABLA);
        }
    }

}
