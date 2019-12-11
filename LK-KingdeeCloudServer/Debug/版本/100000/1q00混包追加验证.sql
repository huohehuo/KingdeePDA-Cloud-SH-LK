if (exists (select * from sys.objects where name = 'proc_AssembleCatch1_check'))
    drop proc proc_AssembleCatch1_check
go
create proc proc_AssembleCatch1_check
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
  --FItemID int not null,   --商品id
  --FUnitID int not null,   --单位id
  --FLength varchar(128),--长度
  --FQty decimal(28,10), --数量
  --FVolume decimal(28,10), --面积 

  --FDCStockID int, --仓库id
  --FDCSPID int, --仓位id
  --FBatchNo varchar(255), --批次
  --FSTOCKORGID int,--库存组织ID
  --FOWNERID int,  --货主
  --FQtyAll decimal(28,10), --总数量
  --FVolumeAll decimal(28,10), --总面积
  FAssmble varchar(128),--箱码
  --FName varchar(255),--名称
  --FModel varchar(255),--规格 
  --FWidth varchar(128),--宽度
  FExplanation varchar(255) --说明
) 
if not exists(select 1 from t_AssembleRecord1 where FBillNo=@FBillNo)--判断条码是否存在系统中
begin
   set @FExplanation='该箱码不存在系统,不能追加' 
end 
if exists(select 1 from t_AssembleRecord1 where FBillNo=@FBillNo and FStatus = 2)
 set @FExplanation='该箱码已出库,不能追加'
 
insert into #Tmp11111(FExplanation,FAssmble)values(@FExplanation,@FBillNo)

if not exists(select 1 from #Tmp11111)
begin
insert into #Tmp11111(FExplanation)values(@FExplanation)
end
select t0.FExplanation as 说明,t1.FType as 箱码类型,t1.FCarNum as 车牌号,t1.FPackets as 包数,t1.FPackets1 as 分包号,t1.FWidth as 宽度,convert(decimal(28,0),t2.FQty-isnull(t2.FQtyOut,0)) as 数量,convert(float,t2.FVolume-isnull(t2.FVolumeOut,0)) as 体积,t2.FLevel as 等级,t4.FSPECIFICATION as 规格,t7.FNumber as 仓库编码,t2.FBatchNo as 批号,t8.FNumber as 库存组织编码,t9.Fnumber as 货主编码 from #Tmp11111 t0 left join t_AssembleRecord1 t1 on t0.FAssmble = t1.FBillNo left join t_AssembleRecord1entry t2 on t1.FID =t2.FID   left join (select sum(FQty-isnull(FQtyOut,0)) as FQty,sum(FVolume-isnull(FVolumeOut,0)) as FVolume,FID from t_AssembleRecord1entry where FQty>isnull(FQtyOut,0) group by FID) t6 on t1.FID =t6.FID left join t_bd_material_l t4 on  t2.FItemID=t4.fmaterialid  left join t_BD_Stock t7 on t7.FSTOCKID = t2.FDCStockID left join T_ORG_Organizations t8 on t8.FORGID = t2.FSTOCKORGID left join T_ORG_Organizations t9 on t9.FORGID = t2.FOWNERID   order by FEntryID
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
