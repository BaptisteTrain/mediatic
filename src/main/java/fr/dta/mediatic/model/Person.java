package fr.dta.mediatic.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
public class Person {

    /* COLUMNS */

    @NotBlank
    @Column(name = "lastname")
    private String lastname;

    @NotBlank
    @Column(name = "firstname")
    private String firstname;

    @NotBlank
    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private Gender gender;

    /* CONSTRUCTORS */

    public Person() {
    }

    public Person(String lastname, String firstname, String email, Gender gender) {
	this.lastname = lastname;
	this.firstname = firstname;
	this.email = email;
	this.gender = gender;
    }

    /* GETTERS AND SETTERS */

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

    public Gender getGender() {
	return gender;
    }

    public void setGender(Gender gender) {
	this.gender = gender;
    }

    @Override
    public String toString() {
	return "Person [lastname=" + lastname + ", firstname=" + firstname + ", email=" + email + ", gender=" + gender + "]";
    }

}
