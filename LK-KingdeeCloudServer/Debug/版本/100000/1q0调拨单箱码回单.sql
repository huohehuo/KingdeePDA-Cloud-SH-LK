if (exists (select * from sys.objects where name = 'proc_K3ProductPick1_Allot'))
drop proc proc_K3ProductPick1_Allot
go
create proc proc_K3ProductPick1_Allot
(
  @mainStr nvarchar(1000)--主表参数
 
)
as 
set nocount on     --开启一个模式，也就是不再刷新多少行受影响的信息，可以提高性能
set xact_abort on  --事务选项设置为出错全部回滚
begin tran declare
          @FDate varchar(20),            --单据日期   
          @FBillerID varchar(20),        --制单人ID 
          @FOrderID varchar(50),--PDA单据编号
          @FPDAID varchar(50),  --PDA序列号 
          @FInterID int,--单据内码
          @FBillNo varchar(128) --单据编号
        
          set @FBillerID=dbo.getString(@mainStr,'|',1)   -- 制单人ID 
          set @FBillNo=dbo.getString(@mainStr,'|',2)     --回单时返回的单据编号
          set @FOrderID=dbo.getString(@mainStr,'|',3)  --PDA单据编号
          set @FPDAID=dbo.getString(@mainStr,'|',4)      --PDA序号
               select @FInterID=FID from T_STK_STKTRANSFERIN where FBillNo=@FBillNo                                   
    set @Fdate = convert(varchar(20),GETDATE(),23)  
         
    insert into t_AssembleRecord1_Allot(FInterID,FBarCode,FQty,FBillNo,FRemark,FStockID_Before,FStockPlaceID_Before,FStockID_Now,FStockPlaceID_Now,FSTOCKORGID_Before,FOWNERID_Before,FSTOCKORGID_Now,FOWNERID_Now,FEntryID)
                                    select distinct @FInterID,C.FBillNo,1,@FBillNo,'箱码调拨单',b.FDCStockID,b.FDCSPID,a.FDCStockID,a.FDCSPID,b.FSTOCKORGID,b.FOWNERID,a.FSTOCKORGID,a.FOWNERID,b.FEntryID from t_AssembleRecord1Temp a left join t_AssembleRecord1 c on a.FBillNo = c.FBillNo left join t_AssembleRecord1Entry b on c.FID=b.FID where a.FOrderID=@FOrderID and a.FPDAID=@FPDAID
   update t_AssembleRecord1  set FInterIDAllot=@FInterID, FStatus = 0 from (select a.* from t_AssembleRecord1Temp a left join t_AssembleRecord1 b on a.FBillNo=b.FBillNo where a.FOrderID=@FOrderID and a.FPDAID=@FPDAID) as t where t_AssembleRecord1.FBillNo=t.FBillNo

update t_AssembleRecord1Entry  set FDCStockID=t.FDCStockID,FDCSPID=t.FDCSPID,FSTOCKORGID=t.FSTOCKORGID,FOWNERID=t.FOWNERID from (select a.*,c.FDCStockID as AAA,c.FDCSPID as BBB,b.FID from t_AssembleRecord1Temp a left join t_AssembleRecord1 b on a.FBillNo=b.FBillNo left join t_AssembleRecord1Entry c on b.FID = c.FID  where a.FOrderID=@FOrderID and a.FPDAID=@FPDAID) as t where t_AssembleRecord1Entry.FID=t.FID                      
delete from t_AssembleRecord1Temp where FOrderID=@FOrderID and FPDAID=@FPDAID   
commit tran 
return;
if(0<>@@error)
	goto error1
error1:
	rollback tran;