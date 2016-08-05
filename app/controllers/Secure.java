package controllers;

import com.google.inject.Inject;

import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

public class Secure extends Security.Authenticator{
	
	 @Override
	    public String getUsername(Context ctx) {
	        return ctx.session().get("user");
	    }

	    @Override
	    public Result onUnauthorized(Context ctx) {
	        return redirect("/login");
	    }
}
