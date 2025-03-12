package sdai.com.sis.versionado.proyectosdaplicacion.accesoadatos;

import sdai.com.sis.accesoadatos.AbstractAccesoADatos;
import sdai.com.sis.conexiones.IdQuery;
import sdai.com.sis.versionado.KVersionado;

/**
 * @date 12/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class ADProyectoDAplicacion extends AbstractAccesoADatos {

	ADProyectoDAplicacion() {
		super();
		marcarEntornoDConfiguracion();
	}

	ADProyectoDAplicacion(String idDConexion) throws Exception {
		super(idDConexion);
	}

	ProyectoDAplicacion getProyectoDAplicacion(String codigoDProyectoDAplicacion) throws Exception {
		IdQuery idQuery = new IdQuery(KVersionado.KProyectosDAplicacion.KProyectoDAplicacion.NamedQueries.SPRAPL0000);
		idQuery.addParametroDQuery(KVersionado.KProyectosDAplicacion.KProyectoDAplicacion.AtributosDEntidad.CODPROYECT, codigoDProyectoDAplicacion);
		ProyectoDAplicacion proyectoDAplicacion = (ProyectoDAplicacion) ejecutarConsultaSimple(idQuery);
		return proyectoDAplicacion;
	}

	void createProyectoDAplicacion(ProyectoDAplicacion proyectoDAplicacion) throws Exception {
		ejecutarPersist(proyectoDAplicacion);
	}

	void updateProyectoDAplicacion(ProyectoDAplicacion proyectoDAplicacion) throws Exception {
		String codigoDProyectoDAplicacion = proyectoDAplicacion.getCodigoDProyectoDAplicacion();
		proyectoDAplicacion = getProyectoDAplicacion(codigoDProyectoDAplicacion);
		ejecutarMerge(proyectoDAplicacion);
	}

}
