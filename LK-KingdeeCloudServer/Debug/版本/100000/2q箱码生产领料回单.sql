if (exists (select * from sys.objects where name = 'proc_K3ProductPick_Assemble'))
drop proc proc_K3ProductPick_Assemble
go
create proc proc_K3ProductPick_Assemble
(
  @mainStr nvarchar(1000)--主表参数
 
)
as 
set nocount on     --开启一个模式，也就是不再刷新多少行受影响的信息，可以提高性能
set xact_abort on  --事务选项设置为出错全部回滚
begin tran declare
          @FDate varchar(20),            --单据日期   
          @FBillerID varchar(20),        --制单人ID 
          @FOrderID varchar(50),--PDA单据编号
          @FPDAID varchar(50),  --PDA序列号 
          @FInterID int,--单据内码
          @FBillNo varchar(128) --单据编号
        
          set @FBillerID=dbo.getString(@mainStr,'|',1)   -- 制单人ID 
          set @FBillNo=dbo.getString(@mainStr,'|',2)     --回单时返回的单据编号
          set @FOrderID=dbo.getString(@mainStr,'|',3)  --PDA单据编号
          set @FPDAID=dbo.getString(@mainStr,'|',4)      --PDA序号
          select @FInterID=FID from T_SP_PICKMTRL where FBillNo=@FBillNo 
          set @Fdate = convert(varchar(20),GETDATE(),23)                                       
  update t_AssembleRecord  set FInterIDOut=@FInterID, FStatus = 2,FRemark = '刨光车间生产领料' from (select a.* from t_AssembleRecordTemp a left join t_AssembleRecord b on a.FBillNo=b.FBillNo where a.FOrderID=@FOrderID and a.FPDAID=@FPDAID) as t where t_AssembleRecord.FBillNo=t.FBillNo
  delete from t_AssembleRecordTemp where FOrderID=@FOrderID and FPDAID=@FPDAID
   
commit tran 
return;
if(0<>@@error)
	goto error1
error1:
	rollback tran;