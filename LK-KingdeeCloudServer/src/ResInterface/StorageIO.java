package ResInterface;

import Bean.DownloadReturnBean;
import Utils.CommonJson;
import Utils.JDBCUtil;
import Utils.getDataBaseUrl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by NB on 2017/8/7.
 */
@WebServlet(urlPatterns = "/StorageIO")
public class StorageIO extends BaseIO {
    @Override
    protected void dealData(HttpServletRequest request, HttpServletResponse response, String parameter, String version) throws ServletException, IOException   {
        ArrayList<DownloadReturnBean.storage> container = new ArrayList<>();
        String SQL = "";
        System.out.println(parameter);
        if (parameter != null) {
            try {
                System.out.println(request.getParameter("sqlip") + " " + request.getParameter("sqlport") + " " + request.getParameter("sqlname") + " " + request.getParameter("sqlpass") + " " + request.getParameter("sqluser"));
                conn = JDBCUtil.getConn(getDataBaseUrl.getUrl(request.getParameter("sqlip"), request.getParameter("sqlport"), request.getParameter("sqlname")), request.getParameter("sqlpass"), request.getParameter("sqluser"));
                SQL = "SELECT t0.FUSEORGID,t0.FSTOCKID as 仓库ID,t0.FNUMBER as 仓库编码,t1.FNAME as 仓库名称,FISOPENLOCATION as 启用仓位,FALLOWMINUSQTY as 允许负库存 FROM t_BD_Stock t0 LEFT OUTER JOIN t_BD_Stock_L t1 ON (t0.FSTOCKID = t1.FSTOCKID AND t1.FLocaleId = 2052) WHERE t0.FFORBIDSTATUS = 'A'";//רҵ��

                sta = conn.prepareStatement(SQL);
                System.out.println("SQL:"+SQL);
                rs = sta.executeQuery();
                DownloadReturnBean downloadReturnBean = new DownloadReturnBean();
                if(rs!=null){
                    int i = rs.getRow();
                    System.out.println("rs的长度"+i);
                    while (rs.next()) {
                        DownloadReturnBean.storage bean = downloadReturnBean.new storage();
                        bean.FItemID = rs.getString("仓库ID");
                        bean.FName = rs.getString("仓库名称");
                        bean.FNumber = rs.getString("仓库编码");
                        bean.FIsOpenWaveHouse = rs.getString("启用仓位");
                        bean.FallowFStore = rs.getString("允许负库存");
                        bean.FOrg = rs.getString("FUSEORGID");
                        container.add(bean);
                    }
                    downloadReturnBean.storage = container;
                    response.getWriter().write(CommonJson.getCommonJson(true,gson.toJson(downloadReturnBean)));
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
}
