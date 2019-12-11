if not exists(select * from sysobjects where xtype='u' and name='t_PDABarCodeSign_COUNTIN')
begin
create table t_PDABarCodeSign_COUNTIN
(    
  FBarCode varchar(255) not null,  --条码
  FInterID int  not null, --出库单号
  FBillNo varchar(128),--单号
  FQty decimal(28,10),  --盘点数量
  FQty_All  decimal(28,10), --盘点前总数
  FQty_Out  decimal(28,10), --盘点前出库数
  FDate datetime,--盘点日期
  FBillerID varchar(20),--制单人
  FStatus int, --0 关闭
  FOrderID varchar(50),--PDA单据编号
  FPDAID varchar(50),  --PDA序列号 
  FTypeID int,  --单据类型 1盘盈 2盘亏
  FRemark varchar(255)--备注
) 
CREATE UNIQUE INDEX [idx_t_PDABarCodeSign_COUNTIN] ON [dbo].t_PDABarCodeSign_COUNTIN
(
	FBarCode ASC,
	FInterID ASC 
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
end
 