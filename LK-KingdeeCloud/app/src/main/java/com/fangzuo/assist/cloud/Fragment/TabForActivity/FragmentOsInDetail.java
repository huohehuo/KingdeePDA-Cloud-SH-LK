package com.fangzuo.assist.cloud.Fragment.TabForActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseFragment;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Activity.PagerForActivity;
import com.fangzuo.assist.cloud.Activity.PrintOutTestActivity;
import com.fangzuo.assist.cloud.Activity.ProductCheckActivity;
import com.fangzuo.assist.cloud.Activity.ProductSearchActivity;
import com.fangzuo.assist.cloud.Activity.ReViewActivity;
import com.fangzuo.assist.cloud.Activity.ReViewDataActivity;
import com.fangzuo.assist.cloud.Adapter.PushDownListAdapter;
import com.fangzuo.assist.cloud.Adapter.SearchAdapter;
import com.fangzuo.assist.cloud.Beans.BackData;
import com.fangzuo.assist.cloud.Beans.BlueToothBean;
import com.fangzuo.assist.cloud.Beans.CodeCheckBackDataBean;
import com.fangzuo.assist.cloud.Beans.CodeCheckBean;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.PrintHistory;
import com.fangzuo.assist.cloud.Beans.SearchBean;
import com.fangzuo.assist.cloud.Dao.Product;
import com.fangzuo.assist.cloud.Dao.PushDownMain;
import com.fangzuo.assist.cloud.Dao.Storage;
import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.Dao.T_main;
import com.fangzuo.assist.cloud.Dao.Unit;
import com.fangzuo.assist.cloud.Dao.WaveHouse;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Service.BaseUtilService;
import com.fangzuo.assist.cloud.Service.DataService;
import com.fangzuo.assist.cloud.Utils.Asynchttp;
import com.fangzuo.assist.cloud.Utils.BasicShareUtil;
import com.fangzuo.assist.cloud.Utils.CommonUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.DataModel;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.GreedDaoUtil.GreenDaoManager;
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.MathUtil;
import com.fangzuo.assist.cloud.Utils.MediaPlayer;
import com.fangzuo.assist.cloud.Utils.ShareUtil;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.UpLoadModel;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.fangzuo.assist.cloud.widget.MyWaveHouseSpinner;
import com.fangzuo.assist.cloud.widget.SpinnerActualModel;
import com.fangzuo.assist.cloud.widget.SpinnerAuxSign;
import com.fangzuo.assist.cloud.widget.SpinnerStorage;
import com.fangzuo.assist.cloud.widget.SpinnerUnit;
import com.fangzuo.greendao.gen.DaoSession;
import com.fangzuo.greendao.gen.PushDownMainDao;
import com.fangzuo.greendao.gen.T_DetailDao;
import com.fangzuo.greendao.gen.T_mainDao;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.orhanobut.hawk.Hawk;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import zpSDK.zpSDK.zpBluetoothPrinter;


//选择单据信息Fragment（所属：PushDownPagerActivity);
public class FragmentOsInDetail extends BaseFragment {

    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.ed_code)
    EditText edCode;
    @BindView(R.id.ed_pihao)
    EditText edPihao;
    @BindView(R.id.tv_goodName)
    AppCompatTextView tvGoodName;
    @BindView(R.id.tv_model)
    TextView tvModel;
    @BindView(R.id.sp_unit)
    SpinnerUnit spUnit;
//    @BindView(R.id.sp_unit_store)
//    SpinnerUnit spUnitStore;
//    @BindView(R.id.ed_storenum)
//    EditText edStorenum;
//    @BindView(R.id.sp_unit_jiben)
//    SpinnerUnit spUnitJiben;
//    @BindView(R.id.ed_basenum)
//    EditText edBasenum;
    @BindView(R.id.ed_num)
    EditText edNum;
    @BindView(R.id.sp_wavehouse)
    MyWaveHouseSpinner spWavehouse;
//    @BindView(R.id.sp_auxsign)
//    SpinnerAuxSign spAuxsign;
//    @BindView(R.id.sp_actualmodel)
//    SpinnerActualModel spActualmodel;
    @BindView(R.id.ed_purchase_no)
    EditText edPurchaseNo;
    @BindView(R.id.sp_which_storage)
    SpinnerStorage spWhichStorage;
    @BindView(R.id.tv_createdate)
    TextView tvCreateDate;
    private boolean IsOpenCreateManager;

    private FragmentActivity mContext;
    private PagerForActivity activityPager;
    Unbinder unbinder;
    private long ordercode;
    private Product product;
    //    private Storage storage;
    private WaveHouse waveHouse;
    private Unit unit;
    private CodeCheckBackDataBean codeCheckBackDataBean;
    protected boolean isOpenBatch = false;
    private List<String> listOrder;
    private Gson gson;
    private DaoSession daoSession;
    private T_mainDao t_mainDao;
    private T_DetailDao t_detailDao;
    private ShareUtil share;
    private int activity;
    private String barcode = "";
    private String auxNum = "";
    private String batch = "";
    private String baseNum = "";
    private String storeNum = "";

    private DaoSession daosession;
    private ArrayList<Boolean> isCheck;
    private int year;
    private int month;
    private int day;
    private String enddate;
    private String startdate;
    private PushDownMainDao pushDownMainDao;
    //    private SupplierSpAdapter supplierAdapter;
//    private ClientSpAdapter clientSpAdapter;
    private String supplierID;
    private boolean defaultsp = false;
    private List<PushDownMain> container;               //单据信息，用于存储查找到的单据数据
    private ArrayList<PushDownMain> downloadIDs;        //单据信息，用于存储选中的单据数据
    private PushDownListAdapter pushDownListAdapter;
    private Intent intent;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.Login_User:
                String res = (String) event.postEvent;
                LoadingUtil.dismiss();
                if ("OK".equals(res)){
                    LoadingUtil.showDialog(mContext, "正在上传...");
                    UpLoadModel.action(mContext, activity);
                }else{
                    LoadingUtil.showAlter(mContext,res);
                }
                break;
            case EventBusInfoCode.Product:
                product = (Product) event.postEvent;
                edCode.setText(product.FNumber);
                Lg.e("获得物料信息：", product);
//                binding.setProduct(product);
                dealProduct();
                break;
            case EventBusInfoCode.Upload_OK://回单成功
                BackData backData = (BackData) event.postEvent;
                if (backData.getResult().getResponseStatus().getIsSuccess()) {
                    //获取生成的单号数据
                    for (int i = 0; i < backData.getResult().getResponseStatus().getSuccessEntitys().size(); i++) {
                        listOrder.add(backData.getResult().getResponseStatus().getSuccessEntitys().get(i).getNumber());
                    }
                    final List<T_main> mains = t_mainDao.queryBuilder().where(
                            T_mainDao.Properties.Activity.eq(activity),
                            T_mainDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
                    ).build().list();
                    for (int i = 0; i < mains.size(); i++) {
                        final int pos = i;
                                t_detailDao.deleteInTx(t_detailDao.queryBuilder().where(
                                        T_DetailDao.Properties.Activity.eq(activity),
                                        T_DetailDao.Properties.FAccountID.eq(CommonUtil.getAccountID()),
                                        T_DetailDao.Properties.FOrderId.eq(mains.get(pos).FOrderId)
                                ).build().list());
//                        String reString = mains.get(i).FBillerID + "|" + listOrder.get(i) + "|" + mains.get(i).FOrderId + "|" + mains.get(i).IMIE;
//                        App.getRService().doIOAction(WebApi.PrISUpload, reString, new MySubscribe<CommonResponse>() {
//                            @Override
//                            public void onNext(CommonResponse commonResponse) {
//                                super.onNext(commonResponse);
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                super.onError(e);
//                            }
//                        });
                    }
                    ordercode++;
                    Log.e("ordercode", ordercode + "");
                    share.setOrderCode(activityPager.getActivity(), ordercode);
                    MediaPlayer.getInstance(mContext).ok();
                    Toast.showText(mContext, "上传成功");
//                btnBackorder.setClickable(true);
                    LoadingUtil.dismiss();
                    DataModel.submitAndAudit(mContext,Config.PurchaseInStoreActivity,listOrder.get(0));
                } else {
                    LoadingUtil.dismiss();
                    List<BackData.ResultBean.ResponseStatusBean.ErrorsBean> errorsBeans = backData.getResult().getResponseStatus().getErrors();
                    StringBuilder builder = new StringBuilder();
                    for (BackData.ResultBean.ResponseStatusBean.ErrorsBean error : errorsBeans) {
                        builder.append(error.getFieldName() + "\n");
                        builder.append(error.getMessage() + "\n");
                    }
                    AlertDialog.Builder delete = new AlertDialog.Builder(activityPager);
                    delete.setTitle("上传错误");
                    delete.setMessage(builder.toString());
                    delete.setPositiveButton("确定", null);
                    delete.setNegativeButton("反馈信息", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DataService.pushBackJson(mContext, FragmentOsInDetail.this.getClass().getSimpleName(), Hawk.get(Config.Company,""));
                        }
                    });
                    delete.create().show();
                }

                break;
            case EventBusInfoCode.Upload_Error://回单失败
                String error = (String) event.postEvent;
                Toast.showText(mContext, error);
//                btnBackorder.setClickable(true);
                LoadingUtil.dismiss();
                MediaPlayer.getInstance(mContext).error();
                break;
//            case EventBusInfoCode.Code_Check://条码检测
//                LoadingUtil.dismiss();
//                codeCheckBackDataBean = (CodeCheckBackDataBean)event.postEvent;
//                if (codeCheckBackDataBean.FTip.equals("OK")){
//                    binding.edPihao.setText(codeCheckBackDataBean.FBatchNo);
//                    binding.edNum.setText(codeCheckBackDataBean.FQty);
//                    LoadingUtil.showDialog(mContext,"正在查找物料信息");
//                    DataModel.getProductForNumber(codeCheckBackDataBean.FItemID,org);
//                }else{
//                    ReSetScan(binding.cbScaning);
//                    Toast.showText(mContext,codeCheckBackDataBean.FTip);
//                }
//                break;
            case EventBusInfoCode.Code_Only_Insert://写入条码唯一临时表
                codeCheckBackDataBean = (CodeCheckBackDataBean) event.postEvent;
                if (codeCheckBackDataBean.FTip.equals("OK")) {
                    Addorder();
                } else {
                    Toast.showText(mContext, codeCheckBackDataBean.FTip);
                }
                break;
            case EventBusInfoCode.UpdataView://由表头的数据决定是否更新明细数据
                if (null != activityPager) {
                    spWhichStorage.setAuto("", activityPager.getOrgOut());
//                    spDepartmentGet.setAuto(getString(R.string.spDepartmentGet_pis), "", activityPager.getOrgOut(), activityPager.getActivity());
//                    spUnit.setAuto("", SpinnerUnit.Id);
//                    spUnitJiben.setAuto("", SpinnerUnit.Id);
//                    spUnitStore.setAuto("", SpinnerUnit.Id);
//                    spUnitAux.setAuto("", activityPager.getOrgOut(), SpinnerUnit.Id);
                }
                break;
            case EventBusInfoCode.UpdataWaveHouse://检测打印机连接状态.
//                Storage storage = (Storage) event.postEvent;
                break;

        }
    }

    public FragmentOsInDetail() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        activityPager = ((PagerForActivity) activity);
        Lg.e("Fg_D:" + "onAttach");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_osidetail, container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = getActivity();
        EventBusUtil.register(this);
        Lg.e("Fg_D:" + "onCreateView");
        return view;
    }

    @Override
    protected void initView() {
        Lg.e("Fg_D:" + "initView");
        activity = activityPager.getActivity();
        t_mainDao = activityPager.getT_mainDao();
        daoSession = activityPager.getDaoSession();
        t_detailDao = activityPager.getT_detailDao();
        ordercode = activityPager.getOrdercode();
        gson = activityPager.getGson();
        share = activityPager.getShare();
//        spUnitJiben.setEnabled(false);
//        spUnitStore.setEnabled(false);
//        spUnitAux.setEnabled(false);
    }

    @Override
    protected void initData() {
        Lg.e("Fg_D:" + "initData");
        listOrder = new ArrayList<>();
        spWhichStorage.setAuto("","", activityPager.getOrgOut());


    }

    private void updataView() {

    }

    //在oncreateView之前使用 不要使用控件
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume Lg.e("fragment显示");
//            if (null!=activityPager){
//                spWhichStorage.setAuto("",activityPager.getOrgOut());
//                spDepartmentGet.setAuto(getString(R.string.spDepartmentGet_pis),"",activityPager.getOrgOut());
//                spUnit.setAuto("", activityPager.getOrgOut(),SpinnerUnit.Id);
//                spUnitAux.setAuto("", activityPager.getOrgOut(),SpinnerUnit.Id);
//            }
        } else {

            //相当于Fragment的onPause Lg.e("fragment隐藏");
        }
    }

    @Override
    protected void OnReceive(String code) {
        Lg.e("barcode",code);
        barcode =code;
        List<String> list = CommonUtil.ScanBack(code);
        if (list.size()>0){
            edNum.setText(list.get(1));
            getProduct(list.get(0));
        }
    }
    private SearchBean.S2Product s2Product;//用于数据查找...
    private void getProduct(String barcode){
        LoadingUtil.showDialog(mContext,"正在查找物料信息...");
        s2Product = new SearchBean.S2Product();
        s2Product.likeOr = barcode;
        s2Product.FOrg = activityPager.getOrgOut(1);
        toFilter(activity);//设置查询条件
        if (BasicShareUtil.getInstance(mContext).getIsOL()) {
            App.getRService().doIOAction(WebApi.S2Product, gson.toJson(new SearchBean(SearchBean.product_for_barcode,gson.toJson(s2Product))) , new MySubscribe<CommonResponse>() {
                @Override
                public void onNext(CommonResponse commonResponse) {
                    super.onNext(commonResponse);
                    LoadingUtil.dismiss();
                    DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                    if (null !=dBean.products && dBean.products.size()>0){
                        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Product,dBean.products.get(0)));
                    }else{
                        Toast.showText(mContext, "无数据");
                    }
                }

                @Override
                public void onError(Throwable e) {
//                    super.onError(e);
                    Toast.showText(mContext, "查询物料失败");
                }
            });
        }
        else {
            con = "";
            if (!"".equals(FIsPurchase))con=con+" and FIS_PURCHASE="+FIsPurchase;
            if (!"".equals(FIsProduce))con=con+" and FIS_PRODUCE="+FIsProduce;
            if (!"".equals(FIsSale))con=con+" and FIS_SALE="+FIsSale;
            if (!"".equals(FIsInventory))con=con+" and FIS_INVENTORY="+FIsInventory;
            if (!"".equals(barcode)){
                con=con+" and FBARCODE = '"+barcode+"'";
            }
            Lg.e("条件",con);
            String SQL = "SELECT * FROM PRODUCT WHERE 1=1 "+con;
            Lg.e("SQL:"+SQL);
            Cursor cursor = GreenDaoManager.getmInstance(mContext).getDaoSession().getDatabase().rawQuery(SQL, null);
            List<Product> list = new ArrayList<>();
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
                list.add(f);
            }
            LoadingUtil.dismiss();
            if (list.size() > 0) {
                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Product,list.get(0)));
            } else {
                Toast.showText(mContext, "无数据");
            }
        }
    }
    private String FIsPurchase="";//允许采购
    private String FIsSale="";//允许销售
    private String FIsInventory="";//允许库存
    private String FIsProduce="";//允许生产
    private String FIsSubContract="";//允许委外
    private String FIsAsset="";//允许资产
    private String con="";//条件拼接
    //根据activity过滤是否物料（是否允许生产，是否允许采购等)
    private void toFilter(int activity){
        switch (activity){
            case Config.OutsourcingInActivity://采购订单下推外购入库单
            case Config.PdCgOrder2WgrkActivity://采购订单下推外购入库单
            case Config.PurchaseInStoreActivity://采购入库
                s2Product.FIsPurchase="1";
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


    @Override
    protected void initListener() {
        tvCreateDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!IsOpenCreateManager){
                    tvCreateDate.setText("");
                    Toast.showText(mContext,"未开启生产日期管理");
                }else{
                    datePickerWithData(tvCreateDate,tvCreateDate.getText().toString());
                }
            }
        });

        spWhichStorage.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                Storage storage = (Storage) spWhichStorage.getAdapter().getItem(i);
                activityPager.setStorage(storage);
                spWhichStorage.setTitleText(storage.FName);
                waveHouse = null;
                spWavehouse.setAuto(mContext, storage, "");
                Lg.e("选中仓库：", storage);
            }
        });
        spWavehouse.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                waveHouse = (WaveHouse) spWavehouse.getAdapter().getItem(i);
                Lg.e("选中仓位：", waveHouse);
            }
        });
        spUnit.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                unit = (Unit) spUnit.getAdapter().getItem(i);
                Lg.e("单位选择",unit);
            }
        });

    }

    //处理物料数据
    private void dealProduct() {
        if (product == null) {
            return;
        }
        tvGoodName.setText(product.FName);
        tvModel.setText(product.FModel);
        tvCode.setText(product.FNumber);


        if ((product.FIsKFperiod) != null && (product.FIsKFperiod).equals("1")) {
            IsOpenCreateManager = true;
            tvCreateDate.setText(CommonUtil.getTime(true));
//                tvCreateDate.setText(CommonUtil.dealCreateDate(getTime(true),MathUtil.toInt("".equals(shelfLife4Code)?product.FKFPeriod:shelfLife4Code)));
        } else {
            tvCreateDate.setText("");
            IsOpenCreateManager = false;
        }

        //带出物料的默认值
        spUnit.setAuto(product.FUnitGroupID,product.FPurchaseUnitNumber, SpinnerUnit.Number);
//        spUnitJiben.setAuto(product.FBaseUnitID, SpinnerUnit.Id);
//        spUnitStore.setAuto(product.FStoreUnitID, SpinnerUnit.Id);
//        spUnitAux.setAuto(product.FAuxUnitID, activityPager.getOrgOut(), SpinnerUnit.Id);
//        if (activityPager.isStorage()) {
            Lg.e("更新仓库");
//            spWhichStorage.setAutoSelection("", product.FStockID);
//            spWhichStorage.setAuto(product.FStockID, activityPager.getOrgOut());
            EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.UpdataStorage, product.FStockID));

//        }
        if (CommonUtil.isOpen(product.FIsBatchManage)) {
            isOpenBatch = true;
            edPihao.setEnabled(true);
            edPihao.setHint("请输入批号");
        } else {
            edPihao.setHint("未启用批号管理");
            edPihao.setEnabled(false);
            edPihao.setText("");
            isOpenBatch = false;
        }

//        spAuxsign.getData(product.FMASTERID, "");
//        spActualmodel.getData(product.FMASTERID, "");

        //自动添加
        Lg.e("自动添加",activityPager.getIsAuto().isChecked());
        if (activityPager.getIsAuto().isChecked()) {
            if (!checkBeforeAdd()) {
//                ReSetScan(cbScaning);
            }
        } else {
//            ReSetScan(cbScaning);
        }
    }

    //添加前检测
    private boolean checkBeforeAdd() {
        if (product == null) {
            Toast.showText(mContext, "请选择物料");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        if (activityPager.getStorage().FName.equals("")) {
            Toast.showText(mContext, "请选择仓库");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        if (IsOpenCreateManager && "".equals(tvCreateDate.getText().toString())){
            MediaPlayer.getInstance(mContext).error();
            Toast.showText(mContext, "请选择生产日期");
            return false;
        }
        if (IsOpenCreateManager && MathUtil.toD(product.FExpperiod)<=0){
            MediaPlayer.getInstance(mContext).error();
            Toast.showText(mContext, "开启保质期管理时，保质期不能小于等于 0,否则不能添加");
            return false;
        }
        if (activityPager.getSuppliers().FName.equals("")) {
            Toast.showText(mContext, "供应商不能为空");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        if (activityPager.getDepartMent().equals("")) {
            Toast.showText(mContext, "生产车间不能为空");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }

        if (isOpenBatch && edPihao.getText().toString().trim().equals("")) {
            Toast.showText(mContext, "请输入批号信息");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        //--------------------------------------------------
        if (edNum.getText().toString().trim().equals("") || "0".equals(edNum.getText().toString())) {
            Toast.showText(mContext, "请输入数量");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }//--------------------------------------------------
        Addorder();

//        LoadingUtil.showDialog(mContext, "正在获取条码数据...");
//        String pdata = product.FMaterialid + "|" + spUnit.getDataId() + "|" + edNum.getText().toString().trim()
//                + "|" + spActualmodel.getDataNumber() + "|" + spAuxsign.getDataNumber() + "|" + edPurchaseNo.getText().toString()
//                + "|" + BasicShareUtil.getInstance(mContext).getIMIE() + "|" + activityPager.getStorage().FNumber + "|" + activityPager.getHuozhuOut(0);
//        App.getRService().doIOAction(WebApi.PrintData, pdata, new MySubscribe<CommonResponse>() {
//            @Override
//            public void onNext(CommonResponse commonResponse) {
//                super.onNext(commonResponse);
//                LoadingUtil.dismiss();
//                if (!commonResponse.state) return;
//                DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
//                if (null != dBean && dBean.printDataBeans.size() > 0) {
//                    barcode = dBean.printDataBeans.get(0).FBarCode;
//                    auxNum = dBean.printDataBeans.get(0).FAuxNum;
//                    batch = dBean.printDataBeans.get(0).FBatch;
//                    baseNum = dBean.printDataBeans.get(0).FBaseNum;
//                    storeNum = dBean.printDataBeans.get(0).FStoreNum;
//                    edBasenum.setText(baseNum);
//                    edStorenum.setText(storeNum);
//                    //把需要打印的数据保存到本地
//                    PrintHistory printHistory = new PrintHistory();
//                    printHistory.setData(product, spUnitStore.getDataObject(), spUnitJiben.getDataObject(), storeNum,
//                            baseNum, spWavehouse.getWaveHouseId(), activityPager.getNote(),
//                            activityPager.getOrgOut().FNote, barcode, batch, CommonUtil.getTime(true), "",spAuxsign.getDataNumber(),spActualmodel.getDataNumber());
//                    daoSession.getPrintHistoryDao().insert(printHistory);
//
//                    //-----END
//                    CodeCheckBean bean = new CodeCheckBean(barcode, ordercode + "", activityPager.getStorage().FItemID, waveHouse == null ? "" : waveHouse.FSPID, BasicShareUtil.getInstance(mContext).getIMIE());
//                    DataModel.codeOnlyInsert(WebApi.CodeCheckInsertForIn, gson.toJson(bean));
//                } else {
//                    Toast.showText(mContext, "生成条码失败，请重试");
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//                LoadingUtil.dismiss();
////                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Code_Only_Insert,new CodeCheckBackDataBean("找不到条码信息")));
//            }
//        });

//        Addorder();
        return true;
    }

    //添加数据
    private void Addorder() {
        try {
            String num = edNum.getText().toString();
            String auxNum="1";
            if ("".equals(num)||"0".equals(num))return;//避免多次点击，以上请求多次，导致第一次清空之后，再去添加一个空的数据
//            if (activityPager.getIsHebing().isChecked()) {
            if (true) {
                Lg.e("合并");
                List<T_Detail> detailhebing = t_detailDao.queryBuilder().where(
                        T_DetailDao.Properties.Activity.eq(activity),
                        T_DetailDao.Properties.FOrderId.eq(ordercode),
                        T_DetailDao.Properties.FMaterialId.eq(product.FNumber),
                        T_DetailDao.Properties.FUnitID.eq(spUnit.getDataNumber()),
                        T_DetailDao.Properties.FBarcode.eq(barcode),
                        T_DetailDao.Properties.FStorageId.eq(activityPager.getStorage().FNumber),
                        T_DetailDao.Properties.FWaveHouseId.eq(spWavehouse.getwaveHouseNumber()),
                        T_DetailDao.Properties.FProduceDate.eq(IsOpenCreateManager?tvCreateDate.getText().toString():""),
                        T_DetailDao.Properties.FExpPeriod.eq(IsOpenCreateManager?product.FExpperiod:"0"),
                        T_DetailDao.Properties.FBatch.eq(edPihao.getText().toString())
                ).build().list();
                Lg.e("合并：" + detailhebing.size(), detailhebing);
                if (detailhebing.size() > 0) {
                    for (int i = 0; i < detailhebing.size(); i++) {
                        num = MathUtil.D2save5(MathUtil.toD(num) + MathUtil.toD(detailhebing.get(i).FRemainInStockQty)) + "";
                        if (null !=product.FAuxUnitID && !"0".equals(product.FAuxUnitID)){
                            auxNum = (MathUtil.toInt(auxNum)+MathUtil.toInt(detailhebing.get(i).FAuxQty))+"";
                        }
                        t_detailDao.delete(detailhebing.get(i));
                    }
                }
            }

            t_mainDao.deleteInTx(t_mainDao.queryBuilder().where(
                    T_mainDao.Properties.FOrderId.eq(ordercode)
            ).build().list());
            String timesecond = CommonUtil.getTimesecond();
            T_main main = new T_main();//--------------------------------------表头-----------------
            main.activity = activity;
            main.FAccountID = CommonUtil.getAccountID();
            main.FBillerID = Hawk.get(Info.user_id, "");
            main.FBarcode = barcode;
            main.IMIE = BasicShareUtil.getInstance(mContext).getIMIE();
            main.FOrderId = ordercode;
            main.FIndex = timesecond;
            main.setData(Info.getType(activity), activityPager.getOrgIn(0), activityPager.getOrgOut(0), activityPager.getOrgOut(0));
            main.FDepartmentNumber = activityPager.getDepartMent();
            main.FPurchaseDeptId = activityPager.getDepartMentBuy();
            main.FPurchaserId = activityPager.getManGet();
            main.FStockerNumber = activityPager.getManStore();
            main.FDate = activityPager.getDate();
            main.FNot = activityPager.getNote();
            main.F_FFF_Text = activityPager.getFOrderNo();
            main.setSupplier(activityPager.getSuppliers());
            long insert1 = t_mainDao.insert(main);


            T_Detail detail = new T_Detail();//--------------------------------明细-----------------
            detail.activity = activity;
            detail.FAccountID = CommonUtil.getAccountID();
            detail.FBillerID = Hawk.get(Info.user_id, "");
            detail.FBarcode = barcode;
            detail.IMIE = BasicShareUtil.getInstance(mContext).getIMIE();
            detail.FOrderId = ordercode;
            detail.FIndex = timesecond;
            detail.FRemainInStockQty = num;
            detail.FRealQty = num;
            detail.FAuxQty = auxNum;
            detail.FAuxUnitID = product.FAuxUnitNumber;
            detail.FBatch = edPihao.getText().toString();
            detail.FProductNo = edPurchaseNo.getText().toString();
            detail.FWorkShopId1 = activityPager.getDepartMent();
//            detail.AuxSign = spAuxsign.getDataNumber();
//            detail.ActualModel = spActualmodel.getDataNumber();
            detail.setProduct(product);
            detail.setStorage(activityPager.getStorage());
            detail.setWaveHouse(waveHouse);
            detail.setUnit(spUnit.getDataObject());
            detail.FProduceDate=IsOpenCreateManager?tvCreateDate.getText().toString():"";
            detail.FExpPeriod=IsOpenCreateManager?product.FExpperiod:"0";
            long insert2 = t_detailDao.insert(detail);

            if (insert1 > 0 && insert2 > 0) {
                Lg.e("成功添加main：", main);
                Lg.e("成功添加detail：", detail);
                MediaPlayer.getInstance(mContext).ok();
                Toast.showText(mContext, "添加成功");
                resetAll();
            } else {
                MediaPlayer.getInstance(mContext).error();
                Toast.showText(mContext, "添加失败，请重试");
            }
        } catch (Exception e) {
            DataService.pushError(mContext, this.getClass().getSimpleName(), e);
        }

    }

    private void resetAll() {
//        listOrder.clear();
//        edPihao.setText("");
        edNum.setText("");
//        edPurchaseNo.setText("");
//        tvCode.setText("");
//        tvGoodName.setText("");
//        tvModel.setText("");
//        product = null;
        barcode = "";
        auxNum = "";
//        batch = "";
        baseNum = "";
        storeNum = "";

    }

    //执行完单，PDA单据编号+1
    public void finishOrder() {
        AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
        ab.setTitle("确认使用完单");
        ab.setMessage("确认？");
        ab.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ordercode++;
                Log.e("ordercode", ordercode + "");
                share.setOrderCode(activityPager.getActivity(), ordercode);
            }
        });
        ab.setNegativeButton("取消", null);
        ab.create().show();

    }


    @OnClick({R.id.btn_add, R.id.btn_finishorder, R.id.btn_backorder, R.id.btn_checkorder, R.id.search_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_code:
                ProductSearchActivity.start(mContext,edCode.getText().toString(),activityPager.getOrgOut(1),Info.SEARCHPRODUCT,activity);
                break;
//                Bundle bundle1 = new Bundle();
//                bundle1.putString("search", "");
//                bundle1.putInt("where", Info.SEARCHPRODUCT);
//                bundle1.putString("org", activityPager.getOrgOut(1));
//                bundle1.putInt("activity", activity);
//                startNewActivityForResult(activityPager, ProductCheckActivity.class, R.anim.activity_open, 0, Info.SEARCHFORRESULT, bundle1);
            case R.id.btn_add:
                checkBeforeAdd();
                break;
            case R.id.btn_backorder:
                new AlertDialog.Builder(mContext)
                        .setTitle("确认上传？")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LoadingUtil.showDialog(mContext, "正在登录...");
                                BaseUtilService.reLogin(mContext);
                            }
                        })
                        .create().show();
                break;
            case R.id.btn_finishorder:
                finishOrder();
                break;
            case R.id.btn_checkorder:
                Bundle bundle = new Bundle();
                bundle.putInt("activity", activity);
                startNewActivity(activityPager, ReViewDataActivity.class,
                        R.anim.activity_fade_in, R.anim.activity_fade_out, false, bundle);
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        //执行该方法时，Fragment处于活动状态，用户可与之交互。
        Lg.e("onResume");
    }

    @Override
    public void onStart() {
        super.onStart();
        //执行该方法时，Fragment由不可见变为可见状态。
        Lg.e("onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        //执行该方法时，Fragment处于暂停状态，但依然可见，用户不能与之交互
        Lg.e("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        //执行该方法时，Fragment完全不可见。
        Lg.e("onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //销毁Fragment。通常按Back键退出或者Fragment被回收时调用此方法
        Lg.e("onDestroy");
        try {
            EventBusUtil.unregister(this);
        } catch (Exception e) {
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //解除与Activity的绑定。在onDestroy方法之后调用。
        Lg.e("onDetach");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存当前Fragment的状态。该方法会自动保存Fragment的状态，比如EditText键入的文本，即使Fragment被回收又重新创建，一样能恢复EditText之前键入的文本
        Lg.e("onSaveInstanceState");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
