package sdai.com.sis.versionado.numerosdversion;

import sdai.com.sis.utilidades.Util;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.versionado.proyectosdaplicacion.IProyecto;

/**
 * @date 12/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class NumerosDVersionUtil {

	public static NumeroDVersionAux createNumeroDVersion(IProyecto proyecto) throws Exception {
		String numeroDVersion = proyecto.getNumeroDVersion();
		String[] palabrasGeneral = Util.separarCadena(numeroDVersion, Character.valueOf('-'));
		if (palabrasGeneral.length < 2)
			// TODO: Corregir cuando se desarrolla el mutliidioma
			throw new Exception("El número de versión enviado no es correcto.");
		return NumerosDVersionUtil.createNumeroDVersion(palabrasGeneral[0]);
	}

	public static Boolean isVersionRelease(IProyecto proyecto) throws Exception {
		String numeroDVersion = proyecto.getNumeroDVersion();
		String[] palabrasGeneral = Util.separarCadena(numeroDVersion, Character.valueOf('-'));
		if (palabrasGeneral.length < 2)
			// TODO: Corregir cuando se desarrolla el mutliidioma
			throw new Exception("El número de versión enviado no es correcto.");
		return palabrasGeneral[1].equals("RELEASE");
	}

	public static NumeroDVersionAux createNumeroDVersion(NumeroDVersion numeroDVersion) throws Exception {
		return new NumeroDVersionAux(numeroDVersion);
	}

	private static NumeroDVersionAux createNumeroDVersion(String cadena) throws Exception {
		String[] palabrasGeneral = Util.separarCadena(cadena, Character.valueOf('.'));
		if (palabrasGeneral.length < 4)
			// TODO: Corregir cuando se desarrolla el mutliidioma
			throw new Exception("El número de versión enviado no es correcto.");
		NumeroDVersionAux numeroDVersionAux = new NumeroDVersionAux(palabrasGeneral);
		return numeroDVersionAux;
	}

	public static Integer getVersionRelease(IProyecto proyecto) throws Exception {
		NumeroDVersionAux numeroDVersionAux = NumerosDVersionUtil.createNumeroDVersion(proyecto);
		return numeroDVersionAux.getVersionDRelease();
	}

	public static Integer getVersionFeature(IProyecto proyecto) throws Exception {
		NumeroDVersionAux numeroDVersionAux = NumerosDVersionUtil.createNumeroDVersion(proyecto);
		return numeroDVersionAux.getVersionDFeature();
	}

	public static Integer getVersionFix(IProyecto proyecto) throws Exception {
		NumeroDVersionAux numeroDVersionAux = NumerosDVersionUtil.createNumeroDVersion(proyecto);
		return numeroDVersionAux.getVersionDFix();
	}

	public static Integer getVersionHotfix(IProyecto proyecto) throws Exception {
		NumeroDVersionAux numeroDVersionAux = NumerosDVersionUtil.createNumeroDVersion(proyecto);
		return numeroDVersionAux.getVersionDHotfix();
	}

}
