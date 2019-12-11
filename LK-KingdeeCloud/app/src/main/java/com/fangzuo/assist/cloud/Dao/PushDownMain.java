package com.fangzuo.assist.cloud.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by NB on 2017/8/24.
 *
 *              单据信息
 */
@Entity
public class PushDownMain {
    @Id(autoincrement = true)
    public Long id;
    public String FID;
    public String FBillNo;
    public String FDate;
    public String FSupplyID;
    public String FSupply;
    public String FSaleOrgID;
    public String FStoreOrgID;
    public String FSettleOrgId;
    public String FSaleManID;
    public String FSaleDeptID;
    public String FNot;
    public int tag;
    public String FBillTypeName;
    public String FAccountID;

    public String FDept;
    public String FDeptID;
    public String FClient;
    public String FClientID;

    public String FAppOrgID;
    public String FHuozhuInType;
    public String FHuozhuOutType;
    public String FDbType;

    public String FOrderNo;
    public String FWordShopID;
    public String FWordShop;
    public String FStoreManID;
    public String FStockerGroupID;
    public String FCreateOrgID;
    public String FHuoZhuID;

    public String FWlCompany;
    public String FCarBoxNo;
    public String FPassNo;
    public String FFreight;
    public String FYaoNo;            //
    public String FFieldMan;

    public String FStr1;
    public String FStr2;
    public String FStr3;
    public String FStr4;
    public String FStr5;

    @Generated(hash = 1938902929)
    public PushDownMain(Long id, String FID, String FBillNo, String FDate, String FSupplyID, String FSupply,
            String FSaleOrgID, String FStoreOrgID, String FSettleOrgId, String FSaleManID, String FSaleDeptID,
            String FNot, int tag, String FBillTypeName, String FAccountID, String FDept, String FDeptID,
            String FClient, String FClientID, String FAppOrgID, String FHuozhuInType, String FHuozhuOutType,
            String FDbType, String FOrderNo, String FWordShopID, String FWordShop, String FStoreManID,
            String FStockerGroupID, String FCreateOrgID, String FHuoZhuID, String FWlCompany, String FCarBoxNo,
            String FPassNo, String FFreight, String FYaoNo, String FFieldMan, String FStr1, String FStr2,
            String FStr3, String FStr4, String FStr5) {
        this.id = id;
        this.FID = FID;
        this.FBillNo = FBillNo;
        this.FDate = FDate;
        this.FSupplyID = FSupplyID;
        this.FSupply = FSupply;
        this.FSaleOrgID = FSaleOrgID;
        this.FStoreOrgID = FStoreOrgID;
        this.FSettleOrgId = FSettleOrgId;
        this.FSaleManID = FSaleManID;
        this.FSaleDeptID = FSaleDeptID;
        this.FNot = FNot;
        this.tag = tag;
        this.FBillTypeName = FBillTypeName;
        this.FAccountID = FAccountID;
        this.FDept = FDept;
        this.FDeptID = FDeptID;
        this.FClient = FClient;
        this.FClientID = FClientID;
        this.FAppOrgID = FAppOrgID;
        this.FHuozhuInType = FHuozhuInType;
        this.FHuozhuOutType = FHuozhuOutType;
        this.FDbType = FDbType;
        this.FOrderNo = FOrderNo;
        this.FWordShopID = FWordShopID;
        this.FWordShop = FWordShop;
        this.FStoreManID = FStoreManID;
        this.FStockerGroupID = FStockerGroupID;
        this.FCreateOrgID = FCreateOrgID;
        this.FHuoZhuID = FHuoZhuID;
        this.FWlCompany = FWlCompany;
        this.FCarBoxNo = FCarBoxNo;
        this.FPassNo = FPassNo;
        this.FFreight = FFreight;
        this.FYaoNo = FYaoNo;
        this.FFieldMan = FFieldMan;
        this.FStr1 = FStr1;
        this.FStr2 = FStr2;
        this.FStr3 = FStr3;
        this.FStr4 = FStr4;
        this.FStr5 = FStr5;
    }
    @Generated(hash = 92092905)
    public PushDownMain() {
    }

    public PushDownMain(String FID, String FBillNo, String FDate, String FSupplyID, String FSupply, int tag) {
        this.FID = FID;
        this.FBillNo = FBillNo;
        this.FDate = FDate;
        this.FSupplyID = FSupplyID;
        this.FSupply = FSupply;
        this.tag = tag;
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
    public String getFSupplyID() {
        return this.FSupplyID;
    }
    public void setFSupplyID(String FSupplyID) {
        this.FSupplyID = FSupplyID;
    }
    public String getFSupply() {
        return this.FSupply;
    }
    public void setFSupply(String FSupply) {
        this.FSupply = FSupply;
    }
    public int getTag() {
        return this.tag;
    }
    public void setTag(int tag) {
        this.tag = tag;
    }
    public String getFSaleOrgID() {
        return this.FSaleOrgID;
    }
    public void setFSaleOrgID(String FSaleOrgID) {
        this.FSaleOrgID = FSaleOrgID;
    }
    public String getFSaleManID() {
        return this.FSaleManID;
    }
    public void setFSaleManID(String FSaleManID) {
        this.FSaleManID = FSaleManID;
    }
    public String getFSaleDeptID() {
        return this.FSaleDeptID;
    }
    public void setFSaleDeptID(String FSaleDeptID) {
        this.FSaleDeptID = FSaleDeptID;
    }
    public String getFNot() {
        return this.FNot;
    }
    public void setFNot(String FNot) {
        this.FNot = FNot;
    }
    public String getFBillTypeName() {
        return this.FBillTypeName;
    }
    public void setFBillTypeName(String FBillTypeName) {
        this.FBillTypeName = FBillTypeName;
    }
    public String getFStoreOrgID() {
        return this.FStoreOrgID;
    }
    public void setFStoreOrgID(String FStoreOrgID) {
        this.FStoreOrgID = FStoreOrgID;
    }
    public String getFSettleOrgId() {
        return this.FSettleOrgId;
    }
    public void setFSettleOrgId(String FSettleOrgId) {
        this.FSettleOrgId = FSettleOrgId;
    }
    public String getFAppOrgID() {
        return this.FAppOrgID;
    }
    public void setFAppOrgID(String FAppOrgID) {
        this.FAppOrgID = FAppOrgID;
    }
    public String getFHuozhuInType() {
        return this.FHuozhuInType;
    }
    public void setFHuozhuInType(String FHuozhuInType) {
        this.FHuozhuInType = FHuozhuInType;
    }
    public String getFHuozhuOutType() {
        return this.FHuozhuOutType;
    }
    public void setFHuozhuOutType(String FHuozhuOutType) {
        this.FHuozhuOutType = FHuozhuOutType;
    }
    public String getFDbType() {
        return this.FDbType;
    }
    public void setFDbType(String FDbType) {
        this.FDbType = FDbType;
    }
    public String getFDept() {
        return this.FDept;
    }
    public void setFDept(String FDept) {
        this.FDept = FDept;
    }
    public String getFDeptID() {
        return this.FDeptID;
    }
    public void setFDeptID(String FDeptID) {
        this.FDeptID = FDeptID;
    }
    public String getFClient() {
        return this.FClient;
    }
    public void setFClient(String FClient) {
        this.FClient = FClient;
    }
    public String getFClientID() {
        return this.FClientID;
    }
    public void setFClientID(String FClientID) {
        this.FClientID = FClientID;
    }
    public String getFOrderNo() {
        return this.FOrderNo;
    }
    public void setFOrderNo(String FOrderNo) {
        this.FOrderNo = FOrderNo;
    }
    public String getFWordShopID() {
        return this.FWordShopID;
    }
    public void setFWordShopID(String FWordShopID) {
        this.FWordShopID = FWordShopID;
    }
    public String getFWordShop() {
        return this.FWordShop;
    }
    public void setFWordShop(String FWordShop) {
        this.FWordShop = FWordShop;
    }
    public String getFStoreManID() {
        return this.FStoreManID;
    }
    public void setFStoreManID(String FStoreManID) {
        this.FStoreManID = FStoreManID;
    }
    public String getFStockerGroupID() {
        return this.FStockerGroupID;
    }
    public void setFStockerGroupID(String FStockerGroupID) {
        this.FStockerGroupID = FStockerGroupID;
    }
    public String getFCreateOrgID() {
        return this.FCreateOrgID;
    }
    public void setFCreateOrgID(String FCreateOrgID) {
        this.FCreateOrgID = FCreateOrgID;
    }
    public String getFHuoZhuID() {
        return this.FHuoZhuID;
    }
    public void setFHuoZhuID(String FHuoZhuID) {
        this.FHuoZhuID = FHuoZhuID;
    }
    public String getFAccountID() {
        return this.FAccountID;
    }
    public void setFAccountID(String FAccountID) {
        this.FAccountID = FAccountID;
    }
    public String getFStr1() {
        return this.FStr1;
    }
    public void setFStr1(String FStr1) {
        this.FStr1 = FStr1;
    }
    public String getFStr2() {
        return this.FStr2;
    }
    public void setFStr2(String FStr2) {
        this.FStr2 = FStr2;
    }
    public String getFStr3() {
        return this.FStr3;
    }
    public void setFStr3(String FStr3) {
        this.FStr3 = FStr3;
    }
    public String getFStr4() {
        return this.FStr4;
    }
    public void setFStr4(String FStr4) {
        this.FStr4 = FStr4;
    }
    public String getFStr5() {
        return this.FStr5;
    }
    public void setFStr5(String FStr5) {
        this.FStr5 = FStr5;
    }
    public String getFWlCompany() {
        return this.FWlCompany;
    }
    public void setFWlCompany(String FWlCompany) {
        this.FWlCompany = FWlCompany;
    }
    public String getFCarBoxNo() {
        return this.FCarBoxNo;
    }
    public void setFCarBoxNo(String FCarBoxNo) {
        this.FCarBoxNo = FCarBoxNo;
    }
    public String getFPassNo() {
        return this.FPassNo;
    }
    public void setFPassNo(String FPassNo) {
        this.FPassNo = FPassNo;
    }
    public String getFFreight() {
        return this.FFreight;
    }
    public void setFFreight(String FFreight) {
        this.FFreight = FFreight;
    }
    public String getFYaoNo() {
        return this.FYaoNo;
    }
    public void setFYaoNo(String FYaoNo) {
        this.FYaoNo = FYaoNo;
    }
    public String getFFieldMan() {
        return this.FFieldMan;
    }
    public void setFFieldMan(String FFieldMan) {
        this.FFieldMan = FFieldMan;
    }

//    public String FName;
//    public String FDeptID;
//    public String FManagerID;
//    public String FEmpID;
//    public String FInterID;

}
