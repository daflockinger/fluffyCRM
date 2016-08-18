package dtos;

import models.User;

public class UserDTO extends EntityDTO<User>{
	public UserDTO(User user){
		setEntity(user);
	}
}
