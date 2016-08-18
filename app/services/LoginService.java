package services;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.annotation.Transactional;

import dtos.LoginCredentials;
import models.User;

public class LoginService {
	
	@Transactional
	public boolean authenticate(LoginCredentials credentials){
		return Ebean.find(User.class).where()
				 		.and( Expr.eq("user", credentials.getUser()), 
				 			  Expr.eq("password", credentials.getPassword()) )
				 		.findUnique() != null;
	}
}
