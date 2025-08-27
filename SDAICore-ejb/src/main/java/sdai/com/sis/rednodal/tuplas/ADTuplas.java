package sdai.com.sis.rednodal.tuplas;

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
import sdai.com.sis.rednodal.nodos.ADNodos;
import sdai.com.sis.rednodal.nodos.Nodo;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Stateless
public class ADTuplas {

    @Inject
    private PoolDConexionesLocal poolDConexionesLocal;
    @Inject
    private ADNodos adNodos;
    @Inject
    private GlobalCaches globalCaches;

    public Tupla getTupla(String codigoDTupla) {
        try {
            EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
            TypedQuery<Tupla> query = entityManager.createNamedQuery(KTuplas.NamedQueries.STUPLA0000, Tupla.class);
            query.setParameter(KTuplas.AtributosDEntidad.CODIGTUPLA, codigoDTupla);
            return query.getSingleResult();
        } catch (PersistenceException ex) {
            return null;
        }
    }

    public Tupla[] getTuplas() {
        EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
        TypedQuery<Tupla> query = entityManager.createNamedQuery(KTuplas.NamedQueries.STUPLA0001, Tupla.class);
        return query.getResultList().toArray(Tupla[]::new);
    }

    public void persistTuplaVersion(XMLCFGLocal xmlCFGLocal) {
        XMLTupla xmlTupla = (XMLTupla) xmlCFGLocal;
        String codigoDTupla = xmlTupla.getCodigoDTupla();
        String codigoDNodo = xmlTupla.getCodigoDNodo();
        Tupla tupla = getTupla(codigoDTupla);
        if (tupla == null) {
            tupla = persistTupla(xmlTupla);
            Nodo nodo = this.adNodos.getNodo(codigoDNodo);
            nodo.getTuplas().add(tupla);
            this.adNodos.mergeNodo(nodo);
        }
    }

    public Tupla persistTupla(XMLTupla xmlTupla) {
        Tupla tupla = new Tupla();
        tupla.setCodigoDTupla(xmlTupla.getCodigoDTupla());
        tupla.setDescripcionDTupla(xmlTupla.getDescripcionDTupla());
        String codigoDNodo = xmlTupla.getCodigoDNodo();
        Nodo nodo = this.adNodos.getNodo(codigoDNodo);
        tupla.setNodo(nodo);
        EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
        entityManager.persist(tupla);
        return tupla;
    }

    public Tupla mergeTupla(Tupla tupla) {
        KeyCache keyCache = KeyCache.getInstancia(Tupla.class, tupla.getCodigoDTupla());
        CacheDSistemaLocal cacheDSistemaLocal = this.globalCaches.getCacheDSistemaLocal(KCachesDSistema.CachesDSistema.CACHEREDNO);
        cacheDSistemaLocal.eliminarInstancia(keyCache);
        EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
        entityManager.merge(tupla);
        return tupla;
    }

}
