package dtos;

import play.data.validation.Constraints.Required;

public class LoginCredentials {
	
	public LoginCredentials(String user, String password) {
		this.user = user;
		this.password = password;
	}
	
	@Required(message="No user name entered")
    protected String user;
	
	@Required(message="No password entered")
    protected String password;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
