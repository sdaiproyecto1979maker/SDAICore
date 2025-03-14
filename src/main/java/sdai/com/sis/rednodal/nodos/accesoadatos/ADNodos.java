package sdai.com.sis.rednodal.nodos.accesoadatos;

import java.util.Collections;
import java.util.List;

import org.w3c.dom.Node;

import sdai.com.sis.accesoadatos.AbstractAccesoADatos;
import sdai.com.sis.accesoadatos.IAccesoADatosCFG;
import sdai.com.sis.aplicaciones.KAplicaciones;
import sdai.com.sis.cacchesdsistema.KeyCache;
import sdai.com.sis.conexiones.IdQuery;
import sdai.com.sis.rednodal.CacheDRednodal;
import sdai.com.sis.rednodal.datosdsistema.accesoadatos.SituacionDDatoDSistema;
import sdai.com.sis.rednodal.nodos.KNodos;
import sdai.com.sis.versionado.numerosdversion.NumerosDVersionUtil;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.versionado.proyectosdaplicacion.ProyectosDAplicacion;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 13/03/2025
 * @since VERSIONDCOREENCURSO
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
	public void generateElementosDCache() throws Exception {
		Nodo[] nodos = getNodos();
		for (Nodo nodo : nodos) {
			String codigoDNodo = nodo.getCodigoDNodo();
			KeyCache keyCache = KeyCache.getInstancia(Nodo.class, codigoDNodo);
			CacheDRednodal.almacenarInstancia(keyCache, nodo);
			SituacionDNodo.getInstancia(codigoDNodo);
		}
	}

	@Override
	public void generateElementosVersionEnCurso(String codigoDProyectoDAplicacion, Node[] nodes) throws Exception {
		for (Node node : nodes) {
			String codigoDNodo = DocumentoXML.getStringValueNodeDescendencia(node, KNodos.KNodo.AtributosDEntidad.CODIGONODO);
			KeyCache keyCache = KeyCache.getInstancia(Nodo.class, codigoDNodo);
			if (CacheDRednodal.existeInstancia(keyCache)) {
				SituacionDNodo situacionDNodo = SituacionDNodo.getInstancia(codigoDNodo);
				if (situacionDNodo == null) {
					situacionDNodo = getSituacionDNodo(codigoDNodo, Boolean.valueOf(false));
					if (existenCambios(situacionDNodo, node)) {
						Nodo nodo = Nodo.getInstancia(codigoDNodo);
						ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
						NumeroDVersion numeroDVersion = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
						SituacionDNodo instancia = new SituacionDNodo();
						instancia.addNode(numeroDVersion, Integer.valueOf(1), node);
						instancia.setNodo(nodo);
						nodo.setSituacionDNodo(situacionDNodo);
						nodo.getSituacionesDNodo().add(situacionDNodo);
						keyCache = KeyCache.getInstancia(SituacionDDatoDSistema.class, codigoDNodo);
						CacheDRednodal.almacenarInstancia(keyCache, situacionDNodo);
					}
				}
			} else {
				ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
				NumeroDVersion numeroDVersion = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
				Nodo nodo = new Nodo();
				nodo.addNode(numeroDVersion, Integer.valueOf(1), node);
				SituacionDNodo situacionDNodo = new SituacionDNodo();
				situacionDNodo.addNode(numeroDVersion, Integer.valueOf(1), node);
				situacionDNodo.setNodo(nodo);
				nodo.setSituacionDNodo(situacionDNodo);
				nodo.getSituacionesDNodo().add(situacionDNodo);
				keyCache = KeyCache.getInstancia(Nodo.class, codigoDNodo);
				CacheDRednodal.almacenarInstancia(keyCache, nodo);
				keyCache = KeyCache.getInstancia(SituacionDDatoDSistema.class, codigoDNodo);
				CacheDRednodal.almacenarInstancia(keyCache, situacionDNodo);
			}
		}
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
		Nodo[] datosDSistema = ejecutarConsulta(idQuery).toArray(new Nodo[0]);
		return datosDSistema;
	}

	SituacionDNodo getSituacionDNodo(String codigoDNodo) throws Exception {
		return getSituacionDNodo(codigoDNodo, Boolean.valueOf(true));
	}

	SituacionDNodo getSituacionDNodo(String codigoDNodo, Boolean swValidarActivo) throws Exception {
		Nodo nodo = getNodo(codigoDNodo);
		List<SituacionDNodo> situacionesDNodo = nodo.getSituacionesDNodo();
		Collections.sort(situacionesDNodo);
		for (Integer i = Integer.valueOf(situacionesDNodo.size()); i > 0; i--) {
			SituacionDNodo situacionDNodo = situacionesDNodo.get(i);
			NumeroDVersion numeroDVersion = situacionDNodo.getNumeroDVersion();
			if (NumerosDVersionUtil.isVersionDElementoValida(numeroDVersion).equals(Boolean.valueOf(true))) {
				return swValidarActivo.equals(Boolean.valueOf(false)) ? situacionDNodo : situacionDNodo.getSwEntidadActiva() ? situacionDNodo : null;
			}
		}
		return null;
	}

}
