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

    @Generated(hash = 854973381)
    public WaveHouse(String FSPID, String FNumber, String FName, String FOrg) {
        this.FSPID = FSPID;
        this.FNumber = FNumber;
        this.FName = FName;
        this.FOrg = FOrg;
    }

    @Generated(hash = 365743171)
    public WaveHouse() {
    }

    @Override
    public String toString() {
        return "WaveHouse{" +
                "FSPID='" + FSPID + '\'' +
                ", FNumber='" + FNumber + '\'' +
                ", FName='" + FName + '\'' +
//                ", FSPGroupID='" + FSPGroupID + '\'' +
//                ", FFullName='" + FFullName + '\'' +
//                ", FLevel='" + FLevel + '\'' +
//                ", FDetail='" + FDetail + '\'' +
//                ", FParentID='" + FParentID + '\'' +
//                ", FClassTypeID='" + FClassTypeID + '\'' +
//                ", FDefaultSPID='" + FDefaultSPID + '\'' +
                '}';
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
}
