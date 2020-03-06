package Server.NumInStorage;

import Bean.DownloadReturnBean;
import Bean.InStoreNumBean;
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
@WebServlet("/GetStoreNum4sql")
public class GetStoreNum4sql extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStoreNum4sql() {
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
		ArrayList<DownloadReturnBean.InstorageNum> list = new ArrayList<>();
		if(parameter!=null&&!parameter.equals("")){
			try {
				DownloadReturnBean downloadReturnBean = new DownloadReturnBean();
				System.out.println(parameter);
				conn = JDBCUtil.getConn(getDataBaseUrl.getUrl(request.getParameter("sqlip"), request.getParameter("sqlport"), request.getParameter("sqlname")), request.getParameter("sqlpass"), request.getParameter("sqluser"));
				System.out.println(request.getParameter("sqlip")+" "+request.getParameter("sqlport")+" "+request.getParameter("sqlname")+" "+request.getParameter("sqlpass")+" "+request.getParameter("sqluser"));

				InStoreNumBean bean = gson.fromJson(parameter, InStoreNumBean.class);
				Lg.e("查询库存条件：",bean);
				sta = conn.prepareStatement("exec proc_StockSearchQty ?,?,?,?,?,?,?,?");

					sta.setString(1, bean.FType);
					sta.setString(2, "0".equals(bean.FType)?"0":bean.FItemID);
					sta.setString(3, "0".equals(bean.FType)?"0":bean.FStockID);
					sta.setString(4, "0".equals(bean.FType)?"0":bean.FStockPlaceID);
					sta.setString(5, "0".equals(bean.FType)?"0":bean.FOrgID);
					sta.setString(6, "0".equals(bean.FType)?"":bean.FBatchNo);
					sta.setString(7, "0".equals(bean.FType)?"0":bean.FOwnerID);
					sta.setString(8, "0".equals(bean.FType)?"":bean.FKFDate);
					rs = sta.executeQuery();
					if (rs != null) {
						while (rs.next()) {
							DownloadReturnBean.InstorageNum cBean = downloadReturnBean.new InstorageNum();
//							cBean.FUnitID = rs.getString("库存单位ID");
							cBean.FStockPlaceID = rs.getString("仓位ID");
							cBean.FKFDate = rs.getString("生产日期");
							cBean.FItemID = rs.getString("商品ID");
							cBean.FStockID = rs.getString("仓库ID");
							cBean.FBatchNo = rs.getString("批号");
							cBean.FQty = rs.getString("库存");
							cBean.FStoreState = rs.getString("库存状态");
							cBean.FStoreOrgID = rs.getString("库存组织ID");
							cBean.FHuozhuID = rs.getString("货主ID");
							list.add(cBean);
						}
					}

				downloadReturnBean.InstorageNum = list;
				Lg.e("GetStoreNum4sql："+list.size(),list);
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
