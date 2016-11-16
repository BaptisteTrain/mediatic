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
	
	public Member findMemberFromField() {
		
		EntityManager em = DataBaseHelper.createEntityManager();
		DataBaseHelper.beginTx(em);
		TypedQuery<Member> query = em.createQuery("SELECT u"
				+ "FROM Member m"
				+ "WEHRE m.lastname = :lastname AND m.firstname = :firstname AND "
				+ "")
	}
	
}
