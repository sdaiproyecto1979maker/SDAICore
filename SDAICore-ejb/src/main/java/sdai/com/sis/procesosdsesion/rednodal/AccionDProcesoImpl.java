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
 * @date 28/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class AccionDProcesoImpl extends ElementoDRed implements AccionDProcesoImplLocal {

    private static final String CODIGONODO = "ACCIOPROCE";

    private static final String CODIGPROCE = "CODIGPROCE";
    private static final String CODIACCION = "CODIACCION";
    private static final String CDQUALIFER = "CDQUALIFER";
    private static final String PROCDESTIN = "PROCDESTIN";
    private static final String SISTERULES = "SISTERULES";

    private AccionDProcesoImpl(TuplaDNodoLocal tuplaDNodoLocal) {
        super(tuplaDNodoLocal);
    }

    public static AccionDProcesoImpl getInstancia(String codigoDProceso, String codigoDAccion) throws ErrorGeneral {
        KeyCache keyCache = KeyCache.getInstancia(AccionDProcesoImpl.class, codigoDProceso);
        GlobalCaches globalCaches = CDI.current().select(GlobalCaches.class).get();
        CacheDSistemaLocal cacheDSistemaLocal = globalCaches.getCacheDSistemaLocal(KCachesDSistema.CachesDSistema.CACHEREDNO);
        AccionDProcesoImpl instancia = (AccionDProcesoImpl) cacheDSistemaLocal.recuperarInstancia(keyCache);
        if (instancia == null) {
            try {
                NodosDRed nodosDRed = CDI.current().select(NodosDRed.class).get();
                NodoDRedLocal nodoDRedLocal = nodosDRed.getNodoDRedLocal(CODIGONODO);
                TuplaDNodoLocal tuplaDNodoLocal = nodoDRedLocal.getTuplaDNodo(CODIGPROCE, codigoDProceso, CODIACCION, codigoDAccion);
                instancia = new AccionDProcesoImpl(tuplaDNodoLocal);
                cacheDSistemaLocal.almacenarInstancia(keyCache, instancia);
            } catch (ErrorGeneral ex) {
                return null;
            }
        }
        return instancia;
    }

    @Override
    public String getCodigoDAccion() {
        return getValorString(CODIACCION);
    }

    @Override
    public String getCodigoDProceso() {
        return getValorString(CODIGPROCE);
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
