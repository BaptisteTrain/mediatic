package fr.dta.mediatic.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "loan")
public class Loan extends AbstractEntity {

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotNull
    @NotBlank
    private Date loanDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date returnDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotNull
    @NotBlank
    private Date plannedReturnDate;

    @ManyToOne
    private Media media;

    @ManyToOne
    private Member member;

    public Loan() {
    }

    public Loan(Long id, Date loanDate, Date returnDate, Date plannedReturnDate, Media media, Member member) {
	super();
	this.id = id;
	this.loanDate = loanDate;
	this.returnDate = returnDate;
	this.plannedReturnDate = plannedReturnDate;
	this.media = media;
	this.member = member;
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

    @Override
    public String toString() {
	return "Loan [id=" + id + ", loanDate=" + loanDate + ", returnDate=" + returnDate + ", plannedReturnDate=" + plannedReturnDate + ", media=" + media + ", member=" + member + "]";
    }
}
