package sdai.com.sis.rednodal.datosdsistema.accesoadatos;

import org.w3c.dom.Node;

import sdai.com.sis.accesoadatos.AbstractAccesoADatos;
import sdai.com.sis.accesoadatos.IAccesoADatosCFG;
import sdai.com.sis.conexiones.IdQuery;
import sdai.com.sis.rednodal.datosdsistema.DatosDSistemaUtil;
import sdai.com.sis.rednodal.datosdsistema.KDatosDSistema;

/**
 * @date 13/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class ADDatosDSistema extends AbstractAccesoADatos implements IAccesoADatosCFG {

	ADDatosDSistema() {
		super();
		marcarEntornoDConfiguracion();
	}

	ADDatosDSistema(String idDConexion) throws Exception {
		super(idDConexion);
	}

	@Override
	public void generateElementosDCache() throws Exception {
		DatoDSistema[] datosDSistema = getDatosDSistema();
		DatosDSistemaUtil.generateCacheDDatosDSistema(datosDSistema);
	}

	@Override
	public void generateElementosVersionEnCurso(String codigoDProyectoDAplicacion, Node[] nodes) throws Exception {
		for (Node node : nodes) {
			DatoDSistema datoDSistema = DatosDSistemaUtil.getDatoDSistema(node);
			if (datoDSistema != null) {
				SituacionDDatoDSistema situacionDDatoDSistema = DatosDSistemaUtil.getSituacionDDatoDSistema(datoDSistema);
				if (DatosDSistemaUtil.existenCambiosEnSituacionDDatoDSistema(situacionDDatoDSistema, node))
					DatosDSistemaUtil.createNewSituacionDDatoDSistema(datoDSistema, node, codigoDProyectoDAplicacion);
			} else {

			}
		}
	}

	DatoDSistema getDatoDSistema(String codigoDDato) throws Exception {
		IdQuery idQuery = new IdQuery(KDatosDSistema.KDatoDSistema.NamedQueries.SDASIS0000);
		idQuery.addParametroDQuery(KDatosDSistema.KDatoDSistema.AtributosDEntidad.CODIGODATO, codigoDDato);
		DatoDSistema datoDSistema = (DatoDSistema) ejecutarConsultaSimple(idQuery);
		return datoDSistema;
	}

	DatoDSistema[] getDatosDSistema() throws Exception {
		IdQuery idQuery = new IdQuery(KDatosDSistema.KDatoDSistema.NamedQueries.SDASIS0001);
		DatoDSistema[] datosDSistema = ejecutarConsulta(idQuery).toArray(new DatoDSistema[0]);
		return datosDSistema;
	}

}
