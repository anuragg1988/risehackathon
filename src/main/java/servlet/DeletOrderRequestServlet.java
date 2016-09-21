
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

import model.OrderResponse;
import model.User;

// [START example]
@SuppressWarnings("serial")
public class DeletOrderRequestServlet extends HttpServlet {

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
    DataHandler dataHandler = new DataHandler();
    User user = dataHandler.deleteUser(mobileNumber);
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String json = ow.writeValueAsString(user);
    out.println(json);
  }
  
}
// [END example]