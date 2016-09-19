package model;

public class MobileNumberLoginResponse {
	private User user;
	private String responseMessage;
	private String responseCode;
	public MobileNumberLoginResponse(User user, String responseMessage, String responseCode) {
		super();
		this.user = user;
		this.responseMessage = responseMessage;
		this.responseCode = responseCode;
	}
	public MobileNumberLoginResponse() {
		
	}
	public User getUser() {
		return user;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public String getResponseCode() {
		return responseCode;
	}
	
	
	
}
