package Server.print;

import Bean.DownloadReturnBean;
import Utils.CommonJson;
import Utils.JDBCUtil;
import Utils.Lg;
import Utils.MathUtil;
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
 * 条码检测（出库）
 */
@WebServlet("/PrintData4P1")
public class PrintData4P1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrintData4P1() {
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
		ArrayList<DownloadReturnBean.PrintDataBean> list = new ArrayList<>();
		if(parameter!=null&&!parameter.equals("")){
			try {
				Lg.e("PrintData",parameter);
				DownloadReturnBean downloadReturnBean = new DownloadReturnBean();
				conn = JDBCUtil.getConn(request);
				sta = conn.prepareStatement("exec proc_PDABarCodeSign1_Insert ?");

					sta.setString(1, parameter);
					rs = sta.executeQuery();
					if (rs != null) {
						while (rs.next()) {
							DownloadReturnBean.PrintDataBean cBean = downloadReturnBean.new PrintDataBean();
							cBean.FBarCode					=rs.getString("条码");
							cBean.FBatch					=rs.getString("批号");
							cBean.FAuxNum					=rs.getString("辅助数量");
							cBean.FBaseNum					= MathUtil.cutZero(rs.getString("基本单位数量"));
							cBean.FStoreNum					=MathUtil.cutZero(rs.getString("库存单位数量"));
							list.add(cBean);
						}
					}

				downloadReturnBean.printDataBeans = list;
				Lg.e("PrintData:",list);
				response.getWriter().write(CommonJson.getCommonJson(true,gson.toJson(downloadReturnBean)));
//				response.getWriter().write(CommonJson.getCommonJson(true, ""));
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
