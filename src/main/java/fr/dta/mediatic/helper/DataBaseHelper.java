package fr.dta.mediatic.helper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DataBaseHelper {

	@PersistenceContext
	private static EntityManager entityManager;

	public static EntityManager getEntityManager() {
		return entityManager;
	}

	public static void commitTxAndClose(EntityManager entityManager) {
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public static void rollbackTxAndClose(EntityManager entityManager) {
		entityManager.getTransaction().rollback();
		entityManager.close();
	}

	public static void beginTx(EntityManager entityManager) {
		entityManager.getTransaction().begin();
	}
}