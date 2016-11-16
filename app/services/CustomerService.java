package services;

import models.Address;
import models.Customer;

public class CustomerService extends BaseService<Customer>{

	@Override
	protected String orderedBy() {
		return "name";
	}

	@Override
	public Customer getById(Long id) {
		Customer customer = super.getById(id);

		if (customer.address == null) {
			customer.address = new Address();
		}

		return customer;
	}
}
