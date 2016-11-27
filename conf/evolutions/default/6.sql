# Schema

# --- !Ups

insert into orders (created, status, customer_id) 
values (DATE('2015-01-11 01:02:03'), "ORDERED", 1),(DATE('2016-01-11 01:02:03'), "CANCELED", 1),(DATE('2016-11-11 11:11:11'), "PROCESSING", 2);

insert into order_position (name, description, created, price, status, order_id) 
values ("article 1","details of article 1",DATE('2015-01-11 01:02:03'),123.04,"ORDERED",1),("article 2","details of article 2",DATE('2015-01-11 01:02:03'),8.99,"ORDERED",1),
("article 3","details of article 3", DATE('2015-01-11 01:02:03'),1026.76,"ORDERED",1);


# --- !Downs

delete from order_position;
delete from orders;
