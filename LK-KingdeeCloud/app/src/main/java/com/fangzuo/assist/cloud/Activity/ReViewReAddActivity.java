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
import com.fangzuo.assist.cloud.Adapter.ReViewAdapter;
import com.fangzuo.assist.cloud.Adapter.ReViewReAddAdapter;
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
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.MathUtil;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.assist.cloud.databinding.ActivityReViewReAddBinding;
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

public class ReViewReAddActivity extends BaseActivity {
    ActivityReViewReAddBinding binding;
    private int activity;
    private List<T_Detail> list;
    private List<T_main> mainsList;
    private ReViewReAddAdapter adapter;
    private List<Boolean> isCheck;
    private PDSubDao pdSubDao;
    private zpBluetoothPrinter zpSDK;
    private BlueToothBean bean;
    boolean isOkPrint=false;
    @Override
    protected void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
        }
    }

    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_re_view_re_add);
        ButterKnife.bind(this);
        pdSubDao = daoSession.getPDSubDao();
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
        Lg.e("列表数据：",list);
        adapter = new ReViewReAddAdapter(mContext, list, isCheck);
        binding.lvResult.setAdapter(adapter);
//        adapter.setInnerListener(this);
        adapter.notifyDataSetChanged();
//        List<String> products = new ArrayList<>();
//        products.clear();
        if (list.size() > 0) {
//            if (products.size() == 0) {
//                products.add(list.get(0).FBarcode);
//            }
            for (int i = 0; i < list.size(); i++) {
//                if (!products.contains(list.get(i).FBarcode)) {
//                    products.add(list.get(i).FBarcode);
//                }
//                num += MathUtil.toD(list.get(i).FBaseNum);
                storenum += MathUtil.toD(list.get(i).FRealQty);
            }

            binding.productcategory.setText("已添加数量:" + list.size() + "个");
//            binding.productnum.setText("基本数量:" + num +list.get(0).FBaseUnit);
            binding.tvStorenum.setText("数量:" + DoubleUtil.Cut4(storenum+"") + list.get(0).FStoreUnit);
        } else {
            binding.productcategory.setText("已添加数量:" + 0 + "个");
//            binding.productnum.setText("基本数量:" + 0 + "");
            binding.tvStorenum.setText("数量:" + 0 + "");
        }

        mainsList = new ArrayList<>();
        mainsList = t_mainDao.queryBuilder().where(
                T_mainDao.Properties.Activity.eq(activity),
                T_mainDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
        ).build().list();

        if (mainsList.size() > 0) {
            for (int i = 0; i < mainsList.size(); i++) {
                List<T_Detail> details = t_detailDao.queryBuilder().where(
                        T_DetailDao.Properties.FOrderId.eq(mainsList.get(i).FOrderId),
                        T_DetailDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
                ).build().list();
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
            case R.id.delete_all:
                AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                ab.setTitle("确认删除");
                ab.setMessage("确认删除所有么？");
                ab.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int pos) {
//                        LoadingUtil.showDialog(mContext, "正在删除...");
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
                            }
                        }
                        deleteMain(t_detailList);
                        initList();
                        LoadingUtil.dismiss();
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

