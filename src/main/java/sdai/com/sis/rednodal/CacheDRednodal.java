package sdai.com.sis.rednodal;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import sdai.com.sis.cacchesdsistema.KeyCache;

/**
 * @date 13/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class CacheDRednodal {

	private static ConcurrentMap<String, Object> almacenDInstancias;

	static {
		synchronized (CacheDRednodal.class) {
			CacheDRednodal.almacenDInstancias = new ConcurrentHashMap<String, Object>();
		}
	}

	public static Object recuperarInstancia(KeyCache keyCache) {
		String key = keyCache.getKeyCache();
		Object instancia = CacheDRednodal.almacenDInstancias.get(key);
		return instancia;
	}

	public static void almacenarInstancia(KeyCache keyCache, Object instancia) {
		if (instancia != null) {
			String key = keyCache.getKeyCache();
			CacheDRednodal.almacenDInstancias.put(key, instancia);
		}
	}

	public static Boolean existeInstancia(KeyCache keyCache) {
		String key = keyCache.getKeyCache();
		return CacheDRednodal.almacenDInstancias.containsKey(key);
	}

}
