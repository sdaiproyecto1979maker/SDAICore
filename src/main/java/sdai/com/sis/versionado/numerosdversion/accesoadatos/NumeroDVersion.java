package sdai.com.sis.versionado.numerosdversion.accesoadatos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import sdai.com.sis.accesoadatos.AbstractEntidad;
import sdai.com.sis.conexiones.IdConexion;
import sdai.com.sis.versionado.numerosdversion.KNumerosDVersion;
import sdai.com.sis.versionado.versionesdproyecto.KVersionesDProyecto;
import sdai.com.sis.versionado.versionesdproyecto.accesoadatos.VersionDProyecto;

/**
 * @date 09/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Entity
@Table(name = KNumerosDVersion.NOMBRTABLA)
@NamedQueries({
		@NamedQuery(name = KNumerosDVersion.NamedQueries.SNUVRS0000, query = "SELECT N FROM NumeroDVersion N WHERE N.versionDRelease =:VERRELEASE AND N.versionDFeature =:VERFEATURE AND N.versionDFix =:VERSIONFIX AND N.versionDHotfix =:VERDHOTFIX AND N.versionDProyecto.tipoDVersionDProyecto =:TIPOVRSPRY") })
public final class NumeroDVersion extends AbstractEntidad implements Comparable<NumeroDVersion> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = KNumerosDVersion.AtributosDEntidad.IDNUMEVERS)
	private Long identificador;

	@Column(name = KNumerosDVersion.AtributosDEntidad.NUMERSITUA, nullable = false)
	private Integer numeroDSituacion;

	@Column(name = KNumerosDVersion.AtributosDEntidad.VERRELEASE, nullable = false)
	private Integer versionDRelease;

	@Column(name = KNumerosDVersion.AtributosDEntidad.VERFEATURE, nullable = false)
	private Integer versionDFeature;

	@Column(name = KNumerosDVersion.AtributosDEntidad.VERSIONFIX, nullable = false)
	private Integer versionDFix;

	@Column(name = KNumerosDVersion.AtributosDEntidad.VERDHOTFIX, nullable = false)
	private Integer versionDHotfix;

	@Column(name = KNumerosDVersion.AtributosDEntidad.SWVRELEASE, nullable = false)
	private Boolean swRelease;

	@Column(name = KNumerosDVersion.AtributosDEntidad.SWDEPRECAT, nullable = false)
	private Boolean swDeprecated;

	@ManyToOne
	@JoinColumn(name = KVersionesDProyecto.KVersionDProyecto.AtributosDEntidad.IDVERPROYT)
	private VersionDProyecto versionDProyecto;

	NumeroDVersion() {

	}

	public static NumeroDVersion getCreateInstancia(String numeroDVersion, String tipoDVersionDProyecto, Boolean swRelease) throws Throwable {
		ADNumeroDVersion adatos = new ADNumeroDVersion();
		NumeroDVersion instancia = adatos.getNumeroDVersion(numeroDVersion, tipoDVersionDProyecto);
		if (instancia == null) {
			IdConexion idConexion = IdConexion.getInstancia(IdConexion.CONEXCONFI);
			String idDConexion = idConexion.getIdDConexion();
			ADNumeroDVersion adatosTX = new ADNumeroDVersion(idDConexion);
			adatosTX.createNumeroDVersion(numeroDVersion, tipoDVersionDProyecto, swRelease);
			idConexion.doCommit();
			idConexion.liberarConexion();
			instancia = adatos.getNumeroDVersion(numeroDVersion, tipoDVersionDProyecto);
		}
		return instancia;
	}

	public static void mergeNumeroDVersion(String idDConexion, NumeroDVersion numeroDVersion) throws Throwable {
		ADNumeroDVersion adatos = new ADNumeroDVersion(idDConexion);
		adatos.updateNumeroDVersion(numeroDVersion);
	}

	public Long getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}

	public Integer getNumeroDSituacion() {
		return numeroDSituacion;
	}

	public void setNumeroDSituacion(Integer numeroDSituacion) {
		this.numeroDSituacion = numeroDSituacion;
	}

	public Integer getVersionDRelease() {
		return versionDRelease;
	}

	public void setVersionDRelease(Integer versionDRelease) {
		this.versionDRelease = versionDRelease;
	}

	public Integer getVersionDFeature() {
		return versionDFeature;
	}

	public void setVersionDFeature(Integer versionDFeature) {
		this.versionDFeature = versionDFeature;
	}

	public Integer getVersionDFix() {
		return versionDFix;
	}

	public void setVersionDFix(Integer versionDFix) {
		this.versionDFix = versionDFix;
	}

	public Integer getVersionDHotfix() {
		return versionDHotfix;
	}

	public void setVersionDHotfix(Integer versionDHotfix) {
		this.versionDHotfix = versionDHotfix;
	}

	public Boolean getSwRelease() {
		return swRelease;
	}

	public void setSwRelease(Boolean swRelease) {
		this.swRelease = swRelease;
	}

	public Boolean getSwDeprecated() {
		return swDeprecated;
	}

	public void setSwDeprecated(Boolean swDeprecated) {
		this.swDeprecated = swDeprecated;
	}

	public VersionDProyecto getVersionDProyecto() {
		return versionDProyecto;
	}

	public void setVersionDProyecto(VersionDProyecto versionDProyecto) {
		this.versionDProyecto = versionDProyecto;
	}

	@Override
	public int compareTo(NumeroDVersion arg0) {
		if (getVersionDRelease() > arg0.getVersionDRelease())
			return 1;
		else if (getVersionDRelease() < arg0.getVersionDRelease())
			return -1;
		else {
			if (getVersionDFeature() > arg0.getVersionDFeature())
				return 1;
			else if (getVersionDFeature() < arg0.getVersionDFeature())
				return -1;
			else {
				if (getVersionDFix() > arg0.getVersionDFix())
					return 1;
				else if (getVersionDFix() < arg0.getVersionDFix())
					return -1;
				else {
					if (getVersionDHotfix() > arg0.getVersionDHotfix())
						return 1;
					else if (getVersionDHotfix() < arg0.getVersionDHotfix())
						return -1;
				}
			}
		}
		return 0;
	}

}
