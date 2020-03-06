package com.fangzuo.assist.cloud.Fragment.TabForActivity.PushDownFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
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
import com.fangzuo.assist.cloud.Activity.ReViewPDActivity;
import com.fangzuo.assist.cloud.Activity.SearchDataActivity;
import com.fangzuo.assist.cloud.Adapter.PushDownSubList42Adapter;
import com.fangzuo.assist.cloud.Adapter.PushDownSubListAdapter;
import com.fangzuo.assist.cloud.Beans.BackData;
import com.fangzuo.assist.cloud.Beans.CodeCheckBackDataBean;
import com.fangzuo.assist.cloud.Beans.CodeCheckBean;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.InStoreNumBean;
import com.fangzuo.assist.cloud.Beans.SearchBean;
import com.fangzuo.assist.cloud.Dao.InStorageNum;
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
import com.fangzuo.assist.cloud.Utils.GreedDaoUtil.GreenDaoManager;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


//选择单据信息Fragment（所属：PushDownPagerActivity);
public class FragmentSaleOutDetailForPD extends BaseFragment {
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
//    @BindView(R.id.sp_wavehouse)
//    MyWaveHouseSpinner spWavehouse;
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
    @BindView(R.id.ed_wavehouse)
    EditText edWavehouse;
    @BindView(R.id.tv_createdate)
    TextView tvCreateDate;
    private boolean IsOpenCreateManager;
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
    private String autoAuxSing = "";
    private String autoActualModel = "";
    private String scanOfHuozhuNumber = "";
    private String autoStorage = "";
//    private ScanManager mCaptureManager;
    private PushDownSub pushDownSub;
    private List<PushDownSub> container;
    private PushDownMainDao pushDownMainDao;
    private PushDownSubDao pushDownSubDao;
    private ArrayList<String> fidcontainer;
    private PushDownSubList42Adapter pushDownSubListAdapter;
    private String default_unitID;
    protected PushDownMain pushDownMain;
//    private SearchBean.S2Product s2Product;//用于数据查找...
    private String mainSaleDept = "";//表头带出
    private String mainSaleMan = "";//表头带出
    private String mainSaleOrg = "";//表头带出

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.WaveHouse://
                waveHouse = (WaveHouse) event.postEvent;
                Lg.e("wavehouseName", waveHouse);
                getStoreNum(product, storage,waveHouse, edPihao.getText().toString().trim(), mContext, tvStorenum,activityPager.getOrgOut(),tvCreateDate.getText().toString());
                edWavehouse.setText(waveHouse.FName);
                break;
            case EventBusInfoCode.Close_Activity:
                Bundle b = new Bundle();
                b.putInt("123", tag);
                startNewActivity(activityPager, PushDownPagerActivity.class, 0, 0, true, b);
                break;
            case EventBusInfoCode.ScanResult:
                BarcodeResult res = (BarcodeResult) event.postEvent;
//                if (cbScaning.isChecked()) {
//                } else {
//                mCaptureManager.onPause();
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
                        t_detailDao.deleteInTx(t_detailDao.queryBuilder().where(
                                T_DetailDao.Properties.Activity.eq(activity),
                                T_DetailDao.Properties.FAccountID.eq(CommonUtil.getAccountID()),
                                T_DetailDao.Properties.FOrderId.eq(mains.get(i).FOrderId)
                        ).build().list());
                        for (int j = 0; j < mains.size(); j++) {
                            List<PushDownSub> pushDownSubs = pushDownSubDao.queryBuilder().where(
                                    PushDownSubDao.Properties.FBillNo.eq(mains.get(j).FBillNo),
                                    PushDownSubDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
                            ).build().list();
                            pushDownSubDao.deleteInTx(pushDownSubs);
                            List<PushDownMain> pushDownMains = pushDownMainDao.queryBuilder().where(
                                    PushDownMainDao.Properties.FBillNo.eq(mains.get(j).FBillNo),
                                    PushDownMainDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
                            ).build().list();
                            pushDownMainDao.deleteInTx(pushDownMains);
                        }

//                        final int pos = i;
//                        String reString = mains.get(i).FBillerID + "|" + listOrder.get(i) + "|" + mains.get(i).FOrderId + "|" + mains.get(i).IMIE;
//                        App.getRService().doIOAction(WebApi.SaleOutUpload, reString, new MySubscribe<CommonResponse>() {
//                            @Override
//                            public void onNext(CommonResponse commonResponse) {
//                                super.onNext(commonResponse);
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                super.onError(e);
//                            }
//                        });
                    }
                    t_mainDao.deleteInTx(mains);
                    LoadingUtil.dismiss();
//                    ordercode++;
//                    Log.e("ordercode", ordercode + "");
//                    share.setOrderCode(activityPager.getActivity()+fidcontainer.get(0), ordercode);
                    MediaPlayer.getInstance(mContext).ok();
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock+"NO"));//上传成功，解锁表头
                    Toast.showText(mContext, "上传成功");
                    DataModel.submitAndAudit(mContext,Config.PdSaleOrder2SaleOutActivity,listOrder,tag);
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
                            DataService.pushBackJson(mContext, FragmentSaleOutDetailForPD.this.getClass().getSimpleName(), Hawk.get(Config.Company,""));
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
            case EventBusInfoCode.Code_Only_Insert://写入条码唯一临时表
                codeCheckBackDataBean = (CodeCheckBackDataBean) event.postEvent;
                if (codeCheckBackDataBean.FTip.equals("OK")) {
                    Addorder();
                } else {
                    Toast.showText(mContext, codeCheckBackDataBean.FTip);
                }
                break;
            case EventBusInfoCode.UpdataDate://由表头的数据决定是否更新明细数据
                getStoreNum(product, storage,waveHouse, edPihao.getText().toString().trim(), mContext, tvStorenum,activityPager.getOrgOut(),tvCreateDate.getText().toString());
                break;
            case EventBusInfoCode.UpdataView://由表头的数据决定是否更新明细数据
                if (null != activityPager) {
                Lg.e("更新view",Hawk.get(Info.Storage+activityPager.getActivityMain(),""));
                    spUnit.setAuto("", SpinnerUnit.Id);
                    spWhichStorage.setAuto("", Hawk.get(Info.Storage+activityPager.getActivityMain(),""),activityPager.getOrgOut());
                }
                break;

        }
    }

    public FragmentSaleOutDetailForPD() {
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
        View view = inflater.inflate(R.layout.fragment_saleoutdetail_forpd, container, false);
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
    //获取列表数据
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
            pushDownSubListAdapter = new PushDownSubList42Adapter(mContext, container);
            lvPushsub.setAdapter(pushDownSubListAdapter);
            pushDownSubListAdapter.notifyDataSetChanged();
        } else {
            Toast.showText(mContext, getString(R.string.find_nothing));
        }
    }

    @Override
    protected void OnReceive(String code) {
//        barcode = code;
//        LoadingUtil.showDialog(mContext, "正在检测条码...");
//        //查询条码唯一表
//        CodeCheckBean bean = new CodeCheckBean(code);
//        DataModel.codeCheck(WebApi.CodeCheckForOut, gson.toJson(bean));
        barcode =code;
        List<String> list = CommonUtil.ScanBack(code);
        if (list.size()>0){
            if (code.startsWith("01")){
                edNum.setText(list.get(1));
                edPihao.setText(list.get(2));
                tvCreateDate.setText(dateDealString(list.get(2)));
                getProduct(list.get(0),0);
            }else{
                edNum.setText(MathUtil.toInt(list.get(3))+"");
                tvCreateDate.setText(dateDealString(list.get(2)));
                edPihao.setText(list.get(1));
                getProduct(list.get(0),0);
            }
        }

    }
    private SearchBean.S2Product s2Product;//用于数据查找...
    private void getProduct(String barcode , final int type){
        LoadingUtil.showDialog(mContext,"正在查找物料信息...");
        s2Product = new SearchBean.S2Product();
        s2Product.likeOr = barcode;
        s2Product.FOrg = activityPager.getOrgOut(1);
        toFilter(activity);//设置查询条件
        if (BasicShareUtil.getInstance(mContext).getIsOL()) {
            App.getRService().doIOAction(WebApi.S2Product, gson.toJson(new SearchBean(type==0?SearchBean.product_for_barcode:SearchBean.product_for_number,gson.toJson(s2Product))) , new MySubscribe<CommonResponse>() {
                @Override
                public void onNext(CommonResponse commonResponse) {
                    super.onNext(commonResponse);
                    LoadingUtil.dismiss();
                    DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                    if (null !=dBean.products && dBean.products.size()>0){
                        if (type== 1){
                            dealProduct();
                        }else{
                            EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Product,dBean.products.get(0)));
                        }
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
                if (type == 0){
                    con=con+" and FBARCODE = '"+barcode+"'";
                }else{
                    con=con+" and FNUMBER = '"+barcode+"'";
                }
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
                f.FMASTERID = cursor.getString(cursor.getColumnIndex("FMASTERID"));
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
                if (type== 1){
                    dealProduct();
                }else{
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Product,list.get(0)));
                }
            } else {
                Toast.showText(mContext, "本地查无数据");
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
                storage = (Storage) spWhichStorage.getAdapter().getItem(i);
                spWhichStorage.setTitleText(storage.FName);
                Hawk.put(Info.Storage+activityPager.getActivityMain(),storage.FName);
                Lg.e("选中仓库：", storage);
                waveHouse = null;
                edWavehouse.setText("");
//                spWavehouse.setAuto(mContext, storage, "");
                getStoreNum(product, storage,waveHouse, edPihao.getText().toString().trim(), mContext, tvStorenum,activityPager.getOrgOut(),tvCreateDate.getText().toString());


            }
        });
//        spWavehouse.setOnItemSelectedListener(new ItemListener() {
//            @Override
//            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                waveHouse = (WaveHouse) spWavehouse.getAdapter().getItem(i);
//                Lg.e("选中仓位：", waveHouse);
//            }
//        });
        spUnit.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                unit = (Unit) spUnit.getAdapter().getItem(i);
            }
        });

        lvPushsub.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pushDownSub = (PushDownSub) pushDownSubListAdapter.getItem(i);
                Lg.e("点击明细:", pushDownSub);
                getProduct(pushDownSub.FNumber,1);
//                if (BasicShareUtil.getInstance(mContext).getIsOL()) {
//                    s2Product.likeOr = pushDownSub.FNumber;
//                    s2Product.FOrg = activityPager.getOrgOut() == null ? "" : activityPager.getOrgOut().FOrgID;
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
//
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
////                    Asynchttp.post(mContext, getBaseUrl() + WebApi.S2Product, gson.toJson(new SearchBean(SearchBean.product_for_id,gson.toJson(s2Product))), new Asynchttp.Response() {
////                        @Override
////                        public void onSucceed(CommonResponse cBean, AsyncHttpClient client) {
////                            final DownloadReturnBean dBean = new Gson().fromJson(cBean.returnJson, DownloadReturnBean.class);
////                            Log.e("product.size", dBean.products.size() + "");
////                            if (dBean.products.size() > 0) {
////                                product = dBean.products.get(0);
////                                Log.e("product.size", product + "");
////                                dealProduct();
////                            }else{
////                                Toast.showText(mContext,"找不到物料数据");
////                            }
////                        }
////
////                        @Override
////                        public void onFailed(String Msg, AsyncHttpClient client) {
////                            Toast.showText(mContext, "列表物料:" + Msg);
////                        }
////                    });
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



    private void setProduct(Product product) {
        if (product != null) {
            boolean flag = true;
            for (int j = 0; j < pushDownSubListAdapter.getCount(); j++) {
                PushDownSub pushDownSub1 = (PushDownSub) pushDownSubListAdapter.getItem(j);
                if (product.FNumber.equals(pushDownSub1.FNumber)) {
                    if (MathUtil.toD(pushDownSub1.FQty) == MathUtil.toD(pushDownSub1.FQtying)) {
                        flag = true;
                        continue;
                    } else {
                    /*判断条码带出在辅助标识是否为空，不为空就判断，接着判断单位；否则直接判断单位*/
//                    if (null!=autoAuxSing && !"".equals(autoAuxSing)){
//                        if (autoAuxSing.equals(pushDownSub1.AuxSign)){
//                            if (!"".equals(default_unitID)) {
//                                if (default_unitID.equals(pushDownSub1.FUnitID)) {
//                                    flag = false;
//                                    lvPushsub.setSelection(j);
//                                    lvPushsub.performItemClick(lvPushsub.getChildAt(j), j, lvPushsub.getItemIdAtPosition(j));
//                                    break;
//                                }
//                            } else {
//                                flag = false;
//                                lvPushsub.setSelection(j);
//                                lvPushsub.performItemClick(lvPushsub.getChildAt(j), j, lvPushsub.getItemIdAtPosition(j));
//                                break;
//                            }
//                        }else if (null==pushDownSub1.AuxSign || "".equals(pushDownSub1.AuxSign)){
//                            if (!"".equals(default_unitID)) {
//                                if (default_unitID.equals(pushDownSub1.FUnitID)) {
//                                    flag = false;
//                                    lvPushsub.setSelection(j);
//                                    lvPushsub.performItemClick(lvPushsub.getChildAt(j), j, lvPushsub.getItemIdAtPosition(j));
//                                    break;
//                                }
//                            } else {
//                                flag = false;
//                                lvPushsub.setSelection(j);
//                                lvPushsub.performItemClick(lvPushsub.getChildAt(j), j, lvPushsub.getItemIdAtPosition(j));
//                                break;
//                            }
//                        }
//                    }else{

                        if (!"".equals(default_unitID)) {
                            if (default_unitID.equals(pushDownSub1.FUnitID)) {
                                if (tvCreateDate.getText().toString().equals(pushDownSub1.FCreateDate)){
                                    flag = false;
                                    lvPushsub.setSelection(j);
                                    lvPushsub.performItemClick(lvPushsub.getChildAt(j), j, lvPushsub.getItemIdAtPosition(j));
                                    break;
                                }
                            }
                        } else {
                            if (tvCreateDate.getText().toString().equals(pushDownSub1.FCreateDate)){
                                flag = false;
                                lvPushsub.setSelection(j);
                                lvPushsub.performItemClick(lvPushsub.getChildAt(j), j, lvPushsub.getItemIdAtPosition(j));
                                break;
                            }

                        }
//                    }

                    }

                }
            }

            if (flag) {
                Toast.showText(mContext, "商品不存在或者生产日期不一致");
                MediaPlayer.getInstance(mContext).error();

            }
        } else {
            Toast.showText(mContext, "列表中不存在商品");
        }
    }
    private boolean autoAdd=false;
    //获取库存
    private void getStoreNum(Product product, Storage storage, WaveHouse waveHouse, String batch, Context mContext, final TextView textView, Org org, String date,boolean auto){
        autoAdd=auto;
        getStoreNum(product,storage,waveHouse,batch,mContext,textView,org,date);
    }
    private void getStoreNum(Product product, Storage storage, WaveHouse waveHouse, String batch, Context mContext, final TextView textView, Org org, String date){
        if (product == null || storage == null || org == null){
            textView.setText("0");
            return;
        }
        Lg.e("库存查找条件：",product.FMASTERID+"-"+storage.FItemID+"-"+(waveHouse==null?"0":waveHouse.FSPID)+"-"+batch+"-"+org.FOrgID+"-"+date);
        if (BasicShareUtil.getInstance(mContext).getIsOL()) {
            InStoreNumBean storageNum = new InStoreNumBean(product.FMASTERID,storage.FItemID,waveHouse==null?"0":waveHouse.FSPID,batch,org.FOrgID,org.FOrgID,date);
            storageNum.FType="1";
            App.getRService().doIOAction("GetStoreNum4sql", new Gson().toJson(storageNum), new MySubscribe<CommonResponse>() {
                @Override
                public void onNext(CommonResponse commonResponse) {
                    super.onNext(commonResponse);
                    if (!commonResponse.state)return;
                    DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                    if (dBean.InstorageNum.size()>0){
                        textView.setText(dealStoreNumForOut(dBean.InstorageNum.get(0).FQty));
                    }else{
                        textView.setText("0");
                    }
                    if (autoAdd){
                        checkBeforeAdd();
                    }
                    autoAdd = false;
                }

                @Override
                public void onError(Throwable e) {
//                    super.onError(e);
                    textView.setText("0");
                    if (autoAdd){
                        checkBeforeAdd();
                    }
                }
            });
        }
        else{
            List<InStorageNum> container = new ArrayList<>();
            String con="";
            con=con+" and FITEM_ID='"+product.FMASTERID+"' and FSTOCK_ID='"+storage.FItemID+"' and FSTOCK_PLACE_ID='"+(waveHouse==null?"0":waveHouse.FSPID)+"'";
            if (!"".equals(batch))con=con+" and FBATCH_NO='"+batch+"'";
            if (!"".equals(batch))con=con+" and FSTORE_ORG_ID='"+org.FOrgID+"'";
            if (!"".equals(batch))con=con+" and FHUOZHU_ID='"+org.FOrgID+"'";
            if (!"".equals(date))con=con+" and FKFDATE='"+date+"'";

            String SQL = "SELECT * FROM IN_STORAGE_NUM WHERE 1=1 "+con;
            Lg.e("库存查询SQL:"+SQL);
            Cursor cursor = GreenDaoManager.getmInstance(mContext).getDaoSession().getDatabase().rawQuery(SQL, null);
            while (cursor.moveToNext()) {
                InStorageNum f = new InStorageNum();
                f.FQty = cursor.getString(cursor.getColumnIndex("FQTY"));
                Lg.e("库存查询存在FQty："+f.FQty);
                container.add(f);
            }
            if (container.size() > 0) {
                textView.setText(dealStoreNumForOut(container.get(0).FQty));
            } else {
                textView.setText("0");
            }
            if (autoAdd){
                checkBeforeAdd();
            }
        }
    }


    //处理网络库存与已添加的本地库存数量问题
    private String dealStoreNumForOut(String num) {
        Lg.e("网络库存",num);
        if (product == null) {
            return num;
        }
        List<T_Detail> list1 = t_detailDao.queryBuilder().where(
                T_DetailDao.Properties.FMaterialId.eq(product.FNumber),
                T_DetailDao.Properties.FStorageId.eq(storage.FNumber)
        ).build().list();
        Lg.e("本地明细库存"+list1.size(),list1);
        List<T_Detail> list = new ArrayList<>();
        list.addAll(list1);
        if (!"".equals(edPihao.getText().toString())) {
            for (T_Detail bean : list) {
                if (!edPihao.getText().toString().equals(bean.FBatch)) {
                    list1.remove(bean);
                }
            }
        }
        if (!"0".equals((waveHouse==null?"0":waveHouse.FNumber))) {
            for (T_Detail bean : list) {
                if (!waveHouse.FNumber.equals(bean.FWaveHouseId)) {
                    list1.remove(bean);
                }
            }
        }
        Lg.e("本地明细库存后"+list1.size(),list1);
        if (list1.size() > 0) {
            double qty = 0;
            for (int i = 0; i < list1.size(); i++) {
                qty += MathUtil.toD(list1.get(i).FRealQty);
            }
            Lg.e("本地：FQty:" + qty);
            Lg.e("返回"+(MathUtil.toD(num) - qty));
            return MathUtil.toD(num) - qty + "";
        } else {
            return num;
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
        spUnit.setAuto(product.FUnitGroupID,product.FPurchaseUnitID, SpinnerUnit.Id);
//        if (activityPager.isStorage()) {
//            spWhichStorage.setAutoSelection("", product.FStockID);
//        spWhichStorage.setAuto("",autoStorage, activityPager.getOrgOut());
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
        if ((product.FIsKFperiod) != null && (product.FIsKFperiod).equals("1")) {
            IsOpenCreateManager = true;
//            tvCreateDate.setText(CommonUtil.getTime(true));
//                tvCreateDate.setText(CommonUtil.dealCreateDate(getTime(true),MathUtil.toInt("".equals(shelfLife4Code)?product.FKFPeriod:shelfLife4Code)));
        } else {
            tvCreateDate.setText("");
            IsOpenCreateManager = false;
        }



//        spAuxsign.getData(product.FMASTERID, autoAuxSing);
//        spActualmodel.getData(product.FMASTERID, autoActualModel);

        //自动添加
        if (activityPager.getIsAuto().isChecked()) {
            getStoreNum(product, storage,waveHouse, edPihao.getText().toString().trim(), mContext, tvStorenum,activityPager.getOrgOut(),tvCreateDate.getText().toString(),true);

//            if (!checkBeforeAdd()) {
////                activityPager.ReSetScan(cbScaning);
//            }
        } else {
            getStoreNum(product, storage,waveHouse, edPihao.getText().toString().trim(), mContext, tvStorenum,activityPager.getOrgOut(),tvCreateDate.getText().toString());
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
        if (!"0".equals(storage.FIsOpenWaveHouse) && "".equals(edWavehouse.getText().toString())) {
            Toast.showText(mContext, "请选择仓位");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        if (IsOpenCreateManager && "".equals(tvCreateDate.getText().toString())){
            MediaPlayer.getInstance(mContext).error();
            Toast.showText(mContext, "请选择生产日期");
            return false;
        }

        if (activityPager.getOrgOut(0).equals("")) {
            Toast.showText(mContext, "发货组织不能为空");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        if (pushDownSub == null) {
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
        if (MathUtil.toD(edNum.getText().toString())>MathUtil.toD(tvStorenum.getText().toString())){
            MediaPlayer.getInstance(mContext).error();
            Toast.showText(mContext, "添加数不能超过库存数");
            return false;
        }
        if (edNum.getText().toString().trim().equals("") || "0".equals(edNum.getText().toString())) {
            Toast.showText(mContext, "请输入数量");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }//--------------------------------------------------
        ordercode = CommonUtil.createOrderCode(activityPager.getActivity()+pushDownMain.FID+scanOfHuozhuNumber);//单据编号

        //插入条码唯一临时表
//        CodeCheckBean bean = new CodeCheckBean(barcode, ordercode + "", edNum.getText().toString(), BasicShareUtil.getInstance(mContext).getIMIE());
//        DataModel.codeOnlyInsert(WebApi.CodeCheckInsertForOut, gson.toJson(bean));
        Addorder();
        return true;
    }

    //添加数据
    private void Addorder() {
        try {
//{"ActualModel":"","AuxSign":"","FAccountID":"5df094869da35e","FAuxQty":"1","FBarcode":"011234567890123431020002001320011321","FBatch":"","FBillerID":"135125","FBoxCodeOrder":0,"FCfLenght":0,"FCfUnitID":"10101","FCountNumber":0,"FEntryID":"101034","FExpPeriod":"100.000000","FHuoZhuNumber":"","FID":"100376","FIndex":"1578885785537","FIsFree":false,"FIsGift":"0","FIsInBox":0,"FItemID":"136321","FMaterialId":"11001","FMaterialIdForPD":"136321","FOrderId":20200113110235001,"FPriceUnitID":"Pcs","FProduceDate":"2020-01-13","FProductName":"测试","FProductNo":"","FRealQty":"2.0","FRemainInStockQty":"10.0","FRemainInStockUnitId":"Pcs","FSOEntryId":"101034","FStorage":"Transit Warehouse","FStorageId":"CK001","FStoragePDId":"101218","FTaxPrice":"50.0000000000","FUnit":"Pcs","FUnitID":"Pcs","FUnitIDForPD":"10101","FWaveHouse":"","FWaveHouseId":"","FWaveHousePDId":"0","IMIE":"A100005327E645","activity":10034,"model":"嗷嗷嗷"}
//{"ActualModel":"","AuxSign":"","FAccountID":"5df094869da35e","FAuxQty":"1","FBarcode":"011234567890123431020002001320011321","FBatch":"","FBillerID":"135125","FBoxCodeOrder":0,"FCfLenght":0,"FCfUnitID":"10101","FCountNumber":0,"FEntryID":"101034","FExpPeriod":"100.000000","FHuoZhuNumber":"","FID":"100376","FIndex":"1578885789745","FIsFree":false,"FIsGift":"0","FIsInBox":0,"FItemID":"136321","FMaterialId":"11001","FMaterialIdForPD":"136321","FOrderId":20200113110235001,"FPriceUnitID":"Pcs","FProduceDate":"2020-01-13","FProductName":"测试","FProductNo":"","FRealQty":"2.0","FRemainInStockQty":"10.0","FRemainInStockUnitId":"Pcs","FSOEntryId":"101034","FStorage":"Transit Warehouse","FStorageId":"CK001","FStoragePDId":"101218","FTaxPrice":"50.0000000000","FUnit":"Pcs","FUnitID":"Pcs","FUnitIDForPD":"10101","FWaveHouse":"","FWaveHouseId":"","FWaveHousePDId":"0","IMIE":"A100005327E645","activity":10034,"model":"嗷嗷嗷"}
            String num = edNum.getText().toString();
            String auxNum="0";
            if (null !=product.FAuxUnitID && !"0".equals(product.FAuxUnitID)){
                auxNum="1";
            }else{
                auxNum="0";
            }
            if ("".equals(num)||"0".equals(num))return;//避免多次点击，以上请求多次，导致第一次清空之后，再去添加一个空的数据
            if (true) {
                List<T_Detail> detailhebing = t_detailDao.queryBuilder().where(
                        T_DetailDao.Properties.Activity.eq(activity),
                        T_DetailDao.Properties.FOrderId.eq(ordercode),
                        T_DetailDao.Properties.FMaterialId.eq(product.FNumber),
                        T_DetailDao.Properties.FUnitID.eq(unit.FNumber),
//                        T_DetailDao.Properties.FBarcode.eq(barcode),
                        T_DetailDao.Properties.FEntryID.eq(pushDownSub.FEntryID),
                        T_DetailDao.Properties.FID.eq(pushDownSub.FID),
                        T_DetailDao.Properties.FStorageId.eq(storage.FNumber),
                        T_DetailDao.Properties.FWaveHouseId.eq(waveHouse==null?"0":waveHouse.FNumber),
                        T_DetailDao.Properties.FProduceDate.eq(IsOpenCreateManager?tvCreateDate.getText().toString():""),
                        T_DetailDao.Properties.FExpPeriod.eq(IsOpenCreateManager?product.FExpperiod:"0"),
                        T_DetailDao.Properties.FBatch.eq(edPihao.getText().toString())
                ).build().list();
                Lg.e("合并",detailhebing);
                if (detailhebing.size() > 0) {
                    for (int i = 0; i < detailhebing.size(); i++) {
                        num = (MathUtil.toD(num) + MathUtil.toD(detailhebing.get(i).FRealQty)) + "";
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
            main.FID = pushDownSub.FID;
            main.FIndex = timesecond;
            main.FBillNo = pushDownMain.FBillNo;
            main.setData(CommonUtil.getSaleOutBillType(pushDownMain.FBillTypeName), mainSaleOrg, activityPager.getOrgOut(0), activityPager.getOrgOut(0));
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
            detail.FAuxQty = auxNum;
            detail.FAuxUnitID = product.FAuxUnitNumber;
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
            detail.FProduceDate=IsOpenCreateManager?tvCreateDate.getText().toString():"";
            detail.FExpPeriod=IsOpenCreateManager?product.FExpperiod:"0";
            long insert2 = t_detailDao.insert(detail);

            if (insert1 > 0 && insert2 > 0) {
                pushDownSub.FQtying = DoubleUtil.sum(MathUtil.toD(pushDownSub.FQtying),
                        (MathUtil.toD(edNum.getText().toString()))) + "";
                pushDownSub.FAuxQtying = auxNum;
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

    private void resetAll() {
//        activityPager.ReSetScan(cbScaning);
        edPurchaseNo.setText("");
        listOrder.clear();
        tvStorenum.setText(MathUtil.doubleSub(MathUtil.toD(tvStorenum.getText().toString()),MathUtil.toD(edNum.getText().toString()))+"");
        barcode = "";
        edPihao.setText("");
        edNum.setText("");
        product = null;
        pushDownSub = null;
        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock));
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

    @OnClick({R.id.search, R.id.btn_add, R.id.btn_finishorder, R.id.btn_backorder, R.id.btn_checkorder, R.id.search_wavehouse})
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
                getStoreNum(product, storage,waveHouse, edPihao.getText().toString().trim(), mContext, tvStorenum,activityPager.getOrgOut(),tvCreateDate.getText().toString(),true);
                break;
            case R.id.search_wavehouse:
                if (null == storage){
                    Toast.showText(mContext,"请先确认仓库");
                    return;
                }
                SearchDataActivity.start(mContext,edWavehouse.getText().toString(),storage.FItemID,EventBusInfoCode.WaveHouse);
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
            case R.id.btn_finishorder:
                finishOrder();
                break;
            case R.id.btn_checkorder:
                Bundle bundle = new Bundle();
                bundle.putString("fid", pushDownMain==null?"":pushDownMain.FID);
                bundle.putInt("activity", activity);
                startNewActivity(activityPager, ReViewPDActivity.class,
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

}
