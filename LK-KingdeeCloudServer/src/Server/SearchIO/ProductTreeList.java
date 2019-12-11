package Server.SearchIO;

import Bean.ProductTreeBeanList;
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
@WebServlet(urlPatterns = "/ProductTreeList")
public class ProductTreeList extends HttpServlet {
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
        ArrayList<ProductTreeBeanList.ProductTreeBean> container = new ArrayList<>();
        System.out.println(parameter);
        String con="";
        if (parameter != null) {
            try {
                conn = JDBCUtil.getConn(request);
                SQL="select t1.FID,t1.FNumber,t2.FName,t1.FPARENTID from T_BD_MATERIALGROUP t1 left join T_BD_MATERIALGROUP_L t2 on t1.FID=t2.FID Where FPARENTID ='"+parameter+"'order by FNumber";
                sta = conn.prepareStatement(SQL);
                Lg.e("ProductTreeList:SQL:"+SQL);
                rs = sta.executeQuery();
                ProductTreeBeanList productTreeList = new ProductTreeBeanList();
                if(rs!=null){
                    int i = rs.getRow();
                    System.out.println("rs的长度"+i);
                    while (rs.next()) {
                        ProductTreeBeanList.ProductTreeBean productTreeBean= productTreeList.new ProductTreeBean();
                        productTreeBean.FID                = rs.getString("FID");
                        productTreeBean.FNumber                = rs.getString("FNumber");
                        productTreeBean.FName                = rs.getString("FName");
                        productTreeBean.FPARENTID                = rs.getString("FPARENTID");
                        container.add(productTreeBean);
                    }
                    productTreeList.treeBeans = container;
                    response.getWriter().write(CommonJson.getCommonJson(true,gson.toJson(productTreeList)));
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
