package com.fangzuo.assist.cloud.Beans;

import com.fangzuo.assist.cloud.Dao.Department;
import com.fangzuo.assist.cloud.Dao.Org;

public class MainSaveBean {
	public String FActivity;
	public String FOrderId;
	public String FAccountID;
	public String FBarcode;
	public String FNot;
	public String FOrderNo;
	public String FBillerID;

	public Department FDepartment;
	public String FMan1;
	public String FMan2;
	public String FMan3;
	public Org FOrgOut;
	public Org FOrgIn;
	public Org FHuozhuOut;
	public Org FHuozhuIn;

	public MainSaveBean() {
		this.FDepartment = null;
		this.FMan1 = null;
		this.FMan2 = null;
		this.FMan3 = null;
		this.FOrgOut = null;
		this.FOrgIn = null;
		this.FHuozhuOut = null;
		this.FHuozhuIn = null;
	}
}
