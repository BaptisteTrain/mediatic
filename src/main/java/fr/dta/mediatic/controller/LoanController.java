package fr.dta.mediatic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.dta.mediatic.model.Media;
import fr.dta.mediatic.model.Member;
import fr.dta.mediatic.service.LoanService;

@RestController
@RequestMapping("/loan")
public class LoanController {

	@Autowired
	private LoanService loanService;
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public void getDetailMedia(@PathVariable Member member, Media media) {
		loanService.createNewLoan(member, media);
	}
}
