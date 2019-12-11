package com.fangzuo.assist.cloud.Fragment.TabForActivity.OtherInOutBox;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseFragment;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Activity.PagerForActivity;
import com.fangzuo.assist.cloud.Activity.PrintOutTestActivity;
import com.fangzuo.assist.cloud.Activity.ProductCheckActivity;
import com.fangzuo.assist.cloud.Activity.ReViewActivity;
import com.fangzuo.assist.cloud.Adapter.PushDownListAdapter;
import com.fangzuo.assist.cloud.Beans.BackData;
import com.fangzuo.assist.cloud.Beans.BlueToothBean;
import com.fangzuo.assist.cloud.Beans.CodeCheckBackDataBean;
import com.fangzuo.assist.cloud.Beans.CodeCheckBean;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.PrintHistory;
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
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.LocDataUtil;
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
import com.fangzuo.greendao.gen.PushDownMainDao;
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
public class FragmentOInDetail extends BaseFragment {

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
    @BindView(R.id.ed_purchase_no)
    EditText edPurchaseNo;
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
    private zpBluetoothPrinter zpSDK;
    private BlueToothBean bean;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.Product:
                product = (Product) event.postEvent;
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
                    final List<T_main> mains = t_mainDao.queryBuilder().where(T_mainDao.Properties.Activity.eq(activity)).build().list();
                    for (int i = 0; i < mains.size(); i++) {
                        final int pos = i;
                        String reString = mains.get(i).FBillerID + "|" + listOrder.get(i) + "|" + mains.get(i).FOrderId + "|" + mains.get(i).IMIE;
                        Lg.e("回单拼接",reString);
                        App.getRService().doIOAction(WebApi.OtherInUpload, reString, new MySubscribe<CommonResponse>() {
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
                    Log.e("ordercode", ordercode + "");
                    share.setOrderCode(activityPager.getActivity(), ordercode);
                    MediaPlayer.getInstance(mContext).ok();
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock+"NO"));//上传成功，解锁表头
                    Toast.showText(mContext, "上传成功");
//                btnBackorder.setClickable(true);
                    LoadingUtil.dismiss();
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
                                    checkPrint(false);
                                }
                            }).start();
                        }
                    });
                    ab.create().show();
                    tvPrint.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startNewActivity(activityPager, PrintOutTestActivity.class, R.anim.activity_slide_left_in, R.anim.activity_slide_left_out, false, null);
//                            activityPager.finish();
                        }
                    });
                    tvPrint.setText("连接打印机错误");
                    tvPrint.setTextColor(Color.RED);
                }
                break;

        }
    }

    public FragmentOInDetail() {
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
        View view = inflater.inflate(R.layout.fragment_oin_detail, container, false);
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
        zpSDK = new zpBluetoothPrinter(mContext);
        bean = Hawk.get(Config.OBJ_BLUETOOTH, new BlueToothBean("", ""));
        spUnitJiben.setEnabled(false);
        spUnitStore.setEnabled(false);
//        spUnitAux.setEnabled(false);
    }

    @Override
    protected void initData() {
        Lg.e("Fg_D:" + "initData");
        listOrder = new ArrayList<>();
        ordercode = CommonUtil.createOrderCode(activityPager.getActivity());//单据编号

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
        btnAdd.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                checkMainDlg();
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
                        checkPrint(false);
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
        spUnitJiben.setAuto(product.FBaseUnitID,SpinnerUnit.Id);
        spUnitStore.setAuto(product.FStoreUnitID, SpinnerUnit.Id);
        unit = LocDataUtil.getUnit(product.FPurchaseUnitID);
        Lg.e("得到单位：",LocDataUtil.getUnit(product.FPurchaseUnitID));
//        spUnit.setAuto(product.FPurchaseUnitID, activityPager.getOrgOut(), SpinnerUnit.Id);
//        spUnitAux.setAuto(product.FAuxUnitID, activityPager.getOrgOut(), SpinnerUnit.Id);
//        if (activityPager.isStorage()) {
            Lg.e("更新仓库");
//            spWhichStorage.setAutoSelection("", product.FStockID);
//            spWhichStorage.setAuto(product.FStockID, activityPager.getOrgOut());
            EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.UpdataStorage, product.FStockID));

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
        if (activityPager.getIsAuto().isChecked()) {
            checkMainDlg();
//            if (!checkBeforeAdd()) {
//                ReSetScan(cbScaning);
//            }
        } else {
//            ReSetScan(cbScaning);
        }
    }

    //若为该单据为第一次，弹出确认框
    private void checkMainDlg(){
        if (!activityPager.isHasLock()){
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
        }else{
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
        if (activityPager.getOrgIn(0).equals("")) {
            Toast.showText(mContext, "货主不能为空");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        if (activityPager.getSuppliers().FName.equals("")) {
            Toast.showText(mContext, "供应商不能为空");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        Lg.e("添加时单位：",unit);
        if (unit==null ||unit.FMeasureUnitID.equals("")) {
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

        LoadingUtil.showDialog(mContext, "正在获取条码数据...");
        String pdata = product.FMaterialid + "|" + unit.FMeasureUnitID + "|" + edNum.getText().toString().trim()
                + "|" + spActualmodel.getDataNumber() + "|" + spAuxsign.getDataNumber() + "|" + edPurchaseNo.getText().toString()
                + "|" + BasicShareUtil.getInstance(mContext).getIMIE() + "|" + activityPager.getStorage().FNumber + "|" + activityPager.getOrgIn(0);
        App.getRService().doIOAction(WebApi.PrintData, pdata, new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);
                if (!commonResponse.state) return;
                DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                if (null != dBean && dBean.printDataBeans.size() > 0) {
                    barcode = dBean.printDataBeans.get(0).FBarCode;
                    auxNum = dBean.printDataBeans.get(0).FAuxNum;
                    batch = dBean.printDataBeans.get(0).FBatch;
                    baseNum = dBean.printDataBeans.get(0).FBaseNum;
                    storeNum = dBean.printDataBeans.get(0).FStoreNum;
                    edBasenum.setText(baseNum);
                    edStorenum.setText(storeNum);
                    //把需要打印的数据保存到本地
                    PrintHistory printHistory = new PrintHistory();
                    printHistory.setData(product, spUnitStore.getDataObject(), spUnitJiben.getDataObject(), storeNum,
                            baseNum, spWavehouse.getWaveHouseId(), activityPager.getNote(),
                            activityPager.getOrgIn().FNote, barcode, batch, CommonUtil.getTime(true), "",spAuxsign.getDataNumber(),spActualmodel.getDataNumber());
                    daoSession.getPrintHistoryDao().insert(printHistory);
                    try {
                        CommonUtil.doPrint(zpSDK, printHistory,activityPager.getPrintNum());
                    } catch (Exception e) {
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
            main.FBillerID = Hawk.get(Info.user_id, "");
            main.FBarcode = barcode;
            main.IMIE = BasicShareUtil.getInstance(mContext).getIMIE();
            main.FOrderId = ordercode;
            main.FIndex = timesecond;
            main.setDataForOtherIn(Info.getType(activity),activityPager.getOrgOut(0),activityPager.getDBType(),activityPager.getOrgIn(0));
//            main.setData(Info.getType(activity), activityPager.getOrgOut(0), activityPager.getOrgIn(0), activityPager.getOrgIn(0));
            main.FDepartmentNumber = activityPager.getDepartMent();
//            main.FPurchaseDeptId = spDepartmentBuy.getDataNumber();
//            main.FPurchaserId = spBuyer.getDataNumber();
            main.FStockerNumber = activityPager.getManStore();
            main.FDate = activityPager.getDate();
            main.FNot = activityPager.getNote();
            main.F_FFF_Text = activityPager.getFOrderNo();
            main.setSupplier(activityPager.getSuppliers());
            long insert1 = t_mainDao.insert(main);


            T_Detail detail = new T_Detail();//--------------------------------明细-----------------
            detail.activity = activity;
            detail.FBillerID = Hawk.get(Info.user_id, "");
            detail.FBarcode = barcode;
            detail.IMIE = BasicShareUtil.getInstance(mContext).getIMIE();
            detail.FOrderId = ordercode;
            detail.FIndex = timesecond;
            detail.FRemainInStockQty = num;
            detail.FRealQty = num;
            detail.FStoreNum = edStorenum.getText().toString();
            detail.FBaseNum = edBasenum.getText().toString();
            detail.FBatch = batch;
            detail.FProductNo = edPurchaseNo.getText().toString();
            detail.FWorkShopId1 = activityPager.getDepartMent();
            detail.AuxSign = spAuxsign.getDataNumber();
            detail.ActualModel = spActualmodel.getDataNumber();
            detail.setProduct(product);
            detail.setStorage(activityPager.getStorage());
            detail.setWaveHouse(waveHouse);
            detail.setUnit(unit);
            detail.setBaseUnit(spUnitJiben.getDataObject());
            detail.setStoreUnit(spUnitStore.getDataObject());
            long insert2 = t_detailDao.insert(detail);

            if (insert1 > 0 && insert2 > 0) {
                Lg.e("成功添加：", main);
                Lg.e("成功添加：", detail);
                MediaPlayer.getInstance(mContext).ok();
                Toast.showText(mContext, "添加成功");
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
        edNum.setText("");
//        edPurchaseNo.setText("");
//        tvCode.setText("");
//        tvGoodName.setText("");
//        tvModel.setText("");
//        product = null;
        barcode = "";
        auxNum = "";
        batch = "";
        baseNum = "";
        storeNum = "";

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

    @OnClick({R.id.tv_print, R.id.search, R.id.btn_add, R.id.btn_finishorder, R.id.btn_backorder, R.id.btn_checkorder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_print:
                break;
            case R.id.search:
                ProductCheckActivity.start(activityPager,activity,tvCode.getText().toString(),activityPager.getOrgOut(1));
//                Bundle bundle1 = new Bundle();
//                bundle1.putString("search", "");
//                bundle1.putInt("where", Info.SEARCHPRODUCT);
//                bundle1.putString("org", activityPager.getOrgOut(1));
//                bundle1.putInt("activity", activity);
//                startNewActivityForResult(activityPager, ProductCheckActivity.class, R.anim.activity_open, 0, Info.SEARCHFORRESULT, bundle1);
                break;
//            case R.id.btn_add:
//                checkBeforeAdd();
//                break;
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


    @Override
    public void onResume() {
        super.onResume();
        //执行该方法时，Fragment处于活动状态，用户可与之交互。
        Lg.e("onResume");
        new Thread(new Runnable() {
            @Override
            public void run() {
                checkPrint(false);
            }
        }).start();
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
        try {
            if (null != zpSDK) zpSDK.disconnect();
        } catch (Exception e) {
        }
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
        try {
            zpSDK.disconnect();
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
