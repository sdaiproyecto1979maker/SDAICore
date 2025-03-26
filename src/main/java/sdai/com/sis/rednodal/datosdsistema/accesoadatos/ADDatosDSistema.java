package sdai.com.sis.rednodal.datosdsistema.accesoadatos;

import java.util.List;

import org.w3c.dom.Node;

import sdai.com.sis.accesoadatos.AbstractAccesoADatos;
import sdai.com.sis.accesoadatos.IAccesoADatosCFG;
import sdai.com.sis.conexiones.IdQuery;
import sdai.com.sis.rednodal.datosdsistema.DatosDSistemaUtil;
import sdai.com.sis.rednodal.datosdsistema.KDatosDSistema;
import sdai.com.sis.versionado.numerosdversion.NumerosDVersionUtil;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.xml.DocumentoXML;

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
	public void generateElementosVersionEnCurso(String codigoDProyectoDAplicacion, Node[] nodes) throws Exception {
		for (Node node : nodes) {
			String codigoDDato = DocumentoXML.getStringValueNodeDescendencia(node, KDatosDSistema.KDatoDSistema.AtributosDEntidad.CODIGODATO);
			DatoDSistema datoDSistema = DatoDSistema.getInstancia(codigoDDato);
			if (datoDSistema != null) {
				SituacionDDatoDSistema situacionDDatoDSistema = SituacionDDatoDSistema.getInstancia(codigoDDato);
				if (DatosDSistemaUtil.existenCambiosEnSituacionDDatoDSistema(situacionDDatoDSistema, node)) {
					DatosDSistemaUtil.createNewSituacionDDatoDSistema(datoDSistema, node, codigoDProyectoDAplicacion);
				}
			} else {
				datoDSistema = DatosDSistemaUtil.createNewDatoDSistema(node, codigoDProyectoDAplicacion);
				DatosDSistemaUtil.createNewSituacionDDatoDSistema(datoDSistema, node, codigoDProyectoDAplicacion);
			}
		}
	}

	DatoDSistema getDatoDSistema(String codigoDDato) throws Exception {
		IdQuery idQuery = new IdQuery(KDatosDSistema.KDatoDSistema.NamedQueries.SDASIS0000);
		idQuery.addParametroDQuery(KDatosDSistema.KDatoDSistema.AtributosDEntidad.CODIGODATO, codigoDDato);
		DatoDSistema datoDSistema = (DatoDSistema) ejecutarConsultaSimple(idQuery);
		return datoDSistema;
	}

	SituacionDDatoDSistema getSituacionDDatoDSistema(String codigoDDato) throws Exception {
		DatoDSistema datoDSistema = DatoDSistema.getInstancia(codigoDDato);
		List<SituacionDDatoDSistema> instancias = datoDSistema.getSituacionesDDatoDSistema();
		for (SituacionDDatoDSistema instancia : instancias) {
			NumeroDVersion numeroDVersion = instancia.getNumeroDVersion();
			Boolean swValida = NumerosDVersionUtil.isVersionDElementoValida(numeroDVersion);
			return swValida ? instancia : null;
		}
		return null;
	}

}
