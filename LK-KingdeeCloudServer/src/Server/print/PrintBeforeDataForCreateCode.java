package Server.print;

import Bean.DownloadReturnBean;
import Utils.*;
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
@WebServlet("/PrintBeforeDataForCreateCode")
public class PrintBeforeDataForCreateCode extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrintBeforeDataForCreateCode() {
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
        if (parameter != null && !parameter.equals("")) {
            try {
                DownloadReturnBean downloadReturnBean = new DownloadReturnBean();
                System.out.println(parameter);
                conn = JDBCUtil.getConn(getDataBaseUrl.getUrl(request.getParameter("sqlip"), request.getParameter("sqlport"), request.getParameter("sqlname")), request.getParameter("sqlpass"), request.getParameter("sqluser"));
                System.out.println(request.getParameter("sqlip") + " " + request.getParameter("sqlport") + " " + request.getParameter("sqlname") + " " + request.getParameter("sqlpass") + " " + request.getParameter("sqluser"));
                sta = conn.prepareStatement("exec proc_PDABarCodeSignBatch_Insert ?");
                sta.setString(1, parameter);
                rs = sta.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        DownloadReturnBean.CodeCheckBackDataBean cBean = downloadReturnBean.new CodeCheckBackDataBean();
                        cBean.FBarCode = rs.getString("条码");
                        cBean.FBatchNo = rs.getString("批号");
                        cBean.FQty = rs.getString("辅助数量");
                        cBean.FBaseQty = MathUtil.cutZero(rs.getString("基本单位数量"));
                        cBean.FStoreQty = MathUtil.cutZero(rs.getString("库存单位数量"));

//							cBean.FItemID				=rs.getString("FItemID");
//							cBean.FUnitID				=rs.getString("FUnitID");
//							cBean.FStockID				=rs.getString("FStockID");
//							cBean.FStockPlaceID			=rs.getString("FStockPlaceID");
//							cBean.FBatchNo				=rs.getString("FBatchNo");
//							cBean.FKFPeriod				=rs.getString("FKFPeriod");
//							cBean.FKFDate				=rs.getString("FKFDate");
//							cBean.FActualmodel				=rs.getString("实际规格");
//							cBean.FAuxsign				=rs.getString("辅助标识");
//							cBean.FPurchaseNo				=rs.getString("生产编号");
                        list.add(cBean);
                    }
                }

                downloadReturnBean.codeCheckBackDataBeans = list;
                Lg.e("PrintBeforeDataForCreateCode--list:", list);
                System.out.println(list.toString());
                response.getWriter().write(CommonJson.getCommonJson(true, gson.toJson(downloadReturnBean)));
//				response.getWriter().write(CommonJson.getCommonJson(true, ""));
            } catch (ClassNotFoundException | SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response.getWriter().write(CommonJson.getCommonJson(false, "数据库错误\r\n----------------\r\n错误原因:\r\n" + e.toString()));

            } finally {
                JDBCUtil.close(null, sta, conn);
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
