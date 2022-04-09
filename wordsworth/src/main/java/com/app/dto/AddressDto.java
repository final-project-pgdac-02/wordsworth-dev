package com.app.dto;


public class AddressDto {
	public Integer addressId;
	private String detailedAddress;
	private String city;
	private String addressName;
	private String state;
	private String country;
	private String pinCode;
		
	
	public AddressDto() {
		super();
	}

	public AddressDto(Integer addressId, String detailedAddress, String city, String addressName, String state,
			String country, String pinCode) {
		super();
		this.addressId = addressId;
		this.detailedAddress = detailedAddress;
		this.city = city;
		this.addressName = addressName;
		this.state = state;
		this.country = country;
		this.pinCode = pinCode;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
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

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
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
		return "AddressDto [addressId=" + addressId + ", detailedAddress=" + detailedAddress + ", city=" + city
				+ ", addressName=" + addressName + ", state=" + state + ", country=" + country + ", pinCode=" + pinCode
				+ "]";
	}

}
