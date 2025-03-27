package sdai.com.sis.rednodal.tuplas.accesoadatos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Node;

import sdai.com.sis.accesoadatos.AbstractAccesoADatos;
import sdai.com.sis.accesoadatos.IAccesoADatosCFG;
import sdai.com.sis.cacchesdsistema.KeyCache;
import sdai.com.sis.cacchesdsistema.contenedores.CacheDRednodal;
import sdai.com.sis.conexiones.IdQuery;
import sdai.com.sis.rednodal.nodos.KNodos;
import sdai.com.sis.rednodal.nodos.accesoadatos.Nodo;
import sdai.com.sis.rednodal.nodos.accesoadatos.SituacionDNodo;
import sdai.com.sis.rednodal.tuplas.KTuplas;
import sdai.com.sis.utilidades.Reflexion;
import sdai.com.sis.versionado.numerosdversion.NumerosDVersionUtil;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 13/03/2025
 * @since VERSIONDCOREENCURSO
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
			//TODO Desarrollar la parte de la comprobación de los nodos cuando haya conexion
			Tupla tupla = createNewTupla(node, codigoDProyectoDAplicacion);
			createNewSituacionDTupla(tupla, node, codigoDProyectoDAplicacion);
		}
	}
	
	private Tupla createNewTupla(Node root, String codigoDProyectoDAplicacion) throws Exception {
		//TODO: Descomentar las lineas cuando haya conexion
		/*
		ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
		NumeroDVersion numeroDVersion = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
		*/
		//TODO: Quitar esta linea despues de descomentar las anteriores
		NumeroDVersion numeroDVersion = (NumeroDVersion) Reflexion.createInstancia(NumeroDVersion.class); 
		Integer numeroDSituacion = Integer.valueOf(1);
		Tupla tupla = (Tupla) Reflexion.createInstancia(Tupla.class);		
		tupla.addNode(numeroDVersion, numeroDSituacion, root);
		String codigoDTupla = tupla.getCodigoDTupla();
		KeyCache keyCache = KeyCache.getInstancia(Tupla.class, Boolean.valueOf(false), codigoDTupla);
		CacheDRednodal.almacenarInstancia(keyCache, tupla);
		return tupla;
	}
	
	private void createNewSituacionDTupla(Tupla tupla, Node root, String codigoDProyectoDAplicacion) throws Exception {
		//TODO: Descomentar las lineas cuando haya conexion
		/*
		ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
		NumeroDVersion numeroDVersion = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
		*/
		//TODO: Quitar esta linea despues de descomentar las anteriores
		NumeroDVersion numeroDVersion = (NumeroDVersion) Reflexion.createInstancia(NumeroDVersion.class); 
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
		List<SituacionDTupla> lista = new ArrayList<SituacionDTupla>();
		KeyCache keyCache = KeyCache.getInstancia(SituacionDTupla.class, codigoDNodo);
		SituacionDTupla[] situacionesDTupla = (SituacionDTupla[]) CacheDRednodal.recuperarInstancia(keyCache);
		if(situacionesDTupla == null || situacionesDTupla.length == 0) 
			situacionesDTupla = getSituacionesDTupla(codigoDNodo);
		for(SituacionDTupla situacionDTupla2 : situacionesDTupla)
			lista.add(situacionDTupla2);
		lista.add(situacionDTupla);
		situacionesDTupla = lista.toArray(new SituacionDTupla[0]);
		CacheDRednodal.almacenarInstancia(keyCache, situacionesDTupla);
	}

	Tupla getTupla(String codigoDTupla) throws Exception {
		IdQuery idQuery = new IdQuery(KTuplas.KTupla.NamedQueries.STUPLA0000);
		idQuery.addParametroDQuery(KTuplas.KTupla.AtributosDEntidad.CODIGTUPLA, codigoDTupla);
		Tupla tupla = (Tupla) ejecutarConsultaSimple(idQuery);
		return tupla;
	}

	SituacionDTupla[] getSituacionesDTupla(String codigoDNodo) throws Exception {
		List<SituacionDTupla> lista = new ArrayList<SituacionDTupla>();
		SituacionDNodo situacionDNodo = SituacionDNodo.getInstancia(codigoDNodo);
		if (situacionDNodo != null) {
			Nodo nodo = situacionDNodo.getNodo();
			List<Tupla> tuplas = nodo.getTuplas();
			for (Tupla tupla : tuplas) {
				String codigoDTupla = tupla.getCodigoDTupla();
				SituacionDTupla situacionDTupla = SituacionDTupla.getInstancia(codigoDTupla);
				if (situacionDTupla != null)
					lista.add(situacionDTupla);
			}
		}
		return lista.toArray(new SituacionDTupla[0]);
	}

	SituacionDTupla getSituacionDTupla(String codigoDTupla) throws Exception {
		Tupla tupla = Tupla.getInstancia(codigoDTupla);
		List<SituacionDTupla> situacionesDTupla = tupla.getSituacionesDTupla();
		Collections.sort(situacionesDTupla);
		for (Integer i = Integer.valueOf(situacionesDTupla.size()); i > 0; i--) {
			SituacionDTupla situacionDTupla = situacionesDTupla.get(i);
			NumeroDVersion numeroDVersion = situacionDTupla.getNumeroDVersion();
			if (NumerosDVersionUtil.isVersionDElementoValida(numeroDVersion))
				return situacionDTupla;
		}
		return null;
	}

}
