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
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Dao.Org;
import com.fangzuo.assist.cloud.Dao.Storage;
import com.fangzuo.assist.cloud.Dao.T_main;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.CommonUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.DataModel;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.LocDataUtil;
import com.fangzuo.assist.cloud.widget.SpinnerCommon;
import com.fangzuo.assist.cloud.widget.SpinnerHuozhu;
import com.fangzuo.assist.cloud.widget.SpinnerOrg;
import com.fangzuo.assist.cloud.widget.SpinnerStorage;
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
public class FragmentDB4P1BoxMain extends BaseFragment {


    @BindView(R.id.cb_isStorage)
    CheckBox cbIsStorage;
    @BindView(R.id.tv_date)
    TextView tvDate;
    //    @BindView(R.id.sp_db_type)
//    SpinnerCommon spDbType;
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
    @BindView(R.id.sp_which_storage_out)
    SpinnerStorage spWhichStorageOut;
    @BindView(R.id.sp_which_storage_in)
    SpinnerStorage spWhichStorageIn;
    @BindView(R.id.ed_wl_company)
    EditText edWlCompany;
    @BindView(R.id.ed_carbox_no)
    EditText edCarboxNo;
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
//                    spDbDirection.setEnable(false);
                    spOrgIn.setEnable(false);
                    spOrgOut.setEnable(false);
                    spOrgHuozhuIn.setEnable(false);
                    spOrgHuozhuOut.setEnable(false);
                    spStoreman.setEnable(false);
                    edNot.setFocusable(false);
                    edCarboxNo.setFocusable(false);
                    edWlCompany.setFocusable(false);
//                    edNot.setText(Hawk.get(Config.Note + activityPager.getActivityMain(), edNot.getText().toString()));
//                    Hawk.put(Config.Note+activityPager.getActivityMain(),edNot.getText().toString());//保存业务单号
                } else {
                    activityPager.setHasLock(false);
//                    spDbDirection.setEnable(true);
                    spOrgOut.setEnable(true);
                    spOrgHuozhuOut.setEnable(true);
                    spStoreman.setEnable(true);
                    spOrgIn.setEnable(true);
                    spOrgHuozhuIn.setEnable(true);
                    edNot.setText("");
                    edNot.setFocusable(true);
                    edNot.setFocusableInTouchMode(true);
                    edCarboxNo.setFocusable(true);
                    edCarboxNo.setFocusableInTouchMode(true);
                    edWlCompany.setFocusable(true);
                    edWlCompany.setFocusableInTouchMode(true);
//                    Hawk.put(Config.Note+activityPager.getActivityMain(),"");
                }
                break;

        }
    }

    public FragmentDB4P1BoxMain() {
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
        View view = inflater.inflate(R.layout.fragment_db4p1boxmain, container, false);
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
        if (!LocDataUtil.hasTDetail(activityPager.getActivity())) {
            EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock + "NO"));
        } else {
            EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Lock_Main, Config.Lock));
        }

        setfocus(tvDate);
    }

    @Override
    public void onResume() {
        super.onResume();
        tvDate.setText(CommonUtil.getTime(true));
        activityPager.setDBType("InnerOrgTransfer");//组织内调拨InnerOrgTransfer/OverOrgTransfer
        activityPager.setDBDirection("GENERAL");//调拨类型
        spDbDirection.setData(Info.Type_DB_direction);
//        spDbDirection.setEnable(false);
        //第一个参数用于保存上一个值，第二个为自动跳转到该默认值
        spOrgOut.setAutoSelection(getString(R.string.spOrgOut_db) + activityPager.getActivityMain(), Hawk.get(getString(R.string.spOrgOut_db) + activityPager.getActivityMain(), ""));
        spOrgHuozhuOut.setAutoSelection(getString(R.string.spOrgHuozhuOut_db) + activityPager.getActivityMain(), activityPager.getOrgOut(), Hawk.get(getString(R.string.spOrgHuozhuOut_db) + activityPager.getActivityMain(), ""));

        spOrgIn.setAutoSelection(getString(R.string.spOrgIn_db) + activityPager.getActivityMain(), Hawk.get(getString(R.string.spOrgIn_db) + activityPager.getActivityMain(), ""));
        spOrgHuozhuIn.setAutoSelection(getString(R.string.spOrgHuozhuIn_db) + activityPager.getActivityMain(), activityPager.getOrgIn(), Hawk.get(getString(R.string.spOrgHuozhuIn_db) + activityPager.getActivityMain(), ""));

        spStoreman.setAuto(getString(R.string.spStoreman_db) + activityPager.getActivityMain(), Hawk.get(getString(R.string.spStoreman_db) + activityPager.getActivityMain(), ""), activityPager.getOrgOut());
//        spDbDirection.setAutoSelection(getString(R.string.spDbDirection_db)+activityPager.getActivityMain(),Hawk.get(getString(R.string.spDbDirection_db)+activityPager.getActivityMain(),""));
//        spDbDirection.setAutoSelection(getString(R.string.spDbDirection_db)+activityPager.getActivityMain(),"普通");
//        binding.spOrgIn.setEnable(false);
//        binding.spOrgCreate.setEnable(false);
        cbIsStorage.setChecked(Hawk.get(Info.Storage + activityPager.getActivityMain(), false));

        List<T_main> list = activityPager.getT_mainDao().queryBuilder().where(
                T_mainDao.Properties.FOrderId.eq(CommonUtil.createOrderCode(activityPager.getActivity())),
                T_mainDao.Properties.Activity.eq(activityPager.getActivity()),
                T_mainDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
        ).build().list();
        if (list.size() > 0) {
//            edFfOrder.setText(list.get(0).F_FFF_Text);
            edNot.setText(list.get(0).FNot);
            edWlCompany.setText(list.get(0).FWlCompany);
            edCarboxNo.setText(list.get(0).FCarBoxNo);
        }
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
                activityPager.setWlCompany(edWlCompany == null ? "" : edWlCompany.getText().toString());
                activityPager.setCarboxNo(edCarboxNo == null ? "" : edCarboxNo.getText().toString());
//                Hawk.put(Config.Note+activityPager.getActivityMain(),edNot.getText().toString());//保存备注
            }
        }
    }


    @Override
    protected void initListener() {

//        spDbDirection.setOnItemSelectedListener(new ItemListener() {
//            @Override
//            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                CommonBean dc = (CommonBean) spDbDirection.getAdapter().getItem(i);
////                Lg.e("调拨方向：", dc);
//                activityPager.setDBDirection(dc.FNumber);
//                Hawk.put(getString(R.string.spDbDirection_db)+activityPager.getActivityMain(),dc.FName);
//            }
//        });

        spOrgOut.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                activityPager.setOrgOut((Org) spOrgOut.getAdapter().getItem(i));
                Hawk.put(getString(R.string.spOrgOut_db) + activityPager.getActivityMain(), activityPager.getOrgOut().FName);
                Lg.e("a调出组织：", (Org) spOrgOut.getAdapter().getItem(i));
                spOrgHuozhuOut.setAutoSelection(getString(R.string.spOrgHuozhuOut_db) + activityPager.getActivityMain(), activityPager.getOrgOut(), "");
                spStoreman.setAuto(getString(R.string.spStoreman_db) + activityPager.getActivityMain(), "", activityPager.getOrgOut());
//                spOrgIn.setAutoSelection("", activityPager.getOrgOut().FName);
                spWhichStorageOut.setAuto("storageout"+activityPager.getActivityMain(), Hawk.get("storageout"+activityPager.getActivityMain(), ""), activityPager.getOrgOut());

                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.UpdataView, ""));
            }
        });
        spOrgIn.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                activityPager.setOrgIn((Org) spOrgIn.getAdapter().getItem(i));
                Hawk.put(getString(R.string.spOrgIn_db) + activityPager.getActivityMain(), activityPager.getOrgIn().FName);
                Lg.e("a调入组织：", (Org) spOrgIn.getAdapter().getItem(i));
                spOrgHuozhuIn.setAutoSelection(getString(R.string.spOrgHuozhuIn_db) + activityPager.getActivityMain(), activityPager.getOrgIn(), Hawk.get(getString(R.string.spOrgHuozhuIn_db) + activityPager.getActivityMain(), ""));
                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.UpdataViewForDBInStorage, ""));
                spWhichStorageIn.setAuto("storagein"+activityPager.getActivityMain(), Hawk.get("storagein"+activityPager.getActivityMain(), ""), activityPager.getOrgIn());

            }
        });
        spOrgHuozhuOut.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                activityPager.setHuozhuOut((Org) spOrgHuozhuOut.getAdapter().getItem(i));
                Hawk.put(getString(R.string.spOrgHuozhuOut_db) + activityPager.getActivityMain(), activityPager.getHuozhuOut().FName);
                Lg.e("组织内货主出：" + activityPager.getHuozhuOut().FName);
//                spOrgHuozhuIn.setAutoSelection("", activityPager.getOrgIn(), activityPager.getHuozhuOut().FName);
            }
        });
        spOrgHuozhuIn.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                activityPager.setHuozhuIn((Org) spOrgHuozhuIn.getAdapter().getItem(i));
                Hawk.put(getString(R.string.spOrgHuozhuIn_db) + activityPager.getActivityMain(), activityPager.getHuozhuIn().FName);
            }
        });
        spWhichStorageOut.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                Storage storageOut = (Storage) spWhichStorageOut.getAdapter().getItem(i);
                activityPager.setStorageOut(storageOut);
                spWhichStorageOut.setTitleText(storageOut.FName);
                Lg.e("选中调出仓库：", storageOut);
                Hawk.put("storageout"+activityPager.getActivityMain(),storageOut.FName);
//                waveHouseOut = null;
//                spWavehouseOut.setAuto(mContext, storageOut, "");
//                DataModel.getStoreNum(product, storageOut, edPihao.getText().toString().trim(), mContext, tvStorenum,activityPager.getOrgOut(),activityPager.getHuozhuOut());

            }
        });

        spWhichStorageIn.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                Storage storageIn = (Storage) spWhichStorageIn.getAdapter().getItem(i);
                activityPager.setStorageIn(storageIn);
                spWhichStorageIn.setTitleText(storageIn.FName);
                Hawk.put("storagein"+activityPager.getActivityMain(),storageIn.FName);
                Lg.e("选中调入仓库：", storageIn);
//                waveHouseIn = null;
//                spWavehouseIn.setAuto(mContext, storageIn, "");

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
        } catch (Exception e) {
        }
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
