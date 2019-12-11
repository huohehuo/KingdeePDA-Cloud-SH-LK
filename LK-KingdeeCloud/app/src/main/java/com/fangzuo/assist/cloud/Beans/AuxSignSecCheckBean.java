package com.fangzuo.assist.cloud.Beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class AuxSignSecCheckBean {
    public String FAUXPTYID;
    public String FNUMBER;
    @Generated(hash = 1194292872)
    public AuxSignSecCheckBean(String FAUXPTYID, String FNUMBER) {
        this.FAUXPTYID = FAUXPTYID;
        this.FNUMBER = FNUMBER;
    }
    @Generated(hash = 549343855)
    public AuxSignSecCheckBean() {
    }
    public String getFAUXPTYID() {
        return this.FAUXPTYID;
    }
    public void setFAUXPTYID(String FAUXPTYID) {
        this.FAUXPTYID = FAUXPTYID;
    }
    public String getFNUMBER() {
        return this.FNUMBER;
    }
    public void setFNUMBER(String FNUMBER) {
        this.FNUMBER = FNUMBER;
    }
}
