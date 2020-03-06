package com.fangzuo.assist.cloud.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by NB on 2017/7/26.
 * //9.供应商表  --对应金蝶系统表t_Supplier
 */
@Entity
public class Suppliers {
    public String FItemID ;
    public String FName;
    public String FNumber;
    public String FOrg;
    public String FNote;
    public String FMASTERID;
    public String FSupplyClassIfy;

    public String FItemClassID;
    public String FParentID;
    public String FLevel;
    public String FDetail;
    public String FAddress;
    public String FPhone;
    public String FEmail;
    public Suppliers(String id){
        this.FItemID=id;
    }
    @Generated(hash = 1538077258)
    public Suppliers(String FItemID, String FName, String FNumber, String FOrg,
            String FNote, String FMASTERID, String FSupplyClassIfy,
            String FItemClassID, String FParentID, String FLevel, String FDetail,
            String FAddress, String FPhone, String FEmail) {
        this.FItemID = FItemID;
        this.FName = FName;
        this.FNumber = FNumber;
        this.FOrg = FOrg;
        this.FNote = FNote;
        this.FMASTERID = FMASTERID;
        this.FSupplyClassIfy = FSupplyClassIfy;
        this.FItemClassID = FItemClassID;
        this.FParentID = FParentID;
        this.FLevel = FLevel;
        this.FDetail = FDetail;
        this.FAddress = FAddress;
        this.FPhone = FPhone;
        this.FEmail = FEmail;
    }
    @Generated(hash = 1907968983)
    public Suppliers() {
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
    public String getFNote() {
        return this.FNote;
    }
    public void setFNote(String FNote) {
        this.FNote = FNote;
    }
    public String getFMASTERID() {
        return this.FMASTERID;
    }
    public void setFMASTERID(String FMASTERID) {
        this.FMASTERID = FMASTERID;
    }
    public String getFItemClassID() {
        return this.FItemClassID;
    }
    public void setFItemClassID(String FItemClassID) {
        this.FItemClassID = FItemClassID;
    }
    public String getFParentID() {
        return this.FParentID;
    }
    public void setFParentID(String FParentID) {
        this.FParentID = FParentID;
    }
    public String getFLevel() {
        return this.FLevel;
    }
    public void setFLevel(String FLevel) {
        this.FLevel = FLevel;
    }
    public String getFDetail() {
        return this.FDetail;
    }
    public void setFDetail(String FDetail) {
        this.FDetail = FDetail;
    }
    public String getFAddress() {
        return this.FAddress;
    }
    public void setFAddress(String FAddress) {
        this.FAddress = FAddress;
    }
    public String getFPhone() {
        return this.FPhone;
    }
    public void setFPhone(String FPhone) {
        this.FPhone = FPhone;
    }
    public String getFEmail() {
        return this.FEmail;
    }
    public void setFEmail(String FEmail) {
        this.FEmail = FEmail;
    }
    public String getFSupplyClassIfy() {
        return this.FSupplyClassIfy;
    }
    public void setFSupplyClassIfy(String FSupplyClassIfy) {
        this.FSupplyClassIfy = FSupplyClassIfy;
    }

}
