package fr.dta.mediatic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.dta.mediatic.model.AbstractEntity;
import fr.dta.mediatic.repository.AbstractRepository;

public abstract class AbstractService<T extends AbstractRepository<AbstractEntity>> {

    protected T repo;

    public List<AbstractEntity> getAll() {
	return repo.getAll();
    }

    public AbstractEntity getById(int id) {
	return repo.getById(id);
    }

    public void add(AbstractEntity entity) {
	repo.add(entity);
    }

    public void delete(AbstractEntity entity) {
	repo.delete(entity);
    }

    public void update(AbstractEntity entity) {
	repo.update(entity);
    }

}
