package dtos;

import java.util.List;

import models.OrderPosition;

public class OrderPositionDTO extends EntityDTO<OrderPosition>{

	public OrderPositionDTO(OrderPosition position, Long orderId, List<String> statuses){
		setEntity(position);
		this.orderId = orderId;
		this.statuses = statuses;
	}
	
	private Long orderId;

	private List<String> statuses;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public List<String> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<String> statuses) {
		this.statuses = statuses;
	}
}
