if (exists (select * from sys.objects where name = 'proc_K3ProductPick1_Assemble'))
drop proc proc_K3ProductPick1_Assemble
go
create proc proc_K3ProductPick1_Assemble
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
          select @FInterID=FID from T_SP_PICKMTRL where FBillNo=@FBillNo 
          set @Fdate = convert(varchar(20),GETDATE(),23)                                       
  update t_AssembleRecord1  set FInterIDOut=@FInterID, FStatus = 2,FRemark = '�������ϵ�' from (select a.* from t_AssembleRecord1Temp a left join t_AssembleRecord1 b on a.FBillNo=b.FBillNo where a.FOrderID=@FOrderID and a.FPDAID=@FPDAID) as t where t_AssembleRecord1.FBillNo=t.FBillNo
  delete from t_AssembleRecord1Temp where FOrderID=@FOrderID and FPDAID=@FPDAID
   
commit tran 
return;
if(0<>@@error)
	goto error1
error1:
	rollback tran;