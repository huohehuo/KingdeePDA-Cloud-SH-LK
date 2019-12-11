if not exists(select * from sysobjects where xtype='u' and name='t_AssembleRecord1Entry')
begin
create table t_AssembleRecord1Entry
(
  FID int, --明细键 
  FEntryID int identity(1,1) primary key not null,--明细ID
  FItemID int not null,   --商品id
  FUnitID int not null,   --单位id
  FWidth varchar(128),--宽度
  FLength varchar(128),--长度
  FLevel varchar(128),--等级
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
CREATE NONCLUSTERED INDEX [idx_t_AssembleRecord1Entry] ON [dbo].t_AssembleRecord1Entry 
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
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_AssembleRecord1Entry' AND T2.NAME='FQtyCatch')
alter table t_AssembleRecord1Entry add FQtyCatch decimal(28,10) DEFAULT 0 --追加数量
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_AssembleRecord1Entry' AND T2.NAME='FVolumeCatch')
alter table t_AssembleRecord1Entry add FVolumeCatch decimal(28,10) DEFAULT 0 --追加体积
