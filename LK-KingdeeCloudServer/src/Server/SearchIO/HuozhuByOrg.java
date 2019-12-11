package Server.SearchIO;

import Bean.DownloadReturnBean;
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
 * 物料查询：可根据 id  barcode 模糊查询三个方式查询数据
 */
@WebServlet(urlPatterns = "/HuozhuByOrg")
public class HuozhuByOrg extends HttpServlet {
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
        ArrayList<DownloadReturnBean.Org> container = new ArrayList<>();
        System.out.println(parameter);
        String con="";
        if (parameter != null) {
            try {
                conn = JDBCUtil.getConn(request);
                SQL="SELECT t0_L.FDESCRIPTION,t0.FNUMBER, t0_L.FNAME,t0.FORGID  FROM V_SCM_OWNERORG t0 LEFT OUTER JOIN V_SCM_OWNERORG_L t0_L ON (t0.FORGID = t0_L.FORGID AND t0_L.FLocaleId = 2052) WHERE ((((EXISTS (SELECT 1 FROM t_org_bizrelation a INNER JOIN t_org_bizrelationEntry b ON a.FBIZRELATIONID = b.FBIZRELATIONID WHERE (( a.FBRTYPEID = 112 AND b.FRELATIONORGID = '"+parameter+"') AND b.FORGID = t0.FORGID)) OR t0.FORGID = '"+parameter+"') AND (t0.FDOCUMENTSTATUS = 'C' AND t0.FFORBIDSTATUS = 'A')) AND t0.FFORBIDSTATUS = 'A') )";
                sta = conn.prepareStatement(SQL);
                Lg.e("HuozhuByOrg:SQL:"+SQL);
                rs = sta.executeQuery();
                DownloadReturnBean dBean = new DownloadReturnBean();
                if(rs!=null){
                    int i = rs.getRow();
                    System.out.println("rs的长度"+i);
                    while (rs.next()) {
                        DownloadReturnBean.Org bean = dBean.new Org();
                        bean.FNumber = rs.getString("FNUMBER");
                        bean.FName =   rs.getString("FNAME");
                        bean.FOrgID =  rs.getString("FORGID");
                        bean.FNote =  rs.getString("FDESCRIPTION");
                        container.add(bean);
                    }
                    dBean.orgs = container;
                    Lg.e("货主带出:",container);
                    response.getWriter().write(CommonJson.getCommonJson(true,gson.toJson(dBean)));
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
