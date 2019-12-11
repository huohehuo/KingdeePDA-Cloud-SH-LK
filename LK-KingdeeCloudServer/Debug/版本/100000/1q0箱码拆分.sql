if (exists (select * from sys.objects where name = 'proc_PDACountOff_Split1'))
    drop proc proc_PDACountOff_Split1
go
create proc proc_PDACountOff_Split1
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
          @FAssembleBillNo varchar(128),--拆箱的箱号
          @FBillNo varchar(128),
        @FShortDate varchar(128),
        @FBillerID int,   --制单人id 
        @FRemark varchar(200)--摘要 
set @FBillerID = dbo.getString(@mainStr,'|',1) --操作员
set @FAssembleBillNo =  dbo.getString(@mainStr,'|',2) --拆箱的箱号

if not exists(select 1 from t_AssembleRecord1 where FBillNo = @FAssembleBillNo)
begin
print convert(int,'拆分失败,后台不存在该箱码')
end

set @Fdate = convert(varchar(20),GETDATE(),20)

set @FShortDate =convert(nvarchar(60),getdate(),112)

------------------------------------------------------------得到箱码
declare  @FIndex int,  --(循环标) 
         @FNum varchar(50) --流水号
        set @FBillNo ='' 
 
select @FIndex=isnull(MAX(FIndex),0)+1 from t_AssembleRecord1 where FShortDate=@FShortDate
set @FNum='000000000'+CONVERT(varchar(20),@FIndex)
set @FNum=right(@FNum,6)  
set @FBillNo='ZZ'+@FShortDate+@FNum
INSERT INTO t_AssembleRecord1(FBillNo,FIndex,FDate,FBillerID,FStatus,FShortDate,FCarNum,FHuZhu,FName,FSourceID,FType,FStockName,FPackets,FWidth) select @FBillNo,@FIndex,@FDate,@FBillerID,0,@FShortDate,FCarNum,FHuZhu,FName,FSourceID,FType,FStockName,FPackets,FWidth from  t_AssembleRecord1 where FBillNo = @FAssembleBillNo
select @FID=MAX(FID) from t_AssembleRecord1

 declare @FEntryID varchar(20),       --明细序号  
  @FQty decimal(28,10), 
        @detailqty int,               --明细参数的个数
        @detailcount int,             --明细每行数据的长度 
        @detailIndex int,            --明细每行下标
        @countindex int              --分隔符|的数量
        
       set @detailqty=0        
       set @detailcount=2   
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
	set @FQty=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+1)    --数量 
  
    set @FEntryID=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+2) --明细唯一序号  
    
	set @detailIndex=@detailIndex+1
 
	update t_AssembleRecord1Entry set FQtyOut =isnull(FQtyOut,0) + @FQty,FVolumeOut =isnull(FVolumeOut,0) +convert(decimal(20,4), @FQty/FQty*FVolume) where FEntryID =@FEntryID
	    insert into t_AssembleRecord1Entry(FID,FItemID,FUnitID,FWidth,FLength,FLevel,FQty,FDCStockID,FDCSPID,FBatchNo,FSTOCKORGID,FOWNERID,FVolume,FQtyOut,FVolumeOut)
	select @FID,FItemID,FUnitID,FWidth,FLength,FLevel,@FQty,FDCStockID,FDCSPID,FBatchNo,FSTOCKORGID,FOWNERID,convert(decimal(20,4), @FQty/FQty*FVolume),0,0 from t_AssembleRecord1Entry where FEntryID =@FEntryID
	
        insert into t_AssembleRecord1Entry_C(FID,FItemID,FUnitID,FWidth,FLength,FLevel,FQty,FDCStockID,FDCSPID,FBatchNo,FSTOCKORGID,FOWNERID,FVolume,FSourceFEntryID)
	select @FID,FItemID,FUnitID,FWidth,FLength,FLevel,@FQty,FDCStockID,FDCSPID,FBatchNo,FSTOCKORGID,FOWNERID,convert(decimal(20,4), @FQty/FQty*FVolume),FEntryID from t_AssembleRecord1Entry where FEntryID =@FEntryID
  
  set @FEntryID=@detailqty*50+@detailIndex 
end
set @detailqty=@detailqty+1
end

select t1.FType as 箱码类型,t1.FStockName as 仓库,t1.FPackets as 包数,t1.FWidth as 宽度,convert(float, t6.FQty) as 总数量,convert(decimal(20,4),t6.FVolume) as 总面积,t1.FBillNo as 箱码,t1.FDate as 装箱日期,t3.FName as 制单人,t1.FName as 名称,t1.FHuZhu as 货主描述,t1.FCarNum as 车号,t5.FName as 单位, t4.FSPECIFICATION as 规格,t2.FBatchNo as 批号,t2.FLevel as 等级,convert(decimal(28,0),t2.FQty-isnull(t2.FQtyOut,0)) as 数量,convert(decimal(20,4),t2.FVolume-isnull(t2.FVolumeOut,0)) as 面积 from t_AssembleRecord1 t1 inner join t_AssembleRecord1entry t2 on t1.FID =t2.FID left join  T_SEC_user  t3 on t1.FBillerID=t3.FUSERID left join t_bd_material_l t4 on  t2.FItemID=t4.fmaterialid left join  T_BD_UNIT_L t5 ON  t2.FUNITID = t5.FUNITID inner join (select sum(FQty-isnull(FQtyOut,0)) as FQty,sum(FVolume-isnull(FVolumeOut,0)) as FVolume,FID from t_AssembleRecord1entry where FQty>isnull(FQtyOut,0)  group by FID) t6 on t1.FID =t6.FID  where t1.FBillNo = @FBillNo order by convert(int,t2.FWidth),t2.FLevel,convert(int,FLength) desc
 
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
