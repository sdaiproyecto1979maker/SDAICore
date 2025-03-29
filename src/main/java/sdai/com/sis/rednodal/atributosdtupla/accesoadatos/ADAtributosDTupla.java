package sdai.com.sis.rednodal.atributosdtupla.accesoadatos;

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
import sdai.com.sis.rednodal.atributosdtupla.KAtributosDTupla;
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
import sdai.com.sis.versionado.proyectosdaplicacion.ProyectosDAplicacion;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 15/03/2025
 * @since 1.0.0.0-RELEASE
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
			String codigoDTupla = DocumentoXML.getStringValueNodeDescendencia(node, KTuplas.KTupla.AtributosDEntidad.CODIGTUPLA);
			String codigoDDato = DocumentoXML.getStringValueNodeDescendencia(node, KDatosDSistema.KDatoDSistema.AtributosDEntidad.CODIGODATO);
			AtributoDTupla atributoDTupla = AtributoDTupla.getInstancia(codigoDTupla, codigoDDato);
			if (atributoDTupla != null) {
				SituacionDAtributoDTupla situacionDAtributoDTupla = SituacionDAtributoDTupla.getInstancia(codigoDTupla, codigoDDato);
				if (existenDiferencias(situacionDAtributoDTupla, node))
					createNewSituacionDAtributoDTupla(atributoDTupla, node, codigoDProyectoDAplicacion);
			} else {
				atributoDTupla = createNewAtributoDTupla(node, codigoDProyectoDAplicacion);
				createNewSituacionDAtributoDTupla(atributoDTupla, node, codigoDProyectoDAplicacion);
			}
		}
	}

	private AtributoDTupla createNewAtributoDTupla(Node root, String codigoDProyectoDAplicacion) throws Exception {
		ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
		NumeroDVersion numeroDVersion = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
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
		ProyectosDAplicacion proyectosDAplicacion = ProyectosDAplicacion.getInstancia();
		NumeroDVersion numeroDVersion = proyectosDAplicacion.getNumeroDVersion(codigoDProyectoDAplicacion);
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

	private Boolean existenDiferencias(SituacionDAtributoDTupla situacionDAtributoDTupla, Node root) {
		if (!situacionDAtributoDTupla.getValorDAtributo().equals(DocumentoXML.getStringValueNodeDescendencia(root, KAtributosDTupla.KSituacionDAtributoDTupla.AtributosDEntidad.VALORATRIB)))
			return Boolean.valueOf(true);
		if (!situacionDAtributoDTupla.getSwEntidadActiva().equals(DocumentoXML.getBooleanValueNodeDescendencia(root, KAplicaciones.AtributosDEntidad.SWENTACTIV)))
			return Boolean.valueOf(true);
		return Boolean.valueOf(false);
	}

	AtributoDTupla getAtributoDTupla(String codigoDTupla, String codigoDDato) throws Exception {
		IdQuery idQuery = new IdQuery(KAtributosDTupla.KAtributoDTupla.NamedQueries.SATRTU0000);
		idQuery.addParametroDQuery(KTuplas.KTupla.AtributosDEntidad.CODIGTUPLA, codigoDTupla);
		idQuery.addParametroDQuery(KDatosDSistema.KDatoDSistema.AtributosDEntidad.CODIGODATO, codigoDDato);
		AtributoDTupla atributoDTupla = (AtributoDTupla) ejecutarConsultaSimple(idQuery);
		return atributoDTupla;
	}

	SituacionDAtributoDTupla getSituacionDAtributoDTupla(String codigoDTupla, String codigoDDato) throws Exception {
		SituacionDTupla situacionDTupla = SituacionDTupla.getInstancia(codigoDTupla);
		SituacionDDatoDSistema situacionDDatoDSistema = SituacionDDatoDSistema.getInstancia(codigoDDato);
		if (situacionDTupla != null && situacionDDatoDSistema != null) {
			AtributoDTupla atributoDTupla = AtributoDTupla.getInstancia(codigoDTupla, codigoDDato);
			SituacionDAtributoDTupla[] instancias = atributoDTupla.getSituacionesDAtributoDTupla().toArray(new SituacionDAtributoDTupla[0]);
			Util.ordenar(instancias, AbstractEntidadCFG.NUMVERSDES);
			for (SituacionDAtributoDTupla instancia : instancias) {
				NumeroDVersion numeroDVersion = instancia.getNumeroDVersion();
				if (NumerosDVersionUtil.isVersionDElementoValida(numeroDVersion))
					return instancia;
			}
		}
		return null;
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
					SituacionDAtributoDTupla[] instancias = atributoDTupla.getSituacionesDAtributoDTupla().toArray(new SituacionDAtributoDTupla[0]);
					Util.ordenar(instancias, AbstractEntidadCFG.NUMVERSDES);
					for (SituacionDAtributoDTupla instancia : instancias) {
						NumeroDVersion numeroDVersion = instancia.getNumeroDVersion();
						if (NumerosDVersionUtil.isVersionDElementoValida(numeroDVersion))
							lista.add(instancia);
					}
					return lista.toArray(new SituacionDAtributoDTupla[0]);
				}
			}
		}
		return new SituacionDAtributoDTupla[0];
	}

}
