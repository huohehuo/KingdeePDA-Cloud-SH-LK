if (exists (select * from sys.objects where name = 'proc_PDABarCodeSignBatch_Insert'))
    drop proc proc_PDABarCodeSignBatch_Insert
go
create proc proc_PDABarCodeSignBatch_Insert
(
 @mainStr nvarchar(1000) --�������
  
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
declare  @FShortBarCode varchar(128),--������
         @FShortIndex varchar(128),--����ˮ�� 
         @FCargoNumber varchar(128),--��������
         @FIndex int,--��ˮ�� 
         @FMATERIALID int,--��ƷID
         @FUnitID int,--��λID,
         @FBarCode varchar(128),--����  
         @FDatePrint varchar(128),--��ӡ����  
         @FQty decimal(28,18),--����,
         @FBatchNo varchar(125),--����  
         @FKFPeriod int,@FKFDate varchar(12),
         @FDatePrintShort varchar(128),--������  
         @FPrintType varchar(128),--��ֵ
         @FRemark1 varchar(128),--�������
         @FRemark2 varchar(128),--��������,
       @FStockID int,--��λID
         @FRemark3 varchar(255),--ʵ�ʹ��,
         @FRemark4 varchar(255),--������ʶ,
             @FRemark5 varchar(255),--��������,
         @FRemark6 varchar(255),--��������, 
         @FRemark8 varchar(255)--�豸ID
set @FDatePrint=convert(varchar(128),getdate(),20)
set @FDatePrintShort = convert(varchar(128),getdate(),112)
set @FKFPeriod=0
set @FKFDate = ''
set @FPrintType = 'PDA���Ų���'
set @FRemark1 ='PDA���Ų������'
set @FRemark2 =''
set @FMATERIALID = dbo.getString(@mainStr,'|',1) --��ƷID 
set @FUnitID = dbo.getString(@mainStr,'|',2) --��λID 
set @FQty = dbo.getString(@mainStr,'|',3) --����
set @FBatchNo = dbo.getString(@mainStr,'|',4) --����
set @FRemark8 = dbo.getString(@mainStr,'|',5) --�豸ID(PDA���к�)
 
set @FCargoNumber = dbo.getString(@mainStr,'|',6) --��������

set @FRemark3 = dbo.getString(@mainStr,'|',7) --ʵ�ʹ��
set @FRemark4 = dbo.getString(@mainStr,'|',8) --������ʶ
set @FRemark5=@FCargoNumber
--SELECT @FRemark5 = t0_L.FNAME   FROM T_ORG_Organizations t0 LEFT OUTER JOIN T_ORG_Organizations_L t0_L ON (t0.FORGID = t0_L.FORGID AND t0_L.FLocaleId = 2052) where t0.FNUMBER=@FCargoNumber
--��������
declare @FAUXQty decimal(20,8),--��������
        @FUnitQty decimal(20,8),--��λ����
        @FAUXUNITID int,--������λ
        @FSTOREUNITID int,--��浥λ
        @FSTOREQty decimal(20,8),--��浥λ����
        @FBASEUNITID int,--������λ
        @FBASEQty decimal(20,8),--������λ����
        @FCONVERTNUMERATOR decimal(20,8),--������λ������
        @FCONVERTDENOMINATOR decimal(20,8),--������λ������
        @FMASTERID int --��ƷIDID
	set @FSTOREQty=@FQty
      select  @FMASTERID=FMASTERID  from T_BD_MATERIAL where  FMATERIALID=@FMATERIALID
      select @FBASEUNITID=FBASEUNITID from  t_BD_MaterialBase where  FMATERIALID=@FMATERIALID
      select  @FAUXUNITID=FAUXUNITID,@FSTOREUNITID=FSTOREUNITID  from T_BD_MATERIALSTOCK where  FMATERIALID=@FMATERIALID
     --������λ����
     if exists(select 1 from T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FUnitID)
      select @FBASEQty=@FQty*FCONVERTNUMERATOR/FCONVERTDENOMINATOR from T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FUnitID     
     else
       select @FBASEQty=@FQty*FCONVERTNUMERATOR/FCONVERTDENOMINATOR from T_BD_UNITCONVERTRATE where   FMASTERID=0 and FCURRENTUNITID=@FUnitID     
     if exists(select 1 from T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FUnitID)
     --������λ����
      if exists(select 1 from T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FAUXUNITID)
        select @FAUXQty=@FBASEQty*FCONVERTDENOMINATOR/FCONVERTNUMERATOR  from  T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FAUXUNITID   
      else
        select @FAUXQty=@FBASEQty*FCONVERTDENOMINATOR/FCONVERTNUMERATOR  from  T_BD_UNITCONVERTRATE where   FMASTERID=0 and FCURRENTUNITID=@FAUXUNITID   
     --��浥λ����
     --if(@FAUXUNITID=0)
     --set @FAUXQty=0
     --else
     --begin
     -- if exists(select 1 from T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FAUXUNITID)
     --  select @FSTOREQty=@FBASEQty*FCONVERTDENOMINATOR/FCONVERTNUMERATOR  from  T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FSTOREUNITID   
     -- else
     --   select @FSTOREQty=@FBASEQty*FCONVERTDENOMINATOR/FCONVERTNUMERATOR  from  T_BD_UNITCONVERTRATE where   FMASTERID=0 and FCURRENTUNITID=@FSTOREUNITID   
     --end
--��������
--declare @FAUXQty decimal(20,8),--��������
--        @FUnitQty decimal(20,8),--��λ����
--        @FAUXUNITID int,--������λ
--        @FMASTERID int --��ƷIDID
--      select  @FMASTERID=FMASTERID  from T_BD_MATERIAL where  FMATERIALID=@FMATERIALID
 
--   select  @FAUXUNITID=FAUXUNITID  from T_BD_MATERIALSTOCK where  FMATERIALID=@FMATERIALID
--   if(@FAUXUNITID=0)
--   set @FAUXQty = 0
--   else
--   begin 
--select @FUnitQty=@FQty*FCONVERTDENOMINATOR from T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FUnitID
--select @FAUXQty=@FUnitQty*FCONVERTDENOMINATOR from T_BD_UNITCONVERTRATE where    FMASTERID=@FMASTERID and FCURRENTUNITID=@FAUXUNITID
 
--if(@FAUXQty=0 or @FAUXQty is null)
--begin
-- set @FAUXQty=@FQty
--end
--   end
--
set @FRemark6 = @FAUXQty
select @FStockID=t0.FStockID from T_STK_INVENTORY t0 left join    T_BD_LOTMASTER st035 ON t0.FLOT = st035.FLOTID where st035.Fnumber = @FBatchNo
--��������
select @FIndex=isnull(max(FIndex),0)+1 from t_PDABarCodeSign where FItemID=@FMATERIALID and FDatePrintShort = @FDatePrintShort
set @FShortBarCode='000000000000'+convert(varchar(12),@FMATERIALID)
set @FShortIndex='000000000000'+convert(varchar(12),@FIndex)
set @FBarCode = right(@FShortBarCode,8)+@FDatePrintShort+right(@FShortIndex,6)
 
--��������
 insert into t_PDABarCodeSign(FIndex,FInterID,FEntryID,FItemID,FUnitID,FBarCode,FDatePrint,FQty,FBatchNo,FKFPeriod,FKFDate,FDatePrintShort,FPrintType,FBillNo,FInterIDAssemble,FPathLab,FRemark,FRemark1,FRemark2,FRemark3,FRemark4,FRemark5,FRemark6,FRemark8,FInterIDIn,FIsInStore,FStockID,FStockPlaceID)
                    values(@FIndex,0,0,@FMATERIALID,@FUnitID,@FBarCode,@FDatePrint,@FQty,@FBatchNo,@FKFPeriod,@FKFDate,@FDatePrintShort,@FPrintType,'',0,'','',@FRemark1,@FRemark2,@FRemark3,@FRemark4,@FRemark5,@FRemark6,@FRemark8,0,'�����',@FStockID,0)
create table #Tmp11112 --������ʱ��#Tmp
( 
    FBarCode varchar(128),
    FBatchNo varchar(128),
   
)
select @FSTOREQty=ROUND( @FSTOREQty,FPRECISION) from T_BD_UNIT   where FUNITID=@FSTOREUNITID
select @FBASEQty=ROUND( @FBASEQty,FPRECISION) from T_BD_UNIT   where FUNITID=@FBASEUNITID
insert into #Tmp11112(FBarCode,FBatchNo) values(@FBarCode,@FBatchNo)
select FBarCode as ����,FBatchNo as ����,convert(float,isnull(@FAUXQty,0)) as ��������,convert(float,@FBASEQty) as ������λ����,convert(float,@FSTOREQty) as ��浥λ���� from #Tmp11112
drop table #Tmp11112 
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

