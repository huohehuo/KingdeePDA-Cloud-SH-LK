package com.fangzuo.assist.cloud.Beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class WortPrintData {
	@Id(autoincrement = true)
	public Long FIndex;
	
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
	public String FWide;
	public String FMaker;
	public String FPrintNum;

	public String FFBaoNum;
	public String FLev;
	public String FCarNo;
	public String FFBaoNo;
	public String FType;
	public String FHuozhuNumber;//货主编码
	public String FStorageNumber;//货主编码
	public String FOrgNumber;//库存组织编码

	@Generated(hash = 1754679523)
	public WortPrintData(Long FIndex, String FID, String FEntryID, String FItemID,
			String FUnitID, String FDCStockID, String FDCSPID, String FSTOCKORGID,
			String FOWNERID, String FBoxCode, String FSplitBoxCode, String FDate,
			String FUser, String FName, String FUnit, String FModel, String FBatch,
			String FLenght, String FChang, String FKuan, String FHou, String FQty,
			String FQtySplit, String FM2Split, String FQtySum, String FM2, String FM2Sum,
			String FVolSum, String FTip, String FWide, String FMaker, String FPrintNum,
			String FFBaoNum, String FLev, String FCarNo, String FFBaoNo, String FType,
			String FHuozhuNumber, String FStorageNumber, String FOrgNumber) {
		this.FIndex = FIndex;
		this.FID = FID;
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
		this.FWide = FWide;
		this.FMaker = FMaker;
		this.FPrintNum = FPrintNum;
		this.FFBaoNum = FFBaoNum;
		this.FLev = FLev;
		this.FCarNo = FCarNo;
		this.FFBaoNo = FFBaoNo;
		this.FType = FType;
		this.FHuozhuNumber = FHuozhuNumber;
		this.FStorageNumber = FStorageNumber;
		this.FOrgNumber = FOrgNumber;
	}
	@Generated(hash = 2003884253)
	public WortPrintData() {
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
	public String getFBoxCode() {
		return this.FBoxCode;
	}
	public void setFBoxCode(String FBoxCode) {
		this.FBoxCode = FBoxCode;
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
	public String getFQty() {
		return this.FQty;
	}
	public void setFQty(String FQty) {
		this.FQty = FQty;
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
	public String getFQtySplit() {
		return this.FQtySplit;
	}
	public void setFQtySplit(String FQtySplit) {
		this.FQtySplit = FQtySplit;
	}
	public String getFSplitBoxCode() {
		return this.FSplitBoxCode;
	}
	public void setFSplitBoxCode(String FSplitBoxCode) {
		this.FSplitBoxCode = FSplitBoxCode;
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
	public String getFM2Split() {
		return this.FM2Split;
	}
	public void setFM2Split(String FM2Split) {
		this.FM2Split = FM2Split;
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
	public Long getFIndex() {
		return this.FIndex;
	}
	public void setFIndex(Long FIndex) {
		this.FIndex = FIndex;
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
	public String getFWide() {
		return this.FWide;
	}
	public void setFWide(String FWide) {
		this.FWide = FWide;
	}
	public String getFMaker() {
		return this.FMaker;
	}
	public void setFMaker(String FMaker) {
		this.FMaker = FMaker;
	}
	public String getFPrintNum() {
		return this.FPrintNum;
	}
	public void setFPrintNum(String FPrintNum) {
		this.FPrintNum = FPrintNum;
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
	public String getFFBaoNo() {
		return this.FFBaoNo;
	}
	public void setFFBaoNo(String FFBaoNo) {
		this.FFBaoNo = FFBaoNo;
	}
	public String getFType() {
		return this.FType;
	}
	public void setFType(String FType) {
		this.FType = FType;
	}
	public String getFHuozhuNumber() {
		return this.FHuozhuNumber;
	}
	public void setFHuozhuNumber(String FHuozhuNumber) {
		this.FHuozhuNumber = FHuozhuNumber;
	}
	public String getFStorageNumber() {
		return this.FStorageNumber;
	}
	public void setFStorageNumber(String FStorageNumber) {
		this.FStorageNumber = FStorageNumber;
	}
	public String getFOrgNumber() {
		return this.FOrgNumber;
	}
	public void setFOrgNumber(String FOrgNumber) {
		this.FOrgNumber = FOrgNumber;
	}

	
}
