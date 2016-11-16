package fr.dta.mediatic.user.dao;

import fr.dta.mediatic.helper.GenericDAO;
import fr.dta.mediatic.member.dao.MemberDAO;
import fr.dta.mediatic.member.model.Member;
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
	
	public static void addMember(User user) {
		
		dao.persist(user);
	}
	
	public static void removeMember(User user) {
		
		dao.remove(user.getId());
	}
	
	public static void modifyMenber(User user) {
		
		dao.merge(user);
	}
}
