package sdai.com.sis.rednodal.tuplas;

import org.w3c.dom.Node;
import sdai.com.sis.rednodal.XMLCFGLocal;
import sdai.com.sis.rednodal.nodos.KNodos;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 25/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class XMLTupla implements XMLCFGLocal {

    private final String codigoDTupla;
    private final String descripcionDTupla;
    private final String codigoDNodo;

    public XMLTupla(Node root) {
        this.codigoDTupla = DocumentoXML.getValorString(root, KTuplas.AtributosDEntidad.CODIGTUPLA);
        this.descripcionDTupla = DocumentoXML.getValorString(root, KTuplas.AtributosDEntidad.DESCRTUPLA);
        this.codigoDNodo = DocumentoXML.getValorString(root, KNodos.AtributosDEntidad.CODIGONODO);
    }

    public String getCodigoDTupla() {
        return codigoDTupla;
    }

    public String getDescripcionDTupla() {
        return descripcionDTupla;
    }

    public String getCodigoDNodo() {
        return codigoDNodo;
    }

}
