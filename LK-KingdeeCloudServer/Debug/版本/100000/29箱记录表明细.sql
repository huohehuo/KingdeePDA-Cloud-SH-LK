if not exists(select * from sysobjects where xtype='u' and name='t_AssembleRecordEntry')
begin
create table t_AssembleRecordEntry
(
  FID int, --明细键 
  FEntryID int identity(1,1) primary key not null,--明细ID
  FItemID int not null,   --商品id
  FUnitID int not null,   --单位id
  FLength varchar(128),--长度
  FQty decimal(28,10), --数量
  FVolume decimal(28,10), --面积
  FQtyOut decimal(28,10) DEFAULT 0, --出库数量
  FVolumeOut decimal(28,10) DEFAULT 0, --出库数量
  FDCStockID int, --仓库id
  FDCSPID int, --仓位id
  FBatchNo varchar(255), --批次
  FSTOCKORGID int,--库存组织ID
  FOWNERID int  --货主
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
alter table t_AssembleRecordEntry add FVolumeOut decimal(20,8)  default 0 --体积