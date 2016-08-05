package controllers;

import com.google.inject.Inject;

import dtos.LoginCredentials;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

public class Login extends Controller{
	
	@Inject FormFactory formFactory;
	@Inject WebJarAssets webJarAssets;

	public Result showLoginForm(){
		return ok(views.html.login.render(webJarAssets,null,null));
	}
	
	public Result login(){
		Form<LoginCredentials> loginForm = formFactory.form(LoginCredentials.class).bindFromRequest();
		
		if(loginForm.hasErrors() ){
			return badRequest(views.html.login.render(webJarAssets,loginForm, "Please enter user and password!"));
		}
			LoginCredentials credentials = loginForm.get();
			if(validate(credentials)){
				session().clear();
		        session("user", credentials.getUser());
				return redirect("/");
			}else{
				return badRequest(views.html.login.render(webJarAssets,loginForm, "Wrong user and password entered!"));
			}
	}
	
	private boolean validate(LoginCredentials credentials){
		return credentials.getUser().equals("flo") && credentials.getPassword().equals("flo");
	}
}
