package com.fangzuo.assist.cloud.Beans;


import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.Dao.T_main;

import java.util.ArrayList;
import java.util.List;

public class SaleOutBean {


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
     * Model : {"FID":"0","FBillTypeID":{"FNUMBER":""},"FBillNo":"","FDate":"1900-01-01","FSaleOrgId":{"FNumber":""},"FCustomerID":{"FNumber":""},"FSaleDeptID":{"FNumber":""},"FHeadLocationId":{"FNumber":""},"FCarrierID":{"FNumber":""},"FCorrespondOrgId":{"FNumber":""},"FCarriageNO":"","FSalesGroupID":{"FNumber":""},"FSalesManID":{"FNumber":""},"FStockOrgId":{"FNumber":""},"FDeliveryDeptID":{"FNumber":""},"FStockerGroupID":{"FNumber":""},"FStockerID":{"FNumber":""},"FNote":"","FReceiverID":{"FNumber":""},"FReceiveAddress":"","FSettleID":{"FNumber":""},"FReceiverContactID":{"FNAME":""},"FPayerID":{"FNumber":""},"FOwnerTypeIdHead":"","FOwnerIdHead":{"FNumber":""},"FScanBox":"","FCDateOffsetUnit":"","FCDateOffsetValue":"0","FPlanRecAddress":"","FIsTotalServiceOrCost":"false","SubHeadEntity":{"FEntryId":"0","FSettleCurrID":{"FNumber":""},"FThirdBillNo":"","FThirdBillId":"","FThirdSrcType":"","FSettleOrgID":{"FNumber":""},"FSettleTypeID":{"FNumber":""},"FReceiptConditionID":{"FNumber":""},"FPriceListId":{"FNumber":""},"FDiscountListId":{"FNumber":""},"FIsIncludedTax":"false","FLocalCurrID":{"FNumber":""},"FExchangeTypeID":{"FNumber":""},"FExchangeRate":"0","FIsPriceExcludeTax":"false"},"FEntity":[{"FENTRYID":"0","FRowType":"","FCustMatID":{"FNumber":""},"FMaterialID":{"FNumber":""},"FAuxPropId":{},"FUnitID":{"FNumber":""},"FInventoryQty":"0","FParentMatId":{"FNUMBER":""},"FRealQty":"0","FDisPriceQty":"0","FPrice":"0","FTaxPrice":"0","FIsFree":"false","FBomID":{"FNumber":""},"FProduceDate":"1900-01-01","FOwnerTypeID":"","FOwnerID":{"FNumber":""},"FLot":{"FNumber":""},"FExpiryDate":"1900-01-01","FTaxCombination":{"FNumber":""},"FEntryTaxRate":"0","FAuxUnitQty":"0","FExtAuxUnitId":{"FNumber":""},"FExtAuxUnitQty":"0","FStockID":{"FNumber":""},"FStockLocID":{"FSTOCKLOCID__FF100003":{"FNumber":""},"FSTOCKLOCID__FF100004":{"FNumber":""},"FSTOCKLOCID__FF100012":{"FNumber":""},"FSTOCKLOCID__FF100013":{"FNumber":""},"FSTOCKLOCID__FF100014":{"FNumber":""},"FSTOCKLOCID__FF100015":{"FNumber":""},"FSTOCKLOCID__FF100016":{"FNumber":""},"FSTOCKLOCID__FF100017":{"FNumber":""},"FSTOCKLOCID__FF100018":{"FNumber":""},"FSTOCKLOCID__FF100019":{"FNumber":""}},"FStockStatusID":{"FNumber":""},"FQualifyType":"","FMtoNo":"","FEntrynote":"","FDiscountRate":"0","FPriceDiscount":"0","FActQty":"0","FSalUnitID":{"FNumber":""},"FSALUNITQTY":"0","FSALBASEQTY":"0","FPRICEBASEQTY":"0","FProjectNo":"","FOUTCONTROL":"false","FRepairQty":"0","FIsCreateProDoc":"","FEOwnerSupplierId":{"FNUMBER":""},"FIsOverLegalOrg":"false","FESettleCustomerId":{"FNUMBER":""},"FPriceListEntry":{"FNUMBER":""},"FARNOTJOINQTY":"0","FQmEntryID":"0","FConvertEntryID":"0","FSOEntryId":"0","FThirdEntryId":"","FBeforeDisPriceQty":"0","FSignQty":"0","FTaxDetailSubEntity":[{"FDetailID":"0","FTaxRate":"0"}],"FSerialSubEntity":[{"FDetailID":"0","FSerialNo":"","FSerialNote":""}]}],"FOutStockTrace":[{"FEntryID":"0","FLogComId":{"FCODE":""},"FCarryBillNo":"","FDelTime":"1900-01-01","FTraceStatus":"","FOutStockTraceDetail":[{"FDetailID":"0","FTraceTime":"","FTraceDetail":""}]}]}
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
         * FCustomerID : {"FNumber":""}
         * FSaleDeptID : {"FNumber":""}
         * FHeadLocationId : {"FNumber":""}
         * FCarrierID : {"FNumber":""}
         * FCorrespondOrgId : {"FNumber":""}
         * FCarriageNO :
         * FSalesGroupID : {"FNumber":""}
         * FSalesManID : {"FNumber":""}
         * FStockOrgId : {"FNumber":""}
         * FDeliveryDeptID : {"FNumber":""}
         * FStockerGroupID : {"FNumber":""}
         * FStockerID : {"FNumber":""}
         * FNote :
         * FReceiverID : {"FNumber":""}
         * FReceiveAddress :
         * FSettleID : {"FNumber":""}
         * FReceiverContactID : {"FNAME":""}
         * FPayerID : {"FNumber":""}
         * FOwnerTypeIdHead :
         * FOwnerIdHead : {"FNumber":""}
         * FScanBox :
         * FCDateOffsetUnit :
         * FCDateOffsetValue : 0
         * FPlanRecAddress :
         * FIsTotalServiceOrCost : false
         * SubHeadEntity : {"FEntryId":"0","FSettleCurrID":{"FNumber":""},"FThirdBillNo":"","FThirdBillId":"","FThirdSrcType":"","FSettleOrgID":{"FNumber":""},"FSettleTypeID":{"FNumber":""},"FReceiptConditionID":{"FNumber":""},"FPriceListId":{"FNumber":""},"FDiscountListId":{"FNumber":""},"FIsIncludedTax":"false","FLocalCurrID":{"FNumber":""},"FExchangeTypeID":{"FNumber":""},"FExchangeRate":"0","FIsPriceExcludeTax":"false"}
         * FEntity : [{"FENTRYID":"0","FRowType":"","FCustMatID":{"FNumber":""},"FMaterialID":{"FNumber":""},"FAuxPropId":{},"FUnitID":{"FNumber":""},"FInventoryQty":"0","FParentMatId":{"FNUMBER":""},"FRealQty":"0","FDisPriceQty":"0","FPrice":"0","FTaxPrice":"0","FIsFree":"false","FBomID":{"FNumber":""},"FProduceDate":"1900-01-01","FOwnerTypeID":"","FOwnerID":{"FNumber":""},"FLot":{"FNumber":""},"FExpiryDate":"1900-01-01","FTaxCombination":{"FNumber":""},"FEntryTaxRate":"0","FAuxUnitQty":"0","FExtAuxUnitId":{"FNumber":""},"FExtAuxUnitQty":"0","FStockID":{"FNumber":""},"FStockLocID":{"FSTOCKLOCID__FF100003":{"FNumber":""},"FSTOCKLOCID__FF100004":{"FNumber":""},"FSTOCKLOCID__FF100012":{"FNumber":""},"FSTOCKLOCID__FF100013":{"FNumber":""},"FSTOCKLOCID__FF100014":{"FNumber":""},"FSTOCKLOCID__FF100015":{"FNumber":""},"FSTOCKLOCID__FF100016":{"FNumber":""},"FSTOCKLOCID__FF100017":{"FNumber":""},"FSTOCKLOCID__FF100018":{"FNumber":""},"FSTOCKLOCID__FF100019":{"FNumber":""}},"FStockStatusID":{"FNumber":""},"FQualifyType":"","FMtoNo":"","FEntrynote":"","FDiscountRate":"0","FPriceDiscount":"0","FActQty":"0","FSalUnitID":{"FNumber":""},"FSALUNITQTY":"0","FSALBASEQTY":"0","FPRICEBASEQTY":"0","FProjectNo":"","FOUTCONTROL":"false","FRepairQty":"0","FIsCreateProDoc":"","FEOwnerSupplierId":{"FNUMBER":""},"FIsOverLegalOrg":"false","FESettleCustomerId":{"FNUMBER":""},"FPriceListEntry":{"FNUMBER":""},"FARNOTJOINQTY":"0","FQmEntryID":"0","FConvertEntryID":"0","FSOEntryId":"0","FThirdEntryId":"","FBeforeDisPriceQty":"0","FSignQty":"0","FTaxDetailSubEntity":[{"FDetailID":"0","FTaxRate":"0"}],"FSerialSubEntity":[{"FDetailID":"0","FSerialNo":"","FSerialNote":""}]}]
         * FOutStockTrace : [{"FEntryID":"0","FLogComId":{"FCODE":""},"FCarryBillNo":"","FDelTime":"1900-01-01","FTraceStatus":"","FOutStockTraceDetail":[{"FDetailID":"0","FTraceTime":"","FTraceDetail":""}]}]
         */

        private String FID;
        private FBillTypeIDBean FBillTypeID;
        private String FBillNo;
        private String FDate;
        private FSaleOrgIdBean FSaleOrgId;
        private FCustomerIDBean FCustomerID;
        private FSaleDeptIDBean FSaleDeptID;
        private FHeadLocationIdBean FHeadLocationId;
        private FCarrierIDBean FCarrierID;
        private FCorrespondOrgIdBean FCorrespondOrgId;
        private String FCarriageNO;
        private FSalesGroupIDBean FSalesGroupID;
        private FSalesManIDBean FSalesManID;
        private FStockOrgIdBean FStockOrgId;
        private FDeliveryDeptIDBean FDeliveryDeptID;
        private FStockerGroupIDBean FStockerGroupID;
        private FStockerIDBean FStockerID;
        private String FNote;
        private FReceiverIDBean FReceiverID;
        private String FReceiveAddress;
        private FSettleIDBean FSettleID;
        private FReceiverContactIDBean FReceiverContactID;
        private FPayerIDBean FPayerID;
        private String FOwnerTypeIdHead;
        private FOwnerIdHeadBean FOwnerIdHead;
        private String FScanBox;
        private String FCDateOffsetUnit;
        private String FCDateOffsetValue;
        private String FPlanRecAddress;
        private String FIsTotalServiceOrCost;
        private SubHeadEntityBean SubHeadEntity;
        private List<FEntityBean> FEntity;
        private List<FOutStockTraceBean> FOutStockTrace;
        public void setModelBean(T_main main, List<T_Detail> beans){
            //表头设置-------------------------------------------------------------
            this.FBillNo ="";
            this.FStockOrgId=new SaleOutBean.ModelBean.FStockOrgIdBean();             //发货组织
            this.FStockOrgId.FNumber=main.FStockOrgId;             //发货组织
            this.FDeliveryDeptID=new SaleOutBean.ModelBean.FDeliveryDeptIDBean();             //发货部门
            this.FDeliveryDeptID.FNumber=main.FDepartmentNumber;             //发货部门
//            this.FDeliveryDeptID.FName="车间";             //发货部门
            this.FStockerID = new SaleOutBean.ModelBean.FStockerIDBean();       //仓管员
            this.FStockerID.FNumber=main.FStockerNumber;       //仓管员
            this.FBillTypeID=new SaleOutBean.ModelBean.FBillTypeIDBean();//单据类型
            this.FBillTypeID.FNUMBER=main.FBillTypeID;//单据类型
            this.FSalesManID=new SaleOutBean.ModelBean.FSalesManIDBean();//销售员
            this.FSalesManID.FNumber=main.FPurchaserId;//销售员
//            this.FOwnerTypeIdHead = main.FOwnerTypeIdHead;//货主类型
//            this.FOwnerIdHead=new SaleOutBean.ModelBean.FOwnerIdHeadBean(main.FOwnerIdHead);//货主
            this.FDate = main.FDate;//入库日期
            this.FNote = "备注";//入库日期
            this.FCustomerID=new FCustomerIDBean();//供应商
            this.FCustomerID.FNumber=main.FCustomerID;//供应商
//            this.FInStockFin=new FInStockFinBean();//一个大类
//            this.FInStockFin.FPriceTimePoint= main.FPriceTimePoint;         //定价时点
//            this.FInStockFin.FSettleOrgId=new FInStockFinBean.FSettleOrgIdBean(main.FSettleOrgId);//结算组织
            this.SubHeadEntity=new SaleOutBean.ModelBean.SubHeadEntityBean();
            this.SubHeadEntity.FSettleCurrID=new SubHeadEntityBean.FSettleCurrIDBean();
            this.SubHeadEntity.FSettleCurrID.FNumber = main.FSettleCurrId;//结算币别
            //----------------------------------------------------------------
            //表体设置----------------------------------------------------------------
            this.FEntity=new ArrayList<>();//一个大类数组
            for (int i = 0; i < beans.size(); i++) {
                FEntityBean bean1=new FEntityBean();//一个大类
                bean1.FMaterialID = new FEntityBean.FMaterialIDBean();//物料编码
                bean1.FMaterialID.FNumber=beans.get(i).FMaterialId;//物料编码
                bean1.FStockID=new FEntityBean.FStockIDBean();//仓库
                bean1.FStockID.FNumber=beans.get(i).FStorageId;//仓库
                bean1.FLot=new FEntityBean.FLotBean();//批号
                bean1.FLot.FNumber=beans.get(i).FBatch;//批号
//                bean1.FRemainInStockQty=beans.get(i).FRemainInStockQty;        //采购数量
                bean1.FRealQty=beans.get(i).FRealQty;         //实收数量
//                bean1.FRemainInStockUnitId = new FEntityBean.FRemainInStockUnitIdBean(beans.get(i).FRemainInStockUnitId);//采购单位
//                bean1.FPriceUnitID = new FEntityBean.FPriceUnitIDBean(beans.get(i).FPriceUnitID);//计价单位
                bean1.FUnitID = new FEntityBean.FUnitIDBean();//库存单位
                bean1.FUnitID.FNumber=beans.get(i).FUnitID;//库存单位
                bean1.FIsFree = beans.get(i).FIsFree;//是否为赠品


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

        public FCustomerIDBean getFCustomerID() {
            return FCustomerID;
        }

        public void setFCustomerID(FCustomerIDBean FCustomerID) {
            this.FCustomerID = FCustomerID;
        }

        public FSaleDeptIDBean getFSaleDeptID() {
            return FSaleDeptID;
        }

        public void setFSaleDeptID(FSaleDeptIDBean FSaleDeptID) {
            this.FSaleDeptID = FSaleDeptID;
        }

        public FHeadLocationIdBean getFHeadLocationId() {
            return FHeadLocationId;
        }

        public void setFHeadLocationId(FHeadLocationIdBean FHeadLocationId) {
            this.FHeadLocationId = FHeadLocationId;
        }

        public FCarrierIDBean getFCarrierID() {
            return FCarrierID;
        }

        public void setFCarrierID(FCarrierIDBean FCarrierID) {
            this.FCarrierID = FCarrierID;
        }

        public FCorrespondOrgIdBean getFCorrespondOrgId() {
            return FCorrespondOrgId;
        }

        public void setFCorrespondOrgId(FCorrespondOrgIdBean FCorrespondOrgId) {
            this.FCorrespondOrgId = FCorrespondOrgId;
        }

        public String getFCarriageNO() {
            return FCarriageNO;
        }

        public void setFCarriageNO(String FCarriageNO) {
            this.FCarriageNO = FCarriageNO;
        }

        public FSalesGroupIDBean getFSalesGroupID() {
            return FSalesGroupID;
        }

        public void setFSalesGroupID(FSalesGroupIDBean FSalesGroupID) {
            this.FSalesGroupID = FSalesGroupID;
        }

        public FSalesManIDBean getFSalesManID() {
            return FSalesManID;
        }

        public void setFSalesManID(FSalesManIDBean FSalesManID) {
            this.FSalesManID = FSalesManID;
        }

        public FStockOrgIdBean getFStockOrgId() {
            return FStockOrgId;
        }

        public void setFStockOrgId(FStockOrgIdBean FStockOrgId) {
            this.FStockOrgId = FStockOrgId;
        }

        public FDeliveryDeptIDBean getFDeliveryDeptID() {
            return FDeliveryDeptID;
        }

        public void setFDeliveryDeptID(FDeliveryDeptIDBean FDeliveryDeptID) {
            this.FDeliveryDeptID = FDeliveryDeptID;
        }

        public FStockerGroupIDBean getFStockerGroupID() {
            return FStockerGroupID;
        }

        public void setFStockerGroupID(FStockerGroupIDBean FStockerGroupID) {
            this.FStockerGroupID = FStockerGroupID;
        }

        public FStockerIDBean getFStockerID() {
            return FStockerID;
        }

        public void setFStockerID(FStockerIDBean FStockerID) {
            this.FStockerID = FStockerID;
        }

        public String getFNote() {
            return FNote;
        }

        public void setFNote(String FNote) {
            this.FNote = FNote;
        }

        public FReceiverIDBean getFReceiverID() {
            return FReceiverID;
        }

        public void setFReceiverID(FReceiverIDBean FReceiverID) {
            this.FReceiverID = FReceiverID;
        }

        public String getFReceiveAddress() {
            return FReceiveAddress;
        }

        public void setFReceiveAddress(String FReceiveAddress) {
            this.FReceiveAddress = FReceiveAddress;
        }

        public FSettleIDBean getFSettleID() {
            return FSettleID;
        }

        public void setFSettleID(FSettleIDBean FSettleID) {
            this.FSettleID = FSettleID;
        }

        public FReceiverContactIDBean getFReceiverContactID() {
            return FReceiverContactID;
        }

        public void setFReceiverContactID(FReceiverContactIDBean FReceiverContactID) {
            this.FReceiverContactID = FReceiverContactID;
        }

        public FPayerIDBean getFPayerID() {
            return FPayerID;
        }

        public void setFPayerID(FPayerIDBean FPayerID) {
            this.FPayerID = FPayerID;
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

        public String getFPlanRecAddress() {
            return FPlanRecAddress;
        }

        public void setFPlanRecAddress(String FPlanRecAddress) {
            this.FPlanRecAddress = FPlanRecAddress;
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

        public List<FOutStockTraceBean> getFOutStockTrace() {
            return FOutStockTrace;
        }

        public void setFOutStockTrace(List<FOutStockTraceBean> FOutStockTrace) {
            this.FOutStockTrace = FOutStockTrace;
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

        public static class FCustomerIDBean {
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

        public static class FSaleDeptIDBean {
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

        public static class FHeadLocationIdBean {
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

        public static class FCarrierIDBean {
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

        public static class FSalesGroupIDBean {
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

        public static class FSalesManIDBean {
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

        public static class FDeliveryDeptIDBean {
            /**
             * FNumber :
             */

            private String FNumber;
            private String FName;

            public String getFName() {
                return FName;
            }

            public void setFName(String FName) {
                this.FName = FName;
            }

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FStockerGroupIDBean {
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

        public static class FStockerIDBean {
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

        public static class FReceiverIDBean {
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

        public static class FSettleIDBean {
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

        public static class FReceiverContactIDBean {
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

        public static class FPayerIDBean {
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
             * FSettleCurrID : {"FNumber":""}
             * FThirdBillNo :
             * FThirdBillId :
             * FThirdSrcType :
             * FSettleOrgID : {"FNumber":""}
             * FSettleTypeID : {"FNumber":""}
             * FReceiptConditionID : {"FNumber":""}
             * FPriceListId : {"FNumber":""}
             * FDiscountListId : {"FNumber":""}
             * FIsIncludedTax : false
             * FLocalCurrID : {"FNumber":""}
             * FExchangeTypeID : {"FNumber":""}
             * FExchangeRate : 0
             * FIsPriceExcludeTax : false
             */

            private String FEntryId;
            private FSettleCurrIDBean FSettleCurrID;
            private String FThirdBillNo;
            private String FThirdBillId;
            private String FThirdSrcType;
            private FSettleOrgIDBean FSettleOrgID;
            private FSettleTypeIDBean FSettleTypeID;
            private FReceiptConditionIDBean FReceiptConditionID;
            private FPriceListIdBean FPriceListId;
            private FDiscountListIdBean FDiscountListId;
            private String FIsIncludedTax;
            private FLocalCurrIDBean FLocalCurrID;
            private FExchangeTypeIDBean FExchangeTypeID;
            private String FExchangeRate;
            private String FIsPriceExcludeTax;

            public String getFEntryId() {
                return FEntryId;
            }

            public void setFEntryId(String FEntryId) {
                this.FEntryId = FEntryId;
            }

            public FSettleCurrIDBean getFSettleCurrID() {
                return FSettleCurrID;
            }

            public void setFSettleCurrID(FSettleCurrIDBean FSettleCurrID) {
                this.FSettleCurrID = FSettleCurrID;
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

            public FSettleOrgIDBean getFSettleOrgID() {
                return FSettleOrgID;
            }

            public void setFSettleOrgID(FSettleOrgIDBean FSettleOrgID) {
                this.FSettleOrgID = FSettleOrgID;
            }

            public FSettleTypeIDBean getFSettleTypeID() {
                return FSettleTypeID;
            }

            public void setFSettleTypeID(FSettleTypeIDBean FSettleTypeID) {
                this.FSettleTypeID = FSettleTypeID;
            }

            public FReceiptConditionIDBean getFReceiptConditionID() {
                return FReceiptConditionID;
            }

            public void setFReceiptConditionID(FReceiptConditionIDBean FReceiptConditionID) {
                this.FReceiptConditionID = FReceiptConditionID;
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

            public String getFIsIncludedTax() {
                return FIsIncludedTax;
            }

            public void setFIsIncludedTax(String FIsIncludedTax) {
                this.FIsIncludedTax = FIsIncludedTax;
            }

            public FLocalCurrIDBean getFLocalCurrID() {
                return FLocalCurrID;
            }

            public void setFLocalCurrID(FLocalCurrIDBean FLocalCurrID) {
                this.FLocalCurrID = FLocalCurrID;
            }

            public FExchangeTypeIDBean getFExchangeTypeID() {
                return FExchangeTypeID;
            }

            public void setFExchangeTypeID(FExchangeTypeIDBean FExchangeTypeID) {
                this.FExchangeTypeID = FExchangeTypeID;
            }

            public String getFExchangeRate() {
                return FExchangeRate;
            }

            public void setFExchangeRate(String FExchangeRate) {
                this.FExchangeRate = FExchangeRate;
            }

            public String getFIsPriceExcludeTax() {
                return FIsPriceExcludeTax;
            }

            public void setFIsPriceExcludeTax(String FIsPriceExcludeTax) {
                this.FIsPriceExcludeTax = FIsPriceExcludeTax;
            }

            public static class FSettleCurrIDBean {
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

            public static class FSettleOrgIDBean {
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

            public static class FSettleTypeIDBean {
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

            public static class FReceiptConditionIDBean {
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

            public static class FLocalCurrIDBean {
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

            public static class FExchangeTypeIDBean {
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
             * FCustMatID : {"FNumber":""}
             * FMaterialID : {"FNumber":""}
             * FAuxPropId : {}
             * FUnitID : {"FNumber":""}
             * FInventoryQty : 0
             * FParentMatId : {"FNUMBER":""}
             * FRealQty : 0
             * FDisPriceQty : 0
             * FPrice : 0
             * FTaxPrice : 0
             * FIsFree : false
             * FBomID : {"FNumber":""}
             * FProduceDate : 1900-01-01
             * FOwnerTypeID :
             * FOwnerID : {"FNumber":""}
             * FLot : {"FNumber":""}
             * FExpiryDate : 1900-01-01
             * FTaxCombination : {"FNumber":""}
             * FEntryTaxRate : 0
             * FAuxUnitQty : 0
             * FExtAuxUnitId : {"FNumber":""}
             * FExtAuxUnitQty : 0
             * FStockID : {"FNumber":""}
             * FStockLocID : {"FSTOCKLOCID__FF100003":{"FNumber":""},"FSTOCKLOCID__FF100004":{"FNumber":""},"FSTOCKLOCID__FF100012":{"FNumber":""},"FSTOCKLOCID__FF100013":{"FNumber":""},"FSTOCKLOCID__FF100014":{"FNumber":""},"FSTOCKLOCID__FF100015":{"FNumber":""},"FSTOCKLOCID__FF100016":{"FNumber":""},"FSTOCKLOCID__FF100017":{"FNumber":""},"FSTOCKLOCID__FF100018":{"FNumber":""},"FSTOCKLOCID__FF100019":{"FNumber":""}}
             * FStockStatusID : {"FNumber":""}
             * FQualifyType :
             * FMtoNo :
             * FEntrynote :
             * FDiscountRate : 0
             * FPriceDiscount : 0
             * FActQty : 0
             * FSalUnitID : {"FNumber":""}
             * FSALUNITQTY : 0
             * FSALBASEQTY : 0
             * FPRICEBASEQTY : 0
             * FProjectNo :
             * FOUTCONTROL : false
             * FRepairQty : 0
             * FIsCreateProDoc :
             * FEOwnerSupplierId : {"FNUMBER":""}
             * FIsOverLegalOrg : false
             * FESettleCustomerId : {"FNUMBER":""}
             * FPriceListEntry : {"FNUMBER":""}
             * FARNOTJOINQTY : 0
             * FQmEntryID : 0
             * FConvertEntryID : 0
             * FSOEntryId : 0
             * FThirdEntryId :
             * FBeforeDisPriceQty : 0
             * FSignQty : 0
             * FTaxDetailSubEntity : [{"FDetailID":"0","FTaxRate":"0"}]
             * FSerialSubEntity : [{"FDetailID":"0","FSerialNo":"","FSerialNote":""}]
             */

            private String FENTRYID;
            private String FRowType;
            private FCustMatIDBean FCustMatID;
            private FMaterialIDBean FMaterialID;
            private FAuxPropIdBean FAuxPropId;
            private FUnitIDBean FUnitID;
            private String FInventoryQty;
            private FParentMatIdBean FParentMatId;
            private String FRealQty;
            private String FDisPriceQty;
            private String FPrice;
            private String FTaxPrice;
            private boolean FIsFree;
            private FBomIDBean FBomID;
            private String FProduceDate;
            private String FOwnerTypeID;
            private FOwnerIDBean FOwnerID;
            private FLotBean FLot;
            private String FExpiryDate;
            private FTaxCombinationBean FTaxCombination;
            private String FEntryTaxRate;
            private String FAuxUnitQty;
            private FExtAuxUnitIdBean FExtAuxUnitId;
            private String FExtAuxUnitQty;
            private FStockIDBean FStockID;
            private FStockLocIDBean FStockLocID;
            private FStockStatusIDBean FStockStatusID;
            private String FQualifyType;
            private String FMtoNo;
            private String FEntrynote;
            private String FDiscountRate;
            private String FPriceDiscount;
            private String FActQty;
            private FSalUnitIDBean FSalUnitID;
            private String FSALUNITQTY;
            private String FSALBASEQTY;
            private String FPRICEBASEQTY;
            private String FProjectNo;
            private String FOUTCONTROL;
            private String FRepairQty;
            private String FIsCreateProDoc;
            private FEOwnerSupplierIdBean FEOwnerSupplierId;
            private String FIsOverLegalOrg;
            private FESettleCustomerIdBean FESettleCustomerId;
            private FPriceListEntryBean FPriceListEntry;
            private String FARNOTJOINQTY;
            private String FQmEntryID;
            private String FConvertEntryID;
            private String FSOEntryId;
            private String FThirdEntryId;
            private String FBeforeDisPriceQty;
            private String FSignQty;
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

            public FCustMatIDBean getFCustMatID() {
                return FCustMatID;
            }

            public void setFCustMatID(FCustMatIDBean FCustMatID) {
                this.FCustMatID = FCustMatID;
            }

            public FMaterialIDBean getFMaterialID() {
                return FMaterialID;
            }

            public void setFMaterialID(FMaterialIDBean FMaterialID) {
                this.FMaterialID = FMaterialID;
            }

            public FAuxPropIdBean getFAuxPropId() {
                return FAuxPropId;
            }

            public void setFAuxPropId(FAuxPropIdBean FAuxPropId) {
                this.FAuxPropId = FAuxPropId;
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

            public FParentMatIdBean getFParentMatId() {
                return FParentMatId;
            }

            public void setFParentMatId(FParentMatIdBean FParentMatId) {
                this.FParentMatId = FParentMatId;
            }

            public String getFRealQty() {
                return FRealQty;
            }

            public void setFRealQty(String FRealQty) {
                this.FRealQty = FRealQty;
            }

            public String getFDisPriceQty() {
                return FDisPriceQty;
            }

            public void setFDisPriceQty(String FDisPriceQty) {
                this.FDisPriceQty = FDisPriceQty;
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

            public FBomIDBean getFBomID() {
                return FBomID;
            }

            public void setFBomID(FBomIDBean FBomID) {
                this.FBomID = FBomID;
            }

            public String getFProduceDate() {
                return FProduceDate;
            }

            public void setFProduceDate(String FProduceDate) {
                this.FProduceDate = FProduceDate;
            }

            public String getFOwnerTypeID() {
                return FOwnerTypeID;
            }

            public void setFOwnerTypeID(String FOwnerTypeID) {
                this.FOwnerTypeID = FOwnerTypeID;
            }

            public FOwnerIDBean getFOwnerID() {
                return FOwnerID;
            }

            public void setFOwnerID(FOwnerIDBean FOwnerID) {
                this.FOwnerID = FOwnerID;
            }

            public FLotBean getFLot() {
                return FLot;
            }

            public void setFLot(FLotBean FLot) {
                this.FLot = FLot;
            }

            public String getFExpiryDate() {
                return FExpiryDate;
            }

            public void setFExpiryDate(String FExpiryDate) {
                this.FExpiryDate = FExpiryDate;
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

            public FStockIDBean getFStockID() {
                return FStockID;
            }

            public void setFStockID(FStockIDBean FStockID) {
                this.FStockID = FStockID;
            }

            public FStockLocIDBean getFStockLocID() {
                return FStockLocID;
            }

            public void setFStockLocID(FStockLocIDBean FStockLocID) {
                this.FStockLocID = FStockLocID;
            }

            public FStockStatusIDBean getFStockStatusID() {
                return FStockStatusID;
            }

            public void setFStockStatusID(FStockStatusIDBean FStockStatusID) {
                this.FStockStatusID = FStockStatusID;
            }

            public String getFQualifyType() {
                return FQualifyType;
            }

            public void setFQualifyType(String FQualifyType) {
                this.FQualifyType = FQualifyType;
            }

            public String getFMtoNo() {
                return FMtoNo;
            }

            public void setFMtoNo(String FMtoNo) {
                this.FMtoNo = FMtoNo;
            }

            public String getFEntrynote() {
                return FEntrynote;
            }

            public void setFEntrynote(String FEntrynote) {
                this.FEntrynote = FEntrynote;
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

            public String getFActQty() {
                return FActQty;
            }

            public void setFActQty(String FActQty) {
                this.FActQty = FActQty;
            }

            public FSalUnitIDBean getFSalUnitID() {
                return FSalUnitID;
            }

            public void setFSalUnitID(FSalUnitIDBean FSalUnitID) {
                this.FSalUnitID = FSalUnitID;
            }

            public String getFSALUNITQTY() {
                return FSALUNITQTY;
            }

            public void setFSALUNITQTY(String FSALUNITQTY) {
                this.FSALUNITQTY = FSALUNITQTY;
            }

            public String getFSALBASEQTY() {
                return FSALBASEQTY;
            }

            public void setFSALBASEQTY(String FSALBASEQTY) {
                this.FSALBASEQTY = FSALBASEQTY;
            }

            public String getFPRICEBASEQTY() {
                return FPRICEBASEQTY;
            }

            public void setFPRICEBASEQTY(String FPRICEBASEQTY) {
                this.FPRICEBASEQTY = FPRICEBASEQTY;
            }

            public String getFProjectNo() {
                return FProjectNo;
            }

            public void setFProjectNo(String FProjectNo) {
                this.FProjectNo = FProjectNo;
            }

            public String getFOUTCONTROL() {
                return FOUTCONTROL;
            }

            public void setFOUTCONTROL(String FOUTCONTROL) {
                this.FOUTCONTROL = FOUTCONTROL;
            }

            public String getFRepairQty() {
                return FRepairQty;
            }

            public void setFRepairQty(String FRepairQty) {
                this.FRepairQty = FRepairQty;
            }

            public String getFIsCreateProDoc() {
                return FIsCreateProDoc;
            }

            public void setFIsCreateProDoc(String FIsCreateProDoc) {
                this.FIsCreateProDoc = FIsCreateProDoc;
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

            public String getFQmEntryID() {
                return FQmEntryID;
            }

            public void setFQmEntryID(String FQmEntryID) {
                this.FQmEntryID = FQmEntryID;
            }

            public String getFConvertEntryID() {
                return FConvertEntryID;
            }

            public void setFConvertEntryID(String FConvertEntryID) {
                this.FConvertEntryID = FConvertEntryID;
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

            public String getFBeforeDisPriceQty() {
                return FBeforeDisPriceQty;
            }

            public void setFBeforeDisPriceQty(String FBeforeDisPriceQty) {
                this.FBeforeDisPriceQty = FBeforeDisPriceQty;
            }

            public String getFSignQty() {
                return FSignQty;
            }

            public void setFSignQty(String FSignQty) {
                this.FSignQty = FSignQty;
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

            public static class FCustMatIDBean {
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

            public static class FMaterialIDBean {
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

            public static class FAuxPropIdBean {
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

            public static class FBomIDBean {
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

            public static class FOwnerIDBean {
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

            public static class FStockIDBean {
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

            public static class FStockLocIDBean {
                /**
                 * FSTOCKLOCID__FF100003 : {"FNumber":""}
                 * FSTOCKLOCID__FF100004 : {"FNumber":""}
                 * FSTOCKLOCID__FF100012 : {"FNumber":""}
                 * FSTOCKLOCID__FF100013 : {"FNumber":""}
                 * FSTOCKLOCID__FF100014 : {"FNumber":""}
                 * FSTOCKLOCID__FF100015 : {"FNumber":""}
                 * FSTOCKLOCID__FF100016 : {"FNumber":""}
                 * FSTOCKLOCID__FF100017 : {"FNumber":""}
                 * FSTOCKLOCID__FF100018 : {"FNumber":""}
                 * FSTOCKLOCID__FF100019 : {"FNumber":""}
                 */

                private FSTOCKLOCIDFF100003Bean FSTOCKLOCID__FF100003;
                private FSTOCKLOCIDFF100004Bean FSTOCKLOCID__FF100004;
                private FSTOCKLOCIDFF100012Bean FSTOCKLOCID__FF100012;
                private FSTOCKLOCIDFF100013Bean FSTOCKLOCID__FF100013;
                private FSTOCKLOCIDFF100014Bean FSTOCKLOCID__FF100014;
                private FSTOCKLOCIDFF100015Bean FSTOCKLOCID__FF100015;
                private FSTOCKLOCIDFF100016Bean FSTOCKLOCID__FF100016;
                private FSTOCKLOCIDFF100017Bean FSTOCKLOCID__FF100017;
                private FSTOCKLOCIDFF100018Bean FSTOCKLOCID__FF100018;
                private FSTOCKLOCIDFF100019Bean FSTOCKLOCID__FF100019;

                public FSTOCKLOCIDFF100003Bean getFSTOCKLOCID__FF100003() {
                    return FSTOCKLOCID__FF100003;
                }

                public void setFSTOCKLOCID__FF100003(FSTOCKLOCIDFF100003Bean FSTOCKLOCID__FF100003) {
                    this.FSTOCKLOCID__FF100003 = FSTOCKLOCID__FF100003;
                }

                public FSTOCKLOCIDFF100004Bean getFSTOCKLOCID__FF100004() {
                    return FSTOCKLOCID__FF100004;
                }

                public void setFSTOCKLOCID__FF100004(FSTOCKLOCIDFF100004Bean FSTOCKLOCID__FF100004) {
                    this.FSTOCKLOCID__FF100004 = FSTOCKLOCID__FF100004;
                }

                public FSTOCKLOCIDFF100012Bean getFSTOCKLOCID__FF100012() {
                    return FSTOCKLOCID__FF100012;
                }

                public void setFSTOCKLOCID__FF100012(FSTOCKLOCIDFF100012Bean FSTOCKLOCID__FF100012) {
                    this.FSTOCKLOCID__FF100012 = FSTOCKLOCID__FF100012;
                }

                public FSTOCKLOCIDFF100013Bean getFSTOCKLOCID__FF100013() {
                    return FSTOCKLOCID__FF100013;
                }

                public void setFSTOCKLOCID__FF100013(FSTOCKLOCIDFF100013Bean FSTOCKLOCID__FF100013) {
                    this.FSTOCKLOCID__FF100013 = FSTOCKLOCID__FF100013;
                }

                public FSTOCKLOCIDFF100014Bean getFSTOCKLOCID__FF100014() {
                    return FSTOCKLOCID__FF100014;
                }

                public void setFSTOCKLOCID__FF100014(FSTOCKLOCIDFF100014Bean FSTOCKLOCID__FF100014) {
                    this.FSTOCKLOCID__FF100014 = FSTOCKLOCID__FF100014;
                }

                public FSTOCKLOCIDFF100015Bean getFSTOCKLOCID__FF100015() {
                    return FSTOCKLOCID__FF100015;
                }

                public void setFSTOCKLOCID__FF100015(FSTOCKLOCIDFF100015Bean FSTOCKLOCID__FF100015) {
                    this.FSTOCKLOCID__FF100015 = FSTOCKLOCID__FF100015;
                }

                public FSTOCKLOCIDFF100016Bean getFSTOCKLOCID__FF100016() {
                    return FSTOCKLOCID__FF100016;
                }

                public void setFSTOCKLOCID__FF100016(FSTOCKLOCIDFF100016Bean FSTOCKLOCID__FF100016) {
                    this.FSTOCKLOCID__FF100016 = FSTOCKLOCID__FF100016;
                }

                public FSTOCKLOCIDFF100017Bean getFSTOCKLOCID__FF100017() {
                    return FSTOCKLOCID__FF100017;
                }

                public void setFSTOCKLOCID__FF100017(FSTOCKLOCIDFF100017Bean FSTOCKLOCID__FF100017) {
                    this.FSTOCKLOCID__FF100017 = FSTOCKLOCID__FF100017;
                }

                public FSTOCKLOCIDFF100018Bean getFSTOCKLOCID__FF100018() {
                    return FSTOCKLOCID__FF100018;
                }

                public void setFSTOCKLOCID__FF100018(FSTOCKLOCIDFF100018Bean FSTOCKLOCID__FF100018) {
                    this.FSTOCKLOCID__FF100018 = FSTOCKLOCID__FF100018;
                }

                public FSTOCKLOCIDFF100019Bean getFSTOCKLOCID__FF100019() {
                    return FSTOCKLOCID__FF100019;
                }

                public void setFSTOCKLOCID__FF100019(FSTOCKLOCIDFF100019Bean FSTOCKLOCID__FF100019) {
                    this.FSTOCKLOCID__FF100019 = FSTOCKLOCID__FF100019;
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

                public static class FSTOCKLOCIDFF100004Bean {
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

                public static class FSTOCKLOCIDFF100013Bean {
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

                public static class FSTOCKLOCIDFF100014Bean {
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

                public static class FSTOCKLOCIDFF100015Bean {
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

                public static class FSTOCKLOCIDFF100016Bean {
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

                public static class FSTOCKLOCIDFF100017Bean {
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

                public static class FSTOCKLOCIDFF100018Bean {
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

                public static class FSTOCKLOCIDFF100019Bean {
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

            public static class FStockStatusIDBean {
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

        public static class FOutStockTraceBean {
            /**
             * FEntryID : 0
             * FLogComId : {"FCODE":""}
             * FCarryBillNo :
             * FDelTime : 1900-01-01
             * FTraceStatus :
             * FOutStockTraceDetail : [{"FDetailID":"0","FTraceTime":"","FTraceDetail":""}]
             */

            private String FEntryID;
            private FLogComIdBean FLogComId;
            private String FCarryBillNo;
            private String FDelTime;
            private String FTraceStatus;
            private List<FOutStockTraceDetailBean> FOutStockTraceDetail;

            public String getFEntryID() {
                return FEntryID;
            }

            public void setFEntryID(String FEntryID) {
                this.FEntryID = FEntryID;
            }

            public FLogComIdBean getFLogComId() {
                return FLogComId;
            }

            public void setFLogComId(FLogComIdBean FLogComId) {
                this.FLogComId = FLogComId;
            }

            public String getFCarryBillNo() {
                return FCarryBillNo;
            }

            public void setFCarryBillNo(String FCarryBillNo) {
                this.FCarryBillNo = FCarryBillNo;
            }

            public String getFDelTime() {
                return FDelTime;
            }

            public void setFDelTime(String FDelTime) {
                this.FDelTime = FDelTime;
            }

            public String getFTraceStatus() {
                return FTraceStatus;
            }

            public void setFTraceStatus(String FTraceStatus) {
                this.FTraceStatus = FTraceStatus;
            }

            public List<FOutStockTraceDetailBean> getFOutStockTraceDetail() {
                return FOutStockTraceDetail;
            }

            public void setFOutStockTraceDetail(List<FOutStockTraceDetailBean> FOutStockTraceDetail) {
                this.FOutStockTraceDetail = FOutStockTraceDetail;
            }

            public static class FLogComIdBean {
                /**
                 * FCODE :
                 */

                private String FCODE;

                public String getFCODE() {
                    return FCODE;
                }

                public void setFCODE(String FCODE) {
                    this.FCODE = FCODE;
                }
            }

            public static class FOutStockTraceDetailBean {
                /**
                 * FDetailID : 0
                 * FTraceTime :
                 * FTraceDetail :
                 */

                private String FDetailID;
                private String FTraceTime;
                private String FTraceDetail;

                public String getFDetailID() {
                    return FDetailID;
                }

                public void setFDetailID(String FDetailID) {
                    this.FDetailID = FDetailID;
                }

                public String getFTraceTime() {
                    return FTraceTime;
                }

                public void setFTraceTime(String FTraceTime) {
                    this.FTraceTime = FTraceTime;
                }

                public String getFTraceDetail() {
                    return FTraceDetail;
                }

                public void setFTraceDetail(String FTraceDetail) {
                    this.FTraceDetail = FTraceDetail;
                }
            }
        }
    }
}
