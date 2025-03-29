package sdai.com.sis.accesoadatos;

import org.w3c.dom.Node;

/**
 * @date 13/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
public interface IAccesoADatosCFG {	

	void generateElementosVersionEnCurso(String codigoDProyectoDAplicacion, Node[] nodes) throws Exception;
}
