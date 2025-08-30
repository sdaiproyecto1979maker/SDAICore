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
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class ProcesoDSesionImpl extends ElementoDRed implements ProcesoDSesionImplLocal {

    private static final String CODIGONODO = "PROCSESION";

    private static final String CODIGPROCE = "CODIGPROCE";
    private static final String DESCRPROCE = "DESCRPROCE";
    private static final String CDQUALIFER = "CDQUALIFER";
    private static final String PAGEDPROCE = "PAGEDPROCE";

    private ProcesoDSesionImpl(TuplaDNodoLocal tuplaDNodoLocal) {
        super(tuplaDNodoLocal);
    }

    public static ProcesoDSesionImpl getInstancia(String codigoDProceso) throws ErrorGeneral {
        KeyCache keyCache = KeyCache.getInstancia(ProcesoDSesionImpl.class, codigoDProceso);
        GlobalCaches globalCaches = CDI.current().select(GlobalCaches.class).get();
        CacheDSistemaLocal cacheDSistemaLocal = globalCaches.getCacheDSistemaLocal(KCachesDSistema.CachesDSistema.CACHEREDNO);
        ProcesoDSesionImpl instancia = (ProcesoDSesionImpl) cacheDSistemaLocal.recuperarInstancia(keyCache);
        if (instancia == null) {
            NodosDRed nodosDRed = CDI.current().select(NodosDRed.class).get();
            NodoDRedLocal nodoDRedLocal = nodosDRed.getNodoDRedLocal(CODIGONODO);
            TuplaDNodoLocal tuplaDNodoLocal = nodoDRedLocal.getTuplaDNodo(CODIGPROCE, codigoDProceso);
            instancia = new ProcesoDSesionImpl(tuplaDNodoLocal);
            cacheDSistemaLocal.almacenarInstancia(keyCache, instancia);
        }
        return instancia;
    }

    @Override
    public String getCodigoDProceso() {
        return getValorString(CODIGPROCE);
    }

    @Override
    public String getDescripcionDProceso() {
        return getValorString(DESCRPROCE);
    }

    @Override
    public String getCodigoDQualifer() {
        return getValorString(CDQUALIFER);
    }

    @Override
    public String getPaginaDProceso() {
        return getValorString(PAGEDPROCE);
    }

}
