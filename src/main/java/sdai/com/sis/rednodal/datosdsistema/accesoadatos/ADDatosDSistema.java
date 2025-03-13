package sdai.com.sis.rednodal.datosdsistema.accesoadatos;

import java.util.Collections;
import java.util.List;

import org.w3c.dom.Node;

import sdai.com.sis.accesoadatos.AbstractAccesoADatos;
import sdai.com.sis.accesoadatos.IAccesoADatosCFG;
import sdai.com.sis.aplicaciones.KAplicaciones;
import sdai.com.sis.cacchesdsistema.KeyCache;
import sdai.com.sis.conexiones.IdQuery;
import sdai.com.sis.rednodal.CacheDRednodal;
import sdai.com.sis.rednodal.datosdsistema.KDatosDSistema;
import sdai.com.sis.versionado.numerosdversion.NumerosDVersionUtil;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.versionado.proyectosdaplicacion.ProyectosDAplicacion;
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
	public void generateElementosDCache() throws Exception {
		DatoDSistema[] datosDSistema = getDatosDSistema();
		for (DatoDSistema datoDSistema : datosDSistema) {
			String codigoDDato = datoDSistema.getCodigoDDato();
			KeyCache keyCache = KeyCache.getInstancia(DatoDSistema.class, codigoDDato);
			CacheDRednodal.almacenarInstancia(keyCache, datosDSistema);
			SituacionDDatoDSistema.getInstancia(codigoDDato);
		}
	}

	@Override
	public void generateElementosVersionEnCurso(String codigoDProyectoDAplicacion, Node[] nodes) throws Exception {
		for (Node node : nodes) {
			String codigoDDato = DocumentoXML.getStringValueNodeDescendencia(node, KDatosDSistema.KDatoDSistema.AtributosDEntidad.CODIGODATO);
			KeyCache keyCache = KeyCache.getInstancia(DatoDSistema.class, codigoDDato);
			if (CacheDRednodal.existeInstancia(keyCache)) {
				SituacionDDatoDSistema situacionDDatoDSistema = SituacionDDatoDSistema.getInstancia(codigoDDato);
				if (situacionDDatoDSistema == null) {
					situacionDDatoDSistema = getSituacionDDatoDSistema(codigoDDato, Boolean.valueOf(false));
					if (existenCambios(situacionDDatoDSistema, node)) {
						DatoDSistema datoDSistema = DatoDSistema.getInstancia(codigoDDato);
						ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
						NumeroDVersion numeroDVersion = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
						SituacionDDatoDSistema instancia = new SituacionDDatoDSistema();
						instancia.addNode(numeroDVersion, Integer.valueOf(1), node);
						instancia.setDatoDSistema(datoDSistema);
						datoDSistema.setSituacionDDatoDSistema(situacionDDatoDSistema);
						datoDSistema.getSituacionesDDatoDSistema().add(situacionDDatoDSistema);
						keyCache = KeyCache.getInstancia(SituacionDDatoDSistema.class, codigoDDato);
						CacheDRednodal.almacenarInstancia(keyCache, situacionDDatoDSistema);
					}
				}
			} else {
				ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
				NumeroDVersion numeroDVersion = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
				DatoDSistema datoDSistema = new DatoDSistema();
				datoDSistema.addNode(numeroDVersion, Integer.valueOf(1), node);
				SituacionDDatoDSistema situacionDDatoDSistema = new SituacionDDatoDSistema();
				situacionDDatoDSistema.addNode(numeroDVersion, Integer.valueOf(1), node);
				situacionDDatoDSistema.setDatoDSistema(datoDSistema);
				datoDSistema.setSituacionDDatoDSistema(situacionDDatoDSistema);
				datoDSistema.getSituacionesDDatoDSistema().add(situacionDDatoDSistema);
				keyCache = KeyCache.getInstancia(DatoDSistema.class, codigoDDato);
				CacheDRednodal.almacenarInstancia(keyCache, datoDSistema);
				keyCache = KeyCache.getInstancia(SituacionDDatoDSistema.class, codigoDDato);
				CacheDRednodal.almacenarInstancia(keyCache, situacionDDatoDSistema);
			}
		}
	}

	private Boolean existenCambios(SituacionDDatoDSistema situacionDDatoDSistema, Node root) {
		if (!situacionDDatoDSistema.getDescripcionDDato().equals(DocumentoXML.getStringValueNodeDescendencia(root, KDatosDSistema.KSituacionDDatoDSistema.AtributosDEntidad.DESCRDDATO)))
			return Boolean.valueOf(true);
		if (!situacionDDatoDSistema.getSwEntidadActiva().equals(DocumentoXML.getBooleanValueNodeDescendencia(root, KAplicaciones.AtributosDEntidad.SWENTACTIV)))
			return Boolean.valueOf(true);
		return Boolean.valueOf(false);
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

	SituacionDDatoDSistema getSituacionDDatoDSistema(String codigoDDato) throws Exception {
		return getSituacionDDatoDSistema(codigoDDato, Boolean.valueOf(true));
	}

	SituacionDDatoDSistema getSituacionDDatoDSistema(String codigoDDato, Boolean swValidarActivo) throws Exception {
		DatoDSistema datoDSistema = getDatoDSistema(codigoDDato);
		List<SituacionDDatoDSistema> situacionesDDatoDSistema = datoDSistema.getSituacionesDDatoDSistema();
		Collections.sort(situacionesDDatoDSistema);
		for (Integer i = Integer.valueOf(situacionesDDatoDSistema.size()); i > 0; i--) {
			SituacionDDatoDSistema situacionDDatoDSistema = situacionesDDatoDSistema.get(i);
			NumeroDVersion numeroDVersion = situacionDDatoDSistema.getNumeroDVersion();
			if (NumerosDVersionUtil.isVersionDElementoValida(numeroDVersion).equals(Boolean.valueOf(true))) {
				return swValidarActivo.equals(Boolean.valueOf(false)) ? situacionDDatoDSistema : situacionDDatoDSistema.getSwEntidadActiva() ? situacionDDatoDSistema : null;
			}
		}
		return null;
	}

}
