package sdai.com.sis.conexiones;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @date 12/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class MultiPoolDConexiones {

	private static MultiPoolDConexiones instancia;
	private final ConcurrentMap<Integer, PoolDConexiones> almacenDPools;

	private MultiPoolDConexiones() {
		this.almacenDPools = new ConcurrentHashMap<Integer, PoolDConexiones>();
	}

	static MultiPoolDConexiones getInstancia() throws Exception {
		if (MultiPoolDConexiones.instancia == null) {
			synchronized (MultiPoolDConexiones.class) {
				if (MultiPoolDConexiones.instancia == null) {
					MultiPoolDConexiones.instancia = new MultiPoolDConexiones();
					MultiPoolDConexiones.instancia.load();
				}
			}
		}
		return MultiPoolDConexiones.instancia;
	}

	private void load() throws Exception {
		BaseDDatos[] instancias = BasesDDatos.getInstancias();
		for (BaseDDatos instancia : instancias) {
			Integer entornoDConexion = instancia.getEntornoDConexion();
			PoolDConexiones poolDConexiones = new PoolDConexiones(instancia);
			this.almacenDPools.put(entornoDConexion, poolDConexiones);
		}
	}

	PoolDConexiones getPoolDConexiones(Integer entornoDConexion) {
		PoolDConexiones instancia = this.almacenDPools.get(entornoDConexion);
		return instancia;
	}

}
