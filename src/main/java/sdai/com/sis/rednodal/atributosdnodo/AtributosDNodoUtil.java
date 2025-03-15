package sdai.com.sis.rednodal.atributosdnodo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Node;

import sdai.com.sis.aplicaciones.KAplicaciones;
import sdai.com.sis.cacchesdsistema.KeyCache;
import sdai.com.sis.rednodal.CacheDRednodal;
import sdai.com.sis.rednodal.atributosdnodo.accesoadatos.AtributoDNodo;
import sdai.com.sis.rednodal.atributosdnodo.accesoadatos.SituacionDAtributoDNodo;
import sdai.com.sis.rednodal.datosdsistema.KDatosDSistema;
import sdai.com.sis.rednodal.datosdsistema.accesoadatos.DatoDSistema;
import sdai.com.sis.rednodal.nodos.KNodos;
import sdai.com.sis.rednodal.nodos.accesoadatos.Nodo;
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
public abstract class AtributosDNodoUtil {

	public static void generateCacheAtributosDNodo(AtributoDNodo[] atributosDNodo) throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(AtributoDNodo.class);
		CacheDRednodal.almacenarInstancia(keyCache, atributosDNodo);
		AtributosDNodoUtil.generateCacheAtributosDNodoByNodo(atributosDNodo);
	}

	private static void generateCacheAtributosDNodoByNodo(AtributoDNodo[] atributosDNodo) throws Exception {
		Map<String, List<AtributoDNodo>> almacen = new HashMap<String, List<AtributoDNodo>>();
		for (AtributoDNodo atributoDNodo : atributosDNodo) {
			AtributosDNodoUtil.generateCacheAtributoDNodoByNodoDatoDSistema(atributoDNodo);
			Nodo nodo = atributoDNodo.getNodo();
			String codigoDNodo = nodo.getCodigoDNodo();
			List<AtributoDNodo> lista = almacen.get(codigoDNodo);
			if (lista == null) {
				lista = new ArrayList<AtributoDNodo>();
				almacen.put(codigoDNodo, lista);
			}
			lista.add(atributoDNodo);
			AtributoDNodo[] _atributosDNodo = lista.toArray(new AtributoDNodo[0]);
			KeyCache keyCache = KeyCache.getInstancia(AtributoDNodo.class, codigoDNodo);
			CacheDRednodal.almacenarInstancia(keyCache, _atributosDNodo);
			SituacionDAtributoDNodo.getInstancias(codigoDNodo);
		}
	}

	private static void generateCacheAtributoDNodoByNodoDatoDSistema(AtributoDNodo atributoDNodo) throws Exception {
		Nodo nodo = atributoDNodo.getNodo();
		String codigoDNodo = nodo.getCodigoDNodo();
		DatoDSistema datoDSistema = atributoDNodo.getDatoDSistema();
		String codigoDDato = datoDSistema.getCodigoDDato();
		KeyCache keyCache = KeyCache.getInstancia(AtributoDNodo.class, codigoDNodo, codigoDDato);
		CacheDRednodal.almacenarInstancia(keyCache, atributoDNodo);
		SituacionDAtributoDNodo.getInstancia(codigoDNodo, codigoDDato);
	}

	public static AtributoDNodo getAtributoDNodo(Node root) {
		String codigoDNodo = DocumentoXML.getStringValueNodeDescendencia(root, KNodos.KNodo.AtributosDEntidad.CODIGONODO);
		String codigoDDato = DocumentoXML.getStringValueNodeDescendencia(root, KDatosDSistema.KDatoDSistema.AtributosDEntidad.CODIGODATO);
		KeyCache keyCache = KeyCache.getInstancia(AtributoDNodo.class, codigoDNodo, codigoDDato);
		AtributoDNodo atributoDNodo = (AtributoDNodo) CacheDRednodal.recuperarInstancia(keyCache);
		return atributoDNodo;
	}

	public static SituacionDAtributoDNodo[] getSituacionesDAtributoDNodo(String codigoDNodo) throws Exception {
		List<SituacionDAtributoDNodo> lista = new ArrayList<SituacionDAtributoDNodo>();
		AtributoDNodo[] atributosDNodo = AtributoDNodo.getInstancias(codigoDNodo);
		for (AtributoDNodo atributoDNodo : atributosDNodo) {
			List<SituacionDAtributoDNodo> situacionesDAtributoDNodo = atributoDNodo.getSituacionesDAtributoDNodo();
			for (SituacionDAtributoDNodo situacionDAtributoDNodo : situacionesDAtributoDNodo)
				if (AtributosDNodoUtil.isSituacionDAtributoDNodoValida(situacionDAtributoDNodo) && situacionDAtributoDNodo.getSwEntidadActiva().equals(Boolean.valueOf(true)))
					lista.add(situacionDAtributoDNodo);
		}
		return lista.toArray(new SituacionDAtributoDNodo[0]);
	}

	private static Boolean isSituacionDAtributoDNodoValida(SituacionDAtributoDNodo situacionDAtributoDNodo) throws Exception {
		NumeroDVersion numeroDVersion = situacionDAtributoDNodo.getNumeroDVersion();
		Boolean isValida = NumerosDVersionUtil.isVersionDElementoValida(numeroDVersion);
		return isValida.equals(Boolean.valueOf(true));
	}

	public static SituacionDAtributoDNodo getSituacionDAtributoDNodo(String codigoDNodo, String codigoDDato) throws Exception {
		AtributoDNodo atributoDNodo = AtributoDNodo.getInstancia(codigoDNodo, codigoDDato);
		List<SituacionDAtributoDNodo> situacionesDAtributoDNodo = atributoDNodo.getSituacionesDAtributoDNodo();
		for (SituacionDAtributoDNodo situacionDAtributoDNodo : situacionesDAtributoDNodo)
			if (AtributosDNodoUtil.isSituacionDAtributoDNodoValida(situacionDAtributoDNodo) && situacionDAtributoDNodo.getSwEntidadActiva().equals(Boolean.valueOf(true)))
				return situacionDAtributoDNodo;
		return null;
	}

	public static SituacionDAtributoDNodo getSituacionDAtributoDNodo(Nodo nodo, DatoDSistema datoDSistema) throws Exception {
		String codigoDNodo = nodo.getCodigoDNodo();
		String codigoDDato = datoDSistema.getCodigoDDato();
		AtributoDNodo atributoDNodo = AtributoDNodo.getInstancia(codigoDNodo, codigoDDato);
		List<SituacionDAtributoDNodo> situacionesDAtributoDNodo = atributoDNodo.getSituacionesDAtributoDNodo();
		for (SituacionDAtributoDNodo situacionDAtributoDNodo : situacionesDAtributoDNodo)
			if (AtributosDNodoUtil.isSituacionDAtributoDNodoValida(situacionDAtributoDNodo))
				return situacionDAtributoDNodo;
		return null;
	}

	public static Boolean existenCambiosEnSituacionDAtributoDNodo(SituacionDAtributoDNodo situacionDAtributoDNodo, Node root) {
		if (!situacionDAtributoDNodo.getSwEntidadActiva().equals(DocumentoXML.getBooleanValueNodeDescendencia(root, KAplicaciones.AtributosDEntidad.SWENTACTIV)))
			return Boolean.valueOf(true);
		return Boolean.valueOf(false);
	}

	public static void createSituacionDAtributoDNodoNewVersion(String codigoDProyectoDAplicacion, AtributoDNodo atributoDNodo, Node root) throws Exception {
		ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
		NumeroDVersion numeroDVersion = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
		Integer numeroDSituacion = atributoDNodo.getSituacionesDAtributoDNodo().size();
		SituacionDAtributoDNodo situacionDAtributoDNodo = (SituacionDAtributoDNodo) Reflexion.createInstancia(SituacionDAtributoDNodo.class);
		situacionDAtributoDNodo.addNode(numeroDVersion, numeroDSituacion, root);
		situacionDAtributoDNodo.setAtributoDNodo(atributoDNodo);
		atributoDNodo.setSituacionDAtributoDNodo(situacionDAtributoDNodo);
		atributoDNodo.getSituacionesDAtributoDNodo().add(situacionDAtributoDNodo);
		AtributosDNodoUtil.addToCacheNewVersionSituacionDAtributoDNodo(situacionDAtributoDNodo);
	}

	private static void addToCacheNewVersionSituacionDAtributoDNodo(SituacionDAtributoDNodo situacionDAtributoDNodo) {
		AtributoDNodo atributoDNodo = situacionDAtributoDNodo.getAtributoDNodo();
		Nodo nodo = atributoDNodo.getNodo();
		String codigoDNodo = nodo.getCodigoDNodo();
		DatoDSistema datoDSistema = atributoDNodo.getDatoDSistema();
		String codigoDDato = datoDSistema.getCodigoDDato();
		KeyCache keyCache = KeyCache.getInstancia(SituacionDAtributoDNodo.class, codigoDNodo, codigoDDato);
		CacheDRednodal.almacenarInstancia(keyCache, situacionDAtributoDNodo);
		AtributosDNodoUtil.replaceSituacionDAtributoDNodoIfExists(codigoDNodo, codigoDDato, situacionDAtributoDNodo);
	}

	private static void replaceSituacionDAtributoDNodoIfExists(String codigoDNodo, String codigoDDato, SituacionDAtributoDNodo situacionDAtributoDNodo) {
		List<SituacionDAtributoDNodo> lista = new ArrayList<SituacionDAtributoDNodo>();
		KeyCache keyCache = KeyCache.getInstancia(SituacionDAtributoDNodo.class, codigoDNodo);
		SituacionDAtributoDNodo[] instancias = (SituacionDAtributoDNodo[]) CacheDRednodal.recuperarInstancia(keyCache);
		for (SituacionDAtributoDNodo instancia : instancias) {
			AtributoDNodo atributoDNodo = instancia.getAtributoDNodo();
			DatoDSistema datoDSistema = atributoDNodo.getDatoDSistema();
			String codigo = datoDSistema.getCodigoDDato();
			if (!codigo.equals(codigoDDato))
				lista.add(instancia);
		}
		lista.add(situacionDAtributoDNodo);
		instancias = lista.toArray(new SituacionDAtributoDNodo[0]);
		CacheDRednodal.almacenarInstancia(keyCache, instancias);
	}

	public static AtributoDNodo createAtributoDNodo(Node root) throws Exception {
		String codigoDNodo = DocumentoXML.getStringValueNodeDescendencia(root, KNodos.KNodo.AtributosDEntidad.CODIGONODO);
		Nodo nodo = Nodo.getInstancia(codigoDNodo);
		String codigoDDato = DocumentoXML.getStringValueNodeDescendencia(root, KDatosDSistema.KDatoDSistema.AtributosDEntidad.CODIGODATO);
		DatoDSistema datoDSistema = DatoDSistema.getInstancia(codigoDDato);
		AtributoDNodo instancia = (AtributoDNodo) Reflexion.createInstancia(AtributoDNodo.class);
		instancia.setNodo(nodo);
		instancia.setDatoDSistema(datoDSistema);
		AtributosDNodoUtil.addToCacheAtributosDNodo(instancia);
		AtributosDNodoUtil.addToCacheAtributosDNodoByNodo(instancia);
		AtributosDNodoUtil.addToCacheAtributosDNodoByNodoDatoDSistema(instancia);
		return instancia;
	}

	private static void addToCacheAtributosDNodo(AtributoDNodo atributoDNodo) {
		List<AtributoDNodo> lista = new ArrayList<AtributoDNodo>();
		KeyCache keyCache = KeyCache.getInstancia(AtributoDNodo.class);
		if (CacheDRednodal.existeInstancia(keyCache).equals(Boolean.valueOf(false))) {
			lista.add(atributoDNodo);
			AtributoDNodo[] instancias = lista.toArray(new AtributoDNodo[0]);
			CacheDRednodal.almacenarInstancia(keyCache, instancias);
		} else {
			AtributoDNodo[] instancias = (AtributoDNodo[]) CacheDRednodal.recuperarInstancia(keyCache);
			for (AtributoDNodo instancia : instancias)
				lista.add(instancia);
			lista.add(atributoDNodo);
			instancias = lista.toArray(new AtributoDNodo[0]);
			CacheDRednodal.almacenarInstancia(keyCache, instancias);
		}
	}

	private static void addToCacheAtributosDNodoByNodo(AtributoDNodo atributoDNodo) {
		Nodo nodo = atributoDNodo.getNodo();
		String codigoDNodo = nodo.getCodigoDNodo();
		List<AtributoDNodo> lista = new ArrayList<AtributoDNodo>();
		KeyCache keyCache = KeyCache.getInstancia(AtributoDNodo.class, codigoDNodo);
		if (CacheDRednodal.existeInstancia(keyCache).equals(Boolean.valueOf(false))) {
			lista.add(atributoDNodo);
			AtributoDNodo[] instancias = lista.toArray(new AtributoDNodo[0]);
			CacheDRednodal.almacenarInstancia(keyCache, instancias);
		} else {
			AtributoDNodo[] instancias = (AtributoDNodo[]) CacheDRednodal.recuperarInstancia(keyCache);
			for (AtributoDNodo instancia : instancias)
				lista.add(instancia);
			lista.add(atributoDNodo);
			instancias = lista.toArray(new AtributoDNodo[0]);
			CacheDRednodal.almacenarInstancia(keyCache, instancias);
		}
	}

	private static void addToCacheAtributosDNodoByNodoDatoDSistema(AtributoDNodo atributoDNodo) {
		Nodo nodo = atributoDNodo.getNodo();
		String codigoDNodo = nodo.getCodigoDNodo();
		DatoDSistema datoDSistema = atributoDNodo.getDatoDSistema();
		String codigoDDato = datoDSistema.getCodigoDDato();
		KeyCache keyCache = KeyCache.getInstancia(AtributoDNodo.class, codigoDNodo, codigoDDato);
		CacheDRednodal.almacenarInstancia(keyCache, atributoDNodo);
	}

}
