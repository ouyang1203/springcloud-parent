create database springcloud ;
use  springcloud;
create table product(
product_id int primary key  auto_increment,
product_name varchar(200),
product_amount int);
insert into product(product_name,product_amount) values('A',100);
insert into product(product_name,product_amount) values('B',90);
create database test;
use test;
create table order_info(
order_id int primary key  auto_increment,
product_name varchar(200),
product_amount int);
insert into order_info(product_name,product_amount) values('A',10);
insert into order_info(product_name,product_amount) values('B',20);