if exists (select * from sys.objects where name = 'T_STK_MISCELLANEOUSdeleteFZ')
drop  trigger T_STK_MISCELLANEOUSdeleteFZ
go
create trigger T_STK_MISCELLANEOUSdeleteFZ
on T_STK_MISCELLANEOUS
for delete
as 
 
 
if not exists(select 1 from T_STK_MISCELLANEOUSEntry where FID in (select FID from deleted))
 begin 
 update t_PDABarCodeSign  set FIsInStore='Î´Èë¿â' where FInterIDIn in (select FID from deleted)
 
 delete from t_PDABarCodeSign_In where FInterID in (select FID from deleted)
 end
  


