create table customer (
  id                     bigint auto_increment not null,
  name                   varchar(255) not null,
  email                  varchar(255),
  first_name                 varchar(255),
  last_name                  varchar(255),
  company_name   		  	varchar(255),
  street                   	varchar(255),
  zip                  		varchar(255),
  city                 	    varchar(255),
  country                   varchar(255),
  phone                  	varchar(255),
  constraint uq_customer_1  unique (id, name, email),
  constraint pk_customer    primary key (id))
;