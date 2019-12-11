if (exists (select * from sys.objects where name = 'proc_AssembleCatch1_check'))
    drop proc proc_AssembleCatch1_check
go
create proc proc_AssembleCatch1_check
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
  --FItemID int not null,   --��Ʒid
  --FUnitID int not null,   --��λid
  --FLength varchar(128),--����
  --FQty decimal(28,10), --����
  --FVolume decimal(28,10), --��� 

  --FDCStockID int, --�ֿ�id
  --FDCSPID int, --��λid
  --FBatchNo varchar(255), --����
  --FSTOCKORGID int,--�����֯ID
  --FOWNERID int,  --����
  --FQtyAll decimal(28,10), --������
  --FVolumeAll decimal(28,10), --�����
  FAssmble varchar(128),--����
  --FName varchar(255),--����
  --FModel varchar(255),--��� 
  --FWidth varchar(128),--���
  FExplanation varchar(255) --˵��
) 
if not exists(select 1 from t_AssembleRecord1 where FBillNo=@FBillNo)--�ж������Ƿ����ϵͳ��
begin
   set @FExplanation='�����벻����ϵͳ,����׷��' 
end 
if exists(select 1 from t_AssembleRecord1 where FBillNo=@FBillNo and FStatus = 2)
 set @FExplanation='�������ѳ���,����׷��'
 
insert into #Tmp11111(FExplanation,FAssmble)values(@FExplanation,@FBillNo)

if not exists(select 1 from #Tmp11111)
begin
insert into #Tmp11111(FExplanation)values(@FExplanation)
end
select t0.FExplanation as ˵��,t1.FType as ��������,t1.FCarNum as ���ƺ�,t1.FPackets as ����,t1.FPackets1 as �ְ���,t1.FWidth as ���,convert(decimal(28,0),t2.FQty-isnull(t2.FQtyOut,0)) as ����,convert(float,t2.FVolume-isnull(t2.FVolumeOut,0)) as ���,t2.FLevel as �ȼ�,t4.FSPECIFICATION as ���,t7.FNumber as �ֿ����,t2.FBatchNo as ����,t8.FNumber as �����֯����,t9.Fnumber as �������� from #Tmp11111 t0 left join t_AssembleRecord1 t1 on t0.FAssmble = t1.FBillNo left join t_AssembleRecord1entry t2 on t1.FID =t2.FID   left join (select sum(FQty-isnull(FQtyOut,0)) as FQty,sum(FVolume-isnull(FVolumeOut,0)) as FVolume,FID from t_AssembleRecord1entry where FQty>isnull(FQtyOut,0) group by FID) t6 on t1.FID =t6.FID left join t_bd_material_l t4 on  t2.FItemID=t4.fmaterialid  left join t_BD_Stock t7 on t7.FSTOCKID = t2.FDCStockID left join T_ORG_Organizations t8 on t8.FORGID = t2.FSTOCKORGID left join T_ORG_Organizations t9 on t9.FORGID = t2.FOWNERID   order by FEntryID
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
