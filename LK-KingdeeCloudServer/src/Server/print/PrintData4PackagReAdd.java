package Server.print;

import Bean.CommonBean;
import Bean.DownloadReturnBean;
import Bean.PurchaseInStoreUploadBean;
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
 * 条码检测（出库）
 */
@WebServlet("/PrintData4PackagReAdd")
public class PrintData4PackagReAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrintData4PackagReAdd() {
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
				Lg.e("PrintData4PackagReAdd",parameter);
				DownloadReturnBean downloadReturnBean = new DownloadReturnBean();
				conn = JDBCUtil.getConn(request);
				CommonBean commonBean = new CommonBean();
				PurchaseInStoreUploadBean pBean = gson.fromJson(parameter, PurchaseInStoreUploadBean.class);
				for(int i =0;i<pBean.list.size();i++) {
					sta = conn.prepareStatement("exec proc_PDAAssembleAdd_Insert1 ?,?,?,?,?,?");
					String main = pBean.list.get(i).main;
					sta.setString(1, main);
					sta.setString(2, "");
					sta.setString(3, "");
					sta.setString(4, "");
					sta.setString(5, "");
					sta.setString(6, "");
					for (int j = 0; j < pBean.list.get(i).detail.size(); j++) {
						sta.setString(j + 2, pBean.list.get(i).detail.get(j));
					}
					System.out.println("SQL:" + sta);
					rs = sta.executeQuery();
					if (rs != null) {
						while (rs.next()) {
							DownloadReturnBean.PrintDataBean cBean = downloadReturnBean.new PrintDataBean();
							cBean. FFBaoNo					=rs.getString("分包号");
							cBean.FBaoNum					=rs.getString("包数");
							cBean.FWide					=rs.getString("宽度");
							cBean.FStorage					=rs.getString("仓库");
							cBean. FQtyAll					=rs.getString("总数量");
							cBean. FVolAll					=rs.getString("总面积");
							cBean. FBoxCode					=rs.getString("箱码");
							cBean. FBoxDate					=rs.getString("装箱日期");
							cBean. FMaker					=rs.getString("制单人");
							cBean. FName					=rs.getString("名称");
							cBean. FHuozhuNote					=rs.getString("货主描述");
							cBean. FCarNo					=rs.getString("车号");
							cBean. FUnit					=rs.getString("单位");
							cBean. FModel					=rs.getString("规格");
							cBean.FBatch					=rs.getString("批号");
							cBean. FQty					=rs.getString("数量");
							cBean. FVol					=rs.getString("面积");
							cBean. FVolSplit					=rs.getString("面积");
							cBean.FLev					=rs.getString("等级");
//							cBean.FBoxType					=rs.getString("箱码类型");
							list.add(cBean);
						}
					}

				}

				downloadReturnBean.printDataBeans = list;
				Lg.e("PrintData4PackagReAdd:",list);
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
