package com.fangzuo.assist.cloud.Dao;

import com.fangzuo.assist.cloud.Beans.WortPrintData;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by NB on 2017/7/26.
 */
@Entity
public class DryingGetData {
    @Id(autoincrement = true)
    public Long FIndex;

    public String FID;
    public String FAccountID;
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

    public String FFBaoNum;
    public String FLev;
    public String FCarNo;

    public String FStr1;
    public String FStr2;
    public String FStr3;
    public String FStr4;
    public String FStr5;




    public void setData(WortPrintData wortPrintData){

        this.FItemID = wortPrintData.FItemID;
        this.FUnitID = wortPrintData.FUnitID;
        this.FDCStockID = wortPrintData.FDCStockID;
        this.FDCSPID = wortPrintData.FDCSPID;
        this.FSTOCKORGID = wortPrintData.FSTOCKORGID;
        this.FOWNERID = wortPrintData.FOWNERID;
        this.FBoxCode = wortPrintData.FBoxCode;
        this.FSplitBoxCode = wortPrintData.FSplitBoxCode;
        this.FDate = wortPrintData.FDate;
        this.FName = wortPrintData.FName;
        this.FUnit = wortPrintData.FUnit;
        this.FModel = wortPrintData.FModel;
        this.FBatch = wortPrintData.FBatch;
        this.FLenght = wortPrintData.FLenght;
        this.FChang = wortPrintData.FChang;
        this.FKuan = wortPrintData.FKuan;
        this.FHou = wortPrintData.FHou;
        this.FQty = wortPrintData.FQty;
        this.FQtySplit = wortPrintData.FQtySplit;
        this.FM2Split = wortPrintData.FM2Split;
        this.FQtySum = wortPrintData.FQtySum;
        this.FM2 = wortPrintData.FM2;
        this.FM2Sum = wortPrintData.FM2Sum;
        this.FVolSum = wortPrintData.FVolSum;
        this.FTip = wortPrintData.FTip;
        this.FFBaoNum = wortPrintData.FFBaoNum;
        this.FLev = wortPrintData.FLev;
        this.FCarNo = wortPrintData.FCarNo;
    }







    @Generated(hash = 2038671664)
    public DryingGetData(Long FIndex, String FID, String FAccountID,
            String FEntryID, String FItemID, String FUnitID, String FDCStockID,
            String FDCSPID, String FSTOCKORGID, String FOWNERID, String FBoxCode,
            String FSplitBoxCode, String FDate, String FUser, String FName,
            String FUnit, String FModel, String FBatch, String FLenght,
            String FChang, String FKuan, String FHou, String FQty, String FQtySplit,
            String FM2Split, String FQtySum, String FM2, String FM2Sum,
            String FVolSum, String FTip, String FBarcode, String IMIE,
            String FBillerID, long FOrderId, int activity, String FNumber,
            String FUnitNumber, String FStorageNumber, String FStoreOrgNumber,
            String FHuozhuNumber, String FFBaoNum, String FLev, String FCarNo,
            String FStr1, String FStr2, String FStr3, String FStr4, String FStr5) {
        this.FIndex = FIndex;
        this.FID = FID;
        this.FAccountID = FAccountID;
        this.FEntryID = FEntryID;
        this.FItemID = FItemID;
        this.FUnitID = FUnitID;
        this.FDCStockID = FDCStockID;
        this.FDCSPID = FDCSPID;
        this.FSTOCKORGID = FSTOCKORGID;
        this.FOWNERID = FOWNERID;
        this.FBoxCode = FBoxCode;
        this.FSplitBoxCode = FSplitBoxCode;
        this.FDate = FDate;
        this.FUser = FUser;
        this.FName = FName;
        this.FUnit = FUnit;
        this.FModel = FModel;
        this.FBatch = FBatch;
        this.FLenght = FLenght;
        this.FChang = FChang;
        this.FKuan = FKuan;
        this.FHou = FHou;
        this.FQty = FQty;
        this.FQtySplit = FQtySplit;
        this.FM2Split = FM2Split;
        this.FQtySum = FQtySum;
        this.FM2 = FM2;
        this.FM2Sum = FM2Sum;
        this.FVolSum = FVolSum;
        this.FTip = FTip;
        this.FBarcode = FBarcode;
        this.IMIE = IMIE;
        this.FBillerID = FBillerID;
        this.FOrderId = FOrderId;
        this.activity = activity;
        this.FNumber = FNumber;
        this.FUnitNumber = FUnitNumber;
        this.FStorageNumber = FStorageNumber;
        this.FStoreOrgNumber = FStoreOrgNumber;
        this.FHuozhuNumber = FHuozhuNumber;
        this.FFBaoNum = FFBaoNum;
        this.FLev = FLev;
        this.FCarNo = FCarNo;
        this.FStr1 = FStr1;
        this.FStr2 = FStr2;
        this.FStr3 = FStr3;
        this.FStr4 = FStr4;
        this.FStr5 = FStr5;
    }







    @Generated(hash = 480121418)
    public DryingGetData() {
    }
    public Long getFIndex() {
        return this.FIndex;
    }
    public void setFIndex(Long FIndex) {
        this.FIndex = FIndex;
    }
    public String getFID() {
        return this.FID;
    }
    public void setFID(String FID) {
        this.FID = FID;
    }
    public String getFEntryID() {
        return this.FEntryID;
    }
    public void setFEntryID(String FEntryID) {
        this.FEntryID = FEntryID;
    }
    public String getFItemID() {
        return this.FItemID;
    }
    public void setFItemID(String FItemID) {
        this.FItemID = FItemID;
    }
    public String getFUnitID() {
        return this.FUnitID;
    }
    public void setFUnitID(String FUnitID) {
        this.FUnitID = FUnitID;
    }
    public String getFDCStockID() {
        return this.FDCStockID;
    }
    public void setFDCStockID(String FDCStockID) {
        this.FDCStockID = FDCStockID;
    }
    public String getFDCSPID() {
        return this.FDCSPID;
    }
    public void setFDCSPID(String FDCSPID) {
        this.FDCSPID = FDCSPID;
    }
    public String getFSTOCKORGID() {
        return this.FSTOCKORGID;
    }
    public void setFSTOCKORGID(String FSTOCKORGID) {
        this.FSTOCKORGID = FSTOCKORGID;
    }
    public String getFOWNERID() {
        return this.FOWNERID;
    }
    public void setFOWNERID(String FOWNERID) {
        this.FOWNERID = FOWNERID;
    }
    public String getFBoxCode() {
        return this.FBoxCode;
    }
    public void setFBoxCode(String FBoxCode) {
        this.FBoxCode = FBoxCode;
    }
    public String getFSplitBoxCode() {
        return this.FSplitBoxCode;
    }
    public void setFSplitBoxCode(String FSplitBoxCode) {
        this.FSplitBoxCode = FSplitBoxCode;
    }
    public String getFDate() {
        return this.FDate;
    }
    public void setFDate(String FDate) {
        this.FDate = FDate;
    }
    public String getFUser() {
        return this.FUser;
    }
    public void setFUser(String FUser) {
        this.FUser = FUser;
    }
    public String getFName() {
        return this.FName;
    }
    public void setFName(String FName) {
        this.FName = FName;
    }
    public String getFUnit() {
        return this.FUnit;
    }
    public void setFUnit(String FUnit) {
        this.FUnit = FUnit;
    }
    public String getFModel() {
        return this.FModel;
    }
    public void setFModel(String FModel) {
        this.FModel = FModel;
    }
    public String getFBatch() {
        return this.FBatch;
    }
    public void setFBatch(String FBatch) {
        this.FBatch = FBatch;
    }
    public String getFLenght() {
        return this.FLenght;
    }
    public void setFLenght(String FLenght) {
        this.FLenght = FLenght;
    }
    public String getFChang() {
        return this.FChang;
    }
    public void setFChang(String FChang) {
        this.FChang = FChang;
    }
    public String getFKuan() {
        return this.FKuan;
    }
    public void setFKuan(String FKuan) {
        this.FKuan = FKuan;
    }
    public String getFHou() {
        return this.FHou;
    }
    public void setFHou(String FHou) {
        this.FHou = FHou;
    }
    public String getFQty() {
        return this.FQty;
    }
    public void setFQty(String FQty) {
        this.FQty = FQty;
    }
    public String getFQtySplit() {
        return this.FQtySplit;
    }
    public void setFQtySplit(String FQtySplit) {
        this.FQtySplit = FQtySplit;
    }
    public String getFM2Split() {
        return this.FM2Split;
    }
    public void setFM2Split(String FM2Split) {
        this.FM2Split = FM2Split;
    }
    public String getFQtySum() {
        return this.FQtySum;
    }
    public void setFQtySum(String FQtySum) {
        this.FQtySum = FQtySum;
    }
    public String getFM2() {
        return this.FM2;
    }
    public void setFM2(String FM2) {
        this.FM2 = FM2;
    }
    public String getFM2Sum() {
        return this.FM2Sum;
    }
    public void setFM2Sum(String FM2Sum) {
        this.FM2Sum = FM2Sum;
    }
    public String getFVolSum() {
        return this.FVolSum;
    }
    public void setFVolSum(String FVolSum) {
        this.FVolSum = FVolSum;
    }
    public String getFTip() {
        return this.FTip;
    }
    public void setFTip(String FTip) {
        this.FTip = FTip;
    }







    public String getFBarcode() {
        return this.FBarcode;
    }







    public void setFBarcode(String FBarcode) {
        this.FBarcode = FBarcode;
    }







    public String getIMIE() {
        return this.IMIE;
    }







    public void setIMIE(String IMIE) {
        this.IMIE = IMIE;
    }







    public String getFBillerID() {
        return this.FBillerID;
    }







    public void setFBillerID(String FBillerID) {
        this.FBillerID = FBillerID;
    }







    public long getFOrderId() {
        return this.FOrderId;
    }







    public void setFOrderId(long FOrderId) {
        this.FOrderId = FOrderId;
    }







    public int getActivity() {
        return this.activity;
    }







    public void setActivity(int activity) {
        this.activity = activity;
    }







    public String getFNumber() {
        return this.FNumber;
    }







    public void setFNumber(String FNumber) {
        this.FNumber = FNumber;
    }







    public String getFUnitNumber() {
        return this.FUnitNumber;
    }







    public void setFUnitNumber(String FUnitNumber) {
        this.FUnitNumber = FUnitNumber;
    }







    public String getFStorageNumber() {
        return this.FStorageNumber;
    }







    public void setFStorageNumber(String FStorageNumber) {
        this.FStorageNumber = FStorageNumber;
    }







    public String getFStoreOrgNumber() {
        return this.FStoreOrgNumber;
    }







    public void setFStoreOrgNumber(String FStoreOrgNumber) {
        this.FStoreOrgNumber = FStoreOrgNumber;
    }







    public String getFHuozhuNumber() {
        return this.FHuozhuNumber;
    }







    public void setFHuozhuNumber(String FHuozhuNumber) {
        this.FHuozhuNumber = FHuozhuNumber;
    }







    public String getFAccountID() {
        return this.FAccountID;
    }







    public void setFAccountID(String FAccountID) {
        this.FAccountID = FAccountID;
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







    public String getFFBaoNum() {
        return this.FFBaoNum;
    }







    public void setFFBaoNum(String FFBaoNum) {
        this.FFBaoNum = FFBaoNum;
    }







    public String getFLev() {
        return this.FLev;
    }







    public void setFLev(String FLev) {
        this.FLev = FLev;
    }







    public String getFCarNo() {
        return this.FCarNo;
    }







    public void setFCarNo(String FCarNo) {
        this.FCarNo = FCarNo;
    }
}
