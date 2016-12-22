package fr.dta.mediatic.controller;

import java.util.*;
import javax.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import fr.dta.mediatic.model.*;
import fr.dta.mediatic.service.*;

@RestController
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
	@RequestMapping(value = "/allmembers", method = RequestMethod.GET)
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
	public Member getMemberById(@PathVariable String id) {	
		return memberService.getMemberById(id);
	}
	
	/**
	 * Returns Members who borrowed a specific Media.
	 * Access page: /api/member/membersbymedia
	 * Request method: POST
	 * @param media
	 * @return List<Member>
	 */
	@RequestMapping(value = "/membersbymedia", method = RequestMethod.POST)
	public List<Member> findMembersFromMedia(Media media) {
		return memberService.findMembersFromMedia(media);
	}
	
	/**
	 * Search for Members by Identifier, Lastname and/or Firstname.
	 * Access page: /api/member/searchmembers?identifier={identifier}&lastname={lastname}&firstname={firstname}
	 * Request method: GET
	 * @param identifier
	 * @param lastname
	 * @param firstname
	 * @return List<Member>
	 */
	@RequestMapping(value = "/searchmembers", method = RequestMethod.GET)
	public List<Member> findMembersByIdOrNames(@RequestParam (value = "identifier", required = false) String identifier, @RequestParam (value = "lastname", required = false) String lastname, @RequestParam (value = "firstname", required = false) String firstname) {
		return memberService.findMembersByIdOrNames(identifier, lastname, firstname);
	}
	
	/**
	 * Create a new Member
	 * @param member
	 * @return
	 */
	@RequestMapping(value = "/addmember", method = RequestMethod.POST)
	@ResponseBody
	public void addMember(@RequestBody @Valid Member member) {
		//return memberService.findMembersByIdOrNames(identifier, lastname, firstname);
		memberService.addMember(member);
	}
}
