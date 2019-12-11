package com.fangzuo.assist.cloud.Beans;

import java.util.ArrayList;

public class ProductTreeBeanList {
    public ArrayList<ProductTreeBean> treeBeans;
    public class ProductTreeBean{
        public String FID;
        public String FName;
        public String FNumber;
        public String FPARENTID;

        public ProductTreeBean() {}
        public ProductTreeBean(String FID, String FName, String FNumber, String FPARENTID) {
            this.FID = FID;
            this.FName = FName;
            this.FNumber = FNumber;
            this.FPARENTID = FPARENTID;
        }
    }

}
