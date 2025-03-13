package sdai.com.sis.accesoadatos;

import org.w3c.dom.Node;

import sdai.com.sis.conexiones.IdConexion;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;

/**
 * @date 13/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AbstractFabricaDEntidadesCFG extends AbstractFabricaDEntidades implements IFabricaDEntidadesCFG {

	private final NumeroDVersion numeroDVersion;
	private final Node[] nodes;

	protected AbstractFabricaDEntidadesCFG(NumeroDVersion numeroDVersion, Node[] nodes) throws Exception {
		super(IdConexion.CONEXCONFI);
		this.numeroDVersion = numeroDVersion;
		this.nodes = nodes;
	}

	@Override
	public void versionar() throws Exception {
		for (Node node : this.nodes)
			versionarElemento(node);
	}

	protected abstract IEntidad versionarElemento(Node root) throws Exception;

	public NumeroDVersion getNumeroDVersion() {
		return numeroDVersion;
	}

	public Node[] getNodes() {
		return nodes;
	}

}
