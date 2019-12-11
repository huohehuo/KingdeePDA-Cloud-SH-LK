if (exists (select * from sys.objects where name = 'proc_OutStoreBarCode_Insert'))
    drop proc proc_OutStoreBarCode_Insert
go
create proc proc_OutStoreBarCode_Insert
(
  @FOrderID varchar(255),--PDA单据号
  @FPDAID varchar(255),  --PDA序列号
  @FBarCode varchar(128), --条码
  @FQty decimal(20,8) --数量
)
as 
--------------开启一个模式，也就是不再刷新多少行受影响的信息，可以提高性能
set nocount on
--------------开始存储过程
begin
--------------事务选项设置为出错全部回滚
set xact_abort on
--------------开启事务
begin tran
declare @FBillNo varchar(128) 
         
set @FBillNo='OK'
if exists(select 1 from a_DetailsTable where FBarCode=@FBarCode and FOrderID=@FOrderID and FPDAID=@FPDAID)
begin
  update  a_DetailsTable set FQty=FQty+@FQty where FBarCode=@FBarCode and FOrderID=@FOrderID and FPDAID=@FPDAID
end
else
begin
      insert into a_DetailsTable(FPDAID,FOrderID,FBarCode,FItemID,FStockID,FStockPlaceID, FBatchNo,  FKFPeriod,  FKFDate,  FQty)
        select top 1  @FPDAID,@FOrderID, FBarCode, FItemID,FStockID, FStockPlaceID,  FBatchNo,  FKFPeriod,  FKFDate,  @FQty from t_PDABarCodeSign where FBarCode=@FBarCode
 end
 
 
 create table #Tmp11111 --创建临时表#Tmp
(
    FBillNo   varchar(255), 
)
set @FBillNo='OK'
 
insert into #Tmp11111(FBillNo)values(@FBillNo)
select FBillNo as 单据编号 from #Tmp11111
drop table #Tmp11111
commit tran 
return;
--------------存在错误
if(0<>@@error)
	goto error1
--------------回滚事务	
error1:
	rollback tran;
--------------结束存储过程
end
