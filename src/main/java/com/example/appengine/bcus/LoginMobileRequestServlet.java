
package com.example.appengine.bcus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import model.MobileNumberLoginResponse;
import model.OrderResponse;
import model.User;

// [START example]
@SuppressWarnings("serial")
public class LoginMobileRequestServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    PrintWriter out = resp.getWriter();
    out.println("Hello, world, Its a get");
  }
  
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    PrintWriter out = resp.getWriter();
   
    StringBuilder sb = new StringBuilder();
    BufferedReader reader = req.getReader();
    try {
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append('\n');
        }
    } finally {
        reader.close();
    }
    System.out.println(sb.toString());
    String mobileNumber = req.getParameter("mobileNumber");
    String otp = req.getParameter("otp");
    LoginModule login = new LoginModule();
    MobileNumberLoginResponse response = null;
    if(otp != null && !otp.equalsIgnoreCase(" ")){
    	response = login.loginMobileWithOTP(mobileNumber, otp);
    }
    else{
    	response = login.loginMobile(mobileNumber);
    }
    
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String json = ow.writeValueAsString(response);
    out.println(json);
  }
  
}
// [END example]
