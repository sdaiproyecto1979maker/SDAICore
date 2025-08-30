package sdai.com.sis.cachesdsistema;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Map;
import sdai.com.sis.utilidades.FacesUtil;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@ApplicationScoped
@CachesDSistema(KCachesDSistema.CachesDSistema.CACHESEGUR)
public class CacheDSeguridad extends AbstractCacheDSistema {

    @Override
    public Object recuperarInstancia(KeyCache keyCache) {
        String key = keyCache.getKeyCache();
        Map<String, Object> almacenDSesion = FacesUtil.getAlmacenDSesion();
        if (!almacenDSesion.containsKey(key)) {
            return super.recuperarInstancia(keyCache);
        }
        return almacenDSesion.get(key);
    }

    @Override
    public void almacenarInstancia(KeyCache keyCache, Object instancia) {
        if (instancia != null) {
            super.almacenarInstancia(keyCache, instancia);
            String key = keyCache.getKeyCache();
            Map<String, Object> almacenDSesion = FacesUtil.getAlmacenDSesion();
            almacenDSesion.put(key, instancia);
        }
    }

}
