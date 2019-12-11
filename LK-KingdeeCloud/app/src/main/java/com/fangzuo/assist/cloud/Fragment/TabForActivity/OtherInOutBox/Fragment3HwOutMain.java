package com.fangzuo.assist.cloud.Fragment.TabForActivity.OtherInOutBox;

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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseFragment;
import com.fangzuo.assist.cloud.Activity.PagerForActivity;
import com.fangzuo.assist.cloud.Activity.ProductSearchActivity;
import com.fangzuo.assist.cloud.Beans.CommonBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Dao.Client;
import com.fangzuo.assist.cloud.Dao.Org;
import com.fangzuo.assist.cloud.Dao.Suppliers;
import com.fangzuo.assist.cloud.Dao.T_main;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.CommonUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.LocDataUtil;
import com.fangzuo.assist.cloud.widget.SpinnerCommon;
import com.fangzuo.assist.cloud.widget.SpinnerDepartMent;
import com.fangzuo.assist.cloud.widget.SpinnerHuozhu;
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
public class Fragment3HwOutMain extends BaseFragment {


    @BindView(R.id.cb_isStorage)
    CheckBox cbIsStorage;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.ed_ff_order)
    EditText edFfOrder;
    @BindView(R.id.sp_org_send)
    SpinnerOrg spOrgSend;
    @BindView(R.id.sp_department_send)
    SpinnerDepartMent spDepartmentSend;
    //        @BindView(R.id.sp_saleman)
//        SpinnerSaleMan spSaleman;
    @BindView(R.id.sp_storeman)
    SpinnerStoreMan spStoreman;
    @BindView(R.id.ed_not)
    EditText edNot;
    @BindView(R.id.ed_client)
    EditText edClient;
    @BindView(R.id.ll_client)
    LinearLayout llClient;
    @BindView(R.id.search_client)
    RelativeLayout searchClient;
    @BindView(R.id.sp_org_huozhu)
    SpinnerHuozhu spOrgHuozhu;
    @BindView(R.id.ll_supplier_hz)
    LinearLayout llSupplierHz;
    @BindView(R.id.ed_supplier_hz)
    EditText edSupplierHz;
    @BindView(R.id.search_supplier_hz)
    RelativeLayout searchSupplierHz;
    @BindView(R.id.sp_hz_type)
    SpinnerCommon spHzType;
    //    @BindView(R.id.sp_department_sale)
//    SpinnerDepartMent spDepartmentSale;
    private FragmentActivity mContext;
    private PagerForActivity activityPager;
    Unbinder unbinder;
    private Suppliers supplierHz;
    private CommonBean hzType;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.Client:
                Client client = (Client) event.postEvent;
                Lg.e("获得供应商：", client);
                edClient.setText(client.FName);
                activityPager.setClient(client);
                Hawk.put(Config.Client + activityPager.getActivityMain(), client.FName);
                break;
            case EventBusInfoCode.Supplier_Hz:
                supplierHz = (Suppliers) event.postEvent;
                Lg.e("获得供应商Hz：", supplierHz);
                if (supplierHz.FItemID.equals("")) {
                    activityPager.setHuozhuOut(null);
                    edSupplierHz.setText("");
                } else {
                    activityPager.setHuozhuOut(new Org(supplierHz.FMASTERID, supplierHz.FNumber, supplierHz.FName, supplierHz.FNote));
                    edSupplierHz.setText(supplierHz.FName);
                    Hawk.put(Config.SupplierHz + activityPager.getActivityMain(), supplierHz.FName);//保存业务单号
                }
                break;
            case EventBusInfoCode.Lock_Main:
                String lock = (String) event.postEvent;
                if (Config.Lock.equals(lock)) {
                    activityPager.setHasLock(true);
                    spOrgSend.setEnable(false);
                    spOrgHuozhu.setEnable(false);
                    spDepartmentSend.setEnable(false);
                    spStoreman.setEnable(false);
                    searchClient.setClickable(false);
                    edNot.setFocusable(false);
                    edClient.setFocusable(false);
                    searchSupplierHz.setClickable(false);
                    spHzType.setEnable(false);

                    Client client1 = LocDataUtil.getClient(Hawk.get(Config.Client + activityPager.getActivityMain(), edClient.getText().toString()));
                    edClient.setText(client1.FName);
                    activityPager.setClient(client1);
//                    edNot.setText(Hawk.get(Config.Note + activityPager.getActivityMain(), edNot.getText().toString()));
//                    edFfOrder.setText(Hawk.get(Config.OrderNo + activityPager.getActivityMain(), edFfOrder.getText().toString()));
//                    Hawk.put(Config.OrderNo + activityPager.getActivityMain(), edFfOrder.getText().toString());//保存客户数据
//                    Hawk.put(Config.Note + activityPager.getActivityMain(), edNot.getText().toString());//保存客户数据
                } else {
                    activityPager.setHasLock(false);
                    spOrgSend.setEnable(true);
                    spOrgHuozhu.setEnable(true);
                    spDepartmentSend.setEnable(true);
                    spStoreman.setEnable(true);
                    searchClient.setClickable(true);
                    edClient.setFocusable(true);
                    edClient.setFocusableInTouchMode(true);
                    edNot.setFocusable(true);
                    edNot.setFocusableInTouchMode(true);
                    spHzType.setEnable(true);
                    searchSupplierHz.setClickable(true);
//                    activityPager.setClient(null);
//                    edClient.setText("");
//                    edNot.setText("");
//                    Hawk.put(Config.Client + activityPager.getActivityMain(), "");//清空保存的客户数据
//                    Hawk.put(Config.OrderNo + activityPager.getActivityMain(), "");//清空保存的客户数据
//                    Hawk.put(Config.Note + activityPager.getActivityMain(), "");//清空保存的客户数据
                }
                break;
        }
    }

    public Fragment3HwOutMain() {
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
        View view = inflater.inflate(R.layout.fragment_oout_main, container, false);
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
        spHzType.setData(Info.Type_Hz_type);
//        spHzType.setEnable(false);
        spHzType.setAutoSelection(getString(R.string.spHzType_oout3)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spHzType_oout3)+activityPager.getActivityMain(), ""));
        activityPager.setDate(tvDate.getText().toString());
        //第一个参数用于保存上一个值，第二个为自动跳转到该默认值
        spOrgSend.setAutoSelection(getString(R.string.spOrgSend_oout3)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spOrgSend_oout3)+activityPager.getActivityMain(), ""));//仓库，仓管员，部门都以组织id来过滤
//        spOrgHuozhu.setAutoSelection(getString(R.string.spOrgHuozhu_oout3)+activityPager.getActivityMain(), activityPager.getOrgOut(), Hawk.get(getString(R.string.spOrgHuozhu_oout3)+activityPager.getActivityMain(), ""));
//        spDepartmentSend.setAuto(getString(R.string.spDepartmentSend_oout3)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spDepartmentSend_oout3)+activityPager.getActivityMain(), ""), activityPager.getOrgOut(), activityPager.getActivity());
//        spDepartmentSale.setAuto(getString(R.string.spDepartmentSale_oout3)+activityPager.getActivityMain(), "", activityPager.getOrgOut(), activityPager.getActivityMain());
//        spStoreman.setAuto(getString(R.string.spStoreman_oout3)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spStoreman_oout3)+activityPager.getActivityMain(), ""), activityPager.getOrgOut());
//        spSaleman.setAuto(getString(R.string.spSaleman_oout3)+activityPager.getActivityMain(), "", activityPager.getOrgOut());
        cbIsStorage.setChecked(Hawk.get(Info.Storage + activityPager.getActivityMain(), false));
        Client client1 = LocDataUtil.getClient(Hawk.get(Config.Client + activityPager.getActivityMain(), ""));
        edClient.setText(client1.FName);
        activityPager.setClient(client1);

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
                activityPager.setFOrderNo(edFfOrder == null ? "" : edFfOrder.getText().toString());
                activityPager.setManStore(spStoreman.getDataNumber());
//                activityPager.setManSale(spSaleman.getDataNumber());
                activityPager.setDepartMent(spDepartmentSend.getDataNumber());
//                activityPager.setDepartMentBuy(spDepartmentSale.getDataNumber());
//                Hawk.put(Config.OrderNo+activityPager.getActivityMain(),edClient.getText().toString());//保存业务单号
//                Hawk.put(Config.Note + activityPager.getActivityMain(), edNot.getText().toString());//保存业务单号
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
        spHzType.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                hzType = (CommonBean) spHzType.getAdapter().getItem(i);
                Lg.e("货主类型：", hzType);
                activityPager.setDBType(hzType.FNumber);
                if ("BD_OwnerOrg".equals(hzType.FNumber)) {//业务组织
                    llSupplierHz.setVisibility(View.GONE);
                    spOrgHuozhu.setVisibility(View.VISIBLE);
                    spOrgHuozhu.setAutoSelection(getString(R.string.spOrgHuozhu_oout3)+activityPager.getActivityMain(), activityPager.getOrgOut(), Hawk.get(getString(R.string.spOrgHuozhu_oout3)+activityPager.getActivityMain(), ""));
                } else {//供应商
                    activityPager.setHuozhuOut(null);
                    spOrgHuozhu.setVisibility(View.GONE);
                    llSupplierHz.setVisibility(View.VISIBLE);
                    LocDataUtil.getSuppliers(Hawk.get(Config.SupplierHz + activityPager.getActivityMain(), ""),activityPager.getOrgOut(1),EventBusInfoCode.Supplier_Hz);
                }
                Hawk.put(getString(R.string.spHzType_oout3)+activityPager.getActivityMain(), hzType.FName);
            }
        });
        spOrgSend.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                activityPager.setOrgOut((Org) spOrgSend.getAdapter().getItem(i));
                Hawk.put(getString(R.string.spOrgSend_oout3)+activityPager.getActivityMain(), activityPager.getOrgOut().FName);
                if ("BD_OwnerOrg".equals(hzType.FNumber)) {//业务组织
                    spOrgHuozhu.setAutoSelection(getString(R.string.spOrgHuozhu_oout3)+activityPager.getActivityMain(), activityPager.getOrgOut(), Hawk.get(getString(R.string.spOrgHuozhu_oout3)+activityPager.getActivityMain(), ""));
                }else{
                    LocDataUtil.getSuppliers(Hawk.get(Config.SupplierHz + activityPager.getActivityMain(), ""),activityPager.getOrgOut(1),EventBusInfoCode.Supplier_Hz);
                }
//                spOrgHuozhu.setAutoSelection(getString(R.string.spOrgHuozhu_oout3)+activityPager.getActivityMain(), activityPager.getOrgOut(), "");
                spDepartmentSend.setAuto(getString(R.string.spDepartmentSend_oout3)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spDepartmentSend_oout3)+activityPager.getActivityMain(), ""), activityPager.getOrgOut(), activityPager.getActivity());
                spStoreman.setAuto(getString(R.string.spStoreman_oout3)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spStoreman_oout3)+activityPager.getActivityMain(), ""), activityPager.getOrgOut());
//        spDepartmentSale.setAuto(getString(R.string.spDepartmentSale_oout3)+activityPager.getActivityMain(), "", activityPager.getOrgOut(), activityPager.getActivityMain());
//        spSaleman.setAuto(getString(R.string.spSaleman_oout3)+activityPager.getActivityMain(), "", activityPager.getOrgOut());
                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.UpdataView, ""));

            }
        });
        spOrgHuozhu.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                activityPager.setHuozhuOut((Org) spOrgHuozhu.getAdapter().getItem(i));
                Hawk.put(getString(R.string.spOrgHuozhu_oout3)+activityPager.getActivityMain(), activityPager.getHuozhuOut().FName);
            }
        });
        cbIsStorage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                activityPager.setStorage(b);
            }
        });

    }

    @OnClick({R.id.tv_date, R.id.search_client, R.id.search_supplier_hz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_date:
                datePicker(tvDate);
//                activityPager.setNote(edNot.getText().toString());
                break;
            case R.id.search_client:
                Bundle b = new Bundle();
                b.putString("search", edClient.getText().toString());
                b.putInt("where", Info.SEARCHCLIENT);
                b.putString("org", activityPager.getOrgOut().FOrgID);
                startNewActivity(activityPager, ProductSearchActivity.class, R.anim.activity_open, 0, false, b);
                break;
            case R.id.search_supplier_hz:
                Bundle b2 = new Bundle();
                b2.putString("search", edSupplierHz.getText().toString());
                b2.putInt("where", Info.SearchSupplier);
                b2.putString("org", activityPager.getOrgOut().FOrgID);
                startNewActivity(activityPager, ProductSearchActivity.class, R.anim.activity_open, 0, false, b2);
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
