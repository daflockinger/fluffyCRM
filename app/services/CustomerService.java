package services;

import models.Customer;

public class CustomerService extends BaseService<Customer>{

	@Override
	protected String orderedBy() {
		return "name";
	}

}
