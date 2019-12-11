package com.fangzuo.assist.cloud.Activity;

import android.app.AlertDialog;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Adapter.PrintHistoryAdapter;
import com.fangzuo.assist.cloud.Beans.BlueToothBean;
import com.fangzuo.assist.cloud.Beans.CommonBean;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.PrintHistory;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Utils.BasicShareUtil;
import com.fangzuo.assist.cloud.Utils.CommonUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.LocDataUtil;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.assist.cloud.databinding.ActivityPrintBeforeDataBinding;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.google.gson.Gson;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.orhanobut.hawk.Hawk;

import zpSDK.zpSDK.zpBluetoothPrinter;

public class PrintBeforeDataActivity extends BaseActivity {
    ActivityPrintBeforeDataBinding binding;
    zpBluetoothPrinter zpSDK;
    private PrintHistoryAdapter adapter;
    BlueToothBean bean;
    private PrintHistory printHistory;
    @Override
    protected void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.Print_Check://回单失败
                String msg = (String) event.postEvent;
                LoadingUtil.dismiss();
                if ("OK".equals(msg)){
                    binding.toolbar.tvRight.setText("打印机就绪");
                    binding.toolbar.tvRight.setTextColor(Color.BLACK);
                }else{
                    binding.toolbar.tvRight.setText("点击重连打印机");
                    binding.toolbar.tvRight.setTextColor(Color.RED);
                }
                break;

        }
    }

    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_print_before_data);
        zpSDK=new zpBluetoothPrinter(this);
        binding.toolbar.tvTitle.setText("期初物料补打");
        binding.cbNum.setSaveKey("PrintBeforeDataActivity-printnum");
        binding.ryPrintHistory.setAdapter(adapter = new PrintHistoryAdapter(this));
        binding.ryPrintHistory.setLayoutManager(new LinearLayoutManager(this));
        bean = Hawk.get(Config.OBJ_BLUETOOTH, new BlueToothBean("", ""));
        if ("".equals(bean.address)){
            LoadingUtil.showAlter(mContext,"未配置打印机，请先配置打印机");
        }
//        LoadingUtil.showDialog(mContext,"正在连接打印机...");

//        linkBluePrint();
    }

    @Override
    protected void initData() {

    }

    private boolean printing=false;
    @Override
    protected void initListener() {
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (!printing){
                    printing=true;
                    printHistory =adapter.getAllData().get(position);
                    Lg.e("点击历史：",printHistory);
//                if (binding.toolbar.tvRight.getText().toString().equals("点击重连打印机")){
//                    Toast.showText(mContext,"请先配置好打印机");
//                }else{
                    String stringdata = printHistory.FMaterialid+"|"+printHistory.FBaseUnitID+"|"+printHistory.FNum+"|"+
                            printHistory.FBatch+"|"+ BasicShareUtil.getInstance(mContext).getIMIE()+"|"+printHistory.FHuoquan +"|"+
                            printHistory.FActualModel+"|"+printHistory.FAuxSign;
//                bean.FBatch = binding.edPihao.getText().toString();
                    pushAndCreateCode(stringdata);
//                }
                }


            }
        });
        binding.ivFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.edPihao.getText().toString().equals("") || !binding.edProduct.getText().toString().equals("")){
                    getPrintHistory(binding.edPihao.getText().toString(),binding.edProduct.getText().toString());
                }else{
                    Toast.showText(mContext,"请输入需要查询的条件");
                }
            }
        });
        binding.edPihao.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 0 && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (!binding.edPihao.getText().toString().equals("") || !binding.edProduct.getText().toString().equals("")){
                        getPrintHistory(binding.edPihao.getText().toString(),binding.edProduct.getText().toString());
                    }else{
                        Toast.showText(mContext,"请输入需要查询的条件");
                    }
                }
                return true;
            }
        });
        binding.toolbar.tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"打印机就绪".equals(binding.toolbar.tvRight.getText().toString())){
                    LoadingUtil.showDialog(mContext,"正在连接打印机...");
                    linkBluePrint();
                }
            }
        });
    }

    /**
     *              通过批号查找打印数据
     * @param string        需要查找的模糊批号
     */
    private void getPrintHistory(String string,String product){
        LoadingUtil.showDialog(mContext,"正在查找打印数据...");
        printing=true;
        App.getRService().doIOAction(WebApi.PrintBeforeData, gson.toJson(new CommonBean(string,product)), new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);
                LoadingUtil.dismiss();
                if (!commonResponse.state)return;
//                Lg.e("获得打印数据：",commonResponse.returnJson);
                DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                if (dBean.printHistories.size()>0){
                    adapter.clear();
                    adapter.addAll(dBean.printHistories);
                    printing=false;
//                    showMsg(dBean.printHistories.get(0));
                }else{
                    printing=false;
                    Toast.showText(mContext,"无数据");
                    adapter.clear();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                adapter.clear();
                printing=false;
                LoadingUtil.dismiss();
            }
        });
    }

    //生成条码并执行打印
    private void pushAndCreateCode(String string){
        Lg.e("Data:pushAndCreateCode",printHistory);
        App.getRService().doIOAction(WebApi.PrintBeforeDataForCreateCode, string, new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);
                if (!commonResponse.state)return;
                DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                if (null != dBean && dBean.codeCheckBackDataBeans.size() > 0) {
                    PrintHistory printBean = new PrintHistory();
                    printBean.setPrintHistory(printHistory);
                    printBean.FNum=dBean.codeCheckBackDataBeans.get(0).FStoreQty;
                    printBean.FNum2=dBean.codeCheckBackDataBeans.get(0).FBaseQty;
                    printBean.FBarCode = dBean.codeCheckBackDataBeans.get(0).FBarCode;
                    printBean.FDate = getTime(true);
                    String huozhuNote= LocDataUtil.getRemark(printBean.FHuoquan,"number").FNote;
                    printBean.FHuoquan=huozhuNote;
                    try {
                        CommonUtil.doPrintOut(zpSDK,printBean,binding.cbNum.getNum());
                        printHistory=null;
                        printing=false;
                        getPrintHistory(binding.edPihao.getText().toString(),binding.edProduct.getText().toString());
                    } catch (Exception e) {
//                    e.printStackTrace();
                        printHistory=null;
                        printing=false;
                        getPrintHistory(binding.edPihao.getText().toString(),binding.edProduct.getText().toString());
                        LoadingUtil.showAlter(mContext,getString(R.string.error_print),getString(R.string.check_print));
                    }
                }else{
                    printing=false;
                    Toast.showText(mContext,"生成条码失败,请重试");
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                printing=false;
            }
        });
    }


    //连接打印机
    private void linkBluePrint(){
        Lg.e("点击打印机重连");
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (bean.address.equals("")){
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Print_Check, "NOOK"));
                }else{
                    if(!zpSDK.connect(bean.address))
                    {
                        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Print_Check, "NOOK"));
                    }else{
                        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Print_Check, "OK"));
                    }
                }
            }
        }).start();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            zpSDK.disconnect();
        }catch (Exception e){}
    }
    @Override
    protected void OnReceive(String code) {

    }
    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }
}
