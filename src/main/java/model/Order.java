package model;

public class Order {
	private String merchantAccountNumber;
	private String merchantId;
	private Amount amount;
	private String productId;
	private String orderId;
	private String mobileNumber;
	public Order(String merchantAccountNumber, String merchantId, Amount amount, String productId, String orderId,String mobileNumber) {
		super();
		this.merchantAccountNumber = merchantAccountNumber;
		this.merchantId = merchantId;
		this.amount = amount;
		this.productId = productId;
		this.orderId = orderId;
		this.mobileNumber= mobileNumber;
	}
	public String getMerchantAccountNumber() {
		return merchantAccountNumber;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public Amount getAmount() {
		return amount;
	}
	public String getProductId() {
		return productId;
	}
	public String getOrderId() {
		return orderId;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public Order() {
		
		// TODO Auto-generated constructor stub
	}
	

}
