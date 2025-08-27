package sdai.com.sis.rednodal;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import sdai.com.sis.cachesdsistema.CacheDSistemaLocal;
import sdai.com.sis.cachesdsistema.GlobalCaches;
import sdai.com.sis.cachesdsistema.KCachesDSistema;
import sdai.com.sis.cachesdsistema.KeyCache;
import sdai.com.sis.conexiones.PoolDConexionesLocal;
import sdai.com.sis.rednodal.nodos.KNodos;
import sdai.com.sis.rednodal.nodos.Nodo;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Stateless
public class NodosDRed implements NodosDRedLocal {

    @Inject
    private PoolDConexionesLocal poolDConexionesLocal;
    @Inject
    private GlobalCaches globalCaches;

    @Override
    public NodoDRedLocal getNodoDRedLocal(String codigoDNodo) {
        KeyCache keyCache = KeyCache.getInstancia(Nodo.class, codigoDNodo);
        CacheDSistemaLocal cacheDSistemaLocal = this.globalCaches.getCacheDSistemaLocal(KCachesDSistema.CachesDSistema.CACHEREDNO);
        Nodo nodo = (Nodo) cacheDSistemaLocal.recuperarInstancia(keyCache);
        if (nodo == null) {
            EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
            TypedQuery<Nodo> query = entityManager.createNamedQuery(KNodos.NamedQueries.SNODOS0000, Nodo.class);
            query.setParameter(KNodos.AtributosDEntidad.CODIGONODO, codigoDNodo);
            nodo = query.getSingleResult();
        }
        return new NodoDRed(nodo);
    }

}
