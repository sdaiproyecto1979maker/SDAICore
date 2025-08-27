package sdai.com.sis.xml;

import jakarta.faces.application.FacesMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import sdai.com.sis.excepciones.ErrorGeneral;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class DocumentoXML {

    private static final String ROOT = "ROOT";

    private final Document document;

    public DocumentoXML(InputStream inputStream) throws ErrorGeneral {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            this.document = documentBuilder.parse(inputStream);
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            ErrorGeneral errorGeneral = new ErrorGeneral(FacesMessage.SEVERITY_ERROR, "Documento XML", ex.getMessage());
            throw errorGeneral;
        }
    }

    public DocumentoXML(String bdXML) throws ErrorGeneral {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            this.document = documentBuilder.parse(new InputSource(new StringReader(bdXML)));
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            ErrorGeneral errorGeneral = new ErrorGeneral(FacesMessage.SEVERITY_ERROR, "Documento XML", ex.getMessage());
            throw errorGeneral;
        }
    }

    public Node getRoot() {
        NodeList nodeList = this.document.getElementsByTagName(ROOT);
        Node root = nodeList.item(0);
        return root;
    }

    public static Node[] getDescendencia(Node root) {
        List<Node> lista = new ArrayList<>();
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            short nodeType = node.getNodeType();
            if (nodeType == Node.ELEMENT_NODE) {
                lista.add(node);
            }
        }
        return lista.toArray(Node[]::new);
    }

    public static Node[] getDescendenciaTagName(Node root, String tagName) {
        List<Node> lista = new ArrayList<>();
        Node[] nodes = DocumentoXML.getDescendencia(root);
        for (Node node : nodes) {
            String nodeName = node.getNodeName();
            if (nodeName.equals(tagName)) {
                lista.add(node);
            }
        }
        return lista.toArray(Node[]::new);
    }

    public static Node getNodeDescendenciaTagName(Node root, String tagName) {
        Node[] nodes = DocumentoXML.getDescendenciaTagName(root, tagName);
        if (nodes == null || nodes.length == 0) {
            return null;
        }
        return nodes[0];
    }

    public static String getValorString(Node root, String tagName) {
        Node node = DocumentoXML.getNodeDescendenciaTagName(root, tagName);
        return node == null ? "" : node.getTextContent();
    }

    public Document getDocument() {
        return document;
    }

}
