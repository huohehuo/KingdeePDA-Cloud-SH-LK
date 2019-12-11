package Bean;


import java.util.ArrayList;

/**
 * Created by NB on 2017/8/23.
 */
public class PushDownListReturnBean {
    public ArrayList<PushDownListBean> list;
    public class PushDownListBean{
        public String FID;
        public String FBillNo;
        public String FAccountID;
        public String FDate;
        public String FSupplyID;
        public String FSupply;

        public String FName;
        public String FSaleOrgID;
        public String FStoreOrgID;
        public String FSettleOrgId;
        public String FSaleManID;
        public String FSaleDeptID;
        public String FNot;
        public String FDept;
        public String FClient;
        public String FClientID;
        public String FDeptID;
        public String FManagerID;
        public String FEmpID;
        public String FInterID;

        public String FAppOrgID;
        public String FHuozhuInType;
        public String FHuozhuOutType;
        public String FDbType;

        public int tag;

        public String FBillTypeName;

        public String FOrderNo;
        public String FWordShopID;
        public String FWordShop;
        public String FStoreManID;
        public String FStockerGroupID;
        public String FCreateOrgID;
        public String FHuoZhuID;


        public String FWlCompany;
        public String FCarBoxNo;
        public String FPassNo;
        public String FFreight;
        public String FYaoNo;            //

        public String FFieldMan;            //

        @Override
        public String toString() {
            return "PushDownListBean{" +
                    "FBillNo='" + FBillNo + '\'' +
                    ", FName='" + FName + '\'' +
                    ", FDate='" + FDate + '\'' +
                    ", FSupply='" + FSupply + '\'' +
                    ", FSupplyID='" + FSupplyID + '\'' +
                    ", FDeptID='" + FDeptID + '\'' +
                    ", FManagerID='" + FManagerID + '\'' +
                    ", FEmpID='" + FEmpID + '\'' +
                    ", FInterID='" + FInterID + '\'' +
                    ", tag=" + tag +
                    '}';
        }
    }
}
