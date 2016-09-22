
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

import model.User;
import model.UserDetailResponse;

// [START example]
@SuppressWarnings("serial")
public class SaveUserDataServlet extends HttpServlet {

  
  
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
    try{
    DataHandler datahandler = new DataHandler();
    UserDetailResponse userResponse = datahandler.storeData(sb.toString());
    if(userResponse.getSuccessCode().equalsIgnoreCase("11")){
    	resp.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
    }
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String json = ow.writeValueAsString(userResponse);
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
