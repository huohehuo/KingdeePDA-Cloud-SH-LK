package com.fangzuo.assist.cloud.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by NB on 2017/7/26.
 */
@Entity
public class Client {
    public String FItemID ;
    public String FName;
    public String FNumber;
    public String FOrg;
    public String FMASTERID;
    @Generated(hash = 226389966)
    public Client(String FItemID, String FName, String FNumber, String FOrg,
            String FMASTERID) {
        this.FItemID = FItemID;
        this.FName = FName;
        this.FNumber = FNumber;
        this.FOrg = FOrg;
        this.FMASTERID = FMASTERID;
    }
    @Generated(hash = 1485887936)
    public Client() {
    }
    public String getFItemID() {
        return this.FItemID;
    }
    public void setFItemID(String FItemID) {
        this.FItemID = FItemID;
    }
    public String getFName() {
        return this.FName;
    }
    public void setFName(String FName) {
        this.FName = FName;
    }
    public String getFNumber() {
        return this.FNumber;
    }
    public void setFNumber(String FNumber) {
        this.FNumber = FNumber;
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



}
