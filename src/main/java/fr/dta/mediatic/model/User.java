package fr.dta.mediatic.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

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

    @Column(name = "role")
    private Role role;

    @Embedded
    private Person person;

    /* CRONSTRUCTORS */

    public User() {

    }

    public User(String login, String password, Role role, String lastname, String firstname, String email, Gender gender) {

	this.login = login;
	this.password = password;
	this.role = role;
	this.person = new Person(lastname, firstname, email, gender);
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
