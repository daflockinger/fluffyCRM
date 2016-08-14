package services;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.annotation.Transactional;

import models.BaseModel;
import play.Logger;

public class BaseService<T extends BaseModel> {

	@Transactional
	public T getById(Long id) {
		return Ebean.find(getModelClass(), id);
	}

	@Transactional
	public List<T> getAll() {
		return Ebean.find(getModelClass()).findList();
	}

	@Transactional
	public void save(T entity) {
		if (entity.id != null) {
			Ebean.update(entity);
		} else {
			Ebean.insert(entity);
		}
	}

	@Transactional
	public void delete(Long id) {
		Ebean.delete(getById(id));
	}

	@SuppressWarnings("unchecked")
	private Class<T> getModelClass() {
		Class<T> entityClass = null;
		
		try {
			entityClass = (Class<T>) Class.forName(
					((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName());
		} catch (ClassNotFoundException e) {
			Logger.error("Model not found", e);
		}

		return entityClass;
	}
}
