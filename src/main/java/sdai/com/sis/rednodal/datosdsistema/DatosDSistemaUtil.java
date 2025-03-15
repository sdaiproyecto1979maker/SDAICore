package sdai.com.sis.rednodal.datosdsistema;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

import sdai.com.sis.aplicaciones.KAplicaciones;
import sdai.com.sis.cacchesdsistema.KeyCache;
import sdai.com.sis.rednodal.CacheDRednodal;
import sdai.com.sis.rednodal.datosdsistema.accesoadatos.DatoDSistema;
import sdai.com.sis.rednodal.datosdsistema.accesoadatos.SituacionDDatoDSistema;
import sdai.com.sis.utilidades.Reflexion;
import sdai.com.sis.versionado.numerosdversion.NumerosDVersionUtil;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.versionado.proyectosdaplicacion.ProyectosDAplicacion;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 15/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class DatosDSistemaUtil {

	public static void generateCacheDDatosDSistema(DatoDSistema[] instancias) throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(DatoDSistema.class);
		CacheDRednodal.almacenarInstancia(keyCache, instancias);
		for (DatoDSistema instancia : instancias)
			DatosDSistemaUtil.generateCacheDDatoDSistema(instancia);
	}

	private static void generateCacheDDatoDSistema(DatoDSistema instancia) throws Exception {
		String codigoDDato = instancia.getCodigoDDato();
		KeyCache keyCache = KeyCache.getInstancia(DatoDSistema.class, codigoDDato);
		CacheDRednodal.almacenarInstancia(keyCache, instancia);
		SituacionDDatoDSistema.getInstancia(codigoDDato);
	}

	public static SituacionDDatoDSistema getSituacionDDatoDSistema(String codigoDDato) throws Exception {
		DatoDSistema datoDSistema = DatoDSistema.getInstancia(codigoDDato);
		List<SituacionDDatoDSistema> instancias = datoDSistema.getSituacionesDDatoDSistema();
		for (SituacionDDatoDSistema instancia : instancias) {
			NumeroDVersion numeroDVersion = instancia.getNumeroDVersion();
			Boolean swValida = NumerosDVersionUtil.isVersionDElementoValida(numeroDVersion);
			if (swValida && instancia.getSwEntidadActiva().equals(Boolean.valueOf(true)))
				return instancia;
		}
		return null;
	}

	public static DatoDSistema getDatoDSistema(Node root) {
		String codigoDDato = DocumentoXML.getStringValueNodeDescendencia(root, KDatosDSistema.KDatoDSistema.AtributosDEntidad.CODIGODATO);
		KeyCache keyCache = KeyCache.getInstancia(DatoDSistema.class, codigoDDato);
		DatoDSistema instancia = (DatoDSistema) CacheDRednodal.recuperarInstancia(keyCache);
		return instancia;
	}

	public static SituacionDDatoDSistema getSituacionDDatoDSistema(DatoDSistema datoDSistema) throws Exception {
		List<SituacionDDatoDSistema> instancias = datoDSistema.getSituacionesDDatoDSistema();
		for (SituacionDDatoDSistema instancia : instancias) {
			NumeroDVersion numeroDVersion = instancia.getNumeroDVersion();
			Boolean swValida = NumerosDVersionUtil.isVersionDElementoValida(numeroDVersion);
			return swValida ? instancia : null;
		}
		return null;
	}

	public static Boolean existenCambiosEnSituacionDDatoDSistema(SituacionDDatoDSistema instancia, Node root) {
		String descripcionDDato = DocumentoXML.getStringValueNodeDescendencia(root, KDatosDSistema.KSituacionDDatoDSistema.AtributosDEntidad.DESCRDDATO);
		Boolean swActiva = DocumentoXML.getBooleanValueNodeDescendencia(root, KAplicaciones.AtributosDEntidad.SWENTACTIV);
		if (!instancia.getDescripcionDDato().equals(descripcionDDato))
			return Boolean.valueOf(true);
		if (!instancia.getSwEntidadActiva().equals(swActiva))
			return Boolean.valueOf(true);
		return Boolean.valueOf(false);
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
		KeyCache keyCache = KeyCache.getInstancia(SituacionDDatoDSistema.class, codigoDDato);
		CacheDRednodal.almacenarInstancia(keyCache, instancia);
	}

	public static void createNewDatoDSistema(Node root, String codigoDProyectoDAplicacion) throws Exception {
		ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
		NumeroDVersion numeroDVersion = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
		Integer numeroDSituacion = Integer.valueOf(1);
		DatoDSistema instancia = (DatoDSistema) Reflexion.createInstancia(DatoDSistema.class);
		instancia.addNode(numeroDVersion, numeroDSituacion, root);
		String codigoDDato = instancia.getCodigoDDato();
		KeyCache keyCache = KeyCache.getInstancia(SituacionDDatoDSistema.class, codigoDDato);
		CacheDRednodal.almacenarInstancia(keyCache, instancia);
		DatosDSistemaUtil.addToCacheDatosDSistema(instancia);
	}

	private static void addToCacheDatosDSistema(DatoDSistema datoDSistema) {
		List<DatoDSistema> lista = new ArrayList<DatoDSistema>();
		KeyCache keyCache = KeyCache.getInstancia(DatoDSistema.class);
		if (CacheDRednodal.existeInstancia(keyCache).equals(Boolean.valueOf(false))) {
			lista.add(datoDSistema);
			DatoDSistema[] instancias = lista.toArray(new DatoDSistema[0]);
			CacheDRednodal.almacenarInstancia(keyCache, instancias);
		} else {
			DatoDSistema[] instancias = (DatoDSistema[]) CacheDRednodal.recuperarInstancia(keyCache);
			for (DatoDSistema instancia : instancias)
				lista.add(instancia);
			lista.add(datoDSistema);
			instancias = lista.toArray(new DatoDSistema[0]);
			CacheDRednodal.almacenarInstancia(keyCache, instancias);
		}
	}

}
