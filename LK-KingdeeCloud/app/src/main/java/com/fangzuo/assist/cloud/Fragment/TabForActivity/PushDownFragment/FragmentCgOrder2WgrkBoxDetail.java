package com.fangzuo.assist.cloud.Fragment.TabForActivity.PushDownFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseFragment;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Activity.PagerForActivity;
import com.fangzuo.assist.cloud.Activity.PrintOutTestActivity;
import com.fangzuo.assist.cloud.Activity.PushDownPagerActivity;
import com.fangzuo.assist.cloud.Activity.ReViewPD4FLActivity;
import com.fangzuo.assist.cloud.Activity.ReViewPDActivity;
import com.fangzuo.assist.cloud.Adapter.PushDownSubListAdapter;
import com.fangzuo.assist.cloud.Beans.BackData;
import com.fangzuo.assist.cloud.Beans.BlueToothBean;
import com.fangzuo.assist.cloud.Beans.CodeCheckBackDataBean;
import com.fangzuo.assist.cloud.Beans.CodeCheckBean;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.PrintHistory;
import com.fangzuo.assist.cloud.Beans.PurchaseInStoreUploadBean;
import com.fangzuo.assist.cloud.Beans.SearchBean;
import com.fangzuo.assist.cloud.Dao.Org;
import com.fangzuo.assist.cloud.Dao.Product;
import com.fangzuo.assist.cloud.Dao.PushDownMain;
import com.fangzuo.assist.cloud.Dao.PushDownSub;
import com.fangzuo.assist.cloud.Dao.Storage;
import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.Dao.T_main;
import com.fangzuo.assist.cloud.Dao.Unit;
import com.fangzuo.assist.cloud.Dao.WaveHouse;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Service.DataService;
import com.fangzuo.assist.cloud.Utils.BasicShareUtil;
import com.fangzuo.assist.cloud.Utils.CommonUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.DataModel;
import com.fangzuo.assist.cloud.Utils.DoubleUtil;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.LocDataUtil;
import com.fangzuo.assist.cloud.Utils.MathUtil;
import com.fangzuo.assist.cloud.Utils.MediaPlayer;
import com.fangzuo.assist.cloud.Utils.ShareUtil;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.UpLoadModel;
import com.fangzuo.assist.cloud.Utils.VibratorUtil;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.fangzuo.assist.cloud.widget.MyWaveHouseSpinner;
import com.fangzuo.assist.cloud.widget.SpinnerActualModel;
import com.fangzuo.assist.cloud.widget.SpinnerAuxSign;
import com.fangzuo.assist.cloud.widget.SpinnerStorage;
import com.fangzuo.assist.cloud.widget.SpinnerUnit;
import com.fangzuo.assist.cloud.zxing.ScanManager;
import com.fangzuo.greendao.gen.DaoSession;
import com.fangzuo.greendao.gen.PushDownMainDao;
import com.fangzuo.greendao.gen.PushDownSubDao;
import com.fangzuo.greendao.gen.T_DetailDao;
import com.fangzuo.greendao.gen.T_mainDao;
import com.google.gson.Gson;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
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
public class FragmentCgOrder2WgrkBoxDetail extends BaseFragment {
    @BindView(R.id.sl_all)
    ScrollView slAll;
    //    private int activity = Config.PdSaleOrder2SaleOutActivity;
    private int tag = 2;
    @BindView(R.id.zxing_barcode_scanner)
    DecoratedBarcodeView zxingBarcodeScanner;
    //    @BindView(R.id.cb_scaning)
//    CheckBox cbScaning;
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_goodName)
    TextView tvGoodName;
    @BindView(R.id.tv_model)
    TextView tvModel;
//    @BindView(R.id.tv_storenum)
//    TextView tvStorenum;
//    @BindView(R.id.sp_unit)
//    SpinnerUnit spUnit;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.sp_which_storage)
    SpinnerStorage spWhichStorage;
    @BindView(R.id.sp_wavehouse)
    MyWaveHouseSpinner spWavehouse;
    @BindView(R.id.ed_num)
    EditText edNum;
//    @BindView(R.id.ed_pihao)
//    EditText edPihao;
    @BindView(R.id.sp_auxsign)
    SpinnerAuxSign spAuxsign;
    @BindView(R.id.sp_actualmodel)
    SpinnerActualModel spActualmodel;
    @BindView(R.id.ed_purchase_no)
    EditText edPurchaseNo;
    @BindView(R.id.ed_batch_remark)
    EditText edBatchRemark;
    @BindView(R.id.lv_pushsub)
    ListView lvPushsub;
//    @BindView(R.id.sp_unit_jiben)
//    SpinnerUnit spUnitJiben;
//    @BindView(R.id.ed_storenum)
//    TextView edStorenum;
//    @BindView(R.id.ed_basenum)
//    TextView edBasenum;
//    @BindView(R.id.sp_unit_store)
//    SpinnerUnit spUnitStore;
    @BindView(R.id.tv_print)
    TextView tvPrint;
    private FragmentActivity mContext;
    private PagerForActivity activityPager;
    Unbinder unbinder;
    private long ordercode;
    private Product product;
    private Storage storage;
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
    private String autoAuxSing = "";
    private String autoActualModel = "";
//    private String scanOfHuozhuNumber = "";
    private String autoStorage = "";
    private ScanManager mCaptureManager;
    private PushDownSub pushDownSub;
    private List<PushDownSub> container;
    private PushDownMainDao pushDownMainDao;
    private PushDownSubDao pushDownSubDao;
    private ArrayList<String> fidcontainer;
    private PushDownSubListAdapter pushDownSubListAdapter;
    private String default_unitID;
    protected PushDownMain pushDownMain;
    private SearchBean.S2Product s2Product;//用于数据查找...
    private String mainBuyDept = "";//表头带出
//    private String mainSaleMan = "";//表头带出
//    private String mainSaleOrg = "";//表头带出
    private Org mainBuyOrg;//表头带出
    private Org mainSettleOrg;//表头带出
//    private zpBluetoothPrinter zpSDK;
//    private BlueToothBean bean;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.Close_Activity:
                Bundle b = new Bundle();
                b.putInt("123", tag);
                startNewActivity(activityPager, PushDownPagerActivity.class, 0, 0, true, b);
                break;
            case EventBusInfoCode.ScanResult:
                BarcodeResult res = (BarcodeResult) event.postEvent;
//                if (cbScaning.isChecked()) {
//                } else {
                mCaptureManager.onPause();
                zxingBarcodeScanner.setVisibility(View.GONE);
//                }

                OnReceive(res.getResult().getText());
//                Toast.showText(mContext, "扫描结果：" + res.getResult().getText());
                break;
            case EventBusInfoCode.Product:
                product = (Product) event.postEvent;
                Lg.e("获得物料信息：", product);
//                binding.setProduct(product);
                default_unitID = product.FPurchaseUnitID;
//                dealProduct();
                setProduct(product);
                break;
            case EventBusInfoCode.Upload_OK://回单成功
                BackData backData = (BackData) event.postEvent;
                if (backData.getResult().getResponseStatus().getIsSuccess()) {
                    listOrder = new ArrayList<>();
                    //获取生成的单号数据
                    for (int i = 0; i < backData.getResult().getResponseStatus().getSuccessEntitys().size(); i++) {
                        listOrder.add(backData.getResult().getResponseStatus().getSuccessEntitys().get(i).getNumber());
                    }
                    final List<T_main> mains = t_mainDao.queryBuilder().where(
                            T_mainDao.Properties.Activity.eq(activity),
                            T_mainDao.Properties.FAccountID.eq(CommonUtil.getAccountID()),
                            T_mainDao.Properties.FID.eq(pushDownMain.FID)
                    ).build().list();
                    for (int i = 0; i < mains.size(); i++) {
                        final int pos = i;
                        String reString = mains.get(i).FBillerID + "|" + listOrder.get(i) + "|" + mains.get(i).FID;
                        App.getRService().doIOAction(WebApi.CgOrder2WgrkBoxUpload, reString, new MySubscribe<CommonResponse>() {
                            @Override
                            public void onNext(CommonResponse commonResponse) {
                                super.onNext(commonResponse);
                                List<T_Detail> list =t_detailDao.queryBuilder().where(
                                        T_DetailDao.Properties.Activity.eq(activity),
                                        T_DetailDao.Properties.FAccountID.eq(CommonUtil.getAccountID()),
                                        T_DetailDao.Properties.FOrderId.eq(mains.get(pos).FOrderId)
                                ).build().list();
                                //删掉本地用于查看界面的箱码补打数据
                                for (T_Detail bean:list) {
                                    Hawk.delete(bean.FCfBoxCode);
                                }
                                t_detailDao.deleteInTx(list);
                                for (int i = 0; i < mains.size(); i++) {
                                    List<PushDownSub> pushDownSubs = pushDownSubDao.queryBuilder().where(
                                            PushDownSubDao.Properties.FBillNo.eq(mains.get(i).FBillNo),
                                            PushDownSubDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
                                    ).build().list();
                                    pushDownSubDao.deleteInTx(pushDownSubs);
                                    List<PushDownMain> pushDownMains = pushDownMainDao.queryBuilder().where(
                                            PushDownMainDao.Properties.FBillNo.eq(mains.get(i).FBillNo),
                                            PushDownMainDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
                                    ).build().list();
                                    pushDownMainDao.deleteInTx(pushDownMains);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                            }
                        });
                    }
                    t_mainDao.deleteInTx(mains);
                    LoadingUtil.dismiss();
//                    ordercode++;
//                    Log.e("ordercode", ordercode + "");
//                    share.setOrderCode(activityPager.getActivity()+fidcontainer.get(0), ordercode);
                    MediaPlayer.getInstance(mContext).ok();
                    Toast.showText(mContext, "上传成功");
//                btnBackorder.setClickable(true);
                    DataModel.submitAndAudit(mContext,Config.PdCgOrder2WgrkActivity,listOrder,tag);
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
                            DataService.pushBackJson(mContext, FragmentCgOrder2WgrkBoxDetail.this.getClass().getSimpleName(), Hawk.get(Config.Company,""));
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
//                codeCheckBackDataBean = (CodeCheckBackDataBean) event.postEvent;
//                Lg.e("条码检测返回：",codeCheckBackDataBean);
//                if (codeCheckBackDataBean.FTip.equals("OK")) {
////                    edPihao.setText(codeCheckBackDataBean.FBatchNo);
//                    edPurchaseNo.setText(codeCheckBackDataBean.FPurchaseNo);
//                    edNum.setText(codeCheckBackDataBean.FQty);
//                    autoActualModel = codeCheckBackDataBean.FActualmodel;
//                    autoAuxSing = codeCheckBackDataBean.FAuxsign;
//                    autoStorage = codeCheckBackDataBean.FStockID;
////                    scanOfHuozhuNumber = codeCheckBackDataBean.FHuoZhuNumber;
//                    LoadingUtil.showDialog(mContext, "正在查找物料信息");
//                    DataModel.getProductForNumber(codeCheckBackDataBean.FItemID, activityPager.getOrgOut());
//                } else {
////                    activityPager.ReSetScan(cbScaning);
//                    Toast.showText(mContext, codeCheckBackDataBean.FTip);
//                }
//                break;
            case EventBusInfoCode.Code_Only_Insert://写入条码唯一临时表
                codeCheckBackDataBean = (CodeCheckBackDataBean) event.postEvent;
                LoadingUtil.dismiss();
                if (codeCheckBackDataBean.FTip.equals("OK")) {
                    Addorder();
                } else {
                    Toast.showText(mContext, codeCheckBackDataBean.FTip);
                }
                break;
            case EventBusInfoCode.UpdataView://由表头的数据决定是否更新明细数据
                if (null != activityPager) {
////                    spUnit.setAuto("", SpinnerUnit.Id);
                    spWhichStorage.setAuto(Info.Storage+activityPager.getActivityMain(),"",activityPager.getOrgOut());
                }
                break;
//            case EventBusInfoCode.Print_Check://检测打印机连接状态
//                String msg = (String) event.postEvent;
//                LoadingUtil.dismiss();
//                if ("OK".equals(msg)) {
//                    tvPrint.setText("打印机就绪");
//                    tvPrint.setTextColor(Color.BLACK);
//                } else {
//                    AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
//                    ab.setTitle("连接打印机错误,请到先配置蓝牙打印机");
////            ab.setMessage("确认？");
//                    ab.setPositiveButton("前往", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            startNewActivity(activityPager, PrintOutTestActivity.class, R.anim.activity_slide_left_in, R.anim.activity_slide_left_out, false, null);
//                            activityPager.finish();
//                        }
//                    });
//                    ab.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            activityPager.finish();
//                        }
//                    });
//                    ab.setNeutralButton("重连", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            LoadingUtil.showDialog(mContext,"正在重连...");
//                            new Thread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    checkPrint(false);
//                                }
//                            }).start();
//                        }
//                    });
//                    ab.create().show();
//                    tvPrint.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            startNewActivity(activityPager, PrintOutTestActivity.class, R.anim.activity_slide_left_in, R.anim.activity_slide_left_out, false, null);
////                            activityPager.finish();
//                        }
//                    });
//                    tvPrint.setText("连接打印机错误");
//                    tvPrint.setTextColor(Color.RED);
//                }
//                break;
        }
    }

    public FragmentCgOrder2WgrkBoxDetail() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        activityPager = ((PagerForActivity) activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cgorder2wgrk_box_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = getActivity();
        EventBusUtil.register(this);
        return view;
    }

    @Override
    protected void initView() {
        activity = activityPager.getActivity();
        t_mainDao = activityPager.getT_mainDao();
        daoSession = activityPager.getDaoSession();
        t_detailDao = activityPager.getT_detailDao();
        gson = activityPager.getGson();
        share = activityPager.getShare();
        //摄像头初始化
        mCaptureManager = new ScanManager(activityPager, zxingBarcodeScanner);
        mCaptureManager.initializeFromIntent(activityPager.getIntent(), activityPager.getSavedInstanceState());
        activityPager.setScanManager(mCaptureManager);
//        zpSDK = new zpBluetoothPrinter(mContext);
//        bean = Hawk.get(Config.OBJ_BLUETOOTH, new BlueToothBean("", ""));
//        spUnitJiben.setEnabled(false);
//        spUnitStore.setEnabled(false);
        btnAdd.setText("装箱");
    }

    @Override
    protected void initData() {
        s2Product = new SearchBean.S2Product();
        listOrder = new ArrayList<>();
//        spAuxsign.setEnabled(false);
//        spActualmodel.setEnabled(false);

        container = new ArrayList<>();
        fidcontainer = activityPager.getIntent().getExtras().getStringArrayList("fid");
//        ordercode = CommonUtil.createOrderCode(activityPager.getActivity()+fidcontainer.get(0));//单据编号
        getList();
        List<PushDownMain> pushDownMains = pushDownMainDao.queryBuilder().where(
                PushDownMainDao.Properties.FBillNo.eq(fidcontainer.get(0))).build().list();
        if (pushDownMains.size() > 0) {
            Lg.e("表头：", pushDownMains.get(0));
            pushDownMain = pushDownMains.get(0);
            activityPager.setFBillNo(pushDownMain.FBillNo);
            mainBuyDept = LocDataUtil.getDept(pushDownMain.FSaleDeptID).FNumber;
//            mainSaleMan = LocDataUtil.getSaleMan(pushDownMain.FSaleManID).FNumber;
//            mainSaleOrg = LocDataUtil.getOrg(pushDownMain.FSaleOrgID,"id").FNumber;
            mainBuyOrg = LocDataUtil.getOrg(pushDownMain.FSaleOrgID,"id");
            mainSettleOrg = LocDataUtil.getOrg(pushDownMain.FSettleOrgId,"id");
//            Lg.e("得到表头解析数据:"+mainSaleDept);
//            Lg.e("得到表头解析数据:"+mainSaleMan);
//            Lg.e("得到表头解析数据:"+mainSaleOrg);
//            Lg.e("得到表头解析数据:",mainStoreOrg);
//            activityPager.setOrgOut(mainStoreOrg);
//            mainBuyOrg = activityPager.getOrgOut();
            EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.UpdataView, ""));
            if ("".equals(pushDownMain.FSupplyID==null?"":pushDownMain.FSupplyID)){
                LoadingUtil.showAlter(mContext,"注意","表头明细的客户数据带出失败，请重试...",false);
            }
            if ("".equals(mainBuyDept)){
                mainBuyDept = LocDataUtil.getDept(LocDataUtil.getSaleMan(pushDownMain.FSaleManID).FDeptID).FNumber;
            }
        } else {
            LoadingUtil.showAlter(mContext,"注意","表头数据获取失败，请重新下载单据...",false);
//            Toast.showText(mContext, "表头数据获取失败");
        }

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
//        barcode = code;
//        LoadingUtil.showDialog(mContext, "正在检测条码...");
//        //查询条码唯一表
//        CodeCheckBean bean = new CodeCheckBean(code);
//        DataModel.codeCheck(WebApi.CodeCheckForOut, gson.toJson(bean));
    }


    @Override
    protected void initListener() {
        spWhichStorage.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                storage = (Storage) spWhichStorage.getAdapter().getItem(i);
                spWhichStorage.setTitleText(storage.FName);
                Hawk.put(Info.Storage+activityPager.getActivityMain(),storage.FName);
                Lg.e("选中仓库：", storage);
                waveHouse = null;
                spWavehouse.setAuto(mContext, storage, "");
//                DataModel.getStoreNum(product, storage, edPihao.getText().toString().trim(), mContext, tvStorenum,activityPager.getOrgOut());

            }
        });
        spWavehouse.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                waveHouse = (WaveHouse) spWavehouse.getAdapter().getItem(i);
                Lg.e("选中仓位：", waveHouse);
            }
        });
//        spUnit.setOnItemSelectedListener(new ItemListener() {
//            @Override
//            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                unit = (Unit) spUnit.getAdapter().getItem(i);
//            }
//        });

        lvPushsub.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pushDownSub = (PushDownSub) pushDownSubListAdapter.getItem(i);
                Lg.e("点击明细:", pushDownSub);
                VibratorUtil.Vibrate(mContext,Info.VibratorTime);
                if (BasicShareUtil.getInstance(mContext).getIsOL()) {
                    s2Product.likeOr = pushDownSub.FNumber;
                    s2Product.FOrg = mainBuyOrg == null ? "" : mainBuyOrg.FOrgID;
                    App.getRService().doIOAction(WebApi.S2Product, gson.toJson(new SearchBean(SearchBean.product_for_number, gson.toJson(s2Product))), new MySubscribe<CommonResponse>() {
                        @Override
                        public void onNext(CommonResponse commonResponse) {
                            super.onNext(commonResponse);
                            if (!commonResponse.state) return;
                            final DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                            Log.e("product.size", dBean.products.size() + "");
                            if (dBean.products.size() > 0) {
                                product = dBean.products.get(0);
                                Log.e("product.size", product + "");
                                dealProduct();
                            } else {
                                Toast.showText(mContext, "找不到物料数据");
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
//                            super.onError(e);
                            Toast.showText(mContext, "列表物料:" + e.toString());
                        }
                    });
                }
//                else {
//                    ProductDao productDao = daoSession.getProductDao();
//                    List<Product> products = productDao.queryBuilder().where(ProductDao.Properties.FMaterialid.eq(pushDownSub.FMaterialID)).build().list();
//                    if (products.size() > 0) {
//                        product = products.get(0);
//                        dealProduct();
//                    }else{
//                        Toast.showText(mContext,"找不到物料数据");
//                    }
//                }

            }
        });
//        lvPushsub.setOnTouchListener(new View.OnTouchListener() {
//
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_MOVE) {
//                    slAll.requestDisallowInterceptTouchEvent(true);
//                }
//                return false;
//            }
//        });

    }

    private void getList() {
        container.clear();
        pushDownSubDao = daoSession.getPushDownSubDao();
        pushDownMainDao = daoSession.getPushDownMainDao();
        for (int i = 0; i < fidcontainer.size(); i++) {
            List<PushDownSub> list = pushDownSubDao.queryBuilder().where(
                    PushDownSubDao.Properties.FBillNo.eq(fidcontainer.get(i))).build().list();
            container.addAll(list);
        }
        if (container.size() > 0) {
            pushDownSubListAdapter = new PushDownSubListAdapter(mContext, container);
            lvPushsub.setAdapter(pushDownSubListAdapter);
            pushDownSubListAdapter.notifyDataSetChanged();
        } else {
            Toast.showText(mContext, getString(R.string.find_nothing));
        }
    }

    private void setProduct(Product product) {
        if (product != null) {
            boolean flag = true;
            for (int j = 0; j < pushDownSubListAdapter.getCount(); j++) {
                PushDownSub pushDownSub1 = (PushDownSub) pushDownSubListAdapter.getItem(j);
                if (product.FMaterialid.equals(pushDownSub1.FMaterialID)) {
                    if (MathUtil.toD(pushDownSub1.FQty) == MathUtil.toD(pushDownSub1.FQtying)) {
                        flag = true;
                        continue;
                    } else {
                        if (!"".equals(default_unitID)) {
                            if (default_unitID.equals(pushDownSub1.FUnitID)) {
                                flag = false;
                                lvPushsub.setSelection(j);
                                lvPushsub.performItemClick(lvPushsub.getChildAt(j), j, lvPushsub.getItemIdAtPosition(j));
                                break;
                            }
                        } else {
                            flag = false;
                            lvPushsub.setSelection(j);
                            lvPushsub.performItemClick(lvPushsub.getChildAt(j), j, lvPushsub.getItemIdAtPosition(j));
                            break;
                        }
                    }

                }
            }

            if (flag) {
                Toast.showText(mContext, getString(R.string.product_nothing));
                MediaPlayer.getInstance(mContext).error();

            }
        } else {
            Toast.showText(mContext, "列表中不存在商品");
        }
    }

    //处理物料数据
    private void dealProduct() {
        if (product == null) {
            return;
        }
        tvGoodName.setText(product.FName);
        tvModel.setText(product.FModel);
        tvCode.setText(product.FNumber);
        //带出物料的默认值
//        spUnit.setAuto(product.FPurchaseUnitID, SpinnerUnit.Id);
//        spUnitJiben.setAuto(product.FBaseUnitID,SpinnerUnit.Id);
//        spUnitStore.setAuto(product.FStoreUnitID, SpinnerUnit.Id);
//        if (activityPager.isStorage()) {
//            spWhichStorage.setAutoSelection("", product.FStockID);
        spWhichStorage.setAuto(Info.Storage+activityPager.getActivityMain(),autoStorage, activityPager.getOrgOut());
        unit = LocDataUtil.getUnit(product.FPurchaseUnitID);
//        }
        if (CommonUtil.isOpen(product.FIsBatchManage)) {
            isOpenBatch = true;
//            edPihao.setEnabled(true);
        } else {
//            edPihao.setEnabled(false);
//            edPihao.setText("");
            isOpenBatch = false;
        }
        spAuxsign.getData(product.FMASTERID, "常规");
        spActualmodel.getData(product.FMASTERID, "");

        //自动添加
        if (activityPager.getIsAuto().isChecked()) {
            if (!checkBeforeAdd()) {
//                activityPager.ReSetScan(cbScaning);
            }
        } else {
//            activityPager.ReSetScan(cbScaning);
        }
    }

    //添加前检测
    private boolean checkBeforeAdd() {
        if (product == null) {
            Toast.showText(mContext, "请选择物料");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        if (storage == null) {
            Toast.showText(mContext, "请选择仓库");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        if (mainBuyOrg.FNumber.equals("")) {
            Toast.showText(mContext, "采购组织不能为空");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        unit = LocDataUtil.getUnit(product.FPurchaseUnitID);
        if (unit==null ||unit.FMeasureUnitID.equals("")) {
            Toast.showText(mContext, "物料单位未带出，请重试...");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
//        if (activityPager.getClient().FName.equals("")) {
//            Toast.showText(mContext, "客户不能为空");
//            MediaPlayer.getInstance(mContext).error();
//            return false;
//        }

//        if (activityPager.getHuozhuOut(0).equals("")) {
//            Toast.showText(mContext, "货主不能为空");
//            MediaPlayer.getInstance(mContext).error();
//            return false;
//        }
//        if (isOpenBatch && edPihao.getText().toString().trim().equals("")) {
//            Toast.showText(mContext, "请输入批号信息");
//            MediaPlayer.getInstance(mContext).error();
//            return false;
//        }
        //--------------------------------------------------
//        Lg.e("明细数据:",pushDownSub);
//        if (MathUtil.toD(pushDownSub.FQty) < ((MathUtil.toD(edNum.getText().toString())) + MathUtil.toD(pushDownSub.FQtying))) {
//            MediaPlayer.getInstance(mContext).error();
//            Toast.showText(mContext, "大兄弟,您的数量超过我的想象");
//            return false;
//        }
        if (edNum.getText().toString().trim().equals("") || "0".equals(edNum.getText().toString())) {
            Toast.showText(mContext, "请输入数量");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }//--------------------------------------------------
        ordercode = CommonUtil.createOrderCode(activityPager.getActivity()+pushDownMain.FID+mainBuyOrg.FNumber);//单据编号

        Addorder();
//        LoadingUtil.showDialog(mContext, "正在获取条码数据...");
//        String pdata = product.FMaterialid + "|" + unit.FMeasureUnitID + "|" + edNum.getText().toString().trim()
//                + "|" + spActualmodel.getDataNumber() + "|" + spAuxsign.getDataNumber() + "|" + edPurchaseNo.getText().toString()
//                + "|" + BasicShareUtil.getInstance(mContext).getIMIE() + "|" + storage.FNumber + "|" + mainSettleOrg.FNumber;
//        App.getRService().doIOAction(WebApi.PrintData, pdata, new MySubscribe<CommonResponse>() {
//            @Override
//            public void onNext(CommonResponse commonResponse) {
//                super.onNext(commonResponse);
//                if (!commonResponse.state) return;
//                DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
//                if (null != dBean && dBean.printDataBeans.size() > 0) {
//                    barcode = dBean.printDataBeans.get(0).FBarCode;
//                    auxNum = dBean.printDataBeans.get(0).FAuxNum;
//                    batch = dBean.printDataBeans.get(0).FBatch;
//                    baseNum = dBean.printDataBeans.get(0).FBaseNum;
//                    storeNum = dBean.printDataBeans.get(0).FStoreNum;
////                    edBasenum.setText(baseNum);
////                    edStorenum.setText(storeNum);
//                    //把需要打印的数据保存到本地
//                    PrintHistory printHistory = new PrintHistory();
//                    printHistory.setData(product, spUnitStore.getDataObject(), spUnitJiben.getDataObject(), storeNum,
//                            baseNum, spWavehouse.getWaveHouseId(), activityPager.getNote(),
//                            mainSettleOrg.FNote, barcode, batch, CommonUtil.getTime(true), "",spAuxsign.getDataNumber(),spActualmodel.getDataNumber());
//                    daoSession.getPrintHistoryDao().insert(printHistory);
//                    try {
//                        CommonUtil.doPrint(zpSDK, printHistory,activityPager.getPrintNum());
//                    } catch (Exception e) {
//                    }
//                    //-----END
//                    CodeCheckBean bean = new CodeCheckBean(barcode, ordercode + "", storage.FItemID, waveHouse == null ? "" : waveHouse.FSPID, BasicShareUtil.getInstance(mContext).getIMIE());
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

        return true;
    }

    //添加数据
    private void Addorder() {
        try {

            String num = edNum.getText().toString();
            if ("".equals(num)||"0".equals(num))return;//避免多次点击，以上请求多次，导致第一次清空之后，再去添加一个空的数据
//            if (true) {
//                Lg.e("合并");
//                List<T_Detail> detailhebing = t_detailDao.queryBuilder().where(
//                        T_DetailDao.Properties.Activity.eq(activity),
//                        T_DetailDao.Properties.FOrderId.eq(ordercode),
//                        T_DetailDao.Properties.FMaterialId.eq(product.FMaterialid),
//                        T_DetailDao.Properties.FUnitID.eq(unit.FNumber),
//                        T_DetailDao.Properties.FBarcode.eq(barcode),
//                        T_DetailDao.Properties.FStorageId.eq(storage.FNumber),
//                        T_DetailDao.Properties.FWaveHouseId.eq(spWavehouse.getwaveHouseNumber()),
//                        T_DetailDao.Properties.FBatch.eq(edPihao.getText().toString())
//                ).build().list();
//                if (detailhebing.size() > 0) {
//                    Lg.e("合并：" + detailhebing.size() + "--" + detailhebing.get(0).toString());
//                    for (int i = 0; i < detailhebing.size(); i++) {
//                        num = (MathUtil.toD(num) + MathUtil.toD(detailhebing.get(i).FRemainInStockQty)) + "";
//                        t_detailDao.delete(detailhebing.get(i));
//                    }
//                }
//            }


            t_mainDao.deleteInTx(t_mainDao.queryBuilder().where(
                    T_mainDao.Properties.FOrderId.eq(ordercode),
                    T_mainDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
            ).build().list());
            String timesecond = CommonUtil.getTimesecond();
            T_main main = new T_main();//--------------------------------------表头-----------------
            main.activity = activity;
            main.FAccountID = CommonUtil.getAccountID();
            main.FBillerID = Hawk.get(Info.user_id, "");
            main.FBarcode = barcode;
            main.IMIE = BasicShareUtil.getInstance(mContext).getIMIE();
            main.FCarBoxNo = activityPager.getCarNo();
            main.FStorageId = storage.FItemID;
            main.FStorageNumber = storage.FNumber;
            main.FOwnerIdHeadNote = LocDataUtil.getOrg(pushDownSub.FNeedOrgID,"id").FNote;
            main.FOrgId = activityPager.getOrgOut(1);
            main.FHuozhuId = LocDataUtil.getOrg(pushDownSub.FNeedOrgID,"id").FOrgID;
            main.FOrderId = ordercode;
            main.FSoorDerno = pushDownMain.FBillNo;
            main.FID = pushDownSub.FID;
            main.FNeedOrgId = LocDataUtil.getOrg(pushDownSub.FNeedOrgID,"id").FNumber;
            main.FIndex = timesecond;
            main.FBillNo = pushDownMain.FBillNo;
            main.setData(Info.getType(activity), activityPager.getOrgOut(0), mainBuyOrg.FNumber, mainSettleOrg.FNumber);
            main.FDepartmentNumber = activityPager.getDepartMent();
//            main.FPurchaseDeptId = activityPager.getDepartMentBuy();
//            main.FPurchaserId = activityPager.getManSale();
            main.FSupplierId = pushDownMain.FSupplyID;
            main.FPurchaseDeptId = mainBuyDept;
//            main.FPurchaserId = mainSaleMan;
            main.FStockerNumber = activityPager.getManStore();
            main.FDate = activityPager.getDate();
            main.FNot = activityPager.getNote();
            main.FCustomerID = pushDownMain.FSupplyID;
            main.F_FFF_Text = activityPager.getFOrderNo();
//            main.setClient(activityPager.getClient());
            long insert1 = t_mainDao.insert(main);


            T_Detail detail = new T_Detail();//--------------------------------明细-----------------
            detail.activity = activity;
            detail.FAccountID = CommonUtil.getAccountID();
            detail.FBillerID = Hawk.get(Info.user_id, "");
            detail.FBarcode = barcode;
            detail.FCfBoxCode = barcode;
            detail.FBillNo = pushDownMain.FBillNo;
            detail.IMIE = BasicShareUtil.getInstance(mContext).getIMIE();
            detail.FOrderId = ordercode;
            detail.FIndex = timesecond;
            detail.FIndex = timesecond;
            detail.FEntryID = pushDownSub.FEntryID;
            detail.FID = pushDownSub.FID;
//            detail.FHuoZhuNumber = scanOfHuozhuNumber;
            detail.FHuoZhuNumber = activityPager.getHuozhuOut(0);
            detail.FSOEntryId = pushDownSub.FEntryID;
            detail.FRemainInStockQty = pushDownSub.FQty;
            detail.FTaxPrice = pushDownSub.FTaxPrice;
            detail.FTaxRate = pushDownSub.FTaxRate;
            detail.FRealQty = num;
//            detail.FStoreNum = edStorenum.getText().toString();
//            detail.FBaseNum = edBasenum.getText().toString();
            detail.FBackType = "THLX01_SYS";
            detail.FBackDate = CommonUtil.getTime(true);
            detail.FIsFree = false;
            detail.FProductNo = edPurchaseNo.getText().toString();
            detail.FBatch = batch;
            detail.AuxSign = spAuxsign.getDataNumber();
            detail.ActualModel = spActualmodel.getDataNumber();
            detail.setProduct(product);
            detail.setStorage(storage);
            detail.setWaveHouse(waveHouse);
            detail.setUnit(unit);
            detail.FPriceUnitID = pushDownSub.FPriceUnitID;//必须再设置上面的单位之后设置计价单位
//            detail.setBaseUnit(spUnitJiben.getDataObject());
//            detail.setStoreUnit(spUnitStore.getDataObject());
            long insert2 = t_detailDao.insert(detail);

            if (insert1 > 0 && insert2 > 0) {
                pushDownSub.FQtying = DoubleUtil.sum(MathUtil.toD(pushDownSub.FQtying),
                        (MathUtil.toD(edNum.getText().toString()))) + "";
                pushDownSubDao.update(pushDownSub);
                pushDownSubListAdapter.notifyDataSetChanged();
                Lg.e("成功添加表头：" ,main);
                Lg.e("成功添加明细：" ,detail);
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
    private void doPacking() {
        PurchaseInStoreUploadBean pBean = new PurchaseInStoreUploadBean();
        PurchaseInStoreUploadBean.purchaseInStore puBean = pBean.new purchaseInStore();
        ArrayList<String> detailContainer = new ArrayList<>();
        final ArrayList<PurchaseInStoreUploadBean.purchaseInStore> data = new ArrayList<>();
        final List<T_main> mains = t_mainDao.queryBuilder().where(
                T_mainDao.Properties.Activity.eq(activity),
                T_mainDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
        ).orderAsc(T_mainDao.Properties.FOrderId).build().list();
        for (int i = 0; i < mains.size(); i++) {
            if (i > 0 && mains.get(i).FOrderId == mains.get(i - 1).FOrderId) {

            } else {
                detailContainer = new ArrayList<>();
                puBean = pBean.new purchaseInStore();
                String main;
                String detail = "";
                T_main t_main = mains.get(i);
                main = t_main.FBillerID + "|" +
                        t_main.FCarBoxNo + "|" +
                        t_main.FOwnerIdHeadNote + "|" +
                        t_main.FID + "|" +
                        t_main.FStorageNumber + "|" +
                        t_main.FStorageId + "|" +
                        t_main.FOrgId + "|"+
                        t_main.FHuozhuId+ "|"+
                        edBatchRemark.getText().toString()+ "|";
                puBean.main = main;
//                List<T_Detail> details =DataModel.mergeDetail(mContext,t_main.FOrderId+"",activity);
                List<T_Detail> details = t_detailDao.queryBuilder().where(
                        T_DetailDao.Properties.FOrderId.eq(t_main.FOrderId),
                        T_DetailDao.Properties.FAccountID.eq(CommonUtil.getAccountID()),
                        T_DetailDao.Properties.Activity.eq(activity),
                        T_DetailDao.Properties.FIsInBox.notEq(1)
                ).build().list();
                for (int j = 0; j < details.size(); j++) {
                    if (j != 0 && (j+1) % 50 == 0) {
                        Log.e("j%49", j % 49 + "");
                        T_Detail t_detail = details.get(j);
                        detail = detail +
                                t_detail.FItemID + "|" +
                                t_detail.FUnitID + "|" +
                                t_detail.FRealQty + "|" +
                                "0" + "|" ;
                        detail = detail.subSequence(0, detail.length() - 1).toString();
                        detailContainer.add(detail);
                        detail = "";
                    } else {
                        Log.e("j", j + "");
                        Log.e("details.size()", details.size() + "");
                        T_Detail t_detail = details.get(j);
                        detail = detail +
                                t_detail.FItemID + "|" +
                                t_detail.FUnitID + "|" +
                                t_detail.FRealQty + "|" +
                                "0" + "|" ;
                        Log.e("detail1", detail);
                    }

                }
                if (detail.length() > 0) {
                    detail = detail.subSequence(0, detail.length() - 1).toString();
                }

                Log.e("detail", detail);
                detailContainer.add(detail);
                puBean.detail = detailContainer;
                data.add(puBean);
            }

        }
        pBean.list = data;
        App.getRService().doIOAction(WebApi.PrintData4FlInStore, gson.toJson(pBean), new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);
                if (!commonResponse.state) return;
                DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                if (null != dBean && dBean.printDataBeans.size() > 0) {
//                    printDataBeans.addAll(dBean.printDataBeans);
//                    barcode = dBean.printDataBeans.get(0).FBarCode;
//                    auxNum = dBean.printDataBeans.get(0).FAuxNum;
//                    batch = dBean.printDataBeans.get(0).FBatch;
//                    baseNum = dBean.printDataBeans.get(0).FBaseNum;
//                    storeNum = dBean.printDataBeans.get(0).FStoreNum;
//                    edBasenum.setText(baseNum);
//                    edStorenum.setText(storeNum);
//                    for (int i = 0; i < printDataBeans.size(); i++) {
//                        printDataBeans.get(i).FPrintNum = activityPager.getPrintNum();
//                    }
                    //把需要打印的数据保存到本地
//                    Hawk.put(dBean.printDataBeans.get(0).FBoxCode,dBean.printDataBeans);
                    List<T_Detail> detailhebing = t_detailDao.queryBuilder().where(
                            T_DetailDao.Properties.Activity.eq(activity),
                            T_DetailDao.Properties.FAccountID.eq(CommonUtil.getAccountID()),
                            T_DetailDao.Properties.FOrderId.eq(ordercode),
                            T_DetailDao.Properties.FIsInBox.notEq(1)
                    ).build().list();
                    for (int i = 0; i < detailhebing.size(); i++) {
                        detailhebing.get(i).FBatch = dBean.printDataBeans.get(0).FBatch;
                        detailhebing.get(i).FCfBoxCode = dBean.printDataBeans.get(0).FBoxCode;
                        detailhebing.get(i).FIsInBox = 1;
                        t_detailDao.updateInTx(detailhebing.get(i));
                    }
                    for (int i = 0; i < mains.size(); i++) {
                        mains.get(i).FBoxCode = dBean.printDataBeans.get(0).FBoxCode;
                        t_mainDao.updateInTx(mains.get(i));
                    }
//                    LoadingUtil.showDialog(mContext, "正在上传....");
//                    UpLoadModel.action(mContext, activity);
//                    PrintHistory printHistory = new PrintHistory();
//                    printHistory.setData(product, spUnitStore.getDataObject(), spUnitJiben.getDataObject(), storeNum,
//                            baseNum, spWavehouse.getWaveHouseId(), activityPager.getNote(),
//                            activityPager.getOrgIn().FNote, barcode, batch, CommonUtil.getTime(true), "",spAuxsign.getDataNumber(),spActualmodel.getDataNumber());
//                    daoSession.getPrintHistoryDao().insert(printHistory);
                    Hawk.put(dBean.printDataBeans.get(0).FBoxCode,dBean.printDataBeans);
                    try {
                        CommonUtil.doPrint4P1BoxCode4BoxReAdd32(mContext, dBean.printDataBeans,activityPager.getPrintNum(),false);
                    } catch (Exception e) {
                    }
                    edBatchRemark.setText("");
                    LoadingUtil.dismiss();
//                    Addorder();
                } else {
                    LoadingUtil.dismiss();
                    Toast.showText(mContext, "生成条码失败，请重试");
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                LoadingUtil.dismiss();
//                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Code_Only_Insert,new CodeCheckBackDataBean("找不到条码信息")));
            }
        });
    }



    private void resetAll() {
//        activityPager.ReSetScan(cbScaning);
        edPurchaseNo.setText("");
        listOrder.clear();
        barcode = "";
//        edPihao.setText("");
        edNum.setText("");
        product = null;
        pushDownSub = null;
        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock));

    }
//    //检测打印机连接状态
//    private void checkPrint(boolean check) {
//        if (bean.address.equals("")) {
//            EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Print_Check, "NOOK"));
//        } else {
//            if (!zpSDK.connect(bean.address)) {
//                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Print_Check, "NOOK"));
//
//            } else {
//                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Print_Check, "OK"));
//            }
//        }
//    }
    //执行完单，PDA单据编号+1
    public void finishOrder() {
//        AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
//        ab.setTitle("确认使用完单");
//        ab.setMessage("确认？");
//        ab.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                ordercode++;
//                Log.e("ordercode", ordercode + "");
//                share.setOrderCode(activityPager.getActivity()+fidcontainer.get(0), ordercode);
//            }
//        });
//        ab.setNegativeButton("取消", null);
//        ab.create().show();

    }

    @OnClick({R.id.search,R.id.btn_add_temp, R.id.btn_add, R.id.btn_finishorder, R.id.btn_backorder, R.id.btn_checkorder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search:
//                if (zxingBarcodeScanner.getVisibility() == View.VISIBLE) {
//                    zxingBarcodeScanner.setVisibility(View.GONE);
////                    mCaptureManager.onPause();
//                } else {
//                    mCaptureManager.onResume();
//                    zxingBarcodeScanner.setVisibility(View.VISIBLE);
//                    mCaptureManager.decode();
//                }

//                Bundle bundle1 = new Bundle();
//                bundle1.putString("search", "");
//                bundle1.putInt("where", Info.SEARCHPRODUCT);
//                bundle1.putString("org", activityPager.getOrgOut(1));
//                bundle1.putInt("activity", activity);
//                startNewActivityForResult(activityPager, ProductSearchActivity.class, R.anim.activity_open, 0, Info.SEARCHFORRESULT, bundle1);
                break;
            case R.id.btn_add:
                new AlertDialog.Builder(mContext)
                        .setTitle("确认装箱？")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LoadingUtil.showDialog(mContext, "正在装箱...");
                                doPacking();
                            }
                        })
                        .create().show();
                break;
            case R.id.btn_add_temp:
                checkBeforeAdd();
                break;
            case R.id.btn_backorder:
                new AlertDialog.Builder(mContext)
                        .setTitle("确认上传？")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (!checkHasPackingData()){
                                    LoadingUtil.showDialog(mContext, "正在上传...");
                                    UpLoadModel.actionPushDown(mContext, activity,pushDownMain.FID);
                                }else{
                                    LoadingUtil.showAlter(mContext,"存在未装箱数据,请装箱后再上传");
                                }
                            }
                        })
                        .create().show();
                break;
            case R.id.btn_finishorder:
                finishOrder();
                break;
            case R.id.btn_checkorder:
                Bundle bundle = new Bundle();
                bundle.putString("fid", pushDownMain.FID);
                bundle.putInt("activity", activity);
                startNewActivity(activityPager, ReViewPD4FLActivity.class,
                        R.anim.activity_fade_in, R.anim.activity_fade_out, false, bundle);
                break;
        }
    }
    //检测是否存在未装箱数据
    private boolean checkHasPackingData(){
        List<T_Detail> list = t_detailDao.queryBuilder().where(
                T_DetailDao.Properties.Activity.eq(activity),
                T_DetailDao.Properties.FIsInBox.eq(0)
        ).build().list();
        if (list.size()>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getList();
        //执行该方法时，Fragment处于活动状态，用户可与之交互。
        Lg.e("onResume");//执行该方法时，Fragment处于活动状态，用户可与之交互。
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                checkPrint(false);
//            }
//        }).start();
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
//        try {
//            if (null != zpSDK) zpSDK.disconnect();
//        } catch (Exception e) {
//        }
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
//        mCaptureManager.onDestroy();
        try {
            EventBusUtil.unregister(this);
        } catch (Exception e) {
        }
//        try {
//            zpSDK.disconnect();
//        } catch (Exception e) {
//
//        }
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
