package fr.dta.mediatic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dta.mediatic.model.Subscription;
import fr.dta.mediatic.repository.SubscriptionRepository;

@Service
public class SubscriptionService {
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	public List<Subscription> getAll() {
		
		return subscriptionRepository.getAll(); 
	}

	/**
	 * Ask for Subscription by Id
	 * 
	 * @param id
	 * @return
	 */
	public Subscription getById(long id) {
		
		return subscriptionRepository.getById(id);
	}
	
	/**
	 * Update a Subscription
	 * 
	 * @param entity
	 */
	public void setSubscription(Subscription entity) {
		
		subscriptionRepository.update(entity);
	}
	
	/**
	 * Create a Subscription
	 * 
	 * @param entity
	 */
	public void create (Subscription entity) {
		
		subscriptionRepository.add(entity);
	}
	
	/**
	 * Delete a subscription
	 * 
	 * @param entity
	 */
	public void delete (Subscription entity) {
		
		subscriptionRepository.delete(entity);
	}
}
