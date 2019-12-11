package com.fangzuo.assist.cloud.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Adapter.ReViewPDAdapter;
import com.fangzuo.assist.cloud.Beans.BlueToothBean;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.PrintHistory;
import com.fangzuo.assist.cloud.Beans.PurchaseInStoreUploadBean;
import com.fangzuo.assist.cloud.Dao.PushDownSub;
import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.Dao.T_main;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Utils.CommonUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.MathUtil;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.assist.cloud.databinding.ActivityReViewPd31Binding;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.fangzuo.greendao.gen.PrintHistoryDao;
import com.fangzuo.greendao.gen.PushDownSubDao;
import com.fangzuo.greendao.gen.T_DetailDao;
import com.fangzuo.greendao.gen.T_mainDao;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import butterknife.ButterKnife;
import butterknife.OnClick;
import zpSDK.zpSDK.zpBluetoothPrinter;

public class ReViewPD31Activity extends BaseActivity {
    ActivityReViewPd31Binding binding;
    private int activity;
    private String  fid;
    //    private T_main main;
    private List<T_Detail> list;
    private List<T_main> list_main;
    private ReViewPDAdapter adapter;
    private List<Boolean> isCheck;

    @Override
    protected void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
//            case EventBusInfoCode.Print_Check://回单失败
//                String msg = (String) event.postEvent;
//                if ("OK".equals(msg)){
//                    isOkPrint = true;
//                    Toast.showText(mContext,"打印机就绪");
////                    binding.toolbar.tvRight.setText("打印机就绪");
////                    binding.toolbar.tvRight.setTextColor(Color.BLACK);
//                }else{
//                    isOkPrint = false;
//                    Toast.showText(mContext,"连接打印机错误");
////                    binding.toolbar.tvRight.setText("连接打印机错误");
////                    binding.toolbar.tvRight.setTextColor(Color.RED);
//                }
//                LoadingUtil.dismiss();
//                break;
//            case EventBusInfoCode.Print_Out://打印
//                PrintHistory data = (PrintHistory) event.postEvent;
//                try {
//                    CommonUtil.doPrint(zpSDK,data,"1");
//                } catch (Exception e) {
////                    e.printStackTrace();
//                    LoadingUtil.showAlter(mContext,getString(R.string.error_print),getString(R.string.check_print));
//                }
//                break;
        }
    }
    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_re_view_pd31);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        Intent in = getIntent();
        Bundle extras = in.getExtras();
        activity = extras.getInt("activity");
        fid = extras.getString("fid");
        Lg.e("得到数据："+activity);
        Lg.e("得到数据："+fid);
        initList();
    }

    private void initList() {
        double num = 0;
        isCheck = new ArrayList<>();
        list = new ArrayList<>();
        list_main = new ArrayList<>();
        list = t_detailDao.queryBuilder().where(
                T_DetailDao.Properties.Activity.eq(activity),
                T_DetailDao.Properties.FAccountID.eq(CommonUtil.getAccountID()),
                T_DetailDao.Properties.FID.eq(fid)
        ).build().list();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                isCheck.add(false);
            }
        }else{//若列表为空，删除所有该activity的表头信息
            t_mainDao.deleteInTx(t_mainDao.queryBuilder().where(
                    T_mainDao.Properties.Activity.eq(activity),
                    T_mainDao.Properties.FAccountID.eq(CommonUtil.getAccountID()),
                    T_mainDao.Properties.FID.eq(fid)
            ).build().list());
            EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock+"NO"));
        }
        Lg.e("列表数据：" ,list);
        adapter = new ReViewPDAdapter(mContext, list, isCheck);
        binding.lvResult.setAdapter(adapter);
//        adapter.setInnerListener(this);
        adapter.notifyDataSetChanged();
        List<String> products = new ArrayList<>();
        products.clear();
        if (list.size() > 0) {
//            binding.tvCheckMain.setVisibility(View.GONE);
            list_main = t_mainDao.queryBuilder().where(
                    T_mainDao.Properties.Activity.eq(activity),
                    T_mainDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
            ).build().list();
            if (products.size() == 0) {
                products.add(list.get(0).FBarcode);
            }
            for (int i = 0; i < list.size(); i++) {
                if (!products.contains(list.get(i).FBarcode)) {
                    products.add(list.get(i).FBarcode);
                }
                num += MathUtil.toD(list.get(i).FRealQty);
            }
            binding.productcategory.setText("已添加数量:" + products.size() + "个");
            binding.productnum.setText("物料总数为:" + num + "");
        } else {
//            binding.tvCheckMain.setVisibility(View.GONE);
            binding.productcategory.setText("已添加数量:" + 0 + "个");
            binding.productnum.setText("物料总数为:" + 0 + "");
        }

    }

    @Override
    protected void initListener() {
        binding.lvResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (isCheck.get(i)) {
                    isCheck.set(i, false);
                } else {
                    isCheck.set(i, true);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    private PurchaseInStoreUploadBean pBean;
    private PurchaseInStoreUploadBean.purchaseInStore listBean;
    private ArrayList<PurchaseInStoreUploadBean.purchaseInStore> data;
    private ArrayList<T_Detail> t_detailList;
    private ArrayList<T_main> t_mainsList;
    @OnClick({R.id.btn_back, R.id.delete_all, R.id.btn_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
//            case R.id.delete_all:
//                AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
//                ab.setTitle("确认删除");
//                ab.setMessage("确认删除所有么？");
//                ab.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        t_detailDao.deleteInTx(t_detailDao.queryBuilder().where(
//                                T_DetailDao.Properties.Activity.eq(activity)).build().list());
//                        initList();
//                        t_mainDao.deleteInTx(t_mainDao.queryBuilder().where(
//                                T_mainDao.Properties.Activity.eq(activity)).build().list());
//                    }
//                });
//                ab.setNegativeButton("取消", null);
//                ab.create().show();
//                break;
            case R.id.btn_delete:
                AlertDialog.Builder delete = new AlertDialog.Builder(mContext);
                delete.setTitle("确认删除");
                delete.setMessage("确认删除选中单据么？");
                delete.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LoadingUtil.showDialog(mContext, "正在删除...");
                        t_detailList = new ArrayList<>();
                        t_mainsList = new ArrayList<>();
                        pBean = new PurchaseInStoreUploadBean();
                        listBean = pBean.new purchaseInStore();
                        data = new ArrayList<>();
                        for (int j = 0; j < isCheck.size(); j++) {
                            if (isCheck.get(j)) {
//                                Log.e(i + "", isCheck.get(j) + "");
                                final T_Detail t_detail = t_detailDao.queryBuilder().where(
                                        T_DetailDao.Properties.FIndex.eq(list.get(j).FIndex)
                                ).build().unique();
                                t_detailList.add(t_detail);
//                                final T_main t_main = t_mainDao.queryBuilder().where(
//                                        T_mainDao.Properties.FIndex.eq(list.get(j).FIndex)
//                                ).build().unique();
//                                t_mainsList.add(t_main);
                                Lg.e("获取到T_Detail:",t_detail);
                            }
                        }
                        String detail = "";
                        listBean.main = "";
                        ArrayList<String> detailContainer = new ArrayList<>();
                        for (int j = 0; j < t_detailList.size(); j++) {
                            if (j != 0 && j % 49 == 0) {
                                Log.e("j%49", j % 49 + "");
                                T_Detail t_detail = t_detailList.get(j);
                                detail = detail +
                                        t_detail.FBarcode + "|" +
                                        t_detail.FRealQty + "|" +
                                        t_detail.IMIE + "|" +
                                        t_detail.FOrderId + "|";
                                detail = detail.subSequence(0, detail.length() - 1).toString();
                                detailContainer.add(detail);
                                detail = "";
                            } else {
                                Log.e("j", j + "");
                                T_Detail t_detail = t_detailList.get(j);
                                detail = detail +
                                        t_detail.FBarcode + "|" +
                                        t_detail.FRealQty + "|" +
                                        t_detail.IMIE + "|" +
                                        t_detail.FOrderId + "|";
                                Log.e("detail1", detail);
                            }
                        }
                        if (detail.length() > 0) {
                            detail = detail.subSequence(0, detail.length() - 1).toString();
                        }
                        Log.e("detail", detail);
                        detailContainer.add(detail);
                        listBean.detail = detailContainer;
                        data.add(listBean);
                        pBean.list = data;
                        Gson gson = new Gson();
                        App.getRService().doIOAction(WebApi.CodeOnlyDelete4All, gson.toJson(pBean), new MySubscribe<CommonResponse>() {
                            @Override
                            public void onNext(CommonResponse commonResponse) {
                                super.onNext(commonResponse);
                                if (!commonResponse.state) return;
                                Lg.e("删除请求成功");
                                deleteMain(t_detailList);
//                                t_detailDao.deleteInTx(t_detailList);
//                                t_mainDao.deleteInTx(t_mainsList);
//                                Toast.showText(mContext, "删除成功");
//                                initList();
//                                tableAdapter.notifyDataSetChanged();
                                LoadingUtil.dismiss();
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                LoadingUtil.dismiss();
                                Toast.showText(mContext, "删除失败：" + e.toString());
                            }
                        });

                    }
                });
                delete.setNegativeButton("取消", null);
                delete.create().show();
                break;
        }
    }
    //删除相应的表头信息
    private void deleteMain(List<T_Detail> listsss){
        for (int j = 0; j < list.size(); j++) {//这里要以列表数据为基础，不能以过滤出来的数据
            if (isCheck.get(j)) {
                if (!list.get(j).FBarcode.startsWith("ZZ")){
                    PushDownSubDao pushDownSubDao = daoSession.getPushDownSubDao();
                    List<PushDownSub> pushDownSubs = pushDownSubDao.queryBuilder().where(
                            PushDownSubDao.Properties.FEntryID.eq(list.get(j).FEntryID)
                    ).build().list();
                    Lg.e(pushDownSubs.size() + "多少个");
                    if (pushDownSubs.size() > 0) {
                        Lg.e("存在明细：-数量："+list.get(j).FRealQty);
                        //删除后，更新数据里面的已验收数
                        double result = MathUtil.toD(list.get(j).FRealQty);
                        pushDownSubs.get(0).FQtying = MathUtil.doubleSub(MathUtil.toD(pushDownSubs.get(0).FQtying), result) + "";
                        Lg.e("减掉得到：", pushDownSubs.get(0).FQtying);
                        pushDownSubDao.update(pushDownSubs.get(0));
                    }
                    //删除单个明细
                    t_detailDao.deleteInTx(t_detailDao.queryBuilder().where(
                            T_DetailDao.Properties.FBarcode.eq(list.get(j).FBarcode)
                    ).build().list());
                }else{
                    List<T_Detail> boxDetails = t_detailDao.queryBuilder().where(
                            T_DetailDao.Properties.FBarcode.eq(list.get(j).FBarcode)
                    ).build().list();
                    if (boxDetails.size()>0){
                        for (int i = 0; i < boxDetails.size(); i++) {
                            PushDownSubDao pushDownSubDao = daoSession.getPushDownSubDao();
                            List<PushDownSub> pushDownSubs = pushDownSubDao.queryBuilder().where(
                                    PushDownSubDao.Properties.FEntryID.eq(boxDetails.get(i).FEntryID)
                            ).build().list();
                            Lg.e(pushDownSubs.size() + "多少个");
                            if (pushDownSubs.size() > 0) {
                                Lg.e("存在明细：-数量："+boxDetails.get(i).FRealQty);
                                //删除后，更新数据里面的已验收数
                                double result = MathUtil.toD(boxDetails.get(i).FRealQty);
                                pushDownSubs.get(0).FQtying = MathUtil.doubleSub(MathUtil.toD(pushDownSubs.get(0).FQtying), result) + "";
                                Lg.e("减掉得到：", pushDownSubs.get(0).FQtying);
                                pushDownSubDao.update(pushDownSubs.get(0));
                            }
                        }
                        //删除所属所有的箱码明细
                        t_detailDao.deleteInTx(boxDetails);
                    }
                }

            }
        }
//        t_detailDao.deleteInTx(listsss);
        initList();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Lg.e("ReView：","OnPause");

    }


    @Override
    protected void OnReceive(String code) {

    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

}
