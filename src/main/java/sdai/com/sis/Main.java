package sdai.com.sis;

import java.io.InputStream;

import org.w3c.dom.Node;

import sdai.com.sis.cacchesdsistema.KeyCache;
import sdai.com.sis.cacchesdsistema.contenedores.CacheDRednodal;
import sdai.com.sis.cacchesdsistema.rednodal.ContenedorDCache;
import sdai.com.sis.rednodal.tuplas.accesoadatos.SituacionDTupla;
import sdai.com.sis.versionado.elementosCFG.ElementosCFG;
import sdai.com.sis.versionado.versionesCFG.AbstractVersionCFG;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 26/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class Main {

	public static void main(String[] args) throws Exception {
		String path = "/sdai/com/sis/versionado/versionesCFG/xml/NEWVERSION.xml";
		InputStream inputStream = AbstractVersionCFG.class.getResourceAsStream(path);
		DocumentoXML documentoXML = new DocumentoXML(inputStream);
		Node root = documentoXML.getRoot();
		ElementosCFG elementosCFG = ElementosCFG.getInstancia();
		String codigoDProyecto = null;
		elementosCFG.loadVersionEnCurso(codigoDProyecto, root);
		ContenedorDCache.getInstancias();
		KeyCache keyCache = KeyCache.getInstancia(SituacionDTupla.class, "CONTECACHE");
		CacheDRednodal.eliminarInstancia(keyCache);
	}

}
