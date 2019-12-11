package com.fangzuo.assist.cloud.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Adapter.PDListAdapter;
import com.fangzuo.assist.cloud.Adapter.PDMainSpAdapter;
import com.fangzuo.assist.cloud.Adapter.PiciSpForSubAdapter;
import com.fangzuo.assist.cloud.Adapter.ProductselectAdapter;
import com.fangzuo.assist.cloud.Adapter.ProductselectAdapter1;
import com.fangzuo.assist.cloud.Adapter.StorageSpAdapter;
import com.fangzuo.assist.cloud.Adapter.UnitSpAdapter;
import com.fangzuo.assist.cloud.Beans.BackData;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.PDListReturnBean;
import com.fangzuo.assist.cloud.Beans.PDSubRequestBean;
import com.fangzuo.assist.cloud.Beans.PDsubReturnBean;
import com.fangzuo.assist.cloud.Dao.BarCode;
import com.fangzuo.assist.cloud.Dao.PDMain;
import com.fangzuo.assist.cloud.Dao.PDSub;
import com.fangzuo.assist.cloud.Dao.Product;
import com.fangzuo.assist.cloud.Dao.Storage;
import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.Dao.Unit;
import com.fangzuo.assist.cloud.Dao.WaveHouse;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Service.DataService;
import com.fangzuo.assist.cloud.Utils.Asynchttp;
import com.fangzuo.assist.cloud.Utils.BasicShareUtil;
import com.fangzuo.assist.cloud.Utils.CommonMethod;
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
import com.fangzuo.assist.cloud.databinding.ActivityPdBinding;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.fangzuo.assist.cloud.widget.SpinnerUnit;
import com.fangzuo.assist.cloud.zxing.ScanManager;
import com.fangzuo.greendao.gen.BarCodeDao;
import com.fangzuo.greendao.gen.PDMainDao;
import com.fangzuo.greendao.gen.PDSubDao;
import com.fangzuo.greendao.gen.ProductDao;
import com.fangzuo.greendao.gen.T_DetailDao;
import com.google.gson.Gson;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.loopj.android.http.AsyncHttpClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

//import com.fangzuo.assist.zxing.activity.CaptureActivity;

public class PDActivity extends BaseActivity {
    ActivityPdBinding binding;
    private int activity = Config.PDActivity;
    private List<PDMain> mainContainer;
    private List<Boolean> isCheck;
    private PDListAdapter pdListAdapter;
    private ArrayList<String> choice;
//    private ProgressDialog pg;
    private CommonMethod method;
    private PDMainSpAdapter pdMainSpAdapter;
    private String fid;
    private List<Product> products;
    private UnitSpAdapter unitAdapter;
    private String fprocessID;
    private StorageSpAdapter storageAdapter;
    private PDSub pdsubChoice;
    private Storage storage;
    private WaveHouse waveHouse;
    private Unit unit;
    private PDSubDao pdSubDao;
    private ProductselectAdapter productselectAdapter;
    private ProductselectAdapter1 productselectAdapter1;
    private Product product;
    private String default_unitID;
    private boolean fBatchManager;
    private String FInStoreDate;
    private String FSpplierID;
    private String FInStoreOrderID;
//    private boolean isClear = false;
    private PDMain pdMain;
    private List<PDMain> choiceContainer;
//    private boolean isHebing = true;
//    private boolean isAuto;

    private String pihao = "";
    private PiciSpForSubAdapter piciSpAdapter;
    private PDSub pdSub;
    private String wavehouseAutoString = "";
    protected boolean isOpenBatch=false;

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }
    @Override
    protected boolean isScan() {
        return true;
    }
    @Override
    protected void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.ScanResult:
                BarcodeResult res = (BarcodeResult) event.postEvent;
                if (binding.cbScaning.isChecked()){
                }else{
                    mCaptureManager.onPause();
                    binding.zxingBarcodeScanner.setVisibility(View.GONE);
                }
                OnReceive(res.getResult().getText());
//                Toast.showText(mContext, "扫描结果：" + res.getResult().getText());
                break;
            case EventBusInfoCode.Product:
                product = (Product) event.postEvent;
                binding.setProduct(product);
                getSubQty();
                dealProduct();
//                setDATA("", true);
                break;
            case EventBusInfoCode.Upload_OK://回单成功
                BackData backData = (BackData)event.postEvent;
                if (backData.getResult().getResponseStatus().getIsSuccess()){
                    t_detailDao.deleteInTx(t_detailDao.queryBuilder().where(
                            T_DetailDao.Properties.Activity.eq(activity)
                    ).build().list());

                    Toast.showText(mContext, "回单成功");
                    MediaPlayer.getInstance(mContext).ok();
                    binding.btnBackorder.setClickable(true);
                    LoadingUtil.dismiss();
                }else{
                    LoadingUtil.dismiss();
                    List<BackData.ResultBean.ResponseStatusBean.ErrorsBean> errorsBeans = backData.getResult().getResponseStatus().getErrors();
                    StringBuilder builder =new StringBuilder();
                    for (BackData.ResultBean.ResponseStatusBean.ErrorsBean error :errorsBeans) {
                        builder.append(error.getFieldName()+"\n");
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
                String error = (String)event.postEvent;
                MediaPlayer.getInstance(mContext).error();
                binding.btnBackorder.setClickable(true);
                Toast.showText(mContext, error);
                LoadingUtil.dismiss();
                break;
        }
    }

    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pd);
        ButterKnife.bind(this);
        pdSubDao = daoSession.getPDSubDao();
        //摄像头初始化
        mCaptureManager = new ScanManager(this, binding.zxingBarcodeScanner);
        mCaptureManager.initializeFromIntent(getIntent(), savedInstanceState);
    }

    @Override
    protected void initData() {
        method = CommonMethod.getMethod(mContext);
        mainContainer = new ArrayList<>();
        isCheck = new ArrayList<>();
        choice = new ArrayList<>();
        choiceContainer = new ArrayList<>();
        pdListAdapter = new PDListAdapter(mContext, mainContainer, isCheck);
        binding.lvPdlist.setAdapter(pdListAdapter);
        binding.spWhichStorage.setAutoSelection(getString(R.string.spStorage_pd), "");
        pdMainSpAdapter = method.getpdmain(binding.spPdplan);
        getList();
        setfocus(binding.spPdplan);
    }

    @Override
    protected void initListener() {
        binding.btnBackorder.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (DataModel.checkHasDetail(mContext, activity)) {
                    binding.btnBackorder.setClickable(false);
                    LoadingUtil.show(mContext, "正在回单...");
                    UpLoadModel.action(mContext,activity);
                } else {
                    Toast.showText(mContext, "无单据信息");
                }
            }
        });
        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.refresh.setRefreshing(true);
                getList();
                binding.refresh.setRefreshing(false);
                binding.refresh.setRefreshing(false);
            }
        });
        binding.edCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 0 && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
//                    setDATA(binding.edCode.getText().toString(), false);
//                    setfocus(binding.edCode);
                }
                return true;
            }
        });
        binding.spWhichStorage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                isSpStorageDefault = false;
                storage = (Storage) binding.spWhichStorage.getAdapter().getItem(i);
                Lg.e("选中仓库：" + storage.toString());
                waveHouse = null;
                binding.spWavehouse.setAuto(mContext, storage, "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.spWavehouse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                waveHouse = (WaveHouse) binding.spWavehouse.getAdapter().getItem(i);
                Lg.e("选中仓位：" + waveHouse.toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.spUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                unit = (Unit) binding.spUnit.getAdapter().getItem(i);
                Lg.e("单位："+gson.toJson(unit));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.lvPdlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (isCheck.get(i)) {
                    isCheck.set(i, false);
                    Log.e("isCheck", isCheck.get(i) + "");
                    for (int j = 0; j < choice.size(); j++) {
                        if (choice.get(j).equals(mainContainer.get(i).FID)) {
                            choice.remove(j);
                            choiceContainer.remove(j);
                        }
                    }
                } else {
                    isCheck.set(i, true);
                    Log.e("isCheck", isCheck.get(i) + "");
                    choice.add(mainContainer.get(i).FID);
                    choiceContainer.add(mainContainer.get(i));

                }
                getSubQty();
                pdListAdapter.notifyDataSetChanged();
            }
        });
        binding.spPdplan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pdMain = (PDMain) pdMainSpAdapter.getItem(i);
                fid = pdMain.FID;
                fprocessID = pdMain.FSchemeNo;
//                if (product != null && fid != null) {
//                    final List<PDSub> pdSubs = pdSubDao.queryBuilder().where(
//                            PDSubDao.Properties.FID.eq(fid),
//                            PDSubDao.Properties.FItemID.eq(product.FItemID)
//                    ).build().list();
//                    tvYtnum.setText(pdSubs.get(0).FCheckQty);
//                }
                Log.e("fprocessID", fprocessID);
                Lg.e("盘点方案：",pdMain);
                getSubQty();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void OnReceive(String code) {
        DataModel.getProductForScan(code,null);
    }
    private void getSubQty() {
        if (fid == null || product == null || storage == null) {
            Lg.e("getSubQty:查询数据不完整");
            return;
        }
        final List<PDSub> pdSubs = pdSubDao.queryBuilder().where(
                PDSubDao.Properties.FID.eq(fid),
                PDSubDao.Properties.FMaterialID.eq(product.FMaterialid),
                PDSubDao.Properties.FStockID.eq(storage.FItemID),
                PDSubDao.Properties.FStockPlaceID.eq(binding.spWavehouse.getWaveHouseId()),
                PDSubDao.Properties.FLot_Text.eq(binding.edPihao.getText().toString())
        ).build().list();
        Lg.e("getSubQty:" + pdSubs.toString());
        if (pdSubs.size() > 0) {
            pdsubChoice = pdSubs.get(0);
            binding.tvNuming.setText(pdSubs.get(0).FCountQty);
            binding.tvStorenum.setText(pdSubs.get(0).FAcctQty);
        } else {
            binding.tvNuming.setText("0");
            binding.tvStorenum.setText("0");
        }

    }
    //获取盘点方案list
    private void getList() {
        Asynchttp.post(mContext, getBaseUrl() + WebApi.PDMAINLIST, "", new Asynchttp.Response() {
            @Override
            public void onSucceed(CommonResponse cBean, AsyncHttpClient client) {
                mainContainer.clear();
                isCheck.clear();
                choice.clear();
                PDListReturnBean pBean = gson.fromJson(cBean.returnJson, PDListReturnBean.class);
                if (pBean.items != null) {
                    mainContainer.addAll(pBean.items);
                    for (int i = 0; i < mainContainer.size(); i++) {
                        isCheck.add(false);
                        Log.e("isCheck", isCheck.size() + "");
                    }
                }
                pdListAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailed(String Msg, AsyncHttpClient client) {
                Toast.showText(mContext, Msg);
            }
        });
    }


    @OnClick({R.id.btn_delete, R.id.scanbyCamera, R.id.search, R.id.btn_add, R.id.btn_checkorder, R.id.btn_downloadall, R.id.btn_downloadchoosen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_delete:
                final PDMainDao pdMainDao = daoSession.getPDMainDao();
                if (fid != null) {
                    final PDMain pdMains = pdMainDao.queryBuilder().where(PDMainDao.Properties.FID.eq(fid)).build().unique();
                    AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                    ab.setTitle("删除此计划么");
                    ab.setMessage("确认删除盘点计划?");
                    ab.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            pdSubDao = daoSession.getPDSubDao();
                            Log.e("deleteFID", pdMain.FID);
                            List<PDSub> list = pdSubDao.queryBuilder().where(
                                    PDSubDao.Properties.FID.eq(pdMain.FID)
                            ).build().list();
                            for (int j = 0; j < list.size(); j++) {
                                pdSubDao.delete(list.get(j));
                            }
                            if (pdMains != null) {
                                pdMainDao.delete(pdMains);
                            }
                            method.getpdmain(binding.spPdplan);
                            Toast.showText(mContext, "删除成功");
//                            startNewActivity(MiddleActivity.class, R.anim.activity_fade_in, R.anim.activity_fade_out, true, null);
                        }
                    }).setNegativeButton("取消", null).create().show();
                }
                break;
            case R.id.scanbyCamera:
                mCaptureManager.onResume();
                binding.zxingBarcodeScanner.setVisibility(View.VISIBLE);
                mCaptureManager.decode();
//                Intent in = new Intent(mContext, CaptureActivity.class);
//                startActivityForResult(in, 0);
                break;
            case R.id.search:
                Log.e("search", "onclick");
                Bundle b1 = new Bundle();
                b1.putString("search", binding.edCode.getText().toString());
                b1.putInt("where", Info.SEARCHPRODUCT);
                startNewActivityForResult(ProductSearchActivity.class, R.anim.activity_open, 0, Info.SEARCHFORRESULT, b1);
                break;
            case R.id.btn_add:
                AddOrder();
                break;
            case R.id.btn_checkorder:
                Bundle b2 = new Bundle();
                b2.putInt("activity", activity);
                startNewActivity(ReViewActivity.class, R.anim.activity_fade_in, R.anim.activity_fade_out, false, b2);
                break;
            case R.id.btn_downloadall:
                if (null!=mainContainer && mainContainer.size()>0){
                    choice.clear();
                    for (int i = 0; i < mainContainer.size(); i++) {
                        choice.add(mainContainer.get(i).FID);
                    }
                    PDSubRequestBean pdSubRequestBean = new PDSubRequestBean();
                    pdSubRequestBean.isClear = binding.cbIsClear.isChecked();
                    pdSubRequestBean.Fid = choice;
                    download(gson.toJson(pdSubRequestBean), true);
                }else{
                    Toast.showText(mContext,"无法找到盘点方案");
                }

                break;
            case R.id.btn_downloadchoosen:
                if (choice.size() < 1) {
                    Toast.showText(mContext, "没有选择盘点方案");
                } else {
                    PDSubRequestBean pdSubRequestBean1 = new PDSubRequestBean();
                    pdSubRequestBean1.Fid = choice;
                    pdSubRequestBean1.isClear = binding.cbIsClear.isChecked();
                    download(gson.toJson(pdSubRequestBean1), false);
                }
                break;
        }
    }

    private void AddOrder() {
        try {
            String num = binding.edNum.getText().toString();
            if (binding.edCode.getText().toString().equals("")) {
                Toast.showText(mContext, "请输入物料编号");
                MediaPlayer.getInstance(mContext).error();
                return;
            }
            if (binding.edNum.getText().toString().equals("0") ||
                    binding.edNum.getText().toString().equals("")) {
                Toast.showText(mContext, "请输入盘点数量");
                MediaPlayer.getInstance(mContext).error();
                return;
            }
            if (isOpenBatch && binding.edPihao.getText().toString().equals("")) {
                Toast.showText(mContext, "请输入批号");
                MediaPlayer.getInstance(mContext).error();
                return;
            }
                T_DetailDao t_detailDao = daoSession.getT_DetailDao();
//                Lg.e("AddOrder:" + pdsubChoice.toString());
//                Lg.e("pihao:" + pihao);
            Lg.e("添加合并：",binding.ishebing.isChecked());
                    Lg.e("添加合并：",product.FMaterialid);
                    Lg.e("添加合并：",binding.edPihao.getText().toString());
                    Lg.e("添加合并：",binding.edPihao.getText().toString());
                    Lg.e("添加合并：",binding.spUnit.getDataId());
                    Lg.e("添加合并：",storage.FItemID);
                    Lg.e("添加合并：",binding.spWavehouse.getWaveHouseId());
                if (binding.ishebing.isChecked()) {
                    List<T_Detail> detailhebing = t_detailDao.queryBuilder().where(
                            T_DetailDao.Properties.Activity.eq(activity),
                            T_DetailDao.Properties.FMaterialIdForPD.eq(product.FMaterialid),
                            T_DetailDao.Properties.FBatch.eq(binding.edPihao.getText().toString()),
                            T_DetailDao.Properties.FUnitIDForPD.eq(binding.spUnit.getDataId()),
                            T_DetailDao.Properties.FStoragePDId.eq(storage.FItemID),
                            T_DetailDao.Properties.FWaveHousePDId.eq(binding.spWavehouse.getWaveHouseId())
                    ).build().list();
                    if (detailhebing.size() > 0) {
                        Lg.e("存在合并：",detailhebing.size());
                        Lg.e("存在合并：",detailhebing.get(0));
                        for (int i = 0; i < detailhebing.size(); i++) {
                            num = (MathUtil.toD(num) + MathUtil.toD(detailhebing.get(i).FCheckQtyDefault)) + "";
                            t_detailDao.delete(detailhebing.get(i));
                        }
                    }else{
                        Lg.e("不存在合并");
                    }
                }
                String timesecond = getTimesecond();
                T_Detail detail = new T_Detail();//--------------------------------明细-----------------
                detail.activity = activity;
//                detail.FOrderId = ordercode;
                detail.FIndex = timesecond;
                detail.FBillTypeID = Info.BT_PD;
                detail.FRemainInStockQty = num;
                detail.FCheckQtyDefault = num;
                detail.FEntryID = fid;
                detail.FBatch = binding.edPihao.getText().toString();
                detail.setProduct(product);
                detail.setStorage(storage);
                detail.setWaveHouse(waveHouse);
                detail.setUnit(unit);
                Lg.e("添加：",detail);
                long insert = t_detailDao.insert(detail);


                if (insert > 0) {
                    MediaPlayer.getInstance(mContext).ok();
                    Lg.e("盘点明细sub：",pdsubChoice);
                    Toast.showText(mContext, "添加成功");
                    if (pdsubChoice != null) {
                        pdsubChoice.FCountQty = (MathUtil.toD(pdsubChoice.FCountQty) + MathUtil.toD(num)) + "";
                        pdSubDao.update(pdsubChoice);
                        ResetAll();
                    } else {
                        PDSub pdSub = new PDSub();
                        pdSub.FMaterialID = product.FMaterialid;
                        pdSub.FID = fid;
                        pdSub.FAcctQty = "0";
                        pdSub.FCountQty = binding.edNum.getText().toString();
                        pdSub.FStockPlaceID = binding.spWavehouse.getWaveHouseId();
                        pdSub.FStockID = storage.FItemID;
                        pdSub.FLot_Text = binding.edPihao.getText().toString();
                        Lg.e("盘点明细sub_add：",pdSub);
                        pdSubDao.insert(pdSub);
                        ResetAll();
                    }
                } else {
                    MediaPlayer.getInstance(mContext).error();
                    Toast.showText(mContext, "添加失败");
                }
        } catch (Exception e) {
            DataService.pushError(mContext, this.getClass().getSimpleName(), e);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("code", requestCode + "" + "    " + resultCode);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Bundle b = data.getExtras();
                String message = b.getString("result");
                OnReceive(message);
//                edCode.setText(message);
//                Toast.showText(mContext, message);
//                edCode.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
            }
        }
    }


    //处理物料数据
    private void dealProduct(){
        try{
            if (product==null){
                return;
            }
            //带出物料的默认值
            binding.spUnit.setAuto(mContext, product.FPurchaseUnitID, SpinnerUnit.Id);
            binding.spWhichStorage.setAutoSelection("",product.FStockID);

            if (CommonUtil.isOpen(product.FIsBatchManage)){
                isOpenBatch=true;
            }else{
                binding.edPihao.setText("");
                isOpenBatch=false;
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (fid != null) {
                        final List<PDSub> pdSubs = pdSubDao.queryBuilder().where(
                                PDSubDao.Properties.FID.eq(fid),
                                PDSubDao.Properties.FMaterialID.eq(product.FMaterialid),
                                PDSubDao.Properties.FStockID.eq(storage.FItemID)
                        ).build().list();
                        if (pdSubs.size() > 0) {
//                        if (pdSubs.size() == 1) {
                            pdsubChoice = pdSubs.get(0);
//                            getPici();
//                            edPihao.setText(pdsubChoice.FBatchNo);
                            binding.tvNuming.setText(pdsubChoice.FCountQty);
                            binding.tvStorenum.setText(pdsubChoice.FAcctQty);
                            if (binding.isAutoAdd.isChecked()) {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        binding.edNum.setText("1.0");
                                        AddOrder();
                                    }
                                }, 100);

                            }
//                        } else {
//
//                            ////////////////////////////////////////////////////////////////
//                            AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
//                            ab.setTitle("请选择盘点明细");
//                            View v = LayoutInflater.from(mContext).inflate(R.layout.pd_alert, null);
//                            ListView lv = v.findViewById(R.id.lv_alert);
//                            AlertLvAdapter alertLvAdapter = new AlertLvAdapter(mContext, pdSubs);
//                            lv.setAdapter(alertLvAdapter);
//                            ab.setView(v);
//                            final AlertDialog alertDialog = ab.create();
//                            alertDialog.show();
//                            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                    pdsubChoice = pdSubs.get(i);
////                                    edPihao.setText(pdsubChoice.FBatchNo);
//                                    for (int j = 0; j < storageAdapter.getCount(); j++) {
//                                        Storage storage = (Storage) storageAdapter.getItem(j);
//                                        if (storage.FItemID.equals(pdsubChoice.FStockID)) {
//                                            spStorage.setSelection(j);
//                                        }
//                                    }
//                                    if (!pdsubChoice.FStockPlaceID.equals("0")) {
//                                        for (int j = 0; j < waveHouseAdapter.getCount(); j++) {
//                                            WaveHouse waveHouse = (WaveHouse) waveHouseAdapter.getItem(j);
//                                            if (waveHouse.FSPID.equals(pdsubChoice.FStockPlaceID)) {
//                                                spWavehouse.setSelection(j);
//                                            }
//                                        }
//                                    }
//                                    tvYtnum.setText(pdsubChoice.FCheckQty);
//                                    tvNumOnServer.setText(pdsubChoice.FQty);
//                                    if (isAuto) {
//                                        edPdnum.setText("1.0");
//                                        AddOrder();
//                                    }
//                                    alertDialog.dismiss();
//                                }
//                            });
//                        }


                        } else {
                            Toast.showText(mContext, "未找到盘点明细");
//                        if (isAuto && fid != null) {
//                            edPdnum.setText("1.0");
//                            AddOrder();
//                        }
                        }
                    } else {
                        Toast.showText(mContext, "请下载盘点方案");
                    }

                }
            }, 100);
        } catch (Exception e) {
            DataService.pushError(mContext, this.getClass().getSimpleName(), e);
        }

    }
    private void setDATA(String fnumber, boolean flag) {
//        Log.e(TAG,"setDATA--product:"+product.toString()+" flag:"+flag);
        default_unitID = null;
//        edPihao.setText("");
        binding.edCode.setText(fnumber);
        if (flag) {
            default_unitID = product.FAuxUnitID;
            tvorIsAuto(product);
        } else {
            if (BasicShareUtil.getInstance(mContext).getIsOL()) {
                Asynchttp.post(mContext, getBaseUrl() + WebApi.SEARCHPRODUCTS, fnumber, new Asynchttp.Response() {
                    @Override
                    public void onSucceed(CommonResponse cBean, AsyncHttpClient client) {
                        final DownloadReturnBean dBean = new Gson().fromJson(cBean.returnJson, DownloadReturnBean.class);
                        if (dBean.products.size() == 1) {
                            getProductOL(dBean, 0);
                            default_unitID = dBean.products.get(0).FAuxUnitID;
//                            chooseUnit(default_unitID);
                        } else if (dBean.products.size() > 1) {
                            AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                            ab.setTitle("请选择物料");
                            View v = LayoutInflater.from(mContext).inflate(R.layout.pd_alert, null);
                            ListView lv = v.findViewById(R.id.lv_alert);
                            productselectAdapter1 = new ProductselectAdapter1(mContext, dBean.products);
                            lv.setAdapter(productselectAdapter);
                            ab.setView(v);
                            final AlertDialog alertDialog = ab.create();
                            alertDialog.show();
                            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    getProductOL(dBean, i);
                                    default_unitID = dBean.products.get(i).FAuxUnitID;
//                                    chooseUnit(default_unitID);
                                    alertDialog.dismiss();
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailed(String Msg, AsyncHttpClient client) {
                        Toast.showText(mContext, Msg);
                    }
                });
            } else {
                final ProductDao productDao = daoSession.getProductDao();
                BarCodeDao barCodeDao = daoSession.getBarCodeDao();
                final List<BarCode> barCodes = barCodeDao.queryBuilder().where(BarCodeDao.Properties.FBarCode.eq(fnumber)).build().list();
                if (barCodes.size() > 0) {
                    if (barCodes.size() == 1) {
                        products = productDao.queryBuilder().where(ProductDao.Properties.FMaterialid.eq(barCodes.get(0).FItemID)).build().list();
                        default_unitID = barCodes.get(0).FUnitID;
                        product = products.get(0);
                        tvorIsAuto(product);

                    } else {
                        AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                        ab.setTitle("请选择物料");
                        View v = LayoutInflater.from(mContext).inflate(R.layout.pd_alert, null);
                        ListView lv = v.findViewById(R.id.lv_alert);
                        productselectAdapter = new ProductselectAdapter(mContext, barCodes);
                        lv.setAdapter(productselectAdapter);
                        ab.setView(v);
                        final AlertDialog alertDialog = ab.create();
                        alertDialog.show();
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                BarCode barCode = (BarCode) productselectAdapter.getItem(i);
                                products = productDao.queryBuilder().where(
                                        ProductDao.Properties.FMaterialid.eq(barCode.FItemID)
                                ).build().list();
                                default_unitID = barCode.FUnitID;
//                                chooseUnit(default_unitID);
                                product = products.get(0);
                                tvorIsAuto(product);
                                alertDialog.dismiss();
                            }
                        });

                    }
                } else {
                    Toast.showText(mContext, "未找到物料");
                    MediaPlayer.getInstance(mContext).error();
                    binding.edCode.setText("");
                }
            }

        }

    }



    private void tvorIsAuto(final Product product) {
        try {
            binding.edCode.setText(product.FNumber);
            binding.tvGoodName.setText(product.FName);
            pdSubDao = daoSession.getPDSubDao();
            wavehouseAutoString = product.FStockPlaceID;
            if ((product.FIsBatchManage) != null && (product.FIsBatchManage).equals("1")) {
                fBatchManager = true;
//            setfocus(edPihao);
//            setfocus(edPihao);
//            edPihao.setEnabled(true);
            } else {
//            edPihao.setEnabled(false);
                fBatchManager = false;
            }

            binding.spUnit.setAuto(mContext, default_unitID, SpinnerUnit.Id);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (fid != null) {
                        final List<PDSub> pdSubs = pdSubDao.queryBuilder().where(
                                PDSubDao.Properties.FID.eq(fid),
                                PDSubDao.Properties.FMaterialID.eq(product.FMaterialid),
                                PDSubDao.Properties.FStockID.eq(storage.FItemID)
                        ).build().list();
                        if (pdSubs.size() > 0) {
//                        if (pdSubs.size() == 1) {
                            pdsubChoice = pdSubs.get(0);
//                            getPici();
//                            edPihao.setText(pdsubChoice.FBatchNo);
                            binding.tvNuming.setText(pdsubChoice.FCountQty);
                            binding.tvStorenum.setText(pdsubChoice.FAcctQty);
                            if (binding.isAutoAdd.isChecked()) {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        binding.edNum.setText("1.0");
                                        AddOrder();
                                    }
                                }, 100);

                            }
//                        } else {
//
//                            ////////////////////////////////////////////////////////////////
//                            AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
//                            ab.setTitle("请选择盘点明细");
//                            View v = LayoutInflater.from(mContext).inflate(R.layout.pd_alert, null);
//                            ListView lv = v.findViewById(R.id.lv_alert);
//                            AlertLvAdapter alertLvAdapter = new AlertLvAdapter(mContext, pdSubs);
//                            lv.setAdapter(alertLvAdapter);
//                            ab.setView(v);
//                            final AlertDialog alertDialog = ab.create();
//                            alertDialog.show();
//                            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                    pdsubChoice = pdSubs.get(i);
////                                    edPihao.setText(pdsubChoice.FBatchNo);
//                                    for (int j = 0; j < storageAdapter.getCount(); j++) {
//                                        Storage storage = (Storage) storageAdapter.getItem(j);
//                                        if (storage.FItemID.equals(pdsubChoice.FStockID)) {
//                                            spStorage.setSelection(j);
//                                        }
//                                    }
//                                    if (!pdsubChoice.FStockPlaceID.equals("0")) {
//                                        for (int j = 0; j < waveHouseAdapter.getCount(); j++) {
//                                            WaveHouse waveHouse = (WaveHouse) waveHouseAdapter.getItem(j);
//                                            if (waveHouse.FSPID.equals(pdsubChoice.FStockPlaceID)) {
//                                                spWavehouse.setSelection(j);
//                                            }
//                                        }
//                                    }
//                                    tvYtnum.setText(pdsubChoice.FCheckQty);
//                                    tvNumOnServer.setText(pdsubChoice.FQty);
//                                    if (isAuto) {
//                                        edPdnum.setText("1.0");
//                                        AddOrder();
//                                    }
//                                    alertDialog.dismiss();
//                                }
//                            });
//                        }


                        } else {
                            Toast.showText(mContext, "未找到盘点明细");
//                        if (isAuto && fid != null) {
//                            edPdnum.setText("1.0");
//                            AddOrder();
//                        }
                        }
                    } else {
                        Toast.showText(mContext, "请下载盘点方案");
                    }

                }
            }, 100);
        } catch (Exception e) {
            DataService.pushError(mContext, this.getClass().getSimpleName(), e);
        }
    }

    private void getProductOL(DownloadReturnBean dBean, int j) {
        product = dBean.products.get(j);
        tvorIsAuto(product);
        boolean isAuto = false;
        if (isAuto) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    binding.edNum.setText("1.0");
                    AddOrder();
                }
            }, 150);

        }
    }

    private void download(final String Json, final boolean downloadall) {
        LoadingUtil.showDialog(mContext,"正在下载,请稍候...");
        Asynchttp.post(mContext, getBaseUrl() + WebApi.PDSUBLIST, Json, new Asynchttp.Response() {
            @Override
            public void onSucceed(CommonResponse cBean, AsyncHttpClient client) {
                PDsubReturnBean pBean = gson.fromJson(cBean.returnJson, PDsubReturnBean.class);
                PDSubDao pdSubDao = daoSession.getPDSubDao();
                for (int i = 0; i < pBean.items.size(); i++) {
                    List<PDSub> list = pdSubDao.queryBuilder().where(
                            PDSubDao.Properties.FID.eq(pBean.items.get(i).FID),
                            PDSubDao.Properties.FStockPlaceID.eq(pBean.items.get(i).FStockPlaceID),
                            PDSubDao.Properties.FStockID.eq(pBean.items.get(i).FStockID),
                            PDSubDao.Properties.FLot_Text.eq(pBean.items.get(i).FLot_Text),
                            PDSubDao.Properties.FMaterialID.eq(pBean.items.get(i).FMaterialID)
                    ).build().list();
                    if (list.size() == 0) {
                        pdSubDao.deleteInTx(list);
                        long insert = pdSubDao.insert(pBean.items.get(i));
                    } else {
                        pdSubDao.deleteInTx(list);
                        long insert = pdSubDao.insert(pBean.items.get(i));
                    }


                }
                PDMainDao pdMainDao = daoSession.getPDMainDao();
                if (downloadall) {
                    for (int i = 0; i < mainContainer.size(); i++) {
                        PDMain p = pdMainDao.queryBuilder().where(
                                PDMainDao.Properties.FID.eq(mainContainer.get(i).FID)).build().unique();
                        if (p != null) {
                            pdMainDao.delete(p);
                        }
                        try {
                            pdMainDao.insert(mainContainer.get(i));
                        } catch (SQLiteConstraintException e) {
                            continue;
                        }
                    }
                } else {
                    for (int i = 0; i < mainContainer.size(); i++) {
                        for (int j = 0; j < choice.size(); j++) {
                            if (choice.get(j).equals(mainContainer.get(i).FID)) {
                                PDMain p = pdMainDao.queryBuilder().where(
                                        PDMainDao.Properties.FID.eq(mainContainer.get(i).FID)).build().unique();
                                if (p != null) {
                                    pdMainDao.delete(p);
                                }
                                try {
                                    pdMainDao.insert(mainContainer.get(i));
                                } catch (SQLiteConstraintException e) {
                                    continue;
                                }


                            }
                        }
                    }
                }

                Toast.showText(mContext, "下载完成");
                pdMainSpAdapter = method.getpdmain(binding.spPdplan);
                LoadingUtil.dismiss();

            }

            @Override
            public void onFailed(String Msg, AsyncHttpClient client) {
                Toast.showText(mContext, Msg);
                LoadingUtil.dismiss();
            }
        });
    }

    private void ResetAll() {
        binding.tvGoodName.setText("");
        binding.tvStorenum.setText("");
        binding.tvNuming.setText("");
        pdsubChoice=null;
//        edPihao.setText("");
//        List<PDSub> container = new ArrayList<>();
//        piciSpAdapter = new PiciSpForSubAdapter(mContext, container);
//        spPihao.setAdapter(piciSpAdapter);
        binding.edCode.setText("");
        binding.edNum.setText("");
    }
    /**
     * 权限处理
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        mCaptureManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed() {
        if (binding.zxingBarcodeScanner.getVisibility()==View.VISIBLE) {
            binding.zxingBarcodeScanner.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }

}
