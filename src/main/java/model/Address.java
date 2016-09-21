package model;

public class Address {
	
	private String houseNumber;
	private String society;
	private String Street;
	private String city;
	private String state;
	private String country;
	private String pinCode;
	public Address(String houseNumber, String society, String street, String city, String state, String country,
			String pinCode) {
		super();
		this.houseNumber = houseNumber;
		this.society = society;
		Street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pinCode = pinCode;
	}
	public Address() {
		
		// TODO Auto-generated constructor stub
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getSociety() {
		return society;
	}
	public void setSociety(String society) {
		this.society = society;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
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
	
	
	
	

}
