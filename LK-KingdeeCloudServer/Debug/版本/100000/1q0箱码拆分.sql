if (exists (select * from sys.objects where name = 'proc_PDACountOff_Split1'))
    drop proc proc_PDACountOff_Split1
go
create proc proc_PDACountOff_Split1
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
          @FAssembleBillNo varchar(128),--��������
          @FBillNo varchar(128),
        @FShortDate varchar(128),
        @FBillerID int,   --�Ƶ���id 
        @FRemark varchar(200)--ժҪ 
set @FBillerID = dbo.getString(@mainStr,'|',1) --����Ա
set @FAssembleBillNo =  dbo.getString(@mainStr,'|',2) --��������

if not exists(select 1 from t_AssembleRecord1 where FBillNo = @FAssembleBillNo)
begin
print convert(int,'���ʧ��,��̨�����ڸ�����')
end

set @Fdate = convert(varchar(20),GETDATE(),20)

set @FShortDate =convert(nvarchar(60),getdate(),112)

------------------------------------------------------------�õ�����
declare  @FIndex int,  --(ѭ����) 
         @FNum varchar(50) --��ˮ��
        set @FBillNo ='' 
 
select @FIndex=isnull(MAX(FIndex),0)+1 from t_AssembleRecord1 where FShortDate=@FShortDate
set @FNum='000000000'+CONVERT(varchar(20),@FIndex)
set @FNum=right(@FNum,6)  
set @FBillNo='ZZ'+@FShortDate+@FNum
INSERT INTO t_AssembleRecord1(FBillNo,FIndex,FDate,FBillerID,FStatus,FShortDate,FCarNum,FHuZhu,FName,FSourceID,FType,FStockName,FPackets,FWidth) select @FBillNo,@FIndex,@FDate,@FBillerID,0,@FShortDate,FCarNum,FHuZhu,FName,FSourceID,FType,FStockName,FPackets,FWidth from  t_AssembleRecord1 where FBillNo = @FAssembleBillNo
select @FID=MAX(FID) from t_AssembleRecord1

 declare @FEntryID varchar(20),       --��ϸ���  
  @FQty decimal(28,10), 
        @detailqty int,               --��ϸ�����ĸ���
        @detailcount int,             --��ϸÿ�����ݵĳ��� 
        @detailIndex int,            --��ϸÿ���±�
        @countindex int              --�ָ���|������
        
       set @detailqty=0        
       set @detailcount=2   
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
	set @FQty=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+1)    --���� 
  
    set @FEntryID=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+2) --��ϸΨһ���  
    
	set @detailIndex=@detailIndex+1
 
	update t_AssembleRecord1Entry set FQtyOut =isnull(FQtyOut,0) + @FQty,FVolumeOut =isnull(FVolumeOut,0) +convert(decimal(20,4), @FQty/FQty*FVolume) where FEntryID =@FEntryID
	    insert into t_AssembleRecord1Entry(FID,FItemID,FUnitID,FWidth,FLength,FLevel,FQty,FDCStockID,FDCSPID,FBatchNo,FSTOCKORGID,FOWNERID,FVolume,FQtyOut,FVolumeOut)
	select @FID,FItemID,FUnitID,FWidth,FLength,FLevel,@FQty,FDCStockID,FDCSPID,FBatchNo,FSTOCKORGID,FOWNERID,convert(decimal(20,4), @FQty/FQty*FVolume),0,0 from t_AssembleRecord1Entry where FEntryID =@FEntryID
	
        insert into t_AssembleRecord1Entry_C(FID,FItemID,FUnitID,FWidth,FLength,FLevel,FQty,FDCStockID,FDCSPID,FBatchNo,FSTOCKORGID,FOWNERID,FVolume,FSourceFEntryID)
	select @FID,FItemID,FUnitID,FWidth,FLength,FLevel,@FQty,FDCStockID,FDCSPID,FBatchNo,FSTOCKORGID,FOWNERID,convert(decimal(20,4), @FQty/FQty*FVolume),FEntryID from t_AssembleRecord1Entry where FEntryID =@FEntryID
  
  set @FEntryID=@detailqty*50+@detailIndex 
end
set @detailqty=@detailqty+1
end

select t1.FType as ��������,t1.FStockName as �ֿ�,t1.FPackets as ����,t1.FWidth as ���,convert(float, t6.FQty) as ������,convert(decimal(20,4),t6.FVolume) as �����,t1.FBillNo as ����,t1.FDate as װ������,t3.FName as �Ƶ���,t1.FName as ����,t1.FHuZhu as ��������,t1.FCarNum as ����,t5.FName as ��λ, t4.FSPECIFICATION as ���,t2.FBatchNo as ����,t2.FLevel as �ȼ�,convert(decimal(28,0),t2.FQty-isnull(t2.FQtyOut,0)) as ����,convert(decimal(20,4),t2.FVolume-isnull(t2.FVolumeOut,0)) as ��� from t_AssembleRecord1 t1 inner join t_AssembleRecord1entry t2 on t1.FID =t2.FID left join  T_SEC_user  t3 on t1.FBillerID=t3.FUSERID left join t_bd_material_l t4 on  t2.FItemID=t4.fmaterialid left join  T_BD_UNIT_L t5 ON  t2.FUNITID = t5.FUNITID inner join (select sum(FQty-isnull(FQtyOut,0)) as FQty,sum(FVolume-isnull(FVolumeOut,0)) as FVolume,FID from t_AssembleRecord1entry where FQty>isnull(FQtyOut,0)  group by FID) t6 on t1.FID =t6.FID  where t1.FBillNo = @FBillNo order by convert(int,t2.FWidth),t2.FLevel,convert(int,FLength) desc
 
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
