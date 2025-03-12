package sdai.com.sis.accesoadatos;

import java.util.List;
import java.util.Map;

import sdai.com.sis.aplicaciones.AplicacionUtil;
import sdai.com.sis.conexiones.IdConexion;
import sdai.com.sis.conexiones.IdQuery;

/**
 * @date 12/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AbstractAccesoADatos implements IAccesoADatos {

	private Integer entornoDConexion = IdConexion.CONEXDATOS;
	private String idDConexion;
	private Boolean isConexionDSesion = Boolean.valueOf(false);

	protected AbstractAccesoADatos() {
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

	protected AbstractAccesoADatos(String idDConexion) throws Exception {
		this.idDConexion = idDConexion;
		IdConexion idConexion = IdConexion.getInstancia(idDConexion);
		this.entornoDConexion = idConexion.getEntornoDConexion();
		this.isConexionDSesion = Boolean.valueOf(true);
	}

	protected void marcarEntornoDConfiguracion() {
		this.idDConexion = "";
		this.entornoDConexion = IdConexion.CONEXCONFI;
		this.isConexionDSesion = Boolean.valueOf(false);
	}

	protected IEntidad ejecutarConsultaSimple(IdQuery idQuery) throws Exception {
		List<IEntidad> resultados = ejecutarConsulta(idQuery);
		return resultados == null || resultados.isEmpty() ? null : resultados.get(0);
	}

	protected List<IEntidad> ejecutarConsulta(IdQuery idQuery) throws Exception {
		try {
			IdConexion idConexion = obtenerIdConexion();
			List<IEntidad> resultados = idConexion.ejecutarConsulta(idQuery);
			return resultados;
		} finally {
			liberarConexion();
		}
	}

	protected void ejecutarPersist(IEntidad entidad) throws Exception {
		IdConexion idConexion = obtenerIdConexion();
		idConexion.ejecutarPersist(entidad);
	}

	protected void ejecutarMerge(IEntidad entidad) throws Exception {
		IdConexion idConexion = obtenerIdConexion();
		idConexion.ejecutarMerge(entidad);
	}

	protected void ejecutarRemove(IEntidad entidad) throws Exception {
		IdConexion idConexion = obtenerIdConexion();
		idConexion.ejecutarRemove(entidad);
	}

	private IdConexion obtenerIdConexion() throws Exception {
		if (this.idDConexion.length() == 0 && !this.isConexionDSesion) {
			IdConexion idConexion = IdConexion.getInstancia(this.entornoDConexion);
			this.idDConexion = idConexion.getIdDConexion();
		}
		IdConexion idConexion = IdConexion.getInstancia(this.idDConexion);
		return idConexion;
	}

	private void liberarConexion() throws Exception {
		if (this.idDConexion.length() > 0 && !this.isConexionDSesion) {
			IdConexion idConexion = IdConexion.getInstancia(this.idDConexion);
			idConexion.liberarConexion();
			this.idDConexion = "";
		}
	}

	public Integer getEntornoDConexion() {
		return entornoDConexion;
	}

	public String getIdDConexion() {
		return idDConexion;
	}

	public Boolean getIsConexionDSesion() {
		return isConexionDSesion;
	}

}
