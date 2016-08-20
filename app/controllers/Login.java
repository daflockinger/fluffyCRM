package controllers;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;

import dtos.LoginCredentials;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.LoginService;

public class Login extends BaseController {

	@Inject
	LoginService loginService;
	
	private List<String> requiredFields = ImmutableList.of("user", "password");
	
	
	public Result showLoginForm() {
		return ok(views.html.login.render(webJarAssets, null));
	}

	
	public Result login() {
		JsonNode creds = request().body().asJson();
		return getLoginResult(creds);
	}
	
	public Result logout(){
		session().clear();
		return ok();
	}
	
	private Result getLoginResult(JsonNode json){
		if (jsonError.hasErrors(requiredFields, json)) {
			return jsonError.getErrorResult(requiredFields, json);
		}
		LoginCredentials credentials = Json.fromJson(json, LoginCredentials.class);
		if (!loginService.authenticate(credentials)) {
			return  unauthorized("Wrong user/password entered");
		}else{
			storeCredentialsInSession(credentials);
			return ok();//TODO maybe return credential stuff to auth for angular
		}
	}
	
	private void storeCredentialsInSession(LoginCredentials credentials){
		session().clear();
		session("user", credentials.getUser());
	}
}
