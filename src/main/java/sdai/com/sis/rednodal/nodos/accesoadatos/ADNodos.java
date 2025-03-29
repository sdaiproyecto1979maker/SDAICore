package sdai.com.sis.rednodal.nodos.accesoadatos;

import org.w3c.dom.Node;

import sdai.com.sis.accesoadatos.AbstractAccesoADatos;
import sdai.com.sis.accesoadatos.AbstractEntidadCFG;
import sdai.com.sis.accesoadatos.IAccesoADatosCFG;
import sdai.com.sis.aplicaciones.KAplicaciones;
import sdai.com.sis.cacchesdsistema.KeyCache;
import sdai.com.sis.cacchesdsistema.contenedores.CacheDRednodal;
import sdai.com.sis.conexiones.IdQuery;
import sdai.com.sis.rednodal.nodos.KNodos;
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
public final class ADNodos extends AbstractAccesoADatos implements IAccesoADatosCFG {

	ADNodos() {
		super();
		marcarEntornoDConfiguracion();
	}

	ADNodos(String idDConexion) throws Exception {
		super(idDConexion);
	}

	@Override
	public void generateElementosVersionEnCurso(String codigoDProyectoDAplicacion, Node[] nodes) throws Exception {
		for (Node node : nodes) {
			String codigoDNodo = DocumentoXML.getStringValueNodeDescendencia(node, KNodos.KNodo.AtributosDEntidad.CODIGONODO);
			Nodo nodo = Nodo.getInstancia(codigoDNodo);
			if (nodo != null) {
				SituacionDNodo situacionDNodo = SituacionDNodo.getInstancia(codigoDNodo);
				if (existenCambios(situacionDNodo, node))
					createNewSituacionDNodo(nodo, node, codigoDProyectoDAplicacion);
			} else {
				nodo = createNewNodo(node, codigoDProyectoDAplicacion);
				createNewSituacionDNodo(nodo, node, codigoDProyectoDAplicacion);
			}
		}
	}

	private Nodo createNewNodo(Node root, String codigoDProyectoDAplicacion) throws Exception {
		ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
		NumeroDVersion numeroDVersion = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
		Integer numeroDSituacion = Integer.valueOf(1);
		Nodo nodo = (Nodo) Reflexion.createInstancia(Nodo.class);
		nodo.addNode(numeroDVersion, numeroDSituacion, root);
		String codigoDNodo = nodo.getCodigoDNodo();
		KeyCache keyCache = KeyCache.getInstancia(Nodo.class, Boolean.valueOf(false), codigoDNodo);
		CacheDRednodal.almacenarInstancia(keyCache, nodo);
		return nodo;
	}

	private void createNewSituacionDNodo(Nodo nodo, Node root, String codigoDProyectoDAplicacion) throws Exception {
		ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
		NumeroDVersion numeroDVersion = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
		Integer numeroDSituacion = nodo.getSituacionesDNodo().size() + 1;
		SituacionDNodo situacionDNodo = (SituacionDNodo) Reflexion.createInstancia(SituacionDNodo.class);
		situacionDNodo.addNode(numeroDVersion, numeroDSituacion, root);
		situacionDNodo.setNodo(nodo);
		nodo.setSituacionDNodo(situacionDNodo);
		nodo.getSituacionesDNodo().add(situacionDNodo);
		String codigoDNodo = nodo.getCodigoDNodo();
		KeyCache keyCache = KeyCache.getInstancia(SituacionDNodo.class, Boolean.valueOf(false), codigoDNodo);
		CacheDRednodal.almacenarInstancia(keyCache, situacionDNodo);
	}

	private Boolean existenCambios(SituacionDNodo situacionDNodo, Node root) {
		if (!situacionDNodo.getDescripcionDNodo().equals(DocumentoXML.getStringValueNodeDescendencia(root, KNodos.KSituacionDNodo.AtributosDEntidad.DESCRDNODO)))
			return Boolean.valueOf(true);
		if (!situacionDNodo.getSwEntidadActiva().equals(DocumentoXML.getBooleanValueNodeDescendencia(root, KAplicaciones.AtributosDEntidad.SWENTACTIV)))
			return Boolean.valueOf(true);
		return Boolean.valueOf(false);
	}

	Nodo getNodo(String codigoDNodo) throws Exception {
		IdQuery idQuery = new IdQuery(KNodos.KNodo.NamedQueries.SNODOS0000);
		idQuery.addParametroDQuery(KNodos.KNodo.AtributosDEntidad.CODIGONODO, codigoDNodo);
		Nodo nodo = (Nodo) ejecutarConsultaSimple(idQuery);
		return nodo;
	}

	Nodo[] getNodos() throws Exception {
		IdQuery idQuery = new IdQuery(KNodos.KNodo.NamedQueries.SNODOS0001);
		Nodo[] nodos = ejecutarConsulta(idQuery).toArray(new Nodo[0]);
		return nodos;
	}

	SituacionDNodo getSituacionDNodo(String codigoDNodo) throws Exception {
		Nodo nodo = Nodo.getInstancia(codigoDNodo);
		SituacionDNodo[] situacionesDNodo = nodo.getSituacionesDNodo().toArray(new SituacionDNodo[0]);
		Util.ordenar(situacionesDNodo, AbstractEntidadCFG.NUMVERSDES);
		for (SituacionDNodo situacionDNodo : situacionesDNodo) {
			NumeroDVersion numeroDVersion = situacionDNodo.getNumeroDVersion();
			if (NumerosDVersionUtil.isVersionDElementoValida(numeroDVersion))
				return situacionDNodo;
		}
		return null;
	}

}
