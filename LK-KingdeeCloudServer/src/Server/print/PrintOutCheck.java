package Server.print;

import Bean.DownloadReturnBean;
import Bean.PrintHistory;
import Bean.SearchBean;
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
 * 条码打印
 */
@WebServlet(urlPatterns = "/PrintOutCheck")
public class PrintOutCheck extends HttpServlet {
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
                // 1 ：通过批号查找，0：通过条码查找
                SearchBean searchBean = new Gson().fromJson(parameter,SearchBean.class);
                if ("0".equals(searchBean.searchType)){//通过条码查找
                    con=con+"and t1.FBarCode = '"+searchBean.json+"'" ;
                }else{//通过批号查找
                    con=con+"and t1.FBatchNo like '%"+searchBean.json+"%'" ;
                }
                System.out.println(request.getParameter("sqlip") + " " + request.getParameter("sqlport") + " " + request.getParameter("sqlname") + " " + request.getParameter("sqlpass") + " " + request.getParameter("sqluser"));
                conn = JDBCUtil.getConn(getDataBaseUrl.getUrl(request.getParameter("sqlip"), request.getParameter("sqlport"), request.getParameter("sqlname")), request.getParameter("sqlpass"), request.getParameter("sqluser"));
//                SQL="select  convert(float, t1.FQty*isnull(t_R1.FCONVERTNUMERATOR,1)/isnull(t_R1.FCONVERTDENOMINATOR,1)) as 基本单位数量,convert(float,t1.FQty*isnull(t_R1.FCONVERTNUMERATOR,1)/isnull(t_R1.FCONVERTDENOMINATOR,1)*isnull(t_R3.FCONVERTDENOMINATOR,1)/isnull(t_R3.FCONVERTNUMERATOR,1)) as 库存单位数量,t1.FBarCode as 条码,t2.FNumber as 商品代码,t2L.FName as 商品名称,t2L.FSPECIFICATION as 规格,convert(decimal(28,0),t1.FQty) 数量,t3.FName as 单位,t1.FRemark6 as 辅助数量,t4.FName as 辅助单位,t1.FBatchNo as 批次,t1.FRemark5 as 货主,t5.FName as 库存单位,t6.FName as 基本单位 from t_PDABarCodeSign t1 left join T_BD_MATERIAL t2 on t1.FItemID = t2.FMaterialid left join t_bd_material_l t2L on  t2.fmaterialid=t2L.fmaterialid left join T_BD_MATERIALSTOCK t2U  on t2.fmaterialid=t2U.fmaterialid left join t_BD_MaterialBase t2M on t2.fmaterialid=t2M.fmaterialid left join T_BD_UNIT_L t3 on t1.FUnitID=t3.FUnitID left join T_BD_UNIT_L t4 on t2U.FAUXUNITID=t4.FUnitID left join T_BD_UNIT_L t5 on t5.FUnitID=t2U.FSTOREUNITID left join T_BD_UNIT_L t6 on t6.FUnitID = t2M.FBASEUNITID " +
//                        "left join  T_BD_UNITCONVERTRATE t_R1 on (t_R1.FMASTERID=t1.FItemID and t_R1.FCURRENTUNITID = t1.FUnitID) " +
//                        "left join  T_BD_UNITCONVERTRATE t_R3 on (t_R3.FMASTERID=t1.FItemID and t_R3.FCURRENTUNITID = t2U.FSTOREUNITID)where 1=1 "+con +" ORDER BY t1.FBarCode ASC";
                SQL="select top 20 t_st1.FName as 仓库,'' as 仓位,CONVERT (VARCHAR (12),t1.FDateInStore,23) as 入库日期,t1.FRemark3 as 实际规格,t1.FRemark4 as 辅助标识,convert(float, round((t1.FQty-isnull(t1.FQtyOut,0)) *isnull(t_R1.FCONVERTNUMERATOR,1)/isnull(t_R1.FCONVERTDENOMINATOR,1),t101.FPRECISION)) as 基本单位数量,convert(float,ROUND( (t1.FQty-isnull(t1.FQtyOut,0))*isnull(t_R1.FCONVERTNUMERATOR,1)/isnull(t_R1.FCONVERTDENOMINATOR,1)*isnull(t_R3.FCONVERTDENOMINATOR,1)/isnull(t_R3.FCONVERTNUMERATOR,1),t100.FPRECISION)) as 库存单位数量,t1.FBarCode as 条码,t2.FNumber as 商品代码,t2L.FName as 商品名称,t2L.FSPECIFICATION as 规格,convert(decimal(28,0),(t1.FQty-isnull(t1.FQtyOut,0))) 数量,t3.FName as 单位,t1.FRemark6 as 辅助数量,t4.FName as 辅助单位,t1.FBatchNo as 批次,t1.FRemark5 as 货主,t5.FName as 库存单位,t6.FName as 基本单位 from t_PDABarCodeSign t1 left join T_BD_MATERIAL t2 on t1.FItemID = t2.FMaterialid left join t_bd_material_l t2L on  t2.fmaterialid=t2L.fmaterialid left join T_BD_MATERIALSTOCK t2U  on t2.fmaterialid=t2U.fmaterialid left join t_BD_MaterialBase t2M on t2.fmaterialid=t2M.fmaterialid left join T_BD_UNIT_L t3 on t1.FUnitID=t3.FUnitID left join T_BD_UNIT_L t4 on t2U.FAUXUNITID=t4.FUnitID left join T_BD_UNIT_L t5 on t5.FUnitID=t2U.FSTOREUNITID left join T_BD_UNIT_L t6 on t6.FUnitID = t2M.FBASEUNITID left join  T_BD_UNITCONVERTRATE t_R1 on (t_R1.FMASTERID=t2.FMASTERID and t_R1.FCURRENTUNITID = t1.FUnitID) left join  T_BD_UNITCONVERTRATE t_R3 on (t_R3.FMASTERID=t2.FMASTERID and t_R3.FCURRENTUNITID = t2U.FSTOREUNITID) left join t_BD_Stock_L t_st1 on t1.FStockID=t_st1.FSTOCKID left join T_BD_UNIT t100 on t5.FUNITID=t100.FUNITID left join T_BD_UNIT t101 on t6.FUNITID=t101.FUNITID where 1=1 "+con +" ORDER BY t1.FBarCode ASC";
                Lg.e("条码打印SQL"+SQL);
                sta = conn.prepareStatement(SQL);
                rs = sta.executeQuery();
                DownloadReturnBean downloadReturnBean = new DownloadReturnBean();
                if(rs!=null){
                    int i = rs.getRow();
                    System.out.println("rs的长度"+i);
                    while (rs.next()) {
                        PrintHistory bean = new PrintHistory();
                        bean.FHuoquan     = rs.getString("货主");
                        bean.FBarCode     = rs.getString("条码");
                        bean.FBatch       = rs.getString("批次");
                        bean.FName        = rs.getString("商品名称");
                        bean.FModel       = rs.getString("规格");
                        bean.FNum         = MathUtil.cutZero(rs.getString("库存单位数量"));
                        bean.FNum2         = MathUtil.cutZero(rs.getString("基本单位数量"));
                        bean.FUnit        = rs.getString("库存单位");
                        bean.FUnitAux     = rs.getString("基本单位");
                        bean.FAuxSign     = rs.getString("辅助标识");
                        bean.FActualModel     = rs.getString("实际规格");
                        bean.FPrintMan     = rs.getString("实际规格");
                        bean.FNumber     = rs.getString("商品代码");
                        bean.FStorage     = rs.getString("仓库");
                        bean.FWaveHouse     = rs.getString("仓位");
                        bean.FDate     = rs.getString("入库日期");
//                        bean.FNot         = rs.getString("生产单位ID");
//                        bean.FWaveHouse   = rs.getString("生产单位ID");

                        container.add(bean);
                    }
                    downloadReturnBean.printHistories = container;
                    Lg.e("条码打印",container);
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
