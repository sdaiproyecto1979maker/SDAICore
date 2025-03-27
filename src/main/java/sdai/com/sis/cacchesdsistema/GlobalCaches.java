package sdai.com.sis.cacchesdsistema;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import sdai.com.sis.cacchesdsistema.rednodal.ContenedorDCache;
import sdai.com.sis.servidor.rednodal.InstanciaSDAI;
import sdai.com.sis.utilidades.Hora;
import sdai.com.sis.utilidades.Reflexion;

/**
 * @date 26/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class GlobalCaches extends Thread {

	private static GlobalCaches instancia;
	private final ConcurrentMap<String, ICacheDSistema> almacenDContenedores;
	private final InstanciaSDAI instanciaSDAI;
	private final Boolean isContinuar;

	private GlobalCaches() throws Exception {
		this.almacenDContenedores = new ConcurrentHashMap<String, ICacheDSistema>();
		this.instanciaSDAI = InstanciaSDAI.getInstancia();
		this.isContinuar = Boolean.valueOf(true);
	}

	public static GlobalCaches getInstancia() throws Exception {
		if (GlobalCaches.instancia == null) {
			synchronized (GlobalCaches.class) {
				if (GlobalCaches.instancia == null) {
					GlobalCaches.instancia = new GlobalCaches();
					GlobalCaches.instancia.load();
					GlobalCaches.instancia.start();
				}
			}
		}
		return GlobalCaches.instancia;
	}

	private void load() throws Exception {
		ContenedorDCache[] contenedoresDCache = ContenedorDCache.getInstancias();
		for (ContenedorDCache contenedorDCache : contenedoresDCache) {
			String codigoDContenedor = contenedorDCache.getCodigoDContenedor();
			String className = contenedorDCache.getClaseDContenedor();
			ICacheDSistema cacheDSistema = (ICacheDSistema) Reflexion.invokeMetodoEstatico(className, "getInstancia");
			cacheDSistema.setContenedorDCache(contenedorDCache);
			this.almacenDContenedores.put(codigoDContenedor, cacheDSistema);
		}
	}

	@Override
	public void run() {
		try {
			while (this.isContinuar.equals(Boolean.valueOf(true))) {
				Hora horaDSistema = Hora.getHoraDSistema();
				Integer hora = horaDSistema.getHora();
				Integer horaDGestion = this.instanciaSDAI.getHoraGestionDCaches();
				if (hora.equals(horaDGestion)) {
					ICacheDSistema[] cachesDSistema = this.almacenDContenedores.values().toArray(new ICacheDSistema[0]);
					for (ICacheDSistema cacheDSistema : cachesDSistema)
						cacheDSistema.eliminarInstancias();
				}
				Thread.sleep(3600000);
			}
		} catch (Exception ex) {

		}
	}

}
