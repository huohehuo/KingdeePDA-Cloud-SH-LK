package com.fangzuo.assist.cloud.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Adapter.MenuFragmentAdapter;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Fragment.P2.FourFragment;
import com.fangzuo.assist.cloud.Fragment.P2.OneFragment;
import com.fangzuo.assist.cloud.Fragment.P2.ThrFragment;
import com.fangzuo.assist.cloud.Fragment.P2.TwoFragment;
import com.fangzuo.assist.cloud.Fragment.PurchaseFragment;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.ShareUtil;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.databinding.ActivityHomeP2Binding;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

public class  HomeP2Activity extends BaseActivity {
    ActivityHomeP2Binding binding;
    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void receiveEvent(ClassEvent event) {
        switch (event.Msg){
            case EventBusInfoCode.Updata_OK://回单成功
                String time = (String)event.Msg2;
                String size = (String)event.Msg3;
                long endTime = System.currentTimeMillis();
                AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                ab.setTitle("下载完成");
                ab.setMessage("耗时:" + (endTime - Long.parseLong(time)) + "ms" + ",共插入" + size + "条数据");
                ab.setPositiveButton("确认",null);
                ab.create().show();
                break;
            case EventBusInfoCode.Updata_Error://回单失败
                Toast.showText(mContext,(String)event.postEvent);
                break;
            case EventBusInfoCode.Click_Order://回单失败
                int activity = (int)event.postEvent;
                clickOrder(activity);
                break;
            case EventBusInfoCode.Updata_Account://用于切换账套后，更新数据中心的显示
                binding.tvData.setText(getString(R.string.data_center)+ Hawk.get(Info.user_data,""));
                break;

        }
    }

    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_p2);
        initBar();
        setDrawerLeftEdgeSize(this, binding.drawerLayout, 0.1f);//设置抽屉滑动响应范围
        binding.tvUser.setText(getString(R.string.this_user)+ ShareUtil.getInstance(mContext).getUserName());
        binding.tvData.setText(getString(R.string.data_center)+ Hawk.get(Info.user_data,""));
    }
    @Override
    protected void onResume() {
        super.onResume();
        binding.tvData.setText(getString(R.string.data_center)+ Hawk.get(Info.user_data,""));
    }
    @Override
    protected void initData() {
        FragmentManager fm = getSupportFragmentManager();
        OneFragment purchaseFragment = new OneFragment();
        TwoFragment twoFragment = new TwoFragment();
        ThrFragment thrFragment = new ThrFragment();
        FourFragment fourFragment = new FourFragment();
//        SaleFragment saleFragment = new SaleFragment();
//        StorageFragment storageFragment = new StorageFragment();
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(purchaseFragment);
        fragments.add(twoFragment);
        fragments.add(thrFragment);
        fragments.add(fourFragment);
//        fragments.add(saleFragment);
//        fragments.add(storageFragment);
        MenuFragmentAdapter menuFragmentAdapter = new MenuFragmentAdapter(fm, fragments);
        binding.viewPager.setAdapter(menuFragmentAdapter);
        binding.viewPager.setCurrentItem(Hawk.get(Config.Fragment_Tag,0));
        binding.tabBottom.ivPurchase.setImageResource(R.mipmap.purchase);
        binding.tabBottom.tvPurchase.setTextColor(getResources().getColor(R.color.bottombartv));
        binding.tvAbout.setText(getString(R.string.version_no)+ Info.getAppNo());
        binding.indicator.setUpWidthViewPager(binding.viewPager,Hawk.get(Config.Fragment_Tag,0));
    }

    @Override
    protected void initListener() {
        binding.ivUpdata.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle("是否进入回执调试")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startNewActivity(BackJsonActivity.class,0,0,false, null);
                            }
                        })
                        .create().show();
                return true;
            }
        });
        binding.ivUpdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UpdataLocData.getInstance(mContext).alertToChoose();
            }
        });
        //viewPage选项卡
        binding.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        resetBottomView();
                        binding.tabBottom.ivPurchase.setImageResource(R.mipmap.purchase);
                        binding.tabBottom.tvPurchase.setTextColor(getResources().getColor(R.color.bottombartv));
                        break;
//                    case 1:
//                        resetBottomView();
//                        binding.tabBottom.ivSale.setImageResource(R.mipmap.sale);
//                        binding.tabBottom.tvSale.setTextColor(getResources().getColor(R.color.bottombartv));
//                        break;
//                    case 2:
//                        resetBottomView();
//                        binding.tabBottom.ivStorage.setImageResource(R.mipmap.storage);
//                        binding.tabBottom.tvStorage.setTextColor(getResources().getColor(R.color.bottombartv));
//                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        binding.tabBottom.bottomBtnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.viewPager.setCurrentItem(0, true);
                resetBottomView();
                binding.tabBottom.ivPurchase.setImageResource(R.mipmap.purchase);
                binding.tabBottom.tvPurchase.setTextColor(getResources().getColor(R.color.bottombartv));
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    binding.tabBottom.tvPurchase.setTextColor(getColor(R.color.bottombartv));
//                }else{

            }
        });
        binding.tabBottom.bottomBtnSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.viewPager.setCurrentItem(1, true);
                resetBottomView();
                binding.tabBottom.ivSale.setImageResource(R.mipmap.sale);
                binding.tabBottom.tvSale.setTextColor(getResources().getColor(R.color.bottombartv));
            }
        });
        binding.tabBottom.bottomBtnStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.viewPager.setCurrentItem(2, true);
                resetBottomView();
                binding.tabBottom.ivStorage.setImageResource(R.mipmap.storage);
                binding.tabBottom.tvStorage.setTextColor(getResources().getColor(R.color.bottombartv));
            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
                    binding.drawerLayout.openDrawer(GravityCompat.START);
                }else{
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });

    }

    @Override
    protected void OnReceive(String code) {

    }
    //初始化所有按钮
    private void resetBottomView() {
        binding.tabBottom.ivPurchase.setImageResource(R.mipmap.unpurchase);
        binding.tabBottom.ivSale.setImageResource(R.mipmap.unsale);
        binding.tabBottom.ivStorage.setImageResource(R.mipmap.unstorage);
        binding.tabBottom.ivSetting.setImageResource(R.mipmap.unsetting);
        binding.tabBottom.tvPurchase.setTextColor(getResources().getColor(R.color.fragment_text));
        binding.tabBottom.tvSale.setTextColor(getResources().getColor(R.color.fragment_text));
        binding.tabBottom.tvSetting.setTextColor(getResources().getColor(R.color.fragment_text));
        binding.tabBottom.tvStorage.setTextColor(getResources().getColor(R.color.fragment_text));
    }
    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private AlertDialog.Builder builder;
    String[] items_cp_get = new String[]{"业务组织领料", "供应商领料"};
    String[] items_cp_in = new String[]{"业务组织入库", "供应商入库"};

    //统一点击单据时跳转
    public void clickOrder(int activity) {
        switch (activity) {
            /*-------------二期单据---------------------------------------------*/
            case Config.P2ProductionInStoreActivity://简单生产入库-弃用
//                        PushDownSearchActivity.start(getActivity(),1);
                PushDownPagerActivity.start(mContext, 25);
                break;
            case Config.WgDryingInStoreActivity://外购烘干板入库
//                        PushDownSearchActivity.start(getActivity(),1);
                PushDownPagerActivity.start(mContext, 33);
                break;
            case Config.P2ProductionInStore2Activity://简单生产入库
//                        PushDownSearchActivity.start(getActivity(),1);
                PushDownPagerActivity.start(mContext, 27);
                break;
            case Config.P2PdCgrk2ProductGetActivity://采购入库单下推简单生产领料
//                        PushDownSearchActivity.start(getActivity(),1);
                PushDownPagerActivity.start(mContext, 26);
                break;
            case Config.ProductGet4P2Activity://原木领料
                PagerForActivity.start(mContext, Config.ProductGet4P2Activity);
                break;
            case Config.ProductGet4P24PihaoActivity://原木领料
                PagerForActivity.start(mContext, Config.ProductGet4P24PihaoActivity);
                break;
            case Config.ShuiBanGetActivity://水板板材拆托-水板领料
                PagerForActivity.start(mContext, Config.ShuiBanGetActivity);
                break;
            case Config.ShuiBanGet2Activity://水板板材拆托-水板领料
                PagerForActivity.start(mContext, Config.ShuiBanGet2Activity);
                break;
            case Config.OutKilnGetActivity://出窑领料
                PagerForActivity.start(mContext, Config.OutKilnGetActivity);
                break;
            case Config.DryingGetActivity://刨光板码托
                PagerForActivity.start(mContext, Config.DryingGetActivity);
                break;
            case Config.ProductInStore4P2Activity://水版产品入库
                PagerForActivity.start(mContext, Config.ProductInStore4P2Activity);
                break;
            case Config.ProductInStore4P2MpActivity://水板板材码拍
                PagerForActivity.start(mContext, Config.ProductInStore4P2MpActivity);
                break;
            case Config.DryingInStoreActivity://烘干板入库
                PagerForActivity.start(mContext, Config.DryingInStoreActivity);
                break;
            case Config.WortInStore4P2Activity://刨光干板入库
                PagerForActivity.start(mContext, Config.WortInStore4P2Activity);
                break;
            case Config.DBCopy2P2Activity://组织间调拨/箱码  复制与一期20191121
                PagerForActivity.start(mContext, Config.DBCopy2P2Activity);
                break;
            case Config.PrintHistory4P2Activity://条码补打
                PrintHistory4P2Activity.start(mContext);
                break;

            case Config.PrintBoxCodeActivity://箱码补打
                PrintBoxCodeActivity.start(mContext);
                break;
            case Config.SplitBoxP2Activity://拆箱
                SplitBoxP2Activity.start(mContext);
                break;
            case Config.DB4P2Activity://水板板材调拨--水板调拨
                PagerForActivity.start(mContext, Config.DB4P2Activity);
                break;
            case Config.ShuiBanDB4P2Activity://水板调拨入库
                PagerForActivity.start(mContext, Config.ShuiBanDB4P2Activity);
                break;
            case Config.DBInKiln4P2Activity://调拨入窑
                PagerForActivity.start(mContext, Config.DBInKiln4P2Activity);
                break;
            case Config.ProductGet4BoxP2Activity://码拍领料
                PagerForActivity.start(mContext, Config.ProductGet4BoxP2Activity);
                break;
            case Config.BoxReAddP2Activity://混包新增
                PagerForActivity.start(mContext, Config.BoxReAddP2Activity);
                break;
            //------------------------------------------------------------------------成品车间
            case Config.CpGetActivityDlg://成品加工领料
                // 创建对话框构建器
                builder = new AlertDialog.Builder(this);
                // 设置参数
                builder.setAdapter(
                        new ArrayAdapter<String>(this,
                                R.layout.item_choose, R.id.textView, items_cp_get),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                switch (which) {
                                    case 0:
                                        PagerForActivity.start(mContext, Config.WorkOrgGet4P2Activity);
                                        break;
                                    case 1:
                                        PagerForActivity.start(mContext, Config.SupplierGet4P2Activity);
                                        break;
                                }
                            }
                        });
                builder.create().show();
                break;
            case Config.CpInActivityDlg://成品加工入库
                // 创建对话框构建器
                builder = new AlertDialog.Builder(this);
                // 设置参数
                builder.setAdapter(
                        new ArrayAdapter<String>(this,
                                R.layout.item_choose, R.id.textView, items_cp_in),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                switch (which) {
                                    case 0:
                                        PagerForActivity.start(mContext, Config.WorkOrgIn4P2Activity);
//                                                startNewActivity(SaleOutActivity.class, null);
                                        break;
                                    case 1:
                                        PagerForActivity.start(mContext, Config.SupplierIn4P2Activity);
                                        break;
                                }
                            }
                        });
                builder.create().show();
                break;
            case Config.YbOut4P2Activity://样板出库
                PagerForActivity.start(mContext, Config.YbOut4P2Activity);
                break;
            case Config.PrintHistoryActivity://条码补打
                PrintHistoryActivity.start(mContext);
                break;
            case Config.PrintBeforeDataActivity://期初补打
                startNewActivity(PrintBeforeDataActivity.class,0,0,false, null);
                break;




            case 0:
                LoadingUtil.showAlter(mContext,"该单据待开发...");
                break;
        }
    }

}
