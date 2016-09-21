package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{
	private List<Account> accounts= new ArrayList();
	private String mobileNumber;
	private String pin;
	private boolean authenticated;
	private List<Address> addressList= new ArrayList();
	private List<Order> previousOrderList = new ArrayList();
	private Order currentOrder ;
  public User(List<Account> accounts, String mobileNumber,List<Address> address) {
		super();
		this.accounts = accounts;
		this.mobileNumber = mobileNumber;
		this.addressList = address;
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
public boolean isAuthenticated() {
	return authenticated;
}
public void setAuthenticated(boolean authenticated) {
	this.authenticated = authenticated;
}
public List<Address> getAddressList() {
	return addressList;
}
public void setAddressList(List<Address> addressList) {
	this.addressList = addressList;
}
public List<Order> getOrderList() {
	return previousOrderList;
}
public void addOrderToList(Order order) {
	this.previousOrderList.add(order);
}
public Order getCurrentOrder() {
	return currentOrder;
}
public void setCurrentOrder(Order currentOrder) {
	this.currentOrder = currentOrder;
}




  
}
