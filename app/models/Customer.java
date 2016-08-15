package models;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import play.data.validation.Constraints;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "id", "name", "email" }) })
public class Customer extends BaseModel{
		
	@Column(nullable=false)
	@Constraints.Required
	public String name;
	
	@Column(nullable=false)
	@Constraints.Required
	public String email;
	
	@Embedded
	public Address address;
	
}
