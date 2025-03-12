package sdai.com.sis.versionado.elementosCFG;

import org.w3c.dom.Node;

import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 11/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AbstractElementoCFG implements IElementoCFG {

	private static final String CODELEMENT = "CODELEMENT";
	private static final String FBKELEMENT = "FBKELEMENT";

	private final Node root;

	protected AbstractElementoCFG(Node root) {
		this.root = root;
	}

	@Override
	public String getCodigoDElemento() {
		return DocumentoXML.getStringValueNodeDescendencia(this.root, CODELEMENT);
	}

	@Override
	public String getFabricaDElemento() {
		return DocumentoXML.getStringValueNodeDescendencia(this.root, FBKELEMENT);
	}

}
