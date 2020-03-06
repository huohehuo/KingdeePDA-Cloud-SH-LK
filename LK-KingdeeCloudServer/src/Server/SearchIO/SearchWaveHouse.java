package Server.SearchIO;

import Bean.DownloadReturnBean;
import Bean.SearchBean;
import Utils.CommonJson;
import Utils.JDBCUtil;
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
 * Created by NB on 2017/8/7.
 */
@WebServlet(urlPatterns = "/SearchWaveHouse")
public class SearchWaveHouse extends HttpServlet {
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
        ArrayList<DownloadReturnBean.wavehouse> container = new ArrayList<>();
        System.out.println(parameter);
        if (parameter != null) {
            try {
                SearchBean searchBean = new Gson().fromJson(parameter, SearchBean.class);
//                System.out.println(request.getParameter("sqlip") + " " + request.getParameter("sqlport") + " " + request.getParameter("sqlname") + " " + request.getParameter("sqlpass") + " " + request.getParameter("sqluser"));
                conn = JDBCUtil.getConn(getDataBaseUrl.getUrl(request.getParameter("sqlip"), request.getParameter("sqlport"), request.getParameter("sqlname")), request.getParameter("sqlpass"), request.getParameter("sqluser"));
//                SQL = "SELECT  FInterID, FID,FName  FROM t_SubMessage Where FInterID>0  AND FDeleted=0  And FTypeID=10001 and (FInterID like '%"+parameter+"%' or FName like '%"+parameter+"%') order by FID";//专业版
//                SQL = "SELECT t0.FUSEORGID,t0.FID as 仓位ID,t0.FNUMBER as 仓位编码,t1.FNAME as 仓位名称 FROM T_BAS_FLEXVALUES t0 LEFT OUTER JOIN T_BAS_FLEXVALUES_L t1 ON (t0.FID = t1.FID AND t1.FLocaleId = 2052) WHERE ((t0.FFORBIDSTATUS='A'))";
                SQL = "SELECT t0.FUSEORGID,t2.FENTRYID as 仓位ID,t4.FSEQ,t2.FNUMBER as 仓位编码,t2_L.FNAME as 仓位名称,t4.FStockID as 仓库ID FROM T_BAS_FLEXVALUES t0 LEFT OUTER JOIN T_BAS_FLEXVALUES_L t1 ON (t0.FID = t1.FID AND t1.FLocaleId = 2052) left join T_BAS_FLEXVALUESENTRY t2 on t1.FID = t2.FID left join T_BAS_FLEXVALUESENTRY_L t2_L on t2_L.FENTRYID = t2.FENTRYID  and t2_L.FLocaleId = 2052  left join T_BAS_FLEXVALUES  t3 on t1.FID = t3.FID left join T_BD_STOCKFLEXITEM t4 on  t4.FFlexId = t3.FId WHERE ((t0.FFORBIDSTATUS='A')) AND t4.FStockID='"+searchBean.val2+"'  and (t2.FNUMBER like '%"+searchBean.val1+"%'  or t2_L.FNAME like '%"+searchBean.val1+"%')";
                sta = conn.prepareStatement(SQL);
                System.out.println("SQL:"+SQL);
                rs = sta.executeQuery();
                DownloadReturnBean downloadReturnBean = new DownloadReturnBean();
                if(rs!=null){
                    int i = rs.getRow();
                    System.out.println("rs的长度"+i);
                    while (rs.next()) {
                        DownloadReturnBean.wavehouse bean = downloadReturnBean.new wavehouse();
                        bean.FSPID = rs.getString("仓位ID");
                        bean.FNumber = rs.getString("仓位编码");
                        bean.FName = rs.getString("仓位名称");
                        bean.FOrg = rs.getString("FUSEORGID");
                        bean.FStorageID = rs.getString("仓库ID");
                        bean.FSEQ = rs.getString("FSEQ");
                        container.add(bean);
                    }
                    downloadReturnBean.wavehouse = container;
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
