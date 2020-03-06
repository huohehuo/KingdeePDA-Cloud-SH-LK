package com.fangzuo.assist.cloud.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by NB on 2017/7/26.
 */
@Entity
public class InStorageNum {
    @Id(autoincrement = true)
    private Long _id;
    public String FItemID;
    public String FStockID;
    public String FQty;
    public String FStoreState;
    public String FStoreOrgID;
    public String FBatchNo;
    public String FUnitID;
    public String FKFDate;
    public String FKFPeriod;
    public String FStockPlaceID;
    public String FHuozhuID;
    @Generated(hash = 1756249501)
    public InStorageNum(Long _id, String FItemID, String FStockID, String FQty,
            String FStoreState, String FStoreOrgID, String FBatchNo, String FUnitID,
            String FKFDate, String FKFPeriod, String FStockPlaceID,
            String FHuozhuID) {
        this._id = _id;
        this.FItemID = FItemID;
        this.FStockID = FStockID;
        this.FQty = FQty;
        this.FStoreState = FStoreState;
        this.FStoreOrgID = FStoreOrgID;
        this.FBatchNo = FBatchNo;
        this.FUnitID = FUnitID;
        this.FKFDate = FKFDate;
        this.FKFPeriod = FKFPeriod;
        this.FStockPlaceID = FStockPlaceID;
        this.FHuozhuID = FHuozhuID;
    }
    @Generated(hash = 471196027)
    public InStorageNum() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public String getFItemID() {
        return this.FItemID;
    }
    public void setFItemID(String FItemID) {
        this.FItemID = FItemID;
    }
    public String getFStockID() {
        return this.FStockID;
    }
    public void setFStockID(String FStockID) {
        this.FStockID = FStockID;
    }
    public String getFQty() {
        return this.FQty;
    }
    public void setFQty(String FQty) {
        this.FQty = FQty;
    }
    public String getFStoreState() {
        return this.FStoreState;
    }
    public void setFStoreState(String FStoreState) {
        this.FStoreState = FStoreState;
    }
    public String getFStoreOrgID() {
        return this.FStoreOrgID;
    }
    public void setFStoreOrgID(String FStoreOrgID) {
        this.FStoreOrgID = FStoreOrgID;
    }
    public String getFBatchNo() {
        return this.FBatchNo;
    }
    public void setFBatchNo(String FBatchNo) {
        this.FBatchNo = FBatchNo;
    }
    public String getFUnitID() {
        return this.FUnitID;
    }
    public void setFUnitID(String FUnitID) {
        this.FUnitID = FUnitID;
    }
    public String getFKFDate() {
        return this.FKFDate;
    }
    public void setFKFDate(String FKFDate) {
        this.FKFDate = FKFDate;
    }
    public String getFKFPeriod() {
        return this.FKFPeriod;
    }
    public void setFKFPeriod(String FKFPeriod) {
        this.FKFPeriod = FKFPeriod;
    }
    public String getFStockPlaceID() {
        return this.FStockPlaceID;
    }
    public void setFStockPlaceID(String FStockPlaceID) {
        this.FStockPlaceID = FStockPlaceID;
    }
    public String getFHuozhuID() {
        return this.FHuozhuID;
    }
    public void setFHuozhuID(String FHuozhuID) {
        this.FHuozhuID = FHuozhuID;
    }

}
