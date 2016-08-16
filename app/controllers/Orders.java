package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;

import dtos.SearchParams;
import models.Customer;
import models.Order;
import models.OrderStatus;
import play.data.Form;
import play.mvc.Result;
import services.CustomerService;
import services.OrderService;

public class Orders extends BaseController{
	
	@Inject OrderService orderService;
	@Inject CustomerService customerService;

	public Result get(Long id){
		Order order = orderService.getById(id);
		
		if(order==null){
			order = new Order();
			order.created = new Date();
			order.customer = new Customer();
		}
		
		return ok(views.html.edit.forms.orderForm.render(order,OrderStatus.getStatusList(),customerService.getAll()));
	}
	
	public Result getAll(){
		return ok(views.html.edit.orderList.render(orderService.getAll(), getFilters(),new SearchParams()));
	}
	
	public Result save(){
		Form<Order> form = formFactory.form(Order.class).bindFromRequest();
		
		orderService.save(form.get());
		return ok();
	}
	
	public Result delete(Long id){
		orderService.delete(id);
		return ok();
	}
	
	public Result search() {
		Form<SearchParams> form = formFactory.form(SearchParams.class).bindFromRequest();
		return ok(
				views.html.edit.orderList.render(orderService.getFiltered(form.get()), getFilters(), form.get()));
	}

	private List<String> getFilters() {
		return ImmutableList.of("name", "status");
	}
}
