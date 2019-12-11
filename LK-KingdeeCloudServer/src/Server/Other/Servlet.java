package Server.Other;

import Bean.PDListReturnBean;
import Bean.PDMain;
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
import java.util.List;

/**
 * Created by NB on 2017/8/18.
 */
@WebServlet("/GetPDList")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        Connection conn = null;
        PreparedStatement sta = null;
        ResultSet rs = null;
        Gson gson = new Gson();
        try {
            List<PDMain> container = new ArrayList<>();
            conn = JDBCUtil.getConn(getDataBaseUrl.getUrl(request.getParameter("sqlip"), request.getParameter("sqlport"), request.getParameter("sqlname")), request.getParameter("sqlpass"), request.getParameter("sqluser"));
            System.out.println(request.getParameter("sqlip")+" "+request.getParameter("sqlport")+" "+request.getParameter("sqlname")+" "+request.getParameter("sqlpass")+" "+request.getParameter("sqluser"));
//            sta = conn.prepareStatement("select a.FID,a.FProcessID,FRemark,a.FDate,b.fname as FUsername from icstockcheckprocess a join t_user b on a.foperatorid=b.fuserid  and a.fstatus=0  order by fid Desc");
            sta = conn.prepareStatement("select t0.FBILLNO as 单据编码,convert(varchar(20), t0.FBACKUPDATE,23) as 账存日期,t0.FSCHEMENO as 盘点方案编码, t0.FSCHEMENAME as 盘点方案名称,t0.FID from T_STK_STKCOUNTINPUT t0 where t0.FSOURCE = '1' AND t0.FSOURCE = '1' AND t0.FSTOCKORGID IN (1, 100029, 100030, 110032)  AND t0.FSTOCKORGID IN (1, 0)");
            rs = sta.executeQuery();
            while(rs.next()){
                PDMain pdMain = new PDMain();
                pdMain.FID = rs.getString("FID");
                pdMain.FBillNo = rs.getString("单据编码");
                pdMain.FDate = rs.getString("账存日期");
                pdMain.FSchemeNo = rs.getString("盘点方案编码");
                pdMain.FSchemeName = rs.getString("盘点方案名称");
                container.add(pdMain);
            }
            if(container.size()>0){
                PDListReturnBean pBean = new PDListReturnBean();
                pBean.items = container;
                response.getWriter().write(CommonJson.getCommonJson(true,gson.toJson(pBean)));
            }else{
                response.getWriter().write(CommonJson.getCommonJson(false,"无数据"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write(CommonJson.getCommonJson(false,"数据库错误\r\n----------------\r\n错误原因:\r\n"+e.toString()));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().write(CommonJson.getCommonJson(false,"数据库错误\r\n----------------\r\n错误原因:\r\n"+e.toString()));

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
