package dtos;

import java.util.List;

import models.BaseModel;

public class SearchEntities<T extends BaseModel> extends EntitiesDTO<T>{
	
	public SearchEntities(List<T> entities, List<String> filters, SearchParams searchParams){
		setEntities(entities);
		this.filters = filters;
		this.searchParams = searchParams;
	}

	private List<String> filters;

	private SearchParams searchParams;

	public List<String> getFilters() {
		return filters;
	}

	public void setFilters(List<String> filters) {
		this.filters = filters;
	}

	public SearchParams getSearchParams() {
		return searchParams;
	}

	public void setSearchParams(SearchParams searchParams) {
		this.searchParams = searchParams;
	}
}
