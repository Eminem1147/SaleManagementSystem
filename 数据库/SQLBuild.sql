drop database salemanagement;
create database salemanagement;
use salemanagement;

create table account(
accountid integer primary key,
accountname varchar(30) not null,
accountpassword varchar(20) not null
)DEFAULT CHARSET=utf8;
create table product(
productid integer primary key,
productname varchar(40) not null,
producedate datetime not null,
inprice float  not null,
saleprice float not null,
storagetime int not null,
unit varchar(8) not null,
indate date
)DEFAULT CHARSET=utf8;
create table guest(
guestid int primary key,
guestname varchar(20) not null,
guestaddress varchar(40) not null,
phonenumber varchar(20) not null
)DEFAULT CHARSET=utf8;
create table sale(
saleid int primary key,
productid int not null,
productname varchar(45) not null,
guestid int not null,
selltime date not null,
sellplace varchar(20) not null,
number int not null,
money float not null
)DEFAULT CHARSET=utf8;
create table store( /*进货*/
storeid int primary key,
productid int not null,
productname varchar(45) not null,
inprice float not null,
number int not null,
unit varchar(8) not null
)DEFAULT CHARSET=utf8;
create table storage( /*库存*/
productname varchar(45) not null,
productid int not null primary key,
unit varchar(8) not null,
price float not null,
downlimit int not null,
number int not null
)DEFAULT CHARSET=utf8;


