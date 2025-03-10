package sdai.com.sis.versionado.versionesdproyecto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sdai.com.sis.conexiones.IdConexion;
import sdai.com.sis.utilidades.Util;
import sdai.com.sis.versionado.numerosdversion.NumerosDVersionUtil;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.versionado.versionesdproyecto.accesoadatos.VersionDProyecto;

/**
 * @date 09/03/2025
 * @since 0.1.0.0-SNAPSHOT
 * @author Sergio_M
 */
public abstract class VersionesDProyectoUtil {

	public static NumeroDVersion getNumeroDVersion(String numeroVersion, String tipoDVersionDProyecto) throws Throwable {
		if (Util.isCadenaVacia(numeroVersion))
			// TODO: Modificar la descripción del error cuando se desarrolle el multiidioma
			throw new Throwable("El número de versión no puede estar vacio.");
		String[] elementosDNumeroVersion = Util.separarCadena(numeroVersion, Character.valueOf('-'));
		if (elementosDNumeroVersion.length != 2)
			throw new Throwable("El número de versión enviado no es correcto.");
		String cadena = elementosDNumeroVersion[0];
		Boolean swRelease = VersionesDProyectoUtil.isRelease(numeroVersion);
		NumeroDVersion numeroDVersion = NumerosDVersionUtil.createNumeroDVersion(cadena, tipoDVersionDProyecto, swRelease);
		return numeroDVersion;
	}

	public static Boolean isRelease(String numeroVersion) throws Throwable {
		if (Util.isCadenaVacia(numeroVersion))
			// TODO: Modificar la descripción del error cuando se desarrolle el multiidioma
			throw new Throwable("El número de versión no puede estar vacio.");
		String[] elementosDNumeroVersion = Util.separarCadena(numeroVersion, Character.valueOf('-'));
		if (elementosDNumeroVersion.length != 2)
			throw new Throwable("El número de versión enviado no es correcto.");
		String cadena = elementosDNumeroVersion[1];
		return cadena.equals(KVersionesDProyecto.RELEASE);
	}

	public static void ordenarNumerosDVersion(VersionDProyecto versionDProyecto) throws Throwable {
		List<NumeroDVersion> listaUpdates = new ArrayList<NumeroDVersion>();
		List<NumeroDVersion> lista = versionDProyecto.getNumerosDVersion();
		if (lista.size() > Integer.valueOf(1)) {
			Collections.sort(lista);
			for (Integer i = Integer.valueOf(1); i <= lista.size(); i++) {
				NumeroDVersion numeroDVersion = lista.get(i - 1);
				if (!numeroDVersion.getNumeroDSituacion().equals(i)) {
					numeroDVersion.setNumeroDSituacion(i);
					listaUpdates.add(numeroDVersion);
				}
			}
			if (!listaUpdates.isEmpty()) {
				IdConexion idConexion = IdConexion.getInstancia(IdConexion.CONEXCONFI);
				String idDConexion = idConexion.getIdDConexion();
				for(NumeroDVersion numeroDVersion : listaUpdates)
					NumeroDVersion.mergeNumeroDVersion(idDConexion, numeroDVersion);
				idConexion.doCommit();
				idConexion.liberarConexion();
			}
		}
	}

}
