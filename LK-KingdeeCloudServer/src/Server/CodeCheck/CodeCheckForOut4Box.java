package Server.CodeCheck;

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
@WebServlet("/CodeCheckForOut4Box")
public class CodeCheckForOut4Box extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CodeCheckForOut4Box() {
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
				sta = conn.prepareStatement("exec proc_AssembleAndBarCodeOut1_check ?,?,?");

					sta.setString(1, bean.FBillNo);
					sta.setString(2, bean.FTypeID);
					sta.setString(3, bean.FID);
//					sta.setString(2, bean.FPDAID);
//					sta.setString(3, bean.FBarCode);
//					sta.setString(4, bean.FStockID);
//					sta.setString(5, bean.FStockPlaceID);
					rs = sta.executeQuery();
					if (rs != null) {
						while (rs.next()) {
							DownloadReturnBean.CodeCheckBackDataBean cBean = downloadReturnBean.new CodeCheckBackDataBean();
							cBean.FTip					=rs.getString("说明");
							cBean.FNumber				=rs.getString("商品编码");
							cBean.FBatchNo				=rs.getString("批号");
							cBean.FStoreOrgNumber				=rs.getString("库存组织编码");
							cBean.FUnitNumber				=rs.getString("单位编码");
							cBean.FStorageNumber					=rs.getString("仓库编码");
							cBean.FHuoZhuNumber				=rs.getString("货主编码");
							cBean.FQty				=rs.getString("数量");
							cBean.FStorage				=rs.getString("仓库名称");
							cBean.FName				=rs.getString("商品名称");
							cBean.FModel				=rs.getString("规格");

							list.add(cBean);
						}
					}

				downloadReturnBean.codeCheckBackDataBeans = list;
				Lg.e("箱码出库验证返回：",list);
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
