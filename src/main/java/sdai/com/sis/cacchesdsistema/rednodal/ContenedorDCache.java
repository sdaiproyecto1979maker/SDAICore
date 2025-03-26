package sdai.com.sis.cacchesdsistema.rednodal;

import java.util.ArrayList;
import java.util.List;

import sdai.com.sis.cacchesdsistema.KeyCache;
import sdai.com.sis.cacchesdsistema.contenedores.CacheDRednodal;
import sdai.com.sis.rednodal.AbstractElementoDRed;
import sdai.com.sis.rednodal.ElementoDRed;
import sdai.com.sis.rednodal.TuplaDNodo;

/**
 * @date 26/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class ContenedorDCache extends AbstractElementoDRed {

	private static final String CODIGONODO = "CONTECACHE";

	private static final String CODIGCONTE = "CODIGCONTE";
	private static final String DESCRCONTE = "DESCRCONTE";
	private static final String CLASECONTE = "CLASECONTE";
	private static final String MINUTCONTE = "MINUTCONTE";
	private static final String ELEMENTMAX = "ELEMENTMAX";

	private ContenedorDCache(TuplaDNodo tuplaDNodo) throws Exception {
		super(CODIGONODO);
		setTuplaDNodo(tuplaDNodo);
	}

	public static ContenedorDCache[] getInstancias() throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(ContenedorDCache.class);
		ContenedorDCache[] instancias = (ContenedorDCache[]) CacheDRednodal.recuperarInstancia(keyCache);
		if (instancias == null) {
			ElementoDRed elementoDRed = new ElementoDRed(CODIGONODO);
			TuplaDNodo[] tuplasDNodo = elementoDRed.getTuplasDNodo();
			if (tuplasDNodo != null && tuplasDNodo.length > 0) {
				List<ContenedorDCache> lista = new ArrayList<ContenedorDCache>();
				for (TuplaDNodo tuplaDNodo : tuplasDNodo) {
					ContenedorDCache instancia = new ContenedorDCache(tuplaDNodo);
					lista.add(instancia);
				}
				instancias = lista.toArray(new ContenedorDCache[0]);
				CacheDRednodal.almacenarInstancia(keyCache, instancias);
			}
		}
		return instancias;
	}

	public String getCodigoDContenedor() {
		return getValorString(CODIGCONTE);
	}

	public String getDescripcionDContenedor() {
		return getValorString(DESCRCONTE);
	}

	public String getClaseDContenedor() {
		return getValorString(CLASECONTE);
	}

	public Integer getMinutosEnContenedor() {
		return getValorInteger(MINUTCONTE);
	}

	public Integer getElementosMaximos() {
		return getValorInteger(ELEMENTMAX);
	}

}
