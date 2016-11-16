package fr.dta.mediatic.member.dao;

import fr.dta.mediatic.helper.GenericDAO;
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
	
	public static void addMember(Member member) {
		
		dao.persist(member);
	}
	
	public static void removeMember(Member member) {
		
		dao.remove(member.getIdentifier());
	}
	
	public static void modifyMenber(Member member) {
		
		dao.merge(member);
	}
	
}
