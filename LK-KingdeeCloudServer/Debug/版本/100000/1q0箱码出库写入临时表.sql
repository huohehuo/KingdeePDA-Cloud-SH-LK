if (exists (select * from sys.objects where name = 'proc_AssembleOut1_Insert'))
    drop proc proc_AssembleOut1_Insert
go
create proc proc_AssembleOut1_Insert
(
  @FOrderID varchar(255),--PDA单据号
  @FPDAID varchar(255),  --PDA序列号
  @FBillNo varchar(128) --箱码 
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
declare @FExplanation varchar(128) 
         
set @FExplanation='OK'


create table #Tmp11111 --创建临时表#Tmp
(
    FExplanation   varchar(255), 
)
set @FExplanation='OK'
insert into t_AssembleRecord1Temp(FOrderID,FPDAID,FBillNo,FItemID,FUnitID,FLength,FQty,FVolume,FDCStockID,FDCSPID,FBatchNo,FSTOCKORGID,FOWNERID)
select @FOrderID,@FPDAID,FBillNo,FItemID,FUnitID,FLength,FQty-isnull(FQtyOut,0),FVolume-isnull(FVolumeOut,0),FDCStockID,FDCSPID,FBatchNo,FSTOCKORGID,FOWNERID from t_AssembleRecord1 t1 inner join t_AssembleRecord1entry t2 on t1.FID =t2.FID where t2.FQty>isnull(t2.FQtyOut,0) AND t1.FBillNo =@FBillNo
insert into #Tmp11111(FExplanation)values(@FExplanation)
select FExplanation as 单据编号 from #Tmp11111
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
