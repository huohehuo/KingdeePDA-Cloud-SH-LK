if not exists(select * from sysobjects where xtype='u' and name='t_AssembleRecordEntry_C')
begin
create table t_AssembleRecordEntry_C
(
  FID int, --��ϸ�� 
  FEntryID int   identity(1,1) primary key not null, 
  FSourceID int,
  FSourceFEntryID int,
  FItemID int not null,   --��Ʒid
  FUnitID int not null,   --��λid
  FLength varchar(128),--����
  FQty decimal(28,10), --����
  FVolume decimal(28,10), --��� 
  FDCStockID int, --�ֿ�id
  FDCSPID int, --��λid
  FBatchNo varchar(255), --����
  FSTOCKORGID int,--�����֯ID
  FDate datetime,
  FUserID int,--
  FOWNERID int  --����
)
CREATE NONCLUSTERED INDEX [idx_t_AssembleRecordEntry_C] ON [dbo].t_AssembleRecordEntry_C
(
	FID ASC,
	FItemID ASC, 
	FUnitID ASC,   
	FSTOCKORGID  ASC,
	FOWNERID ASC,
	FDCStockID ASC, 
	FDCSPID ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
end
 