package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "addresses")
public class Address extends BaseEntity {
	
	
	@Column(name="detailed_address", length=200)
	@NotBlank
	private String detailedAddress;
	
	@Column(length = 30)
	@NotBlank
	private String city;
	
	@Column(length = 50)
	@NotBlank
	private String addressName;
	
	@Column(length = 30)
	@NotBlank
	private String state;
	
	@Column(length = 30)
	@NotBlank
	private String country;
	
	@Column(length = 6, name="pin_code")
	@NotBlank
	@Length(min = 6, max = 6)
	private String pinCode;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
	public Address() {
		super();
	}
	
	
	
	
	
	public Address( String detailedAddress,  String city,  String addressName,
			 String state,  String country,  String pinCode) {
		super();
		this.detailedAddress = detailedAddress;
		this.city = city;
		this.addressName = addressName;
		this.state = state;
		this.country = country;
		this.pinCode = pinCode;
	}





	public User getUser() {
		return user;
	}




	public String getAddressName() {
		return addressName;
	}




	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public String getDetailedAddress() {
		return detailedAddress;
	}


	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}


	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
	public String getPinCode() {
		return pinCode;
	}




	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}




	@Override
	public String toString() {
		return "Address [addressName = "+addressName+" city=" + city + ", state=" + state + ", country=" + country + ", pinCode=" + pinCode + "]";
	}
	
	
	
}
