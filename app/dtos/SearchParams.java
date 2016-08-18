package dtos;

import java.util.Date;

import play.data.format.Formats.DateTime;

public class SearchParams {
	
	private String filter;
	
	private String searchTerm;
	
	@DateTime(pattern="MM/dd/yyyy hh:mm aa")
	private Date from;
	
	@DateTime(pattern="MM/dd/yyyy hh:mm aa")
	private Date to;

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}
}
