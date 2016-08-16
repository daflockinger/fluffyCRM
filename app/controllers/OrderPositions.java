package controllers;

import java.util.ArrayList;

import com.google.inject.Inject;

import dtos.SearchParams;
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

		return ok(views.html.edit.forms.orderPositionForm.render(orderPos, OrderStatus.getStatusList(),orderId));
	}

	public Result getAllOfOrder(Long orderId) {
		return ok(views.html.edit.orderPositionList.render(orderPosService.getAllFromOrder(orderId),
				new ArrayList<String>(), orderId,new SearchParams()));
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
