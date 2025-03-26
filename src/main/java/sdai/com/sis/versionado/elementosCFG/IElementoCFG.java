package sdai.com.sis.versionado.elementosCFG;

import org.w3c.dom.Node;

import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;

/**
 * @date 11/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public interface IElementoCFG {

	void versionar(NumeroDVersion numeroDVersion, Node[] nodes) throws Exception;

	void loadVersionEnCurso(String codigoDProyectoDAplicacion, Node[] nodes) throws Exception;

	String getCodigoDElemento();

	String getFabricaDElemento();

	String getClaseDAccesoADatos();

}
