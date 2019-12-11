if (exists (select * from sys.objects where name = 'proc_PDAGetDataAssemble2'))
    drop proc proc_PDAGetDataAssemble2
go
create proc proc_PDAGetDataAssemble2
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
        @FStockNumber varchar(255),--�ֿ����
        @FWorkshopNumber varchar(255),--�����������
        @FShortDate varchar(128),
        @FRemark varchar(200)--ժҪ 
set @FBillerID = dbo.getString(@mainStr,'|',1) --����Ա
 set @FChang =dbo.getString(@mainStr,'|',2)      --�� 
set @FHuang =dbo.getString(@mainStr,'|',3)      --�� 
set @FHou =dbo.getString(@mainStr,'|',4)      --��  
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
  
------------------------------------------------------------�õ�����
 
create table #Tmp11111 --������ʱ��#Tmp
( 
    FBillNo   varchar(255) 
) 
insert into #Tmp11111(FBillNo)values(@FBillNo)
select FBillNo as ���� from #Tmp11111
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
