if not exists(select * from sysobjects where xtype='u' and name='t_PDABarCodeSign_In')
begin
create table t_PDABarCodeSign_In
(
   FBarCode varchar(255) not null,  --����
   FInterID int not null, --������ӡ��������
   FInterID_Before int,--֮ǰid
   FROB int,  --���۳��ⵥ������
   FBillNO varchar(128),--����
   FStatus int,--����״̬
   FTypeID int, --��������� 0 ���� 1����
   FRemark varchar(255)
)
CREATE UNIQUE  INDEX [idx_t_PDABarCodeSign_In] ON [dbo].t_PDABarCodeSign_In 
(
	  FBarCode asc,
	  FInterID asc
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]

end