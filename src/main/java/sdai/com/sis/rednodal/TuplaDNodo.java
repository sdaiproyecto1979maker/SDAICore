package sdai.com.sis.rednodal;

import java.util.ArrayList;
import java.util.List;

import sdai.com.sis.rednodal.atributosdtupla.accesoadatos.SituacionDAtributoDTupla;
import sdai.com.sis.rednodal.tuplas.accesoadatos.SituacionDTupla;
import sdai.com.sis.rednodal.tuplas.accesoadatos.Tupla;

/**
 * @date 26/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class TuplaDNodo {

	private final String codigoDTupla;
	private final String descripcionDTupla;
	private final DatoDTupla[] datosDTupla;

	TuplaDNodo(SituacionDTupla situacionDTupla) throws Exception {
		Tupla tupla = situacionDTupla.getTupla();
		this.codigoDTupla = tupla.getCodigoDTupla();
		this.descripcionDTupla = situacionDTupla.getDescripcionDTupla();
		this.datosDTupla = loadDatosDTupla(codigoDTupla);
	}

	private DatoDTupla[] loadDatosDTupla(String codigoDTupla) throws Exception {
		List<DatoDTupla> lista = new ArrayList<DatoDTupla>();
		SituacionDAtributoDTupla[] situacionesDAtributoDTupla = SituacionDAtributoDTupla.getInstancias(codigoDTupla);
		for (SituacionDAtributoDTupla situacionDAtributoDTupla : situacionesDAtributoDTupla) {
			DatoDTupla datoDTupla = new DatoDTupla(situacionDAtributoDTupla);
			lista.add(datoDTupla);
		}
		return lista.toArray(new DatoDTupla[0]);
	}

	public String getCodigoDTupla() {
		return codigoDTupla;
	}

	public String getDescripcionDTupla() {
		return descripcionDTupla;
	}

	public DatoDTupla[] getDatosDTupla() {
		return datosDTupla;
	}

}
