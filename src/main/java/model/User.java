package model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable{
	private List<Account> accounts;
	private String mobileNumber;
	private String pin;
  public User(List<Account> accounts, String mobileNumber) {
		super();
		this.accounts = accounts;
		this.mobileNumber = mobileNumber;
  }
  public List<Account> getAccounts() {
		return accounts;
  }
  public String getMobileNumber() {
		return mobileNumber;
  }
public User() {
	
	// TODO Auto-generated constructor stub
}
public String getPin() {
	return pin;
}
public void setPin(String pin) {
	this.pin = pin;
}


  
}
