package fr.dta.mediatic.member.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.dta.mediatic.helper.DataBaseHelper;
import fr.dta.mediatic.helper.GenericDAO;
import fr.dta.mediatic.media.model.Media;
import fr.dta.mediatic.member.model.Member;

public class MemberDAO extends GenericDAO<Member> {

    private static MemberDAO dao;

    private MemberDAO() {
    	super(Member.class);
    }

    public static MemberDAO instance() {
		if (dao == null) {
		    dao = new MemberDAO();
		}
		
		return dao;
    }
    
    /**
     * Select all the members with the loans list and the media of the loan
     * @return
     */
    public List<Member> selectAllMembers() {
		EntityManager em = DataBaseHelper.getEntityManager();
		DataBaseHelper.beginTx(em);
		TypedQuery<Member> query = em.createQuery("SELECT m "
							+ "FROM Member m " 
							+ "LEFT OUTER JOIN m.listLoan l ", Member.class);
		List<Member> listeReturn = query.getResultList();
		em.close();
		return listeReturn;
    }

    /**
     * 
     * @param id
     * @return List<Member>
     */
    public List<Member> findMembersByIdPartial(String id) {
		EntityManager em = DataBaseHelper.getEntityManager();
	
		DataBaseHelper.beginTx(em);
		TypedQuery<Member> query = em.createQuery(
			"SELECT m " 
			+ "FROM Member m " 
			+ "WHERE m.identifier LIKE :id", Member.class);
	
		query.setParameter("id",  id + "%");
		List<Member> listeReturn = query.getResultList();
		em.close();
	
		return listeReturn;
    }

    /**
     * Return the member with this id 
     * @param id
     * @return Member
     */
    public Member findMemberById(String id) {
		EntityManager em = DataBaseHelper.getEntityManager();
	
		DataBaseHelper.beginTx(em);
		TypedQuery<Member> query = em.createQuery(
			"SELECT m " 
			+ "FROM Member m " 
			+ "WHERE m.identifier = :id", Member.class);
	
		query.setParameter("id", id);
		Member result = query.getSingleResult();
		em.close();
	
		return result;
    }

    /**
     * Find members by name
     * @param lastname
     * @param firstname
     * @return List<Member>
     */
    public List<Member> findMemberByName(String lastname, String firstname) {
		EntityManager em = DataBaseHelper.getEntityManager();
	
		DataBaseHelper.beginTx(em);
		TypedQuery<Member> query = em.createQuery(
			"SELECT m " 
			+ "FROM Member m " 
			+ "WHERE UPPER(m.person.lastname) LIKE :lastname " 
			+ "AND UPPER(m.person.firstname) LIKE :firstname", Member.class);
	
		query.setParameter("lastname", "%" + lastname.toUpperCase() + "%");
		query.setParameter("firstname", "%" + firstname.toUpperCase() + "%");
		List<Member> listeReturn = query.getResultList();
		em.close();
	
		return listeReturn;
    }

    /**
     * Find the members and its loan from a media
     * @param media
     * @return List<Member>
     */
    public List<Member> findMembersFromMedia(Media media) {
		EntityManager em = DataBaseHelper.getEntityManager();
		DataBaseHelper.beginTx(em);
		TypedQuery<Member> query = em.createQuery(
			"SELECT m " 
			+ "FROM Member m " 
			+ "JOIN m.listLoan l " 
			+ "WHERE l.media.id = :id ", Member.class);
		
		query.setParameter("id", media.getId());
		List<Member> listeReturn = query.getResultList();
		em.close();
		
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
		EntityManager em = DataBaseHelper.getEntityManager();
		DataBaseHelper.beginTx(em);
		TypedQuery<Member> query = em.createQuery(
			"SELECT m " 
			+ "FROM Member m " 
			+ "WHERE m.person.lastname LIKE :lastname " 
			+ "OR m.person.firstname LIKE :firstname " 
			+ "OR m.identifier LIKE :id", Member.class);
		
		query.setParameter("id", id + "%");
		query.setParameter("lastname", "%" + lastname + "%");
		query.setParameter("firstname", "%" + firstname + "%");
		List<Member> listeReturn = query.getResultList();
		em.close();
		
		return listeReturn;
    }

}
