if not exists(select * from sysobjects where xtype='u' and name='t_UserPermitPC')
begin
create table t_UserPermitPC
(
FUserID varchar(20) primary key not null, --�û�id
FUserName varchar(100),           --�û���
FUserState varchar(10),           --�û�˵��
FCondition varchar(8000),         --����(����Ա)
FAssetID varchar(8000),           --���id
FDepartmentID varchar(8000),      --����id
FRemark varchar(20),              --�Ƿ�����Ȩ
FPassWord varchar(100)            --�û�����
)
end
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_UserPermitPC' AND T2.NAME='FUserNameERP')
alter table t_UserPermitPC add FUserNameERP varchar(255) --erp�û���
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_UserPermitPC' AND T2.NAME='FPassWordERP')
alter table t_UserPermitPC add FPassWordERP varchar(255) --erp�û�����
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_UserPermitPC' AND T2.NAME='FUserIDERP')
alter table t_UserPermitPC add FUserIDERP varchar(20) --erp�û�����
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_UserPermitPC' AND T2.NAME='FTypeID')
alter table t_UserPermitPC add FTypeID int --1Ϊ�����û�