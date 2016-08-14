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
		User user = userService.getById(id);
		
		if(user==null){
			user = new User();
		}
		
		return ok(views.html.edit.forms.userForm.render(user));
	}
	
	public Result getUserList(){
		return ok(views.html.edit.userList.render(userService.getAll()));
	}
	
	public Result saveUser(){
		Form<User> form = formFactory.form(User.class).bindFromRequest();
		
		userService.save(form.get());
		return ok();
	}
	
	public Result deleteUser(Long id){
		userService.delete(id);
		return ok();
	}
}
