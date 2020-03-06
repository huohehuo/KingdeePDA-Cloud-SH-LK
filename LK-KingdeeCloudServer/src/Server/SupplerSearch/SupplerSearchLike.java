package Server.SupplerSearch;

import Bean.DownloadReturnBean;
import Bean.SearchBean;
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
import java.util.ArrayList;

/**
 * Created by NB on 2017/8/7.
 */
@WebServlet(urlPatterns = "/SupplerSearchLike")
public class SupplerSearchLike extends HttpServlet {
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
        String con="";
        ArrayList<DownloadReturnBean.suppliers> container = new ArrayList<>();
        System.out.println(parameter);
        if (parameter != null) {
            try {
                conn = JDBCUtil.getConn(request);
                SearchBean searchBean = new Gson().fromJson(parameter,SearchBean.class);
                SearchBean.S2Product s2Product = new Gson().fromJson(searchBean.json,SearchBean.S2Product.class);
                Lg.e("查找供应商：json",s2Product);
                if (!"".equals(s2Product.FOrg))con+=con+" and t0.FUSEORGID="+s2Product.FOrg;
                if (null != s2Product.FVal1 && !"".equals(s2Product.FVal1))con+=con+" and (t2.FSUPPLYCLASSIFY "+s2Product.FVal1+")";
//              SQL = "select top 50 FSecCoefficient,FSecUnitID,FIsSnManage,FItemID,FISKFPeriod,convert(INT,FKFPeriod) as FKFPeriod,FNumber,FModel,FName,FFullName,FUnitID,FUnitGroupID,FDefaultLoc,isnull(FProfitRate,0) as FProfitRate,isnull(FTaxRate,1) as FTaxRate,isnull(FOrderPrice,0) as FOrderPrice,isnull(FSalePrice,0) as FSalePrice,isnull(FPlanPrice,0) as FPlanPrice,FBarcode,FSPID,FBatchManager from t_ICItem where FErpClsID not in (6,8) and FDeleted = 0 and (FNumber like '%"+parameter+"%' or FName like '%"+parameter+"%') order by FNumber";//旗舰版和k3
//                SQL = "SELECT TOP 30 t2.FSUPPLYCLASSIFY,t0.FMASTERID,t1.FSHORTNAME as 简称,t0.FUSEORGID,t0.FSUPPLIERID as 供应商ID,t0.FNUMBER as 供应商编码,t1.FNAME as 供应商名称 FROM t_BD_Supplier t0 LEFT OUTER JOIN t_BD_Supplier_L t1 ON (t0.FSUPPLIERID = t1.FSUPPLIERID AND t1.FLocaleId = 2052) LEFT OUTER JOIN t_BD_SupplierBase t2 ON t0.FSUPPLIERID = t2.FSUPPLIERID WHERE ((t0.FFORBIDSTATUS = 'A')) and (t0.FNUMBER like '%"+s2Product.likeOr+"%' or t1.FNAME like '%"+s2Product.likeOr+"%')" +con;
                SQL = "SELECT TOP 30 t2.FSUPPLYCLASSIFY,t0.FMASTERID,t1.FSHORTNAME as 简称,t0.FUSEORGID,t0.FSUPPLIERID as 供应商ID,t0.FNUMBER as 供应商编码,t1.FNAME as 供应商名称 FROM t_BD_Supplier t0 LEFT OUTER JOIN t_BD_Supplier_L t1 ON (t0.FSUPPLIERID = t1.FSUPPLIERID AND t1.FLocaleId = 2052) LEFT OUTER JOIN t_BD_SupplierBase t2 ON t0.FSUPPLIERID = t2.FSUPPLIERID WHERE (t0.FFORBIDSTATUS = 'A') and (t0.FNUMBER like '%"+s2Product.likeOr+"%' or t1.FNAME like '%"+s2Product.likeOr+"%')" +con;

                sta = conn.prepareStatement(SQL);
                Lg.e("Suppliers:SQL:"+SQL);
                rs = sta.executeQuery();
                DownloadReturnBean downloadReturnBean = new DownloadReturnBean();
                if(rs!=null){
                    int i = rs.getRow();
                    System.out.println("rs的长度"+i);
                    while (rs.next()) {
                        DownloadReturnBean.suppliers bean = downloadReturnBean.new suppliers();
                        bean.FItemID = rs.getString("供应商ID");
                        bean.FNumber = rs.getString("供应商编码");
                        bean.FName = rs.getString("供应商名称");
                        bean.FOrg = rs.getString("FUSEORGID");
                        bean.FNote = rs.getString("简称");
                        bean.FMASTERID = rs.getString("FMASTERID");
                        bean.FSupplyClassIfy = rs.getString("FSUPPLYCLASSIFY");

                        container.add(bean);
                    }
                    Lg.e("获得供应商数据",container);
                    downloadReturnBean.suppliers = container;
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
