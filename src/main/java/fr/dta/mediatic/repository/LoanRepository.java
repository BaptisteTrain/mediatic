package fr.dta.mediatic.repository;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fr.dta.mediatic.model.Loan;
import fr.dta.mediatic.model.Media;
import fr.dta.mediatic.model.Member;
import fr.dta.mediatic.model.TypeMedia;

@Repository
@Transactional
public class LoanRepository extends AbstractRepository<Loan> {

    @Override
    protected Class<Loan> getEntityClass() {
	return Loan.class;
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
		em.persist(loan);
	}

	/**
	 * Count how many loan's media
	 * @return int
	 */
	public int howManyAllLoanMedia() {
		Query query = em.createQuery("SELECT count(l) FROM Loan l ");
		int count = ((Integer) query.getSingleResult()).intValue();
		return count;
	}

	/**
	 * Count how many loaning's media
	 * @return int
	 */
	public int howManyLoaningMedia() {
		Query query = em.createQuery(" SELECT count(l) "
									+ "FROM Loan l "
									+ "WHERE l.returnDate is null ");
		int count = ((Integer) query.getSingleResult()).intValue();
		return count;
	}
	

	/**
	 * Count how many loaning's media for one member
	 * @return int
	 */
	public int howManyLoanByIdMember(Long id) {
		Query query = em.createQuery(" SELECT count(l) "
									+ "FROM Loan l JOIN Member m "
									+ "WHERE l.returnDate is null AND m.id = :id");
		query.setParameter("id", id);
		int count = ((Integer) query.getSingleResult()).intValue();
		return count;
	}
}
