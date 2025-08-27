package sdai.com.sis.procesosdsesion.rednodal;

import sdai.com.sis.rednodal.ElementoDRed;
import sdai.com.sis.rednodal.TuplaDNodoLocal;

/**
 * @date 25/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class DataSwapDProcesoImpl extends ElementoDRed implements DataSwapDProcesoImplLocal {

    public static final String CODIGONODO = "DASWAPROCE";

    public static final String CODIGPROCE = "CODIGPROCE";
    public static final String CDDATASWAP = "CDDATASWAP";

    public DataSwapDProcesoImpl(TuplaDNodoLocal tuplaDNodoLocal) {
        super(tuplaDNodoLocal);
    }

    @Override
    public String getCodigoDProceso() {
        return getValorString(CODIGPROCE);
    }

    @Override
    public String getCodigoDDataSwap() {
        return getValorString(CDDATASWAP);
    }

    @Override
    public String getCodigoDQualifer() {
        return "";
    }
}
