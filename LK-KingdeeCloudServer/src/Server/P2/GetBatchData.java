package Server.P2;

import Bean.DownloadReturnBean;
import Utils.CommonJson;
import Utils.JDBCUtil;
import Utils.Lg;
import Utils.getDataBaseUrl;
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
 * 条码打印
 */
@WebServlet(urlPatterns = "/GetBatchData")
public class GetBatchData extends HttpServlet {
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
        ArrayList<DownloadReturnBean.BatchDataBean> list = new ArrayList<>();
        System.out.println(parameter);
        String con="";
        if (parameter != null) {
            try {

                DownloadReturnBean downloadReturnBean = new DownloadReturnBean();
                System.out.println(request.getParameter("sqlip") + " " + request.getParameter("sqlport") + " " + request.getParameter("sqlname") + " " + request.getParameter("sqlpass") + " " + request.getParameter("sqluser"));
                conn = JDBCUtil.getConn(getDataBaseUrl.getUrl(request.getParameter("sqlip"), request.getParameter("sqlport"), request.getParameter("sqlname")), request.getParameter("sqlpass"), request.getParameter("sqluser"));

                SQL="select top 20  t1.FBarCode as 条码,t2L.FName as 商品名称,convert(decimal(28,0),(t1.FQty-isnull(t1.FQtyOut,0))) 数量,t1.FBatchNo as 批次,convert(decimal(28,2),ISNull(F_FFF_INTEGER,0)) as 原木长,convert(decimal(28,2),ISNull(F_FFF_INTEGER1,0)) as 原木直径,convert(decimal(28,0),ISNull(F_FFF_INTEGER2,0)) as 板长,convert(decimal(28,0),ISNull(F_FFF_INTEGER3,0)) as 板宽,convert(decimal(28,0),ISNull(F_FFF_INTEGER4,0)) as 板厚,convert(float,ISNull(F_FFF_Volume,0)) as 体积,isnull(F_TypeID,1) as 条码类型,convert(int,isnull( F_Plies,0)) as 层数 from t_PDABarCodeSign t1 left join T_BD_MATERIAL t2 on t1.FItemID = t2.FMaterialid left join t_bd_material_l t2L on  t2.fmaterialid=t2L.fmaterialid left join T_BD_MATERIALSTOCK t2U  on t2.fmaterialid=t2U.fmaterialid left join t_BD_MaterialBase t2M on t2.fmaterialid=t2M.fmaterialid left join T_BD_UNIT_L t3 on t1.FUnitID=t3.FUnitID left join T_BD_UNIT_L t4 on t2U.FAUXUNITID=t4.FUnitID left join T_BD_UNIT_L t5 on t5.FUnitID=t2U.FSTOREUNITID left join T_BD_UNIT_L t6 on t6.FUnitID = t2M.FBASEUNITID left join  T_BD_UNITCONVERTRATE t_R1 on (t_R1.FMASTERID=t2.FMASTERID and t_R1.FCURRENTUNITID = t1.FUnitID) left join  T_BD_UNITCONVERTRATE t_R3 on (t_R3.FMASTERID=t2.FMASTERID and t_R3.FCURRENTUNITID = t2U.FSTOREUNITID) left join t_BD_Stock_L t_st1 on t1.FStockID=t_st1.FSTOCKID left join T_BD_UNIT t100 on t5.FUNITID=t100.FUNITID left join T_BD_UNIT t101 on t6.FUNITID=t101.FUNITID where FIsInStore='已入库' and  isnull(FIsOutStore,'未出库')='未出库' and t1.FQty>isnull(t1.FQtyOut,0) and t1.FBatchNo like '%"+parameter+"%' ORDER BY t1.FBarCode ASC";
                Lg.e("条码打印SQL"+SQL);
                sta = conn.prepareStatement(SQL);
                rs = sta.executeQuery();
                if(rs!=null){
                    int i = rs.getRow();
                    System.out.println("rs的长度"+i);
                    while (rs.next()) {
                        DownloadReturnBean.BatchDataBean cBean = downloadReturnBean.new BatchDataBean();
                        cBean. FBarCode					=rs.getString("条码");
                        cBean. FName					=rs.getString("商品名称");
                        cBean. FQty					=rs.getString("数量");
                        cBean. FBatchNo					=rs.getString("批次");
                        cBean. FLenght					=rs.getString("原木长");
                        cBean. FDiv					=rs.getString("原木直径");
                        cBean. FBLenght					=rs.getString("板长");
                        cBean. FBWide					=rs.getString("板宽");
                        cBean. FThick					=rs.getString("板厚");
                        cBean. FVol					=rs.getString("体积");
                        cBean. FTypeID					=rs.getString("条码类型");
                        cBean. FCeng				=rs.getString("层数");

                        list.add(cBean);
                    }
                    downloadReturnBean.batchDataBeans = list;
                    Lg.e("GetBoxPrintData",list);
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
