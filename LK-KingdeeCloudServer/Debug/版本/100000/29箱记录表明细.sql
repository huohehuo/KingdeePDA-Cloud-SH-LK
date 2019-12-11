if not exists(select * from sysobjects where xtype='u' and name='t_AssembleRecordEntry')
begin
create table t_AssembleRecordEntry
(
  FID int, --��ϸ�� 
  FEntryID int identity(1,1) primary key not null,--��ϸID
  FItemID int not null,   --��Ʒid
  FUnitID int not null,   --��λid
  FLength varchar(128),--����
  FQty decimal(28,10), --����
  FVolume decimal(28,10), --���
  FQtyOut decimal(28,10) DEFAULT 0, --��������
  FVolumeOut decimal(28,10) DEFAULT 0, --��������
  FDCStockID int, --�ֿ�id
  FDCSPID int, --��λid
  FBatchNo varchar(255), --����
  FSTOCKORGID int,--�����֯ID
  FOWNERID int  --����
)
CREATE NONCLUSTERED INDEX [idx_t_AssembleRecordEntry] ON [dbo].t_AssembleRecordEntry 
(
	FID ASC,
	FItemID ASC, 
	FUnitID ASC,   
	FSTOCKORGID  ASC,
	FOWNERID ASC,
	FStockID ASC, 
	FDCSPID ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
end
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_AssembleRecordEntry' AND T2.NAME='FVolumeOut')
alter table t_AssembleRecordEntry add FVolumeOut decimal(20,8)  default 0 --���