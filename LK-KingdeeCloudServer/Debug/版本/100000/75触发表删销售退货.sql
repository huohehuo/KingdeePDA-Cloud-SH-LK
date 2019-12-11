if exists (select * from sys.objects where name = 'T_SAL_RETURNSTOCKdeleteFZ')
drop  trigger T_SAL_RETURNSTOCKdeleteFZ
go
create trigger T_SAL_RETURNSTOCKdeleteFZ
on T_SAL_RETURNSTOCK
for delete
as 
 
 
if not exists(select 1 from T_SAL_RETURNSTOCKEntry where FID in (select FID from deleted))
 begin  
       --      update t_PDABarCodeSign set FIsOutStore='Î´³ö¿â',FQtyOut=t_PDABarCodeSign.FQtyOut-t.FQtyOut from (select  FBarCode,sum(FQtyOut) as FQtyOut from t_PDABarCodeSign_Red where FInterID in (select FID from deleted) group by FBarCode)  as t  where t.FBarCode=t_PDABarCodeSign.FBarCode
       --delete from t_PDABarCodeSign_Red where FInterID in (select FID from deleted)
        update t_PDABarCodeSign  set FIsInStore='Î´Èë¿â' where FInterIDIn in (select FID from deleted) 
     delete from t_PDABarCodeSign_In where FInterID in (select FID from deleted)
 end
  


