if not exists(select * from sysobjects where xtype='u' and name='t_PDABarCodeSign_Red')
begin
create table t_PDABarCodeSign_Red
(
   FBarCode varchar(255) not null,  --����
   FInterID int not null, --������ӡ��������
   FInterID_Before int,--֮ǰid
   FROB int,  --���۳��ⵥ������
   FBillNO varchar(128),--����
   FStatus int,--����״̬
   FTypeID int, --��������� 0 ���� 1����
   FQtyOut decimal(20,8)  default 0,   --��������   
   FRemark varchar(255)
)
CREATE UNIQUE  INDEX [idx_t_PDABarCodeSign_Red] ON [dbo].t_PDABarCodeSign_Red 
(
	  FBarCode asc,
	  FInterID asc
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
end
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_PDABarCodeSign_Red' AND T2.NAME='FQtyOut')
alter table t_PDABarCodeSign_Red add FQtyOut decimal(20,8)  default 0 --��������