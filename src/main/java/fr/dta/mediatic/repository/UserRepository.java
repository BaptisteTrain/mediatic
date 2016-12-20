package fr.dta.mediatic.repository;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fr.dta.mediatic.model.User;

@Repository
@Transactional
public class UserRepository extends AbstractRepository<User> {

    @Override
    protected Class<User> getEntityClass() {
	return User.class;
    }

}
