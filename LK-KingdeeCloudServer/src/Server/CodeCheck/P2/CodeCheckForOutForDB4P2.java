package Server.CodeCheck.P2;

import Bean.CodeCheckBean;
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
 * 条码检测（出库）
 */
@WebServlet("/CodeCheckForOutForDB4P2")
public class CodeCheckForOutForDB4P2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CodeCheckForOutForDB4P2() {
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
		ArrayList<DownloadReturnBean.CodeCheckBackDataBean> list = new ArrayList<>();
		if(parameter!=null&&!parameter.equals("")){
			try {
				DownloadReturnBean downloadReturnBean = new DownloadReturnBean();
				System.out.println(parameter);
				conn = JDBCUtil.getConn(getDataBaseUrl.getUrl(request.getParameter("sqlip"), request.getParameter("sqlport"), request.getParameter("sqlname")), request.getParameter("sqlpass"), request.getParameter("sqluser"));
				System.out.println(request.getParameter("sqlip")+" "+request.getParameter("sqlport")+" "+request.getParameter("sqlname")+" "+request.getParameter("sqlpass")+" "+request.getParameter("sqluser"));

				CodeCheckBean bean = gson.fromJson(parameter, CodeCheckBean.class);
				sta = conn.prepareStatement("exec proc_AllotBarCode_check2 ?");

					sta.setString(1, bean.FBarCode);
//					sta.setString(2, bean.FPDAID);
//					sta.setString(3, bean.FBarCode);
//					sta.setString(4, bean.FStockID);
//					sta.setString(5, bean.FStockPlaceID);
					rs = sta.executeQuery();
					if (rs != null) {
						while (rs.next()) {
							DownloadReturnBean.CodeCheckBackDataBean cBean = downloadReturnBean.new CodeCheckBackDataBean();
							cBean.FTip					=rs.getString("说明");
							cBean.FBillNo				=rs.getString("FBillNo");
							cBean.FItemID				=rs.getString("FItemID");
							cBean.FUnitID				=rs.getString("FUnitID");
							cBean.FQty					=rs.getString("FQty");
							cBean.FStockID				=rs.getString("FStockID");
							cBean.FStockPlaceID			=rs.getString("FStockPlaceID");
							cBean.FBatchNo				=rs.getString("FBatchNo");
							cBean.FKFPeriod				=rs.getString("FKFPeriod");
							cBean.FKFDate				=rs.getString("FKFDate");
							cBean.FActualmodel				=rs.getString("实际规格");
							cBean.FAuxsign				=rs.getString("辅助标识");
							cBean.FPurchaseNo				=rs.getString("生产编号");
							cBean.FHuoZhuNumber				=rs.getString("货主编码");

							cBean.FLevel				=rs.getString("等级");
							cBean.FYmLenght				=rs.getString("原木长");
							cBean.FYmDiameter			=rs.getString("原木直径");
							cBean.FBLenght				=rs.getString("板长");
							cBean.FBWide				=rs.getString("板宽");
							cBean.FBThick				=rs.getString("板厚");
							cBean.FVolume				=rs.getString("体积");
							cBean.FCeng				=rs.getString("层数");
							cBean.FCodeType				=rs.getString("条码类型");

							list.add(cBean);
						}
					}

				downloadReturnBean.codeCheckBackDataBeans = list;
				Lg.e("出库验证返回：",list);
				System.out.println(list.toString());
				response.getWriter().write(CommonJson.getCommonJson(true,gson.toJson(downloadReturnBean)));
//				response.getWriter().write(CommonJson.getCommonJson(true, ""));
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
