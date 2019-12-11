if (exists (select * from sys.objects where name = 'proc_AssembleAndBarCodeOut1_check'))
    drop proc proc_AssembleAndBarCodeOut1_check
go
create proc proc_AssembleAndBarCodeOut1_check
(  
  @FBillNo varchar(128),  --箱码 
  @FTypeID int,--单据类型 1为销售订单下推销售出库单
  @FID int --订单单号ID 
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
declare @FExplanation varchar(128) 
      
 set @FExplanation='OK'
create table #Tmp11111 --创建临时表#Tmp
(   
   FItemID int,--商品ID
    FUnitID int,--单位ID
    FQty decimal(28,10),--数量
    FStockID int,
    FStockPlaceID int,
    FSTOCKORGID int,--库存组织ID
    FOWNERID int,  --货主
    FBatchNo varchar(128), --条码 
    FExplanation varchar(255),--说明
 
)
--if(left(@FBillNo,2)='ZZ') --箱码验证
--begin
 if not exists(select 1 from t_AssembleRecord1 where FBillNo=@FBillNo)--判断条码是否存在系统中
 begin
   set @FExplanation='该箱码不存在系统' 
 end
 else
 begin
  if  exists(select 1 from t_AssembleRecord1Temp where FBillNo=@FBillNo)--判断条码是否存在系统中
      set @FExplanation='该箱码已发生业务关系'
  else
     if exists(select 1 from t_AssembleRecord1 where FBillNo=@FBillNo and FStatus = 2)
     set @FExplanation='该箱码已出库'
 end
 if(@FExplanation='OK')
 begin
   declare  @FItemID int,--商品ID
    @FUnitID int,--单位ID
    @FQty decimal(28,10),--数量
    @FDCStockID int,
    @FDCSPID int,
    @FSTOCKORGID int,--库存组织ID
    @FNumber varchar(255),--物料编码
    @FOWNERID int,  --货主
    @FBatchNo varchar(128) --条码 
    declare My_cursor cursor dynamic --scroll表示可以向前或向后移动    dynamic：表示可写也可读
         for 
       select  t3.FNumber,t2.FItemID,t2.FUnitID,t2.FQty-isnull(t2.FQtyOut,0) as FQty,t2.FDCStockID,t2.FDCSPID,t2.FSTOCKORGID,t2.FOWNERID,t2.FBatchNo from  t_AssembleRecord1 t1 inner join t_AssembleRecord1entry t2 on t1.FID =t2.FID left join T_BD_MATERIAL t3 on t3.FMATERIALID =t2.FItemID  where t1.FBillNo=@FBillNo
         open My_cursor      
         fetch next from my_cursor into @FNumber,@FItemID,@FUnitID,@FQty,@FDCStockID,@FDCSPID,@FSTOCKORGID,@FOWNERID,@FBatchNo --游标停在第一条记录前面，第一次执行，测试有没有记录存在,
         while(@@FETCH_STATUS = 0) --取数据 0表示成功执行FETCH语句  -1 表示FETCH语句失败，例如移动行指针使其超出了结果集 -2 表示被提取的行不存在。
         begin 
       
         if(@FID>0)
         begin 
         if not exists(select 1 from T_SAL_ORDEREntry t1 left join T_BD_MATERIAL t2 on t1.FMATERIALID = t2.FMATERIALID where FID =@FID and t2.FNumber = @FNumber)
         begin
       
         
            set @FExplanation = @FNumber + '物料不在明细中..'
         end
         else
         begin
           if not exists(select 1 from ( select  sum( CAST(CASE  WHEN (T2.FSTOREURNOM = 0  OR T2.FSTOREURNUM = 0) THEN T0.FBASEQTY ELSE (CAST((T0.FBASEQTY * T2.FSTOREURNOM) AS NUMERIC(23, 10)) / T2.FSTOREURNUM) END AS NUMERIC(23, 10))) as FQty  from T_STK_INVENTORY t0 left join T_BD_MATERIALSTOCK t2 on t0.FMATERIALID=t2.FMATERIALID left join T_BD_MATERIAL t100  on t0.FMATERIALID=t100.FMATERIALID LEFT OUTER JOIN T_BD_LOTMASTER st035 ON t0.FLOT=st035.FLOTID LEFT join t_BD_Supplier t_20  on t_20.FSUPPLIERID=t0.FOWNERID left join t_BD_Customer t_22  on t_22.FCUSTID=t0.FOWNERID   where  1=1 and t100.FNumber = @FNumber and st035.FNUMBER = @FBatchNo and  t0.FSTOCKID = @FDCStockID and  t0.FOWNERID = @FOWNERID and t0.FSTOCKORGID = @FSTOCKORGID  group by t0.FMATERIALID,t0.FSTOCKID,st035.FNUMBER,FSTOCKSTATUSID,t0.FSTOCKSTATUSID,t0.FSTOCKORGID,t0.FOWNERID,t_20.FSUPPLIERID,t_22.FCUSTID ) t where t.FQty-@FQty>=0 )
            begin 
            set @FExplanation = @FNumber + '物料库存不足明细中..'
            end
         end
         end
         insert into #Tmp11111(FItemID,FUnitID,FQty,FStockID,FStockPlaceID,FSTOCKORGID,FOWNERID,FBatchNo,FExplanation)values(@FItemID,@FUnitID,@FQty,@FDCStockID,@FDCSPID,@FSTOCKORGID,@FOWNERID,@FBatchNo,@FExplanation)
         set @FExplanation = 'OK'
         fetch next from my_cursor into @FNumber,@FItemID,@FUnitID,@FQty,@FDCStockID,@FDCSPID,@FSTOCKORGID,@FOWNERID,@FBatchNo --再次将游标停在第一条记录前面，第一次执行，测试有没有记录存在,估测也是为@@FETCH_STATUS赋值
	      end  
				 close my_cursor
				 deallocate my_cursor  
         
  
 end
--end
--else  --唯一码验证
--begin
-- if not exists(select 1 from t_PDABarCodeSign where FBarCode=@FBillNo)--判断条码是否存在系统中
-- begin
--   set @FExplanation='条码不存在系统' 
-- end
-- else
-- begin 
--  if not exists(select 1 from t_PDABarCodeSign where FBarCode=@FBillNo and FIsInStore='已入库')
--  begin
--   set @FExplanation='未入库条码不能出库'
--  end
--  else
--  begin
--    declare @FQtyAll decimal(28,10)
--    select @FQtyAll=isnull(sum(FQty),0) from a_DetailsTable where FBarCode=@FBillNo
--    if exists(select 1 from t_PDABarCodeSign where FBarCode=@FBillNo and FQty-isnull(FQtyOut,0)-@FQtyAll<=0) 
--     set @FExplanation='该条码完全出库,不能再次出库'
--  end
--  	insert into #Tmp11111(FItemID,FUnitID,FQty,FStockID,FStockPlaceID,FBatchNo,FExplanation,FRemark3,FRemark4,FRemark5)
--	select top 1 FItemID,FUnitID,FQty-isnull(FQtyOut,0)-@FQtyAll,FStockID,FStockPlaceID,FBatchNo,@FExplanation,FRemark3,FRemark4,FRemark5  from t_PDABarCodeSign where FBarCode=@FBillNo
-- end
--end
if not exists(select 1 from #Tmp11111)
begin
insert into #Tmp11111(FExplanation)values(@FExplanation)
end
 if exists(select 1 from #Tmp11111 where FExplanation<>'OK')
 begin
 delete from #Tmp11111 where FExplanation='OK'
 end
select t41.FName as 仓库名称,t21.FName as 商品名称,t21.FSPECIFICATION as 规格,t1.FExplanation as 说明,t2.FNumber as 商品编码,t1.FBatchNo as 批号,convert(float, t1.FQty) as 数量,t3.FNumber as 单位编码,t4.FNumber as 仓库编码,t5.FNumber as 库存组织编码,t6.FNumber as 货主编码 from #Tmp11111 t1 left join  T_BD_MATERIAL t2 ON t2.FMATERIALID = t1.FItemID  left join  T_BD_MATERIAL_L t21 ON t21.FMATERIALID = t1.FItemID left join T_BD_UNIT t3 on t3.FUNITID = t1.FUnitID left join t_BD_Stock  t4 on t4.FSTOCKID  = t1.FStockID left join t_BD_Stock_L  t41 on t41.FSTOCKID  = t1.FStockID left join T_ORG_Organizations t5 on t5.FORGID = t1.FSTOCKORGID left join T_ORG_Organizations t6 on t6.FORGID = t1.FOWNERID 
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
