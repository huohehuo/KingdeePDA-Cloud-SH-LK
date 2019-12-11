if not exists(select * from sysobjects where xtype='u' and name='t_UserPermitPC')
begin
create table t_UserPermitPC
(
FUserID varchar(20) primary key not null, --用户id
FUserName varchar(100),           --用户名
FUserState varchar(10),           --用户说明
FCondition varchar(8000),         --条件(管理员)
FAssetID varchar(8000),           --类别id
FDepartmentID varchar(8000),      --部门id
FRemark varchar(20),              --是否已授权
FPassWord varchar(100)            --用户密码
)
end
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_UserPermitPC' AND T2.NAME='FUserNameERP')
alter table t_UserPermitPC add FUserNameERP varchar(255) --erp用户名
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_UserPermitPC' AND T2.NAME='FPassWordERP')
alter table t_UserPermitPC add FPassWordERP varchar(255) --erp用户密码
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_UserPermitPC' AND T2.NAME='FUserIDERP')
alter table t_UserPermitPC add FUserIDERP varchar(20) --erp用户密码
IF Not EXISTS ( SELECT 1 FROM SYSOBJECTS T1  INNER JOIN SYSCOLUMNS T2 ON T1.ID=T2.ID  WHERE T1.NAME='t_UserPermitPC' AND T2.NAME='FTypeID')
alter table t_UserPermitPC add FTypeID int --1为新增用户