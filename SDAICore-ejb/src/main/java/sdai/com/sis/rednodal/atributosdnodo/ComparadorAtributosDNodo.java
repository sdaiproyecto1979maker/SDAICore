package sdai.com.sis.rednodal.atributosdnodo;

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
public class ComparadorAtributosDNodo implements ComparadorDElemento {

    private final List<XMLAtributoDNodo> removes;
    private final List<XMLAtributoDNodo> persists;

    public ComparadorAtributosDNodo() {
        this.removes = new ArrayList<>();
        this.persists = new ArrayList<>();
    }

    @Override
    public void getMerges(DocumentoXML documentoXMLOrigen, DocumentoXML documentoXMLDestino) {

    }

    @Override
    public void getRemoves(DocumentoXML documentoXMLOrigen, DocumentoXML documentoXMLDestino) {
        Node rootOrigen = documentoXMLOrigen.getRoot();
        Node[] nodes = DocumentoXML.getDescendenciaTagName(rootOrigen, KAtributosDNodo.NOMBRTABLA);
        if (nodes != null && nodes.length > 0) {
            for (Node node : nodes) {
                XMLAtributoDNodo xmlAtributoDNodoOrigen = new XMLAtributoDNodo(node);
                String codigoDNodo = xmlAtributoDNodoOrigen.getCodigoDNodo();
                String codigoDDato = xmlAtributoDNodoOrigen.getCodigoDDato();
                XMLAtributoDNodo xmlAtributoDNodoDestino = getXMLAtributoDNodo(documentoXMLDestino, codigoDNodo, codigoDDato);
                if (xmlAtributoDNodoDestino == null) {
                    this.removes.add(xmlAtributoDNodoOrigen);
                }
            }
        }
    }

    @Override
    public void getPersists(DocumentoXML documentoXMLOrigen, DocumentoXML documentoXMLDestino) {
        Node rootOrigen = documentoXMLDestino.getRoot();
        Node[] nodes = DocumentoXML.getDescendenciaTagName(rootOrigen, KAtributosDNodo.NOMBRTABLA);
        if (nodes != null && nodes.length > 0) {
            for (Node node : nodes) {
                XMLAtributoDNodo xmlAtributoDNodoDestino = new XMLAtributoDNodo(node);
                String codigoDNodo = xmlAtributoDNodoDestino.getCodigoDNodo();
                String codigoDDato = xmlAtributoDNodoDestino.getCodigoDDato();
                XMLAtributoDNodo xmlAtributoDNodoOrigen = getXMLAtributoDNodo(documentoXMLOrigen, codigoDNodo, codigoDDato);
                if (xmlAtributoDNodoOrigen == null) {
                    this.persists.add(xmlAtributoDNodoDestino);
                }
            }
        }
    }

    private XMLAtributoDNodo getXMLAtributoDNodo(DocumentoXML documentoXML, String codigoDNodo, String codigoDDato) {
        Node root = documentoXML.getRoot();
        Node[] nodes = DocumentoXML.getDescendenciaTagName(root, KAtributosDNodo.NOMBRTABLA);
        if (nodes != null && nodes.length > 0) {
            for (Node node : nodes) {
                XMLAtributoDNodo xmlAtributoDNodo = new XMLAtributoDNodo(node);
                String codigo = xmlAtributoDNodo.getCodigoDNodo();
                String _codigo = xmlAtributoDNodo.getCodigoDDato();
                if (codigo.equals(codigoDNodo) && _codigo.equals(codigoDDato)) {
                    return xmlAtributoDNodo;
                }
            }
        }
        return null;
    }

}
