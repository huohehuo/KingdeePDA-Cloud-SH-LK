if (exists (select * from sys.objects where name = 'proc_PDACountOff_Split2'))
    drop proc proc_PDACountOff_Split2
go
create proc proc_PDACountOff_Split2
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
      @FSourceID int,
       @FSourceFEntryID int,
      
        @detailqty int,               --明细参数的个数
        @detailcount int,             --明细每行数据的长度 
        @detailIndex int,            --明细每行下标
        @countindex int              --分隔符|的数量
         set @FVolumeAll = 0
         set @FQtyAll = 0
       set @detailqty=0        
       set @detailcount=11   
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
     set @FSourceID=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+10)    --FID
    set @FSourceFEntryID=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+11)    --FEntryID
    
	set @detailIndex=@detailIndex+1
	set @FVolumeAll = @FVolumeAll+@FVolume
	set @FQtyAll = @FQtyAll +@FQty
	update t_AssembleRecordEntry set FQtyOut =isnull(FQtyOut,0) + @FQty,FVolumeOut =isnull(FVolumeOut,0) + @FVolume where FID = @FSourceID and FEntryID =@FSourceFEntryID
        insert into t_AssembleRecordEntry_C(FID,FItemID,FUnitID,FLength,FQty,FDCStockID,FDCSPID,FBatchNo,FSTOCKORGID,FOWNERID,FVolume,FSourceID,FSourceFEntryID)
	values(@FID,@FItemID,@FUnitID,@FLength,@FQty,@FDCStockID,@FDCSPID,@FBatchNo,@FSTOCKORGID,@FOWNERID,@FVolume,@FSourceID,@FSourceFEntryID)
    insert into t_AssembleRecordEntry(FID,FItemID,FUnitID,FLength,FQty,FDCStockID,FDCSPID,FBatchNo,FSTOCKORGID,FOWNERID,FVolume,FQtyOut,FVolumeOut)
	values(@FID,@FItemID,@FUnitID,@FLength,@FQty,@FDCStockID,@FDCSPID,@FBatchNo,@FSTOCKORGID,@FOWNERID,@FVolume,0,0)
  set @FEntryID=@detailqty*50+@detailIndex 
end
set @detailqty=@detailqty+1
end
 select convert(float,@FQtyAll) as 总数量,convert(float,@FVolumeAll) as 总面积,t1.FBillNo as 箱码,t1.FDate as 装箱日期,t3.FName as 制单人,t4.FName as 名称,t5.FName as 单位,(t1.FChang+'x'+t1.FHuang+'x'+t1.FHou) as 规格,t2.FBatchNo as 批号,t2.FLength as 长度,convert(decimal(28,0),t2.FQty) as 数量,convert(float,t2.FVolume) as 面积 from t_AssembleRecord t1 left join t_AssembleRecordentry t2 on t1.FID =t2.FID left join  T_SEC_user  t3 on t1.FBillerID=t3.FUSERID 
 left join t_bd_material_l t4 on  t2.FItemID=t4.fmaterialid left join  T_BD_UNIT_L t5 ON  t2.FUNITID = t5.FUNITID where t1.FID=@FID
 
--create table #Tmp11111 --创建临时表#Tmp
--(
--    FBillNo   varchar(255) 
--) 
--insert into #Tmp11111(FBillNo)values(@FBillNo)
--select FBillNo as 箱码,@FBatchNo as 批号 from #Tmp11111
--drop table #Tmp11111
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
