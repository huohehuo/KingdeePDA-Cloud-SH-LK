if (exists (select * from sys.objects where name = 'proc_K3SaleStock'))
drop proc proc_K3SaleStock
go
create proc proc_K3SaleStock
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
          select @FInterID=FID from T_SAL_OUTSTOCK where FBillNo=@FBillNo 
    set @Fdate = convert(varchar(20),GETDATE(),23)                                       
update t_PDABarCodeSign  set FInterIDOut=@FInterID,FDateOutStore=@fdate,FIsOutStore='�ѳ���',FUserOutStore=@FBillerID,FRemark2='���۳��ⵥ',FQtyOut=isnull(FQtyOut,0)+t.FQty   from (select a.* from a_DetailsTable a left join t_PDABarCodeSign b on a.FBarCode=b.FBarCode where FOrderID=@FOrderID and FPDAID=@FPDAID) as t where t_PDABarCodeSign.FBarCode=t.FBarCode
 insert into t_PDABarCodeSign_Red(FBarCode,FInterID,FROB,FRemark,FInterID_Before,FBillNO,FStatus,FQtyOut)select a.FBarCode,@FInterID,0,'���۳��ⵥ',b.FInterIDOut,@FBillNO,1,a.FQty from a_DetailsTable a left join t_PDABarCodeSign b on a.FBarCode=b.FBarCode where FOrderID=@FOrderID and FPDAID=@FPDAID 

delete from a_DetailsTable where FOrderID=@FOrderID and FPDAID=@FPDAID
   
commit tran 
return;
if(0<>@@error)
	goto error1
error1:
	rollback tran;