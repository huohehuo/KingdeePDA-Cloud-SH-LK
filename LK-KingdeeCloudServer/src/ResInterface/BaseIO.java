package ResInterface;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by NB on 2017/8/7.
 */
public abstract class BaseIO extends HttpServlet {
    public Connection conn = null;
    public PreparedStatement sta = null;
    public ResultSet rs = null;
    public Gson gson;
    protected abstract void dealData(HttpServletRequest request, HttpServletResponse response,String json,String version) throws ServletException, IOException;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String parameter = request.getParameter("json");
        String version = request.getParameter("version");
        gson = new Gson();
        dealData(request,response,parameter,version);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
