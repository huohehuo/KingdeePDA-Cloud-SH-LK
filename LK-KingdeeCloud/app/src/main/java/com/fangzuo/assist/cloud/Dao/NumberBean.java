package com.fangzuo.assist.cloud.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by NB on 2017/7/26.
 */
@Entity
public class NumberBean {
    public int FNumber;
    public int FNumber2;
    public int FNumber3;
    public int FNumber4;
    public Double FDouble;
    public Double FDouble2;
    public Double FDouble3;
    public Double FDouble4;

    public NumberBean(int num){
        this.FNumber = num;
    }

    @Generated(hash = 325603880)
    public NumberBean(int FNumber, int FNumber2, int FNumber3, int FNumber4,
            Double FDouble, Double FDouble2, Double FDouble3, Double FDouble4) {
        this.FNumber = FNumber;
        this.FNumber2 = FNumber2;
        this.FNumber3 = FNumber3;
        this.FNumber4 = FNumber4;
        this.FDouble = FDouble;
        this.FDouble2 = FDouble2;
        this.FDouble3 = FDouble3;
        this.FDouble4 = FDouble4;
    }

    @Generated(hash = 1722912181)
    public NumberBean() {
    }

    public int getFNumber() {
        return this.FNumber;
    }

    public void setFNumber(int FNumber) {
        this.FNumber = FNumber;
    }

    public int getFNumber2() {
        return this.FNumber2;
    }

    public void setFNumber2(int FNumber2) {
        this.FNumber2 = FNumber2;
    }

    public int getFNumber3() {
        return this.FNumber3;
    }

    public void setFNumber3(int FNumber3) {
        this.FNumber3 = FNumber3;
    }

    public int getFNumber4() {
        return this.FNumber4;
    }

    public void setFNumber4(int FNumber4) {
        this.FNumber4 = FNumber4;
    }

    public Double getFDouble() {
        return this.FDouble;
    }

    public void setFDouble(Double FDouble) {
        this.FDouble = FDouble;
    }

    public Double getFDouble2() {
        return this.FDouble2;
    }

    public void setFDouble2(Double FDouble2) {
        this.FDouble2 = FDouble2;
    }

    public Double getFDouble3() {
        return this.FDouble3;
    }

    public void setFDouble3(Double FDouble3) {
        this.FDouble3 = FDouble3;
    }

    public Double getFDouble4() {
        return this.FDouble4;
    }

    public void setFDouble4(Double FDouble4) {
        this.FDouble4 = FDouble4;
    }
}
