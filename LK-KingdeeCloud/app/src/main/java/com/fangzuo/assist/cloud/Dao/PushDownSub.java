package com.fangzuo.assist.cloud.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by NB on 2017/8/24.
 *      订单详情
 */
@Entity
public class PushDownSub {
    @Id(autoincrement = true)
    public Long id;
    public String FSEQ;
    public String FID;
    public String FMaterialID;
    public String FAccountID;
    public String FEntryID;
    public String FUnitID;
    public String FNumber;
    public String FName;
    public String FModel;
    public String FBillNo;
    public String FQty;
    public String FQtying;
    public String FTaxPrice;
    public String FStockID;
    public String FBatchNo;
    public String FBaseCanreturnQty;
    public String FHuoZhuNumber;
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

    /*------------------------二期---------------------*/
    public String FLevel;//等级
    public String FYmLenght;//原木长度
    public String FYmDiameter;//原木直径
    public String FBLenght;//板长
    public String FBWide;//板宽
    public String FBThick;//板厚

    public String FIsPrint;//是否已打印


    public String FWide;//宽度
    public String FM3;//宽度
    public String FPCS;//宽度

    public String FStr1;
    public String FStr2;
    public String FStr3;
    public String FStr4;
    public String FStr5;

    @Generated(hash = 259545152)
    public PushDownSub(Long id, String FSEQ, String FID, String FMaterialID,
            String FAccountID, String FEntryID, String FUnitID, String FNumber,
            String FName, String FModel, String FBillNo, String FQty,
            String FQtying, String FTaxPrice, String FStockID, String FBatchNo,
            String FBaseCanreturnQty, String FHuoZhuNumber, String AuxSign,
            String ActualModel, String FPriceUnitID, String FTaxRate,
            String FIsGift, String FStorageOutID, String FStorageInID,
            String FOrgOutID, String FOrgInID, String FHuozhuOutID,
            String FHuozhuInID, String FNeedOrgID, String FLevel, String FYmLenght,
            String FYmDiameter, String FBLenght, String FBWide, String FBThick,
            String FIsPrint, String FWide, String FM3, String FPCS, String FStr1,
            String FStr2, String FStr3, String FStr4, String FStr5) {
        this.id = id;
        this.FSEQ = FSEQ;
        this.FID = FID;
        this.FMaterialID = FMaterialID;
        this.FAccountID = FAccountID;
        this.FEntryID = FEntryID;
        this.FUnitID = FUnitID;
        this.FNumber = FNumber;
        this.FName = FName;
        this.FModel = FModel;
        this.FBillNo = FBillNo;
        this.FQty = FQty;
        this.FQtying = FQtying;
        this.FTaxPrice = FTaxPrice;
        this.FStockID = FStockID;
        this.FBatchNo = FBatchNo;
        this.FBaseCanreturnQty = FBaseCanreturnQty;
        this.FHuoZhuNumber = FHuoZhuNumber;
        this.AuxSign = AuxSign;
        this.ActualModel = ActualModel;
        this.FPriceUnitID = FPriceUnitID;
        this.FTaxRate = FTaxRate;
        this.FIsGift = FIsGift;
        this.FStorageOutID = FStorageOutID;
        this.FStorageInID = FStorageInID;
        this.FOrgOutID = FOrgOutID;
        this.FOrgInID = FOrgInID;
        this.FHuozhuOutID = FHuozhuOutID;
        this.FHuozhuInID = FHuozhuInID;
        this.FNeedOrgID = FNeedOrgID;
        this.FLevel = FLevel;
        this.FYmLenght = FYmLenght;
        this.FYmDiameter = FYmDiameter;
        this.FBLenght = FBLenght;
        this.FBWide = FBWide;
        this.FBThick = FBThick;
        this.FIsPrint = FIsPrint;
        this.FWide = FWide;
        this.FM3 = FM3;
        this.FPCS = FPCS;
        this.FStr1 = FStr1;
        this.FStr2 = FStr2;
        this.FStr3 = FStr3;
        this.FStr4 = FStr4;
        this.FStr5 = FStr5;
    }
    @Generated(hash = 2008125598)
    public PushDownSub() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFSEQ() {
        return this.FSEQ;
    }
    public void setFSEQ(String FSEQ) {
        this.FSEQ = FSEQ;
    }
    public String getFID() {
        return this.FID;
    }
    public void setFID(String FID) {
        this.FID = FID;
    }
    public String getFMaterialID() {
        return this.FMaterialID;
    }
    public void setFMaterialID(String FMaterialID) {
        this.FMaterialID = FMaterialID;
    }
    public String getFEntryID() {
        return this.FEntryID;
    }
    public void setFEntryID(String FEntryID) {
        this.FEntryID = FEntryID;
    }
    public String getFUnitID() {
        return this.FUnitID;
    }
    public void setFUnitID(String FUnitID) {
        this.FUnitID = FUnitID;
    }
    public String getFNumber() {
        return this.FNumber;
    }
    public void setFNumber(String FNumber) {
        this.FNumber = FNumber;
    }
    public String getFName() {
        return this.FName;
    }
    public void setFName(String FName) {
        this.FName = FName;
    }
    public String getFModel() {
        return this.FModel;
    }
    public void setFModel(String FModel) {
        this.FModel = FModel;
    }
    public String getFBillNo() {
        return this.FBillNo;
    }
    public void setFBillNo(String FBillNo) {
        this.FBillNo = FBillNo;
    }
    public String getFQty() {
        return this.FQty;
    }
    public void setFQty(String FQty) {
        this.FQty = FQty;
    }
    public String getFQtying() {
        return this.FQtying;
    }
    public void setFQtying(String FQtying) {
        this.FQtying = FQtying;
    }
    public String getFTaxPrice() {
        return this.FTaxPrice;
    }
    public void setFTaxPrice(String FTaxPrice) {
        this.FTaxPrice = FTaxPrice;
    }
    public String getFStockID() {
        return this.FStockID;
    }
    public void setFStockID(String FStockID) {
        this.FStockID = FStockID;
    }
    public String getFBatchNo() {
        return this.FBatchNo;
    }
    public void setFBatchNo(String FBatchNo) {
        this.FBatchNo = FBatchNo;
    }
    public String getFBaseCanreturnQty() {
        return this.FBaseCanreturnQty;
    }
    public void setFBaseCanreturnQty(String FBaseCanreturnQty) {
        this.FBaseCanreturnQty = FBaseCanreturnQty;
    }
    public String getFHuoZhuNumber() {
        return this.FHuoZhuNumber;
    }
    public void setFHuoZhuNumber(String FHuoZhuNumber) {
        this.FHuoZhuNumber = FHuoZhuNumber;
    }
    public String getAuxSign() {
        return this.AuxSign;
    }
    public void setAuxSign(String AuxSign) {
        this.AuxSign = AuxSign;
    }
    public String getActualModel() {
        return this.ActualModel;
    }
    public void setActualModel(String ActualModel) {
        this.ActualModel = ActualModel;
    }
    public String getFStorageOutID() {
        return this.FStorageOutID;
    }
    public void setFStorageOutID(String FStorageOutID) {
        this.FStorageOutID = FStorageOutID;
    }
    public String getFStorageInID() {
        return this.FStorageInID;
    }
    public void setFStorageInID(String FStorageInID) {
        this.FStorageInID = FStorageInID;
    }
    public String getFOrgOutID() {
        return this.FOrgOutID;
    }
    public void setFOrgOutID(String FOrgOutID) {
        this.FOrgOutID = FOrgOutID;
    }
    public String getFOrgInID() {
        return this.FOrgInID;
    }
    public void setFOrgInID(String FOrgInID) {
        this.FOrgInID = FOrgInID;
    }
    public String getFHuozhuOutID() {
        return this.FHuozhuOutID;
    }
    public void setFHuozhuOutID(String FHuozhuOutID) {
        this.FHuozhuOutID = FHuozhuOutID;
    }
    public String getFHuozhuInID() {
        return this.FHuozhuInID;
    }
    public void setFHuozhuInID(String FHuozhuInID) {
        this.FHuozhuInID = FHuozhuInID;
    }
    public String getFLevel() {
        return this.FLevel;
    }
    public void setFLevel(String FLevel) {
        this.FLevel = FLevel;
    }
    public String getFYmLenght() {
        return this.FYmLenght;
    }
    public void setFYmLenght(String FYmLenght) {
        this.FYmLenght = FYmLenght;
    }
    public String getFYmDiameter() {
        return this.FYmDiameter;
    }
    public void setFYmDiameter(String FYmDiameter) {
        this.FYmDiameter = FYmDiameter;
    }
    public String getFBLenght() {
        return this.FBLenght;
    }
    public void setFBLenght(String FBLenght) {
        this.FBLenght = FBLenght;
    }
    public String getFBWide() {
        return this.FBWide;
    }
    public void setFBWide(String FBWide) {
        this.FBWide = FBWide;
    }
    public String getFBThick() {
        return this.FBThick;
    }
    public void setFBThick(String FBThick) {
        this.FBThick = FBThick;
    }
    public String getFIsPrint() {
        return this.FIsPrint;
    }
    public void setFIsPrint(String FIsPrint) {
        this.FIsPrint = FIsPrint;
    }
    public String getFPriceUnitID() {
        return this.FPriceUnitID;
    }
    public void setFPriceUnitID(String FPriceUnitID) {
        this.FPriceUnitID = FPriceUnitID;
    }
    public String getFTaxRate() {
        return this.FTaxRate;
    }
    public void setFTaxRate(String FTaxRate) {
        this.FTaxRate = FTaxRate;
    }
    public String getFAccountID() {
        return this.FAccountID;
    }
    public void setFAccountID(String FAccountID) {
        this.FAccountID = FAccountID;
    }
    public String getFNeedOrgID() {
        return this.FNeedOrgID;
    }
    public void setFNeedOrgID(String FNeedOrgID) {
        this.FNeedOrgID = FNeedOrgID;
    }
    public String getFStr1() {
        return this.FStr1;
    }
    public void setFStr1(String FStr1) {
        this.FStr1 = FStr1;
    }
    public String getFStr2() {
        return this.FStr2;
    }
    public void setFStr2(String FStr2) {
        this.FStr2 = FStr2;
    }
    public String getFStr3() {
        return this.FStr3;
    }
    public void setFStr3(String FStr3) {
        this.FStr3 = FStr3;
    }
    public String getFStr4() {
        return this.FStr4;
    }
    public void setFStr4(String FStr4) {
        this.FStr4 = FStr4;
    }
    public String getFStr5() {
        return this.FStr5;
    }
    public void setFStr5(String FStr5) {
        this.FStr5 = FStr5;
    }
    public String getFIsGift() {
        return this.FIsGift;
    }
    public void setFIsGift(String FIsGift) {
        this.FIsGift = FIsGift;
    }
    public String getFWide() {
        return this.FWide;
    }
    public void setFWide(String FWide) {
        this.FWide = FWide;
    }
    public String getFM3() {
        return this.FM3;
    }
    public void setFM3(String FM3) {
        this.FM3 = FM3;
    }
    public String getFPCS() {
        return this.FPCS;
    }
    public void setFPCS(String FPCS) {
        this.FPCS = FPCS;
    }

}
