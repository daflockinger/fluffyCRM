package models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.JsonIgnore;

import play.data.validation.Constraints;

@Embeddable
public class Address extends Model {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@JsonIgnore
	public Long id;
	
	@Column(nullable=false)
	@Constraints.Required
	public String firstName;
	
	@Column(nullable=false)
	@Constraints.Required
	public String lastName;
	
	public String companyName;
	
	@Column(nullable=false)
	@Constraints.Required
	public String street;
	
	@Column(nullable=false)
	@Constraints.Required
	public String zip;
	
	@Column(nullable=false)
	@Constraints.Required
	public String city;
	
	@Column(nullable=false)
	@Constraints.Required
	public String country;
	
	public String phone;
	
	
}
