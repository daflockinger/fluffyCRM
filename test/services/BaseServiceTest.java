package services;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import play.db.Database;
import play.db.Databases;
import play.db.evolutions.Evolutions;
import play.test.WithApplication;

public class BaseServiceTest extends WithApplication{
	
	private final static Database db =  Databases.createFrom("org.mariadb.jdbc.Driver", "jdbc:mariadb://127.0.0.1:3306/test");
	
	@BeforeClass
	public static void init(){
		Evolutions.applyEvolutions(db);
	}
	
	@AfterClass
	public static void shutdown(){
		Evolutions.cleanupEvolutions(db);
	}
}
