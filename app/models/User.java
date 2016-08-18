package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import play.data.validation.Constraints;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "id", "user" }) })
public class User extends BaseModel {

	@Column(nullable = false)
	@Constraints.Required
	public String user;

	@Column(nullable = false)
	@Constraints.Required
	public String password;

	@Constraints.Email
	public String email;
}
