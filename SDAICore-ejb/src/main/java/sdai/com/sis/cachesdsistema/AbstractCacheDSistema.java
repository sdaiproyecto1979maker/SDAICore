package sdai.com.sis.cachesdsistema;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AbstractCacheDSistema implements CacheDSistemaLocal {

    private final ConcurrentMap<String, Object> almacenDInstancias;

    public AbstractCacheDSistema() {
        this.almacenDInstancias = new ConcurrentHashMap<>();
    }

    @Override
    public Object recuperarInstancia(KeyCache keyCache) {
        String key = keyCache.getKeyCache();
        if (!this.almacenDInstancias.containsKey(key)) {
            return null;
        }
        return this.almacenDInstancias.get(key);
    }

    @Override
    public void almacenarInstancia(KeyCache keyCache, Object instancia) {
        String key = keyCache.getKeyCache();
        if (instancia != null) {
            this.almacenDInstancias.put(key, instancia);
        }
    }

    @Override
    public void eliminarInstancia(KeyCache keyCache) {
        String key = keyCache.getKeyCache();
        this.almacenDInstancias.remove(key);
    }

}
