if exists (select * from sys.objects where name = 'T_STK_MISDELIVERYdeleteFZ')
drop  trigger T_STK_MISDELIVERYdeleteFZ
go
create trigger T_STK_MISDELIVERYdeleteFZ
on T_STK_MISDELIVERY
for delete
as 
 
 
if not exists(select 1 from T_STK_MISDELIVERYEntry where FID in (select FID from deleted))
 begin  
            update t_PDABarCodeSign set FIsOutStore='Î´³ö¿â',FQtyOut=t_PDABarCodeSign.FQtyOut-t.FQtyOut from (select  FBarCode,sum(FQtyOut) as FQtyOut from t_PDABarCodeSign_Red where FInterID in (select FID from deleted) group by FBarCode)  as t  where t.FBarCode=t_PDABarCodeSign.FBarCode
      delete from t_PDABarCodeSign_Red where FInterID in (select FID from deleted)
 end
  


