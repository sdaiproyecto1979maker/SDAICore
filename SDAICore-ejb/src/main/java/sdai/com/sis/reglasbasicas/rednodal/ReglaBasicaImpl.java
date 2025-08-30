package sdai.com.sis.reglasbasicas.rednodal;

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
 * @date 29/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class ReglaBasicaImpl extends ElementoDRed implements ReglaBasicaImplLocal {

    private static final String CODIGONODO = "RULESBASIC";

    private static final String CODIGORULE = "CODIGORULE";
    private static final String DESCRDRULE = "DESCRDRULE";
    private static final String CDQUALIFER = "CDQUALIFER";

    private ReglaBasicaImpl(TuplaDNodoLocal tuplaDNodoLocal) {
        super(tuplaDNodoLocal);
    }

    public static ReglaBasicaImpl getInstancia(String codigoDRegla) throws ErrorGeneral {
        KeyCache keyCache = KeyCache.getInstancia(ReglaBasicaImpl.class, codigoDRegla);
        GlobalCaches globalCaches = CDI.current().select(GlobalCaches.class).get();
        CacheDSistemaLocal cacheDSistemaLocal = globalCaches.getCacheDSistemaLocal(KCachesDSistema.CachesDSistema.CACHEREDNO);
        ReglaBasicaImpl instancia = (ReglaBasicaImpl) cacheDSistemaLocal.recuperarInstancia(keyCache);
        if (instancia == null) {
            NodosDRed nodosDRed = CDI.current().select(NodosDRed.class).get();
            NodoDRedLocal nodoDRedLocal = nodosDRed.getNodoDRedLocal(CODIGONODO);
            TuplaDNodoLocal tuplaDNodoLocal = nodoDRedLocal.getTuplaDNodo(CODIGORULE, codigoDRegla);
            instancia = new ReglaBasicaImpl(tuplaDNodoLocal);
            cacheDSistemaLocal.almacenarInstancia(keyCache, instancia);
        }
        return instancia;
    }

    @Override
    public String getCodigoDRegla() {
        return getValorString(CODIGORULE);
    }

    @Override
    public String getDescripcionDRegla() {
        return getValorString(DESCRDRULE);
    }

    @Override
    public String getCodigoDQualifer() {
        return getValorString(CDQUALIFER);
    }

}
