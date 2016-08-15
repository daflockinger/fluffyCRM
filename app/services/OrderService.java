package services;

import models.Order;

public class OrderService extends BaseService<Order>{

	@Override
	protected String orderedBy() {
		return "created";
	}

}
