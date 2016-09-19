package model;

public class OrderResponse {
Order order;
private String successMessage;
private boolean successCode;
private String transactionId;
private String sourceKey;
public OrderResponse(Order order, String successMessage, boolean successCode, String transactionId,String sourceKey) {
	super();
	this.order = order;
	this.successMessage = successMessage;
	this.successCode = successCode;
	this.transactionId = transactionId;
	this.sourceKey = sourceKey;

}
public OrderResponse() {
	
	// TODO Auto-generated constructor stub
}
public Order getOrder() {
	return order;
}
public String getSuccessMessage() {
	return successMessage;
}
public boolean getSuccessCode() {
	return successCode;
}
public String getTransactionId() {
	return transactionId;
}
public String getSourceKey() {
	return sourceKey;
}


}
