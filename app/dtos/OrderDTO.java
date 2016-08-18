package dtos;

import java.util.List;

import models.Customer;
import models.Order;

public class OrderDTO extends EntityDTO<Order>{
	
	public OrderDTO(Order order, List<String> statuses, List<Customer> customers){
		setEntity(order);
		this.statuses = statuses;
		this.customers = customers;
	}
	
	List<String> statuses;
	
	List<Customer> customers;

	public List<String> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<String> statuses) {
		this.statuses = statuses;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
}
