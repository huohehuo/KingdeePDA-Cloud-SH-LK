package com.fangzuo.assist.cloud.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Adapter.StripAdapter;
import com.fangzuo.assist.cloud.Dao.Client;
import com.fangzuo.assist.cloud.Dao.Department;
import com.fangzuo.assist.cloud.Dao.Org;
import com.fangzuo.assist.cloud.Dao.Storage;
import com.fangzuo.assist.cloud.Dao.StoreMan;
import com.fangzuo.assist.cloud.Dao.Suppliers;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.DbBox.FragmentDB2Main;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.DbBox.FragmentDBBoxDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.DbBox.FragmentDBDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.DbBox.FragmentDBMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentBoxReAddDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentBoxReAddMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentBoxReBoxDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentBoxReBoxMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentDB4P1BoxDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentDB4P1BoxMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentOsInDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentOsInMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentP1PdCgrk2ProductionGetDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentP1PdCgrk2ProductionGetMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentPG2Cprk2Detail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentPG2Cprk2Main;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentPG2CprkDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentPG2CprkMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentPrGet4P1BoxMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentPrGetDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentPrGetMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentPrisDhDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentProductGet4P14P2BoxDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentProductGet4P1BoxDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentSaleOut4PDBoxMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentSaleOut4PDMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.P2.FragmentDB4P2Detail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.P2.FragmentDB4P2Main;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.P2.FragmentDryingGet4P2Detail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.P2.FragmentDryingGet4P2Main;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.P2.FragmentPrGet4P2Detail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.P2.FragmentPrGet4P2PihaoDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.P2.FragmentPris4P2Detail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.P2.FragmentPris4P2Detail4SBBCRK;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.P2.FragmentPris4P2Main;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.P2.FragmentPris4P2WortMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.P2.FragmentSbGet4P2Detail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.P2.FragmentWortInStore4P2Detail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.P2.PushDownFragment.FragmentP2ProductionInStore2Detail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.P2.PushDownFragment.FragmentP2WgDryingISDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.PDBox.FragmentPKDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.PDBox.FragmentPYingDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.PDBox.FragmentPYingMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentPrisDh2Main;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.GbManagerBox.FragmentGbGetMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.GbManagerBox.FragmentGbInMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.OtherInOutBox.FragmentOInDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.OtherInOutBox.FragmentOInMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.OtherInOutBox.FragmentOOutDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.OtherInOutBox.FragmentOOutMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentPISDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentPISMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.P2.FragmentPrGet4P2Main;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentPrisDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentPrisDhMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentPrisMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentSaleOutDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentSaleOutMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.PushDownFragment.FragmentCgOrder2WgrkBoxDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.PushDownFragment.FragmentCgOrder2WgrkDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.PushDownFragment.FragmentCgOrder2WgrkMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.PushDownFragment.FragmentCgOrder2WwrkDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.PushDownFragment.FragmentDbApply2DBDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.PushDownFragment.FragmentDbApply2DBMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.P2.PushDownFragment.FragmentP2PdCgrk2ProductionGetDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.P2.PushDownFragment.FragmentP2PdCgrk2ProductionGetMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.P2.PushDownFragment.FragmentP2ProductionInStoreDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.P2.PushDownFragment.FragmentP2ProductionInStoreMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.PushDownFragment.FragmentSaleOutDetailForPDBox;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.TbManagerBox.FragmentTbGetMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.TbManagerBox.FragmentPrisTBDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.TbManagerBox.FragmentPrisTBMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.OtherInOutBox.Fragment3HwInDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.OtherInOutBox.Fragment3HwInMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.OtherInOutBox.Fragment3HwOutDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.OtherInOutBox.Fragment3HwOutMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.OtherInOutBox.FragmentYbOutDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.OtherInOutBox.FragmentYbOutMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.PushDownFragment.FragmentBackMsg2SaleBackDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.PushDownFragment.FragmentBackMsg2SaleBackMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.PushDownFragment.FragmentSaleOutDetailForPD;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.CommonUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.ShareUtil;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.databinding.ActivityPagerForBinding;
import com.fangzuo.assist.cloud.zxing.ScanManager;
import com.fangzuo.greendao.gen.DaoSession;
import com.fangzuo.greendao.gen.T_DetailDao;
import com.fangzuo.greendao.gen.T_mainDao;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

public class PagerForActivity extends BaseActivity {
    ActivityPagerForBinding binding;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private int activity;
    private String activitykey;
    private Org orgApp;
    private Org orgIn;
    private Org orgOut;
    private Org huozhuIn;
    private Org huozhuOut;
    private Storage storage;
    private Storage storageOut;
    private Storage storageIn;
    private Client client;
    private Suppliers suppliers;
    private String DBType;//调拨类型(货主类型)
    private String DBDirection;//调拨方向
    private String note;
    private String carNo;
    private String BoxCode;
    private String Batch;
    private String FOrderNo;//业务单号
    private String FBillNo;//业务单号
    private String ManStore;//仓管员
    private StoreMan ManStoreObj;//仓管员
    private String ManSale;//销售员
    private String ManBuyer;//销售员
    private String ManGet;//领料人
    private String DepartMent;//生产车间
    private Department DepartMentObj;//生产车间
    private String DepartMentBuy;//采购部门
    private String date;//日期
    private String printNum;//日期
    private String wlCompany;//日期
    private String carboxNo;//日期
    private boolean isHebing;//日期
    private boolean isAutoAdd=true;//日期
    private long ordercode;

    public String getBatchRemark() {
        return batchRemark==null?"":batchRemark;
    }

    public void setBatchRemark(String batchRemark) {
        this.batchRemark = batchRemark;
    }

    private String batchRemark;//批号附加值
    private boolean hasLock=false;//判断表头是否被锁住
    private boolean isStorage=false;//是否带出默认仓库
    StripAdapter stripAdapter;
    @Override
    protected boolean isScan() {
        return true;
    }

    public String getPrintNum() {
        return printNum;
    }

    public void setPrintNum(String printNum) {
        this.printNum = printNum;
    }

    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pager_for);
        Intent in = getIntent();
        Bundle b = in.getExtras();
//        String searchString = b.getString("search", "");
        activity = b.getInt("activity");
        Lg.e("获得数据：",""+activity);
        binding.topActivity.ishebing.setChecked(isHebing);
        binding.topActivity.isAutoAdd.setChecked(isAutoAdd);

        ordercode = CommonUtil.createOrderCode(activity);//单据编号
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        titles.add("明细信息");
        titles.add("表头信息");
        setFragment(activity);

    }

    private void setFragment(int activity){
        switch (activity){
            case Config.OutsourcingInActivity:
                binding.topActivity.tvTitle.setText("外购入库单");
                fragments.add(new FragmentOsInDetail());
                fragments.add(new FragmentOsInMain());
                break;
            case Config.PdSaleOrder2SaleOutActivity:
                binding.topActivity.tvTitle.setText("销售订单下推销售出库");
                fragments.add(new FragmentSaleOutDetailForPD());
                fragments.add(new FragmentSaleOut4PDMain());
                break;
            case Config.PdCgOrder2WgrkActivity://PDA显示的是采购入库
                binding.topActivity.tvTitle.setText("采购订单下推外购入库");
                fragments.add(new FragmentCgOrder2WgrkDetail());
                fragments.add(new FragmentCgOrder2WgrkMain());
                break;
            case Config.PdCgOrder2WwrkActivity://PDA显示的是采购入库
                binding.topActivity.tvTitle.setText("采购订单下推委外入库");
                fragments.add(new FragmentCgOrder2WgrkDetail());
                fragments.add(new FragmentCgOrder2WgrkMain());
                break;

            case Config.OtherInStoreActivity:
                binding.topActivity.tvTitle.setText("其他入库");
                fragments.add(new FragmentOInDetail());
                fragments.add(new FragmentOInMain());
                break;
            case Config.OtherOutStoreActivity:
                binding.topActivity.tvTitle.setText("其他出库");
                fragments.add(new FragmentOOutMain());
                fragments.add(new FragmentOOutDetail());
                break;


            case Config.PYingActivity:
                binding.topActivity.tvTitle.setText("盘盈入库");
                fragments.add(new FragmentPYingMain());
                fragments.add(new FragmentPYingDetail());
                break;
            case Config.PkuiActivity:
                binding.topActivity.tvTitle.setText("盘亏入库");
                fragments.add(new FragmentPYingMain());
                fragments.add(new FragmentPKDetail());
                break;

            /*-----------------------------------------------------------二期单据----------------------------------------------*/
            case Config.P2ProductionInStoreActivity://简单成产入库--弃用
                binding.topActivity.tvTitle.setText(getString(R.string.p2_production_instore));
                fragments.add(new FragmentP2ProductionInStoreMain());
                fragments.add(new FragmentP2ProductionInStoreDetail());
                break;
            case Config.P2ProductionInStore2Activity://原木入库
//                connectPrint();
                binding.topActivity.tvTitle.setText(getString(R.string.p2_production_instore2));
                fragments.add(new FragmentP2ProductionInStoreMain());
                fragments.add(new FragmentP2ProductionInStore2Detail());
                break;
            case Config.ProductGet4P2Activity:
                binding.topActivity.tvTitle.setText("原木领料");
                fragments.add(new FragmentPrGet4P2Main());
                fragments.add(new FragmentPrGet4P2Detail());
                break;
            case Config.ProductGet4P24PihaoActivity:
                binding.topActivity.tvTitle.setText("原木领料2");
                fragments.add(new FragmentPrGet4P2Main());
                fragments.add(new FragmentPrGet4P2PihaoDetail());
                break;


            case Config.ProductInStore4P2Activity:
//                connectPrint();
                binding.topActivity.tvTitle.setText("水板板材入库");
                fragments.add(new FragmentPris4P2Main());
                fragments.add(new FragmentPris4P2Detail4SBBCRK());
                break;
            case Config.P2PdCgrk2ProductGetActivity://采购入库单下推简单生产领料单
                binding.topActivity.tvTitle.setText(getString(R.string.p2_cgrk2production_get));
                fragments.add(new FragmentP2PdCgrk2ProductionGetMain());
                fragments.add(new FragmentP2PdCgrk2ProductionGetDetail());
                break;
            case Config.DB4P2Activity:
                binding.topActivity.tvTitle.setText("板材调拨");
                fragments.add(new FragmentDB4P2Main());
                fragments.add(new FragmentDB4P2Detail());
                break;
                /*烘干车间*/
            case Config.ShuiBanGetActivity:
                binding.topActivity.tvTitle.setText("水板拆托出库");
                fragments.add(new FragmentPrGet4P2Main());
                fragments.add(new FragmentSbGet4P2Detail());
                break;
            case Config.OutKilnGetActivity:
                binding.topActivity.tvTitle.setText("出窑领料");
                fragments.add(new FragmentPrGet4P2Main());
                fragments.add(new FragmentSbGet4P2Detail());
                break;

            case Config.ProductInStore4P2MpActivity:
//                connectPrint();
                binding.topActivity.tvTitle.setText("水板板材码拍");
                fragments.add(new FragmentPris4P2Main());
                fragments.add(new FragmentPris4P2Detail());
                break;
            case Config.DryingInStoreActivity:
//                connectPrint();
                binding.topActivity.tvTitle.setText("烘干板入库");
                fragments.add(new FragmentPris4P2Main());
                fragments.add(new FragmentPris4P2Detail());
                break;
            case Config.WgDryingInStoreActivity:
                binding.topActivity.tvTitle.setText("外购烘干板入库");
                fragments.add(new FragmentPris4P2Main());
                fragments.add(new FragmentP2WgDryingISDetail());
                break;
                /*刨光车间*/
            case Config.ShuiBanGet2Activity:
                binding.topActivity.tvTitle.setText("烘干板领料");
                fragments.add(new FragmentPrGet4P2Main());
                fragments.add(new FragmentSbGet4P2Detail());
                break;
            case Config.DryingGetActivity:
                binding.topActivity.tvTitle.setText("刨光板码托");
                fragments.add(new FragmentDryingGet4P2Main());
                fragments.add(new FragmentDryingGet4P2Detail());
                break;
            case Config.WortInStore4P2Activity:
//                connectPrint();
                binding.topActivity.tvTitle.setText("刨光干板入库");
                fragments.add(new FragmentPris4P2WortMain());
                fragments.add(new FragmentWortInStore4P2Detail());
                break;
            case Config.ShuiBanDB4P2Activity:
                binding.topActivity.tvTitle.setText("水板调拨");
                fragments.add(new FragmentDB4P2Main());
                fragments.add(new FragmentDB4P2Detail());
                break;
            case Config.DBInKiln4P2Activity:
                binding.topActivity.tvTitle.setText("调拨入窑");
                fragments.add(new FragmentDB4P2Main());
                fragments.add(new FragmentDB4P2Detail());
                break;
                /*成品车间*/
            case Config.WorkOrgGet4P2Activity:
                binding.topActivity.tvTitle.setText("业务组织领料");
                fragments.add(new FragmentPrGetMain());
                fragments.add(new FragmentPrGetDetail());
                break;
            case Config.SupplierGet4P2Activity:
                binding.topActivity.tvTitle.setText("供应商领料");
                fragments.add(new Fragment3HwOutMain());
                fragments.add(new Fragment3HwOutDetail());
                break;
            case Config.YbOut4P2Activity:
                binding.topActivity.tvTitle.setText("样板出库");
                fragments.add(new FragmentYbOutMain());
                fragments.add(new FragmentYbOutDetail());
                break;
            case Config.WorkOrgIn4P2Activity:
                binding.topActivity.tvTitle.setText("业务组织入库");
                fragments.add(new FragmentPrisMain());
                fragments.add(new FragmentPrisDetail());
                break;
            case Config.SupplierIn4P2Activity:
                binding.topActivity.tvTitle.setText("供应商入库");
                fragments.add(new Fragment3HwInMain());
                fragments.add(new Fragment3HwInDetail());
                break;
            case Config.BoxReAddP2Activity:
                binding.topActivity.tvTitle.setText("混包新增");
                fragments.add(new FragmentBoxReAddMain());
                fragments.add(new FragmentBoxReAddDetail());
                break;
            case Config.ProductGet4BoxP2Activity:
                binding.topActivity.tvTitle.setText("码拍领料");//生产领料单
                fragments.add(new FragmentPrGet4P1BoxMain());
                fragments.add(new FragmentProductGet4P14P2BoxDetail());//与FragmentProductGet4P1BoxDetail(一期的)区别：查物料不需要org
                break;

            /*-----------------------------------------------------------------一期单据----------------------------------------------*/
            case Config.BoxReAddP1Activity:
                binding.topActivity.tvTitle.setText("混包新增");
                fragments.add(new FragmentBoxReAddMain());
                fragments.add(new FragmentBoxReAddDetail());
                break;
            case Config.Tb1HunInActivity:
                binding.topActivity.tvTitle.setText("挑板入库1(混包)");
                fragments.add(new FragmentBoxReAddMain());
                fragments.add(new FragmentBoxReAddDetail());
                break;
            case Config.Tb2HunInActivity:
                binding.topActivity.tvTitle.setText("挑板入库2(混包)");
                fragments.add(new FragmentBoxReAddMain());
                fragments.add(new FragmentBoxReAddDetail());
                break;
            case Config.Tb3HunInActivity:
                binding.topActivity.tvTitle.setText("挑板入库3(混包)");
                fragments.add(new FragmentBoxReAddMain());
                fragments.add(new FragmentBoxReAddDetail());
                break;
            case Config.SplitBoxHunInActivity:
                binding.topActivity.tvTitle.setText("拆包入库(混包)");
                fragments.add(new FragmentBoxReAddMain());
                fragments.add(new FragmentBoxReAddDetail());
                break;
            case Config.ZbCheJianHunInActivity:
                binding.topActivity.tvTitle.setText("混包入库");
                fragments.add(new FragmentBoxReAddMain());
                fragments.add(new FragmentBoxReAddDetail());
                break;
            case Config.Bg1CheJianHunInActivity:
                binding.topActivity.tvTitle.setText("混包入库");
                fragments.add(new FragmentBoxReAddMain());
                fragments.add(new FragmentBoxReAddDetail());
                break;
            case Config.Bg2CheJianHunInActivity:
                binding.topActivity.tvTitle.setText("混包入库");
                fragments.add(new FragmentBoxReAddMain());
                fragments.add(new FragmentBoxReAddDetail());
                break;
            case Config.CpWgHunInActivity:
                binding.topActivity.tvTitle.setText("外购入库(混包)");
                fragments.add(new FragmentBoxReAddMain());
                fragments.add(new FragmentBoxReAddDetail());
                break;
            case Config.GbHunInActivity:
                binding.topActivity.tvTitle.setText("改板入库(混包)");
                fragments.add(new FragmentBoxReAddMain());
                fragments.add(new FragmentBoxReAddDetail());
                break;
            case Config.BoxReBoxP1Activity:
                binding.topActivity.tvTitle.setText("混包追加");
                fragments.add(new FragmentBoxReBoxMain());
                fragments.add(new FragmentBoxReBoxDetail());
                break;

            case Config.ProductInStoreActivity:
                binding.topActivity.tvTitle.setText("简单生产入库");
                fragments.add(new FragmentPrisMain());
                fragments.add(new FragmentPrisDetail());
                break;
            case Config.ProductGet4BoxActivity:
                binding.topActivity.tvTitle.setText("码拍领料");//生产领料单
                fragments.add(new FragmentPrGet4P1BoxMain());
                fragments.add(new FragmentProductGet4P1BoxDetail());
                break;
            case Config.Tb1DiGetActivity:
                binding.topActivity.tvTitle.setText("挑板领料1(混包)");//生产领料单
                fragments.add(new FragmentPrGet4P1BoxMain());
                fragments.add(new FragmentProductGet4P1BoxDetail());
                break;
            case Config.Tb2DiGetActivity:
                binding.topActivity.tvTitle.setText("挑板领料2(混包)");//生产领料单
                fragments.add(new FragmentPrGet4P1BoxMain());
                fragments.add(new FragmentProductGet4P1BoxDetail());
                break;
            case Config.Tb3DiGetActivity:
                binding.topActivity.tvTitle.setText("挑板领料3(混包)");//生产领料单
                fragments.add(new FragmentPrGet4P1BoxMain());
                fragments.add(new FragmentProductGet4P1BoxDetail());
                break;
            case Config.GbDiGetActivity:
                binding.topActivity.tvTitle.setText("改板领料(混包)");//生产领料单
                fragments.add(new FragmentPrGet4P1BoxMain());
                fragments.add(new FragmentProductGet4P1BoxDetail());
                break;
            case Config.SplitBoxDiGetActivity:
                binding.topActivity.tvTitle.setText("拆包领料(混包)");//生产领料单
                fragments.add(new FragmentPrGet4P1BoxMain());
                fragments.add(new FragmentProductGet4P1BoxDetail());
                break;
            case Config.ZbCheJianDiGetActivity:
                binding.topActivity.tvTitle.setText("底领料(混包)");//生产领料单
                fragments.add(new FragmentPrGet4P1BoxMain());
                fragments.add(new FragmentProductGet4P1BoxDetail());
                break;
            case Config.Bg1CheJianDiGetActivity:
                binding.topActivity.tvTitle.setText("底领料");//生产领料单
                fragments.add(new FragmentPrGet4P1BoxMain());
                fragments.add(new FragmentProductGet4P1BoxDetail());
                break;
            case Config.Bg2CheJianDiGetActivity:
                binding.topActivity.tvTitle.setText("底领料");//生产领料单
                fragments.add(new FragmentPrGet4P1BoxMain());
                fragments.add(new FragmentProductGet4P1BoxDetail());
                break;
            case Config.DB4P1BoxActivity:
                binding.topActivity.tvTitle.setText("箱码调拨单");
                fragments.add(new FragmentDB4P1BoxMain());
                fragments.add(new FragmentDB4P1BoxDetail());
                break;
            case Config.P1PdCgrk2ProductGetActivity://采购入库单下推简单生产领料单
                binding.topActivity.tvTitle.setText(getString(R.string.p2_cgrk2production_get));
                fragments.add(new FragmentP1PdCgrk2ProductionGetMain());
                fragments.add(new FragmentP1PdCgrk2ProductionGetDetail());
                break;
            case Config.P1PdProductGet2CprkActivity:
                binding.topActivity.tvTitle.setText("水板采购");//生产领料单下推产品入库单(整单装箱)
                fragments.add(new FragmentPG2CprkMain());
                fragments.add(new FragmentPG2CprkDetail());
                break;
            case Config.P1PdProductGet2Cprk2Activity:
                binding.topActivity.tvTitle.setText("码拍入窑");//生产领料单下推产品入库单(重新装箱)
                fragments.add(new FragmentPG2Cprk2Main());
                fragments.add(new FragmentPG2Cprk2Detail());
                break;
            case Config.ProductGetActivity:
                binding.topActivity.tvTitle.setText("简单生产领料");
                fragments.add(new FragmentPrGetMain());
                fragments.add(new FragmentPrGetDetail());
                break;
            case Config.DBActivity:
                binding.topActivity.tvTitle.setText("组织间调拨/箱码");
                fragments.add(new FragmentDBMain());
                fragments.add(new FragmentDBBoxDetail());
                break;
            case Config.DBCopy2P2Activity:
                binding.topActivity.tvTitle.setText("组织间调拨/箱码");
                fragments.add(new FragmentDBMain());
                fragments.add(new FragmentDBBoxDetail());
                break;
            case Config.DBClientActivity:
                binding.topActivity.tvTitle.setText("调拨(客户在途)");
                fragments.add(new FragmentDBMain());
                fragments.add(new FragmentDBBoxDetail());
                break;
            case Config.DBStorageActivity:
                binding.topActivity.tvTitle.setText("调拨(在途仓库)");
                fragments.add(new FragmentDBMain());
                fragments.add(new FragmentDBBoxDetail());
                break;
            case Config.DB2Activity:
                binding.topActivity.tvTitle.setText("跨组织调拨单");
                fragments.add(new FragmentDB2Main());
                fragments.add(new FragmentDBDetail());
                break;
            case Config.SaleOutActivity:
                binding.topActivity.tvTitle.setText("销售出库单");
                fragments.add(new FragmentSaleOutMain());
                fragments.add(new FragmentSaleOutDetail());
                break;
            case Config.PurchaseInStoreActivity://???不存在
                binding.topActivity.tvTitle.setText("采购入库单");
                fragments.add(new FragmentPISMain());
                fragments.add(new FragmentPISDetail());
                break;
            case Config.PdSaleOrder2SaleOut4BoxActivity:
                binding.topActivity.tvTitle.setText("销售订单下推销售出库(箱码)");
                fragments.add(new FragmentSaleOut4PDBoxMain());
                fragments.add(new FragmentSaleOutDetailForPDBox());
                break;
            case Config.PdSaleOrder2SaleOut2Activity:
                binding.topActivity.tvTitle.setText("VMI销售订单下推销售出库单");
                fragments.add(new FragmentSaleOut4PDMain());
                fragments.add(new FragmentSaleOutDetailForPD());
                break;
            case Config.PdBackMsg2SaleBackActivity:
                binding.topActivity.tvTitle.setText("退货通知单下推销售退货单");
                fragments.add(new FragmentBackMsg2SaleBackMain());
                fragments.add(new FragmentBackMsg2SaleBackDetail());
                break;
            case Config.ChangeGetActivity:
                binding.topActivity.tvTitle.setText("调整领料(整包)");
                fragments.add(new FragmentTbGetMain());
                fragments.add(new FragmentPrGetDetail());
                break;
            case Config.ChangeLvGetActivity:
                binding.topActivity.tvTitle.setText("调整领料(整包)");
                fragments.add(new FragmentTbGetMain());
                fragments.add(new FragmentPrGetDetail());
                break;
            case Config.ChangeModelGetActivity:
                binding.topActivity.tvTitle.setText("调整领料(整包)");
                fragments.add(new FragmentTbGetMain());
                fragments.add(new FragmentPrGetDetail());
                break;
            case Config.ZbGet1Activity:
                binding.topActivity.tvTitle.setText("简单生产领料1");
                fragments.add(new FragmentTbGetMain());
                fragments.add(new FragmentPrGetDetail());
                break;
            case Config.ZbGet2Activity:
                binding.topActivity.tvTitle.setText("简单生产领料2");
                fragments.add(new FragmentTbGetMain());
                fragments.add(new FragmentPrGetDetail());
                break;
            case Config.ZbGet3Activity:
                binding.topActivity.tvTitle.setText("简单生产领料3");
                fragments.add(new FragmentTbGetMain());
                fragments.add(new FragmentPrGetDetail());
                break;
            case Config.ZbGet4Activity:
                binding.topActivity.tvTitle.setText("简单生产领料4");
                fragments.add(new FragmentTbGetMain());
                fragments.add(new FragmentPrGetDetail());
                break;
            case Config.ZbGet5Activity:
                binding.topActivity.tvTitle.setText("简单生产领料5");
                fragments.add(new FragmentTbGetMain());
                fragments.add(new FragmentPrGetDetail());
                break;
            case Config.SplitBoxGetActivity:
                binding.topActivity.tvTitle.setText("拆包领料(整包)");
                fragments.add(new FragmentTbGetMain());
                fragments.add(new FragmentPrGetDetail());
                break;
            case Config.ZbCheJianDiZGetActivity:
                binding.topActivity.tvTitle.setText("底领料(整包)");
                fragments.add(new FragmentTbGetMain());
                fragments.add(new FragmentPrGetDetail());
                break;
            case Config.TbGetActivity:
                binding.topActivity.tvTitle.setText("挑板领料1(整包)");
                fragments.add(new FragmentTbGetMain());
                fragments.add(new FragmentPrGetDetail());
                break;
            case Config.TbGet2Activity:
                binding.topActivity.tvTitle.setText("挑板领料2(整包)");
                fragments.add(new FragmentTbGetMain());
                fragments.add(new FragmentPrGetDetail());
                break;
            case Config.TbGet3Activity:
                binding.topActivity.tvTitle.setText("挑板领料3(整包)");
                fragments.add(new FragmentTbGetMain());
                fragments.add(new FragmentPrGetDetail());
                break;
            case Config.ChangeInActivity:
                binding.topActivity.tvTitle.setText("调整入库(整包)");
                fragments.add(new FragmentPrisTBMain());
                fragments.add(new FragmentPrisTBDetail());
                break;
            case Config.ChangeLvInActivity:
                binding.topActivity.tvTitle.setText("调整入库(整包)");
                fragments.add(new FragmentPrisTBMain());
                fragments.add(new FragmentPrisTBDetail());
                break;
            case Config.ChangeModelInActivity:
                binding.topActivity.tvTitle.setText("调整入库(整包)");
                fragments.add(new FragmentPrisTBMain());
                fragments.add(new FragmentPrisTBDetail());
                break;
            case Config.SplitBoxInActivity:
                binding.topActivity.tvTitle.setText("拆包入库(整包)");
                fragments.add(new FragmentPrisTBMain());
                fragments.add(new FragmentPrisTBDetail());
                break;
            case Config.ZbCheJianInActivity:
                binding.topActivity.tvTitle.setText("整包入库");
                fragments.add(new FragmentPrisTBMain());
                fragments.add(new FragmentPrisTBDetail());
                break;
            case Config.Bg1CheJianInActivity:
                binding.topActivity.tvTitle.setText("整包入库");
                fragments.add(new FragmentPrisTBMain());
                fragments.add(new FragmentPrisTBDetail());
                break;
            case Config.Bg2CheJianInActivity:
                binding.topActivity.tvTitle.setText("整包入库");
                fragments.add(new FragmentPrisTBMain());
                fragments.add(new FragmentPrisTBDetail());
                break;
            case Config.CpWgInActivity:
                binding.topActivity.tvTitle.setText("外购入库(整包)");
                fragments.add(new FragmentPrisTBMain());
                fragments.add(new FragmentPrisTBDetail());
                break;
            case Config.ZbIn1Activity:
                binding.topActivity.tvTitle.setText("简单生产入库1");
                fragments.add(new FragmentPrisTBMain());
                fragments.add(new FragmentPrisTBDetail());
                break;
            case Config.ZbIn2Activity:
                binding.topActivity.tvTitle.setText("简单生产入库2");
                fragments.add(new FragmentPrisTBMain());
                fragments.add(new FragmentPrisTBDetail());
                break;
            case Config.ZbIn3Activity:
                binding.topActivity.tvTitle.setText("简单生产入库3");
                fragments.add(new FragmentPrisTBMain());
                fragments.add(new FragmentPrisTBDetail());
                break;
            case Config.ZbIn4Activity:
                binding.topActivity.tvTitle.setText("简单生产入库4");
                fragments.add(new FragmentPrisTBMain());
                fragments.add(new FragmentPrisTBDetail());
                break;
            case Config.ZbIn5Activity:
                binding.topActivity.tvTitle.setText("简单生产入库5");
                fragments.add(new FragmentPrisTBMain());
                fragments.add(new FragmentPrisTBDetail());
                break;
            case Config.TbInActivity:
                binding.topActivity.tvTitle.setText("挑板入库1(整包)");
                fragments.add(new FragmentPrisTBMain());
                fragments.add(new FragmentPrisTBDetail());
                break;
            case Config.TbIn2Activity:
                binding.topActivity.tvTitle.setText("挑板入库2(整包)");
                fragments.add(new FragmentPrisTBMain());
                fragments.add(new FragmentPrisTBDetail());
                break;
            case Config.TbIn3Activity:
                binding.topActivity.tvTitle.setText("挑板入库3(整包)");
                fragments.add(new FragmentPrisTBMain());
                fragments.add(new FragmentPrisTBDetail());
                break;
            case Config.GbGetActivity:
                binding.topActivity.tvTitle.setText("改板领料(整包)");
                fragments.add(new FragmentGbGetMain());
                fragments.add(new FragmentPrGetDetail());
                break;
            case Config.GbInActivity:
                binding.topActivity.tvTitle.setText("改板入库(整包)");
                fragments.add(new FragmentGbInMain());
                fragments.add(new FragmentPrisDetail());
                break;
            case Config.DhInActivity:
                binding.topActivity.tvTitle.setText("到货入库1");
                fragments.add(new FragmentPrisDhMain());
                fragments.add(new FragmentPrisDhDetail());
                break;
            case Config.DhIn2Activity:
                binding.topActivity.tvTitle.setText("到货入库2");
                fragments.add(new FragmentPrisDh2Main());
                fragments.add(new FragmentPrisDhDetail());
                break;
//            case Config.SimpleInActivity:
//                binding.topActivity.tvTitle.setText("简单生产入库");
//                fragments.add(new FragmentPrisSimpleInMain());
//                fragments.add(new FragmentPrisDetail());
//                break;
            case Config.YbOutActivity:
                binding.topActivity.tvTitle.setText("样板出库");
                fragments.add(new FragmentYbOutMain());
                fragments.add(new FragmentYbOutDetail());
                break;
            case Config.HwOut3Activity:
                binding.topActivity.tvTitle.setText("第三方货物出库");
                fragments.add(new Fragment3HwOutMain());
                fragments.add(new Fragment3HwOutDetail());
                break;
            case Config.HwIn3Activity:
                binding.topActivity.tvTitle.setText("第三方货物入库");
                fragments.add(new Fragment3HwInMain());
                fragments.add(new Fragment3HwInDetail());
                break;

            case Config.FLInStoreP1Activity://方料入库
                binding.topActivity.tvTitle.setText("方料入库");
                fragments.add(new FragmentCgOrder2WgrkMain());
                fragments.add(new FragmentCgOrder2WgrkBoxDetail());
                break;

            case Config.PkuiVMIActivity:
                binding.topActivity.tvTitle.setText("VMI盘亏入库");
                fragments.add(new FragmentPYingMain());
                fragments.add(new FragmentPKDetail());
                break;
            case Config.PdDbApply2DBActivity:
                binding.topActivity.tvTitle.setText("调拨申请单下推直接调拨单");
                fragments.add(new FragmentDbApply2DBMain());
                fragments.add(new FragmentDbApply2DBDetail());
                break;
            case Config.PdDbApply2DB4VMIActivity:
                binding.topActivity.tvTitle.setText("VMI调拨申请单下推直接调拨单");
                fragments.add(new FragmentDbApply2DBMain());
                fragments.add(new FragmentDbApply2DBDetail());
                break;
            case Config.PdOutApply2OtherOutActivity:
                binding.topActivity.tvTitle.setText("出库申请单下推其他出库单");
                fragments.add(new FragmentDbApply2DBMain());
                fragments.add(new FragmentDbApply2DBDetail());
                break;

//            case Config.DgInActivity:
//                binding.topActivity.tvTitle.setText("到柜入库");
//                fragments.add(new FragmentPrisDGMain());
//                fragments.add(new FragmentPrisDetail());
//                break;
//            case Config.DcInActivity:
//                binding.topActivity.tvTitle.setText("代存入库");
//                fragments.add(new FragmentGbInMain());
//                fragments.add(new FragmentPrisDetail());
//                break;

        }
        //设置pager
        if (null==stripAdapter){
            stripAdapter = new StripAdapter(getSupportFragmentManager(), fragments, titles);
            binding.viewpager.setAdapter(stripAdapter);
            binding.tabstrip.setShouldExpand(true);
            binding.tabstrip.setViewPager(binding.viewpager);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.tabstrip.setDividerColor(getColor(R.color.cpb_blue));
                binding.tabstrip.setIndicatorColor(getColor(R.color.cpb_blue));
            }else{
                binding.tabstrip.setDividerColor(Color.BLUE);
                binding.tabstrip.setIndicatorColor(Color.BLUE);
            }
            binding.tabstrip.setUnderlineHeight(2);
            binding.tabstrip.setTextSize(24);
            binding.tabstrip.setIndicatorHeight(4);
        }
    }

    @Override
    protected void initListener() {
        binding.topActivity.ishebing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isHebing =b;
            }
        });
        binding.topActivity.isAutoAdd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isAutoAdd = b;
            }
        });
    }

    @Override
    protected void OnReceive(String code) {
        Toast.showText(mContext,"本Activity获得条码"+code);
    }

    public long getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(long ordercode) {
        this.ordercode = ordercode;
    }
    public boolean isHebing() {
        return isHebing;
    }

    public boolean isAutoAdd() {
        return isAutoAdd;
    }

    public boolean isHasLock() {
        return hasLock;
    }


    public void setHasLock(boolean hasLock) {
        this.hasLock = hasLock;
    }
    //用于表头key
    public String getActivityMain(){
        return activity + CommonUtil.getAccountID();
    }
    public int getActivity(){
        return activity;
    }

    public String getCarNo() {
        return carNo==null?"":carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getFBillNo() {
        return FBillNo;
    }

    public void setFBillNo(String FBillNo) {
        this.FBillNo = FBillNo;
    }

    public void setOrgIn(Org orgIn) {
        this.orgIn = orgIn;
    }
    public void setOrgApp(Org orgApp) {
        this.orgApp = orgApp;
    }
    public void setOrgOut(Org orgOut) {
        this.orgOut = orgOut;
    }

    public void setHuozhuIn(Org huozhuIn) {
        this.huozhuIn = huozhuIn;
    }

    public void setHuozhuOut(Org huozhuOut) {
        this.huozhuOut = huozhuOut;
    }

    public void setStorage(Storage s) {
        this.storage = s;
    }
    public Storage getStorage() {
        return storage!=null?storage:new Storage("","","","","","");
    }
    public void setStorageOut(Storage s) {
        this.storageOut = s;
    }
    public Storage getStorageOut() {
        return storageOut!=null?storageOut:new Storage("","","","","","");
    }
    public void setStorageIn(Storage s) {
        this.storageIn = s;
    }
    public Storage getStorageIn() {
        return storageIn!=null?storageIn:new Storage("","","","","","");
    }


    public String getBatch() {
        return Batch==null?"":Batch;
    }

    public void setBatch(String batch) {
        Batch = batch;
    }

    public String getBoxCode() {
        return BoxCode==null?"":BoxCode;
    }

    public void setBoxCode(String boxCode) {
        BoxCode = boxCode;
    }

    public void setClient(Client s) {
        this.client = s;
    }
    public void setSuppliers(Suppliers s) {
        this.suppliers = s;
    }

    public Client getClient() {
        return client!=null?client:new Client("","","","","");
    }
    public Suppliers getSuppliers() {
        return suppliers!=null?suppliers:new Suppliers("","","","","","","","","","","","","","");
    }

    public void setDBType(String DBType) {
        this.DBType = DBType;
    }

    public void setDBDirection(String DBDirection) {
        this.DBDirection = DBDirection;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFOrderNo() {
        return FOrderNo;
    }

    public void setFOrderNo(String fOrderNo) {
        this.FOrderNo = fOrderNo;
    }

    public StoreMan getManStoreObj() {
        return ManStoreObj==null?new StoreMan("","","","",""):ManStoreObj;
    }

    public void setManStoreObj(StoreMan manStoreObj) {
        ManStoreObj = manStoreObj;
    }
    public void setManStore(String manStore) {
        ManStore = manStore;
    }

    public void setManSale(String manSale) {
        ManSale = manSale;
    }
    public void setManBuyer(String manSale) {
        ManBuyer = manSale;
    }
    public void setManGet(String manGet) {
        ManGet = manGet;
    }

    public Department getDepartMentObj() {
        return DepartMentObj==null?new Department("","","","",""):DepartMentObj;
    }

    public void setDepartMentObj(Department departMentObj) {
        DepartMentObj = departMentObj;
    }

    public void setDepartMent(String departMent) {
        DepartMent = departMent;
    }
    public void setDepartMentBuy(String departMent) {
        DepartMentBuy = departMent;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public void setStorage(boolean storage) {
        isStorage = storage;
    }

    public String getDBType() {
        return DBType==null?"":DBType;
    }
    public boolean isStorage() {
        return isStorage;
    }
    public String getDBDirection() {
        return DBDirection==null?"":DBDirection;
    }

    public String getNote() {
        return note==null?"":note;
    }

    public String getManStore() {
        return ManStore==null?"":ManStore;
    }

    public String getManSale() {
        return ManSale==null?"":ManSale;
    }
    public String getManBuyer() {
        return ManBuyer==null?"":ManBuyer;
    }
    public String getManGet() {
        return ManGet==null?"":ManGet;
    }

    public String getDepartMent() {
        return DepartMent==null?"":DepartMent;
    }
    public String getDepartMentBuy() {
        return DepartMentBuy==null?"":DepartMentBuy;
    }

    public String getDate() {
        return date==null?"":date;
    }

    public CheckBox getIsHebing() {
        return binding.topActivity.ishebing;
    }

    public CheckBox getIsAuto() {
        return binding.topActivity.isAutoAdd;
    }

    public Org getOrgIn() {
        return orgIn==null?new Org("","","",""):orgIn;
    }
    public Org getOrgApp() {
        return orgApp==null?new Org("","","",""):orgApp;
    }
    public String getOrgIn(int type) {
        if (type==0){
            return orgIn==null?"":orgIn.FNumber;
        }else{
            return orgIn==null?"":orgIn.FOrgID;
        }
    }

    public Org getOrgOut() {
        return orgOut==null?new Org("","","",""):orgOut;
    }
    public String getOrgOut(int type) {
        if (type==0){
            return orgOut==null?"":orgOut.FNumber;
        }else{
            return orgOut==null?"":orgOut.FOrgID;
        }
    }

    public Org getHuozhuIn() {
        return huozhuIn;
    }
    public String getHuozhuIn(int type) {
        if (type==0){
            return huozhuIn==null?"":huozhuIn.FNumber;
        }else{
            return huozhuIn==null?"":huozhuIn.FOrgID;
        }
    }

    public Org getHuozhuOut() {
        return huozhuOut==null?new Org("","","",""):huozhuOut;
    }
    public String getHuozhuOut(int type) {
        if (type==0){
            return huozhuOut==null?"":huozhuOut.FNumber;
        }else{
            return huozhuOut==null?"":huozhuOut.FOrgID;
        }
    }

    public T_mainDao getT_mainDao() {
        return t_mainDao;
    }

    public T_DetailDao getT_detailDao() {
        return t_detailDao;
    }
    public DaoSession getDaoSession() {
        return daoSession;
    }
    public Gson getGson() {
        return gson;
    }
    public ShareUtil getShare() {
        return share;
    }
    public ScanManager getScanManager() {
        return mCaptureManager;
    }
    public void setScanManager(ScanManager scanManager) {
        mCaptureManager = scanManager;
    }
    public Bundle getSavedInstanceState() {
        return savedInstanceState;
    }

    public String getWlCompany() {
        return wlCompany;
    }

    public void setWlCompany(String wlCompany) {
        this.wlCompany = wlCompany;
    }

    public String getCarboxNo() {
        return carboxNo;
    }

    public void setCarboxNo(String carboxNo) {
        this.carboxNo = carboxNo;
    }




    public static void start(Context context, int activity){
        Intent intent = new Intent(context,PagerForActivity.class);
        intent.putExtra("activity",activity);
        context.startActivity(intent);
    }
    public static void start(Context context, int activity, ArrayList<String> fid){
        Intent intent = new Intent(context,PagerForActivity.class);
        intent.putExtra("activity",activity);
        intent.putStringArrayListExtra("fid", fid);
        context.startActivity(intent);
    }
    /**
     * 权限处理
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (null!=mCaptureManager)mCaptureManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //连接打印机
    private void connectPrint(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                App.getInstance().connectPrint();
            }
        }).start();
    }
    @Override
    protected void onDestroy() {
        App.getInstance().disPrint();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
        ab.setTitle("确认退出");
        if (activity == Config.OutsourcingInActivity){
            ab.setMessage("退出会自动执行完单,是否退出?");
        }else{
            ab.setMessage("是否退出?");
        }
        ab.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ordercode++;
                Log.e("ordercode", ordercode + "");
                share.setOrderCode(activity, ordercode);
                finish();
            }
        });
        ab.setNegativeButton("取消", null);
        ab.create().show();
//        super.onBackPressed();
    }
}
