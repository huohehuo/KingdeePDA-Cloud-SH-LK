package com.fangzuo.assist.cloud.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by NB on 2017/7/26.
 */
@Entity
public class Unit {
    public String FMeasureUnitID;
    public String FNumber;
    public String FName;
    public String FOrg;

    @Generated(hash = 1034157639)
    public Unit(String FMeasureUnitID, String FNumber, String FName, String FOrg) {
        this.FMeasureUnitID = FMeasureUnitID;
        this.FNumber = FNumber;
        this.FName = FName;
        this.FOrg = FOrg;
    }

    @Generated(hash = 1236212320)
    public Unit() {
    }

    @Override
    public String toString() {
        return "Unit{" +
                "FMeasureUnitID='" + FMeasureUnitID + '\'' +
                ", FNumber='" + FNumber + '\'' +
                ", FName='" + FName + '\'' +
//                ", FUnitGroupID='" + FUnitGroupID + '\'' +
//                ", FCoefficient='" + FCoefficient + '\'' +
                '}';
    }

    public String getFMeasureUnitID() {
        return this.FMeasureUnitID;
    }

    public void setFMeasureUnitID(String FMeasureUnitID) {
        this.FMeasureUnitID = FMeasureUnitID;
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
