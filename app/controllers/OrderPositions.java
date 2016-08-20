package controllers;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;

import dtos.OrderPositionDTO;
import dtos.OrderPositionsDTO;
import models.Customer;
import models.OrderPosition;
import models.OrderStatus;
import play.data.Form;
import play.libs.Json;
import play.mvc.Result;
import services.OrderPositionService;

public class OrderPositions extends BaseController {
	@Inject
	OrderPositionService orderPosService;
	
	private List<String> requiredFields = ImmutableList.of("name", "price","status","order");


	public Result get(Long id, Long orderId) {
		OrderPosition orderPos = orderPosService.getById(id);

		if (orderPos == null) {
			orderPos = new OrderPosition();
		}

		return ok(Json.toJson(new OrderPositionDTO(orderPos, orderId, OrderStatus.getStatusList())));
	}

	public Result getAllOfOrder(Long orderId) {
		OrderPositionsDTO orderPosesDto = new OrderPositionsDTO(orderPosService.getAllFromOrder(orderId), orderId);

		return ok(Json.toJson(orderPosesDto));
	}

	public Result save() {
		JsonNode json = request().body().asJson();

		if (jsonError.hasErrors(requiredFields, json)) {
			return jsonError.getErrorResult(requiredFields, json);
		} else {
			orderPosService.save(Json.fromJson(json, OrderPosition.class));
			return created(json);
		}
	}

	public Result delete(Long id) {
		orderPosService.delete(id);
		return ok();
	}
}
