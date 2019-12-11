if (exists (select * from sys.objects where name = 'proc_K3AllotPushAllotOut'))
drop proc proc_K3AllotPushAllotOut
go
create proc proc_K3AllotPushAllotOut
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
          select @FInterID=FID from T_STK_STKTRANSFEROUT where FBillNo=@FBillNo 
    set @Fdate = convert(varchar(20),GETDATE(),23)                                       
insert into t_PDABarCodeSign_Allot(FInterID,FBarCode,FQty,FBillNo,FRemark,FStockID_Before,FStockPlaceID_Before,FStockID_Now,FStockPlaceID_Now)
select @FInterID,b.FBarCode,a.FQty,@FBillNo,'调拨申请单下推分布式调出单',b.FStockID,b.FStockPlaceID,a.FStockID,a.FStockPlaceID from a_DetailsTable a left join t_PDABarCodeSign b on a.FBarCode=b.FBarCode where FOrderID=@FOrderID and FPDAID=@FPDAID
update t_PDABarCodeSign  set FStockID=t.FStockID,FStockPlaceID=t.FStockPlaceID  from (select a.*,b.FStockID as AAA,b.FStockPlaceID as BBB from a_DetailsTable a left join t_PDABarCodeSign b on a.FBarCode=b.FBarCode where FOrderID=@FOrderID and FPDAID=@FPDAID) as t where t_PDABarCodeSign.FBarCode=t.FBarCode

delete from a_DetailsTable where FOrderID=@FOrderID and FPDAID=@FPDAID
   
commit tran 
return;
if(0<>@@error)
	goto error1
error1:
	rollback tran;