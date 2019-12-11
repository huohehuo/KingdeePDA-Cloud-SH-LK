package com.fangzuo.assist.cloud.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Adapter.SplitBoxAdapter;
import com.fangzuo.assist.cloud.Adapter.SplitBoxP1Adapter;
import com.fangzuo.assist.cloud.Adapter.StringSpAdapter;
import com.fangzuo.assist.cloud.Beans.CommonBean;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.PrintDataBean;
import com.fangzuo.assist.cloud.Beans.PurchaseInStoreUploadBean;
import com.fangzuo.assist.cloud.Beans.WortPrintData;
import com.fangzuo.assist.cloud.Dao.NumberBean;
import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Utils.CommonUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.DataModel;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.JsonCreater;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.MathUtil;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.fangzuo.assist.cloud.zxing.ScanManager;
import com.fangzuo.greendao.gen.T_DetailDao;
import com.fangzuo.greendao.gen.WortPrintDataDao;
import com.google.gson.Gson;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zpSDK.zpSDK.zpBluetoothPrinter;

public class SplitBoxP1Activity extends BaseActivity {
    @BindView(R.id.btn_back)
    RelativeLayout btnBack;
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    private int activity = Config.SplitBoxP2Activity;

    @BindView(R.id.zxing_barcode_scanner)
    DecoratedBarcodeView zxingBarcodeScanner;
    @BindView(R.id.btn_pic)
    Button btnPic;
    @BindView(R.id.ly_scan)
    RelativeLayout lyScan;
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.tv_print)
    TextView tvPrint;
    @BindView(R.id.tv_goodName)
    TextView tvGoodName;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.tv_lenght)
    TextView tvQtyAll;
    @BindView(R.id.tv_wide)
    TextView tvVolAll;
    @BindView(R.id.tv_pcs_sum)
    AppCompatTextView tvPcsSum;
    @BindView(R.id.tv_hj_pcs)
    AppCompatTextView tvPcsSplitSum;
    @BindView(R.id.tv_hj_m3)
    AppCompatTextView tvM2Sum;
    @BindView(R.id.ry_wide)
    EasyRecyclerView ryWide;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_finishorder)
    Button btnFinishorder;
    @BindView(R.id.btn_backorder)
    Button btnBackorder;
    @BindView(R.id.btn_checkorder)
    Button btnCheckorder;

    private SplitBoxP1Adapter adapter;
    private StringSpAdapter adapterLenght;
    private List<NumberBean> lenghtList;
    private String cfLenght;
    private String boxCode;
    private String splitBoxCode;
    private long ordercode;
    private zpBluetoothPrinter zpSDK;
    private ArrayList<PrintDataBean> listAll;
    private Context mContext;

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.ScanResult:
                BarcodeResult res = (BarcodeResult) event.postEvent;
//                if (cbScaning.isChecked()) {
//                } else {
                mCaptureManager.onPause();
                lyScan.setVisibility(View.GONE);
//                }
                OnReceive(res.getResult().getText());
//                Toast.showText(mContext, "扫描结果：" + res.getResult().getText());
                break;
            case EventBusInfoCode.Updata_OK://回单成功
                String time = (String) event.Msg2;
                String size = (String) event.Msg3;
                long endTime = System.currentTimeMillis();
                AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                ab.setTitle("下载完成");
                ab.setMessage("耗时:" + (endTime - Long.parseLong(time)) + "ms" + ",共插入" + size + "条数据");
                ab.setPositiveButton("确认", null);
                ab.create().show();
                break;
            case EventBusInfoCode.Updata_Error://回单失败
                Toast.showText(mContext, (String) event.postEvent);
                break;
            case EventBusInfoCode.Click_Order://回单失败
                int activity = (int) event.postEvent;
//                clickOrder(activity);
                break;
            case EventBusInfoCode.Print_Error_And_Retry://回单失败
//                int activity = (int) event.postEvent;
//                clickOrder(activity);
                break;
//            case EventBusInfoCode.Print_Check://检测打印机连接状态
//                String msg = (String) event.postEvent;
//                LoadingUtil.dismiss();
//                if ("OK".equals(msg)) {
//                    tvPrint.setText("打印机就绪");
//                    tvPrint.setTextColor(Color.BLACK);
//                    zpSDK = App.getInstance().getZpk();
//                } else {
//                    AlertDialog.Builder printAb = new AlertDialog.Builder(mContext);
//                    printAb.setTitle("连接打印机错误,请到先配置蓝牙打印机");
////            printAb.setMessage("确认？");
//                    printAb.setPositiveButton("前往", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            startNewActivity(SplitBoxP2Activity.class, R.anim.activity_slide_left_in, R.anim.activity_slide_left_out, false, null);
////                            activityPager.finish();
//                        }
//                    });
//                    printAb.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
////                            activityPager.finish();
//                        }
//                    });
//                    printAb.setNeutralButton("重连", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            LoadingUtil.showDialog(mContext, "正在重连...");
//                            new Thread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    App.getInstance().connectPrint();
////                                    checkPrint(false);
//                                }
//                            }).start();
//                        }
//                    });
//                    printAb.create().show();
//                    tvPrint.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            App.getInstance().connectPrint();
////                            startNewActivity(activityPager, PrintOutTestActivity.class, R.anim.activity_slide_left_in, R.anim.activity_slide_left_out, false, null);
////                            activityPager.finish();
//                        }
//                    });
//                    tvPrint.setText("连接打印机错误");
//                    tvPrint.setTextColor(Color.RED);
//                }
//                break;

        }
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_split_box_p1);
        ButterKnife.bind(this);
        mContext = SplitBoxP1Activity.this;
        tvTitle.setText("拆箱");
        //摄像头初始化
        mCaptureManager = new ScanManager(this, zxingBarcodeScanner);
        mCaptureManager.initializeFromIntent(getIntent(), savedInstanceState);
        ryWide.setAdapter(adapter = new SplitBoxP1Adapter(mContext));
        ryWide.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        zpSDK = new zpBluetoothPrinter(this);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                App.getInstance().connectPrint();
//            }
//        }).start();
    }

    private AlertDialog alertDialog=null;
    @Override
    protected void initData() {
//        ordercode = CommonUtil.createOrderCode(this);//单据编号
        listAll = new ArrayList<>();
//        boxCode = Hawk.get(Info.BoxCode + activity, "");
//        splitBoxCode = Hawk.get(Info.SplitBoxCode + activity, "");
//        if ("".equals(boxCode)) {
//            search.setVisibility(View.VISIBLE);
//        } else {
//            search.setVisibility(View.GONE);
//            loadLocData(boxCode);
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        loadLocData(boxCode);
    }

    @Override
    protected void initListener() {
        //装箱
        btnAdd.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                new AlertDialog.Builder(mContext)
                        .setTitle("是否拆箱？")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                spliting();
                            }
                        })
                        .create().show();
//                addCountOff();
//                checkMainDlg();
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final int position) {
                changeDetail(adapter.getAllData().get(position));
//                new AlertDialog.Builder(mContext)
//                        .setTitle("是否清空拆箱数：" + adapter.getAllData().get(position).FQtySplit)
//                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                clearBoxSplitData(adapter.getAllData().get(position));
//                            }
//                        })
//                        .setNeutralButton("取消", null)
//                        .create().show();
            }
        });
//        adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(final int position) {
//                Lg.e("点击历史：", adapter.getAllData().get(position));
//                new AlertDialog.Builder(mContext)
//                        .setTitle("是否清空拆箱数：" + adapter.getAllData().get(position).FQtySplit)
//                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                clearBoxSplitData(adapter.getAllData().get(position));
//                            }
//                        })
//                        .setNeutralButton("取消", null)
//                        .create().show();
//                return true;
//            }
//        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void OnReceive(final String code) {
        boxCode = code;
        adapter.clear();
        listAll.clear();
        App.getRService().doIOAction(WebApi.GetBoxPrintData, code, new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);
                if (!commonResponse.state) return;
                DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                if (null != dBean && dBean.printDataBeans.size() > 0) {
                    listAll.addAll(dBean.printDataBeans);
                    splitBoxCode = dBean.printDataBeans.get(0).FBoxCode;
//                    Hawk.put(Info.BoxCode+activity,code);
//                    WortPrintDataDao wortPrintDataDao = daoSession.getWortPrintDataDao();
//                    wortPrintDataDao.deleteAll();
//                    wortPrintDataDao.insertOrReplaceInTx(dBean.wortPrintDatas);
//                    wortPrintDataDao.detachAll();
                    loadLocData(dBean.printDataBeans);
                } else {
                    LoadingUtil.showAlter(mContext, "装箱码打印信息查询为空");
                    Lg.e("装箱码打印信息查询失败");
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                LoadingUtil.showAlter(mContext, "装箱码打印信息查询失败", e.getMessage());

            }
        });
    }

    //加载本地相关箱码的数据
    private boolean hasSplitData = true;//是否存在已拆箱数据
    private void loadLocData(ArrayList<PrintDataBean> list) {
        adapter.clear();
        hasSplitData = true;
//        List<WortPrintData> list = daoSession.getWortPrintDataDao().queryBuilder().where(
//                WortPrintDataDao.Properties.FBoxCode.eq(code)
//        ).build().list();
//        Lg.e("列表数据" + list.size(), list);

        if (list.size() > 0) {
            adapter.addAll(list);
            tvGoodName.setText(list.get(0).FName);
            tvPcsSum.setText(list.get(0).FQtyAll);
            tvQtyAll.setText(list.get(0).FQtyAll);
            tvVolAll.setText(list.get(0).FVolAll);
        } else {
            tvGoodName.setText("");
            tvM2Sum.setText("");
            tvPcsSum.setText("");
            tvVolAll.setText("");
            adapter.clear();
            search.setVisibility(View.VISIBLE);
        }
        tvPcsSplitSum.setText(adapter.getAllPcsSplit());
        tvM2Sum.setText(adapter.getSplitM3());

        //拆箱数是否大于0
        if (MathUtil.toInt(adapter.getAllPcsSplit())==0){
            hasSplitData = false;
        }
//        //处理箱码中的数据，过滤出长度信息
//        lenghtList = new ArrayList<>();
//        for (WortPrintData bean : list) {
//            lenghtList.add(new NumberBean(MathUtil.toInt(bean.FLenght)));
//        }
//        adapterLenght = new StringSpAdapter(mContext, lenghtList);
//        spLenght.setAdapter(adapterLenght);

    }//显示报数和与报数相关的数据
    public void loadCountOff() {
//        adapterList.clear();
        adapter.clear();
        adapter.addAll(listAll);
//        List<T_Detail> list = daoSession.getT_DetailDao().queryBuilder().where(
//                T_DetailDao.Properties.FOrderId.eq(ordercode),
//                T_DetailDao.Properties.FBoxCodeOrder.eq(boxcodeOrder)
//        ).orderAsc(T_DetailDao.Properties.FCfLenght).build().list();
//        adapter.addAll(list);
//        for (int i = 0; i < list.size(); i++) {
////            int number = list.get(i).FCountNumber;
////            if (i > 0 && list.get(i).FCountNumber == list.get(i - 1).FCountNumber) {
////            } else {
////            //筛选出报数非0的报数数据
////                List<T_Detail> tempList = daoSession.getT_DetailDao().queryBuilder().where(
////                        T_DetailDao.Properties.FCountNumber.notEq(0)
////                ).where(
////                        T_DetailDao.Properties.FOrderId.eq(ordercode),
////                        T_DetailDao.Properties.FCountNumber.eq(number)
////                ).orderAsc(T_DetailDao.Properties.FCountNumber).build().list();
////                if (tempList.size()>0){
////                    adapterList.add(new CountOffBeanList(number, tempList));
////                }
////            }
//            //报数号为0的对象为临时添加对象
////            if (number==0){
//            adapter.add(list.get(i));
////            }
//        }
        tvPcsSplitSum.setText(adapter.getAllPcsSplit());
        tvM2Sum.setText(adapter.getSplitM3());
    }

    //操作明细数据
    private void changeDetail(final PrintDataBean detail) {
        AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
        ab.setTitle("修改数量："+detail.FQty);
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_wort_show, null);
        final EditText editText = v.findViewById(R.id.ed_num);
        editText.setText(detail.FQty);
        ab.setView(v);
        ab.setPositiveButton("修改", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeDetailQty(detail,editText.getText().toString());
            }
        });
        ab.setNegativeButton("取消",null);
//        ab.setNeutralButton("删除", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                delCountOffTemp(detail);
//            }
//        });
        ab.create().show();
    }
    //修改数量
    public void changeDetailQty(PrintDataBean detail,String qty){
        if (MathUtil.toD(qty)>0){
            for (int i = 0; i < listAll.size(); i++) {
                if (listAll.get(i).FEntryID.equals(detail.FEntryID)){
                    if (MathUtil.toD(listAll.get(i).FQty)>=MathUtil.toD(qty)){
                        double cshu=MathUtil.div(MathUtil.toD(qty),MathUtil.toD(listAll.get(i).FQty),10);
                        listAll.get(i).FVolSplit=(MathUtil.toD(listAll.get(i).FVol)*cshu)+"";
                        listAll.get(i).FQtySplit = qty;
                    }else{
                        Toast.showText(mContext,"拆箱数必须小于或等于原数量");
                    }
                }
            }
            loadCountOff();
        }else{
            Toast.showText(mContext,"修改数量不能小于0");
        }
    }

    //清空拆箱数
    private void clearBoxSplitData(PrintDataBean bean) {
        List<WortPrintData> list = daoSession.getWortPrintDataDao().queryBuilder().where(
                WortPrintDataDao.Properties.FBoxCode.eq(bean.FBoxCode)
//                WortPrintDataDao.Properties.FLenght.eq(bean.FLenght)
        ).build().list();
        if (list.size() > 0) {
            list.get(0).FQtySplit = "0";
            list.get(0).FM2Split = "0";
            daoSession.getWortPrintDataDao().updateInTx(list.get(0));
            adapter.notifyDataSetChanged();
        } else {
            Toast.showText(mContext, "添加拆箱数量失败");
        }
    }

    //先判断是否存在箱码和批号信息，再去添加
    private void getSplitBoxCode() {
        if ("".equals(splitBoxCode)) {
            String json = Hawk.get(Info.user_id, "") + "|" + tvQtyAll.getText().toString() + "|" +
                    tvVolAll.getText().toString() + "|" + tvGoodName.getText().toString();
            App.getRService().doIOAction(WebApi.getSplitBoxCode, json, new MySubscribe<CommonResponse>() {
                @Override
                public void onNext(CommonResponse commonResponse) {
                    super.onNext(commonResponse);
                    if (!commonResponse.state) return;
                    CommonBean dBean = JsonCreater.gson.fromJson(commonResponse.returnJson, CommonBean.class);
                    splitBoxCode = dBean.FStandby1;
                    Hawk.put(Info.SplitBoxCode + activity, splitBoxCode);
                    changeBoxData();
                }

                @Override
                public void onError(Throwable e) {
//                    boxcode="";
//                    batch = "";
                    Toast.showText(mContext, "获取箱码数据失败");
//                    super.onError(e);
                }
            });
        } else {
            changeBoxData();
        }
    }

    //修改数据
    private void changeBoxData() {

            return;

//        if (MathUtil.toInt(edNum.getText().toString()) == 0) {
//            Toast.showText(mContext, "请输入需要拆箱的数量");
//        }
//        WortPrintDataDao printDataDao = daoSession.getWortPrintDataDao();
//        List<WortPrintData> list = printDataDao.queryBuilder().where(
//                WortPrintDataDao.Properties.FBoxCode.eq(boxCode),
//                WortPrintDataDao.Properties.FLenght.eq(cfLenght == null ? "" : cfLenght)
//        ).build().list();
//        if (list.size() > 0) {
//            if (MathUtil.toD(list.get(0).FQty)>=MathUtil.toD(edNum.getText().toString())){
//                Lg.e("修改前", list.get(0));
//                list.get(0).FQtySplit = edNum.getText().toString();
//                list.get(0).FSplitBoxCode = splitBoxCode;
//                list.get(0).FM2Split = (MathUtil.toD(list.get(0).FLenght) * MathUtil.toD(list.get(0).FKuan) * MathUtil.toD(edNum.getText().toString()) / 1000000) + "";
//                Lg.e("修改后", list.get(0));
//                printDataDao.update(list.get(0));
//                loadLocData(boxCode);
//                Hawk.put(Info.BoxCode + activity, boxCode);
//                edNum.setText("");
//            }else{
//                Toast.showText(mContext,"拆箱数不能超过原有数量");
//            }
//
//        } else {
//            Toast.showText(mContext, "添加拆箱数量失败");
//        }
    }

    //拆箱
    private void spliting() {
        PurchaseInStoreUploadBean pBean = new PurchaseInStoreUploadBean();
        PurchaseInStoreUploadBean.purchaseInStore puBean = pBean.new purchaseInStore();
        ArrayList<String> detailContainer = new ArrayList<>();
        ArrayList<PurchaseInStoreUploadBean.purchaseInStore> data = new ArrayList<>();
//        List<WortPrintData> list = daoSession.getWortPrintDataDao().queryBuilder().where(
//                WortPrintDataDao.Properties.FBoxCode.eq(boxCode)
//        ).build().list();
//        if (list.size() > 0) {
//            String[] split = list.get(0).FModel.split("x", 3);
//            if (split.length == 3) {
//                tvQtyAll.setText(split[0]);
//                tvVolAll.setText(split[1]);
//                tvThick.setText(split[2]);
//            }
            StringBuffer stringBuffer = new StringBuffer()
                    .append(Hawk.get(Info.user_id, "")).append("|")
//                    .append(tvQtyAll.getText().toString()).append("|")
//                    .append(tvVolAll.getText().toString()).append("|")
//                    .append(tvThick.getText().toString()).append("|")
//                    .append("").append("|")
//                    .append("").append("|")
//                    .append(list.get(0).FSplitBoxCode).append("|")
//                    .append(list.get(0).FBatch).append("|");
                    .append(splitBoxCode).append("|");
            puBean.main = stringBuffer.toString();
            String detail = "";
        for (int i = 0; i < listAll.size(); i++) {
            if (MathUtil.toD(listAll.get(i).FQtySplit)>0){
                detail = detail +listAll.get(i).FQtySplit+"|"+listAll.get(i).FEntryID+"|";
            }
        }
//            for (int j = 0; j < list.size(); j++) {
//                if (MathUtil.toInt(list.get(j).getFQtySplit()) != 0) {
//                    if (j != 0 && j % 49 == 0) {
//                        Log.e("j%49", j % 49 + "");
//                        WortPrintData t_detail = list.get(j);
//                        detail = detail +
//                                t_detail.FItemID + "|" +
//                                t_detail.FUnitID + "|" +
//                                t_detail.FQtySplit + "|" +
//                                t_detail.FDCStockID + "|" +
//                                t_detail.FDCSPID + "|" +
//                                t_detail.FSTOCKORGID + "|" +
//                                t_detail.FOWNERID + "|" +
//                                t_detail.FLenght + "|" +
//                                t_detail.FM2Split + "|" +
//                                t_detail.FID + "|" +
//                                t_detail.FEntryID + "|";
//                        detail = detail.subSequence(0, detail.length() - 1).toString();
//                        detailContainer.add(detail);
//                        detail = "";
//                    } else {
//                        Log.e("j", j + "");
//                        Log.e("details.size()", list.size() + "");
//                        WortPrintData t_detail = list.get(j);
//                        detail = detail +
//                                t_detail.FItemID + "|" +
//                                t_detail.FUnitID + "|" +
//                                t_detail.FQtySplit + "|" +
//                                t_detail.FDCStockID + "|" +
//                                t_detail.FDCSPID + "|" +
//                                t_detail.FSTOCKORGID + "|" +
//                                t_detail.FOWNERID + "|" +
//                                t_detail.FLenght + "|" +
//                                t_detail.FM2Split + "|" +
//                                t_detail.FID + "|" +
//                                t_detail.FEntryID + "|";
//                        Log.e("detail1", detail);
//                    }
//                }//else 为发生拆箱数据变化的不做凭借
//            }

            if (detail.length() > 0) {
                detail = detail.subSequence(0, detail.length() - 1).toString();
            }else{
                Toast.showText(mContext,"没有拆箱操作");
                return;
            }

            Log.e("detail", detail);
            detailContainer.add(detail);
            puBean.detail = detailContainer;
            data.add(puBean);

//        } else {
//            Toast.showText(mContext, "本地无拆箱数据");
//        }
//        for (int i = 0; i < mains.size(); i++) {
//            if (i > 0 && mains.get(i).FOrderId == mains.get(i - 1).FOrderId) {
//            } else {
//                detailContainer = new ArrayList<>();
//                puBean = pBean.new purchaseInStore();
//                String main;
//                String detail = "";
//                T_main t_main = mains.get(i);
//                main = t_main.FBillerID + "|" +
//                        t_main.FCfLenghtAny + "|" +
//                        t_main.FCfWide + "|" +
//                        t_main.FCfThick + "|" +
//                        t_main.FStorageNumber + "|" +
//                        t_main.FDepartmentNumber + "|" +
//                        t_main.FBoxCode + "|" +
//                        t_main.FBatch + "|";
//                puBean.main = main;
//                List<T_Detail> details = t_detailDao.queryBuilder().where(
//                        T_DetailDao.Properties.FOrderId.eq(t_main.FOrderId),
//                        T_DetailDao.Properties.FCfBoxCode.eq(t_main.FBoxCode),
//                        T_DetailDao.Properties.Activity.eq(activity)
//                ).orderAsc(T_DetailDao.Properties.FCfLenght).build().list();
//                for (int j = 0; j < details.size(); j++) {
//                    if (j != 0 && j % 49 == 0) {
//                        Log.e("j%49", j % 49 + "");
//                        T_Detail t_detail = details.get(j);
//                        detail = detail +
//                                t_detail.FItemID + "|" +
//                                t_detail.FCfUnitID + "|" +
//                                t_detail.FCfQty + "|" +
//                                t_detail.FCfStorageID + "|" +
//                                t_detail.FCfWaveHouseID + "|" +
//                                t_detail.FCfStoreOrgID + "|" +
//                                t_detail.FCfHuozhuID + "|" +
//                                t_detail.FCfLenght + "|" +
//                                t_detail.FCfM2 + "|";
//                        detail = detail.subSequence(0, detail.length() - 1).toString();
//                        detailContainer.add(detail);
//                        detail = "";
//                    } else {
//                        Log.e("j", j + "");
//                        Log.e("details.size()", details.size() + "");
//                        T_Detail t_detail = details.get(j);
//                        detail = detail +
//                                t_detail.FItemID + "|" +
//                                t_detail.FCfUnitID + "|" +
//                                t_detail.FCfQty + "|" +
//                                t_detail.FCfStorageID + "|" +
//                                t_detail.FCfWaveHouseID + "|" +
//                                t_detail.FCfStoreOrgID + "|" +
//                                t_detail.FCfHuozhuID + "|" +
//                                t_detail.FCfLenght + "|" +
//                                t_detail.FCfM2 + "|";
//                        Log.e("detail1", detail);
//                    }
//
//                }
//                if (detail.length() > 0) {
//                    detail = detail.subSequence(0, detail.length() - 1).toString();
//                }
//
//                Log.e("detail", detail);
//                detailContainer.add(detail);
//                puBean.detail = detailContainer;
//                data.add(puBean);
//            }
//
//        }
        pBean.list = data;
        App.getRService().doIOAction(WebApi.doSplitingP1Upload, gson.toJson(pBean), new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);
                if (!commonResponse.state) return;
                DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                if (null != dBean && dBean.printDataBeans.size() > 0) {
                    Lg.e("拆箱成功", dBean);
                    Toast.showText(mContext,"拆箱成功");
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
                    printOldBoxCode();
//                    LoadingUtil.showAlter(mContext, "装箱成功");
//                    pickingAfter(dBean.wortPrintDatas);
                } else {
                    LoadingUtil.showAlter(mContext, "装箱返回信息为空");
                    Lg.e("装箱失败");
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                LoadingUtil.showAlter(mContext, "装箱失败", e.getMessage());

            }
        });
    }

    private void printOldBoxCode(){
        AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
        ab.setTitle("拆箱成功,是否继续打印原箱码");
        ab.setPositiveButton("打印", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Lg.e("打印");
                printOldCode(splitBoxCode);
                clearData();

            }
        });
        ab.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Lg.e("取消");
                clearData();
                alertDialog.dismiss();
            }
        });
        alertDialog = ab.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }



    //拆箱后，更改为已装箱，并且打印
//    private void pickingAfter(final List<WortPrintData> wortPrintData) {
//        //暂存一份数据到Hawk，因为查看里面已被清空，此数据用于补打，每次拆箱后，会保存上一次拆箱的数据，便于补打
//        List<WortPrintData> listTemp = DataModel.getP2SplitDetail(mContext,boxCode);
//        List<WortPrintData> list = new ArrayList<>();
//        Lg.e("保存此次拆箱数据",listTemp);
//        for (int i = 0; i < listTemp.size(); i++) {
//            if (MathUtil.toInt(listTemp.get(i).FQtySplit)!=0 && null!=listTemp.get(i).FSplitBoxCode){
//                list.add(listTemp.get(i));
//            }
//        }
//        Hawk.put(Info.SplitBoxTempPrintData,list);
//
////        new Thread(new Runnable() {
////            @Override
////            public void run() {
//        try {
//            CommonUtil.doPrint4P2Wort(mContext, wortPrintData, "1");
//        } catch (Exception e) {
////                    e.printStackTrace();
////            App.getInstance().connectPrint();
//            LoadingUtil.showAlter(mContext, getString(R.string.error_print), getString(R.string.check_print));
//        }
////            }
////        }).start();
//
//
//        AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
//        ab.setTitle("拆箱成功,是否继续打印原箱码");
//        ab.setPositiveButton("打印", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Lg.e("打印");
//                printOldCode(boxCode);
//                clearData();
//
//            }
//        });
//        ab.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Lg.e("取消");
//                clearData();
//                alertDialog.dismiss();
//            }
//        });
//        alertDialog = ab.create();
//        alertDialog.setCanceledOnTouchOutside(false);
//        alertDialog.show();
//
//
//
//    }

    private void clearData(){
        //清空本地数据
//        daoSession.getWortPrintDataDao().detachAll();
//        boxCode = "";
        splitBoxCode = "";
        listAll.clear();
        loadCountOff();
//        Hawk.put(Info.BoxCode + activity, "");
//        Hawk.put(Info.SplitBoxCode + activity, "");
//        loadLocData("");
    }

    private void printOldCode(String code){
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


    @OnClick({R.id.btn_pic, R.id.tv_error, R.id.search, R.id.tv_print, R.id.btn_finishorder, R.id.btn_backorder, R.id.btn_checkorder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_pic:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, Info.Scan_Pic);
                break;
            case R.id.search:
                if (lyScan.getVisibility() == View.VISIBLE) {
                    lyScan.setVisibility(View.GONE);
//                    mCaptureManager.onPause();
                } else {
                    mCaptureManager.onResume();
                    lyScan.setVisibility(View.VISIBLE);
                    mCaptureManager.decode();
                }
                break;
            case R.id.tv_print:
                break;
//            case R.id.btn_add:
//                break;
            case R.id.btn_finishorder:
                break;
            case R.id.btn_backorder:
                break;
            case R.id.tv_error:
                SplitPrintHistoryActivity.start(mContext);
                break;
            case R.id.btn_checkorder:
                Bundle bundle = new Bundle();
                bundle.putString("boxcode", boxCode);
                startNewActivity(ReViewP24SplitActivity.class,
                        R.anim.activity_fade_in, R.anim.activity_fade_out, false, bundle);
                break;

        }
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, SplitBoxP1Activity.class);
//        intent.putExtra("activity",activity);
        context.startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Lg.e("onDestroy");
        mCaptureManager.onDestroy();
//        App.getInstance().disPrint();
        //当列表不存在已拆箱数据，清空本地数据，下次进入重新扫码
        if (!hasSplitData) daoSession.getWortPrintDataDao().deleteAll();
    }
}