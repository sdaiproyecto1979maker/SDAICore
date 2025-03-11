package sdai.com.sis.versionado.elementosCFG;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @date 11/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class ElementosCFG {

	private static final String PATH = "/sdai/com/sis/versionado/elementosCFG/xml/ELEMENTCFG.xml";

	private static ElementosCFG instancia;
	private final List<IElementoCFG> elementosCFG;

	private ElementosCFG() {
		this.elementosCFG = new ArrayList<IElementoCFG>();
	}

	public static ElementosCFG getInstancia() throws Exception {
		if (ElementosCFG.instancia == null) {
			synchronized (ElementosCFG.class) {
				if (ElementosCFG.instancia == null) {
					ElementosCFG.instancia = new ElementosCFG();
					ElementosCFG.instancia.load();
				}
			}
		}
		return ElementosCFG.instancia;
	}

	private void load() throws Exception {
		InputStream inputStream = ElementosCFG.class.getResourceAsStream(PATH);
	}

}
