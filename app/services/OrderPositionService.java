package services;

import java.util.ArrayList;
import java.util.List;

import com.avaje.ebean.Ebean;

import models.OrderPosition;

public class OrderPositionService extends BaseService<OrderPosition> {

	@Override
	protected String orderedBy() {
		return "name";
	}

	public List<OrderPosition> getAllFromOrder(Long orderId) {
		List<OrderPosition> positions = null;
		if(orderId != null){
			positions = Ebean.find(reflectionHelper.getClass(this)).where().eq("order_id", String.valueOf(orderId))
					.orderBy(orderedBy()).findList();
		}
	
		if(positions == null){
			positions = new ArrayList<>();
		}
		return positions;
	}

}
