package Server.upload.P1;

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
import java.sql.SQLException;

/**
 * 样板出库//第三方货物出库
 */
@WebServlet(urlPatterns = "/OtherOutUpload")
public class OtherOutUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OtherOutUpload() {
        super();
    }



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String parameter = request.getParameter("json");
		Gson gson = new Gson();
		Connection conn = null;
		PreparedStatement sta = null;
		boolean execute = true;
		if(parameter!=null&&!parameter.equals("")){
			try {
				System.out.println(parameter);
				conn = JDBCUtil.getConn(getDataBaseUrl.getUrl(request.getParameter("sqlip"), request.getParameter("sqlport"), request.getParameter("sqlname")), request.getParameter("sqlpass"), request.getParameter("sqluser"));
				System.out.println(request.getParameter("sqlip")+" "+request.getParameter("sqlport")+" "+request.getParameter("sqlname")+" "+request.getParameter("sqlpass")+" "+request.getParameter("sqluser"));
					sta = conn.prepareStatement("exec proc_K3OtherOutStock ?");
					sta.setString(1, parameter);
					execute = sta.execute();
				Lg.e("OtherOutUpload回单："+execute);
					response.getWriter().write(CommonJson.getCommonJson(true, ""));

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				response.getWriter().write(CommonJson.getCommonJson(false,"数据库错误\r\n----------------\r\n错误原因:\r\n"+e.toString()));
			}finally {
				JDBCUtil.close(null,sta,conn);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
