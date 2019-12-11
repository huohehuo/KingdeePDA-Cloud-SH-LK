package Server.print;

import Bean.CommonBean;
import Bean.DownloadReturnBean;
import Bean.PrintHistory;
import Utils.*;
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

/**
 * 期初物料补打
 */
@WebServlet(urlPatterns = "/PrintBeforeData")
public class PrintBeforeData extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String parameter = request.getParameter("json");
        String version = request.getParameter("version");
        String SQL = "";
        Gson gson = new Gson();
        Connection conn = null;
        PreparedStatement sta = null;
        ResultSet rs = null;
        ArrayList<PrintHistory> container = new ArrayList<>();
        System.out.println(parameter);
        String con="";
        if (parameter != null) {
            try {
                CommonBean searchBean = new Gson().fromJson(parameter,CommonBean.class);
                Lg.e("查找条件：",searchBean);
                if (!searchBean.FName.equals("")){
                    con=con+" and t0.FNumber like '%"+searchBean.FName+"%'" ;
                }
                if (!searchBean.FNumber.equals("")){
                    con=con+" and (t2.FNumber like '%"+searchBean.FNumber+"%' or t2L.FName like '%"+searchBean.FNumber+"%')" ;
                }

                System.out.println(request.getParameter("sqlip") + " " + request.getParameter("sqlport") + " " + request.getParameter("sqlname") + " " + request.getParameter("sqlpass") + " " + request.getParameter("sqluser"));
                conn = JDBCUtil.getConn(getDataBaseUrl.getUrl(request.getParameter("sqlip"), request.getParameter("sqlport"), request.getParameter("sqlname")), request.getParameter("sqlpass"), request.getParameter("sqluser"));
                SQL="select top 100 t_ST.FName as 仓库,st019.FNumber as 辅助属性,st017.FNumber as 实际规格,isnull(t5.FNumber,isnull(t_20.FNumber,t_22.FNumber)) as 货主编码,t0.FSTOCKID,t0.fmaterialid as 商品ID,t31.FSTOREUNITID as 单位ID,t2.FNumber as 商品代码,t2L.FName as 商品名称,t2L.FSPECIFICATION as 规格,t0.FNumber as 批号,CONVERT (FLOAT ,ROUND( t0.FQty,t100.FPRECISION)) as 库存数,'PCS' as 单位,t3.FName as 基本单位,t4.FName as 辅助单位,t5U.FName as 库存单位  from ( select t0.FAUXPROPID, t0.FOWNERID,t0.FBASEUNITID,t0.FMATERIALID,t0.FSTOCKID,st035.FNUMBER, sum(CAST(CASE  WHEN (T2.FSTOREURNOM = 0 OR T2.FSTOREURNUM = 0) THEN T0.FBASEQTY ELSE (CAST((T0.FBASEQTY * T2.FSTOREURNOM) AS NUMERIC(23, 10)) / T2.FSTOREURNUM) END AS NUMERIC(23, 10))) as FQty   from T_STK_INVENTORY t0 left join T_BD_MATERIALSTOCK t2 on t0.FMATERIALID =t2.FMATERIALID  LEFT OUTER JOIN T_BD_LOTMASTER st035 ON t0.FLOT = st035.FLOTID   where   not exists(select 1 from t_PDABarCodeSign t_P where t_P.FBatchNo=st035.FNUMBER and t_P.FItemID = t0.FMATERIALID)  group by t0.FOWNERID,t0.FMATERIALID,t0.FAUXPROPID,t0.FSTOCKID,st035.FNUMBER,t0.FBASEUNITID,t0.FSTOCKSTATUSID,t0.FSTOCKORGID ) t0  left join T_BD_MATERIAL t2 on t0.FMATERIALID = t2.FMaterialid left join t_bd_material_l t2L on  t2.fmaterialid=t2L.fmaterialid left join T_BD_MATERIALSTOCK t31 on t31.fmaterialid=t0.fmaterialid left join T_BD_UNIT_L t3 on t0.FBASEUNITID=t3.FUnitID left join T_BD_UNIT_L t4 on t31.FAUXUNITID=t4.FUnitID left join T_BD_UNIT_L t5U on t31.FSTOREUNITID=t5U.FUnitID left join T_ORG_Organizations t5 on t0.FOWNERID=t5.FORGID LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st011 ON t0.FAUXPROPID = st011.FID LEFT OUTER JOIN T_BAS_ASSISTANTDATAENTRY st017 ON st011.FF100001 = st017.FEntryId LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st018 ON t0.FAUXPROPID = st018.FID LEFT OUTER JOIN T_BAS_ASSISTANTDATAENTRY st019 ON st018.FF100002 = st019.FEntryId left join t_BD_Supplier t_20  on t_20.FSUPPLIERID = t0.FOWNERID left join t_BD_Customer t_22  on t_22.FCUSTID  = t0.FOWNERID left join T_BD_UNIT t100 on t5U.FUNITID=t100.FUNITID  left join  t_BD_Stock_L t_ST on t0.FSTOCKID = t_ST.FSTOCKID where 1=1 "+con +" and t0.FQty >0 order by t0.FMaterialid,t0.FNumber asc ";
                Lg.e("PrintBeforeData:"+SQL);
                sta = conn.prepareStatement(SQL);
                rs = sta.executeQuery();
                DownloadReturnBean downloadReturnBean = new DownloadReturnBean();
                if(rs!=null){
                    int i = rs.getRow();
                    System.out.println("rs的长度"+i);
                    while (rs.next()) {
                        PrintHistory bean = new PrintHistory();
                        bean.FName        = rs.getString("商品名称");
                        bean.FModel       = rs.getString("规格");
                        bean.FBatch       = rs.getString("批号");
                        bean.FNum         = MathUtil.cutZero(rs.getString("库存数"));
//                        bean.FUnit        = rs.getString("单位");
//                        bean.FUnitAux     = rs.getString("辅助单位");
                        bean.FBaseUnit        = rs.getString("基本单位");
                        bean.FStoreUnit        = rs.getString("库存单位");

                        bean.FUnit        = rs.getString("库存单位");
                        bean.FUnitAux     = rs.getString("基本单位");
                        bean.FMaterialid     = rs.getString("商品ID");
                        bean.FBaseUnitID     = rs.getString("单位ID");
                        bean.FAuxSign     = rs.getString("辅助属性");
                        bean.FActualModel     = rs.getString("实际规格");
                        bean.FHuoquan     = rs.getString("货主编码");
                        bean.FStorage     = rs.getString("仓库");
//                        bean.FHuoquan     = rs.getString("货主");
//                        bean.FBarCode     = rs.getString("条码");
//                        bean.FNum2        = rs.getString("辅助数量");
//                        bean.FNot         = rs.getString("生产单位ID");
//                        bean.FWaveHouse   = rs.getString("生产单位ID");

                        container.add(bean);
                    }
                    downloadReturnBean.printHistories = container;
                    Lg.e("期初补打",container);
                    response.getWriter().write(CommonJson.getCommonJson(true,gson.toJson(downloadReturnBean)));
                }else{
                    response.getWriter().write(CommonJson.getCommonJson(false,"未查询到数据"));
                }


            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().write(CommonJson.getCommonJson(false,"数据库错误\r\n----------------\r\n错误原因:\r\n"+e.toString()));

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                response.getWriter().write(CommonJson.getCommonJson(false,"数据库错误\r\n----------------\r\n错误原因:\r\n"+e.toString()));

            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
