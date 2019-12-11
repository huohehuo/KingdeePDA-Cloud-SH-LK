if not exists(select * from sysobjects where xtype='u' and name='a_DetailsTable')
begin
create table a_DetailsTable
(   
  FPDAID varchar(50),  --PDA���к�
  FOrderID varchar(30),--����id
  FBarCode varchar(200) not null,  --���� 
  FItemID int, --��Ʒid
  FStockID int,   --�ֿ�id
  FStockPlaceID int, --��λid
  FUnitID int, --��λID
  FBatchNo varchar(255),     --����
  FKFPeriod int,  --������,
  FKFDate varchar(20), --��������
  FQty decimal(28,10)  --�������  
) 
--CREATE NONCLUSTERED INDEX [idx_t_a_DetailsTable] ON [dbo].a_DetailsTable 
--(
--	FBarCode asc 
--)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
--CREATE UNIQUE INDEX a_DetailsTable_Index_FBarCode
--ON a_DetailsTable (FBarCode) 
end