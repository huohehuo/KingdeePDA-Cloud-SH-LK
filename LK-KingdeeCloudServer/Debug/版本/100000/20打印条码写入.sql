if (exists (select * from sys.objects where name = 'proc_PDABarCodeSign_Insert2'))
    drop proc proc_PDABarCodeSign_Insert2
go
create proc proc_PDABarCodeSign_Insert2
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
         @FStockNumber varchar(128),--�ֿ����
         @FCargoNumber varchar(128),--��������
         @FProductNumber varchar(128),--�����������
         @FIsAdd int,--�Ƿ�����
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
         @FRemark3 varchar(255),--ʵ�ʹ��,
         @FRemark4 varchar(255),--������ʶ,
             @FRemark5 varchar(255),--��������,
          @F_FFF_TEXT1 varchar(128),--�ȼ�   
          @F_FFF_INTEGER decimal(20,8), --ԭľ��
          @F_FFF_INTEGER1 decimal(20,8), --ԭľֱ��
          @F_FFF_INTEGER2 decimal(20,8), --�峤
          @F_FFF_INTEGER3 decimal(20,8), --���
          @F_FFF_INTEGER4 decimal(20,8), --���
          @F_FFF_Volume decimal(20,8), --���
           @FBatcnNoIndex varchar(128),--���Ÿ���ֵ
          @FWidth varchar(502),
          @F_TypeID int,
          @F_Plies  decimal(20,8),--����
         @FRemark6 varchar(255),--��������,
         @FRemark7 varchar(255),--�������
         @FRemark8 varchar(255)--�豸ID
set @FDatePrint=convert(varchar(128),getdate(),20)
set @FDatePrintShort = convert(varchar(128),getdate(),112)
set @FKFPeriod=0
set @FKFDate = ''
set @FPrintType = 'PDA��Ʒ���'
set @FRemark1 =''
set @FRemark2 =''
set @FMATERIALID = dbo.getString(@mainStr,'|',1) --��ƷID 
set @FUnitID = dbo.getString(@mainStr,'|',2) --��λID 
set @FQty = dbo.getString(@mainStr,'|',3) --����
set @FRemark3 = dbo.getString(@mainStr,'|',4) --ʵ�ʹ��
set @FRemark4 = dbo.getString(@mainStr,'|',5) --������ʶ
set @FRemark7 = dbo.getString(@mainStr,'|',6) --�������
set @FRemark8 = dbo.getString(@mainStr,'|',7) --�豸ID(PDA���к�)
set @FStockNumber = dbo.getString(@mainStr,'|',8) --�ֿ����
set @FCargoNumber = dbo.getString(@mainStr,'|',9) --��������
 
set @F_FFF_TEXT1 = dbo.getString(@mainStr,'|',10)  --�ȼ�
set @F_FFF_INTEGER = dbo.getString(@mainStr,'|',11)  --ԭľ��
set @F_FFF_INTEGER1 = dbo.getString(@mainStr,'|',12) --ԭľֱ��
set @F_FFF_INTEGER2 = dbo.getString(@mainStr,'|',13) --�峤
set @F_FFF_INTEGER3 = dbo.getString(@mainStr,'|',14) --���
set @F_FFF_INTEGER4 = dbo.getString(@mainStr,'|',15) --���
set @F_FFF_Volume = dbo.getString(@mainStr,'|',16) --���
set @FBatchNo = dbo.getString(@mainStr,'|',17) --����
set @FProductNumber = dbo.getString(@mainStr,'|',18) --�����������
set @FIsAdd = dbo.getString(@mainStr,'|',19) --�Զ��������� 1�� 0����
set @FWidth = dbo.getString(@mainStr,'|',20) --�������ֵ
set @F_TypeID = dbo.getString(@mainStr,'|',21)-- ���ֵ������� 0 ��ˮ�� 1������
set @F_Plies = dbo.getString(@mainStr,'|',22)-- ����
set @FBatcnNoIndex = dbo.getString(@mainStr,'|',23) --���Ÿ���ֵ

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
		  if(@FAUXUNITID=0)
		 set @FAUXQty=0
		 else
		 begin
      if exists(select 1 from T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FAUXUNITID)
        select @FAUXQty=@FBASEQty*FCONVERTDENOMINATOR/FCONVERTNUMERATOR  from  T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FAUXUNITID   
      else
        select @FAUXQty=@FBASEQty*FCONVERTDENOMINATOR/FCONVERTNUMERATOR  from  T_BD_UNITCONVERTRATE where   FMASTERID=0 and FCURRENTUNITID=@FAUXUNITID   
        end
     --��浥λ���� 
      if exists(select 1 from T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FSTOREUNITID)
       select @FSTOREQty=@FBASEQty*FCONVERTDENOMINATOR/FCONVERTNUMERATOR  from  T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FSTOREUNITID   
      else
        select @FSTOREQty=@FBASEQty*FCONVERTDENOMINATOR/FCONVERTNUMERATOR  from  T_BD_UNITCONVERTRATE where   FMASTERID=0 and FCURRENTUNITID=@FSTOREUNITID   
   --  end
set @FRemark6 = @FAUXQty
--��������
if(@FIsAdd = 1)
begin
	if exists(select 1  from T_BD_MATERIALSTOCK where FISBATCHMANAGE =1)
	begin
	select @FIndex=isnull(max(FIndex),0)+1 from t_PDABatchNoIndex where FShortDate = @FDatePrintShort
	if exists(select 1 from t_PDABatchNoIndex where FShortDate = @FDatePrintShort)
	begin
	  update t_PDABatchNoIndex set FIndex=@FIndex where FShortDate = @FDatePrintShort
	end
	else
	  begin
	   insert into t_PDABatchNoIndex(FMATERIALID,FStockNumber,FCargoNumber,FShortDate,FIndex)
	   values(@FMATERIALID,@FProductNumber,@FCargoNumber,@FDatePrintShort,@FIndex)
	  end
	set @FShortIndex='000000000000'+convert(varchar(12),@FIndex) 
	set @FBatchNo = @FProductNumber+@FDatePrintShort+right(@FShortIndex,3)+@FBatcnNoIndex
	end
	else
	begin
	set @FBatchNo=''
	end
end
--��������
--��������
select @FIndex=isnull(max(FIndex),0)+1 from t_PDABarCodeSign where FItemID=@FMATERIALID and FDatePrintShort = @FDatePrintShort
set @FShortBarCode='000000000000'+convert(varchar(12),@FMATERIALID)
set @FShortIndex='000000000000'+convert(varchar(12),@FIndex)
set @FBarCode = right(@FShortBarCode,8)+@FDatePrintShort+right(@FShortIndex,6)
 
--��������
 insert into t_PDABarCodeSign(FIndex,FInterID,FEntryID,FItemID,FUnitID,FBarCode,FDatePrint,FQty,FBatchNo,FKFPeriod,FKFDate,FDatePrintShort,FPrintType,FBillNo,FInterIDAssemble,FPathLab,FRemark,FRemark1,FRemark2,FRemark3,FRemark4,FRemark5,FRemark6,FRemark7,FRemark8,F_FFF_TEXT1,F_FFF_INTEGER,F_FFF_INTEGER1,F_FFF_INTEGER2,F_FFF_INTEGER3,F_FFF_INTEGER4,F_FFF_Volume,FWidth,F_TypeID,F_Plies)
                    values(@FIndex,0,0,@FMATERIALID,@FUnitID,@FBarCode,@FDatePrint,@FQty,@FBatchNo,@FKFPeriod,@FKFDate,@FDatePrintShort,@FPrintType,'',0,'','',@FRemark1,@FRemark2,@FRemark3,@FRemark4,@FRemark5,@FRemark6,@FRemark7,@FRemark8,@F_FFF_TEXT1,@F_FFF_INTEGER,@F_FFF_INTEGER1,@F_FFF_INTEGER2,@F_FFF_INTEGER3,@F_FFF_INTEGER4,@F_FFF_Volume,@FWidth,@F_TypeID,@F_Plies)
create table #Tmp11112 --������ʱ��#Tmp
( 
    FBarCode varchar(128),
    FBatchNo varchar(128),
   
)
select @FSTOREQty=ROUND( @FSTOREQty,FPRECISION) from T_BD_UNIT   where FUNITID=@FSTOREUNITID
select @FBASEQty=ROUND( @FBASEQty,FPRECISION) from T_BD_UNIT   where FUNITID=@FBASEUNITID
insert into #Tmp11112(FBarCode,FBatchNo) values(@FBarCode,@FBatchNo)
select FBarCode as ����,FBatchNo as ����,convert(float,isnull(@FAUXQty,0)) as ��������,convert(float,@FBASEQty) as ������λ����,convert(float,@FSTOREQty) as ��浥λ����  from #Tmp11112
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

