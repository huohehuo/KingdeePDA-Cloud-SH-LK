package com.fangzuo.assist.cloud.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

import java.util.List;

/**
 * Created by NB on 2017/7/26.
 */


public class CountOffBeanList {
    public int FCountNumber;//报数码
    public List<T_Detail> list;

    public CountOffBeanList(int number,List<T_Detail> list){
        this.FCountNumber = number;
        this.list = list;
    }


}
