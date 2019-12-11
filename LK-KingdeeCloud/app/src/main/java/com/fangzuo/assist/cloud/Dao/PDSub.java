package com.fangzuo.assist.cloud.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by NB on 2017/8/18.
 */
@Entity
public class PDSub {
    @Id(autoincrement = true)
    public Long id;
    public String FID;//"明细ID");
    public String FEntryID;//ring("明细唯一值");
    public String FSEQ;//("明细序号");
    public String FStockID;//ring("仓库ID");
    public String FStockPlaceID;//getString("仓位ID
    public String FMaterialID;//tString("商品ID")
    public String FUnitID;//ing("单位ID");
    public String FAcctQty;//ring("账存数量");
    public String FCountQty;//tring("已盘数");
    public String FLot;//("批号ID");
    public String FLot_Text;//tring("批号");
    @Generated(hash = 846870720)
    public PDSub(Long id, String FID, String FEntryID, String FSEQ, String FStockID,
            String FStockPlaceID, String FMaterialID, String FUnitID,
            String FAcctQty, String FCountQty, String FLot, String FLot_Text) {
        this.id = id;
        this.FID = FID;
        this.FEntryID = FEntryID;
        this.FSEQ = FSEQ;
        this.FStockID = FStockID;
        this.FStockPlaceID = FStockPlaceID;
        this.FMaterialID = FMaterialID;
        this.FUnitID = FUnitID;
        this.FAcctQty = FAcctQty;
        this.FCountQty = FCountQty;
        this.FLot = FLot;
        this.FLot_Text = FLot_Text;
    }
    @Generated(hash = 974003275)
    public PDSub() {
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
    public String getFEntryID() {
        return this.FEntryID;
    }
    public void setFEntryID(String FEntryID) {
        this.FEntryID = FEntryID;
    }
    public String getFSEQ() {
        return this.FSEQ;
    }
    public void setFSEQ(String FSEQ) {
        this.FSEQ = FSEQ;
    }
    public String getFStockID() {
        return this.FStockID;
    }
    public void setFStockID(String FStockID) {
        this.FStockID = FStockID;
    }
    public String getFStockPlaceID() {
        return this.FStockPlaceID;
    }
    public void setFStockPlaceID(String FStockPlaceID) {
        this.FStockPlaceID = FStockPlaceID;
    }
    public String getFMaterialID() {
        return this.FMaterialID;
    }
    public void setFMaterialID(String FMaterialID) {
        this.FMaterialID = FMaterialID;
    }
    public String getFUnitID() {
        return this.FUnitID;
    }
    public void setFUnitID(String FUnitID) {
        this.FUnitID = FUnitID;
    }
    public String getFAcctQty() {
        return this.FAcctQty;
    }
    public void setFAcctQty(String FAcctQty) {
        this.FAcctQty = FAcctQty;
    }
    public String getFCountQty() {
        return this.FCountQty;
    }
    public void setFCountQty(String FCountQty) {
        this.FCountQty = FCountQty;
    }
    public String getFLot() {
        return this.FLot;
    }
    public void setFLot(String FLot) {
        this.FLot = FLot;
    }
    public String getFLot_Text() {
        return this.FLot_Text;
    }
    public void setFLot_Text(String FLot_Text) {
        this.FLot_Text = FLot_Text;
    }
}
