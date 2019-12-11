if not exists(select * from sysobjects where xtype='u' and name='t_PDABatchNoIndex')
begin
create table t_PDABatchNoIndex
(
  FID int identity(1,1) primary key not null,
  FMATERIALID int,--商品ID
  FStockNumber varchar(128),--仓库代码
  FCargoNumber varchar(128),--货主代码
  FShortDate varchar(20),--
  FIndex int --序号 
)
CREATE NONCLUSTERED INDEX [idx_t_PDABatchNoIndex] ON [dbo].t_PDABatchNoIndex
(
	FShortDate ASC, 
	FMATERIALID ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
 
--CREATE UNIQUE INDEX t_serialIndex_Index_FItemID
--ON t_serialIndex (FItemID)
--CREATE  UNIQUE NONCLUSTERED INDEX [idx_t_PDABatchNoIndex] ON [dbo].t_PDABatchNoIndex
--(
--	FShortDate ASC
 
	 
--)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
end
 