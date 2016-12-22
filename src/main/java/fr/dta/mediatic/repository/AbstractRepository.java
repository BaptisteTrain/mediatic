package fr.dta.mediatic.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.dta.mediatic.model.AbstractEntity;

@Repository
@Transactional
public abstract class AbstractRepository<T extends AbstractEntity> {

    private Class<T> entityClass;

    protected abstract Class<T> getEntityClass();

    @PostConstruct
    public void init() {
	entityClass = getEntityClass();
    }

    @PersistenceContext
    protected EntityManager em;

    public T getById(long id) {
	return em.find(entityClass, id);
    }

    @Transactional
    public void add(T entity) {
	em.persist(entity);
    }

    @Transactional
    public void delete(T entity) {
	entity = em.merge(entity);
	em.remove(entity);
    }

    @Transactional
    public void update(T entity) {
	em.merge(entity);
    }

    public List<T> getAll() {
	TypedQuery<T> query = em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass);
	List<T> listeReturn = query.getResultList();
	return listeReturn;
    }

}
