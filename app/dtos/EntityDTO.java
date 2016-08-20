package dtos;

import models.BaseModel;
import play.data.validation.Constraints.Required;

public class EntityDTO<T extends BaseModel> {

	@Required
	private T entity;

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}
	
}
