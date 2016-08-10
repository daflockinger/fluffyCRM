package services;

import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.annotation.Transactional;

import models.User;

public class UserService {

	@Transactional
	public User getUserById(Long id) {
		User foundUser = User.find.byId(id);
		return foundUser != null ? foundUser : new User();
	}

	@Transactional
	public List<User> getAllUsers() {
		return User.find.all();
	}

	@Transactional
	public void save(User user) {
		if(user.id != null){
			User toUpdateUser = User.find.byId(user.id);
			toUpdateUser.email = user.email;
			toUpdateUser.password = user.password;
			toUpdateUser.save();
		}else{
			user.insert();
		}
	}
/*
	private boolean isUserExisting(User user) {
		return User.find.where().eq("user", user.user).findUnique() != null;
	}*/

	@Transactional
	public void delete(Long id) {
		User.find.byId(id).delete();
	}
}
