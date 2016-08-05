package services;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import dtos.LoginCredentials;
import play.test.WithApplication;

public class LoginServiceTest extends WithApplication{

	private LoginService loginService;
	
	@Before
	public void before(){
		loginService = new LoginService();
	}
	
	@Test
	public void authenticateTest_withInvalidLogin_ShouldReturnFalse(){
		LoginCredentials credentials = new LoginCredentials();
		credentials.setUser("hack");
		credentials.setPassword("me");
		
		assertFalse(loginService.authenticate(credentials));
	}
	
	@Test
	public void authenticateTest_withValidLogin_ShouldReturnTrue(){
		LoginCredentials credentials = new LoginCredentials();
		credentials.setUser("flo");
		credentials.setPassword("flo");
		
		assertTrue(loginService.authenticate(credentials));
	}
}
