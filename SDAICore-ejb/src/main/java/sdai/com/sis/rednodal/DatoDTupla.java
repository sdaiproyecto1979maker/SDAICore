package sdai.com.sis.rednodal;

import sdai.com.sis.rednodal.atributosdtupla.AtributoDTupla;
import sdai.com.sis.rednodal.datosdsistema.DatoDSistema;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class DatoDTupla implements DatoDTuplaLocal {

    private final String codigoDDato;
    private final String valorDAtributo;

    public DatoDTupla(AtributoDTupla atributoDTupla) {
        DatoDSistema datoDSistema = atributoDTupla.getDatoDSistema();
        this.codigoDDato = datoDSistema.getCodigoDDato();
        this.valorDAtributo = atributoDTupla.getValorDAtributo();
    }

    @Override
    public String getCodigoDDato() {
        return codigoDDato;
    }

    @Override
    public String getValorDAtributo() {
        return valorDAtributo;
    }

}
