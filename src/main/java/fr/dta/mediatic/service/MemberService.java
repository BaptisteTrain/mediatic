package fr.dta.mediatic.service;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import fr.dta.mediatic.model.*;
import fr.dta.mediatic.repository.*;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository repo;
	
	/**
	 * Returns all Members of the application.
	 * @return List<Member>
	 */
	public List<Member> selectAllMembers() {
		return repo.selectAllMembers();
	}
	
	/**
	 * Returns a unique Member based on his exact ID.
	 * @param id
	 * @return Member
	 */
	public Member getMemberById(long id) {
		return repo.getById(id);
	}
	
	/**
	 * Returns Members who borrowed a specific Media.
	 * @param media
	 * @return List<Member>
	 */
	public List<Member> findMembersFromMedia(Media media) {
		return repo.findMembersFromMedia(media);
	}
	
	/**
	 * Search for Members by Identifier, Lastname and/or Firstname.
	 * Depending on the value of the parameters, a different DAO method will be called.
	 * 
	 * If all parameters are empty it will return the complete list of Members.
	 * Else
	 * If identifier is empty, the search will only be based on the Lastname and Firstname.
	 * Else
	 * If lastname AND firstname are empty, the search will only be based on the Identifier.
	 * Else
	 * The search will use all three parameters to find the Members.
	 * 
	 * @param identifier
	 * @param lastname
	 * @param firstname
	 * @return List<Member>
	 */
	public List<Member> findMembersByIdOrNames(String identifier, String lastname, String firstname) {
		
		if( (identifier == null || "".equals(identifier)) && (lastname == null || "".equals(lastname)) && (firstname == null || "".equals(firstname)) ) {
			return repo.selectAllMembers();
		}
		else if(identifier == null || "".equals(identifier)) {
			return repo.findMemberByName(lastname, firstname);
		}
		else if( (lastname == null || "".equals(lastname)) && (firstname == null || "".equals(firstname)) ) {
			return repo.findMembersByIdPartial(identifier);
		}
		else {
			return repo.findMembersByIdOrNames(identifier, lastname, firstname);
		}
	}
	
	/**
	 * Create a new Member in the database
	 * @param member
	 */
	public void addMember(Member member) {
		repo.add(member);
	}
	
	/**
	 * Update a Member in the database
	 * @param member
	 */
	public void updateMember(Member member) {
		repo.update(member);
	}
}
