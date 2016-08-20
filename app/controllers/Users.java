package controllers;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;

import dtos.SearchParams;
import dtos.UserDTO;
import dtos.UsersDTO;
import models.User;
import play.libs.Json;
import play.mvc.Result;
import services.UserService;

//@Security.Authenticated(Secure.class)
public class Users extends BaseController {

	@Inject
	UserService userService;

	private List<String> filters = ImmutableList.of("user", "email");
	private List<String> requiredFields = ImmutableList.of("user", "password", "email");

	public Result getUser(Long id) {
		User user = userService.getById(id);

		if (user == null) {
			user = new User();
		}
		return ok(Json.toJson(new UserDTO(user)));
	}

	public Result getUserList() {
		UsersDTO usersDTO = new UsersDTO(userService.getAll(), filters, new SearchParams());

		return ok(Json.toJson(usersDTO));
	}

	public Result saveUser() {
		JsonNode json = request().body().asJson();

		if (jsonError.hasErrors(requiredFields, json)) {
			return jsonError.getErrorResult(requiredFields, json);
		} else {
			userService.save(Json.fromJson(json, User.class));
			return created(json);
		}
	}

	public Result deleteUser(Long id) {
		userService.delete(id);
		return ok();
	}

	public Result search() {
		JsonNode json = request().body().asJson();

		if (jsonError.hasErrors(new ArrayList<>(), json)) {
			return jsonError.getErrorResult(new ArrayList<>(), json);
		} else {
			SearchParams params = Json.fromJson(json, SearchParams.class);
			UsersDTO userDto = new UsersDTO(userService.getFiltered(params), filters, params);
			return ok(Json.toJson(userDto));
		}
	}
}
