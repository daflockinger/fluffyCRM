package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.avaje.ebean.Ebean;

import dtos.SearchParams;
import models.Customer;
import models.OrderPosition;
import models.OrderStatus;
import services.helper.ReflectionHelper;

public class OrderPositionServiceTest extends BaseServiceTest{

	private OrderPositionService service;

	@Before
	public void setUp() throws Exception {
		service = new OrderPositionService();
		service.reflectionHelper = new ReflectionHelper<>();
	}

	@Test
	public void testGetById_withNullId_shouldReturnEmpty() {
		OrderPosition foundOrderPosition = service.getById(null);
		
		assertNotNull(foundOrderPosition);
	}
	
	@Test
	public void testGetById_withNotExistingId_shouldReturnEmpty() {
		OrderPosition foundOrderPosition = service.getById(2343876l);
		
		assertNotNull(foundOrderPosition);
	}
	
	@Test
	public void testGetById_withValidId_shouldReturnCorrect() {
		OrderPosition orderPosition = Ebean.find(OrderPosition.class).where().eq("name", "article 1").findUnique();
		OrderPosition foundOrderPosition = service.getById(orderPosition.id);
		
		assertNotNull(foundOrderPosition);
		assertEquals("details of article 1",foundOrderPosition.description);
		assertEquals(OrderStatus.ORDERED,foundOrderPosition.status);
		assertTrue(foundOrderPosition.price == 123.04);
	}
	
	@Test
	public void testGetAll_shouldReturnCorrect(){		
		List<OrderPosition> orderPositions = service.getAll();
		assertNotNull(orderPositions);
		assertEquals(Ebean.find(OrderPosition.class).findRowCount(), orderPositions.size());
	}
	
	@Test
	public void testSave_withNullOrderPosition_shouldDoNothing(){
		service.save(null);
	}
	
	@Test
	public void testSave_withExistingOrderPosition_shouldUpdate(){
		OrderPosition orderPosition = Ebean.find(OrderPosition.class).where().eq("name", "article 2").findUnique();
		orderPosition.price = 0.01;
		orderPosition.description = "cheap now";
		service.save(orderPosition);
		OrderPosition updatedOrderPosition = Ebean.find(OrderPosition.class).where().eq("name", "article 2").findUnique();
		assertEquals("cheap now", updatedOrderPosition.description);
		assertTrue(updatedOrderPosition.price == 0.01);
	}
	
	@Test
	public void testSave_withNewOrderPosition_shouldInsert(){
		OrderPosition newOrderPosition = new OrderPosition();
		Date now = new Date();
		newOrderPosition.status = OrderStatus.PENDING;
		newOrderPosition.created = now;
		newOrderPosition.name = "article 99";
		newOrderPosition.description = "best article ever";
		newOrderPosition.price = 9999.99;
		service.save(newOrderPosition);
		OrderPosition foundOrderPosition = Ebean.find(OrderPosition.class).where().eq("name", "article 99").findUnique();
		
		assertNotNull(foundOrderPosition);
		assertEquals(now.toString(),foundOrderPosition.created.toString());
		assertEquals(OrderStatus.PENDING,foundOrderPosition.status);
		assertTrue(foundOrderPosition.price == 9999.99);
		assertEquals("article 99",foundOrderPosition.name);
		assertEquals("best article ever",foundOrderPosition.description);
		
		Ebean.delete(foundOrderPosition);
	}
	
	@Test
	public void testDelete_withNull_shouldDoNothing(){
		service.delete(null);
	}
	
	@Test
	public void testDelete_withNotExisting_shouldDoNothing(){
		service.delete(-123l);
	}
	
	@Test
	public void testDelete_withExistingOrderPosition_shouldDelete(){
		OrderPosition newOrderPosition = new OrderPosition();
		newOrderPosition.created = new Date();
		newOrderPosition.name = "outdated article";
		
		service.save(newOrderPosition);
		
		OrderPosition customer = Ebean.find(OrderPosition.class).where().eq("name", "outdated article").findUnique();
		service.delete(customer.id);
		
		OrderPosition goneOrderPosition = Ebean.find(OrderPosition.class).where().eq("name", "outdated article").findUnique();
		assertNull(goneOrderPosition);
	}
	
	@Test
	public void testGetFiltered_withNullSearchParams_ShouldReturnEmpty(){
		assertNotNull(service.getFiltered(null));
	}
	
	@Test
	public void testGetFiltered_withEmptySearchParams_ShouldReturnEmpty(){
		assertNotNull(service.getFiltered(new SearchParams()));
	}
	
	@Test
	public void testGetFiltered_withCorrectParams_ShouldReturnCorrect(){
		SearchParams params = new SearchParams();
		params.setFilter("order_id");
		params.setSearchTerm("1");
		
		List<OrderPosition> founds = service.getFiltered(params);
		assertNotNull(founds);
		assertTrue(founds.size() == 3);
	}
	
	@Test
	public void testGetAllFromOrder_withNullId_shouldReturnEmpty(){
		assertNotNull(service.getAllFromOrder(null));
	}
	
	@Test
	public void testGetAllFromOrder_withNotExisting_shouldReturnEmpty(){
		assertNotNull(service.getAllFromOrder(-1234l));
	}
	
	@Test
	public void testGetAllFromOrder_withRealId_shouldReturnCorrect(){
		List<OrderPosition> positions = service.getAllFromOrder(1l);
		
		assertNotNull(positions);
		assertTrue(positions.size() == 3);
		
		for(OrderPosition pos : positions){
			assertEquals(OrderStatus.ORDERED,pos.status);
		}
	}
}
