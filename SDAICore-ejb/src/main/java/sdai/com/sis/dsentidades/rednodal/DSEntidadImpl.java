package sdai.com.sis.dsentidades.rednodal;

import sdai.com.sis.dsentidades.DSEntidadImplLocal;
import sdai.com.sis.rednodal.ElementoDRed;
import sdai.com.sis.rednodal.TuplaDNodoLocal;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class DSEntidadImpl extends ElementoDRed implements DSEntidadImplLocal {

    protected static final String CODIGONODO = "DFDSENTITY";

    protected static final String CODIENTITY = "CODIENTITY";
    protected static final String DESCENTITY = "DESCENTITY";
    protected static final String CDQUALIFER = "CDQUALIFER";

    DSEntidadImpl(TuplaDNodoLocal tuplaDNodoLocal) {
        super(tuplaDNodoLocal);
    }

    @Override
    public String getCodigoDEntidad() {
        return getValorString(CODIENTITY);
    }

    @Override
    public String getDescripcionDEntidad() {
        return getValorString(DESCENTITY);
    }

    @Override
    public String getCodigoDQualifer() {
        return getValorString(CDQUALIFER);
    }

}
