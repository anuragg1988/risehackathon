
package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import com.example.appengine.bcus.HandleOrderRequest;

import model.OrderResponse;
import model.User;

// [START example]
@SuppressWarnings("serial")
public class GetOrderRequestServlet extends HttpServlet {

  
  
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
    try{
    System.out.println(sb.toString());
    HandleOrderRequest orderRequest = new HandleOrderRequest();
    OrderResponse response = orderRequest.processOrder(sb.toString());
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
