package sdai.com.sis.rednodal.datosdsistema.accesoadatos;

import java.util.Collections;
import java.util.List;

import sdai.com.sis.accesoadatos.AbstractAccesoADatos;
import sdai.com.sis.conexiones.IdQuery;
import sdai.com.sis.rednodal.datosdsistema.KDatosDSistema;
import sdai.com.sis.versionado.numerosdversion.NumerosDVersionUtil;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;

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

	SituacionDDatoDSistema getSituacionDDatoDSistema(String codigoDDato) throws Exception {
		DatoDSistema datoDSistema = getDatoDSistema(codigoDDato);
		List<SituacionDDatoDSistema> situacionesDDatoDSistema = datoDSistema.getSituacionesDDatoDSistema();
		Collections.sort(situacionesDDatoDSistema);
		for (Integer i = Integer.valueOf(situacionesDDatoDSistema.size()); i > 0; i--) {
			SituacionDDatoDSistema situacionDDatoDSistema = situacionesDDatoDSistema.get(i);
			NumeroDVersion numeroDVersion = situacionDDatoDSistema.getNumeroDVersion();
			if (NumerosDVersionUtil.isVersionDElementoValida(numeroDVersion).equals(Boolean.valueOf(true)))
				return situacionDDatoDSistema;
		}
		return null;
	}

}
