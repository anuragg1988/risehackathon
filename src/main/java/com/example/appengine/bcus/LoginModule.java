package com.example.appengine.bcus;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import model.DBData;
import model.MobileNumberLoginResponse;
import model.User;

public class LoginModule {

	public MobileNumberLoginResponse loginMobile(String mobileNumber){
		 DBData dbData = new DBData();
		 MobileNumberLoginResponse mobileNoResponse = null;
		 User user = dbData.getUserDetailsMap().get(mobileNumber.trim());
		 if(user != null && user.isAuthenticated()){

			 mobileNoResponse = new MobileNumberLoginResponse(user,"SUCCESS","00");
		 }
		 else{
			 user=new User();
			 String pinSent = sendOtp(mobileNumber.trim(),false,"OTP for registration login to bcus server is ");
			 user.setPin(pinSent);
			 mobileNoResponse = new MobileNumberLoginResponse(user,"NEW_USER Please send OTP","11");
		 }
		 
		 return mobileNoResponse;
	}
	
	public MobileNumberLoginResponse loginMobileWithOTP(String mobileNumber,String otp ){
		 
		 MobileNumberLoginResponse mobileNoResponse = null;
		 User user = DBData.getUserDetailsMap().get(mobileNumber.trim());
		 if(user == null)
		 {
			 mobileNoResponse = new MobileNumberLoginResponse(user,"Fail, user does not exist","11");
		 }
		 else if(user != null && user.isAuthenticated()){
			 mobileNoResponse = new MobileNumberLoginResponse(user,"SUCCESS User Already exist","00");
		 }
		 
		 else{
			 String otpSent = user.getPin();
			 
			 if(otp.trim().equalsIgnoreCase(otpSent.trim())){
				 user.setAuthenticated(true);
				 user.setPin(""); // de-neutralise pin
				 DBData.getUserDetailsMap().put(mobileNumber.trim(),user); 
			 mobileNoResponse = new MobileNumberLoginResponse(user,"SUCCESS User Saved and Logged in","00");
			 }
			 else
			 mobileNoResponse = new MobileNumberLoginResponse(user,"Fail, wrong OTP entered","11");
		 }
		 
		 return mobileNoResponse;
	}
	
	public String sendOtp(String mobileNumber,boolean isAuthenticated,String messageString){
		Twilio.init("ACd83e6d73d0c73c89b9a8010944eadef4", "3d4829cd9e8f8975e9a310b71bbc6fcc");
		String pin = ""+((int)(Math.random()*9000)+1000);
		User user;
		if(isAuthenticated){
			 user = DBData.getUserDetailsMap().get(mobileNumber.trim());;
		}
		else{
			 user = new User();	
		}
		
		user.setPin(pin);
		user.setAuthenticated(isAuthenticated);
		DBData.getUserDetailsMap().put(mobileNumber.trim(),user);
		if(!mobileNumber.trim().equalsIgnoreCase("7030615522") && !mobileNumber.trim().equalsIgnoreCase("8939563581")){
			
			Message message = Message
			        .create(new PhoneNumber("+91"+mobileNumber), new PhoneNumber("+13107766392"),
			                messageString+pin)
			        .execute();
		}
		else{
			pin = "1234";
		}
		
		/*Message message = Message
		        .create(new PhoneNumber("+91"+mobileNumber), new PhoneNumber("+13107766392"),
		                messageString+pin)
		        .execute();*/
		
		return pin;
		
		
	}
}
