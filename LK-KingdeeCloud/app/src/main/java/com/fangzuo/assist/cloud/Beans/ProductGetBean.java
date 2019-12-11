package com.fangzuo.assist.cloud.Beans;


import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.Dao.T_main;

import java.util.ArrayList;
import java.util.List;

public class ProductGetBean {

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
     * Model : {"FID":"0","FBillNo":"","FBillType":{"FNUMBER":""},"FDate":"1900-01-01","FDescription":"","FStockOrgId":{"FNumber":""},"FStockerGroupId":{"FNUMBER":""},"FStockerId":{"FNUMBER":""},"FPickerId":{"FSTAFFNUMBER":""},"FCurrId":{"FNumber":""},"FOwnerTypeId0":"","FOwnerId0":{"FNumber":""},"FPrdOrgId":{"FNumber":""},"FWorkShopId":{"FNumber":""},"FTransferBizTypeId":{"FNumber":""},"FScanBox":"","FBizType":"","FEntity":[{"FEntryID":"0","FMaterialId":{"FNumber":""},"FBomId":{"FNumber":""},"FAuxPropId":{},"FUnitID":{"FNumber":""},"FAppQty":"0","FActualQty":"0","FStockId":{"FNumber":""},"FStockLocId":{"FSTOCKLOCID__FF100003":{"FNumber":""},"FSTOCKLOCID__FF100004":{"FNumber":""},"FSTOCKLOCID__FF100012":{"FNumber":""},"FSTOCKLOCID__FF100013":{"FNumber":""},"FSTOCKLOCID__FF100014":{"FNumber":""},"FSTOCKLOCID__FF100015":{"FNumber":""},"FSTOCKLOCID__FF100016":{"FNumber":""},"FSTOCKLOCID__FF100017":{"FNumber":""},"FSTOCKLOCID__FF100018":{"FNumber":""},"FSTOCKLOCID__FF100019":{"FNumber":""}},"FLot":{"FNumber":""},"FProductId":{"FNumber":""},"FProductNo":"","FIsAffectCost":"false","FPRODUCTGROUPID":{"FNUMBER":""},"FStockUnitId":{"FNumber":""},"FStockActualQty":"0","FOwnerTypeId":"","FEntryWorkShopId":{"FNumber":""},"FExtAuxUnitId":{"FNumber":""},"FExtAuxUnitQty":"0","FPrice":"0","FAmount":"0","FParentOwnerTypeId":"","FParentOwnerId":{"FNumber":""},"FBaseUnitId":{"FNumber":""},"FServiceContext":"","FBaseActualQty":"0","FOwnerId":{"FNumber":""},"FStockStatusId":{"FNumber":""},"FProduceDate":"1900-01-01","FSecUnitId":{"FNumber":""},"FSecActualQty":"0","FEntrtyMemo":"","FMtoNo":"","FBaseAppQty":"0","FKeeperTypeId":"","FKeeperId":{"FNumber":""},"FExpiryDate":"1900-01-01","FSerialSubEntity":[{"FDetailID":"0","FSerialNo":"","FSerialNote":""}]}]}
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
         * FBillNo :
         * FBillType : {"FNUMBER":""}
         * FDate : 1900-01-01
         * FDescription :
         * FStockOrgId : {"FNumber":""}
         * FStockerGroupId : {"FNUMBER":""}
         * FStockerId : {"FNUMBER":""}
         * FPickerId : {"FSTAFFNUMBER":""}
         * FCurrId : {"FNumber":""}
         * FOwnerTypeId0 :
         * FOwnerId0 : {"FNumber":""}
         * FPrdOrgId : {"FNumber":""}
         * FWorkShopId : {"FNumber":""}
         * FTransferBizTypeId : {"FNumber":""}
         * FScanBox :
         * FBizType :
         * FEntity : [{"FEntryID":"0","FMaterialId":{"FNumber":""},"FBomId":{"FNumber":""},"FAuxPropId":{},"FUnitID":{"FNumber":""},"FAppQty":"0","FActualQty":"0","FStockId":{"FNumber":""},"FStockLocId":{"FSTOCKLOCID__FF100003":{"FNumber":""},"FSTOCKLOCID__FF100004":{"FNumber":""},"FSTOCKLOCID__FF100012":{"FNumber":""},"FSTOCKLOCID__FF100013":{"FNumber":""},"FSTOCKLOCID__FF100014":{"FNumber":""},"FSTOCKLOCID__FF100015":{"FNumber":""},"FSTOCKLOCID__FF100016":{"FNumber":""},"FSTOCKLOCID__FF100017":{"FNumber":""},"FSTOCKLOCID__FF100018":{"FNumber":""},"FSTOCKLOCID__FF100019":{"FNumber":""}},"FLot":{"FNumber":""},"FProductId":{"FNumber":""},"FProductNo":"","FIsAffectCost":"false","FPRODUCTGROUPID":{"FNUMBER":""},"FStockUnitId":{"FNumber":""},"FStockActualQty":"0","FOwnerTypeId":"","FEntryWorkShopId":{"FNumber":""},"FExtAuxUnitId":{"FNumber":""},"FExtAuxUnitQty":"0","FPrice":"0","FAmount":"0","FParentOwnerTypeId":"","FParentOwnerId":{"FNumber":""},"FBaseUnitId":{"FNumber":""},"FServiceContext":"","FBaseActualQty":"0","FOwnerId":{"FNumber":""},"FStockStatusId":{"FNumber":""},"FProduceDate":"1900-01-01","FSecUnitId":{"FNumber":""},"FSecActualQty":"0","FEntrtyMemo":"","FMtoNo":"","FBaseAppQty":"0","FKeeperTypeId":"","FKeeperId":{"FNumber":""},"FExpiryDate":"1900-01-01","FSerialSubEntity":[{"FDetailID":"0","FSerialNo":"","FSerialNote":""}]}]
         */

        private String FID;
        private String FBillNo;
        private FBillTypeBean FBillType;
        private String FDate;
        private String FDescription;
        private FStockOrgIdBean FStockOrgId;
        private FStockerGroupIdBean FStockerGroupId;
        private FStockerIdBean FStockerId;
        private FPickerIdBean FPickerId;
        private FCurrIdBean FCurrId;
        private String FOwnerTypeId0;
        private FOwnerId0Bean FOwnerId0;
        private FPrdOrgIdBean FPrdOrgId;
        private FWorkShopIdBean FWorkShopId;
        private FTransferBizTypeIdBean FTransferBizTypeId;
        private String FScanBox;
        private String FBizType;
        private List<FEntityBean> FEntity;
        public void setModelBean(T_main main, List<T_Detail> beans){
            //表头设置-------------------------------------------------------------
            this.FBillNo ="";
            this.FStockOrgId=new ProductGetBean.ModelBean.FStockOrgIdBean();             //收料组织
            this.FStockOrgId.FNumber=main.FStockOrgId;             //收料组织
            this.FWorkShopId = new FWorkShopIdBean();//生产车间
            this.FWorkShopId.FNumber=main.FDepartmentNumber;//生产车间
            this.FStockerId = new ProductGetBean.ModelBean.FStockerIdBean();       //仓管员
            this.FStockerId.FNUMBER=main.FStockerNumber;       //仓管员
            this.FPickerId = new ProductGetBean.ModelBean.FPickerIdBean();       //领料人
            this.FPickerId.FSTAFFNUMBER=main.FPurchaserId;       //领料人
            this.FBillType=new ProductGetBean.ModelBean.FBillTypeBean();//单据类型
            this.FBillType.FNUMBER=main.FBillTypeID;//单据类型
            this.FPrdOrgId=new ProductGetBean.ModelBean.FPrdOrgIdBean();//生产组织
            this.FPrdOrgId.FNumber=main.FStockOrgId;//生产组织
            this.FOwnerTypeId0=main.FOwnerTypeIdHead;//货主类型
            this.FOwnerId0=new ProductGetBean.ModelBean.FOwnerId0Bean();//货主
            this.FOwnerId0.FNumber=main.FOwnerIdHead;//货主
            this.FDate = main.FDate;//入库日期
            this.FDescription = main.FNot;//入库日期
//            this.FSupplierId=new FSupplierIdBean(main.FSupplierId);//供应商
//            this.FInStockFin=new FInStockFinBean();//一个大类
//            this.FInStockFin.FPriceTimePoint= main.FPriceTimePoint;         //定价时点
//            this.FInStockFin.FSettleOrgId=new FInStockFinBean.FSettleOrgIdBean(main.FSettleOrgId);//结算组织
//            this.FInStockFin.FSettleCurrId=new FInStockFinBean.FSettleCurrIdBean(main.FSettleCurrId);//结算币别
            //----------------------------------------------------------------
            //表体设置----------------------------------------------------------------
            this.FEntity = new ArrayList<>();
            for (int i = 0; i < beans.size(); i++) {
                FEntityBean bean = new FEntityBean();
                bean.FUnitID = new FEntityBean.FUnitIDBean();bean.FUnitID.FNumber=beans.get(i).FUnitID;
                bean.FBaseUnitId=new FEntityBean.FBaseUnitIdBean();bean.FBaseUnitId.FNumber=beans.get(i).FUnitID;//基本单位
                bean.FMaterialId = new FEntityBean.FMaterialIdBean();bean.FMaterialId.FNumber=beans.get(i).FMaterialId;
                bean.FLot = new FEntityBean.FLotBean();bean.FLot.FNumber=beans.get(i).FBatch;
                bean.FStockId=new FEntityBean.FStockIdBean();bean.FStockId.FNumber=beans.get(i).FStorageId;//仓库
                bean.FSecActualQty=beans.get(i).FRealQty;//仓库
                bean.FAppQty=beans.get(i).FRemainInStockQty;//申请数量
                bean.FBaseAppQty=beans.get(i).FRemainInStockQty;//申请数量
                bean.FActualQty=beans.get(i).FRemainInStockQty;//实发
                bean.FSecActualQty=beans.get(i).FRemainInStockQty;//实发数量(库存辅单位
                bean.FStockActualQty=beans.get(i).FRemainInStockQty;//库存单位实发数量:
//                bean.FExtAuxUnitQty=beans.get(i).FRemainInStockQty;//实发数量（辅单位）
                bean.FKeeperTypeId="BD_Customer";//保管者类型
                bean.FKeeperId=new FEntityBean.FKeeperIdBean();
                bean.FKeeperId.FNumber="CUST0003";//保管者
                this.FEntity.add(bean);
            }

        }
        public String getFID() {
            return FID;
        }

        public void setFID(String FID) {
            this.FID = FID;
        }

        public String getFBillNo() {
            return FBillNo;
        }

        public void setFBillNo(String FBillNo) {
            this.FBillNo = FBillNo;
        }

        public FBillTypeBean getFBillType() {
            return FBillType;
        }

        public void setFBillType(FBillTypeBean FBillType) {
            this.FBillType = FBillType;
        }

        public String getFDate() {
            return FDate;
        }

        public void setFDate(String FDate) {
            this.FDate = FDate;
        }

        public String getFDescription() {
            return FDescription;
        }

        public void setFDescription(String FDescription) {
            this.FDescription = FDescription;
        }

        public FStockOrgIdBean getFStockOrgId() {
            return FStockOrgId;
        }

        public void setFStockOrgId(FStockOrgIdBean FStockOrgId) {
            this.FStockOrgId = FStockOrgId;
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

        public FPickerIdBean getFPickerId() {
            return FPickerId;
        }

        public void setFPickerId(FPickerIdBean FPickerId) {
            this.FPickerId = FPickerId;
        }

        public FCurrIdBean getFCurrId() {
            return FCurrId;
        }

        public void setFCurrId(FCurrIdBean FCurrId) {
            this.FCurrId = FCurrId;
        }

        public String getFOwnerTypeId0() {
            return FOwnerTypeId0;
        }

        public void setFOwnerTypeId0(String FOwnerTypeId0) {
            this.FOwnerTypeId0 = FOwnerTypeId0;
        }

        public FOwnerId0Bean getFOwnerId0() {
            return FOwnerId0;
        }

        public void setFOwnerId0(FOwnerId0Bean FOwnerId0) {
            this.FOwnerId0 = FOwnerId0;
        }

        public FPrdOrgIdBean getFPrdOrgId() {
            return FPrdOrgId;
        }

        public void setFPrdOrgId(FPrdOrgIdBean FPrdOrgId) {
            this.FPrdOrgId = FPrdOrgId;
        }

        public FWorkShopIdBean getFWorkShopId() {
            return FWorkShopId;
        }

        public void setFWorkShopId(FWorkShopIdBean FWorkShopId) {
            this.FWorkShopId = FWorkShopId;
        }

        public FTransferBizTypeIdBean getFTransferBizTypeId() {
            return FTransferBizTypeId;
        }

        public void setFTransferBizTypeId(FTransferBizTypeIdBean FTransferBizTypeId) {
            this.FTransferBizTypeId = FTransferBizTypeId;
        }

        public String getFScanBox() {
            return FScanBox;
        }

        public void setFScanBox(String FScanBox) {
            this.FScanBox = FScanBox;
        }

        public String getFBizType() {
            return FBizType;
        }

        public void setFBizType(String FBizType) {
            this.FBizType = FBizType;
        }

        public List<FEntityBean> getFEntity() {
            return FEntity;
        }

        public void setFEntity(List<FEntityBean> FEntity) {
            this.FEntity = FEntity;
        }

        public static class FBillTypeBean {
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

        public static class FStockerIdBean {
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

        public static class FPickerIdBean {
            /**
             * FSTAFFNUMBER :
             */

            private String FSTAFFNUMBER;

            public String getFSTAFFNUMBER() {
                return FSTAFFNUMBER;
            }

            public void setFSTAFFNUMBER(String FSTAFFNUMBER) {
                this.FSTAFFNUMBER = FSTAFFNUMBER;
            }
        }

        public static class FCurrIdBean {
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

        public static class FOwnerId0Bean {
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

        public static class FPrdOrgIdBean {
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

        public static class FWorkShopIdBean {
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

        public static class FTransferBizTypeIdBean {
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

        public static class FEntityBean {
            /**
             * FEntryID : 0
             * FMaterialId : {"FNumber":""}
             * FBomId : {"FNumber":""}
             * FAuxPropId : {}
             * FUnitID : {"FNumber":""}
             * FAppQty : 0
             * FActualQty : 0
             * FStockId : {"FNumber":""}
             * FStockLocId : {"FSTOCKLOCID__FF100003":{"FNumber":""},"FSTOCKLOCID__FF100004":{"FNumber":""},"FSTOCKLOCID__FF100012":{"FNumber":""},"FSTOCKLOCID__FF100013":{"FNumber":""},"FSTOCKLOCID__FF100014":{"FNumber":""},"FSTOCKLOCID__FF100015":{"FNumber":""},"FSTOCKLOCID__FF100016":{"FNumber":""},"FSTOCKLOCID__FF100017":{"FNumber":""},"FSTOCKLOCID__FF100018":{"FNumber":""},"FSTOCKLOCID__FF100019":{"FNumber":""}}
             * FLot : {"FNumber":""}
             * FProductId : {"FNumber":""}
             * FProductNo :
             * FIsAffectCost : false
             * FPRODUCTGROUPID : {"FNUMBER":""}
             * FStockUnitId : {"FNumber":""}
             * FStockActualQty : 0
             * FOwnerTypeId :
             * FEntryWorkShopId : {"FNumber":""}
             * FExtAuxUnitId : {"FNumber":""}
             * FExtAuxUnitQty : 0
             * FPrice : 0
             * FAmount : 0
             * FParentOwnerTypeId :
             * FParentOwnerId : {"FNumber":""}
             * FBaseUnitId : {"FNumber":""}
             * FServiceContext :
             * FBaseActualQty : 0
             * FOwnerId : {"FNumber":""}
             * FStockStatusId : {"FNumber":""}
             * FProduceDate : 1900-01-01
             * FSecUnitId : {"FNumber":""}
             * FSecActualQty : 0
             * FEntrtyMemo :
             * FMtoNo :
             * FBaseAppQty : 0
             * FKeeperTypeId :
             * FKeeperId : {"FNumber":""}
             * FExpiryDate : 1900-01-01
             * FSerialSubEntity : [{"FDetailID":"0","FSerialNo":"","FSerialNote":""}]
             */

            private String FEntryID;
            private FMaterialIdBean FMaterialId;
            private FBomIdBean FBomId;
            private FAuxPropIdBean FAuxPropId;
            private FUnitIDBean FUnitID;
            private String FAppQty;
            private String FActualQty;
            private FStockIdBean FStockId;
            private FStockLocIdBean FStockLocId;
            private FLotBean FLot;
            private FProductIdBean FProductId;
            private String FProductNo;
            private String FIsAffectCost;
            private FPRODUCTGROUPIDBean FPRODUCTGROUPID;
            private FStockUnitIdBean FStockUnitId;
            private String FStockActualQty;
            private String FOwnerTypeId;
            private FEntryWorkShopIdBean FEntryWorkShopId;
            private FExtAuxUnitIdBean FExtAuxUnitId;
            private String FExtAuxUnitQty;
            private String FPrice;
            private String FAmount;
            private String FParentOwnerTypeId;
            private FParentOwnerIdBean FParentOwnerId;
            private FBaseUnitIdBean FBaseUnitId;
            private String FServiceContext;
            private String FBaseActualQty;
            private FOwnerIdBean FOwnerId;
            private FStockStatusIdBean FStockStatusId;
            private String FProduceDate;
            private FSecUnitIdBean FSecUnitId;
            private String FSecActualQty;
            private String FEntrtyMemo;
            private String FMtoNo;
            private String FBaseAppQty;
            private String FKeeperTypeId;
            private FKeeperIdBean FKeeperId;
            private String FExpiryDate;
            private List<FSerialSubEntityBean> FSerialSubEntity;

            public String getFEntryID() {
                return FEntryID;
            }

            public void setFEntryID(String FEntryID) {
                this.FEntryID = FEntryID;
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

            public String getFAppQty() {
                return FAppQty;
            }

            public void setFAppQty(String FAppQty) {
                this.FAppQty = FAppQty;
            }

            public String getFActualQty() {
                return FActualQty;
            }

            public void setFActualQty(String FActualQty) {
                this.FActualQty = FActualQty;
            }

            public FStockIdBean getFStockId() {
                return FStockId;
            }

            public void setFStockId(FStockIdBean FStockId) {
                this.FStockId = FStockId;
            }

            public FStockLocIdBean getFStockLocId() {
                return FStockLocId;
            }

            public void setFStockLocId(FStockLocIdBean FStockLocId) {
                this.FStockLocId = FStockLocId;
            }

            public FLotBean getFLot() {
                return FLot;
            }

            public void setFLot(FLotBean FLot) {
                this.FLot = FLot;
            }

            public FProductIdBean getFProductId() {
                return FProductId;
            }

            public void setFProductId(FProductIdBean FProductId) {
                this.FProductId = FProductId;
            }

            public String getFProductNo() {
                return FProductNo;
            }

            public void setFProductNo(String FProductNo) {
                this.FProductNo = FProductNo;
            }

            public String getFIsAffectCost() {
                return FIsAffectCost;
            }

            public void setFIsAffectCost(String FIsAffectCost) {
                this.FIsAffectCost = FIsAffectCost;
            }

            public FPRODUCTGROUPIDBean getFPRODUCTGROUPID() {
                return FPRODUCTGROUPID;
            }

            public void setFPRODUCTGROUPID(FPRODUCTGROUPIDBean FPRODUCTGROUPID) {
                this.FPRODUCTGROUPID = FPRODUCTGROUPID;
            }

            public FStockUnitIdBean getFStockUnitId() {
                return FStockUnitId;
            }

            public void setFStockUnitId(FStockUnitIdBean FStockUnitId) {
                this.FStockUnitId = FStockUnitId;
            }

            public String getFStockActualQty() {
                return FStockActualQty;
            }

            public void setFStockActualQty(String FStockActualQty) {
                this.FStockActualQty = FStockActualQty;
            }

            public String getFOwnerTypeId() {
                return FOwnerTypeId;
            }

            public void setFOwnerTypeId(String FOwnerTypeId) {
                this.FOwnerTypeId = FOwnerTypeId;
            }

            public FEntryWorkShopIdBean getFEntryWorkShopId() {
                return FEntryWorkShopId;
            }

            public void setFEntryWorkShopId(FEntryWorkShopIdBean FEntryWorkShopId) {
                this.FEntryWorkShopId = FEntryWorkShopId;
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

            public String getFPrice() {
                return FPrice;
            }

            public void setFPrice(String FPrice) {
                this.FPrice = FPrice;
            }

            public String getFAmount() {
                return FAmount;
            }

            public void setFAmount(String FAmount) {
                this.FAmount = FAmount;
            }

            public String getFParentOwnerTypeId() {
                return FParentOwnerTypeId;
            }

            public void setFParentOwnerTypeId(String FParentOwnerTypeId) {
                this.FParentOwnerTypeId = FParentOwnerTypeId;
            }

            public FParentOwnerIdBean getFParentOwnerId() {
                return FParentOwnerId;
            }

            public void setFParentOwnerId(FParentOwnerIdBean FParentOwnerId) {
                this.FParentOwnerId = FParentOwnerId;
            }

            public FBaseUnitIdBean getFBaseUnitId() {
                return FBaseUnitId;
            }

            public void setFBaseUnitId(FBaseUnitIdBean FBaseUnitId) {
                this.FBaseUnitId = FBaseUnitId;
            }

            public String getFServiceContext() {
                return FServiceContext;
            }

            public void setFServiceContext(String FServiceContext) {
                this.FServiceContext = FServiceContext;
            }

            public String getFBaseActualQty() {
                return FBaseActualQty;
            }

            public void setFBaseActualQty(String FBaseActualQty) {
                this.FBaseActualQty = FBaseActualQty;
            }

            public FOwnerIdBean getFOwnerId() {
                return FOwnerId;
            }

            public void setFOwnerId(FOwnerIdBean FOwnerId) {
                this.FOwnerId = FOwnerId;
            }

            public FStockStatusIdBean getFStockStatusId() {
                return FStockStatusId;
            }

            public void setFStockStatusId(FStockStatusIdBean FStockStatusId) {
                this.FStockStatusId = FStockStatusId;
            }

            public String getFProduceDate() {
                return FProduceDate;
            }

            public void setFProduceDate(String FProduceDate) {
                this.FProduceDate = FProduceDate;
            }

            public FSecUnitIdBean getFSecUnitId() {
                return FSecUnitId;
            }

            public void setFSecUnitId(FSecUnitIdBean FSecUnitId) {
                this.FSecUnitId = FSecUnitId;
            }

            public String getFSecActualQty() {
                return FSecActualQty;
            }

            public void setFSecActualQty(String FSecActualQty) {
                this.FSecActualQty = FSecActualQty;
            }

            public String getFEntrtyMemo() {
                return FEntrtyMemo;
            }

            public void setFEntrtyMemo(String FEntrtyMemo) {
                this.FEntrtyMemo = FEntrtyMemo;
            }

            public String getFMtoNo() {
                return FMtoNo;
            }

            public void setFMtoNo(String FMtoNo) {
                this.FMtoNo = FMtoNo;
            }

            public String getFBaseAppQty() {
                return FBaseAppQty;
            }

            public void setFBaseAppQty(String FBaseAppQty) {
                this.FBaseAppQty = FBaseAppQty;
            }

            public String getFKeeperTypeId() {
                return FKeeperTypeId;
            }

            public void setFKeeperTypeId(String FKeeperTypeId) {
                this.FKeeperTypeId = FKeeperTypeId;
            }

            public FKeeperIdBean getFKeeperId() {
                return FKeeperId;
            }

            public void setFKeeperId(FKeeperIdBean FKeeperId) {
                this.FKeeperId = FKeeperId;
            }

            public String getFExpiryDate() {
                return FExpiryDate;
            }

            public void setFExpiryDate(String FExpiryDate) {
                this.FExpiryDate = FExpiryDate;
            }

            public List<FSerialSubEntityBean> getFSerialSubEntity() {
                return FSerialSubEntity;
            }

            public void setFSerialSubEntity(List<FSerialSubEntityBean> FSerialSubEntity) {
                this.FSerialSubEntity = FSerialSubEntity;
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

            public static class FProductIdBean {
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

            public static class FPRODUCTGROUPIDBean {
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

            public static class FStockUnitIdBean {
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

            public static class FEntryWorkShopIdBean {
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

            public static class FParentOwnerIdBean {
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

            public static class FBaseUnitIdBean {
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

            public static class FSecUnitIdBean {
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

            public static class FKeeperIdBean {
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
