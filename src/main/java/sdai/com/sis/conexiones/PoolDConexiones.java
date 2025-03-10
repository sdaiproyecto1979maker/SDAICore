package sdai.com.sis.conexiones;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

import sdai.com.sis.accesoadatos.IEntidad;
import sdai.com.sis.accesoadatos.IdQuery;

/**
 * @date 09/03/2025
 * @since 0.1.0.0-SNAPSHOT
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

	static PoolDConexiones getInstancia(Integer entornoDConexion) throws Throwable {
		MultiPoolDConexiones multiPoolDConexiones = MultiPoolDConexiones.getInstancia();
		PoolDConexiones instancia = multiPoolDConexiones.getPoolDConexiones(entornoDConexion);
		return instancia;
	}

	void establecerConexion(IdConexion idConexion) throws Throwable {
		Integer numeroDConexionesCreadas = this.conexionesLibres.size() + this.conexionesOcupadas.size();
		Integer numeroDConexionesMaximas = this.baseDDatos.getConexionesMaximas();
		if (numeroDConexionesCreadas >= numeroDConexionesMaximas) {
			Thread.sleep(5000);
			establecerConexion(idConexion);
		}
		if (this.conexionesLibres.size() < 5)
			createNewConexion(idConexion);
		else {
			Boolean isConexionEstablecida = isConexionEstablecida(idConexion);
			if (!isConexionEstablecida)
				establecerConexion(idConexion);
		}
	}

	private void createNewConexion(IdConexion idConexion) {
		Conexion conexion = new Conexion(this.baseDDatos);
		String idDConexion = idConexion.getIdDConexion();
		this.conexionesOcupadas.add(conexion);
		this.almacenDConexiones.put(idDConexion, conexion);
	}

	private Boolean isConexionEstablecida(IdConexion idConexion) {
		Conexion conexion = null;
		synchronized (this) {
			conexion = this.conexionesLibres.poll();
		}
		if (conexion == null || conexion.isConexionCaducda())
			return Boolean.valueOf(false);
		String idDConexion = idConexion.getIdDConexion();
		this.almacenDConexiones.put(idDConexion, conexion);
		this.conexionesOcupadas.add(conexion);
		return Boolean.valueOf(true);
	}

	List<IEntidad> ejecutarConsulta(IdConexion idConexion, IdQuery idQuery) {
		String idDConexion = idConexion.getIdDConexion();
		Conexion conexion = this.almacenDConexiones.get(idDConexion);
		List<IEntidad> lista = conexion.ejecutarConsulta(idQuery);
		return lista;
	}

	void persistEntidad(IdConexion idConexion, IEntidad entidad) {
		String idDConexion = idConexion.getIdDConexion();
		Conexion conexion = this.almacenDConexiones.get(idDConexion);
		conexion.persistEntidad(entidad);
	}

	void mergeEntidad(IdConexion idConexion, IEntidad entidad) {
		String idDConexion = idConexion.getIdDConexion();
		Conexion conexion = this.almacenDConexiones.get(idDConexion);
		conexion.mergeEntidad(entidad);
	}

	void removeEntidad(IdConexion idConexion, IEntidad entidad) {
		String idDConexion = idConexion.getIdDConexion();
		Conexion conexion = this.almacenDConexiones.get(idDConexion);
		conexion.removeEntidad(entidad);
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
		this.conexionesOcupadas.remove(conexion);
		this.conexionesLibres.add(conexion);
	}

}
