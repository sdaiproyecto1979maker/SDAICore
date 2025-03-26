package sdai.com.sis.rednodal.atributosdnodo.accesoadatos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Node;

import sdai.com.sis.accesoadatos.AbstractAccesoADatos;
import sdai.com.sis.accesoadatos.IAccesoADatosCFG;
import sdai.com.sis.conexiones.IdQuery;
import sdai.com.sis.rednodal.atributosdnodo.AtributosDNodoUtil;
import sdai.com.sis.rednodal.atributosdnodo.KAtributosDNodo;
import sdai.com.sis.rednodal.datosdsistema.KDatosDSistema;
import sdai.com.sis.rednodal.datosdsistema.accesoadatos.DatoDSistema;
import sdai.com.sis.rednodal.datosdsistema.accesoadatos.SituacionDDatoDSistema;
import sdai.com.sis.rednodal.nodos.KNodos;
import sdai.com.sis.rednodal.nodos.accesoadatos.Nodo;
import sdai.com.sis.rednodal.nodos.accesoadatos.SituacionDNodo;
import sdai.com.sis.versionado.numerosdversion.NumerosDVersionUtil;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;

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
			AtributoDNodo atributoDNodo = AtributosDNodoUtil.getAtributoDNodo(node);
			if (atributoDNodo != null) {
				Nodo nodo = atributoDNodo.getNodo();
				DatoDSistema datoDSistema = atributoDNodo.getDatoDSistema();
				SituacionDAtributoDNodo situacionDAtributoDNodo = AtributosDNodoUtil.getSituacionDAtributoDNodo(nodo, datoDSistema);
				if (AtributosDNodoUtil.existenCambiosEnSituacionDAtributoDNodo(situacionDAtributoDNodo, node))
					AtributosDNodoUtil.createSituacionDAtributoDNodoNewVersion(codigoDProyectoDAplicacion, atributoDNodo, node);
			} else {
				atributoDNodo = AtributosDNodoUtil.createAtributoDNodo(node);
				AtributosDNodoUtil.createSituacionDAtributoDNodoNewVersion(codigoDProyectoDAplicacion, atributoDNodo, node);
			}
		}
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
