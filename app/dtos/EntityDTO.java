package dtos;

import models.BaseModel;

public class EntityDTO<T extends BaseModel> {

	private T entity;

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}
	
}
