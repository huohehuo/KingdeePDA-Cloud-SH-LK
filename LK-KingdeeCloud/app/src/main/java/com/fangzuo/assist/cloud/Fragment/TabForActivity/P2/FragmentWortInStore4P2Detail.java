package com.fangzuo.assist.cloud.Fragment.TabForActivity.P2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.fangzuo.assist.cloud.Activity.ProductCheck4P24WortActivity;
import com.fangzuo.assist.cloud.ABase.BaseFragment;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Activity.PagerForActivity;
import com.fangzuo.assist.cloud.Activity.PrintOutTestActivity;
import com.fangzuo.assist.cloud.Activity.ReViewP24WortActivity;
import com.fangzuo.assist.cloud.Adapter.CountOffAdapter;
import com.fangzuo.assist.cloud.Adapter.PushDownListAdapter;
import com.fangzuo.assist.cloud.Adapter.StringSpAdapter;
import com.fangzuo.assist.cloud.Beans.BackData;
import com.fangzuo.assist.cloud.Beans.CodeCheckBackDataBean;
import com.fangzuo.assist.cloud.Beans.CommonBean;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.PurchaseInStoreUploadBean;
import com.fangzuo.assist.cloud.Beans.SearchBean;
import com.fangzuo.assist.cloud.Beans.WortPrintData;
import com.fangzuo.assist.cloud.Dao.NumberBean;
import com.fangzuo.assist.cloud.Dao.Product;
import com.fangzuo.assist.cloud.Dao.PushDownMain;
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
import com.fangzuo.assist.cloud.Utils.JsonCreater;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.LocDataUtil;
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
import com.fangzuo.assist.cloud.widget.SpinnerUnit;
import com.fangzuo.greendao.gen.DaoSession;
import com.fangzuo.greendao.gen.NumberBeanDao;
import com.fangzuo.greendao.gen.PushDownMainDao;
import com.fangzuo.greendao.gen.T_DetailDao;
import com.fangzuo.greendao.gen.T_mainDao;
import com.google.gson.Gson;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
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
public class FragmentWortInStore4P2Detail extends BaseFragment {

    //    @BindView(R.id.sp_which_storage)
//    SpinnerStorage spWhichStorage;
    @BindView(R.id.sp_wavehouse)
    MyWaveHouseSpinner spWavehouse;
    //    @BindView(R.id.sp_department_get)
//    SpinnerDepartMent spDepartmentGet;
    @BindView(R.id.tv_print)
    TextView tvPrint;
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.tv_goodName)
    TextView tvGoodName;
    @BindView(R.id.tv_model)
    TextView tvModel;
    @BindView(R.id.tv_code)
    TextView tvCode;
    //    @BindView(R.id.sp_unit)
//    SpinnerUnit spUnit;
    @BindView(R.id.ed_num)
    EditText edNum;
    //    @BindView(R.id.sp_unit_aux)
//    SpinnerUnit spUnitAux;
//    @BindView(R.id.ed_purchase_no)
//    EditText edPurchaseNo;
    @BindView(R.id.sp_auxsign)
    SpinnerAuxSign spAuxsign;
    @BindView(R.id.sp_actualmodel)
    SpinnerActualModel spActualmodel;
    @BindView(R.id.sp_unit_jiben)
    SpinnerUnit spUnitJiben;
    @BindView(R.id.ed_storenum)
    TextView edStorenum;
    @BindView(R.id.ed_basenum)
    TextView edBasenum;
    @BindView(R.id.sp_unit_store)
    SpinnerUnit spUnitStore;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.ry_wide)
    EasyRecyclerView ryWide;
    //    @BindView(R.id.ed_thick)
//    EditText edThick;
//    @BindView(R.id.ed_lenght)
//    EditText edLenght;
//    @BindView(R.id.ed_ceng)
//    EditText edCeng;
    @BindView(R.id.ed_lenght)
    EditText edLenght;
    //    @BindView(R.id.ry_countoff)
//    EasyRecyclerView ryCountoff;
    @BindView(R.id.sp_lenght)
    Spinner spLenght;
    @BindView(R.id.tv_pcs_sum)
    AppCompatTextView tvPcsSum;
    @BindView(R.id.tv_m2_sum)
    AppCompatTextView tvM2Sum;
    @BindView(R.id.ed_batch_remark)
    EditText edBatchRermark;
    private FragmentActivity mContext;
    private PagerForActivity activityPager;
    Unbinder unbinder;
    private long ordercode;
    private long boxcodeOrder;
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
    private String boxcode = "";
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
    private zpBluetoothPrinter zpSDK;
    private CountOffAdapter adapter;
    //    private CountOffListAdapter adapterList;
    private StringSpAdapter adapterLenght;
    private String volume;
//    private int countOff;
    private String cfLenght;
    private String cfThick;
    private String cfWide;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.Product_4Wort:
                String number = (String) event.postEvent;
                Hawk.put(Info.WortProductNumber, number);
                getProduct(number);
                break;
            case EventBusInfoCode.Product:
                product = (Product) event.postEvent;
                Lg.e("获得物料信息：", product);
//                binding.setProduct(product);
                String[] split = product.FNumber.split("\\.", 7);
                Lg.e("截取长度", split.length);
                Lg.e("截取长度", split);
                if (split.length == 7) {
                    cfThick = MathUtil.toInt(split[4])+"";
                    cfWide = MathUtil.toInt(split[5])+"";
                }
                Lg.e("得到厚度", cfThick);
                Lg.e("得到宽度", cfWide);
                dealProduct();
                break;
            case EventBusInfoCode.Upload_OK://回单成功
                BackData backData = (BackData) event.postEvent;
                if (backData.getResult().getResponseStatus().getIsSuccess()) {
                    //获取生成的单号数据
                    for (int i = 0; i < backData.getResult().getResponseStatus().getSuccessEntitys().size(); i++) {
                        listOrder.add(backData.getResult().getResponseStatus().getSuccessEntitys().get(i).getNumber());
                    }
                    final List<T_main> mains = t_mainDao.queryBuilder().where(T_mainDao.Properties.Activity.eq(activity)).build().list();
                    for (int i = 0; i < mains.size(); i++) {
                        final int pos = i;
                        String reString = mains.get(i).FBillerID + "|" + listOrder.get(i) + "|" + mains.get(i).FOrderId + "|" + mains.get(i).IMIE;
                        App.getRService().doIOAction(WebApi.PrISUpload, reString, new MySubscribe<CommonResponse>() {
                            @Override
                            public void onNext(CommonResponse commonResponse) {
                                super.onNext(commonResponse);
                                t_detailDao.deleteInTx(t_detailDao.queryBuilder().where(
                                        T_DetailDao.Properties.Activity.eq(activity),
                                        T_DetailDao.Properties.FOrderId.eq(mains.get(pos).FOrderId)
                                ).build().list());
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                            }
                        });
                    }
                    ordercode++;
                    share.setOrderCode(activity, ordercode);
                    MediaPlayer.getInstance(mContext).ok();
                    adapter.clear();
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock + "NO"));//上传成功，解锁表头
                    Toast.showText(mContext, "上传成功");
//                btnBackorder.setClickable(true);
                    LoadingUtil.dismiss();
                    DataModel.submitAndAudit(mContext, Config.ProductInStoreActivity, listOrder.get(0));
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
                            DataService.pushBackJson(mContext, FragmentWortInStore4P2Detail.this.getClass().getSimpleName(), Hawk.get(Config.Company,""));
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
                    LoadingUtil.dismiss();
                    Toast.showText(mContext, codeCheckBackDataBean.FTip);
                }
                break;
            case EventBusInfoCode.UpdataView://由表头的数据决定是否更新明细数据
                if (null != activityPager) {
//                    spWhichStorage.setAuto("", activityPager.getOrgOut());
//                    spDepartmentGet.setAuto(getString(R.string.spDepartmentGet_pis), "", activityPager.getOrgOut(), activityPager.getActivity());
//                    spUnit.setAuto("", activityPager.getOrgOut(), SpinnerUnit.Id);
//                    spUnitJiben.setAuto("", SpinnerUnit.Id);
//                    spUnitStore.setAuto("", SpinnerUnit.Id);
//                    spUnitAux.setAuto("", activityPager.getOrgOut(), SpinnerUnit.Id);
                }
                break;
            case EventBusInfoCode.UpdataWaveHouse://检测打印机连接状态.
//                Storage storage = (Storage) event.postEvent;
                waveHouse = null;
                spWavehouse.setAuto(mContext, activityPager.getStorage(), "");
                break;
            case EventBusInfoCode.Print_Check://检测打印机连接状态
                String msg = (String) event.postEvent;
                LoadingUtil.dismiss();
                if ("OK".equals(msg)) {
                    tvPrint.setText("打印机就绪");
                    tvPrint.setTextColor(Color.BLACK);
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
                            LoadingUtil.showDialog(mContext, "正在重连...");
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
                    tvPrint.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            App.getInstance().connectPrint();
//                            startNewActivity(activityPager, PrintOutTestActivity.class, R.anim.activity_slide_left_in, R.anim.activity_slide_left_out, false, null);
//                            activityPager.finish();
                        }
                    });
                    tvPrint.setText("连接打印机错误");
                    tvPrint.setTextColor(Color.RED);
                }
                break;

        }
    }

    //查找物料
    private SearchBean.S2Product s2Product;//用于数据查找...
    private List<Product> productList;

    private void getProduct(String number) {
        LoadingUtil.showDialog(mContext, "正在查找物料");
        Lg.e("getProduct:" + number);
        if ("".equals(number)) {
            LoadingUtil.dismiss();
            return;
        }
        //初始化
        productList = new ArrayList<>();
        lenghtList = new ArrayList<>();
        s2Product = new SearchBean.S2Product();
        //确认物料过滤条件
        s2Product.FIsProduce = "1";
        s2Product.FOrg = activityPager.getOrgOut(1);
        s2Product.likeOr = number;
        App.getRService().doIOAction(WebApi.ProductSearchForTree, gson.toJson(new SearchBean(SearchBean.product_for_like, gson.toJson(s2Product))), new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);
                LoadingUtil.dismiss();
                if (!commonResponse.state) return;
                DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                if (dBean.products.size() > 0) {
                    productList = dBean.products;
                    Lg.e("得到物料list" + productList.size(), productList);
                    getLenght(dBean.products);
                } else {
                    Toast.showText(mContext, "查无相关物料数据");
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                LoadingUtil.dismiss();
//                binding.pg.setVisibility(View.GONE);
            }
        });
    }

    private List<NumberBean> lenghtList;
    //获取过滤出长度信息
    private void getLenght(List<Product> products) {
        lenghtList = new ArrayList<>();
        daoSession.getNumberBeanDao().deleteAll();
        for (Product bean : products) {
            if (bean.FModel.contains("*")) {
//                if (!lenghtList.contains(bean.FModel.substring(0, bean.FModel.indexOf("*"))))
//                    lenghtList.add(bean.FModel.substring(0, bean.FModel.indexOf("*")));
                List<NumberBean> list = daoSession.getNumberBeanDao().queryBuilder().where(NumberBeanDao.Properties.FNumber.eq(bean.FModel.substring(0, bean.FModel.indexOf("*")))).build().list();
                if (list.size()<=0){
                    daoSession.getNumberBeanDao().save(new NumberBean(MathUtil.toInt(bean.FModel.substring(0, bean.FModel.indexOf("*")))));
                }
            }
        }
        Lg.e("得到长度list", lenghtList);
        lenghtList = daoSession.getNumberBeanDao().queryBuilder().orderAsc(NumberBeanDao.Properties.FNumber).build().list();
        if (lenghtList.size() <= 0) {
            LoadingUtil.showAlter(mContext, "不存在长度信息");
        }
        adapterLenght = new StringSpAdapter(mContext, lenghtList);
        spLenght.setAdapter(adapterLenght);
    }

    //检测出是否存在当前长度的物料信息
    private boolean chooseProduct(String lenght) {
        if (null == productList) return false;
        if (lenghtList.size() <= 0) {
            Toast.showText(mContext, "不存在长度信息");
            return false;
        }
        boolean hasData = false;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).FModel.contains("*")) {
                String cutModel = productList.get(i).FModel.substring(0, productList.get(i).FModel.indexOf("*"));
                if (cutModel.equals(lenght)) {
                    hasData = true;
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Product, productList.get(i)));
                    break;
                }
            }
        }
        if (!hasData) {
            Toast.showText(mContext, "不存在与该长度相关的物料");
        }
        return hasData;
    }

    public FragmentWortInStore4P2Detail() {
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
        View view = inflater.inflate(R.layout.fragment_wortinstore_p2_detail, container, false);
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
        gson = activityPager.getGson();
        share = activityPager.getShare();

        spUnitJiben.setEnabled(false);
        spUnitStore.setEnabled(false);
        ryWide.setAdapter(adapter = new CountOffAdapter(mContext));
        ryWide.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initData() {
        Lg.e("Fg_D:" + "initData");
        listOrder = new ArrayList<>();
        ordercode = CommonUtil.createOrderCode(activity);//单据编号
        boxcodeOrder = CommonUtil.createBoxCode(Info.WortBoxCode);//单据编号
//        countOff = CommonUtil.getCountOffNumber(activity, false);
        batch = Hawk.get(Info.WortBatch+boxcodeOrder,"");
        boxcode = Hawk.get(Info.WortBoxCode+boxcodeOrder,"");

    }
    @Override
    public void onResume() {
        super.onResume();
        //执行该方法时，Fragment处于活动状态，用户可与之交互。
        Lg.e("onResume");
        if (LocDataUtil.hasTDetail(activityPager.getActivity())) {
//            edLenght.setEnabled(false);
//            edLenght.setFocusable(false);
//            edLenght.setFocusableInTouchMode(false);
//            search.setEnabled(false);
//            edLenght.setText(Hawk.get(Info.WortLenght + activity, ""));
            EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Product_4Wort, Hawk.get(Info.WortProductNumber, "")));
        } else {
//            search.setEnabled(true);
//            edLenght.setEnabled(true);
//            edLenght.setFocusable(true);
//            edLenght.setFocusableInTouchMode(true);
//            edLenght.setText("");
            boxcodeOrder++;
            Log.e("装箱识别码boxcodeOrder", boxcodeOrder + "");
            share.setOrderCode(Info.WortBoxCode, boxcodeOrder);
        }
        loadCountOff();
        batch = Hawk.get(Info.WortBatch+boxcodeOrder,"");
        boxcode = Hawk.get(Info.WortBoxCode+boxcodeOrder,"");
        if (checkHasPackingData()){
            edLenght.setEnabled(false);
            edLenght.setFocusable(false);
            edLenght.setFocusableInTouchMode(false);
            search.setEnabled(false);
            edLenght.setText(Hawk.get(Info.WortLenght + activity, ""));
        }else{
            search.setEnabled(true);
            edLenght.setEnabled(true);
            edLenght.setFocusable(true);
            edLenght.setFocusableInTouchMode(true);
            edLenght.setText("");
        }
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
    protected void OnReceive(String barCode) {

    }


    @Override
    protected void initListener() {
        //装箱
        btnAdd.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                new AlertDialog.Builder(mContext)
                        .setTitle("是否装箱？")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                packing();
                            }
                        })
                        .create().show();
//                addCountOff();
//                checkMainDlg();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductCheck4P24WortActivity.start(activityPager, activity, tvCode.getText().toString(), activityPager.getOrgOut(1));
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Lg.e("点击历史：", adapter.getAllData().get(position));
                changeDetail(adapter.getAllData().get(position));
            }
        });
//        adapterList.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(final int position) {
//                Lg.e("点击历史：", adapterList.getAllData().get(position));
//                new AlertDialog.Builder(mContext)
//                        .setTitle("是否删除报数：" + adapterList.getAllData().get(position).FCountNumber)
//                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                delCountOff(adapterList.getAllData().get(position));
//                            }
//                        })
//                        .setNeutralButton("取消", null)
//                        .create().show();
//                return true;
//            }
//        });
        spLenght.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                NumberBean numberBean = (NumberBean)lenghtList.get(i);
                cfLenght = numberBean.FNumber+"";
                chooseProduct(cfLenght);
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
//            }
//        });
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
        tvPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        App.getInstance().connectPrint();
                    }
                }).start();
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
        //带出物料的默认值
        spUnitJiben.setAuto(product.FBaseUnitID, SpinnerUnit.Id);
        spUnitStore.setAuto(product.FStoreUnitID, SpinnerUnit.Id);
        unit = LocDataUtil.getUnit(product.FProduceUnitID);
        Lg.e("得到单位：", unit);
//        spUnit.setAuto(product.FPurchaseUnitID, activityPager.getOrgOut(), SpinnerUnit.Id);
//        spUnitAux.setAuto(product.FAuxUnitID, activityPager.getOrgOut(), SpinnerUnit.Id);
//        if (activityPager.isStorage()) {
//        Lg.e("更新仓库");
//            spWhichStorage.setAutoSelection("", product.FStockID);
//            spWhichStorage.setAuto(product.FStockID, activityPager.getOrgOut());
//        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.UpdataStorage, product.FStockID));

//        }
//        if (CommonUtil.isOpen(product.FIsBatchManage)) {
//            isOpenBatch = true;
//            edPihao.setEnabled(true);
//        } else {
//            edPihao.setEnabled(false);
//            edPihao.setText("");
//            isOpenBatch = false;
//        }

        spAuxsign.getData(product.FMASTERID, "常规");
        spActualmodel.getData(product.FMASTERID, "");

        //自动添加
//        if (activityPager.getIsAuto().isChecked()) {
//            checkMainDlg();
////            if (!checkBeforeAdd()) {
////                ReSetScan(cbScaning);
////            }
//        } else {
////            ReSetScan(cbScaning);
//        }
    }

    //若为该单据为第一次，弹出确认框
    private void checkMainDlg() {
        if (!activityPager.isHasLock()) {
            AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
            ab.setTitle(getString(R.string.checkMainDlg_title));
            ab.setMessage(getString(R.string.checkMainDlg_msg));
            ab.setPositiveButton(getString(R.string.checkMainDlg_ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    getBoxCodeAndBatch();
                }
            });
            ab.setNegativeButton(getString(R.string.cancle), null);
            ab.create().show();
        } else {
            getBoxCodeAndBatch();
        }
    }

    //操作明细数据
    private void changeDetail(final T_Detail detail) {
        AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
        ab.setTitle("操作长度："+detail.FCfLenght);
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_wort_show, null);
        final EditText editText = v.findViewById(R.id.ed_num);
        editText.setText(detail.FCfQty);
        ab.setView(v);
        ab.setPositiveButton("修改", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeDetailQty(detail,editText.getText().toString());
            }
        });
        ab.setNegativeButton("取消",null);
        ab.setNeutralButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                delCountOffTemp(detail);
            }
        });
        ab.create().show();
    }

    //显示报数和与报数相关的数据
    public void loadCountOff() {
//        adapterList.clear();
        adapter.clear();
        List<T_Detail> list = daoSession.getT_DetailDao().queryBuilder().where(
                T_DetailDao.Properties.FOrderId.eq(ordercode),
                T_DetailDao.Properties.FBoxCodeOrder.eq(boxcodeOrder)
        ).orderAsc(T_DetailDao.Properties.FCfLenght).build().list();
        adapter.addAll(list);
//        for (int i = 0; i < list.size(); i++) {
////            int number = list.get(i).FCountNumber;
////            if (i > 0 && list.get(i).FCountNumber == list.get(i - 1).FCountNumber) {
////            } else {
////            //筛选出报数非0的报数数据
////                List<T_Detail> tempList = daoSession.getT_DetailDao().queryBuilder().where(
////                        T_DetailDao.Properties.FCountNumber.notEq(0)
////                ).where(
////                        T_DetailDao.Properties.FOrderId.eq(ordercode),
////                        T_DetailDao.Properties.FCountNumber.eq(number)
////                ).orderAsc(T_DetailDao.Properties.FCountNumber).build().list();
////                if (tempList.size()>0){
////                    adapterList.add(new CountOffBeanList(number, tempList));
////                }
////            }
//            //报数号为0的对象为临时添加对象
////            if (number==0){
//            adapter.add(list.get(i));
////            }
//        }
        tvPcsSum.setText(adapter.getAllPcs());
        tvM2Sum.setText(adapter.getAllM2());
    }

    //修改数量
    public void changeDetailQty(T_Detail detail,String qty){
        if (MathUtil.toD(qty)>0){
            detail.FCfQty = qty;
            detail.FRealQty = qty;
            detail.FCfM2 = (MathUtil.toD(detail.FCfLenght+"")*MathUtil.toD(detail.FCfWide)*MathUtil.toD(qty)/1000000)+"";
            t_detailDao.updateInTx(detail);
        }
        loadCountOff();
    }

    //删除相应的报数的数据
    public void delCountOffTemp(T_Detail beanList) {
        daoSession.getT_DetailDao().deleteInTx(beanList);
        loadCountOff();

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
        if (activityPager.getDepartMent().equals("")) {
            Toast.showText(mContext, "生产车间不能为空");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        if (activityPager.getOrgOut(0).equals("")) {
            Toast.showText(mContext, "组织不能为空");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        if (null == lenghtList || lenghtList.size() <= 0) {
            Toast.showText(mContext, "物料过滤长度信息为空");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
//        if (edLenght.getText().toString().equals("")|| edThick.getText().toString().equals("")||edCeng.getText().toString().equals("")) {
//            Toast.showText(mContext, "厚度、长度、层数为必填项");
//            MediaPlayer.getInstance(mContext).error();
//            return false;
//        }
//        if (adapter.getAllData().size() <= 0) {
//            Toast.showText(mContext, "宽度为空，请输入宽度");
//            MediaPlayer.getInstance(mContext).error();
//            return false;
//        }

        if (activityPager.getOrgIn(0).equals("")) {
            Toast.showText(mContext, "货主不能为空");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        Lg.e("添加时单位：", unit);
        if (unit == null || unit.FMeasureUnitID.equals("")) {
            Toast.showText(mContext, "物料单位未带出，请重试...");
            unit = LocDataUtil.getUnit(product.FProduceUnitID);
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        List<T_Detail> list = t_detailDao.queryBuilder().where(
                T_DetailDao.Properties.FCfLenght.eq(cfLenght),
                T_DetailDao.Properties.FBoxCodeOrder.eq(boxcodeOrder)
        ).build().list();
        if (list.size()>0){
            Toast.showText(mContext, "已存在该长度信息，不能重复添加");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }

//        if (isOpenBatch && edPihao.getText().toString().trim().equals("")) {
//            Toast.showText(mContext, "请输入批号信息");
//            MediaPlayer.getInstance(mContext).error();
//            return false;
//        }
        //--------------------------------------------------
//        if (edNum.getText().toString().trim().equals("") || "0".equals(edNum.getText().toString())) {
//            Toast.showText(mContext, "请输入数量");
//            MediaPlayer.getInstance(mContext).error();
//            return false;
//        }
        //--------------------------------------------------
//        volume =MathUtil.getVoleum4ShuiBan(adapter.getWideAve(),edLenght.getText().toString(),edThick.getText().toString(),edCeng.getText().toString());
//        Lg.e("体积"+volume);
//        LoadingUtil.showDialog(mContext, "正在获取条码数据...");
//        String pdata = product.FMaterialid + "|" + unit.FMeasureUnitID + "|" + "1" + "|" + spActualmodel.getDataNumber() + "|" + spAuxsign.getDataNumber() + "|" + ""
//                + "|" + BasicShareUtil.getInstance(mContext).getIMIE() + "|" + activityPager.getStorage().FNumber + "|" + activityPager.getOrgIn(0)
//                + "|" + "" + "|" + edLenght.getText().toString() + "|" + "0"
//                + "|" + edLenght.getText().toString() + "|" + adapter.getWideAve() + "|" + edThick.getText().toString() + "|" + volume
//                + "|" + "" + "|" + "" + "|" + "1"+"|"+adapter.getWideString()+"|"+"0"+"|"+edCeng.getText().toString();
//        App.getRService().doIOAction(WebApi.PrintData4P2, pdata, new MySubscribe<CommonResponse>() {
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
//                    edBasenum.setText(baseNum);
//                    edStorenum.setText(storeNum);
//                    //把需要打印的数据保存到本地
//                    PrintHistory printHistory = new PrintHistory();
//                    printHistory.setProject("0");
//                    printHistory.setData(product, spUnitStore.getDataObject(), spUnitJiben.getDataObject(), storeNum,
//                            baseNum, spWavehouse.getWaveHouseId(), activityPager.getNote(),
//                            activityPager.getOrgIn().FNote, barcode, batch, CommonUtil.getTime(true), "", spAuxsign.getDataNumber(), spActualmodel.getDataNumber());
//                    printHistory.setFModel(edLenght.getText().toString()+"x"+adapter.getWideAve()+"x"+edThick.getText().toString());
//                    printHistory.setFBWide(adapter.getWideString());
//                    printHistory.setFCeng(edCeng.getText().toString());
//                    printHistory.setFVolume(volume);
//                    daoSession.getPrintHistoryDao().insert(printHistory);
//                    try {
//                        CommonUtil.doPrint4P2Shuiban(zpSDK, printHistory, activityPager.getPrintNum());
//                    } catch (Exception e) {
//                        Lg.e("打印错误",e.getMessage());
//                    }
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
    //先判断是否存在箱码和批号信息，再去添加
    private void getBoxCodeAndBatch(){
        if ("".equals(Hawk.get(Info.WortBoxCode+boxcodeOrder,""))){
            String json =Hawk.get(Info.user_id, "")+"|"+edLenght.getText().toString()+"|"+
                    cfWide+"|"+cfThick+"|"+activityPager.getStorage().FNumber+"|"+activityPager.getDepartMent()+"|"+edBatchRermark.getText().toString();
            App.getRService().doIOAction(WebApi.getBoxCodeAndBatch, json, new MySubscribe<CommonResponse>() {
                @Override
                public void onNext(CommonResponse commonResponse) {
                    super.onNext(commonResponse);
                    if (!commonResponse.state)return;
                    CommonBean dBean = JsonCreater.gson.fromJson(commonResponse.returnJson, CommonBean.class);
                    boxcode=dBean.FStandby1;
                    batch = dBean.FStandby2;
                    Hawk.put(Info.WortBoxCode+boxcodeOrder,boxcode);
                    Hawk.put(Info.WortBatch+boxcodeOrder,batch);
                    Addorder();
                }

                @Override
                public void onError(Throwable e) {
//                    boxcode="";
//                    batch = "";
                    Toast.showText(mContext,"获取箱码数据失败");
//                    super.onError(e);
                }
            });
        }else{
            Addorder();
        }
    }

    //添加数据
    private void Addorder() {
        try {

//            String num = edNum.getText().toString();
//            if ("".equals(num) || "0".equals(num)) return;//避免多次点击，以上请求多次，导致第一次清空之后，再去添加一个空的数据
//            if (true) {
//                Lg.e("合并");
//                List<T_Detail> detailhebing = t_detailDao.queryBuilder().where(
//                        T_DetailDao.Properties.Activity.eq(activity),
//                        T_DetailDao.Properties.FOrderId.eq(ordercode),
//                        T_DetailDao.Properties.FMaterialId.eq(product.FNumber),
//                        T_DetailDao.Properties.FUnitID.eq(unit.FNumber),
//                        T_DetailDao.Properties.FBarcode.eq(barcode),
//                        T_DetailDao.Properties.FStorageId.eq(storage.FNumber),
//                        T_DetailDao.Properties.FWaveHouseId.eq(spWavehouse.getwaveHouseNumber()),
//                        T_DetailDao.Properties.FBatch.eq(batch)
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
            main.FBoxCodeOrder = boxcodeOrder;
            main.FIndex = timesecond;
            main.FBoxCode = boxcode;
            main.FBatch = batch;
            main.FCfThick = cfThick;
            main.FCfLenghtAny = edLenght.getText().toString();
            main.FCfWide = cfWide;
            main.FDepartmentNumber = activityPager.getDepartMent();
            main.FStorageNumber = activityPager.getStorage().FNumber;
            main.setData(Info.getType(activity), activityPager.getOrgOut(0), activityPager.getOrgIn(0), activityPager.getOrgIn(0));
            main.FDepartmentNumber = activityPager.getDepartMent();
//            main.FPurchaseDeptId = spDepartmentBuy.getDataNumber();
//            main.FPurchaserId = spBuyer.getDataNumber();
            main.FStockerNumber = activityPager.getManStore();
            main.FDate = activityPager.getDate();
            main.FNot = activityPager.getNote();
            main.F_FFF_Text = activityPager.getFOrderNo();
            long insert1 = t_mainDao.insert(main);


            T_Detail detail = new T_Detail();//--------------------------------明细-----------------
            detail.activity = activity;
            detail.FAccountID = CommonUtil.getAccountID();
            detail.FBillerID = Hawk.get(Info.user_id, "");
            detail.FBarcode = barcode;
            detail.IMIE = BasicShareUtil.getInstance(mContext).getIMIE();
            detail.FOrderId = ordercode;
            detail.FBoxCodeOrder = boxcodeOrder;
            detail.FIndex = timesecond;
//            detail.FRemainInStockQty = num;
            detail.FRealQty = edNum.getText().toString();
            detail.FStoreNum = edStorenum.getText().toString();
            detail.FBaseNum = edNum.getText().toString();
            detail.FBatch = batch;
//            detail.FProductNo = edPurchaseNo.getText().toString();
            detail.FWorkShopId1 = activityPager.getDepartMent();
            detail.AuxSign = spAuxsign.getDataNumber();
            detail.ActualModel = spActualmodel.getDataNumber();
            detail.setProduct(product);
//            detail.model = edLenght.getText().toString()+"x"+adapter.getWideAve()+"x"+edThick.getText().toString();
            detail.setStorage(activityPager.getStorage());
            detail.setWaveHouse(waveHouse);
            detail.setUnit(unit);
            detail.setBaseUnit(spUnitJiben.getDataObject());
            detail.setStoreUnit(spUnitStore.getDataObject());
            detail.FLevel = "";
//            detail.FYmLenght = edLenght.getText().toString();
            detail.FYmDiameter = "0";
//            detail.FBLenght = edLenght.getText().toString();
//            detail.FBWide = adapter.getWideAve();
//            detail.FCLWide = adapter.getWideString();
//            detail.FBThick = edThick.getText().toString();
//            detail.FVolume = volume;

            //设置报数相关数据
            detail.FCfBoxCode = boxcode;
            detail.model = edLenght.getText().toString()+"x"+cfWide+"x"+cfThick;
            detail.setCfBaseData(product, activityPager.getStorage(), activityPager.getOrgOut(), activityPager.getOrgIn(), 0, cfLenght, cfWide, cfThick, edNum.getText().toString(),edLenght.getText().toString());
            long insert2 = t_detailDao.insert(detail);

            if (insert1 > 0 && insert2 > 0) {
                loadCountOff();
//                adapter.add(detail);
//                edLenght.setText("");
//                setfocus(edLenght);
//                InputMethodManager inputManager =
//                        (InputMethodManager) edWide.getContext().getSystemService(mContext.INPUT_METHOD_SERVICE);
//                inputManager.showSoftInput(edWide, 0);
                Lg.e("成功添加：", main);
                Lg.e("成功添加：", detail);
//                MediaPlayer.getInstance(mContext).ok();
//                Toast.showText(mContext, "添加成功");
                resetAll();

            } else {
                LoadingUtil.dismiss();
                MediaPlayer.getInstance(mContext).error();
                Toast.showText(mContext, "添加失败，请重试");
            }
        } catch (Exception e) {
            LoadingUtil.dismiss();
            DataService.pushError(mContext, this.getClass().getSimpleName(), e);
        }

    }

    private void resetAll() {
        LoadingUtil.dismiss();
        listOrder.clear();
//        edPihao.setText("");
//        edNum.setText("");
//        edPurchaseNo.setText("");
//        tvCode.setText("");
//        tvGoodName.setText("");
//        tvModel.setText("");
//        product = null;

        edBatchRermark.setText("");
        barcode = "";
        auxNum = "";
//        batch = "";
        baseNum = "";
        storeNum = "";
        edNum.setText("");
//        adapter.clear();
        //判断是否锁住表头
        //判断是否有保存的业务单号，没有的话，锁定表头
//        if ("".equals(Hawk.get(Config.OrderNo+activityPager.getActivity(),""))){
        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock));
//        adapter.notifyDataSetChanged();
        search.setEnabled(false);
        edLenght.setEnabled(false);
        edLenght.setFocusable(false);
        Hawk.put(Info.WortLenght + activity, edLenght.getText().toString());
//        }
    }


    @OnClick({R.id.tv_print, R.id.btn_add, R.id.btn_finishorder, R.id.btn_backorder, R.id.btn_checkorder, R.id.btn_add_wide})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_print:
                break;
            case R.id.btn_add:
//                addAllCountOff();
//                checkBeforeAdd();
                break;
            case R.id.btn_backorder:
//                CountOffListDialogFragment fragment = new CountOffListDialogFragment();
//                fragment.loadCountOff(daoSession, activity);
//                fragment.show(mContext.getSupportFragmentManager(), "dialog");
                new AlertDialog.Builder(mContext)
                        .setTitle("确认上传？")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (!checkHasPackingData()){
                                    LoadingUtil.showDialog(mContext, "正在上传...");
                                    UpLoadModel.action(mContext, activity);
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
                bundle.putInt("activity", activity);
                startNewActivity(activityPager, ReViewP24WortActivity.class,
                        R.anim.activity_fade_in, R.anim.activity_fade_out, false, bundle);
                break;
            case R.id.btn_add_wide:
                if (!edLenght.getText().toString().equals("") && !"".equals(edNum.getText().toString())) {
                    if (checkBeforeAdd()) {
                        checkMainDlg();
//                        CountOffBean bean = new CountOffBean(cfLenght,edNum.getText().toString(), ordercode, countOff);
//                        bean.setProduct(product);
//                        bean.setBaseData(activityPager.getStorage(),activityPager.getOrgOut(),activityPager.getOrgIn());
//                        adapter.add(bean);
//                        edWide.setText("");
//                        setfocus(edWide);
//                        InputMethodManager inputManager =
//                                (InputMethodManager) edWide.getContext().getSystemService(mContext.INPUT_METHOD_SERVICE);
//                        inputManager.showSoftInput(edWide, 0);
                    }
//                    else{
//                        Toast.showText(mContext,"请先确定物料信息区间");
//                    }
                } else {
                    Toast.showText(mContext, "请输入长度、数量");
                }

                break;
        }
    }


    public void packing(){
        PurchaseInStoreUploadBean pBean = new PurchaseInStoreUploadBean();
        PurchaseInStoreUploadBean.purchaseInStore puBean = pBean.new purchaseInStore();
        ArrayList<String> detailContainer = new ArrayList<>();
        ArrayList<PurchaseInStoreUploadBean.purchaseInStore> data = new ArrayList<>();
        List<T_main> mains = t_mainDao.queryBuilder().where(
                T_mainDao.Properties.Activity.eq(activity),
                T_mainDao.Properties.FBoxCodeOrder.eq(boxcodeOrder)
        ).build().list();
        for (int i = 0; i < mains.size(); i++) {
            if (i > 0 && mains.get(i).FOrderId == mains.get(i - 1).FOrderId) {
            } else {
                detailContainer = new ArrayList<>();
                puBean = pBean.new purchaseInStore();
                String main;
                String detail = "";
                T_main t_main = mains.get(i);
                main = t_main.FBillerID + "|" +
                        t_main.FCfLenghtAny + "|" +
                        t_main.FCfWide + "|" +
                        t_main.FCfThick + "|" +
                        t_main.FStorageNumber + "|" +
                        t_main.FDepartmentNumber + "|" +
                        t_main.FBoxCode + "|" +
                        t_main.FBatch + "|";
                puBean.main = main;
                List<T_Detail> details = t_detailDao.queryBuilder().where(
                        T_DetailDao.Properties.FOrderId.eq(t_main.FOrderId),
                        T_DetailDao.Properties.FCfBoxCode.eq(t_main.FBoxCode),
                        T_DetailDao.Properties.Activity.eq(activity)
                ).orderAsc(T_DetailDao.Properties.FCfLenght).build().list();
                for (int j = 0; j < details.size(); j++) {
                    if (j != 0 && j % 49 == 0) {
                        Log.e("j%49", j % 49 + "");
                        T_Detail t_detail = details.get(j);
                        detail = detail +
                                t_detail.FItemID + "|" +
                                t_detail.FCfUnitID + "|" +
                                t_detail.FCfQty + "|" +
                                t_detail.FCfStorageID + "|" +
                                t_detail.FCfWaveHouseID + "|" +
                                t_detail.FCfStoreOrgID + "|" +
                                t_detail.FCfHuozhuID + "|" +
                                t_detail.FCfLenght + "|" +
                                t_detail.FCfM2 + "|";
                        detail = detail.subSequence(0, detail.length() - 1).toString();
                        detailContainer.add(detail);
                        detail = "";
                    } else {
                        Log.e("j", j + "");
                        Log.e("details.size()", details.size() + "");
                        T_Detail t_detail = details.get(j);
                        detail = detail +
                                t_detail.FItemID + "|" +
                                t_detail.FCfUnitID + "|" +
                                t_detail.FCfQty + "|" +
                                t_detail.FCfStorageID + "|" +
                                t_detail.FCfWaveHouseID + "|" +
                                t_detail.FCfStoreOrgID + "|" +
                                t_detail.FCfHuozhuID + "|" +
                                t_detail.FCfLenght + "|" +
                                t_detail.FCfM2 + "|";
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
        App.getRService().doIOAction(WebApi.doPackingUpload, gson.toJson(pBean), new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);
                if (!commonResponse.state)return;
                DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                if (null != dBean && dBean.wortPrintDatas.size()>0) {
                    Lg.e("装箱成功",dBean);
                    LoadingUtil.showAlter(mContext,"装箱成功");
                    pickingAfter(dBean.wortPrintDatas);
                }else{
                    LoadingUtil.showAlter(mContext,"装箱返回信息为空");
                    Lg.e("装箱失败");
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                LoadingUtil.showAlter(mContext,"装箱失败",e.getMessage());

            }
        });
    }
    //装箱后，更改为已装箱，并且打印
    private void pickingAfter(List<WortPrintData> wortPrintData){
        //更新为已装箱
        List<T_Detail> list = t_detailDao.queryBuilder().where(
                T_DetailDao.Properties.Activity.eq(activity),
                T_DetailDao.Properties.FBoxCodeOrder.eq(boxcodeOrder)
        ).build().list();
        for (T_Detail bean : list) {
            bean.FIsInBox=1;
            t_detailDao.updateInTx(bean);
        }
        adapter.clear();
        //清空并递增装箱识别号
        Hawk.put(Info.WortBoxCode+boxcodeOrder,"");
        Hawk.put(Info.WortBatch+boxcodeOrder,"");
        boxcode="";
        batch="";
        tvGoodName.setText("");
        product=null;
        Hawk.put(Info.WortProductNumber, "");
        boxcodeOrder++;
        Log.e("boxcodeOrder", boxcodeOrder + "");
        share.setOrderCode(Info.WortBoxCode, boxcodeOrder);
        //释放物料选择和长度
        search.setEnabled(true);
        edLenght.setEnabled(true);
        edLenght.setFocusable(true);
        edLenght.setFocusableInTouchMode(true);
        edLenght.setText("");
        try {
            CommonUtil.doPrint4P2Wort(mContext,wortPrintData,activityPager.getPrintNum());
        } catch (Exception e) {
//                    e.printStackTrace();
            App.getInstance().connectPrint();
            LoadingUtil.showAlter(mContext,getString(R.string.error_print),getString(R.string.check_print));
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




    //-------------------------------------------------



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
                share.setOrderCode(activity, ordercode);
            }
        });
        ab.setNegativeButton("取消", null);
        ab.create().show();

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
