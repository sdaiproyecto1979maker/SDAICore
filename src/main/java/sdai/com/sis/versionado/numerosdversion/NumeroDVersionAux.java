package sdai.com.sis.versionado.numerosdversion;

import sdai.com.sis.utilidades.Transform;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;

/**
 * @date 12/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class NumeroDVersionAux {

	private final Integer versionDRelease;
	private final Integer versionDFeature;
	private final Integer versionDFix;
	private final Integer versionDHotfix;

	NumeroDVersionAux(String[] palabrasGeneral) {
		this.versionDRelease = Transform.toInteger(palabrasGeneral[0]);
		this.versionDFeature = Transform.toInteger(palabrasGeneral[1]);
		this.versionDFix = Transform.toInteger(palabrasGeneral[2]);
		this.versionDHotfix = Transform.toInteger(palabrasGeneral[3]);
	}

	NumeroDVersionAux(NumeroDVersion numeroDVersion) {
		this.versionDRelease = numeroDVersion.getVersionDRelease();
		this.versionDFeature = numeroDVersion.getVersionDFeature();
		this.versionDFix = numeroDVersion.getVersionDFix();
		this.versionDHotfix = numeroDVersion.getVersionDHotfix();
	}

	public Boolean isMismoNumeroDVersion(NumeroDVersionAux numeroDVersionAux) {
		if (!getVersionDRelease().equals(numeroDVersionAux.getVersionDRelease()))
			return Boolean.valueOf(false);
		if (!getVersionDFeature().equals(numeroDVersionAux.getVersionDFeature()))
			return Boolean.valueOf(false);
		if (!getVersionDFix().equals(numeroDVersionAux.getVersionDFix()))
			return Boolean.valueOf(false);
		if (!getVersionDHotfix().equals(numeroDVersionAux.getVersionDHotfix()))
			return Boolean.valueOf(false);
		return Boolean.valueOf(true);
	}

	public Integer getVersionDRelease() {
		return versionDRelease;
	}

	public Integer getVersionDFeature() {
		return versionDFeature;
	}

	public Integer getVersionDFix() {
		return versionDFix;
	}

	public Integer getVersionDHotfix() {
		return versionDHotfix;
	}

}
