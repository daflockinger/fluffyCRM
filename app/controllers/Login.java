package controllers;

import com.google.inject.Inject;

import dtos.LoginCredentials;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import services.LoginService;

public class Login extends BaseController {

	@Inject
	LoginService loginService;
	
	
	public Result showLoginForm() {
		return ok(views.html.login.render(webJarAssets, null));
	}

	
	public Result login() {
		Form<LoginCredentials> loginForm = formFactory.form(LoginCredentials.class).bindFromRequest();

		return getLoginResult(loginForm);
	}
	
	public Result logout(){
		session().clear();
		return redirect("/login");
	}
	
	private Result getLoginResult(Form<LoginCredentials> loginForm){
		if (loginForm.hasErrors()) {
			return badRequest(views.html.login.render(webJarAssets, loginForm));
		}
		LoginCredentials credentials = loginForm.get();
		if (!loginService.authenticate(credentials)) {
			return badRequest(views.html.login.render(webJarAssets, loginForm));
		}else{
			storeCredentialsInSession(credentials);
			return redirect("/");
		}
	}
	
	private void storeCredentialsInSession(LoginCredentials credentials){
		session().clear();
		session("user", credentials.getUser());
	}
}
