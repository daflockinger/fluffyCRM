package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.avaje.ebean.Ebean;

import dtos.SearchParams;
import models.User;
import play.test.WithApplication;
import services.helper.ReflectionHelper;

public class UserServiceTest extends WithApplication {

	private UserService userService;

	@Before
	public void setUp() throws Exception {
		userService = new UserService();
		userService.setReflectionHelper(new ReflectionHelper<User>());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetById_withNullId_ShoulResultEmpty() {
		assertNotNull(userService.getById(null));
	}

	@Test
	public void testGetById_withNotExisting_ShoulResultEmpty() {
		assertNotNull(userService.getById(-1l));
	}

	@Test
	public void testGetById_withExistingId_ShoulResultCorrect() {
		User existingUser = userService.getById(1l);

		assertNotNull(existingUser);
		assertEquals(existingUser.user, "flo");
		assertEquals(existingUser.password, "flo");
		assertEquals(existingUser.email, "flo@daflockinger.com");
	}
	
	@Test
	public void testGetAll_ShouldReceiveCorrect(){
		List<User> users = userService.getAll();
		
		assertNotNull(users);
		assertEquals(users.size(), 1);
	}
	
	@Test
	public void testGetFiltered_withNullSearchParams_shouldReceiveAll(){
		List<User> users = userService.getFiltered(null);
		
		assertNotNull(users);
		assertEquals(users.size(), 1);
	}
	
	@Test
	public void testGetFiltered_withEmptySearchParams_shouldReceiveAll(){
		List<User> users = userService.getFiltered(new SearchParams());
		
		assertNotNull(users);
		assertEquals(users.size(), 1);
	}
	
	@Test
	public void testGetFiltered_withEmptySearchText_shouldReceiveAll(){
		SearchParams params = new SearchParams();
		params.setFilter("user");
		List<User> users = userService.getFiltered(params);
		
		assertNotNull(users);
		assertEquals(users.size(), 1);
	}
	
	@Test
	public void testGetFiltered_withEmptyFilter_shouldReceiveAll(){
		SearchParams params = new SearchParams();
		params.setSearchTerm("me");
		List<User> users = userService.getFiltered(params);
		
		assertNotNull(users);
		assertEquals(users.size(), 1);
	}
	
	@Test
	public void testGetFiltered_withValidSearch_shouldReceiveCorrect(){
		SearchParams params = new SearchParams();
		params.setFilter("email");
		params.setSearchTerm("flo");
		List<User> users = userService.getFiltered(params);
		
		assertNotNull(users);
		assertEquals(users.size(), 1);
		assertEquals(users.get(0).user, "flo");
	}
	
	@Test
	public void testSaveUser_withNullUser_ShouldDoNothing(){
		userService.save(null);
	}
	
	@Test
	public void testSaveUser_withExistingUser_ShouldUpdate(){
		User existingUser = new User();
		existingUser.id = 1l;
		existingUser.user = "flo";
		existingUser.password = "flo";
		existingUser.email = "new@mail.cc";
		
		userService.save(existingUser);
		
		User getExistingUser = Ebean.find(User.class).where().eq("user", "flo").findUnique();
		
		assertEquals(getExistingUser.email, "new@mail.cc");
		
		getExistingUser.email = "flo@daflockinger.com";
		getExistingUser.save();
	}
	
	
	@Test
	public void testDeleteUser_withNullId_shouldDoNothing(){
		userService.delete(null);
	}
	
	@Test
	public void testDeleteUser_withNonExistingId_shouldDoNothing(){
		userService.delete(-1l);
	}
	
	@Test
	public void testDeleteUser_witExistingId_shouldDoNothing(){
		User newUser = new User();
		newUser.id = 3l;
		newUser.user ="sepppp";
		newUser.password = "sepddpl";
		newUser.email = "sepp@eddppl.cc";
		newUser.save();
		
		assertNotNull(Ebean.find(User.class).where().eq("user","sepppp").findUnique());
		
		userService.delete(3l);
		
		assertNull(Ebean.find(User.class).where().eq("user","sepppp").findUnique());
	}
}
