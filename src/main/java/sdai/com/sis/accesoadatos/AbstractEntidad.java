package sdai.com.sis.accesoadatos;

import java.io.Serializable;
import java.util.Date;

import org.w3c.dom.Node;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import sdai.com.sis.aplicaciones.KAplicaciones;
import sdai.com.sis.utilidades.Fecha;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 12/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
@MappedSuperclass
public abstract class AbstractEntidad implements IEntidad, Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = KAplicaciones.AtributosDEntidad.USUARAUDIT, length = 45, nullable = false)
	private String usuarioDAuditoria;

	@SuppressWarnings("deprecation")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = KAplicaciones.AtributosDEntidad.FECHAAUDIT, nullable = false)
	private Date fechaDAuditoria;

	protected AbstractEntidad() {

	}

	@Override
	public void addNode(Node root) {
		this.usuarioDAuditoria = DocumentoXML.getStringValueNodeDescendencia(root, KAplicaciones.AtributosDEntidad.USUARAUDIT);
		Fecha fechaDSistema = Fecha.getFechaDSistema();
		this.fechaDAuditoria = fechaDSistema.toDate();
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
