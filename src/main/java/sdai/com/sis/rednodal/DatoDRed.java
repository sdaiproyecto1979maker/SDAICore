package sdai.com.sis.rednodal;

import sdai.com.sis.rednodal.datosdsistema.accesoadatos.DatoDSistema;
import sdai.com.sis.rednodal.datosdsistema.accesoadatos.SituacionDDatoDSistema;

/**
 * @date 26/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class DatoDRed {

	private final String codigoDDato;
	private final String descripcionDDato;

	DatoDRed(SituacionDDatoDSistema situacionDDatoDSistema) {
		DatoDSistema datoDSistema = situacionDDatoDSistema.getDatoDSistema();
		this.codigoDDato = datoDSistema.getCodigoDDato();
		this.descripcionDDato = situacionDDatoDSistema.getDescripcionDDato();
	}

	public String getCodigoDDato() {
		return codigoDDato;
	}

	public String getDescripcionDDato() {
		return descripcionDDato;
	}

}
