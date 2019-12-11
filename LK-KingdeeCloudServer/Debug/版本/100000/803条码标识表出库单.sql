if not exists(select * from sysobjects where xtype='u' and name='t_PDABarCodeSign_Red')
begin
create table t_PDABarCodeSign_Red
(
   FBarCode varchar(255) not null,  --条码
   FInterID int not null, --所属打印单据内码
   FInterID_Before int,--之前id
   FROB int,  --销售出库单红蓝字
   FBillNO varchar(128),--单号
   FStatus int,--单据状态
   FTypeID int, --出入库类型 0 入了 1出库
   FQtyOut decimal(20,8)  default 0,   --出库数量   
   FRemark varchar(255)
)
CREATE UNIQUE  INDEX [idx_t_PDABarCodeSign_Red] ON [dbo].t_PDABarCodeSign_Red 
(
	  FBarCode asc,
	  FInterID asc
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
end
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_PDABarCodeSign_Red' AND T2.NAME='FQtyOut')
alter table t_PDABarCodeSign_Red add FQtyOut decimal(20,8)  default 0 --出库数量