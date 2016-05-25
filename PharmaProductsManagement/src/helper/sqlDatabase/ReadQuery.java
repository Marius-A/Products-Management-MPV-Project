package helper.sqlDatabase;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ReadQuery {  
    private Connection conn;
    private ResultSet result;
    private PreparedStatement ps;
    
	//CONSDTRUCTORS
    public ReadQuery(){

    	Properties props = new Properties();
    	InputStream instr = getClass().getResourceAsStream("SqlDBConfig.properties");
        try {
			props.load(instr);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    	try {
			instr.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    	
    	final String driver = props.getProperty("driver.name");
    	final String databaseName = props.getProperty("database.name");
    	final String url = props.getProperty("server.name").concat(databaseName);
    	final String user = props.getProperty("user.name");
    	final String password = props.getProperty("user.password");
    	try {
    		Class.forName(driver);
    		conn = DriverManager.getConnection(url,user,password);
    		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(new JFrame(),
				    "Could not establish a connection to the database.",
				    "SQL error",
				    JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
    	
    	ps = null;
    }
    public void prepareStatement(final String query){
    	try {
			ps = conn.prepareStatement(query);
		} catch (SQLException e) {	
			System.out.println(e.getMessage());
		}
    }
    public void setInteger(final int pos , final int val){
    	try {
			ps.setInt(pos, val);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
    }
    public void setFloat(final int pos , final float val){
    	try {
			ps.setFloat(pos, val);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
    }
    @SuppressWarnings("deprecation")
	public void setDate(final int pos , final Date val){
    	int day = val.getDay();
		int month = val.getMonth();
		int year = val.getYear();	
    	java.sql.Date data = new java.sql.Date(year,month,day);
    	try {
			ps.setDate(pos,data);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
    }
    public void setString(final int pos , final String val){
    	try {
			ps.setString(pos, val);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
    }
    public void executeQuery(){
    	try {
			this.result = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    public void executeUpdate(){
    	try {
			ps.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
    }
    public void closeDatabase(){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }
	//GETTERS AND SETTERS
	public ResultSet getResult() {
		return result;
	}
}
