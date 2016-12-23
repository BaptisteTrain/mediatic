package fr.dta.mediatic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fr.dta.mediatic.model.Media;
import fr.dta.mediatic.model.Member;
import fr.dta.mediatic.service.LoanService;

@RestController
@RequestMapping("/loan")
public class LoanController {

	@Autowired
	private LoanService loanService;
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public void getDetailMedia(@RequestBody ObjectNode node) {
		ObjectMapper mapper = new ObjectMapper();
		
		Media media = mapper.convertValue(node.get("media"), Media.class);
		Member member = mapper.convertValue(node.get("member"), Member.class);
		//Member member = node.get("member");
		System.out.println(media);
		System.out.println(member);
		loanService.createNewLoan(member, media);
	}
}
