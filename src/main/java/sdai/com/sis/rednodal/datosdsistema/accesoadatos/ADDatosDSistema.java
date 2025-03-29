package sdai.com.sis.rednodal.datosdsistema.accesoadatos;

import org.w3c.dom.Node;

import sdai.com.sis.accesoadatos.AbstractAccesoADatos;
import sdai.com.sis.accesoadatos.AbstractEntidadCFG;
import sdai.com.sis.accesoadatos.IAccesoADatosCFG;
import sdai.com.sis.cacchesdsistema.KeyCache;
import sdai.com.sis.cacchesdsistema.contenedores.CacheDRednodal;
import sdai.com.sis.conexiones.IdQuery;
import sdai.com.sis.rednodal.datosdsistema.KDatosDSistema;
import sdai.com.sis.utilidades.Reflexion;
import sdai.com.sis.utilidades.Util;
import sdai.com.sis.versionado.numerosdversion.NumerosDVersionUtil;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.versionado.proyectosdaplicacion.ProyectosDAplicacion;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 13/03/2025
 * @since 1.0.0.0-RELEASE
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
				if (existenDiferencias(node, codigoDProyectoDAplicacion, situacionDDatoDSistema))
					createNewSituacionDDatoDSistema(datoDSistema, node, codigoDProyectoDAplicacion);
			} else {
				datoDSistema = createNewDatoDSistema(node, codigoDProyectoDAplicacion);
				createNewSituacionDDatoDSistema(datoDSistema, node, codigoDProyectoDAplicacion);
			}
		}
	}

	private DatoDSistema createNewDatoDSistema(Node root, String codigoDProyectoDAplicacion) throws Exception {
		ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
		NumeroDVersion numeroDVersion = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
		Integer numeroDSituacion = Integer.valueOf(1);
		DatoDSistema datoDSistema = (DatoDSistema) Reflexion.createInstancia(DatoDSistema.class);
		datoDSistema.addNode(numeroDVersion, numeroDSituacion, root);
		String codigoDDato = datoDSistema.getCodigoDDato();
		KeyCache keyCache = KeyCache.getInstancia(DatoDSistema.class, Boolean.valueOf(false), codigoDDato);
		CacheDRednodal.almacenarInstancia(keyCache, datoDSistema);
		return datoDSistema;
	}

	private void createNewSituacionDDatoDSistema(DatoDSistema datoDSistema, Node root, String codigoDProyectoDAplicacion) throws Exception {
		ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
		NumeroDVersion numeroDVersion = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
		Integer numeroDSituacion = datoDSistema.getSituacionesDDatoDSistema().size() + 1;
		SituacionDDatoDSistema situacionDDatoDSistema = (SituacionDDatoDSistema) Reflexion.createInstancia(SituacionDDatoDSistema.class);
		situacionDDatoDSistema.addNode(numeroDVersion, numeroDSituacion, root);
		situacionDDatoDSistema.setDatoDSistema(datoDSistema);
		datoDSistema.setSituacionDDatoDSistema(situacionDDatoDSistema);
		datoDSistema.getSituacionesDDatoDSistema().add(situacionDDatoDSistema);
		String codigoDDato = datoDSistema.getCodigoDDato();
		KeyCache keyCache = KeyCache.getInstancia(SituacionDDatoDSistema.class, Boolean.valueOf(false), codigoDDato);
		CacheDRednodal.almacenarInstancia(keyCache, situacionDDatoDSistema);
	}

	private Boolean existenDiferencias(Node root, String codigoDProyectoDAplicacion, SituacionDDatoDSistema situacionDDatoDSistema) throws Exception {
		ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
		NumeroDVersion numeroDVersion = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
		Integer numeroDSituacion = Integer.valueOf(1);
		SituacionDDatoDSistema newSituacionDDatoDSistema = (SituacionDDatoDSistema) Reflexion.createInstancia(SituacionDDatoDSistema.class);
		newSituacionDDatoDSistema.addNode(numeroDVersion, numeroDSituacion, root);
		if (!situacionDDatoDSistema.getDescripcionDDato().equals(newSituacionDDatoDSistema.getDescripcionDDato()))
			return Boolean.valueOf(true);
		if (!situacionDDatoDSistema.getSwEntidadActiva().equals(newSituacionDDatoDSistema.getSwEntidadActiva()))
			return Boolean.valueOf(true);
		return Boolean.valueOf(false);
	}

	DatoDSistema getDatoDSistema(String codigoDDato) throws Exception {
		IdQuery idQuery = new IdQuery(KDatosDSistema.KDatoDSistema.NamedQueries.SDASIS0000);
		idQuery.addParametroDQuery(KDatosDSistema.KDatoDSistema.AtributosDEntidad.CODIGODATO, codigoDDato);
		DatoDSistema datoDSistema = (DatoDSistema) ejecutarConsultaSimple(idQuery);
		return datoDSistema;
	}

	SituacionDDatoDSistema getSituacionDDatoDSistema(String codigoDDato) throws Exception {
		DatoDSistema datoDSistema = DatoDSistema.getInstancia(codigoDDato);
		SituacionDDatoDSistema[] situacionesDDatoDSistema = datoDSistema.getSituacionesDDatoDSistema().toArray(new SituacionDDatoDSistema[0]);
		Util.ordenar(situacionesDDatoDSistema, AbstractEntidadCFG.NUMVERSDES);
		for (SituacionDDatoDSistema situacionDDatoDSistema : situacionesDDatoDSistema) {
			NumeroDVersion numeroDVersion = situacionDDatoDSistema.getNumeroDVersion();
			if (NumerosDVersionUtil.isVersionDElementoValida(numeroDVersion))
				return situacionDDatoDSistema;
		}
		return null;
	}

}
