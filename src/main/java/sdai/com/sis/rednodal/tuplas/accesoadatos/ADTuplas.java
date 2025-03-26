package sdai.com.sis.rednodal.tuplas.accesoadatos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Node;

import sdai.com.sis.accesoadatos.AbstractAccesoADatos;
import sdai.com.sis.accesoadatos.IAccesoADatosCFG;
import sdai.com.sis.conexiones.IdQuery;
import sdai.com.sis.rednodal.nodos.accesoadatos.Nodo;
import sdai.com.sis.rednodal.nodos.accesoadatos.SituacionDNodo;
import sdai.com.sis.rednodal.tuplas.KTuplas;
import sdai.com.sis.versionado.numerosdversion.NumerosDVersionUtil;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;

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

		}
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
