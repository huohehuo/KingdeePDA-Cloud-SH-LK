if exists (select * from sys.objects where name = 'T_SP_INSTOCKdeleteFZ')
drop  trigger T_SP_INSTOCKdeleteFZ
go
create trigger T_SP_INSTOCKdeleteFZ
on T_SP_INSTOCK
for delete
as 
 

if not exists(select 1 from T_SP_INSTOCKEntry where FID in (select FID from deleted))
 begin 
 update t_PDABarCodeSign  set FIsInStore='Î´Èë¿â' where FInterIDIn in (select FID from deleted)
    
     delete from t_PDABarCodeSign_In where FInterID in (select FID from deleted)
 end
  


