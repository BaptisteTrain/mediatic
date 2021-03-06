package fr.dta.mediatic.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
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
    
    @NotBlank
	@Length(max=30)
	@Column(name = "lastname")
	private String lastname;

	@NotBlank
	@Length(max=30)
	@Column(name = "firstname")
	private String firstname;
	
	@NotBlank
	//@Email
	@Column(name = "email")
	private String email;

    //@Pattern(regexp="^[0-9]{4}-[0-9]{2}-[0-9]{2}$")
    //@Pattern(regexp="^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$")
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Column(name = "numberOfLoans")
    private int numberOfLoans; 
	
	@Column(name = "gender")
	private Gender gender;

	@Column(name = "address")
	private String address;
	
	@Length(min=5,max=5)
	@Column(name = "postalcode")
	private String postalcode;
	
	@Column(name = "city")
	private String city;

    @OneToOne
    private Subscription subscription;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Loan> listLoan;

    /* CONSTRUCTORS */

    public Member() {
	this.numberOfLoans = 0;
    }
    
    public Member(String identifier, String lastname, String firstname, String email, Date birthDate, int numberOfLoans,
			Gender gender, String address, String postalCode, String city, Subscription subscription,
			List<Loan> listLoan) {
		super();
		this.identifier = identifier;
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.birthdate = birthDate;
		this.numberOfLoans = numberOfLoans;
		this.gender = gender;
		this.address = address;
		this.postalcode = postalCode;
		this.city = city;
		this.subscription = subscription;
		this.listLoan = listLoan;
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

    public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthdate() {
    	return birthdate;
    }

    public void setBirthdate(Date birthdate) {
    	this.birthdate = birthdate;
    }

    public int getNumberOfLoans() {
    	return numberOfLoans;
    }

    public void setNumberOfLoans(int numberOfLoans) {
    	this.numberOfLoans = numberOfLoans;
    }

    public String getAddress() {
    	return address;
    }

    public void setAddress(String address) {
    	this.address = address;
    }

    public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Subscription getSubscription() {
	return subscription;
    }

    public void setSubscription(Subscription subscription) {
	this.subscription = subscription;
    }

    public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public List<Loan> getListLoan() {
		return listLoan;
	}

	public void setListLoan(List<Loan> listLoan) {
		this.listLoan = listLoan;
	}

	@Override
    public String toString() {
	return "Member [identifier=" + identifier + ", birthDate=" + birthdate + ", numberOfLoans=" + numberOfLoans + ", person=" + lastname + " " + firstname + " " + email + " " + gender + ", address=" + address + ", subscription=" + subscription
		+ "]";
    }
}
