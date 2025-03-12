package sdai.com.sis.versionado.proyectosdaplicacion;

import org.w3c.dom.Node;

import sdai.com.sis.versionado.KVersionado;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 12/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AbstractProyecto implements IProyecto {

	private final Node root;

	protected AbstractProyecto(Node root) {
		this.root = root;
	}

	@Override
	public String getCodigoDProyecto() {
		return DocumentoXML.getStringValueNodeDescendencia(this.root, KVersionado.KProyectosDAplicacion.CODPROYECT);
	}

	@Override
	public String getPackageDProyecto() {
		return DocumentoXML.getStringValueNodeDescendencia(this.root, KVersionado.KProyectosDAplicacion.PKGPROYECT);
	}

	@Override
	public String getNumeroDVersion() {
		return DocumentoXML.getStringValueNodeDescendencia(this.root, KVersionado.KProyectosDAplicacion.NUMVERSION);
	}

}
