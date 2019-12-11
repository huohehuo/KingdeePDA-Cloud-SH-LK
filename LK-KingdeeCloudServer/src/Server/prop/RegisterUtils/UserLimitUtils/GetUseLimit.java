package Server.prop.RegisterUtils.UserLimitUtils;

import Bean.UseTimeBean;
import Utils.CommonJson;
import Utils.JDBCUtil;
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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by NB on 2017/8/7.
 */
@WebServlet(urlPatterns = "/GetUseLimit")
public class GetUseLimit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        Connection conn = null;
        PreparedStatement sta = null;
        ResultSet rs = null;
        String paramter = request.getParameter("json");
        Gson gson = new Gson();
        if(paramter!=null&&!paramter.equals("")){
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                Date curDate = new Date();
                String str = format.format(curDate);
                conn = JDBCUtil.getSQLiteForTime();
                sta = conn.prepareStatement("SELECT * FROM USERLIMIT");//getTimeManager
                rs = sta.executeQuery();
                if(rs!=null){
                    String limit = rs.getString("LIMIT");
                    if(limit!=null&&!limit.equals("")){
//                        Lg.e("回传数据："+gson.toJson(new UseTimeBean(now_time,Use_Time)));
                        response.getWriter().write(CommonJson.getCommonJson(true,gson.toJson(new UseTimeBean(limit,limit))));
                    }else{
                        response.getWriter().write(CommonJson.getCommonJson(false,"Error"));
                    }

                }else{
                    response.getWriter().write(CommonJson.getCommonJson(false,"Error"));
                }
            } catch (ClassNotFoundException e) {
                response.getWriter().write(CommonJson.getCommonJson(false,"数据库错误：请检查文件是否完整\r\n----------------\r\n错误原因:\r\n"+e.toString()));
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().write(CommonJson.getCommonJson(false,"SQLException-Error,请检查文件是否缺失"));
            }finally {
                JDBCUtil.close(rs,sta,conn);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
