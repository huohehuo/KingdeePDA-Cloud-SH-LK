package Server.prop.RegisterUtils;

import Utils.CommonJson;
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

/**
 * 设置最大用户数
 */
@WebServlet(urlPatterns = "/RegisterGetNum")
public class RegisterGetNum extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        Connection conn = null;
        PreparedStatement sta = null;
        ResultSet rs = null;
        String paramter = request.getParameter("json");
        String SQL="";
        Gson gson = new Gson();
        int num =0;
        Lg.e("RegisterCheck得到json：",paramter);
                //设置最大用户数，程序根据dbother文件的用户数，与设置的对比
                response.getWriter().write(CommonJson.getCommonJson(true,"50"));

//                Lg.e("加密"+DesUtils.encrypt("123"));
//                Lg.e("解密",DesUtils.decrypt(DesUtils.encrypt("123")));
//        Lg.e("加密", DesUtils.base64Encrypt("123"));
//        Lg.e("解密",DesUtils.base64Decode(DesUtils.base64Encrypt("123")));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
