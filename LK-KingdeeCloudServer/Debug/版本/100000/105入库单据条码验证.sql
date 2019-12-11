if (exists (select * from sys.objects where name = 'proc_InStoreBarCode_check'))
    drop proc proc_InStoreBarCode_check
go
create proc proc_InStoreBarCode_check
(  
  @FBarCode varchar(128) --条码 
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
    FItemID int,--商品ID
    FUnitID int,--单位ID
    FQty decimal(28,10),--数量
    FStockID int,
    FStockPlaceID int,
    FBatchNo varchar(128),
    FKFPeriod int,
    FKFDate varchar(12),
    FBillNo   varchar(255),--单据编号
      FRemark3 varchar(255),--实际规格
     FRemark4 varchar(255),--辅助标识
      FRemark7 varchar(255),--生产编号
    FExplanation varchar(255),--说明
)
 
  if not exists(select 1 from t_PDABarCodeSign where FBarCode=@FBarCode)--判断条码是否存在系统中
  begin
      set @FExplanation='条码不存在系统' 
  end
  else
  begin
  	 if  exists(select 1 from a_DetailsTable where FBarCode=@FBarCode)--判断条码是否存在系统中
  	  set @FExplanation='该条码已发生业务关系'
  	 if exists(select 1 from t_PDABarCodeSign where FBarCode=@FBarCode and FIsInStore='已入库')
      set @FExplanation='该条码已入库'
  end
 
 
if(@FExplanation='OK')
begin
	insert into #Tmp11111(FItemID,FUnitID,FQty,FStockID,FStockPlaceID,FBatchNo,FKFPeriod,FKFDate,FBillNo,FExplanation,FRemark3,FRemark4,FRemark7)
	select top 1 FItemID,FUnitID,FQty,FStockID,FStockPlaceID,FBatchNo,FKFPeriod,FKFDate,FBillNo,@FExplanation,FRemark3,FRemark4,FRemark7 from t_PDABarCodeSign where FBarCode=@FBarCode
end
else
begin
insert into #Tmp11111(FExplanation)values(@FExplanation)
end 
declare @FNumber varchar(255)
select @FNumber=FNumber from t_PDABarCodeSign t1 left join T_BD_MATERIAL t2 on t1.FItemID =t2.FMaterialid  where FBarCode=@FBarCode
select FExplanation as 说明,FBillNo,@FNumber as FItemID,FUnitID,convert(float,FQty) as FQty,FStockID,FStockPlaceID,FBatchNo,FKFPeriod,FKFDate,FRemark3 as 实际规格,FRemark4 as 辅助标识,FRemark7 as 生产编号 from #Tmp11111
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
