package controllers;

import services.CustomerService;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;

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
		return ok(views.html.edit.forms.customerForm.render(customer));
	}

	public Result getAll() {
		return ok(views.html.edit.customerList.render(customerService.getAll(), getFilters(), new SearchParams()));
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
		return ok(
				views.html.edit.customerList.render(customerService.getFiltered(form.get()), getFilters(), form.get()));
	}

	private List<String> getFilters() {
		return ImmutableList.of("name", "email", "company_name", "street", "city", "country");
	}
}
