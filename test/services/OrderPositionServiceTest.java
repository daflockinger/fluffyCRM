package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import models.OrderPosition;
import play.test.WithApplication;
import services.helper.ReflectionHelper;

public class OrderPositionServiceTest extends WithApplication{
	
	private OrderPositionService orderPositionService;

	@Before
	public void setUp() throws Exception {
		orderPositionService = new OrderPositionService();
		orderPositionService.setReflectionHelper(new ReflectionHelper<>());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllFromOrder_withNullOrder_shouldReturnEmpty() {
		assertNotNull(orderPositionService.getAllFromOrder(null));
	}
	
	@Test
	public void testGetAllFromOrder_withInvalidOrder_shouldReturnEmpty() {
		assertNotNull(orderPositionService.getAllFromOrder(-1l));
	}
	
	@Test
	public void testGetAllFromOrder_withExistingOrder_shouldReturnCorrect() {
		List<OrderPosition> poses = orderPositionService.getAllFromOrder(1l);
		
		assertNotNull(poses);
		assertEquals(poses.size(), 2);
	}
}
