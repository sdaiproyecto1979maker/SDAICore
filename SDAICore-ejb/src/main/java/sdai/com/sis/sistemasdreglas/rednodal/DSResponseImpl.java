package sdai.com.sis.sistemasdreglas.rednodal;

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
import sdai.com.sis.sistemasdreglas.rednodal.DSResponseImplLocal;

/**
 * @date 29/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class DSResponseImpl extends ElementoDRed implements DSResponseImplLocal {

    private static final String CODIGONODO = "DFRESPONSE";

    private static final String CDRESPONSE = "CDRESPONSE";
    private static final String DSRESPONSE = "DSRESPONSE";
    private static final String CDQUALIFER = "CDQUALIFER";

    private DSResponseImpl(TuplaDNodoLocal tuplaDNodoLocal) {
        super(tuplaDNodoLocal);
    }

    public static DSResponseImpl getInstancia(String codigoDResponse) throws ErrorGeneral {
        KeyCache keyCache = KeyCache.getInstancia(DSResponseImpl.class, codigoDResponse);
        GlobalCaches globalCaches = CDI.current().select(GlobalCaches.class).get();
        CacheDSistemaLocal cacheDSistemaLocal = globalCaches.getCacheDSistemaLocal(KCachesDSistema.CachesDSistema.CACHEREDNO);
        DSResponseImpl instancia = (DSResponseImpl) cacheDSistemaLocal.recuperarInstancia(keyCache);
        if (instancia == null) {
            NodosDRed nodosDRed = CDI.current().select(NodosDRed.class).get();
            NodoDRedLocal nodoDRedLocal = nodosDRed.getNodoDRedLocal(CODIGONODO);
            TuplaDNodoLocal tuplaDNodoLocal = nodoDRedLocal.getTuplaDNodo(CDRESPONSE, codigoDResponse);
            instancia = new DSResponseImpl(tuplaDNodoLocal);
            cacheDSistemaLocal.almacenarInstancia(keyCache, instancia);
        }
        return instancia;
    }

    @Override
    public String getCodigoDResponse() {
        return getValorString(CDRESPONSE);
    }

    @Override
    public String getDescripcionDResponse() {
        return getValorString(DSRESPONSE);
    }

    @Override
    public String getCodigoDQualifer() {
        return getValorString(CDQUALIFER);
    }

}
