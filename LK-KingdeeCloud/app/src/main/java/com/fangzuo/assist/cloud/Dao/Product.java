package com.fangzuo.assist.cloud.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by NB on 2017/7/26.
 *
 * Error:Execution failed for task ':app:greendao'.
 > Found 1 problem(s) parsing "G:\project\fz\android\通用版androidPDA\app\src\main\java\com\fangzuo\assist\Dao\Product.java". First problem:
 Pb(96) The serializable class Product does not declare a static final serialVersionUID field of type long (536871008 at line 12).
 Run gradle with --info for more details.
 */
@Entity
public class Product{
    public String FProduceUnitID;
    public String FProduceUnitNumber;
    public String FPurchaseUnitID;
    public String FPurchaseUnitNumber;
    public String FPurchasePriceUnitID;
    public String FPurchasePriceUnitNumber;
    public String FSaleUnitID;
    public String FSaleUnitNumber;
    public String FSalePriceUnitID;
    public String FSalePriceUnitNumber;
    public String FStoreUnitID;
    public String FStoreUnitNumber;
    public String FAuxUnitID;
    public String FAuxUnitNumber;
    public String FBaseUnitID;
    public String FBaseUnitNumber;
    public String FUnitGroupID;


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

    public String FStr1;
    public String FStr2;
    public String FStr3;
    public String FStr4;
    public String FStr5;

    @Generated(hash = 1469533076)
    public Product(String FProduceUnitID, String FProduceUnitNumber, String FPurchaseUnitID, String FPurchaseUnitNumber,
            String FPurchasePriceUnitID, String FPurchasePriceUnitNumber, String FSaleUnitID, String FSaleUnitNumber, String FSalePriceUnitID,
            String FSalePriceUnitNumber, String FStoreUnitID, String FStoreUnitNumber, String FAuxUnitID, String FAuxUnitNumber,
            String FBaseUnitID, String FBaseUnitNumber, String FUnitGroupID, String FStockID, String FStockPlaceID, String FIsBatchManage,
            String FIsKFperiod, String FExpperiod, String FExpUnit, String FIsPurchase, String FIsSale, String FIsInventory, String FIsProduce,
            String FIsSubContract, String FIsAsset, String FFWeightUnitID, String FVolumeUnitID, String FBarcode, String FGrossWeight,
            String FNetWeight, String FLenght, String FWidth, String FHeight, String FVolume, String FMaterialid, String FNumber,
            String FoldNumber, String FName, String FModel, String FMnemoniccode, String FOrg, String FMASTERID, String FStr1, String FStr2,
            String FStr3, String FStr4, String FStr5) {
        this.FProduceUnitID = FProduceUnitID;
        this.FProduceUnitNumber = FProduceUnitNumber;
        this.FPurchaseUnitID = FPurchaseUnitID;
        this.FPurchaseUnitNumber = FPurchaseUnitNumber;
        this.FPurchasePriceUnitID = FPurchasePriceUnitID;
        this.FPurchasePriceUnitNumber = FPurchasePriceUnitNumber;
        this.FSaleUnitID = FSaleUnitID;
        this.FSaleUnitNumber = FSaleUnitNumber;
        this.FSalePriceUnitID = FSalePriceUnitID;
        this.FSalePriceUnitNumber = FSalePriceUnitNumber;
        this.FStoreUnitID = FStoreUnitID;
        this.FStoreUnitNumber = FStoreUnitNumber;
        this.FAuxUnitID = FAuxUnitID;
        this.FAuxUnitNumber = FAuxUnitNumber;
        this.FBaseUnitID = FBaseUnitID;
        this.FBaseUnitNumber = FBaseUnitNumber;
        this.FUnitGroupID = FUnitGroupID;
        this.FStockID = FStockID;
        this.FStockPlaceID = FStockPlaceID;
        this.FIsBatchManage = FIsBatchManage;
        this.FIsKFperiod = FIsKFperiod;
        this.FExpperiod = FExpperiod;
        this.FExpUnit = FExpUnit;
        this.FIsPurchase = FIsPurchase;
        this.FIsSale = FIsSale;
        this.FIsInventory = FIsInventory;
        this.FIsProduce = FIsProduce;
        this.FIsSubContract = FIsSubContract;
        this.FIsAsset = FIsAsset;
        this.FFWeightUnitID = FFWeightUnitID;
        this.FVolumeUnitID = FVolumeUnitID;
        this.FBarcode = FBarcode;
        this.FGrossWeight = FGrossWeight;
        this.FNetWeight = FNetWeight;
        this.FLenght = FLenght;
        this.FWidth = FWidth;
        this.FHeight = FHeight;
        this.FVolume = FVolume;
        this.FMaterialid = FMaterialid;
        this.FNumber = FNumber;
        this.FoldNumber = FoldNumber;
        this.FName = FName;
        this.FModel = FModel;
        this.FMnemoniccode = FMnemoniccode;
        this.FOrg = FOrg;
        this.FMASTERID = FMASTERID;
        this.FStr1 = FStr1;
        this.FStr2 = FStr2;
        this.FStr3 = FStr3;
        this.FStr4 = FStr4;
        this.FStr5 = FStr5;
    }

    @Generated(hash = 1890278724)
    public Product() {
    }

    public String getFProduceUnitID() {
        return this.FProduceUnitID;
    }

    public void setFProduceUnitID(String FProduceUnitID) {
        this.FProduceUnitID = FProduceUnitID;
    }

    public String getFPurchaseUnitID() {
        return this.FPurchaseUnitID;
    }

    public void setFPurchaseUnitID(String FPurchaseUnitID) {
        this.FPurchaseUnitID = FPurchaseUnitID;
    }

    public String getFPurchasePriceUnitID() {
        return this.FPurchasePriceUnitID;
    }

    public void setFPurchasePriceUnitID(String FPurchasePriceUnitID) {
        this.FPurchasePriceUnitID = FPurchasePriceUnitID;
    }

    public String getFSaleUnitID() {
        return this.FSaleUnitID;
    }

    public void setFSaleUnitID(String FSaleUnitID) {
        this.FSaleUnitID = FSaleUnitID;
    }

    public String getFSalePriceUnitID() {
        return this.FSalePriceUnitID;
    }

    public void setFSalePriceUnitID(String FSalePriceUnitID) {
        this.FSalePriceUnitID = FSalePriceUnitID;
    }

    public String getFStoreUnitID() {
        return this.FStoreUnitID;
    }

    public void setFStoreUnitID(String FStoreUnitID) {
        this.FStoreUnitID = FStoreUnitID;
    }

    public String getFAuxUnitID() {
        return this.FAuxUnitID;
    }

    public void setFAuxUnitID(String FAuxUnitID) {
        this.FAuxUnitID = FAuxUnitID;
    }

    public String getFStockID() {
        return this.FStockID;
    }

    public void setFStockID(String FStockID) {
        this.FStockID = FStockID;
    }

    public String getFStockPlaceID() {
        return this.FStockPlaceID;
    }

    public void setFStockPlaceID(String FStockPlaceID) {
        this.FStockPlaceID = FStockPlaceID;
    }

    public String getFIsBatchManage() {
        return this.FIsBatchManage;
    }

    public void setFIsBatchManage(String FIsBatchManage) {
        this.FIsBatchManage = FIsBatchManage;
    }

    public String getFIsKFperiod() {
        return this.FIsKFperiod;
    }

    public void setFIsKFperiod(String FIsKFperiod) {
        this.FIsKFperiod = FIsKFperiod;
    }

    public String getFExpperiod() {
        return this.FExpperiod;
    }

    public void setFExpperiod(String FExpperiod) {
        this.FExpperiod = FExpperiod;
    }

    public String getFExpUnit() {
        return this.FExpUnit;
    }

    public void setFExpUnit(String FExpUnit) {
        this.FExpUnit = FExpUnit;
    }

    public String getFIsPurchase() {
        return this.FIsPurchase;
    }

    public void setFIsPurchase(String FIsPurchase) {
        this.FIsPurchase = FIsPurchase;
    }

    public String getFIsSale() {
        return this.FIsSale;
    }

    public void setFIsSale(String FIsSale) {
        this.FIsSale = FIsSale;
    }

    public String getFIsInventory() {
        return this.FIsInventory;
    }

    public void setFIsInventory(String FIsInventory) {
        this.FIsInventory = FIsInventory;
    }

    public String getFIsProduce() {
        return this.FIsProduce;
    }

    public void setFIsProduce(String FIsProduce) {
        this.FIsProduce = FIsProduce;
    }

    public String getFIsSubContract() {
        return this.FIsSubContract;
    }

    public void setFIsSubContract(String FIsSubContract) {
        this.FIsSubContract = FIsSubContract;
    }

    public String getFIsAsset() {
        return this.FIsAsset;
    }

    public void setFIsAsset(String FIsAsset) {
        this.FIsAsset = FIsAsset;
    }

    public String getFBaseUnitID() {
        return this.FBaseUnitID;
    }

    public void setFBaseUnitID(String FBaseUnitID) {
        this.FBaseUnitID = FBaseUnitID;
    }

    public String getFFWeightUnitID() {
        return this.FFWeightUnitID;
    }

    public void setFFWeightUnitID(String FFWeightUnitID) {
        this.FFWeightUnitID = FFWeightUnitID;
    }

    public String getFVolumeUnitID() {
        return this.FVolumeUnitID;
    }

    public void setFVolumeUnitID(String FVolumeUnitID) {
        this.FVolumeUnitID = FVolumeUnitID;
    }

    public String getFBarcode() {
        return this.FBarcode;
    }

    public void setFBarcode(String FBarcode) {
        this.FBarcode = FBarcode;
    }

    public String getFGrossWeight() {
        return this.FGrossWeight;
    }

    public void setFGrossWeight(String FGrossWeight) {
        this.FGrossWeight = FGrossWeight;
    }

    public String getFNetWeight() {
        return this.FNetWeight;
    }

    public void setFNetWeight(String FNetWeight) {
        this.FNetWeight = FNetWeight;
    }

    public String getFLenght() {
        return this.FLenght;
    }

    public void setFLenght(String FLenght) {
        this.FLenght = FLenght;
    }

    public String getFWidth() {
        return this.FWidth;
    }

    public void setFWidth(String FWidth) {
        this.FWidth = FWidth;
    }

    public String getFHeight() {
        return this.FHeight;
    }

    public void setFHeight(String FHeight) {
        this.FHeight = FHeight;
    }

    public String getFVolume() {
        return this.FVolume;
    }

    public void setFVolume(String FVolume) {
        this.FVolume = FVolume;
    }

    public String getFMaterialid() {
        return this.FMaterialid;
    }

    public void setFMaterialid(String FMaterialid) {
        this.FMaterialid = FMaterialid;
    }

    public String getFNumber() {
        return this.FNumber;
    }

    public void setFNumber(String FNumber) {
        this.FNumber = FNumber;
    }

    public String getFoldNumber() {
        return this.FoldNumber;
    }

    public void setFoldNumber(String FoldNumber) {
        this.FoldNumber = FoldNumber;
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

    public String getFMnemoniccode() {
        return this.FMnemoniccode;
    }

    public void setFMnemoniccode(String FMnemoniccode) {
        this.FMnemoniccode = FMnemoniccode;
    }

    public String getFOrg() {
        return this.FOrg;
    }

    public void setFOrg(String FOrg) {
        this.FOrg = FOrg;
    }

    public String getFMASTERID() {
        return this.FMASTERID;
    }

    public void setFMASTERID(String FMASTERID) {
        this.FMASTERID = FMASTERID;
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

    public String getFProduceUnitNumber() {
        return this.FProduceUnitNumber;
    }

    public void setFProduceUnitNumber(String FProduceUnitNumber) {
        this.FProduceUnitNumber = FProduceUnitNumber;
    }

    public String getFPurchaseUnitNumber() {
        return this.FPurchaseUnitNumber;
    }

    public void setFPurchaseUnitNumber(String FPurchaseUnitNumber) {
        this.FPurchaseUnitNumber = FPurchaseUnitNumber;
    }

    public String getFPurchasePriceUnitNumber() {
        return this.FPurchasePriceUnitNumber;
    }

    public void setFPurchasePriceUnitNumber(String FPurchasePriceUnitNumber) {
        this.FPurchasePriceUnitNumber = FPurchasePriceUnitNumber;
    }

    public String getFSaleUnitNumber() {
        return this.FSaleUnitNumber;
    }

    public void setFSaleUnitNumber(String FSaleUnitNumber) {
        this.FSaleUnitNumber = FSaleUnitNumber;
    }

    public String getFSalePriceUnitNumber() {
        return this.FSalePriceUnitNumber;
    }

    public void setFSalePriceUnitNumber(String FSalePriceUnitNumber) {
        this.FSalePriceUnitNumber = FSalePriceUnitNumber;
    }

    public String getFStoreUnitNumber() {
        return this.FStoreUnitNumber;
    }

    public void setFStoreUnitNumber(String FStoreUnitNumber) {
        this.FStoreUnitNumber = FStoreUnitNumber;
    }

    public String getFAuxUnitNumber() {
        return this.FAuxUnitNumber;
    }

    public void setFAuxUnitNumber(String FAuxUnitNumber) {
        this.FAuxUnitNumber = FAuxUnitNumber;
    }

    public String getFBaseUnitNumber() {
        return this.FBaseUnitNumber;
    }

    public void setFBaseUnitNumber(String FBaseUnitNumber) {
        this.FBaseUnitNumber = FBaseUnitNumber;
    }

    public String getFUnitGroupID() {
        return this.FUnitGroupID;
    }

    public void setFUnitGroupID(String FUnitGroupID) {
        this.FUnitGroupID = FUnitGroupID;
    }
}
