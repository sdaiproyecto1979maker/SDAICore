package sdai.com.sis.rednodal.atributosdnodo.accesoadatos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Node;

import sdai.com.sis.accesoadatos.AbstractAccesoADatos;
import sdai.com.sis.accesoadatos.IAccesoADatosCFG;
import sdai.com.sis.aplicaciones.KAplicaciones;
import sdai.com.sis.cacchesdsistema.KeyCache;
import sdai.com.sis.conexiones.IdQuery;
import sdai.com.sis.rednodal.CacheDRednodal;
import sdai.com.sis.rednodal.atributosdnodo.KAtributosDNodo;
import sdai.com.sis.rednodal.datosdsistema.KDatosDSistema;
import sdai.com.sis.rednodal.datosdsistema.accesoadatos.DatoDSistema;
import sdai.com.sis.rednodal.datosdsistema.accesoadatos.SituacionDDatoDSistema;
import sdai.com.sis.rednodal.nodos.KNodos;
import sdai.com.sis.rednodal.nodos.accesoadatos.Nodo;
import sdai.com.sis.versionado.numerosdversion.NumerosDVersionUtil;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.versionado.proyectosdaplicacion.ProyectosDAplicacion;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 15/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class ADAtributosDNodo extends AbstractAccesoADatos implements IAccesoADatosCFG {

	ADAtributosDNodo() {
		super();
		marcarEntornoDConfiguracion();
	}

	ADAtributosDNodo(String idDConexion) throws Exception {
		super(idDConexion);
	}

	@Override
	public void generateElementosDCache() throws Exception {
		AtributoDNodo[] atributosDNodo = getAtributosDNodo();
		KeyCache keyCache = KeyCache.getInstancia(AtributoDNodo.class);
		CacheDRednodal.almacenarInstancia(keyCache, atributosDNodo);
		for (AtributoDNodo atributoDNodo : atributosDNodo) {
			Nodo nodo = atributoDNodo.getNodo();
			AtributoDNodo[] instancias = getAtributosDNodo(nodo);
			String codigoDNodo = nodo.getCodigoDNodo();
			keyCache = KeyCache.getInstancia(AtributoDNodo.class, codigoDNodo);
			CacheDRednodal.almacenarInstancia(keyCache, instancias);
			for (AtributoDNodo instancia : instancias) {
				DatoDSistema datoDSistema = instancia.getDatoDSistema();
				String codigoDDato = datoDSistema.getCodigoDDato();
				keyCache = KeyCache.getInstancia(AtributoDNodo.class, codigoDNodo, codigoDDato);
				CacheDRednodal.almacenarInstancia(keyCache, instancias);
				SituacionDAtributoDNodo.getInstancia(codigoDNodo, codigoDDato);
			}
			SituacionDAtributoDNodo.getInstancias(codigoDNodo);
		}
	}

	@Override
	public void generateElementosVersionEnCurso(String codigoDProyectoDAplicacion, Node[] nodes) throws Exception {
		for (Node node : nodes) {
			String codigoDNodo = DocumentoXML.getStringValueNodeDescendencia(node, KNodos.KNodo.AtributosDEntidad.CODIGONODO);
			String codigoDDato = DocumentoXML.getStringValueNodeDescendencia(node, KDatosDSistema.KDatoDSistema.AtributosDEntidad.CODIGODATO);
			KeyCache keyCache = KeyCache.getInstancia(AtributoDNodo.class, codigoDNodo, codigoDDato);
			if (CacheDRednodal.existeInstancia(keyCache)) {
				SituacionDAtributoDNodo situacionDAtributoDNodo = SituacionDAtributoDNodo.getInstancia(codigoDNodo, codigoDDato);
				if (situacionDAtributoDNodo == null) {
					situacionDAtributoDNodo = getSituacionDAtributoDNodo(codigoDNodo, codigoDDato, Boolean.valueOf(false));
					if (!situacionDAtributoDNodo.getSwEntidadActiva().equals(DocumentoXML.getBooleanValueNodeDescendencia(node, KAplicaciones.AtributosDEntidad.SWENTACTIV))) {
						situacionDAtributoDNodo = createSituacionDAtributoDNodoNewVersion(codigoDProyectoDAplicacion, codigoDNodo, codigoDDato, node);
						keyCache = KeyCache.getInstancia(SituacionDDatoDSistema.class, codigoDNodo, codigoDDato);
						CacheDRednodal.almacenarInstancia(keyCache, situacionDAtributoDNodo);
					}
				} else {
					if (!situacionDAtributoDNodo.getSwEntidadActiva().equals(DocumentoXML.getBooleanValueNodeDescendencia(node, KAplicaciones.AtributosDEntidad.SWENTACTIV))) {
						situacionDAtributoDNodo = createSituacionDAtributoDNodoNewVersion(codigoDProyectoDAplicacion, codigoDNodo, codigoDDato, node);
						keyCache = KeyCache.getInstancia(SituacionDDatoDSistema.class, codigoDNodo, codigoDDato);
						CacheDRednodal.almacenarInstancia(keyCache, situacionDAtributoDNodo);
					}
				}
			} else {
				ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
				NumeroDVersion numeroDVersion = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
				Nodo nodo = Nodo.getInstancia(codigoDNodo);
				DatoDSistema datoDSistema = DatoDSistema.getInstancia(codigoDDato);
				AtributoDNodo atributoDNodo = new AtributoDNodo();
				atributoDNodo.addNode(numeroDVersion, Integer.valueOf(1), node);
				atributoDNodo.setNodo(nodo);
				nodo.getAtributosDNodo().add(atributoDNodo);
				atributoDNodo.setDatoDSistema(datoDSistema);
				datoDSistema.getAtributosDNodo().add(atributoDNodo);
				keyCache = KeyCache.getInstancia(AtributoDNodo.class, codigoDNodo, codigoDDato);
				CacheDRednodal.almacenarInstancia(keyCache, atributoDNodo);
				SituacionDAtributoDNodo situacionDAtributoDNodo = createSituacionDAtributoDNodoNewVersion(codigoDProyectoDAplicacion, codigoDNodo, codigoDDato, node);
				keyCache = KeyCache.getInstancia(SituacionDDatoDSistema.class, codigoDNodo, codigoDDato);
				CacheDRednodal.almacenarInstancia(keyCache, situacionDAtributoDNodo);
			}
		}
	}

	private SituacionDAtributoDNodo createSituacionDAtributoDNodoNewVersion(String codigoDProyectoDAplicacion, String codigoDNodo, String codigoDDato, Node root) throws Exception {
		AtributoDNodo atributoDNodo = AtributoDNodo.getInstancia(codigoDNodo, codigoDDato);
		ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
		NumeroDVersion numeroDVersion = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
		SituacionDAtributoDNodo situacionDAtributoDNodo = new SituacionDAtributoDNodo();
		situacionDAtributoDNodo.addNode(numeroDVersion, atributoDNodo.getSituacionesDAtributoDNodo().size(), root);
		situacionDAtributoDNodo.setAtributoDNodo(atributoDNodo);
		atributoDNodo.setSituacionDAtributoDNodo(situacionDAtributoDNodo);
		atributoDNodo.getSituacionesDAtributoDNodo().add(situacionDAtributoDNodo);
		return situacionDAtributoDNodo;
	}

	AtributoDNodo[] getAtributosDNodo() throws Exception {
		IdQuery idQuery = new IdQuery(KAtributosDNodo.KAtributoDNodo.NamedQueries.SATRNO0000);
		AtributoDNodo[] instancias = ejecutarConsulta(idQuery).toArray(new AtributoDNodo[0]);
		return instancias;
	}

	AtributoDNodo[] getAtributosDNodo(String codigoDNodo) throws Exception {
		IdQuery idQuery = new IdQuery(KAtributosDNodo.KAtributoDNodo.NamedQueries.SATRNO0001);
		idQuery.addParametroDQuery(KNodos.KNodo.AtributosDEntidad.CODIGONODO, codigoDNodo);
		AtributoDNodo[] instancias = ejecutarConsulta(idQuery).toArray(new AtributoDNodo[0]);
		return instancias;
	}

	AtributoDNodo getAtributoDNodo(String codigoDNodo, String codigoDDato) throws Exception {
		IdQuery idQuery = new IdQuery(KAtributosDNodo.KAtributoDNodo.NamedQueries.SATRNO0002);
		idQuery.addParametroDQuery(KNodos.KNodo.AtributosDEntidad.CODIGONODO, codigoDNodo);
		idQuery.addParametroDQuery(KDatosDSistema.KDatoDSistema.AtributosDEntidad.CODIGODATO, codigoDDato);
		AtributoDNodo instancia = (AtributoDNodo) ejecutarConsultaSimple(idQuery);
		return instancia;
	}

	private AtributoDNodo[] getAtributosDNodo(Nodo nodo) {
		List<AtributoDNodo> atributosDNodo = nodo.getAtributosDNodo();
		return atributosDNodo.toArray(new AtributoDNodo[0]);
	}

	SituacionDAtributoDNodo[] getSituacionesDAtributoDNodo(String codigoDNodo) throws Exception {
		return getSituacionesDAtributoDNodo(codigoDNodo, Boolean.valueOf(true));
	}

	private SituacionDAtributoDNodo[] getSituacionesDAtributoDNodo(String codigoDNodo, Boolean swValidarActivo) throws Exception {
		List<SituacionDAtributoDNodo> lista = new ArrayList<SituacionDAtributoDNodo>();
		AtributoDNodo[] atributosDNodo = getAtributosDNodo(codigoDNodo);
		for (AtributoDNodo atributoDNodo : atributosDNodo) {
			List<SituacionDAtributoDNodo> situacionesDAtributoDNodo = atributoDNodo.getSituacionesDAtributoDNodo();
			Collections.sort(situacionesDAtributoDNodo);
			for (Integer i = Integer.valueOf(situacionesDAtributoDNodo.size()); i > 0; i--) {
				SituacionDAtributoDNodo situacionDAtributoDNodo = situacionesDAtributoDNodo.get(i);
				NumeroDVersion numeroDVersion = situacionDAtributoDNodo.getNumeroDVersion();
				if (NumerosDVersionUtil.isVersionDElementoValida(numeroDVersion).equals(Boolean.valueOf(true))) {
					if (swValidarActivo && situacionDAtributoDNodo.getSwEntidadActiva())
						lista.add(situacionDAtributoDNodo);
					else
						lista.add(situacionDAtributoDNodo);
				}
			}
		}
		return lista.toArray(new SituacionDAtributoDNodo[0]);
	}

	SituacionDAtributoDNodo getSituacionDAtributoDNodo(String codigoDNodo, String codigoDDato) throws Exception {
		return getSituacionDAtributoDNodo(codigoDNodo, codigoDDato, Boolean.valueOf(true));
	}

	private SituacionDAtributoDNodo getSituacionDAtributoDNodo(String codigoDNodo, String codigoDDato, Boolean swValidarActivo) throws Exception {
		AtributoDNodo atributoDNodo = getAtributoDNodo(codigoDNodo, codigoDDato);
		List<SituacionDAtributoDNodo> situacionesDAtributoDNodo = atributoDNodo.getSituacionesDAtributoDNodo();
		Collections.sort(situacionesDAtributoDNodo);
		for (Integer i = Integer.valueOf(situacionesDAtributoDNodo.size()); i > 0; i--) {
			SituacionDAtributoDNodo situacionDAtributoDNodo = situacionesDAtributoDNodo.get(i);
			NumeroDVersion numeroDVersion = situacionDAtributoDNodo.getNumeroDVersion();
			if (NumerosDVersionUtil.isVersionDElementoValida(numeroDVersion).equals(Boolean.valueOf(true))) {
				return swValidarActivo.equals(Boolean.valueOf(false)) ? situacionDAtributoDNodo : situacionDAtributoDNodo.getSwEntidadActiva() ? situacionDAtributoDNodo : null;
			}
		}
		return null;

	}

}
