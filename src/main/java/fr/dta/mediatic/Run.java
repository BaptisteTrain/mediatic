package fr.dta.mediatic;

import javax.persistence.EntityManager;

import fr.dta.mediatic.helper.DataBaseHelper;

public class Run {

	public static void main(String[] args) {
		EntityManager em = DataBaseHelper.createEntityManager();
		DataBaseHelper.beginTx(em);
		em.close();
	}

}
