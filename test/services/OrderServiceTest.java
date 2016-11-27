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
import models.Order;
import models.OrderStatus;
import services.helper.ReflectionHelper;

public class OrderServiceTest extends BaseServiceTest{

	private OrderService service;

	@Before
	public void setUp() throws Exception {
		service = new OrderService();
		service.reflectionHelper = new ReflectionHelper<>();
	}

	@Test
	public void testGetById_withNullId_shouldReturnEmpty() {
		Order foundOrder = service.getById(null);
		
		assertNotNull(foundOrder);
	}
	
	@Test
	public void testGetById_withNotExistingId_shouldReturnEmpty() {
		Order foundOrder = service.getById(2343876l);
		
		assertNotNull(foundOrder);
	}
	
	@Test
	public void testGetById_withValidId_shouldReturnCorrect() {
		Order order = Ebean.find(Order.class).where().eq("status", "ORDERED").findUnique();
		Order foundOrder = service.getById(order.id);
		
		assertNotNull(foundOrder);
		assertTrue(foundOrder.customer.id == 1l);
	}
	
	@Test
	public void testGetAll_shouldReturnCorrect(){		
		List<Order> orders = service.getAll();
		assertNotNull(orders);
		assertEquals(Ebean.find(Order.class).findRowCount(), orders.size());
	}
	
	@Test
	public void testSave_withNullOrder_shouldDoNothing(){
		service.save(null);
	}
	
	@Test
	public void testSave_withExistingOrder_shouldUpdate(){
		Order order = Ebean.find(Order.class).where().eq("status", "PROCESSING").findUnique();
		Customer betterCustomer = Ebean.find(Customer.class).where().eq("name", "Nullam Foundation").findUnique();
		order.customer = betterCustomer;
		service.save(order);
		Order updatedOrder = Ebean.find(Order.class).where().eq("status", "PROCESSING").findUnique();
		assertEquals("Nullam Foundation", updatedOrder.customer.name);
	}
	
	@Test
	public void testSave_withNewOrder_shouldInsert(){
		Order newOrder = new Order();
		Customer betterCustomer = Ebean.find(Customer.class).where().eq("name", "Nullam Foundation").findUnique();
		Date now = new Date();
		newOrder.customer = betterCustomer;
		newOrder.status = OrderStatus.PENDING;
		newOrder.created = now;
		service.save(newOrder);
		Order foundOrder = Ebean.find(Order.class).where().eq("status", "PENDING").findUnique();
		
		assertNotNull(foundOrder);
		assertEquals(now.toString(),foundOrder.created.toString());
		assertEquals(OrderStatus.PENDING,foundOrder.status);
		assertNotNull(foundOrder.customer);
		assertEquals("Nullam Foundation", foundOrder.customer.name);
		assertEquals("dictum.Phasellus@lorem.org", foundOrder.customer.email);
		
		Ebean.delete(foundOrder);
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
	public void testDelete_withExistingOrder_shouldDelete(){
		Order newOrder = new Order();
		newOrder.customer = new Customer();
		newOrder.created = new Date();
		newOrder.status = OrderStatus.PENDING;
		
		service.save(newOrder);
		
		Order customer = Ebean.find(Order.class).where().eq("status", "PENDING").findUnique();
		service.delete(customer.id);
		
		Order goneOrder = Ebean.find(Order.class).where().eq("status", "PENDING").findUnique();
		assertNull(goneOrder);
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
		params.setFilter("status");
		params.setSearchTerm("PROCESSING");
		
		List<Order> founds = service.getFiltered(params);
		assertNotNull(founds);
		assertTrue(founds.size() == 1);
		assertTrue(founds.get(0).customer.id == 2l);
	}
}
