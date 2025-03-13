package sdai.com.sis.versionado.elementosCFG;

import org.w3c.dom.Node;

import sdai.com.sis.accesoadatos.IFabricaDEntidadesCFG;
import sdai.com.sis.utilidades.Reflexion;
import sdai.com.sis.versionado.KVersionado;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 11/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AbstractElementoCFG implements IElementoCFG {

	private final Node root;

	protected AbstractElementoCFG(Node root) {
		this.root = root;
	}

	@Override
	public void versionar(NumeroDVersion numeroDVersion, Node[] nodes) throws Exception {
		Class<?>[] tipos = { NumeroDVersion.class, Node[].class };
		Object[] argumentos = { numeroDVersion, nodes };
		String className = getFabricaDElemento();
		IFabricaDEntidadesCFG fabricaDEntidadesCFG = (IFabricaDEntidadesCFG) Reflexion.createInstancia(className, tipos, argumentos);
	}

	@Override
	public String getCodigoDElemento() {
		return DocumentoXML.getStringValueNodeDescendencia(this.root, KVersionado.KElementosCFG.CODELEMENT);
	}

	@Override
	public String getFabricaDElemento() {
		return DocumentoXML.getStringValueNodeDescendencia(this.root, KVersionado.KElementosCFG.FBKELEMENT);
	}

}
