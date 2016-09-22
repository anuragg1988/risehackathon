package com.example.appengine.bcus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import model.DBData;
import model.OrderResponse;
import model.User;

public class PaymentService {
	
	public OrderResponse processPayment(String mobileNumber,String otp){
		OrderResponse orderResponse;
		User user = DBData.getUserDetailsMap().get(mobileNumber.trim());
		if(user != null && user.isAuthenticated()){
			if(otp.trim().equalsIgnoreCase(user.getPin().trim())){
				HandleOrderRequest handleOrderRequest = new HandleOrderRequest();
				orderResponse= handleOrderRequest.makePayment(user.getCurrentOrder());
			}
			else{
				orderResponse= new OrderResponse(user.getCurrentOrder(),"Wrong OTP",false,"NA","bcusserver");
			}
		}
		else{
			orderResponse= new OrderResponse(null,"User is not authenticated",false,"NA","bcusserver");
		}
		if(user != null)
		sendCallBackTOBot(orderResponse,user.getCurrentOrder().getCallbackUrl()+orderResponse.getTransactionId()+","+orderResponse.getSuccessMessage());
		return orderResponse;
		
	}
	
	private void sendCallBackTOBot(OrderResponse orderResponse,String url){
		try{
		  String USER_AGENT = "Mozilla/5.0";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	

}
