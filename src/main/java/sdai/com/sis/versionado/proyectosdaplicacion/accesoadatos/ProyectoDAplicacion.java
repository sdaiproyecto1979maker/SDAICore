package sdai.com.sis.versionado.proyectosdaplicacion.accesoadatos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import sdai.com.sis.accesoadatos.AbstractEntidad;
import sdai.com.sis.conexiones.IdConexion;
import sdai.com.sis.utilidades.Fecha;
import sdai.com.sis.versionado.KVersionado;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;

/**
 * @date 12/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
@Entity
@Table(name = KVersionado.KProyectosDAplicacion.KProyectoDAplicacion.NOMBRTABLA)
@NamedQueries({
		@NamedQuery(name = KVersionado.KProyectosDAplicacion.KProyectoDAplicacion.NamedQueries.SPRAPL0000, query = "SELECT P FROM ProyectoDAplicacion P WHERE P.codigoDProyectoDAplicacion =:CODPROYECT") })
public final class ProyectoDAplicacion extends AbstractEntidad {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = KVersionado.KProyectosDAplicacion.KProyectoDAplicacion.AtributosDEntidad.IDPYTAPLIC)
	private Long identificador;

	@Column(name = KVersionado.KProyectosDAplicacion.KProyectoDAplicacion.AtributosDEntidad.CODPROYECT, length = 10, nullable = false, unique = true)
	private String codigoDProyectoDAplicacion;

	@OneToMany(mappedBy = "proyectoDAplicacion", cascade = CascadeType.ALL)
	private List<NumeroDVersion> numerosDVersion;

	ProyectoDAplicacion() {
		this.numerosDVersion = new ArrayList<NumeroDVersion>();
	}

	public static ProyectoDAplicacion getInstancia(String codigoDProyectoDAplicacion) throws Exception {
		ADProyectoDAplicacion adatos = new ADProyectoDAplicacion();
		ProyectoDAplicacion instancia = adatos.getProyectoDAplicacion(codigoDProyectoDAplicacion);
		return instancia;
	}

	// TODO: Eliminar este metodo cuando se prepare el nodo de reflexion y la
	// fabrica
	public static void createInstancia(String codigoDProyectoDAplicacion) throws Exception {
		IdConexion idConexion = IdConexion.getInstancia(IdConexion.CONEXCONFI);
		String idDConexion = idConexion.getIdDConexion();
		ProyectoDAplicacion instancia = new ProyectoDAplicacion();
		instancia.setCodigoDProyectoDAplicacion(codigoDProyectoDAplicacion);
		instancia.setUsuarioDAuditoria("USUAREXPLO");
		instancia.setFechaDAuditoria(Fecha.getFechaDSistema().toDate());
		ADProyectoDAplicacion adatos = new ADProyectoDAplicacion(idDConexion);
		adatos.createProyectoDAplicacion(instancia);
		idConexion.doCommit();
		idConexion.liberarConexion();
	}

	// TODO: Eliminar este metodo cuando se prepare el nodo de reflexion y la
	// fabrica
	public static void updateInstancia(ProyectoDAplicacion instancia) throws Exception {
		IdConexion idConexion = IdConexion.getInstancia(IdConexion.CONEXCONFI);
		String idDConexion = idConexion.getIdDConexion();
		ADProyectoDAplicacion adatos = new ADProyectoDAplicacion(idDConexion);
		adatos.updateProyectoDAplicacion(instancia);
		idConexion.doCommit();
		idConexion.liberarConexion();
	}

	@Override
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

	public List<NumeroDVersion> getNumerosDVersion() {
		return numerosDVersion;
	}

	public void setNumerosDVersion(List<NumeroDVersion> numerosDVersion) {
		this.numerosDVersion = numerosDVersion;
	}

}
