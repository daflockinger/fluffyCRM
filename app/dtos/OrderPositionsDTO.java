package dtos;

import java.util.List;

import models.OrderPosition;

public class OrderPositionsDTO extends EntitiesDTO<OrderPosition>{

	public OrderPositionsDTO(List<OrderPosition> positions, Long orderId){
		setEntities(positions);
		this.orderId = orderId;
	}
	
	private Long orderId;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
}
