package sdai.com.sis.xml;

import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @date 08/05/2025
 * @author Sergio_M
 * @since VERSIONDCOREENCURSO
 */
@Stateless
@LocalBean
public class XMLUtil {

    private static final String ROOT = "ROOT";

    public Document createDocumentoXML(String path) throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
        if (inputStream != null) {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            return documentBuilder.parse(inputStream);
        }
        return null;
    }

    public Node getRoot(Document document) {
        NodeList nodeList = document.getElementsByTagName(ROOT);
        Node node = nodeList.item(0);
        return node;
    }

    public Node[] getNodesDescendencia(Node root) {
        List<Node> lista = new ArrayList<>();
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            short nodeType = node.getNodeType();
            if (nodeType == Node.ELEMENT_NODE) {
                lista.add(node);
            }
        }
        return lista.toArray(new Node[0]);
    }

    public Node[] getNodesDescendencia(Node root, String tagName) {
        List<Node> lista = new ArrayList<>();
        Node[] nodes = getNodesDescendencia(root);
        for (Node node : nodes) {
            String nodeName = node.getNodeName();
            if (nodeName.equals(tagName)) {
                lista.add(node);
            }
        }
        return lista.toArray(new Node[0]);
    }

    public Node getNodeDescendencia(Node root, String tagName) {
        Node[] nodes = getNodesDescendencia(root, tagName);
        if (nodes != null && nodes.length > 0) {
            return nodes[0];
        }
        return null;
    }

    public String getValorString(Node root, String tagName) {
        Node node = getNodeDescendencia(root, tagName);
        return node == null ? "" : node.getTextContent();
    }

}
