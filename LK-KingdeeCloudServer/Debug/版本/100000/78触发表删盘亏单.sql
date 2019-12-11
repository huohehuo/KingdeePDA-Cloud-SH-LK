if exists (select * from sys.objects where name = 'T_STK_STKCOUNTLOSSdeleteFZ')
drop  trigger T_STK_STKCOUNTLOSSdeleteFZ
go
create trigger T_STK_STKCOUNTLOSSdeleteFZ
on T_STK_STKCOUNTLOSS
for delete
as 
 

if not exists(select 1 from T_STK_STKCOUNTLOSSEntry where FID in (select FID from deleted))
 begin 
   update t_PDABarCodeSign set FQty= t.FQty_All from(select FQty_All,FBarCode from t_PDABarCodeSign_COUNTIN where FInterID in (select FID from deleted))  as t where t_PDABarCodeSign.FBarCode=t.FBarCode
     delete from t_PDABarCodeSign_COUNTIN where FInterID in (select FID from deleted)
 end