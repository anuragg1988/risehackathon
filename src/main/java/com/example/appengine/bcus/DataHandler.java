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
		storeUser(user);
		UserDetailResponse userDetailResponse = new UserDetailResponse(user,"SUCCESS","00","bcus");
		return userDetailResponse;
		
	}
	
	public void storeUser(User user){
		 DBData dbData = new DBData();
		 if(dbData.getUserDetailsMap().get(user.getMobileNumber().trim()) == null){
			 dbData.getUserDetailsMap().put(user.getMobileNumber().trim(), user);
		 }
		 else{
			 User userDetail = dbData.getUserDetailsMap().get(user.getMobileNumber().trim());
			 userDetail.getAccounts().addAll(user.getAccounts());
			 dbData.getUserDetailsMap().put(user.getMobileNumber().trim(), userDetail);
		 }
		 
			    
	}
	
	public User deleteUser(String mobileNumber){
		 DBData dbData = new DBData();
		 User user = dbData.getUserDetailsMap().get(mobileNumber.trim());
		 if(user != null){
			 dbData.getUserDetailsMap().remove(mobileNumber);
		 }
		 
		return user;
		 
			    
	}
	public User getUserDetail(String mobileNumber){
		DBData dbData = new DBData();
		return dbData.getUserDetailsMap().get(mobileNumber.trim());
		
			
	}
	
	public void storeUserWithOTP(String mobileNumber){
		 DBData dbData = new DBData();
		 dbData.getUserDetailsMap().put(mobileNumber, new User(null,mobileNumber));
	}
	
	
}
