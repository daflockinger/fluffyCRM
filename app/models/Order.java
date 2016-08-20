package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.avaje.ebean.annotation.JsonIgnore;

import play.data.format.Formats.DateTime;

@Entity
@Table(name="orders")
public class Order extends BaseModel {

	@DateTime(pattern="MM/dd/yyyy hh:mm aa")
	public Date created;
	
	@Enumerated(EnumType.STRING)
	public OrderStatus status;

	@ManyToOne(optional = false)
	public Customer customer;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
	@JsonIgnore
	public List<OrderPosition> positions;
}
