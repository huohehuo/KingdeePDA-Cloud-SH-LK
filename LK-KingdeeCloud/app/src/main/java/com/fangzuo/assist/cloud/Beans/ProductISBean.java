package com.fangzuo.assist.cloud.Beans;


import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.Dao.T_main;

import java.util.ArrayList;
import java.util.List;

public class ProductISBean {


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
     * Model : {"FID":"0","FBillNo":"","FBillType":{"FNUMBER":""},"FDate":"1900-01-01","FStockOrgId":{"FNumber":""},"FPrdOrgId":{"FNumber":""},"FStockerGroupId":{"FNUMBER":""},"FStockerId":{"FNUMBER":""},"FCurrId":{"FNumber":""},"FOwnerTypeId0":"","FOwnerId0":{"FNumber":""},"FDescription":"","FTransferBizTypeId":{"FNumber":""},"FScanBox":"","FEntity":[{"FEntryID":"0","FMaterialId":{"FNumber":""},"FAuxpropId":{},"FInStockType":"","FUnitID":{"FNumber":""},"FMustQty":"0","FRealQty":"0","FStockId":{"FNumber":""},"FStockLocId":{"FSTOCKLOCID__FF100003":{"FNumber":""},"FSTOCKLOCID__FF100004":{"FNumber":""},"FSTOCKLOCID__FF100012":{"FNumber":""},"FSTOCKLOCID__FF100013":{"FNumber":""},"FSTOCKLOCID__FF100014":{"FNumber":""},"FSTOCKLOCID__FF100015":{"FNumber":""},"FSTOCKLOCID__FF100016":{"FNumber":""},"FSTOCKLOCID__FF100017":{"FNumber":""},"FSTOCKLOCID__FF100018":{"FNumber":""},"FSTOCKLOCID__FF100019":{"FNumber":""}},"FLot":{"FNumber":""},"FWorkShopId1":{"FNumber":""},"FShiftGroupId":{"FNumber":""},"FProductNo":"","FIsAffectCost":"false","FOwnerTypeId":"","FOwnerId":{"FNumber":""},"FStockUnitId":{"FNumber":""},"FStockRealQty":"0","FBaseUnitId":{"FNumber":""},"FBaseRealQty":"0","FExtAuxUnitId":{"FNumber":""},"FExtAuxUnitQty":"0","FMtoNo":"","FStockStatusId":{"FNumber":""},"FBomId":{"FNumber":""},"FProduceDate":"1900-01-01","FExpiryDate":"1900-01-01","FSecUnitId":{"FNumber":""},"FSecRealQty":"0","FMemo":"","FBaseMustQty":"0","FKeeperTypeId":"","FKeeperId":{"FNumber":""},"FSerialSubEntity":[{"FDetailID":"0","FSerialNo":"","FSerialNote":""}]}]}
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
         * FStockOrgId : {"FNumber":""}
         * FPrdOrgId : {"FNumber":""}
         * FStockerGroupId : {"FNUMBER":""}
         * FStockerId : {"FNUMBER":""}
         * FCurrId : {"FNumber":""}
         * FOwnerTypeId0 :
         * FOwnerId0 : {"FNumber":""}
         * FDescription :
         * FTransferBizTypeId : {"FNumber":""}
         * FScanBox :
         * FEntity : [{"FEntryID":"0","FMaterialId":{"FNumber":""},"FAuxpropId":{},"FInStockType":"","FUnitID":{"FNumber":""},"FMustQty":"0","FRealQty":"0","FStockId":{"FNumber":""},"FStockLocId":{"FSTOCKLOCID__FF100003":{"FNumber":""},"FSTOCKLOCID__FF100004":{"FNumber":""},"FSTOCKLOCID__FF100012":{"FNumber":""},"FSTOCKLOCID__FF100013":{"FNumber":""},"FSTOCKLOCID__FF100014":{"FNumber":""},"FSTOCKLOCID__FF100015":{"FNumber":""},"FSTOCKLOCID__FF100016":{"FNumber":""},"FSTOCKLOCID__FF100017":{"FNumber":""},"FSTOCKLOCID__FF100018":{"FNumber":""},"FSTOCKLOCID__FF100019":{"FNumber":""}},"FLot":{"FNumber":""},"FWorkShopId1":{"FNumber":""},"FShiftGroupId":{"FNumber":""},"FProductNo":"","FIsAffectCost":"false","FOwnerTypeId":"","FOwnerId":{"FNumber":""},"FStockUnitId":{"FNumber":""},"FStockRealQty":"0","FBaseUnitId":{"FNumber":""},"FBaseRealQty":"0","FExtAuxUnitId":{"FNumber":""},"FExtAuxUnitQty":"0","FMtoNo":"","FStockStatusId":{"FNumber":""},"FBomId":{"FNumber":""},"FProduceDate":"1900-01-01","FExpiryDate":"1900-01-01","FSecUnitId":{"FNumber":""},"FSecRealQty":"0","FMemo":"","FBaseMustQty":"0","FKeeperTypeId":"","FKeeperId":{"FNumber":""},"FSerialSubEntity":[{"FDetailID":"0","FSerialNo":"","FSerialNote":""}]}]
         */

        private String FID;
        private String FBillNo;
        private FBillTypeBean FBillType;
        private String FDate;
        private FStockOrgIdBean FStockOrgId;
        private FPrdOrgIdBean FPrdOrgId;
        private FStockerGroupIdBean FStockerGroupId;
        private FStockerIdBean FStockerId;
        private FCurrIdBean FCurrId;
        private String FOwnerTypeId0;
        private FOwnerId0Bean FOwnerId0;
        private String FDescription;
        private FTransferBizTypeIdBean FTransferBizTypeId;
        private String FScanBox;
        private List<FEntityBean> FEntity;
        public void setModelBean(T_main main, List<T_Detail> beans){
            //表头设置-------------------------------------------------------------
            this.FBillNo ="";
            this.FBillType=new ProductISBean.ModelBean.FBillTypeBean();this.FBillType.FNUMBER=main.FBillTypeID;//单据类型
            this.FStockOrgId=new ProductISBean.ModelBean.FStockOrgIdBean();this.FStockOrgId.FNumber=main.FStockOrgId;//入库组织
            this.FPrdOrgId=new ProductISBean.ModelBean.FPrdOrgIdBean();this.FPrdOrgId.FNumber=main.FPurchaseOrgId;//生产组织
            this.FStockerId = new ProductISBean.ModelBean.FStockerIdBean();this.FStockerId.FNUMBER=main.FStockerNumber;//仓管员
            this.FCurrId = new ProductISBean.ModelBean.FCurrIdBean();this.FCurrId.FNumber="1";//本位币
            this.FOwnerTypeId0 = main.FOwnerTypeIdHead;//货主类型
            this.FOwnerId0=new FOwnerId0Bean();this.FOwnerId0.FNumber=main.FOwnerIdHead;//货主
            this.FDate = main.FDate;//入库日期
            this. FDescription= main.FNot;//备注
            //----------------------------------------------------------------
            //表体设置----------------------------------------------------------------
            this.FEntity = new ArrayList<>();
            for (int i = 0; i < beans.size(); i++) {
                FEntityBean bean = new FEntityBean();
                bean.FUnitID = new FEntityBean.FUnitIDBean();
                bean.FUnitID.FNumber=beans.get(i).FUnitID;//单位
//                bean.FUnitID.FName="米";//单位
                bean.FMaterialId = new FEntityBean.FMaterialIdBean();bean.FMaterialId.FNumber=beans.get(i).FMaterialId;//物料编码
                bean.FLot = new FEntityBean.FLotBean();bean.FLot.FNumber=beans.get(i).FBatch;//批号
                bean.FWorkShopId1 = new FEntityBean.FWorkShopId1Bean();bean.FWorkShopId1.FNumber=beans.get(i).FWorkShopId1;//生产车间
                bean.FStockId=new FEntityBean.FStockIdBean();bean.FStockId.FNumber=beans.get(i).FStorageId;//仓库
                bean.FStockStatusId=new FEntityBean.FStockStatusIdBean();bean.FStockStatusId.FNumber="KCZT01_SYS";//库存状态
                bean.FKeeperTypeId="BD_Customer";//保管者类型
                bean.FKeeperId=new FEntityBean.FKeeperIdBean();
                bean.FKeeperId.FNumber="CUST0201";//保管者
                bean.FRealQty=beans.get(i).FRealQty;//实收数量
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

        public FStockOrgIdBean getFStockOrgId() {
            return FStockOrgId;
        }

        public void setFStockOrgId(FStockOrgIdBean FStockOrgId) {
            this.FStockOrgId = FStockOrgId;
        }

        public FPrdOrgIdBean getFPrdOrgId() {
            return FPrdOrgId;
        }

        public void setFPrdOrgId(FPrdOrgIdBean FPrdOrgId) {
            this.FPrdOrgId = FPrdOrgId;
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

        public String getFDescription() {
            return FDescription;
        }

        public void setFDescription(String FDescription) {
            this.FDescription = FDescription;
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
             * FAuxpropId : {}
             * FInStockType :
             * FUnitID : {"FNumber":""}
             * FMustQty : 0
             * FRealQty : 0
             * FStockId : {"FNumber":""}
             * FStockLocId : {"FSTOCKLOCID__FF100003":{"FNumber":""},"FSTOCKLOCID__FF100004":{"FNumber":""},"FSTOCKLOCID__FF100012":{"FNumber":""},"FSTOCKLOCID__FF100013":{"FNumber":""},"FSTOCKLOCID__FF100014":{"FNumber":""},"FSTOCKLOCID__FF100015":{"FNumber":""},"FSTOCKLOCID__FF100016":{"FNumber":""},"FSTOCKLOCID__FF100017":{"FNumber":""},"FSTOCKLOCID__FF100018":{"FNumber":""},"FSTOCKLOCID__FF100019":{"FNumber":""}}
             * FLot : {"FNumber":""}
             * FWorkShopId1 : {"FNumber":""}
             * FShiftGroupId : {"FNumber":""}
             * FProductNo :
             * FIsAffectCost : false
             * FOwnerTypeId :
             * FOwnerId : {"FNumber":""}
             * FStockUnitId : {"FNumber":""}
             * FStockRealQty : 0
             * FBaseUnitId : {"FNumber":""}
             * FBaseRealQty : 0
             * FExtAuxUnitId : {"FNumber":""}
             * FExtAuxUnitQty : 0
             * FMtoNo :
             * FStockStatusId : {"FNumber":""}
             * FBomId : {"FNumber":""}
             * FProduceDate : 1900-01-01
             * FExpiryDate : 1900-01-01
             * FSecUnitId : {"FNumber":""}
             * FSecRealQty : 0
             * FMemo :
             * FBaseMustQty : 0
             * FKeeperTypeId :
             * FKeeperId : {"FNumber":""}
             * FSerialSubEntity : [{"FDetailID":"0","FSerialNo":"","FSerialNote":""}]
             */

            private String FEntryID;
            private FMaterialIdBean FMaterialId;
            private FAuxpropIdBean FAuxpropId;
            private String FInStockType;
            private FUnitIDBean FUnitID;
            private String FMustQty;
            private String FRealQty;
            private FStockIdBean FStockId;
            private FStockLocIdBean FStockLocId;
            private FLotBean FLot;
            private FWorkShopId1Bean FWorkShopId1;
            private FShiftGroupIdBean FShiftGroupId;
            private String FProductNo;
            private String FIsAffectCost;
            private String FOwnerTypeId;
            private FOwnerIdBean FOwnerId;
            private FStockUnitIdBean FStockUnitId;
            private String FStockRealQty;
            private FBaseUnitIdBean FBaseUnitId;
            private String FBaseRealQty;
            private FExtAuxUnitIdBean FExtAuxUnitId;
            private String FExtAuxUnitQty;
            private String FMtoNo;
            private FStockStatusIdBean FStockStatusId;
            private FBomIdBean FBomId;
            private String FProduceDate;
            private String FExpiryDate;
            private FSecUnitIdBean FSecUnitId;
            private String FSecRealQty;
            private String FMemo;
            private String FBaseMustQty;
            private String FKeeperTypeId;
            private FKeeperIdBean FKeeperId;
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

            public FAuxpropIdBean getFAuxpropId() {
                return FAuxpropId;
            }

            public void setFAuxpropId(FAuxpropIdBean FAuxpropId) {
                this.FAuxpropId = FAuxpropId;
            }

            public String getFInStockType() {
                return FInStockType;
            }

            public void setFInStockType(String FInStockType) {
                this.FInStockType = FInStockType;
            }

            public FUnitIDBean getFUnitID() {
                return FUnitID;
            }

            public void setFUnitID(FUnitIDBean FUnitID) {
                this.FUnitID = FUnitID;
            }

            public String getFMustQty() {
                return FMustQty;
            }

            public void setFMustQty(String FMustQty) {
                this.FMustQty = FMustQty;
            }

            public String getFRealQty() {
                return FRealQty;
            }

            public void setFRealQty(String FRealQty) {
                this.FRealQty = FRealQty;
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

            public FWorkShopId1Bean getFWorkShopId1() {
                return FWorkShopId1;
            }

            public void setFWorkShopId1(FWorkShopId1Bean FWorkShopId1) {
                this.FWorkShopId1 = FWorkShopId1;
            }

            public FShiftGroupIdBean getFShiftGroupId() {
                return FShiftGroupId;
            }

            public void setFShiftGroupId(FShiftGroupIdBean FShiftGroupId) {
                this.FShiftGroupId = FShiftGroupId;
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

            public FStockUnitIdBean getFStockUnitId() {
                return FStockUnitId;
            }

            public void setFStockUnitId(FStockUnitIdBean FStockUnitId) {
                this.FStockUnitId = FStockUnitId;
            }

            public String getFStockRealQty() {
                return FStockRealQty;
            }

            public void setFStockRealQty(String FStockRealQty) {
                this.FStockRealQty = FStockRealQty;
            }

            public FBaseUnitIdBean getFBaseUnitId() {
                return FBaseUnitId;
            }

            public void setFBaseUnitId(FBaseUnitIdBean FBaseUnitId) {
                this.FBaseUnitId = FBaseUnitId;
            }

            public String getFBaseRealQty() {
                return FBaseRealQty;
            }

            public void setFBaseRealQty(String FBaseRealQty) {
                this.FBaseRealQty = FBaseRealQty;
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

            public String getFMtoNo() {
                return FMtoNo;
            }

            public void setFMtoNo(String FMtoNo) {
                this.FMtoNo = FMtoNo;
            }

            public FStockStatusIdBean getFStockStatusId() {
                return FStockStatusId;
            }

            public void setFStockStatusId(FStockStatusIdBean FStockStatusId) {
                this.FStockStatusId = FStockStatusId;
            }

            public FBomIdBean getFBomId() {
                return FBomId;
            }

            public void setFBomId(FBomIdBean FBomId) {
                this.FBomId = FBomId;
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

            public FSecUnitIdBean getFSecUnitId() {
                return FSecUnitId;
            }

            public void setFSecUnitId(FSecUnitIdBean FSecUnitId) {
                this.FSecUnitId = FSecUnitId;
            }

            public String getFSecRealQty() {
                return FSecRealQty;
            }

            public void setFSecRealQty(String FSecRealQty) {
                this.FSecRealQty = FSecRealQty;
            }

            public String getFMemo() {
                return FMemo;
            }

            public void setFMemo(String FMemo) {
                this.FMemo = FMemo;
            }

            public String getFBaseMustQty() {
                return FBaseMustQty;
            }

            public void setFBaseMustQty(String FBaseMustQty) {
                this.FBaseMustQty = FBaseMustQty;
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

            public static class FAuxpropIdBean {
            }

            public static class FUnitIDBean {
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

            public static class FWorkShopId1Bean {
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

            public static class FShiftGroupIdBean {
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
                private String FCUSTID;
                private String FNAME;

                public String getFCUSTID() {
                    return FCUSTID;
                }

                public void setFCUSTID(String FCUSTID) {
                    this.FCUSTID = FCUSTID;
                }

                public String getFNAME() {
                    return FNAME;
                }

                public void setFNAME(String FNAME) {
                    this.FNAME = FNAME;
                }

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
