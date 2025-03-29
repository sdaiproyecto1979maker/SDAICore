package sdai.com.sis.conexiones;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

import sdai.com.sis.accesoadatos.IEntidad;

/**
 * @date 12/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
public final class PoolDConexiones {

	private final BaseDDatos baseDDatos;
	private final ConcurrentLinkedQueue<Conexion> conexionesLibres;
	private final ConcurrentLinkedQueue<Conexion> conexionesOcupadas;
	private final ConcurrentMap<String, Conexion> almacenDConexiones;

	PoolDConexiones(BaseDDatos baseDDatos) {
		this.baseDDatos = baseDDatos;
		this.conexionesLibres = new ConcurrentLinkedQueue<Conexion>();
		this.conexionesOcupadas = new ConcurrentLinkedQueue<Conexion>();
		this.almacenDConexiones = new ConcurrentHashMap<String, Conexion>();
	}

	static PoolDConexiones getInstancia(Integer entornoDConexion) throws Exception {
		MultiPoolDConexiones multiPoolDConexiones = MultiPoolDConexiones.getInstancia();
		PoolDConexiones instancia = multiPoolDConexiones.getPoolDConexiones(entornoDConexion);
		return instancia;
	}

	void establecerConexion(IdConexion idConexion) throws Exception {
		Integer numeroDConexiones = this.conexionesLibres.size() + this.conexionesOcupadas.size();
		Integer conexionesMaximas = this.baseDDatos.getConexionesMaximas();
		if (numeroDConexiones > conexionesMaximas) {
			Thread.sleep(5000);
			establecerConexion(idConexion);
		}
		if (this.conexionesLibres.size() < 10)
			createNewConexion(idConexion);
		else {
			Boolean isConexionEstablecida = establecerConexionLibre(idConexion);
			if (!isConexionEstablecida)
				establecerConexion(idConexion);
		}
	}

	private void createNewConexion(IdConexion idConexion) {
		Conexion conexion = new Conexion(this.baseDDatos);
		String idDConexion = idConexion.getIdDConexion();
		this.almacenDConexiones.put(idDConexion, conexion);
		this.conexionesOcupadas.add(conexion);
	}

	private Boolean establecerConexionLibre(IdConexion idConexion) {
		Conexion conexion = null;
		synchronized (this) {
			conexion = this.conexionesLibres.poll();
		}
		if (conexion == null || conexion.isConexionCaducada())
			return Boolean.valueOf(false);
		String idDConexion = idConexion.getIdDConexion();
		this.almacenDConexiones.put(idDConexion, conexion);
		this.conexionesOcupadas.add(conexion);
		return Boolean.valueOf(true);
	}

	List<IEntidad> ejecutarConsulta(IdConexion idConexion, IdQuery idQuery) {
		String idDConexion = idConexion.getIdDConexion();
		Conexion conexion = this.almacenDConexiones.get(idDConexion);
		List<IEntidad> resultados = conexion.ejecutarConsulta(idQuery);
		return resultados;
	}

	void ejecutarPersist(IdConexion idConexion, IEntidad entidad) {
		String idDConexion = idConexion.getIdDConexion();
		Conexion conexion = this.almacenDConexiones.get(idDConexion);
		conexion.ejecutarPersist(entidad);
	}

	void ejecutarMerge(IdConexion idConexion, IEntidad entidad) {
		String idDConexion = idConexion.getIdDConexion();
		Conexion conexion = this.almacenDConexiones.get(idDConexion);
		conexion.ejecutarMerge(entidad);
	}

	void ejecutarRemove(IdConexion idConexion, IEntidad entidad) {
		String idDConexion = idConexion.getIdDConexion();
		Conexion conexion = this.almacenDConexiones.get(idDConexion);
		conexion.ejecutarRemove(entidad);
	}

	void doCommit(IdConexion idConexion) {
		String idDConexion = idConexion.getIdDConexion();
		Conexion conexion = this.almacenDConexiones.get(idDConexion);
		conexion.doCommit();
	}

	void doRollback(IdConexion idConexion) {
		String idDConexion = idConexion.getIdDConexion();
		Conexion conexion = this.almacenDConexiones.get(idDConexion);
		conexion.doRollback();
	}

	void liberarConexion(IdConexion idConexion) {
		String idDConexion = idConexion.getIdDConexion();
		Conexion conexion = this.almacenDConexiones.remove(idDConexion);
		conexion.liberarConexion();
		this.conexionesOcupadas.remove(conexion);
		this.conexionesLibres.add(conexion);
	}

}
