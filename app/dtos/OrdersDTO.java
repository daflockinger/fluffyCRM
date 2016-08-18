package dtos;

import java.util.List;

import models.Order;

public class OrdersDTO extends SearchEntities<Order>{

	public OrdersDTO(List<Order> entities, List<String> filters, SearchParams searchParams) {
		super(entities, filters, searchParams);
	}
	
}
