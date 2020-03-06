package Server.NumInStorage;

import Bean.InStoreNumBean;
import Utils.CommonJson;
import Utils.JDBCUtil;
import Utils.Lg;
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

/**
 * Created by NB on 2017/8/7.alksdjlfkja
 *
 *              查看库存
 */
@WebServlet(urlPatterns = "/GetInStoreNum")
public class GetInStoreNum extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String parameter = request.getParameter("json");
        String version = request.getParameter("version");
        String SQL;
        String num;
        String con = "";
        Connection conn = null;
        PreparedStatement sta = null;
        ResultSet rs = null;
        Lg.e("查找库存GetInStoreNum--json",parameter);
        if (parameter != null && !parameter.equals("")) {
            InStoreNumBean iBean = new Gson().fromJson(parameter,InStoreNumBean.class);
            //  and t0.FMATERIALID =114947 and t0.FSTOCKID =110934 and st035.FNUMBER ='批号' and t0.FSTOCKORGID='库存组织ID' and (t0.FOWNERID ='货主ID' or t_20.FSUPPLIERID ='货主ID' or t_22.FCUSTID='货主ID')
            if(iBean.FItemID!=null){
                con = con+" and t0.FMATERIALID ="+iBean.FItemID;
            }
            if(iBean.FStockID!=null){
                con = con+" and t0.FSTOCKID ="+iBean.FStockID;
            }
            if(iBean.FBatchNo!=null && !"".equals(iBean.FBatchNo) ){
                con = con+" and st035.FNUMBER ='"+iBean.FBatchNo+"'";
            }
            if(iBean.FOrgID!=null){
                con = con+" and t0.FSTOCKORGID ="+iBean.FOrgID;
            }
            if(iBean.FOwnerID!=null){
//                con = con+" and t0.FOWNERID ="+iBean.FOwnerID;
                con = con+" and (t0.FOWNERID='"+iBean.FOwnerID+"' or t_20.FSUPPLIERID='"+iBean.FOwnerID+"' or t_22.FCUSTID='"+iBean.FOwnerID+"')";
            }
            if(iBean.FStockPlaceID!=null){
                con = con+" and t0.FSTOCKLOCID ="+iBean.FStockPlaceID;
            }
            if(iBean.FKFDate!=null){
                con = con+" and t0.FPRODUCEDATE ='"+iBean.FKFDate+"'";
            }

            try {
                Lg.e("条件:"+con);
                conn = JDBCUtil.getConn(request);
//                SQL = "select convert(float,FQty) as FQty FROM ICInventory where FItemID = ? and FStockID = ? and FStockPlaceID = ? and FBatchNo = ? "+con;
//                SQL = "select t0.FMATERIALID as 商品ID,t0.FSTOCKID as 仓库ID,st035.FNUMBER as 批号,t0.FBASEQTY as 库存,t0.FSTOCKSTATUSID as 库存状态,t0.FSTOCKORGID as 库存组织ID  from T_STK_INVENTORY t0 LEFT OUTER JOIN T_BD_LOTMASTER st035 ON t0.FLOT = st035.FLOTID  where (((FISEFFECTIVED = '1') AND ((t0.FBASEQTY <> 0) OR (t0.FSECQTY <> 0))) and t0.FOBJECTTYPEID = 'STK_Inventory') "+con;
//                SQL = "select  t0.FOWNERID as 货主ID,t0.FMATERIALID as 商品ID,t0.FSTOCKID as 仓库ID,st035.FNUMBER as 批号,sum( CAST(CASE  WHEN (T2.FSTOREURNOM = 0 OR T2.FSTOREURNUM = 0) THEN T0.FBASEQTY ELSE (CAST((T0.FBASEQTY * T2.FSTOREURNOM) AS NUMERIC(23, 10)) / T2.FSTOREURNUM) END AS NUMERIC(23, 10))) as 库存,t0.FSTOCKSTATUSID as 库存状态,t0.FSTOCKORGID as 库存组织ID  from T_STK_INVENTORY t0 left join T_BD_MATERIALSTOCK t2 on t0.FMATERIALID =t2.FMATERIALID  LEFT OUTER JOIN T_BD_LOTMASTER st035 ON t0.FLOT = st035.FLOTID  where  1=1 "+con+" group by t0.FMATERIALID,t0.FSTOCKID,st035.FNUMBER,FSTOCKSTATUSID,t0.FSTOCKSTATUSID,t0.FSTOCKORGID,t0.FOWNERID";
//                SQL = "select  t0.FSTOCKORGID,t_20.FSUPPLIERID,t_22.FCUSTID,t0.FOWNERID as 货主ID,t0.FMATERIALID as 商品ID,t0.FSTOCKID as 仓库ID,st035.FNUMBER as 批号,sum( CAST(CASE  WHEN (T2.FSTOREURNOM = 0  OR T2.FSTOREURNUM = 0) THEN T0.FBASEQTY ELSE (CAST((T0.FBASEQTY * T2.FSTOREURNOM) AS NUMERIC(23, 10)) / T2.FSTOREURNUM) END AS NUMERIC(23, 10))) as 库存,t0.FSTOCKSTATUSID as 库存状态,t0.FSTOCKORGID as 库存组织ID  from T_STK_INVENTORY t0 left join T_BD_MATERIALSTOCK t2 on t0.FMATERIALID=t2.FMATERIALID  LEFT OUTER JOIN T_BD_LOTMASTER st035 ON t0.FLOT=st035.FLOTID LEFT join t_BD_Supplier t_20  on t_20.FSUPPLIERID=t0.FOWNERID left join t_BD_Customer t_22  on t_22.FCUSTID=t0.FOWNERID  where  1=1 "+con+" group by t0.FMATERIALID,t0.FSTOCKID,st035.FNUMBER,FSTOCKSTATUSID,t0.FSTOCKSTATUSID,t0.FSTOCKORGID,t0.FOWNERID,t_20.FSUPPLIERID,t_22.FCUSTID";
                SQL = "select  select distinct t0.FSTOCKORGID,t0.FSTOCKLOCID as 仓位ID,t0.FPRODUCEDATE as 生产日期,t_20.FSUPPLIERID,t_22.FCUSTID,t0.FOWNERID as 货主ID,t0.FMATERIALID as 商品ID,t0.FSTOCKID as 仓库ID,st035.FNUMBER as 批号, T0.FBASEQTY  as 库存,t0.FSTOCKSTATUSID as 库存状态,t0.FSTOCKORGID as 库存组织ID  from ( select  t0.FSTOCKSTATUSID,t0.FSTOCKORGID,t0.FAUXPROPID,t0.FSTOCKLOCID,t0.FPRODUCEDATE, t0.FOWNERID,t0.FMATERIALID,t0.FSTOCKID,t0.FLot, sum(CAST(CASE  WHEN (T2.FSTOREURNOM = 0 OR T2.FSTOREURNUM = 0) THEN T0.FBASEQTY ELSE (CAST((T0.FBASEQTY * T2.FSTOREURNOM) AS NUMERIC(23, 10)) / T2.FSTOREURNUM) END AS NUMERIC(23, 10))) as FBASEQTY   from T_STK_INVENTORY t0 left join T_BD_MATERIALSTOCK t2 on t0.FMATERIALID =t2.FMATERIALID      where  1=1  group by t0.FOWNERID,t0.FMATERIALID,t0.FAUXPROPID,t0.FSTOCKID,t0.FLot,t0.FSTOCKSTATUSID,t0.FSTOCKORGID,t0.FSTOCKLOCID,t0.FPRODUCEDATE,t0.FSTOCKSTATUSID having sum(CAST(CASE  WHEN (T2.FSTOREURNOM = 0 OR T2.FSTOREURNUM = 0) THEN T0.FBASEQTY ELSE (CAST((T0.FBASEQTY * T2.FSTOREURNOM) AS NUMERIC(23, 10)) / T2.FSTOREURNUM) END AS NUMERIC(23, 10)))>0) t0\n" +
                        "left join T_BD_MATERIALSTOCK t2 on t0.FMATERIALID=t2.FMATERIALID  LEFT OUTER JOIN T_BD_LOTMASTER st035 ON t0.FLOT=st035.FLOTID LEFT join t_BD_Supplier t_20  on t_20.FSUPPLIERID=t0.FOWNERID left join t_BD_Customer t_22  on t_22.FCUSTID=t0.FOWNERID   left join T_BD_MATERIALSTOCK t31 on t31.fmaterialid=t0.fmaterialid  left join T_BD_UNIT_L t5U on t31.FSTOREUNITID=t5U.FUnitID  left join T_BD_UNIT t100 on t5U.FUNITID=t100.FUNITID  where  1=1 "+con+" group by t0.FMATERIALID,t0.FSTOCKID,st035.FNUMBER,FSTOCKSTATUSID,t0.FSTOCKSTATUSID,t0.FSTOCKORGID,t0.FOWNERID,t_20.FSUPPLIERID,t_22.FCUSTID,t100.FPRECISION,t0.FSTOCKLOCID,t0.FPRODUCEDATE";
                Lg.e("查找库存GetInStoreNum--SQL："+SQL);
                sta = conn.prepareStatement(SQL);
//                sta.setString(1,iBean.FItemID);
//                sta.setString(2,iBean.FStockID);
//                sta.setString(3,iBean.FStockPlaceID);
//                sta.setString(4,iBean.FBatchNo);
                rs = sta.executeQuery();
                if(rs!=null){
                    rs.next();
                    if (rs.getRow()>=0){
                        num = rs.getString("库存");
                        System.out.println("库存:"+num);
                        response.getWriter().write(CommonJson.getCommonJson(true,num));
                    }else{
                        response.getWriter().write(CommonJson.getCommonJson(true,"0.00"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().write(CommonJson.getCommonJson(false,"无库存信息"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                response.getWriter().write(CommonJson.getCommonJson(false,"数据库驱动错误"));
            }

        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
