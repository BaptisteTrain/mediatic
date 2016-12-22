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
	@Email
	@Column(name = "email")
	private String email;

    @Pattern(regexp="^[0-9]{4}-[0-9]{2}-[0-9]{2}")
    @Column(name = "birthDate")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "numberOfLoans")
    private int numberOfLoans; 
	
	@Column(name = "gender")
	private Gender gender;

	@Column(name = "address")
	private String address;
	
	@Length(min=5,max=5)
	@Column(name = "postalCode")
	private String postalCode;
	
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
		this.birthDate = birthDate;
		this.numberOfLoans = numberOfLoans;
		this.gender = gender;
		this.address = address;
		this.postalCode = postalCode;
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

    public String getAddress() {
    	return address;
    }

    public void setAddress(String address) {
    	this.address = address;
    }

    public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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

    @Override
    public String toString() {
	return "Member [identifier=" + identifier + ", birthDate=" + birthDate + ", numberOfLoans=" + numberOfLoans + ", person=" + lastname + " " + firstname + " " + email + " " + gender + ", address=" + address + ", subscription=" + subscription
		+ "]";
    }
}
