package sdai.com.sis.rednodal;

import sdai.com.sis.utilidades.Transform;

/**
 * @date 26/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AbstractElementoDRed implements IElementoDRed {

	private final NodoDRed nodoDRed;
	private TuplaDNodo tuplaDNodo;

	protected AbstractElementoDRed(String codigoDNodo) throws Exception {
		this.nodoDRed = NodoDRed.getInstancia(codigoDNodo);
	}

	protected TuplaDNodo[] getTuplasDNodo(String... argumentos) throws Exception {
		TuplaDNodo[] tuplasDNodo = this.nodoDRed.getTuplasDNodo(argumentos);
		return tuplasDNodo;
	}

	protected TuplaDNodo getTuplaDNodo(String... argumentos) throws Exception {
		TuplaDNodo[] tuplasDNodo = this.nodoDRed.getTuplasDNodo(argumentos);
		if (tuplasDNodo == null || tuplasDNodo.length == 0)
			return null;
		return tuplasDNodo[0];
	}

	public void setTuplaDNodo(TuplaDNodo tuplaDNodo) {
		this.tuplaDNodo = tuplaDNodo;
	}

	protected String getValorString(String key) {
		DatoDTupla datoDTupla = this.tuplaDNodo.getDatoDTupla(key);
		return datoDTupla == null ? "" : Transform.toString(datoDTupla.getValorDAtributo());
	}

	protected Integer getValorInteger(String key) {
		DatoDTupla datoDTupla = this.tuplaDNodo.getDatoDTupla(key);
		return datoDTupla == null ? Integer.valueOf(0) : Transform.toInteger(datoDTupla.getValorDAtributo());
	}

}
