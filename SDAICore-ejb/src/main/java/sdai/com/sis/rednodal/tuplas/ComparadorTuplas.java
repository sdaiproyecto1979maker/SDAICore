package sdai.com.sis.rednodal.tuplas;

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
public class ComparadorTuplas implements ComparadorDElemento {

    private final List<XMLTupla> merges;
    private final List<XMLTupla> removes;
    private final List<XMLTupla> persists;

    public ComparadorTuplas() {
        this.merges = new ArrayList<>();
        this.removes = new ArrayList<>();
        this.persists = new ArrayList<>();
    }

    @Override
    public void getMerges(DocumentoXML documentoXMLOrigen, DocumentoXML documentoXMLDestino) {
        Node rootOrigen = documentoXMLOrigen.getRoot();
        Node[] nodes = DocumentoXML.getDescendenciaTagName(rootOrigen, KTuplas.NOMBRTABLA);
        if (nodes != null && nodes.length > 0) {
            for (Node node : nodes) {
                XMLTupla xmlTuplaOrigen = new XMLTupla(node);
                String codigoDTupla = xmlTuplaOrigen.getCodigoDTupla();
                XMLTupla xmlTuplaDestino = getXMLTupla(documentoXMLDestino, codigoDTupla);
                if (xmlTuplaDestino != null && existenCambios(xmlTuplaOrigen, xmlTuplaDestino)) {
                    this.merges.add(xmlTuplaDestino);
                }
            }
        }
    }

    private Boolean existenCambios(XMLTupla xmlTuplaOrigen, XMLTupla xmlTuplaDestino) {
        return !xmlTuplaOrigen.getDescripcionDTupla().equals(xmlTuplaDestino.getDescripcionDTupla());
    }

    @Override
    public void getRemoves(DocumentoXML documentoXMLOrigen, DocumentoXML documentoXMLDestino) {
        Node rootOrigen = documentoXMLOrigen.getRoot();
        Node[] nodes = DocumentoXML.getDescendenciaTagName(rootOrigen, KTuplas.NOMBRTABLA);
        if (nodes != null && nodes.length > 0) {
            for (Node node : nodes) {
                XMLTupla xmlTuplaOrigen = new XMLTupla(node);
                String codigoDTupla = xmlTuplaOrigen.getCodigoDTupla();
                XMLTupla xmlTuplaDestino = getXMLTupla(documentoXMLDestino, codigoDTupla);
                if (xmlTuplaDestino == null) {
                    this.removes.add(xmlTuplaOrigen);
                }
            }
        }
    }

    @Override
    public void getPersists(DocumentoXML documentoXMLOrigen, DocumentoXML documentoXMLDestino) {
        Node rootOrigen = documentoXMLDestino.getRoot();
        Node[] nodes = DocumentoXML.getDescendenciaTagName(rootOrigen, KTuplas.NOMBRTABLA);
        if (nodes != null && nodes.length > 0) {
            for (Node node : nodes) {
                XMLTupla xmlTuplaDestino = new XMLTupla(node);
                String codigoDTupla = xmlTuplaDestino.getCodigoDTupla();
                XMLTupla xmlTuplaOrigen = getXMLTupla(documentoXMLOrigen, codigoDTupla);
                if (xmlTuplaOrigen == null) {
                    this.persists.add(xmlTuplaDestino);
                }
            }
        }
    }

    private XMLTupla getXMLTupla(DocumentoXML documentoXML, String codigoDTupla) {
        Node root = documentoXML.getRoot();
        Node[] nodes = DocumentoXML.getDescendenciaTagName(root, KTuplas.NOMBRTABLA);
        if (nodes != null && nodes.length > 0) {
            for (Node node : nodes) {
                XMLTupla xmlTupla = new XMLTupla(node);
                String codigo = xmlTupla.getCodigoDTupla();
                if (codigo.equals(codigoDTupla)) {
                    return xmlTupla;
                }
            }
        }
        return null;
    }

}
