
package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import com.example.appengine.bcus.LoginModule;

import model.MobileNumberLoginResponse;
import model.OrderResponse;
import model.User;

// [START example]
@SuppressWarnings("serial")
public class LoginMobileRequestServlet extends HttpServlet {

 
  
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	  resp.setContentType("application/json");
	 
   
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
    try{
    if(otp != null && !otp.equalsIgnoreCase(" ")){
    	response = login.loginMobileWithOTP(mobileNumber, otp);
    }
    else{
    	response = login.loginMobile(mobileNumber);
    }
    if(response.getResponseCode().equalsIgnoreCase("11")){
    	resp.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
    }
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String json = ow.writeValueAsString(response);
    PrintWriter out = resp.getWriter();
    out.println(json);
    }
    catch(Exception ex){
    	resp.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
    	PrintWriter out = resp.getWriter();
    	out.println(ex);
    }
  }
  
}
// [END example]
