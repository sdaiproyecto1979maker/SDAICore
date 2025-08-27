package sdai.com.sis.rednodal.nodos;

import org.w3c.dom.Node;
import sdai.com.sis.rednodal.XMLCFGLocal;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 25/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class XMLNodo implements XMLCFGLocal {

    private final String codigoDNodo;
    private final String descripcionDNodo;

    public XMLNodo(Node root) {
        this.codigoDNodo = DocumentoXML.getValorString(root, KNodos.AtributosDEntidad.CODIGONODO);
        this.descripcionDNodo = DocumentoXML.getValorString(root, KNodos.AtributosDEntidad.DESCRDNODO);
    }

    public String getCodigoDNodo() {
        return codigoDNodo;
    }

    public String getDescripcionDNodo() {
        return descripcionDNodo;
    }

}
