package services;

import java.util.List;

import com.avaje.ebean.Ebean;

import models.OrderPosition;

public class OrderPositionService extends BaseService<OrderPosition> {

	@Override
	protected String orderedBy() {
		return "name";
	}

	public List<OrderPosition> getAllFromOrder(Long orderId) {
		return Ebean.find(reflectionHelper.getClass(this)).where().eq("order_id", String.valueOf(orderId))
				.orderBy(orderedBy()).findList();
	}

}
