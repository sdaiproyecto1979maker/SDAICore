package sdai.com.sis.cachesdsistema.contenedores;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.inject.Inject;
import sdai.com.sis.cachesdsistema.CacheDSistema;
import sdai.com.sis.cachesdsistema.GlobalCaches;
import sdai.com.sis.cachesdsistema.ICacheDSistema;
import sdai.com.sis.cachesdsistema.IContenedorDCache;
import sdai.com.sis.cachesdsistema.KeyCache;
import sdai.com.sis.cachesdsistema.accesoadatos.ContenedorDCache;
import sdai.com.sis.cachesdsistema.accesoadatos.DAOContenedoresDCache;

/**
 * @date 08/05/2025
 * @author Sergio_M
 * @since VERSIONDCOREENCURSO
 */
@Singleton
public class CacheDConfiguracion implements IContenedorDCache {

    private static final String CODIGCONTE = "CACHECONFG";

    @Inject
    private GlobalCaches globalCaches;
    @Inject
    private DAOContenedoresDCache dAOContenedoresDCache;

    private ICacheDSistema cacheDSistema;
    private ContenedorDCache contenedorDCache;

    public CacheDConfiguracion() {
    }

    @PostConstruct
    public void init() {
        this.cacheDSistema = CacheDSistema.getInstancia(CacheDConfiguracion.class);
        this.contenedorDCache = dAOContenedoresDCache.getContenedorDCache(CODIGCONTE);
        this.cacheDSistema.setContenedorDCache(this);
        globalCaches.addContenedorDCache(this);
    }

    @Override
    public Object recuperarInstancia(KeyCache keyCache) {
        Object instancia = this.cacheDSistema.recuperarInstancia(keyCache);
        return instancia;
    }

    @Override
    public void almacenarInstancia(KeyCache keyCache, Object instancia) {
        if (instancia != null) {
            this.cacheDSistema.almacenarInstancia(keyCache, instancia);
        }
    }

}
