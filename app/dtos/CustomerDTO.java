package dtos;

import models.Customer;

public class CustomerDTO extends EntityDTO<Customer>{
	public CustomerDTO(Customer customer){
		setEntity(customer);
	}
}
