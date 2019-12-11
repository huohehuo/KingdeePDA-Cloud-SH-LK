package Server.OnlyCode;

import Bean.CommonBean;
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
@WebServlet(urlPatterns = "/GetPGetData")
public class GetPGetData extends HttpServlet {
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
        ArrayList<DownloadReturnBean.PGetData> list = new ArrayList<>();
        System.out.println(parameter);
        String con="";
        if (parameter != null) {
            try {
                // 1 ：通过批号查找，0：通过条码查找
//                SearchBean searchBean = new Gson().fromJson(parameter,SearchBean.class);
//                if ("0".equals(searchBean.searchType)){//通过条码查找
//                    con=con+"and t1.FBarCode = '"+searchBean.json+"'" ;
//                }else{//通过批号查找
//                    con=con+"and t1.FBatchNo like '%"+searchBean.json+"%'" ;
//                }
                CommonBean bean = new Gson().fromJson(parameter, CommonBean.class);
                DownloadReturnBean downloadReturnBean = new DownloadReturnBean();
                System.out.println(request.getParameter("sqlip") + " " + request.getParameter("sqlport") + " " + request.getParameter("sqlname") + " " + request.getParameter("sqlpass") + " " + request.getParameter("sqluser"));
                conn = JDBCUtil.getConn(getDataBaseUrl.getUrl(request.getParameter("sqlip"), request.getParameter("sqlport"), request.getParameter("sqlname")), request.getParameter("sqlpass"), request.getParameter("sqluser"));
//                SQL="select  convert(float, t1.FQty*isnull(t_R1.FCONVERTNUMERATOR,1)/isnull(t_R1.FCONVERTDENOMINATOR,1)) as 基本单位数量,convert(float,t1.FQty*isnull(t_R1.FCONVERTNUMERATOR,1)/isnull(t_R1.FCONVERTDENOMINATOR,1)*isnull(t_R3.FCONVERTDENOMINATOR,1)/isnull(t_R3.FCONVERTNUMERATOR,1)) as 库存单位数量,t1.FBarCode as 条码,t2.FNumber as 商品代码,t2L.FName as 商品名称,t2L.FSPECIFICATION as 规格,convert(decimal(28,0),t1.FQty) 数量,t3.FName as 单位,t1.FRemark6 as 辅助数量,t4.FName as 辅助单位,t1.FBatchNo as 批次,t1.FRemark5 as 货主,t5.FName as 库存单位,t6.FName as 基本单位 from t_PDABarCodeSign t1 left join T_BD_MATERIAL t2 on t1.FItemID = t2.FMaterialid left join t_bd_material_l t2L on  t2.fmaterialid=t2L.fmaterialid left join T_BD_MATERIALSTOCK t2U  on t2.fmaterialid=t2U.fmaterialid left join t_BD_MaterialBase t2M on t2.fmaterialid=t2M.fmaterialid left join T_BD_UNIT_L t3 on t1.FUnitID=t3.FUnitID left join T_BD_UNIT_L t4 on t2U.FAUXUNITID=t4.FUnitID left join T_BD_UNIT_L t5 on t5.FUnitID=t2U.FSTOREUNITID left join T_BD_UNIT_L t6 on t6.FUnitID = t2M.FBASEUNITID " +
//                        "left join  T_BD_UNITCONVERTRATE t_R1 on (t_R1.FMASTERID=t1.FItemID and t_R1.FCURRENTUNITID = t1.FUnitID) " +
//                        "left join  T_BD_UNITCONVERTRATE t_R3 on (t_R3.FMASTERID=t1.FItemID and t_R3.FCURRENTUNITID = t2U.FSTOREUNITID)where 1=1 "+con +" ORDER BY t1.FBarCode ASC";
//                SQL="select convert(float, t6.FQty) as 总数量,convert(float,t6.FVolume) as 总面积,t1.FBillNo as 箱码,t1.FDate as 装箱日期,t3.FName as 制单人,t4.FName as 名称,t5.FName as 单位,(t1.FChang+'x'+t1.FHuang+'x'+t1.FHou) as 规格,t2.FBatchNo as 批号,t2.FLength as 长度,convert(decimal(28,0),t2.FQty-isnull(t2.FQtyOut,0)) as 数量,convert(float,t2.FVolume-isnull(t2.FVolumeOut,0)) as 面积 from t_AssembleRecord t1 inner join t_AssembleRecordentry t2 on t1.FID =t2.FID left join  T_SEC_user  t3 on t1.FBillerID=t3.FUSERID left join t_bd_material_l t4 on  t2.FItemID=t4.fmaterialid left join  T_BD_UNIT_L t5 ON  t2.FUNITID = t5.FUNITID inner join (select sum(FQty-isnull(FQtyOut,0)) as FQty,sum(FVolume-isnull(FVolumeOut,0)) as FVolume,FID from t_AssembleRecordentry where FQty>isnull(FQtyOut,0)  group by FID) t6 on t1.FID =t6.FID  where t1.FBillNo = '"+parameter+"' order by FEntryID";
                SQL="select  sum( convert(float,t3.FSTOCKACTUALQTY)) as PCS,sum( convert(float,FSECACTUALQTY)) as M3,convert(int, dbo.getString(st31.FNumber,'.',6)) as 宽度 from T_SP_PICKMTRL t0   LEFT OUTER JOIN T_SP_PICKMTRLData t3 ON t0.FID = t3.FID    LEFT OUTER JOIN T_BD_MATERIAL st31 ON t3.FMATERIALID = st31.FMATERIALID left join T_BD_MATERIAL_L st33 ON t3.FMATERIALID = st33.FMATERIALID LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st011 ON t3.FAUXPROPID = st011.FID LEFT OUTER JOIN T_BAS_ASSISTANTDATAENTRY st017 ON st011.FF100001 = st017.FEntryId LEFT OUTER JOIN T_BD_FLEXSITEMDETAILV st018 ON t3.FAUXPROPID = st018.FID LEFT OUTER JOIN T_BAS_ASSISTANTDATAENTRY st019 ON st018.FF100002 = st019.FEntryId  where  t0.FFORMID = 'SP_PickMtrl'  and t0.FID ='"+bean.FStandby1+"' group by convert(int, dbo.getString(st31.FNumber,'.',6)) order by  convert(int, dbo.getString(st31.FNumber,'.',6))";
                Lg.e("GetPGetData/SQL"+SQL);
                sta = conn.prepareStatement(SQL);
                rs = sta.executeQuery();
                if(rs!=null){
                    int i = rs.getRow();
                    System.out.println("rs的长度"+i);
                    while (rs.next()) {
                        DownloadReturnBean.PGetData cBean = downloadReturnBean.new PGetData();
                        cBean.FID					=bean.FStandby1;
                        cBean.FAccountID			=bean.FStandby2;
                        cBean.FWide					=rs.getString("宽度");
                        cBean.PCS					=rs.getString("PCS");
                        cBean.M3					=rs.getString("M3");

                        list.add(cBean);
                    }
                    downloadReturnBean.pGetDatas = list;
                    Lg.e("GetPGetData"+list.size(),list);
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
