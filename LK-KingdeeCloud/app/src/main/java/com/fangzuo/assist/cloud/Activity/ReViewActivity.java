package com.fangzuo.assist.cloud.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Adapter.ReViewAdapter;
import com.fangzuo.assist.cloud.Beans.BlueToothBean;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.PrintHistory;
import com.fangzuo.assist.cloud.Beans.PurchaseInStoreUploadBean;
import com.fangzuo.assist.cloud.Dao.PDSub;
import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.Dao.T_main;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Utils.CommonUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.DoubleUtil;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.MathUtil;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.assist.cloud.databinding.ActivityReViewBinding;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.fangzuo.greendao.gen.PDSubDao;
import com.fangzuo.greendao.gen.PrintHistoryDao;
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

public class ReViewActivity extends BaseActivity {
    ActivityReViewBinding binding;
    private int activity;
    private List<T_Detail> list;
    private List<T_main> mainsList;
    private ReViewAdapter adapter;
    private List<Boolean> isCheck;
    private PDSubDao pdSubDao;
    private zpBluetoothPrinter zpSDK;
    private BlueToothBean bean;
    boolean isOkPrint=false;

    @Override
    protected void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.Print_Check://回单失败
                String msg = (String) event.postEvent;
                if ("OK".equals(msg)){
                    isOkPrint = true;
                    Toast.showText(mContext,"打印机就绪");
//                    binding.toolbar.tvRight.setText("打印机就绪");
//                    binding.toolbar.tvRight.setTextColor(Color.BLACK);
                }else{
                    isOkPrint = false;
                    Toast.showText(mContext,"连接打印机错误");
//                    binding.toolbar.tvRight.setText("连接打印机错误");
//                    binding.toolbar.tvRight.setTextColor(Color.RED);
                }
                LoadingUtil.dismiss();
                break;
            case EventBusInfoCode.Print_Out://打印
                PrintHistory data = (PrintHistory) event.postEvent;
                try {
                    CommonUtil.doPrint(zpSDK,data,"1");
                } catch (Exception e) {
//                    e.printStackTrace();
                    LoadingUtil.showAlter(mContext,getString(R.string.error_print),getString(R.string.check_print));
                }
                break;
        }
    }

    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_re_view);
        ButterKnife.bind(this);
        pdSubDao = daoSession.getPDSubDao();
    }

    @Override
    protected void initData() {
        Intent in = getIntent();
        Bundle extras = in.getExtras();
        activity = extras.getInt("activity");
        //当为产品入库时，初始化打印机并连接
        if (activity == Config.ProductInStoreActivity||activity==Config.TbInActivity||activity==Config.DgInActivity||activity==Config.ChangeModelInActivity
                ||activity==Config.WorkOrgIn4P2Activity||activity==Config.ChangeInActivity||activity==Config.ChangeLvInActivity
                ||activity==Config.SimpleInActivity||activity==Config.GbInActivity||activity==Config.DhInActivity||activity==Config.P1PdProductGet2CprkActivity
                ||activity==Config.DhIn2Activity||activity==Config.TbIn2Activity||activity==Config.TbIn3Activity||activity==Config.P1PdProductGet2Cprk2Activity
                ||activity==Config.SplitBoxInActivity||activity==Config.ZbCheJianInActivity||activity==Config.Bg1CheJianInActivity||activity==Config.Bg2CheJianInActivity
                ||activity==Config.CpWgInActivity
                ||activity==Config.ZbIn1Activity
                ||activity==Config.ZbIn2Activity
                ||activity==Config.ZbIn3Activity
                ||activity==Config.ZbIn4Activity
                ||activity==Config.ZbIn5Activity
                ) {
            zpSDK = new zpBluetoothPrinter(this);
            bean = Hawk.get(Config.OBJ_BLUETOOTH, new BlueToothBean("", ""));
            linkBluePrint();
        } else {
            binding.tvPrint.setVisibility(View.GONE);
        }
        initList();
    }

    private void initList() {
        double num = 0;
        double storenum = 0;
        isCheck = new ArrayList<>();
        list = new ArrayList<>();
        list = t_detailDao.queryBuilder().where(
                T_DetailDao.Properties.Activity.eq(activity),
                T_DetailDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
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
            EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock+"NO"));
        }
        Lg.e("列表数据：" + gson.toJson(list));
        adapter = new ReViewAdapter(mContext, list, isCheck);
        binding.lvResult.setAdapter(adapter);
//        adapter.setInnerListener(this);
        adapter.notifyDataSetChanged();
        List<String> products = new ArrayList<>();
        products.clear();
        if (list.size() > 0) {
            if (products.size() == 0) {
                products.add(list.get(0).FBarcode);
            }
            for (int i = 0; i < list.size(); i++) {
                if (!products.contains(list.get(i).FBarcode)) {
                    products.add(list.get(i).FBarcode);
                }
                num += MathUtil.toD(list.get(i).FBaseNum);
                storenum += MathUtil.toD(list.get(i).FStoreNum);
            }

            binding.productcategory.setText("已添加数量:" + products.size() + "个");
            binding.productnum.setText("基本数量:" + num +list.get(0).FBaseUnit);
            binding.tvStorenum.setText("库存数量:" + DoubleUtil.Cut4(storenum+"") + list.get(0).FStoreUnit);
        } else {
            binding.productcategory.setText("已添加数量:" + 0 + "个");
            binding.productnum.setText("基本数量:" + 0 + "");
            binding.tvStorenum.setText("库存数量:" + 0 + "");
        }

        mainsList = new ArrayList<>();
        mainsList = t_mainDao.queryBuilder().where(
                T_mainDao.Properties.Activity.eq(activity),
                T_mainDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
        ).build().list();
        Lg.e("表头信息",mainsList);
        if (mainsList.size() > 0) {
            for (int i = 0; i < mainsList.size(); i++) {
                List<T_Detail> details = t_detailDao.queryBuilder().where(
                        T_DetailDao.Properties.FOrderId.eq(mainsList.get(i).FOrderId),
                        T_DetailDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
                ).build().list();
                if (details.size() == 0 || details == null) {
                    t_mainDao.deleteInTx(mainsList.get(i));
                    Hawk.delete(Info.MainData+mainsList.get(i).FOrderId);
                }
            }
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
        binding.tvPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOkPrint){
                    LoadingUtil.showDialog(mContext,"正在重连打印机...");
                    linkBluePrint();
                }else{
                    new AlertDialog.Builder(mContext)
                            .setTitle("是否补打单据？")
                            .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    getAndPrintData();
                                }
                            })
                            .create().show();
                }
//                startNewActivity(PrintHistoryActivity.class, R.anim.activity_slide_left_in, R.anim.activity_slide_left_out, false, null);

            }
        });
    }



    //连接蓝牙打印机
    private void linkBluePrint() {
//        LoadingUtil.showDialog(mContext,"正在连接打印机...");
        new Thread(new Runnable() {
            @Override
            public void run() {
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
        }).start();
    }

    //获取列表相对应的打印数据
    private void getAndPrintData() {
        List<PrintHistory> printHistoryList = new ArrayList<>();
        //收集列表对应的打印数据
        for (int j = 0; j < isCheck.size(); j++) {
            if (isCheck.get(j)) {
                T_Detail t_detail = t_detailDao.queryBuilder().where(
                        T_DetailDao.Properties.FIndex.eq(list.get(j).FIndex)
                ).build().unique();
                PrintHistoryDao printHistoryDao = daoSession.getPrintHistoryDao();
                printHistoryList.add(printHistoryDao.queryBuilder().where(
                        PrintHistoryDao.Properties.FBarCode.eq(t_detail.FBarcode)).build().unique());
            }
        }
        if (printHistoryList.size()<=0){
            Toast.showText(mContext,"请选择需要打印的单据");
            return;
        }
        //遍历逐个打印
        for (PrintHistory bean:printHistoryList) {
            EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Print_Out, bean));
        }
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
            case R.id.delete_all:
                AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                ab.setTitle("确认删除");
                ab.setMessage("确认删除所有么？");
                ab.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int pos) {
                        if (activity == Config.PDActivity) {
                            for (int i = 0; i < list.size(); i++) {
                                List<PDSub> list2 = pdSubDao.queryBuilder().where(
                                        PDSubDao.Properties.FID.eq(list.get(i).FEntryID),
                                        PDSubDao.Properties.FLot_Text.eq(list.get(i).FBatch),
                                        PDSubDao.Properties.FStockID.eq(list.get(i).FStoragePDId),
                                        PDSubDao.Properties.FStockPlaceID.eq(list.get(i).FWaveHousePDId == null ? "" : list.get(i).FWaveHousePDId)
                                ).build().list();
                                if (list2.size() > 0) {
                                    list2.get(0).FCountQty = (MathUtil.toD(list2.get(0).FCountQty) - MathUtil.toD(list.get(i).FCheckQtyDefault)) + "";
                                    pdSubDao.update(list2.get(0));
                                    Lg.e("盘点明细删除：", list2.get(0));
                                } else {
                                    Lg.e("盘点明细删除：无");
                                }
                            }
                        }
                        LoadingUtil.showDialog(mContext, "正在删除...");
                        pBean = new PurchaseInStoreUploadBean();
                        listBean = pBean.new purchaseInStore();
                        data = new ArrayList<>();
                        String detail = "";
                        listBean.main = "";
                        ArrayList<String> detailContainer = new ArrayList<>();
                        for (int j = 0; j < list.size(); j++) {
                            if (j != 0 && j % 49 == 0) {
                                Log.e("j%49", j % 49 + "");
                                T_Detail t_detail = list.get(j);
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
                                T_Detail t_detail = list.get(j);
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
                        App.getRService().doIOAction(WebApi.CodeOnlyDelete, gson.toJson(pBean), new MySubscribe<CommonResponse>() {
                            @Override
                            public void onNext(CommonResponse commonResponse) {
                                super.onNext(commonResponse);
                                if (!commonResponse.state) return;
                                t_detailDao.deleteInTx(t_detailDao.queryBuilder().where(
                                        T_DetailDao.Properties.Activity.eq(activity),
                                        T_DetailDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
                                ).build().list());
                                initList();
                                t_mainDao.deleteInTx(t_mainDao.queryBuilder().where(
                                        T_mainDao.Properties.Activity.eq(activity),
                                        T_mainDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
                                ).build().list());
                                Toast.showText(mContext, "删除成功");
                                initList();
//                                adapter.notifyDataSetChanged();
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
                                final T_main t_main = t_mainDao.queryBuilder().where(
                                        T_mainDao.Properties.FIndex.eq(list.get(j).FIndex)
                                ).build().unique();
                                t_mainsList.add(t_main);
                                Log.e(TAG, "获取到T_Detail:" + t_detail.toString());
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
                        App.getRService().doIOAction(WebApi.CodeOnlyDelete, gson.toJson(pBean), new MySubscribe<CommonResponse>() {
                            @Override
                            public void onNext(CommonResponse commonResponse) {
                                super.onNext(commonResponse);
                                if (!commonResponse.state) return;
                                deleteMain(t_detailList);
                                initList();
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

//                        List<T_Detail> details = new ArrayList<>();
//                        for (int j = 0; j < list.size(); j++) {
//                            if (isCheck.get(j)) {
//                                details.add(t_detailDao.queryBuilder().where(
//                                        T_DetailDao.Properties.FIndex.eq(list.get(j).FIndex)
//                                ).build().unique());
//                            }
//                        }
//                        deleteMain(details);
//                        initList();
                    }
                });
                delete.setNegativeButton("取消", null);
                delete.create().show();
                break;
        }
    }

    //删除相应的表头信息
    private void deleteMain(List<T_Detail> list) {
        TreeSet<String> treeSet = new TreeSet<>();
        for (int i = 0; i < list.size(); i++) {
            treeSet.add(list.get(i).FOrderId + "");
            if (activity == Config.PDActivity) {
                Lg.e("盘点明细删除detail：", list.get(i));
                List<PDSub> list2 = pdSubDao.queryBuilder().where(
                        PDSubDao.Properties.FID.eq(list.get(i).FEntryID),
                        PDSubDao.Properties.FLot_Text.eq(list.get(i).FBatch),
                        PDSubDao.Properties.FStockID.eq(list.get(i).FStoragePDId),
                        PDSubDao.Properties.FStockPlaceID.eq(list.get(i).FWaveHousePDId == null ? "" : list.get(i).FWaveHousePDId)
                ).build().list();
                if (list2.size() > 0) {
                    list2.get(0).FCountQty = (MathUtil.toD(list2.get(0).FCountQty) - MathUtil.toD(list.get(i).FCheckQtyDefault)) + "";
                    pdSubDao.update(list2.get(0));
                    Lg.e("盘点明细删除：", list2.get(0));
                } else {
                    Lg.e("盘点明细删除：无");
                }
            }

        }
        for (String string : treeSet) {
            List<T_Detail> list1 = t_detailDao.queryBuilder().where(
                    T_DetailDao.Properties.FAccountID.eq(CommonUtil.getAccountID()),
                    T_DetailDao.Properties.FOrderId.eq(string)
            ).build().list();

            //当明细小于等于1时，删除该单据的表头数据
            if (list1.size() <= 1) {
                t_mainDao.deleteInTx(t_mainDao.queryBuilder().where(
                        T_mainDao.Properties.FOrderId.eq(string)).build().list());
            }
        }
        t_detailDao.deleteInTx(list);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Lg.e("ReView：","OnPause");
        if (activity==Config.ProductInStoreActivity||activity==Config.TbInActivity||activity==Config.DgInActivity||activity==Config.WorkOrgIn4P2Activity||activity==Config.ChangeInActivity
                ||activity==Config.SimpleInActivity||activity==Config.GbInActivity||activity==Config.DhInActivity||activity==Config.P1PdProductGet2CprkActivity||activity==Config.ChangeLvInActivity
                ||activity==Config.DhIn2Activity||activity==Config.TbIn2Activity||activity==Config.TbIn3Activity||activity==Config.P1PdProductGet2Cprk2Activity||activity==Config.ChangeModelInActivity
                ||activity==Config.SplitBoxInActivity||activity==Config.ZbCheJianInActivity||activity==Config.Bg1CheJianInActivity||activity==Config.Bg2CheJianInActivity
                ||activity==Config.CpWgInActivity
                ||activity==Config.ZbIn1Activity
                ||activity==Config.ZbIn2Activity
                ||activity==Config.ZbIn3Activity
                ||activity==Config.ZbIn4Activity
                ||activity==Config.ZbIn5Activity
                ){
            try {
                zpSDK.disconnect();
            }catch (Exception e){}
        }
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
