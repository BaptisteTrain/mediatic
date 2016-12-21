package fr.dta.mediatic.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Member extends AbstractEntity {

    /* COLUMNS */

    @NotBlank
    @Length(min=4)
    @Pattern(regexp="^[a-zA-Z0-9]+$")
    @Column(name = "identifier",unique=true)
    private String identifier;

    @Pattern(regexp="^[0-9]{4}-[0-9]{2}-[0-9]{2}")
    @Column(name = "birthDate")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "numberOfLoans")
    private int numberOfLoans;

    @Embedded
    private Person person;

    @Embedded
    private Address address;

    @OneToOne
    private Subscription subscription;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Loan> listLoan;

    /* CONSTRUCTORS */

    public Member() {
	this.numberOfLoans = 0;
    }

    public Member(Long id, String identifier, Date birthDate, String lastname, String firstname, String email, Gender gender, String address, String postalCode, String city, Subscription sub) {
	this.id = id;
	this.identifier = identifier;
	this.birthDate = birthDate;
	this.numberOfLoans = 0;
	this.person = new Person(lastname, firstname, email, gender);
	this.address = new Address(address, postalCode, city);
	this.subscription = sub;
    }

    /* GETTERS / SETTERS */

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }
    
    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
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

    @Override
    public String toString() {
	return "Member [identifier=" + identifier + ", birthDate=" + birthDate + ", numberOfLoans=" + numberOfLoans + ", person=" + person + ", address=" + address + ", subscription=" + subscription
		+ "]";
    }

}
