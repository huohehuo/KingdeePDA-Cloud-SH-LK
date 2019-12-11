package Utils;

import Bean.ImageBean;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by NB on 2017/8/7.
 */
@WebServlet(urlPatterns = "/ImageUpload")
public class ImageUpload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement sta = null;
        ResultSet rs = null;
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        String json = request.getParameter("json");
        String SQL = "";
        String con = "";
        if(json!=null&&!json.equals("")){
            ImageBean bean = new Gson().fromJson(json,ImageBean.class);
            if(null != bean.bitmapByte && bean.bitmapByte.length > 0){
                System.out.println("读取到：" + bean.bitmapByte.length + " 字节");
                String fileName = "save.jpg";
                ImageUtil.writeImageToDisk(bean.bitmapByte, fileName);
                writer.write(CommonJson.getCommonJson(true,""));
            }else{
                System.out.println("没有从该连接获得内容");
            }


//            try {
//                conn = JDBCUtil.getConn(getDataBaseUrl.getUrl(request.getParameter("sqlip")
//                        , request.getParameter("sqlport"), request.getParameter("sqlname")),
//                        request.getParameter("sqlpass"), request.getParameter("sqluser"));
//                System.out.println(request.getParameter("sqlip")+" "
//                        +request.getParameter("sqlport")+" "+request.getParameter("sqlname")
//                        +" "+request.getParameter("sqlpass")+" "+request.getParameter("sqluser"));
//
//
////                con= "  and t1.FBarCode = '"+json+"'";
////
////                if (!"".equals(bean.userId)){
////                    con= con+" and t1.FItemID = "+bean.userId;
////                }
////                if (!"".equals(bean.date)){
////                    con =con + " and t1.FStockID= "+bean.date;
////                }
////                //此为物料名称的模糊查询
////                if (!"".equals(bean.FStockPlaceID)){
//////                    con =con + " and t1.FStockPlaceID= "+bean.FStockPlaceID;
////                    con =con + " and t2.FName like '%"+bean.FStockPlaceID+"%'";
////                }
//////
////                if (!"".equals(bean.barcode)){
//////                    con =con + " and (t2.FModel like '%"+bean.FBatchNo+"%' or t2.FName like '%"+bean.FBatchNo+"%') ";
////                    con =con + " and t1.FBatchNo like '%"+bean.barcode+"%'";
////                }
////                SQL = "update t_PDABarCodeSign  set  FIsVerificat = 1,FVerificatMarker=?,FVerificatDate = ?  where FBarCode = ? ";
//                SQL = "exec proc_Verificat_FZ ?,?,?,?";
//
//                System.out.println("GetInStorageNumList:"+SQL);
////                if(json!=null&&json!=null&) {
////                    SQL = "select t2.FNumber as 物料编码,t2.FName as 物料名称,convert(float,t1.FQty) as 基本单位库存,convert(float,t1.FSecQty) as 辅助单位库存,t5.FName as 基本单位,t3.FName as 仓库,t4.FName as 仓位,t1.FBatchNo as 批次,t1.FKFDate as 生产日期,t1.FKFPeriod as 保质期 from ICInventory t1 left join t_ICItem t2 on t1.FItemID = t2.FItemID left join t_stock t3 on t1.FStockID = t3.FItemID left join t_stockPlace t4 on t1.FStockPlaceID = t4.FSPID left join t_Measureunit t5 on t2.FUnitID = t5.FItemID where 1=1 " + json;
////                    sta = conn.prepareStatement(SQL);
////                    sta.setString(1,json);
////                    sta.setString(2,json);
////                    sta.setString(3,json);
////                }
////                esle if(json!=null)
////                {
////                    SQL = "select t2.FNumber as 物料编码,t2.FName as 物料名称,convert(float,t1.FQty) as 基本单位库存,convert(float,t1.FSecQty) as 辅助单位库存,t5.FName as 基本单位,t3.FName as 仓库,t4.FName as 仓位,t1.FBatchNo as 批次,t1.FKFDate as 生产日期,t1.FKFPeriod as 保质期 from ICInventory t1 left join t_ICItem t2 on t1.FItemID = t2.FItemID left join t_stock t3 on t1.FStockID = t3.FItemID left join t_stockPlace t4 on t1.FStockPlaceID = t4.FSPID left join t_Measureunit t5 on t2.FUnitID = t5.FItemID where t1.FItemID=? ";
////                    sta = conn.prepareStatement(SQL);
////                    sta.setString(1,json);
////                }
////                else  if(json!=null)
////                {
////                    SQL = "select t2.FNumber as 物料编码,t2.FName as 物料名称,convert(float,t1.FQty) as 基本单位库存,convert(float,t1.FSecQty) as 辅助单位库存,t5.FName as 基本单位,t3.FName as 仓库,t4.FName as 仓位,t1.FBatchNo as 批次,t1.FKFDate as 生产日期,t1.FKFPeriod as 保质期 from ICInventory t1 left join t_ICItem t2 on t1.FItemID = t2.FItemID left join t_stock t3 on t1.FStockID = t3.FItemID left join t_stockPlace t4 on t1.FStockPlaceID = t4.FSPID left join t_Measureunit t5 on t2.FUnitID = t5.FItemID where   t1.FStockID=? and t1.FStockPlaceID=?";
////                    sta = conn.prepareStatement(SQL);
////                    sta.setString(1,json);
////                    sta.setString(2,json);
////                }
////                else
////                {
////                    SQL = "select t2.FNumber as 物料编码,t2.FName as 物料名称,convert(float,t1.FQty) as 基本单位库存,convert(float,t1.FSecQty) as 辅助单位库存,t5.FName as 基本单位,t3.FName as 仓库,t4.FName as 仓位,t1.FBatchNo as 批次,t1.FKFDate as 生产日期,t1.FKFPeriod as 保质期 from ICInventory t1 left join t_ICItem t2 on t1.FItemID = t2.FItemID left join t_stock t3 on t1.FStockID = t3.FItemID left join t_stockPlace t4 on t1.FStockPlaceID = t4.FSPID left join t_Measureunit t5 on t2.FUnitID = t5.FItemID ";
////                    sta = conn.prepareStatement(SQL);
////                }
//                sta = conn.prepareStatement(SQL);
////                sta.setString(1,bean.barcode);
////                sta.setString(2,bean.userId);
//                sta.setString(3,bean.date);
//                sta.setBytes(4,bean.bitmapByte);
//                sta.execute();
////                ArrayList<CheckBarCodeListBean.BarCodeList> container = new ArrayList<>();
////                CheckBarCodeListBean iBean = new CheckBarCodeListBean();
////                while (rs.next()){
////                    CheckBarCodeListBean.BarCodeList inBean = iBean.new BarCodeList();
////                    inBean.FIsSell          = rs.getString("FIsSell");
////                    inBean.FIsVerificat     = rs.getString("FIsVerificat");
////                    inBean.FBillNo          = rs.getString("销售订单号");
////                    inBean.FPrintDate       = rs.getString("打印日期");
////                    inBean.FPrintName       = rs.getString("打印员");
////                    inBean.FPrintState      = rs.getString("打印状态");
////                    inBean.FBarCode         = rs.getString("条码");
////                    inBean.StoreCode        = rs.getString("存货编码");
////                    inBean.StoreName        = rs.getString("存货名称");
////                    inBean.Model            = rs.getString("规格型号");
////                    inBean.Qty              = rs.getString("数量");
////                    inBean.SaleState        = rs.getString("发售状态");
////                    inBean.SaleDate         = rs.getString("发售日期");
////                    inBean.SaleName         = rs.getString("发售员");
////                    inBean.HxState          = rs.getString("核销状态");
////                    inBean.HxDate           = rs.getString("核销日期");
////                    inBean.HxName           = rs.getString("核销员");
////                    inBean.ProductBillNo    = rs.getString("销货单号");
////                    inBean.clientName       = rs.getString("客户");
////                    inBean.FIndex           = rs.getString("序号");
////                    container.add(inBean);
////                }
////                if(container.size()>0){
////                    iBean.list = container;
////                }else{
////                    writer.write(CommonJson.getCommonJson(false,"未查询到数据"));
////                }
//                    writer.write(CommonJson.getCommonJson(true,""));
//            } catch (SQLException e) {
//                writer.write(CommonJson.getCommonJson(false,"数据库错误\r\n----------------\r\n错误原因:\r\n"+e.toString()));
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                writer.write(CommonJson.getCommonJson(false,"服务器错误\r\n----------------\r\n错误原因:\r\n"+e.toString()));
//                e.printStackTrace();
//            }finally {
//                JDBCUtil.close(rs,sta,conn);
//            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
