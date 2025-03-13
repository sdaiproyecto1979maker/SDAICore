package sdai.com.sis.rednodal.datosdsistema.accesoadatos;

import sdai.com.sis.accesoadatos.AbstractAccesoADatos;
import sdai.com.sis.conexiones.IdQuery;
import sdai.com.sis.rednodal.datosdsistema.KDatosDSistema;

/**
 * @date 13/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class ADDatosDSistema extends AbstractAccesoADatos {

	ADDatosDSistema() {
		super();
		marcarEntornoDConfiguracion();
	}

	ADDatosDSistema(String idDConexion) throws Exception {
		super(idDConexion);
	}

	DatoDSistema getDatoDSistema(String codigoDDato) throws Exception {
		IdQuery idQuery = new IdQuery(KDatosDSistema.KDatoDSistema.NamedQueries.SDASIS0000);
		idQuery.addParametroDQuery(KDatosDSistema.KDatoDSistema.AtributosDEntidad.CODIGODATO, codigoDDato);
		DatoDSistema datoDSistema = (DatoDSistema) ejecutarConsultaSimple(idQuery);
		return datoDSistema;
	}

}
