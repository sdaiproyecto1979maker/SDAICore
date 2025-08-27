package sdai.com.sis.cachesdsistema;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@ApplicationScoped
public class GlobalCaches {

    @Inject
    @Any
    private Instance<CacheDSistemaLocal> instancias;

    public CacheDSistemaLocal getCacheDSistemaLocal(String codigoDCache) {
        CachesDSistemaLiteral cachesDSistemaLiteral = CachesDSistemaLiteral.of(codigoDCache);
        Instance<CacheDSistemaLocal> instancia = this.instancias.select(cachesDSistemaLiteral);
        CacheDSistemaLocal cacheDSistemaLocal = instancia.get();
        return cacheDSistemaLocal;
    }

}
