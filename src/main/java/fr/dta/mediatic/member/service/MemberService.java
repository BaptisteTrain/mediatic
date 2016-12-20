package fr.dta.mediatic.member.service;

import java.util.*;
import org.springframework.stereotype.*;

import fr.dta.mediatic.media.model.*;
import fr.dta.mediatic.member.dao.*;
import fr.dta.mediatic.member.model.*;

@Service
public class MemberService {
	/** Instanciation of the DAO in order to access its methods. **/
	private MemberDAO memberDAO = MemberDAO.instance();
	
	/**
	 * Returns all Members of the application.
	 * @return List<Member>
	 */
	public List<Member> selectAllMembers() {
		return memberDAO.selectAllMembers();
	}
	
	/**
	 * Returns a unique Member based on his complete ID.
	 * @param id
	 * @return Member
	 */
	public Member getMemberById(String id) {
		return memberDAO.findMemberById(id);
	}
	
	/**
	 * Returns Members who borrowed a specific Media.
	 * @param media
	 * @return List<Member>
	 */
	public List<Member> findMembersFromMedia(Media media) {
		return memberDAO.findMembersFromMedia(media);
	}
	
	/**
	 * Search for Members by ID, Lastname and/or Firstname.
	 * Depending on the value of the parameters, a different DAO method will be called.
	 * 
	 * If all parameters are empty it will return the complete list of Members.
	 * Else
	 * If id is empty, the search will only be based on the Lastname and Firstname.
	 * Else
	 * If lastname AND firstname are empty, the search will only be based on the ID.
	 * Else
	 * The search will use all three parameters to find the Members.
	 * 
	 * @param id
	 * @param lastname
	 * @param firstname
	 * @return List<Member>
	 */
	public List<Member> findMembersByIdOrNames(String id, String lastname, String firstname) {
		if( (id == null || "".equals(id)) && (lastname == null || "".equals(lastname)) && (firstname == null || "".equals(firstname)) ) {
			return memberDAO.selectAllMembers();
		}
		else if(id == null || "".equals(id)) {
			return memberDAO.findMemberByName(lastname, firstname);
		}
		else if( (lastname == null || "".equals(lastname)) && (firstname == null || "".equals(firstname)) ) {
			return memberDAO.findMembersByIdPartial(id);
		}
		else {
			return memberDAO.findMembersByIdOrNames(id, lastname, firstname);
		}
	}
}
