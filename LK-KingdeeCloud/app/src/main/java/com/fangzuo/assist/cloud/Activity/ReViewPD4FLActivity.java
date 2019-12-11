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
import com.fangzuo.assist.cloud.Adapter.ReViewPD4FLAdapter;
import com.fangzuo.assist.cloud.Adapter.ReViewPDAdapter;
import com.fangzuo.assist.cloud.Beans.BlueToothBean;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.PrintDataBean;
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
import com.fangzuo.assist.cloud.databinding.ActivityReViewPd4FlBinding;
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

public class ReViewPD4FLActivity extends BaseActivity {
    ActivityReViewPd4FlBinding binding;
    private int activity;
    private String  fid;
    //    private T_main main;
    private List<T_Detail> list;
    private List<T_main> list_main;
    private ReViewPD4FLAdapter adapter;
    private List<Boolean> isCheck;
    private List<T_main> mainsList;
//    private zpBluetoothPrinter zpSDK;
//    private BlueToothBean bean;
//    boolean isOkPrint=false;
    private List<String> printBoxCode;

    @Override
    protected void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.Print_Out://打印
                String data = (String) event.postEvent;
                try {
                    List<PrintDataBean> list =Hawk.get(data, new ArrayList<PrintDataBean>());
                    if (list.size()>0){
                        CommonUtil.doPrint4P1BoxCode4BoxReAdd32(mContext, Hawk.get(data, new ArrayList<PrintDataBean>()), "1",false);
                    }else{
                        Toast.showText(mContext,"单据未装箱，不存在本地打印数据");
                    }
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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_re_view_pd4_fl);
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
//        //当为产品入库时，初始化打印机并连接
//        if (activity == Config.PdBackMsg2SaleBackActivity) {
//            zpSDK = new zpBluetoothPrinter(this);
//            bean = Hawk.get(Config.OBJ_BLUETOOTH, new BlueToothBean("", ""));
//            linkBluePrint();
//        } else {
//            binding.tvPrint.setVisibility(View.GONE);
//        }
        initList();
    }

    private void initList() {
        double num = 0;
        isCheck = new ArrayList<>();
        list = new ArrayList<>();
        printBoxCode = new ArrayList<>();
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
        adapter = new ReViewPD4FLAdapter(mContext, list, isCheck);
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

        mainsList = new ArrayList<>();
        mainsList = t_mainDao.queryBuilder().where(
                T_mainDao.Properties.Activity.eq(activity),
                T_mainDao.Properties.FID.eq(fid),
                T_mainDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
        ).build().list();
        if (mainsList.size() > 0) {
            for (int i = 0; i < mainsList.size(); i++) {
                List<T_Detail> details = t_detailDao.queryBuilder().where(
                        T_DetailDao.Properties.Activity.eq(activity),
                        T_DetailDao.Properties.FOrderId.eq(mainsList.get(i).FOrderId),
                        T_DetailDao.Properties.FID.eq(fid),
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
                    printBoxCode.remove(list.get(i).FCfBoxCode);
                    isCheck.set(i, false);
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

//    //连接蓝牙打印机
//    private void linkBluePrint() {
////        LoadingUtil.showDialog(mContext,"正在连接打印机...");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if (bean.address.equals("")) {
//                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Print_Check, "NOOK"));
//                } else {
//                    if (!zpSDK.connect(bean.address)) {
//                        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Print_Check, "NOOK"));
//                    } else {
//                        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Print_Check, "OK"));
//                    }
//                }
//            }
//        }).start();
//    }

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
//                                Log.e(TAG, "获取到T_Detail:" + t_detail.toString());
                            }
                        }
                        boolean hasBox = false;
                        for (T_Detail bean:t_detailList) {
                            if (bean.FIsInBox==1){
                                hasBox = true;
                            }
                        }
                        if (hasBox){
                            Toast.showText(mContext,"不能删除已装箱的单据");
                            return;
                        }

                        deleteMain(t_detailList);

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
//                                Lg.e("删除请求成功");
//                                deleteMain(t_detailList);
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

//                        List<T_Detail> details = new ArrayList<>();
//                        for (int j = 0; j < list.size(); j++) {
//                            if (isCheck.get(j)) {
//                                details.add(t_detailDao.queryBuilder().where(
//                                        T_DetailDao.Properties.FIndex.eq(list.get(j).FIndex)
//                                ).build().unique());
//                                PushDownSubDao pushDownSubDao = daoSession.getPushDownSubDao();
//                                List<PushDownSub> pushDownSubs = pushDownSubDao.queryBuilder().where(
//                                        PushDownSubDao.Properties.FEntryID.eq(list.get(j).FEntryID)
//                                ).build().list();
//                                Lg.e(pushDownSubs.size() + "多少个");
//                                if (pushDownSubs.size() > 0) {
//                                    //删除后，更新数据里面的已验收数
//                                    double result = MathUtil.toD(list.get(j).FRealQty);
//                                    pushDownSubs.get(0).FQtying = MathUtil.doubleSub(MathUtil.toD(pushDownSubs.get(0).FQtying), result) + "";
//                                    pushDownSubDao.update(pushDownSubs.get(0));
//                                }
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
    private void deleteMain(List<T_Detail> listsss){
        for (int j = 0; j < list.size(); j++) {//这里要以列表数据为基础，不能以过滤出来的数据
            if (isCheck.get(j)) {
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
            }
        }
//
//
//        TreeSet<String> treeSet = new TreeSet<>();
//        for (int i = 0; i < listsss.size(); i++) {
//            treeSet.add(listsss.get(i).FOrderId+"");
//        }
//        for (String string:treeSet) {
//            List<T_Detail> list1=t_detailDao.queryBuilder().where(
//                    T_DetailDao.Properties.FAccountID.eq(CommonUtil.getAccountID()),
//                    T_DetailDao.Properties.FOrderId.eq(string)
//            ).build().list();
//            //当明细小于等于1时，删除该单据的表头数据
//            if (list1.size()<=1){
//                t_mainDao.deleteInTx(t_mainDao.queryBuilder().where(
//                        T_mainDao.Properties.FOrderId.eq(string)).build().list());
//            }
//        }
        t_detailDao.deleteInTx(listsss);
        LoadingUtil.dismiss();
        initList();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Lg.e("ReView：","OnPause");
//        if (activity==Config.PdBackMsg2SaleBackActivity){
//            try {
//                zpSDK.disconnect();
//            }catch (Exception e){}
//        }
    }


    @Override
    protected void OnReceive(String code) {

    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }


//    private double getUnitrateSub(PushDownSub pushDownSub) {
//        UnitDao unitDao = daoSession.getUnitDao();
//        List<Unit> units = unitDao.queryBuilder().where(
//                UnitDao.Properties.FMeasureUnitID.eq(pushDownSub.FUnitID)
//        ).build().list();
//        if (units.size() > 0) {
//            return Double.valueOf(units.get(0).FCoefficient);
////            Lg.e("获得明细换算率：" + unitrateSub);
//        } else {
//            return  1;
////            Lg.e("获得明细换算率失败：" + unitrateSub);
//        }
//    }
}
