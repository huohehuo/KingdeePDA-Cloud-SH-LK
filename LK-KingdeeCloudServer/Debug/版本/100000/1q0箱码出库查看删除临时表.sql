if (exists (select * from sys.objects where name = 'proc_AssembleOut1_delete'))
    drop proc proc_AssembleOut1_delete
go
create proc proc_AssembleOut1_delete
(
 @detailStr1 varchar(max),--��ϸ����
 @detailStr2 varchar(max),
 @detailStr3 varchar(max),
 @detailStr4 varchar(max),
 @detailStr5 varchar(max) 
)
as
set nocount on
--------------��ʼ�洢����
begin
--------------����ѡ������Ϊ����ȫ���ع�
set xact_abort on
--------------��������
begin tran 
declare  
        @FPDAID varchar(50),  --PDA���к�
        @FOrderID varchar(30),--����id
        
        @FQty decimal(28,10),  --�������
        @FBillNo varchar(255),     --����
        @detailqty int,             --��ϸ�����ĸ���
        @detailcount int,           --��ϸÿ�����ݵĳ��� 
        @detailIndex int,           --��ϸÿ���±�
        @countindex int             --�ָ���|������
       set @detailqty=0        
       set @detailcount=3   
    while(@detailqty<5)--�ж�����ϸ���ĸ�����
    begin
    if(@detailqty=1)
	begin
	set @detailStr1=@detailStr2
	end  
	if(@detailqty=2)
	begin
	set @detailStr1=@detailStr3
	end 
	if(@detailqty=3)
	begin
	set @detailStr1=@detailStr4
	end 
	if(@detailqty=4)
	begin
	set @detailStr1=@detailStr5
	end 
	if(@detailStr1='' or @detailStr1=null)
	begin
	break;
	end
	set @detailIndex=0 
	select @countindex=len(@detailStr1)-len(replace(@detailStr1, '|', ''))
	while(@countindex>@detailIndex*@detailcount)
	begin
	set @FBillNo=dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+1)    --���� 
    set @FPDAID =dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+2)  --PDA���к�
    set @FOrderID =dbo.getString(@detailStr1,'|',@detailcount*@detailIndex+3)  --����id
    set @detailIndex=@detailIndex+1   
    delete from t_AssembleRecord1Temp where FBillNo=@FBillNo and FPDAID=@FPDAID and FOrderID = @FOrderID
           
    end
    set @detailqty=@detailqty+1
    end 
commit tran 
return;
--------------���ڴ���
if(0<>@@error)
	goto error1
--------------�ع�����	
error1:
	rollback tran;
--------------�����洢����
end
