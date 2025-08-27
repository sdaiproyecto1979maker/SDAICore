package sdai.com.sis.accionesdsistema.rednodal;

import sdai.com.sis.accionesdsistema.AccionDSistemaImplLocal;
import sdai.com.sis.rednodal.ElementoDRed;
import sdai.com.sis.rednodal.TuplaDNodoLocal;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class AccionDSistemaImpl extends ElementoDRed implements AccionDSistemaImplLocal {

    protected static final String CODIGONODO = "ACCIOSISTE";

    protected static final String CODIACCION = "CODIACCION";
    protected static final String DESCACCION = "DESCACCION";
    protected static final String CDQUALIFER = "CDQUALIFER";
    protected static final String PROCDESTIN = "PROCDESTIN";

    AccionDSistemaImpl(TuplaDNodoLocal tuplaDNodoLocal) {
        super(tuplaDNodoLocal);
    }

    @Override
    public String getCodigoDAccion() {
        return getValorString(CODIACCION);
    }

    @Override
    public String getDescripcionDAccion() {
        return getValorString(DESCACCION);
    }

    @Override
    public String getCodigoDQualifer() {
        return getValorString(CDQUALIFER);
    }

    @Override
    public String getProcesoDestino() {
        return getValorString(PROCDESTIN);
    }

}
