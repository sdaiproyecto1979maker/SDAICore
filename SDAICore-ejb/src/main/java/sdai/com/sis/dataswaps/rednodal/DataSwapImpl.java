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

    public static final String CODIGONODO = "DFDATASWAP";

    public static final String CDDATASWAP = "CDDATASWAP";
    public static final String DSDATASWAP = "DSDATASWAP";
    public static final String CDQUALIFER = "CDQUALIFER";

    public DataSwapImpl(TuplaDNodoLocal tuplaDNodoLocal) {
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
