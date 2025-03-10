package sdai.com.sis.xml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import sdai.com.sis.utilidades.Transform;

/**
 * @date 09/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class DocumentoXML {

	public static final String ROOT = "ROOT";

	private final Document document;

	public DocumentoXML(InputStream inputStream) throws Throwable {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		this.document = documentBuilder.parse(inputStream);
	}

	public Node getRoot() {
		NodeList nodeList = this.document.getElementsByTagName(ROOT);
		Node node = nodeList.item(0);
		return node;
	}

	public static Node[] getDescendencia(Node root) {
		List<Node> lista = new ArrayList<Node>();
		NodeList nodeList = root.getChildNodes();
		for (Integer i = Integer.valueOf(0); i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			Short nodeType = node.getNodeType();
			if (nodeType.equals(Node.ELEMENT_NODE))
				lista.add(node);
		}
		return lista.toArray(new Node[0]);
	}

	public static Node[] getDescendencia(Node root, String tagName) {
		List<Node> lista = new ArrayList<Node>();
		Node[] nodes = DocumentoXML.getDescendencia(root);
		for (Node node : nodes) {
			String nodeName = node.getNodeName();
			if (nodeName.equals(tagName))
				lista.add(node);
		}
		return lista.toArray(new Node[0]);
	}

	public static Node getNodeDescendencia(Node root, String tagName) {
		Node[] nodes = DocumentoXML.getDescendencia(root, tagName);
		return nodes == null || nodes.length == 0 ? null : nodes[0];
	}

	public static String getValorStringNodeDescendencia(Node root, String tagName) {
		Node node = DocumentoXML.getNodeDescendencia(root, tagName);
		return node == null ? "" : node.getTextContent();
	}

	public static Integer getValorIntegerNodeDescendencia(Node root, String tagName) {
		Node node = DocumentoXML.getNodeDescendencia(root, tagName);
		return node == null ? Integer.valueOf(0) : Transform.toInteger(node.getTextContent());
	}

}
