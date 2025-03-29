package sdai.com.sis.conexiones;

import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import sdai.com.sis.accesoadatos.IEntidad;
import sdai.com.sis.utilidades.Hora;

/**
 * @date 12/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
public final class Conexion {

	private final EntityManager entityManager;
	private final Hora horaDCreacion;

	Conexion(BaseDDatos baseDDatos) {
		this.entityManager = baseDDatos.createEntityManager();
		this.horaDCreacion = Hora.getHoraDSistema();
	}

	Boolean isConexionCaducada() {
		Hora horaDSistema = Hora.getHoraDSistema();
		Integer minutosTranscurridos = this.horaDCreacion.getMinutosTranscurridos(horaDSistema);
		return minutosTranscurridos > Integer.valueOf(30);
	}

	List<IEntidad> ejecutarConsulta(IdQuery idQuery) {
		String namedQuery = idQuery.getNamedQuery();
		Map<String, Object> parametrosDQuery = idQuery.getParametrosDQuery();
		TypedQuery<IEntidad> query = this.entityManager.createNamedQuery(namedQuery, IEntidad.class);
		for (Map.Entry<String, Object> mapa : parametrosDQuery.entrySet()) {
			String key = mapa.getKey();
			Object value = mapa.getValue();
			query.setParameter(key, value);
		}
		List<IEntidad> resultados = query.getResultList();
		return resultados;
	}

	void ejecutarPersist(IEntidad entidad) {
		EntityTransaction entityTransaction = this.entityManager.getTransaction();
		if (!entityTransaction.isActive())
			entityTransaction.begin();
		this.entityManager.persist(entidad);
	}

	void ejecutarMerge(IEntidad entidad) {
		EntityTransaction entityTransaction = this.entityManager.getTransaction();
		if (!entityTransaction.isActive())
			entityTransaction.begin();
		this.entityManager.merge(entidad);
	}

	void ejecutarRemove(IEntidad entidad) {
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

	void liberarConexion() {
		this.entityManager.clear();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Hora getHoraDCreacion() {
		return horaDCreacion;
	}

}
