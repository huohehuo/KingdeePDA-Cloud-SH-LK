package com.fangzuo.assist.cloud.Fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.fangzuo.assist.cloud.ABase.BaseFragment;
import com.fangzuo.assist.cloud.Activity.PagerForActivity;
import com.fangzuo.assist.cloud.Activity.ScanProductActivity;
import com.fangzuo.assist.cloud.Adapter.GridViewAdapter;
import com.fangzuo.assist.cloud.Beans.SettingList;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.GetSettingList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//销售fragment
public class SaleFragment extends BaseFragment {
    @BindView(R.id.gv)
    GridView gv;
    Unbinder unbinder;
    private FragmentActivity mContext;

    public SaleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sale, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }


    @Override
    public void initView() {
        mContext = getActivity();
    }

    @Override
    protected void OnReceive(String barCode) {

    }
    GridViewAdapter ada;
    @Override
    protected void initData() {
        ada = new GridViewAdapter(mContext, GetSettingList.getSaleList());
        gv.setAdapter(ada);
        ada.notifyDataSetChanged();
    }

    @Override
    protected void initListener() {
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SettingList tv = (SettingList) ada.getItem(i);
                switch (tv.activity){
                    case 0://简单生产领料
                        PagerForActivity.start(mContext, Config.ProductGetActivity);
//                        startNewActivity(ProductInStoreActivity.class, null);
                        break;
                    case 1://简单生产入库
                        PagerForActivity.start(mContext, Config.ProductInStoreActivity);
//                        startNewActivity(ProductGetActivity.class, null);
                        break;
                    case 2:
                        ScanProductActivity.start(mContext);
                        break;
//                    case 0://挑板入库
//                        PagerForActivity.start(mContext, Config.TbInActivity);
////                        startNewActivity(SaleOrderActivity.class,null);
//                        break;
//                    case 1://到柜入库
//                        PagerForActivity.start(mContext, Config.DgInActivity);
////                        startNewActivity(SoldOutActivity.class,null);
//                        break;
//                    case 2://简单生产入库
//                        PagerForActivity.start(mContext, Config.SimpleInActivity);
////                        startNewActivity(PushDownActivity.class,null);
//                        break;
//                    case 3://生产领料
////                        startNewActivity(ProduceAndGetActivity.class,null);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
