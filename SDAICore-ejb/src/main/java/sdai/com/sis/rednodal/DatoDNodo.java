package sdai.com.sis.rednodal;

import sdai.com.sis.rednodal.atributosdnodo.AtributoDNodo;
import sdai.com.sis.rednodal.datosdsistema.DatoDSistema;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class DatoDNodo implements DatoDNodoLocal {

    private final String codigoDDato;

    public DatoDNodo(AtributoDNodo atributoDNodo) {
        DatoDSistema datoDSistema = atributoDNodo.getDatoDSistema();
        this.codigoDDato = datoDSistema.getCodigoDDato();
    }

    @Override
    public String getCodigoDDato() {
        return codigoDDato;
    }

}
