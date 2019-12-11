package com.fangzuo.assist.cloud.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Adapter.MenuFragmentAdapter;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Fragment.P1OneFragment;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.ShareUtil;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.databinding.ActivityHomeBinding;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {
    ActivityHomeBinding binding;
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
        }
    }

    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        initBar();
        setDrawerLeftEdgeSize(this, binding.drawerLayout, 0.1f);//设置抽屉滑动响应范围
        binding.tvUser.setText(getString(R.string.this_user)+ ShareUtil.getInstance(mContext).getUserName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.tvData.setText(getString(R.string.data_center)+ Hawk.get(Info.user_data,""));
    }

    @Override
    protected void initData() {
        FragmentManager fm = getSupportFragmentManager();
        P1OneFragment purchaseFragment = new P1OneFragment();
//        SaleFragment saleFragment = new SaleFragment();
//        StorageFragment storageFragment = new StorageFragment();
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(purchaseFragment);
//        fragments.add(saleFragment);
//        fragments.add(storageFragment);
        MenuFragmentAdapter menuFragmentAdapter = new MenuFragmentAdapter(fm, fragments);
        binding.viewPager.setAdapter(menuFragmentAdapter);
        binding.viewPager.setCurrentItem(0);
        binding.tabBottom.ivPurchase.setImageResource(R.mipmap.purchase);
        binding.tabBottom.tvPurchase.setTextColor(getResources().getColor(R.color.bottombartv));
        binding.tvAbout.setText(getString(R.string.version_no)+ Info.getAppNo());
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

}
