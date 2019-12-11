package com.fangzuo.assist.cloud.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Adapter.SplitBoxP1Adapter;
import com.fangzuo.assist.cloud.Adapter.SplitHistoryAdapter;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Dao.PrintErrorHistory;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Utils.CommonUtil;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.fangzuo.greendao.gen.PrintErrorHistoryDao;
import com.google.gson.Gson;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zpSDK.zpSDK.zpBluetoothPrinter;

public class SplitPrintHistoryActivity extends BaseActivity {

    @BindView(R.id.cancle)
    View cancle;
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.pg)
    ProgressBar pg;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.ry_data)
    EasyRecyclerView ryData;

    private SplitHistoryAdapter adapter;
    private PrintErrorHistoryDao printErrorHistoryDao;
    private zpBluetoothPrinter zpSDK;
    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.PrintBox_OK://回单失败
                String activity = (String) event.postEvent;
                List<PrintErrorHistory> list = printErrorHistoryDao.queryBuilder().where(PrintErrorHistoryDao.Properties.FBoxCode.eq(activity)).build().list();
                if (list.size()>0){
                    list.get(0).FState="1";
                    printErrorHistoryDao.update(list.get(0));
                }
                reLoad();
//                clickOrder(activity);
                break;
            case EventBusInfoCode.PrintBox_Error://回单失败
                String eror = (String) event.postEvent;
                Toast.showText(mContext,"打印失败"+eror);
                break;

        }
    }
    @Override
    protected void initView() {
        setContentView(R.layout.activity_split_print_history);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        ryData.setAdapter(adapter = new SplitHistoryAdapter(mContext));
        ryData.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        zpSDK = new zpBluetoothPrinter(this);
        printErrorHistoryDao = daoSession.getPrintErrorHistoryDao();
        reLoad();
    }

    private void reLoad(){
        pg.setVisibility(View.VISIBLE);
        adapter.clear();
        adapter.addAll(printErrorHistoryDao.queryBuilder().orderDesc(PrintErrorHistoryDao.Properties.FIndex).build().list());
        pg.setVisibility(View.GONE);
    }

    @Override
    protected void initListener() {
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final int position) {
                Lg.e("列表",adapter.getAllData().get(position));
                new AlertDialog.Builder(mContext)
                        .setTitle("是否补打箱码？")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                doPrint(adapter.getAllData().get(position).FBoxCode);
                            }
                        })
                        .setNeutralButton("取消",null)
                        .create().show();
            }
        });


    }

    private void doPrint(String code){
        App.getRService().doIOAction(WebApi.GetBoxPrintData,code, new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);
                if (!commonResponse.state)return;
                DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                if (null != dBean && dBean.printDataBeans.size()>0) {
                    try {
                        if ("0".equals(dBean.printDataBeans.get(0).FBoxType)){
                            CommonUtil.doPrint4P1BoxCode(mContext, dBean.printDataBeans,"1",false);
                        }else{
                            CommonUtil.doPrint4P1BoxCode2(zpSDK,mContext, dBean.printDataBeans,"1");
                        }
                    } catch (Exception e) {
//                    e.printStackTrace();
//            App.getInstance().connectPrint();
                        LoadingUtil.showAlter(mContext, getString(R.string.error_print), getString(R.string.check_print));
                    }
                }else{
                    LoadingUtil.showAlter(mContext,"箱码数据为空");
                    Lg.e("箱码补打查询失败");
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                LoadingUtil.showAlter(mContext,"箱码补打查询失败",e.getMessage());
//                Toast.showText(mContext,"未拆箱条码不能补打");

            }
        });
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, SplitPrintHistoryActivity.class);
//        intent.putExtra("activity",activity);
        context.startActivity(intent);
    }
    @Override
    protected void OnReceive(String code) {

    }

    @OnClick({R.id.cancle, R.id.btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancle:
                finish();
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
