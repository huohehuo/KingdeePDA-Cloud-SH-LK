if (exists (select * from sys.objects where name = 'proc_AssembleOut1_Insert'))
    drop proc proc_AssembleOut1_Insert
go
create proc proc_AssembleOut1_Insert
(
  @FOrderID varchar(255),--PDA���ݺ�
  @FPDAID varchar(255),  --PDA���к�
  @FBillNo varchar(128) --���� 
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
    FExplanation   varchar(255), 
)
set @FExplanation='OK'
insert into t_AssembleRecord1Temp(FOrderID,FPDAID,FBillNo,FItemID,FUnitID,FLength,FQty,FVolume,FDCStockID,FDCSPID,FBatchNo,FSTOCKORGID,FOWNERID)
select @FOrderID,@FPDAID,FBillNo,FItemID,FUnitID,FLength,FQty-isnull(FQtyOut,0),FVolume-isnull(FVolumeOut,0),FDCStockID,FDCSPID,FBatchNo,FSTOCKORGID,FOWNERID from t_AssembleRecord1 t1 inner join t_AssembleRecord1entry t2 on t1.FID =t2.FID where t2.FQty>isnull(t2.FQtyOut,0) AND t1.FBillNo =@FBillNo
insert into #Tmp11111(FExplanation)values(@FExplanation)
select FExplanation as ���ݱ�� from #Tmp11111
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
