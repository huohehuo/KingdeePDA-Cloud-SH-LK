if (exists (select * from sys.objects where name = 'proc_OutStoreBarCode_Insert'))
    drop proc proc_OutStoreBarCode_Insert
go
create proc proc_OutStoreBarCode_Insert
(
  @FOrderID varchar(255),--PDA���ݺ�
  @FPDAID varchar(255),  --PDA���к�
  @FBarCode varchar(128), --����
  @FQty decimal(20,8) --����
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
declare @FBillNo varchar(128) 
         
set @FBillNo='OK'
if exists(select 1 from a_DetailsTable where FBarCode=@FBarCode and FOrderID=@FOrderID and FPDAID=@FPDAID)
begin
  update  a_DetailsTable set FQty=FQty+@FQty where FBarCode=@FBarCode and FOrderID=@FOrderID and FPDAID=@FPDAID
end
else
begin
      insert into a_DetailsTable(FPDAID,FOrderID,FBarCode,FItemID,FStockID,FStockPlaceID, FBatchNo,  FKFPeriod,  FKFDate,  FQty)
        select top 1  @FPDAID,@FOrderID, FBarCode, FItemID,FStockID, FStockPlaceID,  FBatchNo,  FKFPeriod,  FKFDate,  @FQty from t_PDABarCodeSign where FBarCode=@FBarCode
 end
 
 
 create table #Tmp11111 --������ʱ��#Tmp
(
    FBillNo   varchar(255), 
)
set @FBillNo='OK'
 
insert into #Tmp11111(FBillNo)values(@FBillNo)
select FBillNo as ���ݱ�� from #Tmp11111
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
