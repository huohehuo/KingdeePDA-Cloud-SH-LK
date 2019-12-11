package com.fangzuo.assist.cloud.Fragment.TabForActivity.TbManagerBox;

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
import com.fangzuo.assist.cloud.Dao.T_main;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.CommonUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.LocDataUtil;
import com.fangzuo.assist.cloud.widget.SpinnerDepartMent;
import com.fangzuo.assist.cloud.widget.SpinnerOrg;
import com.fangzuo.assist.cloud.widget.SpinnerStoreMan;
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
public class FragmentTbGetMain extends BaseFragment {


    @BindView(R.id.cb_isStorage)
    CheckBox cbIsStorage;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.sp_department)
    SpinnerDepartMent spDepartment;
    @BindView(R.id.sp_org_send)
    SpinnerOrg spOrgSend;
    @BindView(R.id.sp_org_create)
    SpinnerOrg spOrgCreate;
    @BindView(R.id.sp_org_huozhu)
    SpinnerOrg spOrgHuozhu;
//    @BindView(R.id.sp_getman)
//    SpinnerEmployee spGetman;
    @BindView(R.id.sp_storeman)
    SpinnerStoreMan spStoreman;
    @BindView(R.id.ed_not)
    EditText edNot;
    @BindView(R.id.ed_ff_order)
    EditText edFfOrder;
    private FragmentActivity mContext;
    private PagerForActivity activityPager;
    Unbinder unbinder;
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.Lock_Main:
                String lock = (String) event.postEvent;
                if (Config.Lock.equals(lock)){
                    activityPager.setHasLock(true);
                    spDepartment.setEnable(false);
                    spOrgCreate.setEnable(false);
                    spOrgSend.setEnable(false);
                    spOrgHuozhu.setEnable(false);
                    spStoreman.setEnable(false);
                    edFfOrder.setFocusable(false);
                    edNot.setFocusable(false);

//                    edFfOrder.setText(Hawk.get(Config.OrderNo+activityPager.getActivityMain(),""));
//                    edNot.setText(Hawk.get(Config.Note+activityPager.getActivityMain(),""));
//                    Hawk.put(Config.OrderNo+activityPager.getActivityMain(),edFfOrder.getText().toString());//保存业务单号
//                    Hawk.put(Config.Note+activityPager.getActivityMain(),edNot.getText().toString());//保存业务单号
                }else{
                    activityPager.setHasLock(false);
                    spDepartment.setEnable(true);
                    spOrgCreate.setEnable(true);
                    spOrgSend.setEnable(true);
                    spOrgHuozhu.setEnable(true);
                    spStoreman.setEnable(true);
                    edFfOrder.setFocusable(true);
                    edFfOrder.setFocusableInTouchMode(true);
                    edNot.setFocusable(true);
                    edNot.setFocusableInTouchMode(true);

//                    edFfOrder.setText("");
//                    edNot.setText("");
//                    Hawk.put(Config.OrderNo+activityPager.getActivityMain(),"");//清空存储的业务单号
//                    Hawk.put(Config.Note+activityPager.getActivityMain(),"");//清空存储的业务单号
                }
                break;

        }
    }
    public FragmentTbGetMain() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        activityPager = ((PagerForActivity) activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_prgetmain, container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = getActivity();
        EventBusUtil.register(this);
        return view;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void OnReceive(String barCode) {

    }

    @Override
    protected void initData() {

        //判断是否有保存的业务单号，不存在的话，解锁表头
        if (!LocDataUtil.hasTDetail(activityPager.getActivity())){
            EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock+"NO"));
        }else{
            EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock));
        }
        setfocus(tvDate);
    }

    @Override
    public void onResume() {
        super.onResume();
        tvDate.setText(CommonUtil.getTime(true));
        //第一个参数用于保存上一个值，第二个为自动跳转到该默认值
        spOrgCreate.setAutoSelection(getString(R.string.spOrgCreate_tbget)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spOrgCreate_tbget)+activityPager.getActivityMain(),""));
        spOrgSend.setAutoSelection(getString(R.string.spOrgSend_tbget)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spOrgSend_tbget)+activityPager.getActivityMain(),""),activityPager.getActivity(),0);
        spOrgHuozhu.setAutoSelection(getString(R.string.spOrgHuozhu_tbget)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spOrgHuozhu_tbget)+activityPager.getActivityMain(),""));
//        spGetman.setAuto(getString(R.string.spBuyer_tbget)+activityPager.getActivityMain(), "",activityPager.getOrgOut());
        spDepartment.setAuto(getString(R.string.spDepartmentCreate_tbget)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spDepartmentCreate_tbget)+activityPager.getActivityMain(),""),activityPager.getOrgIn(),activityPager.getActivity());
        spStoreman.setAuto(getString(R.string.spStoreman_tbget)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spStoreman_tbget)+activityPager.getActivityMain(),""),activityPager.getOrgOut());

//        binding.spOrgIn.setEnable(false);
//        binding.spOrgCreate.setEnable(false);
        cbIsStorage.setChecked(Hawk.get(Info.Storage + activityPager.getActivityMain(), false));

        List<T_main> list =activityPager.getT_mainDao().queryBuilder().where(
                T_mainDao.Properties.FOrderId.eq(CommonUtil.createOrderCode(activityPager.getActivity())),
                T_mainDao.Properties.Activity.eq(activityPager.getActivity()),
                T_mainDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
        ).build().list();
        if (list.size()>0){
            edFfOrder.setText(list.get(0).F_FFF_Text);
            edNot.setText(list.get(0).FNot);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume
        } else {
            //相当于Fragment的onPause
            if (null!=activityPager){
                activityPager.setDate(tvDate==null?"":tvDate.getText().toString());
                activityPager.setNote(edNot==null?"":edNot.getText().toString());
                activityPager.setManStore(spStoreman.getDataNumber());
//                activityPager.setManGet(spGetman.getDataNumber());
                activityPager.setDepartMent(spDepartment.getDataNumber());
                activityPager.setFOrderNo(edFfOrder == null ? "" : edFfOrder.getText().toString());
//                Hawk.put(Config.OrderNo+activityPager.getActivityMain(),edFfOrder.getText().toString());//保存业务单号
//                Hawk.put(Config.Note+activityPager.getActivityMain(),edNot.getText().toString());//保存业务单号
            }
        }
    }


    @Override
    protected void initListener() {
        spOrgSend.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                activityPager.setOrgOut((Org) spOrgSend.getAdapter().getItem(i));
                Hawk.put(getString(R.string.spOrgSend_tbget)+activityPager.getActivityMain(),activityPager.getOrgOut().FName);
//        spGetman.setAuto(getString(R.string.spBuyer_tbget)+activityPager.getActivityMain(), "",activityPager.getOrgOut());
                spStoreman.setAuto(getString(R.string.spStoreman_tbget)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spStoreman_tbget)+activityPager.getActivityMain(),""),activityPager.getOrgOut());
                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.UpdataView,""));
            }
        });
        spOrgCreate.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                activityPager.setOrgIn((Org) spOrgCreate.getAdapter().getItem(i));
                spDepartment.setAuto(getString(R.string.spDepartmentCreate_tbget)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spDepartmentCreate_tbget)+activityPager.getActivityMain(),""),activityPager.getOrgIn(),activityPager.getActivity());
                Hawk.put(getString(R.string.spOrgCreate_tbget)+activityPager.getActivityMain(),activityPager.getOrgIn().FName);
            }
        });
        spOrgHuozhu.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                activityPager.setHuozhuOut((Org) spOrgHuozhu.getAdapter().getItem(i));
                Hawk.put(getString(R.string.spOrgHuozhu_tbget)+activityPager.getActivityMain(),activityPager.getHuozhuOut().FName);
            }
        });
        cbIsStorage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                activityPager.setStorage(b);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Hawk.put(Info.Storage+activityPager,cbIsStorage.isChecked());
        unbinder.unbind();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            EventBusUtil.unregister(this);
        } catch (Exception e) { }
    }

    @OnClick({R.id.tv_date, R.id.ed_not})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_date:
                datePicker(tvDate);
                break;
            case R.id.ed_not:
                break;
        }
    }
}
