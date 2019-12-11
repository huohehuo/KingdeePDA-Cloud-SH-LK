if exists (select * from sys.objects where name = 'T_SAL_OUTSTOCKdeleteFZ')
drop  trigger T_SAL_OUTSTOCKdeleteFZ
go
create trigger T_SAL_OUTSTOCKdeleteFZ
on T_SAL_OUTSTOCK
for delete
as  
 
if not exists(select 1 from T_SAL_OUTSTOCKEntry where FID in (select FID from deleted))
 begin  
          update t_PDABarCodeSign set FIsOutStore='未出库',FQtyOut=t_PDABarCodeSign.FQtyOut-t.FQtyOut from (select  FBarCode,sum(FQtyOut) as FQtyOut from t_PDABarCodeSign_Red where FInterID in (select FID from deleted) group by FBarCode)  as t  where t.FBarCode=t_PDABarCodeSign.FBarCode
        delete from t_PDABarCodeSign_Red where FInterID in (select FID from deleted)
         --刨光车间装箱条码
    if  exists(select * from sysobjects where xtype='u' and name='t_AssembleRecord')
     begin
	    update	t_AssembleRecord  set FInterIDOut=0, FStatus = 1,FRemark = '' where  FInterIDOut in (select FID from deleted)
	 end
	 
	  if  exists(select * from sysobjects where xtype='u' and name='t_AssembleRecord1')
     begin
	    update	t_AssembleRecord1  set FInterIDOut=0, FStatus = 1,FRemark = '' where  FInterIDOut in (select FID from deleted)
	 end
 end
  


