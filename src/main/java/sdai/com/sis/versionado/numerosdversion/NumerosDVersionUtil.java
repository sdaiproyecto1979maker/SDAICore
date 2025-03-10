package sdai.com.sis.versionado.numerosdversion;

import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;

/**
 * @date 09/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class NumerosDVersionUtil {

	public static NumeroDVersion createNumeroDVersion(String numeroVersion, String tipoDVersionDProyecto, Boolean swRelease) throws Throwable {
		NumeroDVersion numeroDVersion = NumeroDVersion.getCreateInstancia(numeroVersion, tipoDVersionDProyecto, swRelease);
		return numeroDVersion;
	}
}
