package sdai.com.sis.rednodal;

/**
 * @date 26/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AbstractElementoDRed implements IElementoDRed {

	private final NodoDRed nodoDRed;

	protected AbstractElementoDRed(String codigoDNodo) throws Exception {
		this.nodoDRed = NodoDRed.getInstancia(codigoDNodo);
	}

	protected TuplaDNodo[] getTuplasDNodo(String... argumentos) throws Exception {

	}

	protected TuplaDNodo getTuplaDNodo(String... argumentos) throws Exception {

	}

}
