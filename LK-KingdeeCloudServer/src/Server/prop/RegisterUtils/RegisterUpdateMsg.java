package Server.prop.RegisterUtils;

import Bean.RegisterBean;
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

/**
 * Created by NB on 2017/8/7.
 */
@WebServlet(urlPatterns = "/RegisterUpdateMsg")
public class RegisterUpdateMsg extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        Connection conn = null;
        PreparedStatement sta = null;
        PreparedStatement updateSta = null;
        ResultSet rs = null;
        String paramter = request.getParameter("json");
        String SQL="";
        Gson gson = new Gson();
        int num =0;
        if(paramter!=null&&!paramter.equals("")){
            try {
                RegisterBean searchBean = new Gson().fromJson(paramter,RegisterBean.class);
                 Lg.e("RegisterUpdateMsg：更新手机信息",searchBean);
                conn = JDBCUtil.getSQLiteConn2();
//                    SQL = "UPDATE REGISTER SET orange_juice = '"+searchBean.val1+"' WHERE Register_code = ? ";
                    SQL = "UPDATE REGISTER SET Phone_msg = '"+searchBean.val1+"', version='"+searchBean.val2+"',Last_use_date='"+searchBean.val3+"' WHERE Register_code = ? ";
                    sta = conn.prepareStatement(SQL);
                    sta.setString(1,searchBean.Register_code);
                    sta.execute();
//                    Lg.e("rs:",rs.toString());
//                    while (rs.next()){
//                        SQL = "UPDATE REGISTER SET orange_juice = ?";
//                        updateSta = conn.prepareStatement(SQL);
//                        updateSta.setString(1,searchBean.val1);
//                        updateSta.execute();
//                        Lg.e("更新手机数据成功");
//                    }
//                    response.getWriter().write(CommonJson.getCommonJson(true,"更新手机数据信息"));

//                    if(rs!=null){
//                        String code = rs.getString("Register_code");
//                        if(code!=null&&!code.equals("")){
//                            response.getWriter().write(CommonJson.getCommonJson(true,"OK"));
//                        }else{
//                            response.getWriter().write(CommonJson.getCommonJson(true,"RegisterError"));
//                        }
//
//                    }else{
//                        response.getWriter().write(CommonJson.getCommonJson(false,"请联系软件供应商注册"));
//                    }
            } catch (ClassNotFoundException e) {
                response.getWriter().write(CommonJson.getCommonJson(false,"数据库错误\r\n----------------\r\n错误原因:\r\n"+e.toString()));
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().write(CommonJson.getCommonJson(false,"1"));
            } finally {
                JDBCUtil.close(rs,sta,conn);
//                JDBCUtil.close(null,updateSta,null);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
