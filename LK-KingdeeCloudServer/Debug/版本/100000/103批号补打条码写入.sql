if (exists (select * from sys.objects where name = 'proc_PDABarCodeSignBatch_Insert'))
    drop proc proc_PDABarCodeSignBatch_Insert
go
create proc proc_PDABarCodeSignBatch_Insert
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
         @FCargoNumber varchar(128),--货主代码
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
       @FStockID int,--仓位ID
         @FRemark3 varchar(255),--实际规格,
         @FRemark4 varchar(255),--辅助标识,
             @FRemark5 varchar(255),--货主名称,
         @FRemark6 varchar(255),--辅助数量, 
         @FRemark8 varchar(255)--设备ID
set @FDatePrint=convert(varchar(128),getdate(),20)
set @FDatePrintShort = convert(varchar(128),getdate(),112)
set @FKFPeriod=0
set @FKFDate = ''
set @FPrintType = 'PDA批号补打'
set @FRemark1 ='PDA批号补打入库'
set @FRemark2 =''
set @FMATERIALID = dbo.getString(@mainStr,'|',1) --商品ID 
set @FUnitID = dbo.getString(@mainStr,'|',2) --单位ID 
set @FQty = dbo.getString(@mainStr,'|',3) --数量
set @FBatchNo = dbo.getString(@mainStr,'|',4) --批号
set @FRemark8 = dbo.getString(@mainStr,'|',5) --设备ID(PDA序列号)
 
set @FCargoNumber = dbo.getString(@mainStr,'|',6) --货主代码

set @FRemark3 = dbo.getString(@mainStr,'|',7) --实际规格
set @FRemark4 = dbo.getString(@mainStr,'|',8) --辅助标识
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
	set @FSTOREQty=@FQty
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
      if exists(select 1 from T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FAUXUNITID)
        select @FAUXQty=@FBASEQty*FCONVERTDENOMINATOR/FCONVERTNUMERATOR  from  T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FAUXUNITID   
      else
        select @FAUXQty=@FBASEQty*FCONVERTDENOMINATOR/FCONVERTNUMERATOR  from  T_BD_UNITCONVERTRATE where   FMASTERID=0 and FCURRENTUNITID=@FAUXUNITID   
     --库存单位数量
     --if(@FAUXUNITID=0)
     --set @FAUXQty=0
     --else
     --begin
     -- if exists(select 1 from T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FAUXUNITID)
     --  select @FSTOREQty=@FBASEQty*FCONVERTDENOMINATOR/FCONVERTNUMERATOR  from  T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FSTOREUNITID   
     -- else
     --   select @FSTOREQty=@FBASEQty*FCONVERTDENOMINATOR/FCONVERTNUMERATOR  from  T_BD_UNITCONVERTRATE where   FMASTERID=0 and FCURRENTUNITID=@FSTOREUNITID   
     --end
--辅助数量
--declare @FAUXQty decimal(20,8),--辅助数量
--        @FUnitQty decimal(20,8),--单位数量
--        @FAUXUNITID int,--辅助单位
--        @FMASTERID int --商品IDID
--      select  @FMASTERID=FMASTERID  from T_BD_MATERIAL where  FMATERIALID=@FMATERIALID
 
--   select  @FAUXUNITID=FAUXUNITID  from T_BD_MATERIALSTOCK where  FMATERIALID=@FMATERIALID
--   if(@FAUXUNITID=0)
--   set @FAUXQty = 0
--   else
--   begin 
--select @FUnitQty=@FQty*FCONVERTDENOMINATOR from T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FUnitID
--select @FAUXQty=@FUnitQty*FCONVERTDENOMINATOR from T_BD_UNITCONVERTRATE where    FMASTERID=@FMASTERID and FCURRENTUNITID=@FAUXUNITID
 
--if(@FAUXQty=0 or @FAUXQty is null)
--begin
-- set @FAUXQty=@FQty
--end
--   end
--
set @FRemark6 = @FAUXQty
select @FStockID=t0.FStockID from T_STK_INVENTORY t0 left join    T_BD_LOTMASTER st035 ON t0.FLOT = st035.FLOTID where st035.Fnumber = @FBatchNo
--条码生成
select @FIndex=isnull(max(FIndex),0)+1 from t_PDABarCodeSign where FItemID=@FMATERIALID and FDatePrintShort = @FDatePrintShort
set @FShortBarCode='000000000000'+convert(varchar(12),@FMATERIALID)
set @FShortIndex='000000000000'+convert(varchar(12),@FIndex)
set @FBarCode = right(@FShortBarCode,8)+@FDatePrintShort+right(@FShortIndex,6)
 
--条码生成
 insert into t_PDABarCodeSign(FIndex,FInterID,FEntryID,FItemID,FUnitID,FBarCode,FDatePrint,FQty,FBatchNo,FKFPeriod,FKFDate,FDatePrintShort,FPrintType,FBillNo,FInterIDAssemble,FPathLab,FRemark,FRemark1,FRemark2,FRemark3,FRemark4,FRemark5,FRemark6,FRemark8,FInterIDIn,FIsInStore,FStockID,FStockPlaceID)
                    values(@FIndex,0,0,@FMATERIALID,@FUnitID,@FBarCode,@FDatePrint,@FQty,@FBatchNo,@FKFPeriod,@FKFDate,@FDatePrintShort,@FPrintType,'',0,'','',@FRemark1,@FRemark2,@FRemark3,@FRemark4,@FRemark5,@FRemark6,@FRemark8,0,'已入库',@FStockID,0)
create table #Tmp11112 --创建临时表#Tmp
( 
    FBarCode varchar(128),
    FBatchNo varchar(128),
   
)
select @FSTOREQty=ROUND( @FSTOREQty,FPRECISION) from T_BD_UNIT   where FUNITID=@FSTOREUNITID
select @FBASEQty=ROUND( @FBASEQty,FPRECISION) from T_BD_UNIT   where FUNITID=@FBASEUNITID
insert into #Tmp11112(FBarCode,FBatchNo) values(@FBarCode,@FBatchNo)
select FBarCode as 条码,FBatchNo as 批号,convert(float,isnull(@FAUXQty,0)) as 辅助数量,convert(float,@FBASEQty) as 基本单位数量,convert(float,@FSTOREQty) as 库存单位数量 from #Tmp11112
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

