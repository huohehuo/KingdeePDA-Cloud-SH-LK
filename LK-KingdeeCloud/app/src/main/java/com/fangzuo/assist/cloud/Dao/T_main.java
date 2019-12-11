package com.fangzuo.assist.cloud.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by NB on 2017/8/3.
 */
@Entity
public class    T_main {
    @Id
    public String FIndex;
    public long FOrderId;
    public String FAccountID;
    public String FBarcode;
    public String IMIE;   //用于记录多订单下推时的InterId，获取ordercode
    public String FBillNo;
    public String FBillerID;
    public int activity;

    //必填项
    public String FSoorDerno;//订单单号
    public String FPriceTimePoint;//定价时点
    public String FStockOrgId;//收料组织
    public String FPurchaseOrgId;//采购组织
    public String FBillTypeID;//单据类型

    public String FOwnerTypeIdHead;//(调出)货主类型
    public String FOwnerIdHead;//(调出)货主
    public String FOwnerIdHeadNote;//货主描述
    public String FHuozhuId;//货主描述
    public String FOrgId;//货主描述
    public String FOwnerTypeIdHeadIn;//调入货主类型
    public String FOwnerIdHeadIn;//调入货主
    public String FDBType;//调拨类型
    public String FDBDirection;//调拨方向




    public String FID;//结算组织
    public String FEntryID;//结算组织

    public String FSettleOrgId;//结算组织
    public String FNeedOrgId;//结算组织
    public String FSettleCurrId;//结算币别
    public String FDate;//入库日期
    public String FSupplierId;//供应商
    public String FDepartmentNumber;    //收料部门
    public String FDepartment;          //收料部门
    public String FPurchaseDeptId;    //采购部门
    public String FPurchaseDept;          //采购部门
    public String FPurchaserId;    //采购员
    public String FPurchaser;          //采购员
    public String FStockerNumber;       //仓管员
    public String FStocker;            //仓管员
    public String FCustomerID;            //客户
    public String FCustomer;            //客户
    public String FNot;            //备注
    public String F_FFF_Text;            //业务单号


    public String FCfLenghtAny;//自定义的一个长度
    public String FCfThick;//厚度
    public String FCfWide;//宽度
    public String FStorageId;           //实为number
    public String FStorageNumber;           //实为number
    public String FStorage;             //仓库名
    public String FBoxCode;             //仓库名
    public String FBatch;             //仓库名
    public long FBoxCodeOrder;//箱码单号


    public String FStorageOutId;            //
    public String FStorageOut;            //
    public String FStorageInId;            //
    public String FStorageIn;            //
    public String FWaveHouseOutId;            //
    public String FWaveHouseOut;            //
    public String FWaveHouseInId;            //
    public String FWaveHouseIn;            //


    public String FWlCompany;            //
    public String FCarBoxNo;            //
    public String FPassNo;            //
    public String FFreight;            //
    public String FYaoNo;            //
    public String FBaoNum;            //



    public int FIsInBox;//是否已装箱   0 未装箱，1已装箱
    public String FStr1;
    public String FStr2;
    public String FStr3;
    public String FStr4;
    public String FStr5;

    public String FFieldMan;

    public void setStorageOut(Storage storage){
        if (null==storage){
            return;
        }
        this.FStorageOutId = storage.FNumber;
        this.FStorageOut= storage.FName;
    }
    public void setStorageIn(Storage storage){
        if (null==storage){
            return;
        }
        this.FStorageInId = storage.FNumber;
        this.FStorageIn= storage.FName;
    }

    @Generated(hash = 1718949020)
    public T_main(String FIndex, long FOrderId, String FAccountID, String FBarcode, String IMIE, String FBillNo, String FBillerID, int activity,
            String FSoorDerno, String FPriceTimePoint, String FStockOrgId, String FPurchaseOrgId, String FBillTypeID, String FOwnerTypeIdHead,
            String FOwnerIdHead, String FOwnerIdHeadNote, String FHuozhuId, String FOrgId, String FOwnerTypeIdHeadIn, String FOwnerIdHeadIn,
            String FDBType, String FDBDirection, String FID, String FEntryID, String FSettleOrgId, String FNeedOrgId, String FSettleCurrId,
            String FDate, String FSupplierId, String FDepartmentNumber, String FDepartment, String FPurchaseDeptId, String FPurchaseDept,
            String FPurchaserId, String FPurchaser, String FStockerNumber, String FStocker, String FCustomerID, String FCustomer, String FNot,
            String F_FFF_Text, String FCfLenghtAny, String FCfThick, String FCfWide, String FStorageId, String FStorageNumber, String FStorage,
            String FBoxCode, String FBatch, long FBoxCodeOrder, String FStorageOutId, String FStorageOut, String FStorageInId,
            String FStorageIn, String FWaveHouseOutId, String FWaveHouseOut, String FWaveHouseInId, String FWaveHouseIn, String FWlCompany,
            String FCarBoxNo, String FPassNo, String FFreight, String FYaoNo, String FBaoNum, int FIsInBox, String FStr1, String FStr2,
            String FStr3, String FStr4, String FStr5, String FFieldMan) {
        this.FIndex = FIndex;
        this.FOrderId = FOrderId;
        this.FAccountID = FAccountID;
        this.FBarcode = FBarcode;
        this.IMIE = IMIE;
        this.FBillNo = FBillNo;
        this.FBillerID = FBillerID;
        this.activity = activity;
        this.FSoorDerno = FSoorDerno;
        this.FPriceTimePoint = FPriceTimePoint;
        this.FStockOrgId = FStockOrgId;
        this.FPurchaseOrgId = FPurchaseOrgId;
        this.FBillTypeID = FBillTypeID;
        this.FOwnerTypeIdHead = FOwnerTypeIdHead;
        this.FOwnerIdHead = FOwnerIdHead;
        this.FOwnerIdHeadNote = FOwnerIdHeadNote;
        this.FHuozhuId = FHuozhuId;
        this.FOrgId = FOrgId;
        this.FOwnerTypeIdHeadIn = FOwnerTypeIdHeadIn;
        this.FOwnerIdHeadIn = FOwnerIdHeadIn;
        this.FDBType = FDBType;
        this.FDBDirection = FDBDirection;
        this.FID = FID;
        this.FEntryID = FEntryID;
        this.FSettleOrgId = FSettleOrgId;
        this.FNeedOrgId = FNeedOrgId;
        this.FSettleCurrId = FSettleCurrId;
        this.FDate = FDate;
        this.FSupplierId = FSupplierId;
        this.FDepartmentNumber = FDepartmentNumber;
        this.FDepartment = FDepartment;
        this.FPurchaseDeptId = FPurchaseDeptId;
        this.FPurchaseDept = FPurchaseDept;
        this.FPurchaserId = FPurchaserId;
        this.FPurchaser = FPurchaser;
        this.FStockerNumber = FStockerNumber;
        this.FStocker = FStocker;
        this.FCustomerID = FCustomerID;
        this.FCustomer = FCustomer;
        this.FNot = FNot;
        this.F_FFF_Text = F_FFF_Text;
        this.FCfLenghtAny = FCfLenghtAny;
        this.FCfThick = FCfThick;
        this.FCfWide = FCfWide;
        this.FStorageId = FStorageId;
        this.FStorageNumber = FStorageNumber;
        this.FStorage = FStorage;
        this.FBoxCode = FBoxCode;
        this.FBatch = FBatch;
        this.FBoxCodeOrder = FBoxCodeOrder;
        this.FStorageOutId = FStorageOutId;
        this.FStorageOut = FStorageOut;
        this.FStorageInId = FStorageInId;
        this.FStorageIn = FStorageIn;
        this.FWaveHouseOutId = FWaveHouseOutId;
        this.FWaveHouseOut = FWaveHouseOut;
        this.FWaveHouseInId = FWaveHouseInId;
        this.FWaveHouseIn = FWaveHouseIn;
        this.FWlCompany = FWlCompany;
        this.FCarBoxNo = FCarBoxNo;
        this.FPassNo = FPassNo;
        this.FFreight = FFreight;
        this.FYaoNo = FYaoNo;
        this.FBaoNum = FBaoNum;
        this.FIsInBox = FIsInBox;
        this.FStr1 = FStr1;
        this.FStr2 = FStr2;
        this.FStr3 = FStr3;
        this.FStr4 = FStr4;
        this.FStr5 = FStr5;
        this.FFieldMan = FFieldMan;
    }
    public void setData(String FBillTypeID,String FStockOrgId,String FPurchaseOrgId){
        this.FPriceTimePoint = "";//定价时点
        this.FStockOrgId = FStockOrgId;//收料组织
        this.FPurchaseOrgId = FPurchaseOrgId;//采购组织
        this.FBillTypeID = FBillTypeID;//单据类型
        this.FOwnerTypeIdHead = "BD_OwnerOrg";//货主类型BD_SupplierBD_Customer
        this.FOwnerIdHead = "102";//货主
        this.FSettleOrgId = FPurchaseOrgId;//结算组织
        this.FSettleCurrId = "PRE001";//结算币别
    }

    public void setDataForOtherIn(String FBillTypeID,String FStockOrgId,String type,String huozhu){
        this.FPriceTimePoint = "";//定价时点
        this.FStockOrgId = FStockOrgId;//收料组织
        this.FBillTypeID = FBillTypeID;//单据类型
        this.FOwnerTypeIdHead = type;//货主类型BD_SupplierBD_Customer
        this.FOwnerIdHead = huozhu;//货主
//        this.FSettleOrgId = FPurchaseOrgId;//结算组织
//        this.FSettleCurrId = "PRE001";//结算币别
    }
    //第三方货物出库
    public void setData4OtherOut(String FBillTypeID,String FStockOrgId,String FPurchaseOrgId,String type,String huozhu){
        this.FPriceTimePoint = "";//定价时点
        this.FStockOrgId = FStockOrgId;//收料组织
        this.FPurchaseOrgId = FPurchaseOrgId;//采购组织
        this.FBillTypeID = FBillTypeID;//单据类型
        this.FOwnerTypeIdHead = type;//货主类型BD_OwnerOrgBD_SupplierBD_Customer
        this.FOwnerIdHead = huozhu;//货主
        this.FSettleOrgId = FPurchaseOrgId;//结算组织
        this.FSettleCurrId = "PRE001";//结算币别
    }
    //第三方货物出库
    public void setData4PYing(String FBillTypeID,String FStockOrgId,String FPurchaseOrgId,String type,String huozhu){
        this.FPriceTimePoint = "";//定价时点
        this.FStockOrgId = FStockOrgId;//收料组织
        this.FPurchaseOrgId = FPurchaseOrgId;//采购组织
        this.FBillTypeID = FBillTypeID;//单据类型
        this.FOwnerTypeIdHead = type;//货主类型BD_OwnerOrgBD_SupplierBD_Customer
        this.FOwnerIdHead = huozhu;//货主
        this.FSettleOrgId = FPurchaseOrgId;//结算组织
        this.FSettleCurrId = "PRE001";//结算币别
    }
    public void setDataBBM2SB(String FBillTypeID,String FStockOrgId,String FPurchaseOrgId,String SettleOrgId,String huozhu){
        this.FPriceTimePoint = "";//定价时点
        this.FStockOrgId = FStockOrgId;//收料组织
        this.FPurchaseOrgId = FPurchaseOrgId;//采购组织
        this.FBillTypeID = FBillTypeID;//单据类型
        this.FOwnerTypeIdHead = "BD_OwnerOrg";//货主类型BD_SupplierBD_Customer
        this.FOwnerIdHead = huozhu;//货主
        this.FSettleOrgId = SettleOrgId;//结算组织
        this.FSettleCurrId = "PRE001";//结算币别
    }
    public void setData(String FBillTypeID,String FStockOrgId,String FPurchaseOrgId,String huozhu){
        this.FPriceTimePoint = "";//定价时点
        this.FStockOrgId = FStockOrgId;//收料组织
        this.FPurchaseOrgId = FPurchaseOrgId;//采购组织
        this.FBillTypeID = FBillTypeID;//单据类型
        this.FOwnerTypeIdHead = "BD_OwnerOrg";//货主类型BD_SupplierBD_Customer
        this.FOwnerIdHead = huozhu;//货主
        this.FSettleOrgId = huozhu;//结算组织
        this.FSettleCurrId = "PRE001";//结算币别
    }
    public void setDataForSOrder2SOut(String FBillTypeID,String FStockOrgId,String FPurchaseOrgId,String hzType,String huozhu){
        this.FPriceTimePoint = "";//定价时点
        this.FStockOrgId = FStockOrgId;//收料组织
        this.FPurchaseOrgId = FPurchaseOrgId;//采购组织
        this.FBillTypeID = FBillTypeID;//单据类型
        this.FOwnerTypeIdHead = hzType;//货主类型BD_SupplierBD_Customer
        this.FOwnerIdHead = huozhu;//货主
        this.FSettleOrgId = FPurchaseOrgId;//结算组织
        this.FSettleCurrId = "PRE001";//结算币别
    }
    //调拨单时
    public void setData(String FBillTypeID,String FStockOrgId,String FPurchaseOrgId,String huozhu,String huozhuIn){
        this.FPriceTimePoint = "";//定价时点
        this.FStockOrgId = FStockOrgId;//收料组织
        this.FPurchaseOrgId = FPurchaseOrgId;//采购组织
        this.FBillTypeID = FBillTypeID;//单据类型
        this.FOwnerTypeIdHead = "BD_OwnerOrg";//货主类型BD_SupplierBD_Customer
        this.FOwnerTypeIdHeadIn = "BD_OwnerOrg";//货主类型BD_SupplierBD_Customer
        this.FOwnerIdHead = huozhu;//调出货主
        this.FOwnerIdHeadIn = huozhuIn;//调入货主
        this.FSettleOrgId = FPurchaseOrgId;//结算组织
        this.FSettleCurrId = "PRE001";//结算币别
    }
    //调拨单时
    public void setData4DbApply2DB(String FBillTypeID,String FStockOrgId,String FPurchaseOrgId,String huozhuType,String huozhu,String huozhuIn){
        this.FPriceTimePoint = "";//定价时点
        this.FStockOrgId = FStockOrgId;//收料组织
        this.FPurchaseOrgId = FPurchaseOrgId;//采购组织
        this.FBillTypeID = FBillTypeID;//单据类型
        this.FOwnerTypeIdHead = huozhuType;//货主类型BD_SupplierBD_Customer
        this.FOwnerTypeIdHeadIn = huozhuType;//货主类型BD_SupplierBD_Customer
        this.FOwnerIdHead = huozhu;//调出货主
        this.FOwnerIdHeadIn = huozhuIn;//调入货主
        this.FSettleOrgId = FPurchaseOrgId;//结算组织
        this.FSettleCurrId = "PRE001";//结算币别
    }


    public void setSupplier(Suppliers suppliers){
        if (null==suppliers){
            return;
        }
        this.FSupplierId = suppliers.FNumber;
//        this.FSupplier= suppliers.FName;
    }
    public void setClient(Client client){
        if (null==client){
            return;
        }
        this.FCustomerID = client.FNumber;
        this.FCustomer = client.FName;
//        this.FSupplier= suppliers.FName;
    }


    @Generated(hash = 884209494)
    public T_main() {
    }

    public String getFIndex() {
        return this.FIndex;
    }
    public void setFIndex(String FIndex) {
        this.FIndex = FIndex;
    }
    public long getFOrderId() {
        return this.FOrderId;
    }
    public void setFOrderId(long FOrderId) {
        this.FOrderId = FOrderId;
    }
    public String getIMIE() {
        return this.IMIE;
    }
    public void setIMIE(String IMIE) {
        this.IMIE = IMIE;
    }
    public int getActivity() {
        return this.activity;
    }
    public void setActivity(int activity) {
        this.activity = activity;
    }
    public String getFPriceTimePoint() {
        return this.FPriceTimePoint;
    }
    public void setFPriceTimePoint(String FPriceTimePoint) {
        this.FPriceTimePoint = FPriceTimePoint;
    }
    public String getFStockOrgId() {
        return this.FStockOrgId;
    }
    public void setFStockOrgId(String FStockOrgId) {
        this.FStockOrgId = FStockOrgId;
    }
    public String getFPurchaseOrgId() {
        return this.FPurchaseOrgId;
    }
    public void setFPurchaseOrgId(String FPurchaseOrgId) {
        this.FPurchaseOrgId = FPurchaseOrgId;
    }
    public String getFBillTypeID() {
        return this.FBillTypeID;
    }
    public void setFBillTypeID(String FBillTypeID) {
        this.FBillTypeID = FBillTypeID;
    }
    public String getFOwnerTypeIdHead() {
        return this.FOwnerTypeIdHead;
    }
    public void setFOwnerTypeIdHead(String FOwnerTypeIdHead) {
        this.FOwnerTypeIdHead = FOwnerTypeIdHead;
    }
    public String getFOwnerIdHead() {
        return this.FOwnerIdHead;
    }
    public void setFOwnerIdHead(String FOwnerIdHead) {
        this.FOwnerIdHead = FOwnerIdHead;
    }
    public String getFSettleOrgId() {
        return this.FSettleOrgId;
    }
    public void setFSettleOrgId(String FSettleOrgId) {
        this.FSettleOrgId = FSettleOrgId;
    }
    public String getFSettleCurrId() {
        return this.FSettleCurrId;
    }
    public void setFSettleCurrId(String FSettleCurrId) {
        this.FSettleCurrId = FSettleCurrId;
    }
    public String getFDate() {
        return this.FDate;
    }
    public void setFDate(String FDate) {
        this.FDate = FDate;
    }
    public String getFSupplierId() {
        return this.FSupplierId;
    }
    public void setFSupplierId(String FSupplierId) {
        this.FSupplierId = FSupplierId;
    }
    public String getFDepartmentNumber() {
        return this.FDepartmentNumber;
    }
    public void setFDepartmentNumber(String FDepartmentNumber) {
        this.FDepartmentNumber = FDepartmentNumber;
    }
    public String getFDepartment() {
        return this.FDepartment;
    }
    public void setFDepartment(String FDepartment) {
        this.FDepartment = FDepartment;
    }
    public String getFStockerNumber() {
        return this.FStockerNumber;
    }
    public void setFStockerNumber(String FStockerNumber) {
        this.FStockerNumber = FStockerNumber;
    }
    public String getFStocker() {
        return this.FStocker;
    }
    public void setFStocker(String FStocker) {
        this.FStocker = FStocker;
    }
    public String getFPurchaseDeptId() {
        return this.FPurchaseDeptId;
    }
    public void setFPurchaseDeptId(String FPurchaseDeptId) {
        this.FPurchaseDeptId = FPurchaseDeptId;
    }
    public String getFPurchaseDept() {
        return this.FPurchaseDept;
    }
    public void setFPurchaseDept(String FPurchaseDept) {
        this.FPurchaseDept = FPurchaseDept;
    }
    public String getFPurchaserId() {
        return this.FPurchaserId;
    }
    public void setFPurchaserId(String FPurchaserId) {
        this.FPurchaserId = FPurchaserId;
    }
    public String getFPurchaser() {
        return this.FPurchaser;
    }
    public void setFPurchaser(String FPurchaser) {
        this.FPurchaser = FPurchaser;
    }
    public String getFCustomerID() {
        return this.FCustomerID;
    }
    public void setFCustomerID(String FCustomerID) {
        this.FCustomerID = FCustomerID;
    }
    public String getFCustomer() {
        return this.FCustomer;
    }
    public void setFCustomer(String FCustomer) {
        this.FCustomer = FCustomer;
    }
    public String getFNot() {
        return this.FNot;
    }
    public void setFNot(String FNot) {
        this.FNot = FNot;
    }
    public String getFBillNo() {
        return this.FBillNo;
    }
    public void setFBillNo(String FBillNo) {
        this.FBillNo = FBillNo;
    }
    public String getFSoorDerno() {
        return this.FSoorDerno;
    }
    public void setFSoorDerno(String FSoorDerno) {
        this.FSoorDerno = FSoorDerno;
    }
    public String getFBarcode() {
        return this.FBarcode;
    }
    public void setFBarcode(String FBarcode) {
        this.FBarcode = FBarcode;
    }
    public String getFBillerID() {
        return this.FBillerID;
    }
    public void setFBillerID(String FBillerID) {
        this.FBillerID = FBillerID;
    }
    public String getF_FFF_Text() {
        return this.F_FFF_Text;
    }
    public void setF_FFF_Text(String F_FFF_Text) {
        this.F_FFF_Text = F_FFF_Text;
    }
    public String getFOwnerTypeIdHeadIn() {
        return this.FOwnerTypeIdHeadIn;
    }
    public void setFOwnerTypeIdHeadIn(String FOwnerTypeIdHeadIn) {
        this.FOwnerTypeIdHeadIn = FOwnerTypeIdHeadIn;
    }
    public String getFOwnerIdHeadIn() {
        return this.FOwnerIdHeadIn;
    }
    public void setFOwnerIdHeadIn(String FOwnerIdHeadIn) {
        this.FOwnerIdHeadIn = FOwnerIdHeadIn;
    }
    public String getFDBType() {
        return this.FDBType;
    }
    public void setFDBType(String FDBType) {
        this.FDBType = FDBType;
    }
    public String getFDBDirection() {
        return this.FDBDirection;
    }
    public void setFDBDirection(String FDBDirection) {
        this.FDBDirection = FDBDirection;
    }
    public String getFID() {
        return this.FID;
    }
    public void setFID(String FID) {
        this.FID = FID;
    }
    public String getFEntryID() {
        return this.FEntryID;
    }
    public void setFEntryID(String FEntryID) {
        this.FEntryID = FEntryID;
    }
    public String getFCfLenghtAny() {
        return this.FCfLenghtAny;
    }
    public void setFCfLenghtAny(String FCfLenghtAny) {
        this.FCfLenghtAny = FCfLenghtAny;
    }
    public String getFCfThick() {
        return this.FCfThick;
    }
    public void setFCfThick(String FCfThick) {
        this.FCfThick = FCfThick;
    }
    public String getFCfWide() {
        return this.FCfWide;
    }
    public void setFCfWide(String FCfWide) {
        this.FCfWide = FCfWide;
    }
    public String getFStorageId() {
        return this.FStorageId;
    }
    public void setFStorageId(String FStorageId) {
        this.FStorageId = FStorageId;
    }
    public String getFStorageNumber() {
        return this.FStorageNumber;
    }
    public void setFStorageNumber(String FStorageNumber) {
        this.FStorageNumber = FStorageNumber;
    }
    public String getFStorage() {
        return this.FStorage;
    }
    public void setFStorage(String FStorage) {
        this.FStorage = FStorage;
    }
    public String getFBoxCode() {
        return this.FBoxCode;
    }
    public void setFBoxCode(String FBoxCode) {
        this.FBoxCode = FBoxCode;
    }
    public String getFBatch() {
        return this.FBatch;
    }
    public void setFBatch(String FBatch) {
        this.FBatch = FBatch;
    }
    public long getFBoxCodeOrder() {
        return this.FBoxCodeOrder;
    }
    public void setFBoxCodeOrder(long FBoxCodeOrder) {
        this.FBoxCodeOrder = FBoxCodeOrder;
    }
    public String getFAccountID() {
        return this.FAccountID;
    }
    public void setFAccountID(String FAccountID) {
        this.FAccountID = FAccountID;
    }
    public String getFNeedOrgId() {
        return this.FNeedOrgId;
    }
    public void setFNeedOrgId(String FNeedOrgId) {
        this.FNeedOrgId = FNeedOrgId;
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
    public String getFStorageOutId() {
        return this.FStorageOutId;
    }
    public void setFStorageOutId(String FStorageOutId) {
        this.FStorageOutId = FStorageOutId;
    }
    public String getFStorageOut() {
        return this.FStorageOut;
    }
    public void setFStorageOut(String FStorageOut) {
        this.FStorageOut = FStorageOut;
    }
    public String getFStorageInId() {
        return this.FStorageInId;
    }
    public void setFStorageInId(String FStorageInId) {
        this.FStorageInId = FStorageInId;
    }
    public String getFStorageIn() {
        return this.FStorageIn;
    }
    public void setFStorageIn(String FStorageIn) {
        this.FStorageIn = FStorageIn;
    }
    public String getFWaveHouseOutId() {
        return this.FWaveHouseOutId;
    }
    public void setFWaveHouseOutId(String FWaveHouseOutId) {
        this.FWaveHouseOutId = FWaveHouseOutId;
    }
    public String getFWaveHouseOut() {
        return this.FWaveHouseOut;
    }
    public void setFWaveHouseOut(String FWaveHouseOut) {
        this.FWaveHouseOut = FWaveHouseOut;
    }
    public String getFWaveHouseInId() {
        return this.FWaveHouseInId;
    }
    public void setFWaveHouseInId(String FWaveHouseInId) {
        this.FWaveHouseInId = FWaveHouseInId;
    }
    public String getFWaveHouseIn() {
        return this.FWaveHouseIn;
    }
    public void setFWaveHouseIn(String FWaveHouseIn) {
        this.FWaveHouseIn = FWaveHouseIn;
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
    public String getFOwnerIdHeadNote() {
        return this.FOwnerIdHeadNote;
    }
    public void setFOwnerIdHeadNote(String FOwnerIdHeadNote) {
        this.FOwnerIdHeadNote = FOwnerIdHeadNote;
    }
    public String getFBaoNum() {
        return this.FBaoNum;
    }
    public void setFBaoNum(String FBaoNum) {
        this.FBaoNum = FBaoNum;
    }
    public String getFHuozhuId() {
        return this.FHuozhuId;
    }
    public void setFHuozhuId(String FHuozhuId) {
        this.FHuozhuId = FHuozhuId;
    }
    public String getFOrgId() {
        return this.FOrgId;
    }
    public void setFOrgId(String FOrgId) {
        this.FOrgId = FOrgId;
    }
    public int getFIsInBox() {
        return this.FIsInBox;
    }
    public void setFIsInBox(int FIsInBox) {
        this.FIsInBox = FIsInBox;
    }
    public String getFFieldMan() {
        return this.FFieldMan;
    }
    public void setFFieldMan(String FFieldMan) {
        this.FFieldMan = FFieldMan;
    }
}
