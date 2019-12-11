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
import com.fangzuo.assist.cloud.Adapter.ReViewP24WortAdapter;
import com.fangzuo.assist.cloud.Adapter.ReViewP2Adapter;
import com.fangzuo.assist.cloud.Adapter.ReViewPDAP2ForShuibanAdapter;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.PrintHistory;
import com.fangzuo.assist.cloud.Beans.PurchaseInStoreUploadBean;
import com.fangzuo.assist.cloud.Beans.WortPrintData;
import com.fangzuo.assist.cloud.Dao.PDSub;
import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.Dao.T_main;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Utils.CommonUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.DataModel;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.MathUtil;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.assist.cloud.databinding.ActivityReViewP24WortBinding;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.fangzuo.greendao.gen.PDSubDao;
import com.fangzuo.greendao.gen.PrintHistoryDao;
import com.fangzuo.greendao.gen.T_DetailDao;
import com.fangzuo.greendao.gen.T_mainDao;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import butterknife.ButterKnife;
import butterknife.OnClick;
import zpSDK.zpSDK.zpBluetoothPrinter;

public class ReViewP24WortActivity extends BaseActivity {
    ActivityReViewP24WortBinding binding;

    private int activity;
    private List<T_Detail> list;
    private List<T_main> mainsList;
    private ReViewP24WortAdapter adapter;
//    private ReViewPDAP2ForShuibanAdapter adapter4SB;
    private List<String> printBoxCode;
    private List<Boolean> isCheck;
    private PDSubDao pdSubDao;
    private zpBluetoothPrinter zpSDK;
    @Override
    protected void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.Print_Out://打印
                List<WortPrintData> data = (List<WortPrintData>) event.postEvent;
                try {
                    CommonUtil.doPrint4P2Wort(mContext,data,"1");
                } catch (Exception e) {
//                    e.printStackTrace();
                    App.getInstance().connectPrint();
                    LoadingUtil.showAlter(mContext,getString(R.string.error_print),getString(R.string.check_print));
                }
                break;
        }
    }

    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_re_view_p24_wort);
        ButterKnife.bind(this);
//        pdSubDao = daoSession.getPDSubDao();
    }

    @Override
    protected void initData() {
        Intent in = getIntent();
        Bundle extras = in.getExtras();
        activity = extras.getInt("activity");
        //当为产品入库时，初始化打印机并连接

//        if (activity == Config.ProductInStoreActivity||activity==Config.ProductInStore4P2Activity) {
//            zpSDK = App.getInstance().getZpk();
//        } else {
//            binding.tvPrint.setVisibility(View.GONE);
//        }
        initList();
    }

    private void initList() {
        double num = 0;
        double storenum =0;
        LoadingUtil.dismiss();
        isCheck = new ArrayList<>();
        list = new ArrayList<>();
        printBoxCode = new ArrayList<>();
        list = DataModel.getP2WortDetail(mContext,activity);
//        list = t_detailDao.queryBuilder().where(
//                T_DetailDao.Properties.Activity.eq(activity)
//        ).build().list();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                isCheck.add(false);
            }
        } else {//若列表为空，删除所有该activity的表头信息
            t_mainDao.deleteInTx(t_mainDao.queryBuilder().where(T_mainDao.Properties.Activity.eq(activity)).build().list());
            EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock+"NO"));
        }
        Lg.e("列表数据：" + gson.toJson(list));
            adapter = new ReViewP24WortAdapter(mContext, list, isCheck);
            binding.lvResult.setAdapter(adapter);
//        adapter.setInnerListener(this);
            adapter.notifyDataSetChanged();


        List<String> products = new ArrayList<>();
        products.clear();
        if (list.size() > 0) {
            if (products.size() == 0) {
                products.add(list.get(0).FCfBoxCode);
            }
            for (int i = 0; i < list.size(); i++) {
                if (!products.contains(list.get(i).FCfBoxCode)) {
                    products.add(list.get(i).FCfBoxCode);
                }
                num = MathUtil.sum(num+"",list.get(i).FCfQtySum);
                storenum = MathUtil.sum(storenum+"",list.get(i).FCfM2Sum);
            }

            binding.productcategory.setText("已添加数量:" + products.size() + "个");
//            if (activity==Config.ProductInStore4P2Activity){//水版
//                binding.tvStorenum.setVisibility(View.GONE);
//                binding.productnum.setText("基本数量:" + num +"立方米");
//            }else{
                binding.tvStorenum.setText("PCS:" + MathUtil.Cut0(num+"") + "");
                binding.productnum.setText("M2:" + storenum);

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
        mainsList = t_mainDao.queryBuilder().where(T_mainDao.Properties.Activity.eq(activity)).build().list();

        if (mainsList.size() > 0) {
            for (int i = 0; i < mainsList.size(); i++) {
                List<T_Detail> details = t_detailDao.queryBuilder().where(T_DetailDao.Properties.FOrderId.eq(mainsList.get(i).FOrderId)).build().list();
                if (details.size() == 0 || details == null) {
                    t_mainDao.deleteInTx(mainsList.get(i));
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
                    printBoxCode.remove(list.get(i).FCfBoxCode);
                } else {
                    printBoxCode.add(list.get(i).FCfBoxCode);
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
        if (printBoxCode.size()>1){
            Toast.showText(mContext,"请逐个箱码补打");
            return;
        }
        if (printBoxCode.size()==0){
            Toast.showText(mContext,"请选择需要补打的箱码");
            return;
        }
        List<T_Detail> list = t_detailDao.queryBuilder().where(
                T_DetailDao.Properties.Activity.eq(activity),
                T_DetailDao.Properties.FCfBoxCode.eq(printBoxCode.get(0)),
                T_DetailDao.Properties.FIsInBox.eq(0)
        ).build().list();
        if (list.size()>0){
            Toast.showText(mContext,"装箱后才能进行补打");
            return ;
        }
        App.getRService().doIOAction(WebApi.GetWortPrintData, printBoxCode.get(0), new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);
                if (!commonResponse.state)return;
                DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                if (null != dBean && dBean.wortPrintDatas.size()>0) {
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Print_Out, dBean.wortPrintDatas));
                }else{
                    LoadingUtil.showAlter(mContext,"箱码补打查询失败");
                    Lg.e("箱码补打查询失败");
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                LoadingUtil.showAlter(mContext,"箱码补打查询失败",e.getMessage());

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
            case R.id.delete_all:
                AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                ab.setTitle("确认删除");
                ab.setMessage("确认删除所有么？");
                ab.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int pos) {
                        LoadingUtil.showDialog(mContext, "正在删除...");
                        for (int i = 0; i < list.size(); i++) {
                            t_detailDao.deleteInTx(t_detailDao.queryBuilder().where(T_DetailDao.Properties.FCfBoxCode.eq(list.get(i).FCfBoxCode)).build().list());
                        }
                        Toast.showText(mContext, "删除成功");
                        initList();
////                                adapter.notifyDataSetChanged();
//                                LoadingUtil.dismiss();
//                        pBean = new PurchaseInStoreUploadBean();
//                        listBean = pBean.new purchaseInStore();
//                        data = new ArrayList<>();
//                        String detail = "";
//                        listBean.main = "";
//                        ArrayList<String> detailContainer = new ArrayList<>();
//                        for (int j = 0; j < list.size(); j++) {
//                            if (j != 0 && j % 49 == 0) {
//                                Log.e("j%49", j % 49 + "");
//                                T_Detail t_detail = list.get(j);
//                                detail = detail +
//                                        t_detail.FBarcode + "|" +
//                                        t_detail.FRealQty + "|" +
//                                        t_detail.IMIE + "|" +
//                                        t_detail.FOrderId + "|";
//                                detail = detail.subSequence(0, detail.length() - 1).toString();
//                                detailContainer.add(detail);
//                                detail = "";
//                            } else {
//                                Log.e("j", j + "");
//                                T_Detail t_detail = list.get(j);
//                                detail = detail +
//                                        t_detail.FBarcode + "|" +
//                                        t_detail.FRealQty + "|" +
//                                        t_detail.IMIE + "|" +
//                                        t_detail.FOrderId + "|";
//                                Log.e("detail1", detail);
//                            }
//                        }
//                        if (detail.length() > 0) {
//                            detail = detail.subSequence(0, detail.length() - 1).toString();
//                        }
//                        Log.e("detail", detail);
//                        detailContainer.add(detail);
//                        listBean.detail = detailContainer;
//                        data.add(listBean);
//                        pBean.list = data;
//                        Gson gson = new Gson();
//                        App.getRService().doIOAction(WebApi.CodeOnlyDelete, gson.toJson(pBean), new MySubscribe<CommonResponse>() {
//                            @Override
//                            public void onNext(CommonResponse commonResponse) {
//                                super.onNext(commonResponse);
//                                if (!commonResponse.state) return;
//                                t_detailDao.deleteInTx(t_detailDao.queryBuilder().where(
//                                        T_DetailDao.Properties.Activity.eq(activity)).build().list());
//                                initList();
//                                t_mainDao.deleteInTx(t_mainDao.queryBuilder().where(
//                                        T_mainDao.Properties.Activity.eq(activity)).build().list());
//                                Toast.showText(mContext, "删除成功");
//                                initList();
////                                adapter.notifyDataSetChanged();
//                                LoadingUtil.dismiss();
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                super.onError(e);
//                                LoadingUtil.dismiss();
//                                Toast.showText(mContext, "删除失败：" + e.toString());
//                            }
//                        });
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
                            if (isCheck.get(j)){
                                t_detailDao.deleteInTx(t_detailDao.queryBuilder().where(
                                        T_DetailDao.Properties.FCfBoxCode.eq(list.get(j).FCfBoxCode)).build().list());
                            }
                        }
                        Toast.showText(mContext, "删除成功");
                        initList();
//
//                        t_detailList = new ArrayList<>();
//                        t_mainsList = new ArrayList<>();
//                        pBean = new PurchaseInStoreUploadBean();
//                        listBean = pBean.new purchaseInStore();
//                        data = new ArrayList<>();
//                        for (int j = 0; j < isCheck.size(); j++) {
//                            if (isCheck.get(j)) {
////                                Log.e(i + "", isCheck.get(j) + "");
//                                final T_Detail t_detail = t_detailDao.queryBuilder().where(
//                                        T_DetailDao.Properties.FIndex.eq(list.get(j).FIndex)
//                                ).build().unique();
//                                t_detailList.add(t_detail);
//                                final T_main t_main = t_mainDao.queryBuilder().where(
//                                        T_mainDao.Properties.FIndex.eq(list.get(j).FIndex)
//                                ).build().unique();
//                                t_mainsList.add(t_main);
//                                Log.e(TAG, "获取到T_Detail:" + t_detail.toString());
//                            }
//                        }
//                        String detail = "";
//                        listBean.main = "";
//                        ArrayList<String> detailContainer = new ArrayList<>();
//                        for (int j = 0; j < t_detailList.size(); j++) {
//                            if (j != 0 && j % 49 == 0) {
//                                Log.e("j%49", j % 49 + "");
//                                T_Detail t_detail = t_detailList.get(j);
//                                detail = detail +
//                                        t_detail.FBarcode + "|" +
//                                        t_detail.FRealQty + "|" +
//                                        t_detail.IMIE + "|" +
//                                        t_detail.FOrderId + "|";
//                                detail = detail.subSequence(0, detail.length() - 1).toString();
//                                detailContainer.add(detail);
//                                detail = "";
//                            } else {
//                                Log.e("j", j + "");
//                                T_Detail t_detail = t_detailList.get(j);
//                                detail = detail +
//                                        t_detail.FBarcode + "|" +
//                                        t_detail.FRealQty + "|" +
//                                        t_detail.IMIE + "|" +
//                                        t_detail.FOrderId + "|";
//                                Log.e("detail1", detail);
//                            }
//                        }
//                        if (detail.length() > 0) {
//                            detail = detail.subSequence(0, detail.length() - 1).toString();
//                        }
//                        Log.e("detail", detail);
//                        detailContainer.add(detail);
//                        listBean.detail = detailContainer;
//                        data.add(listBean);
//                        pBean.list = data;
//                        Gson gson = new Gson();
//                        App.getRService().doIOAction(WebApi.CodeOnlyDelete, gson.toJson(pBean), new MySubscribe<CommonResponse>() {
//                            @Override
//                            public void onNext(CommonResponse commonResponse) {
//                                super.onNext(commonResponse);
//                                if (!commonResponse.state) return;
//                                deleteMain(t_detailList);
//                                initList();
////                                t_detailDao.deleteInTx(t_detailList);
////                                t_mainDao.deleteInTx(t_mainsList);
////                                Toast.showText(mContext, "删除成功");
////                                initList();
////                                tableAdapter.notifyDataSetChanged();
//                                LoadingUtil.dismiss();
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                super.onError(e);
//                                LoadingUtil.dismiss();
//                                Toast.showText(mContext, "删除失败：" + e.toString());
//                            }
//                        });

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

//    //删除相应的表头信息
//    private void deleteMain(List<T_Detail> list) {
//        TreeSet<String> treeSet = new TreeSet<>();
//        for (int i = 0; i < list.size(); i++) {
//            treeSet.add(list.get(i).FOrderId + "");
//            if (activity == Config.PDActivity) {
//                Lg.e("盘点明细删除detail：", list.get(i));
//                List<PDSub> list2 = pdSubDao.queryBuilder().where(
//                        PDSubDao.Properties.FID.eq(list.get(i).FEntryID),
//                        PDSubDao.Properties.FLot_Text.eq(list.get(i).FBatch),
//                        PDSubDao.Properties.FStockID.eq(list.get(i).FStoragePDId),
//                        PDSubDao.Properties.FStockPlaceID.eq(list.get(i).FWaveHousePDId == null ? "" : list.get(i).FWaveHousePDId)
//                ).build().list();
//                if (list2.size() > 0) {
//                    list2.get(0).FCountQty = (MathUtil.toD(list2.get(0).FCountQty) - MathUtil.toD(list.get(i).FCheckQtyDefault)) + "";
//                    pdSubDao.update(list2.get(0));
//                    Lg.e("盘点明细删除：", list2.get(0));
//                } else {
//                    Lg.e("盘点明细删除：无");
//                }
//            }
//
//        }
//        for (String string : treeSet) {
//            List<T_Detail> list1 = t_detailDao.queryBuilder().where(
//                    T_DetailDao.Properties.FOrderId.eq(string)
//            ).build().list();
//
//            //当明细小于等于1时，删除该单据的表头数据
//            if (list1.size() <= 1) {
//                t_mainDao.deleteInTx(t_mainDao.queryBuilder().where(
//                        T_mainDao.Properties.FOrderId.eq(string)).build().list());
//            }
//        }
//        t_detailDao.deleteInTx(list);
//    }

    @Override
    protected void onPause() {
        super.onPause();
        Lg.e("ReView：","OnPause");
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
