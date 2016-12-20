package fr.dta.mediatic.helper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;


public class DataBaseHelper {


    private static EntityManager entityManager2;

    public static EntityManager getEntityManager() {
	return entityManager2;
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