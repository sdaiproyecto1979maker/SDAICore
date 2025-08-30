package sdai.com.sis.rednodal.atributosdtupla;

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
public class ComparadorAtributosDTupla implements ComparadorDElemento {

    private final List<XMLAtributoDTupla> merges;
    private final List<XMLAtributoDTupla> removes;
    private final List<XMLAtributoDTupla> persists;

    public ComparadorAtributosDTupla() {
        this.merges = new ArrayList<>();
        this.removes = new ArrayList<>();
        this.persists = new ArrayList<>();
    }

    @Override
    public void getMerges(DocumentoXML documentoXMLOrigen, DocumentoXML documentoXMLDestino) {
        Node rootOrigen = documentoXMLOrigen.getRoot();
        Node[] nodes = DocumentoXML.getDescendenciaTagName(rootOrigen, KAtributosDTupla.NOMBRTABLA);
        if (nodes != null && nodes.length > 0) {
            for (Node node : nodes) {
                XMLAtributoDTupla xmlAtributoDTuplaOrigen = new XMLAtributoDTupla(node);
                String codigoDTupla = xmlAtributoDTuplaOrigen.getCodigoDTupla();
                String codigoDDato = xmlAtributoDTuplaOrigen.getCodigoDDato();
                XMLAtributoDTupla xmlAtributoDTuplaDestino = getXMLAtributoDTupla(documentoXMLDestino, codigoDTupla, codigoDDato);
                if (xmlAtributoDTuplaDestino != null && existenCambios(xmlAtributoDTuplaOrigen, xmlAtributoDTuplaDestino)) {
                    this.merges.add(xmlAtributoDTuplaDestino);
                }
            }
        }
    }

    private Boolean existenCambios(XMLAtributoDTupla xmlAtributoDTuplaOrigen, XMLAtributoDTupla xmlAtributoDTuplaDestino) {
        return !xmlAtributoDTuplaOrigen.getValorDAtributo().equals(xmlAtributoDTuplaDestino.getValorDAtributo());
    }

    @Override
    public void getRemoves(DocumentoXML documentoXMLOrigen, DocumentoXML documentoXMLDestino) {
        Node rootOrigen = documentoXMLOrigen.getRoot();
        Node[] nodes = DocumentoXML.getDescendenciaTagName(rootOrigen, KAtributosDTupla.NOMBRTABLA);
        if (nodes != null && nodes.length > 0) {
            for (Node node : nodes) {
                XMLAtributoDTupla xmlAtributoDTuplaOrigen = new XMLAtributoDTupla(node);
                String codigoDTupla = xmlAtributoDTuplaOrigen.getCodigoDTupla();
                String codigoDDato = xmlAtributoDTuplaOrigen.getCodigoDDato();
                XMLAtributoDTupla xmlAtributoDTuplaDestino = getXMLAtributoDTupla(documentoXMLDestino, codigoDTupla, codigoDDato);
                if (xmlAtributoDTuplaDestino == null) {
                    this.removes.add(xmlAtributoDTuplaOrigen);
                }
            }
        }
    }

    @Override
    public void getPersists(DocumentoXML documentoXMLOrigen, DocumentoXML documentoXMLDestino) {
        Node rootOrigen = documentoXMLDestino.getRoot();
        Node[] nodes = DocumentoXML.getDescendenciaTagName(rootOrigen, KAtributosDTupla.NOMBRTABLA);
        if (nodes != null && nodes.length > 0) {
            for (Node node : nodes) {
                XMLAtributoDTupla xmlAtributoDTuplaDestino = new XMLAtributoDTupla(node);
                String codigoDTupla = xmlAtributoDTuplaDestino.getCodigoDTupla();
                String codigoDDato = xmlAtributoDTuplaDestino.getCodigoDDato();
                XMLAtributoDTupla xmlAtributoDTuplaOrigen = getXMLAtributoDTupla(documentoXMLOrigen, codigoDTupla, codigoDDato);
                if (xmlAtributoDTuplaOrigen == null) {
                    this.persists.add(xmlAtributoDTuplaDestino);
                }
            }
        }
    }

    private XMLAtributoDTupla getXMLAtributoDTupla(DocumentoXML documentoXML, String codigoDTupla, String codigoDDato) {
        Node root = documentoXML.getRoot();
        Node[] nodes = DocumentoXML.getDescendenciaTagName(root, KAtributosDTupla.NOMBRTABLA);
        if (nodes != null && nodes.length > 0) {
            for (Node node : nodes) {
                XMLAtributoDTupla xmlAtributoDTupla = new XMLAtributoDTupla(node);
                String codigo = xmlAtributoDTupla.getCodigoDTupla();
                String _codigo = xmlAtributoDTupla.getCodigoDDato();
                if (codigo.equals(codigoDTupla) && _codigo.equals(codigoDDato)) {
                    return xmlAtributoDTupla;
                }
            }
        }
        return null;
    }

}
