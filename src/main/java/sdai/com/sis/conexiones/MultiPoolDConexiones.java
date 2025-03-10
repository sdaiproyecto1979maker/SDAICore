package sdai.com.sis.conexiones;

import java.util.HashMap;
import java.util.Map;

/**
 * @date 09/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class MultiPoolDConexiones {

	private static MultiPoolDConexiones instancia;
	private final Map<Integer, PoolDConexiones> almacenDPools;

	private MultiPoolDConexiones() {
		this.almacenDPools = new HashMap<Integer, PoolDConexiones>();
	}

	static MultiPoolDConexiones getInstancia() throws Throwable {
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

	private void load() throws Throwable {
		BaseDDatos[] basesDDatos = BasesDDatos.getInstancias();
		for (BaseDDatos baseDDatos : basesDDatos) {
			Integer entornoDConexion = baseDDatos.getEntornoDConexion();
			PoolDConexiones poolDConexiones = new PoolDConexiones(baseDDatos);
			this.almacenDPools.put(entornoDConexion, poolDConexiones);
		}
	}

	PoolDConexiones getPoolDConexiones(Integer entornoDConexion) {
		PoolDConexiones poolDConexiones = this.almacenDPools.get(entornoDConexion);
		return poolDConexiones;
	}

}
