package sdai.com.sis.rednodal;

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

	TuplaDNodo(SituacionDTupla situacionDTupla) {
		Tupla tupla = situacionDTupla.getTupla();
		this.codigoDTupla = tupla.getCodigoDTupla();
		this.descripcionDTupla = situacionDTupla.getDescripcionDTupla();
	}

	public String getCodigoDTupla() {
		return codigoDTupla;
	}

	public String getDescripcionDTupla() {
		return descripcionDTupla;
	}

}
