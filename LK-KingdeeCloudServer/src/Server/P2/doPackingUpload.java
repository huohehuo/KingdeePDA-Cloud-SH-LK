package Server.P2;

import Bean.CommonBean;
import Bean.DownloadReturnBean;
import Bean.PurchaseInStoreUploadBean;
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
 * Servlet implementation class PurchaseInStoreUpload
 */
@WebServlet("/doPackingUpload")
public class doPackingUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public doPackingUpload() {
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
		ResultSet rs = null;
		boolean execute = true;
		ArrayList<DownloadReturnBean.WortPrintData> list = new ArrayList<>();
		if(parameter!=null&&!parameter.equals("")){
			try {
				System.out.println(parameter);
				conn = JDBCUtil.getConn(getDataBaseUrl.getUrl(request.getParameter("sqlip"), request.getParameter("sqlport"), request.getParameter("sqlname")), request.getParameter("sqlpass"), request.getParameter("sqluser"));
				System.out.println(request.getParameter("sqlip")+" "+request.getParameter("sqlport")+" "+request.getParameter("sqlname")+" "+request.getParameter("sqlpass")+" "+request.getParameter("sqluser"));
				CommonBean commonBean = new CommonBean();
				DownloadReturnBean downloadReturnBean = new DownloadReturnBean();
				PurchaseInStoreUploadBean pBean = gson.fromJson(parameter, PurchaseInStoreUploadBean.class);
				for(int i =0;i<pBean.list.size();i++){
					sta = conn.prepareStatement("exec proc_PDACountOff_Insert2 ?,?,?,?,?,?");
					String main = pBean.list.get(i).main;
					sta.setString(1, main);
					sta.setString(2, "");
					sta.setString(3, "");
					sta.setString(4, "");
					sta.setString(5, "");
					sta.setString(6, "");
					for(int j = 0;j<pBean.list.get(i).detail.size();j++){
						sta.setString(j+2, pBean.list.get(i).detail.get(j));
					}
					System.out.println("SQL:"+sta);
					rs = sta.executeQuery();
					if (rs != null) {
						while (rs.next()) {
							DownloadReturnBean.WortPrintData cBean = downloadReturnBean.new WortPrintData();
							cBean.FBoxCode					=rs.getString("箱码");
							cBean.FDate						=rs.getString("装箱日期");
							cBean.FUser						=rs.getString("制单人");
							cBean.FName						=rs.getString("名称");
							cBean.FUnit						=rs.getString("单位");
							cBean.FModel					=rs.getString("规格");
							cBean.FBatch					=rs.getString("批号");
							cBean.FLenght					=rs.getString("长度");
							cBean.FQty						=rs.getString("数量");
							cBean.FQtySum					=rs.getString("总数量");
							cBean.FM2Sum					=rs.getString("总面积");
							cBean.FM2					=rs.getString("面积");
							list.add(cBean);
						}
					}
				}
				downloadReturnBean.wortPrintDatas = list;
				Lg.e("doPackingUpload:",downloadReturnBean);
				response.getWriter().write(CommonJson.getCommonJson(true,gson.toJson(downloadReturnBean)));
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
