package com.fangzuo.assist.cloud.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by NB on 2017/7/26.
 */
@Entity
public class StoreMan {
    public String FID;
    public String FNumber;
    public String FName;
    public String FDeptID;
    public String FOrg;
    @Generated(hash = 38597912)
    public StoreMan(String FID, String FNumber, String FName, String FDeptID,
            String FOrg) {
        this.FID = FID;
        this.FNumber = FNumber;
        this.FName = FName;
        this.FDeptID = FDeptID;
        this.FOrg = FOrg;
    }
    @Generated(hash = 1249744050)
    public StoreMan() {
    }
    public String getFID() {
        return this.FID;
    }
    public void setFID(String FID) {
        this.FID = FID;
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
    public String getFDeptID() {
        return this.FDeptID;
    }
    public void setFDeptID(String FDeptID) {
        this.FDeptID = FDeptID;
    }

    @Override
    public String toString() {
        return "StoreMan{" +
                "FID='" + FID + '\'' +
                ", FNumber='" + FNumber + '\'' +
                ", FName='" + FName + '\'' +
                ", FDeptID='" + FDeptID + '\'' +
                ", FOrg='" + FOrg + '\'' +
                '}';
    }
    public String getFOrg() {
        return this.FOrg;
    }
    public void setFOrg(String FOrg) {
        this.FOrg = FOrg;
    }
}
