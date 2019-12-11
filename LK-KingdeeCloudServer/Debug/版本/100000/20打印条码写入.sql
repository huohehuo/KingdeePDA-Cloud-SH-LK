if (exists (select * from sys.objects where name = 'proc_PDABarCodeSign_Insert2'))
    drop proc proc_PDABarCodeSign_Insert2
go
create proc proc_PDABarCodeSign_Insert2
(
 @mainStr nvarchar(1000) --主表参数
  
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
declare  @FShortBarCode varchar(128),--短条码
         @FShortIndex varchar(128),--短流水号
         @FStockNumber varchar(128),--仓库代码
         @FCargoNumber varchar(128),--货主代码
         @FProductNumber varchar(128),--生产车间编码
         @FIsAdd int,--是否新增
         @FIndex int,--流水号 
         @FMATERIALID int,--商品ID
         @FUnitID int,--单位ID,
         @FBarCode varchar(128),--条码  
         @FDatePrint varchar(128),--打印日期  
         @FQty decimal(28,18),--数量,
         @FBatchNo varchar(125),--批号  
         @FKFPeriod int,@FKFDate varchar(12),
         @FDatePrintShort varchar(128),--短日期  
         @FPrintType varchar(128),--赋值
         @FRemark1 varchar(128),--入库类型
         @FRemark2 varchar(128),--出库类型,
         @FRemark3 varchar(255),--实际规格,
         @FRemark4 varchar(255),--辅助标识,
             @FRemark5 varchar(255),--货主名称,
          @F_FFF_TEXT1 varchar(128),--等级   
          @F_FFF_INTEGER decimal(20,8), --原木长
          @F_FFF_INTEGER1 decimal(20,8), --原木直径
          @F_FFF_INTEGER2 decimal(20,8), --板长
          @F_FFF_INTEGER3 decimal(20,8), --板宽
          @F_FFF_INTEGER4 decimal(20,8), --板厚
          @F_FFF_Volume decimal(20,8), --体积
           @FBatcnNoIndex varchar(128),--批号附加值
          @FWidth varchar(502),
          @F_TypeID int,
          @F_Plies  decimal(20,8),--层数
         @FRemark6 varchar(255),--辅助数量,
         @FRemark7 varchar(255),--生产编号
         @FRemark8 varchar(255)--设备ID
set @FDatePrint=convert(varchar(128),getdate(),20)
set @FDatePrintShort = convert(varchar(128),getdate(),112)
set @FKFPeriod=0
set @FKFDate = ''
set @FPrintType = 'PDA产品入库'
set @FRemark1 =''
set @FRemark2 =''
set @FMATERIALID = dbo.getString(@mainStr,'|',1) --商品ID 
set @FUnitID = dbo.getString(@mainStr,'|',2) --单位ID 
set @FQty = dbo.getString(@mainStr,'|',3) --数量
set @FRemark3 = dbo.getString(@mainStr,'|',4) --实际规格
set @FRemark4 = dbo.getString(@mainStr,'|',5) --辅助标识
set @FRemark7 = dbo.getString(@mainStr,'|',6) --生产编号
set @FRemark8 = dbo.getString(@mainStr,'|',7) --设备ID(PDA序列号)
set @FStockNumber = dbo.getString(@mainStr,'|',8) --仓库代码
set @FCargoNumber = dbo.getString(@mainStr,'|',9) --货主代码
 
set @F_FFF_TEXT1 = dbo.getString(@mainStr,'|',10)  --等级
set @F_FFF_INTEGER = dbo.getString(@mainStr,'|',11)  --原木长
set @F_FFF_INTEGER1 = dbo.getString(@mainStr,'|',12) --原木直径
set @F_FFF_INTEGER2 = dbo.getString(@mainStr,'|',13) --板长
set @F_FFF_INTEGER3 = dbo.getString(@mainStr,'|',14) --板宽
set @F_FFF_INTEGER4 = dbo.getString(@mainStr,'|',15) --板厚
set @F_FFF_Volume = dbo.getString(@mainStr,'|',16) --体积
set @FBatchNo = dbo.getString(@mainStr,'|',17) --批号
set @FProductNumber = dbo.getString(@mainStr,'|',18) --生产车间编码
set @FIsAdd = dbo.getString(@mainStr,'|',19) --自动生成批号 1是 0不是
set @FWidth = dbo.getString(@mainStr,'|',20) --宽的所有值
set @F_TypeID = dbo.getString(@mainStr,'|',21)-- 区分单据类型 0 是水版 1是其他
set @F_Plies = dbo.getString(@mainStr,'|',22)-- 层数
set @FBatcnNoIndex = dbo.getString(@mainStr,'|',23) --批号附加值

set @FRemark5=@FCargoNumber
--SELECT @FRemark5 = t0_L.FNAME   FROM T_ORG_Organizations t0 LEFT OUTER JOIN T_ORG_Organizations_L t0_L ON (t0.FORGID = t0_L.FORGID AND t0_L.FLocaleId = 2052) where t0.FNUMBER=@FCargoNumber
--辅助数量
declare @FAUXQty decimal(20,8),--辅助数量
        @FUnitQty decimal(20,8),--单位数量
        @FAUXUNITID int,--辅助单位
        @FSTOREUNITID int,--库存单位
        @FSTOREQty decimal(20,8),--库存单位数量
        @FBASEUNITID int,--基本单位
        @FBASEQty decimal(20,8),--基本单位数量
        @FCONVERTNUMERATOR decimal(20,8),--基本单位换算率
        @FCONVERTDENOMINATOR decimal(20,8),--辅助单位换算率
        @FMASTERID int --商品IDID
      select  @FMASTERID=FMASTERID  from T_BD_MATERIAL where  FMATERIALID=@FMATERIALID
      select @FBASEUNITID=FBASEUNITID from  t_BD_MaterialBase where  FMATERIALID=@FMATERIALID
      select  @FAUXUNITID=FAUXUNITID,@FSTOREUNITID=FSTOREUNITID  from T_BD_MATERIALSTOCK where  FMATERIALID=@FMATERIALID
     --基本单位数量
     if exists(select 1 from T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FUnitID)
      select @FBASEQty=@FQty*FCONVERTNUMERATOR/FCONVERTDENOMINATOR from T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FUnitID     
     else
       select @FBASEQty=@FQty*FCONVERTNUMERATOR/FCONVERTDENOMINATOR from T_BD_UNITCONVERTRATE where   FMASTERID=0 and FCURRENTUNITID=@FUnitID     
     if exists(select 1 from T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FUnitID)
     --辅助单位数量
		  if(@FAUXUNITID=0)
		 set @FAUXQty=0
		 else
		 begin
      if exists(select 1 from T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FAUXUNITID)
        select @FAUXQty=@FBASEQty*FCONVERTDENOMINATOR/FCONVERTNUMERATOR  from  T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FAUXUNITID   
      else
        select @FAUXQty=@FBASEQty*FCONVERTDENOMINATOR/FCONVERTNUMERATOR  from  T_BD_UNITCONVERTRATE where   FMASTERID=0 and FCURRENTUNITID=@FAUXUNITID   
        end
     --库存单位数量 
      if exists(select 1 from T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FSTOREUNITID)
       select @FSTOREQty=@FBASEQty*FCONVERTDENOMINATOR/FCONVERTNUMERATOR  from  T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FSTOREUNITID   
      else
        select @FSTOREQty=@FBASEQty*FCONVERTDENOMINATOR/FCONVERTNUMERATOR  from  T_BD_UNITCONVERTRATE where   FMASTERID=0 and FCURRENTUNITID=@FSTOREUNITID   
   --  end
set @FRemark6 = @FAUXQty
--批号生成
if(@FIsAdd = 1)
begin
	if exists(select 1  from T_BD_MATERIALSTOCK where FISBATCHMANAGE =1)
	begin
	select @FIndex=isnull(max(FIndex),0)+1 from t_PDABatchNoIndex where FShortDate = @FDatePrintShort
	if exists(select 1 from t_PDABatchNoIndex where FShortDate = @FDatePrintShort)
	begin
	  update t_PDABatchNoIndex set FIndex=@FIndex where FShortDate = @FDatePrintShort
	end
	else
	  begin
	   insert into t_PDABatchNoIndex(FMATERIALID,FStockNumber,FCargoNumber,FShortDate,FIndex)
	   values(@FMATERIALID,@FProductNumber,@FCargoNumber,@FDatePrintShort,@FIndex)
	  end
	set @FShortIndex='000000000000'+convert(varchar(12),@FIndex) 
	set @FBatchNo = @FProductNumber+@FDatePrintShort+right(@FShortIndex,3)+@FBatcnNoIndex
	end
	else
	begin
	set @FBatchNo=''
	end
end
--批号生成
--条码生成
select @FIndex=isnull(max(FIndex),0)+1 from t_PDABarCodeSign where FItemID=@FMATERIALID and FDatePrintShort = @FDatePrintShort
set @FShortBarCode='000000000000'+convert(varchar(12),@FMATERIALID)
set @FShortIndex='000000000000'+convert(varchar(12),@FIndex)
set @FBarCode = right(@FShortBarCode,8)+@FDatePrintShort+right(@FShortIndex,6)
 
--条码生成
 insert into t_PDABarCodeSign(FIndex,FInterID,FEntryID,FItemID,FUnitID,FBarCode,FDatePrint,FQty,FBatchNo,FKFPeriod,FKFDate,FDatePrintShort,FPrintType,FBillNo,FInterIDAssemble,FPathLab,FRemark,FRemark1,FRemark2,FRemark3,FRemark4,FRemark5,FRemark6,FRemark7,FRemark8,F_FFF_TEXT1,F_FFF_INTEGER,F_FFF_INTEGER1,F_FFF_INTEGER2,F_FFF_INTEGER3,F_FFF_INTEGER4,F_FFF_Volume,FWidth,F_TypeID,F_Plies)
                    values(@FIndex,0,0,@FMATERIALID,@FUnitID,@FBarCode,@FDatePrint,@FQty,@FBatchNo,@FKFPeriod,@FKFDate,@FDatePrintShort,@FPrintType,'',0,'','',@FRemark1,@FRemark2,@FRemark3,@FRemark4,@FRemark5,@FRemark6,@FRemark7,@FRemark8,@F_FFF_TEXT1,@F_FFF_INTEGER,@F_FFF_INTEGER1,@F_FFF_INTEGER2,@F_FFF_INTEGER3,@F_FFF_INTEGER4,@F_FFF_Volume,@FWidth,@F_TypeID,@F_Plies)
create table #Tmp11112 --创建临时表#Tmp
( 
    FBarCode varchar(128),
    FBatchNo varchar(128),
   
)
select @FSTOREQty=ROUND( @FSTOREQty,FPRECISION) from T_BD_UNIT   where FUNITID=@FSTOREUNITID
select @FBASEQty=ROUND( @FBASEQty,FPRECISION) from T_BD_UNIT   where FUNITID=@FBASEUNITID
insert into #Tmp11112(FBarCode,FBatchNo) values(@FBarCode,@FBatchNo)
select FBarCode as 条码,FBatchNo as 批号,convert(float,isnull(@FAUXQty,0)) as 辅助数量,convert(float,@FBASEQty) as 基本单位数量,convert(float,@FSTOREQty) as 库存单位数量  from #Tmp11112
drop table #Tmp11112 
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

