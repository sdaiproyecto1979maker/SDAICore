package sdai.com.sis.cacchesdsistema;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @date 26/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class GlobalCaches {

	private static GlobalCaches instancia;
	private final ConcurrentMap<String, ICacheDSistema> almacenDContenedores;

	private GlobalCaches() {
		this.almacenDContenedores = new ConcurrentHashMap<String, ICacheDSistema>();
	}

	public static GlobalCaches getInstancia() throws Exception {
		if (GlobalCaches.instancia == null) {
			synchronized (GlobalCaches.class) {
				if (GlobalCaches.instancia == null) {
					GlobalCaches.instancia = new GlobalCaches();
					GlobalCaches.instancia.load();
				}
			}
		}
		return GlobalCaches.instancia;
	}

	private void load() throws Exception {

	}

}
