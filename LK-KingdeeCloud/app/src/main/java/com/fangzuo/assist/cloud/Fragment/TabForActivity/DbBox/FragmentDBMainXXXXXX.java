/*
package com.fangzuo.assist.cloud.Fragment.TabForActivity.DbBox;

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
import com.fangzuo.assist.cloud.Beans.CommonBean;
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
import com.fangzuo.assist.cloud.widget.SpinnerCommon;
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
public class FragmentDBMainXXXXXX extends BaseFragment {


    @BindView(R.id.cb_isStorage)
    CheckBox cbIsStorage;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.sp_db_type)
    SpinnerCommon spDbType;
    @BindView(R.id.sp_db_direction)
    SpinnerCommon spDbDirection;
    @BindView(R.id.sp_org_out)
    SpinnerOrg spOrgOut;
    @BindView(R.id.sp_org_huozhu_out)
    SpinnerHuozhu spOrgHuozhuOut;
    @BindView(R.id.sp_org_in)
    SpinnerOrg spOrgIn;
    @BindView(R.id.sp_org_huozhu_in)
    SpinnerHuozhu spOrgHuozhuIn;
    @BindView(R.id.sp_storeman)
    SpinnerStoreMan spStoreman;
    @BindView(R.id.ed_not)
    EditText edNot;
    private FragmentActivity mContext;
    private PagerForActivity activityPager;
    Unbinder unbinder;
    private CommonBean commonBean;
    private boolean isTheSame = false;//用于判断是否为组织内调拨true
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.Lock_Main:
                String lock = (String) event.postEvent;
                if (Config.Lock.equals(lock)){
                    activityPager.setHasLock(true);
                    spDbType.setEnable(false);
                    spDbType.setFocusable(false);
                    spDbDirection.setEnable(false);
                    spOrgIn.setEnable(false);
                    spOrgOut.setEnable(false);
                    spOrgHuozhuIn.setEnable(false);
                    spOrgHuozhuOut.setEnable(false);
                    spStoreman.setEnable(false);
                    edNot.setFocusable(false);
                    edNot.setText(Hawk.get(Config.Note + activityPager.getActivity(), edNot.getText().toString()));
                    Hawk.put(Config.Note+activityPager.getActivity(),edNot.getText().toString());//保存业务单号
                }else{
                    forKzz=false;
                    activityPager.setHasLock(false);
                    spDbType.setEnable(true);
                    spDbDirection.setEnable(true);
                    spOrgOut.setEnable(true);
                    spOrgHuozhuOut.setEnable(true);
                    spStoreman.setEnable(true);
                    if (commonBean.FNumber.equals("InnerOrgTransfer")) {//组织内调拨，不允许修改
                        spOrgIn.setEnable(false);
                        spOrgHuozhuIn.setEnable(false);
                    } else {
                        spOrgIn.setEnable(true);
                        spOrgHuozhuIn.setEnable(true);
                    }
                    edNot.setText("");
                    edNot.setFocusable(true);
                    edNot.setFocusableInTouchMode(true);
                    Hawk.put(Config.Note+activityPager.getActivity(),"");
                }
                break;

        }
    }
    public FragmentDBMainXXXXXX() {
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
        View view = inflater.inflate(R.layout.fragment_dbmain, container, false);
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

    private boolean forKzz=false;//用于判断调拨类型是否锁定调入组织货主
    @Override
    protected void initData() {
        tvDate.setText(CommonUtil.getTime(true));
        spDbType.setData(Info.Type_DB_type);
        spDbDirection.setData(Info.Type_DB_direction);
        spDbType.setEnable(false);
        spDbDirection.setEnable(false);
        //第一个参数用于保存上一个值，第二个为自动跳转到该默认值
        spOrgOut.setAutoSelection(getString(R.string.spOrgOut_db), Hawk.get(getString(R.string.spOrgOut_db), ""));
        spOrgHuozhuOut.setAutoSelection(getString(R.string.spOrgHuozhuOut_db),activityPager.getOrgOut(), Hawk.get(getString(R.string.spOrgHuozhuOut_db), ""));

        spOrgIn.setAutoSelection(getString(R.string.spOrgIn_db), Hawk.get(getString(R.string.spOrgIn_db), ""));
        spOrgHuozhuIn.setAutoSelection(getString(R.string.spOrgHuozhuIn_db),activityPager.getOrgIn(), Hawk.get(getString(R.string.spOrgHuozhuIn_db), ""));

        spStoreman.setAuto(getString(R.string.spStoreman_db), Hawk.get(getString(R.string.spStoreman_db),""), activityPager.getOrgOut());
        spDbType.setAutoSelection(getString(R.string.spDbType_db),Hawk.get(getString(R.string.spDbType_db),""));
        spDbDirection.setAutoSelection(getString(R.string.spDbDirection_db),Hawk.get(getString(R.string.spDbDirection_db),""));
        spDbType.setAutoSelection(getString(R.string.spDbType_db),"组织内调拨");
        spDbDirection.setAutoSelection(getString(R.string.spDbDirection_db),"普通");
//        binding.spOrgIn.setEnable(false);
//        binding.spOrgCreate.setEnable(false);
        cbIsStorage.setChecked(Hawk.get(Info.Storage + activityPager.getActivity(), false));
        //判断是否有保存的业务单号，不存在的话，解锁表头
        if (!LocDataUtil.hasTDetail(activityPager.getActivity())){
            forKzz=false;
            EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock+"NO"));
        }else{
            forKzz=true;
            spDbType.setAutoSelection(getString(R.string.spDbType_db),Hawk.get(getString(R.string.spDbType_db),""));
            EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock));
        }

        setfocus(tvDate);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume
        } else {
            //相当于Fragment的onPause
            if (null != activityPager) {
                activityPager.setDate(tvDate == null ? "" : tvDate.getText().toString());
                activityPager.setNote(edNot == null ? "" : edNot.getText().toString());
                activityPager.setManStore(spStoreman.getDataNumber());
                Hawk.put(Config.Note+activityPager.getActivity(),edNot.getText().toString());//保存备注
            }
        }
    }


    @Override
    protected void initListener() {
        spDbType.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                commonBean = (CommonBean) spDbType.getAdapter().getItem(i);
//                Lg.e("调拨类型：", commonBean);
                Hawk.put(getString(R.string.spDbType_db),commonBean.FName);
                activityPager.setDBType(commonBean.FNumber);
                if (commonBean.FNumber.equals("InnerOrgTransfer")) {//组织内调拨，不允许修改
                    isTheSame = true;
                    spOrgIn.setEnable(false);
                    spOrgHuozhuIn.setEnable(false);
                    if (null!=activityPager.getOrgOut()){
                        spOrgIn.setAutoSelection("", activityPager.getOrgOut().FName);
                        spOrgHuozhuIn.setAutoSelection("",activityPager.getOrgIn(), activityPager.getHuozhuOut().FName);
                    }
                } else {
                    if (forKzz){
                        spOrgIn.setEnable(false);
                        spOrgHuozhuIn.setEnable(false);
                        Lg.e("可获得焦点");
                    }else{
                        spOrgIn.setEnable(true);
                        spOrgHuozhuIn.setEnable(true);
                        Lg.e("不可获得焦点");
                    }
                    isTheSame = false;
                }
            }
        });
        spDbDirection.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                CommonBean dc = (CommonBean) spDbDirection.getAdapter().getItem(i);
//                Lg.e("调拨方向：", dc);
                activityPager.setDBDirection(dc.FNumber);
                Hawk.put(getString(R.string.spDbDirection_db),dc.FName);
            }
        });

        spOrgOut.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                activityPager.setOrgOut((Org) spOrgOut.getAdapter().getItem(i));
                Hawk.put(getString(R.string.spOrgOut_db),activityPager.getOrgOut().FName);
                Lg.e("a调出组织：",(Org) spOrgOut.getAdapter().getItem(i));
                spOrgHuozhuOut.setAutoSelection(getString(R.string.spOrgHuozhuOut_db), activityPager.getOrgOut(),"");
                spStoreman.setAuto(getString(R.string.spStoreman_db), "", activityPager.getOrgOut());
                if (isTheSame) {
                    spOrgIn.setAutoSelection("", activityPager.getOrgOut().FName);
                }else{
                    spOrgIn.setAutoSelection(getString(R.string.spOrgIn_db), "");
                }
                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.UpdataView, ""));
            }
        });
        spOrgIn.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                activityPager.setOrgIn((Org) spOrgIn.getAdapter().getItem(i));
                Hawk.put(getString(R.string.spOrgIn_db),activityPager.getOrgIn().FName);
                Lg.e("a调入组织：",(Org) spOrgIn.getAdapter().getItem(i));
                if (isTheSame){//当为组织内调拨时，调出入货主必须相同
                    spOrgHuozhuIn.setAutoSelection("", activityPager.getOrgIn(),activityPager.getOrgOut().FName);
                }else{
//                    Lg.e("调入组织，货主为："+Hawk.get(getString(R.string.spOrgHuozhuIn_db),""));
                    spOrgHuozhuIn.setAutoSelection(getString(R.string.spOrgHuozhuIn_db), activityPager.getOrgIn(),"");
                }
                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.UpdataViewForDBInStorage, ""));
            }
        });
        spOrgHuozhuOut.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                activityPager.setHuozhuOut((Org) spOrgHuozhuOut.getAdapter().getItem(i));
                Hawk.put(getString(R.string.spOrgHuozhuOut_db),activityPager.getHuozhuOut().FName);
                if (isTheSame) {
                    Lg.e("组织内货主出："+activityPager.getHuozhuOut().FName);
                    spOrgHuozhuIn.setAutoSelection("", activityPager.getOrgIn(),activityPager.getHuozhuOut().FName);
                }else{
                    Lg.e("跨组织货主出："+activityPager.getHuozhuOut().FName);
                    spOrgHuozhuIn.setAutoSelection(getString(R.string.spOrgHuozhuIn_db), activityPager.getOrgIn(),"");
                }
            }
        });
        spOrgHuozhuIn.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                activityPager.setHuozhuIn((Org) spOrgHuozhuIn.getAdapter().getItem(i));
                Hawk.put(getString(R.string.spOrgHuozhuIn_db),activityPager.getHuozhuIn().FName);
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
        Hawk.put(Info.Storage + activityPager, cbIsStorage.isChecked());
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
*/
