package fr.dta.mediatic.repository;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fr.dta.mediatic.model.Subscription;

@Repository
@Transactional
public class SubscriptionRepository extends AbstractRepository<Subscription> {

    @Override
    protected Class<Subscription> getEntityClass() {
	return Subscription.class;
    }

}
