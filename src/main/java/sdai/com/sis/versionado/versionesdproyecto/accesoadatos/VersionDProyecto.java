package sdai.com.sis.versionado.versionesdproyecto.accesoadatos;

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
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.versionado.versionesdproyecto.KVersionesDProyecto;

/**
 * @date 09/03/2025
 * @since 0.1.0.0-SNAPSHOT
 * @author Sergio_M
 */
@Entity
@Table(name = KVersionesDProyecto.KVersionDProyecto.NOMBRTABLA)
@NamedQueries({ @NamedQuery(name = KVersionesDProyecto.KVersionDProyecto.NamedQueries.SVRPRY0000, query = "SELECT V FROM VersionDProyecto V WHERE V.tipoDVersionDProyecto =:TIPOVRSPRY") })
public final class VersionDProyecto extends AbstractEntidad {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = KVersionesDProyecto.KVersionDProyecto.AtributosDEntidad.IDVERPROYT)
	private Long identificador;

	@Column(name = KVersionesDProyecto.KVersionDProyecto.AtributosDEntidad.TIPOVRSPRY, length = 10, nullable = false, unique = true)
	private String tipoDVersionDProyecto;

	@OneToMany(mappedBy = "versionDProyecto", cascade = CascadeType.ALL)
	private List<NumeroDVersion> numerosDVersion;

	VersionDProyecto() {
		this.numerosDVersion = new ArrayList<NumeroDVersion>();
	}

	public static VersionDProyecto getCreateInstancia(String tipoDVersionDProyecto) throws Throwable {
		ADVersionDProyecto adatos = new ADVersionDProyecto();
		VersionDProyecto instancia = adatos.getVersionDProyecto(tipoDVersionDProyecto);
		if (instancia == null) {
			IdConexion idConexion = IdConexion.getInstancia(IdConexion.CONEXCONFI);
			String idDConexion = idConexion.getIdDConexion();
			ADVersionDProyecto adatosTX = new ADVersionDProyecto(idDConexion);
			adatosTX.createVersionDProyecto(tipoDVersionDProyecto);
			idConexion.doCommit();
			idConexion.liberarConexion();
			instancia = adatos.getVersionDProyecto(tipoDVersionDProyecto);
		}
		return instancia;
	}

	public Long getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}

	public String getTipoDVersionDProyecto() {
		return tipoDVersionDProyecto;
	}

	public void setTipoDVersionDProyecto(String tipoDVersionDProyecto) {
		this.tipoDVersionDProyecto = tipoDVersionDProyecto;
	}

	public List<NumeroDVersion> getNumerosDVersion() {
		return numerosDVersion;
	}

	public void setNumerosDVersion(List<NumeroDVersion> numerosDVersion) {
		this.numerosDVersion = numerosDVersion;
	}

}
