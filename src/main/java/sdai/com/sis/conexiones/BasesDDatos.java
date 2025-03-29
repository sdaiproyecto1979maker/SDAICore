package sdai.com.sis.conexiones;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 12/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
public abstract class BasesDDatos {

	private static final String PATH = "/sdai/com/sis/conexiones/xml/BASESDATOS.xml";

	static BaseDDatos[] getInstancias() throws Exception {
		InputStream inputStream = BasesDDatos.class.getResourceAsStream(PATH);
		if (inputStream == null)
			// TODO: Modificar cuando se desarrolle el multiidioma
			throw new Exception("No se ha definido fichero con las conexiones a bases de datos.");
		List<BaseDDatos> lista = new ArrayList<>();
		DocumentoXML documentoXML = new DocumentoXML(inputStream);
		Node root = documentoXML.getRoot();
		Node[] nodes = DocumentoXML.getDescendencia(root);
		for (Node node : nodes) {
			BaseDDatos baseDDatos = new BaseDDatos(node);
			lista.add(baseDDatos);
		}
		return lista.toArray(new BaseDDatos[0]);
	}

}
