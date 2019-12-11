if (exists (select * from sys.objects where name = 'proc_PDACountOffALL_Insert1'))
    drop proc proc_PDACountOffALL_Insert1
go
create proc proc_PDACountOffALL_Insert1
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

declare @FID int,     --����id 
        @Fdate datetime,       --����  
        @FBillerID int,   --�Ƶ���id
        @FBillNo varchar(50),--���� 
        @FCarNum varchar(128),--���ƺ�
        @FHuZhu varchar(128),--��������
        @FHuoZhuNumber varchar(128),--��������
        @FBatchNo varchar(255), --����
        @FStockNumber varchar(255),--�ֿ���� 
        @FDCStockID int, --�ֿ�id
        @FDCSPID int, --��λid 
        @FSTOCKORGID int,--�����֯ID
        @FOWNERID int,  --����ID
        @FName varchar(255),--����(ȡ��������ǰ2��ά��)
        @FSourceID int,--Դ������
        @FShortDate varchar(128),
        @FPackets1_i int,--�ְ���
        @FPackets1 varchar(128),--�ְ���
        @FRemark varchar(200)--ժҪ 
set @FBillerID = dbo.getString(@mainStr,'|',1) --����Ա
set @FCarNum = dbo.getString(@mainStr,'|',2) --���ƺ� 
set @FHuZhu = dbo.getString(@mainStr,'|',3) --��������
set @FSourceID = dbo.getString(@mainStr,'|',4) --Դ������ ID(�������ϵ�ID)
set @FStockNumber =dbo.getString(@mainStr,'|',5) --�ֿ����
set @FDCStockID =dbo.getString(@mainStr,'|',6) --�ֿ�ID
set @FSTOCKORGID =dbo.getString(@mainStr,'|',7) --�����֯ID
set @FOWNERID =dbo.getString(@mainStr,'|',8) --����ID
set @FPackets1_i = dbo.getString(@mainStr,'|',9) --�ְ���
select @FHuoZhuNumber=FNumber from T_ORG_Organizations where FORGID = @FOWNERID

set @FPackets1=convert( varchar(128),@FPackets1_i)+'-1'
set @Fdate = convert(varchar(20),GETDATE(),20)
set @FShortDate =convert(nvarchar(60),getdate(),112)
------------------------------------------------------------�õ�����
declare  @FIndex int,  --(ѭ����) 
         @FNum varchar(50) --��ˮ��
        set @FBillNo ='' 
select  @FName=  st31.FName from    T_SP_PICKMTRL t0   LEFT OUTER JOIN T_SP_PICKMTRLData t3 ON t0.FID = t3.FID  LEFT OUTER JOIN T_BD_MATERIAL_L st31 ON t3.FMATERIALID = st31.FMATERIALID where t0.FID =@FSourceID
set  @FName = replace(@FName, reverse(substring(reverse(@FName),1,charindex('*',reverse(@FName)) )) ,'')
select @FIndex=isnull(MAX(FIndex),0)+1 from t_AssembleRecord1 where FShortDate=@FShortDate
set @FNum='000000000'+CONVERT(varchar(20),@FIndex)
set @FNum=right(@FNum,6)  
set @FBillNo='ZZ'+@FShortDate+@FNum 
INSERT INTO t_AssembleRecord1(FBillNo,FIndex,FDate,FBillerID,FStatus,FShortDate,FCarNum,FHuZhu,FName,FSourceID,FType,FPackets1) 
values (@FBillNo,@FIndex,@FDate,@FBillerID,0,@FShortDate,@FCarNum,@FHuZhu,@FName,@FSourceID,0,@FPackets1)
select @FID=MAX(FID) from t_AssembleRecord1
 
 --�������� 
 declare @FDatePrintShort varchar(8),
         @FShortIndex varchar(50)
set @FDatePrintShort = RIGHT( convert(nvarchar(6),getdate(),112),4)
select @FIndex=isnull(max(FIndex),0)+1 from t_PDABatchNoIndex1 where FShortDate = @FDatePrintShort and FHuoZhuNumber=@FHuoZhuNumber and FStockNumber=@FStockNumber
if exists(select 1 from t_PDABatchNoIndex1 where FShortDate = @FDatePrintShort  and FHuoZhuNumber=@FHuoZhuNumber and FStockNumber=@FStockNumber)
begin
  update t_PDABatchNoIndex1 set FIndex=@FIndex where FShortDate = @FDatePrintShort  and FHuoZhuNumber=@FHuoZhuNumber and FStockNumber=@FStockNumber
end
else
  begin
   insert into t_PDABatchNoIndex1(FMATERIALID,FStockNumber,FHuoZhuNumber,FShortDate,FIndex)
   values(0,@FStockNumber,@FHuoZhuNumber,@FDatePrintShort,@FIndex)
  end
set @FShortIndex='000000000000'+convert(varchar(12),@FIndex)
set @FBatchNo = @FStockNumber+@FDatePrintShort+right(@FShortIndex,3) 
--װ��
select @FID= FID  from t_AssembleRecord1 where FBillNo=@FBillNo
declare @FEntryID varchar(20),       --��ϸ���  
        @FMATERIALID int,--��ƷID
        @FUNITID int,--��λID
        @FUNITNumber varchar(128),--��λ����
        @FWidth int,--���
        @FLength varchar(128),--����
        @FLevel varchar(128),--�ȼ�
        @FVolume decimal(28,10),--���
        @FQTY decimal(28,10), --����
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
	set @detailIndex=@detailIndex+1
    select @FWidth=convert(int, dbo.getString(st33.FNumber,'.',6)),@FLength=dbo.getString(st33.FNumber,'.',7),@FLevel=dbo.getString(st33.FNumber,'.',4) from T_BD_MATERIAL st33 where  FMATERIALID=@FMATERIALID
    insert into t_AssembleRecord1Entry(FID,FItemID,FUnitID,FQty,FDCStockID,FDCSPID,FBatchNo,FSTOCKORGID,FOWNERID,FWidth,FLength,FLevel,FVolume)
	values (@FID,@FMATERIALID,@FUNITID,@FQty,@FDCStockID,0,@FBatchNo,@FSTOCKORGID,@FOWNERID,@FWidth,@FLength,@FLevel,@FVolume)
  set @FEntryID=@detailqty*50+@detailIndex 
end
set @detailqty=@detailqty+1
end
--װ��
--��������
------------------------------------------------------------�õ�����
---�ְ�����������
if(@FPackets1_i>1)
begin
  declare @FID1 int,
          @I int,
          @FBillNo1 varchar(128)
  set @I = 1
  while(@I<@FPackets1_i)
  begin
    set @I = @I+1
    set @FBillNo1=@FBillNo+convert(varchar(128),@I)
    set @FPackets1=convert( varchar(128),@FPackets1_i)+'-' +convert(varchar(128),@I)
    INSERT INTO t_AssembleRecord1(FBillNo,FIndex,FDate,FBillerID,FStatus,FShortDate,FCarNum,FHuZhu,FName,FSourceID,FType,FPackets1)
    select @FBillNo1,@FIndex,@FDate,@FBillerID,0,@FShortDate,@FCarNum,@FHuZhu,@FName,@FSourceID,0,@FPackets1 from t_AssembleRecord1 where FID = @FID
    select @FID1=MAX(FID) from t_AssembleRecord1 where FBillNo=@FBillNo1
    insert into t_AssembleRecord1Entry(FID,FItemID,FUnitID,FQty,FDCStockID,FDCSPID,FBatchNo,FSTOCKORGID,FOWNERID,FWidth,FLength,FLevel,FVolume)
    select @FID1,FItemID,FUnitID,FQty,FDCStockID,FDCSPID,FBatchNo,FSTOCKORGID,FOWNERID,FWidth,FLength,FLevel,FVolume from t_AssembleRecord1Entry where FID=@FID
  end
end 
---�ְ�����������
select t1.FPackets1 as �ְ���,t1.FWidth as ���,t1.FPackets as ����,t1.FStockName as �ֿ�,convert(float, t6.FQty) as ������,convert(decimal(20,4),t6.FVolume) as �����,t1.FBillNo as ����,t1.FDate as װ������,t3.FName as �Ƶ���,t1.FName as ����,t1.FHuZhu as ��������,t1.FCarNum as ����,t5.FName as ��λ, t4.FSPECIFICATION as ���,t2.FBatchNo as ����,t2.FLevel as �ȼ�,convert(decimal(28,0),t2.FQty-isnull(t2.FQtyOut,0)) as ����,convert(decimal(20,4),t2.FVolume-isnull(t2.FVolumeOut,0)) as ��� from t_AssembleRecord1 t1 inner join t_AssembleRecord1entry t2 on t1.FID =t2.FID left join  T_SEC_user  t3 on t1.FBillerID=t3.FUSERID left join t_bd_material_l t4 on  t2.FItemID=t4.fmaterialid left join  T_BD_UNIT_L t5 ON  t2.FUNITID = t5.FUNITID inner join (select sum(FQty-isnull(FQtyOut,0)) as FQty,sum(FVolume-isnull(FVolumeOut,0)) as FVolume,FID from t_AssembleRecord1entry where FQty>isnull(FQtyOut,0)  group by FID) t6 on t1.FID =t6.FID  where t1.FBillNo = @FBillNo order by convert(int,t2.FWidth),t2.FLevel,convert(int,FLength) desc
 
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
