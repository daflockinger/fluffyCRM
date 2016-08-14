package services;

import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.annotation.Transactional;
import com.google.inject.Inject;

import dtos.SearchParams;
import models.BaseModel;
import services.helper.ReflectionHelper;

public class BaseService<T extends BaseModel> {

	@Inject
	ReflectionHelper<T> reflectionHelper;

	@Transactional
	public T getById(Long id) {
		return Ebean.find(reflectionHelper.getClass(this), id);
	}

	@Transactional
	public List<T> getAll() {
		return Ebean.find(reflectionHelper.getClass(this)).findList();
	}

	@Transactional
	public List<T> getFiltered(SearchParams searchParams) {
		return Ebean.find(reflectionHelper.getClass(this)).where()
				.like(searchParams.getFilter(), "%" + searchParams.getSearchTerm() + "%").findList();
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
}
