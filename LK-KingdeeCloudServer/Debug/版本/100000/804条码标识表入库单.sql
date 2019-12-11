if not exists(select * from sysobjects where xtype='u' and name='t_PDABarCodeSign_In')
begin
create table t_PDABarCodeSign_In
(
   FBarCode varchar(255) not null,  --条码
   FInterID int not null, --所属打印单据内码
   FInterID_Before int,--之前id
   FROB int,  --销售出库单红蓝字
   FBillNO varchar(128),--单号
   FStatus int,--单据状态
   FTypeID int, --出入库类型 0 入了 1出库
   FRemark varchar(255)
)
CREATE UNIQUE  INDEX [idx_t_PDABarCodeSign_In] ON [dbo].t_PDABarCodeSign_In 
(
	  FBarCode asc,
	  FInterID asc
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]

end