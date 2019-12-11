if not exists(select * from sysobjects where xtype='u' and name='t_AssembleRecordTemp')
begin
create table t_AssembleRecordTemp
(
  FOrderID varchar(128),--PDA单号
  FPDAID varchar(128),--PDA序号
  FBillNo varchar(128),--箱码
  FItemID int not null,   --商品id
  FUnitID int not null,   --单位id
  FLength varchar(128),--长度
  FQty decimal(28,10), --数量
  FVolume decimal(28,10), --面积 
  FDCStockID int, --仓库id
  FDCSPID int, --仓位id
  FBatchNo varchar(255), --批次
  FSTOCKORGID int,--库存组织ID
  FOWNERID int  --货主
)
end
 