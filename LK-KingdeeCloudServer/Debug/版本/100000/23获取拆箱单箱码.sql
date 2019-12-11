if (exists (select * from sys.objects where name = 'proc_PDAGetDataAssemble2'))
    drop proc proc_PDAGetDataAssemble2
go
create proc proc_PDAGetDataAssemble2
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
        @FStockNumber varchar(255),--仓库代码
        @FWorkshopNumber varchar(255),--生产车间代码
        @FShortDate varchar(128),
        @FRemark varchar(200)--摘要 
set @FBillerID = dbo.getString(@mainStr,'|',1) --操作员
 set @FChang =dbo.getString(@mainStr,'|',2)      --长 
set @FHuang =dbo.getString(@mainStr,'|',3)      --宽 
set @FHou =dbo.getString(@mainStr,'|',4)      --厚  
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
  
------------------------------------------------------------得到箱码
 
create table #Tmp11111 --创建临时表#Tmp
( 
    FBillNo   varchar(255) 
) 
insert into #Tmp11111(FBillNo)values(@FBillNo)
select FBillNo as 箱码 from #Tmp11111
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
