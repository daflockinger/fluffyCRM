create table user (
  id                     bigint auto_increment not null,
  user                   varchar(255) not null,
  password               varchar(255) not null,
  email                  varchar(255),
  constraint uq_user_1 unique (id, user),
  constraint pk_user primary key (id))
;

insert into user(user,password,email) values('flo','flo','flo@daflockinger.com');