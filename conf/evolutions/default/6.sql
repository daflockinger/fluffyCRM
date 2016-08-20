# Schema

# --- !Ups

insert into orders(created,status,customer_id) values('2015-07-21','PENDING',1);
insert into order_position(name,description,created,price,status,order_id) 
	   values('pos1','descript1','2015-07-21',12.56,'COMPLETED',1);
insert into order_position(name,description,created,price,status,order_id) 
	   values('pos2','descript2','2015-07-21',92.69,'PENDING',1);
	   

# --- !Downs

alter table orders drop foreign key fk_customer;
alter table order_position drop foreign key fk_order;
delete from orders;
delete from order_position;