package fr.dta.mediatic.service;
//package fr.dta.mediatic.member.service;
//
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//
//import fr.dta.mediatic.repository.MemberRepository;
//import fr.dta.mediatic.model.Media;
//import fr.dta.mediatic.model.Member;
//
//@Service
//public class MemberService {
//	/** Instanciation of the Repository in order to access its methods. **/
//	private MemberRepository memberRepository = MemberRepository.instance();
//	
//	/**
//	 * Returns all Members of the application.
//	 * @return List<Member>
//	 *
//	public List<Member> selectAllMembers() {
//		return memberRepository.selectAllMembers();
//	}
//	
//	/**
//	 * Returns a unique Member based on his exact ID.
//	 * @param id
//	 * @return Member
//	 *
//	public Member getMemberById(String id) {
//		return memberRepository.findMemberById(id);
//	}
//	
//	/**
//	 * Returns Members who borrowed a specific Media.
//	 * @param media
//	 * @return List<Member>
//	 *
//	public List<Member> findMembersFromMedia(Media media) {
//		return memberRepository.findMembersFromMedia(media);
//	}
//	
//	/**
//	 * Search for Members by ID, Lastname and/or Firstname.
//	 * Depending on the value of the parameters, a different Repository method will be called.
//	 * 
//	 * If all parameters are empty it will return the complete list of Members.
//	 * Else
//	 * If id is empty, the search will only be based on the Lastname and Firstname.
//	 * Else
//	 * If lastname AND firstname are empty, the search will only be based on the ID.
//	 * Else
//	 * The search will use all three parameters to find the Members.
//	 * 
//	 * @param id
//	 * @param lastname
//	 * @param firstname
//	 * @return List<Member>
//	 *
//	public List<Member> findMembersByIdOrNames(String id, String lastname, String firstname) {
//		if( (id == null || "".equals(id)) && (lastname == null || "".equals(lastname)) && (firstname == null || "".equals(firstname)) ) {
//			return memberRepository.selectAllMembers();
//		}
//		else if(id == null || "".equals(id)) {
//			return memberRepository.findMemberByName(lastname, firstname);
//		}
//		else if( (lastname == null || "".equals(lastname)) && (firstname == null || "".equals(firstname)) ) {
//			return memberRepository.findMembersByIdPartial(id);
//		}
//		else {
//			return memberRepository.findMembersByIdOrNames(id, lastname, firstname);
//		}
//	}*/
//}
