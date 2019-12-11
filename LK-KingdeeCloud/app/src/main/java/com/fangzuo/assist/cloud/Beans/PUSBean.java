package com.fangzuo.assist.cloud.Beans;


import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.Dao.T_main;

import java.util.ArrayList;
import java.util.List;

public class PUSBean {

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
     * Model : {"FID":"0","FBillTypeID":{"FNUMBER":""},"FBillNo":"","FDate":"1900-01-01","FSupplierId":{"FNumber":""},"FPurchaseOrgId":{"FNumber":""},"FPurchaseDeptId":{"FNumber":""},"FPurchaserGroupId":{"FNumber":""},"FPurchaserId":{"FNumber":""},"FProviderId":{"FNumber":""},"FProviderContactId":{"FCONTACTNUMBER":""},"FProviderAddress":"","FSettleId":{"FNumber":""},"FChargeId":{"FNumber":""},"FConfirmerId":{"FUserID":""},"FConfirmDate":"1900-01-01","FCorrespondOrgId":{"FNumber":""},"FProviderContact":"","FIsModificationOperator":"false","F_QGWW_Base":{"FNUMBER":""},"FPOOrderFinance":{"FEntryId":"0","FSettleCurrId":{"FNumber":""},"FPayConditionId":{"FNumber":""},"FSettleModeId":{"FNumber":""},"FExchangeTypeId":{"FNumber":""},"FExchangeRate":"0","FPriceListId":{"FNumber":""},"FDiscountListId":{"FNumber":""},"FPriceTimePoint":"","FFOCUSSETTLEORGID":{"FNumber":""},"FIsIncludedTax":"false","FISPRICEEXCLUDETAX":"false","FLocalCurrId":{"FNumber":""},"FPayAdvanceBillId":{},"FPAYADVANCEAMOUNT":"0","FSupToOderExchangeBusRate":"0","FSEPSETTLE":"false"},"FPOOrderClause":[{"FEntryID":"0","FClauseId":{"FNumber":""},"FClauseDesc":""}],"FPOOrderEntry":[{"FEntryID":"0","FProductType":"","FMaterialId":{"FNumber":""},"FBomId":{"FNumber":""},"FMaterialDesc":"","FAuxPropId":{},"FUnitId":{"FNumber":""},"FQty":"0","FPriceUnitId":{"FNumber":""},"FPriceUnitQty":"0","FPriceBaseQty":"0","FDeliveryDate":"1900-01-01","FLocation":"","FLocationAddress":"","FPrice":"0","FTaxPrice":"0","FEntryDiscountRate":"0","FTaxCombination":{"FNumber":""},"FEntryTaxRate":"0","FRequireOrgId":{"FNumber":""},"FRequireDeptId":{"FNumber":""},"FRequireStaffId":{},"FReceiveOrgId":{"FNumber":""},"FReceiveDeptId":{"FNUMBER":""},"FEntrySettleOrgId":{"FNumber":""},"FGiveAway":"false","FEntryNote":"","FSupMatId":"","FSupMatName":"","FStockUnitID":{"FNumber":""},"FStockQty":"0","FStockBaseQty":"0","FLot":{"FNumber":""},"FSupplierLot":"","FProcesser":{"FNumber":""},"FDeliveryControl":"false","FTimeControl":"false","FDeliveryMaxQty":"0","FDeliveryMinQty":"0","FDeliveryBeforeDays":"0","FDeliveryDelayDays":"0","FDeliveryEarlyDate":"1900-01-01","FDeliveryLastDate":"1900-01-01","FPriceCoefficient":"0","FEntrySettleModeId":{"FNumber":""},"FConsumeSumQty":"0","FContractNo":"","FReqTraceNo":"","FMtoNo":"","FDEMANDTYPE":"","FDEMANDBILLNO":"","FDEMANDBILLENTRYSEQ":"0","FDEMANDBILLENTRYID":"0","FLocationId":{"FNumber":""},"FPlanConfirm":"false","FSalUnitID":{"FNumber":""},"FSalQty":"0","FSalJoinQty":"0","FBaseSalJoinQty":"0","FSetPriceUnitID":{"FNumber":""},"FInventoryQty":"0","FChargeProjectID":{"FNumber":""},"FCentSettleOrgId":{"FNumber":""},"FDispSettleOrgId":{"FNumber":""},"FGroup":"0","FDeliveryStockStatus":{"FNumber":""},"FMaxPrice":"0","FMinPrice":"0","FIsStock":"false","FBaseConsumeSumQty":"0","FSalBaseQty":"0","FSubOrgId":{"FNumber":""},"FEntryPayOrgId":{"FNumber":""},"FEntryDeliveryPlan":[{"FDetailId":"0","FDeliveryDate_Plan":"1900-01-01","FPlanQty":"0","FELocation":"","FELocationAddress":"","FSUPPLIERDELIVERYDATE":"1900-01-01","FPREARRIVALDATE":"1900-01-01","FTRLT":"0","FConfirmDeliQty":"0","FConfirmDeliDate":"1900-01-01","FConfirmInfo":"","FELocationId":{"FNumber":""}}],"FTaxDetailSubEntity":[{"FDetailID":"0","FTaxRateId":{"FNumber":""},"FTaxRate":"0"}]}],"FIinstallment":[{"FENTRYID":"0","FYFDATE":"1900-01-01","FYFRATIO":"0","FYFAMOUNT":"0","FISPREPAYMENT":"false","FRelBillNo":"","FInsPrepaidAmount":"0","FACTUALAMOUNT":"0","FPayJoinAmount":"0","FRemarks":"","FPayMaterialId":{"FNUMBER":""},"FMATERIALSEQ":"0","FPayPlanQty":"0","FPayPlanPrice":"0","FAppliedQty":"0","FActualPayQty":"0","FAPPLYAMOUNT":"0","FPURCHASEORDERNO":"","FOrderEntryId":"0","FinsPayAdvanceRate":"0","FInsPayAdvanceAmount":"0","FPAYPLANPRICEUNITID":{"FNumber":""},"FBasePriceUnit":{"FNumber":""},"FBasePayPlanQty":"0","FOrderActualPaySubEntity":[{"FDetailID":"0","FPAYBILLID":"0","FPAYBILLENTITYID":"0","FPOORDERID":"0","FAmount":"0","FPREAMOUNT":"0","FPPSettleOrgId":{"FNumber":""},"FAPPLYBILLNO":"","FPREPAYBillNo":"","FPAPPLYAMOUNT":"0","FPPayJoinAmount":"0"}]}]}
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
         * FSupplierId : {"FNumber":""}
         * FPurchaseOrgId : {"FNumber":""}
         * FPurchaseDeptId : {"FNumber":""}
         * FPurchaserGroupId : {"FNumber":""}
         * FPurchaserId : {"FNumber":""}
         * FProviderId : {"FNumber":""}
         * FProviderContactId : {"FCONTACTNUMBER":""}
         * FProviderAddress :
         * FSettleId : {"FNumber":""}
         * FChargeId : {"FNumber":""}
         * FConfirmerId : {"FUserID":""}
         * FConfirmDate : 1900-01-01
         * FCorrespondOrgId : {"FNumber":""}
         * FProviderContact :
         * FIsModificationOperator : false
         * F_QGWW_Base : {"FNUMBER":""}
         * FPOOrderFinance : {"FEntryId":"0","FSettleCurrId":{"FNumber":""},"FPayConditionId":{"FNumber":""},"FSettleModeId":{"FNumber":""},"FExchangeTypeId":{"FNumber":""},"FExchangeRate":"0","FPriceListId":{"FNumber":""},"FDiscountListId":{"FNumber":""},"FPriceTimePoint":"","FFOCUSSETTLEORGID":{"FNumber":""},"FIsIncludedTax":"false","FISPRICEEXCLUDETAX":"false","FLocalCurrId":{"FNumber":""},"FPayAdvanceBillId":{},"FPAYADVANCEAMOUNT":"0","FSupToOderExchangeBusRate":"0","FSEPSETTLE":"false"}
         * FPOOrderClause : [{"FEntryID":"0","FClauseId":{"FNumber":""},"FClauseDesc":""}]
         * FPOOrderEntry : [{"FEntryID":"0","FProductType":"","FMaterialId":{"FNumber":""},"FBomId":{"FNumber":""},"FMaterialDesc":"","FAuxPropId":{},"FUnitId":{"FNumber":""},"FQty":"0","FPriceUnitId":{"FNumber":""},"FPriceUnitQty":"0","FPriceBaseQty":"0","FDeliveryDate":"1900-01-01","FLocation":"","FLocationAddress":"","FPrice":"0","FTaxPrice":"0","FEntryDiscountRate":"0","FTaxCombination":{"FNumber":""},"FEntryTaxRate":"0","FRequireOrgId":{"FNumber":""},"FRequireDeptId":{"FNumber":""},"FRequireStaffId":{},"FReceiveOrgId":{"FNumber":""},"FReceiveDeptId":{"FNUMBER":""},"FEntrySettleOrgId":{"FNumber":""},"FGiveAway":"false","FEntryNote":"","FSupMatId":"","FSupMatName":"","FStockUnitID":{"FNumber":""},"FStockQty":"0","FStockBaseQty":"0","FLot":{"FNumber":""},"FSupplierLot":"","FProcesser":{"FNumber":""},"FDeliveryControl":"false","FTimeControl":"false","FDeliveryMaxQty":"0","FDeliveryMinQty":"0","FDeliveryBeforeDays":"0","FDeliveryDelayDays":"0","FDeliveryEarlyDate":"1900-01-01","FDeliveryLastDate":"1900-01-01","FPriceCoefficient":"0","FEntrySettleModeId":{"FNumber":""},"FConsumeSumQty":"0","FContractNo":"","FReqTraceNo":"","FMtoNo":"","FDEMANDTYPE":"","FDEMANDBILLNO":"","FDEMANDBILLENTRYSEQ":"0","FDEMANDBILLENTRYID":"0","FLocationId":{"FNumber":""},"FPlanConfirm":"false","FSalUnitID":{"FNumber":""},"FSalQty":"0","FSalJoinQty":"0","FBaseSalJoinQty":"0","FSetPriceUnitID":{"FNumber":""},"FInventoryQty":"0","FChargeProjectID":{"FNumber":""},"FCentSettleOrgId":{"FNumber":""},"FDispSettleOrgId":{"FNumber":""},"FGroup":"0","FDeliveryStockStatus":{"FNumber":""},"FMaxPrice":"0","FMinPrice":"0","FIsStock":"false","FBaseConsumeSumQty":"0","FSalBaseQty":"0","FSubOrgId":{"FNumber":""},"FEntryPayOrgId":{"FNumber":""},"FEntryDeliveryPlan":[{"FDetailId":"0","FDeliveryDate_Plan":"1900-01-01","FPlanQty":"0","FELocation":"","FELocationAddress":"","FSUPPLIERDELIVERYDATE":"1900-01-01","FPREARRIVALDATE":"1900-01-01","FTRLT":"0","FConfirmDeliQty":"0","FConfirmDeliDate":"1900-01-01","FConfirmInfo":"","FELocationId":{"FNumber":""}}],"FTaxDetailSubEntity":[{"FDetailID":"0","FTaxRateId":{"FNumber":""},"FTaxRate":"0"}]}]
         * FIinstallment : [{"FENTRYID":"0","FYFDATE":"1900-01-01","FYFRATIO":"0","FYFAMOUNT":"0","FISPREPAYMENT":"false","FRelBillNo":"","FInsPrepaidAmount":"0","FACTUALAMOUNT":"0","FPayJoinAmount":"0","FRemarks":"","FPayMaterialId":{"FNUMBER":""},"FMATERIALSEQ":"0","FPayPlanQty":"0","FPayPlanPrice":"0","FAppliedQty":"0","FActualPayQty":"0","FAPPLYAMOUNT":"0","FPURCHASEORDERNO":"","FOrderEntryId":"0","FinsPayAdvanceRate":"0","FInsPayAdvanceAmount":"0","FPAYPLANPRICEUNITID":{"FNumber":""},"FBasePriceUnit":{"FNumber":""},"FBasePayPlanQty":"0","FOrderActualPaySubEntity":[{"FDetailID":"0","FPAYBILLID":"0","FPAYBILLENTITYID":"0","FPOORDERID":"0","FAmount":"0","FPREAMOUNT":"0","FPPSettleOrgId":{"FNumber":""},"FAPPLYBILLNO":"","FPREPAYBillNo":"","FPAPPLYAMOUNT":"0","FPPayJoinAmount":"0"}]}]
         */

        private String FID;
        private FBillTypeIDBean FBillTypeID;
        private String FBillNo;
        private String FDate;
        private FSupplierIdBean FSupplierId;
        private FPurchaseOrgIdBean FPurchaseOrgId;
        private FPurchaseDeptIdBean FPurchaseDeptId;
        private FPurchaserGroupIdBean FPurchaserGroupId;
        private FPurchaserIdBean FPurchaserId;
        private FProviderIdBean FProviderId;
        private FProviderContactIdBean FProviderContactId;
        private String FProviderAddress;
        private FSettleIdBean FSettleId;
        private FChargeIdBean FChargeId;
        private FConfirmerIdBean FConfirmerId;
        private String FConfirmDate;
        private FCorrespondOrgIdBean FCorrespondOrgId;
        private String FProviderContact;
        private String FIsModificationOperator;
        private FQGWWBaseBean F_QGWW_Base;
        private FPOOrderFinanceBean FPOOrderFinance;
        private List<FPOOrderClauseBean> FPOOrderClause;
        private List<FPOOrderEntryBean> FPOOrderEntry;
        private List<FIinstallmentBean> FIinstallment;

        public void setModelBean(T_main main, List<T_Detail> beans){
            //表头设置-------------------------------------------------------------
            this.FBillNo ="";
            this.FPurchaseOrgId=new PUSBean.ModelBean.FPurchaseOrgIdBean();    //采购组织
            this.FPurchaseOrgId.FNumber=main.FPurchaseOrgId;    //采购组织
            this.FPurchaseDeptId = new PUSBean.ModelBean.FPurchaseDeptIdBean();//采购部门
            this.FPurchaseDeptId.FNumber=main.FPurchaseDeptId;//采购部门
            this.FPurchaserId = new PUSBean.ModelBean.FPurchaserIdBean();       //采购员
            this.FPurchaserId.FNumber=main.FPurchaserId;       //采购员
            this.FBillTypeID=new PUSBean.ModelBean.FBillTypeIDBean();//单据类型
            this.FBillTypeID.FNUMBER=main.FBillTypeID;//单据类型
//            this.FOwnerTypeIdHead = main.FOwnerTypeIdHead;//货主类型
//            this.FOwnerIdHead=new PUSBean.ModelBean.FOwnerIdHeadBean(main.FOwnerIdHead);//货主
            this.FDate = main.FDate;//入库日期
            this.FSupplierId=new PUSBean.ModelBean.FSupplierIdBean();//供应商
            this.FSupplierId.FNumber=main.FSupplierId;//供应商
//            this.FInStockFin=new FInStockFinBean();//一个大类
//            this.FPriceTimePoint= main.FPriceTimePoint;         //定价时点
            this.FPOOrderFinance= new PUSBean.ModelBean.FPOOrderFinanceBean();         //定价时点
            this.FPOOrderFinance.FPriceTimePoint= new PUSBean.ModelBean.FPOOrderFinanceBean.FPriceTimePointBean();         //定价时点
            this.FPOOrderFinance.FPriceTimePoint.FNumber= main.FPriceTimePoint;         //定价时点
//            this.FInStockFin.FSettleOrgId=new FInStockFinBean.FSettleOrgIdBean(main.FSettleOrgId);//结算组织
//            this.FInStockFin.FSettleCurrId=new FInStockFinBean.FSettleCurrIdBean(main.FSettleCurrId);//结算币别
            //----------------------------------------------------------------
            //表体设置----------------------------------------------------------------
            this.FPOOrderEntry=new ArrayList<>();//一个大类数组
            for (int i = 0; i < beans.size(); i++) {
                FPOOrderEntryBean bean1=new FPOOrderEntryBean();//一个大类
                bean1.FMaterialId = new FPOOrderEntryBean.FMaterialIdBean();//物料编码
                bean1.FMaterialId.FNumber = beans.get(i).FMaterialId;//物料编码
//                bean1.FStockId=new FPOOrderEntryBean.FStockIdBean(beans.get(i).FStorageId);//仓库
                bean1.FLot=new FPOOrderEntryBean.FLotBean();//批号
                bean1.FLot.FNumber=beans.get(i).FBatch;//批号
                bean1.FQty=beans.get(i).FRealQty;         //计价数量
                bean1.FPriceUnitId = new FPOOrderEntryBean.FPriceUnitIdBean();//计价单位
                bean1.FPriceUnitId.FNumber = beans.get(i).FPriceUnitID;//计价单位
                bean1.FUnitId = new FPOOrderEntryBean.FUnitIdBean();//采购单位
                bean1.FUnitId.FNumber =beans.get(i).FUnitID;//库存单位
                bean1.FGiveAway = beans.get(i).FIsFree;//库存单位
                this.FPOOrderEntry.add(bean1);//添加进数组
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

        public FSupplierIdBean getFSupplierId() {
            return FSupplierId;
        }

        public void setFSupplierId(FSupplierIdBean FSupplierId) {
            this.FSupplierId = FSupplierId;
        }

        public FPurchaseOrgIdBean getFPurchaseOrgId() {
            return FPurchaseOrgId;
        }

        public void setFPurchaseOrgId(FPurchaseOrgIdBean FPurchaseOrgId) {
            this.FPurchaseOrgId = FPurchaseOrgId;
        }

        public FPurchaseDeptIdBean getFPurchaseDeptId() {
            return FPurchaseDeptId;
        }

        public void setFPurchaseDeptId(FPurchaseDeptIdBean FPurchaseDeptId) {
            this.FPurchaseDeptId = FPurchaseDeptId;
        }

        public FPurchaserGroupIdBean getFPurchaserGroupId() {
            return FPurchaserGroupId;
        }

        public void setFPurchaserGroupId(FPurchaserGroupIdBean FPurchaserGroupId) {
            this.FPurchaserGroupId = FPurchaserGroupId;
        }

        public FPurchaserIdBean getFPurchaserId() {
            return FPurchaserId;
        }

        public void setFPurchaserId(FPurchaserIdBean FPurchaserId) {
            this.FPurchaserId = FPurchaserId;
        }

        public FProviderIdBean getFProviderId() {
            return FProviderId;
        }

        public void setFProviderId(FProviderIdBean FProviderId) {
            this.FProviderId = FProviderId;
        }

        public FProviderContactIdBean getFProviderContactId() {
            return FProviderContactId;
        }

        public void setFProviderContactId(FProviderContactIdBean FProviderContactId) {
            this.FProviderContactId = FProviderContactId;
        }

        public String getFProviderAddress() {
            return FProviderAddress;
        }

        public void setFProviderAddress(String FProviderAddress) {
            this.FProviderAddress = FProviderAddress;
        }

        public FSettleIdBean getFSettleId() {
            return FSettleId;
        }

        public void setFSettleId(FSettleIdBean FSettleId) {
            this.FSettleId = FSettleId;
        }

        public FChargeIdBean getFChargeId() {
            return FChargeId;
        }

        public void setFChargeId(FChargeIdBean FChargeId) {
            this.FChargeId = FChargeId;
        }

        public FConfirmerIdBean getFConfirmerId() {
            return FConfirmerId;
        }

        public void setFConfirmerId(FConfirmerIdBean FConfirmerId) {
            this.FConfirmerId = FConfirmerId;
        }

        public String getFConfirmDate() {
            return FConfirmDate;
        }

        public void setFConfirmDate(String FConfirmDate) {
            this.FConfirmDate = FConfirmDate;
        }

        public FCorrespondOrgIdBean getFCorrespondOrgId() {
            return FCorrespondOrgId;
        }

        public void setFCorrespondOrgId(FCorrespondOrgIdBean FCorrespondOrgId) {
            this.FCorrespondOrgId = FCorrespondOrgId;
        }

        public String getFProviderContact() {
            return FProviderContact;
        }

        public void setFProviderContact(String FProviderContact) {
            this.FProviderContact = FProviderContact;
        }

        public String getFIsModificationOperator() {
            return FIsModificationOperator;
        }

        public void setFIsModificationOperator(String FIsModificationOperator) {
            this.FIsModificationOperator = FIsModificationOperator;
        }

        public FQGWWBaseBean getF_QGWW_Base() {
            return F_QGWW_Base;
        }

        public void setF_QGWW_Base(FQGWWBaseBean F_QGWW_Base) {
            this.F_QGWW_Base = F_QGWW_Base;
        }

        public FPOOrderFinanceBean getFPOOrderFinance() {
            return FPOOrderFinance;
        }

        public void setFPOOrderFinance(FPOOrderFinanceBean FPOOrderFinance) {
            this.FPOOrderFinance = FPOOrderFinance;
        }

        public List<FPOOrderClauseBean> getFPOOrderClause() {
            return FPOOrderClause;
        }

        public void setFPOOrderClause(List<FPOOrderClauseBean> FPOOrderClause) {
            this.FPOOrderClause = FPOOrderClause;
        }

        public List<FPOOrderEntryBean> getFPOOrderEntry() {
            return FPOOrderEntry;
        }

        public void setFPOOrderEntry(List<FPOOrderEntryBean> FPOOrderEntry) {
            this.FPOOrderEntry = FPOOrderEntry;
        }

        public List<FIinstallmentBean> getFIinstallment() {
            return FIinstallment;
        }

        public void setFIinstallment(List<FIinstallmentBean> FIinstallment) {
            this.FIinstallment = FIinstallment;
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

        public static class FSupplierIdBean {
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

        public static class FPurchaseOrgIdBean {
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

        public static class FPurchaseDeptIdBean {
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

        public static class FPurchaserGroupIdBean {
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

        public static class FPurchaserIdBean {
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

        public static class FProviderIdBean {
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

        public static class FProviderContactIdBean {
            /**
             * FCONTACTNUMBER :
             */

            private String FCONTACTNUMBER;

            public String getFCONTACTNUMBER() {
                return FCONTACTNUMBER;
            }

            public void setFCONTACTNUMBER(String FCONTACTNUMBER) {
                this.FCONTACTNUMBER = FCONTACTNUMBER;
            }
        }

        public static class FSettleIdBean {
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

        public static class FChargeIdBean {
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

        public static class FConfirmerIdBean {
            /**
             * FUserID :
             */

            private String FUserID;

            public String getFUserID() {
                return FUserID;
            }

            public void setFUserID(String FUserID) {
                this.FUserID = FUserID;
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

        public static class FQGWWBaseBean {
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

        public static class FPOOrderFinanceBean {
            /**
             * FEntryId : 0
             * FSettleCurrId : {"FNumber":""}
             * FPayConditionId : {"FNumber":""}
             * FSettleModeId : {"FNumber":""}
             * FExchangeTypeId : {"FNumber":""}
             * FExchangeRate : 0
             * FPriceListId : {"FNumber":""}
             * FDiscountListId : {"FNumber":""}
             * FPriceTimePoint :
             * FFOCUSSETTLEORGID : {"FNumber":""}
             * FIsIncludedTax : false
             * FISPRICEEXCLUDETAX : false
             * FLocalCurrId : {"FNumber":""}
             * FPayAdvanceBillId : {}
             * FPAYADVANCEAMOUNT : 0
             * FSupToOderExchangeBusRate : 0
             * FSEPSETTLE : false
             */

            private String FEntryId;
            private FSettleCurrIdBean FSettleCurrId;
            private FPayConditionIdBean FPayConditionId;
            private FSettleModeIdBean FSettleModeId;
            private FExchangeTypeIdBean FExchangeTypeId;
            private String FExchangeRate;
            private FPriceListIdBean FPriceListId;
            private FDiscountListIdBean FDiscountListId;
            private FPriceTimePointBean FPriceTimePoint;
            private FFOCUSSETTLEORGIDBean FFOCUSSETTLEORGID;
            private String FIsIncludedTax;
            private String FISPRICEEXCLUDETAX;
            private FLocalCurrIdBean FLocalCurrId;
            private FPayAdvanceBillIdBean FPayAdvanceBillId;
            private String FPAYADVANCEAMOUNT;
            private String FSupToOderExchangeBusRate;
            private String FSEPSETTLE;

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

            public FPayConditionIdBean getFPayConditionId() {
                return FPayConditionId;
            }

            public void setFPayConditionId(FPayConditionIdBean FPayConditionId) {
                this.FPayConditionId = FPayConditionId;
            }

            public FSettleModeIdBean getFSettleModeId() {
                return FSettleModeId;
            }

            public void setFSettleModeId(FSettleModeIdBean FSettleModeId) {
                this.FSettleModeId = FSettleModeId;
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

            public FPriceTimePointBean getFPriceTimePoint() {
                return FPriceTimePoint;
            }

            public void setFPriceTimePoint(FPriceTimePointBean FPriceTimePoint) {
                this.FPriceTimePoint = FPriceTimePoint;
            }

            public FFOCUSSETTLEORGIDBean getFFOCUSSETTLEORGID() {
                return FFOCUSSETTLEORGID;
            }

            public void setFFOCUSSETTLEORGID(FFOCUSSETTLEORGIDBean FFOCUSSETTLEORGID) {
                this.FFOCUSSETTLEORGID = FFOCUSSETTLEORGID;
            }

            public String getFIsIncludedTax() {
                return FIsIncludedTax;
            }

            public void setFIsIncludedTax(String FIsIncludedTax) {
                this.FIsIncludedTax = FIsIncludedTax;
            }

            public String getFISPRICEEXCLUDETAX() {
                return FISPRICEEXCLUDETAX;
            }

            public void setFISPRICEEXCLUDETAX(String FISPRICEEXCLUDETAX) {
                this.FISPRICEEXCLUDETAX = FISPRICEEXCLUDETAX;
            }

            public FLocalCurrIdBean getFLocalCurrId() {
                return FLocalCurrId;
            }

            public void setFLocalCurrId(FLocalCurrIdBean FLocalCurrId) {
                this.FLocalCurrId = FLocalCurrId;
            }

            public FPayAdvanceBillIdBean getFPayAdvanceBillId() {
                return FPayAdvanceBillId;
            }

            public void setFPayAdvanceBillId(FPayAdvanceBillIdBean FPayAdvanceBillId) {
                this.FPayAdvanceBillId = FPayAdvanceBillId;
            }

            public String getFPAYADVANCEAMOUNT() {
                return FPAYADVANCEAMOUNT;
            }

            public void setFPAYADVANCEAMOUNT(String FPAYADVANCEAMOUNT) {
                this.FPAYADVANCEAMOUNT = FPAYADVANCEAMOUNT;
            }

            public String getFSupToOderExchangeBusRate() {
                return FSupToOderExchangeBusRate;
            }

            public void setFSupToOderExchangeBusRate(String FSupToOderExchangeBusRate) {
                this.FSupToOderExchangeBusRate = FSupToOderExchangeBusRate;
            }

            public String getFSEPSETTLE() {
                return FSEPSETTLE;
            }

            public void setFSEPSETTLE(String FSEPSETTLE) {
                this.FSEPSETTLE = FSEPSETTLE;
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

            public static class FPayConditionIdBean {
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

            public static class FSettleModeIdBean {
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
            public static class FPriceTimePointBean {
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

            public static class FFOCUSSETTLEORGIDBean {
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

            public static class FPayAdvanceBillIdBean {
            }
        }

        public static class FPOOrderClauseBean {
            /**
             * FEntryID : 0
             * FClauseId : {"FNumber":""}
             * FClauseDesc :
             */

            private String FEntryID;
            private FClauseIdBean FClauseId;
            private String FClauseDesc;

            public String getFEntryID() {
                return FEntryID;
            }

            public void setFEntryID(String FEntryID) {
                this.FEntryID = FEntryID;
            }

            public FClauseIdBean getFClauseId() {
                return FClauseId;
            }

            public void setFClauseId(FClauseIdBean FClauseId) {
                this.FClauseId = FClauseId;
            }

            public String getFClauseDesc() {
                return FClauseDesc;
            }

            public void setFClauseDesc(String FClauseDesc) {
                this.FClauseDesc = FClauseDesc;
            }

            public static class FClauseIdBean {
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

        public static class FPOOrderEntryBean {
            /**
             * FEntryID : 0
             * FProductType :
             * FMaterialId : {"FNumber":""}
             * FBomId : {"FNumber":""}
             * FMaterialDesc :
             * FAuxPropId : {}
             * FUnitId : {"FNumber":""}
             * FQty : 0
             * FPriceUnitId : {"FNumber":""}
             * FPriceUnitQty : 0
             * FPriceBaseQty : 0
             * FDeliveryDate : 1900-01-01
             * FLocation :
             * FLocationAddress :
             * FPrice : 0
             * FTaxPrice : 0
             * FEntryDiscountRate : 0
             * FTaxCombination : {"FNumber":""}
             * FEntryTaxRate : 0
             * FRequireOrgId : {"FNumber":""}
             * FRequireDeptId : {"FNumber":""}
             * FRequireStaffId : {}
             * FReceiveOrgId : {"FNumber":""}
             * FReceiveDeptId : {"FNUMBER":""}
             * FEntrySettleOrgId : {"FNumber":""}
             * FGiveAway : false
             * FEntryNote :
             * FSupMatId :
             * FSupMatName :
             * FStockUnitID : {"FNumber":""}
             * FStockQty : 0
             * FStockBaseQty : 0
             * FLot : {"FNumber":""}
             * FSupplierLot :
             * FProcesser : {"FNumber":""}
             * FDeliveryControl : false
             * FTimeControl : false
             * FDeliveryMaxQty : 0
             * FDeliveryMinQty : 0
             * FDeliveryBeforeDays : 0
             * FDeliveryDelayDays : 0
             * FDeliveryEarlyDate : 1900-01-01
             * FDeliveryLastDate : 1900-01-01
             * FPriceCoefficient : 0
             * FEntrySettleModeId : {"FNumber":""}
             * FConsumeSumQty : 0
             * FContractNo :
             * FReqTraceNo :
             * FMtoNo :
             * FDEMANDTYPE :
             * FDEMANDBILLNO :
             * FDEMANDBILLENTRYSEQ : 0
             * FDEMANDBILLENTRYID : 0
             * FLocationId : {"FNumber":""}
             * FPlanConfirm : false
             * FSalUnitID : {"FNumber":""}
             * FSalQty : 0
             * FSalJoinQty : 0
             * FBaseSalJoinQty : 0
             * FSetPriceUnitID : {"FNumber":""}
             * FInventoryQty : 0
             * FChargeProjectID : {"FNumber":""}
             * FCentSettleOrgId : {"FNumber":""}
             * FDispSettleOrgId : {"FNumber":""}
             * FGroup : 0
             * FDeliveryStockStatus : {"FNumber":""}
             * FMaxPrice : 0
             * FMinPrice : 0
             * FIsStock : false
             * FBaseConsumeSumQty : 0
             * FSalBaseQty : 0
             * FSubOrgId : {"FNumber":""}
             * FEntryPayOrgId : {"FNumber":""}
             * FEntryDeliveryPlan : [{"FDetailId":"0","FDeliveryDate_Plan":"1900-01-01","FPlanQty":"0","FELocation":"","FELocationAddress":"","FSUPPLIERDELIVERYDATE":"1900-01-01","FPREARRIVALDATE":"1900-01-01","FTRLT":"0","FConfirmDeliQty":"0","FConfirmDeliDate":"1900-01-01","FConfirmInfo":"","FELocationId":{"FNumber":""}}]
             * FTaxDetailSubEntity : [{"FDetailID":"0","FTaxRateId":{"FNumber":""},"FTaxRate":"0"}]
             */

            private String FEntryID;
            private String FProductType;
            private FMaterialIdBean FMaterialId;
            private FBomIdBean FBomId;
            private String FMaterialDesc;
            private FAuxPropIdBean FAuxPropId;
            private FUnitIdBean FUnitId;
            private String FQty;
            private FPriceUnitIdBean FPriceUnitId;
            private String FPriceUnitQty;
            private String FPriceBaseQty;
            private String FDeliveryDate;
            private String FLocation;
            private String FLocationAddress;
            private String FPrice;
            private String FTaxPrice;
            private String FEntryDiscountRate;
            private FTaxCombinationBean FTaxCombination;
            private String FEntryTaxRate;
            private FRequireOrgIdBean FRequireOrgId;
            private FRequireDeptIdBean FRequireDeptId;
            private FRequireStaffIdBean FRequireStaffId;
            private FReceiveOrgIdBean FReceiveOrgId;
            private FReceiveDeptIdBean FReceiveDeptId;
            private FEntrySettleOrgIdBean FEntrySettleOrgId;
            private boolean FGiveAway;
            private String FEntryNote;
            private String FSupMatId;
            private String FSupMatName;
            private FStockUnitIDBean FStockUnitID;
            private String FStockQty;
            private String FStockBaseQty;
            private FLotBean FLot;
            private String FSupplierLot;
            private FProcesserBean FProcesser;
            private String FDeliveryControl;
            private String FTimeControl;
            private String FDeliveryMaxQty;
            private String FDeliveryMinQty;
            private String FDeliveryBeforeDays;
            private String FDeliveryDelayDays;
            private String FDeliveryEarlyDate;
            private String FDeliveryLastDate;
            private String FPriceCoefficient;
            private FEntrySettleModeIdBean FEntrySettleModeId;
            private String FConsumeSumQty;
            private String FContractNo;
            private String FReqTraceNo;
            private String FMtoNo;
            private String FDEMANDTYPE;
            private String FDEMANDBILLNO;
            private String FDEMANDBILLENTRYSEQ;
            private String FDEMANDBILLENTRYID;
            private FLocationIdBean FLocationId;
            private String FPlanConfirm;
            private FSalUnitIDBean FSalUnitID;
            private String FSalQty;
            private String FSalJoinQty;
            private String FBaseSalJoinQty;
            private FSetPriceUnitIDBean FSetPriceUnitID;
            private String FInventoryQty;
            private FChargeProjectIDBean FChargeProjectID;
            private FCentSettleOrgIdBean FCentSettleOrgId;
            private FDispSettleOrgIdBean FDispSettleOrgId;
            private String FGroup;
            private FDeliveryStockStatusBean FDeliveryStockStatus;
            private String FMaxPrice;
            private String FMinPrice;
            private String FIsStock;
            private String FBaseConsumeSumQty;
            private String FSalBaseQty;
            private FSubOrgIdBean FSubOrgId;
            private FEntryPayOrgIdBean FEntryPayOrgId;
            private List<FEntryDeliveryPlanBean> FEntryDeliveryPlan;
            private List<FTaxDetailSubEntityBean> FTaxDetailSubEntity;

            public String getFEntryID() {
                return FEntryID;
            }

            public void setFEntryID(String FEntryID) {
                this.FEntryID = FEntryID;
            }

            public String getFProductType() {
                return FProductType;
            }

            public void setFProductType(String FProductType) {
                this.FProductType = FProductType;
            }

            public FMaterialIdBean getFMaterialId() {
                return FMaterialId;
            }

            public void setFMaterialId(FMaterialIdBean FMaterialId) {
                this.FMaterialId = FMaterialId;
            }

            public FBomIdBean getFBomId() {
                return FBomId;
            }

            public void setFBomId(FBomIdBean FBomId) {
                this.FBomId = FBomId;
            }

            public String getFMaterialDesc() {
                return FMaterialDesc;
            }

            public void setFMaterialDesc(String FMaterialDesc) {
                this.FMaterialDesc = FMaterialDesc;
            }

            public FAuxPropIdBean getFAuxPropId() {
                return FAuxPropId;
            }

            public void setFAuxPropId(FAuxPropIdBean FAuxPropId) {
                this.FAuxPropId = FAuxPropId;
            }

            public FUnitIdBean getFUnitId() {
                return FUnitId;
            }

            public void setFUnitId(FUnitIdBean FUnitId) {
                this.FUnitId = FUnitId;
            }

            public String getFQty() {
                return FQty;
            }

            public void setFQty(String FQty) {
                this.FQty = FQty;
            }

            public FPriceUnitIdBean getFPriceUnitId() {
                return FPriceUnitId;
            }

            public void setFPriceUnitId(FPriceUnitIdBean FPriceUnitId) {
                this.FPriceUnitId = FPriceUnitId;
            }

            public String getFPriceUnitQty() {
                return FPriceUnitQty;
            }

            public void setFPriceUnitQty(String FPriceUnitQty) {
                this.FPriceUnitQty = FPriceUnitQty;
            }

            public String getFPriceBaseQty() {
                return FPriceBaseQty;
            }

            public void setFPriceBaseQty(String FPriceBaseQty) {
                this.FPriceBaseQty = FPriceBaseQty;
            }

            public String getFDeliveryDate() {
                return FDeliveryDate;
            }

            public void setFDeliveryDate(String FDeliveryDate) {
                this.FDeliveryDate = FDeliveryDate;
            }

            public String getFLocation() {
                return FLocation;
            }

            public void setFLocation(String FLocation) {
                this.FLocation = FLocation;
            }

            public String getFLocationAddress() {
                return FLocationAddress;
            }

            public void setFLocationAddress(String FLocationAddress) {
                this.FLocationAddress = FLocationAddress;
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

            public String getFEntryDiscountRate() {
                return FEntryDiscountRate;
            }

            public void setFEntryDiscountRate(String FEntryDiscountRate) {
                this.FEntryDiscountRate = FEntryDiscountRate;
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

            public FRequireOrgIdBean getFRequireOrgId() {
                return FRequireOrgId;
            }

            public void setFRequireOrgId(FRequireOrgIdBean FRequireOrgId) {
                this.FRequireOrgId = FRequireOrgId;
            }

            public FRequireDeptIdBean getFRequireDeptId() {
                return FRequireDeptId;
            }

            public void setFRequireDeptId(FRequireDeptIdBean FRequireDeptId) {
                this.FRequireDeptId = FRequireDeptId;
            }

            public FRequireStaffIdBean getFRequireStaffId() {
                return FRequireStaffId;
            }

            public void setFRequireStaffId(FRequireStaffIdBean FRequireStaffId) {
                this.FRequireStaffId = FRequireStaffId;
            }

            public FReceiveOrgIdBean getFReceiveOrgId() {
                return FReceiveOrgId;
            }

            public void setFReceiveOrgId(FReceiveOrgIdBean FReceiveOrgId) {
                this.FReceiveOrgId = FReceiveOrgId;
            }

            public FReceiveDeptIdBean getFReceiveDeptId() {
                return FReceiveDeptId;
            }

            public void setFReceiveDeptId(FReceiveDeptIdBean FReceiveDeptId) {
                this.FReceiveDeptId = FReceiveDeptId;
            }

            public FEntrySettleOrgIdBean getFEntrySettleOrgId() {
                return FEntrySettleOrgId;
            }

            public void setFEntrySettleOrgId(FEntrySettleOrgIdBean FEntrySettleOrgId) {
                this.FEntrySettleOrgId = FEntrySettleOrgId;
            }

            public boolean getFGiveAway() {
                return FGiveAway;
            }

            public void setFGiveAway(boolean FGiveAway) {
                this.FGiveAway = FGiveAway;
            }

            public String getFEntryNote() {
                return FEntryNote;
            }

            public void setFEntryNote(String FEntryNote) {
                this.FEntryNote = FEntryNote;
            }

            public String getFSupMatId() {
                return FSupMatId;
            }

            public void setFSupMatId(String FSupMatId) {
                this.FSupMatId = FSupMatId;
            }

            public String getFSupMatName() {
                return FSupMatName;
            }

            public void setFSupMatName(String FSupMatName) {
                this.FSupMatName = FSupMatName;
            }

            public FStockUnitIDBean getFStockUnitID() {
                return FStockUnitID;
            }

            public void setFStockUnitID(FStockUnitIDBean FStockUnitID) {
                this.FStockUnitID = FStockUnitID;
            }

            public String getFStockQty() {
                return FStockQty;
            }

            public void setFStockQty(String FStockQty) {
                this.FStockQty = FStockQty;
            }

            public String getFStockBaseQty() {
                return FStockBaseQty;
            }

            public void setFStockBaseQty(String FStockBaseQty) {
                this.FStockBaseQty = FStockBaseQty;
            }

            public FLotBean getFLot() {
                return FLot;
            }

            public void setFLot(FLotBean FLot) {
                this.FLot = FLot;
            }

            public String getFSupplierLot() {
                return FSupplierLot;
            }

            public void setFSupplierLot(String FSupplierLot) {
                this.FSupplierLot = FSupplierLot;
            }

            public FProcesserBean getFProcesser() {
                return FProcesser;
            }

            public void setFProcesser(FProcesserBean FProcesser) {
                this.FProcesser = FProcesser;
            }

            public String getFDeliveryControl() {
                return FDeliveryControl;
            }

            public void setFDeliveryControl(String FDeliveryControl) {
                this.FDeliveryControl = FDeliveryControl;
            }

            public String getFTimeControl() {
                return FTimeControl;
            }

            public void setFTimeControl(String FTimeControl) {
                this.FTimeControl = FTimeControl;
            }

            public String getFDeliveryMaxQty() {
                return FDeliveryMaxQty;
            }

            public void setFDeliveryMaxQty(String FDeliveryMaxQty) {
                this.FDeliveryMaxQty = FDeliveryMaxQty;
            }

            public String getFDeliveryMinQty() {
                return FDeliveryMinQty;
            }

            public void setFDeliveryMinQty(String FDeliveryMinQty) {
                this.FDeliveryMinQty = FDeliveryMinQty;
            }

            public String getFDeliveryBeforeDays() {
                return FDeliveryBeforeDays;
            }

            public void setFDeliveryBeforeDays(String FDeliveryBeforeDays) {
                this.FDeliveryBeforeDays = FDeliveryBeforeDays;
            }

            public String getFDeliveryDelayDays() {
                return FDeliveryDelayDays;
            }

            public void setFDeliveryDelayDays(String FDeliveryDelayDays) {
                this.FDeliveryDelayDays = FDeliveryDelayDays;
            }

            public String getFDeliveryEarlyDate() {
                return FDeliveryEarlyDate;
            }

            public void setFDeliveryEarlyDate(String FDeliveryEarlyDate) {
                this.FDeliveryEarlyDate = FDeliveryEarlyDate;
            }

            public String getFDeliveryLastDate() {
                return FDeliveryLastDate;
            }

            public void setFDeliveryLastDate(String FDeliveryLastDate) {
                this.FDeliveryLastDate = FDeliveryLastDate;
            }

            public String getFPriceCoefficient() {
                return FPriceCoefficient;
            }

            public void setFPriceCoefficient(String FPriceCoefficient) {
                this.FPriceCoefficient = FPriceCoefficient;
            }

            public FEntrySettleModeIdBean getFEntrySettleModeId() {
                return FEntrySettleModeId;
            }

            public void setFEntrySettleModeId(FEntrySettleModeIdBean FEntrySettleModeId) {
                this.FEntrySettleModeId = FEntrySettleModeId;
            }

            public String getFConsumeSumQty() {
                return FConsumeSumQty;
            }

            public void setFConsumeSumQty(String FConsumeSumQty) {
                this.FConsumeSumQty = FConsumeSumQty;
            }

            public String getFContractNo() {
                return FContractNo;
            }

            public void setFContractNo(String FContractNo) {
                this.FContractNo = FContractNo;
            }

            public String getFReqTraceNo() {
                return FReqTraceNo;
            }

            public void setFReqTraceNo(String FReqTraceNo) {
                this.FReqTraceNo = FReqTraceNo;
            }

            public String getFMtoNo() {
                return FMtoNo;
            }

            public void setFMtoNo(String FMtoNo) {
                this.FMtoNo = FMtoNo;
            }

            public String getFDEMANDTYPE() {
                return FDEMANDTYPE;
            }

            public void setFDEMANDTYPE(String FDEMANDTYPE) {
                this.FDEMANDTYPE = FDEMANDTYPE;
            }

            public String getFDEMANDBILLNO() {
                return FDEMANDBILLNO;
            }

            public void setFDEMANDBILLNO(String FDEMANDBILLNO) {
                this.FDEMANDBILLNO = FDEMANDBILLNO;
            }

            public String getFDEMANDBILLENTRYSEQ() {
                return FDEMANDBILLENTRYSEQ;
            }

            public void setFDEMANDBILLENTRYSEQ(String FDEMANDBILLENTRYSEQ) {
                this.FDEMANDBILLENTRYSEQ = FDEMANDBILLENTRYSEQ;
            }

            public String getFDEMANDBILLENTRYID() {
                return FDEMANDBILLENTRYID;
            }

            public void setFDEMANDBILLENTRYID(String FDEMANDBILLENTRYID) {
                this.FDEMANDBILLENTRYID = FDEMANDBILLENTRYID;
            }

            public FLocationIdBean getFLocationId() {
                return FLocationId;
            }

            public void setFLocationId(FLocationIdBean FLocationId) {
                this.FLocationId = FLocationId;
            }

            public String getFPlanConfirm() {
                return FPlanConfirm;
            }

            public void setFPlanConfirm(String FPlanConfirm) {
                this.FPlanConfirm = FPlanConfirm;
            }

            public FSalUnitIDBean getFSalUnitID() {
                return FSalUnitID;
            }

            public void setFSalUnitID(FSalUnitIDBean FSalUnitID) {
                this.FSalUnitID = FSalUnitID;
            }

            public String getFSalQty() {
                return FSalQty;
            }

            public void setFSalQty(String FSalQty) {
                this.FSalQty = FSalQty;
            }

            public String getFSalJoinQty() {
                return FSalJoinQty;
            }

            public void setFSalJoinQty(String FSalJoinQty) {
                this.FSalJoinQty = FSalJoinQty;
            }

            public String getFBaseSalJoinQty() {
                return FBaseSalJoinQty;
            }

            public void setFBaseSalJoinQty(String FBaseSalJoinQty) {
                this.FBaseSalJoinQty = FBaseSalJoinQty;
            }

            public FSetPriceUnitIDBean getFSetPriceUnitID() {
                return FSetPriceUnitID;
            }

            public void setFSetPriceUnitID(FSetPriceUnitIDBean FSetPriceUnitID) {
                this.FSetPriceUnitID = FSetPriceUnitID;
            }

            public String getFInventoryQty() {
                return FInventoryQty;
            }

            public void setFInventoryQty(String FInventoryQty) {
                this.FInventoryQty = FInventoryQty;
            }

            public FChargeProjectIDBean getFChargeProjectID() {
                return FChargeProjectID;
            }

            public void setFChargeProjectID(FChargeProjectIDBean FChargeProjectID) {
                this.FChargeProjectID = FChargeProjectID;
            }

            public FCentSettleOrgIdBean getFCentSettleOrgId() {
                return FCentSettleOrgId;
            }

            public void setFCentSettleOrgId(FCentSettleOrgIdBean FCentSettleOrgId) {
                this.FCentSettleOrgId = FCentSettleOrgId;
            }

            public FDispSettleOrgIdBean getFDispSettleOrgId() {
                return FDispSettleOrgId;
            }

            public void setFDispSettleOrgId(FDispSettleOrgIdBean FDispSettleOrgId) {
                this.FDispSettleOrgId = FDispSettleOrgId;
            }

            public String getFGroup() {
                return FGroup;
            }

            public void setFGroup(String FGroup) {
                this.FGroup = FGroup;
            }

            public FDeliveryStockStatusBean getFDeliveryStockStatus() {
                return FDeliveryStockStatus;
            }

            public void setFDeliveryStockStatus(FDeliveryStockStatusBean FDeliveryStockStatus) {
                this.FDeliveryStockStatus = FDeliveryStockStatus;
            }

            public String getFMaxPrice() {
                return FMaxPrice;
            }

            public void setFMaxPrice(String FMaxPrice) {
                this.FMaxPrice = FMaxPrice;
            }

            public String getFMinPrice() {
                return FMinPrice;
            }

            public void setFMinPrice(String FMinPrice) {
                this.FMinPrice = FMinPrice;
            }

            public String getFIsStock() {
                return FIsStock;
            }

            public void setFIsStock(String FIsStock) {
                this.FIsStock = FIsStock;
            }

            public String getFBaseConsumeSumQty() {
                return FBaseConsumeSumQty;
            }

            public void setFBaseConsumeSumQty(String FBaseConsumeSumQty) {
                this.FBaseConsumeSumQty = FBaseConsumeSumQty;
            }

            public String getFSalBaseQty() {
                return FSalBaseQty;
            }

            public void setFSalBaseQty(String FSalBaseQty) {
                this.FSalBaseQty = FSalBaseQty;
            }

            public FSubOrgIdBean getFSubOrgId() {
                return FSubOrgId;
            }

            public void setFSubOrgId(FSubOrgIdBean FSubOrgId) {
                this.FSubOrgId = FSubOrgId;
            }

            public FEntryPayOrgIdBean getFEntryPayOrgId() {
                return FEntryPayOrgId;
            }

            public void setFEntryPayOrgId(FEntryPayOrgIdBean FEntryPayOrgId) {
                this.FEntryPayOrgId = FEntryPayOrgId;
            }

            public List<FEntryDeliveryPlanBean> getFEntryDeliveryPlan() {
                return FEntryDeliveryPlan;
            }

            public void setFEntryDeliveryPlan(List<FEntryDeliveryPlanBean> FEntryDeliveryPlan) {
                this.FEntryDeliveryPlan = FEntryDeliveryPlan;
            }

            public List<FTaxDetailSubEntityBean> getFTaxDetailSubEntity() {
                return FTaxDetailSubEntity;
            }

            public void setFTaxDetailSubEntity(List<FTaxDetailSubEntityBean> FTaxDetailSubEntity) {
                this.FTaxDetailSubEntity = FTaxDetailSubEntity;
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

            public static class FBomIdBean {
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

            public static class FUnitIdBean {
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

            public static class FPriceUnitIdBean {
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

            public static class FRequireOrgIdBean {
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

            public static class FRequireDeptIdBean {
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

            public static class FRequireStaffIdBean {
            }

            public static class FReceiveOrgIdBean {
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

            public static class FReceiveDeptIdBean {
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

            public static class FEntrySettleOrgIdBean {
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

            public static class FStockUnitIDBean {
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

            public static class FProcesserBean {
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

            public static class FEntrySettleModeIdBean {
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

            public static class FLocationIdBean {
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

            public static class FSetPriceUnitIDBean {
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

            public static class FChargeProjectIDBean {
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

            public static class FCentSettleOrgIdBean {
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

            public static class FDispSettleOrgIdBean {
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

            public static class FDeliveryStockStatusBean {
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

            public static class FSubOrgIdBean {
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

            public static class FEntryPayOrgIdBean {
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

            public static class FEntryDeliveryPlanBean {
                /**
                 * FDetailId : 0
                 * FDeliveryDate_Plan : 1900-01-01
                 * FPlanQty : 0
                 * FELocation :
                 * FELocationAddress :
                 * FSUPPLIERDELIVERYDATE : 1900-01-01
                 * FPREARRIVALDATE : 1900-01-01
                 * FTRLT : 0
                 * FConfirmDeliQty : 0
                 * FConfirmDeliDate : 1900-01-01
                 * FConfirmInfo :
                 * FELocationId : {"FNumber":""}
                 */

                private String FDetailId;
                private String FDeliveryDate_Plan;
                private String FPlanQty;
                private String FELocation;
                private String FELocationAddress;
                private String FSUPPLIERDELIVERYDATE;
                private String FPREARRIVALDATE;
                private String FTRLT;
                private String FConfirmDeliQty;
                private String FConfirmDeliDate;
                private String FConfirmInfo;
                private FELocationIdBean FELocationId;

                public String getFDetailId() {
                    return FDetailId;
                }

                public void setFDetailId(String FDetailId) {
                    this.FDetailId = FDetailId;
                }

                public String getFDeliveryDate_Plan() {
                    return FDeliveryDate_Plan;
                }

                public void setFDeliveryDate_Plan(String FDeliveryDate_Plan) {
                    this.FDeliveryDate_Plan = FDeliveryDate_Plan;
                }

                public String getFPlanQty() {
                    return FPlanQty;
                }

                public void setFPlanQty(String FPlanQty) {
                    this.FPlanQty = FPlanQty;
                }

                public String getFELocation() {
                    return FELocation;
                }

                public void setFELocation(String FELocation) {
                    this.FELocation = FELocation;
                }

                public String getFELocationAddress() {
                    return FELocationAddress;
                }

                public void setFELocationAddress(String FELocationAddress) {
                    this.FELocationAddress = FELocationAddress;
                }

                public String getFSUPPLIERDELIVERYDATE() {
                    return FSUPPLIERDELIVERYDATE;
                }

                public void setFSUPPLIERDELIVERYDATE(String FSUPPLIERDELIVERYDATE) {
                    this.FSUPPLIERDELIVERYDATE = FSUPPLIERDELIVERYDATE;
                }

                public String getFPREARRIVALDATE() {
                    return FPREARRIVALDATE;
                }

                public void setFPREARRIVALDATE(String FPREARRIVALDATE) {
                    this.FPREARRIVALDATE = FPREARRIVALDATE;
                }

                public String getFTRLT() {
                    return FTRLT;
                }

                public void setFTRLT(String FTRLT) {
                    this.FTRLT = FTRLT;
                }

                public String getFConfirmDeliQty() {
                    return FConfirmDeliQty;
                }

                public void setFConfirmDeliQty(String FConfirmDeliQty) {
                    this.FConfirmDeliQty = FConfirmDeliQty;
                }

                public String getFConfirmDeliDate() {
                    return FConfirmDeliDate;
                }

                public void setFConfirmDeliDate(String FConfirmDeliDate) {
                    this.FConfirmDeliDate = FConfirmDeliDate;
                }

                public String getFConfirmInfo() {
                    return FConfirmInfo;
                }

                public void setFConfirmInfo(String FConfirmInfo) {
                    this.FConfirmInfo = FConfirmInfo;
                }

                public FELocationIdBean getFELocationId() {
                    return FELocationId;
                }

                public void setFELocationId(FELocationIdBean FELocationId) {
                    this.FELocationId = FELocationId;
                }

                public static class FELocationIdBean {
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

            public static class FTaxDetailSubEntityBean {
                /**
                 * FDetailID : 0
                 * FTaxRateId : {"FNumber":""}
                 * FTaxRate : 0
                 */

                private String FDetailID;
                private FTaxRateIdBean FTaxRateId;
                private String FTaxRate;

                public String getFDetailID() {
                    return FDetailID;
                }

                public void setFDetailID(String FDetailID) {
                    this.FDetailID = FDetailID;
                }

                public FTaxRateIdBean getFTaxRateId() {
                    return FTaxRateId;
                }

                public void setFTaxRateId(FTaxRateIdBean FTaxRateId) {
                    this.FTaxRateId = FTaxRateId;
                }

                public String getFTaxRate() {
                    return FTaxRate;
                }

                public void setFTaxRate(String FTaxRate) {
                    this.FTaxRate = FTaxRate;
                }

                public static class FTaxRateIdBean {
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
        }

        public static class FIinstallmentBean {
            /**
             * FENTRYID : 0
             * FYFDATE : 1900-01-01
             * FYFRATIO : 0
             * FYFAMOUNT : 0
             * FISPREPAYMENT : false
             * FRelBillNo :
             * FInsPrepaidAmount : 0
             * FACTUALAMOUNT : 0
             * FPayJoinAmount : 0
             * FRemarks :
             * FPayMaterialId : {"FNUMBER":""}
             * FMATERIALSEQ : 0
             * FPayPlanQty : 0
             * FPayPlanPrice : 0
             * FAppliedQty : 0
             * FActualPayQty : 0
             * FAPPLYAMOUNT : 0
             * FPURCHASEORDERNO :
             * FOrderEntryId : 0
             * FinsPayAdvanceRate : 0
             * FInsPayAdvanceAmount : 0
             * FPAYPLANPRICEUNITID : {"FNumber":""}
             * FBasePriceUnit : {"FNumber":""}
             * FBasePayPlanQty : 0
             * FOrderActualPaySubEntity : [{"FDetailID":"0","FPAYBILLID":"0","FPAYBILLENTITYID":"0","FPOORDERID":"0","FAmount":"0","FPREAMOUNT":"0","FPPSettleOrgId":{"FNumber":""},"FAPPLYBILLNO":"","FPREPAYBillNo":"","FPAPPLYAMOUNT":"0","FPPayJoinAmount":"0"}]
             */

            private String FENTRYID;
            private String FYFDATE;
            private String FYFRATIO;
            private String FYFAMOUNT;
            private String FISPREPAYMENT;
            private String FRelBillNo;
            private String FInsPrepaidAmount;
            private String FACTUALAMOUNT;
            private String FPayJoinAmount;
            private String FRemarks;
            private FPayMaterialIdBean FPayMaterialId;
            private String FMATERIALSEQ;
            private String FPayPlanQty;
            private String FPayPlanPrice;
            private String FAppliedQty;
            private String FActualPayQty;
            private String FAPPLYAMOUNT;
            private String FPURCHASEORDERNO;
            private String FOrderEntryId;
            private String FinsPayAdvanceRate;
            private String FInsPayAdvanceAmount;
            private FPAYPLANPRICEUNITIDBean FPAYPLANPRICEUNITID;
            private FBasePriceUnitBean FBasePriceUnit;
            private String FBasePayPlanQty;
            private List<FOrderActualPaySubEntityBean> FOrderActualPaySubEntity;

            public String getFENTRYID() {
                return FENTRYID;
            }

            public void setFENTRYID(String FENTRYID) {
                this.FENTRYID = FENTRYID;
            }

            public String getFYFDATE() {
                return FYFDATE;
            }

            public void setFYFDATE(String FYFDATE) {
                this.FYFDATE = FYFDATE;
            }

            public String getFYFRATIO() {
                return FYFRATIO;
            }

            public void setFYFRATIO(String FYFRATIO) {
                this.FYFRATIO = FYFRATIO;
            }

            public String getFYFAMOUNT() {
                return FYFAMOUNT;
            }

            public void setFYFAMOUNT(String FYFAMOUNT) {
                this.FYFAMOUNT = FYFAMOUNT;
            }

            public String getFISPREPAYMENT() {
                return FISPREPAYMENT;
            }

            public void setFISPREPAYMENT(String FISPREPAYMENT) {
                this.FISPREPAYMENT = FISPREPAYMENT;
            }

            public String getFRelBillNo() {
                return FRelBillNo;
            }

            public void setFRelBillNo(String FRelBillNo) {
                this.FRelBillNo = FRelBillNo;
            }

            public String getFInsPrepaidAmount() {
                return FInsPrepaidAmount;
            }

            public void setFInsPrepaidAmount(String FInsPrepaidAmount) {
                this.FInsPrepaidAmount = FInsPrepaidAmount;
            }

            public String getFACTUALAMOUNT() {
                return FACTUALAMOUNT;
            }

            public void setFACTUALAMOUNT(String FACTUALAMOUNT) {
                this.FACTUALAMOUNT = FACTUALAMOUNT;
            }

            public String getFPayJoinAmount() {
                return FPayJoinAmount;
            }

            public void setFPayJoinAmount(String FPayJoinAmount) {
                this.FPayJoinAmount = FPayJoinAmount;
            }

            public String getFRemarks() {
                return FRemarks;
            }

            public void setFRemarks(String FRemarks) {
                this.FRemarks = FRemarks;
            }

            public FPayMaterialIdBean getFPayMaterialId() {
                return FPayMaterialId;
            }

            public void setFPayMaterialId(FPayMaterialIdBean FPayMaterialId) {
                this.FPayMaterialId = FPayMaterialId;
            }

            public String getFMATERIALSEQ() {
                return FMATERIALSEQ;
            }

            public void setFMATERIALSEQ(String FMATERIALSEQ) {
                this.FMATERIALSEQ = FMATERIALSEQ;
            }

            public String getFPayPlanQty() {
                return FPayPlanQty;
            }

            public void setFPayPlanQty(String FPayPlanQty) {
                this.FPayPlanQty = FPayPlanQty;
            }

            public String getFPayPlanPrice() {
                return FPayPlanPrice;
            }

            public void setFPayPlanPrice(String FPayPlanPrice) {
                this.FPayPlanPrice = FPayPlanPrice;
            }

            public String getFAppliedQty() {
                return FAppliedQty;
            }

            public void setFAppliedQty(String FAppliedQty) {
                this.FAppliedQty = FAppliedQty;
            }

            public String getFActualPayQty() {
                return FActualPayQty;
            }

            public void setFActualPayQty(String FActualPayQty) {
                this.FActualPayQty = FActualPayQty;
            }

            public String getFAPPLYAMOUNT() {
                return FAPPLYAMOUNT;
            }

            public void setFAPPLYAMOUNT(String FAPPLYAMOUNT) {
                this.FAPPLYAMOUNT = FAPPLYAMOUNT;
            }

            public String getFPURCHASEORDERNO() {
                return FPURCHASEORDERNO;
            }

            public void setFPURCHASEORDERNO(String FPURCHASEORDERNO) {
                this.FPURCHASEORDERNO = FPURCHASEORDERNO;
            }

            public String getFOrderEntryId() {
                return FOrderEntryId;
            }

            public void setFOrderEntryId(String FOrderEntryId) {
                this.FOrderEntryId = FOrderEntryId;
            }

            public String getFinsPayAdvanceRate() {
                return FinsPayAdvanceRate;
            }

            public void setFinsPayAdvanceRate(String FinsPayAdvanceRate) {
                this.FinsPayAdvanceRate = FinsPayAdvanceRate;
            }

            public String getFInsPayAdvanceAmount() {
                return FInsPayAdvanceAmount;
            }

            public void setFInsPayAdvanceAmount(String FInsPayAdvanceAmount) {
                this.FInsPayAdvanceAmount = FInsPayAdvanceAmount;
            }

            public FPAYPLANPRICEUNITIDBean getFPAYPLANPRICEUNITID() {
                return FPAYPLANPRICEUNITID;
            }

            public void setFPAYPLANPRICEUNITID(FPAYPLANPRICEUNITIDBean FPAYPLANPRICEUNITID) {
                this.FPAYPLANPRICEUNITID = FPAYPLANPRICEUNITID;
            }

            public FBasePriceUnitBean getFBasePriceUnit() {
                return FBasePriceUnit;
            }

            public void setFBasePriceUnit(FBasePriceUnitBean FBasePriceUnit) {
                this.FBasePriceUnit = FBasePriceUnit;
            }

            public String getFBasePayPlanQty() {
                return FBasePayPlanQty;
            }

            public void setFBasePayPlanQty(String FBasePayPlanQty) {
                this.FBasePayPlanQty = FBasePayPlanQty;
            }

            public List<FOrderActualPaySubEntityBean> getFOrderActualPaySubEntity() {
                return FOrderActualPaySubEntity;
            }

            public void setFOrderActualPaySubEntity(List<FOrderActualPaySubEntityBean> FOrderActualPaySubEntity) {
                this.FOrderActualPaySubEntity = FOrderActualPaySubEntity;
            }

            public static class FPayMaterialIdBean {
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

            public static class FPAYPLANPRICEUNITIDBean {
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

            public static class FBasePriceUnitBean {
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

            public static class FOrderActualPaySubEntityBean {
                /**
                 * FDetailID : 0
                 * FPAYBILLID : 0
                 * FPAYBILLENTITYID : 0
                 * FPOORDERID : 0
                 * FAmount : 0
                 * FPREAMOUNT : 0
                 * FPPSettleOrgId : {"FNumber":""}
                 * FAPPLYBILLNO :
                 * FPREPAYBillNo :
                 * FPAPPLYAMOUNT : 0
                 * FPPayJoinAmount : 0
                 */

                private String FDetailID;
                private String FPAYBILLID;
                private String FPAYBILLENTITYID;
                private String FPOORDERID;
                private String FAmount;
                private String FPREAMOUNT;
                private FPPSettleOrgIdBean FPPSettleOrgId;
                private String FAPPLYBILLNO;
                private String FPREPAYBillNo;
                private String FPAPPLYAMOUNT;
                private String FPPayJoinAmount;

                public String getFDetailID() {
                    return FDetailID;
                }

                public void setFDetailID(String FDetailID) {
                    this.FDetailID = FDetailID;
                }

                public String getFPAYBILLID() {
                    return FPAYBILLID;
                }

                public void setFPAYBILLID(String FPAYBILLID) {
                    this.FPAYBILLID = FPAYBILLID;
                }

                public String getFPAYBILLENTITYID() {
                    return FPAYBILLENTITYID;
                }

                public void setFPAYBILLENTITYID(String FPAYBILLENTITYID) {
                    this.FPAYBILLENTITYID = FPAYBILLENTITYID;
                }

                public String getFPOORDERID() {
                    return FPOORDERID;
                }

                public void setFPOORDERID(String FPOORDERID) {
                    this.FPOORDERID = FPOORDERID;
                }

                public String getFAmount() {
                    return FAmount;
                }

                public void setFAmount(String FAmount) {
                    this.FAmount = FAmount;
                }

                public String getFPREAMOUNT() {
                    return FPREAMOUNT;
                }

                public void setFPREAMOUNT(String FPREAMOUNT) {
                    this.FPREAMOUNT = FPREAMOUNT;
                }

                public FPPSettleOrgIdBean getFPPSettleOrgId() {
                    return FPPSettleOrgId;
                }

                public void setFPPSettleOrgId(FPPSettleOrgIdBean FPPSettleOrgId) {
                    this.FPPSettleOrgId = FPPSettleOrgId;
                }

                public String getFAPPLYBILLNO() {
                    return FAPPLYBILLNO;
                }

                public void setFAPPLYBILLNO(String FAPPLYBILLNO) {
                    this.FAPPLYBILLNO = FAPPLYBILLNO;
                }

                public String getFPREPAYBillNo() {
                    return FPREPAYBillNo;
                }

                public void setFPREPAYBillNo(String FPREPAYBillNo) {
                    this.FPREPAYBillNo = FPREPAYBillNo;
                }

                public String getFPAPPLYAMOUNT() {
                    return FPAPPLYAMOUNT;
                }

                public void setFPAPPLYAMOUNT(String FPAPPLYAMOUNT) {
                    this.FPAPPLYAMOUNT = FPAPPLYAMOUNT;
                }

                public String getFPPayJoinAmount() {
                    return FPPayJoinAmount;
                }

                public void setFPPayJoinAmount(String FPPayJoinAmount) {
                    this.FPPayJoinAmount = FPPayJoinAmount;
                }

                public static class FPPSettleOrgIdBean {
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
        }
    }
}
