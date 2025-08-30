package sdai.com.sis.procesosdsesion.rednodal;

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
 * @date 25/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class DataSwapDProcesoImpl extends ElementoDRed implements DataSwapDProcesoImplLocal {

    private static final String CODIGONODO = "DASWAPROCE";

    private static final String CODIGPROCE = "CODIGPROCE";
    private static final String CDDATASWAP = "CDDATASWAP";

    private DataSwapDProcesoImpl(TuplaDNodoLocal tuplaDNodoLocal) {
        super(tuplaDNodoLocal);
    }

    public static DataSwapDProcesoImpl getInstancia(String codigoDProceso) throws ErrorGeneral {
        KeyCache keyCache = KeyCache.getInstancia(DataSwapDProcesoImpl.class, codigoDProceso);
        GlobalCaches globalCaches = CDI.current().select(GlobalCaches.class).get();
        CacheDSistemaLocal cacheDSistemaLocal = globalCaches.getCacheDSistemaLocal(KCachesDSistema.CachesDSistema.CACHEREDNO);
        DataSwapDProcesoImpl instancia = (DataSwapDProcesoImpl) cacheDSistemaLocal.recuperarInstancia(keyCache);
        if (instancia == null) {
            NodosDRed nodosDRed = CDI.current().select(NodosDRed.class).get();
            NodoDRedLocal nodoDRedLocal = nodosDRed.getNodoDRedLocal(CODIGONODO);
            TuplaDNodoLocal tuplaDNodoLocal = nodoDRedLocal.getTuplaDNodo(CODIGPROCE, codigoDProceso);
            instancia = new DataSwapDProcesoImpl(tuplaDNodoLocal);
            cacheDSistemaLocal.almacenarInstancia(keyCache, instancia);
        }
        return instancia;
    }

    @Override
    public String getCodigoDProceso() {
        return getValorString(CODIGPROCE);
    }

    @Override
    public String getCodigoDDataSwap() {
        return getValorString(CDDATASWAP);
    }
}
