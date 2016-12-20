package fr.dta.mediatic.loan.dao;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.dta.mediatic.helper.DataBaseHelper;
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

	/**
	 * Add a loan's media
	 * @param member
	 * @param media
	 * @return
	 */
	public void insertLoan(Member member, Media media){
		Loan loan = new Loan();
		loan.setLoanDate(new Date());
		loan.setReturnDate(null); 
		Calendar today = Calendar.getInstance(); // To have the today date
		today.add(Calendar.DATE, TypeMedia.getDuration(media.getType())); // To have today date plus duration
		loan.setPlannedReturnDate(today.getTime());
		loan.setMedia(media);
		loan.setMember(member);
		persist(loan);
	}

	/**
	 * Count how many loan's media
	 * @return int
	 */
	public int howManyAllLoanMedia() {
		EntityManager em = DataBaseHelper.getEntityManager();
		DataBaseHelper.beginTx(em);
		Query query = em.createQuery("SELECT count(l) FROM Loan l ");
		int count = ((Integer) query.getSingleResult()).intValue();
		em.close();
		return count;
	}

	/**
	 * Count how many loaning's media
	 * @return int
	 */
	public int howManyLoaningMedia() {
		EntityManager em = DataBaseHelper.getEntityManager();
		DataBaseHelper.beginTx(em);
		Query query = em.createQuery(" SELECT count(l) "
									+ "FROM Loan l "
									+ "WHERE l.returnDate is null ");
		int count = ((Integer) query.getSingleResult()).intValue();
		em.close();
		return count;
	}
}
