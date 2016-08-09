package services;

import java.util.List;

import com.avaje.ebean.Ebean;

import models.User;

public class UserService {

	
	public User getUserById(Long id){
		return User.find.byId(id);
	}
	
	public List<User> getAllUsers(){
		return User.find.all();
	}
	
	public void save(User user){
		Ebean.save(user);
	}
	
	public void delete(User user){
		Ebean.delete(User.find.byId(user.id));
	}
}
