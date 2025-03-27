package sdai.com.sis.rednodal.atributosdtupla.accesoadatos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Node;

import sdai.com.sis.accesoadatos.AbstractAccesoADatos;
import sdai.com.sis.accesoadatos.IAccesoADatosCFG;
import sdai.com.sis.cacchesdsistema.KeyCache;
import sdai.com.sis.cacchesdsistema.contenedores.CacheDRednodal;
import sdai.com.sis.rednodal.datosdsistema.KDatosDSistema;
import sdai.com.sis.rednodal.datosdsistema.accesoadatos.DatoDSistema;
import sdai.com.sis.rednodal.datosdsistema.accesoadatos.SituacionDDatoDSistema;
import sdai.com.sis.rednodal.tuplas.KTuplas;
import sdai.com.sis.rednodal.tuplas.accesoadatos.SituacionDTupla;
import sdai.com.sis.rednodal.tuplas.accesoadatos.Tupla;
import sdai.com.sis.utilidades.Reflexion;
import sdai.com.sis.utilidades.Util;
import sdai.com.sis.versionado.numerosdversion.NumerosDVersionUtil;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 15/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class ADAtributosDTupla extends AbstractAccesoADatos implements IAccesoADatosCFG {

	ADAtributosDTupla() {
		super();
		marcarEntornoDConfiguracion();
	}

	ADAtributosDTupla(String idDConexion) throws Exception {
		super(idDConexion);
	}

	@Override
	public void generateElementosVersionEnCurso(String codigoDProyectoDAplicacion, Node[] nodes) throws Exception {
		for (Node node : nodes) {
			// TODO Desarrollar la parte de la comprobación de los nodos cuando haya
			// conexion
			AtributoDTupla atributoDTupla = createNewAtributoDTupla(node, codigoDProyectoDAplicacion);
			createNewSituacionDAtributoDTupla(atributoDTupla, node, codigoDProyectoDAplicacion);
		}
	}

	private AtributoDTupla createNewAtributoDTupla(Node root, String codigoDProyectoDAplicacion) throws Exception {
		// TODO: Descomentar las lineas cuando haya conexion
		/*
		 * ProyectosDAplicacion proyectosDAplicacion =
		 * ProyectosDAplicacion.getInstancia(); NumeroDVersion numeroDVersion =
		 * proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
		 */
		// TODO: Quitar esta linea despues de descomentar las anteriores
		NumeroDVersion numeroDVersion = (NumeroDVersion) Reflexion.createInstancia(NumeroDVersion.class);
		Integer numeroDSituacion = Integer.valueOf(1);
		AtributoDTupla atributoDTupla = (AtributoDTupla) Reflexion.createInstancia(AtributoDTupla.class);
		atributoDTupla.addNode(numeroDVersion, numeroDSituacion, root);
		String codigoDTupla = DocumentoXML.getStringValueNodeDescendencia(root, KTuplas.KTupla.AtributosDEntidad.CODIGTUPLA);
		Tupla tupla = Tupla.getInstancia(codigoDTupla);
		atributoDTupla.setTupla(tupla);
		String codigoDDato = DocumentoXML.getStringValueNodeDescendencia(root, KDatosDSistema.KDatoDSistema.AtributosDEntidad.CODIGODATO);
		DatoDSistema datoDSistema = DatoDSistema.getInstancia(codigoDDato);
		atributoDTupla.setDatoDSistema(datoDSistema);
		KeyCache keyCache = KeyCache.getInstancia(AtributoDTupla.class, Boolean.valueOf(false), codigoDTupla, codigoDDato);
		CacheDRednodal.almacenarInstancia(keyCache, atributoDTupla);
		return atributoDTupla;
	}

	private void createNewSituacionDAtributoDTupla(AtributoDTupla atributoDTupla, Node root, String codigoDProyectoDAplicacion) throws Exception {
		// TODO: Descomentar las lineas cuando haya conexion
		/*
		 * ProyectosDAplicacion proyectosDAplicacion =
		 * ProyectosDAplicacion.getInstancia(); NumeroDVersion numeroDVersion =
		 * proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
		 */
		// TODO: Quitar esta linea despues de descomentar las anteriores
		NumeroDVersion numeroDVersion = (NumeroDVersion) Reflexion.createInstancia(NumeroDVersion.class);
		Integer numeroDSituacion = atributoDTupla.getSituacionesDAtributoDTupla().size() + 1;
		SituacionDAtributoDTupla situacionDAtributoDTupla = (SituacionDAtributoDTupla) Reflexion.createInstancia(SituacionDAtributoDTupla.class);
		situacionDAtributoDTupla.addNode(numeroDVersion, numeroDSituacion, root);
		situacionDAtributoDTupla.setAtributoDTupla(atributoDTupla);
		atributoDTupla.setSituacionDAtributoDTupla(situacionDAtributoDTupla);
		atributoDTupla.getSituacionesDAtributoDTupla().add(situacionDAtributoDTupla);
		String codigoDTupla = DocumentoXML.getStringValueNodeDescendencia(root, KTuplas.KTupla.AtributosDEntidad.CODIGTUPLA);
		String codigoDDato = DocumentoXML.getStringValueNodeDescendencia(root, KDatosDSistema.KDatoDSistema.AtributosDEntidad.CODIGODATO);
		KeyCache keyCache = KeyCache.getInstancia(SituacionDAtributoDTupla.class, Boolean.valueOf(false), codigoDTupla, codigoDDato);
		CacheDRednodal.almacenarInstancia(keyCache, situacionDAtributoDTupla);
		addToCacheDTuplas(codigoDTupla, situacionDAtributoDTupla);
	}

	private void addToCacheDTuplas(String codigoDTupla, SituacionDAtributoDTupla situacionDAtributoDTupla) throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(SituacionDAtributoDTupla.class, codigoDTupla);
		SituacionDAtributoDTupla[] situacionesDAtributoDTupla = (SituacionDAtributoDTupla[]) CacheDRednodal.recuperarInstancia(keyCache);
		if (situacionesDAtributoDTupla == null || situacionesDAtributoDTupla.length == 0)
			situacionesDAtributoDTupla = getSituacionesDAtributoDTupla(codigoDTupla);
		situacionesDAtributoDTupla = (SituacionDAtributoDTupla[]) Util.addItemArray(situacionesDAtributoDTupla, situacionDAtributoDTupla);
		CacheDRednodal.almacenarInstancia(keyCache, situacionesDAtributoDTupla);
	}

	SituacionDAtributoDTupla[] getSituacionesDAtributoDTupla(String codigoDTupla) throws Exception {
		SituacionDTupla situacionDTupla = SituacionDTupla.getInstancia(codigoDTupla);
		if (situacionDTupla != null) {
			Tupla tupla = situacionDTupla.getTupla();
			List<AtributoDTupla> atributosDTupla = tupla.getAtibutosDTupla();
			for (AtributoDTupla atributoDTupla : atributosDTupla) {
				DatoDSistema datoDSistema = atributoDTupla.getDatoDSistema();
				String codigoDDato = datoDSistema.getCodigoDDato();
				SituacionDDatoDSistema situacionDDatoDSistema = SituacionDDatoDSistema.getInstancia(codigoDDato);
				if (situacionDDatoDSistema != null) {
					List<SituacionDAtributoDTupla> lista = new ArrayList<SituacionDAtributoDTupla>();
					List<SituacionDAtributoDTupla> situacionesDAtributoDTupla = atributoDTupla.getSituacionesDAtributoDTupla();
					Collections.sort(situacionesDAtributoDTupla);
					for (Integer i = Integer.valueOf(situacionesDAtributoDTupla.size()); i > 0; i--) {
						SituacionDAtributoDTupla situacionDAtributoDTupla = situacionesDAtributoDTupla.get(i);
						NumeroDVersion numeroDVersion = situacionDAtributoDTupla.getNumeroDVersion();
						if (NumerosDVersionUtil.isVersionDElementoValida(numeroDVersion))
							lista.add(situacionDAtributoDTupla);
					}
					return lista.toArray(new SituacionDAtributoDTupla[0]);
				}
			}
		}
		return new SituacionDAtributoDTupla[0];
	}

}
