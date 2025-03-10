package sdai.com.sis.accesoadatos;

import java.util.List;
import java.util.Map;

import sdai.com.sis.aplicacion.AplicacionUtil;
import sdai.com.sis.conexiones.IdConexion;
import sdai.com.sis.utilidades.Util;

/**
 * @date 09/03/2025
 * @since 0.1.0.0-SNAPSHOT
 * @author Sergio_M
 */
public abstract class AbstractAccesoADatos implements IAccesoADatos {

	private Integer entornoDConexion = IdConexion.CONEXDATOS;
	private String idDConexion;
	private Boolean isConexionDSesion = Boolean.valueOf(false);

	protected AbstractAccesoADatos() throws Throwable {
		Map<String, Object> almacenDSesion = AplicacionUtil.getAlmacenDSesion();
		IdConexion idConexion = (IdConexion) almacenDSesion.get(IdConexion.class.getName());
		if (idConexion != null) {
			Integer entornoDConexion = idConexion.getEntornoDConexion();
			if (entornoDConexion.equals(this.entornoDConexion)) {
				this.idDConexion = idConexion.getIdDConexion();
				this.isConexionDSesion = Boolean.valueOf(true);
			}
		}
	}

	protected AbstractAccesoADatos(String idDConexion) throws Throwable {
		this.idDConexion = idDConexion;
		IdConexion idConexion = IdConexion.getInstancia(idDConexion);
		this.entornoDConexion = idConexion.getEntornoDConexion();
		this.isConexionDSesion = Boolean.valueOf(true);
	}

	protected void marcarEntornoDConfiguracion() {
		this.entornoDConexion = IdConexion.CONEXCONFI;
		this.isConexionDSesion = Boolean.valueOf(false);
		this.idDConexion = "";
	}

	protected IEntidad ejecutarConsultaSimple(IdQuery idQuery) throws Throwable {
		List<IEntidad> lista = ejecutarConsulta(idQuery);
		return lista == null || lista.isEmpty() ? null : lista.get(0);
	}

	protected List<IEntidad> ejecutarConsulta(IdQuery idQuery) throws Throwable {
		try {
			IdConexion idConexion = obtenerIdConexion();
			List<IEntidad> lista = idConexion.ejecutarConsulta(idQuery);
			return lista;
		} finally {
			liberarConexion();
		}
	}

	protected void ejecutarPersist(IEntidad entidad) throws Throwable {
		IdConexion idConexion = obtenerIdConexion();
		idConexion.persistEntidad(entidad);
	}

	protected void ejecutarMerge(IEntidad entidad) throws Throwable {
		IdConexion idConexion = obtenerIdConexion();
		idConexion.mergeEntidad(entidad);
	}

	protected void ejecutarRemove(IEntidad entidad) throws Throwable {
		IdConexion idConexion = obtenerIdConexion();
		idConexion.removeEntidad(entidad);
	}

	private IdConexion obtenerIdConexion() throws Throwable {
		if (Util.isCadenaVacia(this.idDConexion) && !this.isConexionDSesion) {
			IdConexion idConexion = IdConexion.getInstancia(this.entornoDConexion);
			this.idDConexion = idConexion.getIdDConexion();
		}
		IdConexion idConexion = IdConexion.getInstancia(this.idDConexion);
		return idConexion;
	}

	private void liberarConexion() throws Throwable {
		if (Util.isCadenaNoVacia(this.idDConexion) && !this.isConexionDSesion) {
			IdConexion idConexion = IdConexion.getInstancia(this.idDConexion);
			idConexion.liberarConexion();
			this.idDConexion = "";
		}
	}

}
