package sdai.com.sis.accesoadatos;

import org.w3c.dom.Node;

import sdai.com.sis.cacchesdsistema.InstanciaDContenedor;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;

/**
 * @date 13/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
public interface IEntidadCFG {

	void addNode(NumeroDVersion numeroDVersion, Integer numeroDSituacion, Node root);

	void deleteCacheInstanciaArray(InstanciaDContenedor instanciaDContenedor) throws Exception;
}
