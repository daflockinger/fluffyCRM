package controllers;

import com.google.inject.Inject;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.UserService;

@Security.Authenticated(Secure.class)
public class Users extends BaseController{
	
	@Inject UserService userService;

	public Result getUser(Long id){
		return ok();//ok(views.html.editUser.render(userService.getUserById(id),webJarAssets));
	}
	
	public Result getUserList(){
		return ok();//ok(views.html.editUser.render(userService.getAllUsers(),webJarAssets));
	}
	
	public Result saveUser(){
		Form<User> form = formFactory.form(User.class).bindFromRequest();
		
		userService.save(form.get());
		return ok();
	}
	
	public Result deleteUser(){
		Form<User> form = formFactory.form(User.class).bindFromRequest();
		
		userService.delete(form.get());
		return ok();
	}
}
