package sdai.com.sis.accesoadatos;

import org.w3c.dom.Node;

/**
 * @date 13/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public interface IAccesoADatosCFG {

	void generateElementosDCache() throws Exception;

	void generateElementosVersionEnCurso(String codigoDProyectoDAplicacion, Node[] nodes) throws Exception;
}
