package com.fangzuo.assist.cloud.Fragment.P2;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseFragment;
import com.fangzuo.assist.cloud.Adapter.GridViewAdapter;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.SettingList;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.GetSettingList;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.orhanobut.hawk.Hawk;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class FourFragment extends BaseFragment {
    @BindView(R.id.gv)
    GridView gv;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    Unbinder unbinder;
    private FragmentActivity mContext;

    public FourFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_one, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void initView() {
        mContext = getActivity();
        tvTitle.setText("成品车间");
    }

    @Override
    protected void OnReceive(String barCode) {

    }

    GridViewAdapter ada;

    @Override
    protected void initData() {
//        String getPermit=share.getString(ShareInfo.USER_PERMIT);
//        String[] arylist = getPermit.split("\\-"); // 这样才能得到正确的结果
        ada = new GridViewAdapter(mContext, GetSettingList.getOrderList(4));
        gv.setAdapter(ada);
        ada.notifyDataSetChanged();
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume
            Lg.e("FourFragment",isVisibleToUser);
            Hawk.put(Config.Fragment_Tag,3);
        } else {
            Lg.e("FourFragment",isVisibleToUser);
            //相当于Fragment的onPause
        }
    }
    @Override
    protected void initListener() {
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SettingList tv = (SettingList) ada.getItem(i);
//                Log.e("listitem", tv.tv);
                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Click_Order,tv.activity));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
