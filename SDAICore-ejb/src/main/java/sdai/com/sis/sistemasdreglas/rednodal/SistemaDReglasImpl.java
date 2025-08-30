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

/**
 * @date 28/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class SistemaDReglasImpl extends ElementoDRed implements SistemaDReglasImplLocal {

    private static final String CODIGONODO = "SISTERULES";

    private static final String CODIGSISTE = "CODIGSISTE";
    private static final String DESCRSISTE = "DESCRSISTE";
    private static final String CDQUALIFER = "CDQUALIFER";
    private static final String CDRESPONSE = "CDRESPONSE";

    private SistemaDReglasImpl(TuplaDNodoLocal tuplaDNodoLocal) {
        super(tuplaDNodoLocal);
    }

    public static SistemaDReglasImpl getInstancia(String codigoDSistema) throws ErrorGeneral {
        KeyCache keyCache = KeyCache.getInstancia(SistemaDReglasImpl.class, codigoDSistema);
        GlobalCaches globalCaches = CDI.current().select(GlobalCaches.class).get();
        CacheDSistemaLocal cacheDSistemaLocal = globalCaches.getCacheDSistemaLocal(KCachesDSistema.CachesDSistema.CACHEREDNO);
        SistemaDReglasImpl instancia = (SistemaDReglasImpl) cacheDSistemaLocal.recuperarInstancia(keyCache);
        if (instancia == null) {
            NodosDRed nodosDRed = CDI.current().select(NodosDRed.class).get();
            NodoDRedLocal nodoDRedLocal = nodosDRed.getNodoDRedLocal(CODIGONODO);
            TuplaDNodoLocal tuplaDNodoLocal = nodoDRedLocal.getTuplaDNodo(CODIGSISTE, codigoDSistema);
            instancia = new SistemaDReglasImpl(tuplaDNodoLocal);
            cacheDSistemaLocal.almacenarInstancia(keyCache, instancia);
        }
        return instancia;
    }

    @Override
    public String getCodigoDSistema() {
        return getValorString(CODIGSISTE);
    }

    @Override
    public String getDescripcionDSistema() {
        return getValorString(DESCRSISTE);
    }

    @Override
    public String getCodigoDQualifer() {
        return getValorString(CDQUALIFER);
    }

    @Override
    public String getCodigoDResponse() {
        return getValorString(CDRESPONSE);
    }

}
