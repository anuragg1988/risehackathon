package com.example.appengine.bcus;

import java.security.AccessController;
import java.security.PrivilegedAction;

import org.codehaus.jackson.map.ObjectMapper;

import com.realexpayments.remote.sdk.RealexClient;
import com.realexpayments.remote.sdk.domain.Card;
import com.realexpayments.remote.sdk.domain.Cvn;
import com.realexpayments.remote.sdk.domain.payment.AutoSettle;
import com.realexpayments.remote.sdk.domain.payment.PaymentRequest;
import com.realexpayments.remote.sdk.domain.payment.PaymentResponse;
import com.realexpayments.remote.sdk.http.HttpConfiguration;

import model.Order;
import model.OrderResponse;
import model.User;

public class HandleOrderRequest {
private Order order;
public OrderResponse processOrder(String requestString){
	
	ObjectMapper mapper = new ObjectMapper();
	
	try {
		 order = mapper.readValue(requestString, Order.class);
		 
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	return makePayment(order);
}

public OrderResponse makePayment(Order order){
	DataHandler dataHandler = new DataHandler();
	
	User user = dataHandler.getUserDetail(order.getMobileNumber());
	 OrderResponse orderResponse=null;
	if(user != null){
    Card card = new Card()
            .addExpiryDate(user.getAccounts().get(0).getExpDate())
            .addNumber(user.getAccounts().get(0).getAccountNumber()) //SUCCESS
            //addNumber("4000120000001154") //FAILURE
            .addType(Card.CardType.VISA)
            .addCardHolderName(user.getAccounts().get(0).getName())
            .addCvn(user.getAccounts().get(0).getCvv())
            .addCvnPresenceIndicator(Cvn.PresenceIndicator.CVN_PRESENT);
    Double dAmount = Double.parseDouble(order.getAmount().getAmount().trim());
    Long amount = dAmount.longValue();
    final PaymentRequest request = new PaymentRequest()
            .addAccount("yourAccount")
            .addMerchantId("realexsandbox")
            .addType(PaymentRequest.PaymentType.AUTH)
            .addAmount(amount)
            .addCurrency(order.getAmount().getCurrency())
            .addCard(card)
            .addAutoSettle(new AutoSettle().addFlag(AutoSettle.AutoSettleFlag.TRUE));

    final HttpConfiguration httpConfiguration = new HttpConfiguration();
    httpConfiguration.setEndpoint("https://test.realexpayments.com/epage-remote.cgi");
    try
    {
    
    
    

    //RealexClient client = new RealexClient("shared secret");
    final PaymentResponse[] response = new PaymentResponse[1];
    
    AccessController.doPrivileged(new PrivilegedAction<String>() {
    	
        public String run() {
        	final RealexClient client = new RealexClient("Po8lRRT67a", httpConfiguration);
            response[0] = client.send(request);
            return null;
        }
    });

    System.out.println(response[0].getMessage());
    System.out.println(response[0].isSuccess());
    System.out.println(response[0].getHash());
    System.out.println(response[0].getPaymentsReference());
    System.out.println(response[0].getResult());
    String messageToDisplay="not succesfull - ";
    if(response[0].getResult().equalsIgnoreCase("00"))
    {
    	messageToDisplay = "succesfull!!";
    }
    else{
    	messageToDisplay += " response[0].getMessage()";
    }
     orderResponse = new OrderResponse(order,messageToDisplay,response[0].isSuccess(),response[0].getPaymentsReference(),"bcusserver");
    }
    catch(Exception e){
    	orderResponse = new OrderResponse(order,"FAIL "+e.getMessage(),false,"NA","bcusserver");	
    	e.printStackTrace();
    }
    
    }
	else{
		  orderResponse = new OrderResponse(order,"FAIL User does not exist",false,"NA","bcusserver");	
	}
    return orderResponse;
}

}
