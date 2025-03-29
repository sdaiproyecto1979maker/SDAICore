package sdai.com.sis.conexiones;

import java.net.InetAddress;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import sdai.com.sis.accesoadatos.IEntidad;

/**
 * @date 12/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
public final class IdConexion {

	public static final Integer CONEXDATOS = Integer.valueOf(0);
	public static final Integer CONEXCONFI = Integer.valueOf(1);

	private static Long numeradorDConexiones;
	private static ConcurrentMap<String, IdConexion> almacenDConexiones;

	static {
		synchronized (IdConexion.class) {
			IdConexion.numeradorDConexiones = new Date().getTime();
			IdConexion.almacenDConexiones = new ConcurrentHashMap<String, IdConexion>();
		}
	}

	private final Integer entornoDConexion;
	private final String idDConexion;

	private IdConexion(Integer entornoDConexion) throws Exception {
		this.entornoDConexion = entornoDConexion;
		this.idDConexion = obtenerIdDConexion();
		PoolDConexiones poolDConexiones = PoolDConexiones.getInstancia(entornoDConexion);
		poolDConexiones.establecerConexion(this);
		IdConexion.almacenDConexiones.put(this.idDConexion, this);
	}

	private synchronized String obtenerIdDConexion() throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("[ ");
		InetAddress inetAddress = InetAddress.getLocalHost();
		stringBuilder.append(inetAddress.getHostAddress());
		stringBuilder.append(" - ");
		IdConexion.numeradorDConexiones++;
		stringBuilder.append(IdConexion.numeradorDConexiones);
		stringBuilder.append(" ]");
		return stringBuilder.toString();
	}

	public static IdConexion getInstancia() throws Exception {
		return IdConexion.getInstancia(IdConexion.CONEXDATOS);
	}

	public static IdConexion getInstancia(Integer entornoDConexion) throws Exception {
		return new IdConexion(entornoDConexion);
	}

	public static IdConexion getInstancia(String idDConexion) throws Exception {
		IdConexion instancia = IdConexion.almacenDConexiones.get(idDConexion);
		if (instancia == null)
			// TODO: Modificar cuando se haya desarrollado el multiidioma
			throw new Exception("No se ha encontrado ninguna conexión para el identificador" + idDConexion);
		return instancia;
	}

	public List<IEntidad> ejecutarConsulta(IdQuery idQuery) throws Exception {
		PoolDConexiones poolDConexiones = PoolDConexiones.getInstancia(this.entornoDConexion);
		List<IEntidad> resultados = poolDConexiones.ejecutarConsulta(this, idQuery);
		return resultados;
	}

	public void ejecutarPersist(IEntidad entidad) throws Exception {
		PoolDConexiones poolDConexiones = PoolDConexiones.getInstancia(this.entornoDConexion);
		poolDConexiones.ejecutarPersist(this, entidad);
	}

	public void ejecutarMerge(IEntidad entidad) throws Exception {
		PoolDConexiones poolDConexiones = PoolDConexiones.getInstancia(this.entornoDConexion);
		poolDConexiones.ejecutarMerge(this, entidad);
	}

	public void ejecutarRemove(IEntidad entidad) throws Exception {
		PoolDConexiones poolDConexiones = PoolDConexiones.getInstancia(this.entornoDConexion);
		poolDConexiones.ejecutarRemove(this, entidad);
	}

	public void doCommit() throws Exception {
		PoolDConexiones poolDConexiones = PoolDConexiones.getInstancia(this.entornoDConexion);
		poolDConexiones.doCommit(this);
	}

	public void doRollback() throws Exception {
		PoolDConexiones poolDConexiones = PoolDConexiones.getInstancia(this.entornoDConexion);
		poolDConexiones.doRollback(this);
	}

	public void liberarConexion() throws Exception {
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
