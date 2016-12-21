package fr.dta.mediatic.repository;

import java.util.List;

import javax.persistence.TypedQuery;
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
    
    /**
     * Select all the users
     * 
     * @return List<User> 
     */
    public List<User> selectAllUsers() {
		TypedQuery<User> query = em.createQuery("SELECT u "
							+ "FROM User u", User.class);
		List<User> listeReturn = query.getResultList();
		
		return listeReturn;
    }
    
    /**
     * Find user by complete id
     * 
     * @param id
     * @return List<User>
     */
    public User findUsersById(int id) {
		TypedQuery<User> query = em.createQuery("SELECT u " 
							+ "FROM Usr u " 
							+ "WHERE u.id = :id", User.class);
	
		query.setParameter("id",  id);
		User singleReturn = query.getSingleResult();
	
		return singleReturn;
    }    
   
    /**
     * Find user by partial id
     * 
     * @param id
     * @return List<User>
     */
    public List<User> findUsersByIdPartial(String id) {
		TypedQuery<User> query = em.createQuery("SELECT u " 
							+ "FROM Usr u " 
							+ "WHERE u.login LIKE :id", User.class);
	
		query.setParameter("id",  id + "%");

		List<User> listeReturn = query.getResultList();

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
		TypedQuery<User> query = em.createQuery("SELECT u " 
							+ "FROM Usr u " 
							+ "WHERE upper(u.person.lastname) LIKE :lastname " 
							+ "AND upper(u.person.firstname) LIKE :firstname", User.class);

		query.setParameter("lastname", "%" + lastname.toUpperCase() + "%");
		query.setParameter("firstname", "%" + firstname.toUpperCase() + "%");
		
		List<User> listeReturn = query.getResultList();

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
		TypedQuery<User> query = em.createQuery("SELECT u " 
							+ "FROM Usr u " 
							+ "WHERE upper(u.person.lastname) LIKE :lastname " 
							+ "OR upper(u.person.firstname) LIKE :firstname " 
							+ "OR u.login LIKE :id", User.class);

		query.setParameter("id", id + "%");
		query.setParameter("lastname", "%" + lastname + "%");
		query.setParameter("firstname", "%" + firstname + "%");
		
		List<User> listeReturn = query.getResultList();
		
		return listeReturn;
	}    
}
