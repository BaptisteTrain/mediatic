package fr.dta.mediatic.member.model;

import java.util.*;
import javax.persistence.*;

import fr.dta.mediatic.loan.model.Loan;
import fr.dta.mediatic.model.*;

public class Member {
	
	/* COLUMNS */
	
	@Id
	@Column(name = "identifier")
	private Long identifier;
	
	@Column(name = "birthDate")
	private Date birthDate;
	
	@Column(name = "numberOfLoans")
	int numberOfLoans;
	
	@Embedded
	Person person;
	
	@Embedded
	Address address;
	
	@Embedded
	Subscription subscription;
	
	@OneToMany(mappedBy = "member")
	List<Loan> listLoan;
	
	/* CONSTRUCTORS */
	
	public Member() {
		this.numberOfLoans = 0;
	}
	
	public Member(Long identifier, Date birthDate, String lastname, String firstname, String email, Gender gender, String address, int postalCode, String city, int amount, Date paymentDate) {
		this.identifier = identifier;
		this.birthDate = birthDate;
		this.numberOfLoans = 0;
		this.person = new Person(lastname, firstname, email, gender);
		this.address = new Address(address, postalCode,city);
		this.subscription = new Subscription(amount, paymentDate);
	}
	
	/* GETTERS / SETTERS */
	
	public Long getIdentifier() {
		return identifier;
	}
	
	public void setIdentifier(Long identifier) {
		this.identifier = identifier;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public int getNumberOfLoans() {
		return numberOfLoans;
	}
	
	public void setNumberOfLoans(int numberOfLoans) {
		this.numberOfLoans = numberOfLoans;
	}
	
	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Subscription getSubscription() {
		return subscription;
	}
	
	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}
}
