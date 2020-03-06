package com.fangzuo.assist.cloud.Fragment.TabForActivity.PDBox;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseFragment;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Activity.PagerForActivity;
import com.fangzuo.assist.cloud.Activity.PrintOutTestActivity;
import com.fangzuo.assist.cloud.Activity.ReViewActivity;
import com.fangzuo.assist.cloud.Beans.BackData;
import com.fangzuo.assist.cloud.Beans.BlueToothBean;
import com.fangzuo.assist.cloud.Beans.CodeCheckBackDataBean;
import com.fangzuo.assist.cloud.Beans.CodeCheckBean;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.GetQtyMsg;
import com.fangzuo.assist.cloud.Beans.PrintHistory;
import com.fangzuo.assist.cloud.Beans.SearchBean;
import com.fangzuo.assist.cloud.Dao.Product;
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
import com.fangzuo.assist.cloud.zxing.ScanManager;
import com.fangzuo.greendao.gen.DaoSession;
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
public class FragmentPYingDetail extends BaseFragment {


    @BindView(R.id.sp_which_storage)
    SpinnerStorage spWhichStorage;
    @BindView(R.id.sp_wavehouse)
    MyWaveHouseSpinner spWavehouse;
    //    @BindView(R.id.ed_code)
//    EditText edCode;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.tv_goodName)
    TextView tvGoodName;
    @BindView(R.id.tv_model)
    TextView tvModel;
    //    @BindView(R.id.sp_unit)
//    SpinnerUnit spUnit;
    @BindView(R.id.ed_pihao)
    TextView edPihao;
    @BindView(R.id.ed_num)
    EditText edNum;
    @BindView(R.id.ed_purchase_no)
    EditText edPurchaseNo;
    @BindView(R.id.sp_auxsign)
    SpinnerAuxSign spAuxsign;
    @BindView(R.id.sp_actualmodel)
    SpinnerActualModel spActualmodel;
    @BindView(R.id.zxing_barcode_scanner)
    DecoratedBarcodeView zxingBarcodeScanner;
    //    @BindView(R.id.cb_scaning)
//    CheckBox cbScaning;
    @BindView(R.id.tv_storenum)
    TextView tvStorenum;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.ly_scan)
    RelativeLayout lyScan;
    @BindView(R.id.tv_print)
    TextView tvPrint;
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
    private GetQtyMsg getQtyMsg;
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
    private String autoStorage = "";
    private String scanNum = "";
//    private ScanManager mCaptureManager;
//    private zpBluetoothPrinter zpSDK;
//    private BlueToothBean bean;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.ScanResult:
                BarcodeResult res = (BarcodeResult) event.postEvent;
//                if (cbScaning.isChecked()){
//                }else{
//                mCaptureManager.onPause();
                lyScan.setVisibility(View.GONE);
//                }

                OnReceive(res.getResult().getText());
//                Toast.showText(mContext, "扫描结果：" + res.getResult().getText());
                break;
            case EventBusInfoCode.Product:
                product = (Product) event.postEvent;
                Lg.e("获得物料信息：", product);
//                binding.setProduct(product);
                dealProduct();
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
                            T_mainDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
                    ).build().list();
                    for (int i = 0; i < mains.size(); i++) {
                        final int pos = i;
                        String reString = mains.get(i).FBillerID + "|" + listOrder.get(i) + "|" + mains.get(i).FOrderId + "|" + mains.get(i).IMIE;
                        App.getRService().doIOAction(activityPager.getActivity()==Config.PYingActivity?WebApi.PYUpload:WebApi.PKUpload, reString, new MySubscribe<CommonResponse>() {
                            @Override
                            public void onNext(CommonResponse commonResponse) {
                                super.onNext(commonResponse);
                                t_detailDao.deleteInTx(t_detailDao.queryBuilder().where(
                                        T_DetailDao.Properties.Activity.eq(activity),
                                        T_DetailDao.Properties.FAccountID.eq(CommonUtil.getAccountID()),
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
                    Log.e("ordercode", ordercode + "");
                    share.setOrderCode(activityPager.getActivity(), ordercode);
                    MediaPlayer.getInstance(mContext).ok();
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock + "NO"));//上传成功，解锁表头
                    Toast.showText(mContext, "上传成功");
//                btnBackorder.setClickable(true);
                    LoadingUtil.dismiss();
                    DataModel.submitAndAudit(mContext, Config.PYingActivity, listOrder.get(0));
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
                            DataService.pushBackJson(mContext, FragmentPYingDetail.this.getClass().getSimpleName(), Hawk.get(Config.Company,""));
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
                Lg.e("条码检测：", codeCheckBackDataBean);
                if (codeCheckBackDataBean.FTip.equals("OK")) {
                    edPihao.setText(codeCheckBackDataBean.FBatchNo);
                    edPurchaseNo.setText(codeCheckBackDataBean.FPurchaseNo);
                    scanNum = codeCheckBackDataBean.FQty;
                    edNum.setText(codeCheckBackDataBean.FQty);
                    autoActualModel = codeCheckBackDataBean.FActualmodel;
                    autoAuxSing = codeCheckBackDataBean.FAuxsign;
                    autoStorage = codeCheckBackDataBean.FStockID;
                    LoadingUtil.showDialog(mContext, "正在查找物料信息");
                    DataModel.getProductForNumber(codeCheckBackDataBean.FItemID, activityPager.getOrgOut());//03/12:已改为FNumber查找，不分org
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
                    LoadingUtil.dismiss();
                    Toast.showText(mContext, codeCheckBackDataBean.FTip);
                }
                break;
            case EventBusInfoCode.UpdataView://由表头的数据决定是否更新明细数据
                if (null != activityPager) {
//                    spUnit.setAuto("",  SpinnerUnit.Id);
                    spWhichStorage.setAuto("", "", activityPager.getOrgOut());
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

    public FragmentPYingDetail() {
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
        View view = inflater.inflate(R.layout.fragment_pying_detail, container, false);
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
//        zpSDK = new zpBluetoothPrinter(mContext);
//        bean = Hawk.get(Config.OBJ_BLUETOOTH, new BlueToothBean("", ""));
    }

    @Override
    protected void initData() {
        listOrder = new ArrayList<>();
        ordercode = CommonUtil.createOrderCode(activityPager.getActivity());//单据编号
        spAuxsign.setEnabled(false);
        spActualmodel.setEnabled(false);

    }

    //在oncreateView之前使用 不要使用控件
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume Lg.e("fragment显示");
            if (null != activityPager) {
                setfocus(search);
//                spWhichStorage.setAuto("",activityPager.getOrgOut());
//                spDepartmentGet.setAuto(getString(R.string.spDepartmentGet_pis),"",activityPager.getOrgOut());
//                spUnit.setAuto("", activityPager.getOrgOut(),SpinnerUnit.Id);
//                spUnitAux.setAuto("", activityPager.getOrgOut(),SpinnerUnit.Id);
            }
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
//        barcode = code;
//        LoadingUtil.showDialog(mContext, "正在检测条码...");
//        //查询条码唯一表
//        CodeCheckBean bean = new CodeCheckBean(code);
//        DataModel.codeCheck(WebApi.CodeCheckForPY, gson.toJson(bean));
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

        btnAdd.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                checkMainDlg();
            }
        });
        spWhichStorage.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                storage = (Storage) spWhichStorage.getAdapter().getItem(i);
                spWhichStorage.setTitleText(storage.FName);
                Lg.e("选中仓库：", storage);
                waveHouse = null;
                spWavehouse.setAuto(mContext, storage, "");
                DataModel.getStoreNumForType(product, storage, edPihao.getText().toString().trim(), mContext, tvStorenum,activityPager.getDBType(), activityPager.getOrgOut(), activityPager.getHuozhuIn());

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
        unit = LocDataUtil.getUnit(product.FPurchaseUnitID);
//        spUnit.setAuto(product.FPurchaseUnitID,SpinnerUnit.Id);
//        if (activityPager.isStorage()) {
//            spWhichStorage.setAutoSelection("", product.FStockID);
        spWhichStorage.setAuto("", autoStorage, activityPager.getOrgOut());
//        }
        if (CommonUtil.isOpen(product.FIsBatchManage)) {
            isOpenBatch = true;
            edPihao.setEnabled(true);
        } else {
            edPihao.setEnabled(false);
            edPihao.setText("");
            isOpenBatch = false;
        }
        if ((product.FIsKFperiod) != null && (product.FIsKFperiod).equals("1")) {
            IsOpenCreateManager = true;
            tvCreateDate.setText(CommonUtil.getTime(true));
//                tvCreateDate.setText(CommonUtil.dealCreateDate(getTime(true),MathUtil.toInt("".equals(shelfLife4Code)?product.FKFPeriod:shelfLife4Code)));
        } else {
            tvCreateDate.setText("");
            IsOpenCreateManager = false;
        }


        DataModel.getStoreNumForType(product, storage, edPihao.getText().toString().trim(), mContext, tvStorenum,activityPager.getDBType(), activityPager.getOrgOut(), activityPager.getHuozhuIn());

        spAuxsign.getData(product.FMASTERID, autoAuxSing);
        spActualmodel.getData(product.FMASTERID, autoActualModel);

        //自动添加
        if (activityPager.getIsAuto().isChecked()) {
            checkMainDlg();
//            if (!checkBeforeAdd()) {
//                activityPager.ReSetScan(cbScaning);
//            }
        } else {
//            activityPager.ReSetScan(cbScaning);
        }
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
                    checkBeforeAdd();
                }
            });
            ab.setNegativeButton(getString(R.string.cancle), null);
            ab.create().show();
        } else {
            checkBeforeAdd();
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
        if (activityPager.getOrgOut(0).equals("")) {
            Toast.showText(mContext, "组织不能为空");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        if (IsOpenCreateManager && "".equals(tvCreateDate.getText().toString())){
            MediaPlayer.getInstance(mContext).error();
            Toast.showText(mContext, "请选择生产日期");
            return false;
        }

        if (activityPager.getDepartMent().equals("")) {
            Toast.showText(mContext, "部门不能为空");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        if (activityPager.getHuozhuOut(0).equals("")) {
            Toast.showText(mContext, "货主不能为空");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        if (unit == null || unit.FMeasureUnitID.equals("")) {
            Toast.showText(mContext, "物料单位未带出，请重试...");
            unit = LocDataUtil.getUnit(product.FPurchaseUnitID);
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
//        if (isOpenBatch && edPihao.getText().toString().trim().equals("")) {
//            Toast.showText(mContext, "请输入批号信息");
//            MediaPlayer.getInstance(mContext).error();
//            return false;
//        }
        //--------------------------------------------------
        if (edNum.getText().toString().trim().equals("") || "0".equals(edNum.getText().toString())) {
            Toast.showText(mContext, "请输入数量");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }//--------------------------------------------------
        if (MathUtil.toD(tvStorenum.getText().toString()) >= MathUtil.toD(edNum.getText().toString())) {
            Toast.showText(mContext, "盘盈数量必须大于库存数量");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
//        if (MathUtil.toD(edNum.getText().toString()) > MathUtil.toD(scanNum)) {
//            Toast.showText(mContext, "出库数量不能大于扫码出库数量");
//            MediaPlayer.getInstance(mContext).error();
//            return false;
//        }
        Addorder();
//        LoadingUtil.showDialog(mContext, "正在添加...");
//        App.getRService().doIOAction(WebApi.GetQtyForOut, gson.toJson(new GetQtyMsg(product.FMaterialid, unit.FMeasureUnitID, edNum.getText().toString())), new MySubscribe<CommonResponse>() {
//            @Override
//            public void onNext(CommonResponse commonResponse) {
//                super.onNext(commonResponse);
//                LoadingUtil.dismiss();
//                if (!commonResponse.state) return;
//                getQtyMsg = new Gson().fromJson(commonResponse.returnJson, GetQtyMsg.class);
//                //插入条码唯一临时表
//                CodeCheckBean bean = new CodeCheckBean(barcode, ordercode + "", edNum.getText().toString(), BasicShareUtil.getInstance(mContext).getIMIE());
//                DataModel.codeOnlyInsert(WebApi.CodeCheckInsertForPY, gson.toJson(bean));
//                //        Addorder();
//            }
//
//            @Override
//            public void onError(Throwable e) {
////                super.onError(e);
//                Toast.showText(mContext, "获取基本数量失败");
//                LoadingUtil.dismiss();
//            }
//        });

        return true;
    }

    //添加数据
    private void Addorder() {
        try {
            String num = edNum.getText().toString();
            if ("".equals(num) || "0".equals(num)) return;//避免多次点击，以上请求多次，导致第一次清空之后，再去添加一个空的数据
            if (true) {
                Lg.e("合并");
                List<T_Detail> detailhebing = t_detailDao.queryBuilder().where(
                        T_DetailDao.Properties.Activity.eq(activity),
                        T_DetailDao.Properties.FOrderId.eq(ordercode),
                        T_DetailDao.Properties.FMaterialId.eq(product.FNumber),
                        T_DetailDao.Properties.FUnitID.eq(unit.FNumber),
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
            main.FIndex = timesecond;
            main.setData4PYing(Info.getType(activity), activityPager.getOrgOut(0), activityPager.getOrgOut(0),activityPager.getDBType(), activityPager.getHuozhuOut(0));
            main.FDepartmentNumber = activityPager.getDepartMent();
//            main.FPurchaseDeptId = spDepartmentBuy.getDataNumber();
//            main.FPurchaserId = activityPager.getManGet();
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
            detail.FIndex = timesecond;
//            detail.FRemainInStockQty = MathUtil.doubleSub(MathUtil.toD(num),MathUtil.toD(tvStorenum.getText().toString()))+"";
            detail.FRemainInStockQty = MathUtil.toD(tvStorenum.getText().toString())+"";
            detail.FRealQty = num;
            detail.FStoreNum = getQtyMsg == null ? "" : getQtyMsg.FStoreQty;
            detail.FBaseNum = getQtyMsg == null ? "" : getQtyMsg.FBaseQty;
            detail.FProductNo = edPurchaseNo.getText().toString();
            detail.FBatch = edPihao.getText().toString();
            detail.AuxSign = spAuxsign.getDataNumber();
            detail.ActualModel = spActualmodel.getDataNumber();
            detail.FOwnerId = activityPager.getHuozhuIn(0);
            detail.setProduct(product);
            detail.setStorage(storage);
            detail.setWaveHouse(waveHouse);
            detail.setUnit(unit);
            detail.setBaseUnitName(getQtyMsg == null ? "" : getQtyMsg.FBaseUnitName);
            detail.setStoreUnitName(getQtyMsg == null ? "" : getQtyMsg.FStoreUnitName);
            detail.FProduceDate=IsOpenCreateManager?tvCreateDate.getText().toString():"";
            detail.FExpPeriod=IsOpenCreateManager?product.FExpperiod:"0";
            long insert2 = t_detailDao.insert(detail);

            if (insert1 > 0 && insert2 > 0) {
                Lg.e("成功添加Main：",main);
                Lg.e("成功添加Detail：",detail);
                MediaPlayer.getInstance(mContext).ok();
                Toast.showText(mContext, "添加成功");
                resetAll();
                //打印条码
//                printOut(barcode,num);
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

    //打印
//    private void printOut(final String barcode, String num){
//        LoadingUtil.showDialog(mContext,"正在打印数据...");
//        SearchBean searchBean = new SearchBean(num,barcode);
//        App.getRService().doIOAction(WebApi.PrintOutForPD, gson.toJson(searchBean), new MySubscribe<CommonResponse>() {
//            @Override
//            public void onNext(CommonResponse commonResponse) {
//                super.onNext(commonResponse);
//                LoadingUtil.dismiss();
//                if (!commonResponse.state)return;
////                Lg.e("获得打印数据：",commonResponse.returnJson);
//                DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
//                if (dBean.printHistories.size()>0){
//                    PrintHistory history = dBean.printHistories.get(0);
//                    history.FHuoquan= LocDataUtil.getRemark(history.getFHuoquan(),"number").FNote;
//                    //把需要打印的数据保存到本地
//                    PrintHistory printHistory = new PrintHistory();
//                    printHistory.setData(product, history.FUnit, history.FUnitAux, history.FNum,
//                            history.FNum2, history.FWaveHouse, "",
//                            history.FHuoquan, barcode, history.FBatch, CommonUtil.getTime(true), "",history.FAuxSign,history.FActualModel);
//                    daoSession.getPrintHistoryDao().insert(printHistory);
//
//                    resetAll();
//                    try {
//                        CommonUtil.doPrint(zpSDK, printHistory,activityPager.getPrintNum());
//                    } catch (Exception e) {
//                    }
//
//                }else{
//                    resetAll();
//                    Toast.showTextLong(mContext,"找不到条码打印数据");
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//                resetAll();
////                adapter.clear();
////                LoadingUtil.dismiss();
//            }
//        });
//    }
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

    private void resetAll() {
        LoadingUtil.dismiss();
//        activityPager.ReSetScan(cbScaning);
        edPurchaseNo.setText("");
        listOrder.clear();
        barcode = "";
        scanNum = "";
        edPihao.setText("");
        edNum.setText("");
        product = null;
        //判断是否锁住表头
        //判断是否有保存的业务单号，没有的话，锁定表头
//        if ("".equals(Hawk.get(Config.OrderNo+activityPager.getActivity(),""))){
        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock));
//        }
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

    @OnClick({R.id.search, R.id.btn_add, R.id.btn_finishorder, R.id.btn_backorder, R.id.btn_checkorder, R.id.btn_pic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            case R.id.search:
//                if (lyScan.getVisibility()==View.VISIBLE){
//                    lyScan.setVisibility(View.GONE);
////                    mCaptureManager.onPause();
//                }else{
////                    mCaptureManager.onResume();
//                    lyScan.setVisibility(View.VISIBLE);
////                    mCaptureManager.decode();
//                }
//                break;
//            case R.id.btn_add:
//                checkBeforeAdd();
//                break;
            case R.id.btn_pic:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, Info.Scan_Pic);
                break;
            case R.id.btn_backorder:
                new AlertDialog.Builder(mContext)
                        .setTitle("确认上传？")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LoadingUtil.showDialog(mContext, "正在上传...");
                                UpLoadModel.action(mContext, activity);
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
                startNewActivity(activityPager, ReViewActivity.class,
                        R.anim.activity_fade_in, R.anim.activity_fade_out, false, bundle);
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        //执行该方法时，Fragment处于活动状态，用户可与之交互。
        Lg.e("onResume");
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
