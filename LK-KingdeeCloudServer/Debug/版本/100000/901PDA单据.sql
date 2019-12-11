if not exists(select * from sysobjects where xtype='u' and name='a_DetailsTable')
begin
create table a_DetailsTable
(   
  FPDAID varchar(50),  --PDA序列号
  FOrderID varchar(30),--单据id
  FBarCode varchar(200) not null,  --条码 
  FItemID int, --商品id
  FStockID int,   --仓库id
  FStockPlaceID int, --仓位id
  FUnitID int, --单位ID
  FBatchNo varchar(255),     --批次
  FKFPeriod int,  --保质期,
  FKFDate varchar(20), --生产日期
  FQty decimal(28,10)  --添加数量  
) 
--CREATE NONCLUSTERED INDEX [idx_t_a_DetailsTable] ON [dbo].a_DetailsTable 
--(
--	FBarCode asc 
--)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
--CREATE UNIQUE INDEX a_DetailsTable_Index_FBarCode
--ON a_DetailsTable (FBarCode) 
end