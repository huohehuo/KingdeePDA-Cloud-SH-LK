package com.fangzuo.assist.cloud.Fragment.P2;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseFragment;
import com.fangzuo.assist.cloud.Activity.PagerForActivity;
import com.fangzuo.assist.cloud.Activity.PrintBeforeDataActivity;
import com.fangzuo.assist.cloud.Activity.PrintBoxCodeActivity;
import com.fangzuo.assist.cloud.Activity.PrintHistoryActivity;
import com.fangzuo.assist.cloud.Activity.PushDownPagerActivity;
import com.fangzuo.assist.cloud.Activity.ScanProductActivity;
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


public class OneFragment extends BaseFragment {
    @BindView(R.id.gv)
    GridView gv;
    Unbinder unbinder;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private FragmentActivity mContext;

    public OneFragment() {

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
        tvTitle.setText("锯材车间");
    }

    @Override
    protected void OnReceive(String barCode) {

    }

    GridViewAdapter ada;
    @Override
    protected void initData() {
//        String getPermit=share.getString(ShareInfo.USER_PERMIT);
//        String[] arylist = getPermit.split("\\-"); // 这样才能得到正确的结果
        ada = new GridViewAdapter(mContext, GetSettingList.getOrderList(1));
        gv.setAdapter(ada);
        ada.notifyDataSetChanged();
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume
            Lg.e("OneFragment",isVisibleToUser);
            Hawk.put(Config.Fragment_Tag,0);
        } else {
            Lg.e("OneFragment",isVisibleToUser);
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
//                switch (tv.activity) {
//                    /*-------------二期单据---------------------------------------------*/
//                    case Config.P2ProductionInStoreActivity://简单生产入库
////                        PushDownSearchActivity.start(getActivity(),1);
//                        PushDownPagerActivity.start(getActivity(), 25);
//                        break;
//                    case Config.P2ProductionInStore2Activity://简单生产入库
////                        PushDownSearchActivity.start(getActivity(),1);
//                        PushDownPagerActivity.start(getActivity(), 27);
//                        break;
//                    case Config.P2PdCgrk2ProductGetActivity://采购入库单下推简单生产领料
////                        PushDownSearchActivity.start(getActivity(),1);
//                        PushDownPagerActivity.start(getActivity(), 26);
//                        break;
//                    case Config.ProductGet4P2Activity://生产领料
//                        PagerForActivity.start(mContext, Config.ProductGet4P2Activity);
//                        break;
//                    case Config.ProductInStore4P2Activity://水版产品入库
//                        PagerForActivity.start(mContext, Config.ProductInStore4P2Activity);
//                        break;
//                    case Config.WortInStore4P2Activity://刨光干板入库
//                        PagerForActivity.start(mContext, Config.WortInStore4P2Activity);
//                        break;
//                    case Config.PrintBoxCodeActivity://箱码补打
//                        startNewActivity(PrintBoxCodeActivity.class, null);
//                        break;
//                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
