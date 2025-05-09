package sdai.com.sis.cachesdsistema;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @date 08/05/2025
 * @author Sergio_M
 * @since VERSIONDCOREENCURSO
 */
@ApplicationScoped
public class GlobalCaches {

    private final ConcurrentMap<String, IContenedorDCache> almacenDCaches;

    public GlobalCaches() {
        this.almacenDCaches = new ConcurrentHashMap<>();
    }

    public void addContenedorDCache(IContenedorDCache contenedorDCache) {

    }

}
