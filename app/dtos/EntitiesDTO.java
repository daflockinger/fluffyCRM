package dtos;

import java.util.List;

import models.BaseModel;
import play.data.validation.Constraints.Required;

public abstract class EntitiesDTO<T extends BaseModel> {

	@Required
	private List<T> entities;

	public List<T> getEntities() {
		return entities;
	}

	public void setEntities(List<T> entities) {
		this.entities = entities;
	}
	
}
