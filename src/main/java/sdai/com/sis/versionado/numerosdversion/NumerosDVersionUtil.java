package sdai.com.sis.versionado.numerosdversion;

import sdai.com.sis.utilidades.Util;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.versionado.proyectosdaplicacion.IProyecto;
import sdai.com.sis.versionado.proyectosdaplicacion.ProyectosDAplicacion;
import sdai.com.sis.versionado.proyectosdaplicacion.accesoadatos.ProyectoDAplicacion;

/**
 * @date 12/03/2025
 * @since 1.0.0.0-RELEASE
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
		String[] palabrasGeneral = Util.separarCadena(cadena, Character.valueOf('-'));
		if (palabrasGeneral.length == 1) {
			palabrasGeneral = Util.separarCadena(palabrasGeneral[0], Character.valueOf('.'));
			if (palabrasGeneral.length < 4)
				// TODO: Corregir cuando se desarrolla el mutliidioma
				throw new Exception("El número de versión enviado no es correcto.");
			NumeroDVersionAux numeroDVersionAux = new NumeroDVersionAux(palabrasGeneral);
			return numeroDVersionAux;
		} else {
			palabrasGeneral = Util.separarCadena(cadena, Character.valueOf('.'));
			if (palabrasGeneral.length < 4)
				// TODO: Corregir cuando se desarrolla el mutliidioma
				throw new Exception("El número de versión enviado no es correcto.");
			NumeroDVersionAux numeroDVersionAux = new NumeroDVersionAux(palabrasGeneral);
			return numeroDVersionAux;
		}
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

	public static Integer getVersionRelease(String cadena) throws Exception {
		NumeroDVersionAux numeroDVersionAux = NumerosDVersionUtil.createNumeroDVersion(cadena);
		return numeroDVersionAux.getVersionDRelease();
	}

	public static Integer getVersionFeature(String cadena) throws Exception {
		NumeroDVersionAux numeroDVersionAux = NumerosDVersionUtil.createNumeroDVersion(cadena);
		return numeroDVersionAux.getVersionDFeature();
	}

	public static Integer getVersionFix(String cadena) throws Exception {
		NumeroDVersionAux numeroDVersionAux = NumerosDVersionUtil.createNumeroDVersion(cadena);
		return numeroDVersionAux.getVersionDFix();
	}

	public static Integer getVersionHotfix(String cadena) throws Exception {
		NumeroDVersionAux numeroDVersionAux = NumerosDVersionUtil.createNumeroDVersion(cadena);
		return numeroDVersionAux.getVersionDHotfix();
	}

	public static String getCadenaNumeroDVersion(NumeroDVersion numeroDVersion) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(numeroDVersion.getVersionDRelease());
		stringBuilder.append(".");
		stringBuilder.append(numeroDVersion.getVersionDFeature());
		stringBuilder.append(".");
		stringBuilder.append(numeroDVersion.getVersionDFix());
		stringBuilder.append(".");
		stringBuilder.append(numeroDVersion.getVersionDHotfix());
		stringBuilder.append("-");
		stringBuilder.append(numeroDVersion.getSwRelease() ? "RELEASE" : "SNAPSHOT");
		return stringBuilder.toString();
	}

	public static Boolean isVersionDElementoValida(NumeroDVersion numeroDVersion) throws Exception {
		ProyectoDAplicacion proyectoDAplicacion = numeroDVersion.getProyectoDAplicacion();
		String codigoDProyectoDAplicacion = proyectoDAplicacion.getCodigoDProyectoDAplicacion();
		ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
		NumeroDVersion numeroDVersionDProyecto = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
		return numeroDVersion.getNumeroDSituacion() <= numeroDVersionDProyecto.getNumeroDSituacion();
	}

	public static Boolean isNumeroDVersionMayor(NumeroDVersion numeroDVersion1, NumeroDVersion numeroDVersion2) {
		if (NumerosDVersionUtil.isMismoNumeroDVersion(numeroDVersion1, numeroDVersion2).equals(Boolean.valueOf(false))
				&& NumerosDVersionUtil.isNumeroDVersionMenor(numeroDVersion1, numeroDVersion2).equals(Boolean.valueOf(false)))
			return Boolean.valueOf(true);
		return Boolean.valueOf(false);
	}

	public static Boolean isNumeroDVersionMenor(NumeroDVersion numeroDVersion1, NumeroDVersion numeroDVersion2) {
		if (NumerosDVersionUtil.isMismoNumeroDVersion(numeroDVersion1, numeroDVersion2).equals(Boolean.valueOf(false))) {
			if (numeroDVersion1.getVersionDRelease() > numeroDVersion2.getVersionDRelease())
				return Boolean.valueOf(false);
			if (numeroDVersion1.getVersionDFeature() > numeroDVersion2.getVersionDFeature())
				return Boolean.valueOf(false);
			if (numeroDVersion1.getVersionDFix() > numeroDVersion2.getVersionDFix())
				return Boolean.valueOf(false);
			if (numeroDVersion1.getVersionDHotfix() > numeroDVersion2.getVersionDHotfix())
				return Boolean.valueOf(false);
			return Boolean.valueOf(true);
		}
		return Boolean.valueOf(false);
	}

	public static Boolean isMismoNumeroDVersion(NumeroDVersion numeroDVersion1, NumeroDVersion numeroDVersion2) {
		if (!numeroDVersion1.getVersionDRelease().equals(numeroDVersion2.getVersionDRelease()))
			return Boolean.valueOf(false);
		if (!numeroDVersion1.getVersionDFeature().equals(numeroDVersion2.getVersionDFeature()))
			return Boolean.valueOf(false);
		if (!numeroDVersion1.getVersionDFix().equals(numeroDVersion2.getVersionDFix()))
			return Boolean.valueOf(false);
		if (!numeroDVersion1.getVersionDHotfix().equals(numeroDVersion2.getVersionDHotfix()))
			return Boolean.valueOf(false);
		return Boolean.valueOf(true);
	}

}
