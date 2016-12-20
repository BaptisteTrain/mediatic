package fr.dta.mediatic.user.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.dta.mediatic.helper.DataBaseHelper;
import fr.dta.mediatic.helper.GenericDAO;
import fr.dta.mediatic.user.model.User;

public class UserDAO extends GenericDAO<User> {

	private static UserDAO dao;

	public UserDAO() {
		super(User.class);
	}

	public static UserDAO instance() {
		if (dao == null) {
			dao = new UserDAO();
		}

		return dao;
	}
	
	 /**
     * Select all the users
     * 
     * @return List<User>
     */
    public List<User> selectAllUsers() {
    	
		EntityManager em = DataBaseHelper.createEntityManager();
		DataBaseHelper.beginTx(em);
		TypedQuery<User> query = em.createQuery("SELECT u "
							+ "FROM User u", User.class);
		List<User> listeReturn = query.getResultList();
		em.close();
		
		return listeReturn;
    }
    
    /**
     * Find user by complete id
     * 
     * @param id
     * @return List<User>
     */
    public User findUsersById(String id) {

		EntityManager em = DataBaseHelper.createEntityManager();
	
		DataBaseHelper.beginTx(em);
		TypedQuery<User> query = em.createQuery("SELECT u " 
							+ "FROM User u " 
							+ "WHERE u.identifier = :id", User.class);
	
		query.setParameter("id",  id);
		User singleReturn = query.getSingleResult();
		em.close();
	
		return singleReturn;
    }    
   
    /**
     * Find user by partial id
     * 
     * @param id
     * @return List<User>
     */
    public List<User> findUsersByIdPartial(String id) {

		EntityManager em = DataBaseHelper.createEntityManager();
	
		DataBaseHelper.beginTx(em);
		TypedQuery<User> query = em.createQuery("SELECT u " 
							+ "FROM User u " 
							+ "WHERE u.identifier LIKE :id", User.class);
	
		query.setParameter("id",  id + "%");

		List<User> listeReturn = query.getResultList();
		em.close();

		return listeReturn;
    }

    /**
     * Find members by name
     * 
     * @param lastname
     * @param firstname
     * @return List<User>
     */
    public List<User> findUserByName(String lastname, String firstname) {

		EntityManager em = DataBaseHelper.createEntityManager();
	
		DataBaseHelper.beginTx(em);
		TypedQuery<User> query = em.createQuery("SELECT u " 
							+ "FROM User u " 
							+ "WHERE upper(u.person.lastname) LIKE :lastname " 
							+ "AND upper(u.person.firstname) LIKE :firstname", User.class);

		query.setParameter("lastname", "%" + lastname.toUpperCase() + "%");
		query.setParameter("firstname", "%" + firstname.toUpperCase() + "%");
		
		List<User> listeReturn = query.getResultList();
		em.close();

		return listeReturn;
    }

    /**
     * Find the members by id or lastname or firstname
     * 
     * @param id
     * @param lastname
     * @param firstname
     * @return List<User>
     */
    public List<User> findUserByIdOrNames(String id, String lastname, String firstname) {
    	
		EntityManager em = DataBaseHelper.createEntityManager();
		
		DataBaseHelper.beginTx(em);
		TypedQuery<User> query = em.createQuery("SELECT u " 
							+ "FROM User u " 
							+ "WHERE u.person.lastname LIKE :lastname " 
							+ "OR u.person.firstname LIKE :firstname " 
							+ "OR u.identifier LIKE :id", User.class);

		query.setParameter("id", id + "%");
		query.setParameter("lastname", "%" + lastname + "%");
		query.setParameter("firstname", "%" + firstname + "%");
		
		List<User> listeReturn = query.getResultList();
		em.close();
		
		return listeReturn;
	}
}
