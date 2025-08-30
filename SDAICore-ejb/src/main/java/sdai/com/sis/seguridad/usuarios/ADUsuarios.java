package sdai.com.sis.seguridad.usuarios;

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
import sdai.com.sis.utilidades.Fecha;

/**
 * @date 29/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Stateless
public class ADUsuarios {

    @Inject
    private PoolDConexionesLocal poolDConexionesLocal;
    @Inject
    private GlobalCaches globalCaches;

    public Usuario getUsuario(String codigoDUsuario) {
        CacheDSistemaLocal cacheDSistemaLocal = this.globalCaches.getCacheDSistemaLocal(KCachesDSistema.CachesDSistema.CACHESEGUR);
        KeyCache keyCache = KeyCache.getInstancia(Usuario.class, codigoDUsuario);
        Usuario usuario = (Usuario) cacheDSistemaLocal.recuperarInstancia(keyCache);
        if (usuario == null) {
            try {
                EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
                TypedQuery<Usuario> query = entityManager.createNamedQuery(KUsuarios.NamedQueries.SUSUAR0000, Usuario.class);
                query.setParameter(KUsuarios.AtributosDEntidad.CODIGUSUAR, codigoDUsuario);
                usuario = query.getSingleResult();
                cacheDSistemaLocal.almacenarInstancia(keyCache, usuario);
            } catch (PersistenceException ex) {

            }
        }
        return usuario;
    }

    public Usuario mergeUsuario(Usuario usuario) {
        EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
        entityManager.merge(usuario);
        return usuario;
    }

    public SecretoDUsuario persistSecretoDUsuario(Usuario usuario, String passwordDUsuario) {
        SecretoDUsuario secretoDUsuario = new SecretoDUsuario();
        secretoDUsuario.setPasswordDUsuario(passwordDUsuario);
        Fecha fechaDSistema = Fecha.getFechaDSistema();
        fechaDSistema.addMeses(Long.valueOf(3));
        secretoDUsuario.setFechaDCaducidad(fechaDSistema.getLocalDate());
        secretoDUsuario.setUsuario(usuario);
        EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
        entityManager.persist(secretoDUsuario);
        return secretoDUsuario;
    }

    public AtributoDUsuario persistAtributoDUsuario(Usuario usuario, String nombreDAtributo, String valorDAtributo) {
        AtributoDUsuario atributoDUsuario = new AtributoDUsuario();
        atributoDUsuario.setNombreDAtributo(nombreDAtributo);
        atributoDUsuario.setValorDAtributo(valorDAtributo);
        atributoDUsuario.setUsuario(usuario);
        EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
        entityManager.persist(atributoDUsuario);
        return atributoDUsuario;
    }

    public AtributoDUsuario mergeAtributoDUsuario(AtributoDUsuario atributoDUsuario) {
        EntityManager entityManager = this.poolDConexionesLocal.getConexionCFG();
        entityManager.merge(atributoDUsuario);
        return atributoDUsuario;
    }

}
