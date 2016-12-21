package fr.dta.mediatic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.dta.mediatic.model.Media;
import fr.dta.mediatic.model.Member;
import fr.dta.mediatic.service.MemberService;

@RestController
@SessionAttributes
@RequestMapping("/api/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * Returns all Members of the application.
	 * Access page: /api/member/allmembers
	 * Request method: GET
	 * @return List<Member>
	 */
	@RequestMapping(value = "/allMembers", method = RequestMethod.GET)
	public List<Member> selectAllMembers() {
		return memberService.selectAllMembers();
	}
	
	/**
	 * Returns a unique Member based on his complete ID.
	 * Access page: /api/member/{id}
	 * Request method: GET
	 * @param id
	 * @return Member
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Member getMemberById(@PathVariable int id) {	
		return memberService.getMemberById(id);
	}
	
	/**
	 * Returns Members who borrowed a specific Media.
	 * Access page: /api/member/membersbymedia
	 * Request method: POST
	 * @param media
	 * @return List<Member>
	 */
	@RequestMapping(value = "/membersByMedia", method = RequestMethod.POST)
	public List<Member> findMembersFromMedia(Media media) {
		return memberService.findMembersFromMedia(media);
	}
	/**
	 * Search for Members by ID, Lastname and/or Firstname.
	 * Access page: /api/member/searchmembers?id={id}&lastname={lastname}&firstname={firstname}
	 * Request method: GET
	 * @param id
	 * @param lastname
	 * @param firstname
	 * @return List<Member>
	 */
	@RequestMapping(value = "/searchMembers?id={id}&lastname={lastname}&firstname={firstname}", method = RequestMethod.GET)
	public List<Member> findMembersByIdOrNames(@PathVariable String id, @PathVariable String lastname, @PathVariable String firstname) {
		return memberService.findMembersByIdOrNames(id, lastname, firstname);
	}
}
