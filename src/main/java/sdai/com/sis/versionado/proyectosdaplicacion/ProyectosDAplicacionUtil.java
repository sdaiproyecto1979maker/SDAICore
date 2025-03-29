package sdai.com.sis.versionado.proyectosdaplicacion;

import java.util.List;

import sdai.com.sis.utilidades.Util;
import sdai.com.sis.versionado.numerosdversion.NumeroDVersionAux;
import sdai.com.sis.versionado.numerosdversion.NumerosDVersionUtil;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.versionado.proyectosdaplicacion.accesoadatos.ProyectoDAplicacion;

/**
 * @date 12/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
public abstract class ProyectosDAplicacionUtil {

	public static Boolean existeNumeroDVersion(IProyecto proyecto, ProyectoDAplicacion proyectoDAplicacion) throws Exception {
		String numeroDVersion = proyecto.getNumeroDVersion();
		if (Util.isCadenaVacia(numeroDVersion))
			// TODO: Corregir cuando se desarrolla el mutliidioma
			throw new Exception("El número de versión no puede ser un campo vacio.");
		NumeroDVersionAux numeroDVersionAuxProyecto = NumerosDVersionUtil.createNumeroDVersion(proyecto);
		List<NumeroDVersion> numerosDVersion = proyectoDAplicacion.getNumerosDVersion();
		for (NumeroDVersion numDVersion : numerosDVersion) {
			NumeroDVersionAux numeroDVersionAux = NumerosDVersionUtil.createNumeroDVersion(numDVersion);
			if (numeroDVersionAux.isMismoNumeroDVersion(numeroDVersionAuxProyecto))
				return Boolean.valueOf(true);
		}
		return Boolean.valueOf(false);
	}

}
