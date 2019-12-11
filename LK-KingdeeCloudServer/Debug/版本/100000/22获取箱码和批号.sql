if (exists (select * from sys.objects where name = 'proc_PDAGetDataBatchNo2'))
    drop proc proc_PDAGetDataBatchNo2
go
create proc proc_PDAGetDataBatchNo2
(
 @mainStr varchar(1000) 
 
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

declare @FID int,     --����id 
        @Fdate datetime,       --����  
        @FBillerID int,   --�Ƶ���id
        @FBillNo varchar(50),--���� 
        @FChang varchar(255),--����
        @FHuang varchar(255),--��
        @FHou varchar(255),--��
          @FBatchNo varchar(255), --����
        @FStockNumber varchar(255),--�ֿ����
        @FWorkshopNumber varchar(255),--�����������
        @FShortDate varchar(128),
          @FBatcnNoIndex varchar(128),--���Ÿ���ֵ
        @FRemark varchar(200)--ժҪ 
set @FBillerID = dbo.getString(@mainStr,'|',1) --����Ա
 set @FChang =dbo.getString(@mainStr,'|',2)      --�� 
set @FHuang =dbo.getString(@mainStr,'|',3)      --�� 
set @FHou =dbo.getString(@mainStr,'|',4)      --�� 
set @FStockNumber =dbo.getString(@mainStr,'|',5) --�ֿ����
set @FWorkshopNumber =dbo.getString(@mainStr,'|',6)  --�ֿ����
set @FBatcnNoIndex = dbo.getString(@mainStr,'|',7) --���Ÿ���ֵ
set @Fdate = convert(varchar(20),GETDATE(),20)
set @FShortDate =convert(nvarchar(60),getdate(),112)
------------------------------------------------------------�õ�����
declare  @FIndex int,  --(ѭ����) 
         @FNum varchar(50) --��ˮ��
        set @FBillNo ='' 
 
select @FIndex=isnull(MAX(FIndex),0)+1 from t_AssembleRecord where FShortDate=@FShortDate
set @FNum='000000000'+CONVERT(varchar(20),@FIndex)
set @FNum=right(@FNum,6)  
set @FBillNo='ZZ'+@FShortDate+@FNum
 

INSERT INTO t_AssembleRecord(FBillNo,FIndex,FDate,FBillerID,FChang,FHuang,FHou,FStatus,FShortDate) values (@FBillNo,@FIndex,@FDate,@FBillerID,@FChang,@FHuang,@FHou,0,@FShortDate)
select @FID=MAX(FID) from t_AssembleRecord 
 
 --�������� 
 declare @FDatePrintShort varchar(8),
         @FShortIndex varchar(50)
set @FDatePrintShort = RIGHT( convert(nvarchar(6),getdate(),112),4)
select @FIndex=isnull(max(FIndex),0)+1 from t_PDABatchNoIndex2 where FShortDate = @FDatePrintShort and FWorkshopNumber=@FWorkshopNumber and FStockNumber=@FStockNumber
if exists(select 1 from t_PDABatchNoIndex2 where FShortDate = @FDatePrintShort  and FWorkshopNumber=@FWorkshopNumber and FStockNumber=@FStockNumber)
begin
  update t_PDABatchNoIndex2 set FIndex=@FIndex where FShortDate = @FDatePrintShort  and FWorkshopNumber=@FWorkshopNumber and FStockNumber=@FStockNumber
end
else
  begin
   insert into t_PDABatchNoIndex2(FMATERIALID,FStockNumber,FWorkshopNumber,FShortDate,FIndex)
   values(0,@FStockNumber,@FWorkshopNumber,@FDatePrintShort,@FIndex)
  end
set @FShortIndex='000000000000'+convert(varchar(12),@FIndex)
set @FBatchNo = @FStockNumber+@FDatePrintShort+right(@FShortIndex,3)+@FBatcnNoIndex
--��������
 
------------------------------------------------------------�õ�����
 
create table #Tmp11111 --������ʱ��#Tmp
( 
    FBillNo   varchar(255) 
) 
insert into #Tmp11111(FBillNo)values(@FBillNo)
select FBillNo as ����,@FBatchNo as ���� from #Tmp11111
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
