package com.fangzuo.assist.cloud.Beans;

public class InStoreNumBean {
	public String FItemID;
	public String FStockID;
	public String FStockPlaceID;
	public String FBatchNo;
	public String FKFDate;
	public String FKFPeriod;
	public String FOrgID;//组织id
	public String FOwnerID;//货主id
	public String FType;//组织id
	public InStoreNumBean() {
	}
	public InStoreNumBean(String FItemID, String FStockID, String FStockPlaceID, String FBatchNo) {
		this.FItemID = FItemID;
		this.FStockID = FStockID;
		this.FStockPlaceID = FStockPlaceID;
		this.FBatchNo = FBatchNo;
	}
	public InStoreNumBean(String FItemID, String FStockID, String FStockPlaceID, String FBatchNo,String orgid,String huozhuid) {
		this.FItemID = FItemID;
		this.FStockID = FStockID;
		this.FStockPlaceID = FStockPlaceID;
		this.FBatchNo = FBatchNo;
		this.FOrgID = orgid;
		this.FOwnerID = huozhuid;
	}
	public InStoreNumBean(String FItemID, String FStockID, String FStockPlaceID, String FBatchNo,String orgid,String huozhuid,String createdate) {
		this.FItemID = FItemID;
		this.FStockID = FStockID;
		this.FStockPlaceID = FStockPlaceID;
		this.FBatchNo = FBatchNo;
		this.FOrgID = orgid;
		this.FOwnerID = huozhuid;
		this.FOwnerID = huozhuid;
		this.FKFDate = createdate;
	}
}
