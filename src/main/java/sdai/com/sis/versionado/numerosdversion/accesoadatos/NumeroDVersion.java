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
import sdai.com.sis.utilidades.Fecha;
import sdai.com.sis.versionado.KVersionado;
import sdai.com.sis.versionado.numerosdversion.NumerosDVersionUtil;
import sdai.com.sis.versionado.proyectosdaplicacion.IProyecto;
import sdai.com.sis.versionado.proyectosdaplicacion.accesoadatos.ProyectoDAplicacion;

/**
 * @date 12/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Entity
@Table(name = KVersionado.KNumerosDVersion.NOMBRTABLA)
@NamedQueries({
		@NamedQuery(name = KVersionado.KNumerosDVersion.NamedQueries.SNUVER0000, query = "SELECT N FROM NumeroDVersion N WHERE N.versionDRelease =:VERRELEASE AND N.versionDFeature =:VERFEATURE AND N.versionDFix =:VERSIONFIX AND N.versionDHotfix =:VERSHOTFIX AND N.proyectoDAplicacion.codigoDProyectoDAplicacion =:CODPROYECT"),
		@NamedQuery(name = KVersionado.KNumerosDVersion.NamedQueries.SNUVER0001, query = "SELECT N FROM NumeroDVersion N WHERE N.identificador =:IDNUMEVERS ") })
public final class NumeroDVersion extends AbstractEntidad implements Comparable<NumeroDVersion> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = KVersionado.KNumerosDVersion.AtributosDEntidad.IDNUMEVERS)
	private Long identificador;

	@Column(name = KVersionado.KNumerosDVersion.AtributosDEntidad.NUMERSITUA, nullable = false)
	private Integer numeroDSituacion;

	@Column(name = KVersionado.KNumerosDVersion.AtributosDEntidad.VERRELEASE, nullable = false)
	private Integer versionDRelease;

	@Column(name = KVersionado.KNumerosDVersion.AtributosDEntidad.VERFEATURE, nullable = false)
	private Integer versionDFeature;

	@Column(name = KVersionado.KNumerosDVersion.AtributosDEntidad.VERSIONFIX, nullable = false)
	private Integer versionDFix;

	@Column(name = KVersionado.KNumerosDVersion.AtributosDEntidad.VERSHOTFIX, nullable = false)
	private Integer versionDHotfix;

	@Column(name = KVersionado.KNumerosDVersion.AtributosDEntidad.SWVRELEASE, nullable = false)
	private Boolean swRelease;

	@Column(name = KVersionado.KNumerosDVersion.AtributosDEntidad.SWVINSTALL, nullable = false)
	private Boolean swInstalada;

	@ManyToOne
	@JoinColumn(name = KVersionado.KProyectosDAplicacion.KProyectoDAplicacion.AtributosDEntidad.IDPYTAPLIC)
	private ProyectoDAplicacion proyectoDAplicacion;

	NumeroDVersion() {

	}

	public static NumeroDVersion getInstancia(IProyecto proyecto) throws Exception {
		ADNumeroDVersion adatos = new ADNumeroDVersion();
		NumeroDVersion instancia = adatos.getNumeroDVersion(proyecto);
		return instancia;
	}

	// TODO: Eliminar este metodo cuando se prepare el nodo de reflexion y la
	// fabrica
	public static void createInstancia(IProyecto proyecto, ProyectoDAplicacion proyectoDAplicacion) throws Exception {
		IdConexion idConexion = IdConexion.getInstancia(IdConexion.CONEXCONFI);
		String idDConexion = idConexion.getIdDConexion();
		NumeroDVersion instancia = new NumeroDVersion();
		instancia.setNumeroDSituacion(Integer.valueOf(0));
		instancia.setVersionDRelease(NumerosDVersionUtil.getVersionRelease(proyecto));
		instancia.setVersionDFeature(NumerosDVersionUtil.getVersionFeature(proyecto));
		instancia.setVersionDFix(NumerosDVersionUtil.getVersionFix(proyecto));
		instancia.setVersionDHotfix(NumerosDVersionUtil.getVersionHotfix(proyecto));
		instancia.setSwRelease(NumerosDVersionUtil.isVersionRelease(proyecto));
		instancia.setSwInstalada(Boolean.valueOf(false));
		instancia.setUsuarioDAuditoria("USUAREXPLO");
		instancia.setFechaDAuditoria(Fecha.getFechaDSistema().toDate());
		instancia.setProyectoDAplicacion(proyectoDAplicacion);
		ADNumeroDVersion adatos = new ADNumeroDVersion(idDConexion);
		adatos.createNumeroDVersion(instancia);
		idConexion.doCommit();
		idConexion.liberarConexion();
	}

	// TODO: Eliminar este metodo cuando se prepare el nodo de reflexion y la
	// fabrica
	public static void updateInstancia(NumeroDVersion instancia) throws Exception {
		IdConexion idConexion = IdConexion.getInstancia(IdConexion.CONEXCONFI);
		String idDConexion = idConexion.getIdDConexion();
		ADNumeroDVersion adatos = new ADNumeroDVersion(idDConexion);
		adatos.updateNumeroDVersion(instancia);
		idConexion.doCommit();
		idConexion.liberarConexion();
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

	public Boolean getSwInstalada() {
		return swInstalada;
	}

	public void setSwInstalada(Boolean swInstalada) {
		this.swInstalada = swInstalada;
	}

	public ProyectoDAplicacion getProyectoDAplicacion() {
		return proyectoDAplicacion;
	}

	public void setProyectoDAplicacion(ProyectoDAplicacion proyectoDAplicacion) {
		this.proyectoDAplicacion = proyectoDAplicacion;
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
