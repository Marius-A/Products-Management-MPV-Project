package helper;

import java.util.List;

import entities.LoginData;
import helper.sqlDatabase.SqlLoginDataHelper;

public class Login {
	static SqlLoginDataHelper service = new SqlLoginDataHelper();
	
    public static boolean authenticate(final String username, final String password) {
        
    	List<LoginData> users = service.getData() ; 
    	Boolean success = false; 	
    	//String df = MD5Encryption.encript("43544355J34B5H345BH43B5H3BH5B34UB5UJ34N5J34Njn34uj32n4j23");
    	final LoginData data = new LoginData(username, password);
		
		for (LoginData user : users) {
			if (user.getUsername().equals(data.getUsername()) && 
				user.getPassword().equals(MD5Encryption.encript(data.getPassword()))) {
				success = true;
			}
		}
		return success;
    }
}