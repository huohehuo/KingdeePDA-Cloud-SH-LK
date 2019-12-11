package Server.P2;

import Bean.DownloadReturnBean;
import Bean.PrintHistory;
import Bean.SearchBean;
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
import java.util.TreeSet;

/**
 * 条码打印
 */
@WebServlet(urlPatterns = "/PrintOutCheck4BoxCode4P1List")
public class PrintOutCheck4BoxCode4P1List extends HttpServlet {
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
        ArrayList<DownloadReturnBean.PrintDataBean> list = new ArrayList<>();
        TreeSet<String> list2 = new TreeSet<>();

        System.out.println(parameter);
        String con="";
        if (parameter != null) {
            try {
                // 1 ：通过批号查找，0：通过条码查找
                SearchBean searchBean = new Gson().fromJson(parameter,SearchBean.class);
                if ("0".equals(searchBean.searchType)){//通过条码查找
                    con=con+"and t1.FBillNo = '"+searchBean.json+"'" ;
                }else{//通过批号查找
                    con=con+"and t2.FBatchNo like '%"+searchBean.json+"%'" ;
                }
                System.out.println(request.getParameter("sqlip") + " " + request.getParameter("sqlport") + " " + request.getParameter("sqlname") + " " + request.getParameter("sqlpass") + " " + request.getParameter("sqluser"));
                conn = JDBCUtil.getConn(getDataBaseUrl.getUrl(request.getParameter("sqlip"), request.getParameter("sqlport"), request.getParameter("sqlname")), request.getParameter("sqlpass"), request.getParameter("sqluser"));
//                SQL="select  convert(float, t1.FQty*isnull(t_R1.FCONVERTNUMERATOR,1)/isnull(t_R1.FCONVERTDENOMINATOR,1)) as 基本单位数量,convert(float,t1.FQty*isnull(t_R1.FCONVERTNUMERATOR,1)/isnull(t_R1.FCONVERTDENOMINATOR,1)*isnull(t_R3.FCONVERTDENOMINATOR,1)/isnull(t_R3.FCONVERTNUMERATOR,1)) as 库存单位数量,t1.FBarCode as 条码,t2.FNumber as 商品代码,t2L.FName as 商品名称,t2L.FSPECIFICATION as 规格,convert(decimal(28,0),t1.FQty) 数量,t3.FName as 单位,t1.FRemark6 as 辅助数量,t4.FName as 辅助单位,t1.FBatchNo as 批次,t1.FRemark5 as 货主,t5.FName as 库存单位,t6.FName as 基本单位 from t_PDABarCodeSign t1 left join T_BD_MATERIAL t2 on t1.FItemID = t2.FMaterialid left join t_bd_material_l t2L on  t2.fmaterialid=t2L.fmaterialid left join T_BD_MATERIALSTOCK t2U  on t2.fmaterialid=t2U.fmaterialid left join t_BD_MaterialBase t2M on t2.fmaterialid=t2M.fmaterialid left join T_BD_UNIT_L t3 on t1.FUnitID=t3.FUnitID left join T_BD_UNIT_L t4 on t2U.FAUXUNITID=t4.FUnitID left join T_BD_UNIT_L t5 on t5.FUnitID=t2U.FSTOREUNITID left join T_BD_UNIT_L t6 on t6.FUnitID = t2M.FBASEUNITID " +
//                        "left join  T_BD_UNITCONVERTRATE t_R1 on (t_R1.FMASTERID=t1.FItemID and t_R1.FCURRENTUNITID = t1.FUnitID) " +
//                        "left join  T_BD_UNITCONVERTRATE t_R3 on (t_R3.FMASTERID=t1.FItemID and t_R3.FCURRENTUNITID = t2U.FSTOREUNITID)where 1=1 "+con +" ORDER BY t1.FBarCode ASC";
                SQL="select t1.FType as 箱码类型,t1.FStockName as 仓库,t1.FPackets as 包数,t1.FWidth as 宽度,t2.FEntryID,convert(float, t6.FQty) as 总数量,convert(float,t6.FVolume) as 总面积,t1.FBillNo as 箱码,t1.FDate as 装箱日期,t3.FName as 制单人,t1.FName as 名称,t1.FHuZhu as 货主描述,t1.FCarNum as 车号,t5.FName as 单位, t4.FSPECIFICATION as 规格,t2.FBatchNo as 批号,t2.FLevel as 等级,convert(decimal(28,0),t2.FQty-isnull(t2.FQtyOut,0)) as 数量,convert(float,t2.FVolume-isnull(t2.FVolumeOut,0)) as 面积 from t_AssembleRecord1 t1 inner join t_AssembleRecord1entry t2 on t1.FID =t2.FID left join  T_SEC_user  t3 on t1.FBillerID=t3.FUSERID left join t_bd_material_l t4 on  t2.FItemID=t4.fmaterialid left join  T_BD_UNIT_L t5 ON  t2.FUNITID = t5.FUNITID inner join (select sum(FQty-isnull(FQtyOut,0)) as FQty,sum(FVolume-isnull(FVolumeOut,0)) as FVolume,FID from t_AssembleRecord1entry where FQty>isnull(FQtyOut,0)  group by FID) t6 on t1.FID =t6.FID  where 1=1 "+con+" order by convert(int,t2.FWidth),t2.FLevel,convert(int,FLength) desc";
                Lg.e("条码打印SQL"+SQL);
                sta = conn.prepareStatement(SQL);
                rs = sta.executeQuery();
                DownloadReturnBean downloadReturnBean = new DownloadReturnBean();
                if(rs!=null){
                    int i = rs.getRow();
                    System.out.println("rs的长度"+i);
                    while (rs.next()) {
                        DownloadReturnBean.PrintDataBean cBean = downloadReturnBean.new PrintDataBean();
                        cBean. FQtyAll					=rs.getString("总数量");
                        cBean. FVolAll					=rs.getString("总面积");
                        cBean. FBoxCode					=rs.getString("箱码");
                        cBean. FBoxDate					=rs.getString("装箱日期");
                        cBean. FMaker					=rs.getString("制单人");
                        cBean. FName					=rs.getString("名称");
                        cBean. FHuozhuNote					=rs.getString("货主描述");
                        cBean. FCarNo					=rs.getString("车号");
                        cBean. FUnit					=rs.getString("单位");
                        cBean. FModel					=rs.getString("规格");
                        cBean. FQty					=rs.getString("数量");
                        cBean. FVol					=rs.getString("面积");
                        cBean. FVolSplit					=rs.getString("面积");
                        cBean.FBatch					=rs.getString("批号");
                        cBean.FLev					=rs.getString("等级");
                        cBean.FEntryID					=rs.getString("FEntryID");
                        cBean.FBoxType					=rs.getString("箱码类型");
                        cBean.FBaoNum					=rs.getString("包数");
                        cBean.FWide					=rs.getString("宽度");
                        cBean.FStorage					=rs.getString("仓库");
                        if (list.size()==0)list.add(cBean);
                        for (int j = 0; j < list.size(); j++) {
                            boolean hasData=false;
                            for (int k = 0; k < list.size(); k++) {
                                if (list.get(k).FBoxCode.equals(cBean.FBoxCode)){
                                    hasData = true;
                                }
                            }
                            if (!hasData){
                                list.add(cBean);
                            }
                        }
                    }

                    downloadReturnBean.printDataBeans = list;
                    Lg.e("PrintOutCheck4BoxCode4P1List:"+list.size(),list);
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
