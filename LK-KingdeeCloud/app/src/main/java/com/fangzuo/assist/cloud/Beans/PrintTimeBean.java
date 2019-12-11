package com.fangzuo.assist.cloud.Beans;


import com.fangzuo.assist.cloud.Dao.Product;
import com.fangzuo.assist.cloud.Dao.Unit;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

public class PrintTimeBean {
    public int FStart;
    public int FEnd;

    public PrintTimeBean(int FStart, int FEnd) {
        this.FStart = FStart;
        this.FEnd = FEnd;
    }
}
