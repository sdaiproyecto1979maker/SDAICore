package sdai.com.sis.conexiones;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import sdai.com.sis.accesoadatos.IEntidad;
import sdai.com.sis.accesoadatos.IdQuery;
import sdai.com.sis.aplicacion.Servidor;

/**
 * @date 09/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class IdConexion {

	public static final Integer CONEXDATOS = Integer.valueOf(0);
	public static final Integer CONEXCONFI = Integer.valueOf(1);

	private static Long numeradorDConexiones;
	private static ConcurrentMap<String, IdConexion> almacenDConexiones;

	static {
		synchronized (IdConexion.class) {
			IdConexion.numeradorDConexiones = Long.valueOf(0);
			IdConexion.almacenDConexiones = new ConcurrentHashMap<String, IdConexion>();
		}
	}

	private final Integer entornoDConexion;
	private final String idDConexion;

	private IdConexion(Integer entornoDConexion) throws Throwable {
		this.entornoDConexion = entornoDConexion;
		this.idDConexion = obtenerIdDConexion();
		PoolDConexiones poolDConexiones = PoolDConexiones.getInstancia(entornoDConexion);
		poolDConexiones.establecerConexion(this);
		IdConexion.almacenDConexiones.put(this.idDConexion, this);
	}

	private synchronized String obtenerIdDConexion() throws Throwable {
		Servidor servidor = Servidor.getInstancia();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("[ ");
		String hostAddress = servidor.getHostAddress();
		stringBuilder.append(hostAddress);
		stringBuilder.append(" - ");
		IdConexion.numeradorDConexiones++;
		stringBuilder.append(IdConexion.numeradorDConexiones);
		stringBuilder.append(" ]");
		String idDConexion = stringBuilder.toString();
		return idDConexion;
	}

	public static IdConexion getInstancia() throws Throwable {
		return IdConexion.getInstancia(IdConexion.CONEXDATOS);
	}

	public static IdConexion getInstancia(Integer entornoDConexion) throws Throwable {
		return new IdConexion(entornoDConexion);
	}

	public static IdConexion getInstancia(String idDConexion) throws Throwable {
		IdConexion idConexion = IdConexion.almacenDConexiones.get(idDConexion);
		if (idConexion == null)
			// TODO: Modificar la descripción del error cuando se desarrolle el multiidioma
			throw new Throwable("No existe conexión para el identificador " + idDConexion);
		return idConexion;
	}

	public List<IEntidad> ejecutarConsulta(IdQuery idQuery) throws Throwable {
		PoolDConexiones poolDConexiones = PoolDConexiones.getInstancia(this.entornoDConexion);
		List<IEntidad> lista = poolDConexiones.ejecutarConsulta(this, idQuery);
		return lista;
	}

	public void persistEntidad(IEntidad entidad) throws Throwable {
		PoolDConexiones poolDConexiones = PoolDConexiones.getInstancia(this.entornoDConexion);
		poolDConexiones.persistEntidad(this, entidad);
	}

	public void mergeEntidad(IEntidad entidad) throws Throwable {
		PoolDConexiones poolDConexiones = PoolDConexiones.getInstancia(this.entornoDConexion);
		poolDConexiones.mergeEntidad(this, entidad);
	}

	public void removeEntidad(IEntidad entidad) throws Throwable {
		PoolDConexiones poolDConexiones = PoolDConexiones.getInstancia(this.entornoDConexion);
		poolDConexiones.removeEntidad(this, entidad);
	}

	public void doCommit() throws Throwable {
		PoolDConexiones poolDConexiones = PoolDConexiones.getInstancia(this.entornoDConexion);
		poolDConexiones.doCommit(this);
	}

	public void doRollback() throws Throwable {
		PoolDConexiones poolDConexiones = PoolDConexiones.getInstancia(this.entornoDConexion);
		poolDConexiones.doRollback(this);
	}

	public void liberarConexion() throws Throwable {
		PoolDConexiones poolDConexiones = PoolDConexiones.getInstancia(this.entornoDConexion);
		poolDConexiones.liberarConexion(this);
		IdConexion.almacenDConexiones.remove(this.idDConexion);
	}

	public Integer getEntornoDConexion() {
		return entornoDConexion;
	}

	public String getIdDConexion() {
		return idDConexion;
	}

}
