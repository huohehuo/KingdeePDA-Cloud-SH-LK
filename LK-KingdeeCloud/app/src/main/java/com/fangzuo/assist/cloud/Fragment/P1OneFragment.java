package com.fangzuo.assist.cloud.Fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.fangzuo.assist.cloud.ABase.BaseFragment;
import com.fangzuo.assist.cloud.Activity.PagerForActivity;
import com.fangzuo.assist.cloud.Activity.PrintBeforeDataActivity;
import com.fangzuo.assist.cloud.Activity.PrintBoxCode4P1Activity;
import com.fangzuo.assist.cloud.Activity.PrintHistoryActivity;
import com.fangzuo.assist.cloud.Activity.PushDownPagerActivity;
import com.fangzuo.assist.cloud.Activity.ScanProductActivity;
import com.fangzuo.assist.cloud.Activity.SplitBoxP1Activity;
import com.fangzuo.assist.cloud.Adapter.GridViewAdapter;
import com.fangzuo.assist.cloud.Adapter.P1OneAdapter;
import com.fangzuo.assist.cloud.Adapter.SplitHistoryAdapter;
import com.fangzuo.assist.cloud.Beans.SettingList;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.GetSettingList;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class P1OneFragment extends BaseFragment {
    @BindView(R.id.ry_data)
    EasyRecyclerView ryData;
    Unbinder unbinder;
    private FragmentActivity mContext;

    public P1OneFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_p1_one, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void initView() {
        mContext = getActivity();
        items_sout  =new ArrayList<>();
        items_tb    =new ArrayList<>();
        items_tb2   =new ArrayList<>();
        items_tb3   =new ArrayList<>();
        items_pk    =new ArrayList<>();
        items_gb    =new ArrayList<>();
        items_db    =new ArrayList<>();
        items_in_outList= new ArrayList<>();
        items_ph    =new ArrayList<>();
        items_lv    =new ArrayList<>();
        items_model =new ArrayList<>();
        items_splitbox   =new ArrayList<>();
        items_zbchejian  =new ArrayList<>();
        items_bg1chejian =new ArrayList<>();
        items_bg2chejian =new ArrayList<>();
        items_cpwg =new ArrayList<>();
        items_zbin =new ArrayList<>();
        items_zbget =new ArrayList<>();
        items_zbget.add("简单生产领料1");
        items_zbget.add("简单生产领料2");
        items_zbget.add("简单生产领料3");
        items_zbget.add("简单生产领料4");
        items_zbget.add("简单生产领料5");
        items_zbin.add("简单生产入库1");
        items_zbin.add("简单生产入库2");
        items_zbin.add("简单生产入库3");
        items_zbin.add("简单生产入库4");
        items_zbin.add("简单生产入库5");

    }

    @Override
    protected void OnReceive(String barCode) {

    }

    P1OneAdapter adapter;
    @Override
    protected void initData() {
        String getPermit= Hawk.get(Config.User_Permit,"");
        String[] aa = getPermit.split("\\-"); // 这样才能得到正确的结果
        ryData.setAdapter(adapter = new P1OneAdapter(mContext));
        ryData.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        adapter.addAll(GetSettingList.getPurchaseList(aa));
        dealSecMenu(aa);
    }

    private AlertDialog.Builder builder;
    List<String> items_in_outList  ;   /*= new String[]{"原单", "销售订单下推销售出库单","VMI销售订单下推销售出库单","退货通知单下推销售退货单"};*/
    List<String> items_sout  ;   /*= new String[]{"原单", "销售订单下推销售出库单","VMI销售订单下推销售出库单","退货通知单下推销售退货单"};*/
    List<String> items_tb    ;   /*= new String[]{"挑板领料1", "挑板入库1"};*/
    List<String> items_tb2   ;   /*= new String[]{"挑板领料2", "挑板入库2"};*/
    List<String> items_tb3   ;   /*= new String[]{"挑板领料3", "挑板入库3"};*/
    List<String> items_pk    ;   /*= new String[]{"盘亏入库", "VMI盘亏入库"};*/
    List<String> items_gb    ;   /*= new String[]{"改板领料", "改板入库"};*/
    List<String> items_db    ;   /*= new String[]{"组织间调拨", "跨组织调拨", "调拨申请单下推直接调拨单"};*/
    List<String> items_ph    ;   /*= new String[]{"组织间调拨", "跨组织调拨", "调拨申请单下推直接调拨单"};*/
    List<String> items_lv    ;   /*= new String[]{"组织间调拨", "跨组织调拨", "调拨申请单下推直接调拨单"};*/
    List<String> items_model    ;   /*= new String[]{"组织间调拨", "跨组织调拨", "调拨申请单下推直接调拨单"};*/
    List<String> items_splitbox    ;   /*= new String[]{"组织间调拨", "跨组织调拨", "调拨申请单下推直接调拨单"};*/
    List<String> items_zbchejian    ;   /*= new String[]{"组织间调拨", "跨组织调拨", "调拨申请单下推直接调拨单"};*/
    List<String> items_bg1chejian    ;   /*= new String[]{"组织间调拨", "跨组织调拨", "调拨申请单下推直接调拨单"};*/
    List<String> items_bg2chejian    ;   /*= new String[]{"组织间调拨", "跨组织调拨", "调拨申请单下推直接调拨单"};*/
    List<String> items_cpwg    ;   /*= new String[]{"组织间调拨", "跨组织调拨", "调拨申请单下推直接调拨单"};*/
    List<String> items_zbin    ;   /*= new String[]{"组织间调拨", "跨组织调拨", "调拨申请单下推直接调拨单"};*/
    List<String> items_zbget    ;   /*= new String[]{"组织间调拨", "跨组织调拨", "调拨申请单下推直接调拨单"};*/
//    String[] items_dc   = new String[]{"代存出库", "代存入库"};
//    String[] items_db = new String[]{"组织间调拨", "跨组织调拨", "调拨申请单下推直接调拨单", "VMI调拨申请单下推直接调拨单"};
//    String[] items_in_out = new String[]{"样板出库", "第三方货物入库","第三方货物出库","出库申请单下推其他出库单"};
    @Override
    protected void initListener() {
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                SettingList tv = (SettingList) adapter.getAllData().get(position);
                Log.e("listitem", tv.tv);
                switch (tv.activity) {
                    /*-------------一期单据----------------------------------------------*/
                    case Config.OutsourcingInActivity://外购入库单
                        PagerForActivity.start(mContext, Config.OutsourcingInActivity);
                        break;
                    case Config.PdSaleOrder2SaleOutActivity://销售订单下推销售出库
                        PushDownPagerActivity.start(mContext, 2);
                        break;
                    case Config.PdCgOrder2WgrkActivity://实际是采购订单下推外购入库
                        PushDownPagerActivity.start(getActivity(),1);
                        break;
                    case Config.PdCgOrder2WwrkActivity://实际是采购订单下推外购入库
                        PushDownPagerActivity.start(getActivity(),34);
                        break;
                    case Config.OtherInStoreActivity://其他入库
                        PagerForActivity.start(mContext, Config.OtherInStoreActivity);
                        break;
                    case Config.OtherOutStoreActivity://其他出库
                        PagerForActivity.start(mContext, Config.OtherOutStoreActivity);
                        break;


                    case Config.PYingActivity://盘盈入库
                        PagerForActivity.start(mContext, Config.PYingActivity);
                        break;
                    case Config.PkuiActivity://盘亏入库
                        PagerForActivity.start(mContext, Config.PkuiActivity);
                    break;




                    case Config.BoxReAddP1Activity://混包新增
                        PagerForActivity.start(mContext, Config.BoxReAddP1Activity);
                        break;
                    case Config.BoxReBoxP1Activity://混包追加
                        PagerForActivity.start(mContext, Config.BoxReBoxP1Activity);
                        break;
                    case Config.DB4P1BoxActivity://箱码调拨单
                        PagerForActivity.start(mContext, Config.DB4P1BoxActivity);
                        break;
                    case Config.ProductGet4BoxActivity://生产领料(箱码)
                        PagerForActivity.start(mContext, Config.ProductGet4BoxActivity);
                        break;
                    case Config.SplitBoxP1Activity://拆箱
                        SplitBoxP1Activity.start(mContext);
                        break;
                    case Config.PrintBoxCode4P1Activity://箱码补打
                        PrintBoxCode4P1Activity.start(mContext);
                        break;
                    case Config.P1PdCgrk2ProductGetActivity://采购入库单下推简单生产领料
                        PushDownPagerActivity.start(getActivity(),28);
                        break;
                    case Config.P1PdProductGet2CprkActivity://生产领料单下推产品入库单
                        PushDownPagerActivity.start(getActivity(),29);
                        break;
                    case Config.P1PdProductGet2Cprk2Activity://生产领料单下推产品入库单
                        PushDownPagerActivity.start(getActivity(),30);
                        break;

                    case Config.FLInStoreP1Activity://方料入库
                        PushDownPagerActivity.start(getActivity(),32);
                        break;
                    case Config.DBClientActivity://调拨(客户在途)
                        PagerForActivity.start(mContext, Config.DBClientActivity);
                        break;
                    case Config.DBStorageActivity://调拨(在途仓库)
                        PagerForActivity.start(mContext, Config.DBStorageActivity);
                        break;

//                    case "采购入库":
//                        PagerForActivity.start(mContext, Config.PurchaseInStoreActivity);
//                        startNewActivity(PurchaseInStoreActivity.class, null);
//                        break;
                    case Config.DBActivity://调拨单
                        // 创建对话框构建器
                        builder = new AlertDialog.Builder(getActivity());
                        // 设置参数
                        builder.setAdapter(
                                new ArrayAdapter<String>(getActivity(),
                                        R.layout.item_choose, R.id.textView, items_db),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        switch (items_db.get(which)) {
                                            case "组织间调拨/箱码":
                                                PagerForActivity.start(mContext, Config.DBActivity);
//                                                startNewActivity(SaleOutActivity.class, null);
                                                break;
                                            case "跨组织调拨":
                                                PagerForActivity.start(mContext, Config.DB2Activity);
                                                break;
                                            case "调拨申请单下推直接调拨单":
                                                PushDownPagerActivity.start(getActivity(),22);
                                                break;
//                                            case 3:
//                                                PushDownPagerActivity.start(getActivity(),23);
//                                                break;
                                        }
                                    }
                                });
                        builder.create().show();
//                        startNewActivity(DBActivity.class, null);
                        break;
                    case Config.DhInActivity://到货入库1
                        PagerForActivity.start(mContext, Config.DhInActivity);
                        break;
                    case Config.DhIn2Activity://到货入库2
                        PagerForActivity.start(mContext, Config.DhIn2Activity);
                        break;
                    case Config.SaleOutActivity://销售出库
                        // 创建对话框构建器
                        builder = new AlertDialog.Builder(getActivity());
                        // 设置参数
                        builder.setAdapter(
                                new ArrayAdapter<String>(getActivity(),
                                        R.layout.item_choose, R.id.textView, items_sout),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        switch (items_sout.get(which)) {//"原单", "销售订单下推销售出库单","退货通知单下推销售退货单"
                                            case "原单":
                                                PagerForActivity.start(mContext, Config.SaleOutActivity);
//                                                startNewActivity(SaleOutActivity.class, null);
                                                break;
                                            case "销售订单下推销售出库单":
                                                PushDownPagerActivity.start(getActivity(),2);
//                                                Bundle b = new Bundle();
//                                                b.putInt("123", 2);
////                                                startNewActivity(PushDownPagerActivity.class, R.anim.activity_fade_in, R.anim.activity_fade_out, false, b);
//                                                startNewActivity(PushDownPagerActivity.class, b);
                                                break;
                                            case "销售订单下推销售出库单(箱码)":
                                                PushDownPagerActivity.start(getActivity(),31);
                                                break;
                                            case "VMI销售订单下推销售出库单":
                                                PushDownPagerActivity.start(getActivity(),21);
                                                break;
                                            case "退货通知单下推销售退货单":
                                                PushDownPagerActivity.start(getActivity(),6);
                                                break;
                                        }
                                    }
                                });
                        builder.create().show();
                        break;
                    case Config.TbGetActivity://挑板业务1
                        builder = new AlertDialog.Builder(getActivity());
                        // 设置参数
                        builder.setAdapter(
                                new ArrayAdapter<String>(getActivity(),
                                        R.layout.item_choose, R.id.textView, items_tb),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        switch (items_tb.get(which)) {//"挑板领料1", "挑板入库1"
                                            case "挑板领料1(整包)":
                                                PagerForActivity.start(mContext, Config.TbGetActivity);
                                                break;
                                            case "挑板入库1(整包)":
                                                PagerForActivity.start(mContext, Config.TbInActivity);
                                                break;
                                            case "挑板领料1(混包)":
                                                PagerForActivity.start(mContext, Config.Tb1DiGetActivity);
                                                break;
                                            case "挑板入库1(混包)":
                                                PagerForActivity.start(mContext, Config.Tb1HunInActivity);
                                                break;
                                        }
                                    }
                                });
                        builder.create().show();
                        break;
                    case Config.TbGet2Activity://挑板业务2
                        builder = new AlertDialog.Builder(getActivity());
                        // 设置参数
                        builder.setAdapter(
                                new ArrayAdapter<String>(getActivity(),
                                        R.layout.item_choose, R.id.textView, items_tb2),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        switch (items_tb2.get(which)) {
                                            case "挑板领料2(整包)":
                                                PagerForActivity.start(mContext, Config.TbGet2Activity);
                                                break;
                                            case "挑板入库2(整包)":
                                                PagerForActivity.start(mContext, Config.TbIn2Activity);
                                                break;
                                            case "挑板领料2(混包)":
                                                PagerForActivity.start(mContext, Config.Tb2DiGetActivity);
                                                break;
                                            case "挑板入库2(混包)":
                                                PagerForActivity.start(mContext, Config.Tb2HunInActivity);
                                                break;
                                        }
                                    }
                                });
                        builder.create().show();
                        break;
                    case Config.TbGet3Activity://挑板业务3
                        builder = new AlertDialog.Builder(getActivity());
                        // 设置参数
                        builder.setAdapter(
                                new ArrayAdapter<String>(getActivity(),
                                        R.layout.item_choose, R.id.textView, items_tb3),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        switch (items_tb3.get(which)) {
                                            case "挑板领料3(整包)":
                                                PagerForActivity.start(mContext, Config.TbGet3Activity);
                                                break;
                                            case "挑板入库3(整包)":
                                                PagerForActivity.start(mContext, Config.TbIn3Activity);
                                                break;
                                            case "挑板领料3(混包)":
                                                PagerForActivity.start(mContext, Config.Tb3DiGetActivity);
                                                break;
                                            case "挑板入库3(混包)":
                                                PagerForActivity.start(mContext, Config.Tb3HunInActivity);
                                                break;
                                        }
                                    }
                                });
                        builder.create().show();
                        break;
                    case Config.GbGetActivity://改板业务
                        builder = new AlertDialog.Builder(getActivity());
                        // 设置参数
                        builder.setAdapter(
                                new ArrayAdapter<String>(getActivity(),
                                        R.layout.item_choose, R.id.textView, items_gb),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        switch (items_gb.get(which)) {
                                            case "改板领料(整包)":
                                                PagerForActivity.start(mContext, Config.GbGetActivity);
                                                break;
                                            case "改板入库(整包)":
                                                PagerForActivity.start(mContext, Config.GbInActivity);
                                                break;
                                            case "改板领料(混包)":
                                                PagerForActivity.start(mContext, Config.GbDiGetActivity);
                                                break;
                                            case "改板入库(混包)":
                                                PagerForActivity.start(mContext, Config.GbHunInActivity);
                                                break;
                                        }
                                    }
                                });
                        builder.create().show();
                        break;
                    case Config.HwOut3Activity://其他出入库
                        builder = new AlertDialog.Builder(getActivity());
                        // 设置参数
                        builder.setAdapter(
                                new ArrayAdapter<String>(getActivity(),
                                        R.layout.item_choose, R.id.textView, items_in_outList),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        switch (items_in_outList.get(which)) {
                                            case "样板出库":
                                                PagerForActivity.start(mContext, Config.YbOutActivity);
                                                break;
                                            case "第三方货物入库":
                                                PagerForActivity.start(mContext, Config.HwIn3Activity);
                                                break;
                                            case "第三方货物出库":
                                                PagerForActivity.start(mContext, Config.HwOut3Activity);
                                                break;
                                            case "":
                                                PushDownPagerActivity.start(getActivity(),24);
                                                break;
                                        }
                                    }
                                });
                        builder.create().show();
                        break;
                    case Config.PrintHistoryActivity://条码补打
                        startNewActivity(PrintHistoryActivity.class, null);
                        break;
                    case Config.PrintBeforeDataActivity://期初条码补打
                        startNewActivity(PrintBeforeDataActivity.class, null);
                        break;
                    case Config.ProductGetActivity://生产领料
                        PagerForActivity.start(mContext, Config.ProductGetActivity);
                        break;
                    case Config.ProductInStoreActivity://产品入库
                        PagerForActivity.start(mContext, Config.ProductInStoreActivity);
                        break;

//                    case Config.PkuiActivity://盘亏入库
//                        builder = new AlertDialog.Builder(getActivity());
//                        // 设置参数
//                        builder.setAdapter(
//                                new ArrayAdapter<String>(getActivity(),
//                                        R.layout.item_choose, R.id.textView, items_pk),
//                                new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog,
//                                                        int which) {
//                                        switch (items_pk.get(which)) {//"盘亏入库", "VMI盘亏入库"
//                                            case "盘亏入库":
//                                                PagerForActivity.start(mContext, Config.PkuiActivity);
//                                                break;
//                                            case "VMI盘亏入库":
//                                                PagerForActivity.start(mContext, Config.PkuiVMIActivity);
//                                                break;
//                                        }
//                                    }
//                                });
//                        builder.create().show();
//                        break;
                    case Config.ScanProductActivity://扫一扫
                        ScanProductActivity.start(mContext);
                        break;
                    case Config.BatchChangeLv1://批号调整
                        builder = new AlertDialog.Builder(getActivity());
                        // 设置参数
                        builder.setAdapter(
                                new ArrayAdapter<String>(getActivity(),
                                        R.layout.item_choose, R.id.textView, items_ph),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        switch (items_ph.get(which)) {//"盘亏入库", "VMI盘亏入库"
                                            case "调整领料(整包)":
                                                PagerForActivity.start(mContext, Config.ChangeGetActivity);
                                                break;
                                            case "调整入库(整包)":
                                                PagerForActivity.start(mContext, Config.ChangeInActivity);
                                                break;
                                        }
                                    }
                                });
                        builder.create().show();
                        break;
                    case Config.LvChangeLv1://等级调整
                        builder = new AlertDialog.Builder(getActivity());
                        // 设置参数
                        builder.setAdapter(
                                new ArrayAdapter<String>(getActivity(),
                                        R.layout.item_choose, R.id.textView, items_lv),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        switch (items_lv.get(which)) {//"盘亏入库", "VMI盘亏入库"
                                            case "调整领料(整包)":
                                                PagerForActivity.start(mContext, Config.ChangeLvGetActivity);
                                                break;
                                            case "调整入库(整包)":
                                                PagerForActivity.start(mContext, Config.ChangeLvInActivity);
                                                break;
                                        }
                                    }
                                });
                        builder.create().show();
                        break;
                    case Config.ModelChangeLv1://规格调整
                        builder = new AlertDialog.Builder(getActivity());
                        // 设置参数
                        builder.setAdapter(
                                new ArrayAdapter<String>(getActivity(),
                                        R.layout.item_choose, R.id.textView, items_model),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        switch (items_model.get(which)) {//"盘亏入库", "VMI盘亏入库"
                                            case "调整领料(整包)":
                                                PagerForActivity.start(mContext, Config.ChangeModelGetActivity);
                                                break;
                                            case "调整入库(整包)":
                                                PagerForActivity.start(mContext, Config.ChangeModelInActivity);
                                                break;
                                        }
                                    }
                                });
                        builder.create().show();
                        break;
                    case Config.SplitBoxLv1://拆包理货
                        builder = new AlertDialog.Builder(getActivity());
                        // 设置参数
                        builder.setAdapter(
                                new ArrayAdapter<String>(getActivity(),
                                        R.layout.item_choose, R.id.textView, items_splitbox),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        switch (items_splitbox.get(which)) {//"盘亏入库", "VMI盘亏入库"
                                            case "拆包领料(整包)":
                                                PagerForActivity.start(mContext, Config.SplitBoxGetActivity);
                                                break;
                                            case "拆包领料(混包)":
                                                PagerForActivity.start(mContext, Config.SplitBoxDiGetActivity);
                                                break;
                                            case "拆包入库(整包)":
                                                PagerForActivity.start(mContext, Config.SplitBoxInActivity);
                                                break;
                                            case "拆包入库(混包)":
                                                PagerForActivity.start(mContext, Config.SplitBoxHunInActivity);
                                                break;



                                        }
                                    }
                                });
                        builder.create().show();
                        break;
                    case Config.ZbChejianLv1://纵刨车间
                        builder = new AlertDialog.Builder(getActivity());
                        // 设置参数
                        builder.setAdapter(
                                new ArrayAdapter<String>(getActivity(),
                                        R.layout.item_choose, R.id.textView, items_zbchejian),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        switch (items_zbchejian.get(which)) {//"盘亏入库", "VMI盘亏入库"
                                            case "整包入库":
                                                PagerForActivity.start(mContext, Config.ZbCheJianInActivity);
                                                break;
                                            case "混包入库":
                                                PagerForActivity.start(mContext, Config.ZbCheJianHunInActivity);
                                                break;
                                            case "底领料(混包)":
                                                PagerForActivity.start(mContext, Config.ZbCheJianDiGetActivity);
                                                break;
                                            case "底领料(整包)":
                                                PagerForActivity.start(mContext, Config.ZbCheJianDiZGetActivity);
                                                break;
                                        }
                                    }
                                });
                        builder.create().show();
                        break;
                    case Config.Bg1ChejianLv1://刨光一车间
                        builder = new AlertDialog.Builder(getActivity());
                        // 设置参数
                        builder.setAdapter(
                                new ArrayAdapter<String>(getActivity(),
                                        R.layout.item_choose, R.id.textView, items_bg1chejian),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        switch (items_bg1chejian.get(which)) {//"盘亏入库", "VMI盘亏入库"
                                            case "整包入库":
                                                PagerForActivity.start(mContext, Config.Bg1CheJianInActivity);
                                                break;
                                            case "混包入库":
                                                PagerForActivity.start(mContext, Config.Bg1CheJianHunInActivity);
                                                break;
                                            case "底领料":
                                                PagerForActivity.start(mContext, Config.Bg1CheJianDiGetActivity);
                                                break;
                                        }
                                    }
                                });
                        builder.create().show();
                        break;
                    case Config.Bg2ChejianLv1://刨光二车间
                        builder = new AlertDialog.Builder(getActivity());
                        // 设置参数
                        builder.setAdapter(
                                new ArrayAdapter<String>(getActivity(),
                                        R.layout.item_choose, R.id.textView, items_bg2chejian),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        switch (items_bg2chejian.get(which)) {//"盘亏入库", "VMI盘亏入库"
                                            case "整包入库":
                                                PagerForActivity.start(mContext, Config.Bg2CheJianInActivity);
                                                break;
                                            case "混包入库":
                                                PagerForActivity.start(mContext, Config.Bg2CheJianHunInActivity);
                                                break;
                                            case "底领料":
                                                PagerForActivity.start(mContext, Config.Bg2CheJianDiGetActivity);
                                                break;
                                        }
                                    }
                                });
                        builder.create().show();
                        break;
                    case Config.CpWgInLv1://成品外购入库
                        builder = new AlertDialog.Builder(getActivity());
                        // 设置参数
                        builder.setAdapter(
                                new ArrayAdapter<String>(getActivity(),
                                        R.layout.item_choose, R.id.textView, items_cpwg),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        switch (items_cpwg.get(which)) {//"盘亏入库", "VMI盘亏入库"
                                            case "外购入库(整包)":
                                                PagerForActivity.start(mContext, Config.CpWgInActivity);
                                                break;
                                            case "外购入库(混包)":
                                                PagerForActivity.start(mContext, Config.CpWgHunInActivity);
                                                break;
                                        }
                                    }
                                });
                        builder.create().show();
                        break;
                    case Config.ZbGetActivity://整包领料(通用)
                        builder = new AlertDialog.Builder(getActivity());
                        // 设置参数
                        builder.setAdapter(
                                new ArrayAdapter<String>(getActivity(),
                                        R.layout.item_choose, R.id.textView, items_zbget),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        switch (items_zbget.get(which)) {//"盘亏入库", "VMI盘亏入库"
                                            case "简单生产领料1":PagerForActivity.start(mContext, Config.ZbGet1Activity);break;
                                            case "简单生产领料2":PagerForActivity.start(mContext, Config.ZbGet2Activity);break;
                                            case "简单生产领料3":PagerForActivity.start(mContext, Config.ZbGet3Activity);break;
                                            case "简单生产领料4":PagerForActivity.start(mContext, Config.ZbGet4Activity);break;
                                            case "简单生产领料5":PagerForActivity.start(mContext, Config.ZbGet5Activity);break;
                                        }
                                    }
                                });
                        builder.create().show();
                        break;
                    case Config.ZbInActivity://整包入库(通用)
                        builder = new AlertDialog.Builder(getActivity());
                        // 设置参数
                        builder.setAdapter(
                                new ArrayAdapter<String>(getActivity(),
                                        R.layout.item_choose, R.id.textView, items_zbin),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        switch (items_zbin.get(which)) {
                                            case "简单生产入库1":PagerForActivity.start(mContext, Config.ZbIn1Activity);break;
                                            case "简单生产入库2":PagerForActivity.start(mContext, Config.ZbIn2Activity);break;
                                            case "简单生产入库3":PagerForActivity.start(mContext, Config.ZbIn3Activity);break;
                                            case "简单生产入库4":PagerForActivity.start(mContext, Config.ZbIn4Activity);break;
                                            case "简单生产入库5":PagerForActivity.start(mContext, Config.ZbIn5Activity);break;
                                        }
                                    }
                                });
                        builder.create().show();
                        break;
//                    case "代存业务":
//                        builder = new AlertDialog.Builder(getActivity());
//                        // 设置参数
//                        builder.setAdapter(
//                                new ArrayAdapter<String>(getActivity(),
//                                        R.layout.item_choose, R.id.textView, items_dc),
//                                new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog,
//                                                        int which) {
//                                        switch (which) {
//                                            case 0:
//                                                PagerForActivity.start(mContext, Config.DcOutActivity);
//                                                break;
//                                            case 1:
//                                                PagerForActivity.start(mContext, Config.DcInActivity);
//                                                break;
//                                        }
//                                    }
//                                });
//                        builder.create().show();
//                        break;
//                    case "其他出库":
////                        startNewActivity(OtherOutStoreActivity.class, null);
//                        PagerForActivity.start(mContext, Config.OtherOutStoreActivity);
//                        break;
//
//                    case "其他入库":
////                        startNewActivity(OtherInStoreActivity.class, null);
//                        PagerForActivity.start(mContext, Config.OtherInStoreActivity);
//                        break;
//                    case "库存查询":
//                        startNewActivity(CheckStoreActivity.class, null);
//                        break;
//                    case "盘点":
//                        startNewActivity(PDActivity.class, null);
//                        break;
//                    case "单据下推":
//                        startNewActivity(PushDownActivity.class, null);
//                        break;
//                    case "采购订单":
//                        startNewActivity(PurchaseOrderActivity.class, null);
//                        break;
//
//                    case "销售订单":
//                        startNewActivity(SaleOrderActivity.class, null);
//                        break;
//                    case 1://外购入库
//                        startNewActivity(PurchaseInStoreActivity.class, null);
//                        break;
//                    case 2://产品入库
////                        startNewActivity(ProductInStorageActivity.class, null);
//                        break;
                }
            }
        });
    }

    //处理二级菜单权限数据
    private void dealSecMenu(String[] ary){
        Lg.e("赛选：",ary);
        for (int i=0; i<ary.length;i++){
            switch (ary[i]){
                //成品外购入库
                case "40":
                    items_cpwg.add("外购入库(整包)");
                    break;
                case "41":
                    items_cpwg.add("外购入库(混包)");
                    break;
                //刨光一车间
                case "42":
                    items_bg1chejian.add("整包入库");
                    break;
                case "43":
                    items_bg1chejian.add("混包入库");
                    break;
                case "44":
                    items_bg1chejian.add("底领料");
                    break;
                //刨光二车间
                case "45":
                    items_bg2chejian.add("整包入库");
                    break;
                case "46":
                    items_bg2chejian.add("混包入库");
                    break;
                case "47":
                    items_bg2chejian.add("底领料");
                    break;
                //纵刨车间
                case "48":
                    items_zbchejian.add("整包入库");
                    break;
                case "49":
                    items_zbchejian.add("混包入库");
                    break;
                case "50":
                    items_zbchejian.add("底领料(混包)");
                    break;
                case "76":
                    items_zbchejian.add("底领料(整包)");
                    break;

                //拆包理货
                case "51":
                    items_splitbox.add("拆包领料(整包)");
                    break;
                case "52":
                    items_splitbox.add("拆包领料(混包)");
                    break;
                case "53":
                    items_splitbox.add("拆包入库(整包)");
                    break;
                case "54":
                    items_splitbox.add("拆包入库(混包)");
                    break;
                //规格调整
                case "57":
                    items_model.add("调整领料(整包)");
                    break;
                case "58":
                    items_model.add("调整入库(整包)");
                    break;
                //等级调整
                case "59":
                    items_lv.add("调整领料(整包)");
                    break;
                case "60":
                    items_lv.add("调整入库(整包)");
                    break;
                //批号调整
                case "61":
                    items_ph.add("调整领料(整包)");
                    break;
                case "62":
                    items_ph.add("调整入库(整包)");
                    break;
                //盘亏入库
                case "23":
                    items_pk.add("盘亏入库");
                    break;
                case "24":
                    items_pk.add("VMI盘亏入库");
                    break;
                //改板业务
                case "20":
                    items_gb.add("改板领料(整包)");
                    break;
                case "21":
                    items_gb.add("改板入库(整包)");
                    break;
                case "73":
                    items_gb.add("改板领料(混包)");
                    break;
                case "74":
                    items_gb.add("改板入库(混包)");
                    break;
                //挑板业务1
                case "14":
                    items_tb.add("挑板领料1(整包)");
                    break;
                case "15":
                    items_tb.add("挑板入库1(整包)");
                    break;
                case "63":
                    items_tb.add("挑板领料1(混包)");
                    break;
                case "64":
                    items_tb.add("挑板入库1(混包)");
                    break;
                //挑板业务2
                case "16":
                    items_tb2.add("挑板领料2(整包)");
                    break;
                case "17":
                    items_tb2.add("挑板入库2(整包)");
                    break;
                case "69":
                    items_tb2.add("挑板领料2(混包)");
                    break;
                case "70":
                    items_tb2.add("挑板入库2(混包)");
                    break;
                //挑板业务3
                case "18":
                    items_tb3.add("挑板领料3(整包)");
                    break;
                case "19":
                    items_tb3.add("挑板入库3(整包)");
                    break;
                case "71":
                    items_tb3.add("挑板领料3(混包)");
                    break;
                case "72":
                    items_tb3.add("挑板入库3(混包)");
                    break;
                //销售出库组
                case "1":
                    items_sout.add("原单");
                    break;
                case "2":
                    items_sout.add("销售订单下推销售出库单");
                    break;
                case "75":
                    items_sout.add("销售订单下推销售出库单(箱码)");
                    break;
                case "3":
                    items_sout.add("VMI销售订单下推销售出库单");
                    break;
                case "4":
                    items_sout.add("退货通知单下推销售退货单");
                    break;
                    //其他出入库组
                case "8":
                    items_in_outList.add("样板出库");
                    break;
                case "9":
                    items_in_outList.add("第三方货物入库");
                    break;
                case "10":
                    items_in_outList.add("第三方货物出库");
                    break;
                    //调拨组
                case "11":
                    items_db.add("组织间调拨/箱码");
                    break;
                case "12":
                    items_db.add("跨组织调拨");
                    break;
                case "13":
                    items_db.add("调拨申请单下推直接调拨单");
                    break;







            }
//            Log.e("test","定位ary:"+ary[i]);
//            Log.e("test","定位items:"+items.get(i).tag);
            //根据ary的值，遍历list符合的item并添加
//            for (int j=0;j<ary.length;j++){
//                if (TextUtils.equals(items.get(i).tag,ary[j])){
//                    Lg.e("加入单据",items.get(i));
//                    backItems.add(items.get(i));
//                }
//            }
        }

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
