package controllers;

import services.CustomerService;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;

import dtos.CustomerDTO;
import dtos.CustomersDTO;
import dtos.SearchParams;
import models.Address;
import models.Customer;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;

//@Security.Authenticated(Secure.class)
public class Customers extends BaseController {

	private List<String> requiredUserFields = ImmutableList.of("name", "email");
	private List<String> filters = ImmutableList.of("name", "email", "company_name", "street", "city", "country");

	@Inject
	CustomerService customerService;

	public Result get(Long id) {
		Customer customer = customerService.getById(id);

		if (customer == null) {
			customer = new Customer();
			customer.address = new Address();
		}
		return ok(Json.toJson(customer));
	}

	public Result getAll() {
		CustomersDTO customersDto = new CustomersDTO(customerService.getAll(), filters, new SearchParams());
		return ok(Json.toJson(customersDto));
	}

	public Result save() {
		JsonNode json = request().body().asJson();

		if (jsonError.hasErrors(requiredUserFields, json)) {
			return jsonError.getErrorResult(requiredUserFields, json);
		} else {
			customerService.save(Json.fromJson(json, Customer.class));
			return created(json);
		}
	}

	public Result delete(Long id) {
		customerService.delete(id);

		return ok();
	}

	public Result search() {
		JsonNode json = request().body().asJson();

		if (jsonError.hasErrors(new ArrayList<>(), json)) {
			return jsonError.getErrorResult(new ArrayList<>(), json);
		} else {
			SearchParams params = Json.fromJson(json, SearchParams.class);
			CustomersDTO customersDto = new CustomersDTO(customerService.getFiltered(params), filters, params);
			return ok(Json.toJson(customersDto));
		}
	}
}
