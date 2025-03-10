package sdai.com.sis.conexiones;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Node;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 09/03/2025
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
		this.propiedadesDConexion = new HashMap<String, Object>();
		this.entornoDConexion = DocumentoXML.getValorIntegerNodeDescendencia(root, ENTORCONEX);
		this.unidadDPersistencia = DocumentoXML.getValorStringNodeDescendencia(root, UNIDPERSIS);
		this.conexionesMaximas = DocumentoXML.getValorIntegerNodeDescendencia(root, CONEXMAXIM);
		Node[] nodes = DocumentoXML.getDescendencia(root, PROPSCONEX);
		for (Node node : nodes) {
			PropiedadDConexion propiedadDConexion = new PropiedadDConexion(node);
			String nombreDPropiedad = propiedadDConexion.getNombreDPropiedad();
			String valorDPropiedad = propiedadDConexion.getValorDPropiedad();
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

	class PropiedadDConexion {

		final String nombreDPropiedad;
		final String valorDPropiedad;

		PropiedadDConexion(Node root) {
			this.nombreDPropiedad = DocumentoXML.getValorStringNodeDescendencia(root, NOMBRDPROP);
			this.valorDPropiedad = DocumentoXML.getValorStringNodeDescendencia(root, VALORDPROP);
		}

		public String getNombreDPropiedad() {
			return nombreDPropiedad;
		}

		public String getValorDPropiedad() {
			return valorDPropiedad;
		}
	}

	public Map<String, Object> getPropiedadesDConexion() {
		return propiedadesDConexion;
	}

}
