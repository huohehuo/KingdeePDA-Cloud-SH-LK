package com.fangzuo.assist.cloud.Beans;


import com.fangzuo.assist.cloud.Dao.BatchDataBean;
import com.fangzuo.assist.cloud.Dao.Bibie;
import com.fangzuo.assist.cloud.Dao.Buyer;
import com.fangzuo.assist.cloud.Dao.Client;
import com.fangzuo.assist.cloud.Dao.Department;
import com.fangzuo.assist.cloud.Dao.DryingGetData;
import com.fangzuo.assist.cloud.Dao.Employee;
import com.fangzuo.assist.cloud.Dao.GetGoodsDepartment;
import com.fangzuo.assist.cloud.Dao.InStorageNum;
import com.fangzuo.assist.cloud.Dao.InStoreType;
import com.fangzuo.assist.cloud.Dao.Org;
import com.fangzuo.assist.cloud.Dao.PGetData;
import com.fangzuo.assist.cloud.Dao.PayType;
import com.fangzuo.assist.cloud.Dao.PriceMethod;
import com.fangzuo.assist.cloud.Dao.Product;
import com.fangzuo.assist.cloud.Dao.PurchaseMethod;
import com.fangzuo.assist.cloud.Dao.RemarkData;
import com.fangzuo.assist.cloud.Dao.SaleMan;
import com.fangzuo.assist.cloud.Dao.Storage;
import com.fangzuo.assist.cloud.Dao.StoreMan;
import com.fangzuo.assist.cloud.Dao.Suppliers;
import com.fangzuo.assist.cloud.Dao.Unit;
import com.fangzuo.assist.cloud.Dao.Wanglaikemu;
import com.fangzuo.assist.cloud.Dao.WaveHouse;
import com.fangzuo.assist.cloud.Dao.YuandanType;

import java.util.ArrayList;

public class DownloadReturnBean {
	public ArrayList<Bibie> bibiezhongs;//�ұ��
	public ArrayList<Department> department;//���ű�
	public ArrayList<Employee> employee;//ְԱ��
	public ArrayList<WaveHouse> wavehouse;//��λ��
	public ArrayList<InStorageNum> InstorageNum;//����
	public ArrayList<Storage> storage;//�ֿ��
	public ArrayList<Unit> units;
	public ArrayList<com.fangzuo.assist.cloud.Dao.BarCode> BarCode;//�����
	public ArrayList<Suppliers> suppliers;//��Ӧ�̱�
	public ArrayList<PayType> payTypes;//���㷽ʽ��
	public ArrayList<Product> products;//��Ʒ���ϱ�
	public ArrayList<com.fangzuo.assist.cloud.Dao.User> User;//�û���Ϣ��
	public ArrayList<Client> clients;//�ͻ���Ϣ��
	public ArrayList<GetGoodsDepartment> getGoodsDepartments;//������λ
	public ArrayList<PurchaseMethod> purchaseMethod;//����/�ɹ���ʽ��
	public ArrayList<YuandanType> yuandanTypes;//Դ������
	public ArrayList<Wanglaikemu> wanglaikemu;//������Ŀ
	public ArrayList<PriceMethod> priceMethods;//�۸�����
	public ArrayList<InStoreType> inStorageTypes;

	public ArrayList<GetBatchNoBean> getBatchNoBeans;
	public ArrayList<Buyer> buyers;
	public ArrayList<StoreMan> storeMans;
	public ArrayList<SaleMan> saleMans;
	public ArrayList<Org> orgs;
	public ArrayList<CodeCheckBackDataBean> codeCheckBackDataBeans;
	public ArrayList<AuxSignSecCheckBean> auxSignSecCheckBeans;
	public ArrayList<PrintDataBean> printDataBeans;
	public ArrayList<PrintHistory> printHistories;
	public ArrayList<RemarkData> remarkDatas;
	public ArrayList<WortPrintData> wortPrintDatas;
	public ArrayList<DryingGetData> dryingGetDatas;
	public ArrayList<PGetData> pGetDatas;
	public ArrayList<BatchDataBean> batchDataBeans;



	public int size;

	

//	public class bibiezhong{
//		public String FCurrencyID;
//		public String FNumber;
//		public String FName;
//		public String FExChangeRate;
//		public String fClassTypeId;
//	}
//
//	public class department{
//		public String  FItemID ;
//		public String FNumber ;
//		public String FName ;
//		public String FparentID ;
//	}
//
//	public class employee{
//		public String  FItemID ;
//		public String FNumber ;
//		public String FName;
//		public String FDpartmentID ;
//		public String FEmpGroup ;
//		public String FEmpGroupID ;
//	}
//
//	public class wavehouse{
//		public String FSPID;
//		public String FSPGroupID;
//		public String FNumber;
//		public String FName;
//		public String FFullName;
//		public String FLevel;
//		public String FDetail;
//		public String FParentID;
//		public String FClassTypeID;
//		public String FDefaultSPID;
//
//	}
//
//	public class InstorageNum{
//		public String  FItemID ;
//		public String  FStockID ;
//		public String  FQty;
//		public String  FBal ;
//		public String  FStockPlaceID ;
//		public String  FKFPeriod ;
//		public String  FKFDate ;
//		public String  FBatchNo ;
//	}
//
//	public class storage{
//		public String FItemID;
//		public String FEmpID;
//		public String FName;
//		public String FNumber;
//		public String FTypeID;
//		public String FSPGroupID;
//		public String FGroupID;
//		public String FStockGroupID;
//		public String FIsStockMgr;
//		public String FUnderStock;
//	}
//
//
//	public class Unit{
//		public String FMeasureUnitID;
//		public String FUnitGroupID;
//		public String FNumber;
//		public String FName;
//		public String FCoefficient;
//	}
//
//	public class BarCode{
//		public String FName;
//		public String FTypeID;
//		public String FItemID;
//		public String FBarCode;
//		public String FNumber;
//		public String FUnitID;
//	}
//
//	public class suppliers{
//		public String FItemID ;
//		public String FItemClassID;
//		public String FNumber;
//		public String FParentID;
//		public String FLevel;
//		public String FDetail;
//		public String FName;
//		public String FAddress;
//		public String FPhone;
//		public String FEmail;
//	}
//
//	public class payType{
//		public String FItemID;
//		public String FName;
//		public String FNumber;
//	}
//
//
//	public class product{
//		public String FItemID;
//		public String FNumber;
//		public String FModel;
//		public String FName;
//		public String FFullName;
//		public String FUnitID;
//		public String FUnitGroupID;
//		public String FDefaultLoc;
//		public String FProfitRate;
//		public String FTaxRate;
//		public String FOrderPrice;
//		public String FSalePrice;
//		public String FPlanPrice;
//		public String FBarcode;
//		public String FSPID;
//		public String FBatchManager;
//	}
//
//	public  class User{
//		public String FUserID;
//		public String FName;
//		public String FPassWord;
//		public String FEmpID;
//		public String FGroupName;
//		public String FPermit;
//	}
//
//	public class Client{
//		public String FItemID ;
//		public String FItemClassID;
//		public String FNumber;
//		public String FParentID;
//		public String FLevel;
//		public String FDetail;
//		public String FName;
//		public String FAddress;
//		public String FPhone;
//		public String FEmail;
//		public String FTypeID;
//	}
//
//	public class GetGoodsDepartment{
//		public String FItemID ;
//		public String FDeleted ;
//		public String FNumber;
//		public String FName;
//		public String FDetail;
//	}
//
//	public class purchaseMethod{
//		public String FTypeID;
//		public String FItemID;
//		public String FNumber;
//		public String FName;
//	}
//	public class yuandanType{
//		public String FID;
//		public String FName_CHS;
//	}
//
//
//	public class wanglaikemu{
//		public String FAccountID;
//		public String FNumber;
//		public String FFullName;
//	}
//
//	public class PriceMethod{
//		public String FInterID;
//		public String FEntryID;
//		public String FPri;
//		public String FPrice;
//		public String FName;
//		public String FItemID;
//		public String FUnitID;
//		public String FRelatedID;
//		public String FBegQty;
//		public String FEndQty;
//		public String FBegDate;
//		public String FEndDate;
//	}
	

}
