package sdai.com.sis.rednodal.datosdsistema;

import org.w3c.dom.Node;

import sdai.com.sis.aplicaciones.KAplicaciones;
import sdai.com.sis.cacchesdsistema.KeyCache;
import sdai.com.sis.cacchesdsistema.contenedores.CacheDRednodal;
import sdai.com.sis.rednodal.datosdsistema.accesoadatos.DatoDSistema;
import sdai.com.sis.rednodal.datosdsistema.accesoadatos.SituacionDDatoDSistema;
import sdai.com.sis.utilidades.Reflexion;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.versionado.proyectosdaplicacion.ProyectosDAplicacion;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 15/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class DatosDSistemaUtil {

	public static Boolean existenCambiosEnSituacionDDatoDSistema(SituacionDDatoDSistema instancia, Node root) {
		String descripcionDDato = DocumentoXML.getStringValueNodeDescendencia(root, KDatosDSistema.KSituacionDDatoDSistema.AtributosDEntidad.DESCRDDATO);
		Boolean swActiva = DocumentoXML.getBooleanValueNodeDescendencia(root, KAplicaciones.AtributosDEntidad.SWENTACTIV);
		if (!instancia.getDescripcionDDato().equals(descripcionDDato))
			return Boolean.valueOf(true);
		if (!instancia.getSwEntidadActiva().equals(swActiva))
			return Boolean.valueOf(true);
		return Boolean.valueOf(false);
	}

	public static DatoDSistema createNewDatoDSistema(Node root, String codigoDProyectoDAplicacion) throws Exception {
		ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
		NumeroDVersion numeroDVersion = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
		Integer numeroDSituacion = Integer.valueOf(1);
		DatoDSistema datoDSistema = (DatoDSistema) Reflexion.createInstancia(DatoDSistema.class);
		datoDSistema.addNode(numeroDVersion, numeroDSituacion, root);
		String codigoDDato = datoDSistema.getCodigoDDato();
		KeyCache keyCache = KeyCache.getInstancia(SituacionDDatoDSistema.class, Boolean.valueOf(false), codigoDDato);
		CacheDRednodal.almacenarInstancia(keyCache, datoDSistema);
		return datoDSistema;
	}

	public static void createNewSituacionDDatoDSistema(DatoDSistema datoDSistema, Node root, String codigoDProyectoDAplicacion) throws Exception {
		ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
		NumeroDVersion numeroDVersion = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
		Integer numeroDSituacion = datoDSistema.getSituacionesDDatoDSistema().size();
		SituacionDDatoDSistema instancia = (SituacionDDatoDSistema) Reflexion.createInstancia(SituacionDDatoDSistema.class);
		instancia.addNode(numeroDVersion, numeroDSituacion, root);
		instancia.setDatoDSistema(datoDSistema);
		datoDSistema.setSituacionDDatoDSistema(instancia);
		datoDSistema.getSituacionesDDatoDSistema().add(instancia);
		String codigoDDato = datoDSistema.getCodigoDDato();
		KeyCache keyCache = KeyCache.getInstancia(SituacionDDatoDSistema.class, Boolean.valueOf(false), codigoDDato);
		CacheDRednodal.almacenarInstancia(keyCache, instancia);
	}

}
