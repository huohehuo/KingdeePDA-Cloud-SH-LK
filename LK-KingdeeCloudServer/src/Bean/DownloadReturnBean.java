package Bean;

import java.util.ArrayList;

public class DownloadReturnBean {
    public ArrayList<bibiezhong> bibiezhongs;//�ұ��
    public ArrayList<department> department;//���ű�
    public ArrayList<employee> employee;//ְԱ��
    public ArrayList<wavehouse> wavehouse;//��λ��
    public ArrayList<InstorageNum> InstorageNum;//����
    public ArrayList<storage> storage;//�ֿ��
    public ArrayList<Unit> units;//��λ��
    public ArrayList<BarCode> BarCode;//�����
    public ArrayList<suppliers> suppliers;//��Ӧ�̱�
    public ArrayList<payType> payTypes;//���㷽ʽ��
    public ArrayList<product> products;//��Ʒ���ϱ�
    public ArrayList<User> User;//�û���Ϣ��
    public ArrayList<Client> clients;//�ͻ���Ϣ��
    public ArrayList<GetGoodsDepartment> getGoodsDepartments;//������λ
    public ArrayList<purchaseMethod> purchaseMethod;//����/�ɹ���ʽ��
    public ArrayList<yuandanType> yuandanTypes;//Դ������
    public ArrayList<wanglaikemu> wanglaikemu;//������Ŀ
    public ArrayList<PriceMethod> priceMethods;//�۸�����
    public ArrayList<InStorageType> inStorageTypes;
    public ArrayList<buyer> buyers;
    public ArrayList<StoreMan> storeMans;
    public ArrayList<SaleMan> saleMans;
    public ArrayList<Org> orgs;
    public ArrayList<CodeCheckBackDataBean> codeCheckBackDataBeans;
    public ArrayList<AuxSignSecCheckBean> auxSignSecCheckBeans;
    public ArrayList<PrintDataBean> printDataBeans;
    public ArrayList<PrintHistory> printHistories;
    public ArrayList<RemarkData> remarkDatas;
    public ArrayList<WortPrintData> wortPrintDatas;
    public ArrayList<DryingGetData> dryingGetDatas;
    public ArrayList<PGetData> pGetDatas;
    public ArrayList<BatchDataBean> batchDataBeans;

    public int size;

    public class BatchDataBean {
        public String FBarCode;
        public String FName;
        public String FQty;
        public String FBatchNo;
        public String FLenght;
        public String FDiv;
        public String FBLenght;
        public String FBWide;
        public String FThick;
        public String FVol;
        public String FTypeID;
        public String FCeng;

    }

    public class PGetData {
        public String FID;
        public String FBillNo;
        public String PCS;
        public String M3;
        public String FAccountID;
        public String FWide;
        public String FStr1;
        public String FStr2;
        public String FStr3;
        public String FStr4;
        public String FStr5;

    }



    public class DryingGetData {
        public String FID;
        public String FEntryID;
        public String FItemID;
        public String FUnitID;
        public String FDCStockID;
        public String FDCSPID;
        public String FSTOCKORGID;
        public String FOWNERID;
        public String FBoxCode;
        public String FSplitBoxCode;
        public String FDate;
        public String FUser;
        public String FName;
        public String FUnit;
        public String FModel;
        public String FBatch;
        public String FLenght;
        public String FChang;
        public String FKuan;
        public String FHou;
        public String FQty;
        public String FQtySplit;
        public String FM2Split;
        public String FQtySum;
        public String FM2;
        public String FM2Sum;
        public String FVolSum;
        public String FTip;


        public String FBarcode;
        public String IMIE;
        public String FBillerID;
        public long FOrderId;
        public int activity;

        //用于回单
        public String FNumber;
        public String FUnitNumber;
        public String FStorageNumber;
        public String FStoreOrgNumber;
        public String FHuozhuNumber;
//    public String FBatch;
//    public String FLenght;
//    public String FQty;
//    public String FM2;
    }
    public class WortPrintData {
        public String FID;
        public String FEntryID;
        public String FItemID;
        public String FUnitID;
        public String FDCStockID;
        public String FDCSPID;
        public String FSTOCKORGID;
        public String FOWNERID;
        public String FBoxCode;
        public String FDate;
        public String FUser;
        public String FName;
        public String FUnit;
        public String FModel;
        public String FBatch;
        public String FLenght;
        public String FChang;
        public String FKuan;
        public String FHou;
        public String FQty;
        public String FQtySum;
        public String FM2;
        public String FM2Sum;

        public String FVolSum;
        public String FTip;
        public String FWide;
        public String FMaker;

        public String FFBaoNum;
        public String FLev;
        public String FCarNo;
        public String FFBaoNo;
        public String FType;
        public String FHuozhuNumber;//货主编码
        public String FStorageNumber;//货主编码
        public String FOrgNumber;//库存组织编码


    }


    public class RemarkData {
        public String FNUMBER;
        public String FUSEORGID;
        public String FNAME;
        public String FSHORTNAME;
    }

    public class PrintDataBean {
        public String FBarCode;
        public String FBatch;
        public String FAuxNum;
        public String FBaseNum;
        public String FStoreNum;

        public String FQtyAll;
        public String FVolAll;
        public String FBoxCode;
        public String FBoxDate;
        public String FMaker;
        public String FName;
        public String FHuozhuNote;
        public String FCarNo;
        public String FUnit;
        public String FModel;
        public String FQty;
        public String FVol;
        public String FLev;
        public String FEntryID;
        public String FQtySplit;
        public String FVolSplit;
        public String FWide;
        public String FStorage;
        public String FBaoNum;//包数
        public String FFBaoNum;//分包数
        public String FFBaoNo;//分包号
        public String FBoxType;

    }


    public class CodeCheckBackDataBean {
        public String FTip;
        public String FBillNo;
        public String FItemID;
        public String FUnitID;
        public String FBaseUnitName;
        public String FStoreUnitName;
        public String FQty;
        public String FBaseQty;
        public String FStoreQty;
        public String FStockID;
        public String FStockPlaceID;
        public String FBatchNo;
        public String FKFPeriod;
        public String FKFDate;
        public String FActualmodel;
        public String FAuxsign;
        public String FPurchaseNo;

        public String FBarCode;
        public String FHuoZhuNumber;

        public String FLevel;//等级
        public String FYmLenght;//原木长度
        public String FYmDiameter;//原木直径
        public String FBLenght;//板长
        public String FBWide;//板宽
        public String FBThick;//板厚
        public String FVolume;//体积
        public String FCeng;//体积
        public String FCodeType;//0水板，其他原木

        public String FName;
        public String FStorage;
        public String FModel;
        public String FNumber;
        public String FUnitNumber;
        public String FStorageNumber;
        public String FStoreOrgNumber;



    }

    public class InStorageType {
        public String FID;
        public String FName;
    }
    public class Org{
        public String FOrgID;
        public String FNumber;
        public String FName;
        public String FNote;
    }
    public class buyer{
        public String FID;
        public String FNumber;
        public String FName;
        public String FDeptID;
        public String FOrg;
        @Override
        public String toString() {
            return "buyer{" +
                    "FID='" + FID + '\'' +
                    ", FNumber='" + FNumber + '\'' +
                    ", FName='" + FName + '\'' +
                    ", FDeptID='" + FDeptID + '\'' +
                    ", FOrg='" + FOrg + '\'' +
                    '}';
        }
    }
    public class StoreMan{
        public String FID;
        public String FNumber;
        public String FName;
        public String FDeptID;
        public String FOrg;

        @Override
        public String toString() {
            return "StoreMan{" +
                    "FID='" + FID + '\'' +
                    ", FNumber='" + FNumber + '\'' +
                    ", FName='" + FName + '\'' +
                    ", FDeptID='" + FDeptID + '\'' +
                    ", FOrg='" + FOrg + '\'' +
                    '}';
        }
    }
    public class SaleMan{
        public String FID;
        public String FNumber;
        public String FName;
        public String FDeptID;
        public String FOrg;
        @Override
        public String toString() {
            return "SaleMan{" +
                    "FID='" + FID + '\'' +
                    ", FNumber='" + FNumber + '\'' +
                    ", FName='" + FName + '\'' +
                    ", FDeptID='" + FDeptID + '\'' +
                    ", FOrg='" + FOrg + '\'' +
                    '}';
        }
    }

    public class bibiezhong {
        public String FCurrencyID;
        public String FNumber;
        public String FName;
        public String FExChangeRate;
        public String fClassTypeId;
    }

    public class department {
        public String FItemID;
        public String FISSTOCK;
        public String FNumber;
        public String FName;
        public String FOrg;

        @Override
        public String toString() {
            return "department{" +
                    "FItemID='" + FItemID + '\'' +
                    "FISSTOCK='" + FISSTOCK + '\'' +
                    ", FNumber='" + FNumber + '\'' +
                    ", FName='" + FName + '\'' +
                    ", FOrg='" + FOrg + '\'' +
                    '}';
        }
        //        public String FparentID;
    }

    public class employee {
        public String FItemID;
        public String FNumber;
        public String FName;
        public String FDpartmentID;
        public String FEmpGroup;
        public String FEmpGroupID;
        public String FOrg;
    }

    public class wavehouse {
        public String FSPID;
        public String FNumber;
        public String FName;
        public String FOrg;
//        public String FSPGroupID;
//        public String FFullName;
//        public String FLevel;
//        public String FDetail;
//        public String FParentID;
//        public String FClassTypeID;
//        public String FDefaultSPID;

    }

    public class InstorageNum {
        public String FItemID;
        public String FStockID;
        public String FQty;
        public String FStoreState;
        public String FStoreOrgID;
        public String FBatchNo;
        public String FUnitID;

    }

    public class storage {
        public String FItemID;
        public String FName;
        public String FNumber;
        public String FIsOpenWaveHouse;
        public String FallowFStore;//允许负库存
        public String FOrg;//
//        public String FEmpID;
//        public String FTypeID;
//        public String FSPGroupID;
//        public String FGroupID;
//        public String FStockGroupID;
//        public String FIsStockMgr;
//        public String FUnderStock;
    }


    public class Unit {
        public String FMeasureUnitID;
        public String FNumber;
        public String FName;
        public String FOrg;
//        public String FUnitGroupID;
//        public String FCoefficient;
    }

    public class BarCode {
        public String FName;
        public String FTypeID;
        public String FItemID;
        public String FBarCode;
        public String FNumber;
        public String FUnitID;

        @Override
        public String toString() {
            return "BarCode{" +
                    "FName='" + FName + '\'' +
                    ", FTypeID='" + FTypeID + '\'' +
                    ", FItemID='" + FItemID + '\'' +
                    ", FBarCode='" + FBarCode + '\'' +
                    ", FNumber='" + FNumber + '\'' +
                    ", FUnitID='" + FUnitID + '\'' +
                    '}';
        }
    }

    public class suppliers {
        public String FItemID;
        public String FNumber;
        public String FName;
        public String FOrg;
        public String FNote;
        public String FMASTERID;
//        public String FItemClassID;
//        public String FParentID;
//        public String FLevel;
//        public String FDetail;
//        public String FAddress;
//        public String FPhone;
//        public String FEmail;

        @Override
        public String toString() {
            return "suppliers{" +
                    "FItemID='" + FItemID + '\'' +
//                    ", FItemClassID='" + FItemClassID + '\'' +
                    ", FNumber='" + FNumber + '\'' +
//                    ", FParentID='" + FParentID + '\'' +
//                    ", FLevel='" + FLevel + '\'' +
//                    ", FDetail='" + FDetail + '\'' +
                    ", FName='" + FName + '\'' +
                    ", FOrg='" + FOrg + '\'' +
//                    ", FAddress='" + FAddress + '\'' +
//                    ", FPhone='" + FPhone + '\'' +
//                    ", FEmail='" + FEmail + '\'' +
                    '}';
        }
    }

    public class payType {
        public String FItemID;
        public String FName;
        public String FNumber;
    }


    public class product {
        public String FProduceUnitID;
        public String FPurchaseUnitID;
        public String FPurchasePriceUnitID;
        public String FSaleUnitID;
        public String FSalePriceUnitID;
        public String FStoreUnitID;
        public String FAuxUnitID;
        public String FStockID;
        public String FStockPlaceID;
        public String FIsBatchManage;
        public String FIsKFperiod;
        public String FExpperiod;
        public String FExpUnit;
        public String FIsPurchase;
        public String FIsSale;
        public String FIsInventory;
        public String FIsProduce;
        public String FIsSubContract;
        public String FIsAsset;
        public String FBaseUnitID;
        public String FFWeightUnitID;
        public String FVolumeUnitID;
        public String FBarcode;
        public String FGrossWeight;
        public String FNetWeight;
        public String FLenght;
        public String FWidth;
        public String FHeight;
        public String FVolume;
        public String FMaterialid;
        public String FNumber;
        public String FoldNumber;
        public String FName;
        public String FModel;
        public String FMnemoniccode;
        public String FOrg;
        public String FMASTERID;//库存ID,用于查库存
    }

    public class User {
        public String FUserID;
        public String FName;
        public String FPassWord;
        public String FEmpID;
        public String FPermit;
        public String FNameERP;
        public String FPassWordERP;
    }

    public class Client {
        public String FItemID;
        public String FNumber;
        public String FName;
        public String FOrg;
        public String FMASTERID;
//        public String FItemClassID;
//        public String FParentID;
//        public String FLevel;
//        public String FDetail;
//        public String FAddress;
//        public String FPhone;
//        public String FEmail;
//        public String FTypeID;
    }

    public class GetGoodsDepartment {
        public String FItemID;
        public String FDeleted;
        public String FNumber;
        public String FName;
        public String FDetail;
    }

    public class purchaseMethod {
        public String FTypeID;
        public String FItemID;
        public String FNumber;
        public String FName;
    }

    public class yuandanType {
        public String FID;
        public String FName_CHS;
    }


    public class wanglaikemu {
        public String FAccountID;
        public String FNumber;
        public String FFullName;
    }

    public class PriceMethod {
        public String FInterID;
        public String FEntryID;
        public String FPri;
        public String FPrice;
        public String FName;
        public String FItemID;
        public String FUnitID;
        public String FRelatedID;
        public String FBegQty;
        public String FEndQty;
        public String FBegDate;
        public String FEndDate;
    }


}
