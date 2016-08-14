package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import play.data.validation.Constraints;
import services.helper.Searchable;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "id", "name", "email" }) })
public class Customer extends BaseModel{
		
	@Column(nullable=false)
	@Constraints.Required
	@Searchable
	public String name;
	
	@Column(nullable=false)
	@Constraints.Required
	@Searchable
	public String email;
	
	@Embedded
	@Searchable
	public Address address;
	
}
