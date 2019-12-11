package com.fangzuo.assist.cloud.Fragment.TabForActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.fangzuo.assist.cloud.ABase.BaseFragment;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Activity.PagerForActivity;
import com.fangzuo.assist.cloud.Activity.PushDownPagerActivity;
import com.fangzuo.assist.cloud.Activity.ReViewP14BoxActivity;
import com.fangzuo.assist.cloud.Adapter.PushDownSubP1ListAdapter;
import com.fangzuo.assist.cloud.Beans.BackData;
import com.fangzuo.assist.cloud.Beans.CodeCheckBackDataBean;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Beans.EventBusBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.PrintDataBean;
import com.fangzuo.assist.cloud.Beans.SearchBean;
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
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.fangzuo.greendao.gen.DaoSession;
import com.fangzuo.greendao.gen.PushDownMainDao;
import com.fangzuo.greendao.gen.PushDownSubDao;
import com.fangzuo.greendao.gen.T_DetailDao;
import com.fangzuo.greendao.gen.T_mainDao;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


//选择单据信息Fragment（所属：PushDownPagerActivity);
public class FragmentPG2CprkDetailOld0826 extends BaseFragment {

    @BindView(R.id.btn_backorder)
    Button btnBackorder;
    private int tag = 29;
    @BindView(R.id.lv_pushsub)
    ListView lvPushsub;
    private FragmentActivity mContext;
    private PagerForActivity activityPager;
    Unbinder unbinder;
    private long ordercode;
    private Product product;
    private Storage storage;
    private WaveHouse waveHouse;
    private Unit unit;
    private Unit unitStore;
    private Unit unitJiben;
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
    //    private ScanManager mCaptureManager;
    private PushDownSub pushDownSub;
    private List<PushDownSub> container;
    private PushDownMainDao pushDownMainDao;
    private PushDownSubDao pushDownSubDao;
    private ArrayList<String> fidcontainer;
    private PushDownSubP1ListAdapter pushDownSubListAdapter;
    private String default_unitID;
    protected PushDownMain pushDownMain;
    private ArrayList<PrintDataBean> printDataBeans;
    private SearchBean.S2Product s2Product;//用于数据查找...
//    private String mainBuyDept = "";//表头带出
        private String mainSaleMan = "";//表头带出
//    private String mainSaleOrg = "";//表头带出
//    private Org mainBuyOrg;//表头带出
//    private Org mainStoreOrg;//表头带出

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.Close_Activity:
                Bundle b = new Bundle();
                b.putInt("123", tag);
                startNewActivity(activityPager, PushDownPagerActivity.class, 0, 0, true, b);
                break;
//            case EventBusInfoCode.ScanResult:
//                LoadingUtil.dismiss();
//                LoadingUtil.showDialog(mContext, "正在上传....");
//                UpLoadModel.actionPushDown(mContext, activity, pushDownMain.FID);
//                BarcodeResult res = (BarcodeResult) event.postEvent;
//                BarcodeResult res = (BarcodeResult) event.postEvent;
//                if (cbScaning.isChecked()) {
//                } else {
//                mCaptureManager.onPause();
//                zxingBarcodeScanner.setVisibility(View.GONE);
//                }

//                OnReceive(res.getResult().getText());
//                Toast.showText(mContext, "扫描结果：" + res.getResult().getText());
//                break;
//            case EventBusInfoCode.Product:
//                product = (Product) event.postEvent;
//                Lg.e("获得物料信息：", product);
////                binding.setProduct(product);
//                default_unitID = product.FPurchaseUnitID;
////                dealProduct();
//                setProduct(product);
//                break;
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
                        Hawk.delete(mains.get(i).FBoxCode);
                        final int pos = i;
//                        String reString = mains.get(i).FBillerID + "|" + listOrder.get(i) + "|" + mains.get(i).FOrderId + "|" + mains.get(i).IMIE;
                        String reString = mains.get(i).FBillerID + "|" + listOrder.get(i) + "|" + mains.get(i).FBoxCode;
                        App.getRService().doIOAction(WebApi.P1PG2CprkUpload, reString, new MySubscribe<CommonResponse>() {
                            @Override
                            public void onNext(CommonResponse commonResponse) {
                                super.onNext(commonResponse);
                                t_detailDao.deleteInTx(t_detailDao.queryBuilder().where(
                                        T_DetailDao.Properties.Activity.eq(activity),
                                        T_DetailDao.Properties.FAccountID.eq(CommonUtil.getAccountID()),
                                        T_DetailDao.Properties.FOrderId.eq(mains.get(pos).FOrderId)
                                ).build().list());
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
                    DataModel.submitAndAudit(mContext, Config.P1PdProductGet2CprkActivity, listOrder, tag);
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
                            DataService.pushBackJson(mContext, FragmentPG2CprkDetailOld0826.this.getClass().getSimpleName(), Hawk.get(Config.Company,""));
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
//            case EventBusInfoCode.UpdataView://由表头的数据决定是否更新明细数据
//                if (null != activityPager) {
//
//
//////                    spUnit.setAuto("", SpinnerUnit.Id);
////                    spWhichStorage.setAuto("","",activityPager.getOrgOut());
//                }
//                break;
            case EventBusInfoCode.Print_Check://检测打印机连接状态
                String msg = (String) event.postEvent;
                LoadingUtil.dismiss();
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
                break;
        }
    }

    public FragmentPG2CprkDetailOld0826() {
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
        View view = inflater.inflate(R.layout.fragment_pg2cprk_detail, container, false);
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
//        mCaptureManager = new ScanManager(activityPager, zxingBarcodeScanner);
//        mCaptureManager.initializeFromIntent(activityPager.getIntent(), activityPager.getSavedInstanceState());
//        activityPager.setScanManager(mCaptureManager);
//        spUnitJiben.setEnabled(false);
//        spUnitStore.setEnabled(false);
    }

    @Override
    protected void initData() {
        s2Product = new SearchBean.S2Product();
        listOrder = new ArrayList<>();
//        spAuxsign.setEnabled(false);
//        spActualmodel.setEnabled(false);
        EventBusBean busBean = new EventBusBean();
        container = new ArrayList<>();
        printDataBeans = new ArrayList<>();
        fidcontainer = activityPager.getIntent().getExtras().getStringArrayList("fid");
//        ordercode = CommonUtil.createOrderCode(activityPager.getActivity()+fidcontainer.get(0));//单据编号
        getList();
        List<PushDownMain> pushDownMains = pushDownMainDao.queryBuilder().where(
                PushDownMainDao.Properties.FBillNo.eq(fidcontainer.get(0))).build().list();
        if (pushDownMains.size() > 0) {
            Lg.e("表头：", pushDownMains.get(0));
            pushDownMain = pushDownMains.get(0);
//            mainBuyDept = LocDataUtil.getDept(pushDownMain.FSaleDeptID).FNumber;
            mainSaleMan = LocDataUtil.getBuyMan(pushDownMain.FSaleManID).FNumber;
            busBean.FStandby1 = pushDownMain.FWordShop;
            busBean.FStandby2 = pushDownMain.FStoreOrgID;
            busBean.FStandby3 = pushDownMain.FHuoZhuID;
            busBean.FStandby4 = pushDownMain.FCarBoxNo;
//            mainSaleOrg = LocDataUtil.getOrg(pushDownMain.FSaleOrgID,"id").FNumber;
//            mainStoreOrg = LocDataUtil.getOrg(pushDownMain.FStoreOrgID, "id");
//            Lg.e("得到表头解析数据:"+mainSaleDept);
//            Lg.e("得到表头解析数据:"+mainSaleMan);
//            Lg.e("得到表头解析数据:"+mainSaleOrg);
//            Lg.e("得到表头解析数据:",mainStoreOrg);
//            activityPager.setOrgOut(mainStoreOrg);
//            mainBuyOrg = activityPager.getOrgOut();
//            EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.UpdataView, ""));
//            if ("".equals(pushDownMain.FSupplyID == null ? "" : pushDownMain.FSupplyID)) {
//                LoadingUtil.showAlter(mContext, "注意", "表头明细的客户数据带出失败，请重试...", false);
//            }
//            if ("".equals(mainBuyDept)) {
//                mainBuyDept = LocDataUtil.getDept(LocDataUtil.getSaleMan(pushDownMain.FSaleManID).FDeptID).FNumber;
//            }
        } else {
            LoadingUtil.showAlter(mContext, "注意", "表头数据获取失败，请重新下载单据...", false);
//            Toast.showText(mContext, "表头数据获取失败");
        }
        ordercode = CommonUtil.createOrderCode(activityPager.getActivityMain() + pushDownMain.FID);//单据编号
        busBean.FStandby5 = ordercode+"";
        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.UpdataView, busBean));

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
//        lvPushsub.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                pushDownSub = (PushDownSub) pushDownSubListAdapter.getItem(i);
//                Lg.e("点击明细:", pushDownSub);
//                VibratorUtil.Vibrate(mContext, Info.VibratorTime);
//                if (BasicShareUtil.getInstance(mContext).getIsOL()) {
//                    s2Product.likeOr = pushDownSub.FNumber;
//                    s2Product.FOrg = mainStoreOrg == null ? "" : mainStoreOrg.FOrgID;
//                    App.getRService().doIOAction(WebApi.S2Product, gson.toJson(new SearchBean(SearchBean.product_for_number, gson.toJson(s2Product))), new MySubscribe<CommonResponse>() {
//                        @Override
//                        public void onNext(CommonResponse commonResponse) {
//                            super.onNext(commonResponse);
//                            if (!commonResponse.state) return;
//                            final DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
//                            Log.e("product.size", dBean.products.size() + "");
//                            if (dBean.products.size() > 0) {
//                                product = dBean.products.get(0);
//                                Log.e("product.size", product + "");
//                                dealProduct();
//                            } else {
//                                Toast.showText(mContext, "找不到物料数据");
//                            }
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
////                            super.onError(e);
//                            Toast.showText(mContext, "列表物料:" + e.toString());
//                        }
//                    });
//                }
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

//            }
//        });
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
            pushDownSubListAdapter = new PushDownSubP1ListAdapter(mContext, container);
            lvPushsub.setAdapter(pushDownSubListAdapter);
            pushDownSubListAdapter.notifyDataSetChanged();
        } else {
            Toast.showText(mContext, getString(R.string.find_nothing));
        }
        if (null != pushDownSubListAdapter) {
            for (int i = 0; i < pushDownSubListAdapter.getCount(); i++) {
                Lg.e("遍历" + i);
                if (!"1".equals(((PushDownSub) pushDownSubListAdapter.getItem(i)).FIsPrint)) {
                    lvPushsub.setSelection(i);
                    break;
                }
            }
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
        //带出物料的默认值
        unitStore = LocDataUtil.getUnit(product.FStoreUnitID);
        unitJiben = LocDataUtil.getUnit(product.FBaseUnitID);
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
        if (LocDataUtil.hasTDetail(activity,ordercode)){
            Toast.showText(mContext, "本地已存在相同装箱单,请勿重新装箱");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        if (activityPager.getDepartMent().equals("")) {
            Toast.showText(mContext, "请选择生产车间");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        if (activityPager.getCarNo().equals("")) {
            Toast.showText(mContext, "请输入车号");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        if (activityPager.getStorage().FNumber.equals("")) {
            Toast.showText(mContext, "仓库不能为空");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }

//        if (mainStoreOrg.FNumber.equals("")) {
//            Toast.showText(mContext, "采购组织不能为空");
//            MediaPlayer.getInstance(mContext).error();
//            return false;
//        }
//        unit = LocDataUtil.getUnit(pushDownSub.FUnitID);
//        if (unit == null || unit.FMeasureUnitID.equals("")) {
//            Toast.showText(mContext, "物料单位未带出，请重试...");
//            MediaPlayer.getInstance(mContext).error();
//            return false;
//        }
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
//        if (edNum.getText().toString().trim().equals("") || "0".equals(edNum.getText().toString())) {
//            Toast.showText(mContext, "请输入数量");
//            MediaPlayer.getInstance(mContext).error();
//            return false;
//        }//--------------------------------------------------

        //插入条码唯一临时表
//        CodeCheckBean bean = new CodeCheckBean(barcode, ordercode + "", edNum.getText().toString(), BasicShareUtil.getInstance(mContext).getIMIE());
//        DataModel.codeOnlyInsert(WebApi.CodeCheckInsertForOut, gson.toJson(bean));
//        Addorder();

//        LoadingUtil.showDialog(mContext, "正在处理数据...");
//        Addorder();
        LoadingUtil.showDialog(mContext, "正在获取条码数据...");
        String pdata = Hawk.get(Info.user_id, "") + "|" + activityPager.getCarNo() + "|" + activityPager.getOrgIn().FNote
                + "|" + pushDownMain.FID + "|" + activityPager.getStorage().FNumber + "|" + activityPager.getStorage().FItemID
                + "|" + activityPager.getOrgOut().FOrgID + "|" + activityPager.getOrgIn().FOrgID;
        App.getRService().doIOAction(WebApi.PrintData4Package, pdata, new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);
                if (!commonResponse.state) return;
                DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                if (null != dBean && dBean.printDataBeans.size() > 0) {
                    printDataBeans.addAll(dBean.printDataBeans);
//                    barcode = dBean.printDataBeans.get(0).FBarCode;
//                    auxNum = dBean.printDataBeans.get(0).FAuxNum;
//                    batch = dBean.printDataBeans.get(0).FBatch;
//                    baseNum = dBean.printDataBeans.get(0).FBaseNum;
//                    storeNum = dBean.printDataBeans.get(0).FStoreNum;
//                    edBasenum.setText(baseNum);
//                    edStorenum.setText(storeNum);
                    for (int i = 0; i < printDataBeans.size(); i++) {
                        printDataBeans.get(i).FPrintNum = activityPager.getPrintNum();
                    }
                    //把需要打印的数据保存到本地
                    Hawk.put(dBean.printDataBeans.get(0).FBoxCode,dBean.printDataBeans);
//                    PrintHistory printHistory = new PrintHistory();
//                    printHistory.setData(product, spUnitStore.getDataObject(), spUnitJiben.getDataObject(), storeNum,
//                            baseNum, spWavehouse.getWaveHouseId(), activityPager.getNote(),
//                            activityPager.getOrgIn().FNote, barcode, batch, CommonUtil.getTime(true), "",spAuxsign.getDataNumber(),spActualmodel.getDataNumber());
//                    daoSession.getPrintHistoryDao().insert(printHistory);
                    try {
//                        CommonUtil.doPrint4P1BoxCode(mContext, dBean.printDataBeans,activityPager.getPrintNum(),true);
                    } catch (Exception e) {
                    }
                    Addorder();
                } else {
                    Toast.showText(mContext, "生成条码失败，请重试");
                }
                LoadingUtil.dismiss();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                LoadingUtil.dismiss();
//                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Code_Only_Insert,new CodeCheckBackDataBean("找不到条码信息")));
            }
        });




        return true;
    }
    //添加数据
    private void Addorder() {
        try {

//            String num = edNum.getText().toString();
//            if ("".equals(num)||"0".equals(num))return;//避免多次点击，以上请求多次，导致第一次清空之后，再去添加一个空的数据
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
            t_detailDao.deleteInTx(t_detailDao.queryBuilder().where(
                    T_DetailDao.Properties.FOrderId.eq(ordercode),
                    T_DetailDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
            ).build().list());
            String timesecond = CommonUtil.getTimesecond();
            T_main main = new T_main();//--------------------------------------表头-----------------
            main.activity = activity;
            main.FWlCompany = pushDownMain.FWlCompany;
            main.FCarBoxNo = pushDownMain.FCarBoxNo;
            main.FPassNo = pushDownMain.FPassNo;
            main.FFreight = pushDownMain.FFreight;
            main.FYaoNo = pushDownMain.FYaoNo;
            main.FAccountID = CommonUtil.getAccountID();
            main.FBillerID = Hawk.get(Info.user_id, "");
            main.FBarcode = barcode;
            main.FBoxCode = printDataBeans.get(0).FBoxCode;
            main.IMIE = BasicShareUtil.getInstance(mContext).getIMIE();
            main.FOrderId = ordercode;
            main.FSoorDerno = pushDownMain.FBillNo;
            main.FID = pushDownMain.FID;
            main.FIndex = timesecond;
            main.FBillNo = pushDownMain.FBillNo;
            main.setData(Info.getType(activity), activityPager.getOrgOut(0), activityPager.getOrgIn(0), activityPager.getOrgIn(0));
            main.FDepartmentNumber = activityPager.getDepartMent();
//            main.FPurchaseDeptId = activityPager.getDepartMentBuy();
//            main.FPurchaserId = activityPager.getManSale();
//            main.FSupplierId = pushDownMain.FSupplyID;
//            main.FPurchaseDeptId = mainBuyDept;
            main.FPurchaserId = mainSaleMan;
            main.FStockerNumber = activityPager.getManStore();
            main.FDate = activityPager.getDate();
            main.FNot = activityPager.getNote();
            main.FCustomerID = pushDownMain.FSupplyID;
            main.F_FFF_Text = activityPager.getFOrderNo();
            main.FDBType = activityPager.getCarNo();
//            main.setClient(activityPager.getClient());
            long insert1 = t_mainDao.insert(main);
            Lg.e("添加表头" + insert1, main);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < pushDownSubListAdapter.getCount(); i++) {
                        PushDownSub pushDownSub = (PushDownSub) pushDownSubListAdapter.getItem(i);
                        Lg.e("列表明细：",pushDownSub);
                        T_Detail detail = new T_Detail();//--------------------------------明细-----------------
                        String timesecond = CommonUtil.getTimesecond();
                        detail.activity = activity;
                        detail.FAccountID = CommonUtil.getAccountID();
                        detail.FBillerID = Hawk.get(Info.user_id, "");
                        detail.FBarcode = barcode;
                        detail.FProductName = pushDownSub.FName;
                        detail.FCfBoxCode = printDataBeans.get(0).FBoxCode;
                        detail.IMIE = BasicShareUtil.getInstance(mContext).getIMIE();
                        detail.FOrderId = ordercode;
                        detail.FIndex = timesecond;
//            detail.FEntryID = pushDownSub.FEntryID;
                    detail.FID = pushDownSub.FID;
//            detail.FHuoZhuNumber = scanOfHuozhuNumber;
                        detail.FHuoZhuNumber = activityPager.getOrgIn(0);
                        detail.FSOEntryId = pushDownSub.FEntryID;
                        detail.FRemainInStockQty = pushDownSub.FQty;
                        detail.FTaxPrice = pushDownSub.FTaxPrice;
                        detail.FRealQty = pushDownSub.FQty;
                        detail.FCfQtySum = printDataBeans.get(0).FQtyAll;
                        detail.FCfM2Sum = printDataBeans.get(0).FVolAll;
                        detail.FWorkShopId1 = activityPager.getDepartMent();
//            detail.FStoreNum = edStorenum.getText().toString();
//            detail.FBaseNum = edBasenum.getText().toString();
//            detail.FBackType = "THLX01_SYS";
                        detail.FBackDate = CommonUtil.getTime(true);
//            detail.FIsFree = false;
//            detail.FProductNo = edPurchaseNo.getText().toString();
                        detail.FBatch = printDataBeans.get(0).FBatch;
//                        detail.AuxSign = pushDownSub.AuxSign;
//                        detail.ActualModel = pushDownSub.ActualModel;
//                detail.setProduct(product);
                        detail.FMaterialId = pushDownSub.FNumber;
//                        detail.FStorageId = pushDownSub.FStorageOutID;
                        detail.setStorage(activityPager.getStorage());
                        detail.setWaveHouse(waveHouse);
                        detail.FLevel = pushDownSub.FLevel;
                        detail.FYmLenght = pushDownSub.FYmLenght;
                        detail.FYmDiameter = pushDownSub.FYmDiameter;
                        detail.FBLenght = pushDownSub.FBLenght;
                        detail.FBWide = pushDownSub.FBWide;
                        detail.FBThick = pushDownSub.FBThick;
                        detail.setUnit(LocDataUtil.getUnit(pushDownSub.FUnitID));
                        Lg.e("返回单位",LocDataUtil.getUnit(pushDownSub.FUnitID));
//            detail.FVolume = MathUtil.getVoleum(MathUtil.c10(edLenght.getText().toString())+"",edDiameter.getText().toString())+"";
//            detail.setBaseUnit(spUnitJiben.getDataObject());
//            detail.setStoreUnit(spUnitStore.getDataObject());
                        t_detailDao.insertInTx(detail);
                        Lg.e("添加明细",detail);
//                        if (i==pushDownSubListAdapter.getCount()-1){
//                            EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.ScanResult,""));
//                        }
                    }
                }
            }).start();

//
//            if (insert1 > 0 && insert2 > 0) {
////                pushDownSub.FQtying = DoubleUtil.sum(MathUtil.toD(pushDownSub.FQtying),
////                        (MathUtil.toD(edNum.getText().toString()))) + "";
////                pushDownSubDao.update(pushDownSub);
//                pushDownSub.FIsPrint ="1";
//                pushDownSubDao.update(pushDownSub);
//                pushDownSubListAdapter.notifyDataSetChanged();
//                Lg.e("成功添加表头：", main);
//                Lg.e("成功添加明细：", detail);
//                MediaPlayer.getInstance(mContext).ok();
//                Toast.showText(mContext, "添加成功");
//                resetAll();
//            } else {
//                MediaPlayer.getInstance(mContext).error();
//                Toast.showText(mContext, "添加失败，请重试");
//            }

        } catch (Exception e) {
            DataService.pushError(mContext, this.getClass().getSimpleName(), e);
        }

    }

    private void resetAll() {
//        activityPager.ReSetScan(cbScaning);
//        edPurchaseNo.setText("");
        listOrder.clear();
        barcode = "";
//        edPihao.setText("");
//        edNum.setText("");
        product = null;
        pushDownSub = null;
        if (null != pushDownSubListAdapter) {
            for (int i = 0; i < pushDownSubListAdapter.getCount(); i++) {
                Lg.e("遍历" + i);
                if (!"1".equals(((PushDownSub) pushDownSubListAdapter.getItem(i)).FIsPrint)) {
                    lvPushsub.setSelection(i);
                    break;
                }
            }
        }

    }

    @OnClick({R.id.btn_backorder,R.id.btn_package,R.id.btn_checkorder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_backorder:
                new AlertDialog.Builder(mContext)
                        .setTitle("确认上传？")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LoadingUtil.showDialog(mContext, "正在上传....");
                                UpLoadModel.actionPushDown(mContext, activity, pushDownMain.FID);
                            }
                        })
                        .create().show();
                break;
            case R.id.btn_package:
                checkBeforeAdd();
                break;
            case R.id.btn_checkorder:
                Bundle bundle = new Bundle();
                bundle.putInt("activity", activity);
                startNewActivity(activityPager, ReViewP14BoxActivity.class,
                        R.anim.activity_fade_in, R.anim.activity_fade_out, false, bundle);
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        getList();
        //执行该方法时，Fragment处于活动状态，用户可与之交互。
        Lg.e("onResume");//执行该方法时，Fragment处于活动状态，用户可与之交互。
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
//        mCaptureManager.onDestroy();
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
