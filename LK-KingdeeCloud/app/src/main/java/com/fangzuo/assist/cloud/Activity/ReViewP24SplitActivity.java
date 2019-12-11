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
import com.fangzuo.assist.cloud.Adapter.ReViewP24SplitAdapter;
import com.fangzuo.assist.cloud.Adapter.ReViewP24WortAdapter;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
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
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.MathUtil;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.assist.cloud.databinding.ActivityReViewP24SplitBinding;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.fangzuo.greendao.gen.PDSubDao;
import com.fangzuo.greendao.gen.T_DetailDao;
import com.fangzuo.greendao.gen.T_mainDao;
import com.fangzuo.greendao.gen.WortPrintDataDao;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import butterknife.ButterKnife;
import butterknife.OnClick;
import zpSDK.zpSDK.zpBluetoothPrinter;

public class ReViewP24SplitActivity extends BaseActivity {
    ActivityReViewP24SplitBinding binding;
//    private int activity;
    private String  boxCode;
    private List<WortPrintData> list;
    private List<T_main> mainsList;
    private ReViewP24SplitAdapter adapter;
    //    private ReViewPDAP2ForShuibanAdapter adapter4SB;
    private List<String> printBoxCode;
    private List<Boolean> isCheck;
    private WortPrintDataDao wortPrintDataDao;
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
                    LoadingUtil.showAlter(this,getString(R.string.error_print),getString(R.string.check_print));
                }
                break;
        }
    }

    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_re_view_p24_split);
        ButterKnife.bind(this);
        wortPrintDataDao = daoSession.getWortPrintDataDao();
    }

    @Override
    protected void initData() {
        Intent in = getIntent();
        Bundle extras = in.getExtras();
        boxCode = extras.getString("boxcode");
        //当为产品入库时，初始化打印机并连接

//        if (activity == Config.ProductInStoreActivity||activity==Config.ProductInStore4P2Activity) {
        zpSDK = App.getInstance().getZpk();
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
        List<WortPrintData> listTemp = DataModel.getP2SplitDetail(mContext,boxCode);
//        List<WortPrintData> listTemp = daoSession.getWortPrintDataDao().queryBuilder().where(
//                WortPrintDataDao.Properties.FBoxCode.eq(boxCode)
//        ).build().list();
        Lg.e("Hawk里面的数据",Hawk.get(Info.SplitBoxTempPrintData,new ArrayList<WortPrintData>()));
        Lg.e("listTemp里面的数据",listTemp);
        for (int i = 0; i < listTemp.size(); i++) {
            if (MathUtil.toInt(listTemp.get(i).FQtySplit)!=0 && null!=listTemp.get(i).FSplitBoxCode){
                list.add(listTemp.get(i));
            }
        }
        if (list.size()==0){
            list = Hawk.get(Info.SplitBoxTempPrintData,new ArrayList<WortPrintData>());
        }
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                isCheck.add(false);
            }
        }
        Lg.e("列表数据：",list);
        adapter = new ReViewP24SplitAdapter(mContext, list, isCheck);
        binding.lvResult.setAdapter(adapter);
//        adapter.setInnerListener(this);
        adapter.notifyDataSetChanged();

        if (list.size() > 0) {

            for (int i = 0; i < list.size(); i++) {
                num = MathUtil.sum(num+"",MathUtil.toInt(list.get(i).FQtySplit)+"");
                storenum = MathUtil.sum(storenum+"",MathUtil.toInt(list.get(i).FM2Split)+"");
            }

            binding.productcategory.setText("已添加数量:" + list.size() + "个");
            binding.tvStorenum.setText("PCS:" +list.get(0).FQtySplit);
            binding.productnum.setText("M2:" + list.get(0).FM2Split);

        } else {
            binding.tvStorenum.setVisibility(View.GONE);
//            if (activity==Config.ProductInStore4P2Activity){
//            }else{
//                binding.tvStorenum.setText("库存数量:0");
//            }
            binding.productcategory.setText("已添加数量:" + 0 + "个");
            binding.tvStorenum.setText("PCS:" + "0");
            binding.productnum.setText("M2:" + "0");
        }

    }



    @Override
    protected void initListener() {
        binding.lvResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (isCheck.get(i)) {
                    isCheck.set(i, false);
                    printBoxCode.remove(list.get(i).FSplitBoxCode);
                } else {
                    printBoxCode.add(list.get(i).FSplitBoxCode);
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
        App.getRService().doIOAction(WebApi.GetWortPrintData, printBoxCode.get(0), new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);
                if (!commonResponse.state)return;
                DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                if (null != dBean && dBean.wortPrintDatas.size()>0) {
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Print_Out, dBean.wortPrintDatas));
                }else{
                    LoadingUtil.showAlter(mContext,"未拆箱条码不能补打");
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
                            try{
                                list.get(i).FQtySplit="0";
                                list.get(i).FM2Split="0";
                                wortPrintDataDao.update(list.get(i));
                            }catch (Exception e){
                                Hawk.delete(Info.SplitBoxTempPrintData);
                            }

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
                                try{
                                    list.get(j).FQtySplit="0";
                                    list.get(j).FM2Split="0";
                                    wortPrintDataDao.update(list.get(j));
                                }catch (Exception e){
                                    Hawk.delete(Info.SplitBoxTempPrintData);
                                }

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
