package com.fangzuo.assist.cloud.Beans;

public class CodeCheckBean {
    public String FOrderID;
    public String FPDAID;
    public String FBarCode;
    public String FQty;
    public String FStockID;
    public String FStockPlaceID;
    public String FOrgInID;
    public String FOrgOutID;
    public String FHuozhuOutID;
    public String FHuozhuInID;
    public String FStorageOutID;
    public String FStorageInID;
    public String FWaveHouseOutID;
    public String FWaveHouseInID;

    public String FBillNo;
    public String FTypeID;
    public String FID;

    public CodeCheckBean() {
    }
    public CodeCheckBean(String FBarCode, String FOrderID, String FPDAID) {
        this.FOrderID = FOrderID;
        this.FPDAID = FPDAID;
        this.FBarCode = FBarCode;
    }
    public CodeCheckBean(String FBarCode) {
        this.FBarCode = FBarCode;
    }

    public CodeCheckBean(String FBarCode, String FOrderID, String FQty, String FPDAID) {
        this.FOrderID = FOrderID;
        this.FPDAID = FPDAID;
        this.FBarCode = FBarCode;
        this.FQty = FQty;
    }

    public CodeCheckBean(String FBarCode, String FOrderID, String FStockID, String FStockPlaceID, String FPDAID) {
        this.FOrderID = FOrderID;
        this.FPDAID = FPDAID;
        this.FBarCode = FBarCode;
        this.FStockID = FStockID;
        this.FStockPlaceID = FStockPlaceID;
    }

}
