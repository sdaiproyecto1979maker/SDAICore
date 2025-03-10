package sdai.com.sis.conexiones;

import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import sdai.com.sis.accesoadatos.IEntidad;
import sdai.com.sis.accesoadatos.IdQuery;
import sdai.com.sis.utilidades.Hora;

/**
 * @date 09/03/2025
 * @since 0.1.0.0-SNAPSHOT
 * @author Sergio_M
 */
public final class Conexion {

	private final EntityManager entityManager;
	private final Hora horaDCreacion;

	Conexion(BaseDDatos baseDDatos) {
		this.entityManager = baseDDatos.createEntityManager();
		this.horaDCreacion = Hora.getHoraDSistema();
	}

	Boolean isConexionCaducda() {
		Hora horaDSistema = Hora.getHoraDSistema();
		Integer minutosTranscurridos = this.horaDCreacion.getMinutosDDiferencia(horaDSistema);
		return minutosTranscurridos >= Integer.valueOf(30);
	}

	List<IEntidad> ejecutarConsulta(IdQuery idQuery) {
		String namedQuery = idQuery.getNamedQuery();
		TypedQuery<IEntidad> query = this.entityManager.createNamedQuery(namedQuery, IEntidad.class);
		Map<String, Object> parametrosDQuery = idQuery.getParametrosDQuery();
		for (Map.Entry<String, Object> mapa : parametrosDQuery.entrySet()) {
			String key = mapa.getKey();
			Object value = mapa.getValue();
			query.setParameter(key, value);
		}
		List<IEntidad> lista = query.getResultList();
		return lista;
	}

	void persistEntidad(IEntidad entidad) {
		EntityTransaction entityTransaction = this.entityManager.getTransaction();
		if (!entityTransaction.isActive())
			entityTransaction.begin();
		this.entityManager.persist(entidad);
	}

	void mergeEntidad(IEntidad entidad) {
		EntityTransaction entityTransaction = this.entityManager.getTransaction();
		if (!entityTransaction.isActive())
			entityTransaction.begin();
		this.entityManager.merge(entidad);
	}

	void removeEntidad(IEntidad entidad) {
		EntityTransaction entityTransaction = this.entityManager.getTransaction();
		if (!entityTransaction.isActive())
			entityTransaction.begin();
		this.entityManager.remove(entidad);
	}

	void doCommit() {
		EntityTransaction entityTransaction = this.entityManager.getTransaction();
		if (entityTransaction.isActive())
			entityTransaction.commit();
	}

	void doRollback() {
		EntityTransaction entityTransaction = this.entityManager.getTransaction();
		if (entityTransaction.isActive())
			entityTransaction.rollback();
	}

}
