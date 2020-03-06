package Bean;

import java.util.ArrayList;

/**
 * Created by NB on 2017/8/24.
 *
 *              下载bean  销售出库单验货
 *
 */
public class PushDownDLBean {
    public ArrayList<DLbean> list;
    public class DLbean{
        public String FAccountID;
        public String FSEQ;
        public String FID;
        public String FMaterialID;
        public String FEntryID;
        public String FUnitID;
        public String FNumber;
        public String FName;
        public String FModel;
        public String FBillNo;
        public String FQty;
        public String FAuxQty;
        public String FAuxQtying;
        public String FCreateDate;
        public String FQtying;
        public String FTaxPrice;
        public String FStockID;
        public String FBatchNo;
        public String FHuoZhuNumber;
        public String FBaseCanreturnQty;
        public String AuxSign;//辅助标识
        public String ActualModel;//实际规格
        public String FPriceUnitID;//计价单位编码
        public String FTaxRate;//税率
        public String FIsGift;//1为赠品 0不是赠品


        public String FStorageOutID;//
        public String FStorageInID;//
        public String FOrgOutID;//
        public String FOrgInID;//
        public String FHuozhuOutID;//
        public String FHuozhuInID;//
        public String FNeedOrgID;//

        public String FLevel;//等级
        public String FYmLenght;//原木长度
        public String FYmDiameter;//原木直径
        public String FBLenght;//板长
        public String FBWide;//板宽
        public String FBThick;//板厚

        public String FWide;//宽度
        public String FM3;//宽度
        public String FPCS;//宽度



        public String FStr1;
        public String FStr2;
        public String FStr3;
        public String FStr4;
        public String FStr5;
//
//        public String FItemID;
//        public String FAuxQty;
//        public String FSCStockID;
//        public String FDCSPID;
//        public String FDCStockName;
//        public String FDCSPName;
//        public String FKFPeriod;
//        public String FAuxPrice;
//        public String FKFDate;


    }
}
