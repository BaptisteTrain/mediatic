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
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	/**
	 * Returns all Members of the application.
	 * Access page: /api/member/allmembers
	 * Request method: GET
	 * @return List<Member>
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET)
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
	public Member getMemberById(@PathVariable long id) {	
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
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public List<Member> findMembersByIdOrNames(@RequestParam (value = "identifier", required = false) String identifier, @RequestParam (value = "lastname", required = false) String lastname, @RequestParam (value = "firstname", required = false) String firstname) {
		return memberService.findMembersByIdOrNames(identifier, lastname, firstname);
	}
	
	/**
	 * Create a new Member
	 * @param member
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public void addMember(@RequestBody @Valid Member member) {
		Subscription subscription = member.getSubscription();
		//member.setSubscription(null);
		//subscription.setId(null);
		subscriptionService.create(subscription);
		memberService.addMember(member);
	}
	@RequestMapping(value = "/createSub", method = RequestMethod.POST)
	public void addSub(@RequestBody @Valid Subscription subscription) {
		subscriptionService.create(subscription);
	}
	
	/**
	 * Update a Member.
	 * Access page: /api/member/{id}
	 * Request method: PUT
	 * @param id
	 * @return Member
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void updateMember(@RequestBody @Valid Member member) {
		memberService.updateMember(member);
	}
	
	/**
	 * Returns the List of Loans from a Member based on his ID.
	 * Access page: /api/member/loans/{id}
	 * Request method: GET
	 * @param id
	 * @return List<Loan>
	 */
	@RequestMapping(value = "/loans/{id}", method = RequestMethod.GET)
	public List<Loan> getMemberLoans(@PathVariable long id) {	
		return memberService.getMemberLoans(id);
	}
}
