package com.fangzuo.assist.cloud.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by NB on 2017/7/26.
 */
@Entity
public class Employee {
    public String  FItemID ;
    public String FNumber ;
    public String FName;
    public String FOrg;

    @Generated(hash = 131970095)
    public Employee(String FItemID, String FNumber, String FName, String FOrg) {
        this.FItemID = FItemID;
        this.FNumber = FNumber;
        this.FName = FName;
        this.FOrg = FOrg;
    }

    @Generated(hash = 202356944)
    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "FItemID='" + FItemID + '\'' +
                ", FNumber='" + FNumber + '\'' +
                ", FName='" + FName + '\'' +
                ", FOrg='" + FOrg + '\'' +
//                ", FDpartmentID='" + FDpartmentID + '\'' +
//                ", FEmpGroup='" + FEmpGroup + '\'' +
//                ", FEmpGroupID='" + FEmpGroupID + '\'' +
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

    public String getFOrg() {
        return this.FOrg;
    }

    public void setFOrg(String FOrg) {
        this.FOrg = FOrg;
    }
}
