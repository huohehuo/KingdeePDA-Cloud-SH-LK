if not exists(select * from sysobjects where xtype='u' and name='t_PDABatchNoIndex2')
begin
create table t_PDABatchNoIndex2
(
  FID int identity(1,1) primary key not null,
  FMATERIALID int,--商品ID
  FStockNumber varchar(128),--仓库代码
  FWorkshopNumber varchar(128),--生产车间代码
  FShortDate varchar(20),--
  FIndex int --序号 
)
CREATE NONCLUSTERED INDEX [idx_t_PDABatchNoIndex2] ON [dbo].t_PDABatchNoIndex2
(
	FShortDate ASC, 
	FWorkshopNumber ASC,
	FStockNumber ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
end
 