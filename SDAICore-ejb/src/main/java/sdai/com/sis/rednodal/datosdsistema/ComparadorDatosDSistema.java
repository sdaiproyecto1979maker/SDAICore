package sdai.com.sis.rednodal.datosdsistema;

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
public class ComparadorDatosDSistema implements ComparadorDElemento {

    private final List<XMLDatoDSistema> merges;
    private final List<XMLDatoDSistema> removes;
    private final List<XMLDatoDSistema> persists;

    public ComparadorDatosDSistema() {
        this.merges = new ArrayList<>();
        this.removes = new ArrayList<>();
        this.persists = new ArrayList<>();
    }

    @Override
    public void getMerges(DocumentoXML documentoXMLOrigen, DocumentoXML documentoXMLDestino) {
        Node rootOrigen = documentoXMLOrigen.getRoot();
        Node[] nodes = DocumentoXML.getDescendenciaTagName(rootOrigen, KDatosDSistema.NOMBRTABLA);
        if (nodes != null && nodes.length > 0) {
            for (Node node : nodes) {
                XMLDatoDSistema xMLDatoDSistemaOrigen = new XMLDatoDSistema(node);
                String codigoDDato = xMLDatoDSistemaOrigen.getCodigoDDato();
                XMLDatoDSistema xMLDatoDSistemaDestino = getXMLDatoDSistema(documentoXMLDestino, codigoDDato);
                if (xMLDatoDSistemaDestino != null && existenCambios(xMLDatoDSistemaOrigen, xMLDatoDSistemaDestino)) {
                    this.merges.add(xMLDatoDSistemaDestino);
                }
            }
        }
    }

    private Boolean existenCambios(XMLDatoDSistema xMLDatoDSistemaOrigen, XMLDatoDSistema xMLDatoDSistemaDestino) {
        return !xMLDatoDSistemaOrigen.getDescripcionDDato().equals(xMLDatoDSistemaDestino.getDescripcionDDato());
    }

    @Override
    public void getRemoves(DocumentoXML documentoXMLOrigen, DocumentoXML documentoXMLDestino) {
        Node rootOrigen = documentoXMLOrigen.getRoot();
        Node[] nodes = DocumentoXML.getDescendenciaTagName(rootOrigen, KDatosDSistema.NOMBRTABLA);
        if (nodes != null && nodes.length > 0) {
            for (Node node : nodes) {
                XMLDatoDSistema xMLDatoDSistemaOrigen = new XMLDatoDSistema(node);
                String codigoDDato = xMLDatoDSistemaOrigen.getCodigoDDato();
                XMLDatoDSistema xMLDatoDSistemaDestino = getXMLDatoDSistema(documentoXMLDestino, codigoDDato);
                if (xMLDatoDSistemaDestino == null) {
                    this.removes.add(xMLDatoDSistemaOrigen);
                }
            }
        }
    }

    @Override
    public void getPersists(DocumentoXML documentoXMLOrigen, DocumentoXML documentoXMLDestino) {
        Node rootOrigen = documentoXMLDestino.getRoot();
        Node[] nodes = DocumentoXML.getDescendenciaTagName(rootOrigen, KDatosDSistema.NOMBRTABLA);
        if (nodes != null && nodes.length > 0) {
            for (Node node : nodes) {
                XMLDatoDSistema xMLDatoDSistemaDestino = new XMLDatoDSistema(node);
                String codigoDDato = xMLDatoDSistemaDestino.getCodigoDDato();
                XMLDatoDSistema xMLDatoDSistemaOrigen = getXMLDatoDSistema(documentoXMLOrigen, codigoDDato);
                if (xMLDatoDSistemaOrigen == null) {
                    this.persists.add(xMLDatoDSistemaDestino);
                }
            }
        }
    }

    private XMLDatoDSistema getXMLDatoDSistema(DocumentoXML documentoXML, String codigoDDato) {
        Node root = documentoXML.getRoot();
        Node[] nodes = DocumentoXML.getDescendenciaTagName(root, KDatosDSistema.NOMBRTABLA);
        if (nodes != null && nodes.length > 0) {
            for (Node node : nodes) {
                XMLDatoDSistema xMLDatoDSistema = new XMLDatoDSistema(node);
                String codigo = xMLDatoDSistema.getCodigoDDato();
                if (codigo.equals(codigoDDato)) {
                    return xMLDatoDSistema;
                }
            }
        }
        return null;
    }

}
