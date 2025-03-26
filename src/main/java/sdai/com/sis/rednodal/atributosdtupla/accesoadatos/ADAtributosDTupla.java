package sdai.com.sis.rednodal.atributosdtupla.accesoadatos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Node;

import sdai.com.sis.accesoadatos.AbstractAccesoADatos;
import sdai.com.sis.accesoadatos.IAccesoADatosCFG;
import sdai.com.sis.rednodal.datosdsistema.accesoadatos.DatoDSistema;
import sdai.com.sis.rednodal.datosdsistema.accesoadatos.SituacionDDatoDSistema;
import sdai.com.sis.rednodal.tuplas.accesoadatos.SituacionDTupla;
import sdai.com.sis.rednodal.tuplas.accesoadatos.Tupla;
import sdai.com.sis.versionado.numerosdversion.NumerosDVersionUtil;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;

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

		}
	}

	SituacionDAtributoDTupla[] getSituacionesDAtributoDTupla(String codigoDTupla) throws Exception {
		List<SituacionDAtributoDTupla> lista = new ArrayList<SituacionDAtributoDTupla>();
		SituacionDTupla situacionDTupla = SituacionDTupla.getInstancia(codigoDTupla);
		if (situacionDTupla != null) {
			Tupla tupla = situacionDTupla.getTupla();
			List<AtributoDTupla> atributosDTupla = tupla.getAtibutosDTupla();
			for (AtributoDTupla atributoDTupla : atributosDTupla) {
				DatoDSistema datoDSistema = atributoDTupla.getDatoDSistema();
				String codigoDDato = datoDSistema.getCodigoDDato();
				SituacionDDatoDSistema situacionDDatoDSistema = SituacionDDatoDSistema.getInstancia(codigoDDato);
				if (situacionDDatoDSistema != null) {
					List<SituacionDAtributoDTupla> situacionesDAtributoDTupla = atributoDTupla.getSituacionesDAtributoDTupla();
					Collections.sort(situacionesDAtributoDTupla);
					for (Integer i = Integer.valueOf(situacionesDAtributoDTupla.size()); i > 0; i--) {
						SituacionDAtributoDTupla situacionDAtributoDTupla = situacionesDAtributoDTupla.get(i);
						NumeroDVersion numeroDVersion = situacionDAtributoDTupla.getNumeroDVersion();
						if (NumerosDVersionUtil.isVersionDElementoValida(numeroDVersion))
							lista.add(situacionDAtributoDTupla);
					}
				}
			}
		}
		return lista.toArray(new SituacionDAtributoDTupla[0]);
	}

}
