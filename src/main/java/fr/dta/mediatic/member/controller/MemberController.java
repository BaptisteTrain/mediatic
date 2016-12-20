package fr.dta.mediatic.member.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import fr.dta.mediatic.media.model.*;
import fr.dta.mediatic.member.model.*;
import fr.dta.mediatic.member.service.*;

@RestController
@SessionAttributes
@RequestMapping("/api/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/allmembers", method = RequestMethod.GET)
	public List<Member> selectAllMembers() {
		return memberService.selectAllMembers();
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Member getMemberById(@PathVariable String id) {	
		return memberService.getMemberById(id);
	}
	
	@RequestMapping(value = "/membersbymedia", method = RequestMethod.POST)
	public List<Member> findMembersFromMedia(Media media) {
		return memberService.findMembersFromMedia(media);
	}
	
	@RequestMapping(value = "/searchmembers?id={id}&lastname={lastname}&firstname={firstname}", method = RequestMethod.GET)
	public List<Member> findMembersByIdOrNames(@PathVariable String id, @PathVariable String lastname, @PathVariable String firstname) {
		return memberService.findMembersByIdOrNames(id, lastname, firstname);
	}
}
