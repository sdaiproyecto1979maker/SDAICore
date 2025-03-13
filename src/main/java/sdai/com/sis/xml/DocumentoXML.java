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
 * @date 11/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class DocumentoXML {

	private static final String ROOT = "ROOT";

	private final Document document;

	public DocumentoXML(InputStream inputStream) throws Exception {
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
		Node[] nodes = lista.toArray(new Node[0]);
		return nodes;
	}

	public static Node[] getDescendencia(Node root, String tagName) {
		List<Node> lista = new ArrayList<Node>();
		Node[] nodes = DocumentoXML.getDescendencia(root);
		for (Node node : nodes) {
			String nodeName = node.getNodeName();
			if (nodeName.equals(tagName))
				lista.add(node);
		}
		Node[] _nodes = lista.toArray(new Node[0]);
		return _nodes;
	}

	public static Node getNodeDescendencia(Node root, String tagName) {
		Node[] nodes = DocumentoXML.getDescendencia(root, tagName);
		if (nodes == null || nodes.length == 0)
			return null;
		return nodes[0];
	}

	public static String getStringValueNodeDescendencia(Node root, String tagName) {
		Node node = DocumentoXML.getNodeDescendencia(root, tagName);
		return node == null ? "" : node.getTextContent();
	}

	public static Integer getIntegerValueNodeDescendencia(Node root, String tagName) {
		Node node = DocumentoXML.getNodeDescendencia(root, tagName);
		return node == null ? Integer.valueOf(0) : Transform.toInteger(node.getTextContent());
	}

	public static Boolean getBooleanValueNodeDescendencia(Node root, String tagName) {
		Node node = DocumentoXML.getNodeDescendencia(root, tagName);
		return node == null ? Boolean.valueOf(false) : Transform.toBoolean(node.getTextContent());
	}

}
