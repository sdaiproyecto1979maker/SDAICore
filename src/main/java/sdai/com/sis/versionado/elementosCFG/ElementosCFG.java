package sdai.com.sis.versionado.elementosCFG;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 11/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class ElementosCFG {

	private static final String PATH = "/sdai/com/sis/versionado/elementosCFG/xml/ELEMENTCFG.xml";

	private static ElementosCFG instancia;
	private final List<IElementoCFG> elementosCFG;

	private ElementosCFG() {
		this.elementosCFG = new ArrayList<IElementoCFG>();
	}

	public static ElementosCFG getInstancia() throws Exception {
		if (ElementosCFG.instancia == null) {
			synchronized (ElementosCFG.class) {
				if (ElementosCFG.instancia == null) {
					ElementosCFG.instancia = new ElementosCFG();
					ElementosCFG.instancia.load();
				}
			}
		}
		return ElementosCFG.instancia;
	}

	private void load() throws Exception {
		InputStream inputStream = ElementosCFG.class.getResourceAsStream(PATH);
		DocumentoXML documentoXML = new DocumentoXML(inputStream);
		Node root = documentoXML.getRoot();
		Node[] nodes = DocumentoXML.getDescendencia(root);
		for (Node node : nodes) {
			IElementoCFG elementoCFG = new ElementoCFG(node);
			this.elementosCFG.add(elementoCFG);
		}
	}

	public void instalarVersion(NumeroDVersion numeroDVersion, Node root) throws Exception {
		for (IElementoCFG elementoCFG : this.elementosCFG) {
			String codigoDElemento = elementoCFG.getCodigoDElemento();
			Node[] nodes = DocumentoXML.getDescendencia(root, codigoDElemento);
			elementoCFG.versionar(numeroDVersion, nodes);
		}
	}

	public List<IElementoCFG> getElementosCFG() {
		return elementosCFG;
	}

}
