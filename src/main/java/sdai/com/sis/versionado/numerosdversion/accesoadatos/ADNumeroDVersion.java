package sdai.com.sis.versionado.numerosdversion.accesoadatos;

import java.util.Date;

import sdai.com.sis.accesoadatos.AbstractAccesoADatos;
import sdai.com.sis.accesoadatos.IdQuery;
import sdai.com.sis.seguridad.usuarios.KUsuarios;
import sdai.com.sis.utilidades.Fecha;
import sdai.com.sis.utilidades.Transform;
import sdai.com.sis.utilidades.Util;
import sdai.com.sis.versionado.numerosdversion.KNumerosDVersion;
import sdai.com.sis.versionado.versionesdproyecto.KVersionesDProyecto;
import sdai.com.sis.versionado.versionesdproyecto.accesoadatos.VersionDProyecto;

/**
 * @date 09/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class ADNumeroDVersion extends AbstractAccesoADatos {

	ADNumeroDVersion() throws Throwable {
		super();
		marcarEntornoDConfiguracion();
	}

	ADNumeroDVersion(String idDConexion) throws Throwable {
		super(idDConexion);
	}

	NumeroDVersion getNumeroDVersion(String numeroDVersion, String tipoDVersionDProyecto) throws Throwable {
		String[] versiones = Util.separarCadena(numeroDVersion, '.');
		IdQuery idQuery = new IdQuery(KNumerosDVersion.NamedQueries.SNUVRS0000);
		idQuery.addParametroDQuery(KNumerosDVersion.AtributosDEntidad.VERRELEASE, Transform.toInteger(versiones[0]));
		idQuery.addParametroDQuery(KNumerosDVersion.AtributosDEntidad.VERFEATURE, Transform.toInteger(versiones[1]));
		idQuery.addParametroDQuery(KNumerosDVersion.AtributosDEntidad.VERSIONFIX, Transform.toInteger(versiones[2]));
		idQuery.addParametroDQuery(KNumerosDVersion.AtributosDEntidad.VERDHOTFIX, Transform.toInteger(versiones[3]));
		idQuery.addParametroDQuery(KVersionesDProyecto.KVersionDProyecto.AtributosDEntidad.TIPOVRSPRY, tipoDVersionDProyecto);
		NumeroDVersion entidad = (NumeroDVersion) ejecutarConsultaSimple(idQuery);
		return entidad;
	}

	void createNumeroDVersion(String numeroDVersion, String tipoDVersionDProyecto, Boolean swRelease) throws Throwable {
		String[] versiones = Util.separarCadena(numeroDVersion, '.');
		NumeroDVersion entidad = new NumeroDVersion();
		entidad.setVersionDRelease(Integer.valueOf(versiones[0]));
		entidad.setVersionDFeature(Integer.valueOf(versiones[1]));
		entidad.setVersionDFix(Integer.valueOf(versiones[2]));
		entidad.setVersionDHotfix(Integer.valueOf(versiones[3]));
		entidad.setSwRelease(swRelease);
		entidad.setSwDeprecated(Boolean.valueOf(false));
		entidad.setUsuarioDAuditoria(KUsuarios.UsuariosDSistema.USUAREXPLO);
		Fecha fechaDSistema = Fecha.getFechaDSistema();
		Date date = fechaDSistema.toDate();
		entidad.setFechaDAuditoria(date);
		VersionDProyecto versionDProyecto = VersionDProyecto.getCreateInstancia(tipoDVersionDProyecto);
		entidad.setVersionDProyecto(versionDProyecto);
		Integer numeroDSituacion = versionDProyecto.getNumerosDVersion().size();
		entidad.setNumeroDSituacion(numeroDSituacion + 1);
		versionDProyecto.getNumerosDVersion().add(entidad);
		ejecutarPersist(entidad);
	}

	void updateNumeroDVersion(NumeroDVersion numeroDVersion) throws Throwable {
		ejecutarMerge(numeroDVersion);
	}

}
