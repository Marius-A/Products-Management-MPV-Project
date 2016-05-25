package helper.sqlDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entities.LoginData;

public class SqlLoginDataHelper {
	private ReadQuery database;
	
	public SqlLoginDataHelper(){
		this.database = new ReadQuery();
	}
	public ArrayList<LoginData> getData(){
		ArrayList<LoginData> usersList = new ArrayList<LoginData>();
		this.database = new ReadQuery();
		ResultSet rs;
		final String interogation = "SELECT D.Username,D.Password FROM LoginData AS D;";
		database.prepareStatement(interogation);
		database.executeQuery();
		rs = database.getResult();
		try {
			while(rs.next()){
				LoginData d = new LoginData(rs.getString("Username"),rs.getString("Password"));
				usersList.add(d);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		try {
			rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		return usersList;
	}
}
