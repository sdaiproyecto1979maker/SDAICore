package sdai.com.sis.versiones.accesoadatos;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import sdai.com.sis.conexiones.PoolDConexionesLocal;
import sdai.com.sis.versiones.KVersionado;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Stateless
public class ADVersionado {

    @Inject
    private PoolDConexionesLocal poolDConexionesLocal;

    public Version getVersion(String entornoAplicacion) {
        try {
            EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
            TypedQuery<Version> query = entityManager.createNamedQuery(KVersionado.NamedQueries.SVERSI0000, Version.class);
            query.setParameter(KVersionado.AtributosDEntidad.ENTORNOAPP, entornoAplicacion);
            return query.getSingleResult();
        } catch (PersistenceException ex) {
            return null;
        }
    }

    public Version persistVersion(String numeroDVersion, String entornoAplicacion, byte[] ficheroDVersion) {
        Version version = new Version();
        version.setNumeroDVersion(numeroDVersion);
        version.setEntornoAplicacion(entornoAplicacion);
        version.setFicheroDVersion(ficheroDVersion);
        EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
        entityManager.persist(version);
        return version;
    }

    public Version mergeVersion(Version version) {
        EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
        entityManager.merge(version);
        return version;
    }

}
