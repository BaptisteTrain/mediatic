package fr.dta.mediatic.loan.model;

import java.util.Date;

import javax.persistence.*;

import fr.dta.mediatic.media.model.Media;

@Entity
@Table(name="loan")
public class Loan  {

	@Id
	@GeneratedValue
	private Long id ;
	
	private Date loanDate;
	
	private Date returnDate;
	
	private Date plannedReturnDate;
	
	@ManyToOne
	private Media media;
	
	@ManyToOne
	private Member member;
	
	public Loan() {}

	public Loan(Date loanDate, Date returnDate, Date plannedReturnDate) {
		super();
		this.loanDate = loanDate;
		this.returnDate = returnDate;
		this.plannedReturnDate = plannedReturnDate;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Date getPlannedReturnDate() {
		return plannedReturnDate;
	}

	public void setPlannedReturnDate(Date plannedReturnDate) {
		this.plannedReturnDate = plannedReturnDate;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String toString() {
		return "Loan [loanDate=" + loanDate + ", returnDate=" + returnDate + ", plannedReturnDate=" + plannedReturnDate
				+ "]";
	}
}
