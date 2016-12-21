package fr.dta.mediatic.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fr.dta.mediatic.model.Media;
import fr.dta.mediatic.model.Member;

@Repository
@Transactional
public class MemberRepository extends AbstractRepository<Member> {

    @Override
    protected Class<Member> getEntityClass() {
    	return Member.class;
    }
    
    /**
     * Select all the members with the loans list and the media of the loan
     * @return
     */
    public List<Member> selectAllMembers() {
		TypedQuery<Member> query = em.createQuery("SELECT m "
							+ "FROM Member m " 
							+ "LEFT OUTER JOIN m.listLoan l ", Member.class);
		List<Member> listeReturn = query.getResultList();
		
		return listeReturn;
    }

    /**
     * 
     * @param id
     * @return List<Member>
     */
    public List<Member> findMembersByIdPartial(String id) {
		TypedQuery<Member> query = em.createQuery(
			"SELECT m " 
			+ "FROM Member m " 
			+ "WHERE m.identifier LIKE :id", Member.class);
	
		query.setParameter("id",  id + "%");
		List<Member> listeReturn = query.getResultList();
	
		return listeReturn;
    }

    /**
     * Return the member with this id 
     * @param id
     * @return Member
     */
    public Member findMemberById(int id) {
		TypedQuery<Member> query = em.createQuery(
			"SELECT m " 
			+ "FROM Member m " 
			+ "WHERE m.id = :id", Member.class);
	
		query.setParameter("id", id);
		Member result = query.getSingleResult();
	
		return result;
    }

    /**
     * Find members by name
     * @param lastname
     * @param firstname
     * @return List<Member>
     */
    public List<Member> findMemberByName(String lastname, String firstname) {
		TypedQuery<Member> query = em.createQuery(
			"SELECT m " 
			+ "FROM Member m " 
			+ "WHERE UPPER(m.person.lastname) LIKE :lastname " 
			+ "AND UPPER(m.person.firstname) LIKE :firstname", Member.class);
	
		query.setParameter("lastname", "%" + lastname.toUpperCase() + "%");
		query.setParameter("firstname", "%" + firstname.toUpperCase() + "%");
		List<Member> listeReturn = query.getResultList();
	
		return listeReturn;
    }

    /**
     * Find the members and its loan from a media
     * @param media
     * @return List<Member>
     */
    public List<Member> findMembersFromMedia(Media media) {
		TypedQuery<Member> query = em.createQuery(
			"SELECT m " 
			+ "FROM Member m " 
			+ "JOIN m.listLoan l " 
			+ "WHERE l.media.id = :id ", Member.class);
		
		query.setParameter("id", media.getId());
		List<Member> listeReturn = query.getResultList();
		
		return listeReturn;
    }

    /**
     * Find the members by id or lastname or firstname
     * @param id
     * @param lastname
     * @param firstname
     * @return List<Member>
     */
    public List<Member> findMembersByIdOrNames(String id, String lastname, String firstname) {
		TypedQuery<Member> query = em.createQuery(
			"SELECT m " 
			+ "FROM Member m " 
			+ "WHERE upper(m.person.lastname) LIKE :lastname " 
			+ "OR upper(m.person.firstname) LIKE :firstname " 
			+ "OR m.identifier LIKE :id", Member.class);
		
		query.setParameter("id", id + "%");
		query.setParameter("lastname", "%" + lastname + "%");
		query.setParameter("firstname", "%" + firstname + "%");
		List<Member> listeReturn = query.getResultList();
		
		return listeReturn;
    }
}
