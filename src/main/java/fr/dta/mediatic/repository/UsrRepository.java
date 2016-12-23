package fr.dta.mediatic.repository;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fr.dta.mediatic.model.Usr;

@Repository
@Transactional
public class UsrRepository extends AbstractRepository<Usr> {

    @Override
    protected Class<Usr> getEntityClass() {
	return Usr.class;
    }
    
    /**
     * Select all the users
     * 
     * @return List<User> 
     */
    public List<Usr> selectAllUsers() {
		TypedQuery<Usr> query = em.createQuery("SELECT u "
							+ "FROM User u", Usr.class);
		List<Usr> listeReturn = query.getResultList();
		
		return listeReturn;
    }   
   
    /**
     * Find user by partial id
     * 
     * @param id
     * @return List<User>
     */
    public List<Usr> findUsersByIdPartial(String login) {
		TypedQuery<Usr> query = em.createQuery("SELECT u " 
							+ "FROM User u " 
							+ "WHERE u.login LIKE :login", Usr.class);
	
		query.setParameter("login",  login + "%");

		List<Usr> listeReturn = query.getResultList();

		return listeReturn;
    }

    /**
     * Find members by name
     * 
     * @param lastname
     * @param firstname
     * @return List<User>
     */
    public List<Usr> findUserByName(String lastname, String firstname) {
		TypedQuery<Usr> query = em.createQuery("SELECT u " 
							+ "FROM User u " 
							+ "WHERE upper(u.lastname) LIKE :lastname " 
							+ "AND upper(u.firstname) LIKE :firstname", Usr.class);

		query.setParameter("lastname", "%" + lastname.toUpperCase() + "%");
		query.setParameter("firstname", "%" + firstname.toUpperCase() + "%");
		
		List<Usr> listeReturn = query.getResultList();

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
    public List<Usr> findUserByIdOrNames(String login, String lastname, String firstname) {
		TypedQuery<Usr> query = em.createQuery("SELECT u " 
							+ "FROM User u " 
							+ "WHERE upper(u.lastname) LIKE :lastname " 
							+ "OR upper(u.firstname) LIKE :firstname " 
							+ "OR u.login LIKE :login", Usr.class);

		query.setParameter("login", login + "%");
		query.setParameter("lastname", "%" + lastname + "%");
		query.setParameter("firstname", "%" + firstname + "%");
		
		List<Usr> listeReturn = query.getResultList();
		
		return listeReturn;
	}
    
    public boolean authenticate(String login, String pwd) {
    	TypedQuery<Usr> query = em.createQuery("SELECT u " 
				+ "FROM User u " 
				+ "WHERE login = :login " 
				+ "AND password = :pwd", Usr.class);

		query.setParameter("login", login);
		query.setParameter("pwd", "%" + pwd);
		
		Usr valueReturn = query.getSingleResult();
		if(valueReturn != null) {
			return true;
		}
		else {
			return false;
		}
    }

	public Usr findByLogin(String login) {
		TypedQuery<Usr> query = em.createQuery("SELECT u " 
						+ "FROM User u " 
						+ "WHERE login = :login ", Usr.class);
		
		query.setParameter("lastname", login);
		
		Usr singleReturn = query.getSingleResult();
		
		return singleReturn;
	}
}
