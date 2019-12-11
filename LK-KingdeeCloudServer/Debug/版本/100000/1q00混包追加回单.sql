if (exists (select * from sys.objects where name = 'proc_PDAAssembleCatch_Insert1'))
    drop proc proc_PDAAssembleCatch_Insert1
go
create proc proc_PDAAssembleCatch_Insert1
(
 @mainStr varchar(1000),
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

declare @FID int,
        @FDCStockID int,--�ֿ�ID
        @FBatchNo varchar(255),--����
        @FSTOCKORGID int,--�����֯
        @FOWNERID int,--����
        
        @FBillerID int,   --�Ƶ���id
        @FBillNo varchar(50),--����  
        @FRemark varchar(200)--ժҪ 
set @FBillerID = dbo.getString(@mainStr,'|',1) --����Ա
set @FBillNo = dbo.getString(@mainStr,'|',2) --���� 
--װ��
select @FID= FID  from t_AssembleRecord1 where FBillNo=@FBillNo
select @FDCStockID=FDCStockID,@FBatchNo=FBatchNo,@FSTOCKORGID=FSTOCKORGID,@FOWNERID=FOWNERID from t_AssembleRecord1Entry where FID=@FID
declare @FEntryID varchar(20),       --��ϸ���  
        @FMATERIALID int,--��ƷID
        @FUNITID int,--��λID
        @FUNITNumber varchar(128),--��λ����
        @FWidth int,--���
        @FLength varchar(128),--����
        @FLevel varchar(128),--�ȼ�
        @FVolume decimal(28,10),--���
        @FQTY decimal(28,10), --����
        @FBASEUNITID int,--������λ
           @FCONVERTNUMERATOR decimal(20,8),--������λ������
        @FCONVERTDENOMINATOR decimal(20,8),--������λ������
        @FMASTERID int, --��ƷIDID
        
        @detailqty int,               --��ϸ�����ĸ���
        @detailcount int,             --��ϸÿ�����ݵĳ��� 
        @detailIndex int,            --��ϸÿ���±�
        @countindex int              --�ָ���|������
      
       set @detailqty=0        
       set @detailcount=4   
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
    set @FMATERIALID=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+1)    --��ƷID
    set @FUNITNumber=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+2)    --��λ
	set @FQTY=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+3)    --���� 
    set @FVolume=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+4)    --���
    select @FUNITID=FUNITID  from T_BD_UNIT where FNumber=@FUNITNumber
          select  @FMASTERID=FMASTERID  from T_BD_MATERIAL where  FMATERIALID=@FMATERIALID
	set @detailIndex=@detailIndex+1 
	   --������λ����
     if exists(select 1 from T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FUnitID)
      select @FVolume=@FQty*FCONVERTNUMERATOR/FCONVERTDENOMINATOR from T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FUnitID     
     else
       select @FVolume=@FQty*FCONVERTNUMERATOR/FCONVERTDENOMINATOR from T_BD_UNITCONVERTRATE where   FMASTERID=0 and FCURRENTUNITID=@FUnitID  
	select @FBASEUNITID=FBASEUNITID  from  t_BD_MaterialBase where FMATERIALID=@FMATERIALID
	
    select @FWidth=convert(int, dbo.getString(st33.FNumber,'.',6)),@FLength=dbo.getString(st33.FNumber,'.',7),@FLevel=dbo.getString(st33.FNumber,'.',4) from T_BD_MATERIAL st33 where  FMATERIALID=@FMATERIALID
    if exists(select 1 from t_AssembleRecord1Entry where FID=@FID and FItemID=@FMATERIALID)
    begin
       update t_AssembleRecord1Entry set FQty=FQty+@FQTY,FVolume=FVolume+@FVolume,FQtyCatch=isnull(FQtyCatch,0)+@FQty,FVolumeCatch=isnull(FVolumeCatch,0)+@FVolume where FID=@FID and FItemID=@FMATERIALID
    end
    else
    begin
    insert into t_AssembleRecord1Entry(FID,FItemID,FUnitID,FQty,FDCStockID,FDCSPID,FBatchNo,FSTOCKORGID,FOWNERID,FWidth,FLength,FLevel,FVolume,FQtyCatch,FVolumeCatch)
	values (@FID,@FMATERIALID,@FUNITID,@FQty,@FDCStockID,0,@FBatchNo,@FSTOCKORGID,@FOWNERID,@FWidth,@FLength,@FLevel,@FVolume,@FQty,@FVolume)
    end
  set @FEntryID=@detailqty*50+@detailIndex 
end
set @detailqty=@detailqty+1
end 
select t1.FType as ��������,t1.FPackets1 as �ְ���,t1.FWidth as ���,t1.FPackets as ����,t1.FStockName as �ֿ�,convert(float, t6.FQty) as ������,convert(decimal(20,4),t6.FVolume) as �����,t1.FBillNo as ����,t1.FDate as װ������,t3.FName as �Ƶ���,t1.FName as ����,t1.FHuZhu as ��������,t1.FCarNum as ����,t5.FName as ��λ, t4.FSPECIFICATION as ���,t2.FBatchNo as ����,t2.FLevel as �ȼ�,convert(decimal(28,0),t2.FQty-isnull(t2.FQtyOut,0)) as ����,convert(decimal(20,4),t2.FVolume-isnull(t2.FVolumeOut,0)) as ��� from t_AssembleRecord1 t1 inner join t_AssembleRecord1entry t2 on t1.FID =t2.FID left join  T_SEC_user  t3 on t1.FBillerID=t3.FUSERID left join t_bd_material_l t4 on  t2.FItemID=t4.fmaterialid left join  T_BD_UNIT_L t5 ON  t2.FUNITID = t5.FUNITID inner join (select sum(FQty-isnull(FQtyOut,0)) as FQty,sum(FVolume-isnull(FVolumeOut,0)) as FVolume,FID from t_AssembleRecord1entry where FQty>isnull(FQtyOut,0)  group by FID) t6 on t1.FID =t6.FID  where t1.FBillNo = @FBillNo order by convert(int,t2.FWidth),t2.FLevel,convert(int,FLength) desc
 
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
