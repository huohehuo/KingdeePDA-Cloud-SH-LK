package com.fangzuo.assist.cloud.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Adapter.MenuFragmentAdapter;
import com.fangzuo.assist.cloud.Fragment.pushdown.AttrsFragment;
import com.fangzuo.assist.cloud.Fragment.pushdown.CheckFragment;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.PagerSlidingTabStrip;

import java.util.ArrayList;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AttrsPagerActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindColor(R.color.cpb_blue)
    int cpb_blue;
    public int tag;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindColor(R.color.bottombartv)
    int tvcolor;
    private String billNo;
    private String interId;
    private String enterId;
    private int activity;
    private AttrsPagerActivity attrsPagerActivity;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_attrs_pager);
        ButterKnife.bind(this);
        attrsPagerActivity = this;
        //    //接收
    Intent intent = getIntent();
        if (intent != null) {
            activity =   intent.getIntExtra("activity",0);
            billNo = intent.getStringExtra("billNo");
            interId = intent.getStringExtra("interId");
            enterId = intent.getStringExtra("enterId");

    }
//        tag = getIntent().getExtras().getInt("123");
//        Log.e("获取到--tag--", tag + "");
    }

    @Override
    protected void initData() {
        initFragments();
    }

    private void initFragments() {
        FragmentManager fm = getSupportFragmentManager();
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new AttrsFragment());
        fragments.add(new CheckFragment());
        MenuFragmentAdapter menuFragmentAdapter = new MenuFragmentAdapter(fm, fragments);
        viewPager.setAdapter(menuFragmentAdapter);
        viewPager.setCurrentItem(0);
    }

    @Override
    protected void initListener() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        viewPager.setCurrentItem(0, true);
                        resetBottomView();
                        btn1.setTextColor(tvcolor);
                        break;
                    case 1:
                        viewPager.setCurrentItem(1, true);
                        resetBottomView();
                        btn2.setTextColor(tvcolor);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void resetBottomView() {
        btn1.setTextColor(getResources().getColor(R.color.gray));
        btn2.setTextColor(getResources().getColor(R.color.gray));
    }


//    //接收
//    Intent intent = getIntent();
//        if (intent != null) {
//        autoCode =   intent.getStringExtra("code");
//        fidcontainer = intent.getStringArrayListExtra("fid");
//        Lg.e("Intent:"+autoCode);
//        Lg.e("Intent:"+fidcontainer.toString());
//    }
//    //代数据启动页面
//    public static void start(Context context, String code, ArrayList<String> fid) {
//        Intent starter = new Intent(context, PushDownPagerActivity.class);
//        starter.putExtra("code", code);
//        starter.putStringArrayListExtra("fid", fid);
//        context.startActivity(starter);
//    }

    @Override
    protected void OnReceive(String code) {
        Log.e("code:", "PushDownPagerActivity-" + code);
    }

    public int getTitles() {
        return tag;
    }

    public String getBillNo() {
        return billNo==null?"":billNo;
    }

    public String getInterId() {
        return interId==null?"":interId;
    }

    public String getEnterId() {
        return enterId==null?"":enterId;
    }

    public int getActivity() {
        return activity;
    }

    public AttrsPagerActivity getAttrsPagerActivity() {
        return attrsPagerActivity;
    }

    public static void start(Context context, int activity, String billno, String interid, String enterid) {
        Intent starter = new Intent(context, AttrsPagerActivity.class);
//        starter.putExtra("123", id);
        starter.putExtra("activity", activity);
        starter.putExtra("billNo", billno);
        starter.putExtra("interId", interid);
        starter.putExtra("enterId", enterid);
        context.startActivity(starter);
    }
    @OnClick({R.id.btn1, R.id.btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                viewPager.setCurrentItem(0, true);
                resetBottomView();
                btn1.setTextColor(tvcolor);
                break;
            case R.id.btn2:
                viewPager.setCurrentItem(1, true);
                resetBottomView();
                btn2.setTextColor(tvcolor);
                break;
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void clickToCheckView(){
        viewPager.setCurrentItem(1, true);
        resetBottomView();
        btn2.setTextColor(tvcolor);
    }

}
