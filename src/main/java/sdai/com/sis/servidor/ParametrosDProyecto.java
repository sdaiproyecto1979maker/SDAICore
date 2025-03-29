package sdai.com.sis.servidor;

import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.w3c.dom.Node;

import sdai.com.sis.utilidades.Transform;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 27/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
public final class ParametrosDProyecto {

	private static final String PATH = "/sdai/com/sis/servidor/xml/PARMSPRYCT.xml";
	private static final String NOMBRPARAM = "NOMBRPARAM";
	private static final String VALORPARAM = "VALORPARAM";

	private static ParametrosDProyecto instancia;
	private final ConcurrentMap<String, Object> parametrosDProyecto;

	private ParametrosDProyecto() {
		this.parametrosDProyecto = new ConcurrentHashMap<String, Object>();
	}

	public static ParametrosDProyecto getInstancia() throws Exception {
		if (ParametrosDProyecto.instancia == null) {
			synchronized (ParametrosDProyecto.class) {
				if (ParametrosDProyecto.instancia == null) {
					ParametrosDProyecto.instancia = new ParametrosDProyecto();
					ParametrosDProyecto.instancia.load();
				}
			}
		}
		return ParametrosDProyecto.instancia;
	}

	private void load() throws Exception {
		InputStream inputStream = ParametrosDProyecto.class.getResourceAsStream(PATH);
		DocumentoXML documentoXML = new DocumentoXML(inputStream);
		Node root = documentoXML.getRoot();
		Node[] nodes = DocumentoXML.getDescendencia(root);
		for (Node node : nodes) {
			String nombreDParametro = DocumentoXML.getStringValueNodeDescendencia(node, NOMBRPARAM);
			String valorDParametro = DocumentoXML.getStringValueNodeDescendencia(node, VALORPARAM);
			this.parametrosDProyecto.put(nombreDParametro, valorDParametro);
		}
	}

	public String getValorDParametroString(String nombreDParametro) {
		Object value = this.parametrosDProyecto.get(nombreDParametro);
		return Transform.toString(value);
	}

}
