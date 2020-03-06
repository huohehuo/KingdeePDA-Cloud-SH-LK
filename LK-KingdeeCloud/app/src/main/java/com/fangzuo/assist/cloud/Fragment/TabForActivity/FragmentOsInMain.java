package com.fangzuo.assist.cloud.Fragment.TabForActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseFragment;
import com.fangzuo.assist.cloud.Activity.PagerForActivity;
import com.fangzuo.assist.cloud.Activity.ProductSearchActivity;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Dao.Org;
import com.fangzuo.assist.cloud.Dao.Storage;
import com.fangzuo.assist.cloud.Dao.Suppliers;
import com.fangzuo.assist.cloud.Dao.T_main;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.CommonUtil;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.widget.SpinnerBuyer;
import com.fangzuo.assist.cloud.widget.SpinnerDepartMent;
import com.fangzuo.assist.cloud.widget.SpinnerOrg;
import com.fangzuo.assist.cloud.widget.SpinnerSaleMan;
import com.fangzuo.assist.cloud.widget.SpinnerStorage;
import com.fangzuo.assist.cloud.widget.SpinnerStoreMan;
import com.fangzuo.assist.cloud.widget.TextAutoTime;
import com.fangzuo.greendao.gen.T_mainDao;
import com.orhanobut.hawk.Hawk;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


//选择单据信息Fragment（所属：PushDownPagerActivity);
public class FragmentOsInMain extends BaseFragment {


    @BindView(R.id.cb_isStorage)
    CheckBox cbIsStorage;
//    @BindView(R.id.ed_ff_order)
//    EditText edFfOrder;
    @BindView(R.id.sp_department_buy)
    SpinnerDepartMent spDepartmentBuy;
    @BindView(R.id.sp_department_get)
    SpinnerDepartMent spDepartmentGet;
    @BindView(R.id.sp_org_buy)
    SpinnerOrg spOrgBuy;
    @BindView(R.id.sp_org_get)
    SpinnerOrg spOrgGet;
    @BindView(R.id.sp_buyer)
    SpinnerBuyer spBuyer;
    @BindView(R.id.sp_storeman)
    SpinnerStoreMan spStoreman;
    @BindView(R.id.sp_saleman)
    SpinnerSaleMan spSaleman;
//    @BindView(R.id.ed_not)
//    EditText edNot;
    @BindView(R.id.tv_date)
    TextAutoTime tvDate;
    @BindView(R.id.ed_supplier)
    EditText edSupplier;
    private FragmentActivity mContext;
    private PagerForActivity activityPager;
    Unbinder unbinder;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
//            case EventBusInfoCode.UpdataStorage:
//                String id = (String) event.postEvent;
//                Lg.e("更改仓库数据" + id);
//                spWhichStorage.setAuto("",id, activityPager.getOrgOut());
//                break;
            case EventBusInfoCode.Supplier:
                Suppliers supplier = (Suppliers) event.postEvent;
                Lg.e("获得供应商：" + supplier.toString());
                edSupplier.setText(supplier.FName);
                activityPager.setSuppliers(supplier);
                break;
        }
    }

    public FragmentOsInMain() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {//与activity绑定
        super.onAttach(activity);
        activityPager = ((PagerForActivity) activity);
        Lg.e("Fg_M:" + "onAttach");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_osimain, container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = getActivity();
        EventBusUtil.register(this);
        Lg.e("Fg_M:" + "onCreateView");
        return view;
    }

    @Override
    protected void initView() {
        Lg.e("Fg_M:" + "initView");
    }

    @Override
    protected void OnReceive(String barCode) {

    }

    @Override
    protected void initData() {
        Lg.e("Fg_M:" + "initData");
        tvDate.setText(CommonUtil.getTime(true));
        activityPager.setDate(tvDate.getText());
        //第一个参数用于保存上一个值，第二个为自动跳转到该默认值
        spOrgBuy.setAutoSelection(getString(R.string.spOrgbuy_pis)+activityPager.getActivityMain(), Hawk.get(Info.user_org, ""));//仓库，仓管员，部门都以组织id来过滤
        spOrgGet.setAutoSelection(getString(R.string.spOrgget_pis)+activityPager.getActivityMain(), Hawk.get(Info.user_org, ""));
        spDepartmentGet.setAuto(getString(R.string.spDepartmentGet_pis)+activityPager.getActivityMain(), "", activityPager.getOrgOut(), activityPager.getActivity());
        spDepartmentBuy.setAuto(getString(R.string.spDepartmentbuy_pis)+activityPager.getActivityMain(), "", activityPager.getOrgOut(), activityPager.getActivity());
        spStoreman.setAuto(getString(R.string.spStoreMan_pis)+activityPager.getActivityMain(), "", activityPager.getOrgOut());
        spBuyer.setAuto(getString(R.string.spBuyer_pis)+activityPager.getActivityMain(), "", activityPager.getOrgOut());
//        binding.spOrgIn.setEnable(false);
        cbIsStorage.setChecked(Hawk.get(Info.Storage + activityPager.getActivityMain(), false));
        setfocus(tvDate);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<T_main> list =activityPager.getT_mainDao().queryBuilder().where(
                T_mainDao.Properties.FOrderId.eq(CommonUtil.createOrderCode(activityPager.getActivity())),
                T_mainDao.Properties.Activity.eq(activityPager.getActivity()),
                T_mainDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
        ).build().list();
        if (list.size()>0){
//            edFfOrder.setText(list.get(0).F_FFF_Text);
//            edNot.setText(list.get(0).FNot);
        }
    }

    //在oncreateView之前使用 不要使用控件
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume
            Lg.e("fragment显示");
        } else {
            Lg.e("fragment隐藏");
            //相当于Fragment的onPause
            if (null != activityPager) {
                activityPager.setDate(tvDate == null ? "" : tvDate.getText());
//                activityPager.setNote(edNot == null ? "" : edNot.getText().toString());
//                activityPager.setFOrderNo(edFfOrder == null ? "" : edFfOrder.getText().toString());
                activityPager.setManStore(spStoreman.getDataNumber());
                activityPager.setManGet(spBuyer.getDataNumber());
                activityPager.setDepartMent(spDepartmentGet.getDataNumber());
                activityPager.setDepartMentBuy(spDepartmentBuy.getDataNumber());
            }
        }
    }


    @Override
    protected void initListener() {

        spOrgBuy.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                activityPager.setOrgOut((Org) spOrgBuy.getAdapter().getItem(i));
                spDepartmentGet.setAuto(getString(R.string.spDepartmentGet_pis)+activityPager.getActivityMain(), "", activityPager.getOrgOut(), activityPager.getActivity());
                spDepartmentBuy.setAuto(getString(R.string.spDepartmentbuy_pis)+activityPager.getActivityMain(), "", activityPager.getOrgOut(), activityPager.getActivity());
                spStoreman.setAuto(getString(R.string.spStoreMan_pis)+activityPager.getActivityMain(), "", activityPager.getOrgOut());
                spBuyer.setAuto(getString(R.string.spBuyer_pis)+activityPager.getActivityMain(), "", activityPager.getOrgOut());
                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.UpdataView, ""));

            }
        });
        spOrgGet.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                activityPager.setOrgIn((Org) spOrgGet.getAdapter().getItem(i));
            }
        });
        cbIsStorage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                activityPager.setStorage(b);
            }
        });

    }

    @OnClick({ R.id.search_supplier})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_supplier:
                Bundle b = new Bundle();
                b.putString("search", edSupplier.getText().toString());
                b.putInt("where", Info.SEARCHSUPPLIER);
                b.putInt("activity", activityPager.getActivity());
                b.putString("org", activityPager.getOrgOut().FOrgID);
                startNewActivity(activityPager,ProductSearchActivity.class, R.anim.activity_open, 0,false, b);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            EventBusUtil.unregister(this);
        } catch (Exception e) {
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Hawk.put(Info.Storage + activityPager, cbIsStorage.isChecked());
        unbinder.unbind();
    }


}
