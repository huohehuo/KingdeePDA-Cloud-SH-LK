package com.fangzuo.assist.cloud.Beans;

import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.Dao.T_main;

import java.util.ArrayList;
import java.util.List;

public class SaleBackBean {


    /**
     * Creator :
     * NeedUpDateFields : []
     * NeedReturnFields : []
     * IsDeleteEntry : True
     * SubSystemId :
     * IsVerifyBaseDataField : false
     * IsEntryBatchFill : True
     * ValidateFlag : True
     * NumberSearch : True
     * InterationFlags :
     * Model : {"FID":"0","FBillTypeID":{"FNUMBER":""},"FBillNo":"","FDate":"1900-01-01","FSaleOrgId":{"FNumber":""},"FRetcustId":{"FNumber":""},"FSaledeptid":{"FNumber":""},"FReturnReason":{"FNumber":""},"FHeadLocId":{"FNumber":""},"FSaleGroupId":{"FNumber":""},"FTransferBizType":{"FNumber":""},"FCorrespondOrgId":{"FNumber":""},"FSalesManId":{"FNumber":""},"FStockOrgId":{"FNumber":""},"FStockDeptId":{"FNumber":""},"FStockerGroupId":{"FNumber":""},"FStockerId":{"FNumber":""},"FHeadNote":"","FReceiveCustId":{"FNumber":""},"FReceiveAddress":"","FSettleCustId":{"FNumber":""},"FReceiveCusContact":{"FNAME":""},"FPayCustId":{"FNumber":""},"FOwnerTypeIdHead":"","FOwnerIdHead":{"FNumber":""},"FScanBox":"","FCDateOffsetUnit":"","FCDateOffsetValue":"0","FIsTotalServiceOrCost":"false","SubHeadEntity":{"FEntryId":"0","FSettleCurrId":{"FNumber":""},"FThirdBillNo":"","FThirdBillId":"","FThirdSrcType":"","FSettleOrgId":{"FNumber":""},"FSettleTypeId":{"FNumber":""},"FChageCondition":{"FNumber":""},"FPriceListId":{"FNumber":""},"FDiscountListId":{"FNumber":""},"FLocalCurrId":{"FNumber":""},"FExchangeTypeId":{"FNumber":""},"FExchangeRate":"0"},"FEntity":[{"FENTRYID":"0","FRowType":"","FMapId":{"FNumber":""},"FMaterialId":{"FNumber":""},"FAuxpropId":{},"FUnitID":{"FNumber":""},"FInventoryQty":"0","FRealQty":"0","FParentMatId":{"FNUMBER":""},"FPrice":"0","FTaxPrice":"0","FIsFree":"false","FTaxCombination":{"FNumber":""},"FEntryTaxRate":"0","FBOMId":{"FNumber":""},"FReturnType":{"FNumber":""},"FOwnerTypeId":"","FOwnerId":{"FNumber":""},"FProduceDate":"1900-01-01","FExpiryDate":"1900-01-01","FStockId":{"FNumber":""},"FStocklocId":{"FSTOCKLOCID__FF100003":{"FNumber":""},"FSTOCKLOCID__FF100012":{"FNumber":""}},"FStockstatusId":{"FNumber":""},"FDeliveryDate":"1900-01-01","FMtoNo":"","FNote":"","FDiscountRate":"0","FPriceDiscount":"0","FAuxUnitQty":"0","FExtAuxUnitId":{"FNumber":""},"FExtAuxUnitQty":"0","FSalCostPrice":"0","FISCONSUMESUM":"","FLot":{"FNumber":""},"FSalUnitID":{"FNumber":""},"FSalUnitQty":"0","FSalBaseQty":"0","FPriceBaseQty":"0","FProjectNo":"","FQualifyType":"","FEOwnerSupplierId":{"FNUMBER":""},"FIsOverLegalOrg":"false","FESettleCustomerId":{"FNUMBER":""},"FSOEntryId":"0","FThirdEntryId":"","FPriceListEntry":{"FNUMBER":""},"FARNOTJOINQTY":"0","FIsReturnCheck":"false","FTaxDetailSubEntity":[{"FDetailID":"0","FTaxRate":"0"}],"FSerialSubEntity":[{"FDetailID":"0","FSerialNo":"","FSerialNote":""}]}]}
     */

    private String Creator;
    private String IsDeleteEntry;
    private String SubSystemId;
    private String IsVerifyBaseDataField;
    private String IsEntryBatchFill;
    private String ValidateFlag;
    private String NumberSearch;
    private String InterationFlags;
    private ModelBean Model;
    private List<?> NeedUpDateFields;
    private List<?> NeedReturnFields;

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }

    public String getIsDeleteEntry() {
        return IsDeleteEntry;
    }

    public void setIsDeleteEntry(String IsDeleteEntry) {
        this.IsDeleteEntry = IsDeleteEntry;
    }

    public String getSubSystemId() {
        return SubSystemId;
    }

    public void setSubSystemId(String SubSystemId) {
        this.SubSystemId = SubSystemId;
    }

    public String getIsVerifyBaseDataField() {
        return IsVerifyBaseDataField;
    }

    public void setIsVerifyBaseDataField(String IsVerifyBaseDataField) {
        this.IsVerifyBaseDataField = IsVerifyBaseDataField;
    }

    public String getIsEntryBatchFill() {
        return IsEntryBatchFill;
    }

    public void setIsEntryBatchFill(String IsEntryBatchFill) {
        this.IsEntryBatchFill = IsEntryBatchFill;
    }

    public String getValidateFlag() {
        return ValidateFlag;
    }

    public void setValidateFlag(String ValidateFlag) {
        this.ValidateFlag = ValidateFlag;
    }

    public String getNumberSearch() {
        return NumberSearch;
    }

    public void setNumberSearch(String NumberSearch) {
        this.NumberSearch = NumberSearch;
    }

    public String getInterationFlags() {
        return InterationFlags;
    }

    public void setInterationFlags(String InterationFlags) {
        this.InterationFlags = InterationFlags;
    }

    public ModelBean getModel() {
        return Model;
    }

    public void setModel(ModelBean Model) {
        this.Model = Model;
    }

    public List<?> getNeedUpDateFields() {
        return NeedUpDateFields;
    }

    public void setNeedUpDateFields(List<?> NeedUpDateFields) {
        this.NeedUpDateFields = NeedUpDateFields;
    }

    public List<?> getNeedReturnFields() {
        return NeedReturnFields;
    }

    public void setNeedReturnFields(List<?> NeedReturnFields) {
        this.NeedReturnFields = NeedReturnFields;
    }

    public static class ModelBean {
        /**
         * FID : 0
         * FBillTypeID : {"FNUMBER":""}
         * FBillNo :
         * FDate : 1900-01-01
         * FSaleOrgId : {"FNumber":""}
         * FRetcustId : {"FNumber":""}
         * FSaledeptid : {"FNumber":""}
         * FReturnReason : {"FNumber":""}
         * FHeadLocId : {"FNumber":""}
         * FSaleGroupId : {"FNumber":""}
         * FTransferBizType : {"FNumber":""}
         * FCorrespondOrgId : {"FNumber":""}
         * FSalesManId : {"FNumber":""}
         * FStockOrgId : {"FNumber":""}
         * FStockDeptId : {"FNumber":""}
         * FStockerGroupId : {"FNumber":""}
         * FStockerId : {"FNumber":""}
         * FHeadNote :
         * FReceiveCustId : {"FNumber":""}
         * FReceiveAddress :
         * FSettleCustId : {"FNumber":""}
         * FReceiveCusContact : {"FNAME":""}
         * FPayCustId : {"FNumber":""}
         * FOwnerTypeIdHead :
         * FOwnerIdHead : {"FNumber":""}
         * FScanBox :
         * FCDateOffsetUnit :
         * FCDateOffsetValue : 0
         * FIsTotalServiceOrCost : false
         * SubHeadEntity : {"FEntryId":"0","FSettleCurrId":{"FNumber":""},"FThirdBillNo":"","FThirdBillId":"","FThirdSrcType":"","FSettleOrgId":{"FNumber":""},"FSettleTypeId":{"FNumber":""},"FChageCondition":{"FNumber":""},"FPriceListId":{"FNumber":""},"FDiscountListId":{"FNumber":""},"FLocalCurrId":{"FNumber":""},"FExchangeTypeId":{"FNumber":""},"FExchangeRate":"0"}
         * FEntity : [{"FENTRYID":"0","FRowType":"","FMapId":{"FNumber":""},"FMaterialId":{"FNumber":""},"FAuxpropId":{},"FUnitID":{"FNumber":""},"FInventoryQty":"0","FRealQty":"0","FParentMatId":{"FNUMBER":""},"FPrice":"0","FTaxPrice":"0","FIsFree":"false","FTaxCombination":{"FNumber":""},"FEntryTaxRate":"0","FBOMId":{"FNumber":""},"FReturnType":{"FNumber":""},"FOwnerTypeId":"","FOwnerId":{"FNumber":""},"FProduceDate":"1900-01-01","FExpiryDate":"1900-01-01","FStockId":{"FNumber":""},"FStocklocId":{"FSTOCKLOCID__FF100003":{"FNumber":""},"FSTOCKLOCID__FF100012":{"FNumber":""}},"FStockstatusId":{"FNumber":""},"FDeliveryDate":"1900-01-01","FMtoNo":"","FNote":"","FDiscountRate":"0","FPriceDiscount":"0","FAuxUnitQty":"0","FExtAuxUnitId":{"FNumber":""},"FExtAuxUnitQty":"0","FSalCostPrice":"0","FISCONSUMESUM":"","FLot":{"FNumber":""},"FSalUnitID":{"FNumber":""},"FSalUnitQty":"0","FSalBaseQty":"0","FPriceBaseQty":"0","FProjectNo":"","FQualifyType":"","FEOwnerSupplierId":{"FNUMBER":""},"FIsOverLegalOrg":"false","FESettleCustomerId":{"FNUMBER":""},"FSOEntryId":"0","FThirdEntryId":"","FPriceListEntry":{"FNUMBER":""},"FARNOTJOINQTY":"0","FIsReturnCheck":"false","FTaxDetailSubEntity":[{"FDetailID":"0","FTaxRate":"0"}],"FSerialSubEntity":[{"FDetailID":"0","FSerialNo":"","FSerialNote":""}]}]
         */

        private String FID;
        private FBillTypeIDBean FBillTypeID;
        private String FBillNo;
        private String FDate;
        private FSaleOrgIdBean FSaleOrgId;
        private FRetcustIdBean FRetcustId;
        private FSaledeptidBean FSaledeptid;
        private FReturnReasonBean FReturnReason;
        private FHeadLocIdBean FHeadLocId;
        private FSaleGroupIdBean FSaleGroupId;
        private FTransferBizTypeBean FTransferBizType;
        private FCorrespondOrgIdBean FCorrespondOrgId;
        private FSalesManIdBean FSalesManId;
        private FStockOrgIdBean FStockOrgId;
        private FStockDeptIdBean FStockDeptId;
        private FStockerGroupIdBean FStockerGroupId;
        private FStockerIdBean FStockerId;
        private String FHeadNote;
        private FReceiveCustIdBean FReceiveCustId;
        private String FReceiveAddress;
        private FSettleCustIdBean FSettleCustId;
        private FReceiveCusContactBean FReceiveCusContact;
        private FPayCustIdBean FPayCustId;
        private String FOwnerTypeIdHead;
        private FOwnerIdHeadBean FOwnerIdHead;
        private String FScanBox;
        private String FCDateOffsetUnit;
        private String FCDateOffsetValue;
        private String FIsTotalServiceOrCost;
        private SubHeadEntityBean SubHeadEntity;
        private List<FEntityBean> FEntity;

        public void setModelBean(T_main main, List<T_Detail> beans){
            //表头设置-------------------------------------------------------------
            this.FBillNo ="";
            this.FBillTypeID=new SaleBackBean.ModelBean.FBillTypeIDBean();this.FBillTypeID.FNUMBER=main.FBillTypeID;//单据类型
            this.FStockOrgId=new SaleBackBean.ModelBean.FStockOrgIdBean();this.FStockOrgId.FNumber=main.FStockOrgId;//库存组织
            this.FSaleOrgId=new SaleBackBean.ModelBean.FSaleOrgIdBean();this.FSaleOrgId.FNumber=main.FStockOrgId;//销售组织
            this.FDate = main.FDate;//入库日期
            this.FSaledeptid=new SaleBackBean.ModelBean.FSaledeptidBean();this.FSaledeptid.FNumber=main.FPurchaseDeptId;//销售部门
            this.FStockDeptId=new SaleBackBean.ModelBean.FStockDeptIdBean();this.FStockDeptId.FNumber=main.FDepartmentNumber;//库存部门
//            this.FDeliveryDeptID.FName="车间";             //发货部门
            this.FStockerId = new SaleBackBean.ModelBean.FStockerIdBean();       //仓管员
            this.FStockerId.FNumber=main.FStockerNumber;       //仓管员
            this.FSalesManId=new SaleBackBean.ModelBean.FSalesManIdBean();//销售员
            this.FSalesManId.FNumber=main.FPurchaserId;//销售员
//            this.FOwnerTypeIdHead = main.FOwnerTypeIdHead;//货主类型
//            this.FOwnerIdHead=new SaleBackBean.ModelBean.FOwnerIdHeadBean(main.FOwnerIdHead);//货主
            this.FRetcustId=new FRetcustIdBean();this.FRetcustId.FNumber=main.FCustomerID;//退货客户
//            this.FInStockFin=new FInStockFinBean();//一个大类
//            this.FInStockFin.FPriceTimePoint= main.FPriceTimePoint;         //定价时点
            this.SubHeadEntity=new SaleBackBean.ModelBean.SubHeadEntityBean();
            this.SubHeadEntity.FSettleOrgId=new SaleBackBean.ModelBean.SubHeadEntityBean.FSettleOrgIdBean();
            this.SubHeadEntity.FSettleOrgId.FNumber = main.FSettleOrgId;//结算组织
            this.SubHeadEntity.FSettleCurrId=new SaleBackBean.ModelBean.SubHeadEntityBean.FSettleCurrIdBean();
            this.SubHeadEntity.FSettleCurrId.FNumber = main.FSettleCurrId;//结算币别
            //----------------------------------------------------------------
            //表体设置----------------------------------------------------------------
            this.FEntity=new ArrayList<>();//一个大类数组
            for (int i = 0; i < beans.size(); i++) {
                SaleBackBean.ModelBean.FEntityBean bean1=new SaleBackBean.ModelBean.FEntityBean();//一个大类
                bean1.FMaterialId = new SaleBackBean.ModelBean.FEntityBean.FMaterialIdBean();//物料编码
                bean1.FMaterialId.FNumber=beans.get(i).FMaterialId;//物料编码
                bean1.FStockId=new SaleBackBean.ModelBean.FEntityBean.FStockIdBean();//仓库
                bean1.FStockId.FNumber=beans.get(i).FStorageId;//仓库
                bean1.FLot=new SaleBackBean.ModelBean.FEntityBean.FLotBean();//批号
                bean1.FLot.FNumber=beans.get(i).FBatch;//批号
                bean1.FRealQty=beans.get(i).FRealQty;         //实退数量
//                bean1.FRemainInStockUnitId = new FEntityBean.FRemainInStockUnitIdBean(beans.get(i).FRemainInStockUnitId);//采购单位
//                bean1.FPriceUnitID = new FEntityBean.FPriceUnitIDBean(beans.get(i).FPriceUnitID);//计价单位
                bean1.FUnitID = new SaleBackBean.ModelBean.FEntityBean.FUnitIDBean();//库存单位
                bean1.FUnitID.FNumber=beans.get(i).FUnitID;//库存单位
                bean1.FIsFree = beans.get(i).FIsFree;//是否为赠品
                bean1.FReturnType = new SaleBackBean.ModelBean.FEntityBean.FReturnTypeBean();//退货日期
                bean1.FReturnType.FNumber = beans.get(i).FBackType;//退货类型
                bean1.FDeliveryDate = beans.get(i).FBackDate;//退货日期
                bean1.FOwnerTypeId = "BD_OwnerOrg";//货主类型
                bean1.FOwnerId=new SaleBackBean.ModelBean.FEntityBean.FOwnerIdBean();//货主
                bean1.FOwnerId.FNumber = "100";//货主
                this.FEntity.add(bean1);//添加进数组
            }
        }


        public String getFID() {
            return FID;
        }

        public void setFID(String FID) {
            this.FID = FID;
        }

        public FBillTypeIDBean getFBillTypeID() {
            return FBillTypeID;
        }

        public void setFBillTypeID(FBillTypeIDBean FBillTypeID) {
            this.FBillTypeID = FBillTypeID;
        }

        public String getFBillNo() {
            return FBillNo;
        }

        public void setFBillNo(String FBillNo) {
            this.FBillNo = FBillNo;
        }

        public String getFDate() {
            return FDate;
        }

        public void setFDate(String FDate) {
            this.FDate = FDate;
        }

        public FSaleOrgIdBean getFSaleOrgId() {
            return FSaleOrgId;
        }

        public void setFSaleOrgId(FSaleOrgIdBean FSaleOrgId) {
            this.FSaleOrgId = FSaleOrgId;
        }

        public FRetcustIdBean getFRetcustId() {
            return FRetcustId;
        }

        public void setFRetcustId(FRetcustIdBean FRetcustId) {
            this.FRetcustId = FRetcustId;
        }

        public FSaledeptidBean getFSaledeptid() {
            return FSaledeptid;
        }

        public void setFSaledeptid(FSaledeptidBean FSaledeptid) {
            this.FSaledeptid = FSaledeptid;
        }

        public FReturnReasonBean getFReturnReason() {
            return FReturnReason;
        }

        public void setFReturnReason(FReturnReasonBean FReturnReason) {
            this.FReturnReason = FReturnReason;
        }

        public FHeadLocIdBean getFHeadLocId() {
            return FHeadLocId;
        }

        public void setFHeadLocId(FHeadLocIdBean FHeadLocId) {
            this.FHeadLocId = FHeadLocId;
        }

        public FSaleGroupIdBean getFSaleGroupId() {
            return FSaleGroupId;
        }

        public void setFSaleGroupId(FSaleGroupIdBean FSaleGroupId) {
            this.FSaleGroupId = FSaleGroupId;
        }

        public FTransferBizTypeBean getFTransferBizType() {
            return FTransferBizType;
        }

        public void setFTransferBizType(FTransferBizTypeBean FTransferBizType) {
            this.FTransferBizType = FTransferBizType;
        }

        public FCorrespondOrgIdBean getFCorrespondOrgId() {
            return FCorrespondOrgId;
        }

        public void setFCorrespondOrgId(FCorrespondOrgIdBean FCorrespondOrgId) {
            this.FCorrespondOrgId = FCorrespondOrgId;
        }

        public FSalesManIdBean getFSalesManId() {
            return FSalesManId;
        }

        public void setFSalesManId(FSalesManIdBean FSalesManId) {
            this.FSalesManId = FSalesManId;
        }

        public FStockOrgIdBean getFStockOrgId() {
            return FStockOrgId;
        }

        public void setFStockOrgId(FStockOrgIdBean FStockOrgId) {
            this.FStockOrgId = FStockOrgId;
        }

        public FStockDeptIdBean getFStockDeptId() {
            return FStockDeptId;
        }

        public void setFStockDeptId(FStockDeptIdBean FStockDeptId) {
            this.FStockDeptId = FStockDeptId;
        }

        public FStockerGroupIdBean getFStockerGroupId() {
            return FStockerGroupId;
        }

        public void setFStockerGroupId(FStockerGroupIdBean FStockerGroupId) {
            this.FStockerGroupId = FStockerGroupId;
        }

        public FStockerIdBean getFStockerId() {
            return FStockerId;
        }

        public void setFStockerId(FStockerIdBean FStockerId) {
            this.FStockerId = FStockerId;
        }

        public String getFHeadNote() {
            return FHeadNote;
        }

        public void setFHeadNote(String FHeadNote) {
            this.FHeadNote = FHeadNote;
        }

        public FReceiveCustIdBean getFReceiveCustId() {
            return FReceiveCustId;
        }

        public void setFReceiveCustId(FReceiveCustIdBean FReceiveCustId) {
            this.FReceiveCustId = FReceiveCustId;
        }

        public String getFReceiveAddress() {
            return FReceiveAddress;
        }

        public void setFReceiveAddress(String FReceiveAddress) {
            this.FReceiveAddress = FReceiveAddress;
        }

        public FSettleCustIdBean getFSettleCustId() {
            return FSettleCustId;
        }

        public void setFSettleCustId(FSettleCustIdBean FSettleCustId) {
            this.FSettleCustId = FSettleCustId;
        }

        public FReceiveCusContactBean getFReceiveCusContact() {
            return FReceiveCusContact;
        }

        public void setFReceiveCusContact(FReceiveCusContactBean FReceiveCusContact) {
            this.FReceiveCusContact = FReceiveCusContact;
        }

        public FPayCustIdBean getFPayCustId() {
            return FPayCustId;
        }

        public void setFPayCustId(FPayCustIdBean FPayCustId) {
            this.FPayCustId = FPayCustId;
        }

        public String getFOwnerTypeIdHead() {
            return FOwnerTypeIdHead;
        }

        public void setFOwnerTypeIdHead(String FOwnerTypeIdHead) {
            this.FOwnerTypeIdHead = FOwnerTypeIdHead;
        }

        public FOwnerIdHeadBean getFOwnerIdHead() {
            return FOwnerIdHead;
        }

        public void setFOwnerIdHead(FOwnerIdHeadBean FOwnerIdHead) {
            this.FOwnerIdHead = FOwnerIdHead;
        }

        public String getFScanBox() {
            return FScanBox;
        }

        public void setFScanBox(String FScanBox) {
            this.FScanBox = FScanBox;
        }

        public String getFCDateOffsetUnit() {
            return FCDateOffsetUnit;
        }

        public void setFCDateOffsetUnit(String FCDateOffsetUnit) {
            this.FCDateOffsetUnit = FCDateOffsetUnit;
        }

        public String getFCDateOffsetValue() {
            return FCDateOffsetValue;
        }

        public void setFCDateOffsetValue(String FCDateOffsetValue) {
            this.FCDateOffsetValue = FCDateOffsetValue;
        }

        public String getFIsTotalServiceOrCost() {
            return FIsTotalServiceOrCost;
        }

        public void setFIsTotalServiceOrCost(String FIsTotalServiceOrCost) {
            this.FIsTotalServiceOrCost = FIsTotalServiceOrCost;
        }

        public SubHeadEntityBean getSubHeadEntity() {
            return SubHeadEntity;
        }

        public void setSubHeadEntity(SubHeadEntityBean SubHeadEntity) {
            this.SubHeadEntity = SubHeadEntity;
        }

        public List<FEntityBean> getFEntity() {
            return FEntity;
        }

        public void setFEntity(List<FEntityBean> FEntity) {
            this.FEntity = FEntity;
        }

        public static class FBillTypeIDBean {
            /**
             * FNUMBER :
             */

            private String FNUMBER;

            public String getFNUMBER() {
                return FNUMBER;
            }

            public void setFNUMBER(String FNUMBER) {
                this.FNUMBER = FNUMBER;
            }
        }

        public static class FSaleOrgIdBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FRetcustIdBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FSaledeptidBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FReturnReasonBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FHeadLocIdBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FSaleGroupIdBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FTransferBizTypeBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FCorrespondOrgIdBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FSalesManIdBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FStockOrgIdBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FStockDeptIdBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FStockerGroupIdBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FStockerIdBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FReceiveCustIdBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FSettleCustIdBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FReceiveCusContactBean {
            /**
             * FNAME :
             */

            private String FNAME;

            public String getFNAME() {
                return FNAME;
            }

            public void setFNAME(String FNAME) {
                this.FNAME = FNAME;
            }
        }

        public static class FPayCustIdBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FOwnerIdHeadBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class SubHeadEntityBean {
            /**
             * FEntryId : 0
             * FSettleCurrId : {"FNumber":""}
             * FThirdBillNo :
             * FThirdBillId :
             * FThirdSrcType :
             * FSettleOrgId : {"FNumber":""}
             * FSettleTypeId : {"FNumber":""}
             * FChageCondition : {"FNumber":""}
             * FPriceListId : {"FNumber":""}
             * FDiscountListId : {"FNumber":""}
             * FLocalCurrId : {"FNumber":""}
             * FExchangeTypeId : {"FNumber":""}
             * FExchangeRate : 0
             */

            private String FEntryId;
            private FSettleCurrIdBean FSettleCurrId;
            private String FThirdBillNo;
            private String FThirdBillId;
            private String FThirdSrcType;
            private FSettleOrgIdBean FSettleOrgId;
            private FSettleTypeIdBean FSettleTypeId;
            private FChageConditionBean FChageCondition;
            private FPriceListIdBean FPriceListId;
            private FDiscountListIdBean FDiscountListId;
            private FLocalCurrIdBean FLocalCurrId;
            private FExchangeTypeIdBean FExchangeTypeId;
            private String FExchangeRate;

            public String getFEntryId() {
                return FEntryId;
            }

            public void setFEntryId(String FEntryId) {
                this.FEntryId = FEntryId;
            }

            public FSettleCurrIdBean getFSettleCurrId() {
                return FSettleCurrId;
            }

            public void setFSettleCurrId(FSettleCurrIdBean FSettleCurrId) {
                this.FSettleCurrId = FSettleCurrId;
            }

            public String getFThirdBillNo() {
                return FThirdBillNo;
            }

            public void setFThirdBillNo(String FThirdBillNo) {
                this.FThirdBillNo = FThirdBillNo;
            }

            public String getFThirdBillId() {
                return FThirdBillId;
            }

            public void setFThirdBillId(String FThirdBillId) {
                this.FThirdBillId = FThirdBillId;
            }

            public String getFThirdSrcType() {
                return FThirdSrcType;
            }

            public void setFThirdSrcType(String FThirdSrcType) {
                this.FThirdSrcType = FThirdSrcType;
            }

            public FSettleOrgIdBean getFSettleOrgId() {
                return FSettleOrgId;
            }

            public void setFSettleOrgId(FSettleOrgIdBean FSettleOrgId) {
                this.FSettleOrgId = FSettleOrgId;
            }

            public FSettleTypeIdBean getFSettleTypeId() {
                return FSettleTypeId;
            }

            public void setFSettleTypeId(FSettleTypeIdBean FSettleTypeId) {
                this.FSettleTypeId = FSettleTypeId;
            }

            public FChageConditionBean getFChageCondition() {
                return FChageCondition;
            }

            public void setFChageCondition(FChageConditionBean FChageCondition) {
                this.FChageCondition = FChageCondition;
            }

            public FPriceListIdBean getFPriceListId() {
                return FPriceListId;
            }

            public void setFPriceListId(FPriceListIdBean FPriceListId) {
                this.FPriceListId = FPriceListId;
            }

            public FDiscountListIdBean getFDiscountListId() {
                return FDiscountListId;
            }

            public void setFDiscountListId(FDiscountListIdBean FDiscountListId) {
                this.FDiscountListId = FDiscountListId;
            }

            public FLocalCurrIdBean getFLocalCurrId() {
                return FLocalCurrId;
            }

            public void setFLocalCurrId(FLocalCurrIdBean FLocalCurrId) {
                this.FLocalCurrId = FLocalCurrId;
            }

            public FExchangeTypeIdBean getFExchangeTypeId() {
                return FExchangeTypeId;
            }

            public void setFExchangeTypeId(FExchangeTypeIdBean FExchangeTypeId) {
                this.FExchangeTypeId = FExchangeTypeId;
            }

            public String getFExchangeRate() {
                return FExchangeRate;
            }

            public void setFExchangeRate(String FExchangeRate) {
                this.FExchangeRate = FExchangeRate;
            }

            public static class FSettleCurrIdBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FSettleOrgIdBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FSettleTypeIdBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FChageConditionBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FPriceListIdBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FDiscountListIdBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FLocalCurrIdBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FExchangeTypeIdBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }
        }

        public static class FEntityBean {
            /**
             * FENTRYID : 0
             * FRowType :
             * FMapId : {"FNumber":""}
             * FMaterialId : {"FNumber":""}
             * FAuxpropId : {}
             * FUnitID : {"FNumber":""}
             * FInventoryQty : 0
             * FRealQty : 0
             * FParentMatId : {"FNUMBER":""}
             * FPrice : 0
             * FTaxPrice : 0
             * FIsFree : false
             * FTaxCombination : {"FNumber":""}
             * FEntryTaxRate : 0
             * FBOMId : {"FNumber":""}
             * FReturnType : {"FNumber":""}
             * FOwnerTypeId :
             * FOwnerId : {"FNumber":""}
             * FProduceDate : 1900-01-01
             * FExpiryDate : 1900-01-01
             * FStockId : {"FNumber":""}
             * FStocklocId : {"FSTOCKLOCID__FF100003":{"FNumber":""},"FSTOCKLOCID__FF100012":{"FNumber":""}}
             * FStockstatusId : {"FNumber":""}
             * FDeliveryDate : 1900-01-01
             * FMtoNo :
             * FNote :
             * FDiscountRate : 0
             * FPriceDiscount : 0
             * FAuxUnitQty : 0
             * FExtAuxUnitId : {"FNumber":""}
             * FExtAuxUnitQty : 0
             * FSalCostPrice : 0
             * FISCONSUMESUM :
             * FLot : {"FNumber":""}
             * FSalUnitID : {"FNumber":""}
             * FSalUnitQty : 0
             * FSalBaseQty : 0
             * FPriceBaseQty : 0
             * FProjectNo :
             * FQualifyType :
             * FEOwnerSupplierId : {"FNUMBER":""}
             * FIsOverLegalOrg : false
             * FESettleCustomerId : {"FNUMBER":""}
             * FSOEntryId : 0
             * FThirdEntryId :
             * FPriceListEntry : {"FNUMBER":""}
             * FARNOTJOINQTY : 0
             * FIsReturnCheck : false
             * FTaxDetailSubEntity : [{"FDetailID":"0","FTaxRate":"0"}]
             * FSerialSubEntity : [{"FDetailID":"0","FSerialNo":"","FSerialNote":""}]
             */

            private String FENTRYID;
            private String FRowType;
            private FMapIdBean FMapId;
            private FMaterialIdBean FMaterialId;
            private FAuxpropIdBean FAuxpropId;
            private FUnitIDBean FUnitID;
            private String FInventoryQty;
            private String FRealQty;
            private FParentMatIdBean FParentMatId;
            private String FPrice;
            private String FTaxPrice;
            private boolean FIsFree;
            private FTaxCombinationBean FTaxCombination;
            private String FEntryTaxRate;
            private FBOMIdBean FBOMId;
            private FReturnTypeBean FReturnType;
            private String FOwnerTypeId;
            private FOwnerIdBean FOwnerId;
            private String FProduceDate;
            private String FExpiryDate;
            private FStockIdBean FStockId;
            private FStocklocIdBean FStocklocId;
            private FStockstatusIdBean FStockstatusId;
            private String FDeliveryDate;
            private String FMtoNo;
            private String FNote;
            private String FDiscountRate;
            private String FPriceDiscount;
            private String FAuxUnitQty;
            private FExtAuxUnitIdBean FExtAuxUnitId;
            private String FExtAuxUnitQty;
            private String FSalCostPrice;
            private String FISCONSUMESUM;
            private FLotBean FLot;
            private FSalUnitIDBean FSalUnitID;
            private String FSalUnitQty;
            private String FSalBaseQty;
            private String FPriceBaseQty;
            private String FProjectNo;
            private String FQualifyType;
            private FEOwnerSupplierIdBean FEOwnerSupplierId;
            private String FIsOverLegalOrg;
            private FESettleCustomerIdBean FESettleCustomerId;
            private String FSOEntryId;
            private String FThirdEntryId;
            private FPriceListEntryBean FPriceListEntry;
            private String FARNOTJOINQTY;
            private String FIsReturnCheck;
            private List<FTaxDetailSubEntityBean> FTaxDetailSubEntity;
            private List<FSerialSubEntityBean> FSerialSubEntity;

            public String getFENTRYID() {
                return FENTRYID;
            }

            public void setFENTRYID(String FENTRYID) {
                this.FENTRYID = FENTRYID;
            }

            public String getFRowType() {
                return FRowType;
            }

            public void setFRowType(String FRowType) {
                this.FRowType = FRowType;
            }

            public FMapIdBean getFMapId() {
                return FMapId;
            }

            public void setFMapId(FMapIdBean FMapId) {
                this.FMapId = FMapId;
            }

            public FMaterialIdBean getFMaterialId() {
                return FMaterialId;
            }

            public void setFMaterialId(FMaterialIdBean FMaterialId) {
                this.FMaterialId = FMaterialId;
            }

            public FAuxpropIdBean getFAuxpropId() {
                return FAuxpropId;
            }

            public void setFAuxpropId(FAuxpropIdBean FAuxpropId) {
                this.FAuxpropId = FAuxpropId;
            }

            public FUnitIDBean getFUnitID() {
                return FUnitID;
            }

            public void setFUnitID(FUnitIDBean FUnitID) {
                this.FUnitID = FUnitID;
            }

            public String getFInventoryQty() {
                return FInventoryQty;
            }

            public void setFInventoryQty(String FInventoryQty) {
                this.FInventoryQty = FInventoryQty;
            }

            public String getFRealQty() {
                return FRealQty;
            }

            public void setFRealQty(String FRealQty) {
                this.FRealQty = FRealQty;
            }

            public FParentMatIdBean getFParentMatId() {
                return FParentMatId;
            }

            public void setFParentMatId(FParentMatIdBean FParentMatId) {
                this.FParentMatId = FParentMatId;
            }

            public String getFPrice() {
                return FPrice;
            }

            public void setFPrice(String FPrice) {
                this.FPrice = FPrice;
            }

            public String getFTaxPrice() {
                return FTaxPrice;
            }

            public void setFTaxPrice(String FTaxPrice) {
                this.FTaxPrice = FTaxPrice;
            }

            public boolean getFIsFree() {
                return FIsFree;
            }

            public void setFIsFree(boolean FIsFree) {
                this.FIsFree = FIsFree;
            }

            public FTaxCombinationBean getFTaxCombination() {
                return FTaxCombination;
            }

            public void setFTaxCombination(FTaxCombinationBean FTaxCombination) {
                this.FTaxCombination = FTaxCombination;
            }

            public String getFEntryTaxRate() {
                return FEntryTaxRate;
            }

            public void setFEntryTaxRate(String FEntryTaxRate) {
                this.FEntryTaxRate = FEntryTaxRate;
            }

            public FBOMIdBean getFBOMId() {
                return FBOMId;
            }

            public void setFBOMId(FBOMIdBean FBOMId) {
                this.FBOMId = FBOMId;
            }

            public FReturnTypeBean getFReturnType() {
                return FReturnType;
            }

            public void setFReturnType(FReturnTypeBean FReturnType) {
                this.FReturnType = FReturnType;
            }

            public String getFOwnerTypeId() {
                return FOwnerTypeId;
            }

            public void setFOwnerTypeId(String FOwnerTypeId) {
                this.FOwnerTypeId = FOwnerTypeId;
            }

            public FOwnerIdBean getFOwnerId() {
                return FOwnerId;
            }

            public void setFOwnerId(FOwnerIdBean FOwnerId) {
                this.FOwnerId = FOwnerId;
            }

            public String getFProduceDate() {
                return FProduceDate;
            }

            public void setFProduceDate(String FProduceDate) {
                this.FProduceDate = FProduceDate;
            }

            public String getFExpiryDate() {
                return FExpiryDate;
            }

            public void setFExpiryDate(String FExpiryDate) {
                this.FExpiryDate = FExpiryDate;
            }

            public FStockIdBean getFStockId() {
                return FStockId;
            }

            public void setFStockId(FStockIdBean FStockId) {
                this.FStockId = FStockId;
            }

            public FStocklocIdBean getFStocklocId() {
                return FStocklocId;
            }

            public void setFStocklocId(FStocklocIdBean FStocklocId) {
                this.FStocklocId = FStocklocId;
            }

            public FStockstatusIdBean getFStockstatusId() {
                return FStockstatusId;
            }

            public void setFStockstatusId(FStockstatusIdBean FStockstatusId) {
                this.FStockstatusId = FStockstatusId;
            }

            public String getFDeliveryDate() {
                return FDeliveryDate;
            }

            public void setFDeliveryDate(String FDeliveryDate) {
                this.FDeliveryDate = FDeliveryDate;
            }

            public String getFMtoNo() {
                return FMtoNo;
            }

            public void setFMtoNo(String FMtoNo) {
                this.FMtoNo = FMtoNo;
            }

            public String getFNote() {
                return FNote;
            }

            public void setFNote(String FNote) {
                this.FNote = FNote;
            }

            public String getFDiscountRate() {
                return FDiscountRate;
            }

            public void setFDiscountRate(String FDiscountRate) {
                this.FDiscountRate = FDiscountRate;
            }

            public String getFPriceDiscount() {
                return FPriceDiscount;
            }

            public void setFPriceDiscount(String FPriceDiscount) {
                this.FPriceDiscount = FPriceDiscount;
            }

            public String getFAuxUnitQty() {
                return FAuxUnitQty;
            }

            public void setFAuxUnitQty(String FAuxUnitQty) {
                this.FAuxUnitQty = FAuxUnitQty;
            }

            public FExtAuxUnitIdBean getFExtAuxUnitId() {
                return FExtAuxUnitId;
            }

            public void setFExtAuxUnitId(FExtAuxUnitIdBean FExtAuxUnitId) {
                this.FExtAuxUnitId = FExtAuxUnitId;
            }

            public String getFExtAuxUnitQty() {
                return FExtAuxUnitQty;
            }

            public void setFExtAuxUnitQty(String FExtAuxUnitQty) {
                this.FExtAuxUnitQty = FExtAuxUnitQty;
            }

            public String getFSalCostPrice() {
                return FSalCostPrice;
            }

            public void setFSalCostPrice(String FSalCostPrice) {
                this.FSalCostPrice = FSalCostPrice;
            }

            public String getFISCONSUMESUM() {
                return FISCONSUMESUM;
            }

            public void setFISCONSUMESUM(String FISCONSUMESUM) {
                this.FISCONSUMESUM = FISCONSUMESUM;
            }

            public FLotBean getFLot() {
                return FLot;
            }

            public void setFLot(FLotBean FLot) {
                this.FLot = FLot;
            }

            public FSalUnitIDBean getFSalUnitID() {
                return FSalUnitID;
            }

            public void setFSalUnitID(FSalUnitIDBean FSalUnitID) {
                this.FSalUnitID = FSalUnitID;
            }

            public String getFSalUnitQty() {
                return FSalUnitQty;
            }

            public void setFSalUnitQty(String FSalUnitQty) {
                this.FSalUnitQty = FSalUnitQty;
            }

            public String getFSalBaseQty() {
                return FSalBaseQty;
            }

            public void setFSalBaseQty(String FSalBaseQty) {
                this.FSalBaseQty = FSalBaseQty;
            }

            public String getFPriceBaseQty() {
                return FPriceBaseQty;
            }

            public void setFPriceBaseQty(String FPriceBaseQty) {
                this.FPriceBaseQty = FPriceBaseQty;
            }

            public String getFProjectNo() {
                return FProjectNo;
            }

            public void setFProjectNo(String FProjectNo) {
                this.FProjectNo = FProjectNo;
            }

            public String getFQualifyType() {
                return FQualifyType;
            }

            public void setFQualifyType(String FQualifyType) {
                this.FQualifyType = FQualifyType;
            }

            public FEOwnerSupplierIdBean getFEOwnerSupplierId() {
                return FEOwnerSupplierId;
            }

            public void setFEOwnerSupplierId(FEOwnerSupplierIdBean FEOwnerSupplierId) {
                this.FEOwnerSupplierId = FEOwnerSupplierId;
            }

            public String getFIsOverLegalOrg() {
                return FIsOverLegalOrg;
            }

            public void setFIsOverLegalOrg(String FIsOverLegalOrg) {
                this.FIsOverLegalOrg = FIsOverLegalOrg;
            }

            public FESettleCustomerIdBean getFESettleCustomerId() {
                return FESettleCustomerId;
            }

            public void setFESettleCustomerId(FESettleCustomerIdBean FESettleCustomerId) {
                this.FESettleCustomerId = FESettleCustomerId;
            }

            public String getFSOEntryId() {
                return FSOEntryId;
            }

            public void setFSOEntryId(String FSOEntryId) {
                this.FSOEntryId = FSOEntryId;
            }

            public String getFThirdEntryId() {
                return FThirdEntryId;
            }

            public void setFThirdEntryId(String FThirdEntryId) {
                this.FThirdEntryId = FThirdEntryId;
            }

            public FPriceListEntryBean getFPriceListEntry() {
                return FPriceListEntry;
            }

            public void setFPriceListEntry(FPriceListEntryBean FPriceListEntry) {
                this.FPriceListEntry = FPriceListEntry;
            }

            public String getFARNOTJOINQTY() {
                return FARNOTJOINQTY;
            }

            public void setFARNOTJOINQTY(String FARNOTJOINQTY) {
                this.FARNOTJOINQTY = FARNOTJOINQTY;
            }

            public String getFIsReturnCheck() {
                return FIsReturnCheck;
            }

            public void setFIsReturnCheck(String FIsReturnCheck) {
                this.FIsReturnCheck = FIsReturnCheck;
            }

            public List<FTaxDetailSubEntityBean> getFTaxDetailSubEntity() {
                return FTaxDetailSubEntity;
            }

            public void setFTaxDetailSubEntity(List<FTaxDetailSubEntityBean> FTaxDetailSubEntity) {
                this.FTaxDetailSubEntity = FTaxDetailSubEntity;
            }

            public List<FSerialSubEntityBean> getFSerialSubEntity() {
                return FSerialSubEntity;
            }

            public void setFSerialSubEntity(List<FSerialSubEntityBean> FSerialSubEntity) {
                this.FSerialSubEntity = FSerialSubEntity;
            }

            public static class FMapIdBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FMaterialIdBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FAuxpropIdBean {
            }

            public static class FUnitIDBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FParentMatIdBean {
                /**
                 * FNUMBER :
                 */

                private String FNUMBER;

                public String getFNUMBER() {
                    return FNUMBER;
                }

                public void setFNUMBER(String FNUMBER) {
                    this.FNUMBER = FNUMBER;
                }
            }

            public static class FTaxCombinationBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FBOMIdBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FReturnTypeBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FOwnerIdBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FStockIdBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FStocklocIdBean {
                /**
                 * FSTOCKLOCID__FF100003 : {"FNumber":""}
                 * FSTOCKLOCID__FF100012 : {"FNumber":""}
                 */

                private FSTOCKLOCIDFF100003Bean FSTOCKLOCID__FF100003;
                private FSTOCKLOCIDFF100012Bean FSTOCKLOCID__FF100012;

                public FSTOCKLOCIDFF100003Bean getFSTOCKLOCID__FF100003() {
                    return FSTOCKLOCID__FF100003;
                }

                public void setFSTOCKLOCID__FF100003(FSTOCKLOCIDFF100003Bean FSTOCKLOCID__FF100003) {
                    this.FSTOCKLOCID__FF100003 = FSTOCKLOCID__FF100003;
                }

                public FSTOCKLOCIDFF100012Bean getFSTOCKLOCID__FF100012() {
                    return FSTOCKLOCID__FF100012;
                }

                public void setFSTOCKLOCID__FF100012(FSTOCKLOCIDFF100012Bean FSTOCKLOCID__FF100012) {
                    this.FSTOCKLOCID__FF100012 = FSTOCKLOCID__FF100012;
                }

                public static class FSTOCKLOCIDFF100003Bean {
                    /**
                     * FNumber :
                     */

                    private String FNumber;

                    public String getFNumber() {
                        return FNumber;
                    }

                    public void setFNumber(String FNumber) {
                        this.FNumber = FNumber;
                    }
                }

                public static class FSTOCKLOCIDFF100012Bean {
                    /**
                     * FNumber :
                     */

                    private String FNumber;

                    public String getFNumber() {
                        return FNumber;
                    }

                    public void setFNumber(String FNumber) {
                        this.FNumber = FNumber;
                    }
                }
            }

            public static class FStockstatusIdBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FExtAuxUnitIdBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FLotBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FSalUnitIDBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FEOwnerSupplierIdBean {
                /**
                 * FNUMBER :
                 */

                private String FNUMBER;

                public String getFNUMBER() {
                    return FNUMBER;
                }

                public void setFNUMBER(String FNUMBER) {
                    this.FNUMBER = FNUMBER;
                }
            }

            public static class FESettleCustomerIdBean {
                /**
                 * FNUMBER :
                 */

                private String FNUMBER;

                public String getFNUMBER() {
                    return FNUMBER;
                }

                public void setFNUMBER(String FNUMBER) {
                    this.FNUMBER = FNUMBER;
                }
            }

            public static class FPriceListEntryBean {
                /**
                 * FNUMBER :
                 */

                private String FNUMBER;

                public String getFNUMBER() {
                    return FNUMBER;
                }

                public void setFNUMBER(String FNUMBER) {
                    this.FNUMBER = FNUMBER;
                }
            }

            public static class FTaxDetailSubEntityBean {
                /**
                 * FDetailID : 0
                 * FTaxRate : 0
                 */

                private String FDetailID;
                private String FTaxRate;

                public String getFDetailID() {
                    return FDetailID;
                }

                public void setFDetailID(String FDetailID) {
                    this.FDetailID = FDetailID;
                }

                public String getFTaxRate() {
                    return FTaxRate;
                }

                public void setFTaxRate(String FTaxRate) {
                    this.FTaxRate = FTaxRate;
                }
            }

            public static class FSerialSubEntityBean {
                /**
                 * FDetailID : 0
                 * FSerialNo :
                 * FSerialNote :
                 */

                private String FDetailID;
                private String FSerialNo;
                private String FSerialNote;

                public String getFDetailID() {
                    return FDetailID;
                }

                public void setFDetailID(String FDetailID) {
                    this.FDetailID = FDetailID;
                }

                public String getFSerialNo() {
                    return FSerialNo;
                }

                public void setFSerialNo(String FSerialNo) {
                    this.FSerialNo = FSerialNo;
                }

                public String getFSerialNote() {
                    return FSerialNote;
                }

                public void setFSerialNote(String FSerialNote) {
                    this.FSerialNote = FSerialNote;
                }
            }
        }
    }
}
