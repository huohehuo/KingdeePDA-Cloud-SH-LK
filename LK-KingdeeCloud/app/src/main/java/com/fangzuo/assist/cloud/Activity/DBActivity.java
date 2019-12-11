package com.fangzuo.assist.cloud.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Beans.BackData;
import com.fangzuo.assist.cloud.Beans.CodeCheckBackDataBean;
import com.fangzuo.assist.cloud.Beans.CodeCheckBean;
import com.fangzuo.assist.cloud.Beans.CommonBean;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Dao.Org;
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
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.MathUtil;
import com.fangzuo.assist.cloud.Utils.MediaPlayer;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.UpLoadModel;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.fangzuo.assist.cloud.widget.MyWaveHouseSpinner;
import com.fangzuo.assist.cloud.widget.SpinnerActualModel;
import com.fangzuo.assist.cloud.widget.SpinnerAuxSign;
import com.fangzuo.assist.cloud.widget.SpinnerCommon;
import com.fangzuo.assist.cloud.widget.SpinnerHuozhu;
import com.fangzuo.assist.cloud.widget.SpinnerOrg;
import com.fangzuo.assist.cloud.widget.SpinnerStorage;
import com.fangzuo.assist.cloud.widget.SpinnerStoreMan;
import com.fangzuo.assist.cloud.widget.SpinnerUnit;
import com.fangzuo.assist.cloud.zxing.ScanManager;
import com.fangzuo.greendao.gen.T_DetailDao;
import com.fangzuo.greendao.gen.T_mainDao;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DBActivity extends BaseActivity {
    @BindView(R.id.ishebing)
    CheckBox ishebing;
    @BindView(R.id.isAutoAdd)
    CheckBox isAutoAdd;
    @BindView(R.id.zxing_barcode_scanner)
    DecoratedBarcodeView zxingBarcodeScanner;
    @BindView(R.id.ed_supplier)
    EditText edSupplier;
    @BindView(R.id.sp_which_storage_out)
    SpinnerStorage spWhichStorageOut;
    @BindView(R.id.sp_wavehouse_out)
    MyWaveHouseSpinner spWavehouseOut;
    @BindView(R.id.sp_which_storage_in)
    SpinnerStorage spWhichStorageIn;
    @BindView(R.id.sp_wavehouse_in)
    MyWaveHouseSpinner spWavehouseIn;
    @BindView(R.id.cb_scaning)
    CheckBox cbScaning;
    @BindView(R.id.scanbyCamera)
    RelativeLayout scanbyCamera;
    @BindView(R.id.ed_code)
    EditText edCode;
    @BindView(R.id.search)
    RelativeLayout search;
    @BindView(R.id.tv_goodName)
    TextView tvGoodName;
    @BindView(R.id.tv_storenum)
    TextView tvStorenum;
    @BindView(R.id.tv_model)
    TextView tvModel;
    @BindView(R.id.sp_unit)
    SpinnerUnit spUnit;
    @BindView(R.id.ed_pihao)
    EditText edPihao;
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
    @BindView(R.id.ll_btn)
    LinearLayout llBtn;
    @BindView(R.id.cb_isStorage)
    CheckBox cbIsStorage;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.sp_org_out)
    SpinnerOrg spOrgOut;
    @BindView(R.id.sp_org_in)
    SpinnerOrg spOrgIn;
    @BindView(R.id.sp_org_huozhu_out)
    SpinnerHuozhu spOrgHuozhuOut;
    @BindView(R.id.sp_org_huozhu_in)
    SpinnerHuozhu spOrgHuozhuIn;
    @BindView(R.id.sp_storeman)
    SpinnerStoreMan spStoreman;
    @BindView(R.id.ed_not)
    EditText edNot;
    @BindView(R.id.sp_db_type)
    SpinnerCommon spDbType;
    @BindView(R.id.sp_db_direction)
    SpinnerCommon spDbDirection;
    private int activity = Config.DBActivity;
    private long ordercode;
    private Product product;
    private Storage storageOut;
    private Storage storageIn;
    private WaveHouse waveHouseOut;
    private WaveHouse waveHouseIn;
    private Unit unit;
    private Org orgOut;
    private Org orgIn;
    private Org huozhuOut;
    private CommonBean commonBean;
    private boolean isTheSame = false;//用于判断是否为组织内调拨true
    private CodeCheckBackDataBean codeCheckBackDataBean;
    protected boolean isOpenBatch = false;
    private List<String> listOrder;
    private String autoAuxSing = "";
    private String autoActualModel = "";
    private String autoStorage = "";
    //    ActivityDbBinding bindings;

    @Override
    protected void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.ScanResult:
                BarcodeResult res = (BarcodeResult) event.postEvent;
                if (cbScaning.isChecked()) {
                } else {
                    mCaptureManager.onPause();
                    zxingBarcodeScanner.setVisibility(View.GONE);
                }
                OnReceive(res.getResult().getText());
//                Toast.showText(mContext, "扫描结果：" + res.getResult().getText());
                break;
            case EventBusInfoCode.Product:
                product = (Product) event.postEvent;
                Lg.e("获得物料信息：", product);
//                setProduct(product);
                dealProduct();
//                setDATA("", true);
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
                        App.getRService().doIOAction(WebApi.DBUpload, reString, new MySubscribe<CommonResponse>() {
                            @Override
                            public void onNext(CommonResponse commonResponse) {
                                super.onNext(commonResponse);
                                if (!commonResponse.state) return;
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

//                    t_detailDao.deleteInTx(t_detailDao.queryBuilder().where(
//                            T_DetailDao.Properties.Activity.eq(activity)
//                    ).build().list());
//                    t_mainDao.deleteInTx(t_mainDao.queryBuilder().where(
//                            T_mainDao.Properties.Activity.eq(activity)
//                    ).build().list());
                    Toast.showText(mContext, "回单成功");
//                btnBackorder.setClickable(true);
                    LoadingUtil.dismiss();
                    MediaPlayer.getInstance(mContext).ok();
                } else {
                    LoadingUtil.dismiss();
                    List<BackData.ResultBean.ResponseStatusBean.ErrorsBean> errorsBeans = backData.getResult().getResponseStatus().getErrors();
                    StringBuilder builder = new StringBuilder();
                    for (BackData.ResultBean.ResponseStatusBean.ErrorsBean error : errorsBeans) {
                        builder.append(error.getFieldName() + "\n");
                        builder.append(error.getMessage() + "\n");
                    }
                    AlertDialog.Builder delete = new AlertDialog.Builder(mContext);
                    delete.setTitle("回单错误");
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
            case EventBusInfoCode.Code_Check://条码检测
                LoadingUtil.dismiss();
                codeCheckBackDataBean = (CodeCheckBackDataBean) event.postEvent;
                if (codeCheckBackDataBean.FTip.equals("OK")) {
                    edPihao.setText(codeCheckBackDataBean.FBatchNo);
                    edPurchaseNo.setText(codeCheckBackDataBean.FPurchaseNo);
                    edNum.setText(codeCheckBackDataBean.FQty);
                    autoActualModel = codeCheckBackDataBean.FActualmodel;
                    autoAuxSing = codeCheckBackDataBean.FAuxsign;
                    autoStorage = codeCheckBackDataBean.FStockID;
                    LoadingUtil.showDialog(mContext, "正在查找物料信息");
                    DataModel.getProductForNumber(codeCheckBackDataBean.FItemID, orgOut);
                } else {
                    ReSetScan(cbScaning);
                    Toast.showText(mContext, codeCheckBackDataBean.FTip);
                }
                break;
            case EventBusInfoCode.Code_Only_Insert://写入条码唯一临时表
                codeCheckBackDataBean = (CodeCheckBackDataBean) event.postEvent;
                if (codeCheckBackDataBean.FTip.equals("OK")) {
                    Addorder();
                } else {
                    ReSetScan(cbScaning);
                    Toast.showText(mContext, codeCheckBackDataBean.FTip);
                }
                break;
        }
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_db);
        ButterKnife.bind(this);
        //摄像头初始化
        mCaptureManager = new ScanManager(this, zxingBarcodeScanner);
        mCaptureManager.initializeFromIntent(getIntent(), savedInstanceState);
    }

    @Override
    protected void initData() {
        listOrder = new ArrayList<>();
        ordercode = CommonUtil.createOrderCode(this);
        tvDate.setText(getTime(true));
        spDbType.setData(Info.Type_DB_type);
        spDbDirection.setData(Info.Type_DB_direction);
        //第一个参数用于保存上一个值，第二个为自动跳转到该默认值
        spOrgOut.setAutoSelection(getString(R.string.spOrgOut_db), Hawk.get(Info.user_org, ""));
        spOrgHuozhuOut.setAutoSelection(getString(R.string.spOrgHuozhuOut_db),orgOut, Hawk.get(Info.user_org, ""));
        spWhichStorageOut.setAuto("", orgOut);
        spStoreman.setAuto(getString(R.string.spStoreman_db), "", orgOut);
        spWhichStorageIn.setAuto("", orgOut);
        spUnit.setAuto("",SpinnerUnit.Id);

        spOrgIn.setAutoSelection(getString(R.string.spOrgIn_db), Hawk.get(Info.user_org, ""));
        spOrgHuozhuIn.setAutoSelection(getString(R.string.spOrgHuozhuIn_db),orgIn, Hawk.get(Info.user_org, ""));

//        spAuxsign.setEnabled(false);
//        spActualmodel.setEnabled(false);
        cbIsStorage.setChecked(Hawk.get(Info.Storage + activity, false));
    }

    @Override
    protected void initListener() {
        edPihao.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 0 && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    DataModel.getStoreNum(product, storageOut, edPihao.getText().toString().trim(), mContext, tvStorenum);
                }
                return true;
            }
        });
        spDbType.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                commonBean = (CommonBean) spDbType.getAdapter().getItem(i);
                Lg.e("调拨类型：", commonBean);
                if (commonBean.FNumber.equals("InnerOrgTransfer")) {
                    isTheSame = true;
                    spOrgIn.setEnable(false);
                    spOrgHuozhuIn.setEnable(false);
                } else {
                    spOrgIn.setEnable(true);
                    spOrgHuozhuIn.setEnable(true);
                    isTheSame = false;
                }
            }
        });
        spOrgOut.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                orgOut = (Org) spOrgOut.getAdapter().getItem(i);
                Lg.e("调出组织：", orgOut);
                storageOut = null;
                spStoreman.setAuto(getString(R.string.spStoreMan_pis), "", orgOut);
                spWhichStorageOut.setAuto("", orgOut);
                spOrgHuozhuOut.setAutoSelection("", orgOut,orgOut.FName);

                if (isTheSame) {
                    spOrgIn.setAutoSelection("", orgOut.FName);
                }
            }
        });
        spOrgHuozhuOut.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                huozhuOut = (Org) spOrgHuozhuOut.getAdapter().getItem(i);
                if (isTheSame) {
                    spOrgHuozhuIn.setAutoSelection("", orgIn,huozhuOut.FName);
                }
            }
        });
        spOrgIn.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                orgIn = (Org) spOrgIn.getAdapter().getItem(i);
                Lg.e("调入组织：", orgIn);
                storageIn = null;
                spWhichStorageIn.setAuto("", orgIn);
                if (isTheSame){//当为组织内调拨时，调出入货主必须相同
                    spOrgHuozhuIn.setAutoSelection("", orgIn,huozhuOut==null?"":huozhuOut.FName);
                }else{
                    spOrgHuozhuIn.setAutoSelection("", orgIn,orgIn.FName);
                }
            }
        });

        spWhichStorageOut.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                storageOut = (Storage) spWhichStorageOut.getAdapter().getItem(i);
                Lg.e("选中调出仓库：", storageOut);
                waveHouseOut = null;
                spWavehouseOut.setAuto(mContext, storageOut, "");
                DataModel.getStoreNum(product, storageOut, edPihao.getText().toString().trim(), mContext, tvStorenum);
            }
        });
        spWavehouseOut.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                waveHouseOut = (WaveHouse) spWavehouseOut.getAdapter().getItem(i);
                Lg.e("选中调出仓位：", waveHouseOut);
            }
        });

        spWhichStorageIn.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                storageIn = (Storage) spWhichStorageIn.getAdapter().getItem(i);
                Lg.e("选中调入仓库：", storageIn);
                waveHouseIn = null;
                spWavehouseIn.setAuto(mContext, storageIn, "");
//                DataModel.getStoreNum(product, storageIn, edPihao.getText().toString().trim(), mContext, tvStorenum);
            }
        });
        spWavehouseIn.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                waveHouseIn = (WaveHouse) spWavehouseIn.getAdapter().getItem(i);
                Lg.e("选中调入仓位：", waveHouseIn);
            }
        });

        spUnit.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                unit = (Unit) spUnit.getAdapter().getItem(i);
                Lg.e("单位：", unit);
            }
        });
    }

    String barcode = "";

    @Override
    protected void OnReceive(String code) {
        barcode = code;
        LoadingUtil.showDialog(mContext, "正在检测条码...");
        //查询条码唯一表
        CodeCheckBean bean = new CodeCheckBean(code);
        DataModel.codeCheck(WebApi.CodeCheckForOut, gson.toJson(bean));
    }

    //处理物料数据
    private void dealProduct() {
        if (product == null) {
            return;
        }
        //设置物料信息
        edCode.setText(product.FNumber);tvGoodName.setText(product.FName);tvModel.setText(product.FModel);
        //带出物料的默认值
        spUnit.setAuto(mContext, product.FPurchaseUnitID, SpinnerUnit.Id);
        if (cbIsStorage.isChecked()) {
            spWhichStorageOut.setAuto(autoStorage, orgOut);
        }
        if (CommonUtil.isOpen(product.FIsBatchManage)) {
            edPihao.setEnabled(true);
            isOpenBatch = true;
        } else {
            edPihao.setText("");
            edPihao.setEnabled(false);
            isOpenBatch = false;
        }
        DataModel.getStoreNum(product, storageOut, edPihao.getText().toString().trim(), mContext, tvStorenum);

        spAuxsign.getData(product.FMASTERID, autoAuxSing);
        spActualmodel.getData(product.FMASTERID, autoActualModel);


        if (isAutoAdd.isChecked()) {
            if (!checkBeforeAdd()) {
                ReSetScan(cbScaning);
            }
        } else {
            ReSetScan(cbScaning);
        }
    }


    @OnClick({R.id.scanbyCamera, R.id.search, R.id.tv_date, R.id.btn_add, R.id.btn_finishorder, R.id.btn_backorder, R.id.btn_checkorder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.scanbyCamera:
                mCaptureManager.onResume();
                zxingBarcodeScanner.setVisibility(View.VISIBLE);
                mCaptureManager.decode();
                break;
            case R.id.search:
                Bundle bundle1 = new Bundle();
                bundle1.putString("search", edCode.getText().toString());
                bundle1.putInt("where", Info.SEARCHPRODUCT);
                bundle1.putString("org", orgOut == null ? "" : orgOut.FOrgID);
                bundle1.putInt("activity", activity);
                startNewActivityForResult(ProductSearchActivity.class, R.anim.activity_open, 0, Info.SEARCHFORRESULT, bundle1);
                break;
            case R.id.btn_add:
                checkBeforeAdd();
                break;
            case R.id.btn_finishorder:
                finishOrder();
                break;
            case R.id.btn_backorder:
                LoadingUtil.showDialog(mContext, "正在回单...");
                UpLoadModel.action(mContext, activity);
                break;
            case R.id.btn_checkorder:
                Bundle bundle = new Bundle();
                bundle.putInt("activity", activity);
                startNewActivity(ReViewActivity.class,
                        R.anim.activity_fade_in, R.anim.activity_fade_out, false, bundle);
                break;
            case R.id.tv_date:
                datePicker(tvDate);
                break;
        }
    }

    //添加前检测
    private boolean checkBeforeAdd() {
        try {
            if (product == null) {
                Toast.showText(mContext, "请选择物料");
                MediaPlayer.getInstance(mContext).error();
                return false;
            }
            if (storageOut == null || storageIn == null) {
                Toast.showText(mContext, "请选择仓库");
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
            }//--------------------------------------------------
            if (edNum.getText().toString().trim().equals("") || "0".equals(edNum.getText().toString())) {
                Toast.showText(mContext, "请输入数量");
                MediaPlayer.getInstance(mContext).error();
                return false;
            }


            if (null==huozhuOut || "".equals(spOrgHuozhuIn.getDataNumber())){
                Toast.showText(mContext, "调出货主或调入货主不能为空，请检查...");
                MediaPlayer.getInstance(mContext).error();
                return false;
            }
            if (!huozhuOut.FName.equals(spOrgHuozhuIn.getDataName())){
                Toast.showText(mContext, "组织内调拨时，调出调入货主必须一样");
                MediaPlayer.getInstance(mContext).error();
                return false;
            }
            //当为跨组织调拨时，
            if ("OverOrgTransfer".equals(spDbType.getDataNumber())) {
                if (orgOut.FName.equals(orgIn.FName)) {
                    if (huozhuOut != null && huozhuOut.FNumber.equals(spOrgHuozhuIn.getDataNumber())) {
                        Toast.showText(mContext, "相同组织时，货主不能相同");
                        MediaPlayer.getInstance(mContext).error();
                        return false;
                    }
                }
            }

            //--------------------------------------------------
//        if ("".equals(edSupplier.getText().toString())){
//            Toast.showText(mContext,"请选择供应商");
//            MediaPlayer.getInstance(mContext).error();
//            return false;
//        }else if (null==supplier && !"".equals(edSupplier.getText().toString())){//当未选中供应商，但输入框有数据时
//            List<Suppliers> list = GreenDaoManager.getmInstance(mContext).getDaoSession().getSuppliersDao().queryBuilder().where(
//                    SuppliersDao.Properties.FName.eq(edSupplier.getText().toString())
//            ).build().list();
//            if (list.size()>0){
//                supplier = list.get(0);
//            }else{
//                Toast.showText(mContext,"无法找到相对应的供应商信息，请重试");
//                MediaPlayer.getInstance(mContext).error();
//                return false;
//            }
//        }//--------------------------------------------------

            //插入条码唯一临时表
            CodeCheckBean bean = new CodeCheckBean(barcode, ordercode + "", BasicShareUtil.getInstance(mContext).getIMIE());
            DataModel.codeOnlyInsert(WebApi.CodeCheckInsertForOut, gson.toJson(bean));
            return true;
//            Addorder();
        } catch (Exception e) {
            return false;
        }
    }

    //添加数据
    private void Addorder() {
        try {
            String num = edNum.getText().toString();
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
            String timesecond = getTimesecond();
            T_main main = new T_main();//--------------------------------------表头-----------------
            main.activity = activity;
            main.FBillerID = Hawk.get(Info.user_id, "");
            main.FBarcode = barcode;
            main.IMIE = BasicShareUtil.getInstance(mContext).getIMIE();
            main.FOrderId = ordercode;
            main.FIndex = timesecond;
            main.setData(Info.getType(activity), orgOut == null ? "" : orgOut.FNumber,
                    orgIn == null ? "" : orgIn.FNumber, huozhuOut == null ? "" : huozhuOut.FNumber, spOrgHuozhuIn.getDataNumber());
//            main.FDepartmentNumber = spDepartmentCreate.getDataNumber();
//            main.FPurchaseDeptId = spDepartmentBuy.getDataNumber();
//            main.FPurchaserId = spBuyer.getDataNumber();
            main.FStockerNumber = spStoreman.getDataNumber();
            main.FDate = tvDate.getText().toString();
            main.FNot = edNot.getText().toString();
            main.FDBType = commonBean.FNumber;
            main.FDBDirection = spDbDirection.getDataNumber();
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
            detail.FProductNo = edPurchaseNo.getText().toString();
            detail.FBatch = edPihao.getText().toString();
            detail.AuxSign = spAuxsign.getDataNumber();
            detail.ActualModel = spActualmodel.getDataNumber();
            detail.setProduct(product);
            detail.setStorageOut(storageOut);
            detail.setWaveHouseOut(waveHouseOut);
            detail.setStorageIn(storageIn);
            detail.setWaveHouseIn(waveHouseIn);
            detail.setUnit(unit);
            long insert2 = t_detailDao.insert(detail);

            if (insert1 > 0 && insert2 > 0) {
                Lg.e("成功添加：" + main.toString());
                Lg.e("成功添加：" + detail.toString());
                MediaPlayer.getInstance(mContext).ok();
                Toast.showText(mContext, "添加成功");
                resetAll();
            } else {
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
            DataService.pushError(mContext, this.getClass().getSimpleName(), e);
        }

    }

    private void resetAll() {
        ReSetScan(cbScaning);
//        listOrder.clear();
//        barcode = "";
//        edPihao.setText("");
//        edNum.setText("");
//        product = null;
//        setProduct(new Product());

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
                share.setOrderCode(DBActivity.this, ordercode);
            }
        });
        ab.setNegativeButton("取消", null);
        ab.create().show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Hawk.put(Info.Storage + activity, cbIsStorage.isChecked());
        mCaptureManager.onDestroy();
    }


    @Override
    public void onBackPressed() {
        if (zxingBarcodeScanner.getVisibility() == View.VISIBLE) {
            zxingBarcodeScanner.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected boolean isScan() {
        return true;
    }


}
