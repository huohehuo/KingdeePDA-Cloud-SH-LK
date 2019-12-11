if not exists(select * from sysobjects where xtype='u' and name='t_AssembleRecord1_Allot')
begin
create table t_AssembleRecord1_Allot
(    
  FBarCode varchar(255) not null,  --����
  FInterID int  not null, --����ID
  FBillNo varchar(128),--����
  FQty decimal(28,10),  --�������
  FStockID_Before int,-- ֮ǰ�ֿ�
  FStockPlaceID_Before int,-- ֮ǰ��λ
  FStockID_Now int,-- ���ڲֿ�
  FStockPlaceID_Now int,-- ���ڲ�λ
  FEntryID int,--��ϸΨһ��
  FSTOCKORGID_Before int,-- ֮ǰ�����֯ID
  FOWNERID_Before int,-- ֮ǰ��λ
  FSTOCKORGID_Now int,-- ���ڲֿ�
  FOWNERID_Now int,-- ���ڲ�λ
  
  FStatus int, --0 �ر�
  FTypeID int,  --��������
  FRemark varchar(255)--��ע
) 
CREATE UNIQUE INDEX [idx_t_AssembleRecord1_Allot] ON [dbo].t_AssembleRecord1_Allot 
(
	FInterID ASC,
	FEntryID ASC 
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
end
 