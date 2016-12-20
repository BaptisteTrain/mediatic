package fr.dta.mediatic.member.service;

import java.util.*;
import org.springframework.stereotype.*;

import fr.dta.mediatic.media.model.*;
import fr.dta.mediatic.member.dao.*;
import fr.dta.mediatic.member.model.*;

@Service
public class MemberService {
	private MemberDAO memberDAO = MemberDAO.instance();
	
	public List<Member> selectAllMembers() {
		return memberDAO.selectAllMembers();
	}
	
	public Member getMemberById(String id) {
		return memberDAO.findMemberById(id);
	}
	
	public List<Member> findMembersFromMedia(Media media) {
		return memberDAO.findMembersFromMedia(media);
	}
	
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
