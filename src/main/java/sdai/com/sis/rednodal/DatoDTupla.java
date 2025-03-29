package sdai.com.sis.rednodal;

import sdai.com.sis.rednodal.atributosdtupla.accesoadatos.AtributoDTupla;
import sdai.com.sis.rednodal.atributosdtupla.accesoadatos.SituacionDAtributoDTupla;
import sdai.com.sis.rednodal.datosdsistema.accesoadatos.DatoDSistema;
import sdai.com.sis.rednodal.datosdsistema.accesoadatos.SituacionDDatoDSistema;

/**
 * @date 26/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
public final class DatoDTupla {

	private final DatoDRed datoDRed;
	private final String valorDAtributo;

	DatoDTupla(SituacionDAtributoDTupla situacionDAtributoDTupla) throws Exception {
		AtributoDTupla atributoDTupla = situacionDAtributoDTupla.getAtributoDTupla();
		DatoDSistema datoDSistema = atributoDTupla.getDatoDSistema();
		String codigoDDato = datoDSistema.getCodigoDDato();
		SituacionDDatoDSistema situacionDDatoDSistema = SituacionDDatoDSistema.getInstancia(codigoDDato);
		this.datoDRed = new DatoDRed(situacionDDatoDSistema);
		this.valorDAtributo = situacionDAtributoDTupla.getValorDAtributo();
	}

	public DatoDRed getDatoDRed() {
		return datoDRed;
	}

	public String getValorDAtributo() {
		return valorDAtributo;
	}

}
