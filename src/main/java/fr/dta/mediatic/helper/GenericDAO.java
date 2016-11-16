package fr.dta.mediatic.helper;

import javax.persistence.EntityManager;

public class GenericDAO<T> {

    private Class<T> klass;

    public GenericDAO(Class<T> klass) {
        this.klass = klass;
    }

    public T find(Long id) {
        EntityManager entityManager = DataBaseHelper.createEntityManager();
        T t = entityManager.find(klass, id);
        entityManager.close();
        return t;
    }

    public T persist(T t) {
        EntityManager entityManager = DataBaseHelper.createEntityManager();
        try {
        	DataBaseHelper.beginTx(entityManager);
            entityManager.persist(t);
            DataBaseHelper.commitTxAndClose(entityManager);
            return t;
        } catch (Exception e) {
        	DataBaseHelper.rollbackTxAndClose(entityManager);
            throw new RuntimeException(e);
        }
    }

    public T merge(T t) {
        EntityManager entityManager = DataBaseHelper.createEntityManager();
        try {
            DataBaseHelper.beginTx(entityManager);
            entityManager.merge(t);
            DataBaseHelper.commitTxAndClose(entityManager);
            return t;
        } catch (Exception e) {
            DataBaseHelper.rollbackTxAndClose(entityManager);
            throw new RuntimeException(e);
        }
    }

    public void remove(Long id) {
        EntityManager entityManager = DataBaseHelper.createEntityManager();
        try {
            DataBaseHelper.beginTx(entityManager);
            entityManager.remove(entityManager.find(klass, id));
            DataBaseHelper.commitTxAndClose(entityManager);
        } catch (Exception e) {
            DataBaseHelper.rollbackTxAndClose(entityManager);
            throw new RuntimeException(e);
        }
    }
}
