package com.fangzuo.assist.cloud.Fragment.TabForActivity.P2;

import android.app.Activity;
import android.app.AlertDialog;
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
import com.fangzuo.assist.cloud.Activity.ReViewActivity;
import com.fangzuo.assist.cloud.Activity.ReViewP2Activity;
import com.fangzuo.assist.cloud.Beans.BackData;
import com.fangzuo.assist.cloud.Beans.CodeCheckBackDataBean;
import com.fangzuo.assist.cloud.Beans.CodeCheckBean;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.GetQtyMsg;
import com.fangzuo.assist.cloud.Dao.Product;
import com.fangzuo.assist.cloud.Dao.Storage;
import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.Dao.T_main;
import com.fangzuo.assist.cloud.Dao.Unit;
import com.fangzuo.assist.cloud.Dao.WaveHouse;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.RxSerivce.ToSubscribe;
import com.fangzuo.assist.cloud.Service.DataService;
import com.fangzuo.assist.cloud.Utils.BasicShareUtil;
import com.fangzuo.assist.cloud.Utils.CommonUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.DataModel;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.JsonDealUtils;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.LocDataUtil;
import com.fangzuo.assist.cloud.Utils.MathUtil;
import com.fangzuo.assist.cloud.Utils.MediaPlayer;
import com.fangzuo.assist.cloud.Utils.ShareUtil;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.UpLoadModel;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.fangzuo.assist.cloud.widget.SpinnerActualModel;
import com.fangzuo.assist.cloud.widget.SpinnerAuxSign;
import com.fangzuo.assist.cloud.widget.SpinnerStorage;
import com.fangzuo.assist.cloud.widget.SpinnerStorageDlg;
import com.fangzuo.assist.cloud.widget.SpinnerUnit;
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


//选择单据信息Fragment（所属：PushDownPagerActivity);
public class FragmentDB4P2Detail extends BaseFragment {


    @BindView(R.id.zxing_barcode_scanner)
    DecoratedBarcodeView zxingBarcodeScanner;
    @BindView(R.id.ly_scan)
    RelativeLayout lyScan;
//    @BindView(R.id.ed_supplier)
//    EditText edSupplier;
//    @BindView(R.id.search_supplier)
//    RelativeLayout searchSupplier;
//    @BindView(R.id.sp_which_storage_out)
//SpinnerStorage spWhichStorageOut;
//    @BindView(R.id.sp_wavehouse_out)
//    MyWaveHouseSpinner spWavehouseOut;
//    @BindView(R.id.sp_which_storage_in)
//    SpinnerStorageDlg spWhichStorageIn;
//    @BindView(R.id.sp_wavehouse_in)
//    MyWaveHouseSpinner spWavehouseIn;
//    @BindView(R.id.cb_scaning)
//    CheckBox cbScaning;
//    @BindView(R.id.scanbyCamera)
//    RelativeLayout scanbyCamera;
//    @BindView(R.id.ed_code)
//    EditText edCode;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.tv_goodName)
    TextView tvGoodName;
    @BindView(R.id.tv_storenum)
    TextView tvStorenum;
    @BindView(R.id.tv_model)
    TextView tvModel;
    @BindView(R.id.tv_huozhu)
    TextView tvHuozhu;
    @BindView(R.id.tv_ceng)
    TextView tvCeng;
    @BindView(R.id.sp_unit)
    SpinnerUnit spUnit;
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
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_finishorder)
    Button btnFinishorder;
    @BindView(R.id.btn_backorder)
    Button btnBackorder;
    @BindView(R.id.btn_checkorder)
    Button btnCheckorder;
    private FragmentActivity mContext;
    private PagerForActivity activityPager;
    Unbinder unbinder;
    private long ordercode;
    private Product product;
    private Storage storageOut;
    private Storage storageIn;
    private WaveHouse waveHouseOut;//已隐藏去掉
    private WaveHouse waveHouseIn;//已隐藏去掉
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
    private ScanManager mCaptureManager;


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.ScanResult:
                BarcodeResult res = (BarcodeResult) event.postEvent;
//                if (cbScaning.isChecked()) {
//                } else {
                    mCaptureManager.onPause();
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
                    //获取生成的单号数据
                    listOrder = new ArrayList<>();
                    for (int i = 0; i < backData.getResult().getResponseStatus().getSuccessEntitys().size(); i++) {
                        listOrder.add(backData.getResult().getResponseStatus().getSuccessEntitys().get(i).getNumber());
                    }
                    final List<T_main> mains = t_mainDao.queryBuilder().where(T_mainDao.Properties.Activity.eq(activity)).build().list();
                    Lg.e("本地main.size：",mains.size());
                    for (int i = 0; i < mains.size(); i++) {
                        final int pos = i;
                        String reString = mains.get(i).FBillerID + "|" + listOrder.get(i) + "|" + mains.get(i).FOrderId + "|" + mains.get(i).IMIE;
                        App.getRService().doIOAction(WebApi.DBUpload, reString, new MySubscribe<CommonResponse>() {
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
                    MediaPlayer.getInstance(mContext).ok();
                    Toast.showText(mContext, "上传成功");
                    ordercode++;
                    Log.e("ordercode", ordercode + "");
                    share.setOrderCode(activityPager.getActivity(), ordercode);
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock + "NO"));//上传成功，解锁表头
//                btnBackorder.setClickable(true);
                    LoadingUtil.dismiss();
                    DataModel.submitAndAudit(mContext,Config.DBActivity,listOrder.get(0));
//                    submitAndAudit(listOrder.get(0));//是否提交并审核
                } else {
                    LoadingUtil.dismiss();
                    List<BackData.ResultBean.ResponseStatusBean.ErrorsBean> errorsBeans = backData.getResult().getResponseStatus().getErrors();
                    StringBuilder builder = new StringBuilder();
                    for (BackData.ResultBean.ResponseStatusBean.ErrorsBean error : errorsBeans) {
                        builder.append(error.getFieldName() + "\n");
                        builder.append(error.getMessage() + "\n");
                    }
                    AlertDialog.Builder delete = new AlertDialog.Builder(mContext);
                    delete.setTitle("上传错误");
                    delete.setMessage(builder.toString());
                    delete.setPositiveButton("确定", null);
                    delete.setNegativeButton("反馈信息", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DataService.pushBackJson(mContext, FragmentDB4P2Detail.this.getClass().getSimpleName(), Hawk.get(Config.Company,""));
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
                Lg.e("条码检测",codeCheckBackDataBean);
                if (codeCheckBackDataBean.FTip.equals("OK")) {
                    if ("1".equals(codeCheckBackDataBean.FCodeType)){
                        Toast.showText(mContext,"请勿扫描原木条码");
                        return;
                    }
                    edPihao.setText(codeCheckBackDataBean.FBatchNo);
                    edPurchaseNo.setText(codeCheckBackDataBean.FPurchaseNo);
                    edNum.setText(codeCheckBackDataBean.FVolume);
                    tvHuozhu.setText(LocDataUtil.getRemark(codeCheckBackDataBean.FHuoZhuNumber,"number").FNote);
                    tvCeng.setText(codeCheckBackDataBean.FCeng);
                    tvModel.setText(MathUtil.Cut0(codeCheckBackDataBean.FYmLenght)+"x"+codeCheckBackDataBean.FBWide+"x"+codeCheckBackDataBean.FBThick);
                    autoActualModel = codeCheckBackDataBean.FActualmodel;
                    autoAuxSing = codeCheckBackDataBean.FAuxsign;
                    autoStorage = codeCheckBackDataBean.FStockID;
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
                    LoadingUtil.dismiss();
                    Toast.showText(mContext, codeCheckBackDataBean.FTip);
                }
                break;
            case EventBusInfoCode.UpdataView://由表头的数据决定是否更新明细数据
                if (null != activityPager||null != spUnit) {
//                    spUnit.setAuto("", SpinnerUnit.Id);
                    Lg.e("-----------",activityPager.getOrgOut());
//                    spWhichStorageOut.setAuto("storageout"+activityPager.getActivity(),Hawk.get("storageout"+activityPager.getActivity(),""), activityPager.getOrgOut());
                }
                break;
            case EventBusInfoCode.UpdataViewForDBInStorage://由表头的数据决定是否更新明细数据
                if (null != activityPager||null != spUnit) {
//                    spWhichStorageIn.setAuto(Hawk.get("storagein"+activityPager.getActivity(),""), activityPager.getOrgIn());
                }
                break;
        }
    }

    public FragmentDB4P2Detail() {
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
        View view = inflater.inflate(R.layout.fragment_db4p2detail, container, false);
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
            if (null!=activityPager){
                        setfocus(search);

//                spWhichStorage.setAuto("",activityPager.getOrgOut());
//                spDepartmentGet.setAuto(getString(R.string.spDepartmentGet_pis),"",activityPager.getOrgOut());
//                spUnit.setAuto("", activityPager.getOrgOut(),SpinnerUnit.Id);
//                spUnitAux.setAuto("", activityPager.getOrgOut(),SpinnerUnit.Id);
                storageOut = activityPager.getStorageOut();
                storageIn = activityPager.getStorageIn();
                Lg.e("得到仓库",storageOut);
                Lg.e("得到仓库",storageIn);
                DataModel.getStoreNum4P2Shuiban(product, storageOut, edPihao.getText().toString().trim(), mContext, tvStorenum,activityPager.getOrgOut(),activityPager.getHuozhuOut());

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
        DataModel.codeCheck(WebApi.CodeCheckForOutForDB4P2, gson.toJson(bean));
    }


    @Override
    protected void initListener() {
        btnAdd.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                checkMainDlg();
            }
        });
//        spWhichStorageOut.setOnItemSelectedListener(new ItemListener() {
//            @Override
//            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                storageOut = (Storage) spWhichStorageOut.getAdapter().getItem(i);
//                spWhichStorageOut.setTitleText(storageOut.FName);
//                Lg.e("选中调出仓库：", storageOut);
//                Hawk.put("storageout"+activityPager.getActivity(),storageOut.FName);
////                waveHouseOut = null;
////                spWavehouseOut.setAuto(mContext, storageOut, "");
//                DataModel.getStoreNum4P2Shuiban(product, storageOut, edPihao.getText().toString().trim(), mContext, tvStorenum,activityPager.getOrgOut(),activityPager.getHuozhuOut());
//
//            }
//        });
//        spWavehouseOut.setOnItemSelectedListener(new ItemListener() {
//            @Override
//            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                waveHouseOut = (WaveHouse) spWavehouseOut.getAdapter().getItem(i);
//                Lg.e("选中调出仓位：", waveHouseOut);
//            }
//        });

//        spWhichStorageIn.setOnItemSelectedListener(new ItemListener() {
//            @Override
//            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                storageIn = (Storage) spWhichStorageIn.getAdapter().getItem(i);
//                spWhichStorageIn.setTitleText(storageIn.FName);
//                Hawk.put("storagein"+activityPager.getActivity(),storageIn.FName);
//                Lg.e("选中调入仓库：", storageIn);
////                waveHouseIn = null;
////                spWavehouseIn.setAuto(mContext, storageIn, "");
//
//            }
//        });
//        spWavehouseIn.setOnItemSelectedListener(new ItemListener() {
//            @Override
//            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                waveHouseIn = (WaveHouse) spWavehouseIn.getAdapter().getItem(i);
//                Lg.e("选中调入仓位：", spWavehouseIn);
//            }
//        });

        spUnit.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                unit = (Unit) spUnit.getAdapter().getItem(i);
            }
        });

    }

    //处理物料数据
    private void dealProduct() {
        if (product == null) {
            return;
        }
        //设置物料信息
        tvCode.setText(product.FNumber);tvGoodName.setText(product.FName);
//        tvModel.setText(product.FModel);
        //带出物料的默认值
        spUnit.setAuto(product.FProduceUnitID, SpinnerUnit.Id);
//        if (activityPager.isStorage()) {
//            spWhichStorageOut.setAuto("",autoStorage, activityPager.getOrgOut());
//        }
        if (CommonUtil.isOpen(product.FIsBatchManage)) {
            isOpenBatch = true;
            edPihao.setEnabled(true);
        } else {
            edPihao.setText("");
            edPihao.setEnabled(false);
            isOpenBatch = false;
        }
        DataModel.getStoreNum4P2Shuiban(product, storageOut, edPihao.getText().toString().trim(), mContext, tvStorenum,activityPager.getOrgOut(),activityPager.getHuozhuOut());

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
        if (storageOut == null || null==storageIn) {
            Toast.showText(mContext, "请选择仓库");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        if (storageIn.FNumber.equals(storageOut.FNumber)){
            Toast.showText(mContext, "相同仓库不能调拨");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }

        Lg.e("仓库check:", storageOut);
        if (!CommonUtil.isAllowFStore(storageOut.FallowFStore) && MathUtil.toD(tvStorenum.getText().toString()) < MathUtil.toD(edNum.getText().toString())) {
            Toast.showText(mContext, "库存不足");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        if (isOpenBatch && edPihao.getText().toString().trim().equals("")) {
            Toast.showText(mContext, "请输入批号信息");
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
        if ("".equals(activityPager.getHuozhuIn(0))||"".equals(activityPager.getHuozhuOut(0))){
            Toast.showText(mContext, "调出货主或调入货主不能为空，请检查...");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        //--------------------------------------------------
        if (edNum.getText().toString().trim().equals("") || "0".equals(edNum.getText().toString())) {
            Toast.showText(mContext, "请输入数量");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        //当为跨组织调拨时，
        if ("OverOrgTransfer".equals(activityPager.getDBType())) {
            if (activityPager.getOrgOut().FName.equals(activityPager.getOrgIn().FName)) {
                if (activityPager.getHuozhuOut() != null && activityPager.getHuozhuOut().FNumber.equals(activityPager.getHuozhuIn().FNumber)) {
                    Toast.showText(mContext, "相同组织时，货主不能相同");
                    MediaPlayer.getInstance(mContext).error();
                    return false;
                }
            }
        }else{
            if (!activityPager.getHuozhuOut().FName.equals(activityPager.getHuozhuIn().FName)){
                Toast.showText(mContext, "组织内调拨时，调出调入货主必须一样");
                MediaPlayer.getInstance(mContext).error();
                return false;
            }
        }
        LoadingUtil.showDialog(mContext,"正在添加...");

        App.getRService().doIOAction(WebApi.GetQtyForOut, gson.toJson(new GetQtyMsg(product.FMaterialid,unit.FMeasureUnitID,edNum.getText().toString())), new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);
                LoadingUtil.dismiss();
                if (!commonResponse.state)return;
                getQtyMsg = new Gson().fromJson(commonResponse.returnJson, GetQtyMsg.class);
                //插入条码唯一临时表
                CodeCheckBean bean = new CodeCheckBean(barcode, ordercode + "", storageIn.FItemID,waveHouseIn==null?"0":waveHouseIn.FSPID, BasicShareUtil.getInstance(mContext).getIMIE());
                DataModel.codeOnlyInsert(WebApi.CodeCheckInsertForOutDB, gson.toJson(bean));
                //        Addorder();
            }

            @Override
            public void onError(Throwable e) {
//                super.onError(e);
                Toast.showText(mContext,"获取基本数量失败");
                LoadingUtil.dismiss();
            }
        });

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
//                        T_DetailDao.Properties.FStorageOut.eq(storageOut.FNumber),
//                        T_DetailDao.Properties.FWaveHouseOut.eq(waveHouseOut.FNumber),
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
            Lg.e("aa1111",activityPager.getOrgOut());
            Lg.e("aa2222",activityPager.getOrgIn());
            Lg.e("aa3333",activityPager.getHuozhuOut());
            Lg.e("aa4444",activityPager.getHuozhuIn());
            main.setData(Info.getType(activity),
                    activityPager.getOrgOut().FNumber, activityPager.getOrgIn().FNumber,
                    activityPager.getHuozhuOut().FNumber, activityPager.getHuozhuIn().FNumber);
//            main.FDepartmentNumber = spDepartmentCreate.getDataNumber();
//            main.FPurchaseDeptId = spDepartmentBuy.getDataNumber();
//            main.FPurchaserId = spBuyer.getDataNumber();
            main.FStockerNumber = activityPager.getManStore();
            main.FDate = activityPager.getDate();
            main.FNot = activityPager.getNote();
            main.FDBType = activityPager.getDBType();
            main.FDBDirection = activityPager.getDBDirection();
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
            detail.FStoreNum = getQtyMsg==null?"":getQtyMsg.FStoreQty;
            detail.FBaseNum = getQtyMsg==null?"":getQtyMsg.FBaseQty;
            detail.FProductNo = edPurchaseNo.getText().toString();
            detail.FBatch = edPihao.getText().toString();
            detail.AuxSign = spAuxsign.getDataNumber();
            detail.ActualModel = spActualmodel.getDataNumber();
            detail.setProduct(product);
            detail.model=tvModel.getText().toString();
            detail.setStorageOut(storageOut);
            detail.setWaveHouseOut(waveHouseOut);
            detail.setStorageIn(storageIn);
            detail.setWaveHouseIn(waveHouseIn);
            detail.setUnit(unit);
            detail.setBaseUnitName(getQtyMsg==null?"":getQtyMsg.FBaseUnitName);
            detail.setStoreUnitName(getQtyMsg==null?"":getQtyMsg.FStoreUnitName);
            long insert2 = t_detailDao.insert(detail);

            if (insert1 > 0 && insert2 > 0) {
                Lg.e("成功添加Main：", main);
                Lg.e("成功添加Detail：", detail);
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
//        activityPager.ReSetScan(cbScaning);
        listOrder.clear();
        barcode = "";
        edPihao.setText("");
        edNum.setText("");
        tvGoodName.setText("");
        tvModel.setText("");
        tvHuozhu.setText("");
        tvCeng.setText("");
        product = null;
        //判断是否锁住表头
        //判断是否有保存的业务单号，没有的话，锁定表头
//        if ("".equals(Hawk.get(Config.OrderNo+activityPager.getActivity(),""))){
            EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock));
//        }
    }

    //提交并且审核
    private void submitAndAudit(final String order){
        AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
        ab.setTitle("是否直接审核");
        ab.setMessage("确认？");
        ab.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LoadingUtil.showDialog(mContext,"正在审核...");
                final String json = Info.getJson(Config.DBActivity, JsonDealUtils.JSonDB_Check(order));
                App.CloudService().doIOAction(Config.C_Submit, json, new ToSubscribe<BackData>() {
                    @Override
                    public void onNext(BackData backData) {
                        super.onNext(backData);
                        if (backData.getResult().getResponseStatus().getIsSuccess()) {
                            Lg.e("提交成功");
                            App.CloudService().doIOAction(Config.C_Audit, json, new ToSubscribe<BackData>() {
                                @Override
                                public void onNext(BackData backData) {
                                    super.onNext(backData);
                                        LoadingUtil.dismiss();
                                    if (backData.getResult().getResponseStatus().getIsSuccess()) {
                                        Toast.showText(mContext,"审核成功");
                                        Lg.e("审核成功");
                                    }else{
                                        List<BackData.ResultBean.ResponseStatusBean.ErrorsBean> errorsBeans = backData.getResult().getResponseStatus().getErrors();
                                        StringBuilder builder = new StringBuilder();
                                        for (BackData.ResultBean.ResponseStatusBean.ErrorsBean error : errorsBeans) {
                                            builder.append(error.getFieldName() + "\n");
                                            builder.append(error.getMessage() + "\n");
                                        }
                                        LoadingUtil.showAlter(mContext,"",builder.toString());
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);
                                    LoadingUtil.dismiss();
                                    Lg.e("审核失败，请于系统中审核");
                                }
                            });
                        } else {
                            LoadingUtil.dismiss();
                            Toast.showText(mContext,"提交失败，请于系统中提交并审核");
                            Lg.e("提交失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        LoadingUtil.dismiss();
                        Toast.showText(mContext,"提交失败，请于系统中提交并审核");
                        Lg.e("审核失败");
                    }
                });
            }
        });
        ab.setNegativeButton("取消", null);
        final AlertDialog alertDialog = ab.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
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

    @OnClick({ R.id.search, R.id.btn_add, R.id.btn_finishorder, R.id.btn_backorder, R.id.btn_checkorder, R.id.btn_pic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search:
                if (lyScan.getVisibility()==View.VISIBLE){
                    lyScan.setVisibility(View.GONE);
//                    mCaptureManager.onPause();
                }else{
                    mCaptureManager.onResume();
                    lyScan.setVisibility(View.VISIBLE);
                    mCaptureManager.decode();
                }

//                Bundle bundle1 = new Bundle();
//                bundle1.putString("search", "");
//                bundle1.putInt("where", Info.SEARCHPRODUCT);
//                bundle1.putString("org", activityPager.getOrgOut(1));
//                bundle1.putInt("activity", activity);
//                startNewActivityForResult(activityPager, ProductSearchActivity.class, R.anim.activity_open, 0, Info.SEARCHFORRESULT, bundle1);
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
                startNewActivity(activityPager, ReViewP2Activity.class,
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
