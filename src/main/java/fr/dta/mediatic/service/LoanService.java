package fr.dta.mediatic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.dta.mediatic.model.Media;
import fr.dta.mediatic.repository.MediaRepository;

public class LoanService {

	@Autowired
	private MediaRepository repo;

	/**
	 * Select all the loans
	 * @return
	 */
	public List<Media> getAllLoans() {
		return repo.getAll();
	}

}
