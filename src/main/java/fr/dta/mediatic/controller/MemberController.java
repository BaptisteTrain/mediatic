package fr.dta.mediatic.controller;

import java.util.*;
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
	 * Search for Members by ID, Lastname and/or Firstname.
	 * Access page: /api/member/searchmembers?id={id}&lastname={lastname}&firstname={firstname}
	 * Request method: GET
	 * @param id
	 * @param lastname
	 * @param firstname
	 * @return List<Member>
	 */
	//@RequestMapping(value = "/searchmembers?id={id}&lastname={lastname}&firstname={firstname}", method = RequestMethod.GET)
	@RequestMapping(value = "/searchmembers", method = RequestMethod.GET)
	public List<Member> findMembersByIdOrNames(@RequestParam Optional<String> id, @RequestParam Optional<String> lastname, @RequestParam Optional<String> firstname) {
		return memberService.findMembersByIdOrNames(id, lastname, firstname);
	}
}
