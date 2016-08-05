# Base fills

# --- !Ups

insert into user(user,password,email) values('flo','flo','flo@daflockinger.com');

# --- !Downs

delete from user where user='flo';