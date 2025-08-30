package sdai.com.sis.rednodal;

import jakarta.enterprise.context.Dependent;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import sdai.com.sis.cachesdsistema.CacheDSistemaLocal;
import sdai.com.sis.cachesdsistema.GlobalCaches;
import sdai.com.sis.cachesdsistema.KCachesDSistema;
import sdai.com.sis.cachesdsistema.KeyCache;
import sdai.com.sis.conexiones.PoolDConexionesLocal;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.rednodal.nodos.KNodos;
import sdai.com.sis.rednodal.nodos.Nodo;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Dependent
public class NodosDRed implements NodosDRedLocal {

    @Inject
    private PoolDConexionesLocal poolDConexionesLocal;
    @Inject
    private GlobalCaches globalCaches;

    @Override
    public NodoDRedLocal getNodoDRedLocal(String codigoDNodo) throws ErrorGeneral {
        KeyCache keyCache = KeyCache.getInstancia(NodoDRed.class, codigoDNodo);
        CacheDSistemaLocal cacheDSistemaLocal = this.globalCaches.getCacheDSistemaLocal(KCachesDSistema.CachesDSistema.CACHEREDNO);
        NodoDRed nodoDRed = (NodoDRed) cacheDSistemaLocal.recuperarInstancia(keyCache);
        if (nodoDRed == null) {
            Nodo nodo = getNodo(codigoDNodo);
            nodoDRed = new NodoDRed(nodo);
            cacheDSistemaLocal.almacenarInstancia(keyCache, nodoDRed);
        }
        return nodoDRed;
    }

    private Nodo getNodo(String codigoDNodo) throws ErrorGeneral {
        KeyCache keyCache = KeyCache.getInstancia(Nodo.class, codigoDNodo);
        CacheDSistemaLocal cacheDSistemaLocal = this.globalCaches.getCacheDSistemaLocal(KCachesDSistema.CachesDSistema.CACHEREDNO);
        Nodo nodo = (Nodo) cacheDSistemaLocal.recuperarInstancia(keyCache);
        if (nodo == null) {
            try {
                EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
                TypedQuery<Nodo> query = entityManager.createNamedQuery(KNodos.NamedQueries.SNODOS0000, Nodo.class);
                query.setParameter(KNodos.AtributosDEntidad.CODIGONODO, codigoDNodo);
                nodo = query.getSingleResult();
                cacheDSistemaLocal.almacenarInstancia(keyCache, nodo);
            } catch (PersistenceException ex) {
                ErrorGeneral errorGeneral = new ErrorGeneral(FacesMessage.SEVERITY_ERROR, "Nodos de red", "No se ha encontrado nodo para el código ".concat(codigoDNodo));
                throw errorGeneral;
            }
        }
        return nodo;
    }

}
