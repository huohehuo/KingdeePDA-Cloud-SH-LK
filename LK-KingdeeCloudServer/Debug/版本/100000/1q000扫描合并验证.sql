if (exists (select * from sys.objects where name = 'proc_AssembleAndBarCodeOut1_check'))
    drop proc proc_AssembleAndBarCodeOut1_check
go
create proc proc_AssembleAndBarCodeOut1_check
(  
  @FBillNo varchar(128),  --���� 
  @FTypeID int,--�������� 1Ϊ���۶����������۳��ⵥ
  @FID int --��������ID 
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
declare @FExplanation varchar(128) 
      
 set @FExplanation='OK'
create table #Tmp11111 --������ʱ��#Tmp
(   
   FItemID int,--��ƷID
    FUnitID int,--��λID
    FQty decimal(28,10),--����
    FStockID int,
    FStockPlaceID int,
    FSTOCKORGID int,--�����֯ID
    FOWNERID int,  --����
    FBatchNo varchar(128), --���� 
    FExplanation varchar(255),--˵��
 
)
--if(left(@FBillNo,2)='ZZ') --������֤
--begin
 if not exists(select 1 from t_AssembleRecord1 where FBillNo=@FBillNo)--�ж������Ƿ����ϵͳ��
 begin
   set @FExplanation='�����벻����ϵͳ' 
 end
 else
 begin
  if  exists(select 1 from t_AssembleRecord1Temp where FBillNo=@FBillNo)--�ж������Ƿ����ϵͳ��
      set @FExplanation='�������ѷ���ҵ���ϵ'
  else
     if exists(select 1 from t_AssembleRecord1 where FBillNo=@FBillNo and FStatus = 2)
     set @FExplanation='�������ѳ���'
 end
 if(@FExplanation='OK')
 begin
   declare  @FItemID int,--��ƷID
    @FUnitID int,--��λID
    @FQty decimal(28,10),--����
    @FDCStockID int,
    @FDCSPID int,
    @FSTOCKORGID int,--�����֯ID
    @FNumber varchar(255),--���ϱ���
    @FOWNERID int,  --����
    @FBatchNo varchar(128) --���� 
    declare My_cursor cursor dynamic --scroll��ʾ������ǰ������ƶ�    dynamic����ʾ��дҲ�ɶ�
         for 
       select  t3.FNumber,t2.FItemID,t2.FUnitID,t2.FQty-isnull(t2.FQtyOut,0) as FQty,t2.FDCStockID,t2.FDCSPID,t2.FSTOCKORGID,t2.FOWNERID,t2.FBatchNo from  t_AssembleRecord1 t1 inner join t_AssembleRecord1entry t2 on t1.FID =t2.FID left join T_BD_MATERIAL t3 on t3.FMATERIALID =t2.FItemID  where t1.FBillNo=@FBillNo
         open My_cursor      
         fetch next from my_cursor into @FNumber,@FItemID,@FUnitID,@FQty,@FDCStockID,@FDCSPID,@FSTOCKORGID,@FOWNERID,@FBatchNo --�α�ͣ�ڵ�һ����¼ǰ�棬��һ��ִ�У�������û�м�¼����,
         while(@@FETCH_STATUS = 0) --ȡ���� 0��ʾ�ɹ�ִ��FETCH���  -1 ��ʾFETCH���ʧ�ܣ������ƶ���ָ��ʹ�䳬���˽���� -2 ��ʾ����ȡ���в����ڡ�
         begin 
       
         if(@FID>0)
         begin 
         if not exists(select 1 from T_SAL_ORDEREntry t1 left join T_BD_MATERIAL t2 on t1.FMATERIALID = t2.FMATERIALID where FID =@FID and t2.FNumber = @FNumber)
         begin
       
         
            set @FExplanation = @FNumber + '���ϲ�����ϸ��..'
         end
         else
         begin
           if not exists(select 1 from ( select  sum( CAST(CASE  WHEN (T2.FSTOREURNOM = 0  OR T2.FSTOREURNUM = 0) THEN T0.FBASEQTY ELSE (CAST((T0.FBASEQTY * T2.FSTOREURNOM) AS NUMERIC(23, 10)) / T2.FSTOREURNUM) END AS NUMERIC(23, 10))) as FQty  from T_STK_INVENTORY t0 left join T_BD_MATERIALSTOCK t2 on t0.FMATERIALID=t2.FMATERIALID left join T_BD_MATERIAL t100  on t0.FMATERIALID=t100.FMATERIALID LEFT OUTER JOIN T_BD_LOTMASTER st035 ON t0.FLOT=st035.FLOTID LEFT join t_BD_Supplier t_20  on t_20.FSUPPLIERID=t0.FOWNERID left join t_BD_Customer t_22  on t_22.FCUSTID=t0.FOWNERID   where  1=1 and t100.FNumber = @FNumber and st035.FNUMBER = @FBatchNo and  t0.FSTOCKID = @FDCStockID and  t0.FOWNERID = @FOWNERID and t0.FSTOCKORGID = @FSTOCKORGID  group by t0.FMATERIALID,t0.FSTOCKID,st035.FNUMBER,FSTOCKSTATUSID,t0.FSTOCKSTATUSID,t0.FSTOCKORGID,t0.FOWNERID,t_20.FSUPPLIERID,t_22.FCUSTID ) t where t.FQty-@FQty>=0 )
            begin 
            set @FExplanation = @FNumber + '���Ͽ�治����ϸ��..'
            end
         end
         end
         insert into #Tmp11111(FItemID,FUnitID,FQty,FStockID,FStockPlaceID,FSTOCKORGID,FOWNERID,FBatchNo,FExplanation)values(@FItemID,@FUnitID,@FQty,@FDCStockID,@FDCSPID,@FSTOCKORGID,@FOWNERID,@FBatchNo,@FExplanation)
         set @FExplanation = 'OK'
         fetch next from my_cursor into @FNumber,@FItemID,@FUnitID,@FQty,@FDCStockID,@FDCSPID,@FSTOCKORGID,@FOWNERID,@FBatchNo --�ٴν��α�ͣ�ڵ�һ����¼ǰ�棬��һ��ִ�У�������û�м�¼����,����Ҳ��Ϊ@@FETCH_STATUS��ֵ
	      end  
				 close my_cursor
				 deallocate my_cursor  
         
  
 end
--end
--else  --Ψһ����֤
--begin
-- if not exists(select 1 from t_PDABarCodeSign where FBarCode=@FBillNo)--�ж������Ƿ����ϵͳ��
-- begin
--   set @FExplanation='���벻����ϵͳ' 
-- end
-- else
-- begin 
--  if not exists(select 1 from t_PDABarCodeSign where FBarCode=@FBillNo and FIsInStore='�����')
--  begin
--   set @FExplanation='δ������벻�ܳ���'
--  end
--  else
--  begin
--    declare @FQtyAll decimal(28,10)
--    select @FQtyAll=isnull(sum(FQty),0) from a_DetailsTable where FBarCode=@FBillNo
--    if exists(select 1 from t_PDABarCodeSign where FBarCode=@FBillNo and FQty-isnull(FQtyOut,0)-@FQtyAll<=0) 
--     set @FExplanation='��������ȫ����,�����ٴγ���'
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
select t41.FName as �ֿ�����,t21.FName as ��Ʒ����,t21.FSPECIFICATION as ���,t1.FExplanation as ˵��,t2.FNumber as ��Ʒ����,t1.FBatchNo as ����,convert(float, t1.FQty) as ����,t3.FNumber as ��λ����,t4.FNumber as �ֿ����,t5.FNumber as �����֯����,t6.FNumber as �������� from #Tmp11111 t1 left join  T_BD_MATERIAL t2 ON t2.FMATERIALID = t1.FItemID  left join  T_BD_MATERIAL_L t21 ON t21.FMATERIALID = t1.FItemID left join T_BD_UNIT t3 on t3.FUNITID = t1.FUnitID left join t_BD_Stock  t4 on t4.FSTOCKID  = t1.FStockID left join t_BD_Stock_L  t41 on t41.FSTOCKID  = t1.FStockID left join T_ORG_Organizations t5 on t5.FORGID = t1.FSTOCKORGID left join T_ORG_Organizations t6 on t6.FORGID = t1.FOWNERID 
drop table #Tmp11111
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
