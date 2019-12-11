if not exists(select * from sysobjects where xtype='u' and name='t_AssembleRecord1')
begin
create table t_AssembleRecord1
( 
  FID int identity(1,1) primary key not null, 
  FOrderID varchar(128),--PDA单号
  FPDAID varchar(128),--PDA序号
  FDate DateTime, --回单日期
  FShortDate varchar(20),--短码
  FBillerID int,  --制单人 
  FBillNo varchar(128),--装箱单号
  FRemark varchar(8000),--备注 
  FStatus int,--箱状态
  FType int,--箱码类型 0水板卸车后 1水板进窑  
  FCarNum varchar(128),--车牌号
  FHuZhu varchar(255),--货主编码
  FName varchar(1024),--名称
  FSourceID int,--源单ID
  FInterIDIn int  default 0, --入库单号
  FInterIDOut int  default 0, --出库单号
  FPackets varchar(128),--包数
  FPackets1 varchar(128),--分包号
  FStockName varchar(255),--仓库
  FWidth varchar(128),--宽
  FIndex int,--序号
)
CREATE NONCLUSTERED INDEX [t_AssembleRecord1_FBillNo] ON [dbo].[t_AssembleRecord1] 
(
	[FBillNo] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
end
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_AssembleRecord1' AND T2.NAME='FInterIDIn')
alter table t_AssembleRecord1 add FInterIDIn int  default 0 -- 
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_AssembleRecord1' AND T2.NAME='FInterIDOut')
alter table t_AssembleRecord1 add FInterIDOut int  default 0 --出库单号
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_AssembleRecord1' AND T2.NAME='FInterIDAllot')
alter table t_AssembleRecord1 add FInterIDAllot int  default 0 --调拨单号
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_AssembleRecord1' AND T2.NAME='FPackets1')
alter table t_AssembleRecord1 add FPackets1 varchar(128) --分包号

