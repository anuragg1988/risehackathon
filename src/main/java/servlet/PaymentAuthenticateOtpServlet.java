
package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import com.example.appengine.bcus.DataHandler;
import com.example.appengine.bcus.PaymentService;

import model.OrderResponse;
import model.User;
import model.UserDetailResponse;

// [START example]
@SuppressWarnings("serial")
public class PaymentAuthenticateOtpServlet extends HttpServlet {


  
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	  resp.setContentType("application/json");
	  
  try{
    PaymentService paymentService = new PaymentService();
    String mobileNumber = req.getParameter("mobileNumber");
    String otp = req.getParameter("otp");
    OrderResponse orderResponse = paymentService.processPayment(mobileNumber, otp);
    if(!orderResponse.getSuccessCode()){
    	resp.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
    }
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String json = ow.writeValueAsString(orderResponse);
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
