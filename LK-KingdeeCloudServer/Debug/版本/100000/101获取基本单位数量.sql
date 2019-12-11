if (exists (select * from sys.objects where name = 'proc_GetQtyPDA'))
    drop proc proc_GetQtyPDA
go
create proc proc_GetQtyPDA
(  
  @FMATERIALID int,--��ƷID
  @FUnitID int,--��λ
  @FQty decimal(28,18)--
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
declare @FBASEUnitName varchar(128),--������λ����
        @FSTOREUnitName varchar(128),--��浥λ����
        @FAUXQty decimal(20,8),--��������
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
      select @FSTOREUnitName=FName from T_BD_UNIT_L where FUnitID=@FSTOREUNITID
            select @FBASEUnitName=FName from T_BD_UNIT_L where FUnitID=@FBASEUNITID
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
     if(@FAUXUNITID=0)
     set @FAUXQty=0
     else
     begin
      if exists(select 1 from T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FAUXUNITID)
       select @FSTOREQty=@FBASEQty*FCONVERTDENOMINATOR/FCONVERTNUMERATOR  from  T_BD_UNITCONVERTRATE where   FMASTERID=@FMASTERID and FCURRENTUNITID=@FSTOREUNITID   
      else
        select @FSTOREQty=@FBASEQty*FCONVERTDENOMINATOR/FCONVERTNUMERATOR  from  T_BD_UNITCONVERTRATE where   FMASTERID=0 and FCURRENTUNITID=@FSTOREUNITID   
     end
    select convert(float,@FBASEQty) as ������λ����,convert(float,@FSTOREQty) as ��浥λ����,@FBASEUnitName as ������λ����,@FSTOREUnitName as ��浥λ���� 
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
