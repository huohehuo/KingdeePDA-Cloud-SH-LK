if not exists(select * from sysobjects where xtype='u' and name='t_AssembleRecordEntry_C')
begin
create table t_AssembleRecordEntry_C
(
  FID int, --明细键 
  FEntryID int   identity(1,1) primary key not null, 
  FSourceID int,
  FSourceFEntryID int,
  FItemID int not null,   --商品id
  FUnitID int not null,   --单位id
  FLength varchar(128),--长度
  FQty decimal(28,10), --数量
  FVolume decimal(28,10), --面积 
  FDCStockID int, --仓库id
  FDCSPID int, --仓位id
  FBatchNo varchar(255), --批次
  FSTOCKORGID int,--库存组织ID
  FDate datetime,
  FUserID int,--
  FOWNERID int  --货主
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
 