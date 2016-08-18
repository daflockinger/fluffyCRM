package controllers;

import services.CustomerService;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;

import dtos.CustomerDTO;
import dtos.CustomersDTO;
import dtos.SearchParams;
import models.Address;
import models.Customer;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

@Security.Authenticated(Secure.class)
public class Customers extends BaseController {

	@Inject
	CustomerService customerService;

	public Result get(Long id) {
		Customer customer = customerService.getById(id);

		if (customer == null) {
			customer = new Customer();
			customer.address = new Address();
		}
		return ok(views.html.edit.forms.customerForm.render(new CustomerDTO(customer)));
	}

	public Result getAll() {
		CustomersDTO customersDto = new CustomersDTO(customerService.getAll(), getFilters(), new SearchParams());
		return ok(views.html.edit.customerList.render(customersDto));
	}

	public Result save() {
		Form<Customer> form = formFactory.form(Customer.class).bindFromRequest();

		customerService.save(form.get());
		return ok();
	}

	public Result delete(Long id) {
		customerService.delete(id);
		return ok();
	}

	public Result search() {
		Form<SearchParams> form = formFactory.form(SearchParams.class).bindFromRequest();
		CustomersDTO customersDto = new CustomersDTO(customerService.getFiltered(form.get()), getFilters(), form.get());

		return ok(views.html.edit.customerList.render(customersDto));
	}

	private List<String> getFilters() {
		return ImmutableList.of("name", "email", "company_name", "street", "city", "country");
	}
}
