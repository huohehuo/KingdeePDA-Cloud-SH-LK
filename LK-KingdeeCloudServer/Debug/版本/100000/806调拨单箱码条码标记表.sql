if not exists(select * from sysobjects where xtype='u' and name='t_AssembleRecord1_Allot')
begin
create table t_AssembleRecord1_Allot
(    
  FBarCode varchar(255) not null,  --箱码
  FInterID int  not null, --调拨ID
  FBillNo varchar(128),--单号
  FQty decimal(28,10),  --添加数量
  FStockID_Before int,-- 之前仓库
  FStockPlaceID_Before int,-- 之前仓位
  FStockID_Now int,-- 现在仓库
  FStockPlaceID_Now int,-- 现在仓位
  FEntryID int,--明细唯一码
  FSTOCKORGID_Before int,-- 之前库存组织ID
  FOWNERID_Before int,-- 之前仓位
  FSTOCKORGID_Now int,-- 现在仓库
  FOWNERID_Now int,-- 现在仓位
  
  FStatus int, --0 关闭
  FTypeID int,  --单据类型
  FRemark varchar(255)--备注
) 
CREATE UNIQUE INDEX [idx_t_AssembleRecord1_Allot] ON [dbo].t_AssembleRecord1_Allot 
(
	FInterID ASC,
	FEntryID ASC 
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
end
 