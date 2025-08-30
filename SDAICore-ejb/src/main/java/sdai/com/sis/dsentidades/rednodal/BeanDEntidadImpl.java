package sdai.com.sis.dsentidades.rednodal;

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
public class BeanDEntidadImpl extends ElementoDRed implements BeanDEntidadImplLocal {

    private static final String CODIGONODO = "BEANENTITY";

    private static final String CODIGOBEAN = "CODIGOBEAN";
    private static final String CODIENTITY = "CODIENTITY";

    private BeanDEntidadImpl(TuplaDNodoLocal tuplaDNodoLocal) {
        super(tuplaDNodoLocal);
    }

    public static BeanDEntidadImpl[] getInstancias(String codigoDEntidad) throws ErrorGeneral {
        KeyCache keyCache = KeyCache.getInstancia(BeanDEntidadImpl.class, codigoDEntidad);
        GlobalCaches globalCaches = CDI.current().select(GlobalCaches.class).get();
        CacheDSistemaLocal cacheDSistemaLocal = globalCaches.getCacheDSistemaLocal(KCachesDSistema.CachesDSistema.CACHEREDNO);
        BeanDEntidadImpl[] instancias = (BeanDEntidadImpl[]) cacheDSistemaLocal.recuperarInstancia(keyCache);
        if (instancias == null) {
            NodosDRed nodosDRed = CDI.current().select(NodosDRed.class).get();
            NodoDRedLocal nodoDRedLocal = nodosDRed.getNodoDRedLocal(CODIGONODO);
            TuplaDNodoLocal[] tuplasDNodoLocal = nodoDRedLocal.getTuplasDNodo(CODIENTITY, codigoDEntidad);
            if (tuplasDNodoLocal != null && tuplasDNodoLocal.length > 0) {
                List<BeanDEntidadImpl> lista = new ArrayList<>();
                for (TuplaDNodoLocal tuplaDNodoLocal : tuplasDNodoLocal) {
                    BeanDEntidadImpl instancia = new BeanDEntidadImpl(tuplaDNodoLocal);
                    lista.add(instancia);
                }
                instancias = lista.toArray(BeanDEntidadImpl[]::new);
                cacheDSistemaLocal.almacenarInstancia(keyCache, instancias);
            }
        }
        return instancias;
    }

    @Override
    public String getCodigoDBean() {
        return getValorString(CODIGOBEAN);
    }

    @Override
    public String getCodigoDEntidad() {
        return getValorString(CODIENTITY);
    }

}
