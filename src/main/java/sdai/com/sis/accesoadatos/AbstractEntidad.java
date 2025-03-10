package sdai.com.sis.accesoadatos;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import sdai.com.sis.aplicacion.KAplicacion;

/**
 * @date 09/03/2025
 * @since 0.1.0.0-SNAPSHOT
 * @author Sergio_M
 */
@MappedSuperclass
public abstract class AbstractEntidad implements IEntidad, Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = KAplicacion.AtributosDEntidad.USUARAUDIT, length = 45, nullable = false)
	private String usuarioDAuditoria;

	@SuppressWarnings("deprecation")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = KAplicacion.AtributosDEntidad.FECHAAUDIT, nullable = false)
	private Date fechaDAuditoria;

	protected AbstractEntidad() {

	}

	public String getUsuarioDAuditoria() {
		return usuarioDAuditoria;
	}

	public void setUsuarioDAuditoria(String usuarioDAuditoria) {
		this.usuarioDAuditoria = usuarioDAuditoria;
	}

	public Date getFechaDAuditoria() {
		return fechaDAuditoria;
	}

	public void setFechaDAuditoria(Date fechaDAuditoria) {
		this.fechaDAuditoria = fechaDAuditoria;
	}

}
