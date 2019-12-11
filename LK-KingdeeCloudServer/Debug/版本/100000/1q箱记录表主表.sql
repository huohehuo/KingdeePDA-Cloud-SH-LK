if not exists(select * from sysobjects where xtype='u' and name='t_AssembleRecord1')
begin
create table t_AssembleRecord1
( 
  FID int identity(1,1) primary key not null, 
  FOrderID varchar(128),--PDA����
  FPDAID varchar(128),--PDA���
  FDate DateTime, --�ص�����
  FShortDate varchar(20),--����
  FBillerID int,  --�Ƶ��� 
  FBillNo varchar(128),--װ�䵥��
  FRemark varchar(8000),--��ע 
  FStatus int,--��״̬
  FType int,--�������� 0ˮ��ж���� 1ˮ���Ҥ  
  FCarNum varchar(128),--���ƺ�
  FHuZhu varchar(255),--��������
  FName varchar(1024),--����
  FSourceID int,--Դ��ID
  FInterIDIn int  default 0, --��ⵥ��
  FInterIDOut int  default 0, --���ⵥ��
  FPackets varchar(128),--����
  FPackets1 varchar(128),--�ְ���
  FStockName varchar(255),--�ֿ�
  FWidth varchar(128),--��
  FIndex int,--���
)
CREATE NONCLUSTERED INDEX [t_AssembleRecord1_FBillNo] ON [dbo].[t_AssembleRecord1] 
(
	[FBillNo] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
end
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_AssembleRecord1' AND T2.NAME='FInterIDIn')
alter table t_AssembleRecord1 add FInterIDIn int  default 0 -- 
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_AssembleRecord1' AND T2.NAME='FInterIDOut')
alter table t_AssembleRecord1 add FInterIDOut int  default 0 --���ⵥ��
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_AssembleRecord1' AND T2.NAME='FInterIDAllot')
alter table t_AssembleRecord1 add FInterIDAllot int  default 0 --��������
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_AssembleRecord1' AND T2.NAME='FPackets1')
alter table t_AssembleRecord1 add FPackets1 varchar(128) --�ְ���

