package sdai.com.sis.conexiones;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 09/03/2025
 * @since 0.1.0.0-SNAPSHOT
 * @author Sergio_M
 */
public abstract class BasesDDatos {

	private static final String PATH = "/sdai/com/sis/conexiones/xml/BASESDATOS.xml";

	public static BaseDDatos[] getInstancias() throws Throwable {
		List<BaseDDatos> lista = new ArrayList<BaseDDatos>();
		InputStream inputStream = BasesDDatos.class.getResourceAsStream(PATH);
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
