package com.example.appengine.bcus;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import model.DBData;
import model.User;
import model.UserDetailResponse;

public class DataHandler {

	public static String storedFilePath="UserDetails.txt";
	private String connectionUrl;
	
	public UserDetailResponse storeData(String requestString){
		
		ObjectMapper mapper = new ObjectMapper();
		User user = null;
		try {
			 user = mapper.readValue(requestString, User.class);
			 
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		UserDetailResponse userDetailResponse = new UserDetailResponse(user,"FAIL Not Authenticated","11","bcus");
		if(DBData.getUserDetailsMap().get(user.getMobileNumber().trim()).isAuthenticated())
		{
		storeUser(user);
		 userDetailResponse = new UserDetailResponse(user,"SUCCESS","00","bcus");
		
		}
		
		return userDetailResponse;
		
	}
	
	public void storeUser(User user){
		 if(DBData.getUserDetailsMap().get(user.getMobileNumber().trim()) == null){
			 DBData.getUserDetailsMap().put(user.getMobileNumber().trim(), user);
		 }
		 else{
			 User userDetail = DBData.getUserDetailsMap().get(user.getMobileNumber().trim());
			 userDetail.getAccounts().addAll(user.getAccounts());
			 DBData.getUserDetailsMap().put(user.getMobileNumber().trim(), userDetail);
		 }
		 
			    
	}
	
	public User deleteUser(String mobileNumber){
		
		 User user = DBData.getUserDetailsMap().get(mobileNumber.trim());
		 if(user != null){
			 DBData.getUserDetailsMap().remove(mobileNumber);
		 }
		 
		return user;
		 
			    
	}
	public User getUserDetail(String mobileNumber){
		
		return DBData.getUserDetailsMap().get(mobileNumber.trim());
		
			
	}
	
	public void storeUserWithOTP(String mobileNumber){
		 
		 DBData.getUserDetailsMap().put(mobileNumber, new User(null,mobileNumber,null));
	}
	
	
}
