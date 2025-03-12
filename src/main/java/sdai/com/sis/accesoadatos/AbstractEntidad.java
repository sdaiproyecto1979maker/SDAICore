package sdai.com.sis.accesoadatos;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import sdai.com.sis.aplicaciones.KAplicaciones;

/**
 * @date 12/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@MappedSuperclass
public abstract class AbstractEntidad implements IEntidad, Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = KAplicaciones.AtributosDEntidad.USUARAUDIT, length = 45, nullable = false)
	private String usuarioDAuditoria;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = KAplicaciones.AtributosDEntidad.FECHAAUDIT, nullable = false)

	protected AbstractEntidad() {
		// TODO Auto-generated constructor stub
	}

}
