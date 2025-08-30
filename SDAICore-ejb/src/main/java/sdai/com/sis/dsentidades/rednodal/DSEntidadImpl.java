package sdai.com.sis.dsentidades.rednodal;

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
public final class DSEntidadImpl extends ElementoDRed implements DSEntidadImplLocal {

    private static final String CODIGONODO = "DFDSENTITY";

    private static final String CODIENTITY = "CODIENTITY";
    private static final String DESCENTITY = "DESCENTITY";
    private static final String CDQUALIFER = "CDQUALIFER";

    private DSEntidadImpl(TuplaDNodoLocal tuplaDNodoLocal) {
        super(tuplaDNodoLocal);
    }

    public static DSEntidadImpl getInstancia(String codigoDEntidad) throws ErrorGeneral {
        KeyCache keyCache = KeyCache.getInstancia(DSEntidadImpl.class, codigoDEntidad);
        GlobalCaches globalCaches = CDI.current().select(GlobalCaches.class).get();
        CacheDSistemaLocal cacheDSistemaLocal = globalCaches.getCacheDSistemaLocal(KCachesDSistema.CachesDSistema.CACHEREDNO);
        DSEntidadImpl instancia = (DSEntidadImpl) cacheDSistemaLocal.recuperarInstancia(keyCache);
        if (instancia == null) {
            NodosDRed nodosDRed = CDI.current().select(NodosDRed.class).get();
            NodoDRedLocal nodoDRedLocal = nodosDRed.getNodoDRedLocal(CODIGONODO);
            TuplaDNodoLocal tuplaDNodoLocal = nodoDRedLocal.getTuplaDNodo(CODIENTITY, codigoDEntidad);
            instancia = new DSEntidadImpl(tuplaDNodoLocal);
            cacheDSistemaLocal.almacenarInstancia(keyCache, instancia);
        }
        return instancia;
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
