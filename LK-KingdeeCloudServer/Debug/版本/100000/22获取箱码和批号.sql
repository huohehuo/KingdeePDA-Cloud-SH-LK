if (exists (select * from sys.objects where name = 'proc_PDAGetDataBatchNo2'))
    drop proc proc_PDAGetDataBatchNo2
go
create proc proc_PDAGetDataBatchNo2
(
 @mainStr varchar(1000) 
 
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

declare @FID int,     --单号id 
        @Fdate datetime,       --日期  
        @FBillerID int,   --制单人id
        @FBillNo varchar(50),--单号 
        @FChang varchar(255),--长度
        @FHuang varchar(255),--宽
        @FHou varchar(255),--厚
          @FBatchNo varchar(255), --批次
        @FStockNumber varchar(255),--仓库代码
        @FWorkshopNumber varchar(255),--生产车间代码
        @FShortDate varchar(128),
          @FBatcnNoIndex varchar(128),--批号附加值
        @FRemark varchar(200)--摘要 
set @FBillerID = dbo.getString(@mainStr,'|',1) --操作员
 set @FChang =dbo.getString(@mainStr,'|',2)      --长 
set @FHuang =dbo.getString(@mainStr,'|',3)      --宽 
set @FHou =dbo.getString(@mainStr,'|',4)      --厚 
set @FStockNumber =dbo.getString(@mainStr,'|',5) --仓库代码
set @FWorkshopNumber =dbo.getString(@mainStr,'|',6)  --仓库代码
set @FBatcnNoIndex = dbo.getString(@mainStr,'|',7) --批号附加值
set @Fdate = convert(varchar(20),GETDATE(),20)
set @FShortDate =convert(nvarchar(60),getdate(),112)
------------------------------------------------------------得到箱码
declare  @FIndex int,  --(循环标) 
         @FNum varchar(50) --流水号
        set @FBillNo ='' 
 
select @FIndex=isnull(MAX(FIndex),0)+1 from t_AssembleRecord where FShortDate=@FShortDate
set @FNum='000000000'+CONVERT(varchar(20),@FIndex)
set @FNum=right(@FNum,6)  
set @FBillNo='ZZ'+@FShortDate+@FNum
 

INSERT INTO t_AssembleRecord(FBillNo,FIndex,FDate,FBillerID,FChang,FHuang,FHou,FStatus,FShortDate) values (@FBillNo,@FIndex,@FDate,@FBillerID,@FChang,@FHuang,@FHou,0,@FShortDate)
select @FID=MAX(FID) from t_AssembleRecord 
 
 --批号生成 
 declare @FDatePrintShort varchar(8),
         @FShortIndex varchar(50)
set @FDatePrintShort = RIGHT( convert(nvarchar(6),getdate(),112),4)
select @FIndex=isnull(max(FIndex),0)+1 from t_PDABatchNoIndex2 where FShortDate = @FDatePrintShort and FWorkshopNumber=@FWorkshopNumber and FStockNumber=@FStockNumber
if exists(select 1 from t_PDABatchNoIndex2 where FShortDate = @FDatePrintShort  and FWorkshopNumber=@FWorkshopNumber and FStockNumber=@FStockNumber)
begin
  update t_PDABatchNoIndex2 set FIndex=@FIndex where FShortDate = @FDatePrintShort  and FWorkshopNumber=@FWorkshopNumber and FStockNumber=@FStockNumber
end
else
  begin
   insert into t_PDABatchNoIndex2(FMATERIALID,FStockNumber,FWorkshopNumber,FShortDate,FIndex)
   values(0,@FStockNumber,@FWorkshopNumber,@FDatePrintShort,@FIndex)
  end
set @FShortIndex='000000000000'+convert(varchar(12),@FIndex)
set @FBatchNo = @FStockNumber+@FDatePrintShort+right(@FShortIndex,3)+@FBatcnNoIndex
--批号生成
 
------------------------------------------------------------得到箱码
 
create table #Tmp11111 --创建临时表#Tmp
( 
    FBillNo   varchar(255) 
) 
insert into #Tmp11111(FBillNo)values(@FBillNo)
select FBillNo as 箱码,@FBatchNo as 批号 from #Tmp11111
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
