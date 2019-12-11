package com.fangzuo.assist.cloud.Fragment.TabForActivity.P2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.fangzuo.assist.cloud.Activity.ReViewP2DryingActivity;
import com.fangzuo.assist.cloud.Beans.BackData;
import com.fangzuo.assist.cloud.Beans.CodeCheckBackDataBean;
import com.fangzuo.assist.cloud.Beans.CodeCheckBean;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.GetQtyMsg;
import com.fangzuo.assist.cloud.Beans.SearchBean;
import com.fangzuo.assist.cloud.Beans.WortPrintData;
import com.fangzuo.assist.cloud.Dao.DryingGetData;
import com.fangzuo.assist.cloud.Dao.Product;
import com.fangzuo.assist.cloud.Dao.Storage;
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
import com.fangzuo.assist.cloud.Utils.JsonDealUtils;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.LocDataUtil;
import com.fangzuo.assist.cloud.Utils.MathUtil;
import com.fangzuo.assist.cloud.Utils.MediaPlayer;
import com.fangzuo.assist.cloud.Utils.ShareUtil;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.fangzuo.assist.cloud.widget.MyWaveHouseSpinner;
import com.fangzuo.assist.cloud.widget.SpinnerActualModel;
import com.fangzuo.assist.cloud.widget.SpinnerAuxSign;
import com.fangzuo.assist.cloud.widget.SpinnerStorage;
import com.fangzuo.assist.cloud.zxing.ScanManager;
import com.fangzuo.greendao.gen.DaoSession;
import com.fangzuo.greendao.gen.DryingGetDataDao;
import com.fangzuo.greendao.gen.T_DetailDao;
import com.fangzuo.greendao.gen.T_mainDao;
import com.google.gson.Gson;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.orhanobut.hawk.Hawk;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


//选择单据信息Fragment（所属：PushDownPagerActivity);
public class FragmentDryingGet4P2Detail extends BaseFragment {


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
    @BindView(R.id.tv_huozhu)
    TextView tvHuozhu;
    @BindView(R.id.tv_ceng)
    TextView tvCeng;
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
    @BindView(R.id.ed_lenght)
    TextView edLenght;
    @BindView(R.id.ed_diameter)
    TextView edDiameter;
    @BindView(R.id.ed_volume)
    TextView edVolume;
    @BindView(R.id.ed_yc)
    TextView edYc;
    private FragmentActivity mContext;
    private PagerForActivity activityPager;
    Unbinder unbinder;
    private long ordercode;
    private Product product;
    private Storage storage;
    private WaveHouse waveHouse;
    private Unit unit;
    private GetQtyMsg getQtyMsg;
    private List<WortPrintData> wortPrintDataList;
    private CodeCheckBackDataBean codeCheckBackDataBean4Error;
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
    private ScanManager mCaptureManager;
    private DryingGetDataDao dryingGetDataDao;


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.ScanResult:
                BarcodeResult res = (BarcodeResult) event.postEvent;
                mCaptureManager.onPause();
                lyScan.setVisibility(View.GONE);
                OnReceive(res.getResult().getText());
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
                    final List<T_main> mains = t_mainDao.queryBuilder().where(T_mainDao.Properties.Activity.eq(activity)).build().list();
                    for (int i = 0; i < mains.size(); i++) {
                        final int pos = i;
                        String reString = mains.get(i).FBillerID + "|" + listOrder.get(i) + "|" + mains.get(i).FOrderId + "|" + mains.get(i).IMIE;
                        App.getRService().doIOAction(WebApi.DryingGetUpload, reString, new MySubscribe<CommonResponse>() {
                            @Override
                            public void onNext(CommonResponse commonResponse) {
                                super.onNext(commonResponse);
                                dryingGetDataDao.deleteInTx(dryingGetDataDao.queryBuilder().where(
                                        DryingGetDataDao.Properties.Activity.eq(activity)
                                ).build().list());
                                t_mainDao.deleteInTx(mains.get(pos));
//                                t_detailDao.deleteInTx(t_detailDao.queryBuilder().where(
//                                        T_DetailDao.Properties.Activity.eq(activity),
//                                        T_DetailDao.Properties.FOrderId.eq(mains.get(pos).FOrderId)
//                                ).build().list());
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
//                    DataModel.submitAndAudit(mContext, Config.ProductGetActivity, listOrder.get(0));
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
                            DataService.pushBackJson(mContext, FragmentDryingGet4P2Detail.this.getClass().getSimpleName(), Hawk.get(Config.Company,""));
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
                wortPrintDataList = (List<WortPrintData>) event.postEvent;
                Lg.e("条码检测："+wortPrintDataList.size(), wortPrintDataList);
                if (wortPrintDataList.size()>0){
                    if (wortPrintDataList.get(0).FTip.equals("OK")) {
//                    if ("1".equals(codeCheckBackDataBean.FCodeType)){
//                        Toast.showText(mContext,"请勿扫描原木条码");
//                        return;
//                    }
                        edLenght.setText(MathUtil.Cut0(wortPrintDataList.get(0).FLenght));
                        edDiameter.setText(MathUtil.Cut0(wortPrintDataList.get(0).FVolSum));
                        edVolume.setText(wortPrintDataList.get(0).FVolSum);
//                        edYc.setText(wortPrintDataList.FVolume);
                        edPihao.setText(wortPrintDataList.get(0).FBatch);
//                        edPurchaseNo.setText(wortPrintDataList.FPurchaseNo);
//                        tvHuozhu.setText(LocDataUtil.getRemark(wortPrintDataList.get(0).FOWNERID,"id").FNote);
//                        tvCeng.setText(wortPrintDataList.FCeng);
                        tvModel.setText(wortPrintDataList.get(0).FModel);
//                        scanNum = wortPrintDataList.FQty;
                        edNum.setText(wortPrintDataList.get(0).FVolSum);
//                        autoActualModel = wortPrintDataList.FActualmodel;
//                        autoAuxSing = wortPrintDataList.FAuxsign;
//                        autoStorage = wortPrintDataList.FStockID;
                        LoadingUtil.showDialog(mContext, "正在查找物料信息");
                        DataModel.getProductForId(wortPrintDataList.get(0).FItemID, activityPager.getOrgOut());//03/12:已改为FNumber查找，不分org
                    } else {
//                    activityPager.ReSetScan(cbScaning);
                        Toast.showText(mContext, wortPrintDataList.get(0).FTip);
                    }
                }else{
                    Toast.showText(mContext, "查询箱码数据错误");
                }

                break;
            case EventBusInfoCode.Code_Only_Insert://写入条码唯一临时表
                codeCheckBackDataBean4Error = (CodeCheckBackDataBean) event.postEvent;
                if (codeCheckBackDataBean4Error.FTip.equals("OK")) {
                    Addorder();
                } else {
                    LoadingUtil.dismiss();
                    Toast.showText(mContext, codeCheckBackDataBean4Error.FTip);
                }
                break;
            case EventBusInfoCode.UpdataView://由表头的数据决定是否更新明细数据
                if (null != activityPager) {
//                    spUnit.setAuto("",  SpinnerUnit.Id);
                    spWhichStorage.setAuto("", "", activityPager.getOrgOut());
                }
                break;
            case EventBusInfoCode.DryingGet2Upload://由表头的数据决定是否更新明细数据
                Map<String,List<DryingGetData>> listMap = (Map<String, List<DryingGetData>>) event.postEvent;
                List<T_main> mains = t_mainDao.queryBuilder().where(
                        T_mainDao.Properties.Activity.eq(activity),
                        T_mainDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
                ).build().list();//一个FID只会对应着一个表头
                Lg.e("回单数据：", JsonDealUtils.JSonP2DryingGet(mains,listMap));
                DataModel.upload(Config.C_BatcnSave,Info.getJson(activity,JsonDealUtils.JSonP2DryingGet(mains,listMap)));
                break;

        }
    }

    public FragmentDryingGet4P2Detail() {
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
        View view = inflater.inflate(R.layout.fragment_drying_getdetail_p2, container, false);
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
        dryingGetDataDao = daoSession.getDryingGetDataDao();
        gson = activityPager.getGson();
        share = activityPager.getShare();
        //摄像头初始化
        mCaptureManager = new ScanManager(activityPager, zxingBarcodeScanner);
        mCaptureManager.initializeFromIntent(activityPager.getIntent(), activityPager.getSavedInstanceState());
        activityPager.setScanManager(mCaptureManager);
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
        barcode = code;
        LoadingUtil.showDialog(mContext, "正在检测条码...");
        //查询条码唯一表
        CodeCheckBean bean = new CodeCheckBean(code);
        DataModel.codeCheck4DryOut(WebApi.CodeCheckForDryingOut4P2, gson.toJson(bean));
    }


    @Override
    protected void initListener() {
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
//                DataModel.getStoreNum4P2(product, storage, edPihao.getText().toString().trim(), mContext, tvStorenum, activityPager.getOrgOut(), activityPager.getHuozhuOut());

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
//        tvModel.setText(product.FModel);
        tvCode.setText(product.FNumber);
        //带出物料的默认值
        unit = LocDataUtil.getUnit(product.FProduceUnitID);
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
//        DataModel.getStoreNum4P2(product, storage, edPihao.getText().toString().trim(), mContext, tvStorenum, activityPager.getOrgOut(), activityPager.getHuozhuOut());

        spAuxsign.getData(product.FMASTERID, autoAuxSing);
        spActualmodel.getData(product.FMASTERID, autoActualModel);

        //自动添加
//        if (activityPager.getIsAuto().isChecked()) {
//            checkMainDlg();
////            if (!checkBeforeAdd()) {
////                activityPager.ReSetScan(cbScaning);
////            }
//        } else {
////            activityPager.ReSetScan(cbScaning);
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
//        if (MathUtil.toD(tvStorenum.getText().toString()) <= 0) {
//            Toast.showText(mContext, "库存不足");
//            MediaPlayer.getInstance(mContext).error();
//            return false;
//        }
//        if (MathUtil.toD(edNum.getText().toString()) > MathUtil.toD(scanNum)) {
//            Toast.showText(mContext, "出库数量不能大于扫码出库数量");
//            MediaPlayer.getInstance(mContext).error();
//            return false;
//        }
        LoadingUtil.showDialog(mContext, "正在添加...");
        CodeCheckBean bean = new CodeCheckBean();
        bean.FOrderID = ordercode+"";
        bean.FPDAID = BasicShareUtil.getInstance(mContext).getIMIE();
        bean.FBarCode = barcode;
        DataModel.codeOnlyInsert(WebApi.CodeCheckInsertForOutDrying, gson.toJson(bean));

//        App.getRService().doIOAction(WebApi.GetQtyForOut, gson.toJson(new GetQtyMsg(product.FMaterialid, unit.FMeasureUnitID, edNum.getText().toString())), new MySubscribe<CommonResponse>() {
//            @Override
//            public void onNext(CommonResponse commonResponse) {
//                super.onNext(commonResponse);
//                LoadingUtil.dismiss();
//                if (!commonResponse.state) return;
//                getQtyMsg = new Gson().fromJson(commonResponse.returnJson, GetQtyMsg.class);
//                //插入条码唯一临时表
//
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
            main.setData(Info.getType(activity), activityPager.getOrgOut(0), activityPager.getOrgIn(0), activityPager.getHuozhuOut(0));
            main.FDepartmentNumber = activityPager.getDepartMent();
//            main.FPurchaseDeptId = spDepartmentBuy.getDataNumber();
//            main.FPurchaserId = activityPager.getManGet();
            main.FStockerNumber = activityPager.getManStore();
            main.FDate = activityPager.getDate();
            main.FNot = activityPager.getNote();
            main.F_FFF_Text = activityPager.getFOrderNo();
            long insert1 = t_mainDao.insert(main);

            DryingGetData dryingGetData = new DryingGetData();
            dryingGetData.activity = activity;
            dryingGetData.FBarcode = barcode;
            dryingGetData.FAccountID = CommonUtil.getAccountID();
            dryingGetData.IMIE = BasicShareUtil.getInstance(mContext).getIMIE();
            dryingGetData.FOrderId = ordercode;
            dryingGetData.setData(wortPrintDataList.get(0));

//            T_Detail detail = new T_Detail();//--------------------------------明细-----------------
//            detail.activity = activity;
//            detail.FBillerID = Hawk.get(Info.user_id, "");
//            detail.FBarcode = barcode;
//            detail.IMIE = BasicShareUtil.getInstance(mContext).getIMIE();
//            detail.FOrderId = ordercode;
//            detail.FIndex = timesecond;
//            detail.FRemainInStockQty = codeCheckBackDataBean.FVolume;
//            detail.FRealQty = codeCheckBackDataBean.FVolume;
//            detail.FStoreNum = getQtyMsg == null ? "" : getQtyMsg.FStoreQty;
//            detail.FBaseNum = getQtyMsg == null ? "" : getQtyMsg.FBaseQty;
//            detail.FProductNo = edPurchaseNo.getText().toString();
//            detail.FBatch = edPihao.getText().toString();
//            detail.AuxSign = spAuxsign.getDataNumber();
//            detail.ActualModel = spActualmodel.getDataNumber();
//            detail.setProduct(product);
//            detail.model=tvModel.getText().toString();
//            detail.setStorage(storage);
//            detail.setWaveHouse(waveHouse);
//            detail.setUnit(unit);
//            detail.setBaseUnitName(getQtyMsg == null ? "" : getQtyMsg.FBaseUnitName);
//            detail.setStoreUnitName(getQtyMsg == null ? "" : getQtyMsg.FStoreUnitName);
//            Lg.e("新信息；",codeCheckBackDataBean);
//            detail.FLevel = codeCheckBackDataBean.FLevel;
//            detail.FYmLenght = MathUtil.Cut0(codeCheckBackDataBean.FYmLenght);
//            detail.FYmDiameter = MathUtil.Cut0(codeCheckBackDataBean.FYmLenght);
//            detail.FBLenght = codeCheckBackDataBean.FBLenght;
//            detail.FBWide = codeCheckBackDataBean.FBWide;
//            detail.FBThick = codeCheckBackDataBean.FBThick;
//            detail.FVolume = codeCheckBackDataBean.FVolume;
//            long insert2 = t_detailDao.insert(detail);
            long insert2 = dryingGetDataDao.insert(dryingGetData);

            if (insert1 > 0 && insert2 > 0) {
                Lg.e("成功添加main：" ,main);
                Lg.e("成功添加detail：" ,dryingGetData);
                MediaPlayer.getInstance(mContext).ok();
                Toast.showText(mContext, "添加成功");
                resetAll();
            } else {
                LoadingUtil.dismiss();
                MediaPlayer.getInstance(mContext).error();
                Toast.showText(mContext, "添加失败，请重试");
            }

//            String sss = "{\"Model\":{\"FBillNo\":\"\",\"FBillTypeID\":{\"FNUMBER\":\"RKD01_SYS\"},\"FDate\":\"2018-11-29\",\"FInStockEntry\":[{\"FLot\":{\"FNumber\":\"101\"},\"FMaterialId\":{\"FNumber\":\"02.03.13.120.A.20.1200\"},\"FPriceUnitID\":{\"FNumber\":\"Pcs\"},\"FRealQty\":\"1\",\"FRemainInStockQty\":\"1\",\"FRemainInStockUnitId\":{\"FNumber\":\"Pcs\"},\"FStockId\":{\"FNumber\":\"CK004\"},\"FUnitID\":{\"FNumber\":\"Pcs\"}}],\"FInStockFin\":{\"FPriceTimePoint\":\"\",\"FSettleCurrId\":{\"FNumber\":\"PRE001\"},\"FSettleOrgId\":{\"FNumber\":\"100\"}},\"FOwnerIdHead\":{\"FNumber\":\"100\"},\"FOwnerTypeIdHead\":\"1\",\"FPurchaseOrgId\":{\"FNumber\":\"100\"},\"FStockDeptId\":{\"FNumber\":\"BM000008\"},\"FStockOrgId\":{\"FNumber\":\"100\"},\"FStockerId\":{\"FNumber\":\"00100\"},\"FSupplierId\":{\"FNumber\":\"VEN00002\"}}}";
//            PISBean bean = new PISBean();
//            List<T_Detail> detailList = new ArrayList<>();
//            detailList.add(detail);
//            detailList.add(detail);
//            PISBean.ModelBean modelBean = new PISBean.ModelBean();
//            modelBean.setModelBean(main, detailList);
//            bean.setModel(modelBean);
//            Lg.e("回单数据：" + gson.toJson(bean));
//
//
//            //组合多个单据回单
//            PISBatchBean batchBean = new PISBatchBean();
//            List<PISBean.ModelBean> list = new ArrayList<>();
//            list.add(modelBean);
//            list.add(modelBean);
//            batchBean.setModel(list);
//
//            //业务对象Id
//            String formid = "STK_InStock";
//            JSONArray jsonArray = new JSONArray();
//            jsonArray.put(formid);
//            jsonArray.put(gson.toJson(batchBean));
//            App.CloudService().doIOAction(Config.C_BatcnSave, jsonArray.toString(), new ToSubscribe<BackData>() {
//                @Override
//                public void onNext(BackData backData) {
//                    super.onNext(backData);
//                    if (backData.getResult().getResponseStatus().getIsSuccess()) {
//                        Lg.e("成功：" + gson.toJson(backData));
//                    } else {
//                        Lg.e("失败：" + gson.toJson(backData));
//                    }
//                }
//
//                @Override
//                public void onError(Throwable e) {
//                    super.onError(e);
//                    Lg.e("失败：" + e.toString());
//                }
//            });

        } catch (Exception e) {
            LoadingUtil.dismiss();
            DataService.pushError(mContext, this.getClass().getSimpleName(), e);
        }

    }


    //执行统一回单（查询出物料）
    public static void action4DryingGet(Context mContext, int activity){
        try{
            T_mainDao t_mainDao= GreenDaoManager.getmInstance(mContext).getDaoSession().getT_mainDao();
            final Map<String,List<DryingGetData>> listMap = new HashMap<>();
//            LoadingUtil.showDialog(mContext,"正在上传...");
            final List<T_main> mains = t_mainDao.queryBuilder().where(
                    T_mainDao.Properties.Activity.eq(activity),
                    T_mainDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
            ).build().list();//一个FID只会对应着一个表头
            if (mains.size()<=0){
//                Toast.showText(mContext,"本地不存在单据数据:"+fid);
                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock+"NO"));//本地不存在单据，解锁该单据的表头
                LoadingUtil.dismiss();
                return;
            }
            Lg.e("得到表头："+mains.size(),mains);
            List<T_main> t_mainList =new ArrayList<>();
            t_mainList.addAll(mains);
//                List<String> hzList=DataModel.getHuoZhuForPushDown(mContext,mains.get(0).FOrderId+"",activity,fid);
//                Lg.e("得到货主："+hzList.size(),hzList);
//            for (String str:hzList) {
//                t_mainList.add(mains.get(0));
//            }
            for (int i = 0; i < mains.size(); i++) {
                final int finalI = i;
                App.getRService().doIOAction(WebApi.GetDryingGetUploadData, new Gson().toJson(new SearchBean(mains.get(i).IMIE,mains.get(i).FOrderId+"")), new MySubscribe<CommonResponse>() {
                    @Override
                    public void onNext(CommonResponse commonResponse) {
                        super.onNext(commonResponse);
                        if (!commonResponse.state)return;
                        DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                        if (null != dBean && dBean.dryingGetDatas.size() > 0) {
                            listMap.put(mains.get(finalI).FOrderId+"",dBean.dryingGetDatas);
                            Lg.e("添加一条数据"+dBean.dryingGetDatas.size(),dBean.dryingGetDatas);
                            if (finalI==mains.size()-1){
                                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.DryingGet2Upload,listMap));
                            }
                        }else{
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        Lg.e("查询回单信息错误",e.getMessage());
                    }
                });
            }
            for (final T_main main:t_mainList) {
//                Lg.e("遍历货主:"+huozhu);
                //遍历出不同的货主信息
                //合并回单



//                if (details.size()>0){
//                    listMap.put(main.FOrderId+"",details);
////                    dealResult(activity,main,details);
//                } else{
//                    //当表头找不到相应的明细时，删除表头
////                    Lg.e("???删除"+main.toString());
//                    mains.remove(main);
//                    t_mainDao.delete(main);
////                    Lg.e("单据 "+main.FOrderId+" 不存在明细数据");
////                    Toast.showText(mContext,"单据 "+main.FOrderId+" 不存在明细数据");
//                }
            }
//            Lg.e("回单数据：", JsonDealUtils.JSonP2DryingGet(t_mainList,listMap));
            //执行回单拼接
//            DataModel.upload(Config.C_BatcnSave,Info.getJson(activity,JsonDealUtils.JSonP2DryingGet(mains,listMap)));

//            dealResult(activity,t_mainList,listMap);
        }catch (Exception e){
            Toast.showText(mContext,"回单错误"+e.getMessage());
            LoadingUtil.dismiss();
            DataService.pushError(mContext, mContext.getClass().getSimpleName(), e);
        }
    }


    private void resetAll() {
        LoadingUtil.dismiss();
//        activityPager.ReSetScan(cbScaning);
        edPurchaseNo.setText("");
        listOrder.clear();
        barcode = "";
        scanNum = "";
        edPihao.setText("");
        edNum.setText("");
        tvGoodName.setText("");
        tvHuozhu.setText("");
        tvModel.setText("");
        tvCeng.setText("");
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
            case R.id.search:
                if (lyScan.getVisibility() == View.VISIBLE) {
                    lyScan.setVisibility(View.GONE);
//                    mCaptureManager.onPause();
                } else {
                    mCaptureManager.onResume();
                    lyScan.setVisibility(View.VISIBLE);
                    mCaptureManager.decode();
                }
                break;
            case R.id.btn_pic:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, Info.Scan_Pic);
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
                                action4DryingGet(mContext, activity);
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
                startNewActivity(activityPager, ReViewP2DryingActivity.class,
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
