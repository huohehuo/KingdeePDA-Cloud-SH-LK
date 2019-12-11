package com.fangzuo.assist.cloud.Beans;


import com.fangzuo.assist.cloud.Dao.Product;

public class ProductForDetail {
    public Long id;
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


    public void add(Product product){
        this.FProduceUnitID = product.FProduceUnitID;
        this.FPurchaseUnitID = product.FPurchaseUnitID;
        this.FPurchasePriceUnitID = product.FPurchasePriceUnitID;
        this.FSaleUnitID = product.FSaleUnitID;
        this.FSalePriceUnitID = product.FSalePriceUnitID;
        this.FStoreUnitID = product.FStoreUnitID;
        this.FAuxUnitID = product.FAuxUnitID;
        this.FStockID = product.FStockID;
        this.FStockPlaceID = product.FStockPlaceID;
        this.FIsBatchManage = product.FIsBatchManage;
        this.FIsKFperiod = product.FIsKFperiod;
        this.FExpperiod = product.FExpperiod;
        this.FExpUnit = product.FExpUnit;
        this.FIsPurchase = product.FIsPurchase;
        this.FIsSale = product.FIsSale;
        this.FIsInventory = product.FIsInventory;
        this.FIsProduce = product.FIsProduce;
        this.FIsSubContract = product.FIsSubContract;
        this.FIsAsset = product.FIsAsset;
        this.FBaseUnitID = product.FBaseUnitID;
        this.FFWeightUnitID = product.FFWeightUnitID;
        this.FVolumeUnitID = product.FVolumeUnitID;
        this.FBarcode = product.FBarcode;
        this.FGrossWeight = product.FGrossWeight;
        this.FNetWeight = product.FNetWeight;
        this.FLenght = product.FLenght;
        this.FWidth = product.FWidth;
        this.FHeight = product.FHeight;
        this.FVolume = product.FVolume;
        this.FMaterialid = product.FMaterialid;
        this.FNumber = product.FNumber;
        this.FoldNumber = product.FoldNumber;
        this.FName = product.FName;
        this.FModel = product.FModel;
        this.FMnemoniccode = product.FMnemoniccode;
    }
}
