package com.fangzuo.assist.cloud.Fragment.TabForActivity.PushDownFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseFragment;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Activity.PagerForActivity;
import com.fangzuo.assist.cloud.Activity.PushDownPagerActivity;
import com.fangzuo.assist.cloud.Activity.ReViewPD31Activity;
import com.fangzuo.assist.cloud.Activity.ReViewPDActivity;
import com.fangzuo.assist.cloud.Adapter.PushDownSubListAdapter;
import com.fangzuo.assist.cloud.Beans.BackData;
import com.fangzuo.assist.cloud.Beans.CodeCheckBackDataBean;
import com.fangzuo.assist.cloud.Beans.CodeCheckBean;
import com.fangzuo.assist.cloud.Beans.CommonBean;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
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
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


//选择单据信息Fragment（所属：PushDownPagerActivity);
public class FragmentSaleOutDetailForPDBox extends BaseFragment {
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
    @BindView(R.id.tv_storenum)
    TextView tvStorenum;
    @BindView(R.id.sp_unit)
    SpinnerUnit spUnit;
    @BindView(R.id.sp_which_storage)
    SpinnerStorage spWhichStorage;
    @BindView(R.id.sp_wavehouse)
    MyWaveHouseSpinner spWavehouse;
    @BindView(R.id.ed_num)
    EditText edNum;
    @BindView(R.id.ed_pihao)
    EditText edPihao;
    @BindView(R.id.sp_auxsign)
    SpinnerAuxSign spAuxsign;
    @BindView(R.id.sp_actualmodel)
    SpinnerActualModel spActualmodel;
    @BindView(R.id.ed_purchase_no)
    EditText edPurchaseNo;
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
    private CodeCheckBackDataBean codeCheckBackDataBean;
    protected boolean isOpenBatch = false;
    private List<String> listOrder;
    private List<CodeCheckBackDataBean> codeCheckBackDataBeanList;
    private Gson gson;
    private DaoSession daoSession;
    private T_mainDao t_mainDao;
    private T_DetailDao t_detailDao;
    private ShareUtil share;
    private int activity;
    private String barcode = "";
    private String auxNum = "";
    private String batch = "";
    private String autoAuxSing = "";
    private String autoActualModel = "";
    private String scanOfHuozhuNumber = "";
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
    private String mainSaleDept = "";//表头带出
    private String mainSaleMan = "";//表头带出
    private String mainSaleOrg = "";//表头带出

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
                mCaptureManager.onPause();
                zxingBarcodeScanner.setVisibility(View.GONE);
                OnReceive(res.getResult().getText());
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
                        App.getRService().doIOAction(WebApi.SaleOutBoxUpload, reString, new MySubscribe<CommonResponse>() {
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
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock+"NO"));//上传成功，解锁表头
                    Toast.showText(mContext, "上传成功");
                    DataModel.submitOnly(mContext,Config.PdSaleOrder2SaleOutActivity,listOrder,tag);
//                    DataModel.submitAndAudit(mContext,Config.PdSaleOrder2SaleOutActivity,listOrder,tag);
//                btnBackorder.setClickable(true);
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
                            DataService.pushBackJson(mContext, FragmentSaleOutDetailForPDBox.this.getClass().getSimpleName(), Hawk.get(Config.Company,""));
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
            case EventBusInfoCode.Code_Check://条码检测
                LoadingUtil.dismiss();
                codeCheckBackDataBean = (CodeCheckBackDataBean) event.postEvent;
                Lg.e("条码检测返回：",codeCheckBackDataBean);
                if (codeCheckBackDataBean.FTip.equals("OK")) {
                    edPihao.setText(codeCheckBackDataBean.FBatchNo);
                    edPurchaseNo.setText(codeCheckBackDataBean.FPurchaseNo);
                    edNum.setText(codeCheckBackDataBean.FQty);
                    autoActualModel = codeCheckBackDataBean.FActualmodel;
                    autoAuxSing = codeCheckBackDataBean.FAuxsign;
                    autoStorage = codeCheckBackDataBean.FStockID;
                    scanOfHuozhuNumber = codeCheckBackDataBean.FHuoZhuNumber;
                    LoadingUtil.showDialog(mContext, "正在查找物料信息");
                    DataModel.getProductForNumber(codeCheckBackDataBean.FItemID, activityPager.getOrgOut());
                } else {
//                    activityPager.ReSetScan(cbScaning);
                    Toast.showText(mContext, codeCheckBackDataBean.FTip);
                }
                break;
            case EventBusInfoCode.Code_Check_Box://条码检测
                LoadingUtil.dismiss();
                codeCheckBackDataBeanList = (List<CodeCheckBackDataBean>) event.postEvent;
                Lg.e("箱码条码检测返回：",codeCheckBackDataBeanList);
                if (codeCheckBackDataBeanList.get(0).FTip.equals("OK")) {
                    if (!activityPager.getOrgOut().FNumber.equals(codeCheckBackDataBeanList.get(0).FStoreOrgNumber)){
                        LoadingUtil.showAlter(mContext,"提示","箱码的库存组织("+LocDataUtil.getOrg(codeCheckBackDataBeanList.get(0).FStoreOrgNumber,"number").FName+")与所选表头不一致,请重新选择");
//                        Toast.showText(mContext,"条码的货主("+LocDataUtil.getOrg(wortPrintDataList.get(0).FOWNERID,"id").FName+")与所选表头不一致,请重新选择");
                        return;
                    }

                    edPihao.setText(codeCheckBackDataBeanList.get(0).FBatchNo);
//                    edPurchaseNo.setText(codeCheckBackDataBean.FPurchaseNo);
                    edNum.setText(codeCheckBackDataBeanList.get(0).FQty);
                    autoActualModel = codeCheckBackDataBeanList.get(0).FActualmodel;
                    autoAuxSing = codeCheckBackDataBeanList.get(0).FAuxsign;
                    autoStorage = codeCheckBackDataBeanList.get(0).FStorageNumber;
                    scanOfHuozhuNumber = codeCheckBackDataBeanList.get(0).FHuoZhuNumber;
                    spWhichStorage.setAuto("",autoStorage, activityPager.getOrgOut());
                    getAutoSelection4Box(codeCheckBackDataBeanList);//得出列表存在的角标

//                    LoadingUtil.showDialog(mContext, "正在查找物料信息");
//                    DataModel.getProductForNumber(codeCheckBackDataBean.FItemID, activityPager.getOrgOut());
                } else {
                    StringBuilder builder = new StringBuilder();
                    for (CodeCheckBackDataBean codeCheckBackDataBean : codeCheckBackDataBeanList) {
                        builder.append(codeCheckBackDataBean.FTip + "\n");
                    }
                    AlertDialog.Builder delete = new AlertDialog.Builder(activityPager);
                    delete.setTitle("箱码查询错误");
                    delete.setMessage(builder.toString());
                    delete.setPositiveButton("确定", null);
                    delete.create().show();
//                    activityPager.ReSetScan(cbScaning);
//                    Toast.showText(mContext, codeCheckBackDataBeanList.get(0).FTip);
                }
                break;
            case EventBusInfoCode.Code_Only_Insert://写入条码唯一临时表
                codeCheckBackDataBean = (CodeCheckBackDataBean) event.postEvent;
                if (codeCheckBackDataBean.FTip.equals("OK")) {
                    if (barcode.startsWith("ZZ")){
                        doAutoInput();
                    }else{
                        Addorder();
                    }
                } else {
                    Toast.showText(mContext, codeCheckBackDataBean.FTip);

                }
                break;

            case EventBusInfoCode.UpdataView://由表头的数据决定是否更新明细数据
                if (null != activityPager) {
                    spUnit.setAuto("", SpinnerUnit.Id);
                    spWhichStorage.setAuto("","", activityPager.getOrgOut());
                }
                break;

        }
    }

    public FragmentSaleOutDetailForPDBox() {
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
        View view = inflater.inflate(R.layout.fragment_saleoutdetail_forpdbox, container, false);
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
    }

    @Override
    protected void initData() {
        s2Product = new SearchBean.S2Product();
        listOrder = new ArrayList<>();
        spAuxsign.setEnabled(false);
        spActualmodel.setEnabled(false);

        container = new ArrayList<>();
        fidcontainer = activityPager.getIntent().getExtras().getStringArrayList("fid");
//        ordercode = CommonUtil.createOrderCode(activityPager.getActivity()+fidcontainer.get(0));//单据编号
        getList();
        List<PushDownMain> pushDownMains = pushDownMainDao.queryBuilder().where(
                PushDownMainDao.Properties.FBillNo.eq(fidcontainer.get(0))).build().list();
        if (pushDownMains.size() > 0) {
            Lg.e("表头：", pushDownMains.get(0));
            pushDownMain = pushDownMains.get(0);
            mainSaleDept = LocDataUtil.getDept(pushDownMain.FSaleDeptID).FNumber;
            mainSaleMan = LocDataUtil.getSaleMan(pushDownMain.FSaleManID).FNumber;
            mainSaleOrg = LocDataUtil.getOrg(pushDownMain.FSaleOrgID,"id").FNumber;
            EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Main_Note, pushDownMains.get(0).FNot));
//            fwanglaiUnit = list1.get(0).FSupplyID;
//            employeeId = list1.get(0).FEmpID;
//            departmentId = list1.get(0).FDeptID;
//            Log.e("employeeId", employeeId == null ? "" : employeeId);
//            Log.e("departmentId", departmentId == null ? "" : departmentId);
//            billNo = list1.get(0).FBillNo;
            if ("".equals(pushDownMain.FSupplyID==null?"":pushDownMain.FSupplyID)){
                LoadingUtil.showAlter(mContext,"注意","表头明细的客户数据带出失败，请重试...",false);
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
        barcode = code;
        LoadingUtil.showDialog(mContext, "正在检测条码...");
        if (code.startsWith("ZZ")){
            //查询条码唯一表
            CodeCheckBean bean = new CodeCheckBean();
            bean.FBillNo = code;bean.FTypeID="1";bean.FID = pushDownMain.FID;
            DataModel.codeCheck4Box(WebApi.CodeCheckForOut4Box, gson.toJson(bean));
        }else{
            //查询条码唯一表
            CodeCheckBean bean = new CodeCheckBean(code);
            DataModel.codeCheck(WebApi.CodeCheckForOut, gson.toJson(bean));
        }
    }


    @Override
    protected void initListener() {
        spWhichStorage.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                storage = (Storage) spWhichStorage.getAdapter().getItem(i);
                spWhichStorage.setTitleText(storage.FName);
                Lg.e("选中仓库：", storage);
                waveHouse = null;
                spWavehouse.setAuto(mContext, storage, "");
//                if (activityPager.getActivity()==Config.PdSaleOrder2SaleOutActivity){//非VMI销售订单下推时，货主类型为业务组织，VMI时，为供应商，则查询库存方式不同，查的是供应商表
                    DataModel.getStoreNum(product, storage, edPihao.getText().toString().trim(), mContext, tvStorenum,activityPager.getOrgOut(),LocDataUtil.getOrg(scanOfHuozhuNumber,"number"));
//                }else{
//                    DataModel.getStoreNum4SaleOrder2SaleOut(product, storage, edPihao.getText().toString().trim(), mContext, tvStorenum,pushDownMain.FBillTypeName,activityPager.getOrgOut(),scanOfHuozhuNumber);
//                }

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
            }
        });

        lvPushsub.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                Lg.e("被点击");//可拦截列表点击事件
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Lg.e("点击");
                        y1 = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        Lg.e("点击up");
                        y2 = event.getY();
                        Lg.e("得到坐标y1",y1);
                        Lg.e("得到坐标y2",y2);
                        Lg.e("得到坐标y22",y1-y2);
                        if ( (y1-y2)>150){
//                            int first = lvPushsub.getFirstVisiblePosition();
                            int toselec;
                            if (pos>=pushDownSubListAdapter.getCount()){
                                toselec = pos-1;
                            }else{
                                pos++;
                                toselec = pos;
                            }
                            Lg.e("相比负数："+pos);
                            Lg.e("相比负数："+toselec);
                            lvPushsub.setSelection(toselec);
                        }else if ((y1-y2)<0){
//                            int first = lvPushsub.getFirstVisiblePosition();
                            int toselec;
                            if (pos>0){
                                pos--;
                                toselec = pos;
                            }else{
                                toselec = pos;
                            }
                            Lg.e("相比正数："+pos);
                            Lg.e("相比正数："+toselec);
                            lvPushsub.setSelection(toselec);
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
//                        if (event.getPointerCount() >= 2){
//                            float offsetX = event.getX(0) - event.getX(1);//监听手机的PointerCount数量大于2时，获得两个点的坐标
//                            float offsetY = event.getY(0) - event.getY(1);
//                            Lg.e(">15");
//                        }else{
//                            Lg.e("<2");
//
//                        }
//                        Lg.e("点击move");
                        break;
                }
                return true;
            }
        });

        lvPushsub.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pushDownSub = (PushDownSub) pushDownSubListAdapter.getItem(i);
                Lg.e("点击明细:", pushDownSub);
                if (BasicShareUtil.getInstance(mContext).getIsOL()) {
                    s2Product.likeOr = pushDownSub.FNumber;
                    s2Product.FOrg = activityPager.getOrgOut() == null ? "" : activityPager.getOrgOut().FOrgID;
                    App.getRService().doIOAction(WebApi.S2Product, gson.toJson(new SearchBean(SearchBean.product_for_number, gson.toJson(s2Product))), new MySubscribe<CommonResponse>() {
                        @Override
                        public void onNext(CommonResponse commonResponse) {
                            super.onNext(commonResponse);
                            if (!commonResponse.state) return;
                            final DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                            if (dBean.products.size() > 0) {
                                Lg.e("product.size"+dBean.products.size(), dBean.products);
                                product = dBean.products.get(0);
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

            }
        });
    }
    private float y1;
    private float y2;
    private int pos=0;

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
    //箱码返回数据时
    List<CommonBean> listSelection;//自动添加的列表数据
    private int autoSize=0;//得到自动添加的角标数据的数量
    private void getAutoSelection4Box(List<CodeCheckBackDataBean> list) {
        listSelection = new ArrayList<>();
        autoSize=0;
        if (list != null) {
            for (int j = 0; j < pushDownSubListAdapter.getCount(); j++) {
                PushDownSub pushDownSub1 = (PushDownSub) pushDownSubListAdapter.getItem(j);
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).FNumber.equals(pushDownSub1.FNumber)){
                        //存入与箱码相对应的列表数据：角标和数量
                        listSelection.add(new CommonBean(j+"",list.get(i).FQty,true));
                        autoSize++;
                    }
                }
            }
        } else {
            Toast.showText(mContext, "箱码查询的信息为空");
        }
        checkBeforeAdd();
    }
    //根据自动添加的列表数据，自动点击列表添加数据
    private CommonBean commonBean;
    private void doAutoInput(){
        Lg.e("doAutoInput",listSelection);
        LoadingUtil.showDialog(mContext,"正在添加箱码数据");
        if (listSelection.size()>0){
            //        while (listSelection.size()>0){
            commonBean = listSelection.get(0);
            edNum.setText(commonBean.FStandby2);
            int i= Integer.parseInt(commonBean.FStandby1);
            lvPushsub.setSelection(i);
            lvPushsub.performItemClick(lvPushsub.getChildAt(i), i, lvPushsub.getItemIdAtPosition(i));
//        }
        }else{
            edPihao.setText("");
            barcode="";
            LoadingUtil.dismiss();
            Toast.showText(mContext,"箱码数据添加完成");
        }

    }

    //条码唯一时
    private void setProduct(Product product) {
        if (product != null) {
            boolean flag = true;
            for (int j = 0; j < pushDownSubListAdapter.getCount(); j++) {
                PushDownSub pushDownSub1 = (PushDownSub) pushDownSubListAdapter.getItem(j);
                if (product.FNumber.equals(pushDownSub1.FNumber)) {
//                    if (MathUtil.toD(pushDownSub1.FQty) == MathUtil.toD(pushDownSub1.FQtying)) {
//                        flag = true;
//                        continue;
//                    } else {

                    /*判断条码带出在辅助标识是否为空，不为空就判断，接着判断单位；否则直接判断单位*/
                    if (null!=autoAuxSing && !"".equals(autoAuxSing)){
                        if (autoAuxSing.equals(pushDownSub1.AuxSign)){
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
                        }else if (null==pushDownSub1.AuxSign || "".equals(pushDownSub1.AuxSign)){
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
                    }else{
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

//                    }

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
        spUnit.setAuto(product.FPurchaseUnitID, SpinnerUnit.Id);
//        if (activityPager.isStorage()) {
//            spWhichStorage.setAutoSelection("", product.FStockID);
        spWhichStorage.setAuto("",autoStorage, activityPager.getOrgOut());
//        }
        if (CommonUtil.isOpen(product.FIsBatchManage)) {
            isOpenBatch = true;
            edPihao.setEnabled(true);
        } else {
            edPihao.setEnabled(false);
            edPihao.setText("");
            isOpenBatch = false;
        }
//        if (activityPager.getActivity()==Config.PdSaleOrder2SaleOutActivity){//非VMI销售订单下推时，货主类型为业务组织，VMI时，为供应商，则查询库存方式不同，查的是供应商表
            DataModel.getStoreNum(product, storage, edPihao.getText().toString().trim(), mContext, tvStorenum,activityPager.getOrgOut(),LocDataUtil.getOrg(scanOfHuozhuNumber,"number"));
//        }else{
//            DataModel.getStoreNum4SaleOrder2SaleOut(product, storage, edPihao.getText().toString().trim(), mContext, tvStorenum,pushDownMain.FBillTypeName,activityPager.getOrgOut(),scanOfHuozhuNumber);
//        }

        spAuxsign.getData(product.FMASTERID, autoAuxSing);
        spActualmodel.getData(product.FMASTERID, autoActualModel);

//        //自动添加
//        if (activityPager.getIsAuto().isChecked()) {
//            if (!checkBeforeAdd()) {
////                activityPager.ReSetScan(cbScaning);
//            }
//        } else {
////            activityPager.ReSetScan(cbScaning);
//        }
        if (barcode.startsWith("ZZ")){
            Addorder();
        }
    }

    //添加前检测
    private boolean checkBeforeAdd() {
        if (!barcode.startsWith("ZZ") &&product == null) {
            Toast.showText(mContext, "请选择物料");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        if (storage == null) {
            Toast.showText(mContext, "请选择仓库");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        if (activityPager.getOrgOut(0).equals("")) {
            Toast.showText(mContext, "发货组织不能为空");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        if (edPihao.getText().toString().equals("")) {
            Toast.showText(mContext, "批号不能为空");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        if (!barcode.startsWith("ZZ") &&pushDownSub == null) {
            Toast.showText(mContext, "请重新扫码");
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
        if (!barcode.startsWith("ZZ") && MathUtil.toD(edNum.getText().toString())>MathUtil.toD(tvStorenum.getText().toString())){
            MediaPlayer.getInstance(mContext).error();
            Toast.showText(mContext, "添加数不能超过库存数");
            return false;
        }
        if (!barcode.startsWith("ZZ") && edNum.getText().toString().trim().equals("") || "0".equals(edNum.getText().toString())) {
            Toast.showText(mContext, "请输入数量");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }//--------------------------------------------------
        ordercode = CommonUtil.createOrderCode(activityPager.getActivity()+pushDownMain.FID+scanOfHuozhuNumber);//单据编号

        if (!barcode.startsWith("ZZ")){
            //插入条码唯一临时表
            CodeCheckBean bean = new CodeCheckBean(barcode, ordercode + "", edNum.getText().toString(), BasicShareUtil.getInstance(mContext).getIMIE());
            DataModel.codeOnlyInsert(WebApi.CodeCheckInsertForOut, gson.toJson(bean));
        }else{
            CodeCheckBean bean = new CodeCheckBean();
            bean.FOrderID = ordercode+"";
            bean.FPDAID = BasicShareUtil.getInstance(mContext).getIMIE();
            bean.FBarCode = barcode;
            DataModel.codeOnlyInsert(WebApi.CodeCheckInsertForOutBox, gson.toJson(bean));
        }
//        Addorder();
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
            main.FID = pushDownSub.FID;
            main.FIndex = timesecond;
            main.FBillNo = pushDownMain.FBillNo;
            main.setData(CommonUtil.getSaleOutBillType(pushDownMain.FBillTypeName), mainSaleOrg, activityPager.getOrgOut(0), scanOfHuozhuNumber);
            main.FDepartmentNumber = activityPager.getDepartMent();
//            main.FPurchaseDeptId = activityPager.getDepartMentBuy();
//            main.FPurchaserId = activityPager.getManSale();
            main.FPurchaseDeptId = mainSaleDept;
            main.FPurchaserId = mainSaleMan;
            main.FStockerNumber = activityPager.getManStore();
            main.FDate = activityPager.getDate();
            main.FNot = activityPager.getNote();
            main.FCustomerID = pushDownMain.FSupplyID;
            main.F_FFF_Text = activityPager.getFOrderNo();
            main.FFieldMan = pushDownMain.FFieldMan;
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
            detail.FHuoZhuNumber = scanOfHuozhuNumber;
            detail.FSOEntryId = pushDownSub.FEntryID;
            detail.FRemainInStockQty = pushDownSub.FQty;
            detail.FTaxPrice = pushDownSub.FTaxPrice;
            detail.FRealQty = num;
            detail.FIsGift = pushDownSub.FIsGift;
            detail.FIsFree = "1".equals(pushDownSub.FIsGift);
            detail.FProductNo = edPurchaseNo.getText().toString();
            detail.FBatch = edPihao.getText().toString();
            detail.AuxSign = spAuxsign.getDataNumber();
            detail.ActualModel = spActualmodel.getDataNumber();
            detail.setProduct(product);
            detail.setStorage(storage);
            detail.setWaveHouse(waveHouse);
            detail.setUnit(unit);
            long insert2 = t_detailDao.insert(detail);

            if (insert1 > 0 && insert2 > 0) {
                pushDownSub.FQtying = DoubleUtil.sum(MathUtil.toD(pushDownSub.FQtying),
                        (MathUtil.toD(edNum.getText().toString()))) + "";
                pushDownSubDao.update(pushDownSub);
                pushDownSubListAdapter.notifyDataSetChanged();
                Lg.e("成功添加表头：" ,main);
                Lg.e("成功添加明细：" ,detail);
                if (barcode.startsWith("ZZ")){
                    if (listSelection.size()>0){
                        product = null;
                        pushDownSub = null;
                        edNum.setText("");
                        listSelection.remove(commonBean);
                        doAutoInput();
                        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock));
                    }else{
                        product = null;
                        pushDownSub = null;
                        barcode = "";
                        edPihao.setText("");
                        edNum.setText("");
                        MediaPlayer.getInstance(mContext).ok();
                        Toast.showText(mContext, "箱码数据添加成功");
                    }
                }else{
                    MediaPlayer.getInstance(mContext).ok();
                    Toast.showText(mContext, "添加成功");
                    resetAll();
                }

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
        edPurchaseNo.setText("");
        listOrder.clear();
        barcode = "";
        edPihao.setText("");
        edNum.setText("");
        product = null;
        pushDownSub = null;
        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock));
    }

    @OnClick({R.id.search, R.id.btn_add, R.id.btn_backorder, R.id.btn_checkorder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search:
                if (zxingBarcodeScanner.getVisibility() == View.VISIBLE) {
                    zxingBarcodeScanner.setVisibility(View.GONE);
//                    mCaptureManager.onPause();
                } else {
                    mCaptureManager.onResume();
                    zxingBarcodeScanner.setVisibility(View.VISIBLE);
                    mCaptureManager.decode();
                }
                break;
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
                                UpLoadModel.actionPushDown(mContext, activity,pushDownMain.FID);
                            }
                        })
                        .create().show();
                break;
            case R.id.btn_checkorder:
                Bundle bundle = new Bundle();
                bundle.putString("fid", pushDownMain==null?"":pushDownMain.FID);
                bundle.putInt("activity", activity);
                startNewActivity(activityPager, ReViewPD31Activity.class,
                        R.anim.activity_fade_in, R.anim.activity_fade_out, false, bundle);
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        getList();
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
