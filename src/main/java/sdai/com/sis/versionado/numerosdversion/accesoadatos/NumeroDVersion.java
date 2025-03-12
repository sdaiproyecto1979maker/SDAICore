package sdai.com.sis.versionado.numerosdversion.accesoadatos;

import java.io.Serializable;

import jakarta.persistence.Entity;

/**
 * @date 12/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Entity
//TODO: Generar las anotaciones de la tabla
public final class NumeroDVersion implements Serializable {

	private static final long serialVersionUID = 1L;

	// TODO: Generar las anotaciones
	private Long identificador;
	private Integer numeroDSituacion;
	private Integer versionDRelease;
	private Integer versionDFeature;
	private Integer versionDFix;
	private Integer versionDHotfix;
	private Boolean swRelease;
	private Boolean swInstalada;

	NumeroDVersion() {

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

}
