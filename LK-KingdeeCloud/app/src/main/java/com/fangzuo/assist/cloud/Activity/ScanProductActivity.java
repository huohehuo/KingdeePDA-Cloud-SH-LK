package com.fangzuo.assist.cloud.Activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.PrintHistory;
import com.fangzuo.assist.cloud.Beans.SearchBean;
import com.fangzuo.assist.cloud.Dao.Product;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Utils.AnimUtil;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.LocDataUtil;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.assist.cloud.databinding.ActivityScanProductBinding;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.fangzuo.assist.cloud.zxing.ScanManager;
import com.google.gson.Gson;
import com.journeyapps.barcodescanner.BarcodeResult;

import zpSDK.zpSDK.zpBluetoothPrinter;

public class ScanProductActivity extends BaseActivity {
    ActivityScanProductBinding binding;
    zpBluetoothPrinter zpSDK;
    private ScanManager mCaptureManager;

    @Override
    protected void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.ScanResult:
                BarcodeResult res = (BarcodeResult) event.postEvent;
                mCaptureManager.onPause();
                binding.zxingBarcodeScanner.setVisibility(View.GONE);
                OnReceive(res.getResult().getText());
//                Toast.showText(mContext, "扫描结果：" + res.getResult().getText());
                break;
        }
    }

    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_scan_product);
        zpSDK=new zpBluetoothPrinter(this);
        //摄像头初始化
        mCaptureManager = new ScanManager(ScanProductActivity.this, binding.zxingBarcodeScanner);
        mCaptureManager.initializeFromIntent(getIntent(), savedInstanceState);
    }

    @Override
    protected void initData() {
        mCaptureManager.onResume();
        mCaptureManager.decode();
    }

    @Override
    protected void initListener() {
        binding.ivScan.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
//                if (binding.zxingBarcodeScanner.getVisibility()==View.GONE){
                    binding.cvShow.setVisibility(View.GONE);
                    binding.zxingBarcodeScanner.setVisibility(View.VISIBLE);
                    mCaptureManager.onResume();
                    mCaptureManager.decode();
//                }
            }
        });
    }

    @Override
    protected void OnReceive(String code) {
        getPrintHistory("0",code);
    }
    /**
     *              通过批号或者条码查找打印数据
     * @param type      1 ：通过批号查找，0：通过条码查找
     * @param string
     */
    private void getPrintHistory(String type,String string){
        LoadingUtil.showDialog(mContext,"正在查找打印数据...");
        SearchBean searchBean = new SearchBean(type,string);
        App.getRService().doIOAction(WebApi.PrintOutCheck, gson.toJson(searchBean), new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);
                LoadingUtil.dismiss();
                if (!commonResponse.state)return;
//                Lg.e("获得打印数据：",commonResponse.returnJson);
                DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                if (dBean.printHistories.size()>0){
                    PrintHistory history = dBean.printHistories.get(0);
                    history.FHuoquan= LocDataUtil.getRemark(history.getFHuoquan(),"number").FNote;
                    binding.setPrint(dBean.printHistories.get(0));
                    binding.cvShow.setVisibility(View.VISIBLE);
                    mCaptureManager.onPause();
//                    adapter.clear();
//                    adapter.addAll(dBean.printHistories);
//                    showMsg(dBean.printHistories.get(0));
                }else{
                    binding.cvShow.setVisibility(View.GONE);
                    binding.zxingBarcodeScanner.setVisibility(View.VISIBLE);
                    mCaptureManager.onResume();
                    mCaptureManager.decode();
                    LoadingUtil.showAlter(mContext,"无数据","");
//                    adapter.clear();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
//                adapter.clear();
//                LoadingUtil.dismiss();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            zpSDK.disconnect();
        }catch (Exception e){}
    }
//    @Override
//    public void onBackPressed() {
//        if (binding.zxingBarcodeScanner.getVisibility() == View.VISIBLE) {
//            binding.zxingBarcodeScanner.setVisibility(View.GONE);
//        } else {
//            super.onBackPressed();
//        }
//    }


    public static void start(Context context){
        Intent intent = new Intent(context,ScanProductActivity.class);
//        intent.putExtra("activity",activity);
        context.startActivity(intent);
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
