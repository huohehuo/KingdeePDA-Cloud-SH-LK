if not exists(select * from sysobjects where xtype='u' and name='t_PDABarCodeSign_COUNTIN')
begin
create table t_PDABarCodeSign_COUNTIN
(    
  FBarCode varchar(255) not null,  --����
  FInterID int  not null, --���ⵥ��
  FBillNo varchar(128),--����
  FQty decimal(28,10),  --�̵�����
  FQty_All  decimal(28,10), --�̵�ǰ����
  FQty_Out  decimal(28,10), --�̵�ǰ������
  FDate datetime,--�̵�����
  FBillerID varchar(20),--�Ƶ���
  FStatus int, --0 �ر�
  FOrderID varchar(50),--PDA���ݱ��
  FPDAID varchar(50),  --PDA���к� 
  FTypeID int,  --�������� 1��ӯ 2�̿�
  FRemark varchar(255)--��ע
) 
CREATE UNIQUE INDEX [idx_t_PDABarCodeSign_COUNTIN] ON [dbo].t_PDABarCodeSign_COUNTIN
(
	FBarCode ASC,
	FInterID ASC 
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
end
 