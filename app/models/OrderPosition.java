package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Constraint;

import play.data.format.Formats.DateTime;
import play.data.validation.Constraints;

@Entity
@Table(name="order_position")
public class OrderPosition extends BaseModel{

	public String name;
	
	public String description;
	
	@DateTime(pattern="MM/dd/yyyy hh:mm aa")
	public Date created;
	
	public Double price;
	
	@Enumerated(EnumType.STRING)
	public OrderStatus status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	public Order order;
}
