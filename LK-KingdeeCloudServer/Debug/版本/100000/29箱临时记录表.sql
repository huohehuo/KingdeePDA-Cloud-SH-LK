if not exists(select * from sysobjects where xtype='u' and name='t_AssembleRecordTemp')
begin
create table t_AssembleRecordTemp
(
  FOrderID varchar(128),--PDA����
  FPDAID varchar(128),--PDA���
  FBillNo varchar(128),--����
  FItemID int not null,   --��Ʒid
  FUnitID int not null,   --��λid
  FLength varchar(128),--����
  FQty decimal(28,10), --����
  FVolume decimal(28,10), --��� 
  FDCStockID int, --�ֿ�id
  FDCSPID int, --��λid
  FBatchNo varchar(255), --����
  FSTOCKORGID int,--�����֯ID
  FOWNERID int  --����
)
end
 