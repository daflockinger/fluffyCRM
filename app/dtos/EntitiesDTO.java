package dtos;

import java.util.List;

import models.BaseModel;

public abstract class EntitiesDTO<T extends BaseModel> {

	private List<T> entities;

	public List<T> getEntities() {
		return entities;
	}

	public void setEntities(List<T> entities) {
		this.entities = entities;
	}
	
}
