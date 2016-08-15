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

@Entity
public class Order extends BaseModel {

	/*public Date created;
	
	@Enumerated(EnumType.STRING)
	public OrderStatus status;

	@ManyToOne(optional = false)
	public Customer customer;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order_position", fetch = FetchType.EAGER)
	public List<OrderPosition> position;*/
}
