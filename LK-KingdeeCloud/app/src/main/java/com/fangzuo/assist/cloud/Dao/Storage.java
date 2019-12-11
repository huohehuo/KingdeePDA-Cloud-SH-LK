package com.fangzuo.assist.cloud.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by NB on 2017/7/26.
 */
@Entity
public class Storage {
    public String FItemID;
    public String FName;
    public String FNumber;
    public String FIsOpenWaveHouse;
    public String FOrg;
    public String FallowFStore;//允许负库存
    @Generated(hash = 112601099)
    public Storage(String FItemID, String FName, String FNumber,
            String FIsOpenWaveHouse, String FOrg, String FallowFStore) {
        this.FItemID = FItemID;
        this.FName = FName;
        this.FNumber = FNumber;
        this.FIsOpenWaveHouse = FIsOpenWaveHouse;
        this.FOrg = FOrg;
        this.FallowFStore = FallowFStore;
    }
    @Generated(hash = 2114225574)
    public Storage() {
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
    public String getFIsOpenWaveHouse() {
        return this.FIsOpenWaveHouse;
    }
    public void setFIsOpenWaveHouse(String FIsOpenWaveHouse) {
        this.FIsOpenWaveHouse = FIsOpenWaveHouse;
    }
    public String getFallowFStore() {
        return this.FallowFStore;
    }
    public void setFallowFStore(String FallowFStore) {
        this.FallowFStore = FallowFStore;
    }
    public String getFOrg() {
        return this.FOrg;
    }
    public void setFOrg(String FOrg) {
        this.FOrg = FOrg;
    }



}
