if (exists (select * from sys.objects where name = 'proc_GetQtyPDA'))
    drop proc proc_GetQtyPDA
go
create proc proc_GetQtyPDA
(  
  @FMATERIALID int,--商品ID
  @FUnitID int,--单位
  @FQty decimal(28,18)--
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
declare @FBASEUnitName varchar(128),--基本单位名称
        @FSTOREUnitName varchar(128),--库存单位名称
        @FAUXQty decimal(20,8),--辅助数量
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
      select @FSTOREUnitName=FName from T_BD_UNIT_L where FUnitID=@FSTOREUNITID
            select @FBASEUnitName=FName from T_BD_UNIT_L where FUnitID=@FBASEUNITID
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
     if(@FAUXUNITID=0)
     set @FAUXQty=0
     else
     begin
      if exists(select 1 from T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FAUXUNITID)
       select @FSTOREQty=@FBASEQty*FCONVERTDENOMINATOR/FCONVERTNUMERATOR  from  T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FSTOREUNITID   
      else
        select @FSTOREQty=@FBASEQty*FCONVERTDENOMINATOR/FCONVERTNUMERATOR  from  T_BD_UNITCONVERTRATE where   FMASTERID=0 and FCURRENTUNITID=@FSTOREUNITID   
     end
    select convert(float,@FBASEQty) as 基本单位数量,convert(float,@FSTOREQty) as 库存单位数量,@FBASEUnitName as 基本单位名称,@FSTOREUnitName as 库存单位名称 
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
