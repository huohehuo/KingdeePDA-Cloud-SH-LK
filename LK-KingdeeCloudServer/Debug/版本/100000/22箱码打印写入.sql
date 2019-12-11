if (exists (select * from sys.objects where name = 'proc_PDACountOff_Insert2'))
    drop proc proc_PDACountOff_Insert2
go
create proc proc_PDACountOff_Insert2
(
 @mainStr varchar(1000),--主表参数
 @detailStr1 varchar(max),--明细参数
 @detailStr2 varchar(max),
 @detailStr3 varchar(max),
 @detailStr4 varchar(max),
 @detailStr5 varchar(max)
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
        @FRemark varchar(200)--摘要 
set @FBillerID = dbo.getString(@mainStr,'|',1) --操作员
 set @FChang =dbo.getString(@mainStr,'|',2)      --长 
set @FHuang =dbo.getString(@mainStr,'|',3)      --宽 
set @FHou =dbo.getString(@mainStr,'|',4)      --厚 
set @FStockNumber =dbo.getString(@mainStr,'|',5) --仓库编码
set @FWorkshopNumber =dbo.getString(@mainStr,'|',6)  --生产车间编码
set @FBillNo =dbo.getString(@mainStr,'|',7)  --箱码 
set @FBatchNo =dbo.getString(@mainStr,'|',8) --批号

set @Fdate = convert(varchar(20),GETDATE(),20)
    if not exists(select 1 from t_AssembleRecord where FStatus = 1 and FBillNo=@FBillNo)
    begin
update t_AssembleRecord set FStatus = 1 where FBillNo=@FBillNo
select @FID= FID  from t_AssembleRecord where FBillNo=@FBillNo
 
 declare @FEntryID varchar(20),       --明细序号
         
         @FCountOff varchar(50),--报数
  @FItemID int,   --商品id
  @FUnitID int,   --单位id
  @FLength varchar(128),--长度
  @FQty decimal(28,10), --数量
   @FVolume decimal(28,10), --体积
  @FDCStockID int, --仓库id
  @FDCSPID int, --仓位id 
  @FSTOCKORGID int,--库存组织ID
       @FOWNERID int,  --货主
      @FVolumeAll decimal(28,10),--总面积
      @FQtyAll decimal(28,10),--总数量
     
      
        @detailqty int,               --明细参数的个数
        @detailcount int,             --明细每行数据的长度 
        @detailIndex int,            --明细每行下标
        @countindex int              --分隔符|的数量
         set @FVolumeAll = 0
         set @FQtyAll = 0
       set @detailqty=0        
       set @detailcount=9   
    while(@detailqty<5)--判断是明细的哪个参数
    begin
    if(@detailqty=1)
	begin
	set @detailStr1=@detailStr2
	end  
	if(@detailqty=2)
	begin
	set @detailStr1=@detailStr3
	end 
	if(@detailqty=3)
	begin
	set @detailStr1=@detailStr4
	end 
	if(@detailqty=4)
	begin
	set @detailStr1=@detailStr5
	end 
	if(@detailStr1='' or @detailStr1=null)
	begin
	break;
	end
	set @detailIndex=0 
	select @countindex=len(@detailStr1)-len(replace(@detailStr1, '|', ''))
	
	while(@countindex>@detailIndex*@detailcount)
	begin
	set @FItemID=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+1)    --商品id
	set @FUnitID=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+2)    --单位id 
	set @FQty=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+3)    --数量 
    set @FDCStockID=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+4)    --仓库
    set @FDCSPID=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+5)    --仓位
    set @FSTOCKORGID =dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+6)    --库存组织ID
    set @FOWNERID=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+7)    --货主ID
    set @FLength=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+8)    --长度
        set @FVolume=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+9)    --面积
	set @detailIndex=@detailIndex+1
	set @FVolumeAll = @FVolumeAll+@FVolume
	set @FQtyAll = @FQtyAll +@FQty
	
    insert into t_AssembleRecordEntry(FID,FItemID,FUnitID,FLength,FQty,FDCStockID,FDCSPID,FBatchNo,FSTOCKORGID,FOWNERID,FVolume)
	values(@FID,@FItemID,@FUnitID,@FLength,@FQty,@FDCStockID,@FDCSPID,@FBatchNo,@FSTOCKORGID,@FOWNERID,@FVolume)
  set @FEntryID=@detailqty*50+@detailIndex 
end
set @detailqty=@detailqty+1
end
 select convert(float,@FQtyAll) as 总数量,convert(float,@FVolumeAll) as 总面积,t1.FBillNo as 箱码,t1.FDate as 装箱日期,t3.FName as 制单人,t4.FName as 名称,t5.FName as 单位,(t1.FChang+'x'+t1.FHuang+'x'+t1.FHou) as 规格,t2.FBatchNo as 批号,t2.FLength as 长度,convert(decimal(28,0),t2.FQty) as 数量,convert(float,t2.FVolume) as 面积 from t_AssembleRecord t1 left join t_AssembleRecordentry t2 on t1.FID =t2.FID left join  T_SEC_user  t3 on t1.FBillerID=t3.FUSERID 
 left join t_bd_material_l t4 on  t2.FItemID=t4.fmaterialid left join  T_BD_UNIT_L t5 ON  t2.FUNITID = t5.FUNITID where t1.FID=@FID
end 
	else
	begin
	select convert(float, t6.FQty) as 总数量,convert(float,t6.FVolume) as 总面积,t1.FBillNo as 箱码,t1.FDate as 装箱日期,t3.FName as 制单人,t4.FName as 名称,t5.FName as 单位,(t1.FChang+'x'+t1.FHuang+'x'+t1.FHou) as 规格,t2.FBatchNo as 批号,t2.FLength as 长度,convert(decimal(28,0),t2.FQty-isnull(t2.FQtyOut,0)) as 数量,convert(float,t2.FVolume-isnull(t2.FVolumeOut,0)) as 面积 from t_AssembleRecord t1 inner join t_AssembleRecordentry t2 on t1.FID =t2.FID left join  T_SEC_user  t3 on t1.FBillerID=t3.FUSERID left join t_bd_material_l t4 on  t2.FItemID=t4.fmaterialid left join  T_BD_UNIT_L t5 ON  t2.FUNITID = t5.FUNITID inner join (select sum(FQty-isnull(FQtyOut,0)) as FQty,sum(FVolume-isnull(FVolumeOut,0)) as FVolume,FID from t_AssembleRecordentry where FQty>isnull(FQtyOut,0)  group by FID) t6 on t1.FID =t6.FID  where t1.FBillNo = @FBillNo order by FEntryID
	end
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
