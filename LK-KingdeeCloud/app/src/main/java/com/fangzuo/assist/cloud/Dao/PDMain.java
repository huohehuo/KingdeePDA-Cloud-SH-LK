package com.fangzuo.assist.cloud.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by NB on 2017/8/18.
 */
@Entity
public class PDMain {
    @Id(autoincrement = true)
    public Long id;
    public String FID;
    public String FBillNo;
    public String FDate;
    public String FSchemeName;
    public String FSchemeNo;
    @Generated(hash = 69053010)
    public PDMain(Long id, String FID, String FBillNo, String FDate,
            String FSchemeName, String FSchemeNo) {
        this.id = id;
        this.FID = FID;
        this.FBillNo = FBillNo;
        this.FDate = FDate;
        this.FSchemeName = FSchemeName;
        this.FSchemeNo = FSchemeNo;
    }
    @Generated(hash = 172423400)
    public PDMain() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public String getFDate() {
        return this.FDate;
    }
    public void setFDate(String FDate) {
        this.FDate = FDate;
    }
    public String getFSchemeName() {
        return this.FSchemeName;
    }
    public void setFSchemeName(String FSchemeName) {
        this.FSchemeName = FSchemeName;
    }
    public String getFSchemeNo() {
        return this.FSchemeNo;
    }
    public void setFSchemeNo(String FSchemeNo) {
        this.FSchemeNo = FSchemeNo;
    }

    
}
