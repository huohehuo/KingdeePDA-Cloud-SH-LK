if not exists(select * from sysobjects where xtype='u' and name='t_PDABatchNoIndex1')
begin
create table t_PDABatchNoIndex1
(
  FID int identity(1,1) primary key not null,
  FMATERIALID int,--��ƷID
  FStockNumber varchar(128),--�ֿ����
  FHuoZhuNumber varchar(128),--�������
  FShortDate varchar(20),--
  FIndex int --��� 
)
CREATE NONCLUSTERED INDEX [idx_t_PDABatchNoIndex1] ON [dbo].t_PDABatchNoIndex1
(
	FShortDate ASC, 
	--FWorkshopNumber ASC,
	FHuoZhuNumber asc,
	FStockNumber ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
end
 