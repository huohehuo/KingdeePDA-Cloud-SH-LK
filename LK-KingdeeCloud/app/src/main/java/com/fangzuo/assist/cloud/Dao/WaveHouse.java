package com.fangzuo.assist.cloud.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by NB on 2017/8/1.
 */
@Entity
public class WaveHouse {
    public String FSPID;
    public String FNumber;
    public String FName;
    public String FOrg;
    public String FStorageID;
    public String FSEQ;
    @Generated(hash = 1307608725)
    public WaveHouse(String FSPID, String FNumber, String FName, String FOrg,
            String FStorageID, String FSEQ) {
        this.FSPID = FSPID;
        this.FNumber = FNumber;
        this.FName = FName;
        this.FOrg = FOrg;
        this.FStorageID = FStorageID;
        this.FSEQ = FSEQ;
    }
    @Generated(hash = 365743171)
    public WaveHouse() {
    }
    public String getFSPID() {
        return this.FSPID;
    }
    public void setFSPID(String FSPID) {
        this.FSPID = FSPID;
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
    public String getFOrg() {
        return this.FOrg;
    }
    public void setFOrg(String FOrg) {
        this.FOrg = FOrg;
    }
    public String getFStorageID() {
        return this.FStorageID;
    }
    public void setFStorageID(String FStorageID) {
        this.FStorageID = FStorageID;
    }
    public String getFSEQ() {
        return this.FSEQ;
    }
    public void setFSEQ(String FSEQ) {
        this.FSEQ = FSEQ;
    }

   
}
