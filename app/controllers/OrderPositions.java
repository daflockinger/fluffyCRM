package controllers;

import com.google.inject.Inject;

import dtos.OrderPositionDTO;
import dtos.OrderPositionsDTO;
import models.OrderPosition;
import models.OrderStatus;
import play.data.Form;
import play.mvc.Result;
import services.OrderPositionService;

public class OrderPositions extends BaseController {
	@Inject
	OrderPositionService orderPosService;

	public Result get(Long id, Long orderId) {
		OrderPosition orderPos = orderPosService.getById(id);

		if (orderPos == null) {
			orderPos = new OrderPosition();
		}

		return ok(views.html.edit.forms.orderPositionForm
				.render(new OrderPositionDTO(orderPos, orderId, OrderStatus.getStatusList())));
	}

	public Result getAllOfOrder(Long orderId) {
		OrderPositionsDTO orderPosesDto = new OrderPositionsDTO(orderPosService.getAllFromOrder(orderId), orderId);

		return ok(views.html.edit.orderPositionList.render(orderPosesDto));
	}

	public Result save() {
		Form<OrderPosition> form = formFactory.form(OrderPosition.class).bindFromRequest();

		orderPosService.save(form.get());
		return ok();
	}

	public Result delete(Long id) {
		orderPosService.delete(id);
		return ok();
	}
}
