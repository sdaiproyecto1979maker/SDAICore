package sdai.com.sis.cachesdsistema.accesoadatos;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import sdai.com.sis.conexiones.MultiPoolDConexionesLocal;

/**
 * @date 08/05/2025
 * @author Sergio_M
 * @since VERSIONDCOREENCURSO
 */
@RequestScoped
public class DAOContenedoresDCache {

    @Inject
    private MultiPoolDConexionesLocal multiPoolDConexionesLocal;

    public DAOContenedoresDCache() {
    }

    public ContenedorDCache getContenedorDCache(String codigoDContenedor) {
        EntityManager entityManager = multiPoolDConexionesLocal.getConexionDConfiguracion();
        ContenedorDCache instancia = entityManager.find(ContenedorDCache.class, codigoDContenedor);
        return instancia;
    }

}
