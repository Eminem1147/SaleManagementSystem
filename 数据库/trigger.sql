use salemanagement;


drop trigger commodityinsert;
drop trigger saleinsert;
drop trigger storageinsert;


delimiter /
create trigger commodityinsert
after insert on product
for each row 
begin
insert into storage values(new.productname,new.productid,new.unit,new.inprice,1,0);
end /
delimiter ;

delimiter /
create trigger saleinsert
after insert on sale
for each row 
begin
update storage set number=number-new.number where productid=new.productid;
end /
delimiter ;

delimiter /
create trigger storageinsert
after insert on store
for each row 
begin
update storage set number=number+new.number where productid=new.productid;
end /
delimiter ;
