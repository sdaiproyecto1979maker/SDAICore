package sdai.com.sis.rednodal.nodos;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import sdai.com.sis.cachesdsistema.CacheDSistemaLocal;
import sdai.com.sis.cachesdsistema.GlobalCaches;
import sdai.com.sis.cachesdsistema.KCachesDSistema;
import sdai.com.sis.cachesdsistema.KeyCache;
import sdai.com.sis.conexiones.PoolDConexionesLocal;
import sdai.com.sis.rednodal.XMLCFGLocal;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Stateless
public class ADNodos {

    @Inject
    private PoolDConexionesLocal poolDConexionesLocal;
    @Inject
    private GlobalCaches globalCaches;

    public Nodo getNodo(String codigoDNodo) {
        try {
            EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
            TypedQuery<Nodo> query = entityManager.createNamedQuery(KNodos.NamedQueries.SNODOS0000, Nodo.class);
            query.setParameter(KNodos.AtributosDEntidad.CODIGONODO, codigoDNodo);
            return query.getSingleResult();
        } catch (PersistenceException ex) {
            return null;
        }
    }

    public Nodo[] getNodos() {
        EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
        TypedQuery<Nodo> query = entityManager.createNamedQuery(KNodos.NamedQueries.SNODOS0001, Nodo.class);
        return query.getResultList().toArray(Nodo[]::new);
    }

    public Nodo[] getNodos(String entornoAplicacion) {
        EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
        TypedQuery<Nodo> query = entityManager.createNamedQuery(KNodos.NamedQueries.SNODOS0002, Nodo.class);
        query.setParameter(KNodos.AtributosDEntidad.ENTORNOAPP, entornoAplicacion);
        return query.getResultList().toArray(Nodo[]::new);
    }

    public void persistNodoVersion(XMLCFGLocal xmlCFGLocal) {
        XMLNodo xmlNodo = (XMLNodo) xmlCFGLocal;
        String codigoDNodo = xmlNodo.getCodigoDNodo();
        Nodo nodo = getNodo(codigoDNodo);
        if (nodo == null) {
            persistNodo(xmlNodo);
        }
    }

    public Nodo persistNodo(XMLNodo xmlNodo) {
        Nodo nodo = new Nodo();
        nodo.setCodigoDNodo(xmlNodo.getCodigoDNodo());
        nodo.setDescripcionDNodo(xmlNodo.getDescripcionDNodo());
        EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
        entityManager.persist(nodo);
        return nodo;
    }

    public Nodo mergeNodo(Nodo nodo) {
        KeyCache keyCache = KeyCache.getInstancia(Nodo.class, nodo.getCodigoDNodo());
        CacheDSistemaLocal cacheDSistemaLocal = this.globalCaches.getCacheDSistemaLocal(KCachesDSistema.CachesDSistema.CACHEREDNO);
        cacheDSistemaLocal.eliminarInstancia(keyCache);
        EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
        entityManager.merge(nodo);
        return nodo;
    }

}
