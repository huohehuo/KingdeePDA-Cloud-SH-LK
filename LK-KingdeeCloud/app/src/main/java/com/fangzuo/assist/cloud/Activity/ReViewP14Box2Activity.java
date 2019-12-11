package com.fangzuo.assist.cloud.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Adapter.ReViewP14Box2Adapter;
import com.fangzuo.assist.cloud.Adapter.ReViewP14BoxAdapter;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.PrintDataBean;
import com.fangzuo.assist.cloud.Dao.ReBoxBean;
import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.Dao.T_main;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.CommonUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.DataModel;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.MathUtil;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.databinding.ActivityReViewP14Box2Binding;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.fangzuo.greendao.gen.PDSubDao;
import com.fangzuo.greendao.gen.ReBoxBeanDao;
import com.fangzuo.greendao.gen.T_DetailDao;
import com.fangzuo.greendao.gen.T_mainDao;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import zpSDK.zpSDK.zpBluetoothPrinter;

public class ReViewP14Box2Activity extends BaseActivity {

    ActivityReViewP14Box2Binding binding;
    private int activity;
    private List<ReBoxBean> list;
    private List<T_main> mainsList;
    private ReViewP14Box2Adapter adapter;
    //    private ReViewPDAP2ForShuibanAdapter adapter4SB;
    private List<String> printBoxCode;
    private List<Boolean> isCheck;
    private PDSubDao pdSubDao;
    private ReBoxBeanDao reBoxBeanDao;

    private zpBluetoothPrinter zpSDK;
    @Override
    protected void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.Print_Out://打印
                String data = (String) event.postEvent;
                try {
                    CommonUtil.doPrint4P1BoxCode2(zpSDK,mContext, Hawk.get(data, new ArrayList<PrintDataBean>()), "1",true);
                } catch (Exception e) {
//                    e.printStackTrace();
                    App.getInstance().connectPrint();
                    LoadingUtil.showAlter(mContext, getString(R.string.error_print), getString(R.string.check_print));
                }
                break;
        }
    }

    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_re_view_p14_box2);
        ButterKnife.bind(this);
//        pdSubDao = daoSession.getPDSubDao();
        reBoxBeanDao = daoSession.getReBoxBeanDao();
        zpSDK=new zpBluetoothPrinter(mContext);
    }

    @Override
    protected void initData() {
        Intent in = getIntent();
        Bundle extras = in.getExtras();
        activity = extras.getInt("activity");
        initList();
    }

    private void initList() {
        double num = 0;
        double storenum = 0;
        LoadingUtil.dismiss();
        isCheck = new ArrayList<>();
        list = new ArrayList<>();
        printBoxCode = new ArrayList<>();
//        list = DataModel.getP1BoxDetail(mContext, activity);
        list = reBoxBeanDao.queryBuilder().where(
                ReBoxBeanDao.Properties.Activity.eq(activity),
                ReBoxBeanDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
        ).build().list();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                isCheck.add(false);
            }
        } else {//若列表为空，删除所有该activity的表头信息
            t_mainDao.deleteInTx(t_mainDao.queryBuilder().where(
                    T_mainDao.Properties.Activity.eq(activity),
                    T_mainDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
            ).build().list());
            EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock + "NO"));
        }
        Lg.e("列表数据：" + gson.toJson(list));
        adapter = new ReViewP14Box2Adapter(mContext, list, isCheck);
        binding.lvResult.setAdapter(adapter);
//        adapter.setInnerListener(this);
        adapter.notifyDataSetChanged();


        List<String> products = new ArrayList<>();
        products.clear();
        if (list.size() > 0) {
            if (products.size() == 0) {
                products.add(list.get(0).FBoxCode);
            }
            for (int i = 0; i < list.size(); i++) {
                if (!products.contains(list.get(i).FBoxCode)) {
                    products.add(list.get(i).FBoxCode);
                }
                num = MathUtil.sum(num + "", list.get(i).FPCS);
                storenum = MathUtil.sum(storenum + "", list.get(i).FM3);
            }

            binding.productcategory.setText("已添加数量:" + products.size() + "个");
//            if (activity==Config.ProductInStore4P2Activity){//水版
//                binding.tvStorenum.setVisibility(View.GONE);
//                binding.productnum.setText("基本数量:" + num +"立方米");
//            }else{
            binding.tvStorenum.setText("PCS:" + MathUtil.Cut0(num + "") + "");
            binding.productnum.setText("M3:" + storenum);

//                binding.productnum.setText("基本数量:" + num +list.get(0).FBaseUnit);
//                binding.tvStorenum.setText("库存数量:" + DoubleUtil.Cut4(storenum+"") + list.get(0).FStoreUnit);
//            }
        } else {
            binding.tvStorenum.setVisibility(View.GONE);
//            if (activity==Config.ProductInStore4P2Activity){
//            }else{
//                binding.tvStorenum.setText("库存数量:0");
//            }
            binding.productcategory.setText("已添加数量:" + 0 + "个");
            binding.productnum.setText("基本数量:" + 0 + "");
        }

        mainsList = new ArrayList<>();
        mainsList = t_mainDao.queryBuilder().where(
                T_mainDao.Properties.Activity.eq(activity),
                T_mainDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
        ).build().list();
        Lg.e("表头main"+mainsList.size(),mainsList);
        if (mainsList.size() > 0) {
            for (int i = 0; i < mainsList.size(); i++) {
                List<ReBoxBean> reBoxBeans = reBoxBeanDao.queryBuilder().where(
                        ReBoxBeanDao.Properties.Activity.eq(activity),
                        ReBoxBeanDao.Properties.FOrderId.eq(mainsList.get(i).FOrderId),
                        ReBoxBeanDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
                ).build().list();
                Lg.e("rebox",reBoxBeans);
                if (null ==reBoxBeans || reBoxBeans.size()==0){
                    t_mainDao.deleteInTx(mainsList.get(i));
                    t_detailDao.deleteInTx(t_detailDao.queryBuilder().where(
                            T_DetailDao.Properties.FOrderId.eq(mainsList.get(i).FOrderId),
                            T_DetailDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
                    ).build().list());
                }
            }
        }else{
            t_detailDao.deleteInTx(t_detailDao.queryBuilder().where(
                    T_DetailDao.Properties.Activity.eq(activity),
                    T_DetailDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
            ).build().list());
        }
    }


    @Override
    protected void initListener() {
        binding.lvResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (isCheck.get(i)) {
                    isCheck.set(i, false);
                    printBoxCode.remove(list.get(i).FBoxCode);
                } else {
                    printBoxCode.add(list.get(i).FBoxCode);
                    isCheck.set(i, true);
                }
                adapter.notifyDataSetChanged();

            }
        });
        binding.tvPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!isOkPrint){
//                    LoadingUtil.showDialog(mContext,"正在重连打印机...");
//                    App.getInstance().connectPrint();
//                    linkBluePrint();
//                }else{
                new AlertDialog.Builder(mContext)
                        .setTitle("是否补打单据？")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getAndPrintData();
                            }
                        })
                        .create().show();
//                }
//                startNewActivity(PrintHistoryActivity.class, R.anim.activity_slide_left_in, R.anim.activity_slide_left_out, false, null);

            }
        });
    }

    //获取列表相对应的打印数据
    private void getAndPrintData() {
        if (printBoxCode.size() > 1) {
            Toast.showText(mContext, "请逐个箱码补打");
            return;
        }
        if (printBoxCode.size() == 0) {
            Toast.showText(mContext, "请选择需要补打的箱码");
            return;
        }
        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Print_Out, printBoxCode.get(0)));

//        List<T_Detail> list = t_detailDao.queryBuilder().where(
//                T_DetailDao.Properties.Activity.eq(activity),
//                T_DetailDao.Properties.FCfBoxCode.eq(printBoxCode.get(0)),
//                T_DetailDao.Properties.FIsInBox.eq(0)
//        ).build().list();
//        if (list.size() > 0) {
//            Toast.showText(mContext, "装箱后才能进行补打");
//            return;
//        }

//        App.getRService().doIOAction(WebApi.GetWortPrintData, printBoxCode.get(0), new MySubscribe<CommonResponse>() {
//            @Override
//            public void onNext(CommonResponse commonResponse) {
//                super.onNext(commonResponse);
//                if (!commonResponse.state) return;
//                DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
//                if (null != dBean && dBean.wortPrintDatas.size() > 0) {
//                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Print_Out, dBean.wortPrintDatas));
//                } else {
//                    LoadingUtil.showAlter(mContext, "箱码补打查询失败");
//                    Lg.e("箱码补打查询失败");
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//                LoadingUtil.showAlter(mContext, "箱码补打查询失败", e.getMessage());
//
//            }
//        });
    }

    @OnClick({R.id.btn_back, R.id.delete_all, R.id.btn_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.delete_all:
                AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                ab.setTitle("确认删除");
                ab.setMessage("确认删除所有么？");
                ab.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int pos) {
                        LoadingUtil.showDialog(mContext, "正在删除...");
                        for (int i = 0; i < list.size(); i++) {
                            reBoxBeanDao.deleteInTx(reBoxBeanDao.queryBuilder().where(
                                    ReBoxBeanDao.Properties.FBoxCode.eq(list.get(i).FBoxCode),
                                    ReBoxBeanDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
                            ).build().list());
                            Hawk.delete(list.get(i).FBoxCode);
                        }
                        Toast.showText(mContext, "删除成功");
                        initList();
                    }
                });
                ab.setNegativeButton("取消", null);
                ab.create().show();
                break;
            case R.id.btn_delete:
                AlertDialog.Builder delete = new AlertDialog.Builder(mContext);
                delete.setTitle("确认删除");
                delete.setMessage("确认删除选中单据么？");
                delete.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LoadingUtil.showDialog(mContext, "正在删除...");
                        for (int j = 0; j < isCheck.size(); j++) {
                            if (isCheck.get(j)) {
                                reBoxBeanDao.deleteInTx(reBoxBeanDao.queryBuilder().where(
                                        ReBoxBeanDao.Properties.FOrderId.eq(list.get(j).FOrderId),
                                        ReBoxBeanDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
                                ).build().list());
                                Hawk.delete(list.get(j).FBoxCode);
                                t_mainDao.deleteInTx(t_mainDao.queryBuilder().where(
                                        T_mainDao.Properties.FOrderId.eq(list.get(j).FOrderId),
                                        T_mainDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
                                ).build().list());
                                t_detailDao.deleteInTx(t_detailDao.queryBuilder().where(
                                        T_DetailDao.Properties.FOrderId.eq(list.get(j).FOrderId),
                                        T_DetailDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
                                ).build().list());
                            }
                        }
                        Toast.showText(mContext, "删除成功");
                        initList();
                    }
                });
                delete.setNegativeButton("取消", null);
                delete.create().show();
                break;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        Lg.e("ReView：", "OnPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void OnReceive(String code) {

    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }
}