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
	
	public MemberDAO() {
		
		super(Member.class);
	}
		
	public static MemberDAO instance() {
		
		if (dao == null) {
			
			dao = new MemberDAO();
		}
		
		return dao;
	}
	
	public Member findMembersById(int id) {
		
		EntityManager em = DataBaseHelper.createEntityManager();
		
		DataBaseHelper.beginTx(em);
		TypedQuery<Member> query = em.createQuery("SELECT m"
				+ "FROM Member m"
				+ "WHERE m.id = :id", Member.class);
		
		query.setParameter("id", id);
		em.close();
		
		return query.getResultList().get(0);
	}
	
	public List<Member> findMembersByIdPartial(int id) {
		
		EntityManager em = DataBaseHelper.createEntityManager();
		
		DataBaseHelper.beginTx(em);
		TypedQuery<Member> query = em.createQuery("SELECT m"
				+ "FROM Member m"
				+ "WHERE m.id LIKE :id", Member.class);
		
		query.setParameter("id", "%" + id);
		em.close();
		
		return query.getResultList();
	}
	
	public List<Member> findMemberByName(String lastname, String firstname) {
		
		EntityManager em = DataBaseHelper.createEntityManager();
		
		DataBaseHelper.beginTx(em);
		TypedQuery<Member> query = em.createQuery("SELECT m"
				+ "FROM Member m"
				+ "WHERE m.lastname LIKE :lastname"
				+ "AND m.firstname LIKE :firstname", Member.class);
		
		query.setParameter("lastname", "%" + lastname + "%");
		query.setParameter("firstname", "%" + firstname + "%");
		em.close();
		
		return query.getResultList();		
	}
	
	public List<Member> findMembersFromMedia(Media media) {
		EntityManager em = DataBaseHelper.createEntityManager();
		DataBaseHelper.beginTx(em);
		TypedQuery<Member> query = em.createQuery("SELECT m "
											   + "FROM Member m "
											   + "JOIN m.listLoan l "
											   + "WHERE l.media = :id ", Member.class);
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
	public List<Member> findMemberFromField(long id, String lastname, String firstname) {
		EntityManager em = DataBaseHelper.createEntityManager();
		DataBaseHelper.beginTx(em);
		TypedQuery<Member> query = em.createQuery("SELECT m"
												+ "FROM Member m"
												+ "WHERE m.lastname = :lastname "
												+ "OR m.firstname = :firstname "
												+ "OR m.id = :id", Member.class);
		query.setParameter("id", id);
		query.setParameter("lastname", lastname);
		query.setParameter("firstname", firstname);
		List<Member> listeReturn = query.getResultList();
		em.close();
		return listeReturn;
	}
	
}
