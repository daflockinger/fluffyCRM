package dtos;

import java.util.List;

import models.User;

public class UsersDTO extends SearchEntities<User>{

	public UsersDTO(List<User> entities, List<String> filters, SearchParams searchParams) {
		super(entities, filters, searchParams);
	}
}
