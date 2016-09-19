package model;

import java.io.Serializable;

public class Account implements Serializable{

	private String name;
	private String accountNumber;
	private String cvv;
	private String expDate;
	private String bankName;
  public Account(String name, String accountNumber, String cvv, String expDate, String bankName) {
		super();
		this.name = name;
		this.accountNumber = accountNumber;
		this.cvv = cvv;
		this.expDate = expDate;
		this.bankName = bankName;
	}
  public String getName() {
		return name;
  }
  public String getAccountNumber() {
		return accountNumber;
  }
  public String getCvv() {
		return cvv;
  }
  public String getExpDate() {
		return expDate;
  }
  public String getBankName() {
		return bankName;
  }
  
  public Account() {
		
		// TODO Auto-generated constructor stub
	}
}
