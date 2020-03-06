if (exists (select * from sys.objects where name = 'proc_StockSearchQty'))
    drop proc proc_StockSearchQty
go
create proc proc_StockSearchQty
(
   @Type int,--0全部下载 1条件下载
   @FMATERIALID int,--商品ID
   @FSTOCKID int,--仓库ID
   @FSTOCKLOCID int,--仓位ID
   @FSTOCKORGID int,--库存组织
   @FBatchNo varchar(255),--批次
   @FOWNERID int,--货主 
   @FPRODUCEDATE varchar(128) --生产日期
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
declare  @FFLEXNUMBER varchar(128), -- 
         @FID int,
         @FStockPlaceID int 
    set @FStockPlaceID= @FSTOCKLOCID
   create table #temp111
   (
      FID int IDENTITY (1, 1) ,
      FMATERIALID int,--商品ID
      FSTOCKID int,--仓库ID
      FSTOCKORGID int,--库存组织
      FFLEXNUMBER varchar(128),--关联仓位列
      FBatchNo varchar(255),--批次
      FOWNERID int,--货主
      FSTOCKLOCID int,--仓位ID
      FStockPlaceID int,--仓位ID
      FPRODUCEDATE varchar(128), --生产日期
      FSUPPLIERID int,
      FSTOCKSTATUSID int,
      FCUSTID int,
      FBASEQTY decimal(28,8)--数量
   
   )
   create table #temp112
  (
     YY int
  )
   if(@Type = 0)
   begin
   insert into #temp111(FSTOCKORGID,FSTOCKLOCID,FFLEXNUMBER,FPRODUCEDATE,FSUPPLIERID,FCUSTID,FOWNERID,FMATERIALID,FSTOCKID,FBatchNo,FBASEQTY,FSTOCKSTATUSID)
   select distinct t0.FSTOCKORGID,t0.FSTOCKLOCID,isnull(t0.FFLEXNUMBER,''),convert(varchar(20),t0.FPRODUCEDATE,23),isnull(t_20.FSUPPLIERID,
   0),isnull(t_22.FCUSTID,0),t0.FOWNERID,t0.FMATERIALID,t0.FSTOCKID,st035.FNUMBER, T0.FBASEQTY,t0.FSTOCKSTATUSID from ( select tB.FFLEXNUMBER, t0.FSTOCKSTATUSID,t0.FSTOCKORGID,t0.FAUXPROPID, t0.FSTOCKLOCID,t0.FPRODUCEDATE, t0.FOWNERID,t0.FMATERIALID,t0.FSTOCKID,t0.FLot, sum(CAST(CASE  WHEN (T2.FSTOREURNOM = 0 OR T2.FSTOREURNUM = 0) THEN T0.FBASEQTY ELSE (CAST((T0.FBASEQTY * T2.FSTOREURNOM) AS NUMERIC(23, 10)) / T2.FSTOREURNUM) END AS NUMERIC(23, 10))) as FBASEQTY   from T_STK_INVENTORY t0 left join T_BD_MATERIALSTOCK t2 on t0.FMATERIALID =t2.FMATERIALID   left join T_BD_STOCKFLEXITEM tA on tA.FStockID = t0.FStockID left join T_BAS_FLEXVALUES tB on tA.FFlexId = tB.FId   where  1=1  group by t0.FOWNERID,t0.FMATERIALID,t0.FAUXPROPID,t0.FSTOCKID,t0.FLot,t0.FSTOCKSTATUSID,t0.FSTOCKORGID,t0.FSTOCKLOCID,t0.FPRODUCEDATE,t0.FSTOCKSTATUSID,tB.FFLEXNUMBER having sum(CAST(CASE  WHEN (T2.FSTOREURNOM = 0 OR T2.FSTOREURNUM = 0) THEN T0.FBASEQTY ELSE (CAST((T0.FBASEQTY * T2.FSTOREURNOM) AS NUMERIC(23, 10)) / T2.FSTOREURNUM) END AS NUMERIC(23, 10)))>0) t0
left join T_BD_MATERIALSTOCK t2 on t0.FMATERIALID=t2.FMATERIALID  LEFT OUTER JOIN T_BD_LOTMASTER st035 ON t0.FLOT=st035.FLOTID LEFT join t_BD_Supplier t_20  on t_20.FSUPPLIERID=t0.FOWNERID left join t_BD_Customer t_22  on t_22.FCUSTID=t0.FOWNERID   left join T_BD_MATERIALSTOCK t31 on t31.fmaterialid=t0.fmaterialid  left join T_BD_UNIT_L t5U on t31.FSTOREUNITID=t5U.FUnitID  left join T_BD_UNIT t100 on t5U.FUNITID=t100.FUNITID  
   end
   else
   begin
   insert into #temp111(FSTOCKORGID,FSTOCKLOCID,FFLEXNUMBER,FPRODUCEDATE,FSUPPLIERID,FCUSTID,FOWNERID,FMATERIALID,FSTOCKID,FBatchNo,FBASEQTY,FSTOCKSTATUSID)
   select distinct t0.FSTOCKORGID,t0.FSTOCKLOCID,isnull(t0.FFLEXNUMBER,''),convert(varchar(20),t0.FPRODUCEDATE,23),isnull(t_20.FSUPPLIERID,
   0),isnull(t_22.FCUSTID,0),t0.FOWNERID,t0.FMATERIALID,t0.FSTOCKID,st035.FNUMBER, T0.FBASEQTY,t0.FSTOCKSTATUSID from ( select tB.FFLEXNUMBER, t0.FSTOCKSTATUSID,t0.FSTOCKORGID,t0.FAUXPROPID, t0.FSTOCKLOCID,t0.FPRODUCEDATE, t0.FOWNERID,t0.FMATERIALID,t0.FSTOCKID,t0.FLot, sum(CAST(CASE  WHEN (T2.FSTOREURNOM = 0 OR T2.FSTOREURNUM = 0) THEN T0.FBASEQTY ELSE (CAST((T0.FBASEQTY * T2.FSTOREURNOM) AS NUMERIC(23, 10)) / T2.FSTOREURNUM) END AS NUMERIC(23, 10))) as FBASEQTY   from T_STK_INVENTORY t0 left join T_BD_MATERIALSTOCK t2 on t0.FMATERIALID =t2.FMATERIALID   left join T_BD_STOCKFLEXITEM tA on tA.FStockID = t0.FStockID left join T_BAS_FLEXVALUES tB on tA.FFlexId = tB.FId   where  1=1  group by t0.FOWNERID,t0.FMATERIALID,t0.FAUXPROPID,t0.FSTOCKID,t0.FLot,t0.FSTOCKSTATUSID,t0.FSTOCKORGID,t0.FSTOCKLOCID,t0.FPRODUCEDATE,t0.FSTOCKSTATUSID,tB.FFLEXNUMBER having sum(CAST(CASE  WHEN (T2.FSTOREURNOM = 0 OR T2.FSTOREURNUM = 0) THEN T0.FBASEQTY ELSE (CAST((T0.FBASEQTY * T2.FSTOREURNOM) AS NUMERIC(23, 10)) / T2.FSTOREURNUM) END AS NUMERIC(23, 10)))>0) t0
left join T_BD_MATERIALSTOCK t2 on t0.FMATERIALID=t2.FMATERIALID  LEFT OUTER JOIN T_BD_LOTMASTER st035 ON t0.FLOT=st035.FLOTID LEFT join t_BD_Supplier t_20  on t_20.FSUPPLIERID=t0.FOWNERID left join t_BD_Customer t_22  on t_22.FCUSTID=t0.FOWNERID   left join T_BD_MATERIALSTOCK t31 on t31.fmaterialid=t0.fmaterialid  left join T_BD_UNIT_L t5U on t31.FSTOREUNITID=t5U.FUnitID  left join T_BD_UNIT t100 on t5U.FUNITID=t100.FUNITID  where   t0.FMATERIALID =@FMATERIALID and t0.FSTOCKID =@FSTOCKID and st035.FNUMBER =@FBatchNo and t0.FSTOCKORGID =@FSTOCKORGID and (t0.FOWNERID=@FOWNERID or t_20.FSUPPLIERID=@FOWNERID or t_22.FCUSTID=@FOWNERID)   and isnull(t0.FPRODUCEDATE,'') =@FPRODUCEDATE
   end
  declare @FStockPlaceID1 int
   if exists(select 1 from #temp111 where FSTOCKLOCID>0)
   begin
       declare My_cursor cursor dynamic --scroll表示可以向前或向后移动    dynamic：表示可写也可读
         for 
         select FSTOCKLOCID,FFLEXNUMBER,FID from #temp111 where FSTOCKLOCID>0
         open My_cursor      
         fetch next from my_cursor into  @FSTOCKLOCID,@FFLEXNUMBER,@FID --游标停在第一条记录前面，第一次执行，测试有没有记录存在,
         while(@@FETCH_STATUS = 0) --取数据 0表示成功执行FETCH语句  -1 表示FETCH语句失败，例如移动行指针使其超出了结果集 -2 表示被提取的行不存在。
         begin    
      delete from #temp112
         exec (  'insert into #temp112 select  top 1  t.'+@FFLEXNUMBER+'  from T_BAS_FLEXVALUESDETAIL t where FID='+@FSTOCKLOCID+'')
 	     select @FStockPlaceID1=YY from #temp112
 	  
 	     update #temp111 set FStockPlaceID=@FStockPlaceID1 where FID=@FID
 	 fetch next from my_cursor into @FSTOCKLOCID,@FFLEXNUMBER,@FID --再次将游标停在第一条记录前面，第一次执行，测试有没有记录存在,估测也是为@@FETCH_STATUS赋值
				 end  
				 close my_cursor
				 deallocate my_cursor  
   end
 
   if (@Type=0)
   begin
     select  distinct t0.FSTOCKORGID,isnull(t0.FStockPlaceID,0) as 仓位ID,isnull(t0.FPRODUCEDATE,'')  as 生产日期,t0.FSUPPLIERID,t0.FCUSTID,t0.FOWNERID as 货主ID,t0.FMATERIALID as 商品ID,t0.FSTOCKID as 仓库ID,t0.FBatchNo as 批号, T0.FBASEQTY  as 库存,t0.FSTOCKSTATUSID as 库存状态,t0.FSTOCKORGID as 库存组织ID  from #temp111 t0
   end
   else
   begin
          select  distinct t0.FSTOCKORGID,isnull(t0.FStockPlaceID,0) as 仓位ID,isnull(t0.FPRODUCEDATE,'') as 生产日期,t0.FSUPPLIERID,t0.FCUSTID,t0.FOWNERID as 货主ID,t0.FMATERIALID as 商品ID,t0.FSTOCKID as 仓库ID,t0.FBatchNo as 批号, T0.FBASEQTY  as 库存,t0.FSTOCKSTATUSID as 库存状态,t0.FSTOCKORGID as 库存组织ID  from #temp111 t0
 where isnull(FStockPlaceID,0)=@FStockPlaceID
   end
   drop table #temp111 
   drop table #temp112
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

