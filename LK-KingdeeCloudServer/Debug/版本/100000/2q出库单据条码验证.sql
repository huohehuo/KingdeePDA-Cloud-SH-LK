if (exists (select * from sys.objects where name = 'proc_OutStoreBarCode_check2'))
    drop proc proc_OutStoreBarCode_check2
go
create proc proc_OutStoreBarCode_check2
(  
  @FBarCode varchar(128)  --���� 
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
     FRemark5 varchar(128),--��������
      FRemark7 varchar(255),--�������
    FExplanation varchar(255),--˵��
    F_TypeID int,--��������
        F_Plies decimal(20,8),--����
    F_FFF_TEXT1 varchar(255),
    F_FFF_INTEGER varchar(255),
    F_FFF_INTEGER1  decimal(28,10),
    F_FFF_INTEGER2  decimal(28,10),
    F_FFF_INTEGER3  decimal(28,10),
    F_FFF_INTEGER4  decimal(28,10),
    F_FFF_Volume  decimal(28,10)
)

 if not exists(select 1 from t_PDABarCodeSign where FBarCode=@FBarCode)--�ж������Ƿ����ϵͳ��
 begin
   set @FExplanation='���벻����ϵͳ' 
 end
 else
 begin
  --if  exists(select 1 from a_DetailsTable where FBarCode=@FBarCode)--�ж������Ƿ����ϵͳ��
  --    set @FExplanation='�������ѷ���ҵ���ϵ'
  if not exists(select 1 from t_PDABarCodeSign where FBarCode=@FBarCode and FIsInStore='�����')
  begin
   set @FExplanation='δ������벻�ܳ���'
  end
  else
  begin
    select @FQtyAll=isnull(sum(FQty),0) from a_DetailsTable where FBarCode=@FBarCode
    if exists(select 1 from t_PDABarCodeSign where FBarCode=@FBarCode and FQty-isnull(FQtyOut,0)-@FQtyAll<=0)--and FIsOutStore='�ѳ���'
     set @FExplanation='��������ȫ����,�����ٴγ���'
  end
 end
	insert into #Tmp11111(FItemID,FUnitID,FQty,FStockID,FStockPlaceID,FBatchNo,FKFPeriod,FKFDate,FBillNo,FExplanation,FRemark3,FRemark4,FRemark5,FRemark7,F_FFF_TEXT1,F_FFF_INTEGER,F_FFF_INTEGER1,F_FFF_INTEGER2,F_FFF_INTEGER3,F_FFF_INTEGER4,F_FFF_Volume,F_TypeID,F_Plies)
	select top 1 FItemID,FUnitID,FQty-isnull(FQtyOut,0)-@FQtyAll,FStockID,FStockPlaceID,FBatchNo,FKFPeriod,FKFDate,FBillNo,@FExplanation,FRemark3,FRemark4,FRemark5,FRemark7,F_FFF_TEXT1,F_FFF_INTEGER,F_FFF_INTEGER1,F_FFF_INTEGER2,F_FFF_INTEGER3,F_FFF_INTEGER4,F_FFF_Volume,F_TypeID,F_Plies from t_PDABarCodeSign where FBarCode=@FBarCode
if not exists(select 1 from #Tmp11111)
begin
insert into #Tmp11111(FExplanation)values(@FExplanation)
end
declare @FNumber varchar(255)
select @FNumber=FNumber from t_PDABarCodeSign t1 left join T_BD_MATERIAL t2 on t1.FItemID =t2.FMaterialid  where FBarCode=@FBarCode
select FExplanation as ˵��,FBillNo,@FNumber as FItemID,FUnitID,convert(float,FQty) as FQty,FStockID,FStockPlaceID,FBatchNo,FKFPeriod,FKFDate,FRemark3 as ʵ�ʹ��,FRemark4 as ������ʶ,FRemark5 as ��������,FRemark7 as �������,F_FFF_TEXT1 as �ȼ�,convert(decimal(28,2),ISNull(F_FFF_INTEGER,0)) as ԭľ��,convert(decimal(28,2),ISNull(F_FFF_INTEGER1,0)) as ԭľֱ��,convert(decimal(28,0),ISNull(F_FFF_INTEGER2,0)) as �峤,convert(decimal(28,0),ISNull(F_FFF_INTEGER3,0)) as ���,convert(decimal(28,0),ISNull(F_FFF_INTEGER4,0)) as ���,convert(float,ISNull(F_FFF_Volume,0)) as ���,isnull(F_TypeID,1) as ��������,convert(int,isnull( F_Plies,0)) as ���� from #Tmp11111
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
