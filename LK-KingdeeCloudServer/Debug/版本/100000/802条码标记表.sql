if not exists(select * from sysobjects where xtype='u' and name='t_PDABarCodeSign')
begin
create table t_PDABarCodeSign
(   
  FID int identity(1,1) primary key not null,
  FIndex int not null,  --每天序号
  FInterID int not null, --所属打印单据内码
  FEntryID int not null, 
  FBillNo varchar(100),  --所属订单编号
  FInterIDIn int,   --入库单号id
  FInterIDOut int,--出库单号id 
  FInterIDAssemble int,   --组装单号
  FInterIDDisassemble int,--
  FItemID int not null,    --产品id  
  FUnitID int,    --产品id
  FInterIDAllot int,--调拨单id
  FStockIDAllot int,--调拨单之前仓库id
  FStockPlaceIDAllot int,--调拨单之前仓位id
  
  FBarCode varchar(255) not null,  --条码
  FDatePrint datetime,    --打印日期
  FDateInStore datetime,  --入库时间
  FDateOutStore datetime, --出库时间
  FIsInStore varchar(20),--入库状态
  FIsOutStore varchar(20),--出库状态  
  FUserInStore varchar(20), --PDA入库人
  FUserOutStore varchar(20),  --PDA出库人
  FStockID int,   --仓库id
  FStockPlaceID int, --仓位id
  FBatchNo varchar(255),     --批次
  FKFPeriod int,  --保质期,
  FKFDate varchar(20), --生产日期
  FDatePrintShort varchar(20),
  FQty decimal(20,8),   --数量  
  FQtyOut decimal(20,8)  default 0,   --出库数量   
  FPrintType varchar(20),--打印类型
  FRemark1 varchar(255),
  FRemark2 varchar(255),
  FRemark3 varchar(255),--实际规格
  FRemark4 varchar(255),--辅助标识
  FRemark5 varchar(255),
  FRemark6 varchar(255),
  FRemark7 varchar(255),--生产编号
  FRemark8 varchar(255),  
  FPathLab varchar(255),--条码模板路径 
  FRemark varchar(50)     --备注   
) 
CREATE UNIQUE INDEX PDABarCodeSign_Index_FBarCode
ON t_PDABarCodeSign (FBarCode)
CREATE NONCLUSTERED INDEX [idx_t_PDABarCodeSign] ON [dbo].t_PDABarCodeSign 
(
	[FItemID] ASC,
	FUnitID ASC, 
	FInterIDIn ASC,
	FInterIDOut ASC,
	FStockID ASC, 
	FStockPlaceID ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
end
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_PDABarCodeSign' AND T2.NAME='FQtyOut')
alter table t_PDABarCodeSign add FQtyOut decimal(20,8)  default 0 --出库数量

IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_PDABarCodeSign' AND T2.NAME='F_FFF_TEXT1')
alter table t_PDABarCodeSign add F_FFF_TEXT1 varchar(128)--等级
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_PDABarCodeSign' AND T2.NAME='F_FFF_INTEGER')
alter table t_PDABarCodeSign add F_FFF_INTEGER decimal(20,8)  default 0 --原木长
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_PDABarCodeSign' AND T2.NAME='F_FFF_INTEGER1')
alter table t_PDABarCodeSign add F_FFF_INTEGER1 decimal(20,8)  default 0 --原木直径
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_PDABarCodeSign' AND T2.NAME='F_FFF_INTEGER2')
alter table t_PDABarCodeSign add F_FFF_INTEGER2 decimal(20,8)  default 0 --板长
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_PDABarCodeSign' AND T2.NAME='F_FFF_INTEGER3')
alter table t_PDABarCodeSign add F_FFF_INTEGER3 decimal(20,8)  default 0 --板宽
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_PDABarCodeSign' AND T2.NAME='F_FFF_INTEGER4')
alter table t_PDABarCodeSign add F_FFF_INTEGER4 decimal(20,8)  default 0 --板厚
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_PDABarCodeSign' AND T2.NAME='F_FFF_Volume')
alter table t_PDABarCodeSign add F_FFF_Volume decimal(20,8)  default 0 --体积

IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_PDABarCodeSign' AND T2.NAME='FWidth')
alter table t_PDABarCodeSign add FWidth varchar(502) --体积

IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_PDABarCodeSign' AND T2.NAME='F_TypeID')
alter table t_PDABarCodeSign add F_TypeID int --区分单据类型 0 是水版 1是其他
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_PDABarCodeSign' AND T2.NAME='F_Plies')
alter table t_PDABarCodeSign add F_Plies decimal(20,8)  default 0 --层数