package sdai.com.sis.accionesdsistema.rednodal;

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
public final class AccionDSistemaImpl extends ElementoDRed implements AccionDSistemaImplLocal {

    private static final String CODIGONODO = "ACCIOSISTE";

    private static final String CODIACCION = "CODIACCION";
    private static final String DESCACCION = "DESCACCION";
    private static final String CDQUALIFER = "CDQUALIFER";
    private static final String PROCDESTIN = "PROCDESTIN";
    private static final String SISTERULES = "SISTERULES";

    private AccionDSistemaImpl(TuplaDNodoLocal tuplaDNodoLocal) {
        super(tuplaDNodoLocal);
    }

    public static AccionDSistemaImpl getInstancia(String codigoDAccion) throws ErrorGeneral {
        KeyCache keyCache = KeyCache.getInstancia(AccionDSistemaImpl.class, codigoDAccion);
        GlobalCaches globalCaches = CDI.current().select(GlobalCaches.class).get();
        CacheDSistemaLocal cacheDSistemaLocal = globalCaches.getCacheDSistemaLocal(KCachesDSistema.CachesDSistema.CACHEREDNO);
        AccionDSistemaImpl instancia = (AccionDSistemaImpl) cacheDSistemaLocal.recuperarInstancia(keyCache);
        if (instancia == null) {
            NodosDRed nodosDRed = CDI.current().select(NodosDRed.class).get();
            NodoDRedLocal nodoDRedLocal = nodosDRed.getNodoDRedLocal(CODIGONODO);
            TuplaDNodoLocal tuplaDNodoLocal = nodoDRedLocal.getTuplaDNodo(CODIACCION, codigoDAccion);
            instancia = new AccionDSistemaImpl(tuplaDNodoLocal);
            cacheDSistemaLocal.almacenarInstancia(keyCache, instancia);
        }
        return instancia;
    }

    @Override
    public String getCodigoDAccion() {
        return getValorString(CODIACCION);
    }

    @Override
    public String getDescripcionDAccion() {
        return getValorString(DESCACCION);
    }

    @Override
    public String getCodigoDQualifer() {
        return getValorString(CDQUALIFER);
    }

    @Override
    public String getProcesoDestino() {
        return getValorString(PROCDESTIN);
    }
    
    @Override
    public String getSistemaDReglas() {
        return getValorString(SISTERULES);
    }

}
