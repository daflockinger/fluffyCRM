package models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.avaje.ebean.Model;

@MappedSuperclass
public class BaseModel extends Model{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long id;
}
