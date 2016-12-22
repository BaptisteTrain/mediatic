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
	public Member getMemberById(String id) {
		return repo.getById(Long.parseLong(id));
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
	public List<Member> findMembersByIdOrNames(Optional<String> id, Optional<String> lastname, Optional<String> firstname) {
		
		System.out.println("ID: " + id + " / Last: " + lastname);
		
		/*if( (id == null || "".equals(id)) && (lastname == null || "".equals(lastname)) && (firstname == null || "".equals(firstname)) ) {
			return repo.selectAllMembers();
		}
		else if(id == null || "".equals(id)) {
			return repo.findMemberByName(lastname, firstname);
		}
		else if( (lastname == null || "".equals(lastname)) && (firstname == null || "".equals(firstname)) ) {
			return repo.findMembersByIdPartial(Long.parseLong(id.get()));
		}
		else {
			return repo.findMembersByIdOrNames(Long.parseLong(id), lastname, firstname);
		}*/
		return null;
	}
}
