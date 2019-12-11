if (exists (select * from sys.objects where name = 'proc_K3PurchasePushOutsourcing_Assemble'))
drop proc proc_K3PurchasePushOutsourcing_Assemble
go
create proc proc_K3PurchasePushOutsourcing_Assemble
(
  @mainStr nvarchar(1000)--�������
 
)
as 
set nocount on     --����һ��ģʽ��Ҳ���ǲ���ˢ�¶�������Ӱ�����Ϣ�������������
set xact_abort on  --����ѡ������Ϊ����ȫ���ع�
begin tran declare
          @FDate varchar(20),            --��������   
          @FBillerID varchar(20),        --�Ƶ���ID 
          
          @FInterID int,--��������
          @FAssemble varchar(128),--װ�䵥��
          @FSourceID int,
          @FBillNo varchar(128) --���ݱ��
        
          set @FBillerID=dbo.getString(@mainStr,'|',1)   -- �Ƶ���ID 
          set @FBillNo=dbo.getString(@mainStr,'|',2)     --�ص�ʱ���صĵ��ݱ��
          set @FSourceID =dbo.getString(@mainStr,'|',3)   --��������
        
          select @FInterID=FID from t_STK_InStock where FBillNo=@FBillNo 
          set @Fdate = convert(varchar(20),GETDATE(),23)                                       
  update t_AssembleRecord1  set FInterIDIn=@FInterID, FStatus = 1,FRemark = '�ɹ��������Ʋɹ���ⵥ'  where FSourceID = @FSourceID and FStatus=0 and isnull(FInterIDIn,0) = 0
 
   
commit tran 
return;
if(0<>@@error)
	goto error1
error1:
	rollback tran;