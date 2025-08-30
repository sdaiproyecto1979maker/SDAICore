package sdai.com.sis.dataswaps.rednodal;

import jakarta.enterprise.inject.spi.CDI;
import java.util.ArrayList;
import java.util.List;
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
 * @date 28/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class EntidadDataSwapImpl extends ElementoDRed implements EntidadDataSwapImplLocal {

    private static final String CODIGONODO = "ENTIDASWAP";

    private static final String CDDATASWAP = "CDDATASWAP";
    private static final String CODIENTITY = "CODIENTITY";

    private EntidadDataSwapImpl(TuplaDNodoLocal tuplaDNodoLocal) {
        super(tuplaDNodoLocal);
    }

    public static EntidadDataSwapImpl[] getInstancias(String codigoDDataSwap) throws ErrorGeneral {
        KeyCache keyCache = KeyCache.getInstancia(EntidadDataSwapImpl.class, codigoDDataSwap);
        GlobalCaches globalCaches = CDI.current().select(GlobalCaches.class).get();
        CacheDSistemaLocal cacheDSistemaLocal = globalCaches.getCacheDSistemaLocal(KCachesDSistema.CachesDSistema.CACHEREDNO);
        EntidadDataSwapImpl[] instancias = (EntidadDataSwapImpl[]) cacheDSistemaLocal.recuperarInstancia(keyCache);
        if (instancias == null) {
            List<EntidadDataSwapImpl> lista = new ArrayList<>();
            NodosDRed nodosDRed = CDI.current().select(NodosDRed.class).get();
            NodoDRedLocal nodoDRedLocal = nodosDRed.getNodoDRedLocal(CODIGONODO);
            TuplaDNodoLocal[] tuplasDNodoLocal = nodoDRedLocal.getTuplasDNodo(CDDATASWAP, codigoDDataSwap);
            if (tuplasDNodoLocal != null && tuplasDNodoLocal.length > 0) {
                for (TuplaDNodoLocal tuplaDNodoLocal : tuplasDNodoLocal) {
                    EntidadDataSwapImpl instancia = new EntidadDataSwapImpl(tuplaDNodoLocal);
                    lista.add(instancia);
                }
            }
            instancias = lista.toArray(EntidadDataSwapImpl[]::new);
            cacheDSistemaLocal.almacenarInstancia(keyCache, instancias);
        }
        return instancias;
    }

    @Override
    public String getCodigoDDataSwap() {
        return getValorString(CDDATASWAP);
    }

    @Override
    public String getCodigoDEntidad() {
        return getValorString(CODIENTITY);
    }
}
