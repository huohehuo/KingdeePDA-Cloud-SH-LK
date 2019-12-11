if (exists (select * from sys.objects where name = 'proc_K3PurchasePushOutsourcing_Assemble'))
drop proc proc_K3PurchasePushOutsourcing_Assemble
go
create proc proc_K3PurchasePushOutsourcing_Assemble
(
  @mainStr nvarchar(1000)--主表参数
 
)
as 
set nocount on     --开启一个模式，也就是不再刷新多少行受影响的信息，可以提高性能
set xact_abort on  --事务选项设置为出错全部回滚
begin tran declare
          @FDate varchar(20),            --单据日期   
          @FBillerID varchar(20),        --制单人ID 
          
          @FInterID int,--单据内码
          @FAssemble varchar(128),--装箱单号
          @FSourceID int,
          @FBillNo varchar(128) --单据编号
        
          set @FBillerID=dbo.getString(@mainStr,'|',1)   -- 制单人ID 
          set @FBillNo=dbo.getString(@mainStr,'|',2)     --回单时返回的单据编号
          set @FSourceID =dbo.getString(@mainStr,'|',3)   --单号内码
        
          select @FInterID=FID from t_STK_InStock where FBillNo=@FBillNo 
          set @Fdate = convert(varchar(20),GETDATE(),23)                                       
  update t_AssembleRecord1  set FInterIDIn=@FInterID, FStatus = 1,FRemark = '采购订单下推采购入库单'  where FSourceID = @FSourceID and FStatus=0 and isnull(FInterIDIn,0) = 0
 
   
commit tran 
return;
if(0<>@@error)
	goto error1
error1:
	rollback tran;