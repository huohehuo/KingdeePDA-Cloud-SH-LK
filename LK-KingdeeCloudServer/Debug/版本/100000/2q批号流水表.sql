if not exists(select * from sysobjects where xtype='u' and name='t_PDABatchNoIndex2')
begin
create table t_PDABatchNoIndex2
(
  FID int identity(1,1) primary key not null,
  FMATERIALID int,--��ƷID
  FStockNumber varchar(128),--�ֿ����
  FWorkshopNumber varchar(128),--�����������
  FShortDate varchar(20),--
  FIndex int --��� 
)
CREATE NONCLUSTERED INDEX [idx_t_PDABatchNoIndex2] ON [dbo].t_PDABatchNoIndex2
(
	FShortDate ASC, 
	FWorkshopNumber ASC,
	FStockNumber ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
end
 