package services;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.annotation.Transactional;
import com.google.inject.Inject;

import dtos.SearchParams;
import models.BaseModel;
import services.helper.ReflectionHelper;

public abstract class BaseService<T extends BaseModel> {

	@Inject
	ReflectionHelper<T> reflectionHelper;

	public void setReflectionHelper(ReflectionHelper<T> reflectionHelper) {
		this.reflectionHelper = reflectionHelper;
	}

	@Transactional
	public T getById(Long id) {
		T entity = null;

		if (id != null) {
			entity = Ebean.find(reflectionHelper.getClass(this), id);
		}
		return entity!=null ? entity : reflectionHelper.getInstance(this);
	}

	@Transactional
	public List<T> getAll() {
		return Ebean.find(reflectionHelper.getClass(this)).orderBy(orderedBy()).findList();
	}

	@Transactional
	public List<T> getFiltered(SearchParams searchParams) {
		if(searchParams == null){
			searchParams = new SearchParams();
		}
		return getQueries(searchParams, Ebean.find(reflectionHelper.getClass(this)).where()).orderBy(orderedBy())
				.findList();
	}

	private ExpressionList<T> getQueries(SearchParams searchParams, ExpressionList<T> query) {
		ExpressionList<T> resultQuery = query;

		if (isSearchEntered(searchParams.getFilter(), searchParams.getSearchTerm())) {
			resultQuery = resultQuery.like(searchParams.getFilter(), "%" + searchParams.getSearchTerm() + "%");
		}
		if (isDateRangeEntered(searchParams.getFrom(), searchParams.getTo())) {
			resultQuery = resultQuery.and(Expr.ge("created", searchParams.getFrom()),
					Expr.le("created", searchParams.getTo()));
		}

		return resultQuery;
	}

	private boolean isDateRangeEntered(Date from, Date to) {
		return from != null && to != null && from.getTime() < to.getTime();
	}

	private boolean isSearchEntered(String filter, String searchTerm) {
		return StringUtils.isNotEmpty(filter) && StringUtils.isNotEmpty(searchTerm);
	}

	@Transactional
	public void save(T entity) {
		if (entity != null && entity.id != null) {
			Ebean.update(entity);
		} else if(entity != null){
			Ebean.insert(entity);
		}
	}

	@Transactional
	public void delete(Long id) {
		if (id != null) {
			Ebean.delete(getById(id));
		}
	}

	abstract protected String orderedBy();
}
