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
		 if(user != null){
			 mobileNoResponse = new MobileNumberLoginResponse(user,"SUCCESS","00");
		 }
		 else{
			 mobileNoResponse = new MobileNumberLoginResponse(user,"NEW_USER Please send OTP","11");
			 sendOtp(mobileNumber.trim());
		 }
		 
		 return mobileNoResponse;
	}
	
	public MobileNumberLoginResponse loginMobileWithOTP(String mobileNumber,String otp ){
		 DBData dbData = new DBData();
		 MobileNumberLoginResponse mobileNoResponse = null;
		 User user = dbData.getUserDetailsMap().get(mobileNumber.trim());
		 if(user != null){
			 mobileNoResponse = new MobileNumberLoginResponse(user,"SUCCESS User Already exist","00");
		 }
		 else{
			 String otpSent = user.getPin();
			 
			 if(otp.trim().equalsIgnoreCase(otpSent.trim()))
			 mobileNoResponse = new MobileNumberLoginResponse(user,"SUCCESS User Saved and Logged in","00");
			 else
			 mobileNoResponse = new MobileNumberLoginResponse(user,"Fail, wrong OTP entered","11");
		 }
		 
		 return mobileNoResponse;
	}
	
	private void sendOtp(String mobileNumber){
		Twilio.init("ACd83e6d73d0c73c89b9a8010944eadef4", "3d4829cd9e8f8975e9a310b71bbc6fcc");
		String pin = ""+((int)(Math.random()*9000)+1000);
		DBData dbData = new DBData();
		User user = new User();
		user.setPin(pin);
		
		dbData.getUserDetailsMap().put(mobileNumber.trim(),user);
		Message message = Message
		        .create(new PhoneNumber("+91"+mobileNumber), new PhoneNumber("+13107766392"),
		                "OTP for registration to bcus server is "+pin)
		        .execute();
	}
}
