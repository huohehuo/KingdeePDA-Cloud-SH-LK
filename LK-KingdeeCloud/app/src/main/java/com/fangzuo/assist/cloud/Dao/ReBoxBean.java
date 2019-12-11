package com.fangzuo.assist.cloud.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by NB on 2017/7/26.
 */
@Entity
public class ReBoxBean {
    @Id(autoincrement = true)
    public Long FIndex;
    public int activity;
    public long FOrderId;
    public String FID;
    public String FBillNo;
    public String FAccountID;
    public String FPCS;
    public String FM3;
    public String FWide;
    public String FBoxCode;
    public String FBatch;
    public String FUnit;
    public String FStorage;
    public String FCarNo;
    public String FBaoNum;
    @Generated(hash = 592068388)
    public ReBoxBean(Long FIndex, int activity, long FOrderId, String FID,
            String FBillNo, String FAccountID, String FPCS, String FM3,
            String FWide, String FBoxCode, String FBatch, String FUnit,
            String FStorage, String FCarNo, String FBaoNum) {
        this.FIndex = FIndex;
        this.activity = activity;
        this.FOrderId = FOrderId;
        this.FID = FID;
        this.FBillNo = FBillNo;
        this.FAccountID = FAccountID;
        this.FPCS = FPCS;
        this.FM3 = FM3;
        this.FWide = FWide;
        this.FBoxCode = FBoxCode;
        this.FBatch = FBatch;
        this.FUnit = FUnit;
        this.FStorage = FStorage;
        this.FCarNo = FCarNo;
        this.FBaoNum = FBaoNum;
    }
    @Generated(hash = 1979267999)
    public ReBoxBean() {
    }
    public Long getFIndex() {
        return this.FIndex;
    }
    public void setFIndex(Long FIndex) {
        this.FIndex = FIndex;
    }
    public int getActivity() {
        return this.activity;
    }
    public void setActivity(int activity) {
        this.activity = activity;
    }
    public long getFOrderId() {
        return this.FOrderId;
    }
    public void setFOrderId(long FOrderId) {
        this.FOrderId = FOrderId;
    }
    public String getFID() {
        return this.FID;
    }
    public void setFID(String FID) {
        this.FID = FID;
    }
    public String getFBillNo() {
        return this.FBillNo;
    }
    public void setFBillNo(String FBillNo) {
        this.FBillNo = FBillNo;
    }
    public String getFAccountID() {
        return this.FAccountID;
    }
    public void setFAccountID(String FAccountID) {
        this.FAccountID = FAccountID;
    }
    public String getFPCS() {
        return this.FPCS;
    }
    public void setFPCS(String FPCS) {
        this.FPCS = FPCS;
    }
    public String getFM3() {
        return this.FM3;
    }
    public void setFM3(String FM3) {
        this.FM3 = FM3;
    }
    public String getFWide() {
        return this.FWide;
    }
    public void setFWide(String FWide) {
        this.FWide = FWide;
    }
    public String getFBoxCode() {
        return this.FBoxCode;
    }
    public void setFBoxCode(String FBoxCode) {
        this.FBoxCode = FBoxCode;
    }
    public String getFBatch() {
        return this.FBatch;
    }
    public void setFBatch(String FBatch) {
        this.FBatch = FBatch;
    }
    public String getFUnit() {
        return this.FUnit;
    }
    public void setFUnit(String FUnit) {
        this.FUnit = FUnit;
    }
    public String getFStorage() {
        return this.FStorage;
    }
    public void setFStorage(String FStorage) {
        this.FStorage = FStorage;
    }
    public String getFCarNo() {
        return this.FCarNo;
    }
    public void setFCarNo(String FCarNo) {
        this.FCarNo = FCarNo;
    }
    public String getFBaoNum() {
        return this.FBaoNum;
    }
    public void setFBaoNum(String FBaoNum) {
        this.FBaoNum = FBaoNum;
    }
    

}
