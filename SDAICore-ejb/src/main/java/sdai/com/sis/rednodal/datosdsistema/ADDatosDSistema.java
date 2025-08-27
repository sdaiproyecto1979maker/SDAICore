package sdai.com.sis.rednodal.datosdsistema;

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
public class ADDatosDSistema {

    @Inject
    private PoolDConexionesLocal poolDConexionesLocal;
    @Inject
    private GlobalCaches globalCaches;

    public DatoDSistema getDatoDSistema(String codigoDDato) {
        try {
            EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
            TypedQuery<DatoDSistema> query = entityManager.createNamedQuery(KDatosDSistema.NamedQueries.SDASIS0000, DatoDSistema.class);
            query.setParameter(KDatosDSistema.AtributosDEntidad.CODIGODATO, codigoDDato);
            return query.getSingleResult();
        } catch (PersistenceException ex) {
            return null;
        }
    }

    public DatoDSistema[] getDatosDSistema() {
        EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
        TypedQuery<DatoDSistema> query = entityManager.createNamedQuery(KDatosDSistema.NamedQueries.SDASIS0001, DatoDSistema.class);
        return query.getResultList().toArray(DatoDSistema[]::new);
    }

    public DatoDSistema[] getDatosDSistema(String entornoAplicacion) {
        EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
        TypedQuery<DatoDSistema> query = entityManager.createNamedQuery(KDatosDSistema.NamedQueries.SDASIS0002, DatoDSistema.class);
        query.setParameter(KDatosDSistema.AtributosDEntidad.ENTORNOAPP, entornoAplicacion);
        return query.getResultList().toArray(DatoDSistema[]::new);
    }

    public void persistDatoDSistemaVersion(XMLCFGLocal xmlCFGLocal) {
        XMLDatoDSistema xmlDatoDSistema = (XMLDatoDSistema) xmlCFGLocal;
        String codigoDDato = xmlDatoDSistema.getCodigoDDato();
        DatoDSistema datoDSistema = getDatoDSistema(codigoDDato);
        if (datoDSistema == null) {
            persistDatoDSistema(xmlDatoDSistema);
        }
    }

    public DatoDSistema persistDatoDSistema(XMLDatoDSistema xmlDatoDSistema) {
        DatoDSistema datoDSistema = new DatoDSistema();
        datoDSistema.setCodigoDDato(xmlDatoDSistema.getCodigoDDato());
        datoDSistema.setDescripcionDDato(xmlDatoDSistema.getDescripcionDDato());
        EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
        entityManager.persist(datoDSistema);
        return datoDSistema;
    }

    public DatoDSistema mergeDatoDSistema(DatoDSistema datoDSistema) {
        KeyCache keyCache = KeyCache.getInstancia(DatoDSistema.class, datoDSistema.getCodigoDDato());
        CacheDSistemaLocal cacheDSistemaLocal = this.globalCaches.getCacheDSistemaLocal(KCachesDSistema.CachesDSistema.CACHEREDNO);
        cacheDSistemaLocal.eliminarInstancia(keyCache);
        EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
        entityManager.merge(datoDSistema);
        return datoDSistema;
    }

}
