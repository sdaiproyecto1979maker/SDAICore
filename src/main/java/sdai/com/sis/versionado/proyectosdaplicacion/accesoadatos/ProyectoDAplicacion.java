package sdai.com.sis.versionado.proyectosdaplicacion.accesoadatos;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import sdai.com.sis.versionado.KVersionado.KProyectosDAplicacion;

/**
 * @date 12/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Entity
@Table(name = KProyectosDAplicacion.KProyectoDAplicacion.NOMBRTABLA)
public final class ProyectoDAplicacion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = KProyectosDAplicacion.KProyectoDAplicacion.AtributosDEntidad.IDPYTAPLIC)
	private Long identificador;

	@Column(name = KProyectosDAplicacion.KProyectoDAplicacion.AtributosDEntidad.CODPROYECT, length = 10, nullable = false, unique = true)
	private String codigoDProyectoDAplicacion;

	ProyectoDAplicacion() {

	}

	public static ProyectoDAplicacion getInstancia(String codigoDProyectoDAplicacion) throws Exception {
		// TODO: Desarrollar el metodo cuando se hayan desarrollado las conexiones a la
		// base de datos
		return null;
	}

	public Long getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}

	public String getCodigoDProyectoDAplicacion() {
		return codigoDProyectoDAplicacion;
	}

	public void setCodigoDProyectoDAplicacion(String codigoDProyectoDAplicacion) {
		this.codigoDProyectoDAplicacion = codigoDProyectoDAplicacion;
	}

}
