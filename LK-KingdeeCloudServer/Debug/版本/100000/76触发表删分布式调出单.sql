if exists (select * from sys.objects where name = 'T_STK_STKTRANSFEROUTdeletePC')
drop  trigger T_STK_STKTRANSFEROUTdeletePC
go
create trigger T_STK_STKTRANSFEROUTdeletePC
on T_STK_STKTRANSFEROUT
for delete
as 
 

if not exists(select 1 from T_STK_STKTRANSFEROUTEntry where FID in (select FID from deleted))
 begin 
    update t_PDABarCodeSign set FStockID=t.FStockID_Before,FStockPlaceID =t.FStockPlaceID_Before  from (select  FStockID_Before,FStockPlaceID_Before,FStockID_Now,FStockPlaceID_Now,FBarCode from t_PDABarCodeSign_Allot where FInterID in (select FID from deleted))  as t  where t.FBarCode=t_PDABarCodeSign.FBarCode
        delete from t_PDABarCodeSign_Allot where FInterID in (select FID from deleted)
 end 



