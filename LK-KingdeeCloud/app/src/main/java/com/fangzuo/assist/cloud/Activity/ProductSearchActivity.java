package com.fangzuo.assist.cloud.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Adapter.SearchAdapter;
import com.fangzuo.assist.cloud.Adapter.SearchBatchAdapter;
import com.fangzuo.assist.cloud.Adapter.SearchClientAdapter;
import com.fangzuo.assist.cloud.Adapter.SearchSupplierAdapter;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.SearchBean;
import com.fangzuo.assist.cloud.Dao.BatchDataBean;
import com.fangzuo.assist.cloud.Dao.Client;
import com.fangzuo.assist.cloud.Dao.GetGoodsDepartment;
import com.fangzuo.assist.cloud.Dao.Product;
import com.fangzuo.assist.cloud.Dao.Suppliers;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Utils.Asynchttp;
import com.fangzuo.assist.cloud.Utils.BasicShareUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.GreedDaoUtil.GreenDaoManager;
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.greendao.gen.ClientDao;
import com.fangzuo.greendao.gen.ProductDao;
import com.fangzuo.greendao.gen.SuppliersDao;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductSearchActivity extends BaseActivity {


    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.lv_result)
    ListView lvResult;
    @BindView(R.id.cancle)
    View cancle;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.model)
    TextView model;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.pg)
    ProgressBar pg;
    private String searchString;
    private String searchOrg;
    private int activity;
    private int where;
    private ProductSearchActivity mContext;
    private SearchAdapter ada;
    private List<Product> items1;
    private List<Product> items;
    private List<Product> itemAll;
    private List<Suppliers> itemAllSupplier;
    private List<Suppliers> suppliersList;
    private List<Client> itemAllClient;
    private List<Client> itemClient;
    private List<BatchDataBean> batchDataBeans;
    private List<GetGoodsDepartment> goodsDepartmentList;
    private List<GetGoodsDepartment> goodsDepartmentAllList;
    private String FIsPurchase="";//允许采购
    private String FIsSale="";//允许销售
    private String FIsInventory="";//允许库存
    private String FIsProduce="";//允许生产
    private String FIsSubContract="";//允许委外
    private String FIsAsset="";//允许资产
    private String con="";//条件拼接
    private SearchBean.S2Product s2Product;//用于数据查找...
    @Override
    public void initView() {
        setContentView(R.layout.activity_product_search);
        ButterKnife.bind(this);
        mContext = this;
        Intent in = getIntent();
        Bundle b = in.getExtras();
        searchString = b.getString("search", "");
        searchOrg = b.getString("org", "");
        where = b.getInt("where");
        activity = b.getInt("activity");
        s2Product = new SearchBean.S2Product();
        s2Product.likeOr = searchString;
        s2Product.FOrg = searchOrg;
        toFilter(activity);
        Log.e("searchString", searchString);
        if (where == Info.SEARCHPRODUCT) title.setText("查询结果(物料)");
        if (where == Info.SEARCHSUPPLIER) title.setText("查询结果(供应商)");
        if (where == Info.SearchSupplier) title.setText("查询结果(供应商)");
        if (where == Info.SearchSupplierDetail) title.setText("查询结果(供应商)");
        if (where == Info.SearchClientDetail) title.setText("查询结果(客户)");
        if (where == Info.SEARCHCLIENT) title.setText("查询结果(客户)");
        if (where == Info.SEARCHJH) title.setText("查询结果(交货单位)");
        if (where == Info.Search_Pihao) title.setText("查询结果(批号)");

    }
    public static void start(Context context, String search, String org, int where, int activity) {
        Intent starter = new Intent(context, ProductSearchActivity.class);
        starter.putExtra("search", search);
        starter.putExtra("org", org);
        starter.putExtra("where", where);
        starter.putExtra("activity", activity);
//        starter.putStringArrayListExtra("fid", fid);
        context.startActivity(starter);
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void receiveEvent(ClassEvent event) {
        super.receiveEvent(event);
    }

    @Override
    public void initData() {
        //物料
        if (where == Info.SEARCHPRODUCT) {
            model.setText("编码");
            name.setText("名称");
            if (BasicShareUtil.getInstance(mContext).getIsOL()) {
                Asynchttp.post(mContext, getBaseUrl() + WebApi.S2Product, gson.toJson(new SearchBean(SearchBean.product_for_like,gson.toJson(s2Product))), new Asynchttp.Response() {
                    @Override
                    public void onSucceed(CommonResponse cBean, AsyncHttpClient client) {
                        pg.setVisibility(View.GONE);
                        DownloadReturnBean dBean = new Gson().fromJson(cBean.returnJson, DownloadReturnBean.class);
                        items = dBean.products;
                        Log.e("getProduct:",items.toString());
                        itemAll = new ArrayList<>();
                        itemAll.addAll(items);
                        if (itemAll.size() > 0) {
                            ada = new SearchAdapter(mContext, itemAll);
                            lvResult.setAdapter(ada);
                            ada.notifyDataSetChanged();
                        } else {
                            Toast.showText(mContext, "无数据");
                            setResult(-9998, null);
                            onBackPressed();
                        }
                    }

                    @Override
                    public void onFailed(String Msg, AsyncHttpClient client) {
                        Toast.showText(mContext, Msg);
                    }
                });
            }
            else {
                model.setText("名称");
                name.setText("型号");
                itemAll = new ArrayList<>();
                if (!"".equals(FIsPurchase))con=con+" and FIS_PURCHASE="+FIsPurchase;
                if (!"".equals(FIsProduce))con=con+" and FIS_PRODUCE="+FIsProduce;
                if (!"".equals(FIsSale))con=con+" and FIS_SALE="+FIsSale;
                if (!"".equals(FIsInventory))con=con+" and FIS_INVENTORY="+FIsInventory;
                if (!"".equals(searchString)){
                    con=con+" and (FNAME like '%"+searchString+"%' or FNumber like '%"+searchString+"%')";
                }
                String SQL = "SELECT * FROM PRODUCT WHERE 1=1 "+con;
                Lg.e("SQL:"+SQL);
                Cursor cursor = GreenDaoManager.getmInstance(mContext).getDaoSession().getDatabase().rawQuery(SQL, null);
                while (cursor.moveToNext()) {
                    Product f = new Product();
                    f.FName = cursor.getString(cursor.getColumnIndex("FNAME"));
                    f.FModel = cursor.getString(cursor.getColumnIndex("FMODEL"));
                    f.FNumber = cursor.getString(cursor.getColumnIndex("FNUMBER"));
                    f.FMaterialid = cursor.getString(cursor.getColumnIndex("FMATERIALID"));
                    f.FBarcode = cursor.getString(cursor.getColumnIndex("FBARCODE"));
                    f.FIsBatchManage = cursor.getString(cursor.getColumnIndex("FIS_BATCH_MANAGE"));
                    f.FStockPlaceID = cursor.getString(cursor.getColumnIndex("FSTOCK_PLACE_ID"));
                    f.FStockID = cursor.getString(cursor.getColumnIndex("FSTOCK_ID"));

                    f.FProduceUnitID = cursor.getString(cursor.getColumnIndex("FPRODUCE_UNIT_ID"));
                    f.FPurchaseUnitID = cursor.getString(cursor.getColumnIndex("FPURCHASE_UNIT_ID"));
                    f.FPurchasePriceUnitID = cursor.getString(cursor.getColumnIndex("FPURCHASE_PRICE_UNIT_ID"));
                    f.FSaleUnitID = cursor.getString(cursor.getColumnIndex("FSALE_UNIT_ID"));
                    f.FSalePriceUnitID = cursor.getString(cursor.getColumnIndex("FSALE_PRICE_UNIT_ID"));
                    f.FStoreUnitID = cursor.getString(cursor.getColumnIndex("FSTORE_UNIT_ID"));
                    f.FAuxUnitID = cursor.getString(cursor.getColumnIndex("FAUX_UNIT_ID"));
                    f.FProduceUnitNumber = cursor.getString(cursor.getColumnIndex("FPRODUCE_UNIT_NUMBER"));
                    f.FPurchaseUnitNumber = cursor.getString(cursor.getColumnIndex("FPURCHASE_UNIT_NUMBER"));
                    f.FPurchasePriceUnitNumber = cursor.getString(cursor.getColumnIndex("FPURCHASE_PRICE_UNIT_NUMBER"));
                    f.FSaleUnitNumber = cursor.getString(cursor.getColumnIndex("FSALE_UNIT_NUMBER"));
                    f.FSalePriceUnitNumber = cursor.getString(cursor.getColumnIndex("FSALE_PRICE_UNIT_NUMBER"));
                    f.FStoreUnitNumber = cursor.getString(cursor.getColumnIndex("FSTORE_UNIT_NUMBER"));
                    f.FAuxUnitNumber = cursor.getString(cursor.getColumnIndex("FAUX_UNIT_NUMBER"));
                    f.FUnitGroupID   = cursor.getString(cursor.getColumnIndex("FUNIT_GROUP_ID"));

                    f.FIsPurchase = cursor.getString(cursor.getColumnIndex("FIS_PURCHASE"));
                    f.FIsSale = cursor.getString(cursor.getColumnIndex("FIS_SALE"));
                    f.FIsInventory = cursor.getString(cursor.getColumnIndex("FIS_INVENTORY"));
                    f.FIsProduce = cursor.getString(cursor.getColumnIndex("FIS_PRODUCE"));
                    f.FIsSubContract = cursor.getString(cursor.getColumnIndex("FIS_SUB_CONTRACT"));
                    f.FIsAsset = cursor.getString(cursor.getColumnIndex("FIS_ASSET"));
                    f.FIsKFperiod  = cursor.getString(cursor.getColumnIndex("FIS_KFPERIOD"));
                    f.FExpperiod   = cursor.getString(cursor.getColumnIndex("FEXPPERIOD"));
                    itemAll.add(f);
                }
//                ProductDao productDao = GreenDaoManager.getmInstance(mContext).getDaoSession().getProductDao();
//                items = productDao.queryBuilder().whereOr(
//                        ProductDao.Properties.FNumber.like("%" + searchString + "%"),
//                        ProductDao.Properties.FBarcode.like("%" + searchString + "%"),
//                        ProductDao.Properties.FName.like("%" + searchString + "%")).
//                        orderAsc(ProductDao.Properties.FNumber).limit(50).orderAsc(ProductDao.Properties.FNumber).build().list();
//                itemAll.addAll(items);
                pg.setVisibility(View.GONE);
                if (itemAll.size() > 0) {
                    Lg.e("物料size:"+itemAll.size());
                    ada = new SearchAdapter(mContext, itemAll);
                    lvResult.setAdapter(ada);
                    ada.notifyDataSetChanged();
                } else {
                    Toast.showText(mContext, "无数据");
                    setResult(-9998, null);
                    onBackPressed();
                }
                pg.setVisibility(View.GONE);
            }

            //供应商
        } else if (where == Info.SEARCHSUPPLIER || where == Info.SearchSupplier || where == Info.SearchSupplierDetail) {
            model.setText("编号");
            name.setText("名称");
            if (BasicShareUtil.getInstance(mContext).getIsOL()) {
                Asynchttp.post(mContext, getBaseUrl() + WebApi.SUPPLIERSEARCHLIKE, gson.toJson(new SearchBean(SearchBean.product_for_like,gson.toJson(s2Product))), new Asynchttp.Response() {
                    @Override
                    public void onSucceed(CommonResponse cBean, AsyncHttpClient client) {
                        pg.setVisibility(View.GONE);
                        DownloadReturnBean dBean = new Gson().fromJson(cBean.returnJson, DownloadReturnBean.class);
                        suppliersList = dBean.suppliers;
                        itemAllSupplier = new ArrayList<>();
                        itemAllSupplier.addAll(suppliersList);
                        if (itemAllSupplier.size() > 0) {
                            SearchSupplierAdapter ada1 = new SearchSupplierAdapter(mContext, itemAllSupplier);
                            lvResult.setAdapter(ada1);
                            ada1.notifyDataSetChanged();
                        } else {
                            Toast.showText(mContext, "无数据");
                            setResult(-9998, null);
                            onBackPressed();
                        }
                    }

                    @Override
                    public void onFailed(String Msg, AsyncHttpClient client) {
                        Toast.showText(mContext, Msg);
                    }
                });
            } else{
                SuppliersDao suppliersDao = GreenDaoManager.getmInstance(mContext).getDaoSession().getSuppliersDao();
                List<Suppliers> list = suppliersDao.queryBuilder().
                        where(SuppliersDao.Properties.FOrg.eq(searchOrg)).
                        where(SuppliersDao.Properties.FSupplyClassIfy.notEq("WW")).
                        whereOr(
                            SuppliersDao.Properties.FName.like("%" + searchString + "%"),
                            SuppliersDao.Properties.FNumber.like("%" + searchString + "%")
                ).orderAsc(SuppliersDao.Properties.FItemID).limit(50).build().list();
                itemAllSupplier = new ArrayList<>();
                itemAllSupplier.addAll(list);
                pg.setVisibility(View.GONE);
                if (itemAllSupplier.size() > 0) {
                    SearchSupplierAdapter ada1 = new SearchSupplierAdapter(mContext, itemAllSupplier);
                    lvResult.setAdapter(ada1);
                    ada1.notifyDataSetChanged();
                } else {
                    Toast.showText(mContext, "未查询到数据");
                    setResult(-9998, null);
                    onBackPressed();
                }

            }
                //客户
        } else if (where == Info.SEARCHCLIENT || where == Info.SearchClientDetail) {
            model.setText("编号");
            name.setText("名称");
            if (BasicShareUtil.getInstance(mContext).getIsOL()) {
                Asynchttp.post(mContext, getBaseUrl() + WebApi.CLIENTSEARCHLIKE, gson.toJson(new SearchBean(SearchBean.product_for_like,gson.toJson(s2Product))), new Asynchttp.Response() {
                    @Override
                    public void onSucceed(CommonResponse cBean, AsyncHttpClient client) {
                        pg.setVisibility(View.GONE);
                        DownloadReturnBean dBean = new Gson().fromJson(cBean.returnJson, DownloadReturnBean.class);
                        ClientDao clientDao = daoSession.getClientDao();
                        clientDao.deleteAll();
                        clientDao.insertOrReplaceInTx(dBean.clients);
                        clientDao.detachAll();
                        itemClient = dBean.clients;
                        itemAllClient = new ArrayList<>();
                        itemAllClient.addAll(itemClient);
                        if (itemAllClient.size() > 0) {
                            SearchClientAdapter ada2 = new SearchClientAdapter(mContext, itemAllClient);
                            lvResult.setAdapter(ada2);
                            ada2.notifyDataSetChanged();
                        } else {
                            Toast.showText(mContext, "无数据");
                            setResult(-9998, null);
                            onBackPressed();
                        }
                    }

                    @Override
                    public void onFailed(String Msg, AsyncHttpClient client) {
                        Toast.showText(mContext, Msg);
                    }
                });
            }
//            else{
//                ClientDao clientDao = GreenDaoManager.getmInstance(mContext).getDaoSession().getClientDao();
//                List<Client> clients = clientDao.queryBuilder().whereOr(
//                        ClientDao.Properties.FName.like("%" + searchString + "%"),
//                        ClientDao.Properties.FNumber.like("%" + searchString + "%")
//                ).orderAsc(ClientDao.Properties.FItemID).build().list();
//                itemAllClient = new ArrayList<>();
//                itemAllClient.addAll(clients);
//                if (itemAllClient.size() > 0) {
//                    SearchClientAdapter ada2 = new SearchClientAdapter(mContext, itemAllClient);
//                    lvResult.setAdapter(ada2);
//                    ada2.notifyDataSetChanged();
//                } else {
//                    Toast.showText(mContext, "未查询到数据");
//                    setResult(-9998, null);
//                    onBackPressed();
//                }
//            }
        //交货单位
        }else if (where == Info.Search_Pihao){
//            model.setVisibility(View.GONE);
//            name.setVisibility(View.GONE);
            App.getRService().doIOAction("GetBatchData", searchString, new MySubscribe<CommonResponse>() {
                @Override
                public void onNext(CommonResponse commonResponse) {
                    super.onNext(commonResponse);
                    if (!commonResponse.state)return;
                    pg.setVisibility(View.GONE);
                    DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                    batchDataBeans = dBean.batchDataBeans;
                    if (batchDataBeans.size() > 0) {
                        SearchBatchAdapter ada2 = new SearchBatchAdapter(mContext, batchDataBeans);
                        lvResult.setAdapter(ada2);
                        ada2.notifyDataSetChanged();
                    } else {
                        Toast.showText(mContext, "无数据");
                        setResult(-9998, null);
                        onBackPressed();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                }
            });
        }
//        else if (where == Info.SEARCHJH) {
//            model.setText("编号");
//            name.setText("名称");
//            if (BasicShareUtil.getInstance(mContext).getIsOL()) {
//                Asynchttp.post(mContext, getBaseUrl() + WebApi.SEARCHJHSEARCHLIKE, searchString, new Asynchttp.Response() {
//                    @Override
//                    public void onSucceed(CommonResponse cBean, AsyncHttpClient client) {
//                        DownloadReturnBean dBean = new Gson().fromJson(cBean.returnJson, DownloadReturnBean.class);
//                        goodsDepartmentList = dBean.getGoodsDepartments;
//                        goodsDepartmentAllList = new ArrayList<>();
//                        goodsDepartmentAllList.addAll(goodsDepartmentList);
//                        if (goodsDepartmentAllList.size() > 0) {
//                            SearchDepartmentAdapter ada1 = new SearchDepartmentAdapter(mContext, goodsDepartmentAllList);
//                            lvResult.setAdapter(ada1);
//                            ada1.notifyDataSetChanged();
//                        } else {
//                            Toast.showText(mContext, "无数据");
//                            setResult(-9998, null);
//                            onBackPressed();
//                        }
//                    }
//
//                    @Override
//                    public void onFailed(String Msg, AsyncHttpClient client) {
//                        Toast.showText(mContext, Msg);
//                    }
//                });
//            }else{
//                GetGoodsDepartmentDao getGoodsDepartmentDao = GreenDaoManager.getmInstance(mContext).getDaoSession().getGetGoodsDepartmentDao();
//                List<GetGoodsDepartment> list = getGoodsDepartmentDao.queryBuilder().whereOr(
//                        GetGoodsDepartmentDao.Properties.FNumber.like("%" + searchString + "%"),
//                        GetGoodsDepartmentDao.Properties.FName.like("%" + searchString + "%")
//                ).build().list();
//                goodsDepartmentList = new ArrayList<>();
//                goodsDepartmentList.addAll(list);
//                if (goodsDepartmentList.size() > 0) {
//                    SearchDepartmentAdapter ada3 = new SearchDepartmentAdapter(mContext, goodsDepartmentList);
//                    lvResult.setAdapter(ada3);
//                    ada3.notifyDataSetChanged();
//                } else {
//                    Toast.showText(mContext, "未查询到数据");
//                    setResult(-9998, null);
//                    onBackPressed();
//                }
//            }
//
//        }

    }

    @Override
    public void initListener() {
        lvResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mIntent = new Intent();
                Bundle b = new Bundle();
                if (where == Info.SEARCHPRODUCT) {
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Product,itemAll.get(i)));
                    onBackPressed();
                } else if (where == Info.SEARCHSUPPLIER) {
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Supplier,itemAllSupplier.get(i)));
                    onBackPressed();
                } else if (where == Info.SearchSupplier) {
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Supplier_Hz,itemAllSupplier.get(i)));
                    onBackPressed();
                } else if (where == Info.SearchSupplierDetail) {
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Supplier_Hz_Detail,itemAllSupplier.get(i)));
                    onBackPressed();
                } else if (where == Info.SEARCHCLIENT) {
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Client,itemAllClient.get(i)));
                    onBackPressed();
                } else if (where == Info.SearchClientDetail) {
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Client_Detail,itemAllClient.get(i)));
                    onBackPressed();
                } else if (where == Info.SEARCHJH) {
                    b.putString("001", goodsDepartmentList.get(i).FItemID);
                    b.putString("002", goodsDepartmentList.get(i).FName);
                    mIntent.putExtras(b);
                    setResult(Info.SEARCHFORRESULTJH, mIntent);
                    onBackPressed();
                }else if (where == Info.Search_Pihao){
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Search_Pihao,batchDataBeans.get(i)));
                    onBackPressed();

                }

            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void OnReceive(String code) {

    }

    //根据activity过滤是否物料（是否允许生产，是否允许采购等)
    private void toFilter(int activity){
        switch (activity){
            case Config.OutsourcingInActivity://采购订单下推外购入库单
            case Config.PdCgOrder2WgrkActivity://采购订单下推外购入库单
            case Config.PurchaseInStoreActivity://采购入库
                s2Product.FIsPurchase="1";
                s2Product.FVal1="<>'WW'";
                FIsPurchase="1";
                break;
            case Config.WorkOrgIn4P2Activity://产品入库
            case Config.ProductInStoreActivity://产品入库
            case Config.TbInActivity://挑板入库
            case Config.ChangeInActivity://挑板入库
            case Config.ChangeLvInActivity://挑板入库
            case Config.ZbCheJianInActivity://挑板入库
            case Config.Bg1CheJianInActivity://挑板入库
            case Config.CpWgInActivity://挑板入库
            case Config.Bg2CheJianInActivity://挑板入库
            case Config.ChangeModelInActivity://挑板入库
            case Config.SplitBoxInActivity://挑板入库
            case Config.ZbIn1Activity://挑板入库
            case Config.ZbIn2Activity://挑板入库
            case Config.ZbIn3Activity://挑板入库
            case Config.ZbIn4Activity://挑板入库
            case Config.ZbIn5Activity://挑板入库
            case Config.GbInActivity://改版入库
            case Config.DhInActivity://到货入库
            case Config.DhIn2Activity://到货入库
            case Config.SimpleInActivity://产品入库
                s2Product.FIsProduce="1";
                FIsProduce="1";
                break;
            case Config.WorkOrgGet4P2Activity://生产领料
            case Config.ProductGet4P2Activity://生产领料
            case Config.ProductGetActivity://生产领料
            case Config.TbGetActivity://挑板领料
            case Config.TbGet2Activity://挑板领料
            case Config.TbGet3Activity://挑板领料
            case Config.GbGetActivity://改板领料
                s2Product.FIsInventory="1";
                FIsInventory="1";
                break;
            case Config.SaleOutActivity://销售出库
                s2Product.FIsSale="1";
                FIsSale="1";
                break;
            case Config.OtherInStoreActivity://其他入库
            case Config.HwIn3Activity://第三方货物入库
                s2Product.FIsInventory="1";
                FIsInventory="1";
                break;
            case Config.SupplierGet4P2Activity://其他出库
            case Config.YbOut4P2Activity://其他出库
            case Config.OtherOutStoreActivity://其他出库
            case Config.YbOutActivity://样板出库
            case Config.HwOut3Activity://第三方货物出库
                s2Product.FIsInventory="1";
                FIsInventory="1";
                break;
            case Config.SaleOrderActivity://销售订单
                s2Product.FIsSale="1";
                FIsSale="1";
                break;
            case Config.PurchaseOrderActivity://采购订单
                s2Product.FIsPurchase="1";
                FIsPurchase="1";
                break;
        }
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        finish();
        this.overridePendingTransition(R.anim.bottom_end, 0);
    }

    @Override
    public void onBackPressed() {
        finish();
        this.overridePendingTransition(0, R.anim.bottom_end);
    }



}
