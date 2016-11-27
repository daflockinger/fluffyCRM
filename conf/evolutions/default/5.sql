# Schema

# --- !Ups


create table orders (
  id                         bigint auto_increment not null,
  created					 datetime,
  status                     varchar(255) not null,
  customer_id				 bigint,
  constraint fk_customer	 FOREIGN KEY (customer_id) REFERENCES customer(id),
  constraint pk_order	     primary key (id))
;

create table order_position (
  id                     	 bigint auto_increment not null,
  name                   	 varchar(255) not null,
  description            	 varchar(255),
  created                	 datetime,
  price    	                 double,
  status		   		  	 varchar(255),
  order_id                   bigint,
  CONSTRAINT fk_order	     FOREIGN KEY (order_id) REFERENCES orders(id),
  constraint pk_order_pos    primary key (id))
;


# --- !Downs

alter table orders drop foreign key fk_customer;
alter table order_position drop foreign key fk_order;
DROP TABLE orders;
DROP TABLE order_position;