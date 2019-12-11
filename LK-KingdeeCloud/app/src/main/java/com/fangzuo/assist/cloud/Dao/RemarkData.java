package com.fangzuo.assist.cloud.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by NB on 2017/7/26.
 */
@Entity
public class RemarkData {
    public String FNUMBER;
    public String FUSEORGID;
    public String FNAME;
    public String FSHORTNAME;
    @Generated(hash = 1636602954)
    public RemarkData(String FNUMBER, String FUSEORGID, String FNAME,
            String FSHORTNAME) {
        this.FNUMBER = FNUMBER;
        this.FUSEORGID = FUSEORGID;
        this.FNAME = FNAME;
        this.FSHORTNAME = FSHORTNAME;
    }
    @Generated(hash = 202219444)
    public RemarkData() {
    }
    public String getFNUMBER() {
        return this.FNUMBER;
    }
    public void setFNUMBER(String FNUMBER) {
        this.FNUMBER = FNUMBER;
    }
    public String getFUSEORGID() {
        return this.FUSEORGID;
    }
    public void setFUSEORGID(String FUSEORGID) {
        this.FUSEORGID = FUSEORGID;
    }
    public String getFNAME() {
        return this.FNAME;
    }
    public void setFNAME(String FNAME) {
        this.FNAME = FNAME;
    }
    public String getFSHORTNAME() {
        return this.FSHORTNAME;
    }
    public void setFSHORTNAME(String FSHORTNAME) {
        this.FSHORTNAME = FSHORTNAME;
    }
}
