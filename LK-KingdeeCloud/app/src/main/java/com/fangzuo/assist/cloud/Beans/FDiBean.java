package com.fangzuo.assist.cloud.Beans;


import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.Dao.T_main;

import java.util.ArrayList;
import java.util.List;

public class FDiBean {

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
     * Model : {"FID":"0","FBillNo":"","FBillTypeID":{"FNUMBER":""},"FTransferBizType":"","FTransferDirect":"","FStockOrgID":{"FNumber":""},"FStockOutOrgID":{"FNumber":""},"FDate":"1900-01-01","FTransferMode":"","FOwnerTypeOutIdHead":"","FSTOCKERID":{"FNumber":""},"FOwnerOutIdHead":{"FNumber":""},"FBizType":"","FSTOCKERGROUPID":{"FNumber":""},"FVESTONWAY":"","FOwnerTypeIdHead":"","FBaseCurrID":{"FNumber":""},"FNOTE":"","FScanBox":"","FCustID":{"FNUMBER":""},"FSUPPLIERID":{"FNUMBER":""},"FThirdSrcId":"","FThirdSystem":"","FThirdSrcBillNo":"","FSTKTRSINENTRY":[{"FEntryID":"0","FMaterialID":{"FNumber":""},"FAuxPropId":{},"FUnitID":{"FNumber":""},"FPlanTransferQty":"0","FQty":"0","FPathLossQty":"0","FPathLossRespParty":"","FDestLot":{"FNumber":""},"FSrcStockID":{"FNumber":""},"FDestStockID":{"FNumber":""},"FProduceDate":"1900-01-01","FSRCStockLocId":{"FSRCSTOCKLOCID__FF100003":{"FNumber":""},"FSRCSTOCKLOCID__FF100012":{"FNumber":""}},"FSrcStockStatusID":{"FNumber":""},"FBOMID":{"FNumber":""},"FDestStockStatusID":{"FNumber":""},"FDestStockLocId":{"FDESTSTOCKLOCID__FF100003":{"FNumber":""},"FDESTSTOCKLOCID__FF100012":{"FNumber":""}},"FKeeperTypeOutID":"","FKeeperOutID":{"FNumber":""},"FMTONO":"","FProjectNo":"","FOwnerTypeOutID":"","FOwnerOutID":{"FNumber":""},"FOwnerTypeID":"","FOwnerID":{"FNumber":""},"FEntryNote":"","FBusinessDate":"1900-01-01","FSrcMaterialId":{"FNumber":""},"FSecUnitID":{"FNumber":""},"FSrcProduceDate":"1900-01-01","FLOT":{"FNumber":""},"FSRCBOMID":{"FNumber":""},"FBaseQty":"0","FSRCMTONO":"","FSrcExpiryDate":"1900-01-01","FExtAuxUnitId":{"FNumber":""},"FExtAuxUnitQty":"0","FWayAuxUnitQty":"0","FSrcProjectNo":"","FBasePlanTransQty":"0","FKeeperTypeID":"","FKeeperID":{"FNumber":""},"FBasePathLossQty":"0","FBaseUnitID":{"FNumber":""},"FThirdSrcEntryId":"","FSerialSubEntity":[{"FDetailID":"0","FSerialNo":"","FIsPathLoss":"false","FSerialNote":""}]}]}
     */

    private String Creator;
    private String IsDeleteEntry;
    private String SubSystemId;
    private boolean IsVerifyBaseDataField;
    private boolean IsEntryBatchFill;
    private boolean ValidateFlag;
    private boolean NumberSearch;
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

    public boolean getIsVerifyBaseDataField() {
        return IsVerifyBaseDataField;
    }

    public void setIsVerifyBaseDataField(boolean IsVerifyBaseDataField) {
        this.IsVerifyBaseDataField = IsVerifyBaseDataField;
    }

    public boolean getIsEntryBatchFill() {
        return IsEntryBatchFill;
    }

    public void setIsEntryBatchFill(boolean IsEntryBatchFill) {
        this.IsEntryBatchFill = IsEntryBatchFill;
    }

    public boolean getValidateFlag() {
        return ValidateFlag;
    }

    public void setValidateFlag(boolean ValidateFlag) {
        this.ValidateFlag = ValidateFlag;
    }

    public boolean getNumberSearch() {
        return NumberSearch;
    }

    public void setNumberSearch(boolean NumberSearch) {
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
         * FTransferBizType :
         * FTransferDirect :
         * FStockOrgID : {"FNumber":""}
         * FStockOutOrgID : {"FNumber":""}
         * FDate : 1900-01-01
         * FTransferMode :
         * FOwnerTypeOutIdHead :
         * FSTOCKERID : {"FNumber":""}
         * FOwnerOutIdHead : {"FNumber":""}
         * FBizType :
         * FSTOCKERGROUPID : {"FNumber":""}
         * FVESTONWAY :
         * FOwnerTypeIdHead :
         * FBaseCurrID : {"FNumber":""}
         * FNOTE :
         * FScanBox :
         * FCustID : {"FNUMBER":""}
         * FSUPPLIERID : {"FNUMBER":""}
         * FThirdSrcId :
         * FThirdSystem :
         * FThirdSrcBillNo :
         * FSTKTRSINENTRY : [{"FEntryID":"0","FMaterialID":{"FNumber":""},"FAuxPropId":{},"FUnitID":{"FNumber":""},"FPlanTransferQty":"0","FQty":"0","FPathLossQty":"0","FPathLossRespParty":"","FDestLot":{"FNumber":""},"FSrcStockID":{"FNumber":""},"FDestStockID":{"FNumber":""},"FProduceDate":"1900-01-01","FSRCStockLocId":{"FSRCSTOCKLOCID__FF100003":{"FNumber":""},"FSRCSTOCKLOCID__FF100012":{"FNumber":""}},"FSrcStockStatusID":{"FNumber":""},"FBOMID":{"FNumber":""},"FDestStockStatusID":{"FNumber":""},"FDestStockLocId":{"FDESTSTOCKLOCID__FF100003":{"FNumber":""},"FDESTSTOCKLOCID__FF100012":{"FNumber":""}},"FKeeperTypeOutID":"","FKeeperOutID":{"FNumber":""},"FMTONO":"","FProjectNo":"","FOwnerTypeOutID":"","FOwnerOutID":{"FNumber":""},"FOwnerTypeID":"","FOwnerID":{"FNumber":""},"FEntryNote":"","FBusinessDate":"1900-01-01","FSrcMaterialId":{"FNumber":""},"FSecUnitID":{"FNumber":""},"FSrcProduceDate":"1900-01-01","FLOT":{"FNumber":""},"FSRCBOMID":{"FNumber":""},"FBaseQty":"0","FSRCMTONO":"","FSrcExpiryDate":"1900-01-01","FExtAuxUnitId":{"FNumber":""},"FExtAuxUnitQty":"0","FWayAuxUnitQty":"0","FSrcProjectNo":"","FBasePlanTransQty":"0","FKeeperTypeID":"","FKeeperID":{"FNumber":""},"FBasePathLossQty":"0","FBaseUnitID":{"FNumber":""},"FThirdSrcEntryId":"","FSerialSubEntity":[{"FDetailID":"0","FSerialNo":"","FIsPathLoss":"false","FSerialNote":""}]}]
         */

        private String FID;
        private String FBillNo;
        private FBillTypeIDBean FBillTypeID;
        private String FTransferBizType;
        private String FTransferDirect;
        private FStockOrgIDBean FStockOrgID;
        private FStockOutOrgIDBean FStockOutOrgID;
        private String FDate;
        private String FTransferMode;
        private String FOwnerTypeOutIdHead;
        private FSTOCKERIDBean FSTOCKERID;
        private FOwnerOutIdHeadBean FOwnerOutIdHead;
        private String FBizType;
        private FSTOCKERGROUPIDBean FSTOCKERGROUPID;
        private String FVESTONWAY;
        private String FOwnerTypeIdHead;
        private FBaseCurrIDBean FBaseCurrID;
        private String FNOTE;
        private String FScanBox;
        private FCustIDBean FCustID;
        private FSUPPLIERIDBean FSUPPLIERID;
        private String FThirdSrcId;
        private String FThirdSystem;
        private String FThirdSrcBillNo;
        private List<FSTKTRSINENTRYBean> FSTKTRSINENTRY;
        public void setModelBean(T_main main, List<T_Detail> beans){
            //表头设置-------------------------------------------------------------
            this.FBillNo ="";
            this.FBizType="";//业务类型
            this.FBillTypeID=new FDiBean.ModelBean.FBillTypeIDBean();this.FBillTypeID.FNUMBER=main.FBillTypeID;//单据类型
            this.FStockOutOrgID=new FDiBean.ModelBean.FStockOutOrgIDBean();this.FStockOutOrgID.FNumber=main.FStockOrgId;//调出库存组织
            this.FStockOrgID=new FDiBean.ModelBean.FStockOrgIDBean();this.FStockOrgID.FNumber=main.FStockOrgId;//调入库存组织
            this.FSTOCKERID = new FDiBean.ModelBean.FSTOCKERIDBean();       //仓管员
            this.FSTOCKERID.FNumber=main.FStockerNumber;       //仓管员
            this.FOwnerTypeOutIdHead = main.FOwnerTypeIdHead;//调出货主类型
            this.FOwnerOutIdHead=new FDiBean.ModelBean.FOwnerOutIdHeadBean();//调出货主
            this.FOwnerTypeIdHead = main.FOwnerTypeIdHead;//调入货主类型
//            this.FOwnerIdHead=new FDiBean.ModelBean.FOwnerOutIdHeadBean();//调入货主??没有
            this.FTransferDirect = "";//调拨方向
            this.FTransferMode = "";//调拨方式
            this.FVESTONWAY = "";//携带在途归属
            this.FDate = main.FDate;//日期
            this.FNOTE = "备注";//入库日期
            //----------------------------------------------------------------
            //表体设置----------------------------------------------------------------
            this.FSTKTRSINENTRY=new ArrayList<>();//一个大类数组
            for (int i = 0; i < beans.size(); i++) {
                FSTKTRSINENTRYBean bean = new FDiBean.ModelBean.FSTKTRSINENTRYBean();
                bean.FOwnerID = new FSTKTRSINENTRYBean.FOwnerIDBean();bean.FOwnerID.FNumber = "";//调入货主
                bean.FOwnerTypeID = "";//调入货主类型
                bean.FKeeperID = new FSTKTRSINENTRYBean.FKeeperIDBean();bean.FKeeperID.FNumber = "";//调入保管者
                bean.FKeeperTypeID = "";//调入保管者类型
                bean.FMaterialID = new FSTKTRSINENTRYBean.FMaterialIDBean();bean.FMaterialID.FNumber=beans.get(i).FMaterialId;//物料编码
                bean.FSrcStockID=new FSTKTRSINENTRYBean.FSrcStockIDBean();bean.FSrcStockID.FNumber=beans.get(i).FStorageOutId;//调出仓库
                bean.FDestStockID=new FSTKTRSINENTRYBean.FDestStockIDBean();bean.FDestStockID.FNumber=beans.get(i).FStorageInId;//调入仓库
                bean.FSRCStockLocId=new FSTKTRSINENTRYBean.FSRCStockLocIdBean();//仓位组实体类
                bean.FSRCStockLocId.FSRCSTOCKLOCID__FF100003=new FSTKTRSINENTRYBean.FSRCStockLocIdBean.FSRCSTOCKLOCIDFF100003Bean();//调出仓位
                bean.FSRCStockLocId.FSRCSTOCKLOCID__FF100003.FNumber=beans.get(i).FWaveHouseOutId;//调出仓位
                bean.FSRCStockLocId.FSRCSTOCKLOCID__FF100012=new FSTKTRSINENTRYBean.FSRCStockLocIdBean.FSRCSTOCKLOCIDFF100012Bean();//调出仓位
                bean.FSRCStockLocId.FSRCSTOCKLOCID__FF100012.FNumber=beans.get(i).FWaveHouseOutId;//调出仓位
                bean.FDestStockLocId.FDESTSTOCKLOCID__FF100003=new FSTKTRSINENTRYBean.FDestStockLocIdBean.FDESTSTOCKLOCIDFF100003Bean();//调入仓位
                bean.FDestStockLocId.FDESTSTOCKLOCID__FF100003.FNumber=beans.get(i).FWaveHouseInId;//调入仓位
                bean.FDestStockLocId.FDESTSTOCKLOCID__FF100012=new FSTKTRSINENTRYBean.FDestStockLocIdBean.FDESTSTOCKLOCIDFF100012Bean();//调入仓位
                bean.FDestStockLocId.FDESTSTOCKLOCID__FF100012.FNumber=beans.get(i).FWaveHouseInId;//调入仓位
                bean.FDestLot=new FSTKTRSINENTRYBean.FDestLotBean();//批号
                bean.FDestLot.FNumber=beans.get(i).FBatch;//批号
                bean.FLOT=new FSTKTRSINENTRYBean.FLOTBean();//批号
                bean.FLOT.FNumber=beans.get(i).FBatch;//批号
//                bean1.FRemainInStockQty=beans.get(i).FRemainInStockQty;        //采购数量
                bean.FExtAuxUnitQty=beans.get(i).FRealQty;         //实收数量
                bean.FQty=beans.get(i).FRealQty;         //实收数量
                bean.FUnitID = new FSTKTRSINENTRYBean.FUnitIDBean();bean.FUnitID.FNumber=beans.get(i).FUnitID;//库存单位
                bean.FKeeperOutID = new FSTKTRSINENTRYBean.FKeeperOutIDBean();bean.FKeeperOutID.FNumber="";//调出保管者
                bean.FKeeperTypeOutID = "";//调出保管者类型
                bean.FOwnerOutID = new FSTKTRSINENTRYBean.FOwnerOutIDBean();bean.FOwnerOutID.FNumber="";//调出货主
                bean.FOwnerTypeOutID = "";//调出货主类型

                this.FSTKTRSINENTRY.add(bean);//添加进数组
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

        public String getFTransferBizType() {
            return FTransferBizType;
        }

        public void setFTransferBizType(String FTransferBizType) {
            this.FTransferBizType = FTransferBizType;
        }

        public String getFTransferDirect() {
            return FTransferDirect;
        }

        public void setFTransferDirect(String FTransferDirect) {
            this.FTransferDirect = FTransferDirect;
        }

        public FStockOrgIDBean getFStockOrgID() {
            return FStockOrgID;
        }

        public void setFStockOrgID(FStockOrgIDBean FStockOrgID) {
            this.FStockOrgID = FStockOrgID;
        }

        public FStockOutOrgIDBean getFStockOutOrgID() {
            return FStockOutOrgID;
        }

        public void setFStockOutOrgID(FStockOutOrgIDBean FStockOutOrgID) {
            this.FStockOutOrgID = FStockOutOrgID;
        }

        public String getFDate() {
            return FDate;
        }

        public void setFDate(String FDate) {
            this.FDate = FDate;
        }

        public String getFTransferMode() {
            return FTransferMode;
        }

        public void setFTransferMode(String FTransferMode) {
            this.FTransferMode = FTransferMode;
        }

        public String getFOwnerTypeOutIdHead() {
            return FOwnerTypeOutIdHead;
        }

        public void setFOwnerTypeOutIdHead(String FOwnerTypeOutIdHead) {
            this.FOwnerTypeOutIdHead = FOwnerTypeOutIdHead;
        }

        public FSTOCKERIDBean getFSTOCKERID() {
            return FSTOCKERID;
        }

        public void setFSTOCKERID(FSTOCKERIDBean FSTOCKERID) {
            this.FSTOCKERID = FSTOCKERID;
        }

        public FOwnerOutIdHeadBean getFOwnerOutIdHead() {
            return FOwnerOutIdHead;
        }

        public void setFOwnerOutIdHead(FOwnerOutIdHeadBean FOwnerOutIdHead) {
            this.FOwnerOutIdHead = FOwnerOutIdHead;
        }

        public String getFBizType() {
            return FBizType;
        }

        public void setFBizType(String FBizType) {
            this.FBizType = FBizType;
        }

        public FSTOCKERGROUPIDBean getFSTOCKERGROUPID() {
            return FSTOCKERGROUPID;
        }

        public void setFSTOCKERGROUPID(FSTOCKERGROUPIDBean FSTOCKERGROUPID) {
            this.FSTOCKERGROUPID = FSTOCKERGROUPID;
        }

        public String getFVESTONWAY() {
            return FVESTONWAY;
        }

        public void setFVESTONWAY(String FVESTONWAY) {
            this.FVESTONWAY = FVESTONWAY;
        }

        public String getFOwnerTypeIdHead() {
            return FOwnerTypeIdHead;
        }

        public void setFOwnerTypeIdHead(String FOwnerTypeIdHead) {
            this.FOwnerTypeIdHead = FOwnerTypeIdHead;
        }

        public FBaseCurrIDBean getFBaseCurrID() {
            return FBaseCurrID;
        }

        public void setFBaseCurrID(FBaseCurrIDBean FBaseCurrID) {
            this.FBaseCurrID = FBaseCurrID;
        }

        public String getFNOTE() {
            return FNOTE;
        }

        public void setFNOTE(String FNOTE) {
            this.FNOTE = FNOTE;
        }

        public String getFScanBox() {
            return FScanBox;
        }

        public void setFScanBox(String FScanBox) {
            this.FScanBox = FScanBox;
        }

        public FCustIDBean getFCustID() {
            return FCustID;
        }

        public void setFCustID(FCustIDBean FCustID) {
            this.FCustID = FCustID;
        }

        public FSUPPLIERIDBean getFSUPPLIERID() {
            return FSUPPLIERID;
        }

        public void setFSUPPLIERID(FSUPPLIERIDBean FSUPPLIERID) {
            this.FSUPPLIERID = FSUPPLIERID;
        }

        public String getFThirdSrcId() {
            return FThirdSrcId;
        }

        public void setFThirdSrcId(String FThirdSrcId) {
            this.FThirdSrcId = FThirdSrcId;
        }

        public String getFThirdSystem() {
            return FThirdSystem;
        }

        public void setFThirdSystem(String FThirdSystem) {
            this.FThirdSystem = FThirdSystem;
        }

        public String getFThirdSrcBillNo() {
            return FThirdSrcBillNo;
        }

        public void setFThirdSrcBillNo(String FThirdSrcBillNo) {
            this.FThirdSrcBillNo = FThirdSrcBillNo;
        }

        public List<FSTKTRSINENTRYBean> getFSTKTRSINENTRY() {
            return FSTKTRSINENTRY;
        }

        public void setFSTKTRSINENTRY(List<FSTKTRSINENTRYBean> FSTKTRSINENTRY) {
            this.FSTKTRSINENTRY = FSTKTRSINENTRY;
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

        public static class FStockOrgIDBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FStockOutOrgIDBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FSTOCKERIDBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FOwnerOutIdHeadBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FSTOCKERGROUPIDBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FBaseCurrIDBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FCustIDBean {
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

        public static class FSUPPLIERIDBean {
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

        public static class FSTKTRSINENTRYBean {
            /**
             * FEntryID : 0
             * FMaterialID : {"FNumber":""}
             * FAuxPropId : {}
             * FUnitID : {"FNumber":""}
             * FPlanTransferQty : 0
             * FQty : 0
             * FPathLossQty : 0
             * FPathLossRespParty :
             * FDestLot : {"FNumber":""}
             * FSrcStockID : {"FNumber":""}
             * FDestStockID : {"FNumber":""}
             * FProduceDate : 1900-01-01
             * FSRCStockLocId : {"FSRCSTOCKLOCID__FF100003":{"FNumber":""},"FSRCSTOCKLOCID__FF100012":{"FNumber":""}}
             * FSrcStockStatusID : {"FNumber":""}
             * FBOMID : {"FNumber":""}
             * FDestStockStatusID : {"FNumber":""}
             * FDestStockLocId : {"FDESTSTOCKLOCID__FF100003":{"FNumber":""},"FDESTSTOCKLOCID__FF100012":{"FNumber":""}}
             * FKeeperTypeOutID :
             * FKeeperOutID : {"FNumber":""}
             * FMTONO :
             * FProjectNo :
             * FOwnerTypeOutID :
             * FOwnerOutID : {"FNumber":""}
             * FOwnerTypeID :
             * FOwnerID : {"FNumber":""}
             * FEntryNote :
             * FBusinessDate : 1900-01-01
             * FSrcMaterialId : {"FNumber":""}
             * FSecUnitID : {"FNumber":""}
             * FSrcProduceDate : 1900-01-01
             * FLOT : {"FNumber":""}
             * FSRCBOMID : {"FNumber":""}
             * FBaseQty : 0
             * FSRCMTONO :
             * FSrcExpiryDate : 1900-01-01
             * FExtAuxUnitId : {"FNumber":""}
             * FExtAuxUnitQty : 0
             * FWayAuxUnitQty : 0
             * FSrcProjectNo :
             * FBasePlanTransQty : 0
             * FKeeperTypeID :
             * FKeeperID : {"FNumber":""}
             * FBasePathLossQty : 0
             * FBaseUnitID : {"FNumber":""}
             * FThirdSrcEntryId :
             * FSerialSubEntity : [{"FDetailID":"0","FSerialNo":"","FIsPathLoss":"false","FSerialNote":""}]
             */

            private String FEntryID;
            private FMaterialIDBean FMaterialID;
            private FAuxPropIdBean FAuxPropId;
            private FUnitIDBean FUnitID;
            private String FPlanTransferQty;
            private String FQty;
            private String FPathLossQty;
            private String FPathLossRespParty;
            private FDestLotBean FDestLot;
            private FSrcStockIDBean FSrcStockID;
            private FDestStockIDBean FDestStockID;
            private String FProduceDate;
            private FSRCStockLocIdBean FSRCStockLocId;
            private FSrcStockStatusIDBean FSrcStockStatusID;
            private FBOMIDBean FBOMID;
            private FDestStockStatusIDBean FDestStockStatusID;
            private FDestStockLocIdBean FDestStockLocId;
            private String FKeeperTypeOutID;
            private FKeeperOutIDBean FKeeperOutID;
            private String FMTONO;
            private String FProjectNo;
            private String FOwnerTypeOutID;
            private FOwnerOutIDBean FOwnerOutID;
            private String FOwnerTypeID;
            private FOwnerIDBean FOwnerID;
            private String FEntryNote;
            private String FBusinessDate;
            private FSrcMaterialIdBean FSrcMaterialId;
            private FSecUnitIDBean FSecUnitID;
            private String FSrcProduceDate;
            private FLOTBean FLOT;
            private FSRCBOMIDBean FSRCBOMID;
            private String FBaseQty;
            private String FSRCMTONO;
            private String FSrcExpiryDate;
            private FExtAuxUnitIdBean FExtAuxUnitId;
            private String FExtAuxUnitQty;
            private String FWayAuxUnitQty;
            private String FSrcProjectNo;
            private String FBasePlanTransQty;
            private String FKeeperTypeID;
            private FKeeperIDBean FKeeperID;
            private String FBasePathLossQty;
            private FBaseUnitIDBean FBaseUnitID;
            private String FThirdSrcEntryId;
            private List<FSerialSubEntityBean> FSerialSubEntity;

            public String getFEntryID() {
                return FEntryID;
            }

            public void setFEntryID(String FEntryID) {
                this.FEntryID = FEntryID;
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

            public String getFPlanTransferQty() {
                return FPlanTransferQty;
            }

            public void setFPlanTransferQty(String FPlanTransferQty) {
                this.FPlanTransferQty = FPlanTransferQty;
            }

            public String getFQty() {
                return FQty;
            }

            public void setFQty(String FQty) {
                this.FQty = FQty;
            }

            public String getFPathLossQty() {
                return FPathLossQty;
            }

            public void setFPathLossQty(String FPathLossQty) {
                this.FPathLossQty = FPathLossQty;
            }

            public String getFPathLossRespParty() {
                return FPathLossRespParty;
            }

            public void setFPathLossRespParty(String FPathLossRespParty) {
                this.FPathLossRespParty = FPathLossRespParty;
            }

            public FDestLotBean getFDestLot() {
                return FDestLot;
            }

            public void setFDestLot(FDestLotBean FDestLot) {
                this.FDestLot = FDestLot;
            }

            public FSrcStockIDBean getFSrcStockID() {
                return FSrcStockID;
            }

            public void setFSrcStockID(FSrcStockIDBean FSrcStockID) {
                this.FSrcStockID = FSrcStockID;
            }

            public FDestStockIDBean getFDestStockID() {
                return FDestStockID;
            }

            public void setFDestStockID(FDestStockIDBean FDestStockID) {
                this.FDestStockID = FDestStockID;
            }

            public String getFProduceDate() {
                return FProduceDate;
            }

            public void setFProduceDate(String FProduceDate) {
                this.FProduceDate = FProduceDate;
            }

            public FSRCStockLocIdBean getFSRCStockLocId() {
                return FSRCStockLocId;
            }

            public void setFSRCStockLocId(FSRCStockLocIdBean FSRCStockLocId) {
                this.FSRCStockLocId = FSRCStockLocId;
            }

            public FSrcStockStatusIDBean getFSrcStockStatusID() {
                return FSrcStockStatusID;
            }

            public void setFSrcStockStatusID(FSrcStockStatusIDBean FSrcStockStatusID) {
                this.FSrcStockStatusID = FSrcStockStatusID;
            }

            public FBOMIDBean getFBOMID() {
                return FBOMID;
            }

            public void setFBOMID(FBOMIDBean FBOMID) {
                this.FBOMID = FBOMID;
            }

            public FDestStockStatusIDBean getFDestStockStatusID() {
                return FDestStockStatusID;
            }

            public void setFDestStockStatusID(FDestStockStatusIDBean FDestStockStatusID) {
                this.FDestStockStatusID = FDestStockStatusID;
            }

            public FDestStockLocIdBean getFDestStockLocId() {
                return FDestStockLocId;
            }

            public void setFDestStockLocId(FDestStockLocIdBean FDestStockLocId) {
                this.FDestStockLocId = FDestStockLocId;
            }

            public String getFKeeperTypeOutID() {
                return FKeeperTypeOutID;
            }

            public void setFKeeperTypeOutID(String FKeeperTypeOutID) {
                this.FKeeperTypeOutID = FKeeperTypeOutID;
            }

            public FKeeperOutIDBean getFKeeperOutID() {
                return FKeeperOutID;
            }

            public void setFKeeperOutID(FKeeperOutIDBean FKeeperOutID) {
                this.FKeeperOutID = FKeeperOutID;
            }

            public String getFMTONO() {
                return FMTONO;
            }

            public void setFMTONO(String FMTONO) {
                this.FMTONO = FMTONO;
            }

            public String getFProjectNo() {
                return FProjectNo;
            }

            public void setFProjectNo(String FProjectNo) {
                this.FProjectNo = FProjectNo;
            }

            public String getFOwnerTypeOutID() {
                return FOwnerTypeOutID;
            }

            public void setFOwnerTypeOutID(String FOwnerTypeOutID) {
                this.FOwnerTypeOutID = FOwnerTypeOutID;
            }

            public FOwnerOutIDBean getFOwnerOutID() {
                return FOwnerOutID;
            }

            public void setFOwnerOutID(FOwnerOutIDBean FOwnerOutID) {
                this.FOwnerOutID = FOwnerOutID;
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

            public String getFEntryNote() {
                return FEntryNote;
            }

            public void setFEntryNote(String FEntryNote) {
                this.FEntryNote = FEntryNote;
            }

            public String getFBusinessDate() {
                return FBusinessDate;
            }

            public void setFBusinessDate(String FBusinessDate) {
                this.FBusinessDate = FBusinessDate;
            }

            public FSrcMaterialIdBean getFSrcMaterialId() {
                return FSrcMaterialId;
            }

            public void setFSrcMaterialId(FSrcMaterialIdBean FSrcMaterialId) {
                this.FSrcMaterialId = FSrcMaterialId;
            }

            public FSecUnitIDBean getFSecUnitID() {
                return FSecUnitID;
            }

            public void setFSecUnitID(FSecUnitIDBean FSecUnitID) {
                this.FSecUnitID = FSecUnitID;
            }

            public String getFSrcProduceDate() {
                return FSrcProduceDate;
            }

            public void setFSrcProduceDate(String FSrcProduceDate) {
                this.FSrcProduceDate = FSrcProduceDate;
            }

            public FLOTBean getFLOT() {
                return FLOT;
            }

            public void setFLOT(FLOTBean FLOT) {
                this.FLOT = FLOT;
            }

            public FSRCBOMIDBean getFSRCBOMID() {
                return FSRCBOMID;
            }

            public void setFSRCBOMID(FSRCBOMIDBean FSRCBOMID) {
                this.FSRCBOMID = FSRCBOMID;
            }

            public String getFBaseQty() {
                return FBaseQty;
            }

            public void setFBaseQty(String FBaseQty) {
                this.FBaseQty = FBaseQty;
            }

            public String getFSRCMTONO() {
                return FSRCMTONO;
            }

            public void setFSRCMTONO(String FSRCMTONO) {
                this.FSRCMTONO = FSRCMTONO;
            }

            public String getFSrcExpiryDate() {
                return FSrcExpiryDate;
            }

            public void setFSrcExpiryDate(String FSrcExpiryDate) {
                this.FSrcExpiryDate = FSrcExpiryDate;
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

            public String getFWayAuxUnitQty() {
                return FWayAuxUnitQty;
            }

            public void setFWayAuxUnitQty(String FWayAuxUnitQty) {
                this.FWayAuxUnitQty = FWayAuxUnitQty;
            }

            public String getFSrcProjectNo() {
                return FSrcProjectNo;
            }

            public void setFSrcProjectNo(String FSrcProjectNo) {
                this.FSrcProjectNo = FSrcProjectNo;
            }

            public String getFBasePlanTransQty() {
                return FBasePlanTransQty;
            }

            public void setFBasePlanTransQty(String FBasePlanTransQty) {
                this.FBasePlanTransQty = FBasePlanTransQty;
            }

            public String getFKeeperTypeID() {
                return FKeeperTypeID;
            }

            public void setFKeeperTypeID(String FKeeperTypeID) {
                this.FKeeperTypeID = FKeeperTypeID;
            }

            public FKeeperIDBean getFKeeperID() {
                return FKeeperID;
            }

            public void setFKeeperID(FKeeperIDBean FKeeperID) {
                this.FKeeperID = FKeeperID;
            }

            public String getFBasePathLossQty() {
                return FBasePathLossQty;
            }

            public void setFBasePathLossQty(String FBasePathLossQty) {
                this.FBasePathLossQty = FBasePathLossQty;
            }

            public FBaseUnitIDBean getFBaseUnitID() {
                return FBaseUnitID;
            }

            public void setFBaseUnitID(FBaseUnitIDBean FBaseUnitID) {
                this.FBaseUnitID = FBaseUnitID;
            }

            public String getFThirdSrcEntryId() {
                return FThirdSrcEntryId;
            }

            public void setFThirdSrcEntryId(String FThirdSrcEntryId) {
                this.FThirdSrcEntryId = FThirdSrcEntryId;
            }

            public List<FSerialSubEntityBean> getFSerialSubEntity() {
                return FSerialSubEntity;
            }

            public void setFSerialSubEntity(List<FSerialSubEntityBean> FSerialSubEntity) {
                this.FSerialSubEntity = FSerialSubEntity;
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

            public static class FDestLotBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FSrcStockIDBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FDestStockIDBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FSRCStockLocIdBean {
                /**
                 * FSRCSTOCKLOCID__FF100003 : {"FNumber":""}
                 * FSRCSTOCKLOCID__FF100012 : {"FNumber":""}
                 */

                private FSRCSTOCKLOCIDFF100003Bean FSRCSTOCKLOCID__FF100003;
                private FSRCSTOCKLOCIDFF100012Bean FSRCSTOCKLOCID__FF100012;

                public FSRCSTOCKLOCIDFF100003Bean getFSRCSTOCKLOCID__FF100003() {
                    return FSRCSTOCKLOCID__FF100003;
                }

                public void setFSRCSTOCKLOCID__FF100003(FSRCSTOCKLOCIDFF100003Bean FSRCSTOCKLOCID__FF100003) {
                    this.FSRCSTOCKLOCID__FF100003 = FSRCSTOCKLOCID__FF100003;
                }

                public FSRCSTOCKLOCIDFF100012Bean getFSRCSTOCKLOCID__FF100012() {
                    return FSRCSTOCKLOCID__FF100012;
                }

                public void setFSRCSTOCKLOCID__FF100012(FSRCSTOCKLOCIDFF100012Bean FSRCSTOCKLOCID__FF100012) {
                    this.FSRCSTOCKLOCID__FF100012 = FSRCSTOCKLOCID__FF100012;
                }

                public static class FSRCSTOCKLOCIDFF100003Bean {
                    /**
                     * FNumber :
                     */

                    private String FNumber;

                    public String getFNumber() {
                        return FNumber;
                    }

                    public void setFNumber(String FNumber) {
                        this.FNumber = FNumber;
                    }
                }

                public static class FSRCSTOCKLOCIDFF100012Bean {
                    /**
                     * FNumber :
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

            public static class FSrcStockStatusIDBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FBOMIDBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FDestStockStatusIDBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FDestStockLocIdBean {
                /**
                 * FDESTSTOCKLOCID__FF100003 : {"FNumber":""}
                 * FDESTSTOCKLOCID__FF100012 : {"FNumber":""}
                 */

                private FDESTSTOCKLOCIDFF100003Bean FDESTSTOCKLOCID__FF100003;
                private FDESTSTOCKLOCIDFF100012Bean FDESTSTOCKLOCID__FF100012;

                public FDESTSTOCKLOCIDFF100003Bean getFDESTSTOCKLOCID__FF100003() {
                    return FDESTSTOCKLOCID__FF100003;
                }

                public void setFDESTSTOCKLOCID__FF100003(FDESTSTOCKLOCIDFF100003Bean FDESTSTOCKLOCID__FF100003) {
                    this.FDESTSTOCKLOCID__FF100003 = FDESTSTOCKLOCID__FF100003;
                }

                public FDESTSTOCKLOCIDFF100012Bean getFDESTSTOCKLOCID__FF100012() {
                    return FDESTSTOCKLOCID__FF100012;
                }

                public void setFDESTSTOCKLOCID__FF100012(FDESTSTOCKLOCIDFF100012Bean FDESTSTOCKLOCID__FF100012) {
                    this.FDESTSTOCKLOCID__FF100012 = FDESTSTOCKLOCID__FF100012;
                }

                public static class FDESTSTOCKLOCIDFF100003Bean {
                    /**
                     * FNumber :
                     */

                    private String FNumber;

                    public String getFNumber() {
                        return FNumber;
                    }

                    public void setFNumber(String FNumber) {
                        this.FNumber = FNumber;
                    }
                }

                public static class FDESTSTOCKLOCIDFF100012Bean {
                    /**
                     * FNumber :
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

            public static class FKeeperOutIDBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FOwnerOutIDBean {
                /**
                 * FNumber :
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

            public static class FSrcMaterialIdBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FSecUnitIDBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FLOTBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FSRCBOMIDBean {
                /**
                 * FNumber :
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

            public static class FKeeperIDBean {
                /**
                 * FNumber :
                 */

                private String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FBaseUnitIDBean {
                /**
                 * FNumber :
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
                 * FIsPathLoss : false
                 * FSerialNote :
                 */

                private String FDetailID;
                private String FSerialNo;
                private String FIsPathLoss;
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

                public String getFIsPathLoss() {
                    return FIsPathLoss;
                }

                public void setFIsPathLoss(String FIsPathLoss) {
                    this.FIsPathLoss = FIsPathLoss;
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
