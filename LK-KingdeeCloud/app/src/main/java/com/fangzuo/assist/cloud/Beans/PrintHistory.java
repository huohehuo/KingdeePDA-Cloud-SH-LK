package com.fangzuo.assist.cloud.Beans;


import com.fangzuo.assist.cloud.Dao.Product;
import com.fangzuo.assist.cloud.Dao.Unit;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class PrintHistory {
    public String FTitle;
    public String FHuoquan;
    public String FBarCode;
    public String FBatch;
    public String FName;
    public String FModel;
    public String FNum;
    public String FNum2;
    public String FUnit;
    public String FBaseUnit;
    public String FBaseUnitID;
    public String FStoreUnit;
    public String FUnitAux;
    public String FNot;
    public String FPrintMan;
    public String FNumber;
    public String FStorage;
    public String FWaveHouse;
    public String FSaveIn;
    public String FCheck;
    public String FDate;
    public String FMaterialid;
    public String FAuxSign;
    public String FActualModel;

    public String FLevel;//等级
    public String FYmLenght;//原木长度
    public String FYmDiameter;//原木直径
    public String FBLenght;//板长
    public String FBWide;//板宽
    public String FBThick;//板厚
    public String FVolume;//体积
    public String FCeng;//体积
    public String FWidth;//体积
    public String FProject;//区分单据类型 0 是水版 1为原木（立方米版本）,2或者其他为原木英尺版本
    public String F_TypeID;//区分单据类型 0 是水版 1为原木(英尺版本) todo//早期版本0 是水版 1为原木（立方米版本）,2或者其他为原木英尺版本，0711西安已统一为0水板1原木
    public String F_Plies;//层数

    public void setProduct(Product product){
        this.FName = product.FName;
        this.FModel = product.FModel;
    }
    public void setProject(String pro){
        this.FProject = pro;
    }
    public void setData(Product product, Unit unit, Unit unitAux, String num, String num2, String wave,
                        String not, String huoquan, String barcode, String batch, String date, String title,String auxSign,String actualModel){
        this.FTitle = title;
        this.FName = product.FName;
        this.FModel = product.FModel;
        this.FUnit=unit.FName;
        this.FUnitAux=unitAux.FName;
        this.FNum = num;
        this.FNum2 = num2;
        this.FWaveHouse = wave;
        this.FNot = not;
        this.FHuoquan = huoquan;
        this.FBarCode=barcode;
        this.FBatch = batch;
        this.FDate = date;
        this.FAuxSign = auxSign;
        this.FActualModel = actualModel;
    }
    public void setData33(Product product, Unit unit, String num, String num2,
                          String huoquan, String barcode, String batch, String date, String title,String auxSign,String actualModel){
        this.FTitle = title;
        this.FName = product.FName;
        this.FModel = product.FModel;
        this.FUnit=unit.FName;
        this.FNum = num;
        this.FNum2 = num2;
        this.FHuoquan = huoquan;
        this.FBarCode=barcode;
        this.FBatch = batch;
        this.FDate = date;
        this.FAuxSign = auxSign;
        this.FActualModel = actualModel;
    }
    //用于盘点单
    public void setData(Product product, String unit, String unitAux, String num, String num2, String wave,
                        String not, String huoquan, String barcode, String batch, String date, String title,String auxSign,String actualModel){
        this.FTitle = title;
        this.FName = product.FName;
        this.FModel = product.FModel;
        this.FUnit=unit;
        this.FUnitAux=unitAux;
        this.FNum = num;
        this.FNum2 = num2;
        this.FWaveHouse = wave;
        this.FNot = not;
        this.FHuoquan = huoquan;
        this.FBarCode=barcode;
        this.FBatch = batch;
        this.FDate = date;
        this.FAuxSign = auxSign;
        this.FActualModel = actualModel;
    }

    public void setPrintHistory(PrintHistory bean) {
        this.FTitle = bean.FTitle;
        this.FHuoquan = bean.FHuoquan;
        this.FBarCode = bean.FBarCode;
        this.FBatch = bean.FBatch;
        this.FName = bean.FName;
        this.FModel = bean.FModel;
        this.FNum = bean.FNum;
        this.FNum2 = bean.FNum2;
        this.FUnit = bean.FUnit;
        this.FBaseUnit = bean.FBaseUnit;
        this.FBaseUnitID = bean.FBaseUnitID;
        this.FStoreUnit = bean.FStoreUnit;
        this.FUnitAux = bean.FUnitAux;
        this.FNot = bean.FNot;
        this.FPrintMan = bean.FPrintMan;
        this.FNumber = bean.FNumber;
        this.FStorage = bean.FStorage;
        this.FWaveHouse = bean.FWaveHouse;
        this.FSaveIn = bean.FSaveIn;
        this.FCheck = bean.FCheck;
        this.FDate = bean.FDate;
        this.FMaterialid = bean.FMaterialid;
        this.FAuxSign =bean.FAuxSign;
        this.FActualModel = bean.FActualModel;
    }

    public void setPrintHistory4P2(PrintHistory bean) {
        this.FTitle = bean.FTitle;
        this.FHuoquan = bean.FHuoquan;
        this.FBarCode = bean.FBarCode;
        this.FBatch = bean.FBatch;
        this.FName = bean.FName;
        this.FModel = bean.FModel;
        this.FNum = bean.FNum;
        this.FNum2 = bean.FNum2;
        this.FUnit = bean.FUnit;
        this.FBaseUnit = bean.FBaseUnit;
        this.FBaseUnitID = bean.FBaseUnitID;
        this.FStoreUnit = bean.FStoreUnit;
        this.FUnitAux = bean.FUnitAux;
        this.FNot = bean.FNot;
        this.FPrintMan = bean.FPrintMan;
        this.FNumber = bean.FNumber;
        this.FStorage = bean.FStorage;
        this.FWaveHouse = bean.FWaveHouse;
        this.FSaveIn = bean.FSaveIn;
        this.FCheck = bean.FCheck;
        this.FDate = bean.FDate;
        this.FMaterialid = bean.FMaterialid;
        this.FAuxSign = bean.FAuxSign;
        this.FActualModel = bean.FActualModel;
        this.FProject = bean.FProject;
        this.FLevel = bean.FLevel;
        this.FYmLenght = bean.FYmLenght;
        this.FYmDiameter = bean.FYmDiameter;
        this.FBLenght = bean.FBLenght;
        this.FBWide = bean.FBWide;
        this.FBThick = bean.FBThick;
        this.FVolume = bean.FVolume;
        this.FCeng = bean.FCeng;
        this.FWidth = bean.FWidth;
        this.F_TypeID = bean.F_TypeID;
        this.F_Plies = bean.F_Plies;
    }













    @Generated(hash = 668199552)
    public PrintHistory(String FTitle, String FHuoquan, String FBarCode, String FBatch, String FName, String FModel, String FNum, String FNum2,
            String FUnit, String FBaseUnit, String FBaseUnitID, String FStoreUnit, String FUnitAux, String FNot, String FPrintMan,
            String FNumber, String FStorage, String FWaveHouse, String FSaveIn, String FCheck, String FDate, String FMaterialid,
            String FAuxSign, String FActualModel, String FLevel, String FYmLenght, String FYmDiameter, String FBLenght, String FBWide,
            String FBThick, String FVolume, String FCeng, String FWidth, String FProject, String F_TypeID, String F_Plies) {
        this.FTitle = FTitle;
        this.FHuoquan = FHuoquan;
        this.FBarCode = FBarCode;
        this.FBatch = FBatch;
        this.FName = FName;
        this.FModel = FModel;
        this.FNum = FNum;
        this.FNum2 = FNum2;
        this.FUnit = FUnit;
        this.FBaseUnit = FBaseUnit;
        this.FBaseUnitID = FBaseUnitID;
        this.FStoreUnit = FStoreUnit;
        this.FUnitAux = FUnitAux;
        this.FNot = FNot;
        this.FPrintMan = FPrintMan;
        this.FNumber = FNumber;
        this.FStorage = FStorage;
        this.FWaveHouse = FWaveHouse;
        this.FSaveIn = FSaveIn;
        this.FCheck = FCheck;
        this.FDate = FDate;
        this.FMaterialid = FMaterialid;
        this.FAuxSign = FAuxSign;
        this.FActualModel = FActualModel;
        this.FLevel = FLevel;
        this.FYmLenght = FYmLenght;
        this.FYmDiameter = FYmDiameter;
        this.FBLenght = FBLenght;
        this.FBWide = FBWide;
        this.FBThick = FBThick;
        this.FVolume = FVolume;
        this.FCeng = FCeng;
        this.FWidth = FWidth;
        this.FProject = FProject;
        this.F_TypeID = F_TypeID;
        this.F_Plies = F_Plies;
    }
    @Generated(hash = 915761306)
    public PrintHistory() {
    }
    public String getFTitle() {
        return this.FTitle;
    }
    public void setFTitle(String FTitle) {
        this.FTitle = FTitle;
    }
    public String getFHuoquan() {
        return this.FHuoquan;
    }
    public void setFHuoquan(String FHuoquan) {
        this.FHuoquan = FHuoquan;
    }
    public String getFBarCode() {
        return this.FBarCode;
    }
    public void setFBarCode(String FBarCode) {
        this.FBarCode = FBarCode;
    }
    public String getFBatch() {
        return this.FBatch;
    }
    public void setFBatch(String FBatch) {
        this.FBatch = FBatch;
    }
    public String getFName() {
        return this.FName;
    }
    public void setFName(String FName) {
        this.FName = FName;
    }
    public String getFModel() {
        return this.FModel;
    }
    public void setFModel(String FModel) {
        this.FModel = FModel;
    }
    public String getFNum() {
        return this.FNum;
    }
    public void setFNum(String FNum) {
        this.FNum = FNum;
    }
    public String getFNum2() {
        return this.FNum2;
    }
    public void setFNum2(String FNum2) {
        this.FNum2 = FNum2;
    }
    public String getFUnit() {
        return this.FUnit;
    }
    public void setFUnit(String FUnit) {
        this.FUnit = FUnit;
    }
    public String getFUnitAux() {
        return this.FUnitAux;
    }
    public void setFUnitAux(String FUnitAux) {
        this.FUnitAux = FUnitAux;
    }
    public String getFNot() {
        return this.FNot;
    }
    public void setFNot(String FNot) {
        this.FNot = FNot;
    }
    public String getFWaveHouse() {
        return this.FWaveHouse;
    }
    public void setFWaveHouse(String FWaveHouse) {
        this.FWaveHouse = FWaveHouse;
    }
    public String getFSaveIn() {
        return this.FSaveIn;
    }
    public void setFSaveIn(String FSaveIn) {
        this.FSaveIn = FSaveIn;
    }
    public String getFCheck() {
        return this.FCheck;
    }
    public void setFCheck(String FCheck) {
        this.FCheck = FCheck;
    }
    public String getFDate() {
        return this.FDate;
    }
    public void setFDate(String FDate) {
        this.FDate = FDate;
    }
    public String getFMaterialid() {
        return this.FMaterialid;
    }
    public void setFMaterialid(String FMaterialid) {
        this.FMaterialid = FMaterialid;
    }
    public String getFBaseUnit() {
        return this.FBaseUnit;
    }
    public void setFBaseUnit(String FBaseUnit) {
        this.FBaseUnit = FBaseUnit;
    }
    public String getFAuxSign() {
        return this.FAuxSign;
    }
    public void setFAuxSign(String FAuxSign) {
        this.FAuxSign = FAuxSign;
    }
    public String getFActualModel() {
        return this.FActualModel;
    }
    public void setFActualModel(String FActualModel) {
        this.FActualModel = FActualModel;
    }
    public String getFBaseUnitID() {
        return this.FBaseUnitID;
    }
    public void setFBaseUnitID(String FBaseUnitID) {
        this.FBaseUnitID = FBaseUnitID;
    }
    public String getFStoreUnit() {
        return this.FStoreUnit;
    }
    public void setFStoreUnit(String FStoreUnit) {
        this.FStoreUnit = FStoreUnit;
    }
    public String getFPrintMan() {
        return this.FPrintMan;
    }
    public void setFPrintMan(String FPrintMan) {
        this.FPrintMan = FPrintMan;
    }
    public String getFNumber() {
        return this.FNumber;
    }
    public void setFNumber(String FNumber) {
        this.FNumber = FNumber;
    }
    public String getFStorage() {
        return this.FStorage;
    }
    public void setFStorage(String FStorage) {
        this.FStorage = FStorage;
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
    public String getFProject() {
        return this.FProject;
    }
    public void setFProject(String FProject) {
        this.FProject = FProject;
    }
    public String getFCeng() {
        return this.FCeng;
    }
    public void setFCeng(String FCeng) {
        this.FCeng = FCeng;
    }
    public String getFWidth() {
        return this.FWidth;
    }
    public void setFWidth(String FWidth) {
        this.FWidth = FWidth;
    }
    public String getF_TypeID() {
        return this.F_TypeID;
    }
    public void setF_TypeID(String F_TypeID) {
        this.F_TypeID = F_TypeID;
    }
    public String getF_Plies() {
        return this.F_Plies;
    }
    public void setF_Plies(String F_Plies) {
        this.F_Plies = F_Plies;
    }
}
