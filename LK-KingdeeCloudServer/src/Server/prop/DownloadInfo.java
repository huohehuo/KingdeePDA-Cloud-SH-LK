package Server.prop;

import Bean.ConnectSQLBean;
import Bean.DownloadReturnBean;
import Bean.DownloadReturnBean.*;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Servlet implementation class DownloadInfo
 */
@WebServlet("/DownloadInfo")
public class DownloadInfo extends HttpServlet {
    private static final long serialVersionUID = 1L;



    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadInfo() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        Connection connection = null;
        Statement statement = null;
        ResultSet rSet = null;
        String version = null;
        ArrayList<PriceMethod> priceMethods;
        ArrayList<InStorageType> InStorageTypes;
        ArrayList<wanglaikemu> wanglaikemu;
        ArrayList<yuandanType> getyuandanType;
        ArrayList<purchaseMethod> purchaseMethod;
        ArrayList<Org> orgs;
        ArrayList<Client> client;
        ArrayList<User> user;
        ArrayList<product> products;
        ArrayList<SaleMan> saleMens;
        ArrayList<suppliers> suppliers;
        ArrayList<StoreMan> storeMen;
        ArrayList<Unit> unit;
        ArrayList<storage> storage;
        ArrayList<InstorageNum> instorageNums;
        ArrayList<wavehouse> wavehouse;
        ArrayList<employee> empArrayList;
        ArrayList<department> department;
        ArrayList<buyer> container;
        ArrayList<RemarkData> remarkDatas;
        Gson gson = new Gson();
        int size = 0;
        DownloadReturnBean dBean = new DownloadReturnBean();

        if (request.getParameter("json") != null && !(request.getParameter("json")).equals("")) {
            ConnectSQLBean sqlBean = gson.fromJson(request.getParameter("json"), ConnectSQLBean.class);
            System.out.println(request.getParameter("json"));
            ArrayList<Integer> choose = sqlBean.choose;
            version = sqlBean.Version;
            try {
                connection = JDBCUtil.getConn(getDataBaseUrl.getUrl(sqlBean.ip, sqlBean.port, sqlBean.database), sqlBean.password, sqlBean.username);
                statement = connection.createStatement();
                System.out.println(request.getParameter("json"));
                for (Integer aChoose : choose) {
                    System.out.println("DownloadInfo下载定位:"+aChoose);
                    switch (aChoose) {
                        case 1: //采购员
                            container = getBuyer(statement, rSet, version, dBean);
                            System.out.println("container" + container.size());
                            System.out.println("container" + container.toString());
                            dBean.buyers = container;
                            size += container.size();
                            break;
                        case 2://部门表
                            department = getDepartment(statement, rSet, version, dBean);
                            System.out.println("department" + department.size());
                            System.out.println("department" + department.toString());
                            dBean.department = department;
                            size += department.size();
                            break;

                        case 3://员工表
                            empArrayList = getEmployee(statement, rSet, version, dBean);
                            System.out.println("emp" + empArrayList.size());
                            dBean.employee = empArrayList;
                            size += empArrayList.size();
                            break;
                        case 4://仓位表
                            wavehouse = getWaveHouse(statement, rSet, version, dBean);
                            System.out.println("wavehouse" + wavehouse.size());
                            dBean.wavehouse = wavehouse;
                            size += wavehouse.size();
                            break;
                        case 5://库存表
                            instorageNums = getInstorageNums(statement, rSet, version, dBean);
                            System.out.println("instorageNums" + instorageNums.size());
                            dBean.InstorageNum = instorageNums;
                            size += instorageNums.size();
                            break;
                        case 6://仓库表
                            storage = getStorage(statement, rSet, version, dBean);
                            System.out.println("storage" + storage.size());
                            dBean.storage = storage;
                            size += storage.size();
                            break;
                        case 7://单位表
                            unit = getUnit(statement, rSet, version, dBean);
                            System.out.println("unit" + unit.size());
                            dBean.units = unit;
                            size += unit.size();
                            break;
                        case 8://仓管员表
                            storeMen = getStoreMan(statement, rSet, version, dBean);
                            System.out.println("storeMens" + storeMen.size());
                            System.out.println("storeMens" + storeMen.toString());
                            dBean.storeMans = storeMen;
                            size += storeMen.size();
                            break;
                        case 9://供应商表
                            suppliers = getSuppliers(statement, rSet, version, dBean);
                            System.out.println("suppliers" + suppliers.size());
                            dBean.suppliers = suppliers;
                            size += suppliers.size();
                            break;
                        case 10://销售员表
                            saleMens = getSaleMan(statement, rSet, version, dBean);
                            System.out.println("saleMen" + saleMens.size());
                            System.out.println("saleMen" + saleMens.toString());
                            dBean.saleMans = saleMens;
                            size += saleMens.size();
                            break;
                        case 11://商品资料表
                            products = getProduct(statement, rSet, version, dBean);
                            System.out.println("products" + products.size());
                            dBean.products = products;
                            size += products.size();
                            break;
                        case 12://用户信息表
                            user = getUser(statement, rSet, version, dBean);
                            System.out.println("user" + user.size());
                            dBean.User = user;
                            size += user.size();
                            break;
                        case 13://客户信息表
                            client = getClient(statement, rSet, version, dBean);
                            System.out.println("client" + client.size());
                            dBean.clients = client;
                            size += client.size();
                            break;
                        case 14://组织表
                            orgs = getOrg(statement, rSet, version, dBean);
                            System.out.println("getOrg" + orgs.size());
                            dBean.orgs = orgs;
                            size += orgs.size();
                            break;
                        case 15://组织/供应商/客户 的简称表
                            remarkDatas = getRemarkData(statement, rSet, version, dBean);
                            System.out.println("remarkDatas" + remarkDatas.size());
                            Lg.e("简称表"+remarkDatas.size(),remarkDatas);
                            dBean.remarkDatas = remarkDatas;
                            size += remarkDatas.size();
                            break;
//                        case 15:
//                            purchaseMethod = getPurchaseMethod(statement, rSet, version, dBean);
//                            System.out.println("purchaseMethod" + purchaseMethod.size());
//                            dBean.purchaseMethod = purchaseMethod;
//                            size += purchaseMethod.size();
//                            break;
//                        case 16:
//                            getyuandanType = getyuandanType(statement, rSet, version, dBean);
//                            System.out.println("getyuandanType" + getyuandanType.size());
//                            dBean.yuandanTypes = getyuandanType;
//                            size += getyuandanType.size();
//                            break;
//                        case 17:
//                            wanglaikemu = getWanglaikemu(statement, rSet, version, dBean);
//                            System.out.println("wanglaikemu" + wanglaikemu.size());
//                            dBean.wanglaikemu = wanglaikemu;
//                            size += wanglaikemu.size();
//                            break;
//                        case 18:
//                            priceMethods = getPriceMethods(statement, rSet, version, dBean);
//                            System.out.println("priceMethods" + priceMethods.size());
//                            dBean.priceMethods = priceMethods;
//                            size += priceMethods.size();
//                            break;
//                        case 19:
//                            InStorageTypes = getInStoreType(statement, rSet, version, dBean);
//                            System.out.println("InStorageTypes" + InStorageTypes.size());
//                            dBean.inStorageTypes = InStorageTypes;
//                            size += InStorageTypes.size();
                        default:
                            break;
                    }
                }
                dBean.size = size;
                System.out.println("下载的数据："+CommonJson.getCommonJson(true, gson.toJson(dBean)));
                response.getWriter().write(CommonJson.getCommonJson(true, gson.toJson(dBean)));


            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                response.getWriter().write(CommonJson.getCommonJson(false, "数据库连接错误"));
            }finally {
                JDBCUtil.close(rSet,null,connection);
                if(statement!=null){
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            response.getWriter().write(CommonJson.getCommonJson(false, "未接受到Json数据"));
        }

    }

    private ArrayList<Client> getClient(Statement statement, ResultSet rSet, String version, DownloadReturnBean dBean) {
        ArrayList<Client> container = new ArrayList<DownloadReturnBean.Client>();
        String sql = "SELECT t0.FUSEORGID,t0.FCUSTID as 客户ID,t0.FNUMBER as 客户编码,t1.FNAME as 客户名称 FROM t_BD_Customer t0 LEFT OUTER JOIN t_BD_Customer_L t1 ON (t0.FCUSTID = t1.FCUSTID AND t1.FLocaleId = 2052) WHERE (t0.FFORBIDSTATUS = 'A')";
        try {
            rSet = statement.executeQuery(sql);
            while (rSet.next()) {
                DownloadReturnBean.Client bean = dBean.new Client();
                bean.FItemID = rSet.getString("客户ID");
                bean.FNumber = rSet.getString("客户编码");
                bean.FName = rSet.getString("客户名称");
                bean.FOrg = rSet.getString("FUSEORGID");
//                bean.FParentID = rSet.getString("FParentID");
//                bean.FItemClassID = rSet.getString("FItemClassID");
//                bean.FLevel = rSet.getString("FLevel");
//                bean.FDetail = rSet.getString("FDetail");
//                bean.FAddress = rSet.getString("FAddress");
//                bean.FPhone = rSet.getString("FPhone");
//                bean.FEmail = rSet.getString("FEmail");
//                bean.FTypeID = rSet.getString("FTypeID");
                container.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


        return container;

    }

    private ArrayList<product> getProduct(Statement statement, ResultSet rSet, String version, DownloadReturnBean dBean) {
        ArrayList<product> container = new ArrayList<DownloadReturnBean.product>();
        String sql = "";
//        if (version.equals("500116") || version.equals("500115") ) {
//            sql = "select FIsSnManage,FItemID,FISKFPeriod,FKFPeriod,FNumber,FModel,FName,FFullName,FUnitID,FUnitGroupID,FDefaultLoc,isnull(FProfitRate,0) as FProfitRate,isnull(FTaxRate,1) as FTaxRate,isnull(FOrderPrice,0) as FOrderPrice,isnull(FSalePrice,0) as FSalePrice,isnull(FPlanPrice,0) as FPlanPrice,'' as FBarcode,FSPID,FBatchManager from t_ICItem where FErpClsID not in (6,8) and FDeleted = 0 order by FName collate Chinese_PRC_CI_AS";//k3 rise 12.3
//        } else if (version.equals("800103")  || version.equals("800102") || version.contains("5001")) {
//            sql = "select FIsSnManage,FItemID,FISKFPeriod,convert(INT,FKFPeriod) as FKFPeriod ,FNumber,FModel,FName,FFullName,FUnitID,FUnitGroupID,FDefaultLoc,isnull(FProfitRate,0) as FProfitRate,isnull(FTaxRate,1) as FTaxRate,isnull(FOrderPrice,0) as FOrderPrice,isnull(FSalePrice,0) as FSalePrice,isnull(FPlanPrice,0) as FPlanPrice,FBarcode,FSPID,FBatchManager from t_ICItem where FErpClsID not in (6,8) and FDeleted = 0 order by FName collate Chinese_PRC_CI_AS";//�콢���k3
//        } else {
            sql = "select  t0.FMASTERID,t0.FUSEORGID,t6.FPRODUCEUNITID as 生产单位ID,t5.FPURCHASEUNITID as  采购单位ID,t5.FPURCHASEPRICEUNITID as 采购计价单位ID,t4.FSALEUNITID as 销售单位ID,t4.FSALEPRICEUNITID as 销售计价单位ID,FSTOREUNITID as 库存单位ID,FAUXUNITID as 辅助单位ID,FSTOCKID as 默认仓库ID,FSTOCKPLACEID as 默认仓位ID,FISBATCHMANAGE as 是否启用批号管理,FISKFPERIOD as 是否开启保质期管理,FEXPPERIOD as 保质期,FEXPUNIT as 保质期单位,t2.FISPURCHASE as 允许采购,t2.FISSALE as 允许销售,t2.FISINVENTORY as 允许库存,t2.FISPRODUCE as 允许生产,t2.FISSUBCONTRACT as 允许委外,t2.FISASSET as 允许资产,t2.FBASEUNITID as 基本单位ID,t2.FWEIGHTUNITID as 重量单位ID,t2.FVOLUMEUNITID as 尺寸单位ID,t2.FBARCODE as 条码,t2.FGROSSWEIGHT as 毛重,t2.FNETWEIGHT as 净重,t2.FLENGTH as 长,t2.FWIDTH as 宽,t2.FHEIGHT as 高,t2.FVOLUME as 体积,t1.FMaterialid as 物料内码,t0.FNumber as 编码,t0.FOLDNUMBER as 旧物料编码,t1.FName as 商品名称,t1.FSPECIFICATION as 规格型号,t0.FMNEMONICCODE as 助记码 from T_BD_MATERIAL t0 left join t_bd_material_l t1 on (t0.fmaterialid=t1.fmaterialid AND t1.FLocaleId = 2052) left join t_BD_MaterialBase t2 on t2.fmaterialid=t0.fmaterialid  left join T_BD_MATERIALSTOCK t3 on t3.fmaterialid=t0.fmaterialid left join T_BD_MATERIALSALE t4 on t4.fmaterialid=t0.fmaterialid left join T_BD_MATERIALPURCHASE t5 on t5.fmaterialid=t0.fmaterialid left join T_BD_MATERIALPRODUCE t6 on t6.fmaterialid=t0.fmaterialid  where   (t0.FDOCUMENTSTATUS = 'C' AND t0.FFORBIDSTATUS = 'A') AND t0.FFORBIDSTATUS = 'A'";
        //        }
        try {
            rSet = statement.executeQuery(sql);
            while (rSet.next()) {
                DownloadReturnBean.product bean = dBean.new product();
                bean.FProduceUnitID                = rSet.getString("生产单位ID");
                bean.FPurchaseUnitID               = rSet.getString("采购单位ID");
                bean.FPurchasePriceUnitID          = rSet.getString("采购计价单位ID");
                bean.FSaleUnitID                   = rSet.getString("销售单位ID");
                bean.FSalePriceUnitID              = rSet.getString("销售计价单位ID");
                bean.FStoreUnitID                  = rSet.getString("库存单位ID");
                bean.FAuxUnitID                    = rSet.getString("辅助单位ID");
                bean.FStockID                      = rSet.getString("默认仓库ID");
                bean.FStockPlaceID                 = rSet.getString("默认仓位ID");
                bean.FIsBatchManage                = rSet.getString("是否启用批号管理");
                bean.FIsKFperiod                   = rSet.getString("是否开启保质期管理");
                bean.FExpperiod                    = rSet.getString("保质期");
                bean.FExpUnit                      = rSet.getString("保质期单位");
                bean.FIsPurchase                   = rSet.getString("允许采购");
                bean.FIsSale                       = rSet.getString("允许销售");
                bean.FIsInventory                  = rSet.getString("允许库存");
                bean.FIsProduce                    = rSet.getString("允许生产");
                bean.FIsSubContract                = rSet.getString("允许委外");
                bean.FIsAsset                      = rSet.getString("允许资产");
                bean.FBaseUnitID                   = rSet.getString("基本单位ID");
                bean.FFWeightUnitID                = rSet.getString("重量单位ID");
                bean.FVolumeUnitID                 = rSet.getString("尺寸单位ID");
                bean.FBarcode                      = rSet.getString("条码");
                bean.FGrossWeight                  = rSet.getString("毛重");
                bean.FNetWeight                    = rSet.getString("净重");
                bean.FLenght                       = rSet.getString("长");
                bean.FWidth                        = rSet.getString("宽");
                bean.FHeight                       = rSet.getString("高");
                bean.FVolume                       = rSet.getString("体积");
                bean.FMaterialid                   = rSet.getString("物料内码");
                bean.FNumber                       = rSet.getString("编码");
                bean.FoldNumber                    = rSet.getString("旧物料编码");
                bean.FName                         = rSet.getString("商品名称");
                bean.FModel                        = rSet.getString("规格型号");
                bean.FMnemoniccode                 = rSet.getString("助记码");
                bean.FOrg                          = rSet.getString("FUSEORGID");
                bean.FMASTERID                          = rSet.getString("FMASTERID");

                container.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return container;
    }

    private ArrayList<suppliers> getSuppliers(Statement statement, ResultSet rSet, String version, DownloadReturnBean dBean) {
        ArrayList<suppliers> container = new ArrayList<DownloadReturnBean.suppliers>();
        String sql = "SELECT t1.FSHORTNAME as 简称,t0.FUSEORGID,t0.FSUPPLIERID as 供应商ID,t0.FNUMBER as 供应商编码,t1.FNAME as 供应商名称 FROM t_BD_Supplier t0 LEFT OUTER JOIN t_BD_Supplier_L t1 ON (t0.FSUPPLIERID = t1.FSUPPLIERID AND t1.FLocaleId = 2052) WHERE (t0.FFORBIDSTATUS = 'A')";
        try {
            rSet = statement.executeQuery(sql);
            while (rSet.next()) {
                DownloadReturnBean.suppliers bean = dBean.new suppliers();
                bean.FItemID = rSet.getString("供应商ID");
                bean.FNumber = rSet.getString("供应商编码");
                bean.FName = rSet.getString("供应商名称");
                bean.FOrg = rSet.getString("FUSEORGID");
                bean.FNote = rSet.getString("简称");
//                bean.FItemClassID = rSet.getString("FItemClassID");
//                bean.FParentID = rSet.getString("FParentID");
//                bean.FLevel = rSet.getString("FLevel");
//                bean.FDetail = rSet.getString("FDetail");
//                bean.FAddress = rSet.getString("FAddress");
//                bean.FPhone = rSet.getString("FPhone");
//                bean.FEmail = rSet.getString("FEmail");
                container.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return container;
    }

    //获取单位
    private ArrayList<Unit> getUnit(Statement statement, ResultSet rSet, String version, DownloadReturnBean dBean) {
        ArrayList<Unit> container = new ArrayList<DownloadReturnBean.Unit>();
        String sql = "SELECT t0.FUSEORGID,t0.FUNITID as 单位ID,t0.FNUMBER as 单位编码,t1.FNAME as 单位名称 FROM T_BD_UNIT t0 LEFT OUTER JOIN T_BD_UNIT_L t1 ON (t0.FUNITID = t1.FUNITID AND t1.FLocaleId = 2052) WHERE ((t0.FFORBIDSTATUS='A'))";
        try {
            rSet = statement.executeQuery(sql);
            while (rSet.next()) {
                DownloadReturnBean.Unit bean = dBean.new Unit();
                bean.FMeasureUnitID = rSet.getString("单位ID");
                bean.FNumber = rSet.getString("单位编码");
                bean.FName = rSet.getString("单位名称");
                bean.FOrg = rSet.getString("FUSEORGID");
//                bean.FUnitGroupID = rSet.getString("FUnitGroupID");
//                bean.FCoefficient = rSet.getString("FCoefficient");
                container.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return container;
    }

    private ArrayList<storage> getStorage(Statement statement, ResultSet rSet, String version, DownloadReturnBean dBean) {
        ArrayList<storage> container = new ArrayList<DownloadReturnBean.storage>();
        String sql = null;
//        if (version.contains("5001")) {
//            sql = "select FItemID,FEmpID,FName,FNumber,FTypeID,FSPGroupID,FGroupID," +
//                    "FStockGroupID,FIsStockMgr,FUnderStock from t_Stock where FTypeID " +
//                    "not in (501,502,503) AND FDeleteD=0";//k3 rise 12.3
//        } else if (version.contains("80010" )) {
//            sql = "select t1.FItemID,t1.FEmpID,t1.FName,t1.FNumber,t1.FTypeID,t1.FSPGroupID," +
//                    "t1.FGroupID,t1.FStockGroupID,t1.FIsStockMgr,t1.FUnderStock from t_Stock" +
//                    " t1 left join t_Item t2 on t1.FItemID=t2.FItemID WHERE t2.FItemClassID=5 AND t2.FDetail=1  AND (((FTypeID not in (501,502,503)) and FTypeID <> 504)) AND t2.FDeleteD=0 "; //���k3
//        } else {
            sql = "SELECT t0.FUSEORGID,t0.FSTOCKID as 仓库ID,t0.FNUMBER as 仓库编码,t1.FNAME as 仓库名称,FISOPENLOCATION as 启用仓位,FALLOWMINUSQTY as 允许负库存 FROM t_BD_Stock t0 LEFT OUTER JOIN t_BD_Stock_L t1 ON (t0.FSTOCKID = t1.FSTOCKID AND t1.FLocaleId = 2052) WHERE t0.FFORBIDSTATUS = 'A'";//רҵ��
//        }

        try {
            rSet = statement.executeQuery(sql);
            while (rSet.next()) {
                DownloadReturnBean.storage bean = dBean.new storage();
                bean.FItemID = rSet.getString("仓库ID");
                bean.FName = rSet.getString("仓库名称");
                bean.FNumber = rSet.getString("仓库编码");
                bean.FIsOpenWaveHouse = rSet.getString("启用仓位");
                bean.FallowFStore = rSet.getString("允许负库存");
                bean.FOrg = rSet.getString("FUSEORGID");
//                bean.FEmpID = rSet.getString("FEmpID");
//                bean.FTypeID = rSet.getString("FTypeID");
//                bean.FSPGroupID = rSet.getString("FSPGroupID");
//                bean.FGroupID = rSet.getString("FGroupID");
//                bean.FStockGroupID = rSet.getString("FStockGroupID");
//                bean.FIsStockMgr = rSet.getString("FIsStockMgr");
//                bean.FUnderStock = rSet.getString("FUnderStock");
                container.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return container;
    }

    private ArrayList<wavehouse> getWaveHouse(Statement statement, ResultSet rSet, String version, DownloadReturnBean dBean) {
        ArrayList<wavehouse> container = new ArrayList<>();
        String sql = "SELECT t0.FUSEORGID,t0.FID as 仓位ID,t0.FNUMBER as 仓位编码,t1.FNAME as 仓位名称 FROM T_BAS_FLEXVALUES t0 LEFT OUTER JOIN T_BAS_FLEXVALUES_L t1 ON (t0.FID = t1.FID AND t1.FLocaleId = 2052) WHERE ((t0.FFORBIDSTATUS='A'))";
        try {
            rSet = statement.executeQuery(sql);
            while (rSet.next()) {
                DownloadReturnBean.wavehouse bean = dBean.new wavehouse();
                bean.FSPID = rSet.getString("仓位ID");
                bean.FNumber = rSet.getString("仓位编码");
                bean.FName = rSet.getString("仓位名称");
                bean.FOrg = rSet.getString("FUSEORGID");
//                bean.FSPGroupID = rSet.getString("FSPGroupID");
//                bean.FFullName = rSet.getString("FFullName");
//                bean.FLevel = rSet.getString("FLevel");
//                bean.FDetail = rSet.getString("FDetail");
//                bean.FParentID = rSet.getString("FParentID");
//                bean.FClassTypeID = rSet.getString("FClassTypeID");
//                bean.FDefaultSPID = rSet.getString("FDefaultSPID");
                container.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return container;
    }

    private ArrayList<employee> getEmployee(Statement statement, ResultSet rSet, String version, DownloadReturnBean dBean) {
        ArrayList<employee> container = new ArrayList<>();
        String sql = "SELECT t0.FUSEORGID,t0.FID as 员工ID,t0.FNUMBER as 员工编码,t1.FNAME as 员工名称 FROM T_HR_EMPINFO t0 LEFT OUTER JOIN T_HR_EMPINFO_L t1 ON (t0.FID = t1.FID AND t1.FLocaleId = 2052) WHERE ((t0.FFORBIDSTATUS='A')) and t0.FDOCUMENTSTATUS = 'C'";
        try {
            rSet = statement.executeQuery(sql);
            while (rSet.next()) {
                DownloadReturnBean.employee emp = dBean.new employee();
                emp.FItemID = rSet.getString("员工ID");
                emp.FName = rSet.getString("员工名称");
                emp.FNumber = rSet.getString("员工编码");
                emp.FOrg = rSet.getString("FUSEORGID");
//                emp.FDpartmentID = rSet.getString("FDepartmentID");
//                emp.FEmpGroup = rSet.getString("FEmpGroup");
//                emp.FEmpGroupID = rSet.getString("FEmpGroupID");
                container.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return container;
    }

    private ArrayList<DownloadReturnBean.department> getDepartment(Statement statement, ResultSet rSet, String version, DownloadReturnBean dBean) {
        String sql = "SELECT t0.FUSEORGID,t0.FISSTOCK,t0.FDEPTID as 部门ID,t0.FNUMBER as 部门编码,t1.FNAME as 部门名称 FROM T_BD_DEPARTMENT t0 LEFT OUTER JOIN T_BD_DEPARTMENT_L t1 ON (t0.FDEPTID = t1.FDEPTID AND t1.FLocaleId = 2052) WHERE (t0.FFORBIDSTATUS = 'A')";
        ArrayList<DownloadReturnBean.department> container = new ArrayList<>();
        try {
            rSet = statement.executeQuery(sql);
            while (rSet.next()) {
                DownloadReturnBean.department bean = dBean.new department();
                bean.FItemID = rSet.getString("部门ID");
                bean.FName = rSet.getString("部门名称");
                bean.FNumber = rSet.getString("部门编码");
                bean.FISSTOCK = rSet.getString("FISSTOCK");
                bean.FOrg = rSet.getString("FUSEORGID");
//                bean.FparentID = rSet.getString("FParentID");
                container.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return container;
    }
    private ArrayList<User> getUser(Statement statement, ResultSet rSet, String version, DownloadReturnBean dBean) {
        ArrayList<User> container = new ArrayList<DownloadReturnBean.User>();

//        String sql = "SELECT t0.FUSERID,t0.FNAME as 用户名,t0.FPASSWORD as 密码 FROM T_SEC_user t0 LEFT OUTER JOIN T_SEC_user_L t1 ON (t0.FUSERID = t1.FUSERID AND t1.FLocaleId = 2052) LEFT OUTER JOIN T_SEC_USERGROUP_L st01_L ON (t0.FPRIMARYGROUP = st01_L.FID AND st01_L.FLocaleId = 2052) WHERE (((t0.FCREATEORG IN (1) OR t0.FUSERID IN (SELECT DISTINCT fuserid FROM t_sec_userorg WHERE forgid IN (1))) AND (t0.FFORBIDSTATUS = 'A' AND t0.FUSERTYPE = '1')) ) OPTION ( MAXDOP 0)";
//        String sql = "SELECT t0.FUSERID,t0.FNAME as 用户名,t0.FPASSWORD as 密码,isnull(t2.FCondition,'') as FPermit from  T_SEC_user t0 LEFT OUTER JOIN T_SEC_user_L t1 ON (t0.FUSERID = t1.FUSERID AND t1.FLocaleId = 2052) LEFT OUTER JOIN T_SEC_USERGROUP_L st01_L ON (t0.FPRIMARYGROUP = st01_L.FID AND st01_L.FLocaleId = 2052)  left join t_UserPermitPC t2 on t0.FUserID=t2.FUserID  WHERE t2.FRemark in('一般权限','管理员权限','保管员权限') or t0.FName in('Administrator')  and  (((t0.FCREATEORG IN (1) OR t0.FUSERID IN (SELECT DISTINCT fuserid FROM t_sec_userorg WHERE forgid IN (1))) AND (t0.FFORBIDSTATUS = 'A' AND t0.FUSERTYPE = '1')) ) OPTION ( MAXDOP 0)";
        String sql = "select FUserIDERP as FUSERID,FUserName as 用户名,ISNull(FPASSWORD,'') as 密码,isnull(FCondition,'') as FPermit,FUserNameERP as ERP用户名,FPassWordERP as ERP用户密码 from t_UserPermitPC  where FTypeID=1  and FRemark in('一般权限','管理员权限','保管员权限')";

        try {
            rSet = statement.executeQuery(sql);
            while (rSet.next()) {
                DownloadReturnBean.User bean = dBean.new User();
                bean.FUserID = rSet.getString("FUSERID");
                bean.FName = rSet.getString("用户名");
                bean.FPassWord = rSet.getString("密码");
                bean.FPermit = rSet.getString("FPermit");
                bean.FNameERP = rSet.getString("ERP用户名");
                bean.FPassWordERP = rSet.getString("ERP用户密码");
                container.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return container;
    }


    private ArrayList<InstorageNum> getInstorageNums(Statement statement, ResultSet rSet, String version, DownloadReturnBean dBean) {
        ArrayList<InstorageNum> container = new ArrayList<>();
        String sql = "select t0.FMATERIALID as 商品ID,t0.FSTOCKID as 仓库ID,st035.FNUMBER as 批号,t0.FSTOCKUNITID as 库存单位ID,t0.FBASEQTY as 库存,t0.FSTOCKSTATUSID as 库存状态,t0.FSTOCKORGID as 库存组织ID  from T_STK_INVENTORY t0 LEFT OUTER JOIN T_BD_LOTMASTER st035 ON t0.FLOT = st035.FLOTID  where (((((t0.FSTOCKORGID IN (1) AND t0.FSTOCKORGID = 1) AND FISEFFECTIVED = '1') AND ((t0.FBASEQTY <> 0) OR (t0.FSECQTY <> 0))) AND t0.FSTOCKORGID = 1) AND (t0.FSTOCKORGID IN (1, 0) AND t0.FOBJECTTYPEID = 'STK_Inventory'))";
        try {
            rSet = statement.executeQuery(sql);
            while (rSet.next()) {
                DownloadReturnBean.InstorageNum bean = dBean.new InstorageNum();
                bean.FItemID = rSet.getString("商品ID");
                bean.FStockID = rSet.getString("仓库ID");
                bean.FStoreState = rSet.getString("库存状态");
                bean.FStoreOrgID = rSet.getString("库存组织ID");
                bean.FQty = rSet.getString("库存");
                bean.FUnitID = rSet.getString("库存单位ID");
                bean.FBatchNo = rSet.getString("批号");
                container.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return container;
    }

    private ArrayList<DownloadReturnBean.buyer> getBuyer(Statement statement, ResultSet rSet, String version, DownloadReturnBean dBean) {
        String sql = null;
        ArrayList<DownloadReturnBean.buyer> container = new ArrayList<>();
        sql = "SELECT t0.FBIZORGID,t0.FID as 采购员ID,t0.FNUMBER as 采购员编码,t1.FNAME as 采购员名称,t0.FDEPTID as 所属部门ID FROM  V_BD_BUYER t0 LEFT OUTER JOIN V_BD_BUYER_L t1 ON (t0.FID = t1.FID AND t1.FLocaleId = 2052) WHERE (t0.FFORBIDSTATUS = 'A' ) AND t0.FISUSE = '1'";//רҵ��

        try {
            rSet = statement.executeQuery(sql);
            while (rSet.next()) {
                DownloadReturnBean.buyer bean = dBean.new buyer();
                bean.FID = rSet.getString("采购员ID");
                bean.FNumber = rSet.getString("采购员编码");
                bean.FName = rSet.getString("采购员名称");
                bean.FDeptID = rSet.getString("所属部门ID");
                bean.FOrg = rSet.getString("FBIZORGID");
                container.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return container;
    }

    private ArrayList<DownloadReturnBean.StoreMan> getStoreMan(Statement statement, ResultSet rSet, String version, DownloadReturnBean dBean) {
        String sql = null;
        ArrayList<DownloadReturnBean.StoreMan> container = new ArrayList<>();
        sql = "SELECT t0.FBIZORGID,t0.FID as 仓管员ID,t0.FNUMBER as 仓管员编码,t1.FNAME as 仓管员名称,t0.FDEPTID as 所属部门ID FROM  V_BD_WAREHOUSEWORKERS t0 LEFT OUTER JOIN V_BD_WAREHOUSEWORKERS_L t1 ON (t0.FID = t1.FID AND t1.FLocaleId = 2052) WHERE (t0.FFORBIDSTATUS = 'A' ) AND t0.FISUSE = '1'";//רҵ��

        try {
            rSet = statement.executeQuery(sql);
            while (rSet.next()) {
                DownloadReturnBean.StoreMan bean = dBean.new StoreMan();
                bean.FID = rSet.getString("仓管员ID");
                bean.FNumber = rSet.getString("仓管员编码");
                bean.FName = rSet.getString("仓管员名称");
                bean.FDeptID = rSet.getString("所属部门ID");
                bean.FOrg = rSet.getString("FBIZORGID");
                container.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return container;
    }

    private ArrayList<DownloadReturnBean.SaleMan> getSaleMan(Statement statement, ResultSet rSet, String version, DownloadReturnBean dBean) {
        String sql = null;
        ArrayList<DownloadReturnBean.SaleMan> container = new ArrayList<>();
        sql = "SELECT t0.FBIZORGID,t0.FID as 销售员ID,t0.FNUMBER as 销售员编码,t1.FNAME as 销售员名称,t0.FDEPTID as 所属部门ID FROM  V_BD_SALESMAN t0 LEFT OUTER JOIN V_BD_SALESMAN_L t1 ON (t0.FID = t1.FID AND t1.FLocaleId = 2052) WHERE (t0.FFORBIDSTATUS = 'A' ) and t0.FISUSE = '1'";//רҵ��

        try {
            rSet = statement.executeQuery(sql);
            while (rSet.next()) {
                DownloadReturnBean.SaleMan bean = dBean.new SaleMan();
                bean.FID = rSet.getString("销售员ID");
                bean.FNumber = rSet.getString("销售员编码");
                bean.FName = rSet.getString("销售员名称");
                bean.FDeptID = rSet.getString("所属部门ID");
                bean.FOrg = rSet.getString("FBIZORGID");
                container.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return container;
    }
    private ArrayList<Org> getOrg(Statement statement, ResultSet rSet, String version, DownloadReturnBean dBean) {
        ArrayList<Org> container = new ArrayList<>();
        String sql = "SELECT t0_L.FDESCRIPTION as 货主描述,t0.FNUMBER as 编码, t0_L.FNAME as 名称, t0.FORGID as 组织ID FROM T_ORG_Organizations t0 LEFT OUTER JOIN T_ORG_Organizations_L t0_L ON (t0.FORGID = t0_L.FORGID AND t0_L.FLocaleId = 2052) WHERE (((( ( t0.FDOCUMENTSTATUS = 'C'))  ) AND (t0.FDOCUMENTSTATUS = 'C' AND t0.FFORBIDSTATUS = 'A')))";
        try {
            rSet = statement.executeQuery(sql);
            while (rSet.next()) {
                DownloadReturnBean.Org bean = dBean.new Org();
                bean.FNumber = rSet.getString("编码");
                bean.FName = rSet.getString("名称");
                bean.FOrgID = rSet.getString("组织ID");
                bean.FNote = rSet.getString("货主描述");
                container.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return container;
    }
    private ArrayList<RemarkData> getRemarkData(Statement statement, ResultSet rSet, String version, DownloadReturnBean dBean) {
        ArrayList<RemarkData> container = new ArrayList<>();
        String sql = "select FNUMBER,'' FUSEORGID,FNAME,FSHORTNAME from ( select  t1.FNUMBER,t1.FUSEORGID,t2.FNAME,t2.FSHORTNAME from t_BD_Supplier t1 left join t_BD_Supplier_L t2 on (t1.FSUPPLIERID=t2.FSUPPLIERID) union all " +
                "select  t1.FNUMBER,t1.FUSEORGID,t2.FNAME,t2.FSHORTNAME from t_BD_Customer t1 left join t_BD_Customer_L t2 on (t1.FCUSTID=t2.FCUSTID ) union all " +
                "select t0.FNUMBER,t0.FORGID FUSEORGID,t0_L.FNAME,t0_L.FDESCRIPTION as FSHORTNAME from T_ORG_Organizations t0 LEFT OUTER JOIN T_ORG_Organizations_L t0_L ON (t0.FORGID = t0_L.FORGID AND t0_L.FLocaleId = 2052 )) t where t.FSHORTNAME<>''";
        try {
            rSet = statement.executeQuery(sql);
            while (rSet.next()) {
                DownloadReturnBean.RemarkData bean = dBean.new RemarkData();
                bean.FNUMBER = rSet.getString("FNUMBER");
                bean.FUSEORGID = rSet.getString("FUSEORGID");
                bean.FNAME = rSet.getString("FNAME");
                bean.FSHORTNAME = rSet.getString("FSHORTNAME");
                container.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return container;
    }

    //弃用
    private ArrayList<PriceMethod> getPriceMethods(Statement statement, ResultSet rSet, String version, DownloadReturnBean dBean) {
        ArrayList<PriceMethod> container = new ArrayList<DownloadReturnBean.PriceMethod>();
//        String sql = "select t1.FInterID,t1.FEntryID,t2.FPri,convert(float,FPrice) as FPrice,t2.FName,t1.FItemID,t1.FUnitID,t1.FRelatedID,t1.FBegQty,t1.FEndQty,CONVERT(varchar(50), t1.FBegDate, 23) as FBegDate,CONVERT(varchar(50), t1.FEndDate, 23) as FEndDate from IcPrcPlyEntry t1 left join IcPrcPly t2 on t1.FInterID=t2.FInterID and FChecked=1 and FRelatedID<>0";
//        try {
//            rSet = statement.executeQuery(sql);
//            while (rSet.next()) {
//                DownloadReturnBean.PriceMethod bean = dBean.new PriceMethod();
//                bean.FInterID = rSet.getString("FInterID");
//                bean.FEntryID = rSet.getString("FEntryID");
//                bean.FPri = rSet.getString("FPri");
//                bean.FPrice = rSet.getString("FPrice");
//                bean.FName = rSet.getString("FName");
//                bean.FItemID = rSet.getString("FItemID");
//                bean.FUnitID = rSet.getString("FUnitID");
//                bean.FRelatedID = rSet.getString("FRelatedID");
//                bean.FBegQty = rSet.getString("FBegQty");
//                bean.FEndQty = rSet.getString("FEndQty");
//                bean.FBegDate = rSet.getString("FBegDate");
//                bean.FEndDate = rSet.getString("FEndDate");
//                container.add(bean);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }

        return container;
    }
    //弃用
    private ArrayList<wanglaikemu> getWanglaikemu(Statement statement, ResultSet rSet, String version, DownloadReturnBean dBean) {
        ArrayList<wanglaikemu> container = new ArrayList<DownloadReturnBean.wanglaikemu>();
//        String sql = "SELECT FAccountID,FNumber,FFullName FROM t_Account WHERE ( FDelete=0 Or  FIsAcnt=1)  AND FControlSystem=1 and FLevel=2 ORDER BY FNumber";
//        try {
//            rSet = statement.executeQuery(sql);
//            while (rSet.next()) {
//                DownloadReturnBean.wanglaikemu bean = dBean.new wanglaikemu();
//                bean.FAccountID = rSet.getString("FAccountID");
//                bean.FNumber = rSet.getString("FNumber");
//                bean.FFullName = rSet.getString("FFullName");
//                container.add(bean);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
        return container;
    }
    //弃用
    private ArrayList<InStorageType> getInStoreType(Statement statement, ResultSet rSet, String version, DownloadReturnBean dBean) {
        ArrayList<InStorageType> container = new ArrayList<>();
//        String sql = "select FID,FName from ICBillType where FID in ('1','2')";
//        try {
//            rSet = statement.executeQuery(sql);
//            while (rSet.next()) {
//                DownloadReturnBean.InStorageType bean = dBean.new InStorageType();
//                bean.FID = rSet.getString("FID");
//                bean.FName = rSet.getString("FName");
//                container.add(bean);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }

        return container;
    }
    //弃用
    private ArrayList<yuandanType> getyuandanType(Statement statement, ResultSet rSet, String version, DownloadReturnBean dBean) {
        ArrayList<yuandanType> container = new ArrayList<DownloadReturnBean.yuandanType>();
//        String sql = "select abs(FID) as FID,FName_CHS from ICClassType where FName_CHS in ('产品预测单','销售报价单','发货通知','销售订单','产品入库','采购订单','收料通知单','采购申请','生产任务单','退货通知','外购入库','委外订单')";
//        try {
//            rSet = statement.executeQuery(sql);
//            while (rSet.next()) {
//                DownloadReturnBean.yuandanType bean = dBean.new yuandanType();
//                bean.FID = rSet.getString("FID");
//                bean.FName_CHS = rSet.getString("FName_CHS");
//                container.add(bean);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }

        return container;
    }
    //弃用//弃用
    private ArrayList<purchaseMethod> getPurchaseMethod(Statement statement, ResultSet rSet, String version, DownloadReturnBean dBean) {
        ArrayList<purchaseMethod> container = new ArrayList<DownloadReturnBean.purchaseMethod>();
//        String sql = "SELECT  FTypeID,FInterID FItemID, FID FNumber,FName FROM t_SubMessage Where FInterID>0 AND FDeleted=0  And (FTypeID=162 or FTypeID =101 or FTypeID=997 or FTypeID=32 or FTypeID=33 or FTypeID=668 or FTypeID = 471 or FTypeID=632) AND (FInterID<> 20296 and FInterID<>20298) And (FInterID<> 20296) or (FDeleted=0  And FTypeID=106) ORDER BY FID";
//        try {
//            rSet = statement.executeQuery(sql);
//            while (rSet.next()) {
//                DownloadReturnBean.purchaseMethod bean = dBean.new purchaseMethod();
//                bean.FTypeID = rSet.getString("FTypeID");
//                bean.FItemID = rSet.getString("FItemID");
//                bean.FNumber = rSet.getString("FNumber");
//                bean.FName = rSet.getString("FName");
//                container.add(bean);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
        return container;
    }



    //弃用
    private ArrayList<payType> getpayTypes(Statement statement, ResultSet rSet, String version, DownloadReturnBean dBean) {
        ArrayList<payType> container = new ArrayList<DownloadReturnBean.payType>();
//        String sql = "select FItemID,FName,FNumber  from t_Settle where FItemID!=0";
//        try {
//            rSet = statement.executeQuery(sql);
//            while (rSet.next()) {
//                DownloadReturnBean.payType bean = dBean.new payType();
//                bean.FItemID = rSet.getString("FItemID");
//                bean.FName = rSet.getString("FName");
//                bean.FNumber = rSet.getString("FNumber");
//                container.add(bean);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }

        return container;
    }





    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
