if (exists (select * from sys.objects where name = 'proc_StockSearchQty'))
    drop proc proc_StockSearchQty
go
create proc proc_StockSearchQty
(
   @Type int,--0ȫ������ 1��������
   @FMATERIALID int,--��ƷID
   @FSTOCKID int,--�ֿ�ID
   @FSTOCKLOCID int,--��λID
   @FSTOCKORGID int,--�����֯
   @FBatchNo varchar(255),--����
   @FOWNERID int,--���� 
   @FPRODUCEDATE varchar(128) --��������
)
as 
--------------����һ��ģʽ��Ҳ���ǲ���ˢ�¶�������Ӱ�����Ϣ�������������
set nocount on
--------------��ʼ�洢����
begin
--------------����ѡ������Ϊ����ȫ���ع�
set xact_abort on
--------------��������
begin tran
declare  @FFLEXNUMBER varchar(128), -- 
         @FID int,
         @FStockPlaceID int 
    set @FStockPlaceID= @FSTOCKLOCID
   create table #temp111
   (
      FID int IDENTITY (1, 1) ,
      FMATERIALID int,--��ƷID
      FSTOCKID int,--�ֿ�ID
      FSTOCKORGID int,--�����֯
      FFLEXNUMBER varchar(128),--������λ��
      FBatchNo varchar(255),--����
      FOWNERID int,--����
      FSTOCKLOCID int,--��λID
      FStockPlaceID int,--��λID
      FPRODUCEDATE varchar(128), --��������
      FSUPPLIERID int,
      FSTOCKSTATUSID int,
      FCUSTID int,
      FBASEQTY decimal(28,8)--����
   
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
       declare My_cursor cursor dynamic --scroll��ʾ������ǰ������ƶ�    dynamic����ʾ��дҲ�ɶ�
         for 
         select FSTOCKLOCID,FFLEXNUMBER,FID from #temp111 where FSTOCKLOCID>0
         open My_cursor      
         fetch next from my_cursor into  @FSTOCKLOCID,@FFLEXNUMBER,@FID --�α�ͣ�ڵ�һ����¼ǰ�棬��һ��ִ�У�������û�м�¼����,
         while(@@FETCH_STATUS = 0) --ȡ���� 0��ʾ�ɹ�ִ��FETCH���  -1 ��ʾFETCH���ʧ�ܣ������ƶ���ָ��ʹ�䳬���˽���� -2 ��ʾ����ȡ���в����ڡ�
         begin    
      delete from #temp112
         exec (  'insert into #temp112 select  top 1  t.'+@FFLEXNUMBER+'  from T_BAS_FLEXVALUESDETAIL t where FID='+@FSTOCKLOCID+'')
 	     select @FStockPlaceID1=YY from #temp112
 	  
 	     update #temp111 set FStockPlaceID=@FStockPlaceID1 where FID=@FID
 	 fetch next from my_cursor into @FSTOCKLOCID,@FFLEXNUMBER,@FID --�ٴν��α�ͣ�ڵ�һ����¼ǰ�棬��һ��ִ�У�������û�м�¼����,����Ҳ��Ϊ@@FETCH_STATUS��ֵ
				 end  
				 close my_cursor
				 deallocate my_cursor  
   end
 
   if (@Type=0)
   begin
     select  distinct t0.FSTOCKORGID,isnull(t0.FStockPlaceID,0) as ��λID,isnull(t0.FPRODUCEDATE,'')  as ��������,t0.FSUPPLIERID,t0.FCUSTID,t0.FOWNERID as ����ID,t0.FMATERIALID as ��ƷID,t0.FSTOCKID as �ֿ�ID,t0.FBatchNo as ����, T0.FBASEQTY  as ���,t0.FSTOCKSTATUSID as ���״̬,t0.FSTOCKORGID as �����֯ID  from #temp111 t0
   end
   else
   begin
          select  distinct t0.FSTOCKORGID,isnull(t0.FStockPlaceID,0) as ��λID,isnull(t0.FPRODUCEDATE,'') as ��������,t0.FSUPPLIERID,t0.FCUSTID,t0.FOWNERID as ����ID,t0.FMATERIALID as ��ƷID,t0.FSTOCKID as �ֿ�ID,t0.FBatchNo as ����, T0.FBASEQTY  as ���,t0.FSTOCKSTATUSID as ���״̬,t0.FSTOCKORGID as �����֯ID  from #temp111 t0
 where isnull(FStockPlaceID,0)=@FStockPlaceID
   end
   drop table #temp111 
   drop table #temp112
commit tran 
return;
--------------���ڴ���
if(0<>@@error)
	goto error1
--------------�ع�����	
error1:
	rollback tran;
--------------�����洢����
end

