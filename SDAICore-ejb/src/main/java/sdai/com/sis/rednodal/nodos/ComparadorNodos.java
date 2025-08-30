package sdai.com.sis.rednodal.nodos;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Node;
import sdai.com.sis.versiones.ComparadorDElemento;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 26/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class ComparadorNodos implements ComparadorDElemento {

    private final List<XMLNodo> merges;
    private final List<XMLNodo> removes;
    private final List<XMLNodo> persists;

    public ComparadorNodos() {
        this.merges = new ArrayList<>();
        this.removes = new ArrayList<>();
        this.persists = new ArrayList<>();
    }

    @Override
    public void getMerges(DocumentoXML documentoXMLOrigen, DocumentoXML documentoXMLDestino) {
        Node rootOrigen = documentoXMLOrigen.getRoot();
        Node[] nodes = DocumentoXML.getDescendenciaTagName(rootOrigen, KNodos.NOMBRTABLA);
        if (nodes != null && nodes.length > 0) {
            for (Node node : nodes) {
                XMLNodo xmlNodoOrigen = new XMLNodo(node);
                String codigoDNodo = xmlNodoOrigen.getCodigoDNodo();
                XMLNodo xmlNodoDestino = getXMLNodo(documentoXMLDestino, codigoDNodo);
                if (xmlNodoDestino != null && existenCambios(xmlNodoOrigen, xmlNodoDestino)) {
                    this.merges.add(xmlNodoDestino);
                }
            }
        }
    }

    private Boolean existenCambios(XMLNodo xmlNodoOrigen, XMLNodo xmlNodoDestino) {
        return !xmlNodoOrigen.getDescripcionDNodo().equals(xmlNodoDestino.getDescripcionDNodo());
    }

    @Override
    public void getRemoves(DocumentoXML documentoXMLOrigen, DocumentoXML documentoXMLDestino) {
        Node rootOrigen = documentoXMLOrigen.getRoot();
        Node[] nodes = DocumentoXML.getDescendenciaTagName(rootOrigen, KNodos.NOMBRTABLA);
        if (nodes != null && nodes.length > 0) {
            for (Node node : nodes) {
                XMLNodo xmlNodoOrigen = new XMLNodo(node);
                String codigoDNodo = xmlNodoOrigen.getCodigoDNodo();
                XMLNodo xmlNodoDestino = getXMLNodo(documentoXMLDestino, codigoDNodo);
                if (xmlNodoDestino == null) {
                    this.removes.add(xmlNodoOrigen);
                }
            }
        }
    }

    @Override
    public void getPersists(DocumentoXML documentoXMLOrigen, DocumentoXML documentoXMLDestino) {
        Node rootOrigen = documentoXMLDestino.getRoot();
        Node[] nodes = DocumentoXML.getDescendenciaTagName(rootOrigen, KNodos.NOMBRTABLA);
        if (nodes != null && nodes.length > 0) {
            for (Node node : nodes) {
                XMLNodo xmlNodoDestino = new XMLNodo(node);
                String codigoDNodo = xmlNodoDestino.getCodigoDNodo();
                XMLNodo xmlNodoOrigen = getXMLNodo(documentoXMLOrigen, codigoDNodo);
                if (xmlNodoOrigen == null) {
                    this.persists.add(xmlNodoDestino);
                }
            }
        }
    }

    private XMLNodo getXMLNodo(DocumentoXML documentoXML, String codigoDNodo) {
        Node root = documentoXML.getRoot();
        Node[] nodes = DocumentoXML.getDescendenciaTagName(root, KNodos.NOMBRTABLA);
        if (nodes != null && nodes.length > 0) {
            for (Node node : nodes) {
                XMLNodo xmlNodo = new XMLNodo(node);
                String codigo = xmlNodo.getCodigoDNodo();
                if (codigo.equals(codigoDNodo)) {
                    return xmlNodo;
                }
            }
        }
        return null;
    }

}
