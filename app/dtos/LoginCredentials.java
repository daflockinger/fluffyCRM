package dtos;

import play.data.validation.Constraints.Required;

public class LoginCredentials {
	@Required(message="No user name entered")
    protected String user;
	@Required(message="No password entered")
    protected String password;
	
    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setPassword(String password) {
    	this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
