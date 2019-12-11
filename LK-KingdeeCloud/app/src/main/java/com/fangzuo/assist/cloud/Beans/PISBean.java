package com.fangzuo.assist.cloud.Beans;


import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.Dao.T_main;

import java.util.ArrayList;
import java.util.List;

public class PISBean {

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
     * Model : {"FID":"0","FBillTypeID":{"FNUMBER":""},"FBillNo":"","FDate":"1900-01-01","FStockOrgId":{"FNumber":""},"FStockDeptId":{"FNumber":""},"FStockerGroupId":{"FNumber":""},"FStockerId":{"FNumber":""},"FDemandOrgId":{"FNumber":""},"FPurchaseOrgId":{"FNumber":""},"FCorrespondOrgId":{"FNumber":""},"FPurchaseDeptId":{"FNumber":""},"FPurchaserGroupId":{"FNumber":""},"FPurchaserId":{"FNumber":""},"FSupplierId":{"FNumber":""},"FSupplyId":{"FNumber":""},"FSupplyAddress":"","FSettleId":{"FNumber":""},"FChargeId":{"FNumber":""},"FOwnerTypeIdHead":"","FOwnerIdHead":{"FNumber":""},"FConfirmerId":{"FUserID":""},"FConfirmDate":"1900-01-01","FScanBox":"","FCDateOffsetUnit":"","FCDateOffsetValue":"0","FProviderContactID":{"FCONTACTNUMBER":""},"FInStockFin":{"FEntryId":"0","FSettleOrgId":{"FNumber":""},"FSettleTypeId":{"FNumber":""},"FPayConditionId":{"FNumber":""},"FSettleCurrId":{"FNumber":""},"FIsIncludedTax":"false","FPriceTimePoint":"","FPriceListId":{"FNumber":""},"FDiscountListId":{"FNumber":""},"FLocalCurrId":{"FNumber":""},"FExchangeTypeId":{"FNumber":""},"FExchangeRate":"0","FISPRICEEXCLUDETAX":"false"},"FInStockEntry":[{"FEntryID":"0","FRowType":"","FWWInType":"","FMaterialId":{"FNumber":""},"FUnitID":{"FNumber":""},"FAuxPropId":{},"FParentMatId":{"FNUMBER":""},"FRealQty":"0","FPriceUnitID":{"FNumber":""},"FPrice":"0","FTaxCombination":{"FNumber":""},"FLot":{"FNumber":""},"FStockId":{"FNumber":""},"FDisPriceQty":"0","FStockLocId":{"FSTOCKLOCID__FF100003":{"FNumber":""},"FSTOCKLOCID__FF100004":{"FNumber":""},"FSTOCKLOCID__FF100012":{"FNumber":""},"FSTOCKLOCID__FF100013":{"FNumber":""},"FSTOCKLOCID__FF100014":{"FNumber":""},"FSTOCKLOCID__FF100015":{"FNumber":""},"FSTOCKLOCID__FF100016":{"FNumber":""},"FSTOCKLOCID__FF100017":{"FNumber":""},"FSTOCKLOCID__FF100018":{"FNumber":""},"FSTOCKLOCID__FF100019":{"FNumber":""}},"FStockStatusId":{"FNumber":""},"FMtoNo":"","FGiveAway":"false","FNote":"","FProduceDate":"1900-01-01","FExtAuxUnitId":{"FNumber":""},"FExtAuxUnitQty":"0","FCheckInComing":"false","FProjectNo":"","FSampleDamageQty":"0","FSampleDamageBaseQty":"0","FIsReceiveUpdateStock":"false","FInvoicedJoinQty":"0","FPriceBaseQty":"0","FSetPriceUnitID":{"FNumber":""},"FRemainInStockUnitId":{"FNumber":""},"FBILLINGCLOSE":"false","FRemainInStockQty":"0","FAPNotJoinQty":"0","FRemainInStockBaseQty":"0","FTaxPrice":"0","FEntryTaxRate":"0","FDiscountRate":"0","FCostPrice":"0","FBOMId":{"FNumber":""},"FSupplierLot":"","FExpiryDate":"1900-01-01","FAuxUnitQty":"0","FBeforeDisPriceQty":"0","FEntryPruCost":[{"FDetailID":"0"}],"FTaxDetailSubEntity":[{"FDetailID":"0","FTaxRate":"0"}],"FSerialSubEntity":[{"FDetailID":"0","FSerialNo":"","FSerialNote":""}]}]}
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
         * FStockOrgId : {"FNumber":""}
         * FStockDeptId : {"FNumber":""}
         * FStockerGroupId : {"FNumber":""}
         * FStockerId : {"FNumber":""}
         * FDemandOrgId : {"FNumber":""}
         * FPurchaseOrgId : {"FNumber":""}
         * FCorrespondOrgId : {"FNumber":""}
         * FPurchaseDeptId : {"FNumber":""}
         * FPurchaserGroupId : {"FNumber":""}
         * FPurchaserId : {"FNumber":""}
         * FSupplierId : {"FNumber":""}
         * FSupplyId : {"FNumber":""}
         * FSupplyAddress :
         * FSettleId : {"FNumber":""}
         * FChargeId : {"FNumber":""}
         * FOwnerTypeIdHead :
         * FOwnerIdHead : {"FNumber":""}
         * FConfirmerId : {"FUserID":""}
         * FConfirmDate : 1900-01-01
         * FScanBox :
         * FCDateOffsetUnit :
         * FCDateOffsetValue : 0
         * FProviderContactID : {"FCONTACTNUMBER":""}
         * FInStockFin : {"FEntryId":"0","FSettleOrgId":{"FNumber":""},"FSettleTypeId":{"FNumber":""},"FPayConditionId":{"FNumber":""},"FSettleCurrId":{"FNumber":""},"FIsIncludedTax":"false","FPriceTimePoint":"","FPriceListId":{"FNumber":""},"FDiscountListId":{"FNumber":""},"FLocalCurrId":{"FNumber":""},"FExchangeTypeId":{"FNumber":""},"FExchangeRate":"0","FISPRICEEXCLUDETAX":"false"}
         * FInStockEntry : [{"FEntryID":"0","FRowType":"","FWWInType":"","FMaterialId":{"FNumber":""},"FUnitID":{"FNumber":""},"FAuxPropId":{},"FParentMatId":{"FNUMBER":""},"FRealQty":"0","FPriceUnitID":{"FNumber":""},"FPrice":"0","FTaxCombination":{"FNumber":""},"FLot":{"FNumber":""},"FStockId":{"FNumber":""},"FDisPriceQty":"0","FStockLocId":{"FSTOCKLOCID__FF100003":{"FNumber":""},"FSTOCKLOCID__FF100004":{"FNumber":""},"FSTOCKLOCID__FF100012":{"FNumber":""},"FSTOCKLOCID__FF100013":{"FNumber":""},"FSTOCKLOCID__FF100014":{"FNumber":""},"FSTOCKLOCID__FF100015":{"FNumber":""},"FSTOCKLOCID__FF100016":{"FNumber":""},"FSTOCKLOCID__FF100017":{"FNumber":""},"FSTOCKLOCID__FF100018":{"FNumber":""},"FSTOCKLOCID__FF100019":{"FNumber":""}},"FStockStatusId":{"FNumber":""},"FMtoNo":"","FGiveAway":"false","FNote":"","FProduceDate":"1900-01-01","FExtAuxUnitId":{"FNumber":""},"FExtAuxUnitQty":"0","FCheckInComing":"false","FProjectNo":"","FSampleDamageQty":"0","FSampleDamageBaseQty":"0","FIsReceiveUpdateStock":"false","FInvoicedJoinQty":"0","FPriceBaseQty":"0","FSetPriceUnitID":{"FNumber":""},"FRemainInStockUnitId":{"FNumber":""},"FBILLINGCLOSE":"false","FRemainInStockQty":"0","FAPNotJoinQty":"0","FRemainInStockBaseQty":"0","FTaxPrice":"0","FEntryTaxRate":"0","FDiscountRate":"0","FCostPrice":"0","FBOMId":{"FNumber":""},"FSupplierLot":"","FExpiryDate":"1900-01-01","FAuxUnitQty":"0","FBeforeDisPriceQty":"0","FEntryPruCost":[{"FDetailID":"0"}],"FTaxDetailSubEntity":[{"FDetailID":"0","FTaxRate":"0"}],"FSerialSubEntity":[{"FDetailID":"0","FSerialNo":"","FSerialNote":""}]}]
         */

        private String FID;//
        private FBillTypeIDBean FBillTypeID;//单据类型
        private String FBillNo;//单据编号
        private FPurchaseOrgIdBean FPurchaseOrgId;//采购组织
        private FPurchaseDeptIdBean FPurchaseDeptId;//采购部门
        private FStockOrgIdBean FStockOrgId;//收料组织
        private FStockDeptIdBean FStockDeptId;////收料部门
        private FStockerGroupIdBean FStockerGroupId;//库存组
        private FStockerIdBean FStockerId;//仓管员
        private FDemandOrgIdBean FDemandOrgId;//需求组织
        private FCorrespondOrgIdBean FCorrespondOrgId;//对应组织
        private FPurchaserGroupIdBean FPurchaserGroupId;//采购组
        private FPurchaserIdBean FPurchaserId;//采购员
        private FSupplierIdBean FSupplierId;//供应商
        private FSupplyIdBean FSupplyId;//供货方
        private String FSupplyAddress;//供货方地址
        private FSettleIdBean FSettleId;//结算方
        private FChargeIdBean FChargeId;//收款方
        private String FOwnerTypeIdHead;//货主类型
        private FOwnerIdHeadBean FOwnerIdHead;//货主
        private String FDate;//入库日期
        private FConfirmerIdBean FConfirmerId;//确认人
        private String FConfirmDate;//确认日期
        private String FScanBox;//序列号上传
        private String FCDateOffsetUnit;//创建日期偏移单位
        private String FCDateOffsetValue;//创建日期偏移量
        private FProviderContactIDBean FProviderContactID;//供货方联系人
        private FInStockFinBean FInStockFin;//一个大类
        private List<FInStockEntryBean> FInStockEntry;//一个数组

        public void setModelBean(T_main main, List<T_Detail> beans){
            //表头设置-------------------------------------------------------------
            this.FBillNo ="";
            this.FBillTypeID=new FBillTypeIDBean(main.FBillTypeID);//单据类型
            this.FStockOrgId=new FStockOrgIdBean(main.FStockOrgId);             //收料组织
            this.FPurchaseOrgId=new FPurchaseOrgIdBean(main.FPurchaseOrgId);    //采购组织
            this.FStockDeptId = new FStockDeptIdBean(main.FDepartmentNumber);//收料部门
            this.FPurchaseDeptId = new FPurchaseDeptIdBean(main.FPurchaseDeptId);//采购部门
            this.FStockerId = new FStockerIdBean(main.FStockerNumber);       //仓管员
            this.FPurchaserId = new FPurchaserIdBean(main.FPurchaserId);       //采购员
            this.FOwnerTypeIdHead = main.FOwnerTypeIdHead;//货主类型
            this.FOwnerIdHead=new FOwnerIdHeadBean(main.FOwnerIdHead);//货主
            this.FDate = main.FDate;//入库日期
            this.FSupplierId=new FSupplierIdBean(main.FSupplierId);//供应商

            this.FInStockFin=new FInStockFinBean();//一个大类
            this.FInStockFin.FPriceTimePoint= main.FPriceTimePoint;         //定价时点
            this.FInStockFin.FSettleOrgId=new FInStockFinBean.FSettleOrgIdBean(main.FSettleOrgId);//结算组织
            this.FInStockFin.FSettleCurrId=new FInStockFinBean.FSettleCurrIdBean(main.FSettleCurrId);//结算币别
            //----------------------------------------------------------------
            //表体设置----------------------------------------------------------------
            this.FInStockEntry=new ArrayList<>();//一个大类数组
            for (int i = 0; i < beans.size(); i++) {
                FInStockEntryBean bean1=new FInStockEntryBean();//一个大类
                bean1.FMaterialId = new FInStockEntryBean.FMaterialIdBean(beans.get(i).FMaterialId);//物料编码
                bean1.FStockId=new FInStockEntryBean.FStockIdBean(beans.get(i).FStorageId);//仓库
                bean1.FLot=new FInStockEntryBean.FLotBean(beans.get(i).FBatch);//批号
                bean1.FRemainInStockQty=beans.get(i).FRemainInStockQty;        //采购数量
                bean1.FRealQty=beans.get(i).FRealQty;         //实收数量
                bean1.FRemainInStockUnitId = new FInStockEntryBean.FRemainInStockUnitIdBean(beans.get(i).FRemainInStockUnitId);//采购单位
                bean1.FPriceUnitID = new FInStockEntryBean.FPriceUnitIDBean(beans.get(i).FPriceUnitID);//计价单位
                bean1.FUnitID = new FInStockEntryBean.FUnitIDBean(beans.get(i).FUnitID);//库存单位
                bean1.FGiveAway = beans.get(i).FIsFree;//库存单位
                this.FInStockEntry.add(bean1);//添加进数组
            }
        }

        @Override
        public String toString() {
            return "ModelBean{" +
                    "FID='" + FID + '\'' +
                    ", FBillTypeID=" + FBillTypeID +
                    ", FBillNo='" + FBillNo + '\'' +
                    ", FDate='" + FDate + '\'' +
                    ", FStockOrgId=" + FStockOrgId +
                    ", FStockDeptId=" + FStockDeptId +
                    ", FStockerGroupId=" + FStockerGroupId +
                    ", FStockerId=" + FStockerId +
                    ", FDemandOrgId=" + FDemandOrgId +
                    ", FPurchaseOrgId=" + FPurchaseOrgId +
                    ", FCorrespondOrgId=" + FCorrespondOrgId +
                    ", FPurchaseDeptId=" + FPurchaseDeptId +
                    ", FPurchaserGroupId=" + FPurchaserGroupId +
                    ", FPurchaserId=" + FPurchaserId +
                    ", FSupplierId=" + FSupplierId +
                    ", FSupplyId=" + FSupplyId +
                    ", FSupplyAddress='" + FSupplyAddress + '\'' +
                    ", FSettleId=" + FSettleId +
                    ", FChargeId=" + FChargeId +
                    ", FOwnerTypeIdHead='" + FOwnerTypeIdHead + '\'' +
                    ", FOwnerIdHead=" + FOwnerIdHead +
                    ", FConfirmerId=" + FConfirmerId +
                    ", FConfirmDate='" + FConfirmDate + '\'' +
                    ", FScanBox='" + FScanBox + '\'' +
                    ", FCDateOffsetUnit='" + FCDateOffsetUnit + '\'' +
                    ", FCDateOffsetValue='" + FCDateOffsetValue + '\'' +
                    ", FProviderContactID=" + FProviderContactID +
                    ", FInStockFin=" + FInStockFin +
                    ", FInStockEntry=" + FInStockEntry +
                    '}';
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

        public FDemandOrgIdBean getFDemandOrgId() {
            return FDemandOrgId;
        }

        public void setFDemandOrgId(FDemandOrgIdBean FDemandOrgId) {
            this.FDemandOrgId = FDemandOrgId;
        }

        public FPurchaseOrgIdBean getFPurchaseOrgId() {
            return FPurchaseOrgId;
        }

        public void setFPurchaseOrgId(FPurchaseOrgIdBean FPurchaseOrgId) {
            this.FPurchaseOrgId = FPurchaseOrgId;
        }

        public FCorrespondOrgIdBean getFCorrespondOrgId() {
            return FCorrespondOrgId;
        }

        public void setFCorrespondOrgId(FCorrespondOrgIdBean FCorrespondOrgId) {
            this.FCorrespondOrgId = FCorrespondOrgId;
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

        public FSupplierIdBean getFSupplierId() {
            return FSupplierId;
        }

        public void setFSupplierId(FSupplierIdBean FSupplierId) {
            this.FSupplierId = FSupplierId;
        }

        public FSupplyIdBean getFSupplyId() {
            return FSupplyId;
        }

        public void setFSupplyId(FSupplyIdBean FSupplyId) {
            this.FSupplyId = FSupplyId;
        }

        public String getFSupplyAddress() {
            return FSupplyAddress;
        }

        public void setFSupplyAddress(String FSupplyAddress) {
            this.FSupplyAddress = FSupplyAddress;
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

        public FProviderContactIDBean getFProviderContactID() {
            return FProviderContactID;
        }

        public void setFProviderContactID(FProviderContactIDBean FProviderContactID) {
            this.FProviderContactID = FProviderContactID;
        }

        public FInStockFinBean getFInStockFin() {
            return FInStockFin;
        }

        public void setFInStockFin(FInStockFinBean FInStockFin) {
            this.FInStockFin = FInStockFin;
        }

        public List<FInStockEntryBean> getFInStockEntry() {
            return FInStockEntry;
        }

        public void setFInStockEntry(List<FInStockEntryBean> FInStockEntry) {
            this.FInStockEntry = FInStockEntry;
        }

        public static class FBillTypeIDBean {
            @Override
            public String toString() {
                return "FBillTypeIDBean{" +
                        "FNUMBER='" + FNUMBER + '\'' +
                        '}';
            }

            public FBillTypeIDBean(String FNUMBER) {
                this.FNUMBER = FNUMBER;
            }

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

        public static class FStockOrgIdBean {
            @Override
            public String toString() {
                return "FStockOrgIdBean{" +
                        "FNumber='" + FNumber + '\'' +
                        '}';
            }

            public FStockOrgIdBean(String FNumber) {
                this.FNumber = FNumber;
            }

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
            @Override
            public String toString() {
                return "FStockDeptIdBean{" +
                        "FNumber='" + FNumber + '\'' +
                        '}';
            }

            /**
             * FNumber :
             */
            public FStockDeptIdBean(String FNumber) {
                this.FNumber = FNumber;
            }
            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FStockerGroupIdBean {
            @Override
            public String toString() {
                return "FStockerGroupIdBean{" +
                        "FNumber='" + FNumber + '\'' +
                        '}';
            }

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
            @Override
            public String toString() {
                return "FStockerIdBean{" +
                        "FNumber='" + FNumber + '\'' +
                        '}';
            }

            /**
             * FNumber :
             */

            private String FNumber;

            public FStockerIdBean(String FNumber) {
                this.FNumber = FNumber;
            }

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FDemandOrgIdBean {
            @Override
            public String toString() {
                return "FDemandOrgIdBean{" +
                        "FNumber='" + FNumber + '\'' +
                        '}';
            }

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
            @Override
            public String toString() {
                return "FPurchaseOrgIdBean{" +
                        "FNumber='" + FNumber + '\'' +
                        '}';
            }

            public FPurchaseOrgIdBean(String FNumber) {
                this.FNumber = FNumber;
            }

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
            @Override
            public String toString() {
                return "FCorrespondOrgIdBean{" +
                        "FNumber='" + FNumber + '\'' +
                        '}';
            }

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
            @Override
            public String toString() {
                return "FPurchaseDeptIdBean{" +
                        "FNumber='" + FNumber + '\'' +
                        '}';
            }

            public FPurchaseDeptIdBean(String FNumber) {
                this.FNumber = FNumber;
            }

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
            @Override
            public String toString() {
                return "FPurchaserGroupIdBean{" +
                        "FNumber='" + FNumber + '\'' +
                        '}';
            }

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
            @Override
            public String toString() {
                return "FPurchaserIdBean{" +
                        "FNumber='" + FNumber + '\'' +
                        '}';
            }

            public FPurchaserIdBean(String FNumber) {
                this.FNumber = FNumber;
            }

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

        public static class FSupplierIdBean {
            @Override
            public String toString() {
                return "FSupplierIdBean{" +
                        "FNumber='" + FNumber + '\'' +
                        '}';
            }

            public FSupplierIdBean(String FNumber) {
                this.FNumber = FNumber;
            }

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

        public static class FSupplyIdBean {
            @Override
            public String toString() {
                return "FSupplyIdBean{" +
                        "FNumber='" + FNumber + '\'' +
                        '}';
            }

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
            @Override
            public String toString() {
                return "FSettleIdBean{" +
                        "FNumber='" + FNumber + '\'' +
                        '}';
            }

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
            @Override
            public String toString() {
                return "FChargeIdBean{" +
                        "FNumber='" + FNumber + '\'' +
                        '}';
            }

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
            @Override
            public String toString() {
                return "FOwnerIdHeadBean{" +
                        "FNumber='" + FNumber + '\'' +
                        '}';
            }

            public FOwnerIdHeadBean(String FNumber) {
                this.FNumber = FNumber;
            }

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
            @Override
            public String toString() {
                return "FConfirmerIdBean{" +
                        "FUserID='" + FUserID + '\'' +
                        '}';
            }

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

        public static class FProviderContactIDBean {
            @Override
            public String toString() {
                return "FProviderContactIDBean{" +
                        "FCONTACTNUMBER='" + FCONTACTNUMBER + '\'' +
                        '}';
            }

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

        public static class FInStockFinBean {
            @Override
            public String toString() {
                return "FInStockFinBean{" +
                        "FEntryId='" + FEntryId + '\'' +
                        ", FSettleOrgId=" + FSettleOrgId +
                        ", FSettleTypeId=" + FSettleTypeId +
                        ", FPayConditionId=" + FPayConditionId +
                        ", FSettleCurrId=" + FSettleCurrId +
                        ", FIsIncludedTax='" + FIsIncludedTax + '\'' +
                        ", FPriceTimePoint='" + FPriceTimePoint + '\'' +
                        ", FPriceListId=" + FPriceListId +
                        ", FDiscountListId=" + FDiscountListId +
                        ", FLocalCurrId=" + FLocalCurrId +
                        ", FExchangeTypeId=" + FExchangeTypeId +
                        ", FExchangeRate='" + FExchangeRate + '\'' +
                        ", FISPRICEEXCLUDETAX='" + FISPRICEEXCLUDETAX + '\'' +
                        '}';
            }

            /**
             * FEntryId : 0
             * FSettleOrgId : {"FNumber":""}
             * FSettleTypeId : {"FNumber":""}
             * FPayConditionId : {"FNumber":""}
             * FSettleCurrId : {"FNumber":""}
             * FIsIncludedTax : false
             * FPriceTimePoint :
             * FPriceListId : {"FNumber":""}
             * FDiscountListId : {"FNumber":""}
             * FLocalCurrId : {"FNumber":""}
             * FExchangeTypeId : {"FNumber":""}
             * FExchangeRate : 0
             * FISPRICEEXCLUDETAX : false
             */

            private String FEntryId;//
            private FSettleOrgIdBean FSettleOrgId;//结算组织
            private FSettleTypeIdBean FSettleTypeId;//结算方式
            private FPayConditionIdBean FPayConditionId;//付款条件
            private FSettleCurrIdBean FSettleCurrId;//结算币别
            private String FIsIncludedTax;//含税
            private String FPriceTimePoint;//定价时点
            private FPriceListIdBean FPriceListId;//价目表
            private FDiscountListIdBean FDiscountListId;//折扣表
            private FLocalCurrIdBean FLocalCurrId;//本位币
            private FExchangeTypeIdBean FExchangeTypeId;//汇率类型
            private String FExchangeRate;//汇率
            private String FISPRICEEXCLUDETAX;//价外税

            public String getFEntryId() {
                return FEntryId;
            }

            public void setFEntryId(String FEntryId) {
                this.FEntryId = FEntryId;
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

            public FPayConditionIdBean getFPayConditionId() {
                return FPayConditionId;
            }

            public void setFPayConditionId(FPayConditionIdBean FPayConditionId) {
                this.FPayConditionId = FPayConditionId;
            }

            public FSettleCurrIdBean getFSettleCurrId() {
                return FSettleCurrId;
            }

            public void setFSettleCurrId(FSettleCurrIdBean FSettleCurrId) {
                this.FSettleCurrId = FSettleCurrId;
            }

            public String getFIsIncludedTax() {
                return FIsIncludedTax;
            }

            public void setFIsIncludedTax(String FIsIncludedTax) {
                this.FIsIncludedTax = FIsIncludedTax;
            }

            public String getFPriceTimePoint() {
                return FPriceTimePoint;
            }

            public void setFPriceTimePoint(String FPriceTimePoint) {
                this.FPriceTimePoint = FPriceTimePoint;
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

            public String getFISPRICEEXCLUDETAX() {
                return FISPRICEEXCLUDETAX;
            }

            public void setFISPRICEEXCLUDETAX(String FISPRICEEXCLUDETAX) {
                this.FISPRICEEXCLUDETAX = FISPRICEEXCLUDETAX;
            }

            public static class FSettleOrgIdBean {
                @Override
                public String toString() {
                    return "FSettleOrgIdBean{" +
                            "FNumber='" + FNumber + '\'' +
                            '}';
                }

                public FSettleOrgIdBean(String FNumber) {
                    this.FNumber = FNumber;
                }

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
                @Override
                public String toString() {
                    return "FSettleTypeIdBean{" +
                            "FNumber='" + FNumber + '\'' +
                            '}';
                }

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
                @Override
                public String toString() {
                    return "FPayConditionIdBean{" +
                            "FNumber='" + FNumber + '\'' +
                            '}';
                }

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

            public static class FSettleCurrIdBean {
                @Override
                public String toString() {
                    return "FSettleCurrIdBean{" +
                            "FNumber='" + FNumber + '\'' +
                            '}';
                }

                public FSettleCurrIdBean(String FNumber) {
                    this.FNumber = FNumber;
                }

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
                @Override
                public String toString() {
                    return "FPriceListIdBean{" +
                            "FNumber='" + FNumber + '\'' +
                            '}';
                }

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
                @Override
                public String toString() {
                    return "FDiscountListIdBean{" +
                            "FNumber='" + FNumber + '\'' +
                            '}';
                }

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
                @Override
                public String toString() {
                    return "FLocalCurrIdBean{" +
                            "FNumber='" + FNumber + '\'' +
                            '}';
                }

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
                @Override
                public String toString() {
                    return "FExchangeTypeIdBean{" +
                            "FNumber='" + FNumber + '\'' +
                            '}';
                }

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

        public static class FInStockEntryBean {
            @Override
            public String toString() {
                return "FInStockEntryBean{" +
                        "FEntryID='" + FEntryID + '\'' +
                        ", FRowType='" + FRowType + '\'' +
                        ", FWWInType='" + FWWInType + '\'' +
                        ", FMaterialId=" + FMaterialId +
                        ", FUnitID=" + FUnitID +
                        ", FAuxPropId=" + FAuxPropId +
                        ", FParentMatId=" + FParentMatId +
                        ", FRealQty='" + FRealQty + '\'' +
                        ", FPriceUnitID=" + FPriceUnitID +
                        ", FPrice='" + FPrice + '\'' +
                        ", FTaxCombination=" + FTaxCombination +
                        ", FLot=" + FLot +
                        ", FStockId=" + FStockId +
                        ", FDisPriceQty='" + FDisPriceQty + '\'' +
                        ", FStockLocId=" + FStockLocId +
                        ", FStockStatusId=" + FStockStatusId +
                        ", FMtoNo='" + FMtoNo + '\'' +
                        ", FGiveAway='" + FGiveAway + '\'' +
                        ", FNote='" + FNote + '\'' +
                        ", FProduceDate='" + FProduceDate + '\'' +
                        ", FExtAuxUnitId=" + FExtAuxUnitId +
                        ", FExtAuxUnitQty='" + FExtAuxUnitQty + '\'' +
                        ", FCheckInComing='" + FCheckInComing + '\'' +
                        ", FProjectNo='" + FProjectNo + '\'' +
                        ", FSampleDamageQty='" + FSampleDamageQty + '\'' +
                        ", FSampleDamageBaseQty='" + FSampleDamageBaseQty + '\'' +
                        ", FIsReceiveUpdateStock='" + FIsReceiveUpdateStock + '\'' +
                        ", FInvoicedJoinQty='" + FInvoicedJoinQty + '\'' +
                        ", FPriceBaseQty='" + FPriceBaseQty + '\'' +
                        ", FSetPriceUnitID=" + FSetPriceUnitID +
                        ", FRemainInStockUnitId=" + FRemainInStockUnitId +
                        ", FBILLINGCLOSE='" + FBILLINGCLOSE + '\'' +
                        ", FRemainInStockQty='" + FRemainInStockQty + '\'' +
                        ", FAPNotJoinQty='" + FAPNotJoinQty + '\'' +
                        ", FRemainInStockBaseQty='" + FRemainInStockBaseQty + '\'' +
                        ", FTaxPrice='" + FTaxPrice + '\'' +
                        ", FEntryTaxRate='" + FEntryTaxRate + '\'' +
                        ", FDiscountRate='" + FDiscountRate + '\'' +
                        ", FCostPrice='" + FCostPrice + '\'' +
                        ", FBOMId=" + FBOMId +
                        ", FSupplierLot='" + FSupplierLot + '\'' +
                        ", FExpiryDate='" + FExpiryDate + '\'' +
                        ", FAuxUnitQty='" + FAuxUnitQty + '\'' +
                        ", FBeforeDisPriceQty='" + FBeforeDisPriceQty + '\'' +
                        ", FEntryPruCost=" + FEntryPruCost +
                        ", FTaxDetailSubEntity=" + FTaxDetailSubEntity +
                        ", FSerialSubEntity=" + FSerialSubEntity +
                        '}';
            }

            /**
             * FEntryID : 0
             * FRowType :
             * FWWInType :
             * FMaterialId : {"FNumber":""}
             * FUnitID : {"FNumber":""}
             * FAuxPropId : {}
             * FParentMatId : {"FNUMBER":""}
             * FRealQty : 0
             * FPriceUnitID : {"FNumber":""}
             * FPrice : 0
             * FTaxCombination : {"FNumber":""}
             * FLot : {"FNumber":""}
             * FStockId : {"FNumber":""}
             * FDisPriceQty : 0
             * FStockLocId : {"FSTOCKLOCID__FF100003":{"FNumber":""},"FSTOCKLOCID__FF100004":{"FNumber":""},"FSTOCKLOCID__FF100012":{"FNumber":""},"FSTOCKLOCID__FF100013":{"FNumber":""},"FSTOCKLOCID__FF100014":{"FNumber":""},"FSTOCKLOCID__FF100015":{"FNumber":""},"FSTOCKLOCID__FF100016":{"FNumber":""},"FSTOCKLOCID__FF100017":{"FNumber":""},"FSTOCKLOCID__FF100018":{"FNumber":""},"FSTOCKLOCID__FF100019":{"FNumber":""}}
             * FStockStatusId : {"FNumber":""}
             * FMtoNo :
             * FGiveAway : false
             * FNote :
             * FProduceDate : 1900-01-01
             * FExtAuxUnitId : {"FNumber":""}
             * FExtAuxUnitQty : 0
             * FCheckInComing : false
             * FProjectNo :
             * FSampleDamageQty : 0
             * FSampleDamageBaseQty : 0
             * FIsReceiveUpdateStock : false
             * FInvoicedJoinQty : 0
             * FPriceBaseQty : 0
             * FSetPriceUnitID : {"FNumber":""}
             * FRemainInStockUnitId : {"FNumber":""}
             * FBILLINGCLOSE : false
             * FRemainInStockQty : 0
             * FAPNotJoinQty : 0
             * FRemainInStockBaseQty : 0
             * FTaxPrice : 0
             * FEntryTaxRate : 0
             * FDiscountRate : 0
             * FCostPrice : 0
             * FBOMId : {"FNumber":""}
             * FSupplierLot :
             * FExpiryDate : 1900-01-01
             * FAuxUnitQty : 0
             * FBeforeDisPriceQty : 0
             * FEntryPruCost : [{"FDetailID":"0"}]
             * FTaxDetailSubEntity : [{"FDetailID":"0","FTaxRate":"0"}]
             * FSerialSubEntity : [{"FDetailID":"0","FSerialNo":"","FSerialNote":""}]
             */

            private String FEntryID;
            private String FRowType;
            private String FWWInType;
            private FMaterialIdBean FMaterialId;
            private FUnitIDBean FUnitID;
            private FAuxPropIdBean FAuxPropId;
            private FParentMatIdBean FParentMatId;
            private String FRealQty;
            private FPriceUnitIDBean FPriceUnitID;
            private String FPrice;
            private FTaxCombinationBean FTaxCombination;
            private FLotBean FLot;
            private FStockIdBean FStockId;
            private String FDisPriceQty;
            private FStockLocIdBean FStockLocId;
            private FStockStatusIdBean FStockStatusId;
            private String FMtoNo;
            private boolean FGiveAway;
            private String FNote;
            private String FProduceDate;
            private FExtAuxUnitIdBean FExtAuxUnitId;
            private String FExtAuxUnitQty;
            private String FCheckInComing;
            private String FProjectNo;
            private String FSampleDamageQty;
            private String FSampleDamageBaseQty;
            private String FIsReceiveUpdateStock;
            private String FInvoicedJoinQty;
            private String FPriceBaseQty;
            private FSetPriceUnitIDBean FSetPriceUnitID;
            private FRemainInStockUnitIdBean FRemainInStockUnitId;
            private String FBILLINGCLOSE;
            private String FRemainInStockQty;
            private String FAPNotJoinQty;
            private String FRemainInStockBaseQty;
            private String FTaxPrice;
            private String FEntryTaxRate;
            private String FDiscountRate;
            private String FCostPrice;
            private FBOMIdBean FBOMId;
            private String FSupplierLot;
            private String FExpiryDate;
            private String FAuxUnitQty;
            private String FBeforeDisPriceQty;
            private List<FEntryPruCostBean> FEntryPruCost;
            private List<FTaxDetailSubEntityBean> FTaxDetailSubEntity;
            private List<FSerialSubEntityBean> FSerialSubEntity;

            public String getFEntryID() {
                return FEntryID;
            }

            public void setFEntryID(String FEntryID) {
                this.FEntryID = FEntryID;
            }

            public String getFRowType() {
                return FRowType;
            }

            public void setFRowType(String FRowType) {
                this.FRowType = FRowType;
            }

            public String getFWWInType() {
                return FWWInType;
            }

            public void setFWWInType(String FWWInType) {
                this.FWWInType = FWWInType;
            }

            public FMaterialIdBean getFMaterialId() {
                return FMaterialId;
            }

            public void setFMaterialId(FMaterialIdBean FMaterialId) {
                this.FMaterialId = FMaterialId;
            }

            public FUnitIDBean getFUnitID() {
                return FUnitID;
            }

            public void setFUnitID(FUnitIDBean FUnitID) {
                this.FUnitID = FUnitID;
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

            public String getFRealQty() {
                return FRealQty;
            }

            public void setFRealQty(String FRealQty) {
                this.FRealQty = FRealQty;
            }

            public FPriceUnitIDBean getFPriceUnitID() {
                return FPriceUnitID;
            }

            public void setFPriceUnitID(FPriceUnitIDBean FPriceUnitID) {
                this.FPriceUnitID = FPriceUnitID;
            }

            public String getFPrice() {
                return FPrice;
            }

            public void setFPrice(String FPrice) {
                this.FPrice = FPrice;
            }

            public FTaxCombinationBean getFTaxCombination() {
                return FTaxCombination;
            }

            public void setFTaxCombination(FTaxCombinationBean FTaxCombination) {
                this.FTaxCombination = FTaxCombination;
            }

            public FLotBean getFLot() {
                return FLot;
            }

            public void setFLot(FLotBean FLot) {
                this.FLot = FLot;
            }

            public FStockIdBean getFStockId() {
                return FStockId;
            }

            public void setFStockId(FStockIdBean FStockId) {
                this.FStockId = FStockId;
            }

            public String getFDisPriceQty() {
                return FDisPriceQty;
            }

            public void setFDisPriceQty(String FDisPriceQty) {
                this.FDisPriceQty = FDisPriceQty;
            }

            public FStockLocIdBean getFStockLocId() {
                return FStockLocId;
            }

            public void setFStockLocId(FStockLocIdBean FStockLocId) {
                this.FStockLocId = FStockLocId;
            }

            public FStockStatusIdBean getFStockStatusId() {
                return FStockStatusId;
            }

            public void setFStockStatusId(FStockStatusIdBean FStockStatusId) {
                this.FStockStatusId = FStockStatusId;
            }

            public String getFMtoNo() {
                return FMtoNo;
            }

            public void setFMtoNo(String FMtoNo) {
                this.FMtoNo = FMtoNo;
            }

            public boolean getFGiveAway() {
                return FGiveAway;
            }

            public void setFGiveAway(boolean FGiveAway) {
                this.FGiveAway = FGiveAway;
            }

            public String getFNote() {
                return FNote;
            }

            public void setFNote(String FNote) {
                this.FNote = FNote;
            }

            public String getFProduceDate() {
                return FProduceDate;
            }

            public void setFProduceDate(String FProduceDate) {
                this.FProduceDate = FProduceDate;
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

            public String getFCheckInComing() {
                return FCheckInComing;
            }

            public void setFCheckInComing(String FCheckInComing) {
                this.FCheckInComing = FCheckInComing;
            }

            public String getFProjectNo() {
                return FProjectNo;
            }

            public void setFProjectNo(String FProjectNo) {
                this.FProjectNo = FProjectNo;
            }

            public String getFSampleDamageQty() {
                return FSampleDamageQty;
            }

            public void setFSampleDamageQty(String FSampleDamageQty) {
                this.FSampleDamageQty = FSampleDamageQty;
            }

            public String getFSampleDamageBaseQty() {
                return FSampleDamageBaseQty;
            }

            public void setFSampleDamageBaseQty(String FSampleDamageBaseQty) {
                this.FSampleDamageBaseQty = FSampleDamageBaseQty;
            }

            public String getFIsReceiveUpdateStock() {
                return FIsReceiveUpdateStock;
            }

            public void setFIsReceiveUpdateStock(String FIsReceiveUpdateStock) {
                this.FIsReceiveUpdateStock = FIsReceiveUpdateStock;
            }

            public String getFInvoicedJoinQty() {
                return FInvoicedJoinQty;
            }

            public void setFInvoicedJoinQty(String FInvoicedJoinQty) {
                this.FInvoicedJoinQty = FInvoicedJoinQty;
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

            public FRemainInStockUnitIdBean getFRemainInStockUnitId() {
                return FRemainInStockUnitId;
            }

            public void setFRemainInStockUnitId(FRemainInStockUnitIdBean FRemainInStockUnitId) {
                this.FRemainInStockUnitId = FRemainInStockUnitId;
            }

            public String getFBILLINGCLOSE() {
                return FBILLINGCLOSE;
            }

            public void setFBILLINGCLOSE(String FBILLINGCLOSE) {
                this.FBILLINGCLOSE = FBILLINGCLOSE;
            }

            public String getFRemainInStockQty() {
                return FRemainInStockQty;
            }

            public void setFRemainInStockQty(String FRemainInStockQty) {
                this.FRemainInStockQty = FRemainInStockQty;
            }

            public String getFAPNotJoinQty() {
                return FAPNotJoinQty;
            }

            public void setFAPNotJoinQty(String FAPNotJoinQty) {
                this.FAPNotJoinQty = FAPNotJoinQty;
            }

            public String getFRemainInStockBaseQty() {
                return FRemainInStockBaseQty;
            }

            public void setFRemainInStockBaseQty(String FRemainInStockBaseQty) {
                this.FRemainInStockBaseQty = FRemainInStockBaseQty;
            }

            public String getFTaxPrice() {
                return FTaxPrice;
            }

            public void setFTaxPrice(String FTaxPrice) {
                this.FTaxPrice = FTaxPrice;
            }

            public String getFEntryTaxRate() {
                return FEntryTaxRate;
            }

            public void setFEntryTaxRate(String FEntryTaxRate) {
                this.FEntryTaxRate = FEntryTaxRate;
            }

            public String getFDiscountRate() {
                return FDiscountRate;
            }

            public void setFDiscountRate(String FDiscountRate) {
                this.FDiscountRate = FDiscountRate;
            }

            public String getFCostPrice() {
                return FCostPrice;
            }

            public void setFCostPrice(String FCostPrice) {
                this.FCostPrice = FCostPrice;
            }

            public FBOMIdBean getFBOMId() {
                return FBOMId;
            }

            public void setFBOMId(FBOMIdBean FBOMId) {
                this.FBOMId = FBOMId;
            }

            public String getFSupplierLot() {
                return FSupplierLot;
            }

            public void setFSupplierLot(String FSupplierLot) {
                this.FSupplierLot = FSupplierLot;
            }

            public String getFExpiryDate() {
                return FExpiryDate;
            }

            public void setFExpiryDate(String FExpiryDate) {
                this.FExpiryDate = FExpiryDate;
            }

            public String getFAuxUnitQty() {
                return FAuxUnitQty;
            }

            public void setFAuxUnitQty(String FAuxUnitQty) {
                this.FAuxUnitQty = FAuxUnitQty;
            }

            public String getFBeforeDisPriceQty() {
                return FBeforeDisPriceQty;
            }

            public void setFBeforeDisPriceQty(String FBeforeDisPriceQty) {
                this.FBeforeDisPriceQty = FBeforeDisPriceQty;
            }

            public List<FEntryPruCostBean> getFEntryPruCost() {
                return FEntryPruCost;
            }

            public void setFEntryPruCost(List<FEntryPruCostBean> FEntryPruCost) {
                this.FEntryPruCost = FEntryPruCost;
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

            public static class FMaterialIdBean {
                @Override
                public String toString() {
                    return "FMaterialIdBean{" +
                            "FNumber='" + FNumber + '\'' +
                            '}';
                }

                public FMaterialIdBean(String FNumber) {
                    this.FNumber = FNumber;
                }

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

            public static class FUnitIDBean {
                @Override
                public String toString() {
                    return "FUnitIDBean{" +
                            "FNumber='" + FNumber + '\'' +
                            '}';
                }

                public FUnitIDBean(String FNumber) {
                    this.FNumber = FNumber;
                }

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
                @Override
                public String toString() {
                    return "FParentMatIdBean{" +
                            "FNUMBER='" + FNUMBER + '\'' +
                            '}';
                }

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

            public static class FPriceUnitIDBean {
                @Override
                public String toString() {
                    return "FPriceUnitIDBean{" +
                            "FNumber='" + FNumber + '\'' +
                            '}';
                }

                public FPriceUnitIDBean(String FNumber) {
                    this.FNumber = FNumber;
                }

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
                @Override
                public String toString() {
                    return "FTaxCombinationBean{" +
                            "FNumber='" + FNumber + '\'' +
                            '}';
                }

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
                @Override
                public String toString() {
                    return "FLotBean{" +
                            "FNumber='" + FNumber + '\'' +
                            '}';
                }

                public FLotBean(String FNumber) {
                    this.FNumber = FNumber;
                }

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
                @Override
                public String toString() {
                    return "FStockIdBean{" +
                            "FNumber='" + FNumber + '\'' +
                            '}';
                }

                public FStockIdBean(String FNumber) {
                    this.FNumber = FNumber;
                }

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

            public static class FStockLocIdBean {
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

            public static class FStockStatusIdBean {
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

            public static class FRemainInStockUnitIdBean {
                public FRemainInStockUnitIdBean(String FNumber) {
                    this.FNumber = FNumber;
                }

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

            public static class FEntryPruCostBean {
                /**
                 * FDetailID : 0
                 */

                private String FDetailID;

                public String getFDetailID() {
                    return FDetailID;
                }

                public void setFDetailID(String FDetailID) {
                    this.FDetailID = FDetailID;
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
