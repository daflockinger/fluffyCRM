package services;

import java.util.ArrayList;

import models.Order;

public class OrderService extends BaseService<Order> {

	@Override
	protected String orderedBy() {
		return "created";
	}

	@Override
	public Order getById(Long id) {
		Order order = super.getById(id);

		if (order.positions == null) {
			order.positions = new ArrayList<>();
		}

		return order;
	}
}
