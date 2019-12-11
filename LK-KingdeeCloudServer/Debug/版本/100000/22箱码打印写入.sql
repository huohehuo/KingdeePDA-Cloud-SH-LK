if (exists (select * from sys.objects where name = 'proc_PDACountOff_Insert2'))
    drop proc proc_PDACountOff_Insert2
go
create proc proc_PDACountOff_Insert2
(
 @mainStr varchar(1000),--�������
 @detailStr1 varchar(max),--��ϸ����
 @detailStr2 varchar(max),
 @detailStr3 varchar(max),
 @detailStr4 varchar(max),
 @detailStr5 varchar(max)
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
        @FRemark varchar(200)--ժҪ 
set @FBillerID = dbo.getString(@mainStr,'|',1) --����Ա
 set @FChang =dbo.getString(@mainStr,'|',2)      --�� 
set @FHuang =dbo.getString(@mainStr,'|',3)      --�� 
set @FHou =dbo.getString(@mainStr,'|',4)      --�� 
set @FStockNumber =dbo.getString(@mainStr,'|',5) --�ֿ����
set @FWorkshopNumber =dbo.getString(@mainStr,'|',6)  --�����������
set @FBillNo =dbo.getString(@mainStr,'|',7)  --���� 
set @FBatchNo =dbo.getString(@mainStr,'|',8) --����

set @Fdate = convert(varchar(20),GETDATE(),20)
    if not exists(select 1 from t_AssembleRecord where FStatus = 1 and FBillNo=@FBillNo)
    begin
update t_AssembleRecord set FStatus = 1 where FBillNo=@FBillNo
select @FID= FID  from t_AssembleRecord where FBillNo=@FBillNo
 
 declare @FEntryID varchar(20),       --��ϸ���
         
         @FCountOff varchar(50),--����
  @FItemID int,   --��Ʒid
  @FUnitID int,   --��λid
  @FLength varchar(128),--����
  @FQty decimal(28,10), --����
   @FVolume decimal(28,10), --���
  @FDCStockID int, --�ֿ�id
  @FDCSPID int, --��λid 
  @FSTOCKORGID int,--�����֯ID
       @FOWNERID int,  --����
      @FVolumeAll decimal(28,10),--�����
      @FQtyAll decimal(28,10),--������
     
      
        @detailqty int,               --��ϸ�����ĸ���
        @detailcount int,             --��ϸÿ�����ݵĳ��� 
        @detailIndex int,            --��ϸÿ���±�
        @countindex int              --�ָ���|������
         set @FVolumeAll = 0
         set @FQtyAll = 0
       set @detailqty=0        
       set @detailcount=9   
    while(@detailqty<5)--�ж�����ϸ���ĸ�����
    begin
    if(@detailqty=1)
	begin
	set @detailStr1=@detailStr2
	end  
	if(@detailqty=2)
	begin
	set @detailStr1=@detailStr3
	end 
	if(@detailqty=3)
	begin
	set @detailStr1=@detailStr4
	end 
	if(@detailqty=4)
	begin
	set @detailStr1=@detailStr5
	end 
	if(@detailStr1='' or @detailStr1=null)
	begin
	break;
	end
	set @detailIndex=0 
	select @countindex=len(@detailStr1)-len(replace(@detailStr1, '|', ''))
	
	while(@countindex>@detailIndex*@detailcount)
	begin
	set @FItemID=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+1)    --��Ʒid
	set @FUnitID=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+2)    --��λid 
	set @FQty=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+3)    --���� 
    set @FDCStockID=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+4)    --�ֿ�
    set @FDCSPID=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+5)    --��λ
    set @FSTOCKORGID =dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+6)    --�����֯ID
    set @FOWNERID=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+7)    --����ID
    set @FLength=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+8)    --����
        set @FVolume=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+9)    --���
	set @detailIndex=@detailIndex+1
	set @FVolumeAll = @FVolumeAll+@FVolume
	set @FQtyAll = @FQtyAll +@FQty
	
    insert into t_AssembleRecordEntry(FID,FItemID,FUnitID,FLength,FQty,FDCStockID,FDCSPID,FBatchNo,FSTOCKORGID,FOWNERID,FVolume)
	values(@FID,@FItemID,@FUnitID,@FLength,@FQty,@FDCStockID,@FDCSPID,@FBatchNo,@FSTOCKORGID,@FOWNERID,@FVolume)
  set @FEntryID=@detailqty*50+@detailIndex 
end
set @detailqty=@detailqty+1
end
 select convert(float,@FQtyAll) as ������,convert(float,@FVolumeAll) as �����,t1.FBillNo as ����,t1.FDate as װ������,t3.FName as �Ƶ���,t4.FName as ����,t5.FName as ��λ,(t1.FChang+'x'+t1.FHuang+'x'+t1.FHou) as ���,t2.FBatchNo as ����,t2.FLength as ����,convert(decimal(28,0),t2.FQty) as ����,convert(float,t2.FVolume) as ��� from t_AssembleRecord t1 left join t_AssembleRecordentry t2 on t1.FID =t2.FID left join  T_SEC_user  t3 on t1.FBillerID=t3.FUSERID 
 left join t_bd_material_l t4 on  t2.FItemID=t4.fmaterialid left join  T_BD_UNIT_L t5 ON  t2.FUNITID = t5.FUNITID where t1.FID=@FID
end 
	else
	begin
	select convert(float, t6.FQty) as ������,convert(float,t6.FVolume) as �����,t1.FBillNo as ����,t1.FDate as װ������,t3.FName as �Ƶ���,t4.FName as ����,t5.FName as ��λ,(t1.FChang+'x'+t1.FHuang+'x'+t1.FHou) as ���,t2.FBatchNo as ����,t2.FLength as ����,convert(decimal(28,0),t2.FQty-isnull(t2.FQtyOut,0)) as ����,convert(float,t2.FVolume-isnull(t2.FVolumeOut,0)) as ��� from t_AssembleRecord t1 inner join t_AssembleRecordentry t2 on t1.FID =t2.FID left join  T_SEC_user  t3 on t1.FBillerID=t3.FUSERID left join t_bd_material_l t4 on  t2.FItemID=t4.fmaterialid left join  T_BD_UNIT_L t5 ON  t2.FUNITID = t5.FUNITID inner join (select sum(FQty-isnull(FQtyOut,0)) as FQty,sum(FVolume-isnull(FVolumeOut,0)) as FVolume,FID from t_AssembleRecordentry where FQty>isnull(FQtyOut,0)  group by FID) t6 on t1.FID =t6.FID  where t1.FBillNo = @FBillNo order by FEntryID
	end
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
