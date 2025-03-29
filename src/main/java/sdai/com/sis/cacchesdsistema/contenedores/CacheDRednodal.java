package sdai.com.sis.cacchesdsistema.contenedores;

import sdai.com.sis.cacchesdsistema.DefaultCacheDSistema;
import sdai.com.sis.cacchesdsistema.ICacheDSistema;
import sdai.com.sis.cacchesdsistema.KeyCache;

/**
 * @date 13/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
public abstract class CacheDRednodal {

	private static ICacheDSistema instancia;

	static {
		synchronized (CacheDRednodal.class) {
			CacheDRednodal.instancia = DefaultCacheDSistema.getInstancia(CacheDRednodal.class);
		}
	}

	public static Object recuperarInstancia(KeyCache keyCache) {
		Object instancia = CacheDRednodal.instancia.recuperarInstancia(keyCache);
		return instancia;
	}

	public static void almacenarInstancia(KeyCache keyCache, Object instancia) {
		if (instancia != null)
			CacheDRednodal.instancia.almacenarInstancia(keyCache, instancia);
	}

	public static void eliminarInstancia(KeyCache keyCache) throws Exception {
		CacheDRednodal.instancia.eliminarInstancia(keyCache);
	}

	public static void eliminarInstancias() throws Exception {
		CacheDRednodal.instancia.eliminarInstancias();
	}

	public static Boolean existeInstanciaNoDeleteable(KeyCache keyCache) {
		Boolean swDeleteable = CacheDRednodal.instancia.existeInstanciaNoDeleteable(keyCache);
		return swDeleteable;
	}

	public static ICacheDSistema getInstancia() {
		return CacheDRednodal.instancia;
	}

}
