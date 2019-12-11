if (exists (select * from sys.objects where name = 'proc_InStoreBarCode_check'))
    drop proc proc_InStoreBarCode_check
go
create proc proc_InStoreBarCode_check
(  
  @FBarCode varchar(128) --���� 
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
    FItemID int,--��ƷID
    FUnitID int,--��λID
    FQty decimal(28,10),--����
    FStockID int,
    FStockPlaceID int,
    FBatchNo varchar(128),
    FKFPeriod int,
    FKFDate varchar(12),
    FBillNo   varchar(255),--���ݱ��
      FRemark3 varchar(255),--ʵ�ʹ��
     FRemark4 varchar(255),--������ʶ
      FRemark7 varchar(255),--�������
    FExplanation varchar(255),--˵��
)
 
  if not exists(select 1 from t_PDABarCodeSign where FBarCode=@FBarCode)--�ж������Ƿ����ϵͳ��
  begin
      set @FExplanation='���벻����ϵͳ' 
  end
  else
  begin
  	 if  exists(select 1 from a_DetailsTable where FBarCode=@FBarCode)--�ж������Ƿ����ϵͳ��
  	  set @FExplanation='�������ѷ���ҵ���ϵ'
  	 if exists(select 1 from t_PDABarCodeSign where FBarCode=@FBarCode and FIsInStore='�����')
      set @FExplanation='�����������'
  end
 
 
if(@FExplanation='OK')
begin
	insert into #Tmp11111(FItemID,FUnitID,FQty,FStockID,FStockPlaceID,FBatchNo,FKFPeriod,FKFDate,FBillNo,FExplanation,FRemark3,FRemark4,FRemark7)
	select top 1 FItemID,FUnitID,FQty,FStockID,FStockPlaceID,FBatchNo,FKFPeriod,FKFDate,FBillNo,@FExplanation,FRemark3,FRemark4,FRemark7 from t_PDABarCodeSign where FBarCode=@FBarCode
end
else
begin
insert into #Tmp11111(FExplanation)values(@FExplanation)
end 
declare @FNumber varchar(255)
select @FNumber=FNumber from t_PDABarCodeSign t1 left join T_BD_MATERIAL t2 on t1.FItemID =t2.FMaterialid  where FBarCode=@FBarCode
select FExplanation as ˵��,FBillNo,@FNumber as FItemID,FUnitID,convert(float,FQty) as FQty,FStockID,FStockPlaceID,FBatchNo,FKFPeriod,FKFDate,FRemark3 as ʵ�ʹ��,FRemark4 as ������ʶ,FRemark7 as ������� from #Tmp11111
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
