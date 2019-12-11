package Server.ClientSearch;

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
@WebServlet(urlPatterns = "/ClientSearchLike")
public class ClientSearchLike extends HttpServlet {
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
        ArrayList<DownloadReturnBean.Client> container = new ArrayList<DownloadReturnBean.Client>();
        System.out.println(parameter);
        if (parameter != null) {
            try {
                conn = JDBCUtil.getConn(request);
                SearchBean searchBean = new Gson().fromJson(parameter,SearchBean.class);
                SearchBean.S2Product s2Product = new Gson().fromJson(searchBean.json,SearchBean.S2Product.class);
                Lg.e("查找客户：json",s2Product);
                if (!"".equals(s2Product.FOrg))con+=con+" and t0.FUSEORGID="+s2Product.FOrg;
                SQL = "SELECT distinct top 30 t0.FMASTERID,t0.FMASTERID as FCUSTID,t1.FSHORTNAME as 简称,t0.FNUMBER as 客户编码,t1.FNAME as 客户名称 FROM t_BD_Customer t0 LEFT OUTER JOIN t_BD_Customer_L t1 ON (t0.FCUSTID = t1.FCUSTID AND t1.FLocaleId = 2052) WHERE ((t0.FFORBIDSTATUS = 'A')) and (t0.FNUMBER like '%"+s2Product.likeOr+"%' or t1.FNAME like '%"+s2Product.likeOr+"%'  or t0.FCUSTID like '%"+s2Product.likeOr+"%'or t0.FMASTERID like '%"+s2Product.likeOr+"%')" +con;
                sta = conn.prepareStatement(SQL);
                Lg.e("Client:SQL:"+SQL);
                rs = sta.executeQuery();
                DownloadReturnBean downloadReturnBean = new DownloadReturnBean();
                if(rs!=null){
                    int i = rs.getRow();
                    System.out.println("rs的长度"+i);
                    while (rs.next()) {
                        DownloadReturnBean.Client bean = downloadReturnBean.new Client();
                        // TODO: 2019/6/12 0012  //一期时，这里为MASTERID,后期更新一期时要改为FCUSTID，
                        bean.FItemID = rs.getString("FMASTERID");
                        bean.FNumber = rs.getString("客户编码");
                        bean.FName = rs.getString("客户名称");
                        bean.FOrg = rs.getString("简称");
                        bean.FMASTERID = rs.getString("FMASTERID");
                        container.add(bean);
                    }
                    Lg.e("客户数据："+container.size(),container);
                    downloadReturnBean.clients = container;
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
