package com.fangzuo.assist.cloud.Beans;


import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.Dao.T_main;

import java.util.ArrayList;
import java.util.List;

public class OtherOutBean {

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
     * Model : {"FID":"0","FBillNo":"","FBillTypeID":{"FNUMBER":""},"FStockOrgId":{"FNumber":""},"FPickOrgId":{"FNumber":""},"FStockDirect":"","FDate":"1900-01-01","FCustId":{"FNumber":""},"FDeptId":{"FNumber":""},"FPickerId":{},"FStockerId":{"FNAME":""},"FStockerGroupId":{"FNumber":""},"FBizType":"","FOwnerTypeIdHead":"","FOwnerIdHead":{"FNumber":""},"FNote":"","FBaseCurrId":{"FNumber":""},"FScanBox":"","FEntity":[{"FEntryID":"0","FMaterialId":{"FNumber":""},"FAuxPropId":{},"FUnitID":{"FNumber":""},"FQty":"0","FBaseUnitId":{"FNumber":""},"FStockId":{"FNumber":""},"FStockLocId":{"FSTOCKLOCID__FF100003":{"FNumber":""},"FSTOCKLOCID__FF100004":{"FNumber":""},"FSTOCKLOCID__FF100012":{"FNumber":""},"FSTOCKLOCID__FF100013":{"FNumber":""},"FSTOCKLOCID__FF100014":{"FNumber":""},"FSTOCKLOCID__FF100015":{"FNumber":""},"FSTOCKLOCID__FF100016":{"FNumber":""},"FSTOCKLOCID__FF100017":{"FNumber":""},"FSTOCKLOCID__FF100018":{"FNumber":""},"FSTOCKLOCID__FF100019":{"FNumber":""}},"FLot":{"FNumber":""},"FOwnerTypeId":"","FPRODUCTGROUPID":{"FNUMBER":""},"FOwnerId":{"FNumber":""},"FEntryNote":"","FBomId":{"FNumber":""},"FProjectNo":"","FProduceDate":"1900-01-01","FServiceContext":"","FStockStatusId":{"FNumber":""},"FMtoNo":"","FCostItem":{"FNUMBER":""},"FKeeperTypeId":"","FDistribution":"false","FKeeperId":{"FNumber":""},"FExtAuxUnitId":{"FNumber":""},"FExtAuxUnitQty":"0","FSerialSubEntity":[{"FDetailID":"0","FSerialNo":"","FSerialNote":""}]}]}
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
         * FBillTypeID : {"FNUMBER":""}
         * FStockOrgId : {"FNumber":""}
         * FPickOrgId : {"FNumber":""}
         * FStockDirect :
         * FDate : 1900-01-01
         * FCustId : {"FNumber":""}
         * FDeptId : {"FNumber":""}
         * FPickerId : {}
         * FStockerId : {"FNAME":""}
         * FStockerGroupId : {"FNumber":""}
         * FBizType :
         * FOwnerTypeIdHead :
         * FOwnerIdHead : {"FNumber":""}
         * FNote :
         * FBaseCurrId : {"FNumber":""}
         * FScanBox :
         * FEntity : [{"FEntryID":"0","FMaterialId":{"FNumber":""},"FAuxPropId":{},"FUnitID":{"FNumber":""},"FQty":"0","FBaseUnitId":{"FNumber":""},"FStockId":{"FNumber":""},"FStockLocId":{"FSTOCKLOCID__FF100003":{"FNumber":""},"FSTOCKLOCID__FF100004":{"FNumber":""},"FSTOCKLOCID__FF100012":{"FNumber":""},"FSTOCKLOCID__FF100013":{"FNumber":""},"FSTOCKLOCID__FF100014":{"FNumber":""},"FSTOCKLOCID__FF100015":{"FNumber":""},"FSTOCKLOCID__FF100016":{"FNumber":""},"FSTOCKLOCID__FF100017":{"FNumber":""},"FSTOCKLOCID__FF100018":{"FNumber":""},"FSTOCKLOCID__FF100019":{"FNumber":""}},"FLot":{"FNumber":""},"FOwnerTypeId":"","FPRODUCTGROUPID":{"FNUMBER":""},"FOwnerId":{"FNumber":""},"FEntryNote":"","FBomId":{"FNumber":""},"FProjectNo":"","FProduceDate":"1900-01-01","FServiceContext":"","FStockStatusId":{"FNumber":""},"FMtoNo":"","FCostItem":{"FNUMBER":""},"FKeeperTypeId":"","FDistribution":"false","FKeeperId":{"FNumber":""},"FExtAuxUnitId":{"FNumber":""},"FExtAuxUnitQty":"0","FSerialSubEntity":[{"FDetailID":"0","FSerialNo":"","FSerialNote":""}]}]
         */

        private String FID;
        private String FBillNo;
        private FBillTypeIDBean FBillTypeID;
        private FStockOrgIdBean FStockOrgId;
        private FPickOrgIdBean FPickOrgId;
        private String FStockDirect;
        private String FDate;
        private FCustIdBean FCustId;
        private FDeptIdBean FDeptId;
        private FPickerIdBean FPickerId;
        private FStockerIdBean FStockerId;
        private FStockerGroupIdBean FStockerGroupId;
        private String FBizType;
        private String FOwnerTypeIdHead;
        private FOwnerIdHeadBean FOwnerIdHead;
        private String FNote;
        private FBaseCurrIdBean FBaseCurrId;
        private String FScanBox;
        private List<FEntityBean> FEntity;
        public void setModelBean(T_main main, List<T_Detail> beans){
            //表头设置-------------------------------------------------------------
            this.FBillNo ="";
            this.FStockOrgId=new OtherOutBean.ModelBean.FStockOrgIdBean();             //库存组织
            this.FStockOrgId.FNumber=main.FStockOrgId;             //库存组织
//            this.FPurchaseOrgId=new FPurchaseOrgIdBean(main.FPurchaseOrgId);    //采购组织
            this.FDeptId = new FDeptIdBean();//收料部门
            this.FDeptId.FNumber=main.FDepartmentNumber;//部门
//            this.FPurchaseDeptId = new FPurchaseDeptIdBean(main.FPurchaseDeptId);//采购部门
            this.FStockerId = new OtherOutBean.ModelBean.FStockerIdBean();       //仓管员
            this.FStockerId.FNAME=main.FStockerNumber;       //仓管员
            this.FPickerId = new FPickerIdBean();       //采购员
            this.FPickerId.FNumber=main.FPurchaserId;       //采购员
            this.FBillTypeID=new OtherOutBean.ModelBean.FBillTypeIDBean();//单据类型
            this.FBillTypeID.FNUMBER=main.FBillTypeID;//单据类型
            this.FOwnerTypeIdHead = main.FOwnerTypeIdHead;//货主类型
            this.FOwnerIdHead=new OtherOutBean.ModelBean.FOwnerIdHeadBean();//货主
            this.FOwnerIdHead.FNumber=main.FOwnerIdHead;//货主
            this.FOwnerIdHead.FName="香港荣源木业";//货主
            this.FDate = main.FDate;//入库日期
            this.FCustId=new FCustIdBean();//供应商
            this.FCustId.FNumber=main.FCustomerID;//供应商
//            this.FInStockFin=new FInStockFinBean();//一个大类
//            this.FInStockFin.FPriceTimePoint= main.FPriceTimePoint;         //定价时点
//            this.FInStockFin.FSettleOrgId=new FInStockFinBean.FSettleOrgIdBean(main.FSettleOrgId);//结算组织
//            this.FInStockFin.FSettleCurrId=new FInStockFinBean.FSettleCurrIdBean(main.FSettleCurrId);//结算币别
            //----------------------------------------------------------------
            //表体设置----------------------------------------------------------------
            this.FEntity=new ArrayList<>();//一个大类数组
            for (int i = 0; i < beans.size(); i++) {
                OtherOutBean.ModelBean.FEntityBean bean1=new OtherOutBean.ModelBean.FEntityBean();//一个大类FSTOCKSTATUSID
                bean1.FMaterialId = new OtherOutBean.ModelBean.FEntityBean.FMaterialIdBean();//物料编码
                bean1.FMaterialId.FNumber=beans.get(i).FMaterialId;//物料编码
                bean1.FStockId=new OtherOutBean.ModelBean.FEntityBean.FStockIdBean();//仓库
                bean1.FStockId.FNumber=beans.get(i).FStorageId;//仓库
                bean1.FLot=new OtherOutBean.ModelBean.FEntityBean.FLotBean();//批号
                bean1.FLot.FNumber=beans.get(i).FBatch;//批号
//                bean1.FLot.FLOTID="110072";//批号
//                bean1.FRemainInStockQty=beans.get(i).FRemainInStockQty;        //采购数量
                bean1.FQty=beans.get(i).FRealQty;         //实收数量
//                bean1.FRemainInStockUnitId = new FEntityBean.FRemainInStockUnitIdBean(beans.get(i).FRemainInStockUnitId);//采购单位
//                bean1.FPriceUnitID = new FEntityBean.FPriceUnitIDBean(beans.get(i).FPriceUnitID);//计价单位
                bean1.FUnitID = new OtherOutBean.ModelBean.FEntityBean.FUnitIDBean();//库存单位
                bean1.FUnitID.FNumber=beans.get(i).FUnitID;//库存单位
                bean1.FStockStatusId = new OtherOutBean.ModelBean.FEntityBean.FStockStatusIdBean();//库存状态
                bean1.FStockStatusId.FNumber="KCZT01_SYS";//库存状态
                bean1.FOwnerId = new OtherOutBean.ModelBean.FEntityBean.FOwnerIdBean();//库存状态
                bean1.FOwnerId.FNumber="100";//库存状态

                this.FEntity.add(bean1);//添加进数组
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

        public FBillTypeIDBean getFBillTypeID() {
            return FBillTypeID;
        }

        public void setFBillTypeID(FBillTypeIDBean FBillTypeID) {
            this.FBillTypeID = FBillTypeID;
        }

        public FStockOrgIdBean getFStockOrgId() {
            return FStockOrgId;
        }

        public void setFStockOrgId(FStockOrgIdBean FStockOrgId) {
            this.FStockOrgId = FStockOrgId;
        }

        public FPickOrgIdBean getFPickOrgId() {
            return FPickOrgId;
        }

        public void setFPickOrgId(FPickOrgIdBean FPickOrgId) {
            this.FPickOrgId = FPickOrgId;
        }

        public String getFStockDirect() {
            return FStockDirect;
        }

        public void setFStockDirect(String FStockDirect) {
            this.FStockDirect = FStockDirect;
        }

        public String getFDate() {
            return FDate;
        }

        public void setFDate(String FDate) {
            this.FDate = FDate;
        }

        public FCustIdBean getFCustId() {
            return FCustId;
        }

        public void setFCustId(FCustIdBean FCustId) {
            this.FCustId = FCustId;
        }

        public FDeptIdBean getFDeptId() {
            return FDeptId;
        }

        public void setFDeptId(FDeptIdBean FDeptId) {
            this.FDeptId = FDeptId;
        }

        public FPickerIdBean getFPickerId() {
            return FPickerId;
        }

        public void setFPickerId(FPickerIdBean FPickerId) {
            this.FPickerId = FPickerId;
        }

        public FStockerIdBean getFStockerId() {
            return FStockerId;
        }

        public void setFStockerId(FStockerIdBean FStockerId) {
            this.FStockerId = FStockerId;
        }

        public FStockerGroupIdBean getFStockerGroupId() {
            return FStockerGroupId;
        }

        public void setFStockerGroupId(FStockerGroupIdBean FStockerGroupId) {
            this.FStockerGroupId = FStockerGroupId;
        }

        public String getFBizType() {
            return FBizType;
        }

        public void setFBizType(String FBizType) {
            this.FBizType = FBizType;
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

        public String getFNote() {
            return FNote;
        }

        public void setFNote(String FNote) {
            this.FNote = FNote;
        }

        public FBaseCurrIdBean getFBaseCurrId() {
            return FBaseCurrId;
        }

        public void setFBaseCurrId(FBaseCurrIdBean FBaseCurrId) {
            this.FBaseCurrId = FBaseCurrId;
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

        public static class FPickOrgIdBean {
            /**
             * FNumber :
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

        public static class FDeptIdBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FPickerIdBean {
            /**
             * FNumber :
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

        public static class FOwnerIdHeadBean {
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

        public static class FBaseCurrIdBean {
            /**
             * FNumber :
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
             * FAuxPropId : {}
             * FUnitID : {"FNumber":""}
             * FQty : 0
             * FBaseUnitId : {"FNumber":""}
             * FStockId : {"FNumber":""}
             * FStockLocId : {"FSTOCKLOCID__FF100003":{"FNumber":""},"FSTOCKLOCID__FF100004":{"FNumber":""},"FSTOCKLOCID__FF100012":{"FNumber":""},"FSTOCKLOCID__FF100013":{"FNumber":""},"FSTOCKLOCID__FF100014":{"FNumber":""},"FSTOCKLOCID__FF100015":{"FNumber":""},"FSTOCKLOCID__FF100016":{"FNumber":""},"FSTOCKLOCID__FF100017":{"FNumber":""},"FSTOCKLOCID__FF100018":{"FNumber":""},"FSTOCKLOCID__FF100019":{"FNumber":""}}
             * FLot : {"FNumber":""}
             * FOwnerTypeId :
             * FPRODUCTGROUPID : {"FNUMBER":""}
             * FOwnerId : {"FNumber":""}
             * FEntryNote :
             * FBomId : {"FNumber":""}
             * FProjectNo :
             * FProduceDate : 1900-01-01
             * FServiceContext :
             * FStockStatusId : {"FNumber":""}
             * FMtoNo :
             * FCostItem : {"FNUMBER":""}
             * FKeeperTypeId :
             * FDistribution : false
             * FKeeperId : {"FNumber":""}
             * FExtAuxUnitId : {"FNumber":""}
             * FExtAuxUnitQty : 0
             * FSerialSubEntity : [{"FDetailID":"0","FSerialNo":"","FSerialNote":""}]
             */

            private String FEntryID;
            private FMaterialIdBean FMaterialId;
            private FAuxPropIdBean FAuxPropId;
            private FUnitIDBean FUnitID;
            private String FQty;
            private FBaseUnitIdBean FBaseUnitId;
            private FStockIdBean FStockId;
            private FStockLocIdBean FStockLocId;
            private FLotBean FLot;
            private String FOwnerTypeId;
            private FPRODUCTGROUPIDBean FPRODUCTGROUPID;
            private FOwnerIdBean FOwnerId;
            private String FEntryNote;
            private FBomIdBean FBomId;
            private String FProjectNo;
            private String FProduceDate;
            private String FServiceContext;
            private FStockStatusIdBean FStockStatusId;
            private String FMtoNo;
            private FCostItemBean FCostItem;
            private String FKeeperTypeId;
            private String FDistribution;
            private FKeeperIdBean FKeeperId;
            private FExtAuxUnitIdBean FExtAuxUnitId;
            private String FExtAuxUnitQty;
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

            public String getFQty() {
                return FQty;
            }

            public void setFQty(String FQty) {
                this.FQty = FQty;
            }

            public FBaseUnitIdBean getFBaseUnitId() {
                return FBaseUnitId;
            }

            public void setFBaseUnitId(FBaseUnitIdBean FBaseUnitId) {
                this.FBaseUnitId = FBaseUnitId;
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

            public String getFOwnerTypeId() {
                return FOwnerTypeId;
            }

            public void setFOwnerTypeId(String FOwnerTypeId) {
                this.FOwnerTypeId = FOwnerTypeId;
            }

            public FPRODUCTGROUPIDBean getFPRODUCTGROUPID() {
                return FPRODUCTGROUPID;
            }

            public void setFPRODUCTGROUPID(FPRODUCTGROUPIDBean FPRODUCTGROUPID) {
                this.FPRODUCTGROUPID = FPRODUCTGROUPID;
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

            public FBomIdBean getFBomId() {
                return FBomId;
            }

            public void setFBomId(FBomIdBean FBomId) {
                this.FBomId = FBomId;
            }

            public String getFProjectNo() {
                return FProjectNo;
            }

            public void setFProjectNo(String FProjectNo) {
                this.FProjectNo = FProjectNo;
            }

            public String getFProduceDate() {
                return FProduceDate;
            }

            public void setFProduceDate(String FProduceDate) {
                this.FProduceDate = FProduceDate;
            }

            public String getFServiceContext() {
                return FServiceContext;
            }

            public void setFServiceContext(String FServiceContext) {
                this.FServiceContext = FServiceContext;
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

            public FCostItemBean getFCostItem() {
                return FCostItem;
            }

            public void setFCostItem(FCostItemBean FCostItem) {
                this.FCostItem = FCostItem;
            }

            public String getFKeeperTypeId() {
                return FKeeperTypeId;
            }

            public void setFKeeperTypeId(String FKeeperTypeId) {
                this.FKeeperTypeId = FKeeperTypeId;
            }

            public String getFDistribution() {
                return FDistribution;
            }

            public void setFDistribution(String FDistribution) {
                this.FDistribution = FDistribution;
            }

            public FKeeperIdBean getFKeeperId() {
                return FKeeperId;
            }

            public void setFKeeperId(FKeeperIdBean FKeeperId) {
                this.FKeeperId = FKeeperId;
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
                private String FLOTID;

                public String getFLOTID() {
                    return FLOTID;
                }

                public void setFLOTID(String FLOTID) {
                    this.FLOTID = FLOTID;
                }

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

            public static class FCostItemBean {
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
