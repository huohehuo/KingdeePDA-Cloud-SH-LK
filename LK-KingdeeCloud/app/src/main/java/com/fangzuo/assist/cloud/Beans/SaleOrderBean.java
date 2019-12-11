package com.fangzuo.assist.cloud.Beans;


import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.Dao.T_main;

import java.util.ArrayList;
import java.util.List;

public class SaleOrderBean {

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
     * Model : {"FID":"0","FBillTypeID":{"FNUMBER":""},"FBillNo":"","FDate":"1900-01-01","FSaleOrgId":{"FNumber":""},"FCustId":{"FNumber":""},"FReceiveId":{"FNumber":""},"FHeadDeliveryWay":{"FNumber":""},"FHEADLOCID":{"FNumber":""},"FSaleDeptId":{"FNumber":""},"FCorrespondOrgId":{"FNumber":""},"FSaleGroupId":{"FNumber":""},"FSalerId":{"FNumber":""},"FReceiveAddress":"","FSettleId":{"FNumber":""},"FReceiveContact":{"FNAME":""},"FChargeId":{"FNumber":""},"FNetOrderBillNo":"","FNetOrderBillId":"0","FOppID":"0","FSalePhaseID":{"FNUMBER":""},"FISINIT":"false","FNote":"","FIsMobile":"false","FSaleOrderFinance":{"FEntryId":"0","FSettleCurrId":{"FNumber":""},"FRecConditionId":{"FNumber":""},"FIsIncludedTax":"false","FSettleModeId":{"FNumber":""},"FIsPriceExcludeTax":"false","FPriceListId":{"FNumber":""},"FDiscountListId":{"FNumber":""},"FExchangeTypeId":{"FNumber":""},"FMarginLevel":"0","FMargin":"0"},"FSaleOrderClause":[{"FEntryID":"0","FClauseId":{"FNumber":""},"FClauseDesc":""}],"FSaleOrderEntry":[{"FEntryID":"0","FReturnType":"","FRowType":"","FMapId":{"FNumber":""},"FMaterialId":{"FNumber":""},"FAuxPropId":{},"FParentMatId":{"FNUMBER":""},"FUnitID":{"FNumber":""},"FInventoryQty":"0","FCurrentInventory":"0","FAwaitQty":"0","FAvailableQty":"0","FQty":"0","FOldQty":"0","FPurPriceUnitId":{"FNumber":""},"FPrice":"0","FTaxPrice":"0","FIsFree":"false","FTaxCombination":{"FNumber":""},"FEntryTaxRate":"0","FProduceDate":"1900-01-01","FExpPeriod":"0","FExpUnit":"","FExpiryDate":"1900-01-01","FLot":{"FNumber":""},"FPriceDiscount":"0","FInStockPrice":"0","FDiscountRate":"0","FDeliveryDate":"1900-01-01","FStockOrgId":{"FNumber":""},"FSettleOrgIds":{"FNumber":""},"FSupplyOrgId":{"FNumber":""},"FOwnerTypeId":"","FOwnerId":{"FNumber":""},"FEntryNote":"","FReserveType":"","FPriority":"0","FMtoNo":"","FPromotionMatchType":"","FNetOrderEntryId":"0","FPriceBaseQty":"0","FSetPriceUnitID":{"FNumber":""},"FStockUnitID":{"FNumber":""},"FStockQty":"0","FStockBaseQty":"0","FServiceContext":"","FOUTLMTUNIT":"","FOutLmtUnitID":{"FNumber":""},"FSOStockId":{"FNUMBER":""},"FSOStockLocalId":{"FSOSTOCKLOCALID__FF100003":{"FNumber":""},"FSOSTOCKLOCALID__FF100004":{"FNumber":""},"FSOSTOCKLOCALID__FF100012":{"FNumber":""},"FSOSTOCKLOCALID__FF100013":{"FNumber":""},"FSOSTOCKLOCALID__FF100014":{"FNumber":""},"FSOSTOCKLOCALID__FF100015":{"FNumber":""},"FSOSTOCKLOCALID__FF100016":{"FNumber":""},"FSOSTOCKLOCALID__FF100017":{"FNumber":""},"FSOSTOCKLOCALID__FF100018":{"FNumber":""},"FSOSTOCKLOCALID__FF100019":{"FNumber":""}},"FOrderEntryPlan":[{"FDetailID":"0","FDetailLocId":{"FNumber":""},"FDetailLocAddress":"","FPlanDate":"1900-01-01","FTransportLeadTime":"0","FPlanQty":"0"}],"FTaxDetailSubEntity":[{"FDetailID":"0","FTaxRate":"0","FSellerWithholding":"false","FBuyerWithholding":"false"}]}],"FSaleOrderPlan":[{"FEntryID":"0","FNeedRecAdvance":"false","FReceiveType":{"FNumber":""},"FRecAdvanceRate":"0","FRecAdvanceAmount":"0","FMustDate":"1900-01-01","FRelBillNo":"","FRecAmount":"0","FControlSend":"","FReMark":"","FPlanMaterialId":{"FNUMBER":""},"FMaterialSeq":"0","FOrderEntryId":"0","FSaleOrderPlanEntry":[{"FDETAILID":"0","FPESettleOrgId":{"FNumber":""}}]}],"FSalOrderTrace":[{"FEntryID":"0","FLogComId":{"FCODE":""},"FCarryBillNo":"","FDelTime":"1900-01-01","FTraceStatus":"","FSalOrderTraceDetail":[{"FDetailID":"0","FTraceTime":"","FTraceDetail":""}]}]}
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
         * FCustId : {"FNumber":""}
         * FReceiveId : {"FNumber":""}
         * FHeadDeliveryWay : {"FNumber":""}
         * FHEADLOCID : {"FNumber":""}
         * FSaleDeptId : {"FNumber":""}
         * FCorrespondOrgId : {"FNumber":""}
         * FSaleGroupId : {"FNumber":""}
         * FSalerId : {"FNumber":""}
         * FReceiveAddress :
         * FSettleId : {"FNumber":""}
         * FReceiveContact : {"FNAME":""}
         * FChargeId : {"FNumber":""}
         * FNetOrderBillNo :
         * FNetOrderBillId : 0
         * FOppID : 0
         * FSalePhaseID : {"FNUMBER":""}
         * FISINIT : false
         * FNote :
         * FIsMobile : false
         * FSaleOrderFinance : {"FEntryId":"0","FSettleCurrId":{"FNumber":""},"FRecConditionId":{"FNumber":""},"FIsIncludedTax":"false","FSettleModeId":{"FNumber":""},"FIsPriceExcludeTax":"false","FPriceListId":{"FNumber":""},"FDiscountListId":{"FNumber":""},"FExchangeTypeId":{"FNumber":""},"FMarginLevel":"0","FMargin":"0"}
         * FSaleOrderClause : [{"FEntryID":"0","FClauseId":{"FNumber":""},"FClauseDesc":""}]
         * FSaleOrderEntry : [{"FEntryID":"0","FReturnType":"","FRowType":"","FMapId":{"FNumber":""},"FMaterialId":{"FNumber":""},"FAuxPropId":{},"FParentMatId":{"FNUMBER":""},"FUnitID":{"FNumber":""},"FInventoryQty":"0","FCurrentInventory":"0","FAwaitQty":"0","FAvailableQty":"0","FQty":"0","FOldQty":"0","FPurPriceUnitId":{"FNumber":""},"FPrice":"0","FTaxPrice":"0","FIsFree":"false","FTaxCombination":{"FNumber":""},"FEntryTaxRate":"0","FProduceDate":"1900-01-01","FExpPeriod":"0","FExpUnit":"","FExpiryDate":"1900-01-01","FLot":{"FNumber":""},"FPriceDiscount":"0","FInStockPrice":"0","FDiscountRate":"0","FDeliveryDate":"1900-01-01","FStockOrgId":{"FNumber":""},"FSettleOrgIds":{"FNumber":""},"FSupplyOrgId":{"FNumber":""},"FOwnerTypeId":"","FOwnerId":{"FNumber":""},"FEntryNote":"","FReserveType":"","FPriority":"0","FMtoNo":"","FPromotionMatchType":"","FNetOrderEntryId":"0","FPriceBaseQty":"0","FSetPriceUnitID":{"FNumber":""},"FStockUnitID":{"FNumber":""},"FStockQty":"0","FStockBaseQty":"0","FServiceContext":"","FOUTLMTUNIT":"","FOutLmtUnitID":{"FNumber":""},"FSOStockId":{"FNUMBER":""},"FSOStockLocalId":{"FSOSTOCKLOCALID__FF100003":{"FNumber":""},"FSOSTOCKLOCALID__FF100004":{"FNumber":""},"FSOSTOCKLOCALID__FF100012":{"FNumber":""},"FSOSTOCKLOCALID__FF100013":{"FNumber":""},"FSOSTOCKLOCALID__FF100014":{"FNumber":""},"FSOSTOCKLOCALID__FF100015":{"FNumber":""},"FSOSTOCKLOCALID__FF100016":{"FNumber":""},"FSOSTOCKLOCALID__FF100017":{"FNumber":""},"FSOSTOCKLOCALID__FF100018":{"FNumber":""},"FSOSTOCKLOCALID__FF100019":{"FNumber":""}},"FOrderEntryPlan":[{"FDetailID":"0","FDetailLocId":{"FNumber":""},"FDetailLocAddress":"","FPlanDate":"1900-01-01","FTransportLeadTime":"0","FPlanQty":"0"}],"FTaxDetailSubEntity":[{"FDetailID":"0","FTaxRate":"0","FSellerWithholding":"false","FBuyerWithholding":"false"}]}]
         * FSaleOrderPlan : [{"FEntryID":"0","FNeedRecAdvance":"false","FReceiveType":{"FNumber":""},"FRecAdvanceRate":"0","FRecAdvanceAmount":"0","FMustDate":"1900-01-01","FRelBillNo":"","FRecAmount":"0","FControlSend":"","FReMark":"","FPlanMaterialId":{"FNUMBER":""},"FMaterialSeq":"0","FOrderEntryId":"0","FSaleOrderPlanEntry":[{"FDETAILID":"0","FPESettleOrgId":{"FNumber":""}}]}]
         * FSalOrderTrace : [{"FEntryID":"0","FLogComId":{"FCODE":""},"FCarryBillNo":"","FDelTime":"1900-01-01","FTraceStatus":"","FSalOrderTraceDetail":[{"FDetailID":"0","FTraceTime":"","FTraceDetail":""}]}]
         */

        private String FID;
        private FBillTypeIDBean FBillTypeID;
        private String FBillNo;
        private String FDate;
        private FSaleOrgIdBean FSaleOrgId;
        private FCustIdBean FCustId;
        private FReceiveIdBean FReceiveId;
        private FHeadDeliveryWayBean FHeadDeliveryWay;
        private FHEADLOCIDBean FHEADLOCID;
        private FSaleDeptIdBean FSaleDeptId;
        private FCorrespondOrgIdBean FCorrespondOrgId;
        private FSaleGroupIdBean FSaleGroupId;
        private FSalerIdBean FSalerId;
        private String FReceiveAddress;
        private FSettleIdBean FSettleId;
        private FReceiveContactBean FReceiveContact;
        private FChargeIdBean FChargeId;
        private String FNetOrderBillNo;
        private String FNetOrderBillId;
        private String FOppID;
        private FSalePhaseIDBean FSalePhaseID;
        private String FISINIT;
        private String FNote;
        private String FIsMobile;
        private FSaleOrderFinanceBean FSaleOrderFinance;
        private List<FSaleOrderClauseBean> FSaleOrderClause;
        private List<FSaleOrderEntryBean> FSaleOrderEntry;
        private List<FSaleOrderPlanBean> FSaleOrderPlan;
        private List<FSalOrderTraceBean> FSalOrderTrace;
        public void setModelBean(T_main main, List<T_Detail> beans){
            //表头设置-------------------------------------------------------------
            this.FBillNo ="";
            this.FSaleOrgId=new SaleOrderBean.ModelBean.FSaleOrgIdBean();             //销售组织
            this.FSaleOrgId.FNumber=main.FStockOrgId;             //发货组织
            this.FSaleDeptId=new SaleOrderBean.ModelBean.FSaleDeptIdBean();             //销售部门
            this.FSaleDeptId.FNumber=main.FDepartmentNumber;             //销售部门
            this.FBillTypeID=new SaleOrderBean.ModelBean.FBillTypeIDBean();//单据类型
            this.FBillTypeID.FNUMBER=main.FBillTypeID;//单据类型
            this.FSalerId=new SaleOrderBean.ModelBean.FSalerIdBean();//销售员
            this.FSalerId.FNumber=main.FPurchaserId;//销售员
//            this.FOwnerTypeIdHead = main.FOwnerTypeIdHead;//货主类型
//            this.FOwnerIdHead=new SaleOutBean.ModelBean.FOwnerIdHeadBean(main.FOwnerIdHead);//货主
            this.FDate = main.FDate;//入库日期
            this.FNote = "备注";//入库日期
            this.FCustId=new FCustIdBean();//供应商
            this.FCustId.FNumber=main.FCustomerID;//供应商
//            this.FInStockFin=new FInStockFinBean();//一个大类
//            this.FInStockFin.FPriceTimePoint= main.FPriceTimePoint;         //定价时点
//            this.FInStockFin.FSettleOrgId=new FInStockFinBean.FSettleOrgIdBean(main.FSettleOrgId);//结算组织
//            this.FInStockFin.FSettleCurrId=new FInStockFinBean.FSettleCurrIdBean(main.FSettleCurrId);//结算币别
            //----------------------------------------------------------------
            //表体设置----------------------------------------------------------------
            this.FSaleOrderEntry=new ArrayList<>();//一个大类数组
            for (int i = 0; i < beans.size(); i++) {
                SaleOrderBean.ModelBean.FSaleOrderEntryBean bean1=new SaleOrderBean.ModelBean.FSaleOrderEntryBean();//一个大类
                bean1.FMaterialId = new FSaleOrderEntryBean.FMaterialIdBean();//物料编码
                bean1.FMaterialId.FNumber=beans.get(i).FMaterialId;//物料编码
                bean1.FSOStockId=new FSaleOrderEntryBean.FSOStockIdBean();//仓库
                bean1.FSOStockId.FNUMBER=beans.get(i).FStorageId;//仓库
                bean1.FLot=new SaleOrderBean.ModelBean.FSaleOrderEntryBean.FLotBean();//批号
                bean1.FLot.FNumber=beans.get(i).FBatch;//批号
//                bean1.FRemainInStockQty=beans.get(i).FRemainInStockQty;        //采购数量
                bean1.FQty=beans.get(i).FRealQty;         //实收数量
//                bean1.FRemainInStockUnitId = new FEntityBean.FRemainInStockUnitIdBean(beans.get(i).FRemainInStockUnitId);//采购单位
//                bean1.FPriceUnitID = new FEntityBean.FPriceUnitIDBean(beans.get(i).FPriceUnitID);//计价单位
                bean1.FUnitID = new SaleOrderBean.ModelBean.FSaleOrderEntryBean.FUnitIDBean();//库存单位
                bean1.FUnitID.FNumber=beans.get(i).FUnitID;//库存单位
                bean1.FIsFree = beans.get(i).FIsFree;//是否为赠品


                this.FSaleOrderEntry.add(bean1);//添加进数组
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

        public FCustIdBean getFCustId() {
            return FCustId;
        }

        public void setFCustId(FCustIdBean FCustId) {
            this.FCustId = FCustId;
        }

        public FReceiveIdBean getFReceiveId() {
            return FReceiveId;
        }

        public void setFReceiveId(FReceiveIdBean FReceiveId) {
            this.FReceiveId = FReceiveId;
        }

        public FHeadDeliveryWayBean getFHeadDeliveryWay() {
            return FHeadDeliveryWay;
        }

        public void setFHeadDeliveryWay(FHeadDeliveryWayBean FHeadDeliveryWay) {
            this.FHeadDeliveryWay = FHeadDeliveryWay;
        }

        public FHEADLOCIDBean getFHEADLOCID() {
            return FHEADLOCID;
        }

        public void setFHEADLOCID(FHEADLOCIDBean FHEADLOCID) {
            this.FHEADLOCID = FHEADLOCID;
        }

        public FSaleDeptIdBean getFSaleDeptId() {
            return FSaleDeptId;
        }

        public void setFSaleDeptId(FSaleDeptIdBean FSaleDeptId) {
            this.FSaleDeptId = FSaleDeptId;
        }

        public FCorrespondOrgIdBean getFCorrespondOrgId() {
            return FCorrespondOrgId;
        }

        public void setFCorrespondOrgId(FCorrespondOrgIdBean FCorrespondOrgId) {
            this.FCorrespondOrgId = FCorrespondOrgId;
        }

        public FSaleGroupIdBean getFSaleGroupId() {
            return FSaleGroupId;
        }

        public void setFSaleGroupId(FSaleGroupIdBean FSaleGroupId) {
            this.FSaleGroupId = FSaleGroupId;
        }

        public FSalerIdBean getFSalerId() {
            return FSalerId;
        }

        public void setFSalerId(FSalerIdBean FSalerId) {
            this.FSalerId = FSalerId;
        }

        public String getFReceiveAddress() {
            return FReceiveAddress;
        }

        public void setFReceiveAddress(String FReceiveAddress) {
            this.FReceiveAddress = FReceiveAddress;
        }

        public FSettleIdBean getFSettleId() {
            return FSettleId;
        }

        public void setFSettleId(FSettleIdBean FSettleId) {
            this.FSettleId = FSettleId;
        }

        public FReceiveContactBean getFReceiveContact() {
            return FReceiveContact;
        }

        public void setFReceiveContact(FReceiveContactBean FReceiveContact) {
            this.FReceiveContact = FReceiveContact;
        }

        public FChargeIdBean getFChargeId() {
            return FChargeId;
        }

        public void setFChargeId(FChargeIdBean FChargeId) {
            this.FChargeId = FChargeId;
        }

        public String getFNetOrderBillNo() {
            return FNetOrderBillNo;
        }

        public void setFNetOrderBillNo(String FNetOrderBillNo) {
            this.FNetOrderBillNo = FNetOrderBillNo;
        }

        public String getFNetOrderBillId() {
            return FNetOrderBillId;
        }

        public void setFNetOrderBillId(String FNetOrderBillId) {
            this.FNetOrderBillId = FNetOrderBillId;
        }

        public String getFOppID() {
            return FOppID;
        }

        public void setFOppID(String FOppID) {
            this.FOppID = FOppID;
        }

        public FSalePhaseIDBean getFSalePhaseID() {
            return FSalePhaseID;
        }

        public void setFSalePhaseID(FSalePhaseIDBean FSalePhaseID) {
            this.FSalePhaseID = FSalePhaseID;
        }

        public String getFISINIT() {
            return FISINIT;
        }

        public void setFISINIT(String FISINIT) {
            this.FISINIT = FISINIT;
        }

        public String getFNote() {
            return FNote;
        }

        public void setFNote(String FNote) {
            this.FNote = FNote;
        }

        public String getFIsMobile() {
            return FIsMobile;
        }

        public void setFIsMobile(String FIsMobile) {
            this.FIsMobile = FIsMobile;
        }

        public FSaleOrderFinanceBean getFSaleOrderFinance() {
            return FSaleOrderFinance;
        }

        public void setFSaleOrderFinance(FSaleOrderFinanceBean FSaleOrderFinance) {
            this.FSaleOrderFinance = FSaleOrderFinance;
        }

        public List<FSaleOrderClauseBean> getFSaleOrderClause() {
            return FSaleOrderClause;
        }

        public void setFSaleOrderClause(List<FSaleOrderClauseBean> FSaleOrderClause) {
            this.FSaleOrderClause = FSaleOrderClause;
        }

        public List<FSaleOrderEntryBean> getFSaleOrderEntry() {
            return FSaleOrderEntry;
        }

        public void setFSaleOrderEntry(List<FSaleOrderEntryBean> FSaleOrderEntry) {
            this.FSaleOrderEntry = FSaleOrderEntry;
        }

        public List<FSaleOrderPlanBean> getFSaleOrderPlan() {
            return FSaleOrderPlan;
        }

        public void setFSaleOrderPlan(List<FSaleOrderPlanBean> FSaleOrderPlan) {
            this.FSaleOrderPlan = FSaleOrderPlan;
        }

        public List<FSalOrderTraceBean> getFSalOrderTrace() {
            return FSalOrderTrace;
        }

        public void setFSalOrderTrace(List<FSalOrderTraceBean> FSalOrderTrace) {
            this.FSalOrderTrace = FSalOrderTrace;
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

        public static class FCustIdBean {
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

        public static class FReceiveIdBean {
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

        public static class FHeadDeliveryWayBean {
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

        public static class FHEADLOCIDBean {
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

        public static class FSaleDeptIdBean {
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

        public static class FSalerIdBean {
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

        public static class FReceiveContactBean {
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

        public static class FSalePhaseIDBean {
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

        public static class FSaleOrderFinanceBean {
            /**
             * FEntryId : 0
             * FSettleCurrId : {"FNumber":""}
             * FRecConditionId : {"FNumber":""}
             * FIsIncludedTax : false
             * FSettleModeId : {"FNumber":""}
             * FIsPriceExcludeTax : false
             * FPriceListId : {"FNumber":""}
             * FDiscountListId : {"FNumber":""}
             * FExchangeTypeId : {"FNumber":""}
             * FMarginLevel : 0
             * FMargin : 0
             */

            private String FEntryId;
            private FSettleCurrIdBean FSettleCurrId;
            private FRecConditionIdBean FRecConditionId;
            private String FIsIncludedTax;
            private FSettleModeIdBean FSettleModeId;
            private String FIsPriceExcludeTax;
            private FPriceListIdBean FPriceListId;
            private FDiscountListIdBean FDiscountListId;
            private FExchangeTypeIdBean FExchangeTypeId;
            private String FMarginLevel;
            private String FMargin;

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

            public FRecConditionIdBean getFRecConditionId() {
                return FRecConditionId;
            }

            public void setFRecConditionId(FRecConditionIdBean FRecConditionId) {
                this.FRecConditionId = FRecConditionId;
            }

            public String getFIsIncludedTax() {
                return FIsIncludedTax;
            }

            public void setFIsIncludedTax(String FIsIncludedTax) {
                this.FIsIncludedTax = FIsIncludedTax;
            }

            public FSettleModeIdBean getFSettleModeId() {
                return FSettleModeId;
            }

            public void setFSettleModeId(FSettleModeIdBean FSettleModeId) {
                this.FSettleModeId = FSettleModeId;
            }

            public String getFIsPriceExcludeTax() {
                return FIsPriceExcludeTax;
            }

            public void setFIsPriceExcludeTax(String FIsPriceExcludeTax) {
                this.FIsPriceExcludeTax = FIsPriceExcludeTax;
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

            public FExchangeTypeIdBean getFExchangeTypeId() {
                return FExchangeTypeId;
            }

            public void setFExchangeTypeId(FExchangeTypeIdBean FExchangeTypeId) {
                this.FExchangeTypeId = FExchangeTypeId;
            }

            public String getFMarginLevel() {
                return FMarginLevel;
            }

            public void setFMarginLevel(String FMarginLevel) {
                this.FMarginLevel = FMarginLevel;
            }

            public String getFMargin() {
                return FMargin;
            }

            public void setFMargin(String FMargin) {
                this.FMargin = FMargin;
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

            public static class FRecConditionIdBean {
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

        public static class FSaleOrderClauseBean {
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

        public static class FSaleOrderEntryBean {
            /**
             * FEntryID : 0
             * FReturnType :
             * FRowType :
             * FMapId : {"FNumber":""}
             * FMaterialId : {"FNumber":""}
             * FAuxPropId : {}
             * FParentMatId : {"FNUMBER":""}
             * FUnitID : {"FNumber":""}
             * FInventoryQty : 0
             * FCurrentInventory : 0
             * FAwaitQty : 0
             * FAvailableQty : 0
             * FQty : 0
             * FOldQty : 0
             * FPurPriceUnitId : {"FNumber":""}
             * FPrice : 0
             * FTaxPrice : 0
             * FIsFree : false
             * FTaxCombination : {"FNumber":""}
             * FEntryTaxRate : 0
             * FProduceDate : 1900-01-01
             * FExpPeriod : 0
             * FExpUnit :
             * FExpiryDate : 1900-01-01
             * FLot : {"FNumber":""}
             * FPriceDiscount : 0
             * FInStockPrice : 0
             * FDiscountRate : 0
             * FDeliveryDate : 1900-01-01
             * FStockOrgId : {"FNumber":""}
             * FSettleOrgIds : {"FNumber":""}
             * FSupplyOrgId : {"FNumber":""}
             * FOwnerTypeId :
             * FOwnerId : {"FNumber":""}
             * FEntryNote :
             * FReserveType :
             * FPriority : 0
             * FMtoNo :
             * FPromotionMatchType :
             * FNetOrderEntryId : 0
             * FPriceBaseQty : 0
             * FSetPriceUnitID : {"FNumber":""}
             * FStockUnitID : {"FNumber":""}
             * FStockQty : 0
             * FStockBaseQty : 0
             * FServiceContext :
             * FOUTLMTUNIT :
             * FOutLmtUnitID : {"FNumber":""}
             * FSOStockId : {"FNUMBER":""}
             * FSOStockLocalId : {"FSOSTOCKLOCALID__FF100003":{"FNumber":""},"FSOSTOCKLOCALID__FF100004":{"FNumber":""},"FSOSTOCKLOCALID__FF100012":{"FNumber":""},"FSOSTOCKLOCALID__FF100013":{"FNumber":""},"FSOSTOCKLOCALID__FF100014":{"FNumber":""},"FSOSTOCKLOCALID__FF100015":{"FNumber":""},"FSOSTOCKLOCALID__FF100016":{"FNumber":""},"FSOSTOCKLOCALID__FF100017":{"FNumber":""},"FSOSTOCKLOCALID__FF100018":{"FNumber":""},"FSOSTOCKLOCALID__FF100019":{"FNumber":""}}
             * FOrderEntryPlan : [{"FDetailID":"0","FDetailLocId":{"FNumber":""},"FDetailLocAddress":"","FPlanDate":"1900-01-01","FTransportLeadTime":"0","FPlanQty":"0"}]
             * FTaxDetailSubEntity : [{"FDetailID":"0","FTaxRate":"0","FSellerWithholding":"false","FBuyerWithholding":"false"}]
             */

            private String FEntryID;
            private String FReturnType;
            private String FRowType;
            private FMapIdBean FMapId;
            private FMaterialIdBean FMaterialId;
            private FAuxPropIdBean FAuxPropId;
            private FParentMatIdBean FParentMatId;
            private FUnitIDBean FUnitID;
            private String FInventoryQty;
            private String FCurrentInventory;
            private String FAwaitQty;
            private String FAvailableQty;
            private String FQty;
            private String FOldQty;
            private FPurPriceUnitIdBean FPurPriceUnitId;
            private String FPrice;
            private String FTaxPrice;
            private boolean FIsFree;
            private FTaxCombinationBean FTaxCombination;
            private String FEntryTaxRate;
            private String FProduceDate;
            private String FExpPeriod;
            private String FExpUnit;
            private String FExpiryDate;
            private FLotBean FLot;
            private String FPriceDiscount;
            private String FInStockPrice;
            private String FDiscountRate;
            private String FDeliveryDate;
            private FStockOrgIdBean FStockOrgId;
            private FSettleOrgIdsBean FSettleOrgIds;
            private FSupplyOrgIdBean FSupplyOrgId;
            private String FOwnerTypeId;
            private FOwnerIdBean FOwnerId;
            private String FEntryNote;
            private String FReserveType;
            private String FPriority;
            private String FMtoNo;
            private String FPromotionMatchType;
            private String FNetOrderEntryId;
            private String FPriceBaseQty;
            private FSetPriceUnitIDBean FSetPriceUnitID;
            private FStockUnitIDBean FStockUnitID;
            private String FStockQty;
            private String FStockBaseQty;
            private String FServiceContext;
            private String FOUTLMTUNIT;
            private FOutLmtUnitIDBean FOutLmtUnitID;
            private FSOStockIdBean FSOStockId;
            private FSOStockLocalIdBean FSOStockLocalId;
            private List<FOrderEntryPlanBean> FOrderEntryPlan;
            private List<FTaxDetailSubEntityBean> FTaxDetailSubEntity;

            public String getFEntryID() {
                return FEntryID;
            }

            public void setFEntryID(String FEntryID) {
                this.FEntryID = FEntryID;
            }

            public String getFReturnType() {
                return FReturnType;
            }

            public void setFReturnType(String FReturnType) {
                this.FReturnType = FReturnType;
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

            public FAuxPropIdBean getFAuxPropId() {
                return FAuxPropId;
            }

            public void setFAuxPropId(FAuxPropIdBean FAuxPropId) {
                this.FAuxPropId = FAuxPropId;
            }

            public FParentMatIdBean getFParentMatId() {
                return FParentMatId;
            }

            public void setFParentMatId(FParentMatIdBean FParentMatId) {
                this.FParentMatId = FParentMatId;
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

            public String getFCurrentInventory() {
                return FCurrentInventory;
            }

            public void setFCurrentInventory(String FCurrentInventory) {
                this.FCurrentInventory = FCurrentInventory;
            }

            public String getFAwaitQty() {
                return FAwaitQty;
            }

            public void setFAwaitQty(String FAwaitQty) {
                this.FAwaitQty = FAwaitQty;
            }

            public String getFAvailableQty() {
                return FAvailableQty;
            }

            public void setFAvailableQty(String FAvailableQty) {
                this.FAvailableQty = FAvailableQty;
            }

            public String getFQty() {
                return FQty;
            }

            public void setFQty(String FQty) {
                this.FQty = FQty;
            }

            public String getFOldQty() {
                return FOldQty;
            }

            public void setFOldQty(String FOldQty) {
                this.FOldQty = FOldQty;
            }

            public FPurPriceUnitIdBean getFPurPriceUnitId() {
                return FPurPriceUnitId;
            }

            public void setFPurPriceUnitId(FPurPriceUnitIdBean FPurPriceUnitId) {
                this.FPurPriceUnitId = FPurPriceUnitId;
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

            public boolean isFIsFree() {
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

            public String getFProduceDate() {
                return FProduceDate;
            }

            public void setFProduceDate(String FProduceDate) {
                this.FProduceDate = FProduceDate;
            }

            public String getFExpPeriod() {
                return FExpPeriod;
            }

            public void setFExpPeriod(String FExpPeriod) {
                this.FExpPeriod = FExpPeriod;
            }

            public String getFExpUnit() {
                return FExpUnit;
            }

            public void setFExpUnit(String FExpUnit) {
                this.FExpUnit = FExpUnit;
            }

            public String getFExpiryDate() {
                return FExpiryDate;
            }

            public void setFExpiryDate(String FExpiryDate) {
                this.FExpiryDate = FExpiryDate;
            }

            public FLotBean getFLot() {
                return FLot;
            }

            public void setFLot(FLotBean FLot) {
                this.FLot = FLot;
            }

            public String getFPriceDiscount() {
                return FPriceDiscount;
            }

            public void setFPriceDiscount(String FPriceDiscount) {
                this.FPriceDiscount = FPriceDiscount;
            }

            public String getFInStockPrice() {
                return FInStockPrice;
            }

            public void setFInStockPrice(String FInStockPrice) {
                this.FInStockPrice = FInStockPrice;
            }

            public String getFDiscountRate() {
                return FDiscountRate;
            }

            public void setFDiscountRate(String FDiscountRate) {
                this.FDiscountRate = FDiscountRate;
            }

            public String getFDeliveryDate() {
                return FDeliveryDate;
            }

            public void setFDeliveryDate(String FDeliveryDate) {
                this.FDeliveryDate = FDeliveryDate;
            }

            public FStockOrgIdBean getFStockOrgId() {
                return FStockOrgId;
            }

            public void setFStockOrgId(FStockOrgIdBean FStockOrgId) {
                this.FStockOrgId = FStockOrgId;
            }

            public FSettleOrgIdsBean getFSettleOrgIds() {
                return FSettleOrgIds;
            }

            public void setFSettleOrgIds(FSettleOrgIdsBean FSettleOrgIds) {
                this.FSettleOrgIds = FSettleOrgIds;
            }

            public FSupplyOrgIdBean getFSupplyOrgId() {
                return FSupplyOrgId;
            }

            public void setFSupplyOrgId(FSupplyOrgIdBean FSupplyOrgId) {
                this.FSupplyOrgId = FSupplyOrgId;
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

            public String getFEntryNote() {
                return FEntryNote;
            }

            public void setFEntryNote(String FEntryNote) {
                this.FEntryNote = FEntryNote;
            }

            public String getFReserveType() {
                return FReserveType;
            }

            public void setFReserveType(String FReserveType) {
                this.FReserveType = FReserveType;
            }

            public String getFPriority() {
                return FPriority;
            }

            public void setFPriority(String FPriority) {
                this.FPriority = FPriority;
            }

            public String getFMtoNo() {
                return FMtoNo;
            }

            public void setFMtoNo(String FMtoNo) {
                this.FMtoNo = FMtoNo;
            }

            public String getFPromotionMatchType() {
                return FPromotionMatchType;
            }

            public void setFPromotionMatchType(String FPromotionMatchType) {
                this.FPromotionMatchType = FPromotionMatchType;
            }

            public String getFNetOrderEntryId() {
                return FNetOrderEntryId;
            }

            public void setFNetOrderEntryId(String FNetOrderEntryId) {
                this.FNetOrderEntryId = FNetOrderEntryId;
            }

            public String getFPriceBaseQty() {
                return FPriceBaseQty;
            }

            public void setFPriceBaseQty(String FPriceBaseQty) {
                this.FPriceBaseQty = FPriceBaseQty;
            }

            public FSetPriceUnitIDBean getFSetPriceUnitID() {
                return FSetPriceUnitID;
            }

            public void setFSetPriceUnitID(FSetPriceUnitIDBean FSetPriceUnitID) {
                this.FSetPriceUnitID = FSetPriceUnitID;
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

            public String getFServiceContext() {
                return FServiceContext;
            }

            public void setFServiceContext(String FServiceContext) {
                this.FServiceContext = FServiceContext;
            }

            public String getFOUTLMTUNIT() {
                return FOUTLMTUNIT;
            }

            public void setFOUTLMTUNIT(String FOUTLMTUNIT) {
                this.FOUTLMTUNIT = FOUTLMTUNIT;
            }

            public FOutLmtUnitIDBean getFOutLmtUnitID() {
                return FOutLmtUnitID;
            }

            public void setFOutLmtUnitID(FOutLmtUnitIDBean FOutLmtUnitID) {
                this.FOutLmtUnitID = FOutLmtUnitID;
            }

            public FSOStockIdBean getFSOStockId() {
                return FSOStockId;
            }

            public void setFSOStockId(FSOStockIdBean FSOStockId) {
                this.FSOStockId = FSOStockId;
            }

            public FSOStockLocalIdBean getFSOStockLocalId() {
                return FSOStockLocalId;
            }

            public void setFSOStockLocalId(FSOStockLocalIdBean FSOStockLocalId) {
                this.FSOStockLocalId = FSOStockLocalId;
            }

            public List<FOrderEntryPlanBean> getFOrderEntryPlan() {
                return FOrderEntryPlan;
            }

            public void setFOrderEntryPlan(List<FOrderEntryPlanBean> FOrderEntryPlan) {
                this.FOrderEntryPlan = FOrderEntryPlan;
            }

            public List<FTaxDetailSubEntityBean> getFTaxDetailSubEntity() {
                return FTaxDetailSubEntity;
            }

            public void setFTaxDetailSubEntity(List<FTaxDetailSubEntityBean> FTaxDetailSubEntity) {
                this.FTaxDetailSubEntity = FTaxDetailSubEntity;
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

            public static class FAuxPropIdBean {
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

            public static class FPurPriceUnitIdBean {
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

            public static class FSettleOrgIdsBean {
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

            public static class FSupplyOrgIdBean {
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

            public static class FOutLmtUnitIDBean {
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

            public static class FSOStockIdBean {
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

            public static class FSOStockLocalIdBean {
                /**
                 * FSOSTOCKLOCALID__FF100003 : {"FNumber":""}
                 * FSOSTOCKLOCALID__FF100004 : {"FNumber":""}
                 * FSOSTOCKLOCALID__FF100012 : {"FNumber":""}
                 * FSOSTOCKLOCALID__FF100013 : {"FNumber":""}
                 * FSOSTOCKLOCALID__FF100014 : {"FNumber":""}
                 * FSOSTOCKLOCALID__FF100015 : {"FNumber":""}
                 * FSOSTOCKLOCALID__FF100016 : {"FNumber":""}
                 * FSOSTOCKLOCALID__FF100017 : {"FNumber":""}
                 * FSOSTOCKLOCALID__FF100018 : {"FNumber":""}
                 * FSOSTOCKLOCALID__FF100019 : {"FNumber":""}
                 */

                private FSOSTOCKLOCALIDFF100003Bean FSOSTOCKLOCALID__FF100003;
                private FSOSTOCKLOCALIDFF100004Bean FSOSTOCKLOCALID__FF100004;
                private FSOSTOCKLOCALIDFF100012Bean FSOSTOCKLOCALID__FF100012;
                private FSOSTOCKLOCALIDFF100013Bean FSOSTOCKLOCALID__FF100013;
                private FSOSTOCKLOCALIDFF100014Bean FSOSTOCKLOCALID__FF100014;
                private FSOSTOCKLOCALIDFF100015Bean FSOSTOCKLOCALID__FF100015;
                private FSOSTOCKLOCALIDFF100016Bean FSOSTOCKLOCALID__FF100016;
                private FSOSTOCKLOCALIDFF100017Bean FSOSTOCKLOCALID__FF100017;
                private FSOSTOCKLOCALIDFF100018Bean FSOSTOCKLOCALID__FF100018;
                private FSOSTOCKLOCALIDFF100019Bean FSOSTOCKLOCALID__FF100019;

                public FSOSTOCKLOCALIDFF100003Bean getFSOSTOCKLOCALID__FF100003() {
                    return FSOSTOCKLOCALID__FF100003;
                }

                public void setFSOSTOCKLOCALID__FF100003(FSOSTOCKLOCALIDFF100003Bean FSOSTOCKLOCALID__FF100003) {
                    this.FSOSTOCKLOCALID__FF100003 = FSOSTOCKLOCALID__FF100003;
                }

                public FSOSTOCKLOCALIDFF100004Bean getFSOSTOCKLOCALID__FF100004() {
                    return FSOSTOCKLOCALID__FF100004;
                }

                public void setFSOSTOCKLOCALID__FF100004(FSOSTOCKLOCALIDFF100004Bean FSOSTOCKLOCALID__FF100004) {
                    this.FSOSTOCKLOCALID__FF100004 = FSOSTOCKLOCALID__FF100004;
                }

                public FSOSTOCKLOCALIDFF100012Bean getFSOSTOCKLOCALID__FF100012() {
                    return FSOSTOCKLOCALID__FF100012;
                }

                public void setFSOSTOCKLOCALID__FF100012(FSOSTOCKLOCALIDFF100012Bean FSOSTOCKLOCALID__FF100012) {
                    this.FSOSTOCKLOCALID__FF100012 = FSOSTOCKLOCALID__FF100012;
                }

                public FSOSTOCKLOCALIDFF100013Bean getFSOSTOCKLOCALID__FF100013() {
                    return FSOSTOCKLOCALID__FF100013;
                }

                public void setFSOSTOCKLOCALID__FF100013(FSOSTOCKLOCALIDFF100013Bean FSOSTOCKLOCALID__FF100013) {
                    this.FSOSTOCKLOCALID__FF100013 = FSOSTOCKLOCALID__FF100013;
                }

                public FSOSTOCKLOCALIDFF100014Bean getFSOSTOCKLOCALID__FF100014() {
                    return FSOSTOCKLOCALID__FF100014;
                }

                public void setFSOSTOCKLOCALID__FF100014(FSOSTOCKLOCALIDFF100014Bean FSOSTOCKLOCALID__FF100014) {
                    this.FSOSTOCKLOCALID__FF100014 = FSOSTOCKLOCALID__FF100014;
                }

                public FSOSTOCKLOCALIDFF100015Bean getFSOSTOCKLOCALID__FF100015() {
                    return FSOSTOCKLOCALID__FF100015;
                }

                public void setFSOSTOCKLOCALID__FF100015(FSOSTOCKLOCALIDFF100015Bean FSOSTOCKLOCALID__FF100015) {
                    this.FSOSTOCKLOCALID__FF100015 = FSOSTOCKLOCALID__FF100015;
                }

                public FSOSTOCKLOCALIDFF100016Bean getFSOSTOCKLOCALID__FF100016() {
                    return FSOSTOCKLOCALID__FF100016;
                }

                public void setFSOSTOCKLOCALID__FF100016(FSOSTOCKLOCALIDFF100016Bean FSOSTOCKLOCALID__FF100016) {
                    this.FSOSTOCKLOCALID__FF100016 = FSOSTOCKLOCALID__FF100016;
                }

                public FSOSTOCKLOCALIDFF100017Bean getFSOSTOCKLOCALID__FF100017() {
                    return FSOSTOCKLOCALID__FF100017;
                }

                public void setFSOSTOCKLOCALID__FF100017(FSOSTOCKLOCALIDFF100017Bean FSOSTOCKLOCALID__FF100017) {
                    this.FSOSTOCKLOCALID__FF100017 = FSOSTOCKLOCALID__FF100017;
                }

                public FSOSTOCKLOCALIDFF100018Bean getFSOSTOCKLOCALID__FF100018() {
                    return FSOSTOCKLOCALID__FF100018;
                }

                public void setFSOSTOCKLOCALID__FF100018(FSOSTOCKLOCALIDFF100018Bean FSOSTOCKLOCALID__FF100018) {
                    this.FSOSTOCKLOCALID__FF100018 = FSOSTOCKLOCALID__FF100018;
                }

                public FSOSTOCKLOCALIDFF100019Bean getFSOSTOCKLOCALID__FF100019() {
                    return FSOSTOCKLOCALID__FF100019;
                }

                public void setFSOSTOCKLOCALID__FF100019(FSOSTOCKLOCALIDFF100019Bean FSOSTOCKLOCALID__FF100019) {
                    this.FSOSTOCKLOCALID__FF100019 = FSOSTOCKLOCALID__FF100019;
                }

                public static class FSOSTOCKLOCALIDFF100003Bean {
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

                public static class FSOSTOCKLOCALIDFF100004Bean {
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

                public static class FSOSTOCKLOCALIDFF100012Bean {
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

                public static class FSOSTOCKLOCALIDFF100013Bean {
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

                public static class FSOSTOCKLOCALIDFF100014Bean {
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

                public static class FSOSTOCKLOCALIDFF100015Bean {
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

                public static class FSOSTOCKLOCALIDFF100016Bean {
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

                public static class FSOSTOCKLOCALIDFF100017Bean {
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

                public static class FSOSTOCKLOCALIDFF100018Bean {
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

                public static class FSOSTOCKLOCALIDFF100019Bean {
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

            public static class FOrderEntryPlanBean {
                /**
                 * FDetailID : 0
                 * FDetailLocId : {"FNumber":""}
                 * FDetailLocAddress :
                 * FPlanDate : 1900-01-01
                 * FTransportLeadTime : 0
                 * FPlanQty : 0
                 */

                private String FDetailID;
                private FDetailLocIdBean FDetailLocId;
                private String FDetailLocAddress;
                private String FPlanDate;
                private String FTransportLeadTime;
                private String FPlanQty;

                public String getFDetailID() {
                    return FDetailID;
                }

                public void setFDetailID(String FDetailID) {
                    this.FDetailID = FDetailID;
                }

                public FDetailLocIdBean getFDetailLocId() {
                    return FDetailLocId;
                }

                public void setFDetailLocId(FDetailLocIdBean FDetailLocId) {
                    this.FDetailLocId = FDetailLocId;
                }

                public String getFDetailLocAddress() {
                    return FDetailLocAddress;
                }

                public void setFDetailLocAddress(String FDetailLocAddress) {
                    this.FDetailLocAddress = FDetailLocAddress;
                }

                public String getFPlanDate() {
                    return FPlanDate;
                }

                public void setFPlanDate(String FPlanDate) {
                    this.FPlanDate = FPlanDate;
                }

                public String getFTransportLeadTime() {
                    return FTransportLeadTime;
                }

                public void setFTransportLeadTime(String FTransportLeadTime) {
                    this.FTransportLeadTime = FTransportLeadTime;
                }

                public String getFPlanQty() {
                    return FPlanQty;
                }

                public void setFPlanQty(String FPlanQty) {
                    this.FPlanQty = FPlanQty;
                }

                public static class FDetailLocIdBean {
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
                 * FTaxRate : 0
                 * FSellerWithholding : false
                 * FBuyerWithholding : false
                 */

                private String FDetailID;
                private String FTaxRate;
                private String FSellerWithholding;
                private String FBuyerWithholding;

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

                public String getFSellerWithholding() {
                    return FSellerWithholding;
                }

                public void setFSellerWithholding(String FSellerWithholding) {
                    this.FSellerWithholding = FSellerWithholding;
                }

                public String getFBuyerWithholding() {
                    return FBuyerWithholding;
                }

                public void setFBuyerWithholding(String FBuyerWithholding) {
                    this.FBuyerWithholding = FBuyerWithholding;
                }
            }
        }

        public static class FSaleOrderPlanBean {
            /**
             * FEntryID : 0
             * FNeedRecAdvance : false
             * FReceiveType : {"FNumber":""}
             * FRecAdvanceRate : 0
             * FRecAdvanceAmount : 0
             * FMustDate : 1900-01-01
             * FRelBillNo :
             * FRecAmount : 0
             * FControlSend :
             * FReMark :
             * FPlanMaterialId : {"FNUMBER":""}
             * FMaterialSeq : 0
             * FOrderEntryId : 0
             * FSaleOrderPlanEntry : [{"FDETAILID":"0","FPESettleOrgId":{"FNumber":""}}]
             */

            private String FEntryID;
            private String FNeedRecAdvance;
            private FReceiveTypeBean FReceiveType;
            private String FRecAdvanceRate;
            private String FRecAdvanceAmount;
            private String FMustDate;
            private String FRelBillNo;
            private String FRecAmount;
            private String FControlSend;
            private String FReMark;
            private FPlanMaterialIdBean FPlanMaterialId;
            private String FMaterialSeq;
            private String FOrderEntryId;
            private List<FSaleOrderPlanEntryBean> FSaleOrderPlanEntry;

            public String getFEntryID() {
                return FEntryID;
            }

            public void setFEntryID(String FEntryID) {
                this.FEntryID = FEntryID;
            }

            public String getFNeedRecAdvance() {
                return FNeedRecAdvance;
            }

            public void setFNeedRecAdvance(String FNeedRecAdvance) {
                this.FNeedRecAdvance = FNeedRecAdvance;
            }

            public FReceiveTypeBean getFReceiveType() {
                return FReceiveType;
            }

            public void setFReceiveType(FReceiveTypeBean FReceiveType) {
                this.FReceiveType = FReceiveType;
            }

            public String getFRecAdvanceRate() {
                return FRecAdvanceRate;
            }

            public void setFRecAdvanceRate(String FRecAdvanceRate) {
                this.FRecAdvanceRate = FRecAdvanceRate;
            }

            public String getFRecAdvanceAmount() {
                return FRecAdvanceAmount;
            }

            public void setFRecAdvanceAmount(String FRecAdvanceAmount) {
                this.FRecAdvanceAmount = FRecAdvanceAmount;
            }

            public String getFMustDate() {
                return FMustDate;
            }

            public void setFMustDate(String FMustDate) {
                this.FMustDate = FMustDate;
            }

            public String getFRelBillNo() {
                return FRelBillNo;
            }

            public void setFRelBillNo(String FRelBillNo) {
                this.FRelBillNo = FRelBillNo;
            }

            public String getFRecAmount() {
                return FRecAmount;
            }

            public void setFRecAmount(String FRecAmount) {
                this.FRecAmount = FRecAmount;
            }

            public String getFControlSend() {
                return FControlSend;
            }

            public void setFControlSend(String FControlSend) {
                this.FControlSend = FControlSend;
            }

            public String getFReMark() {
                return FReMark;
            }

            public void setFReMark(String FReMark) {
                this.FReMark = FReMark;
            }

            public FPlanMaterialIdBean getFPlanMaterialId() {
                return FPlanMaterialId;
            }

            public void setFPlanMaterialId(FPlanMaterialIdBean FPlanMaterialId) {
                this.FPlanMaterialId = FPlanMaterialId;
            }

            public String getFMaterialSeq() {
                return FMaterialSeq;
            }

            public void setFMaterialSeq(String FMaterialSeq) {
                this.FMaterialSeq = FMaterialSeq;
            }

            public String getFOrderEntryId() {
                return FOrderEntryId;
            }

            public void setFOrderEntryId(String FOrderEntryId) {
                this.FOrderEntryId = FOrderEntryId;
            }

            public List<FSaleOrderPlanEntryBean> getFSaleOrderPlanEntry() {
                return FSaleOrderPlanEntry;
            }

            public void setFSaleOrderPlanEntry(List<FSaleOrderPlanEntryBean> FSaleOrderPlanEntry) {
                this.FSaleOrderPlanEntry = FSaleOrderPlanEntry;
            }

            public static class FReceiveTypeBean {
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

            public static class FPlanMaterialIdBean {
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

            public static class FSaleOrderPlanEntryBean {
                /**
                 * FDETAILID : 0
                 * FPESettleOrgId : {"FNumber":""}
                 */

                private String FDETAILID;
                private FPESettleOrgIdBean FPESettleOrgId;

                public String getFDETAILID() {
                    return FDETAILID;
                }

                public void setFDETAILID(String FDETAILID) {
                    this.FDETAILID = FDETAILID;
                }

                public FPESettleOrgIdBean getFPESettleOrgId() {
                    return FPESettleOrgId;
                }

                public void setFPESettleOrgId(FPESettleOrgIdBean FPESettleOrgId) {
                    this.FPESettleOrgId = FPESettleOrgId;
                }

                public static class FPESettleOrgIdBean {
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

        public static class FSalOrderTraceBean {
            /**
             * FEntryID : 0
             * FLogComId : {"FCODE":""}
             * FCarryBillNo :
             * FDelTime : 1900-01-01
             * FTraceStatus :
             * FSalOrderTraceDetail : [{"FDetailID":"0","FTraceTime":"","FTraceDetail":""}]
             */

            private String FEntryID;
            private FLogComIdBean FLogComId;
            private String FCarryBillNo;
            private String FDelTime;
            private String FTraceStatus;
            private List<FSalOrderTraceDetailBean> FSalOrderTraceDetail;

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

            public List<FSalOrderTraceDetailBean> getFSalOrderTraceDetail() {
                return FSalOrderTraceDetail;
            }

            public void setFSalOrderTraceDetail(List<FSalOrderTraceDetailBean> FSalOrderTraceDetail) {
                this.FSalOrderTraceDetail = FSalOrderTraceDetail;
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

            public static class FSalOrderTraceDetailBean {
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
