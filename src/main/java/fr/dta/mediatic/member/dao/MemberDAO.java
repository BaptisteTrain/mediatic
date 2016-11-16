package fr.dta.mediatic.member.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.dta.mediatic.helper.DataBaseHelper;
import fr.dta.mediatic.helper.GenericDAO;
import fr.dta.mediatic.media.model.Media;
import fr.dta.mediatic.member.model.Member;

public class MemberDAO extends GenericDAO<Member> {
	
	/*ajouter un adherent
	supp ""
	modif ""*/
	
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
	
	/*
	public static void addMember(Member member) {
		dao.persist(member);
	}
	
	public static void removeMember(Member member) {
		dao.remove(member.getIdentifier());
	}
	
	public static void modifyMenber(Member member) {
		dao.merge(member);
	}
	*/
	
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
	
}
