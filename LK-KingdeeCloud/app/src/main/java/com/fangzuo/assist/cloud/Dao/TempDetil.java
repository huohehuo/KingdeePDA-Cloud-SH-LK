package com.fangzuo.assist.cloud.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by NB on 2017/8/3.
 */
@Entity
public class TempDetil {

    @Id(autoincrement = true)
    public Long FIndex;

    public String FBarcode;
    public String IMIE;
    public String FBillerID;
    public long FOrderId;
    public String FAccountID;
    public int activity;

    public String FLenght;
    public String FWide;
    public String FNum;
    public String FAllNum;

    public String FEntryID;     //下推的明细唯一值
    public String FID;     //下推的明细内码

    public String FProductName;
    public String model;
    public String FStorageId;           //实为number
    public String FStoragePDId;         //盘点时的仓库id
    public String FStorage;             //仓库名
    public String FWaveHouse;             //仓库名
    public String FWaveHouseId;             //仓库名
    public String FWaveHousePDId;             //仓库名
    public String FUnit;
    public String FSupplier;
    public String FQuantity;            //

    public String FTaxPrice;            //含税单价
    public String FTaxRate;//税率
    public String FHuoZhuNumber;
    public String FHuoZhu;

    public String FBackDate;            //退货日期
    public String FBackType;            //退货类型

    public String FSOEntryId;//销售订单EntryId
    public String FB2CORDERDETAILID;//B2C订单明细Id

    public String FStorageOutId;            //
    public String FStorageOut;            //
    public String FStorageInId;            //
    public String FStorageIn;            //
    public String FWaveHouseOutId;            //
    public String FWaveHouseOut;            //
    public String FWaveHouseInId;            //
    public String FWaveHouseIn;            //


    public String FBatch;
    public boolean FIsFree;
    public String FIsGift;//1为赠品 0不是赠品
    public String FWorkShopId1;         //生产车间
    public String FRemainInStockQty;            ////采购数量
    public String FRealQty;            //实收数量
    public String FRemainInStockUnitId;//采购单位
    public String FPriceUnitID;//计价单位
    public String FMaterialId;//物料编码
    public String FItemID;//物料编码
    public String FMaterialIdForPD;//物料编码
    public String FUnitID;//库存单位
    public String FUnitIDForPD;//库存单位
    public String AuxSign;//辅助标识
    public String ActualModel;//实际规格
    public String FProductNo;//生产编号
    public String FStoreNum;//生产编号
    public String FBaseNum;//生产编号

    public String FStoreUnit;//库存单位
    public String FStoreUnitID;//库存单位
    public String FBaseUnit;//基本单位
    public String FBaseUnitID;//库存单位
    //-----------------------------------------

    //盘点
    public String FBillNo;//盘点方案编码
    public String FAllowAddMaterial;//物料盘点作业允许增加物料
    public String FZeroStockInCount;//零库存参与盘点
    public String FBillTypeID;//单据类型
    public String FCheckQtyDefault;//实盘数默认值
    public String FNotIncludeForbidMat;//禁用物料不参与盘点
    public String FStockOrgId;//库存组织
    public String FDocumentStatus;//状态
    public String FCloseStatus;//关闭状态
    public String FNote;//备注
    public String FOWnerTypeID;//货主类型
    public String FOwnerId;//货主
    public String FKeeperTypeId;//保管者类型
    public String FKeeperId;//保管者

    public String FLevel;//等级
    public String FYmLenght;//原木长度
    public String FYmDiameter;//原木直径
    public String FBLenght;//板长
    public String FBWide;//板宽
    public String FCLWide;//测量宽
    public String FBThick;//板厚
    public String FVolume;//体积

    //报数等信息
    public int FCfLenght;//长度
    public String FCfLenghtAny;//自定义的一个长度
    public String FCfThick;//厚度
    public String FCfWide;//宽度
    public int FCountNumber;//报数码-弃用
    public String FCfAveMax;//均值
    public String FCfBoxCode;//箱码
    public String FCfProductID;//箱码
    public String FCfProductNumber;//箱码
    public String FCfUnitID;//箱码
    public String FCfQty;//pcs
    public String FCfQtySum;//pcs
    public String FCfM2;//平方米
    public String FCfM2Sum;//平方米
    public String FCfStorageID;//仓库
    public String FCfWaveHouseID;//仓位
    public String FCfStoreOrgID;//库存组织
    public String FCfHuozhuID;//货主
    public long FBoxCodeOrder;//箱码单号
    public int FIsInBox;//是否已装箱   0 未装箱，1已装箱

    public String FStr1;
    public String FStr2;
    public String FStr3;
    public String FStr4;
    public String FStr5;
    @Generated(hash = 563996532)
    public TempDetil(Long FIndex, String FBarcode, String IMIE, String FBillerID,
            long FOrderId, String FAccountID, int activity, String FLenght,
            String FWide, String FNum, String FAllNum, String FEntryID, String FID,
            String FProductName, String model, String FStorageId,
            String FStoragePDId, String FStorage, String FWaveHouse,
            String FWaveHouseId, String FWaveHousePDId, String FUnit,
            String FSupplier, String FQuantity, String FTaxPrice, String FTaxRate,
            String FHuoZhuNumber, String FHuoZhu, String FBackDate,
            String FBackType, String FSOEntryId, String FB2CORDERDETAILID,
            String FStorageOutId, String FStorageOut, String FStorageInId,
            String FStorageIn, String FWaveHouseOutId, String FWaveHouseOut,
            String FWaveHouseInId, String FWaveHouseIn, String FBatch,
            boolean FIsFree, String FIsGift, String FWorkShopId1,
            String FRemainInStockQty, String FRealQty, String FRemainInStockUnitId,
            String FPriceUnitID, String FMaterialId, String FItemID,
            String FMaterialIdForPD, String FUnitID, String FUnitIDForPD,
            String AuxSign, String ActualModel, String FProductNo, String FStoreNum,
            String FBaseNum, String FStoreUnit, String FStoreUnitID,
            String FBaseUnit, String FBaseUnitID, String FBillNo,
            String FAllowAddMaterial, String FZeroStockInCount, String FBillTypeID,
            String FCheckQtyDefault, String FNotIncludeForbidMat,
            String FStockOrgId, String FDocumentStatus, String FCloseStatus,
            String FNote, String FOWnerTypeID, String FOwnerId,
            String FKeeperTypeId, String FKeeperId, String FLevel, String FYmLenght,
            String FYmDiameter, String FBLenght, String FBWide, String FCLWide,
            String FBThick, String FVolume, int FCfLenght, String FCfLenghtAny,
            String FCfThick, String FCfWide, int FCountNumber, String FCfAveMax,
            String FCfBoxCode, String FCfProductID, String FCfProductNumber,
            String FCfUnitID, String FCfQty, String FCfQtySum, String FCfM2,
            String FCfM2Sum, String FCfStorageID, String FCfWaveHouseID,
            String FCfStoreOrgID, String FCfHuozhuID, long FBoxCodeOrder,
            int FIsInBox, String FStr1, String FStr2, String FStr3, String FStr4,
            String FStr5) {
        this.FIndex = FIndex;
        this.FBarcode = FBarcode;
        this.IMIE = IMIE;
        this.FBillerID = FBillerID;
        this.FOrderId = FOrderId;
        this.FAccountID = FAccountID;
        this.activity = activity;
        this.FLenght = FLenght;
        this.FWide = FWide;
        this.FNum = FNum;
        this.FAllNum = FAllNum;
        this.FEntryID = FEntryID;
        this.FID = FID;
        this.FProductName = FProductName;
        this.model = model;
        this.FStorageId = FStorageId;
        this.FStoragePDId = FStoragePDId;
        this.FStorage = FStorage;
        this.FWaveHouse = FWaveHouse;
        this.FWaveHouseId = FWaveHouseId;
        this.FWaveHousePDId = FWaveHousePDId;
        this.FUnit = FUnit;
        this.FSupplier = FSupplier;
        this.FQuantity = FQuantity;
        this.FTaxPrice = FTaxPrice;
        this.FTaxRate = FTaxRate;
        this.FHuoZhuNumber = FHuoZhuNumber;
        this.FHuoZhu = FHuoZhu;
        this.FBackDate = FBackDate;
        this.FBackType = FBackType;
        this.FSOEntryId = FSOEntryId;
        this.FB2CORDERDETAILID = FB2CORDERDETAILID;
        this.FStorageOutId = FStorageOutId;
        this.FStorageOut = FStorageOut;
        this.FStorageInId = FStorageInId;
        this.FStorageIn = FStorageIn;
        this.FWaveHouseOutId = FWaveHouseOutId;
        this.FWaveHouseOut = FWaveHouseOut;
        this.FWaveHouseInId = FWaveHouseInId;
        this.FWaveHouseIn = FWaveHouseIn;
        this.FBatch = FBatch;
        this.FIsFree = FIsFree;
        this.FIsGift = FIsGift;
        this.FWorkShopId1 = FWorkShopId1;
        this.FRemainInStockQty = FRemainInStockQty;
        this.FRealQty = FRealQty;
        this.FRemainInStockUnitId = FRemainInStockUnitId;
        this.FPriceUnitID = FPriceUnitID;
        this.FMaterialId = FMaterialId;
        this.FItemID = FItemID;
        this.FMaterialIdForPD = FMaterialIdForPD;
        this.FUnitID = FUnitID;
        this.FUnitIDForPD = FUnitIDForPD;
        this.AuxSign = AuxSign;
        this.ActualModel = ActualModel;
        this.FProductNo = FProductNo;
        this.FStoreNum = FStoreNum;
        this.FBaseNum = FBaseNum;
        this.FStoreUnit = FStoreUnit;
        this.FStoreUnitID = FStoreUnitID;
        this.FBaseUnit = FBaseUnit;
        this.FBaseUnitID = FBaseUnitID;
        this.FBillNo = FBillNo;
        this.FAllowAddMaterial = FAllowAddMaterial;
        this.FZeroStockInCount = FZeroStockInCount;
        this.FBillTypeID = FBillTypeID;
        this.FCheckQtyDefault = FCheckQtyDefault;
        this.FNotIncludeForbidMat = FNotIncludeForbidMat;
        this.FStockOrgId = FStockOrgId;
        this.FDocumentStatus = FDocumentStatus;
        this.FCloseStatus = FCloseStatus;
        this.FNote = FNote;
        this.FOWnerTypeID = FOWnerTypeID;
        this.FOwnerId = FOwnerId;
        this.FKeeperTypeId = FKeeperTypeId;
        this.FKeeperId = FKeeperId;
        this.FLevel = FLevel;
        this.FYmLenght = FYmLenght;
        this.FYmDiameter = FYmDiameter;
        this.FBLenght = FBLenght;
        this.FBWide = FBWide;
        this.FCLWide = FCLWide;
        this.FBThick = FBThick;
        this.FVolume = FVolume;
        this.FCfLenght = FCfLenght;
        this.FCfLenghtAny = FCfLenghtAny;
        this.FCfThick = FCfThick;
        this.FCfWide = FCfWide;
        this.FCountNumber = FCountNumber;
        this.FCfAveMax = FCfAveMax;
        this.FCfBoxCode = FCfBoxCode;
        this.FCfProductID = FCfProductID;
        this.FCfProductNumber = FCfProductNumber;
        this.FCfUnitID = FCfUnitID;
        this.FCfQty = FCfQty;
        this.FCfQtySum = FCfQtySum;
        this.FCfM2 = FCfM2;
        this.FCfM2Sum = FCfM2Sum;
        this.FCfStorageID = FCfStorageID;
        this.FCfWaveHouseID = FCfWaveHouseID;
        this.FCfStoreOrgID = FCfStoreOrgID;
        this.FCfHuozhuID = FCfHuozhuID;
        this.FBoxCodeOrder = FBoxCodeOrder;
        this.FIsInBox = FIsInBox;
        this.FStr1 = FStr1;
        this.FStr2 = FStr2;
        this.FStr3 = FStr3;
        this.FStr4 = FStr4;
        this.FStr5 = FStr5;
    }
    @Generated(hash = 81558384)
    public TempDetil() {
    }
    public Long getFIndex() {
        return this.FIndex;
    }
    public void setFIndex(Long FIndex) {
        this.FIndex = FIndex;
    }
    public String getFBarcode() {
        return this.FBarcode;
    }
    public void setFBarcode(String FBarcode) {
        this.FBarcode = FBarcode;
    }
    public String getIMIE() {
        return this.IMIE;
    }
    public void setIMIE(String IMIE) {
        this.IMIE = IMIE;
    }
    public String getFBillerID() {
        return this.FBillerID;
    }
    public void setFBillerID(String FBillerID) {
        this.FBillerID = FBillerID;
    }
    public long getFOrderId() {
        return this.FOrderId;
    }
    public void setFOrderId(long FOrderId) {
        this.FOrderId = FOrderId;
    }
    public String getFAccountID() {
        return this.FAccountID;
    }
    public void setFAccountID(String FAccountID) {
        this.FAccountID = FAccountID;
    }
    public int getActivity() {
        return this.activity;
    }
    public void setActivity(int activity) {
        this.activity = activity;
    }
    public String getFEntryID() {
        return this.FEntryID;
    }
    public void setFEntryID(String FEntryID) {
        this.FEntryID = FEntryID;
    }
    public String getFID() {
        return this.FID;
    }
    public void setFID(String FID) {
        this.FID = FID;
    }
    public String getFProductName() {
        return this.FProductName;
    }
    public void setFProductName(String FProductName) {
        this.FProductName = FProductName;
    }
    public String getModel() {
        return this.model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getFStorageId() {
        return this.FStorageId;
    }
    public void setFStorageId(String FStorageId) {
        this.FStorageId = FStorageId;
    }
    public String getFStoragePDId() {
        return this.FStoragePDId;
    }
    public void setFStoragePDId(String FStoragePDId) {
        this.FStoragePDId = FStoragePDId;
    }
    public String getFStorage() {
        return this.FStorage;
    }
    public void setFStorage(String FStorage) {
        this.FStorage = FStorage;
    }
    public String getFWaveHouse() {
        return this.FWaveHouse;
    }
    public void setFWaveHouse(String FWaveHouse) {
        this.FWaveHouse = FWaveHouse;
    }
    public String getFWaveHouseId() {
        return this.FWaveHouseId;
    }
    public void setFWaveHouseId(String FWaveHouseId) {
        this.FWaveHouseId = FWaveHouseId;
    }
    public String getFWaveHousePDId() {
        return this.FWaveHousePDId;
    }
    public void setFWaveHousePDId(String FWaveHousePDId) {
        this.FWaveHousePDId = FWaveHousePDId;
    }
    public String getFUnit() {
        return this.FUnit;
    }
    public void setFUnit(String FUnit) {
        this.FUnit = FUnit;
    }
    public String getFSupplier() {
        return this.FSupplier;
    }
    public void setFSupplier(String FSupplier) {
        this.FSupplier = FSupplier;
    }
    public String getFQuantity() {
        return this.FQuantity;
    }
    public void setFQuantity(String FQuantity) {
        this.FQuantity = FQuantity;
    }
    public String getFTaxPrice() {
        return this.FTaxPrice;
    }
    public void setFTaxPrice(String FTaxPrice) {
        this.FTaxPrice = FTaxPrice;
    }
    public String getFTaxRate() {
        return this.FTaxRate;
    }
    public void setFTaxRate(String FTaxRate) {
        this.FTaxRate = FTaxRate;
    }
    public String getFHuoZhuNumber() {
        return this.FHuoZhuNumber;
    }
    public void setFHuoZhuNumber(String FHuoZhuNumber) {
        this.FHuoZhuNumber = FHuoZhuNumber;
    }
    public String getFHuoZhu() {
        return this.FHuoZhu;
    }
    public void setFHuoZhu(String FHuoZhu) {
        this.FHuoZhu = FHuoZhu;
    }
    public String getFBackDate() {
        return this.FBackDate;
    }
    public void setFBackDate(String FBackDate) {
        this.FBackDate = FBackDate;
    }
    public String getFBackType() {
        return this.FBackType;
    }
    public void setFBackType(String FBackType) {
        this.FBackType = FBackType;
    }
    public String getFSOEntryId() {
        return this.FSOEntryId;
    }
    public void setFSOEntryId(String FSOEntryId) {
        this.FSOEntryId = FSOEntryId;
    }
    public String getFB2CORDERDETAILID() {
        return this.FB2CORDERDETAILID;
    }
    public void setFB2CORDERDETAILID(String FB2CORDERDETAILID) {
        this.FB2CORDERDETAILID = FB2CORDERDETAILID;
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
    public String getFBatch() {
        return this.FBatch;
    }
    public void setFBatch(String FBatch) {
        this.FBatch = FBatch;
    }
    public boolean getFIsFree() {
        return this.FIsFree;
    }
    public void setFIsFree(boolean FIsFree) {
        this.FIsFree = FIsFree;
    }
    public String getFIsGift() {
        return this.FIsGift;
    }
    public void setFIsGift(String FIsGift) {
        this.FIsGift = FIsGift;
    }
    public String getFWorkShopId1() {
        return this.FWorkShopId1;
    }
    public void setFWorkShopId1(String FWorkShopId1) {
        this.FWorkShopId1 = FWorkShopId1;
    }
    public String getFRemainInStockQty() {
        return this.FRemainInStockQty;
    }
    public void setFRemainInStockQty(String FRemainInStockQty) {
        this.FRemainInStockQty = FRemainInStockQty;
    }
    public String getFRealQty() {
        return this.FRealQty;
    }
    public void setFRealQty(String FRealQty) {
        this.FRealQty = FRealQty;
    }
    public String getFRemainInStockUnitId() {
        return this.FRemainInStockUnitId;
    }
    public void setFRemainInStockUnitId(String FRemainInStockUnitId) {
        this.FRemainInStockUnitId = FRemainInStockUnitId;
    }
    public String getFPriceUnitID() {
        return this.FPriceUnitID;
    }
    public void setFPriceUnitID(String FPriceUnitID) {
        this.FPriceUnitID = FPriceUnitID;
    }
    public String getFMaterialId() {
        return this.FMaterialId;
    }
    public void setFMaterialId(String FMaterialId) {
        this.FMaterialId = FMaterialId;
    }
    public String getFItemID() {
        return this.FItemID;
    }
    public void setFItemID(String FItemID) {
        this.FItemID = FItemID;
    }
    public String getFMaterialIdForPD() {
        return this.FMaterialIdForPD;
    }
    public void setFMaterialIdForPD(String FMaterialIdForPD) {
        this.FMaterialIdForPD = FMaterialIdForPD;
    }
    public String getFUnitID() {
        return this.FUnitID;
    }
    public void setFUnitID(String FUnitID) {
        this.FUnitID = FUnitID;
    }
    public String getFUnitIDForPD() {
        return this.FUnitIDForPD;
    }
    public void setFUnitIDForPD(String FUnitIDForPD) {
        this.FUnitIDForPD = FUnitIDForPD;
    }
    public String getAuxSign() {
        return this.AuxSign;
    }
    public void setAuxSign(String AuxSign) {
        this.AuxSign = AuxSign;
    }
    public String getActualModel() {
        return this.ActualModel;
    }
    public void setActualModel(String ActualModel) {
        this.ActualModel = ActualModel;
    }
    public String getFProductNo() {
        return this.FProductNo;
    }
    public void setFProductNo(String FProductNo) {
        this.FProductNo = FProductNo;
    }
    public String getFStoreNum() {
        return this.FStoreNum;
    }
    public void setFStoreNum(String FStoreNum) {
        this.FStoreNum = FStoreNum;
    }
    public String getFBaseNum() {
        return this.FBaseNum;
    }
    public void setFBaseNum(String FBaseNum) {
        this.FBaseNum = FBaseNum;
    }
    public String getFStoreUnit() {
        return this.FStoreUnit;
    }
    public void setFStoreUnit(String FStoreUnit) {
        this.FStoreUnit = FStoreUnit;
    }
    public String getFStoreUnitID() {
        return this.FStoreUnitID;
    }
    public void setFStoreUnitID(String FStoreUnitID) {
        this.FStoreUnitID = FStoreUnitID;
    }
    public String getFBaseUnit() {
        return this.FBaseUnit;
    }
    public void setFBaseUnit(String FBaseUnit) {
        this.FBaseUnit = FBaseUnit;
    }
    public String getFBaseUnitID() {
        return this.FBaseUnitID;
    }
    public void setFBaseUnitID(String FBaseUnitID) {
        this.FBaseUnitID = FBaseUnitID;
    }
    public String getFBillNo() {
        return this.FBillNo;
    }
    public void setFBillNo(String FBillNo) {
        this.FBillNo = FBillNo;
    }
    public String getFAllowAddMaterial() {
        return this.FAllowAddMaterial;
    }
    public void setFAllowAddMaterial(String FAllowAddMaterial) {
        this.FAllowAddMaterial = FAllowAddMaterial;
    }
    public String getFZeroStockInCount() {
        return this.FZeroStockInCount;
    }
    public void setFZeroStockInCount(String FZeroStockInCount) {
        this.FZeroStockInCount = FZeroStockInCount;
    }
    public String getFBillTypeID() {
        return this.FBillTypeID;
    }
    public void setFBillTypeID(String FBillTypeID) {
        this.FBillTypeID = FBillTypeID;
    }
    public String getFCheckQtyDefault() {
        return this.FCheckQtyDefault;
    }
    public void setFCheckQtyDefault(String FCheckQtyDefault) {
        this.FCheckQtyDefault = FCheckQtyDefault;
    }
    public String getFNotIncludeForbidMat() {
        return this.FNotIncludeForbidMat;
    }
    public void setFNotIncludeForbidMat(String FNotIncludeForbidMat) {
        this.FNotIncludeForbidMat = FNotIncludeForbidMat;
    }
    public String getFStockOrgId() {
        return this.FStockOrgId;
    }
    public void setFStockOrgId(String FStockOrgId) {
        this.FStockOrgId = FStockOrgId;
    }
    public String getFDocumentStatus() {
        return this.FDocumentStatus;
    }
    public void setFDocumentStatus(String FDocumentStatus) {
        this.FDocumentStatus = FDocumentStatus;
    }
    public String getFCloseStatus() {
        return this.FCloseStatus;
    }
    public void setFCloseStatus(String FCloseStatus) {
        this.FCloseStatus = FCloseStatus;
    }
    public String getFNote() {
        return this.FNote;
    }
    public void setFNote(String FNote) {
        this.FNote = FNote;
    }
    public String getFOWnerTypeID() {
        return this.FOWnerTypeID;
    }
    public void setFOWnerTypeID(String FOWnerTypeID) {
        this.FOWnerTypeID = FOWnerTypeID;
    }
    public String getFOwnerId() {
        return this.FOwnerId;
    }
    public void setFOwnerId(String FOwnerId) {
        this.FOwnerId = FOwnerId;
    }
    public String getFKeeperTypeId() {
        return this.FKeeperTypeId;
    }
    public void setFKeeperTypeId(String FKeeperTypeId) {
        this.FKeeperTypeId = FKeeperTypeId;
    }
    public String getFKeeperId() {
        return this.FKeeperId;
    }
    public void setFKeeperId(String FKeeperId) {
        this.FKeeperId = FKeeperId;
    }
    public String getFLevel() {
        return this.FLevel;
    }
    public void setFLevel(String FLevel) {
        this.FLevel = FLevel;
    }
    public String getFYmLenght() {
        return this.FYmLenght;
    }
    public void setFYmLenght(String FYmLenght) {
        this.FYmLenght = FYmLenght;
    }
    public String getFYmDiameter() {
        return this.FYmDiameter;
    }
    public void setFYmDiameter(String FYmDiameter) {
        this.FYmDiameter = FYmDiameter;
    }
    public String getFBLenght() {
        return this.FBLenght;
    }
    public void setFBLenght(String FBLenght) {
        this.FBLenght = FBLenght;
    }
    public String getFBWide() {
        return this.FBWide;
    }
    public void setFBWide(String FBWide) {
        this.FBWide = FBWide;
    }
    public String getFCLWide() {
        return this.FCLWide;
    }
    public void setFCLWide(String FCLWide) {
        this.FCLWide = FCLWide;
    }
    public String getFBThick() {
        return this.FBThick;
    }
    public void setFBThick(String FBThick) {
        this.FBThick = FBThick;
    }
    public String getFVolume() {
        return this.FVolume;
    }
    public void setFVolume(String FVolume) {
        this.FVolume = FVolume;
    }
    public int getFCfLenght() {
        return this.FCfLenght;
    }
    public void setFCfLenght(int FCfLenght) {
        this.FCfLenght = FCfLenght;
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
    public int getFCountNumber() {
        return this.FCountNumber;
    }
    public void setFCountNumber(int FCountNumber) {
        this.FCountNumber = FCountNumber;
    }
    public String getFCfAveMax() {
        return this.FCfAveMax;
    }
    public void setFCfAveMax(String FCfAveMax) {
        this.FCfAveMax = FCfAveMax;
    }
    public String getFCfBoxCode() {
        return this.FCfBoxCode;
    }
    public void setFCfBoxCode(String FCfBoxCode) {
        this.FCfBoxCode = FCfBoxCode;
    }
    public String getFCfProductID() {
        return this.FCfProductID;
    }
    public void setFCfProductID(String FCfProductID) {
        this.FCfProductID = FCfProductID;
    }
    public String getFCfProductNumber() {
        return this.FCfProductNumber;
    }
    public void setFCfProductNumber(String FCfProductNumber) {
        this.FCfProductNumber = FCfProductNumber;
    }
    public String getFCfUnitID() {
        return this.FCfUnitID;
    }
    public void setFCfUnitID(String FCfUnitID) {
        this.FCfUnitID = FCfUnitID;
    }
    public String getFCfQty() {
        return this.FCfQty;
    }
    public void setFCfQty(String FCfQty) {
        this.FCfQty = FCfQty;
    }
    public String getFCfQtySum() {
        return this.FCfQtySum;
    }
    public void setFCfQtySum(String FCfQtySum) {
        this.FCfQtySum = FCfQtySum;
    }
    public String getFCfM2() {
        return this.FCfM2;
    }
    public void setFCfM2(String FCfM2) {
        this.FCfM2 = FCfM2;
    }
    public String getFCfM2Sum() {
        return this.FCfM2Sum;
    }
    public void setFCfM2Sum(String FCfM2Sum) {
        this.FCfM2Sum = FCfM2Sum;
    }
    public String getFCfStorageID() {
        return this.FCfStorageID;
    }
    public void setFCfStorageID(String FCfStorageID) {
        this.FCfStorageID = FCfStorageID;
    }
    public String getFCfWaveHouseID() {
        return this.FCfWaveHouseID;
    }
    public void setFCfWaveHouseID(String FCfWaveHouseID) {
        this.FCfWaveHouseID = FCfWaveHouseID;
    }
    public String getFCfStoreOrgID() {
        return this.FCfStoreOrgID;
    }
    public void setFCfStoreOrgID(String FCfStoreOrgID) {
        this.FCfStoreOrgID = FCfStoreOrgID;
    }
    public String getFCfHuozhuID() {
        return this.FCfHuozhuID;
    }
    public void setFCfHuozhuID(String FCfHuozhuID) {
        this.FCfHuozhuID = FCfHuozhuID;
    }
    public long getFBoxCodeOrder() {
        return this.FBoxCodeOrder;
    }
    public void setFBoxCodeOrder(long FBoxCodeOrder) {
        this.FBoxCodeOrder = FBoxCodeOrder;
    }
    public int getFIsInBox() {
        return this.FIsInBox;
    }
    public void setFIsInBox(int FIsInBox) {
        this.FIsInBox = FIsInBox;
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
    public String getFLenght() {
        return this.FLenght;
    }
    public void setFLenght(String FLenght) {
        this.FLenght = FLenght;
    }
    public String getFWide() {
        return this.FWide;
    }
    public void setFWide(String FWide) {
        this.FWide = FWide;
    }
    public String getFNum() {
        return this.FNum;
    }
    public void setFNum(String FNum) {
        this.FNum = FNum;
    }
    public String getFAllNum() {
        return this.FAllNum;
    }
    public void setFAllNum(String FAllNum) {
        this.FAllNum = FAllNum;
    }
  
}
