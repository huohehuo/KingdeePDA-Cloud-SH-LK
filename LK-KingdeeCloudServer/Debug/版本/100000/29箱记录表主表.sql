if not exists(select * from sysobjects where xtype='u' and name='t_AssembleRecord')
begin
create table t_AssembleRecord
( 
  FID int identity(1,1) primary key not null, 
  FOrderID varchar(128),--PDA����
  FPDAID varchar(128),--PDA���
  FDate DateTime, --�ص�����
  FShortDate varchar(20),--����
  FBillerID int,  --�Ƶ��� 
  FBillNo varchar(128),--װ�䵥��
  FRemark varchar(8000),--��ע
  FChang varchar(255),--����
  FStatus int,--��״̬
  FHuang varchar(255),--��
  FHou varchar(255),--��
  FIndex int,--���
)
CREATE NONCLUSTERED INDEX [t_AssembleRecord_FBillNo] ON [dbo].[t_AssembleRecord] 
(
	[FBillNo] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
end
 
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_AssembleRecord' AND T2.NAME='FInterIDOut')
alter table t_AssembleRecord add FInterIDOut int  default 0 --���ⵥ��
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_AssembleRecord' AND T2.NAME='FInterIDAllot')
alter table t_AssembleRecord add FInterIDAllot int  default 0 --��������

