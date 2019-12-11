package com.fangzuo.assist.cloud.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Beans.BackData;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.SearchBackData;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.ToSubscribe;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.JsonDealUtils;
import com.fangzuo.assist.cloud.Utils.Lg;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PushDownSearchActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.ed_no)
    EditText edNo;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_client)
    TextView tvClient;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.ed_org)
    EditText edOrg;
    @BindView(R.id.ed_ids)
    EditText edIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_down_search);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initListener() {

    }

    private void searchNo(String no) {
        List<String> orders = new ArrayList<>();
        orders.add(no);
        String json = Info.getJson(Config.PdCgOrder2WgrkActivity, JsonDealUtils.JSon_View(orders, edOrg.getText().toString(), edIds.getText().toString()));
//        String json="{\"CreateOrgId\":0,\"Number\":\"\",\"Id\":\"\"}";
        App.CloudService().doIOActionSearch(Config.C_View, json, new ToSubscribe<SearchBackData>() {
            @Override
            public void onNext(SearchBackData backData) {
                super.onNext(backData);
                Lg.e("返回数据",backData);
//                if (backData.getResult().getResponseStatus().getIsSuccess()) {
//                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Upload_OK, backData));
//                } else {
//                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Upload_OK, backData));
//                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Upload_Error, e.toString()));
            }
        });
    }

    @Override
    protected void OnReceive(String code) {

    }

    public static void start(Context context, int id) {
        Intent starter = new Intent(context, PushDownSearchActivity.class);
//        starter.putExtra("123", id);
        context.startActivity(starter);
    }

    @OnClick({R.id.btn_back, R.id.tv_right, R.id.iv_find, R.id.btn_push_down})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_right:
                break;
            case R.id.iv_find:
                searchNo(edNo.getText().toString());
                break;
            case R.id.btn_push_down:
                break;
        }
    }
}
