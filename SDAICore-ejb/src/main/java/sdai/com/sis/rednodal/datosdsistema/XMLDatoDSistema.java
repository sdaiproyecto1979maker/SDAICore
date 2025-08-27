package sdai.com.sis.rednodal.datosdsistema;

import org.w3c.dom.Node;
import sdai.com.sis.rednodal.XMLCFGLocal;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 25/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class XMLDatoDSistema implements XMLCFGLocal {

    private final String codigoDDato;
    private final String descripcionDDato;

    public XMLDatoDSistema(Node root) {
        this.codigoDDato = DocumentoXML.getValorString(root, KDatosDSistema.AtributosDEntidad.CODIGODATO);
        this.descripcionDDato = DocumentoXML.getValorString(root, KDatosDSistema.AtributosDEntidad.DESCRDDATO);
    }

    public String getCodigoDDato() {
        return codigoDDato;
    }

    public String getDescripcionDDato() {
        return descripcionDDato;
    }

}
