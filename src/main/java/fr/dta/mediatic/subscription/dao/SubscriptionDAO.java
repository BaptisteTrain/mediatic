package fr.dta.mediatic.subscription.dao;

import fr.dta.mediatic.helper.GenericDAO;
import fr.dta.mediatic.subscription.model.Subscription;

public class SubscriptionDAO extends GenericDAO<Subscription> {
	
	private static SubscriptionDAO dao;
	
	public SubscriptionDAO() {
		super(Subscription.class);
	}
		
	public static SubscriptionDAO instance() {
		if (dao == null) {
			dao = new SubscriptionDAO();
		}
		return dao;
	}
	

}
