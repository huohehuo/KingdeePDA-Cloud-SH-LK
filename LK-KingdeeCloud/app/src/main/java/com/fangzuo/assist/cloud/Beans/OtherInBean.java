package com.fangzuo.assist.cloud.Beans;


import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.Dao.T_main;

import java.util.ArrayList;
import java.util.List;

public class OtherInBean {

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
     * Model : {"FID":"0","FBillNo":"","FBillTypeID":{"FNUMBER":""},"FStockOrgId":{"FNumber":""},"FStockDirect":"","FDate":"1900-01-01","FSUPPLIERID":{"FNumber":""},"FDEPTID":{"FNumber":""},"FACCEPTANCE":{},"FSTOCKERID":{"FNAME":""},"FSTOCKERGROUPID":{"FNumber":""},"FOwnerTypeIdHead":"","FOwnerIdHead":{"FNumber":""},"FNOTE":"","FBaseCurrId":{"FNumber":""},"FScanBox":"","FEntity":[{"FEntryID":"0","FMATERIALID":{"FNumber":""},"FAuxPropId":{},"FUnitID":{"FNumber":""},"FSTOCKSTATUSID":{"FNumber":""},"FQty":"0","FSTOCKID":{"FNumber":""},"FStockLocId":{"FSTOCKLOCID__FF100003":{"FNumber":""},"FSTOCKLOCID__FF100004":{"FNumber":""},"FSTOCKLOCID__FF100012":{"FNumber":""},"FSTOCKLOCID__FF100013":{"FNumber":""},"FSTOCKLOCID__FF100014":{"FNumber":""},"FSTOCKLOCID__FF100015":{"FNumber":""},"FSTOCKLOCID__FF100016":{"FNumber":""},"FSTOCKLOCID__FF100017":{"FNumber":""},"FSTOCKLOCID__FF100018":{"FNumber":""},"FSTOCKLOCID__FF100019":{"FNumber":""}},"FLOT":{"FNumber":""},"FOWNERTYPEID":"","FOWNERID":{"FNumber":""},"FEntryNote":"","FBOMID":{"FNumber":""},"FPRODUCEDATE":"1900-01-01","FMTONO":"","FProjectNo":"","FExtAuxUnitId":{"FNumber":""},"FExtAuxUnitQty":"0","FKEEPERTYPEID":"","FKEEPERID":{"FNumber":""},"FSerialSubEntity":[{"FDetailID":"0","FSerialNo":"","FSerialNote":""}]}]}
     */

    private String Creator;
    private boolean IsDeleteEntry;
    private String SubSystemId;
    private boolean IsVerifyBaseDataField;
    private boolean IsEntryBatchFill;
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

    public boolean getIsDeleteEntry() {
        return IsDeleteEntry;
    }

    public void setIsDeleteEntry(boolean IsDeleteEntry) {
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
         * FStockDirect :
         * FDate : 1900-01-01
         * FSUPPLIERID : {"FNumber":""}
         * FDEPTID : {"FNumber":""}
         * FACCEPTANCE : {}
         * FSTOCKERID : {"FNAME":""}
         * FSTOCKERGROUPID : {"FNumber":""}
         * FOwnerTypeIdHead :
         * FOwnerIdHead : {"FNumber":""}
         * FNOTE :
         * FBaseCurrId : {"FNumber":""}
         * FScanBox :
         * FEntity : [{"FEntryID":"0","FMATERIALID":{"FNumber":""},"FAuxPropId":{},"FUnitID":{"FNumber":""},"FSTOCKSTATUSID":{"FNumber":""},"FQty":"0","FSTOCKID":{"FNumber":""},"FStockLocId":{"FSTOCKLOCID__FF100003":{"FNumber":""},"FSTOCKLOCID__FF100004":{"FNumber":""},"FSTOCKLOCID__FF100012":{"FNumber":""},"FSTOCKLOCID__FF100013":{"FNumber":""},"FSTOCKLOCID__FF100014":{"FNumber":""},"FSTOCKLOCID__FF100015":{"FNumber":""},"FSTOCKLOCID__FF100016":{"FNumber":""},"FSTOCKLOCID__FF100017":{"FNumber":""},"FSTOCKLOCID__FF100018":{"FNumber":""},"FSTOCKLOCID__FF100019":{"FNumber":""}},"FLOT":{"FNumber":""},"FOWNERTYPEID":"","FOWNERID":{"FNumber":""},"FEntryNote":"","FBOMID":{"FNumber":""},"FPRODUCEDATE":"1900-01-01","FMTONO":"","FProjectNo":"","FExtAuxUnitId":{"FNumber":""},"FExtAuxUnitQty":"0","FKEEPERTYPEID":"","FKEEPERID":{"FNumber":""},"FSerialSubEntity":[{"FDetailID":"0","FSerialNo":"","FSerialNote":""}]}]
         */

        private String FID;
        private String FBillNo;
        private FBillTypeIDBean FBillTypeID;
        private FStockOrgIdBean FStockOrgId;
        private String FStockDirect;
        private String FDate;
        private FSUPPLIERIDBean FSUPPLIERID;
        private FDEPTIDBean FDEPTID;
        private FACCEPTANCEBean FACCEPTANCE;
        private FSTOCKERIDBean FSTOCKERID;
        private FSTOCKERGROUPIDBean FSTOCKERGROUPID;
        private String FOwnerTypeIdHead;
        private FOwnerIdHeadBean FOwnerIdHead;
        private String FNOTE;
        private FBaseCurrIdBean FBaseCurrId;
        private String FScanBox;
        private List<FEntityBean> FEntity;
        public void setModelBean(T_main main, List<T_Detail> beans){
            //表头设置-------------------------------------------------------------
            this.FBillNo ="";
//            this.FNOTE ="表头备注";
            this.FBillTypeID=new OtherInBean.ModelBean.FBillTypeIDBean();this.FBillTypeID.FNUMBER=main.FBillTypeID;//单据类型
            this.FStockOrgId=new OtherInBean.ModelBean.FStockOrgIdBean();this.FStockOrgId.FNumber=main.FStockOrgId;//库存组织
            this.FDEPTID = new FDEPTIDBean();this.FDEPTID.FNumber=main.FDepartmentNumber;//部门
            this.FSTOCKERID = new OtherInBean.ModelBean.FSTOCKERIDBean();this.FSTOCKERID.FNAME=main.FStockerNumber;//仓管员
            this.FACCEPTANCE = new FACCEPTANCEBean();this.FACCEPTANCE.FNumber=main.FPurchaserId;       //采购员
            this.FOwnerTypeIdHead = main.FOwnerTypeIdHead;//货主类型
            this.FOwnerIdHead=new OtherInBean.ModelBean.FOwnerIdHeadBean();this.FOwnerIdHead.FNumber=main.FOwnerIdHead;//货主
            this.FDate = main.FDate;//入库日期
            this.FSUPPLIERID=new FSUPPLIERIDBean();this.FSUPPLIERID.FNumber=main.FSupplierId;//供应商
            this.FStockDirect="GENERAL";//库存方向
//            this.FInStockFin=new FInStockFinBean();//一个大类
//            this.FInStockFin.FPriceTimePoint= main.FPriceTimePoint;         //定价时点
//            this.FInStockFin.FSettleOrgId=new FInStockFinBean.FSettleOrgIdBean(main.FSettleOrgId);//结算组织
//            this.FInStockFin.FSettleCurrId=new FInStockFinBean.FSettleCurrIdBean(main.FSettleCurrId);//结算币别
            //----------------------------------------------------------------
            //表体设置----------------------------------------------------------------
            this.FEntity=new ArrayList<>();//一个大类数组
            for (int i = 0; i < beans.size(); i++) {
                FEntityBean bean1=new FEntityBean();//一个大类
                bean1.FStockId=new FEntityBean.FSTOCKIDBean();bean1.FStockId.FNumber=beans.get(i).FStorageId;//仓库
                bean1.FOWNERID=new FEntityBean.FOWNERIDBean();bean1.FOWNERID.FNumber="100";//货主
                bean1.FKEEPERTYPEID="BD_KeeperOrg";//保管者类型
                bean1.FKEEPERID=new FEntityBean.FKEEPERIDBean();bean1.FKEEPERID.FNumber="100";//保管者
                bean1.FLOT=new FEntityBean.FLOTBean();bean1.FLOT.FNumber=beans.get(i).FBatch;//批号
                bean1.FMATERIALID = new FEntityBean.FMATERIALIDBean();bean1.FMATERIALID.FNumber=beans.get(i).FMaterialId;//物料编码

                bean1.FQty=beans.get(i).FRealQty;         //实收数量
                bean1.FUnitID = new FEntityBean.FUnitIDBean();bean1.FUnitID.FNumber=beans.get(i).FUnitID;//库存单位
                bean1.FSTOCKSTATUSID = new FEntityBean.FSTOCKSTATUSIDBean();bean1.FSTOCKSTATUSID.FNumber="KCZT01_SYS";//库存状态
                bean1.FOWNERTYPEID = main.FOwnerTypeIdHead;//货主类型
                bean1.FOWNERID=new FEntityBean.FOWNERIDBean();bean1.FOWNERID.FNumber=main.FOwnerIdHead;//货主
//                bean1.FEntryNote="明细备注";
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

        public FSUPPLIERIDBean getFSUPPLIERID() {
            return FSUPPLIERID;
        }

        public void setFSUPPLIERID(FSUPPLIERIDBean FSUPPLIERID) {
            this.FSUPPLIERID = FSUPPLIERID;
        }

        public FDEPTIDBean getFDEPTID() {
            return FDEPTID;
        }

        public void setFDEPTID(FDEPTIDBean FDEPTID) {
            this.FDEPTID = FDEPTID;
        }

        public FACCEPTANCEBean getFACCEPTANCE() {
            return FACCEPTANCE;
        }

        public void setFACCEPTANCE(FACCEPTANCEBean FACCEPTANCE) {
            this.FACCEPTANCE = FACCEPTANCE;
        }

        public FSTOCKERIDBean getFSTOCKERID() {
            return FSTOCKERID;
        }

        public void setFSTOCKERID(FSTOCKERIDBean FSTOCKERID) {
            this.FSTOCKERID = FSTOCKERID;
        }

        public FSTOCKERGROUPIDBean getFSTOCKERGROUPID() {
            return FSTOCKERGROUPID;
        }

        public void setFSTOCKERGROUPID(FSTOCKERGROUPIDBean FSTOCKERGROUPID) {
            this.FSTOCKERGROUPID = FSTOCKERGROUPID;
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

        public String getFNOTE() {
            return FNOTE;
        }

        public void setFNOTE(String FNOTE) {
            this.FNOTE = FNOTE;
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

        public static class FSUPPLIERIDBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FDEPTIDBean {
            /**
             * FNumber :
             */

            private String FNumber;

            public String getFNumber() {
                return FNumber;
            }

            public void setFNumber(String FNumber) {
                this.FNumber = FNumber;
            }
        }

        public static class FACCEPTANCEBean {
            /**
             * FNumber :
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
             * FMATERIALID : {"FNumber":""}
             * FAuxPropId : {}
             * FUnitID : {"FNumber":""}
             * FSTOCKSTATUSID : {"FNumber":""}
             * FQty : 0
             * FSTOCKID : {"FNumber":""}
             * FStockLocId : {"FSTOCKLOCID__FF100003":{"FNumber":""},"FSTOCKLOCID__FF100004":{"FNumber":""},"FSTOCKLOCID__FF100012":{"FNumber":""},"FSTOCKLOCID__FF100013":{"FNumber":""},"FSTOCKLOCID__FF100014":{"FNumber":""},"FSTOCKLOCID__FF100015":{"FNumber":""},"FSTOCKLOCID__FF100016":{"FNumber":""},"FSTOCKLOCID__FF100017":{"FNumber":""},"FSTOCKLOCID__FF100018":{"FNumber":""},"FSTOCKLOCID__FF100019":{"FNumber":""}}
             * FLOT : {"FNumber":""}
             * FOWNERTYPEID :
             * FOWNERID : {"FNumber":""}
             * FEntryNote :
             * FBOMID : {"FNumber":""}
             * FPRODUCEDATE : 1900-01-01
             * FMTONO :
             * FProjectNo :
             * FExtAuxUnitId : {"FNumber":""}
             * FExtAuxUnitQty : 0
             * FKEEPERTYPEID :
             * FKEEPERID : {"FNumber":""}
             * FSerialSubEntity : [{"FDetailID":"0","FSerialNo":"","FSerialNote":""}]
             */


            public String FEntryID;
            public FAuxPropIdBean FAuxPropId;
            public FUnitIDBean FUnitID;
            public FSTOCKSTATUSIDBean FSTOCKSTATUSID;
            public String FQty;
            public FSTOCKIDBean FStockId;
            public FStockLocIdBean FStockLocId;
            public String FOWNERTYPEID;
            public FOWNERIDBean FOWNERID;
            public String FEntryNote;
            public FBOMIDBean FBOMID;
            public String FPRODUCEDATE;
            public String FMTONO;
            public String FProjectNo;
            public FExtAuxUnitIdBean FExtAuxUnitId;
            public String FExtAuxUnitQty;
            public String FKEEPERTYPEID;
            public FKEEPERIDBean FKEEPERID;
            public List<FSerialSubEntityBean> FSerialSubEntity;
            public FLOTBean FLOT;
            public FMATERIALIDBean FMATERIALID;

            public String getFEntryID() {
                return FEntryID;
            }

            public void setFEntryID(String FEntryID) {
                this.FEntryID = FEntryID;
            }

            public FMATERIALIDBean getFMATERIALID() {
                return FMATERIALID;
            }

            public void setFMATERIALID(FMATERIALIDBean FMATERIALID) {
                this.FMATERIALID = FMATERIALID;
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

            public FSTOCKSTATUSIDBean getFSTOCKSTATUSID() {
                return FSTOCKSTATUSID;
            }

            public void setFSTOCKSTATUSID(FSTOCKSTATUSIDBean FSTOCKSTATUSID) {
                this.FSTOCKSTATUSID = FSTOCKSTATUSID;
            }

            public String getFQty() {
                return FQty;
            }

            public void setFQty(String FQty) {
                this.FQty = FQty;
            }

            public FSTOCKIDBean getFSTOCKID() {
                return FStockId;
            }

            public void setFSTOCKID(FSTOCKIDBean FSTOCKID) {
                this.FStockId = FSTOCKID;
            }

            public FStockLocIdBean getFStockLocId() {
                return FStockLocId;
            }

            public void setFStockLocId(FStockLocIdBean FStockLocId) {
                this.FStockLocId = FStockLocId;
            }

            public FLOTBean getFLOT() {
                return FLOT;
            }

            public void setFLOT(FLOTBean FLOT) {
                this.FLOT = FLOT;
            }

            public String getFOWNERTYPEID() {
                return FOWNERTYPEID;
            }

            public void setFOWNERTYPEID(String FOWNERTYPEID) {
                this.FOWNERTYPEID = FOWNERTYPEID;
            }

            public FOWNERIDBean getFOWNERID() {
                return FOWNERID;
            }

            public void setFOWNERID(FOWNERIDBean FOWNERID) {
                this.FOWNERID = FOWNERID;
            }

            public String getFEntryNote() {
                return FEntryNote;
            }

            public void setFEntryNote(String FEntryNote) {
                this.FEntryNote = FEntryNote;
            }

            public FBOMIDBean getFBOMID() {
                return FBOMID;
            }

            public void setFBOMID(FBOMIDBean FBOMID) {
                this.FBOMID = FBOMID;
            }

            public String getFPRODUCEDATE() {
                return FPRODUCEDATE;
            }

            public void setFPRODUCEDATE(String FPRODUCEDATE) {
                this.FPRODUCEDATE = FPRODUCEDATE;
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

            public String getFKEEPERTYPEID() {
                return FKEEPERTYPEID;
            }

            public void setFKEEPERTYPEID(String FKEEPERTYPEID) {
                this.FKEEPERTYPEID = FKEEPERTYPEID;
            }

            public FKEEPERIDBean getFKEEPERID() {
                return FKEEPERID;
            }

            public void setFKEEPERID(FKEEPERIDBean FKEEPERID) {
                this.FKEEPERID = FKEEPERID;
            }

            public List<FSerialSubEntityBean> getFSerialSubEntity() {
                return FSerialSubEntity;
            }

            public void setFSerialSubEntity(List<FSerialSubEntityBean> FSerialSubEntity) {
                this.FSerialSubEntity = FSerialSubEntity;
            }

            public static class FMATERIALIDBean {
                /**
                 * FNumber :
                 */

                public String FNumber;

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

                public String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FSTOCKSTATUSIDBean {
                /**
                 * FNumber :
                 */

                public String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FSTOCKIDBean {
                /**
                 * FNumber :
                 */

                public String FNumber;

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

                public FSTOCKLOCIDFF100003Bean FSTOCKLOCID__FF100003;
                public FSTOCKLOCIDFF100004Bean FSTOCKLOCID__FF100004;
                public FSTOCKLOCIDFF100012Bean FSTOCKLOCID__FF100012;
                public FSTOCKLOCIDFF100013Bean FSTOCKLOCID__FF100013;
                public FSTOCKLOCIDFF100014Bean FSTOCKLOCID__FF100014;
                public FSTOCKLOCIDFF100015Bean FSTOCKLOCID__FF100015;
                public FSTOCKLOCIDFF100016Bean FSTOCKLOCID__FF100016;
                public FSTOCKLOCIDFF100017Bean FSTOCKLOCID__FF100017;
                public FSTOCKLOCIDFF100018Bean FSTOCKLOCID__FF100018;
                public FSTOCKLOCIDFF100019Bean FSTOCKLOCID__FF100019;

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

                    public String FNumber;

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

                    public String FNumber;

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

                    public String FNumber;

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

                    public String FNumber;

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

                    public String FNumber;

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

                    public String FNumber;

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

                    public String FNumber;

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

                    public String FNumber;

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

                    public String FNumber;

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

                    public String FNumber;

                    public String getFNumber() {
                        return FNumber;
                    }

                    public void setFNumber(String FNumber) {
                        this.FNumber = FNumber;
                    }
                }
            }

            public static class FLOTBean {
                /**
                 * FNumber :
                 */

                public String FNumber;
                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FOWNERIDBean {
                /**
                 * FNumber :
                 */

                public String FNumber;

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

                public String FNumber;

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

                public String FNumber;

                public String getFNumber() {
                    return FNumber;
                }

                public void setFNumber(String FNumber) {
                    this.FNumber = FNumber;
                }
            }

            public static class FKEEPERIDBean {
                /**
                 * FNumber :
                 */

                public String FNumber;

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

                public String FDetailID;
                public String FSerialNo;
                public String FSerialNote;

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
