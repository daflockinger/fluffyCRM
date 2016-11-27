package controllers;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;

import dtos.OrderDTO;
import dtos.OrdersDTO;
import dtos.SearchParams;
import models.Order;
import models.OrderStatus;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;
import services.CustomerService;
import services.OrderService;

@Security.Authenticated(Secure.class)
public class Orders extends BaseController{
	
	@Inject OrderService orderService;
	@Inject CustomerService customerService;

	public Result get(Long id){
		OrderDTO orderDto = new OrderDTO(orderService.getById(id), OrderStatus.getStatusList(), customerService.getAll());
		
		return ok(views.html.edit.forms.orderForm.render(orderDto));
	}
	
	public Result getAll(){		
		OrdersDTO ordersDto = new OrdersDTO(orderService.getAll(), getFilters(), new SearchParams());
		return ok(views.html.edit.orderList.render(ordersDto));
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
		OrdersDTO ordersDto = new OrdersDTO(orderService.getFiltered(form.get()),getFilters(),form.get());
		return ok(views.html.edit.orderList.render(ordersDto));
	}

	private List<String> getFilters() {
		return ImmutableList.of("name", "status");
	}
}
