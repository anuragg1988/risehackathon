package model;

public class UserDetailResponse {
User user;
private String successMessage;
private String successCode;
private String sourceKey;

public UserDetailResponse(User user, String successMessage, String successCode, String sourceKey) {
	
	this.user = user;
	this.successMessage = successMessage;
	this.successCode = successCode;
	this.sourceKey = sourceKey;
}

public UserDetailResponse() {
	
	// TODO Auto-generated constructor stub
}

public User getUser() {
	return user;
}

public String getSuccessMessage() {
	return successMessage;
}

public String getSuccessCode() {
	return successCode;
}

public String getSourceKey() {
	return sourceKey;
}



}
