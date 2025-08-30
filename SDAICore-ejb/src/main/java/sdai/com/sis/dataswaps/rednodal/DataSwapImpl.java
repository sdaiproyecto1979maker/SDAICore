package sdai.com.sis.dataswaps.rednodal;

import jakarta.enterprise.inject.spi.CDI;
import sdai.com.sis.cachesdsistema.CacheDSistemaLocal;
import sdai.com.sis.cachesdsistema.GlobalCaches;
import sdai.com.sis.cachesdsistema.KCachesDSistema;
import sdai.com.sis.cachesdsistema.KeyCache;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.rednodal.ElementoDRed;
import sdai.com.sis.rednodal.NodoDRedLocal;
import sdai.com.sis.rednodal.NodosDRed;
import sdai.com.sis.rednodal.TuplaDNodoLocal;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class DataSwapImpl extends ElementoDRed implements DataSwapImplLocal {

    private static final String CODIGONODO = "DFDATASWAP";

    private static final String CDDATASWAP = "CDDATASWAP";
    private static final String DSDATASWAP = "DSDATASWAP";
    private static final String CDQUALIFER = "CDQUALIFER";

    private DataSwapImpl(TuplaDNodoLocal tuplaDNodoLocal) {
        super(tuplaDNodoLocal);
    }

    public static DataSwapImpl getInstancia(String codigoDDataSwap) throws ErrorGeneral {
        KeyCache keyCache = KeyCache.getInstancia(DataSwapImpl.class, codigoDDataSwap);
        GlobalCaches globalCaches = CDI.current().select(GlobalCaches.class).get();
        CacheDSistemaLocal cacheDSistemaLocal = globalCaches.getCacheDSistemaLocal(KCachesDSistema.CachesDSistema.CACHEREDNO);
        DataSwapImpl instancia = (DataSwapImpl) cacheDSistemaLocal.recuperarInstancia(keyCache);
        if (instancia == null) {
            NodosDRed nodosDRed = CDI.current().select(NodosDRed.class).get();
            NodoDRedLocal nodoDRedLocal = nodosDRed.getNodoDRedLocal(CODIGONODO);
            TuplaDNodoLocal tuplaDNodoLocal = nodoDRedLocal.getTuplaDNodo(CDDATASWAP, codigoDDataSwap);
            instancia = new DataSwapImpl(tuplaDNodoLocal);
            cacheDSistemaLocal.almacenarInstancia(keyCache, instancia);
        }
        return instancia;
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
