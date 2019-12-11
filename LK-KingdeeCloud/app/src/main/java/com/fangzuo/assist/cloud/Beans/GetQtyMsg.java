package com.fangzuo.assist.cloud.Beans;

public class GetQtyMsg {
    public String FMATERIALID;
    public String FUnitID;
    public String FQty;
    //backData
    public String FBaseUnitName;
    public String FStoreUnitName;
    public String FBaseQty;
    public String FStoreQty;

    public GetQtyMsg(String FMATERIALID, String FUnitID, String FQty) {
        this.FMATERIALID = FMATERIALID;
        this.FUnitID = FUnitID;
        this.FQty = FQty;
    }
}
