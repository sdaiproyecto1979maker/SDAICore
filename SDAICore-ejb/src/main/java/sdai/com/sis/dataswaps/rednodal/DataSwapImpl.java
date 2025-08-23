package sdai.com.sis.dataswaps.rednodal;

import sdai.com.sis.dataswaps.DataSwapImplLocal;
import sdai.com.sis.rednodal.ElementoDRed;
import sdai.com.sis.rednodal.TuplaDNodoLocal;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class DataSwapImpl extends ElementoDRed implements DataSwapImplLocal {

    protected static final String CODIGONODO = "DFDATASWAP";

    protected static final String CDDATASWAP = "CDDATASWAP";
    protected static final String DSDATASWAP = "DSDATASWAP";
    protected static final String CDQUALIFER = "CDQUALIFER";

    DataSwapImpl(TuplaDNodoLocal tuplaDNodoLocal) {
        super(tuplaDNodoLocal);
    }

    @Override
    public String getCodigoDDataSwap() {
        return getValorString(CDDATASWAP);
    }

    @Override
    public String getDescripcionDDataSwap() {
        return getValorString(DSDATASWAP);
    }

    @Override
    public String getCodigoDQualifer() {
        return getValorString(CDQUALIFER);
    }

}
