package model;

import java.util.HashMap;
import java.util.Map;

public class DBData {
	
	
	private static Map<String,User> userDetailsMap = new HashMap();
	
	

	public  static Map<String, User> getUserDetailsMap() {
		return userDetailsMap;
	}

	
	
	
	
	

}
