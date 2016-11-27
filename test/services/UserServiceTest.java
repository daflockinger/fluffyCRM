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
import models.User;
import services.helper.ReflectionHelper;

public class UserServiceTest extends BaseServiceTest{

	private UserService service;

	@Before
	public void setUp() throws Exception {
		service = new UserService();
		service.reflectionHelper = new ReflectionHelper<>();
	}

	@Test
	public void testGetById_withNullId_shouldReturnEmpty() {
		User foundUser = service.getById(null);
		
		assertNotNull(foundUser);
	}
	
	@Test
	public void testGetById_withNotExistingId_shouldReturnEmpty() {
		User foundUser = service.getById(2343876l);
		
		assertNotNull(foundUser);
	}
	
	@Test
	public void testGetById_withValidId_shouldReturnCorrect() {
		User user = Ebean.find(User.class).where().eq("user", "flo").findUnique();
		User foundUser = service.getById(user.id);
		
		assertNotNull(foundUser);
		assertEquals(user.email,foundUser.email);
		assertEquals(user.user,foundUser.user);
		assertEquals(user.password,foundUser.password);
	}
	
	@Test
	public void testGetAll_shouldReturnCorrect(){		
		List<User> users = service.getAll();
		assertNotNull(users);
		assertEquals(Ebean.find(User.class).findRowCount(), users.size());
	}
	
	@Test
	public void testSave_withNullUser_shouldDoNothing(){
		service.save(null);
	}
	
	@Test
	public void testSave_withExistingUser_shouldUpdate(){
		User user = Ebean.find(User.class).where().eq("user", "flo").findUnique();
		user.email = "new@mail.cc";
		service.save(user);
		User updatedUser = Ebean.find(User.class).where().eq("user", "flo").findUnique();
		assertEquals("new@mail.cc", updatedUser.email);
	}
	
	@Test
	public void testSave_withNewUser_shouldInsert(){
		User newUser = new User();
		newUser.email = "test@mail.cc";
		newUser.user = "sepp";
		newUser.password = "topsecret";
		service.save(newUser);
		User foundUser = Ebean.find(User.class).where().eq("user", "sepp").findUnique();
		
		assertNotNull(foundUser);
		assertEquals("test@mail.cc",foundUser.email);
		assertEquals("sepp",foundUser.user);
		assertEquals("topsecret", foundUser.password);
		
		Ebean.delete(foundUser);
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
	public void testDelete_withExistingUser_shouldDelete(){
		User newUser = new User();
		newUser.email = "test@mail.cc";
		newUser.user = "Testy";
		newUser.password = "1234";
		
		service.save(newUser);
		
		User customer = Ebean.find(User.class).where().eq("user", "Testy").findUnique();
		service.delete(customer.id);
		
		User goneUser = Ebean.find(User.class).where().eq("user", "Testy").findUnique();
		assertNull(goneUser);
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
		params.setFilter("password");
		params.setSearchTerm("flo");
		
		List<User> founds = service.getFiltered(params);
		assertNotNull(founds);
		assertTrue(founds.size() == 1);
		assertEquals("flo", founds.get(0).user);
	}
}
