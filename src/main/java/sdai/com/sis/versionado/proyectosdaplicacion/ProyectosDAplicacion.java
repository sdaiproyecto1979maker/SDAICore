package sdai.com.sis.versionado.proyectosdaplicacion;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.w3c.dom.Node;

import sdai.com.sis.utilidades.Transform;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
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
			ordenarNumerosDVersion(proyectoDAplicacion);
			this.almacenDProyectos.put(codigoDProyecto, proyectoDAplicacion);
		}
	}

	private ProyectoDAplicacion getCreateProyectoDAplicacion(IProyecto proyecto) throws Exception {
		String codigoDProyectoDAplicacion = proyecto.getCodigoDProyecto();
		ProyectoDAplicacion proyectoDAplicacion = ProyectoDAplicacion.getInstancia(codigoDProyectoDAplicacion);
		if (proyectoDAplicacion == null) {
			ProyectoDAplicacion.createInstancia(codigoDProyectoDAplicacion);
			proyectoDAplicacion = ProyectoDAplicacion.getInstancia(codigoDProyectoDAplicacion);
		}
		Boolean existeNumeroDVersion = existeNumeroDVersion(proyecto, proyectoDAplicacion);
		if (!existeNumeroDVersion) {
			String numeroVersion = proyecto.getNumeroDVersion();
			Integer numeroDSituacion = proyectoDAplicacion.getNumerosDVersion().size();
			NumeroDVersion.createInstancia(numeroVersion, numeroDSituacion);
			NumeroDVersion numeroDVersion = NumeroDVersion.getInstancia(numeroVersion, codigoDProyectoDAplicacion);
			numeroDVersion.setProyectoDAplicacion(proyectoDAplicacion);
			proyectoDAplicacion.getNumerosDVersion().add(numeroDVersion);
			ProyectoDAplicacion.updateInstancia(proyectoDAplicacion);
		}
		return proyectoDAplicacion;
	}

	// TODO: Hacer mas eficiente este metodo
	private Boolean existeNumeroDVersion(IProyecto proyecto, ProyectoDAplicacion proyectoDAplicacion) {
		String numeroVersion = "";
		String numeroDVersion = proyecto.getNumeroDVersion();
		StringTokenizer stringTokenizer = new StringTokenizer(numeroDVersion, "-");
		Integer contador = Integer.valueOf(0);
		while (stringTokenizer.hasMoreElements()) {
			if (contador.equals(Integer.valueOf(0)))
				numeroVersion = stringTokenizer.nextToken();
		}
		Map<Integer, Integer> almacen = new HashMap<Integer, Integer>();
		stringTokenizer = new StringTokenizer(numeroVersion, ".");
		contador = Integer.valueOf(1);
		while (stringTokenizer.hasMoreElements()) {
			almacen.put(contador, Transform.toInteger(stringTokenizer.nextToken()));
			contador++;
		}
		List<NumeroDVersion> numerosDVersion = proyectoDAplicacion.getNumerosDVersion();
		for (NumeroDVersion instancia : numerosDVersion) {
			Integer versionDRelease = instancia.getVersionDRelease();
			Integer versionDFeature = instancia.getVersionDFeature();
			Integer versionDFix = instancia.getVersionDFix();
			Integer versionDHotfix = instancia.getVersionDHotfix();
			if (versionDRelease.equals(almacen.get(Integer.valueOf(1))) && versionDFeature.equals(almacen.get(Integer.valueOf(2))) && versionDFix.equals(almacen.get(Integer.valueOf(3)))
					&& versionDHotfix.equals(almacen.get(Integer.valueOf(4)))) {
				return Boolean.valueOf(true);
			}
		}
		return Boolean.valueOf(false);
	}

	// TODO: Mejorar el metodo para no coger una conexion por cada actualizacion
	private void ordenarNumerosDVersion(ProyectoDAplicacion proyectoDAplicacion) throws Exception {
		List<NumeroDVersion> numerosDVersion = proyectoDAplicacion.getNumerosDVersion();
		Collections.sort(numerosDVersion);
		for (Integer i = Integer.valueOf(1); i <= numerosDVersion.size(); i++) {
			NumeroDVersion numeroDVersion = numerosDVersion.get(i - 1);
			numeroDVersion.setNumeroDSituacion(i);
			NumeroDVersion.updateInstancia(numeroDVersion);
		}
	}

	public List<IProyecto> getProyectos() {
		return proyectos;
	}

	public ProyectoDAplicacion[] getProyectosDAplicacion() {
		return this.almacenDProyectos.values().toArray(new ProyectoDAplicacion[0]);
	}

	public ProyectoDAplicacion getProyectoDAplicacion(String codigoDProyecto) {
		ProyectoDAplicacion proyectoDAplicacion = this.almacenDProyectos.get(codigoDProyecto);
		return proyectoDAplicacion;
	}

}
