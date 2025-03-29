package sdai.com.sis.rednodal.atributosdnodo.accesoadatos;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

import sdai.com.sis.accesoadatos.AbstractAccesoADatos;
import sdai.com.sis.accesoadatos.AbstractEntidadCFG;
import sdai.com.sis.accesoadatos.IAccesoADatosCFG;
import sdai.com.sis.aplicaciones.KAplicaciones;
import sdai.com.sis.cacchesdsistema.KeyCache;
import sdai.com.sis.cacchesdsistema.contenedores.CacheDRednodal;
import sdai.com.sis.conexiones.IdQuery;
import sdai.com.sis.rednodal.atributosdnodo.KAtributosDNodo;
import sdai.com.sis.rednodal.datosdsistema.KDatosDSistema;
import sdai.com.sis.rednodal.datosdsistema.accesoadatos.DatoDSistema;
import sdai.com.sis.rednodal.datosdsistema.accesoadatos.SituacionDDatoDSistema;
import sdai.com.sis.rednodal.nodos.KNodos;
import sdai.com.sis.rednodal.nodos.accesoadatos.Nodo;
import sdai.com.sis.rednodal.nodos.accesoadatos.SituacionDNodo;
import sdai.com.sis.utilidades.Reflexion;
import sdai.com.sis.utilidades.Util;
import sdai.com.sis.versionado.numerosdversion.NumerosDVersionUtil;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.versionado.proyectosdaplicacion.ProyectosDAplicacion;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 15/03/2025
 * @since 1.0.0.0-RELEASE
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
	public void generateElementosVersionEnCurso(String codigoDProyectoDAplicacion, Node[] nodes) throws Exception {
		for (Node node : nodes) {
			String codigoDNodo = DocumentoXML.getStringValueNodeDescendencia(node, KNodos.KNodo.AtributosDEntidad.CODIGONODO);
			String codigoDDato = DocumentoXML.getStringValueNodeDescendencia(node, KDatosDSistema.KDatoDSistema.AtributosDEntidad.CODIGODATO);
			AtributoDNodo atributoDNodo = AtributoDNodo.getInstancia(codigoDNodo, codigoDDato);
			if (atributoDNodo != null) {
				SituacionDAtributoDNodo situacionDAtributoDNodo = SituacionDAtributoDNodo.getInstancia(codigoDNodo, codigoDDato);
				if (!situacionDAtributoDNodo.getSwEntidadActiva().equals(DocumentoXML.getBooleanValueNodeDescendencia(node, KAplicaciones.AtributosDEntidad.SWENTACTIV)))
					createNewSituacionDAtributoDNodo(atributoDNodo, node, codigoDProyectoDAplicacion);
			} else {
				atributoDNodo = createNewAtributoDNodo(node, codigoDProyectoDAplicacion);
				createNewSituacionDAtributoDNodo(atributoDNodo, node, codigoDProyectoDAplicacion);
			}
		}
	}

	private AtributoDNodo createNewAtributoDNodo(Node root, String codigoDProyectoDAplicacion) throws Exception {
		ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
		NumeroDVersion numeroDVersion = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
		Integer numeroDSituacion = Integer.valueOf(1);
		AtributoDNodo atributoDNodo = (AtributoDNodo) Reflexion.createInstancia(AtributoDNodo.class);
		atributoDNodo.addNode(numeroDVersion, numeroDSituacion, root);
		String codigoDNodo = DocumentoXML.getStringValueNodeDescendencia(root, KNodos.KNodo.AtributosDEntidad.CODIGONODO);
		Nodo nodo = Nodo.getInstancia(codigoDNodo);
		atributoDNodo.setNodo(nodo);
		String codigoDDato = DocumentoXML.getStringValueNodeDescendencia(root, KDatosDSistema.KDatoDSistema.AtributosDEntidad.CODIGODATO);
		DatoDSistema datoDSistema = DatoDSistema.getInstancia(codigoDDato);
		atributoDNodo.setDatoDSistema(datoDSistema);
		KeyCache keyCache = KeyCache.getInstancia(AtributoDNodo.class, Boolean.valueOf(false), codigoDNodo, codigoDDato);
		CacheDRednodal.almacenarInstancia(keyCache, atributoDNodo);
		return atributoDNodo;
	}

	private void createNewSituacionDAtributoDNodo(AtributoDNodo atributoDNodo, Node root, String codigoDProyectoDAplicacion) throws Exception {
		ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
		NumeroDVersion numeroDVersion = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
		Integer numeroDSituacion = atributoDNodo.getSituacionesDAtributoDNodo().size() + 1;
		SituacionDAtributoDNodo situacionDAtributoDNodo = (SituacionDAtributoDNodo) Reflexion.createInstancia(SituacionDAtributoDNodo.class);
		situacionDAtributoDNodo.addNode(numeroDVersion, numeroDSituacion, root);
		situacionDAtributoDNodo.setAtributoDNodo(atributoDNodo);
		atributoDNodo.setSituacionDAtributoDNodo(situacionDAtributoDNodo);
		atributoDNodo.getSituacionesDAtributoDNodo().add(situacionDAtributoDNodo);
		String codigoDNodo = DocumentoXML.getStringValueNodeDescendencia(root, KNodos.KNodo.AtributosDEntidad.CODIGONODO);
		String codigoDDato = DocumentoXML.getStringValueNodeDescendencia(root, KDatosDSistema.KDatoDSistema.AtributosDEntidad.CODIGODATO);
		KeyCache keyCache = KeyCache.getInstancia(SituacionDAtributoDNodo.class, Boolean.valueOf(false), codigoDNodo, codigoDDato);
		CacheDRednodal.almacenarInstancia(keyCache, situacionDAtributoDNodo);
		addToCacheByNodo(situacionDAtributoDNodo, codigoDNodo);
	}

	private void addToCacheByNodo(SituacionDAtributoDNodo situacionDAtributoDNodo, String codigoDNodo) throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(SituacionDAtributoDNodo.class, codigoDNodo);
		SituacionDAtributoDNodo[] situacionesDAtributoDNodo = (SituacionDAtributoDNodo[]) CacheDRednodal.recuperarInstancia(keyCache);
		if (situacionesDAtributoDNodo == null || situacionesDAtributoDNodo.length == 0)
			situacionesDAtributoDNodo = SituacionDAtributoDNodo.getInstancias(codigoDNodo);
		situacionesDAtributoDNodo = (SituacionDAtributoDNodo[]) Util.addItemArray(situacionesDAtributoDNodo, situacionDAtributoDNodo);
		CacheDRednodal.almacenarInstancia(keyCache, situacionesDAtributoDNodo);
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

	SituacionDAtributoDNodo getSituacionDAtributoDNodo(String codigoDNodo, String codigoDDato) throws Exception {
		SituacionDNodo situacionDNodo = SituacionDNodo.getInstancia(codigoDNodo);
		SituacionDDatoDSistema situacionDDatoDSistema = SituacionDDatoDSistema.getInstancia(codigoDDato);
		if (situacionDNodo != null && situacionDDatoDSistema != null) {
			AtributoDNodo atributoDNodo = AtributoDNodo.getInstancia(codigoDNodo, codigoDDato);
			List<SituacionDAtributoDNodo> situacionesDAtributoDNodo = atributoDNodo.getSituacionesDAtributoDNodo();
			SituacionDAtributoDNodo[] instancias = situacionesDAtributoDNodo.toArray(new SituacionDAtributoDNodo[0]);
			Util.ordenar(instancias, AbstractEntidadCFG.NUMVERSDES);
			for (SituacionDAtributoDNodo instancia : instancias) {
				NumeroDVersion numeroDVersion = instancia.getNumeroDVersion();
				if (NumerosDVersionUtil.isVersionDElementoValida(numeroDVersion))
					return instancia;
			}
		}
		return null;
	}

	SituacionDAtributoDNodo[] getSituacionesDAtributoDNodo(String codigoDNodo) throws Exception {
		SituacionDNodo situacionDNodo = SituacionDNodo.getInstancia(codigoDNodo);
		if (situacionDNodo != null) {
			Nodo nodo = Nodo.getInstancia(codigoDNodo);
			List<AtributoDNodo> atributosDNodo = nodo.getAtributosDNodo();
			for (AtributoDNodo atributoDNodo : atributosDNodo) {
				DatoDSistema datoDSistema = atributoDNodo.getDatoDSistema();
				String codigoDDato = datoDSistema.getCodigoDDato();
				SituacionDDatoDSistema situacionDDatoDSistema = SituacionDDatoDSistema.getInstancia(codigoDDato);
				if (situacionDDatoDSistema != null) {
					List<SituacionDAtributoDNodo> lista = new ArrayList<SituacionDAtributoDNodo>();
					List<SituacionDAtributoDNodo> situacionesDAtributoDNodo = atributoDNodo.getSituacionesDAtributoDNodo();
					SituacionDAtributoDNodo[] instancias = situacionesDAtributoDNodo.toArray(new SituacionDAtributoDNodo[0]);
					Util.ordenar(instancias, AbstractEntidadCFG.NUMVERSDES);
					for (SituacionDAtributoDNodo instancia : instancias) {
						NumeroDVersion numeroDVersion = instancia.getNumeroDVersion();
						if (NumerosDVersionUtil.isVersionDElementoValida(numeroDVersion))
							lista.add(instancia);
					}
					return lista.toArray(new SituacionDAtributoDNodo[0]);
				}
			}
		}
		return new SituacionDAtributoDNodo[0];
	}

}
