package sdai.com.sis.accesoadatos;

import org.w3c.dom.Node;

/**
 * @date 12/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
public interface IEntidad {

	Long getIdentificador();

	void addNode(Node root);

}
