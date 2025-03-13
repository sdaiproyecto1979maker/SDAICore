package sdai.com.sis.accesoadatos;

import org.w3c.dom.Node;

import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;

/**
 * @date 13/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public interface IEntidadCFG {

	void addNode(NumeroDVersion numeroDVersion, Integer numeroDSituacion, Node root);
}
