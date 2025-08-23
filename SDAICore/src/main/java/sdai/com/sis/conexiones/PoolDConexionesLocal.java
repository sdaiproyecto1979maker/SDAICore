package sdai.com.sis.conexiones;

import jakarta.ejb.Local;
import jakarta.persistence.EntityManager;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface PoolDConexionesLocal {

    EntityManager getConexionCFG();

}
