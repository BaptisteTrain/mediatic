package fr.dta.mediatic.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	/* COLUMNS */
	
	@Column(name="address")
	private String address;
	
	@Column(name="postalCode", length=5)
	private String postalCode;
	
	@Column(name="city")
	private String city;

	
	/* CONSTRUCTORS */
	
	public Address() {

	}

	public Address(String address, String postalCode, String city) {
		super();
		this.address = address;
		this.postalCode = postalCode;
		this.city = city;
	}

	
	/* GETTERS AND SETTERS */
	
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
	
	

}
