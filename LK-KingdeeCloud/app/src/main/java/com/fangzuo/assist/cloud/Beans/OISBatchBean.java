package com.fangzuo.assist.cloud.Beans;

import java.util.List;

public class OISBatchBean {

    /**
     * NumberSearch : True
     * ValidateFlag : True
     * IsDeleteEntry : True
     * IsEntryBatchFill : True
     * NeedUpDateFields : []
     * NeedReturnFields : []
     * SubSystemId :
     * InterationFlags :
     * Model : []
     * BatchCount : 0
     */

    private boolean NumberSearch;
    private boolean ValidateFlag;
    private boolean IsDeleteEntry;
    private boolean IsEntryBatchFill;
    private String SubSystemId;
    private String InterationFlags;
    private String BatchCount;
    private List<?> NeedUpDateFields;
    private List<?> NeedReturnFields;
    private List<OtherInBean.ModelBean> Model;

    public boolean isNumberSearch() {
        return NumberSearch;
    }

    public void setNumberSearch(boolean NumberSearch) {
        this.NumberSearch = NumberSearch;
    }

    public boolean isValidateFlag() {
        return ValidateFlag;
    }

    public void setValidateFlag(boolean ValidateFlag) {
        this.ValidateFlag = ValidateFlag;
    }

    public boolean isIsDeleteEntry() {
        return IsDeleteEntry;
    }

    public void setIsDeleteEntry(boolean IsDeleteEntry) {
        this.IsDeleteEntry = IsDeleteEntry;
    }

    public boolean isIsEntryBatchFill() {
        return IsEntryBatchFill;
    }

    public void setIsEntryBatchFill(boolean IsEntryBatchFill) {
        this.IsEntryBatchFill = IsEntryBatchFill;
    }

    public String getSubSystemId() {
        return SubSystemId;
    }

    public void setSubSystemId(String SubSystemId) {
        this.SubSystemId = SubSystemId;
    }

    public String getInterationFlags() {
        return InterationFlags;
    }

    public void setInterationFlags(String InterationFlags) {
        this.InterationFlags = InterationFlags;
    }

    public String getBatchCount() {
        return BatchCount;
    }

    public void setBatchCount(String BatchCount) {
        this.BatchCount = BatchCount;
    }

    public List<?> getNeedUpDateFields() {
        return NeedUpDateFields;
    }

    public void setNeedUpDateFields(List<?> NeedUpDateFields) {
        this.NeedUpDateFields = NeedUpDateFields;
    }

    public List<?> getNeedReturnFields() {
        return NeedReturnFields;
    }

    public void setNeedReturnFields(List<?> NeedReturnFields) {
        this.NeedReturnFields = NeedReturnFields;
    }

    public List<OtherInBean.ModelBean> getModel() {
        return Model;
    }

    public void setModel(List<OtherInBean.ModelBean> Model) {
        this.Model = Model;
    }

}
