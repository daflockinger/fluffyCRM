package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.avaje.ebean.Ebean;

import dtos.SearchParams;
import models.Address;
import models.Customer;
import services.helper.ReflectionHelper;

public class CustomerServiceTest extends BaseServiceTest {
	
	private CustomerService service;

	@Before
	public void setUp() throws Exception {
		service = new CustomerService();
		service.reflectionHelper = new ReflectionHelper<>();
	}

	@Test
	public void testGetById_withNullId_shouldReturnEmpty() {
		Customer foundCustomer = service.getById(null);
		
		assertNotNull(foundCustomer);
	}
	
	@Test
	public void testGetById_withNotExistingId_shouldReturnEmpty() {
		Customer foundCustomer = service.getById(2343876l);
		
		assertNotNull(foundCustomer);
	}
	
	@Test
	public void testGetById_withValidId_shouldReturnCorrect() {
		Customer customer = Ebean.find(Customer.class).where().eq("name", "Dui Corporation").findUnique();
		Customer foundCustomer = service.getById(customer.id);
		
		assertNotNull(foundCustomer);
		assertEquals(customer.email,foundCustomer.email);
		assertEquals(customer.name,foundCustomer.name);
		assertNotNull(customer.address);
		assertEquals(customer.address.city,foundCustomer.address.city);
		assertEquals(customer.address.companyName,foundCustomer.address.companyName);
		assertEquals(customer.address.country,foundCustomer.address.country);
		assertEquals(customer.address.firstName,foundCustomer.address.firstName);
		assertEquals(customer.address.lastName,foundCustomer.address.lastName);
		assertEquals(customer.address.phone,foundCustomer.address.phone);
		assertEquals(customer.address.street,foundCustomer.address.street);
		assertEquals(customer.address.zip,foundCustomer.address.zip);
	}
	
	@Test
	public void testGetAll_shouldReturnCorrect(){		
		List<Customer> customers = service.getAll();
		assertNotNull(customers);
		assertEquals(Ebean.find(Customer.class).findRowCount(), customers.size());
	}
	
	@Test
	public void testSave_withNullCustomer_shouldDoNothing(){
		service.save(null);
	}
	
	@Test
	public void testSave_withExistingCustomer_shouldUpdate(){
		Customer customer = Ebean.find(Customer.class).where().eq("name", "Dui Corporation").findUnique();
		customer.email = "new@mail.cc";
		service.save(customer);
		Customer updatedcustomer = Ebean.find(Customer.class).where().eq("name", "Dui Corporation").findUnique();
		assertEquals("new@mail.cc", updatedcustomer.email);
	}
	
	@Test
	public void testSave_withNewCustomer_shouldInsert(){
		Customer newCustomer = new Customer();
		newCustomer.email = "test@mail.cc";
		newCustomer.name = "Testy";
		Address address = new Address();
		address.city = "fake city";
		address.companyName = "fake company";
		address.country = "fantasyland";
		address.firstName = "john";
		address.lastName = "doe";
		address.phone = "5551234";
		address.street = "sesame street";
		address.zip = "1234";
		newCustomer.address = address;
		service.save(newCustomer);
		Customer foundCustomer = Ebean.find(Customer.class).where().eq("name", "Testy").findUnique();
		
		assertNotNull(foundCustomer);
		assertEquals("test@mail.cc",foundCustomer.email);
		assertEquals("Testy",foundCustomer.name);
		assertNotNull(foundCustomer.address);
		assertEquals("fake city",foundCustomer.address.city);
		assertEquals("fake company",foundCustomer.address.companyName);
		assertEquals("fantasyland",foundCustomer.address.country);
		assertEquals("john",foundCustomer.address.firstName);
		assertEquals("doe",foundCustomer.address.lastName);
		assertEquals("5551234",foundCustomer.address.phone);
		assertEquals("sesame street",foundCustomer.address.street);
		assertEquals("1234",foundCustomer.address.zip);
		
		Ebean.delete(foundCustomer);
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
	public void testDelete_withExistingCustomer_shouldDelete(){
		Customer newCustomer = new Customer();
		newCustomer.email = "test@mail.cc";
		newCustomer.name = "Testy";
		Address address = new Address();
		address.city = "fake city";
		address.companyName = "fake company";
		address.country = "fantasyland";
		address.firstName = "john";
		address.lastName = "doe";
		address.phone = "5551234";
		address.street = "sesame street";
		address.zip = "1234";
		newCustomer.address = address;
		service.save(newCustomer);
		
		Customer customer = Ebean.find(Customer.class).where().eq("name", "Testy").findUnique();
		service.delete(customer.id);
		
		Customer goneCustomer = Ebean.find(Customer.class).where().eq("name", "Testy").findUnique();
		assertNull(goneCustomer);
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
		params.setFilter("email");
		params.setSearchTerm("cursus@augueac.co.uk");
		
		List<Customer> founds = service.getFiltered(params);
		assertNotNull(founds);
		assertTrue(founds.size() == 1);
		assertEquals("Nascetur Company", founds.get(0).name);
	}
	
}
