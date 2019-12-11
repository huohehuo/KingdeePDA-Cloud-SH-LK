if (exists (select * from sys.objects where name = 'proc_AssembleOut_check'))
    drop proc proc_AssembleOut_check
go
create proc proc_AssembleOut_check
(  
  @FBillNo varchar(128)  --箱码 
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
  FItemID int not null,   --商品id
  FUnitID int not null,   --单位id
  FLength varchar(128),--长度
  FQty decimal(28,10), --数量
  FVolume decimal(28,10), --面积 

  FDCStockID int, --仓库id
  FDCSPID int, --仓位id
  FBatchNo varchar(255), --批次
  FSTOCKORGID int,--库存组织ID
  FOWNERID int,  --货主
  FQtyAll decimal(28,10), --总数量
    FVolumeAll decimal(28,10), --总面积
    FAssmble varchar(128),--箱码
    FName varchar(255),--名称
    FModel varchar(255),--规格 
    FExplanation varchar(255) --说明
)

 if not exists(select 1 from t_AssembleRecord where FBillNo=@FBillNo)--判断条码是否存在系统中
 begin
   set @FExplanation='该箱码不存在系统' 
 end
 else
 begin
  if  exists(select 1 from t_AssembleRecordTemp where FBillNo=@FBillNo)--判断条码是否存在系统中
      set @FExplanation='该箱码已发生业务关系'
  else
     if exists(select 1 from t_AssembleRecord where FBillNo=@FBillNo and FStatus = 2)
     set @FExplanation='该箱码已出库'
 end
insert into #Tmp11111(FExplanation,FItemID,FUnitID,FLength,FQty,FVolume,FDCStockID,FDCSPID,FBatchNo,FSTOCKORGID,FOWNERID,FQtyAll,FVolumeAll,FAssmble,FName,FModel)	
select @FExplanation,t2.FItemID,t2.FUnitID,t2.FLength,convert(decimal(28,0),t2.FQty-isnull(t2.FQtyOut,0)),convert(float,t2.FVolume-isnull(t2.FVolumeOut,0)),t2.FDCStockID,t2.FDCSPID,t2.FBatchNo,t2.FSTOCKORGID,t2.FOWNERID,convert(float, t6.FQty),convert(float,t6.FVolume),t1.FBillNo,t4.FName,(t1.FChang+'x'+t1.FHuang+'x'+t1.FHou)  from t_AssembleRecord t1 inner join t_AssembleRecordentry t2 on t1.FID =t2.FID left join  T_SEC_user  t3 on t1.FBillerID=t3.FUSERID left join t_bd_material_l t4 on  t2.FItemID=t4.fmaterialid left join  T_BD_UNIT_L t5 ON  t2.FUNITID = t5.FUNITID inner join (select sum(FQty-isnull(FQtyOut,0)) as FQty,sum(FVolume-isnull(FVolumeOut,0)) as FVolume,FID from t_AssembleRecordentry where FQty>isnull(FQtyOut,0) group by FID) t6 on t1.FID =t6.FID  where t2.FQty>isnull(t2.FQtyOut,0) AND t1.FBillNo =@FBillNo order by FEntryID

if not exists(select 1 from #Tmp11111)
begin
insert into #Tmp11111(FExplanation)values(@FExplanation)
end
select t1.FExplanation as 说明,convert(float,t1.FQtyAll) as 总数量,convert(float,t1.FVolumeAll) as 总体积,t1.FName as 名称,t1.FModel as 规格,t1.FLength as 长度,t1.FBatchNo as 批号,convert(float,t1.FQty) as 数量,convert(float,t1.FVolume) as 面积,t1.FItemID as 商品ID,t1.FUnitID as 单位ID,t1.FDCStockID as 仓库ID,t1.FDCSPID as 仓位ID,t1.FSTOCKORGID as 库存组织ID,t1.FOWNERID as 货主ID from #Tmp11111 t1
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
