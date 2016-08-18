package dtos;

import java.util.List;

import models.Customer;

public class CustomersDTO extends SearchEntities<Customer>{
	public CustomersDTO(List<Customer> entities, List<String> filters, SearchParams searchParams) {
		super(entities, filters, searchParams);
	}
}
