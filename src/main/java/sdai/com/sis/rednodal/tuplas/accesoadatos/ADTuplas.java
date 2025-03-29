package sdai.com.sis.rednodal.tuplas.accesoadatos;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

import sdai.com.sis.accesoadatos.AbstractAccesoADatos;
import sdai.com.sis.accesoadatos.IAccesoADatosCFG;
import sdai.com.sis.aplicaciones.KAplicaciones;
import sdai.com.sis.cacchesdsistema.KeyCache;
import sdai.com.sis.cacchesdsistema.contenedores.CacheDRednodal;
import sdai.com.sis.conexiones.IdQuery;
import sdai.com.sis.rednodal.nodos.KNodos;
import sdai.com.sis.rednodal.nodos.accesoadatos.Nodo;
import sdai.com.sis.rednodal.nodos.accesoadatos.SituacionDNodo;
import sdai.com.sis.rednodal.tuplas.KTuplas;
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
public final class ADTuplas extends AbstractAccesoADatos implements IAccesoADatosCFG {

	ADTuplas() {
		super();
		marcarEntornoDConfiguracion();
	}

	ADTuplas(String idDConexion) throws Exception {
		super(idDConexion);
	}

	@Override
	public void generateElementosVersionEnCurso(String codigoDProyectoDAplicacion, Node[] nodes) throws Exception {
		for (Node node : nodes) {
			String codigoDTupla = DocumentoXML.getStringValueNodeDescendencia(node, KTuplas.KTupla.AtributosDEntidad.CODIGTUPLA);
			Tupla tupla = Tupla.getInstancia(codigoDTupla);
			if (tupla != null) {
				SituacionDTupla situacionDTupla = SituacionDTupla.getInstancia(codigoDTupla);
				if (existenDiferencias(situacionDTupla, node))
					createNewSituacionDTupla(tupla, node, codigoDProyectoDAplicacion);
			} else {
				tupla = createNewTupla(node, codigoDProyectoDAplicacion);
				createNewSituacionDTupla(tupla, node, codigoDProyectoDAplicacion);
			}
		}
	}

	private Tupla createNewTupla(Node root, String codigoDProyectoDAplicacion) throws Exception {
		ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
		NumeroDVersion numeroDVersion = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
		Integer numeroDSituacion = Integer.valueOf(1);
		Tupla tupla = (Tupla) Reflexion.createInstancia(Tupla.class);
		tupla.addNode(numeroDVersion, numeroDSituacion, root);
		String codigoDTupla = tupla.getCodigoDTupla();
		KeyCache keyCache = KeyCache.getInstancia(Tupla.class, Boolean.valueOf(false), codigoDTupla);
		CacheDRednodal.almacenarInstancia(keyCache, tupla);
		return tupla;
	}

	private void createNewSituacionDTupla(Tupla tupla, Node root, String codigoDProyectoDAplicacion) throws Exception {
		ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
		NumeroDVersion numeroDVersion = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
		Integer numeroDSituacion = tupla.getSituacionesDTupla().size() + 1;
		SituacionDTupla situacionDTupla = (SituacionDTupla) Reflexion.createInstancia(SituacionDTupla.class);
		situacionDTupla.addNode(numeroDVersion, numeroDSituacion, root);
		situacionDTupla.setTupla(tupla);
		tupla.setSituacionDTupla(situacionDTupla);
		tupla.getSituacionesDTupla().add(situacionDTupla);
		String codigoDTupla = DocumentoXML.getStringValueNodeDescendencia(root, KTuplas.KTupla.AtributosDEntidad.CODIGTUPLA);
		KeyCache keyCache = KeyCache.getInstancia(SituacionDTupla.class, Boolean.valueOf(false), codigoDTupla);
		CacheDRednodal.almacenarInstancia(keyCache, situacionDTupla);
		String codigoDNodo = DocumentoXML.getStringValueNodeDescendencia(root, KNodos.KNodo.AtributosDEntidad.CODIGONODO);
		addToCacheDNodos(codigoDNodo, situacionDTupla);
	}

	private void addToCacheDNodos(String codigoDNodo, SituacionDTupla situacionDTupla) throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(SituacionDTupla.class, codigoDNodo);
		SituacionDTupla[] situacionesDTupla = (SituacionDTupla[]) CacheDRednodal.recuperarInstancia(keyCache);
		if (situacionesDTupla == null || situacionesDTupla.length == 0)
			situacionesDTupla = getSituacionesDTupla(codigoDNodo);
		situacionesDTupla = (SituacionDTupla[]) Util.addItemArray(situacionesDTupla, situacionDTupla);
		CacheDRednodal.almacenarInstancia(keyCache, situacionesDTupla);
	}

	private Boolean existenDiferencias(SituacionDTupla situacionDTupla, Node root) {
		if (!situacionDTupla.getDescripcionDTupla().equals(DocumentoXML.getStringValueNodeDescendencia(root, KTuplas.KSituacionDTupla.AtributosDEntidad.DESCRTUPLA)))
			return Boolean.valueOf(true);
		if (!situacionDTupla.getSwEntidadActiva().equals(DocumentoXML.getBooleanValueNodeDescendencia(root, KAplicaciones.AtributosDEntidad.SWENTACTIV)))
			return Boolean.valueOf(true);
		return Boolean.valueOf(false);
	}

	Tupla getTupla(String codigoDTupla) throws Exception {
		IdQuery idQuery = new IdQuery(KTuplas.KTupla.NamedQueries.STUPLA0000);
		idQuery.addParametroDQuery(KTuplas.KTupla.AtributosDEntidad.CODIGTUPLA, codigoDTupla);
		Tupla tupla = (Tupla) ejecutarConsultaSimple(idQuery);
		return tupla;
	}

	SituacionDTupla[] getSituacionesDTupla(String codigoDNodo) throws Exception {
		SituacionDNodo situacionDNodo = SituacionDNodo.getInstancia(codigoDNodo);
		if (situacionDNodo != null) {
			List<SituacionDTupla> lista = new ArrayList<SituacionDTupla>();
			Nodo nodo = situacionDNodo.getNodo();
			List<Tupla> tuplas = nodo.getTuplas();
			for (Tupla tupla : tuplas) {
				String codigoDTupla = tupla.getCodigoDTupla();
				SituacionDTupla situacionDTupla = SituacionDTupla.getInstancia(codigoDTupla);
				if (situacionDTupla != null)
					lista.add(situacionDTupla);
			}
			return lista.toArray(new SituacionDTupla[0]);
		}
		return new SituacionDTupla[0];
	}

	SituacionDTupla getSituacionDTupla(String codigoDTupla) throws Exception {
		Tupla tupla = Tupla.getInstancia(codigoDTupla);
		SituacionDTupla[] situacionesDTupla = tupla.getSituacionesDTupla().toArray(new SituacionDTupla[0]);
		for (SituacionDTupla situacionDTupla : situacionesDTupla) {
			NumeroDVersion numeroDVersion = situacionDTupla.getNumeroDVersion();
			if (NumerosDVersionUtil.isVersionDElementoValida(numeroDVersion))
				return situacionDTupla;
		}
		return null;
	}

}
