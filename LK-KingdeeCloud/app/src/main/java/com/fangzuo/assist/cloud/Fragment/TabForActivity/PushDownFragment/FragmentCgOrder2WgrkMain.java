package com.fangzuo.assist.cloud.Fragment.TabForActivity.PushDownFragment;

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
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Dao.Org;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.CommonUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.LocDataUtil;
import com.fangzuo.assist.cloud.widget.NumberClick;
import com.fangzuo.assist.cloud.widget.SpinnerBuyer;
import com.fangzuo.assist.cloud.widget.SpinnerDepartMent;
import com.fangzuo.assist.cloud.widget.SpinnerHuozhu;
import com.fangzuo.assist.cloud.widget.SpinnerOrg;
import com.fangzuo.assist.cloud.widget.SpinnerStoreMan;
import com.orhanobut.hawk.Hawk;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


//选择单据信息Fragment（所属：PushDownPagerActivity);
public class FragmentCgOrder2WgrkMain extends BaseFragment {


    @BindView(R.id.cb_isStorage)
    CheckBox cbIsStorage;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.ed_ff_order)
    EditText edFfOrder;
//    @BindView(R.id.sp_org_send)
//    SpinnerOrg spOrgSend;
    @BindView(R.id.sp_org_buy)
    SpinnerOrg spOrgBuy;
    @BindView(R.id.sp_department_send)
    SpinnerDepartMent spDepartmentSend;
//        @BindView(R.id.sp_saleman)
//        SpinnerSaleMan spSaleman;
    @BindView(R.id.sp_buyer)
    SpinnerBuyer spBuyer;
    @BindView(R.id.ed_not)
    EditText edNot;
    @BindView(R.id.cb_num)
    NumberClick cbNum;
//    @BindView(R.id.search_client)
//    RelativeLayout searchClient;
    //    @BindView(R.id.sp_department_sale)
//    SpinnerDepartMent spDepartmentSale;
    private FragmentActivity mContext;
    private PagerForActivity activityPager;
    Unbinder unbinder;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.Lock_Main:
                String lock = (String) event.postEvent;
                if (Config.Lock.equals(lock)) {
                    activityPager.setHasLock(true);
//                    spOrgSend.setEnable(false);
                    spDepartmentSend.setEnable(false);
                    spOrgBuy.setEnable(false);
//                    searchClient.setClickable(false);
                    edNot.setFocusable(false);
                    edNot.setText(Hawk.get(Config.Note + activityPager.getActivityMain(), edNot.getText().toString()));
//                    Hawk.put(Config.OrderNo + activityPager.getActivityMain(), client1.FName);//保存客户数据
                    Hawk.put(Config.Note + activityPager.getActivityMain(), edNot.getText().toString());//保存客户数据
                } else {
                    activityPager.setHasLock(false);
//                    spOrgSend.setEnable(true);
                    spDepartmentSend.setEnable(true);
                    spOrgBuy.setEnable(true);
//                    searchClient.setClickable(true);
                    edNot.setFocusable(true);
                    edNot.setFocusableInTouchMode(true);
                    activityPager.setClient(null);
                    edNot.setText("");
                    Hawk.put(Config.OrderNo + activityPager.getActivityMain(), "");//清空保存的客户数据
                    Hawk.put(Config.Note + activityPager.getActivityMain(), "");//清空保存的客户数据
                }
                break;
            case EventBusInfoCode.UpdataView://由表头的的数据决定是否更新明细数据
                //判断是否有保存的业务单号，不存在的话，解锁表头
                if (!LocDataUtil.hasTDetail(activityPager.getActivity(),activityPager.getFBillNo())) {
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock + "NO"));
                } else {
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock));
                }
//                if (null != activityPager) {
////                    spUnit.setAuto("", SpinnerUnit.Id);
//                    spDepartmentSend.setAuto("", "", activityPager.getOrgOut(), activityPager.getActivity());
//                    spStoreman.setAuto(getString(R.string.spStoreman_bmsg2sb), Hawk.get(getString(R.string.spStoreman_bmsg2sb), ""), activityPager.getOrgOut());
//                    spOrgHuozhu.setAutoSelection(getString(R.string.spHuozhu_bmsg2sb),  activityPager.getOrgOut(), Hawk.get(getString(R.string.spHuozhu_bmsg2sb), ""));//仓库，仓管员，部门都以组织id来过滤
//
//                }
                break;
        }
    }

    public FragmentCgOrder2WgrkMain() {
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
        View view = inflater.inflate(R.layout.fragment_cgorder2wgrk_main, container, false);
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

        setfocus(tvDate);
    }

    @Override
    public void onResume() {
        super.onResume();
        tvDate.setText(CommonUtil.getTime(true));
        cbNum.setSaveKey(activityPager.getActivityMain()+"printnum");
        activityPager.setDate(tvDate.getText().toString());
        //第一个参数用于保存上一个值，第二个为自动跳转到该默认值
        spOrgBuy.setAutoSelection(getString(R.string.spOrgBuy_cg2wg)+activityPager.getActivityMain(), "");
//        spDepartmentSend.setAuto(getString(R.string.spDepartmentBuy_cg2wg)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spDepartmentBuy_cg2wg)+activityPager.getActivityMain(), ""), activityPager.getOrgOut(), activityPager.getActivity());
//        spBuyer.setAuto(getString(R.string.spBuyer_cg2wg)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spBuyer_cg2wg)+activityPager.getActivityMain(), ""), activityPager.getOrgOut());
//        cbIsStorage.setChecked(Hawk.get(Info.Storage + activityPager.getActivityMain(), false));
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
                activityPager.setDate(tvDate == null ? "" : tvDate.getText().toString());
                activityPager.setNote(edNot == null ? "" : edNot.getText().toString());
                activityPager.setPrintNum(cbNum.getNum());
//                activityPager.setFOrderNo(edFfOrder == null ? "" : edFfOrder.getText().toString());
//                activityPager.setManBuyer(spBuyer.getDataNumber());
//                activityPager.setManSale(spSaleman.getDataNumber());
//                activityPager.setDepartMent(spDepartmentSend.getDataNumber());
//                activityPager.setHuozhuOut(spOrgHuozhu.getDataObject());
//                activityPager.setDepartMentBuy(spDepartmentSale.getDataNumber());
//                Hawk.put(Config.OrderNo+activityPager.getActivityMain(),edClient.getText().toString());//保存业务单号
                Hawk.put(Config.Note + activityPager.getActivityMain(), edNot.getText().toString());//保存业务单号
            }
        }
    }


    @Override
    protected void initListener() {
//        spWhichStorage.setOnItemSelectedListener(new ItemListener() {
//            @Override
//            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                Storage storage = (Storage) spWhichStorage.getAdapter().getItem(i);
//                activityPager.setStorage(storage);
//                spWhichStorage.setTitleText(storage.FName);
//                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.UpdataWaveHouse, storage));
//                Lg.e("选中仓库：", storage);
//            }
//        });
//
        spOrgBuy.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                activityPager.setOrgOut((Org) spOrgBuy.getAdapter().getItem(i));
                Lg.e("组织",activityPager.getOrgOut());
                Hawk.put(getString(R.string.spOrgBuy_cg2wg)+activityPager.getActivityMain(), activityPager.getOrgOut().FName);
//                spDepartmentSend.setAuto(getString(R.string.spDepartmentBuy_cg2wg)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spDepartmentBuy_cg2wg)+activityPager.getActivityMain(), ""), activityPager.getOrgOut(), activityPager.getActivity());
//                spBuyer.setAuto(getString(R.string.spBuyer_cg2wg)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spBuyer_cg2wg)+activityPager.getActivityMain(), ""), activityPager.getOrgOut());
//                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.UpdataView, ""));

            }
        });
        cbIsStorage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                activityPager.setStorage(b);
            }
        });

    }

    @OnClick({R.id.tv_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_date:
                datePicker(tvDate);
//                activityPager.setNote(edNot.getText().toString());
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
