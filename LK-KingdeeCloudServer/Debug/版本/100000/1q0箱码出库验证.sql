if (exists (select * from sys.objects where name = 'proc_AssembleOut1_check'))
    drop proc proc_AssembleOut1_check
go
create proc proc_AssembleOut1_check
(  
  @FBillNo varchar(128)  --���� 
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
declare @FExplanation varchar(128),
        @FQtyAll decimal(28,10) --PDA���������
      
 set @FExplanation='OK'
 create table #Tmp11111 --������ʱ��#Tmp
(   
  FItemID int ,   --��Ʒid
  FUnitID int ,   --��λid
  FLength varchar(128),--����
  FQty decimal(28,10), --����
  FVolume decimal(28,10), --��� 
 
  FDCStockID int, --�ֿ�id
  FDCSPID int, --��λid
  FBatchNo varchar(255), --����
  FSTOCKORGID int,--�����֯ID
  FOWNERID int,  --����
  FQtyAll decimal(28,10), --������
    FVolumeAll decimal(28,10), --�����
    FAssmble varchar(128),--����
    FName varchar(255),--����
    FModel varchar(255),--��� 
    FWidth varchar(128),--���
    FLevel varchar(128),--�ȼ�
    FPackets1 varchar(128),--�ְ���
    FCarNum varchar(255),--����
    FExplanation varchar(255) --˵��
)

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
 
insert into #Tmp11111(FModel,FCarNum,FPackets1,FLevel,FExplanation,FItemID,FUnitID,FLength,FQty,FVolume,FDCStockID,FDCSPID,FBatchNo,FSTOCKORGID,FOWNERID,FQtyAll,FVolumeAll,FAssmble,FName,FWidth)	
select t4.FSPECIFICATION,t1.FCarNum,isnull( dbo.getString(t1.FPackets1,'-',1),0),t2.FLevel,@FExplanation,t2.FItemID,t2.FUnitID,t2.FLength,convert(decimal(28,0),t2.FQty-isnull(t2.FQtyOut,0)),convert(float,t2.FVolume-isnull(t2.FVolumeOut,0)),t2.FDCStockID,t2.FDCSPID,t2.FBatchNo,t2.FSTOCKORGID,t2.FOWNERID,convert(float, t6.FQty),convert(float,t6.FVolume),t1.FBillNo,t1.FName,t1.FWidth  from t_AssembleRecord1 t1 inner join t_AssembleRecord1entry t2 on t1.FID =t2.FID left join  T_SEC_user  t3 on t1.FBillerID=t3.FUSERID left join t_bd_material_l t4 on  t2.FItemID=t4.fmaterialid left join  T_BD_UNIT_L t5 ON  t2.FUNITID = t5.FUNITID inner join (select sum(FQty-isnull(FQtyOut,0)) as FQty,sum(FVolume-isnull(FVolumeOut,0)) as FVolume,FID from t_AssembleRecord1entry where FQty>isnull(FQtyOut,0) group by FID) t6 on t1.FID =t6.FID  where t2.FQty>isnull(t2.FQtyOut,0) AND t1.FBillNo =@FBillNo order by FEntryID

if not exists(select 1 from #Tmp11111)
begin
insert into #Tmp11111(FExplanation)values(@FExplanation)
end
select t1.FCarNum as ����,t1.FPackets1 as �ְ���,t1.FLevel as �ȼ�,t1.FWidth as ���,t1.FAssmble as ����,t1.FExplanation as ˵��,convert(float,t1.FQtyAll) as ������,convert(float,t1.FVolumeAll) as �����,t1.FName as ����,t1.FModel as ���,t1.FLength as ����,t1.FBatchNo as ����,convert(float,t1.FQty) as ����,convert(float,t1.FVolume) as ���,t1.FItemID as ��ƷID,t1.FUnitID as ��λID,t1.FDCStockID as �ֿ�ID,t1.FDCSPID as ��λID,t1.FSTOCKORGID as �����֯ID,t1.FOWNERID as ����ID from #Tmp11111 t1
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
