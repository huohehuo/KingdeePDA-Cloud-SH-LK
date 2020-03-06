package com.fangzuo.assist.cloud.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Adapter.StripAdapter;
import com.fangzuo.assist.cloud.Fragment.pushdown.ChooseFragment;
import com.fangzuo.assist.cloud.Fragment.pushdown.DownLoadPushFragment;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.PagerSlidingTabStrip;

import java.util.ArrayList;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;


//          下载，选择单据页面（包含：下载单据Fragment，选择单据Fragment）
public class PushDownPagerActivity extends BaseActivity {
    @BindView(R.id.tabstrip)
    PagerSlidingTabStrip tabstrip;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindColor(R.color.cpb_blue)
    int cpb_blue;
    public int tag;
    @BindView(R.id.tv_pdname)
    TextView tvPdname;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_push_down_pager);
        ButterKnife.bind(this);
        tag = getIntent().getExtras().getInt("123");
        Log.e("获取到--tag--", tag + "");
        setPDTitle(tag);//设置页面标题
    }

    @Override
    protected void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();
        fragments.add(new DownLoadPushFragment());
        fragments.add(new ChooseFragment());
        titles.add(getString(R.string.download_order));
        titles.add(getString(R.string.choose_order));
        StripAdapter stripAdapter = new StripAdapter(getSupportFragmentManager(), fragments, titles);
        Log.e("stripAdapter", stripAdapter + "");
        viewpager.setAdapter(stripAdapter);
        tabstrip.setShouldExpand(true);
        tabstrip.setViewPager(viewpager);
        tabstrip.setDividerColor(cpb_blue);
        tabstrip.setUnderlineHeight(3);
        tabstrip.setIndicatorHeight(6);
        tabstrip.setIndicatorColor(cpb_blue);

    }

    @Override
    protected void initListener() {

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

    //根据tag设置头部标题
    private void setPDTitle(int tag) {
        String string = "";
        switch (tag) {
            case 1:
                string = "采购订单下推采购入库";
                break;
            case 34:
                string = "采购订单下推委外入库";
                break;
            case 2:
                string = "销售订单下推销售出库单";
                break;
            case 31:
                string = "销售订单下推销售出库单(箱码)";
                break;
            case 21:
                string = "VMI销售订单下推销售出库单";
                break;
            case 3:
                string = "销售订单下推销售退货单";
                break;
            case 4:
                string = "销售出库单下推销售退货单";
                break;
            case 5:
                string = "发货通知单下推销售出库单";
                break;
            case 6:
                string = "退货通知单下推销售退货单";
                break;
            case 7:
                string = "调拨申请单下推分布式调入单";
                break;
            case 8:
                string = "调拨申请单下推分布式调出单";
                break;
            case 28:
                string = getString(R.string.p2_cgrk2production_get);
                break;
                /*----------------------------------二期单据--------------------------------------*/
            case 25:
                string = getString(R.string.p2_production_instore);
                break;
            case 27:
                string = getString(R.string.p2_production_instore2);
                break;
            case 26:
                string = getString(R.string.p2_cgrk2production_get);
                break;
            case 29:
                string = "水板采购";//生产领料单下推产品入库单(整单装箱)
                break;
            case 30:
                string = "码拍入窑";//生产领料单下推产品入库单(重新装箱)
                break;
            case 33:
                string = "外购烘干板入库";//外购烘干板入库
                break;

        }
        if (!"".equals(string)){
            tvPdname.setText(string);
        }else{
            tvPdname.setVisibility(View.GONE);
        }
    }

    public static void start(Context context,int id) {
        Intent starter = new Intent(context, PushDownPagerActivity.class);
        starter.putExtra("123", id);
        context.startActivity(starter);
    }
}
