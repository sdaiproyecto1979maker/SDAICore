package sdai.com.sis.beans.rednodal;

import jakarta.enterprise.inject.spi.CDI;
import sdai.com.sis.beans.rednoal.BeanDVistaImplLocal;
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
public final class BeanDVistaImpl extends ElementoDRed implements BeanDVistaImplLocal {

    private static final String CODIGONODO = "BEANSVISTA";

    private static final String CODIGOBEAN = "CODIGOBEAN";
    private static final String DESCRDBEAN = "DESCRDBEAN";
    private static final String CODIGNAMED = "CODIGNAMED";

    private BeanDVistaImpl(TuplaDNodoLocal tuplaDNodoLocal) {
        super(tuplaDNodoLocal);
    }

    public static BeanDVistaImpl getInstancia(String codigoDBean) throws ErrorGeneral {
        KeyCache keyCache = KeyCache.getInstancia(BeanDVistaImpl.class, codigoDBean);
        GlobalCaches globalCaches = CDI.current().select(GlobalCaches.class).get();
        CacheDSistemaLocal cacheDSistemaLocal = globalCaches.getCacheDSistemaLocal(KCachesDSistema.CachesDSistema.CACHEREDNO);
        BeanDVistaImpl instancia = (BeanDVistaImpl) cacheDSistemaLocal.recuperarInstancia(keyCache);
        if (instancia == null) {
            NodosDRed nodosDRed = CDI.current().select(NodosDRed.class).get();
            NodoDRedLocal nodoDRedLocal = nodosDRed.getNodoDRedLocal(CODIGONODO);
            TuplaDNodoLocal tuplaDNodoLocal = nodoDRedLocal.getTuplaDNodo(CODIGOBEAN, codigoDBean);
            instancia = new BeanDVistaImpl(tuplaDNodoLocal);
            cacheDSistemaLocal.almacenarInstancia(keyCache, instancia);
        }
        return instancia;
    }

    @Override
    public String getCodigoDBean() {
        return getValorString(CODIGOBEAN);
    }

    @Override
    public String getDescripcionDBean() {
        return getValorString(DESCRDBEAN);
    }

    @Override
    public String getCodigoDNamed() {
        return getValorString(CODIGNAMED);
    }

}
