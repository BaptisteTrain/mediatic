package fr.dta.mediatic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dta.mediatic.model.Media;
import fr.dta.mediatic.model.Member;
import fr.dta.mediatic.repository.LoanRepository;
import fr.dta.mediatic.repository.MediaRepository;

@Service
public class LoanService {

	@Autowired
	private LoanRepository loanRepo;
	
	@Autowired
	private MediaRepository mediaRepo;

	/**
	 * Select all the loans
	 * @return
	 */
	public List<Media> getAllLoans() {
		return mediaRepo.getAll();
	}

	/**
	 * Select loan's number by id member
	 * @return
	 */
	public int getLoansByIdMember(Long id) {
		return loanRepo.howManyLoanByIdMember(id);
	}
	
	/**
	 * Create a new loan's media
	 * @return
	 */
	public void createNewLoan(Member member, Media media) {
		loanRepo.insertLoan(member, media);
	}
}
