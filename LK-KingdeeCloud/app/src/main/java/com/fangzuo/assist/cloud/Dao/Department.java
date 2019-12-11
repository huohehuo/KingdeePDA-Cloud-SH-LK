package com.fangzuo.assist.cloud.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by NB on 2017/7/26.
 */
@Entity
public class Department {
    public String  FItemID ;
    public String FNumber ;
    public String FName ;
    public String FISSTOCK;
    public String FOrg;


    @Generated(hash = 101868600)
    public Department(String FItemID, String FNumber, String FName, String FISSTOCK,
            String FOrg) {
        this.FItemID = FItemID;
        this.FNumber = FNumber;
        this.FName = FName;
        this.FISSTOCK = FISSTOCK;
        this.FOrg = FOrg;
    }


    @Generated(hash = 355406289)
    public Department() {
    }


    @Override
    public String toString() {
        return "Department{" +
                "FItemID='" + FItemID + '\'' +
                ", FNumber='" + FNumber + '\'' +
                ", FName='" + FName + '\'' +
                ", FISSTOCK='" + FISSTOCK + '\'' +
                ", FOrg='" + FOrg + '\'' +
                '}';
    }


    public String getFItemID() {
        return this.FItemID;
    }


    public void setFItemID(String FItemID) {
        this.FItemID = FItemID;
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


    public String getFISSTOCK() {
        return this.FISSTOCK;
    }


    public void setFISSTOCK(String FISSTOCK) {
        this.FISSTOCK = FISSTOCK;
    }


    public String getFOrg() {
        return this.FOrg;
    }


    public void setFOrg(String FOrg) {
        this.FOrg = FOrg;
    }
}
