package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import play.data.validation.*;
import com.avaje.ebean.Model;
import play.data.format.*;


@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "id", "user" }) })
public class User extends Model{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long id;
	
	@Column(nullable=false)
	@Constraints.Required
	public String user;
	
	@Column(nullable=false)
	@Constraints.Required
	public String password;
	
	@Constraints.Email
	public String email;
	
	public static Finder<Long, User> find = new Finder<Long,User>(User.class);
}
