package fr.dta.mediatic.user.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import fr.dta.mediatic.model.Gender;
import fr.dta.mediatic.model.Person;

@Entity
@Table(name="user")
public class User {
	
	/* COLUMN */
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="login",unique=true)
	private String login;
	
	@NotBlank
	@Column(name="password")
	private String password;
	
	@Column(name="role")
	private Role role;
	
	@Embedded
	private Person person;

	public User() {

	}

	public User(String login, String password, Role role, String lastname, String firstname, String email, Gender gender) {

		this.login = login;
		this.password = password;
		this.role = role;
		this.person = new Person(lastname, firstname, email, gender);
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

	public Role getRole() {
		
		return role;
	}

	public void setRole(Role role) {
		
		this.role = role;
	}

	public Person getPerson() {
		
		return person;
	}

	public void setPerson(Person person) {
		
		this.person = person;
	}
	
	

}
