package Server.OnlyCode;

import Bean.GetQtyMsg;
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

/**
 * 写入临时表（出库）
 */
@WebServlet("/GetQtyForOut")
public class GetQtyForOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetQtyForOut() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String parameter = request.getParameter("json");
		Gson gson = new Gson();
		Connection conn = null;
		PreparedStatement sta = null;
		boolean execute = true;
		ResultSet rs = null;
		if(parameter!=null&&!parameter.equals("")){
			try {
				conn = JDBCUtil.getConn(getDataBaseUrl.getUrl(request.getParameter("sqlip"), request.getParameter("sqlport"), request.getParameter("sqlname")), request.getParameter("sqlpass"), request.getParameter("sqluser"));
				System.out.println(request.getParameter("sqlip")+" "+request.getParameter("sqlport")+" "+request.getParameter("sqlname")+" "+request.getParameter("sqlpass")+" "+request.getParameter("sqluser"));

				GetQtyMsg bean = gson.fromJson(parameter, GetQtyMsg.class);
				sta = conn.prepareStatement("exec proc_GetQtyPDA ?,?,?");

					sta.setString(1, bean.FMATERIALID);
					sta.setString(2, bean.FUnitID);
					sta.setString(3, bean.FQty);
//					sta.setString(4, bean.FQty);
					rs = sta.executeQuery();
					GetQtyMsg cBean=null;
					if (rs != null) {
						while (rs.next()) {
							cBean = new GetQtyMsg();
							cBean.FBaseQty					=rs.getString("基本单位数量");
							cBean.FBaseUnitName					=rs.getString("基本单位名称");
							cBean.FStoreQty					=rs.getString("库存单位数量");
							cBean.FStoreUnitName					=rs.getString("库存单位名称");
						}
					}
				Lg.e("GetQty返回：",cBean);
				if (cBean == null){
					response.getWriter().write(CommonJson.getCommonJson(false,""));
				}else{
					response.getWriter().write(CommonJson.getCommonJson(true,gson.toJson(cBean)));
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
