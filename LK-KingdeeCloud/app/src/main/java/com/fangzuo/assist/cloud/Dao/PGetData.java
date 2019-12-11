package com.fangzuo.assist.cloud.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by NB on 2017/7/26.
 */
@Entity
public class PGetData {

    @Id(autoincrement = true)
    public Long FIndex;
    
    public String FID;
    public String FBillNo;
    public String PCS;
    public String M3;
    public String FAccountID;
    public String FWide;
    public String FStr1;
    public String FStr2;
    public String FStr3;
    public String FStr4;
    public String FStr5;
    @Generated(hash = 1243655028)
    public PGetData(Long FIndex, String FID, String FBillNo, String PCS, String M3,
            String FAccountID, String FWide, String FStr1, String FStr2,
            String FStr3, String FStr4, String FStr5) {
        this.FIndex = FIndex;
        this.FID = FID;
        this.FBillNo = FBillNo;
        this.PCS = PCS;
        this.M3 = M3;
        this.FAccountID = FAccountID;
        this.FWide = FWide;
        this.FStr1 = FStr1;
        this.FStr2 = FStr2;
        this.FStr3 = FStr3;
        this.FStr4 = FStr4;
        this.FStr5 = FStr5;
    }
    @Generated(hash = 2063587557)
    public PGetData() {
    }
    public String getPCS() {
        return this.PCS;
    }
    public void setPCS(String PCS) {
        this.PCS = PCS;
    }
    public String getM3() {
        return this.M3;
    }
    public void setM3(String M3) {
        this.M3 = M3;
    }
    public String getFWide() {
        return this.FWide;
    }
    public void setFWide(String FWide) {
        this.FWide = FWide;
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
    public Long getFIndex() {
        return this.FIndex;
    }
    public void setFIndex(Long FIndex) {
        this.FIndex = FIndex;
    }

}
