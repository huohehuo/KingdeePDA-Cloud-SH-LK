package Server.SearchIO;

import Bean.AuxSignSecCheckBean;
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
 * Created by NB on 2017/8/7.
 */
@WebServlet(urlPatterns = "/AuxSignSecCheck")
public class AuxSignSecCheck extends HttpServlet {
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
        ArrayList<AuxSignSecCheckBean> container = new ArrayList<>();
        System.out.println(parameter);
        if (parameter != null) {
            try {
                System.out.println(request.getParameter("sqlip") + " " + request.getParameter("sqlport") + " " + request.getParameter("sqlname") + " " + request.getParameter("sqlpass") + " " + request.getParameter("sqluser"));
                conn = JDBCUtil.getConn(getDataBaseUrl.getUrl(request.getParameter("sqlip"), request.getParameter("sqlport"), request.getParameter("sqlname")), request.getParameter("sqlpass"), request.getParameter("sqluser"));
                if ("".equals(parameter)){
                    SQL="select t0.FMASTERID as FAUXPTYID,t0.FNUMBER from  T_BAS_ASSISTANTDATAENTRY t0 LEFT OUTER JOIN T_BAS_ASSISTANTDATA_L st01_L ON (t0.FID = st01_L.FID AND st01_L.FLocaleId = 2052) where st01_L.FNAME = N'辅助标识'  AND t0.FFORBIDSTATUS = 'A'";
                }else{
                    SQL="select t1.FAUXPTYID,t1.FAUXPTYNUMBER as FNUMBER from T_BD_AuxPtyValue  t0 left join t_bd_auxptyvalueentity t1  on t0.FAUXPTYVALUEID=t1.FAUXPTYVALUEID  where t0.FMATERIALID = '"+parameter+"' and t0.FMATERIALAUXPROPERTYID=100002";
                }
                sta = conn.prepareStatement(SQL);
                Lg.e("AuxSignSecCheck:"+SQL);
                rs = sta.executeQuery();
                DownloadReturnBean downloadReturnBean = new DownloadReturnBean();
                if(rs!=null){
                    int i = rs.getRow();
                    System.out.println("rs的长度"+i);
                    while (rs.next()) {
                        AuxSignSecCheckBean bean = new AuxSignSecCheckBean();
                        bean.FAUXPTYID                = rs.getString("FAUXPTYID");
                        bean.FNUMBER                = rs.getString("FNUMBER");
                        container.add(bean);
                    }
                    Lg.e("AuxSignSecCheck:",container);
                    downloadReturnBean.auxSignSecCheckBeans = container;
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
