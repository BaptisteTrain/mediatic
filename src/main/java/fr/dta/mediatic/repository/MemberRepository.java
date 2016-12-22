package fr.dta.mediatic.repository;

import java.util.*;
import javax.persistence.*;
import javax.transaction.*;
import org.springframework.stereotype.*;

import fr.dta.mediatic.model.*;

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
		TypedQuery<Member> query = em.createQuery(
			"SELECT m "
			+ "FROM Member m " 
			+ "LEFT OUTER JOIN m.listLoan l ", Member.class);
		List<Member> result = query.getResultList();
		
		// Initialize the lazy list of Loans
		for (Member m : result) {
		    System.out.println(m.getListLoan());
		}
		
		return result;
    }

    /**
     * Search for Members by partial ID
     * @param id
     * @return List<Member>
     */
    public List<Member> findMembersByIdPartial(long id) {
		TypedQuery<Member> query = em.createQuery(
			"SELECT m " 
			+ "FROM Member m " 
			+ "WHERE m.identifier LIKE :id", Member.class);
	
		query.setParameter("id",  id + "%");
		List<Member> listeReturn = query.getResultList();
	
		return listeReturn;
    }
    
    /**
     * Search for Members by Lastname and Firstname
     * @param lastname
     * @param firstname
     * @return List<Member>
     */
    public List<Member> findMemberByName(String lastname, String firstname) {
		TypedQuery<Member> query = em.createQuery(
			"SELECT m " 
			+ "FROM Member m " 
			+ "WHERE UPPER(m.lastname) LIKE :lastname " 
			+ "AND UPPER(m.firstname) LIKE :firstname", Member.class);
	
		query.setParameter("lastname", "%" + lastname.toUpperCase() + "%");
		query.setParameter("firstname", "%" + firstname.toUpperCase() + "%");
		List<Member> listeReturn = query.getResultList();
	
		return listeReturn;
    }
    
    /**
     * Search for Members who loaned a specific Media
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
     * Search for Members by ID, Lastname and Firstname
     * @param id
     * @param lastname
     * @param firstname
     * @return List<Member>
     */
    public List<Member> findMembersByIdOrNames(long id, String lastname, String firstname) {
		TypedQuery<Member> query = em.createQuery(
			"SELECT m " 
			+ "FROM Member m " 
			+ "WHERE m.lastname LIKE :lastname " 
			+ "OR m.firstname LIKE :firstname " 
			+ "OR m.identifier LIKE :id", Member.class);
		
		query.setParameter("id", id + "%");
		query.setParameter("lastname", "%" + lastname + "%");
		query.setParameter("firstname", "%" + firstname + "%");
		List<Member> listeReturn = query.getResultList();
		
		return listeReturn;
    }
}
