if (exists (select * from sys.objects where name = 'proc_K3AllotPushAllotOut'))
drop proc proc_K3AllotPushAllotOut
go
create proc proc_K3AllotPushAllotOut
(
  @mainStr nvarchar(1000)--�������
 
)
as 
set nocount on     --����һ��ģʽ��Ҳ���ǲ���ˢ�¶�������Ӱ�����Ϣ�������������
set xact_abort on  --����ѡ������Ϊ����ȫ���ع�
begin tran declare
          @FDate varchar(20),            --��������   
          @FBillerID varchar(20),        --�Ƶ���ID 
          @FOrderID varchar(50),--PDA���ݱ��
          @FPDAID varchar(50),  --PDA���к� 
          @FInterID int,--��������
          @FBillNo varchar(128) --���ݱ��
        
          set @FBillerID=dbo.getString(@mainStr,'|',1)   -- �Ƶ���ID 
          set @FBillNo=dbo.getString(@mainStr,'|',2)     --�ص�ʱ���صĵ��ݱ��
          set @FOrderID=dbo.getString(@mainStr,'|',3)  --PDA���ݱ��
          set @FPDAID=dbo.getString(@mainStr,'|',4)      --PDA���
          select @FInterID=FID from T_STK_STKTRANSFEROUT where FBillNo=@FBillNo 
    set @Fdate = convert(varchar(20),GETDATE(),23)                                       
insert into t_PDABarCodeSign_Allot(FInterID,FBarCode,FQty,FBillNo,FRemark,FStockID_Before,FStockPlaceID_Before,FStockID_Now,FStockPlaceID_Now)
select @FInterID,b.FBarCode,a.FQty,@FBillNo,'�������뵥���Ʒֲ�ʽ������',b.FStockID,b.FStockPlaceID,a.FStockID,a.FStockPlaceID from a_DetailsTable a left join t_PDABarCodeSign b on a.FBarCode=b.FBarCode where FOrderID=@FOrderID and FPDAID=@FPDAID
update t_PDABarCodeSign  set FStockID=t.FStockID,FStockPlaceID=t.FStockPlaceID  from (select a.*,b.FStockID as AAA,b.FStockPlaceID as BBB from a_DetailsTable a left join t_PDABarCodeSign b on a.FBarCode=b.FBarCode where FOrderID=@FOrderID and FPDAID=@FPDAID) as t where t_PDABarCodeSign.FBarCode=t.FBarCode

delete from a_DetailsTable where FOrderID=@FOrderID and FPDAID=@FPDAID
   
commit tran 
return;
if(0<>@@error)
	goto error1
error1:
	rollback tran;