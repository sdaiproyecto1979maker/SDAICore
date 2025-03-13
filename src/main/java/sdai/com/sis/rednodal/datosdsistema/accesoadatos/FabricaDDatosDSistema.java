package sdai.com.sis.rednodal.datosdsistema.accesoadatos;

import org.w3c.dom.Node;

import sdai.com.sis.accesoadatos.AbstractFabricaDEntidadesCFG;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;

/**
 * @date 13/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class FabricaDDatosDSistema extends AbstractFabricaDEntidadesCFG {

	private FabricaDDatosDSistema(NumeroDVersion numeroDVersion, Node[] nodes) throws Exception {
		super(numeroDVersion, nodes);
	}

}
