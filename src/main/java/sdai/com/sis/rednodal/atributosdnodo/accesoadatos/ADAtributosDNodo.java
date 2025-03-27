package sdai.com.sis.rednodal.atributosdnodo.accesoadatos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Node;

import sdai.com.sis.accesoadatos.AbstractAccesoADatos;
import sdai.com.sis.accesoadatos.IAccesoADatosCFG;
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
import sdai.com.sis.versionado.numerosdversion.NumerosDVersionUtil;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
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
	public void generateElementosVersionEnCurso(String codigoDProyectoDAplicacion, Node[] nodes) throws Exception {
		for (Node node : nodes) {
			//TODO Desarrollar la parte de la comprobación de los nodos cuando haya conexion
			AtributoDNodo atributoDNodo = createNewAtributoDNodo(node, codigoDProyectoDAplicacion);
			createNewSituacionDAtributoDNodo(atributoDNodo, node, codigoDProyectoDAplicacion);			
		}
	}
	
	private AtributoDNodo createNewAtributoDNodo(Node root, String codigoDProyectoDAplicacion) throws Exception {
		//TODO: Descomentar las lineas cuando haya conexion
		/*
		ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
		NumeroDVersion numeroDVersion = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
		*/
		//TODO: Quitar esta linea despues de descomentar las anteriores
		NumeroDVersion numeroDVersion = (NumeroDVersion) Reflexion.createInstancia(NumeroDVersion.class); 
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
		//TODO: Descomentar las lineas cuando haya conexion
		/*
		ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
		NumeroDVersion numeroDVersion = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
		*/
		//TODO: Quitar esta linea despues de descomentar las anteriores
		NumeroDVersion numeroDVersion = (NumeroDVersion) Reflexion.createInstancia(NumeroDVersion.class); 
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
	
	private void addToCacheByNodo(SituacionDAtributoDNodo situacionDAtributoDNodo,  String codigoDNodo) throws Exception {
		List<SituacionDAtributoDNodo> lista = new ArrayList<SituacionDAtributoDNodo>();
		KeyCache keyCache = KeyCache.getInstancia(SituacionDAtributoDNodo.class, codigoDNodo);
		SituacionDAtributoDNodo[] situacionesDAtributoDNodo = (SituacionDAtributoDNodo[]) CacheDRednodal.recuperarInstancia(keyCache);
		if(situacionesDAtributoDNodo == null || situacionesDAtributoDNodo.length == 0) {
			situacionesDAtributoDNodo = getSituacionesDAtributoDNodo(codigoDNodo);//TODO: LLamar al getInstancia cuando haya conexiones
			for(SituacionDAtributoDNodo situacionDAtributoDNodo2 : situacionesDAtributoDNodo)
				lista.add(situacionDAtributoDNodo2);
			lista.add(situacionDAtributoDNodo);
		} else {
			for(SituacionDAtributoDNodo situacionDAtributoDNodo2 : situacionesDAtributoDNodo)
				lista.add(situacionDAtributoDNodo2);
			lista.add(situacionDAtributoDNodo);
		}
		situacionesDAtributoDNodo = lista.toArray(new SituacionDAtributoDNodo[0]);
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

	SituacionDAtributoDNodo[] getSituacionesDAtributoDNodo(String codigoDNodo) throws Exception {
		List<SituacionDAtributoDNodo> lista = new ArrayList<SituacionDAtributoDNodo>();
		SituacionDNodo situacionDNodo = SituacionDNodo.getInstancia(codigoDNodo);
		if (situacionDNodo != null) {
			Nodo nodo = Nodo.getInstancia(codigoDNodo);
			List<AtributoDNodo> atributosDNodo = nodo.getAtributosDNodo();
			for (AtributoDNodo atributoDNodo : atributosDNodo) {
				DatoDSistema datoDSistema = atributoDNodo.getDatoDSistema();
				String codigoDDato = datoDSistema.getCodigoDDato();
				SituacionDDatoDSistema situacionDDatoDSistema = SituacionDDatoDSistema.getInstancia(codigoDDato);
				if (situacionDDatoDSistema != null) {
					List<SituacionDAtributoDNodo> situacionesDAtributoDNodo = atributoDNodo.getSituacionesDAtributoDNodo();
					Collections.sort(situacionesDAtributoDNodo);
					for (Integer i = Integer.valueOf(situacionesDAtributoDNodo.size()); i > 0; i--) {
						SituacionDAtributoDNodo situacionDAtributoDNodo = situacionesDAtributoDNodo.get(i);
						NumeroDVersion numeroDVersion = situacionDAtributoDNodo.getNumeroDVersion();
						if (NumerosDVersionUtil.isVersionDElementoValida(numeroDVersion))
							lista.add(situacionDAtributoDNodo);
					}
				}
			}
		}
		return lista.toArray(new SituacionDAtributoDNodo[0]);
	}

}
