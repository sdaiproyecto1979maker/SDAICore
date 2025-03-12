package sdai.com.sis.versionado.proyectosdaplicacion;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Node;

import sdai.com.sis.versionado.proyectosdaplicacion.accesoadatos.ProyectoDAplicacion;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 12/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class ProyectosDAplicacion {

	private static final String PATH = "/sdai/com/sis/versionado/proyectosdaplicacion/PROYECTAPL.xml";

	private static ProyectosDAplicacion instancia;
	private final List<IProyecto> proyectos;
	private final Map<String, ProyectoDAplicacion> almacenDProyectos;

	private ProyectosDAplicacion() {
		this.proyectos = new ArrayList<IProyecto>();
		this.almacenDProyectos = new HashMap<String, ProyectoDAplicacion>();
	}

	public static ProyectosDAplicacion getInstancia() throws Exception {
		if (ProyectosDAplicacion.instancia == null) {
			synchronized (ProyectosDAplicacion.class) {
				if (ProyectosDAplicacion.instancia == null) {
					ProyectosDAplicacion.instancia = new ProyectosDAplicacion();
					ProyectosDAplicacion.instancia.load();
				}
			}
		}
		return ProyectosDAplicacion.instancia;
	}

	private void load() throws Exception {
		InputStream inputStream = ProyectosDAplicacion.class.getResourceAsStream(PATH);
		if (inputStream == null)
			// TODO: Pendiente del desarrollo del multidioma
			throw new Exception("No se ha definido el fichero de proyectos /sdai/com/sis/versionado/proyectosdaplicacion/PROYECTAPL.xml");
		DocumentoXML documentoXML = new DocumentoXML(inputStream);
		Node root = documentoXML.getRoot();
		Node[] nodes = DocumentoXML.getDescendencia(root);
		for (Node node : nodes) {
			IProyecto proyecto = new Proyecto(node);
			String codigoDProyecto = proyecto.getCodigoDProyecto();
			ProyectoDAplicacion proyectoDAplicacion = getCreateProyectoDAplicacion(proyecto);
			this.almacenDProyectos.put(codigoDProyecto, proyectoDAplicacion);
		}
	}

	private ProyectoDAplicacion getCreateProyectoDAplicacion(IProyecto proyecto) throws Exception {
		// TODO: Pendiente desarrollar las conexiones de la aplicación
		String codigoDProyectoDAplicacion = proyecto.getCodigoDProyecto();
		ProyectoDAplicacion proyectoDAplicacion = ProyectoDAplicacion.getInstancia(codigoDProyectoDAplicacion);
		if (proyectoDAplicacion == null) {
			// TODO: Crear el proyecto de aplicación
		}
		// TODO: Crear o instanciar un número de versión en el caso de que no este
		// asociado a el proyecto de aplicacion. En el caso de no estar asociado
		// actualizar el proyecto de aplicacion con el numero de versión añadido
		return null;
	}

	public ProyectoDAplicacion[] getProyectosDAplicacion() {
		return this.almacenDProyectos.values().toArray(new ProyectoDAplicacion[0]);
	}

	public ProyectoDAplicacion getProyectoDAplicacion(String codigoDProyecto) {
		ProyectoDAplicacion proyectoDAplicacion = this.almacenDProyectos.get(codigoDProyecto);
		return proyectoDAplicacion;
	}

}
