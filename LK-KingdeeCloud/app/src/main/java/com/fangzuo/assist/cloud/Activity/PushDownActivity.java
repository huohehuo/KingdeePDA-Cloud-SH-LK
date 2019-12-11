package com.fangzuo.assist.cloud.Activity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Adapter.SettingListAdapter;
import com.fangzuo.assist.cloud.Beans.SettingList;
import com.fangzuo.assist.cloud.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.fangzuo.assist.cloud.Utils.GetSettingList.GetPushDownList;


public class PushDownActivity extends BaseActivity {

    @BindView(R.id.btn_back)
    RelativeLayout btnBack;
    @BindView(R.id.lv_pushdown_menu)
    ListView lvPushdownMenu;
    private Bundle b;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_push_down);
        ButterKnife.bind(this);
    }
    SettingListAdapter ada;
    @Override
    protected void initData() {
        ada = new SettingListAdapter(mContext, GetPushDownList());
        lvPushdownMenu.setAdapter(ada);
        ada.notifyDataSetChanged();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void initListener() {
        b = new Bundle();
        lvPushdownMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SettingList tv= (SettingList) ada.getItem(i);
                Log.e("listitem",tv.tv);
                switch (tv.tv) {
                    case "采购订单下推外购入库单":
                        b.clear();
                        b.putInt("123", 1);
                        break;
                    case "销售订单下推销售出库单":
                        b.clear();
                        b.putInt("123", 2);
                        break;
                    case "销售订单下推销售退货单":
                        b.clear();
                        b.putInt("123", 3);
                        break;
                    case "销售出库单下推销售退货单":
                        b.clear();
                        b.putInt("123", 4);
                        break;
                    case "发货通知单下推销售出库单":
                        b.clear();
                        b.putInt("123", 5);
                        break;
                    case "退货通知单下推销售退货单":
                        b.clear();
                        b.putInt("123", 6);
                        break;
                    case "调拨申请单下推分布式调入单":
                        b.clear();
                        b.putInt("123", 7);
                        break;
                    case "调拨申请单下推分布式调出单":
                        b.clear();
                        b.putInt("123", 8);
                        break;

                }
                startNewActivity(PushDownPagerActivity.class, R.anim.activity_fade_in, R.anim.activity_fade_out, false, b);

            }
        });
    }

    @Override
    protected void OnReceive(String code) {

    }


}
