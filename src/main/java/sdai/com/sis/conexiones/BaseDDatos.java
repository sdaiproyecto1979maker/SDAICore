package sdai.com.sis.conexiones;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Node;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 12/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class BaseDDatos {

	private static final String ENTORCONEX = "ENTORCONEX";
	private static final String UNIDPERSIS = "UNIDPERSIS";
	private static final String CONEXMAXIM = "CONEXMAXIM";
	private static final String PROPSCONEX = "PROPSCONEX";
	private static final String NOMBRDPROP = "NOMBRDPROP";
	private static final String VALORDPROP = "VALORDPROP";

	private final Integer entornoDConexion;
	private final String unidadDPersistencia;
	private final Integer conexionesMaximas;
	private final Map<String, Object> propiedadesDConexion;

	BaseDDatos(Node root) {
		// TODO: establecer el entorno de conexión cuando este desarrollado el metodo
		// que devuelva un integer en la lectura de un fichero XML
		this.unidadDPersistencia = DocumentoXML.getStringValueNodeDescendencia(root, UNIDPERSIS);
		// TODO: establecer las conexiones maximas cuando este desarrollado el metodo
		// que devuelva un integer en la lectura de un fichero XML
		this.propiedadesDConexion = new HashMap<String, Object>();
		loadPropiedades(root);
	}

	private void loadPropiedades(Node root) {
		Node[] nodes = DocumentoXML.getDescendencia(root, PROPSCONEX);
		for (Node node : nodes) {
			String nombreDPropiedad = DocumentoXML.getStringValueNodeDescendencia(node, NOMBRDPROP);
			String valorDPropiedad = DocumentoXML.getStringValueNodeDescendencia(node, VALORDPROP);
			this.propiedadesDConexion.put(nombreDPropiedad, valorDPropiedad);
		}
	}

	EntityManager createEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(getUnidadDPersistencia(), getPropiedadesDConexion());
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager;
	}

	public Integer getEntornoDConexion() {
		return entornoDConexion;
	}

	public String getUnidadDPersistencia() {
		return unidadDPersistencia;
	}

	public Integer getConexionesMaximas() {
		return conexionesMaximas;
	}

	public Map<String, Object> getPropiedadesDConexion() {
		return propiedadesDConexion;
	}

}
