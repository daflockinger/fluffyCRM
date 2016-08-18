package controllers;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;

import dtos.SearchParams;
import dtos.UserDTO;
import dtos.UsersDTO;
import models.User;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;
import play.twirl.api.Html;
import services.UserService;

@Security.Authenticated(Secure.class)
public class Users extends BaseController {

	@Inject
	UserService userService;

	public Result getUser(Long id) {
		User user = userService.getById(id);

		if (user == null) {
			user = new User();
		}
		return ok(views.html.edit.forms.userForm.render(new UserDTO(user)));
	}

	public Result getUserList() {
		UsersDTO usersDTO = new UsersDTO(userService.getAll(), getFilters(), new SearchParams());

		return ok(views.html.edit.userList.render(usersDTO));
	}

	public Result saveUser() {
		Form<User> form = formFactory.form(User.class).bindFromRequest();

		userService.save(form.get());
		return ok();
	}

	public Result deleteUser(Long id) {
		userService.delete(id);
		return ok();
	}

	public Result search() {
		Form<SearchParams> form = formFactory.form(SearchParams.class).bindFromRequest();
		UsersDTO usersDto = new UsersDTO(userService.getFiltered(form.get()), getFilters(), form.get());
		return ok(views.html.edit.userList.render(usersDto));
	}

	private List<String> getFilters() {
		return ImmutableList.of("user", "email");
	}
}
