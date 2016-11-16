package fr.dta.mediatic.loan.dao;

import java.util.Calendar;
import java.util.Date;

import fr.dta.mediatic.helper.GenericDAO;
import fr.dta.mediatic.loan.model.Loan;
import fr.dta.mediatic.media.model.Media;
import fr.dta.mediatic.media.model.TypeMedia;
import fr.dta.mediatic.member.model.Member;

public class LoanDAO extends GenericDAO<Loan> {

	private static LoanDAO dao;
	
	private LoanDAO(){
		super(Loan.class);
	}
	
	/* Pour que la class soit singleton */
	public static LoanDAO instance() {
		if(dao == null) {
			dao = new LoanDAO();
		}
		return dao;
	}

	public void insertLoan(Member member, Media media){
		Loan loan = new Loan();
		loan.setLoanDate(new Date());
		loan.setReturnDate(null); 
		Calendar today = Calendar.getInstance(); // To have the date of today
		today.add(Calendar.DATE, TypeMedia.getDuration(media.getType())); // To have today date plus duration
		loan.setPlannedReturnDate(today.getTime());
		loan.setMedia(media);
		loan.setMember(member);
		persist(loan);
	}
}
