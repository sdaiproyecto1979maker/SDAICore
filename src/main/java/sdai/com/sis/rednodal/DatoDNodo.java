package sdai.com.sis.rednodal;

import sdai.com.sis.rednodal.atributosdnodo.accesoadatos.AtributoDNodo;
import sdai.com.sis.rednodal.atributosdnodo.accesoadatos.SituacionDAtributoDNodo;
import sdai.com.sis.rednodal.datosdsistema.accesoadatos.DatoDSistema;
import sdai.com.sis.rednodal.datosdsistema.accesoadatos.SituacionDDatoDSistema;

/**
 * @date 26/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
public final class DatoDNodo {

	private final DatoDRed datoDRed;

	DatoDNodo(SituacionDAtributoDNodo situacionDAtributoDNodo) throws Exception {
		AtributoDNodo atributoDNodo = situacionDAtributoDNodo.getAtributoDNodo();
		DatoDSistema datoDSistema = atributoDNodo.getDatoDSistema();
		String codigoDDato = datoDSistema.getCodigoDDato();
		SituacionDDatoDSistema situacionDDatoDSistema = SituacionDDatoDSistema.getInstancia(codigoDDato);
		this.datoDRed = new DatoDRed(situacionDDatoDSistema);
	}

	public DatoDRed getDatoDRed() {
		return datoDRed;
	}

}
