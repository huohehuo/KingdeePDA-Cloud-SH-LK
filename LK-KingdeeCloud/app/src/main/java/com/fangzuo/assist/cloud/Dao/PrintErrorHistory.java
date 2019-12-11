package com.fangzuo.assist.cloud.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by NB on 2017/7/26.
 */
@Entity
public class PrintErrorHistory {
    @Id(autoincrement = true)
    public Long FIndex;
    public String FBoxCode;
    public String FTime;
    public String FState;//0失败，1,成功
    public String FNote;
    public String FRemark1;
    public String FRemark2;
    public String FRemark3;
    public String FRemark4;

    public PrintErrorHistory(String box,String time,String state){
        this.FBoxCode = box;
        this.FTime = time;
        this.FState = state;
    }

    @Generated(hash = 1558543954)
    public PrintErrorHistory(Long FIndex, String FBoxCode, String FTime,
            String FState, String FNote, String FRemark1, String FRemark2,
            String FRemark3, String FRemark4) {
        this.FIndex = FIndex;
        this.FBoxCode = FBoxCode;
        this.FTime = FTime;
        this.FState = FState;
        this.FNote = FNote;
        this.FRemark1 = FRemark1;
        this.FRemark2 = FRemark2;
        this.FRemark3 = FRemark3;
        this.FRemark4 = FRemark4;
    }
    @Generated(hash = 522325354)
    public PrintErrorHistory() {
    }
    public Long getFIndex() {
        return this.FIndex;
    }
    public void setFIndex(Long FIndex) {
        this.FIndex = FIndex;
    }
    public String getFBoxCode() {
        return this.FBoxCode;
    }
    public void setFBoxCode(String FBoxCode) {
        this.FBoxCode = FBoxCode;
    }
    public String getFTime() {
        return this.FTime;
    }
    public void setFTime(String FTime) {
        this.FTime = FTime;
    }
    public String getFState() {
        return this.FState;
    }
    public void setFState(String FState) {
        this.FState = FState;
    }
    public String getFNote() {
        return this.FNote;
    }
    public void setFNote(String FNote) {
        this.FNote = FNote;
    }
    public String getFRemark1() {
        return this.FRemark1;
    }
    public void setFRemark1(String FRemark1) {
        this.FRemark1 = FRemark1;
    }
    public String getFRemark2() {
        return this.FRemark2;
    }
    public void setFRemark2(String FRemark2) {
        this.FRemark2 = FRemark2;
    }
    public String getFRemark3() {
        return this.FRemark3;
    }
    public void setFRemark3(String FRemark3) {
        this.FRemark3 = FRemark3;
    }
    public String getFRemark4() {
        return this.FRemark4;
    }
    public void setFRemark4(String FRemark4) {
        this.FRemark4 = FRemark4;
    }


}
