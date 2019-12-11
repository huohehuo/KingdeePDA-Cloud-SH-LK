package com.fangzuo.assist.cloud.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by NB on 2017/7/26.
 */
@Entity
public class User {
    public String FUserID;
    public String FName;
    public String FPassWord;
    public String FEmpID;
    public String FPermit;
    public String FNameERP;
    public String FPassWordERP;


    @Generated(hash = 1472246090)
    public User(String FUserID, String FName, String FPassWord, String FEmpID,
            String FPermit, String FNameERP, String FPassWordERP) {
        this.FUserID = FUserID;
        this.FName = FName;
        this.FPassWord = FPassWord;
        this.FEmpID = FEmpID;
        this.FPermit = FPermit;
        this.FNameERP = FNameERP;
        this.FPassWordERP = FPassWordERP;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "FUserID='" + FUserID + '\'' +
                ", FName='" + FName + '\'' +
                ", FPassWord='" + FPassWord + '\'' +
                ", FEmpID='" + FEmpID + '\'' +
                '}';
    }

    public String getFUserID() {
        return this.FUserID;
    }

    public void setFUserID(String FUserID) {
        this.FUserID = FUserID;
    }

    public String getFName() {
        return this.FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getFPassWord() {
        return this.FPassWord;
    }

    public void setFPassWord(String FPassWord) {
        this.FPassWord = FPassWord;
    }

    public String getFEmpID() {
        return this.FEmpID;
    }

    public void setFEmpID(String FEmpID) {
        this.FEmpID = FEmpID;
    }

    public String getFPermit() {
        return this.FPermit;
    }

    public void setFPermit(String FPermit) {
        this.FPermit = FPermit;
    }

    public String getFNameERP() {
        return this.FNameERP;
    }

    public void setFNameERP(String FNameERP) {
        this.FNameERP = FNameERP;
    }

    public String getFPassWordERP() {
        return this.FPassWordERP;
    }

    public void setFPassWordERP(String FPassWordERP) {
        this.FPassWordERP = FPassWordERP;
    }
}
