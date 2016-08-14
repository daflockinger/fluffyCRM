package models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.avaje.ebean.Model;

import play.data.validation.Constraints;
import services.helper.Searchable;

@Embeddable
@Searchable
public class Address extends Model {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long id;
	
	@Column(nullable=false)
	@Constraints.Required
	@Searchable
	public String firstName;
	
	@Column(nullable=false)
	@Constraints.Required
	@Searchable
	public String lastName;
	
	@Searchable
	public String companyName;
	
	@Column(nullable=false)
	@Constraints.Required
	@Searchable
	public String street;
	
	@Column(nullable=false)
	@Constraints.Required
	@Searchable
	public String zip;
	
	@Column(nullable=false)
	@Constraints.Required
	@Searchable
	public String city;
	
	@Column(nullable=false)
	@Constraints.Required
	@Searchable
	public String country;
	
	@Searchable
	public String phone;
	
	
}
