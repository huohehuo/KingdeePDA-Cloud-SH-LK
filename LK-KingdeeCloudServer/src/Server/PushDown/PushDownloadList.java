package Server.PushDown;

import Bean.DownLoadSubListBean;
import Bean.PushDownDLBean;
import Bean.PushDownRKBean;
import Utils.CommonJson;
import Utils.JDBCUtil;
import Utils.Lg;
import Utils.MathUtil;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



//获取单据的订单信息
@WebServlet("/PushDownloadList")
public class PushDownloadList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        Connection conn = null;
        PreparedStatement sta = null;
        ResultSet rs = null;
        ArrayList<PushDownDLBean.DLbean> container = new ArrayList<>();
        ArrayList<PushDownRKBean.Rkbean> containerRk = new ArrayList<>();
        String json = request.getParameter("json");
        DownLoadSubListBean dBean = new Gson().fromJson(json, DownLoadSubListBean.class);
        String SQL = "";
        System.out.println("获得数据:" + json);
        System.out.println("获取tag:" + dBean.tag);
        switch (dBean.tag) {
            case 1://采购订单下推外购入库单
            case 34://采购订单下推委外入库单
            case 32://方料入库
//                SQL =   "select '' as 货主编码,t3.FUNITID as 单位ID,st31.FNUMBER as 物料编码,st33.FName as 物料名称,st33.FSPECIFICATION as 规格型号,t0.FBillNo as 订单编号,convert(float,(t3.FSALQTY-t3_R.FJOINQTY)) as 订单数量,0 as 已验数量,t3_F.FTAXPRICE as 含税单价,t3.FMATERIALID as 物料ID,t3.FENTRYID as 明细唯一值,t3.FID as 明细内码,t3.FSEQ as 明细序号 from t_PUR_POOrder t0   LEFT OUTER JOIN t_PUR_POOrderEntry t3 ON t0.FID = t3.FID   LEFT OUTER JOIN t_PUR_POOrderEntry_D t3_D ON t3.FENTRYID = t3_D.FENTRYID  LEFT OUTER JOIN t_PUR_POOrderEntry_F  t3_F ON   t3.FENTRYID = t3_F.FENTRYID LEFT OUTER JOIN T_BD_MATERIAL st31 ON t3.FMATERIALID = st31.FMATERIALID left join T_BD_MATERIAL_L st33 ON t3.FMATERIALID = st33.FMATERIALID LEFT OUTER JOIN t_PUR_POOrderEntry_R t3_R ON t3.FENTRYID = t3_R.FENTRYID   where t0.FOBJECTTYPEID = 'PUR_PurchaseOrder' and t0.FDOCUMENTSTATUS = 'C' AND t0.FCANCELSTATUS = 'A' AND t0.FCLOSESTATUS = 'A' AND t3.FMRPFREEZESTATUS = 'A' AND t3.FMRPTERMINATESTATUS = 'A' AND t3.FMRPCLOSESTATUS = 'A' AND t3.FCHANGEFLAG <> 'I'  AND (t3_D.FBASEDELIVERYMAXQTY > t3_R.FBASESTOCKINQTY) AND (t3_D.FBASEDELIVERYMAXQTY > t3_R.FBASEJOINQTY)  and t0.FBillNo= '" + dBean.interID+"'";
                SQL =   "select t3_F.FTAXRATE as 税率,t3_D.FREQUIREORGID as 需求组织ID,t_PRICEUNITID.FNumber as 计价单位编码,'' as 货主编码,t3.FUNITID as 单位ID,st31.FNUMBER as 物料编码,st33.FName as 物料名称,st33.FSPECIFICATION as 规格型号,t0.FBillNo as 订单编号,convert(float,(t3.FSALQTY-t3_R.FJOINQTY)) as 订单数量,0 as 已验数量,t3_F.FTAXPRICE as 含税单价,t3.FMATERIALID as 物料ID,t3.FENTRYID as 明细唯一值,t3.FID as 明细内码,t3.FSEQ as 明细序号 from t_PUR_POOrder t0   LEFT OUTER JOIN t_PUR_POOrderEntry t3 ON t0.FID = t3.FID   LEFT OUTER JOIN t_PUR_POOrderEntry_D t3_D ON t3.FENTRYID = t3_D.FENTRYID  LEFT OUTER JOIN t_PUR_POOrderEntry_F  t3_F ON   t3.FENTRYID = t3_F.FENTRYID LEFT OUTER JOIN T_BD_MATERIAL st31 ON t3.FMATERIALID = st31.FMATERIALID left join T_BD_MATERIAL_L st33 ON t3.FMATERIALID = st33.FMATERIALID and st33.FLOCALEID = 2052 LEFT OUTER JOIN t_PUR_POOrderEntry_R t3_R ON t3.FENTRYID = t3_R.FENTRYID left join T_BD_UNIT t_PRICEUNITID on t_PRICEUNITID.FUnitID =t3_F.FPRICEUNITID   where t0.FOBJECTTYPEID = 'PUR_PurchaseOrder' and t0.FDOCUMENTSTATUS = 'C' AND t0.FCANCELSTATUS = 'A' AND t0.FCLOSESTATUS = 'A' AND t3.FMRPFREEZESTATUS = 'A' AND t3.FMRPTERMINATESTATUS = 'A' AND t3.FMRPCLOSESTATUS = 'A' AND t3.FCHANGEFLAG <> 'I'  AND (t3_D.FBASEDELIVERYMAXQTY > t3_R.FBASESTOCKINQTY) AND (t3_D.FBASEDELIVERYMAXQTY > t3_R.FBASEJOINQTY)  and t0.FBillNo=  '" + dBean.interID+"'";
                        break;
            case 2://销售订单下推销售出库单
                SQL =   "select  convert(decimal(18,0), t3.F_ORA_QTY) as 辅助数量,isnull( convert(varchar(25), t3.F_ORA_DATE,23),'') as 生产日期,t3_F.FISFREE as 是否赠品 ,t1008.FNumber as 货主编码,t3.FUNITID as 单位ID, st31.FNUMBER as 物料编码,st33.FName as 物料名称,st33.FSPECIFICATION as 规格型号,t0.FBillNo as 订单编号,convert(float,(t2_R.FCANOUTQTY + (t3_D.FDELIVERYMAXQTY - t3.FQTY))) as 订单数量,0 as 已验数量,t3_F.FTAXPRICE as 含税单价,t3.FMATERIALID as 物料ID,t3.FENTRYID as 明细唯一值,t3.FID as 明细内码,t3.FSEQ as 明细序号 from T_SAL_ORDER t0   LEFT OUTER JOIN T_SAL_ORDEREntry t3 ON t0.FID = t3.FID   LEFT OUTER JOIN T_SAL_ORDEREntry_D t3_D ON t3.FENTRYID = t3_D.FENTRYID  LEFT OUTER JOIN T_SAL_ORDEREntry_F  t3_F ON   t3.FENTRYID = t3_F.FENTRYID  LEFT OUTER JOIN T_SAL_ORDERENTRY_R t2_R ON t3.FENTRYID = t2_R.FENTRYID LEFT OUTER JOIN T_BD_MATERIAL st31 ON t3.FMATERIALID = st31.FMATERIALID left join T_BD_MATERIAL_L st33 ON t3.FMATERIALID = st33.FMATERIALID and st33.FLOCALEID = 2052 LEFT OUTER JOIN T_SAL_ORDEREntry_R t3_R ON t3.FENTRYID = t3_R.FENTRYID left join T_ORG_Organizations t1008 on t1008.FORGID =t3.FOWNERID LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st011 ON t3.FAUXPROPID = st011.FID  LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st018 ON t3.FAUXPROPID = st018.FID   where t0.FOBJECTTYPEID = 'SAL_SaleOrder' and t0.FDOCUMENTSTATUS = 'C' AND t0.FCANCELSTATUS = 'A' AND t0.FCLOSESTATUS = 'A' AND t3.FMRPFREEZESTATUS = 'A' AND t3.FMRPTERMINATESTATUS = 'A' AND t3.FMRPCLOSESTATUS = 'A' AND t3.FCHANGEFLAG <> 'I'  AND ((t2_R.FBASECANOUTQTY + (t3_D.FBASEDELIVERYMAXQTY - t3.FBASEUNITQTY)) > 0 OR (t2_R.FBASECANOUTQTY < 0))  and t0.FBillNo='" + dBean.interID+"'";
                break;

            case 31://销售订单下推销售出库单
//                SQL =   "select  t3.FUNITID as 单位ID, st31.FNUMBER as 物料编码,st33.FName as 物料名称,st33.FSPECIFICATION as 规格型号,t0.FBillNo as 订单编号,convert(float,(t2_R.FCANOUTQTY + (t3_D.FDELIVERYMAXQTY - t3.FQTY))) as 订单数量,0 as 已验数量,t3_F.FTAXPRICE as 含税单价,t3.FMATERIALID as 物料ID,t3.FENTRYID as 明细唯一值,t3.FID as 明细内码,t3.FSEQ as 明细序号 from T_SAL_ORDER t0   LEFT OUTER JOIN T_SAL_ORDEREntry t3 ON t0.FID = t3.FID   LEFT OUTER JOIN T_SAL_ORDEREntry_D t3_D ON t3.FENTRYID = t3_D.FENTRYID  LEFT OUTER JOIN T_SAL_ORDEREntry_F  t3_F ON   t3.FENTRYID = t3_F.FENTRYID  LEFT OUTER JOIN T_SAL_ORDERENTRY_R t2_R ON t3.FENTRYID = t2_R.FENTRYID LEFT OUTER JOIN T_BD_MATERIAL st31 ON t3.FMATERIALID = st31.FMATERIALID left join T_BD_MATERIAL_L st33 ON t3.FMATERIALID = st33.FMATERIALID LEFT OUTER JOIN T_SAL_ORDEREntry_R t3_R ON t3.FENTRYID = t3_R.FENTRYID where t0.FOBJECTTYPEID = 'SAL_SaleOrder' and t0.FDOCUMENTSTATUS = 'C' AND t0.FCANCELSTATUS = 'A' AND t0.FCLOSESTATUS = 'A' AND t3.FMRPFREEZESTATUS = 'A' AND t3.FMRPTERMINATESTATUS = 'A' AND t3.FMRPCLOSESTATUS = 'A' AND t3.FCHANGEFLAG <> 'I'  AND ((t2_R.FBASECANOUTQTY + (t3_D.FBASEDELIVERYMAXQTY - t3.FBASEUNITQTY)) > 0 OR (t2_R.FBASECANOUTQTY < 0))  and t0.FBillNo='" + dBean.interID+"'";
//                SQL =   "select  t1008.FNumber as 货主编码,t3.FUNITID as 单位ID, st31.FNUMBER as 物料编码,st33.FName as 物料名称,st33.FSPECIFICATION as 规格型号,t0.FBillNo as 订单编号,convert(float,(t2_R.FCANOUTQTY + (t3_D.FDELIVERYMAXQTY - t3.FQTY))) as 订单数量,0 as 已验数量,t3_F.FTAXPRICE as 含税单价,t3.FMATERIALID as 物料ID,t3.FENTRYID as 明细唯一值,t3.FID as 明细内码,t3.FSEQ as 明细序号 from T_SAL_ORDER t0   LEFT OUTER JOIN T_SAL_ORDEREntry t3 ON t0.FID = t3.FID   LEFT OUTER JOIN T_SAL_ORDEREntry_D t3_D ON t3.FENTRYID = t3_D.FENTRYID  LEFT OUTER JOIN T_SAL_ORDEREntry_F  t3_F ON   t3.FENTRYID = t3_F.FENTRYID  LEFT OUTER JOIN T_SAL_ORDERENTRY_R t2_R ON t3.FENTRYID = t2_R.FENTRYID LEFT OUTER JOIN T_BD_MATERIAL st31 ON t3.FMATERIALID = st31.FMATERIALID left join T_BD_MATERIAL_L st33 ON t3.FMATERIALID = st33.FMATERIALID LEFT OUTER JOIN T_SAL_ORDEREntry_R t3_R ON t3.FENTRYID = t3_R.FENTRYID left join T_ORG_Organizations t1008 on t1008.FORGID =t3.FOWNERID where t0.FOBJECTTYPEID = 'SAL_SaleOrder' and t0.FDOCUMENTSTATUS = 'C' AND t0.FCANCELSTATUS = 'A' AND t0.FCLOSESTATUS = 'A' AND t3.FMRPFREEZESTATUS = 'A' AND t3.FMRPTERMINATESTATUS = 'A' AND t3.FMRPCLOSESTATUS = 'A' AND t3.FCHANGEFLAG <> 'I'  AND ((t2_R.FBASECANOUTQTY + (t3_D.FBASEDELIVERYMAXQTY - t3.FBASEUNITQTY)) > 0 OR (t2_R.FBASECANOUTQTY < 0))  and t0.FBillNo='" + dBean.interID+"'";
                SQL =   "select  t3_F.FISFREE as 是否赠品,st019.FNumber as 辅助标识,st017.FNumber as 实际规格,t1008.FNumber as 货主编码,t3.FUNITID as 单位ID, st31.FNUMBER as 物料编码,st33.FName as 物料名称,st33.FSPECIFICATION as 规格型号,t0.FBillNo as 订单编号,convert(float,(t2_R.FCANOUTQTY + (t3_D.FDELIVERYMAXQTY - t3.FQTY))) as 订单数量,0 as 已验数量,t3_F.FTAXPRICE as 含税单价,t3.FMATERIALID as 物料ID,t3.FENTRYID as 明细唯一值,t3.FID as 明细内码,t3.FSEQ as 明细序号 from T_SAL_ORDER t0   LEFT OUTER JOIN T_SAL_ORDEREntry t3 ON t0.FID = t3.FID   LEFT OUTER JOIN T_SAL_ORDEREntry_D t3_D ON t3.FENTRYID = t3_D.FENTRYID  LEFT OUTER JOIN T_SAL_ORDEREntry_F  t3_F ON   t3.FENTRYID = t3_F.FENTRYID  LEFT OUTER JOIN T_SAL_ORDERENTRY_R t2_R ON t3.FENTRYID = t2_R.FENTRYID LEFT OUTER JOIN T_BD_MATERIAL st31 ON t3.FMATERIALID = st31.FMATERIALID left join T_BD_MATERIAL_L st33 ON t3.FMATERIALID = st33.FMATERIALID LEFT OUTER JOIN T_SAL_ORDEREntry_R t3_R ON t3.FENTRYID = t3_R.FENTRYID left join T_ORG_Organizations t1008 on t1008.FORGID =t3.FOWNERID" +
                        " LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st011 ON t3.FAUXPROPID = st011.FID LEFT OUTER JOIN T_BAS_ASSISTANTDATAENTRY st017 ON st011.FF100001 = st017.FEntryId LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st018 ON t3.FAUXPROPID = st018.FID LEFT OUTER JOIN T_BAS_ASSISTANTDATAENTRY st019 ON st018.FF100002 = st019.FEntryId where t0.FOBJECTTYPEID = 'SAL_SaleOrder' and t0.FDOCUMENTSTATUS = 'C' AND t0.FCANCELSTATUS = 'A' AND t0.FCLOSESTATUS = 'A' AND t3.FMRPFREEZESTATUS = 'A' AND t3.FMRPTERMINATESTATUS = 'A' AND t3.FMRPCLOSESTATUS = 'A' AND t3.FCHANGEFLAG <> 'I'  AND ((t2_R.FBASECANOUTQTY + (t3_D.FBASEDELIVERYMAXQTY - t3.FBASEUNITQTY)) > 0 OR (t2_R.FBASECANOUTQTY < 0))  and t0.FBillNo='" + dBean.interID+"'";
                        break;
            case 21://销售订单下推销售出库单
                SQL =   "select  t3_F.FISFREE as 是否赠品,st019.FNumber as 辅助标识,st017.FNumber as 实际规格,t1008.FNumber as 货主编码,t3.FUNITID as 单位ID, st31.FNUMBER as 物料编码,st33.FName as 物料名称,st33.FSPECIFICATION as 规格型号,t0.FBillNo as 订单编号,convert(float,(t2_R.FCANOUTQTY + (t3_D.FDELIVERYMAXQTY - t3.FQTY))) as 订单数量,0 as 已验数量,t3_F.FTAXPRICE as 含税单价,t3.FMATERIALID as 物料ID,t3.FENTRYID as 明细唯一值,t3.FID as 明细内码,t3.FSEQ as 明细序号 from T_SAL_ORDER t0   LEFT OUTER JOIN T_SAL_ORDEREntry t3 ON t0.FID = t3.FID   LEFT OUTER JOIN T_SAL_ORDEREntry_D t3_D ON t3.FENTRYID = t3_D.FENTRYID  LEFT OUTER JOIN T_SAL_ORDEREntry_F  t3_F ON   t3.FENTRYID = t3_F.FENTRYID  LEFT OUTER JOIN T_SAL_ORDERENTRY_R t2_R ON t3.FENTRYID = t2_R.FENTRYID LEFT OUTER JOIN T_BD_MATERIAL st31 ON t3.FMATERIALID = st31.FMATERIALID left join T_BD_MATERIAL_L st33 ON t3.FMATERIALID = st33.FMATERIALID LEFT OUTER JOIN T_SAL_ORDEREntry_R t3_R ON t3.FENTRYID = t3_R.FENTRYID left join T_ORG_Organizations t1008 on t1008.FORGID =t3.FOWNERID" +
                        " LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st011 ON t3.FAUXPROPID = st011.FID LEFT OUTER JOIN T_BAS_ASSISTANTDATAENTRY st017 ON st011.FF100001 = st017.FEntryId LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st018 ON t3.FAUXPROPID = st018.FID LEFT OUTER JOIN T_BAS_ASSISTANTDATAENTRY st019 ON st018.FF100002 = st019.FEntryId where t0.FOBJECTTYPEID = 'SAL_SaleOrder' and t0.FDOCUMENTSTATUS = 'C' AND t0.FCANCELSTATUS = 'A' AND t0.FCLOSESTATUS = 'A' AND t3.FMRPFREEZESTATUS = 'A' AND t3.FMRPTERMINATESTATUS = 'A' AND t3.FMRPCLOSESTATUS = 'A' AND t3.FCHANGEFLAG <> 'I'  AND ((t2_R.FBASECANOUTQTY + (t3_D.FBASEDELIVERYMAXQTY - t3.FBASEUNITQTY)) > 0 OR (t2_R.FBASECANOUTQTY < 0))  and t0.FBillNo='" + dBean.interID+"'";
                break;
            case 3://销售订单下推销售退货单
                SQL =   "select  t2_R.FBASECANRETURNQTY,t3.FUNITID 单位ID, st31.FNUMBER as 物料编码,st33.FName as 物料名称,st33.FSPECIFICATION as 规格型号,t0.FBillNo as 订单编号,ABS(t2_R.FBASECANRETURNQTY) as 订单数量,0 as 已验数量,t3_F.FTAXPRICE as 含税单价,t3.FMATERIALID as 物料ID,t3.FENTRYID as 明细唯一值,t3.FID as 明细内码,t3.FSEQ as 明细序号 from T_SAL_ORDER t0   LEFT OUTER JOIN T_SAL_ORDEREntry t3 ON t0.FID = t3.FID   LEFT OUTER JOIN T_SAL_ORDEREntry_D t3_D ON t3.FENTRYID = t3_D.FENTRYID  LEFT OUTER JOIN T_SAL_ORDEREntry_F  t3_F ON   t3.FENTRYID = t3_F.FENTRYID  LEFT OUTER JOIN T_SAL_ORDERENTRY_R t2_R ON t3.FENTRYID = t2_R.FENTRYID LEFT OUTER JOIN T_BD_MATERIAL st31 ON t3.FMATERIALID = st31.FMATERIALID left join T_BD_MATERIAL_L st33 ON t3.FMATERIALID = st33.FMATERIALID LEFT OUTER JOIN T_SAL_ORDEREntry_R t3_R ON t3.FENTRYID = t3_R.FENTRYID where t0.FOBJECTTYPEID = 'SAL_SaleOrder' and t0.FDOCUMENTSTATUS = 'C' AND t0.FCANCELSTATUS = 'A' AND t3.FMRPFREEZESTATUS = 'A' AND t3.FMRPTERMINATESTATUS = 'A' and t3.FCHANGEFLAG <> N'I'  AND (t3.FCHANGEFLAG <> N'D') AND NOT EXISTS (SELECT 1 FROM T_BD_MATERIALQUALITY B WHERE (B.FCHECKRETURN = '1' AND B.FMATERIALID = t3.FMATERIALID)) and ABS(t2_R.FBASECANRETURNQTY) > 0 and t0.FBillNo='" + dBean.interID+"'";
                        break;
            case 4://销售出库单下推销售退货单
                SQL =   "select  t3.FSTOCKID as 仓库ID,t3.FLOT_TEXT as 批号,t3.FUNITID 单位ID, st31.FNUMBER as 物料编码,st33.FName as 物料名称,st33.FSPECIFICATION as 规格型号,t0.FBillNo as 订单编号,(0) as 订单数量,t3_F.FSALBASEQTY-t2_R.FBASERETURNQTY as 已验数量,t3_F.FTAXPRICE as 含税单价,t3.FMATERIALID as 物料ID,t3.FENTRYID as 明细唯一值,t3.FID as 明细内码,t3.FSEQ as 明细序号 from T_SAL_OUTSTOCK t0   LEFT OUTER JOIN T_SAL_OUTSTOCKEntry t3 ON t0.FID = t3.FID   LEFT OUTER JOIN T_SAL_OUTSTOCKFIN t1 ON t0.FID = t1.FID LEFT OUTER JOIN T_SAL_OUTSTOCKEntry_F  t3_F ON   t3.FENTRYID = t3_F.FENTRYID  LEFT OUTER JOIN T_SAL_OUTSTOCKENTRY_R t2_R ON t3.FENTRYID = t2_R.FENTRYID LEFT OUTER JOIN T_BD_MATERIAL st31 ON t3.FMATERIALID = st31.FMATERIALID left join T_BD_MATERIAL_L st33 ON t3.FMATERIALID = st33.FMATERIALID LEFT OUTER JOIN T_SAL_OUTSTOCKEntry_R t3_R ON t3.FENTRYID = t3_R.FENTRYID where t0.FOBJECTTYPEID = 'SAL_OUTSTOCK' and t0.FDOCUMENTSTATUS = 'C' AND t0.FCANCELSTATUS = 'A' AND t1.FISGENFORIOS = '0' and  ABS(t3_F.FSALBASEQTY) > ABS(t2_R.FBASERETURNQTY) and t0.FBillNo='" + dBean.interID+"'";
                        break;
            case 5://发货通知单下推销售出库单
                SQL = "select   t3.FUNITID 单位ID,st31.FNUMBER as 物料编码,st33.FName as 物料名称,st33.FSPECIFICATION as 规格型号,t0.FBillNo as 订单编号,t3.FBASEUNITQTY-FBASEJOINOUTQTY as 订单数量,0 as 已验数量,t3_F.FTAXPRICE as 含税单价,t3.FMATERIALID as 物料ID,t3.FENTRYID as 明细唯一值,t3.FID as 明细内码,t3.FSEQ as 明细序号 from T_SAL_DELIVERYNOTICE t0   LEFT OUTER JOIN T_SAL_DELIVERYNOTICEEntry t3 ON t0.FID = t3.FID   LEFT OUTER JOIN T_SAL_DELIVERYNOTICEEntry_E t3_E ON t3.FENTRYID = t3_E.FENTRYID  LEFT OUTER JOIN T_SAL_DELIVERYNOTICEEntry_F  t3_F ON   t3.FENTRYID = t3_F.FENTRYID   LEFT OUTER JOIN T_BD_MATERIAL st31 ON t3.FMATERIALID = st31.FMATERIALID left join T_BD_MATERIAL_L st33 ON t3.FMATERIALID = st33.FMATERIALID  where t0.FOBJECTTYPEID = 'SAL_DELIVERYNOTICE' and t0.FDOCUMENTSTATUS = 'C' AND t0.FCANCELSTATUS = 'A' AND t0.FCLOSESTATUS = 'A'  and  (FBUSINESSTYPE = 'NORMAL' OR FBUSINESSTYPE = 'DRPTRANS' )  AND  t3.FBASEUNITQTY-FBASEJOINOUTQTY>0 and t0.FBillNo='" + dBean.interID+"'";
                break;
            case 6://退货通知单下推销售退货单
                SQL =   "select   t1008.FNumber as 货主编码,t3.FUNITID 单位ID,st31.FNUMBER as 物料编码,st33.FName as 物料名称,st33.FSPECIFICATION as 规格型号,t0.FBillNo as 订单编号,t3.FQTY-FJOINRETQTY as 订单数量,0 as 已验数量,t3_F.FTAXPRICE as 含税单价,t3.FMATERIALID as 物料ID,t3.FENTRYID as 明细唯一值,t3.FID as 明细内码,t3.FSEQ as 明细序号 from T_SAL_RETURNNOTICE t0   LEFT OUTER JOIN T_SAL_RETURNNOTICEEntry t3 ON t0.FID = t3.FID   LEFT OUTER JOIN T_SAL_RETURNNOTICEEntry_E t3_E ON t3.FENTRYID = t3_E.FENTRYID  LEFT OUTER JOIN T_SAL_RETURNNOTICEEntry_F  t3_F ON   t3.FENTRYID = t3_F.FENTRYID   LEFT OUTER JOIN T_BD_MATERIAL st31 ON t3.FMATERIALID = st31.FMATERIALID left join T_BD_MATERIAL_L st33 ON t3.FMATERIALID = st33.FMATERIALID  left join T_ORG_Organizations t1008 on t1008.FORGID =t3.FOWNERID where t0.FOBJECTTYPEID = 'SAL_RETURNNOTICE' and t0.FDOCUMENTSTATUS = 'C' AND t0.FCANCELSTATUS = 'A' AND  t3.FBASEUNITQTY-FBASEJOINRETQTY>0 and t0.FBillNo='" + dBean.interID+"'";
                        break;
            case 7://调拨申请单下推分布式调入单
                SQL =   "select t11.FName as FStoctName,t12.FName as FSPName,FInterID,FBillNo,FWorkShop,t2.FName as " +
                        "FDepartmentName,t1.FItemID,t1.FUnitID,FPlanCommitDate,FPlanFinishDate,(FAuxQty-FAuxCommitQty) " +
                        "as FAuxQty,(FAuxQtyForItem+FAuxQtyScrap) as FAux,'0' as FQtying,'0' as FAuxPrice,t3.FName," +
                        "t3.FModel,0 as FEntryID,t3.FNumber from ICMO t1 left join t_Department t2 on t1.FWorkShop = " +
                        "t2.FItemID left join t_ICItem t3 on t1.FItemID=t3.FItemID left join t_Stock t11 on t11.FItemID = " +
                        "t3.FDefaultLoc left join t_StockPlace t12 on t3.FSPID = t12.FSPID where  FAuxQty-FAuxCommitQty>0 " +
                        "and FStatus in(1,2) and t1.FInterID=" + dBean.interID;
                         break;
            case 8://调拨申请单下推分布式调出单
                SQL =   "select  t3.FUNITID 单位ID, st31.FNUMBER as 物料编码,st33.FName as 物料编码,st33.FSPECIFICATION as 规格型号,t0.FBillNo as 订单编号, t3.FBASEQTY - t3_E.FNOTRANSOUTREBASEQTY+t3_E.FRETRANSOUTBASEQTY + t3_E.FNOTRANSINLOBASEQTY as 订单数量,0 as 已验数量,0 as 含税单价,t3.FMATERIALID as 物料ID,t3.FENTRYID as 明细唯一值,t3.FID as 明细内码,t3.FSEQ as 明细序号,t3.FSTOCKID as 调入仓库ID,t3.FSTOCKINID as 调出仓库ID, t3.FLOT_TEXT as 批号 from T_STK_STKTRANSFERAPP t0   LEFT OUTER JOIN T_STK_STKTRANSFERAPPENTRY t3 ON t0.FID = t3.FID   LEFT OUTER JOIN T_STK_STKTRANSFERAPPEntry_E t3_E ON t3.FENTRYID = t3_E.FENTRYID  LEFT OUTER JOIN T_BD_MATERIAL st31 ON t3.FMATERIALID = st31.FMATERIALID left join T_BD_MATERIAL_L st33 ON t3.FMATERIALID = st33.FMATERIALID  where t0.FOBJECTTYPEID = 'STK_TRANSFERAPPLY' and t0.FDOCUMENTSTATUS = 'C' AND t0.FCLOSESTATUS = 'A'  AND t3.FBUSINESSEND = 'A' AND t3.FBUSINESSCLOSE = 'A' and t3.FBASEQTY - t3_E.FNOTRANSOUTREBASEQTY+t3_E.FRETRANSOUTBASEQTY + t3_E.FNOTRANSINLOBASEQTY>0 and t0.FBillNo=" + dBean.interID;
                        break;
            case 22://调拨申请单下推分布式调出单
                SQL =   "select  t3.FUNITID 单位ID,t3.FSTOCKORGID as 调出组织ID,t3.FSTOCKORGINID as  调入组织ID ,t3.FOWNERID as 调出货主ID,t3.FOWNERINID as 调入货主ID ,st31.FNUMBER as 物料编码,st33.FName as 物料名称,st33.FSPECIFICATION as 规格型号,t0.FBillNo as 订单编号, convert(float,t3.FQTY+ ROUND( (t3_E.FRETRANSOUTBASEQTY + t3_E.FNOTRANSINLOBASEQTY  - t3_E.FNOTRANSOUTREBASEQTY)/isnull(t_R1.FCONVERTNUMERATOR,1)*isnull(t_R1.FCONVERTDENOMINATOR,1)/isnull(t_R3.FCONVERTDENOMINATOR,1)*isnull(t_R3.FCONVERTNUMERATOR,1),t100.FPRECISION)) as 订单数量,0 as 已验数量,0 as 含税单价,t3.FMATERIALID as 物料ID,t3.FENTRYID as 明细唯一值,t3.FID as 明细内码,t3.FSEQ as 明细序号,t3.FSTOCKID as 调入仓库ID,t3.FSTOCKINID as 调出仓库ID, t3.FLOT_TEXT as 批号 from T_STK_STKTRANSFERAPP t0   LEFT OUTER JOIN T_STK_STKTRANSFERAPPENTRY t3 ON t0.FID = t3.FID   LEFT OUTER JOIN T_STK_STKTRANSFERAPPEntry_E t3_E ON  t3.FENTRYID = t3_E.FENTRYID  LEFT OUTER JOIN T_BD_MATERIAL st31 ON t3.FMATERIALID = st31.FMATERIALID left join T_BD_MATERIAL_L st33 ON t3.FMATERIALID = st33.FMATERIALID left join t_BD_MaterialBase t2M on t3.FMATERIALID=t2M.fmaterialid left join T_BD_UNIT_L t6 on t6.FUnitID = t2M.FBASEUNITID left join  T_BD_UNITCONVERTRATE t_R1 on (t_R1.FMASTERID=st31.FMASTERID and t_R1.FCURRENTUNITID = t3.FUnitID)  left join  T_BD_UNITCONVERTRATE t_R3 on (t_R3.FMASTERID=st31.FMASTERID and t_R3.FCURRENTUNITID = t2M.FBASEUNITID) left join T_BD_UNIT t100 on t2M.FBASEUNITID=t100.FUNITID where t0.FOBJECTTYPEID = 'STK_TRANSFERAPPLY' and t0.FDOCUMENTSTATUS = 'C' AND t0.FCLOSESTATUS = 'A'  AND t3.FBUSINESSEND = 'A' AND t3.FBUSINESSCLOSE = 'A' and t3.FBASEQTY - t3_E.FNOTRANSOUTREBASEQTY+t3_E.FRETRANSOUTBASEQTY + t3_E.FNOTRANSINLOBASEQTY>0 and t0.FBillNo= '" + dBean.interID+"'";
                        break;
            case 23://VMI调拨申请单下推分布式调出单
                SQL =   "select  t3.FUNITID 单位ID,t3.FSTOCKORGID as 调出组织ID,t3.FSTOCKORGINID as  调入组织ID ,t3.FOWNERID as 调出货主ID,t3.FOWNERINID as 调入货主ID ,st31.FNUMBER as 物料编码,st33.FName as 物料名称,st33.FSPECIFICATION as 规格型号,t0.FBillNo as 订单编号, convert(float,t3.FQTY+ ROUND( (t3_E.FRETRANSOUTBASEQTY + t3_E.FNOTRANSINLOBASEQTY  - t3_E.FNOTRANSOUTREBASEQTY)/isnull(t_R1.FCONVERTNUMERATOR,1)*isnull(t_R1.FCONVERTDENOMINATOR,1)/isnull(t_R3.FCONVERTDENOMINATOR,1)*isnull(t_R3.FCONVERTNUMERATOR,1),t100.FPRECISION)) as 订单数量,0 as 已验数量,0 as 含税单价,t3.FMATERIALID as 物料ID,t3.FENTRYID as 明细唯一值,t3.FID as 明细内码,t3.FSEQ as 明细序号,t3.FSTOCKID as 调入仓库ID,t3.FSTOCKINID as 调出仓库ID, t3.FLOT_TEXT as 批号 from T_STK_STKTRANSFERAPP t0   LEFT OUTER JOIN T_STK_STKTRANSFERAPPENTRY t3 ON t0.FID = t3.FID   LEFT OUTER JOIN T_STK_STKTRANSFERAPPEntry_E t3_E ON  t3.FENTRYID = t3_E.FENTRYID  LEFT OUTER JOIN T_BD_MATERIAL st31 ON t3.FMATERIALID = st31.FMATERIALID left join T_BD_MATERIAL_L st33 ON t3.FMATERIALID = st33.FMATERIALID left join t_BD_MaterialBase t2M on t3.FMATERIALID=t2M.fmaterialid left join T_BD_UNIT_L t6 on t6.FUnitID = t2M.FBASEUNITID left join  T_BD_UNITCONVERTRATE t_R1 on (t_R1.FMASTERID=st31.FMASTERID and t_R1.FCURRENTUNITID = t3.FUnitID)  left join  T_BD_UNITCONVERTRATE t_R3 on (t_R3.FMASTERID=st31.FMASTERID and t_R3.FCURRENTUNITID = t2M.FBASEUNITID) left join T_BD_UNIT t100 on t2M.FBASEUNITID=t100.FUNITID where t0.FOBJECTTYPEID = 'STK_TRANSFERAPPLY' and t0.FDOCUMENTSTATUS = 'C' AND t0.FCLOSESTATUS = 'A'  AND t3.FBUSINESSEND = 'A' AND t3.FBUSINESSCLOSE = 'A' and t3.FBASEQTY - t3_E.FNOTRANSOUTREBASEQTY+t3_E.FRETRANSOUTBASEQTY + t3_E.FNOTRANSINLOBASEQTY>0 and t0.FBillNo= '" + dBean.interID+"'";
                break;
            case 24://出库申请单下推其他出库单
                SQL =   "select  t3.FUNITID 单位ID, st31.FNUMBER as 物料编码,st33.FName as 物料名称,st33.FSPECIFICATION as 规格型号,t0.FBillNo as 订单编号, convert(float,t3.FQTY+ ROUND( (t3_E.FRETURNBASEQTY-t3_E.FNORMALJOINBASEQTY  )/isnull(t_R1.FCONVERTNUMERATOR,1)*isnull(t_R1.FCONVERTDENOMINATOR,1)/isnull(t_R3.FCONVERTDENOMINATOR,1)*isnull(t_R3.FCONVERTNUMERATOR,1),t100.FPRECISION)) as 订单数量,0 as 已验数量,0 as 含税单价,t3.FMATERIALID as 物料ID,t3.FENTRYID as 明细唯一值,t3.FID as 明细内码,t3.FSEQ as 明细序号 from T_STK_OUTSTOCKAPPLY t0   LEFT OUTER JOIN T_STK_OUTSTOCKAPPLYENTRY t3 ON t0.FID = t3.FID   LEFT OUTER JOIN T_STK_OUTSTOCKAPPLYENTRY_E t3_E ON  t3.FENTRYID = t3_E.FENTRYID  LEFT OUTER JOIN T_BD_MATERIAL st31 ON t3.FMATERIALID = st31.FMATERIALID left join T_BD_MATERIAL_L st33 ON t3.FMATERIALID = st33.FMATERIALID left join t_BD_MaterialBase t2M on t3.FMATERIALID=t2M.fmaterialid left join T_BD_UNIT_L t6 on t6.FUnitID = t2M.FBASEUNITID left join  T_BD_UNITCONVERTRATE t_R1 on (t_R1.FMASTERID=st31.FMASTERID and t_R1.FCURRENTUNITID = t3.FUnitID)  left join  T_BD_UNITCONVERTRATE t_R3 on (t_R3.FMASTERID=st31.FMASTERID and t_R3.FCURRENTUNITID = t2M.FBASEUNITID) left join T_BD_UNIT t100 on t2M.FBASEUNITID=t100.FUNITID where t0.FOBJECTTYPEID = 'STK_OutStockApply' AND t0.FDOCUMENTSTATUS = 'C' AND t0.FCLOSESTATUS = 'A' AND t3.FBUSINESSEND = 'A' AND t3.FBUSINESSCLOSED = 'A' AND t3.FBASEQTY - t3_E.FNORMALJOINBASEQTY + t3_E.FRETURNBASEQTY > 0 and t0.FBillNo= '" + dBean.interID+"'";
                break;
            case 33://简单生产入库
//                SQL =   "select '' as 货主编码,'' as 仓库编码,t3.FLOT_TEXT as 批号,st019.FNumber as 辅助标识,st017.FNumber as 实际规格,t3.F_FFF_TEXT1 as 等级,CONVERT (DECIMAL(28,0),t3.F_FFF_Decimal) as 原木长,CONVERT (DECIMAL(28,0),t3.F_FFF_Decimal1) as 原木直径,t3.F_FFF_INTEGER2 as 板长,t3.F_FFF_INTEGER3 as 板宽,t3.F_FFF_INTEGER4 as 板厚, t3.FUNITID 单位ID, st31.FNUMBER as 物料编码,st33.FName as 物料名称,st33.FSPECIFICATION as 规格型号,t0.FBillNo as 订单编号, convert(float,t3.FACTUALQTY ) as 订单数量,0 as 已验数量,0 as 含税单价,t3.FMATERIALID as 物料ID,t3.FENTRYID as 明细唯一值,t3.FID as 明细内码,t3.FSEQ as 明细序号 from T_SP_PICKMTRL t0   LEFT OUTER JOIN T_SP_PICKMTRLData t3 ON t0.FID = t3.FID    LEFT OUTER JOIN T_BD_MATERIAL st31 ON t3.FMATERIALID = st31.FMATERIALID left join T_BD_MATERIAL_L st33 ON t3.FMATERIALID = st33.FMATERIALID LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st011 ON t3.FAUXPROPID = st011.FID LEFT OUTER JOIN T_BAS_ASSISTANTDATAENTRY st017 ON st011.FF100001 = st017.FEntryId LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st018 ON t3.FAUXPROPID = st018.FID LEFT OUTER JOIN T_BAS_ASSISTANTDATAENTRY st019 ON st018.FF100002 = st019.FEntryId  where  t0.FFORMID = 'SP_PickMtrl'  and t0.FID = '" + dBean.interID+"'";
                SQL =   "select t0.FBillNo as 订单编号,t3.FACTUALQTY as 订单数量,st33.FSPECIFICATION as 规格型号,st33.FName as 物料名称,st31.FNumber as 物料编码,st019.FNumber as 辅助标识,st017.FNumber as 实际规格,t3.FLOT_TEXT as 批号,t3.FMATERIALID  as 物料ID,t3.FUnitID as 单位ID,t3.FENTRYID as 明细唯一值,t3.FID as 明细内码,t3.FSEQ as 明细序号 from T_SP_PICKMTRL t0   LEFT OUTER JOIN T_SP_PICKMTRLData t3 ON t0.FID = t3.FID    LEFT OUTER JOIN T_BD_MATERIAL st31 ON t3.FMATERIALID = st31.FMATERIALID left join T_BD_MATERIAL_L st33 ON t3.FMATERIALID = st33.FMATERIALID LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st011 ON t3.FAUXPROPID = st011.FID LEFT OUTER JOIN T_BAS_ASSISTANTDATAENTRY st017 ON st011.FF100001 = st017.FEntryId LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st018 ON t3.FAUXPROPID = st018.FID LEFT OUTER JOIN T_BAS_ASSISTANTDATAENTRY st019 ON st018.FF100002 = st019.FEntryId  where  t0.FFORMID = 'SP_PickMtrl' and t0.FID = '" + dBean.interID+"'";
                break;
            case 25://简单生产入库
            case 27://简单生产入库
//                SQL =   "select '' as 货主编码,'' as 仓库编码,t3.FLOT_TEXT as 批号,st019.FNumber as 辅助标识,st017.FNumber as 实际规格,t3.F_FFF_TEXT1 as 等级,CONVERT (DECIMAL(28,4),t3.F_FFF_Decimal*0.304794) as 原木长,CONVERT (DECIMAL(28,4),t3.F_FFF_Decimal1*2.53995) as 原木直径,t3.F_FFF_INTEGER2 as 板长,t3.F_FFF_INTEGER3 as 板宽,t3.F_FFF_INTEGER4 as 板厚, t3.FUNITID 单位ID, st31.FNUMBER as 物料编码,st33.FName as 物料名称,st33.FSPECIFICATION as 规格型号,t0.FBillNo as 订单编号, convert(float,t3.FACTUALQTY ) as 订单数量,0 as 已验数量,0 as 含税单价,t3.FMATERIALID as 物料ID,t3.FENTRYID as 明细唯一值,t3.FID as 明细内码,t3.FSEQ as 明细序号 from T_SP_PICKMTRL t0   LEFT OUTER JOIN T_SP_PICKMTRLData t3 ON t0.FID = t3.FID    LEFT OUTER JOIN T_BD_MATERIAL st31 ON t3.FMATERIALID = st31.FMATERIALID left join T_BD_MATERIAL_L st33 ON t3.FMATERIALID = st33.FMATERIALID LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st011 ON t3.FAUXPROPID = st011.FID LEFT OUTER JOIN T_BAS_ASSISTANTDATAENTRY st017 ON st011.FF100001 = st017.FEntryId LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st018 ON t3.FAUXPROPID = st018.FID LEFT OUTER JOIN T_BAS_ASSISTANTDATAENTRY st019 ON st018.FF100002 = st019.FEntryId  where  t0.FFORMID = 'SP_PickMtrl'  and t0.FID = '" + dBean.interID+"'";
                SQL =   "select '' as 货主编码,'' as 仓库编码,t3.FLOT_TEXT as 批号,st019.FNumber as 辅助标识,st017.FNumber as 实际规格,t3.F_FFF_TEXT1 as 等级,CONVERT (DECIMAL(28,0),t3.F_FFF_Decimal) as 原木长,CONVERT (DECIMAL(28,0),t3.F_FFF_Decimal1) as 原木直径,t3.F_FFF_INTEGER2 as 板长,t3.F_FFF_INTEGER3 as 板宽,t3.F_FFF_INTEGER4 as 板厚, t3.FUNITID 单位ID, st31.FNUMBER as 物料编码,st33.FName as 物料名称,st33.FSPECIFICATION as 规格型号,t0.FBillNo as 订单编号, convert(float,t3.FACTUALQTY ) as 订单数量,0 as 已验数量,0 as 含税单价,t3.FMATERIALID as 物料ID,t3.FENTRYID as 明细唯一值,t3.FID as 明细内码,t3.FSEQ as 明细序号 from T_SP_PICKMTRL t0   LEFT OUTER JOIN T_SP_PICKMTRLData t3 ON t0.FID = t3.FID    LEFT OUTER JOIN T_BD_MATERIAL st31 ON t3.FMATERIALID = st31.FMATERIALID left join T_BD_MATERIAL_L st33 ON t3.FMATERIALID = st33.FMATERIALID LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st011 ON t3.FAUXPROPID = st011.FID LEFT OUTER JOIN T_BAS_ASSISTANTDATAENTRY st017 ON st011.FF100001 = st017.FEntryId LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st018 ON t3.FAUXPROPID = st018.FID LEFT OUTER JOIN T_BAS_ASSISTANTDATAENTRY st019 ON st018.FF100002 = st019.FEntryId  where  t0.FFORMID = 'SP_PickMtrl'  and t0.FID = '" + dBean.interID+"'";
                break;
            case 26://采购入库单下推简单生产领料
                SQL =   "select  '' as 货主编码,t_S1.FNumber as 仓库编码,st019.FNumber as 辅助标识,st017.FNumber as 实际规格,t3.FLOT_TEXT as 批号,t3.F_FFF_TEXT as 等级,CONVERT (DECIMAL(28,0),t3.F_FFF_Decimal) as 原木长,CONVERT (DECIMAL(28,0),t3.F_FFF_Decimal1) as 原木直径,t3.F_FFF_INTEGER5 as 板长,t3.F_FFF_INTEGER2 as 板宽,t3.F_FFF_INTEGER3 as 板厚, t3.FUNITID 单位ID, st31.FNUMBER as 物料编码,st33.FName as 物料名称,st33.FSPECIFICATION as 规格型号,t0.FBillNo as 订单编号, convert(float,t3.FREALQTY ) as 订单数量,0 as 已验数量,0 as 含税单价,t3.FMATERIALID as 物料ID,t3.FENTRYID as 明细唯一值,t3.FID as 明细内码,t3.FSEQ as 明细序号 from t_STK_InStock t0   LEFT OUTER JOIN t_STK_InStockEntry t3 ON t0.FID = t3.FID    LEFT OUTER JOIN T_BD_MATERIAL st31 ON t3.FMATERIALID = st31.FMATERIALID left join T_BD_MATERIAL_L st33 ON t3.FMATERIALID = st33.FMATERIALID LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st011 ON t3.FAUXPROPID = st011.FID LEFT OUTER JOIN T_BAS_ASSISTANTDATAENTRY st017 ON st011.FF100001 = st017.FEntryId LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st018 ON t3.FAUXPROPID = st018.FID LEFT OUTER JOIN T_BAS_ASSISTANTDATAENTRY st019 ON st018.FF100002 = st019.FEntryId left join  t_BD_Stock t_S1 on t3.FSTOCKID = t_S1.FSTOCKID  where t0.FOBJECTTYPEID = 'STK_InStock' and t0.FID = '" + dBean.interID+"'";
                break;
            case 28://采购入库单下推简单生产领料
                SQL =   "select  '' as 货主编码,t_S1.FNumber as 仓库编码,st019.FNumber as 辅助标识,st017.FNumber as 实际规格,t3.FLOT_TEXT as 批号,'' as 等级,0 as 原木长,0 as 原木直径,0 as 板长,0 as 板宽,0 as 板厚, t3.FUNITID 单位ID, st31.FNUMBER as 物料编码,st33.FName as 物料名称,st33.FSPECIFICATION as 规格型号,t0.FBillNo as 订单编号, convert(float,t3.FREALQTY ) as 订单数量,0 as 已验数量,0 as 含税单价,t3.FMATERIALID as 物料ID,t3.FENTRYID as 明细唯一值,t3.FID as 明细内码,t3.FSEQ as 明细序号 from t_STK_InStock t0   LEFT OUTER JOIN t_STK_InStockEntry t3 ON t0.FID = t3.FID    LEFT OUTER JOIN T_BD_MATERIAL st31 ON t3.FMATERIALID = st31.FMATERIALID left join T_BD_MATERIAL_L st33 ON t3.FMATERIALID = st33.FMATERIALID LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st011 ON t3.FAUXPROPID = st011.FID LEFT OUTER JOIN T_BAS_ASSISTANTDATAENTRY st017 ON st011.FF100001 = st017.FEntryId LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st018 ON t3.FAUXPROPID = st018.FID LEFT OUTER JOIN T_BAS_ASSISTANTDATAENTRY st019 ON st018.FF100002 = st019.FEntryId left join  t_BD_Stock t_S1 on t3.FSTOCKID = t_S1.FSTOCKID  where t0.FOBJECTTYPEID = 'STK_InStock' and t0.FID = '" + dBean.interID+"'";
                break;
            case 29://生产领料单下推产品入库单(1期)整单装箱
                SQL =   "select   '' as 货主编码,t3.FUNITID as 单位ID,t0.FBillNo as 订单编号,'' as 订单数量,'' as 已验数量,'' as 含税单价,t3.FMATERIALID as 物料ID,convert(float,t3.FACTUALQTY)  PCS, convert(float,FSECACTUALQTY)  as M3,convert(int, dbo.getString(st31.FNumber,'.',6)) as 宽度,dbo.getString(st31.FNumber,'.',4) as 等级,st33.FSPECIFICATION as 规格型号,st33.FName as 物料名称,st31.FNumber as 物料编码,st019.FNumber as 辅助标识,st017.FNumber as 实际规格,t3.FMATERIALID,t3.FUnitID,t3.FENTRYID as 明细唯一值,t3.FID as 明细内码,t3.FSEQ as 明细序号 from T_SP_PICKMTRL t0   LEFT OUTER JOIN T_SP_PICKMTRLData t3 ON t0.FID = t3.FID    LEFT OUTER JOIN T_BD_MATERIAL st31 ON t3.FMATERIALID = st31.FMATERIALID left join T_BD_MATERIAL_L st33 ON t3.FMATERIALID = st33.FMATERIALID LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st011 ON t3.FAUXPROPID = st011.FID LEFT OUTER JOIN T_BAS_ASSISTANTDATAENTRY st017 ON st011.FF100001 = st017.FEntryId LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st018 ON t3.FAUXPROPID = st018.FID LEFT OUTER JOIN T_BAS_ASSISTANTDATAENTRY st019 ON st018.FF100002 = st019.FEntryId  where  t0.FFORMID = 'SP_PickMtrl' and t0.FID = '" + dBean.interID+"' order by  st31.FNumber,convert(int, dbo.getString(st31.FNumber,'.',6)), dbo.getString(st31.FNumber,'.',7),st33.FSPECIFICATION";
                break;
            case 30://生产领料单下推产品入库单(1期)重新装箱
                SQL =   "select  '' as 货主编码,'' as 仓库编码,st019.FNumber as 辅助标识,st017.FNumber as 实际规格,t3.FLOT_TEXT as 批号,'' as 等级,0 as 原木长,0 as 原木直径,0 as 板长,0 as 板宽,0 as 板厚, t3.FUNITID 单位ID, st31.FNUMBER as 物料编码,st33.FName as 物料名称,st33.FSPECIFICATION as 规格型号,t0.FBillNo as 订单编号, convert(float,t3.FSTOCKACTUALQTY) as 订单数量,0 as 已验数量,0 as 含税单价,t3.FMATERIALID as 物料ID,t3.FENTRYID as 明细唯一值,t3.FID as 明细内码,t3.FSEQ as 明细序号 from T_SP_PICKMTRL t0   LEFT OUTER JOIN T_SP_PICKMTRLData t3 ON t0.FID = t3.FID    LEFT OUTER JOIN T_BD_MATERIAL st31 ON t3.FMATERIALID = st31.FMATERIALID left join T_BD_MATERIAL_L st33 ON t3.FMATERIALID = st33.FMATERIALID LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st011 ON t3.FAUXPROPID = st011.FID LEFT OUTER JOIN T_BAS_ASSISTANTDATAENTRY st017 ON st011.FF100001 = st017.FEntryId LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st018 ON t3.FAUXPROPID = st018.FID LEFT OUTER JOIN T_BAS_ASSISTANTDATAENTRY st019 ON st018.FF100002 = st019.FEntryId  where  t0.FFORMID = 'SP_PickMtrl'  and t0.FID = '" + dBean.interID+"'";
                break;
//            case 11:
//                SQL =   "select t11.FName as FStoctName,t12.FName as FSPName,t3.FName,t3.FNumber,t3.FModel,t2.FBillNo,t1.FInterID," +
//                        "FEntryID,t1.FItemID,t1.FUnitID,convert(float,FAuxQty-FAuxCommitQty) as FAuxQty,convert(float,FAuxPrice) " +
//                        "as FAuxPrice,'0' as FQtying from ICSubContractEntry t1 left join ICSubContract t2 on " +
//                        "t1.FInterID=t2.FInterID left join t_ICItem t3 on t1.FItemID=t3.FItemID left join t_Stock t11 " +
//                        "on t11.FItemID = t3.FDefaultLoc left join t_StockPlace t12 on t3.FSPID = t12.FSPID where " +
//                        "t2.FClosed=0 and (t2.FStatus=1 or t2.FStatus=2) and t1.FAuxQty-t1.FAuxCommitQty>0 and " +
//                        "t1.FInterID=" + dBean.interID;
//                        break;
//            case 12:
//                SQL =   "select t11.FName as FStoctName,t12.FName as FSPName,t3.FName,t3.FNumber,t3.FModel,t2.FBillNo,t2.FInterID," +
//                        "t1.FOrderEntryID as FEntryID,t1.FItemID,t1.FUnitID,convert(float,t1.FAuxQtyMust-t1.FAuxQty) as FAuxQty," +
//                        "0 as FAuxPrice,'0' as FQtying from PPBOMEntry t1 left join ICSubContract t2 on t1.FICMoInterID=" +
//                        "t2.FInterID left join t_ICItem t3 on t1.FItemID=t3.FItemID left join t_Stock t11 on t11.FItemID = " +
//                        "t3.FDefaultLoc left join t_StockPlace t12 on t3.FSPID = t12.FSPID where  (t2.FStatus=1 or t2.FStatus=2) " +
//                        "and t1.FAuxQtyMust-t1.FAuxQty>0 and t2.FInterID=" + dBean.interID;
//                        break;
//            case 13:
//                SQL =   "select '' as FAuxPrice, '0' as FQtying,  t11.FName as FStoctName,t12.FName as FSPName,t4.FItemID,(t4.FAuxQtyMust+t4.FAuxQtySupply-t4.FAuxQty) " +
//                        "as FAuxQty,t4.FICMOInterID,t1.FInterID,t1.FBillNo,t5.FWorkSHop,t2.FName as FDepartmentName,t4.FItemID," +
//                        "t4.FUnitID,FPlanCommitDate,FPlanFinishDate,t3.FName,t3.FModel,t4.FEntryID,t3.FNumber  from ICMO t1 " +
//                        "left join t_Department t2 on t1.FWorkShop = t2.FItemID " +
//                        "left join PPBOMEntry t4 on t1.FInterID=t4.FICMOInterID  left join t_ICItem t3 on t4.FItemID=t3.FItemID " +
//                        "left join PPBOM t5 on t4.FInterID=t5.FInterID left join t_Stock t11 on t11.FItemID = t3.FDefaultLoc " +
//                        "left join t_StockPlace t12 on t3.FSPID = t12.FSPID where t1.FStatus in(1,2) and " +
//                        "t4.FAuxQtyMust+t4.FAuxQtySupply-t4.FAuxQty>0 and t5.FType<>1067 and t1.FInterID = " + dBean.interID;
//                        break;
//            case 14:
//                SQL =   "select t11.FName as FStoctName,t12.FName as FSPName,t3.FName,t3.FNumber,t3.FModel,t2.FBillNo,t1.FInterID,FEntryID,t1.FItemID,t1.FUnitID,convert(float,FAuxQty-FAuxCommitQty) as FAuxQty,convert(float,FAuxPrice) as FAuxPrice,'0' as FQtying " +
//                        "from POOrderEntry t1 left join POOrder t2 on t1.FInterID=t2.FInterID left join t_ICItem t3 on t1.FItemID=" +
//                        "t3.FItemID left join t_Stock t11 on t11.FItemID = t3.FDefaultLoc left join t_StockPlace t12 on t3.FSPID = t12.FSPID where t2.FClosed=0 and (t2.FStatus=1 or t2.FStatus=2) and t1.FAuxQty-t1.FAuxCommitQty>0 and " +
//                        "t1.FInterID=" + dBean.interID;
//                break;
//            case 15://销售订单下推发料通知单
//
//            SQL =   "select t11.FName as FStoctName,t12.FName as FSPName,t3.FName,t3.FNumber,t3.FModel,t2.FBillNo,t1.FInterID,FEntryID,t1.FItemID,t1.FUnitID," +
//                    "convert(float,FAuxQty-FAuxCommitQty) as FAuxQty,convert(float,FAuxPrice) as FAuxPrice,'0' as FQtying " +
//                    "from SEOrderEntry t1 left join SEOrder t2 on t1.FInterID=t2.FInterID left join t_ICItem t3 on t1.FItemID=" +
//                    "t3.FItemID left join t_Stock t11 on t11.FItemID = t3.FDefaultLoc left join t_StockPlace t12 on t3.FSPID = t12.FSPID where t2.FClosed=0 and (t2.FStatus=1 or t2.FStatus=2) and t1.FAuxQty-t1.FAuxCommitQty>0 and " +
//                    "t1.FInterID=" + dBean.interID;
//            break;
//            case 16:
//                SQL =   "select '' AS FStoctName,'' AS FSPName,'0' AS FQtying,'' AS FAuxPrice,FInterID,FBillNo,FWorkShop,t2.FName as FDepartmentName,t1.FItemID,t1.FUnitID,FPlanCommitDate,FPlanFinishDate,(FAuxQty-FAuxCommitQty) as FAuxQty," +
//                        "(FAuxQtyForItem+FAuxQtyScrap) as FAuxQtyForItem,t3.FName,t3.FModel,0 as FEntryID,t3.FNumber from ICMO t1 left join t_Department t2 on t1.FWorkShop = t2.FItemID " +
//                        "left join t_ICItem t3 on t1.FItemID=t3.FItemID where  FAuxQty-FAuxCommitQty>0 and FStatus in(1,2) and t1.FInterID =" + dBean.interID;
//                        break;
//
//            case 18://汇报单下推产品入库
//                SQL =   "select '' AS FStoctName,'' AS FSPName, t3.FName,t3.FNumber,t3.FModel,t2.FBillNo,t1.FInterID,FEntryID,t1.FItemID,t1.FUnitID," +
//                        "convert(float,FAuxQtyPass-FAuxQtySelStock) as FAuxQty,0 as FAuxPrice,'0' as FQtying from " +
//                        "ICMORptEntry t1 left join ICMORpt t2 on t1.FInterID=t2.FInterID left join t_ICItem t3 on " +
//                        "t1.FItemID=t3.FItemID where   (t2.FStatus=1 or t2.FStatus=2) and t1.FAuxQtyPass-" +
//                        "t1.FAuxQtySelStock>0  and t1.FInterID="+dBean.interID;
//                        break;
//            case 19:
//                SQL = "select '' as FSPName,'' as FStoctName, t3.FName,t3.FNumber,t3.FModel,t2.FBillNo," +
//                        "t1.FInterID,FEntryID,t1.FItemID," +
//                        "t1.FUnitID,convert(float,FAuxQty) as FAuxQty,convert(float,FAuxPrice) as " +
//                        "FAuxPrice,'0' as FQtying,t1.FDCStockID,t1.FDCSPID,t1.FBatchNo,t1.FSCStockID," +
//                        "t1.FSCSPID from ICStockBillEntry t1 left join ICStockBill t2 on t1.FInterID=" +
//                        "t2.FInterID left join t_ICItem t3 on t1.FItemID=t3.FItemID where (t2.FTranType" +
//                        "=41 AND ((t2.FCheckerID<=0 OR t2.FCheckerID  IS NULL)  AND  t2.FCancellation = " +
//                        "0)) and t1.FInterID="+dBean.interID;
//                break;
//            case 20:
//                SQL =   "select t11.FName as FStoctName,t12.FName as FSPName,t3.FName,t3.FNumber,t3.FModel,t2.FBillNo,t1.FInterID,FEntryID,t1.FItemID,t1.FUnitID,convert(float,FAuxQty-FAuxCommitQty) as FAuxQty,convert(float,FAuxPrice) as FAuxPrice,'0' as FQtying " +
//                        "from SEOutStockEntry t1 left join SEOutStock t2 on t1.FInterID=t2.FInterID left join t_ICItem t3 on t1.FItemID=" +
//                        "t3.FItemID left join t_Stock t11 on t11.FItemID = t3.FDefaultLoc left join t_StockPlace t12 on t3.FSPID = t12.FSPID where t2.FClosed=0 and (t2.FStatus=1 or t2.FStatus=2) and t1.FAuxQty-t1.FAuxCommitQty>0 and " +
//                        "t1.FInterID=" + dBean.interID;
//                break;
//            case 21:
//                SQL = "select t3.FName,t3.FNumber,t3.FModel,t2.FBillNo,t1.FID as FInterID,FEntryID,t1.FItemID," +
//                        "t1.FUnitID,convert(float,FBaseQty-FDistQty) as FAuxQty,convert(float,t1.FOrderPrice) " +
//                        "as FAuxPrice,'0' as FQtying,t1.FDCStockID,t1.FDCSPID,'' as FSCStockID,'' as FSCSPID " +
//                        "from t_rt_RequestGoodsEntry t1 left join t_rt_RequestGoods t2 on t1.FID=t2.FID left join " +
//                        "t_ICItem t3 on t1.FItemID=t3.FItemID where t1.FBaseQty-t1.FDistQty>0 and (t2.FCheckStatus>0  ) " +
//                        "and t2.FClosed=0 and FCancellation<>1 and t1.FInterID="+dBean.interID;
//                break;
//            case 22:
//                SQL = "select FBillNo,t2.FName,FDate,FDeptID as FSupplyID ,FDeptID,FEmpID,'' as FMangerID,FInterID from " +
//                        "ICStockBill t1 left join t_Department t2 on t1.FDeptID=t2.FItemID  where (t1.FTranType=2 AND " +
//                        "((t1.FCheckerID<=0 OR t1.FCheckerID  IS NULL)  AND  t1.FCancellation = 0) AND t1.FInterID="+dBean.interID+")";
//                break;
        }
        try {
            System.out.println("下载ID：" + dBean.interID);
            System.out.println("SQL:" + SQL);
            conn = JDBCUtil.getConn(request);
            sta = conn.prepareStatement(SQL);
            rs = sta.executeQuery();
            PushDownDLBean pushDownDLBean = new PushDownDLBean();
//            PushDownRKBean pushDownRKBean = new PushDownRKBean();
            while (rs.next()) {
                    PushDownDLBean.DLbean dLbean = pushDownDLBean.new DLbean();
                    if (dBean.tag== 33){
                        dLbean.FNumber = rs.getString("物料编码");
                        dLbean.FName = rs.getString("物料名称");
                        dLbean.FModel = rs.getString("规格型号");
                        dLbean.FEntryID = rs.getString("明细唯一值");
                        dLbean.FID = rs.getString("明细内码");
                        dLbean.FSEQ = rs.getString("明细序号");
                        dLbean.AuxSign=rs.getString("辅助标识");
                        dLbean.ActualModel=rs.getString("实际规格");
                        dLbean.FBatchNo=rs.getString("批号");
                        dLbean.FUnitID = rs.getString("单位ID");
                        dLbean.FMaterialID = rs.getString("物料ID");
                        dLbean.FBillNo = rs.getString("订单编号");
                        dLbean.FQty = MathUtil.D2save5(MathUtil.toD(rs.getString("订单数量"))/0.00236)+"";
                    }else{
                        dLbean.FUnitID = rs.getString("单位ID");
                        dLbean.FNumber = rs.getString("物料编码");
                        dLbean.FName = rs.getString("物料名称");
                        dLbean.FModel = rs.getString("规格型号");
                        dLbean.FBillNo = rs.getString("订单编号");
                        dLbean.FQty = rs.getString("订单数量");
                        dLbean.FQtying = rs.getString("已验数量");
                        dLbean.FTaxPrice = rs.getString("含税单价");
                        dLbean.FMaterialID = rs.getString("物料ID");
                        dLbean.FEntryID = rs.getString("明细唯一值");
                        dLbean.FID = rs.getString("明细内码");
                        dLbean.FSEQ = rs.getString("明细序号");
                        if (dBean.tag==2){
                            dLbean.FIsGift=rs.getString("是否赠品");
                            dLbean.FAuxQty=rs.getString("辅助数量");
                            dLbean.FAuxQtying="0";
                            dLbean.FCreateDate=rs.getString("生产日期");
                        }

                        if (dBean.tag== 1 ||dBean.tag ==32||dBean.tag ==34){
                            dLbean.FPriceUnitID = rs.getString("计价单位编码");
                            dLbean.FTaxRate = rs.getString("税率");
                            dLbean.FNeedOrgID = rs.getString("需求组织ID");
                        }
                        if (dBean.tag==22 ||dBean.tag==23){
                            dLbean.FStorageOutID = rs.getString("调出仓库ID");
                            dLbean.FStorageInID = rs.getString("调入仓库ID");
                            dLbean.FOrgOutID = rs.getString("调出组织ID");
                            dLbean.FOrgInID = rs.getString("调入组织ID");
                            dLbean.FHuozhuOutID = rs.getString("调出货主ID");
                            dLbean.FHuozhuInID = rs.getString("调入货主ID");
                        }else{
                            dLbean.FHuoZhuNumber = rs.getString("货主编码");
                        }
                        if (dBean.tag==3){
                            dLbean.FBaseCanreturnQty=rs.getString("FBASECANRETURNQTY");
                        }
                        if (dBean.tag==4){
                            dLbean.FStockID=rs.getString("仓库ID");
                            dLbean.FBatchNo=rs.getString("批号");
                        }
                        if (dBean.tag==31||dBean.tag==21){
                            dLbean.AuxSign=rs.getString("辅助标识");
                            dLbean.ActualModel=rs.getString("实际规格");
                            dLbean.FIsGift=rs.getString("是否赠品");
                        }
                        if (dBean.tag == 25 ||dBean.tag == 26 ||dBean.tag == 28||dBean.tag == 30||dBean.tag == 27){
                            dLbean.FStorageOutID=rs.getString("仓库编码");
                            dLbean.AuxSign=rs.getString("辅助标识");
                            dLbean.ActualModel=rs.getString("实际规格");
                            dLbean.FBatchNo=rs.getString("批号");
                            dLbean.FLevel=rs.getString("等级");
                            dLbean.FYmLenght=rs.getString("原木长");
                            dLbean.FYmDiameter=rs.getString("原木直径");
                            dLbean.FBLenght=rs.getString("板长");
                            dLbean.FBWide=rs.getString("板宽");
                            dLbean.FBThick=rs.getString("板厚");
                        }
                        if (dBean.tag == 29){
                            dLbean.FWide=rs.getString("宽度");
                            dLbean.FM3=rs.getString("M3");
                            dLbean.FPCS=rs.getString("PCS");
                            dLbean.FLevel=rs.getString("等级");
                            dLbean.AuxSign=rs.getString("辅助标识");
                            dLbean.ActualModel=rs.getString("实际规格");
                        }
                    }

                    dLbean.FAccountID=dBean.FAccountID==null?"":dBean.FAccountID;

                    container.add(dLbean);
            }
                if (container.size() > 0) {
                    Lg.e("表体明细：",dBean.tag);
                    Lg.e("表体明细：",container);
//                    System.out.println("返回数据："+container.toString());
                    pushDownDLBean.list = container;
                    response.getWriter().write(CommonJson.getCommonJson(true, new Gson().toJson(pushDownDLBean)));
                } else {
                    response.getWriter().write(CommonJson.getCommonJson(false, "没找到数据"));
                }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write(CommonJson.getCommonJson(false, "数据库错误\r\n----------------\r\n错误原因:\r\n" + e.toString()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().write(CommonJson.getCommonJson(false, "数据库错误\r\n----------------\r\n错误原因:\r\n" + e.toString()));
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
