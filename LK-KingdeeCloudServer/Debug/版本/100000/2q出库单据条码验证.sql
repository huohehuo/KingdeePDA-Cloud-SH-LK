if (exists (select * from sys.objects where name = 'proc_OutStoreBarCode_check2'))
    drop proc proc_OutStoreBarCode_check2
go
create proc proc_OutStoreBarCode_check2
(  
  @FBarCode varchar(128)  --条码 
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
declare @FExplanation varchar(128),
        @FQtyAll decimal(28,10) --PDA已添加数量
      
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
     FRemark5 varchar(128),--货主编码
      FRemark7 varchar(255),--生产编号
    FExplanation varchar(255),--说明
    F_TypeID int,--条码类型
        F_Plies decimal(20,8),--层数
    F_FFF_TEXT1 varchar(255),
    F_FFF_INTEGER varchar(255),
    F_FFF_INTEGER1  decimal(28,10),
    F_FFF_INTEGER2  decimal(28,10),
    F_FFF_INTEGER3  decimal(28,10),
    F_FFF_INTEGER4  decimal(28,10),
    F_FFF_Volume  decimal(28,10)
)

 if not exists(select 1 from t_PDABarCodeSign where FBarCode=@FBarCode)--判断条码是否存在系统中
 begin
   set @FExplanation='条码不存在系统' 
 end
 else
 begin
  --if  exists(select 1 from a_DetailsTable where FBarCode=@FBarCode)--判断条码是否存在系统中
  --    set @FExplanation='该条码已发生业务关系'
  if not exists(select 1 from t_PDABarCodeSign where FBarCode=@FBarCode and FIsInStore='已入库')
  begin
   set @FExplanation='未入库条码不能出库'
  end
  else
  begin
    select @FQtyAll=isnull(sum(FQty),0) from a_DetailsTable where FBarCode=@FBarCode
    if exists(select 1 from t_PDABarCodeSign where FBarCode=@FBarCode and FQty-isnull(FQtyOut,0)-@FQtyAll<=0)--and FIsOutStore='已出库'
     set @FExplanation='该条码完全出库,不能再次出库'
  end
 end
	insert into #Tmp11111(FItemID,FUnitID,FQty,FStockID,FStockPlaceID,FBatchNo,FKFPeriod,FKFDate,FBillNo,FExplanation,FRemark3,FRemark4,FRemark5,FRemark7,F_FFF_TEXT1,F_FFF_INTEGER,F_FFF_INTEGER1,F_FFF_INTEGER2,F_FFF_INTEGER3,F_FFF_INTEGER4,F_FFF_Volume,F_TypeID,F_Plies)
	select top 1 FItemID,FUnitID,FQty-isnull(FQtyOut,0)-@FQtyAll,FStockID,FStockPlaceID,FBatchNo,FKFPeriod,FKFDate,FBillNo,@FExplanation,FRemark3,FRemark4,FRemark5,FRemark7,F_FFF_TEXT1,F_FFF_INTEGER,F_FFF_INTEGER1,F_FFF_INTEGER2,F_FFF_INTEGER3,F_FFF_INTEGER4,F_FFF_Volume,F_TypeID,F_Plies from t_PDABarCodeSign where FBarCode=@FBarCode
if not exists(select 1 from #Tmp11111)
begin
insert into #Tmp11111(FExplanation)values(@FExplanation)
end
declare @FNumber varchar(255)
select @FNumber=FNumber from t_PDABarCodeSign t1 left join T_BD_MATERIAL t2 on t1.FItemID =t2.FMaterialid  where FBarCode=@FBarCode
select FExplanation as 说明,FBillNo,@FNumber as FItemID,FUnitID,convert(float,FQty) as FQty,FStockID,FStockPlaceID,FBatchNo,FKFPeriod,FKFDate,FRemark3 as 实际规格,FRemark4 as 辅助标识,FRemark5 as 货主编码,FRemark7 as 生产编号,F_FFF_TEXT1 as 等级,convert(decimal(28,2),ISNull(F_FFF_INTEGER,0)) as 原木长,convert(decimal(28,2),ISNull(F_FFF_INTEGER1,0)) as 原木直径,convert(decimal(28,0),ISNull(F_FFF_INTEGER2,0)) as 板长,convert(decimal(28,0),ISNull(F_FFF_INTEGER3,0)) as 板宽,convert(decimal(28,0),ISNull(F_FFF_INTEGER4,0)) as 板厚,convert(float,ISNull(F_FFF_Volume,0)) as 体积,isnull(F_TypeID,1) as 条码类型,convert(int,isnull( F_Plies,0)) as 层数 from #Tmp11111
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
