package services;

import dtos.CustomerDTO;
import models.Customer;

public class CustomerService extends BaseService<Customer>{

	@Override
	protected String orderedBy() {
		return "name";
	}

}
