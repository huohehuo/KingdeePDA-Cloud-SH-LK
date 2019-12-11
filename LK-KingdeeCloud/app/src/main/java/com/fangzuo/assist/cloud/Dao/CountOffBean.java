package com.fangzuo.assist.cloud.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by NB on 2017/7/26.
 */

@Entity
public class CountOffBean {
    @Id(autoincrement = true)
    public Long FIndex;

    public String FLenght;//报数
    public int FCountNumber;//报数码
    public String FAveMax;//均值
    public String FBoxCode;//箱码
    public String FProductID;//箱码
    public String FProductNumber;//箱码
    public String FUnitID;//箱码
    public String FQty;//箱码
    public String FStorageID;//箱码
    public String FWaveHouseID;//箱码
    public String FStoreOrgID;//箱码
    public String FHuozhuID;//箱码
    public long FOrderCode;//本次操作装箱码
    public long FBackOrderCode;//回单单号

    public CountOffBean(String lenght,String qty,long orderCode,int number){
        this.FQty = qty;
        this.FLenght = lenght;
        this.FOrderCode = orderCode;
        this.FCountNumber = number;
    }
    public void setBaseData(Storage storage,Org org,Org huozhu){
        this.FStorageID= storage==null?"":storage.FItemID;
        this.FWaveHouseID= "0";
        this.FStoreOrgID= org==null?"":org.FOrgID;
        this.FHuozhuID= huozhu==null?"":huozhu.FOrgID;
    }
    public void setProduct(Product product){
        if (null==product)return;
        this.FProductID = product.FMaterialid;
        this.FProductNumber = product.FNumber;
        this.FUnitID = product.FProduceUnitID;
    }

    @Generated(hash = 1912171878)
    public CountOffBean(Long FIndex, String FLenght, int FCountNumber,
            String FAveMax, String FBoxCode, String FProductID,
            String FProductNumber, String FUnitID, String FQty, String FStorageID,
            String FWaveHouseID, String FStoreOrgID, String FHuozhuID,
            long FOrderCode, long FBackOrderCode) {
        this.FIndex = FIndex;
        this.FLenght = FLenght;
        this.FCountNumber = FCountNumber;
        this.FAveMax = FAveMax;
        this.FBoxCode = FBoxCode;
        this.FProductID = FProductID;
        this.FProductNumber = FProductNumber;
        this.FUnitID = FUnitID;
        this.FQty = FQty;
        this.FStorageID = FStorageID;
        this.FWaveHouseID = FWaveHouseID;
        this.FStoreOrgID = FStoreOrgID;
        this.FHuozhuID = FHuozhuID;
        this.FOrderCode = FOrderCode;
        this.FBackOrderCode = FBackOrderCode;
    }
    @Generated(hash = 224455253)
    public CountOffBean() {
    }

    public Long getFIndex() {
        return this.FIndex;
    }

    public void setFIndex(Long FIndex) {
        this.FIndex = FIndex;
    }

    public String getFLenght() {
        return this.FLenght;
    }

    public void setFLenght(String FLenght) {
        this.FLenght = FLenght;
    }

    public int getFCountNumber() {
        return this.FCountNumber;
    }

    public void setFCountNumber(int FCountNumber) {
        this.FCountNumber = FCountNumber;
    }

    public String getFAveMax() {
        return this.FAveMax;
    }

    public void setFAveMax(String FAveMax) {
        this.FAveMax = FAveMax;
    }

    public String getFBoxCode() {
        return this.FBoxCode;
    }

    public void setFBoxCode(String FBoxCode) {
        this.FBoxCode = FBoxCode;
    }

    public long getFOrderCode() {
        return this.FOrderCode;
    }

    public void setFOrderCode(long FOrderCode) {
        this.FOrderCode = FOrderCode;
    }

    public long getFBackOrderCode() {
        return this.FBackOrderCode;
    }

    public void setFBackOrderCode(long FBackOrderCode) {
        this.FBackOrderCode = FBackOrderCode;
    }
    public String getFProductID() {
        return this.FProductID;
    }
    public void setFProductID(String FProductID) {
        this.FProductID = FProductID;
    }
    public String getFProductNumber() {
        return this.FProductNumber;
    }
    public void setFProductNumber(String FProductNumber) {
        this.FProductNumber = FProductNumber;
    }
    public String getFUnitID() {
        return this.FUnitID;
    }
    public void setFUnitID(String FUnitID) {
        this.FUnitID = FUnitID;
    }
    public String getFQty() {
        return this.FQty;
    }
    public void setFQty(String FQty) {
        this.FQty = FQty;
    }
    public String getFStorageID() {
        return this.FStorageID;
    }
    public void setFStorageID(String FStorageID) {
        this.FStorageID = FStorageID;
    }
    public String getFWaveHouseID() {
        return this.FWaveHouseID;
    }
    public void setFWaveHouseID(String FWaveHouseID) {
        this.FWaveHouseID = FWaveHouseID;
    }
    public String getFStoreOrgID() {
        return this.FStoreOrgID;
    }
    public void setFStoreOrgID(String FStoreOrgID) {
        this.FStoreOrgID = FStoreOrgID;
    }
    public String getFHuozhuID() {
        return this.FHuozhuID;
    }
    public void setFHuozhuID(String FHuozhuID) {
        this.FHuozhuID = FHuozhuID;
    }

}
