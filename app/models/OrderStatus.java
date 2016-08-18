package models;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum OrderStatus {
	ORDERED, PENDING, PROCESSING, COMPLETED, CANCELED;

	public static List<String> getStatusList() {
		return Arrays.asList(OrderStatus.values()).stream().map(status -> status.toString())
				.collect(Collectors.toList());
	}
}
