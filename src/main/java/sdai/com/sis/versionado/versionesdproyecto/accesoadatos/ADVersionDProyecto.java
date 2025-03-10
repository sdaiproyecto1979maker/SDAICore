package sdai.com.sis.versionado.versionesdproyecto.accesoadatos;

import java.util.Date;

import sdai.com.sis.accesoadatos.AbstractAccesoADatos;
import sdai.com.sis.accesoadatos.IdQuery;
import sdai.com.sis.seguridad.usuarios.KUsuarios;
import sdai.com.sis.utilidades.Fecha;
import sdai.com.sis.versionado.versionesdproyecto.KVersionesDProyecto;

/**
 * @date 09/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class ADVersionDProyecto extends AbstractAccesoADatos {

	ADVersionDProyecto() throws Throwable {
		super();
		marcarEntornoDConfiguracion();
	}
	
	ADVersionDProyecto(String idDConexion) throws Throwable {
		super(idDConexion);
	}

	VersionDProyecto getVersionDProyecto(String tipoDVersionDProyecto) throws Throwable {
		IdQuery idQuery = new IdQuery(KVersionesDProyecto.KVersionDProyecto.NamedQueries.SVRPRY0000);
		idQuery.addParametroDQuery(KVersionesDProyecto.KVersionDProyecto.AtributosDEntidad.TIPOVRSPRY, tipoDVersionDProyecto);
		VersionDProyecto entidad = (VersionDProyecto) ejecutarConsultaSimple(idQuery);
		return entidad;
	}

	void createVersionDProyecto(String tipoDVersionDProyecto) throws Throwable {
		VersionDProyecto entidad = new VersionDProyecto();
		entidad.setTipoDVersionDProyecto(tipoDVersionDProyecto);
		entidad.setUsuarioDAuditoria(KUsuarios.UsuariosDSistema.USUAREXPLO);
		Fecha fechaDSistema = Fecha.getFechaDSistema();
		Date date = fechaDSistema.toDate();
		entidad.setFechaDAuditoria(date);
		ejecutarPersist(entidad);
	}

}
