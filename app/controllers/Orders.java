package controllers;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;

import dtos.OrderDTO;
import dtos.OrdersDTO;
import dtos.SearchParams;
import models.Order;
import models.OrderStatus;
import play.libs.Json;
import play.mvc.Result;
import services.CustomerService;
import services.OrderService;


public class Orders extends BaseController{
	
	@Inject OrderService orderService;
	@Inject CustomerService customerService;
	
	private List<String> filters = ImmutableList.of("name", "status");
	private List<String> requiredFields = ImmutableList.of("created", "status","customer");


	public Result get(Long id){
		OrderDTO orderDto = new OrderDTO(orderService.getById(id), OrderStatus.getStatusList(), customerService.getAll());
		
		return ok(Json.toJson(orderDto));
	}
	
	public Result getAll(){		
		OrdersDTO ordersDto = new OrdersDTO(orderService.getAll(), filters, new SearchParams());
		return ok(Json.toJson(ordersDto));
	}
	
	public Result save(){
		JsonNode json = request().body().asJson();

		if (jsonError.hasErrors(requiredFields, json)) {
			return jsonError.getErrorResult(requiredFields, json);
		} else {
			orderService.save(Json.fromJson(json, Order.class));
			return created(json);
		}
	}
	
	public Result delete(Long id){
		orderService.delete(id);
		return ok();
	}
	
	public Result search() {
		JsonNode json = request().body().asJson();
		
		if (jsonError.hasErrors(new ArrayList<>(), json)) {
			return jsonError.getErrorResult(new ArrayList<>(), json);
		} else {
			SearchParams params = Json.fromJson(json, SearchParams.class);
			OrdersDTO ordersDto = new OrdersDTO(orderService.getFiltered(params), filters, params);
			return ok(Json.toJson(ordersDto));
		}
	}
}
