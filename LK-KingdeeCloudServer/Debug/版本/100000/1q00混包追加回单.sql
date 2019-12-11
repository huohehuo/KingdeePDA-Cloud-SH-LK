if (exists (select * from sys.objects where name = 'proc_PDAAssembleCatch_Insert1'))
    drop proc proc_PDAAssembleCatch_Insert1
go
create proc proc_PDAAssembleCatch_Insert1
(
 @mainStr varchar(1000),
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

declare @FID int,
        @FDCStockID int,--仓库ID
        @FBatchNo varchar(255),--批号
        @FSTOCKORGID int,--库存组织
        @FOWNERID int,--货主
        
        @FBillerID int,   --制单人id
        @FBillNo varchar(50),--箱码  
        @FRemark varchar(200)--摘要 
set @FBillerID = dbo.getString(@mainStr,'|',1) --操作员
set @FBillNo = dbo.getString(@mainStr,'|',2) --箱码 
--装箱
select @FID= FID  from t_AssembleRecord1 where FBillNo=@FBillNo
select @FDCStockID=FDCStockID,@FBatchNo=FBatchNo,@FSTOCKORGID=FSTOCKORGID,@FOWNERID=FOWNERID from t_AssembleRecord1Entry where FID=@FID
declare @FEntryID varchar(20),       --明细序号  
        @FMATERIALID int,--商品ID
        @FUNITID int,--单位ID
        @FUNITNumber varchar(128),--单位名称
        @FWidth int,--宽度
        @FLength varchar(128),--长度
        @FLevel varchar(128),--等级
        @FVolume decimal(28,10),--体积
        @FQTY decimal(28,10), --数量
        @FBASEUNITID int,--基本单位
           @FCONVERTNUMERATOR decimal(20,8),--基本单位换算率
        @FCONVERTDENOMINATOR decimal(20,8),--辅助单位换算率
        @FMASTERID int, --商品IDID
        
        @detailqty int,               --明细参数的个数
        @detailcount int,             --明细每行数据的长度 
        @detailIndex int,            --明细每行下标
        @countindex int              --分隔符|的数量
      
       set @detailqty=0        
       set @detailcount=4   
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
    set @FMATERIALID=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+1)    --商品ID
    set @FUNITNumber=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+2)    --单位
	set @FQTY=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+3)    --数量 
    set @FVolume=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+4)    --体积
    select @FUNITID=FUNITID  from T_BD_UNIT where FNumber=@FUNITNumber
          select  @FMASTERID=FMASTERID  from T_BD_MATERIAL where  FMATERIALID=@FMATERIALID
	set @detailIndex=@detailIndex+1 
	   --基本单位数量
     if exists(select 1 from T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FUnitID)
      select @FVolume=@FQty*FCONVERTNUMERATOR/FCONVERTDENOMINATOR from T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FUnitID     
     else
       select @FVolume=@FQty*FCONVERTNUMERATOR/FCONVERTDENOMINATOR from T_BD_UNITCONVERTRATE where   FMASTERID=0 and FCURRENTUNITID=@FUnitID  
	select @FBASEUNITID=FBASEUNITID  from  t_BD_MaterialBase where FMATERIALID=@FMATERIALID
	
    select @FWidth=convert(int, dbo.getString(st33.FNumber,'.',6)),@FLength=dbo.getString(st33.FNumber,'.',7),@FLevel=dbo.getString(st33.FNumber,'.',4) from T_BD_MATERIAL st33 where  FMATERIALID=@FMATERIALID
    if exists(select 1 from t_AssembleRecord1Entry where FID=@FID and FItemID=@FMATERIALID)
    begin
       update t_AssembleRecord1Entry set FQty=FQty+@FQTY,FVolume=FVolume+@FVolume,FQtyCatch=isnull(FQtyCatch,0)+@FQty,FVolumeCatch=isnull(FVolumeCatch,0)+@FVolume where FID=@FID and FItemID=@FMATERIALID
    end
    else
    begin
    insert into t_AssembleRecord1Entry(FID,FItemID,FUnitID,FQty,FDCStockID,FDCSPID,FBatchNo,FSTOCKORGID,FOWNERID,FWidth,FLength,FLevel,FVolume,FQtyCatch,FVolumeCatch)
	values (@FID,@FMATERIALID,@FUNITID,@FQty,@FDCStockID,0,@FBatchNo,@FSTOCKORGID,@FOWNERID,@FWidth,@FLength,@FLevel,@FVolume,@FQty,@FVolume)
    end
  set @FEntryID=@detailqty*50+@detailIndex 
end
set @detailqty=@detailqty+1
end 
select t1.FType as 箱码类型,t1.FPackets1 as 分包号,t1.FWidth as 宽度,t1.FPackets as 包数,t1.FStockName as 仓库,convert(float, t6.FQty) as 总数量,convert(decimal(20,4),t6.FVolume) as 总面积,t1.FBillNo as 箱码,t1.FDate as 装箱日期,t3.FName as 制单人,t1.FName as 名称,t1.FHuZhu as 货主描述,t1.FCarNum as 车号,t5.FName as 单位, t4.FSPECIFICATION as 规格,t2.FBatchNo as 批号,t2.FLevel as 等级,convert(decimal(28,0),t2.FQty-isnull(t2.FQtyOut,0)) as 数量,convert(decimal(20,4),t2.FVolume-isnull(t2.FVolumeOut,0)) as 面积 from t_AssembleRecord1 t1 inner join t_AssembleRecord1entry t2 on t1.FID =t2.FID left join  T_SEC_user  t3 on t1.FBillerID=t3.FUSERID left join t_bd_material_l t4 on  t2.FItemID=t4.fmaterialid left join  T_BD_UNIT_L t5 ON  t2.FUNITID = t5.FUNITID inner join (select sum(FQty-isnull(FQtyOut,0)) as FQty,sum(FVolume-isnull(FVolumeOut,0)) as FVolume,FID from t_AssembleRecord1entry where FQty>isnull(FQtyOut,0)  group by FID) t6 on t1.FID =t6.FID  where t1.FBillNo = @FBillNo order by convert(int,t2.FWidth),t2.FLevel,convert(int,FLength) desc
 
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
