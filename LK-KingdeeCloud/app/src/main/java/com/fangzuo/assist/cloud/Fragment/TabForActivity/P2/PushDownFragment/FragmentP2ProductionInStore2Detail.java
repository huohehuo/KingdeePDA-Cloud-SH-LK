package com.fangzuo.assist.cloud.Fragment.TabForActivity.P2.PushDownFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;

import com.fangzuo.assist.cloud.ABase.BaseFragment;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Activity.PagerForActivity;
import com.fangzuo.assist.cloud.Activity.PrintOutTestActivity;
import com.fangzuo.assist.cloud.Activity.PushDownPagerActivity;
import com.fangzuo.assist.cloud.Activity.ReViewPDAP2ctivity;
import com.fangzuo.assist.cloud.Adapter.NumRvAdapter;
import com.fangzuo.assist.cloud.Adapter.PushDownSubP2ListAdapter;
import com.fangzuo.assist.cloud.Beans.BackData;
import com.fangzuo.assist.cloud.Beans.BlueToothBean;
import com.fangzuo.assist.cloud.Beans.CodeCheckBackDataBean;
import com.fangzuo.assist.cloud.Beans.CodeCheckBean;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.PrintHistory;
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
import com.fangzuo.assist.cloud.widget.RecyclerViewDivider;
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
import zpSDK.zpSDK.zpBluetoothPrinter;


//选择单据信息Fragment（所属：PushDownPagerActivity);
public class FragmentP2ProductionInStore2Detail extends BaseFragment {
    @BindView(R.id.sl_all)
    ScrollView slAll;
    //    @BindView(R.id.tv_level)
//    TextView edLevel;
    @BindView(R.id.ed_lenght)
    EditText edLenght;
    @BindView(R.id.ed_diameter)
    EditText edDiameter;
    @BindView(R.id.ed_pihao)
    EditText edPihao;
    @BindView(R.id.cb_auto_pihao)
    CheckBox cbAutoPihao;
    @BindView(R.id.ed_search_pihao)
    EditText edSearchPihao;
    @BindView(R.id.btn_search_pihao)
    Button btnSearchPihao;
    //    private int activity = Config.PdSaleOrder2SaleOutActivity;
    private int tag = 27;

//    @BindView(R.id.zxing_barcode_scanner)
//    DecoratedBarcodeView zxingBarcodeScanner;
    //    @BindView(R.id.cb_scaning)
//    CheckBox cbScaning;
//    @BindView(R.id.search)
//    TextView search;
//    @BindView(R.id.tv_code)
//    TextView tvCode;
//    @BindView(R.id.tv_goodName)
//    TextView tvGoodName;
//    @BindView(R.id.tv_model)
//    TextView tvModel;
//    @BindView(R.id.tv_storenum)
//    TextView tvStorenum;
//    @BindView(R.id.sp_unit)
//    SpinnerUnit spUnit;
//    @BindView(R.id.sp_which_storage)
//    SpinnerStorage spWhichStorage;
//    @BindView(R.id.sp_wavehouse)
//    MyWaveHouseSpinner spWavehouse;
//    @BindView(R.id.ed_num)
//    EditText edNum;
//    @BindView(R.id.ed_pihao)
//    EditText edPihao;
//    @BindView(R.id.sp_auxsign)
//    SpinnerAuxSign spAuxsign;
//    @BindView(R.id.sp_actualmodel)
//    SpinnerActualModel spActualmodel;
//    @BindView(R.id.ed_purchase_no)
//    EditText edPurchaseNo;
//    @BindView(R.id.sp_unit_jiben)
//    SpinnerUnit spUnitJiben;
//    @BindView(R.id.ed_storenum)
//    TextView edStorenum;
//    @BindView(R.id.ed_basenum)
//    TextView edBasenum;
//    @BindView(R.id.sp_unit_store)
//    SpinnerUnit spUnitStore;
//    @BindView(R.id.tv_print)
//    TextView tvPrint;

    @BindView(R.id.lv_pushsub)
    ListView lvPushsub;
    @BindView(R.id.rv_numChoose)
    RecyclerView rvNumChoose;
    private FragmentActivity mContext;
    private PagerForActivity activityPager;
    Unbinder unbinder;
    private long ordercode;
    private Product product;
    private Storage storage;
    private WaveHouse waveHouse;
    private Unit unit;
    //    private Unit unitStore;
//    private Unit unitJiben;
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
    private List<PushDownSub> containerForSearch;
    private PushDownMainDao pushDownMainDao;
    private PushDownSubDao pushDownSubDao;
    private ArrayList<String> fidcontainer;
    private PushDownSubP2ListAdapter pushDownSubListAdapter;
    private String default_unitID;
    protected PushDownMain pushDownMain;
    private SearchBean.S2Product s2Product;//用于数据查找...
    private String mainBuyDept = "";//表头带出
    //    private String mainSaleMan = "";//表头带出
//    private String mainSaleOrg = "";//表头带出
//    private Org mainBuyOrg;//表头带出
    private Org mainStoreOrg;//表头带出
    private zpBluetoothPrinter zpSDK;
    private BlueToothBean bean;
    private ArrayList<String> container_Width;
    private NumRvAdapter numRvAdapter;
    private GridLayoutManager gridLayoutManager;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.UpdataList:
                String batch = (String) event.postEvent;
                if (container != null && container.size() > 0 && !"".equals(batch)) {
                    containerForSearch.clear();
                    for (PushDownSub data : container) {
                        if (data.FBatchNo.contains(batch)) {
                            containerForSearch.add(data);
                        }
                    }
                    pushDownSubListAdapter = new PushDownSubP2ListAdapter(mContext, containerForSearch);
                    lvPushsub.setAdapter(pushDownSubListAdapter);
                    pushDownSubListAdapter.notifyDataSetChanged();
                    setfocus(lvPushsub);
                } else if (container != null && container.size() > 0 && "".equals(batch)) {
                    pushDownSubListAdapter = new PushDownSubP2ListAdapter(mContext, container);
                    lvPushsub.setAdapter(pushDownSubListAdapter);
                    pushDownSubListAdapter.notifyDataSetChanged();
                }
                break;
            case EventBusInfoCode.Close_Activity:
                Bundle b = new Bundle();
                b.putInt("123", tag);
                startNewActivity(activityPager, PushDownPagerActivity.class, 0, 0, true, b);
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
                        String reString = mains.get(i).FBillerID + "|" + listOrder.get(i) + "|" + mains.get(i).FOrderId + "|" + mains.get(i).IMIE;
                        App.getRService().doIOAction(WebApi.P2ProductionInStoreUpload, reString, new MySubscribe<CommonResponse>() {
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
                    DataModel.submitAndAudit(mContext, Config.P2ProductionInStoreActivity, listOrder, tag);
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
                            DataService.pushBackJson(mContext, FragmentP2ProductionInStore2Detail.this.getClass().getSimpleName(), Hawk.get(Config.Company,""));
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
//                    spWhichStorage.setAuto("","",activityPager.getOrgOut());
                }
                break;
            case EventBusInfoCode.Print_Check://检测打印机连接状态
                String msg = (String) event.postEvent;
                LoadingUtil.dismiss();
                if ("OK".equals(msg)) {
                    Toast.showText(mContext,"打印机就绪");
//                    tvPrint.setText("打印机就绪");
//                    tvPrint.setTextColor(Color.BLACK);
                    zpSDK = App.getInstance().getZpk();
                } else {
                    AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                    ab.setTitle("连接打印机错误,请到先配置蓝牙打印机");
//            ab.setMessage("确认？");
                    ab.setPositiveButton("前往", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startNewActivity(activityPager, PrintOutTestActivity.class, R.anim.activity_slide_left_in, R.anim.activity_slide_left_out, false, null);
                            activityPager.finish();
                        }
                    });
                    ab.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            activityPager.finish();
                        }
                    });
                    ab.setNeutralButton("重连", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LoadingUtil.showDialog(mContext,"正在重连...");
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    App.getInstance().connectPrint();
//                                    checkPrint(false);
                                }
                            }).start();
                        }
                    });
                    ab.create().show();
//                    tvPrint.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            startNewActivity(activityPager, PrintOutTestActivity.class, R.anim.activity_slide_left_in, R.anim.activity_slide_left_out, false, null);
////                            activityPager.finish();
//                        }
//                    });
//                    tvPrint.setText("连接打印机错误");
//                    tvPrint.setTextColor(Color.RED);
                }
                break;
        }
    }

    public FragmentP2ProductionInStore2Detail() {
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
        View view = inflater.inflate(R.layout.fragment_productioninstore_detail, container, false);
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
//
//        zpSDK = new zpBluetoothPrinter(mContext);
//        bean = Hawk.get(Config.OBJ_BLUETOOTH, new BlueToothBean("", ""));
//        spUnitJiben.setEnabled(false);
//        spUnitStore.setEnabled(false);

        gridLayoutManager = new GridLayoutManager(mContext, 5);
        container_Width = new ArrayList<>();
        numRvAdapter = new NumRvAdapter(mContext, container_Width);
        rvNumChoose.setAdapter(numRvAdapter);
        rvNumChoose.addItemDecoration(new RecyclerViewDivider(3));
        rvNumChoose.setLayoutManager(gridLayoutManager);
//        int max = Hawk.get(Config.Jingji_Max,58);
        int num = 20;
        for (int i = 0; i < 20; i++) {
            container_Width.add(num + "");
            num += 2;
//            if (i % 2 == 0) container_Width.add(i + "");
        }
        numRvAdapter.notifyDataSetChanged();
//        gridLayoutManager.scrollToPosition(24);

        zpSDK = App.getInstance().getZpk();
    }

    @Override
    protected void initData() {
        s2Product = new SearchBean.S2Product();
        listOrder = new ArrayList<>();
//        spAuxsign.setEnabled(false);
//        spActualmodel.setEnabled(false);

        container = new ArrayList<>();
        containerForSearch = new ArrayList<>();
        fidcontainer = activityPager.getIntent().getExtras().getStringArrayList("fid");
//        ordercode = CommonUtil.createOrderCode(activityPager.getActivity()+fidcontainer.get(0));//单据编号
        getList();
        List<PushDownMain> pushDownMains = pushDownMainDao.queryBuilder().where(
                PushDownMainDao.Properties.FBillNo.eq(fidcontainer.get(0))).build().list();
        if (pushDownMains.size() > 0) {
            Lg.e("表头：", pushDownMains.get(0));
            pushDownMain = pushDownMains.get(0);
            mainBuyDept = LocDataUtil.getDept(pushDownMain.FSaleDeptID).FNumber;
//            mainSaleMan = LocDataUtil.getSaleMan(pushDownMain.FSaleManID).FNumber;
//            mainSaleOrg = LocDataUtil.getOrg(pushDownMain.FSaleOrgID,"id").FNumber;
            mainStoreOrg = LocDataUtil.getOrg(pushDownMain.FStoreOrgID, "id");
//            Lg.e("得到表头解析数据:"+mainSaleDept);
//            Lg.e("得到表头解析数据:"+mainSaleMan);
//            Lg.e("得到表头解析数据:"+mainSaleOrg);
//            Lg.e("得到表头解析数据:",mainStoreOrg);
//            activityPager.setOrgOut(mainStoreOrg);
//            mainBuyOrg = activityPager.getOrgOut();
            EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.UpdataView, pushDownMain));
//            if ("".equals(pushDownMain.FSupplyID == null ? "" : pushDownMain.FSupplyID)) {
//                LoadingUtil.showAlter(mContext, "注意", "表头明细的客户数据带出失败，请重试...", false);
//            }
            if ("".equals(mainBuyDept)) {
                mainBuyDept = LocDataUtil.getDept(LocDataUtil.getSaleMan(pushDownMain.FSaleManID).FDeptID).FNumber;
            }
        } else {
            LoadingUtil.showAlter(mContext, getString(R.string.attention), getString(R.string.main_load_fail), false);
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
        //批号查询监听，当为空时，自动更新列表
        edSearchPihao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edSearchPihao.getText().toString().equals("")){
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.UpdataList, edSearchPihao.getText().toString()));
                }
            }
        });
        //批号查询长按清空
        edSearchPihao.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                edSearchPihao.setText("");
                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.UpdataList, edSearchPihao.getText().toString()));
                return true;
            }
        });
        numRvAdapter.setOnItemClickListener(new NumRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
//                posi=position;
//                if (!checkInput()){
//                    return;
//                }
                edDiameter.setText(numRvAdapter.getItem(position));
                VibratorUtil.Vibrate(mContext, Info.VibratorTime);

//                getProduct(item);

            }
        });
//        spWhichStorage.setOnItemSelectedListener(new ItemListener() {
//            @Override
//            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                storage = (Storage) spWhichStorage.getAdapter().getItem(i);
//                spWhichStorage.setTitleText(storage.FName);
//                Lg.e("选中仓库：", storage);
//                waveHouse = null;
//                spWavehouse.setAuto(mContext, storage, "");
////                DataModel.getStoreNum(product, storage, edPihao.getText().toString().trim(), mContext, tvStorenum,activityPager.getOrgOut());
//
//            }
//        });
//        spWavehouse.setOnItemSelectedListener(new ItemListener() {
//            @Override
//            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                waveHouse = (WaveHouse) spWavehouse.getAdapter().getItem(i);
//                Lg.e("选中仓位：", waveHouse);
//            }
//        });
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
                pushDownSubListAdapter.setChangeColor(i);
                Lg.e("点击明细:", pushDownSub);
                VibratorUtil.Vibrate(mContext, Info.VibratorTime);
                LoadingUtil.showDialog(mContext,"正在获取数据...");
                if (BasicShareUtil.getInstance(mContext).getIsOL()) {
                    s2Product.likeOr = pushDownSub.FNumber;
                    s2Product.FOrg = mainStoreOrg == null ? "" : mainStoreOrg.FOrgID;
                    App.getRService().doIOAction(WebApi.S2Product, gson.toJson(new SearchBean(SearchBean.product_for_number, gson.toJson(s2Product))), new MySubscribe<CommonResponse>() {
                        @Override
                        public void onNext(CommonResponse commonResponse) {
                            super.onNext(commonResponse);
                            LoadingUtil.dismiss();
                            if (!commonResponse.state) return;
                            final DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                            Log.e("product.size", dBean.products.size() + "");
                            if (dBean.products.size() > 0) {
                                product = dBean.products.get(0);
                                Log.e("product.size", product + "");
                                dealProduct();
                            } else {
                                Toast.showText(mContext, getString(R.string.product_find_nothing));
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
//                            super.onError(e);
                            LoadingUtil.dismiss();
                            Toast.showText(mContext, getString(R.string.product_find_error) + e.toString());
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

    }

    private void getList() {
        container.clear();
        containerForSearch.clear();
        pushDownSubDao = daoSession.getPushDownSubDao();
        pushDownMainDao = daoSession.getPushDownMainDao();
        for (int i = 0; i < fidcontainer.size(); i++) {
            List<PushDownSub> list = pushDownSubDao.queryBuilder().where(
                    PushDownSubDao.Properties.FBillNo.eq(fidcontainer.get(i))).build().list();
            container.addAll(list);
        }
        if (container.size() > 0) {
            pushDownSubListAdapter = new PushDownSubP2ListAdapter(mContext, container);
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
        if (!cbAutoPihao.isChecked())
            edDiameter.setText(pushDownSub.FYmDiameter);
        if (!cbAutoPihao.isChecked()) edLenght.setText(pushDownSub.FYmLenght);
        if (!cbAutoPihao.isChecked())
            edPihao.setText(pushDownSub.FBatchNo == null ? "" : pushDownSub.FBatchNo);
//        edDiameter.setText(MathUtil.D2save2(Double.parseDouble(pushDownSub.FYmDiameter)) + "");
//        edLenght.setText(MathUtil.D2save0(DoubleUtil.mulX10(pushDownSub.FYmLenght)) + "");
//        edLenght.setText(DoubleUtil.Cut0(DoubleUtil.CutTo0(DoubleUtil.mulX10(pushDownSub.FYmLenght)+"") + ""));
//        Lg.e(DoubleUtil.divC10("0.19", 1) + "");
//        Lg.e(DoubleUtil.divC10("0.54", 1) + "");
//        Lg.e(DoubleUtil.divC10("1.92", 1) + "");
//        Lg.e(DoubleUtil.divC10("1.95", 1) + "");
//        edLevel.setText(pushDownSub.FLevel==null?"":pushDownSub.FLevel);
//        tvGoodName.setText(product.FName);
//        tvModel.setText(product.FModel);
//        tvCode.setText(product.FNumber);
        //带出物料的默认值
//        unitStore = LocDataUtil.getUnit(product.FStoreUnitID);
//        unitJiben = LocDataUtil.getUnit(product.FBaseUnitID);
//        spUnit.setAuto(product.FPurchaseUnitID, SpinnerUnit.Id);
//        spUnitJiben.setAuto(product.FBaseUnitID,SpinnerUnit.Id);
//        spUnitStore.setAuto(product.FStoreUnitID, SpinnerUnit.Id);
//        if (activityPager.isStorage()) {
//            spWhichStorage.setAutoSelection("", product.FStockID);
//        spWhichStorage.setAuto("",autoStorage, activityPager.getOrgOut());
//        unit = LocDataUtil.getUnit(product.FPurchaseUnitID);
//        }
//        if (CommonUtil.isOpen(product.FIsBatchManage)) {
//            isOpenBatch = true;
////            edPihao.setEnabled(true);
//        } else {
////            edPihao.setEnabled(false);
////            edPihao.setText("");
//            isOpenBatch = false;
//        }
//        spAuxsign.getData(product.FMASTERID, "常规");
//        spActualmodel.getData(product.FMASTERID, "");

        //自动添加
        if (cbAutoPihao.isChecked()) {
//            edDiameter.setClickable(false);
            edDiameter.setFocusable(true);
//            edLenght.setClickable(false);
            edLenght.setFocusable(true);
        } else {
//            edDiameter.setClickable(true);
            edDiameter.setFocusable(false);
//            edLenght.setClickable(true);
            edLenght.setFocusable(false);
        }
        if (activityPager.getIsAuto().isChecked()) {
            if (!checkBeforeAdd()) {
//                activityPager.ReSetScan(cbScaning);
            }
        } else {
//            activityPager.ReSetScan(cbScaning);
        }
    }

    private String volem="0";
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
        if ("".equals(edLenght.getText().toString()) || MathUtil.toD(edLenght.getText().toString()) <= 0) {
            Toast.showText(mContext, "长度不能小于等于 0 ");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }

//        if (mainStoreOrg.FNumber.equals("")) {
//            Toast.showText(mContext, "采购组织不能为空");
//            MediaPlayer.getInstance(mContext).error();
//            return false;
//        }
        unit = LocDataUtil.getUnit(pushDownSub.FUnitID);
        if (unit == null || unit.FMeasureUnitID.equals("")) {
            Toast.showText(mContext, "物料单位未带出，请重试...");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        Lg.e("单位1111：", unit);
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
        ordercode = CommonUtil.createOrderCode(activityPager.getActivity() + pushDownMain.FID + mainStoreOrg.FNumber);//单据编号
        //插入条码唯一临时表
//        CodeCheckBean bean = new CodeCheckBean(barcode, ordercode + "", edNum.getText().toString(), BasicShareUtil.getInstance(mContext).getIMIE());
//        DataModel.codeOnlyInsert(WebApi.CodeCheckInsertForOut, gson.toJson(bean));
//        Addorder();
        volem = MathUtil.getVoleum2(edLenght.getText().toString() + "", edDiameter.getText().toString())+"";
        Lg.e("得到体积"+volem);
        LoadingUtil.showDialog(mContext, "正在获取条码数据...");
        String pdata = product.FMaterialid + "|" + unit.FMeasureUnitID + "|" + volem + "|" + pushDownSub.ActualModel + "|" + pushDownSub.AuxSign + "|" + ""
                + "|" + BasicShareUtil.getInstance(mContext).getIMIE() + "|" + activityPager.getStorage().FNumber + "|" + activityPager.getOrgIn(0)
                + "|" + pushDownSub.FLevel + "|" + edLenght.getText().toString() + "" + "|" + edDiameter.getText().toString()
                + "|" + pushDownSub.FBLenght + "|" + pushDownSub.FBWide + "|" + pushDownSub.FBThick + "|" + volem
                + "|" + (cbAutoPihao.isChecked() ? "" : pushDownSub.FBatchNo) + "|"
                + activityPager.getDepartMent().replaceAll("\\.", "") + "|" + (cbAutoPihao.isChecked() ? "1" : "0")+"|"+""+"|"+"1"+"|"+"0";
        App.getRService().doIOAction(WebApi.PrintData4P2, pdata, new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);
                if (!commonResponse.state) return;
                DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                if (null != dBean && dBean.printDataBeans.size() > 0) {
                    Lg.e("返回打印数据：", dBean.printDataBeans);
                    barcode = dBean.printDataBeans.get(0).FBarCode;
                    auxNum = dBean.printDataBeans.get(0).FAuxNum;
                    batch = dBean.printDataBeans.get(0).FBatch;
                    baseNum = dBean.printDataBeans.get(0).FBaseNum;
                    storeNum = dBean.printDataBeans.get(0).FStoreNum;
//                    edBasenum.setText(baseNum);
//                    edStorenum.setText(storeNum);
                    //把需要打印的数据保存到本地
                    PrintHistory printHistory = new PrintHistory();
                    printHistory.setProject("2");
                    printHistory.setData(product, unit, unit, MathUtil.getVoleum4Print(edLenght.getText().toString() + "", edDiameter.getText().toString())+"",
                            edLenght.getText().toString() + "", "", activityPager.getNote(),
                            activityPager.getOrgIn().FNote, barcode, batch, CommonUtil.getTime(true), "", edDiameter.getText().toString(), "");
                    daoSession.getPrintHistoryDao().insert(printHistory);
                    try {
                        CommonUtil.doPrint4P2(zpSDK, printHistory, activityPager.getPrintNum());
                    } catch (Exception e) {
                        Lg.e("打印错误",e.getMessage());
                        Toast.showText(mContext,"打印错误"+e.getMessage());
                        App.getInstance().connectPrint();
                    }
                    //-----END
                    CodeCheckBean bean = new CodeCheckBean(barcode, ordercode + "", activityPager.getStorage().FItemID, waveHouse == null ? "" : waveHouse.FSPID, BasicShareUtil.getInstance(mContext).getIMIE());
                    DataModel.codeOnlyInsert(WebApi.CodeCheckInsertForIn, gson.toJson(bean));
                } else {
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
            main.FSoorDerno = pushDownMain.FBillNo;
            main.FID = pushDownSub.FID;
            main.FIndex = timesecond;
            main.FBillNo = pushDownMain.FBillNo;
            main.setData(Info.getType(activity), activityPager.getOrgOut(0), activityPager.getOrgIn(0), activityPager.getOrgIn(0));
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
            detail.IMIE = BasicShareUtil.getInstance(mContext).getIMIE();
            detail.FOrderId = ordercode;
            detail.FIndex = timesecond;
            detail.FEntryID = pushDownSub.FEntryID;
            detail.FID = pushDownSub.FID;
//            detail.FHuoZhuNumber = scanOfHuozhuNumber;
            detail.FHuoZhuNumber = activityPager.getHuozhuOut(0);
            detail.FSOEntryId = pushDownSub.FEntryID;
            detail.FRemainInStockQty = pushDownSub.FQty;
            detail.FTaxPrice = pushDownSub.FTaxPrice;
            detail.FRealQty = volem;
            detail.FWorkShopId1 = activityPager.getDepartMent();
//            detail.FStoreNum = edStorenum.getText().toString();
//            detail.FBaseNum = edBasenum.getText().toString();
//            detail.FBackType = "THLX01_SYS";
            detail.FBackDate = CommonUtil.getTime(true);
//            detail.FIsFree = false;
//            detail.FProductNo = edPurchaseNo.getText().toString();
            detail.FBatch = cbAutoPihao.isChecked() ? batch : pushDownSub.FBatchNo;
            detail.AuxSign = pushDownSub.AuxSign;
            detail.ActualModel = pushDownSub.ActualModel;
            detail.setProduct(product);
            detail.setStorage(activityPager.getStorage());
            detail.setWaveHouse(waveHouse);
            detail.setUnit(unit);
            detail.FLevel = pushDownSub.FLevel;
            detail.FYmLenght = edLenght.getText().toString();
            detail.FYmDiameter = edDiameter.getText().toString();
            detail.FBLenght = pushDownSub.FBLenght;
            detail.FBWide = pushDownSub.FBWide;
            detail.FBThick = pushDownSub.FBThick;
            detail.FVolume = volem;
//            detail.setBaseUnit(spUnitJiben.getDataObject());
//            detail.setStoreUnit(spUnitStore.getDataObject());
            long insert2 = t_detailDao.insert(detail);

            if (insert1 > 0 && insert2 > 0) {
//                pushDownSub.FQtying = DoubleUtil.sum(MathUtil.toD(pushDownSub.FQtying),
//                        (MathUtil.toD(edNum.getText().toString()))) + "";
//                pushDownSubDao.update(pushDownSub);
                if (!cbAutoPihao.isChecked()) {
                    pushDownSub.FIsPrint = "1";
                    pushDownSubDao.update(pushDownSub);
                }

                pushDownSubListAdapter.notifyDataSetChanged();
                Lg.e("成功添加表头：", main);
                Lg.e("成功添加明细：", detail);
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
//        activityPager.ReSetScan(cbScaning);
//        edPurchaseNo.setText("");
        listOrder.clear();
        barcode = "";
        batch = "";
        volem="0";
        edPihao.setText("");
        edPihao.setHint("");
        edDiameter.setText("");
        edLenght.setText("");
//        edPihao.setText("");
//        edNum.setText("");
        if (!cbAutoPihao.isChecked()){
            product = null;
            pushDownSub = null;
        }
        //定位到没有打印的第一条
        if (null != pushDownSubListAdapter) {
            for (int i = 0; i < pushDownSubListAdapter.getCount(); i++) {
                Lg.e("遍历" + i);
                if (!"1".equals(((PushDownSub) pushDownSubListAdapter.getItem(i)).FIsPrint)) {
                    lvPushsub.setSelection(i);
                    break;
                }
            }
        }
        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock));

    }

    //检测打印机连接状态
    private void checkPrint(boolean check) {
        if (bean.address.equals("")) {
            EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Print_Check, "NOOK"));
        } else {
            if (!zpSDK.connect(bean.address)) {
                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Print_Check, "NOOK"));
            } else {
                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Print_Check, "OK"));
            }
        }
    }

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

    @OnClick({R.id.btn_add, R.id.btn_search_pihao, R.id.btn_add_pihao, R.id.btn_finishorder, R.id.btn_backorder, R.id.btn_checkorder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                checkBeforeAdd();
                break;
            case R.id.btn_backorder:
                new AlertDialog.Builder(mContext)
                        .setTitle("确认上传？")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LoadingUtil.showDialog(mContext, "正在上传...");
                                UpLoadModel.actionPushDown(mContext, activity, pushDownMain.FID);
                            }
                        })
                        .create().show();
                break;
            case R.id.btn_finishorder:
                finishOrder();
                break;
            case R.id.btn_search_pihao:
                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.UpdataList, edSearchPihao.getText().toString()));
                break;
            case R.id.btn_checkorder:
                Bundle bundle = new Bundle();
                bundle.putString("fid", pushDownMain.FID);
                bundle.putInt("activity", activity);
                startNewActivity(activityPager, ReViewPDAP2ctivity.class,
                        R.anim.activity_fade_in, R.anim.activity_fade_out, false, bundle);
                break;
            case R.id.btn_add_pihao:
                if (cbAutoPihao.isChecked()) {
                    Lg.e("长度直径不可输入");
                    cbAutoPihao.setChecked(false);
                    edDiameter.setClickable(false);
                    edDiameter.setFocusable(false);
                    edLenght.setClickable(false);
                    edLenght.setFocusable(false);
                    edPihao.setHint("选择列表带出批号");
                } else {
                    Lg.e("长度直径可输入");
                    cbAutoPihao.setChecked(true);
                    edDiameter.setFocusableInTouchMode(true);
                    edDiameter.setClickable(true);
                    edDiameter.setFocusable(true);
                    edLenght.setFocusableInTouchMode(true);
                    edLenght.setClickable(true);
                    edLenght.setFocusable(true);
                    edPihao.setText("");
                    edLenght.setText("");
                    edDiameter.setText("");
                    lvPushsub.setSelection(0);
                    lvPushsub.performItemClick(lvPushsub.getChildAt(0), 0, lvPushsub.getItemIdAtPosition(0));
                    edPihao.setHint("自动生成");
                }


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
