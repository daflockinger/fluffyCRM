package controllers;

import services.CustomerService;
import com.google.inject.Inject;

import models.Address;
import models.Customer;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

@Security.Authenticated(Secure.class)
public class Customers extends BaseController{
	
	@Inject CustomerService customerService;

	public Result get(Long id){
		Customer customer = customerService.getById(id);
		
		if(customer==null){
			customer = new Customer();
			customer.address = new Address();
		}
		return ok(views.html.edit.forms.customerForm.render(customer));
	}
	
	public Result getAll(){
		return ok(views.html.edit.customerList.render(customerService.getAll()));
	}
	
	public Result save(){
		Form<Customer> form = formFactory.form(Customer.class).bindFromRequest();
		
		customerService.save(form.get());
		return ok();
	}
	
	public Result delete(Long id){
		customerService.delete(id);
		return ok();
	}
}
