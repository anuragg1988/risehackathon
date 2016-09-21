package model;

public class Order {
	private String merchantAccountNumber;
	private String merchantId;
	private Amount amount;
	private String productId;
	private String orderId;
	private String mobileNumber;
	private String status;
	private String callbackUrl;
	public Order(String merchantAccountNumber, String merchantId, Amount amount, String productId, String orderId,String mobileNumber,String status) {
		super();
		this.merchantAccountNumber = merchantAccountNumber;
		this.merchantId = merchantId;
		this.amount = amount;
		this.productId = productId;
		this.orderId = orderId;
		this.mobileNumber= mobileNumber;
		this.status=status;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCallbackUrl() {
		return callbackUrl;
	}
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}
	
	

}
