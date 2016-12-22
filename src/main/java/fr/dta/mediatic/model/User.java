package fr.dta.mediatic.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    /* COLUMNS */

    @Length(min = 4)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    @Column(name = "login", unique = true)
    private String login;

    @NotBlank
    @Length(min = 8)
    @Column(name = "password")
    private String password;
    
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

    @Column(name = "role")
    private Role role;

    /* CRONSTRUCTORS */

    public User() {

    }

    public User(String login, String password, String lastname, String firstname, String email, Role role) {
		super();
		this.login = login;
		this.password = password;
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.role = role;
	}

	/* GETTERS AND SETTERS */

    public Long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getLogin() {

	return login;
    }

    public void setLogin(String login) {

	this.login = login;
    }

    public String getPassword() {

	return password;
    }

    public void setPassword(String password) {

	this.password = password;
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

	public Role getRole() {
		return role;
    }

    public void setRole(Role role) {
    	this.role = role;
    }
}
