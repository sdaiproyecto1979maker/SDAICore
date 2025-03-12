package sdai.com.sis.conexiones;

import jakarta.persistence.EntityManager;

/**
 * @date 12/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class Conexion {

	private final EntityManager entityManager;

	Conexion(BaseDDatos baseDDatos) {
		this.entityManager = baseDDatos.createEntityManager();
	}

}
