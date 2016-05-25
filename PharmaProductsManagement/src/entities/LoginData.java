package entities;

public class LoginData {
	private String username;
	private String password;
	
	public LoginData(final String username , final String password){
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	
}
