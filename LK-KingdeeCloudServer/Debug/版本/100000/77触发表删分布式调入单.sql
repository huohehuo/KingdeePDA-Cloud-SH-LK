if exists (select * from sys.objects where name = 'T_STK_STKTRANSFERINdeletePC')
drop  trigger T_STK_STKTRANSFERINdeletePC
go
create trigger T_STK_STKTRANSFERINdeletePC
on T_STK_STKTRANSFERIN
for delete
as 
 
if not exists(select 1 from T_STK_STKTRANSFERINEntry where FID in (select FID from deleted))
 begin 
    update t_PDABarCodeSign set FStockID=t.FStockID_Before,FStockPlaceID =t.FStockPlaceID_Before  from (select  FStockID_Before,FStockPlaceID_Before,FStockID_Now,FStockPlaceID_Now,FBarCode from t_PDABarCodeSign_Allot where FInterID in (select FID from deleted))  as t  where t.FBarCode=t_PDABarCodeSign.FBarCode
      delete from t_PDABarCodeSign_Allot where FInterID in (select FID from deleted)
 end
  if  exists(select * from sysobjects where xtype='u' and name='t_AssembleRecord1_Allot')
  begin
  update t_AssembleRecord1Entry set FDCStockID=t.FStockID_Before,FDCSPID =t.FStockPlaceID_Before,FSTOCKORGID=t.FSTOCKORGID_Before,FOWNERID =t.FOWNERID_Before  from (select  FStockID_Before,FStockPlaceID_Before,FStockID_Now,FStockPlaceID_Now,FBarCode,FEntryID,FSTOCKORGID_Before,FOWNERID_Before,FSTOCKORGID_Now,FOWNERID_Now from t_AssembleRecord1_Allot where FInterID in (select FID from deleted))  as t  where t.FEntryID=t_AssembleRecord1Entry.FEntryID
      delete from t_AssembleRecord1_Allot where FInterID in (select FID from deleted)
 end