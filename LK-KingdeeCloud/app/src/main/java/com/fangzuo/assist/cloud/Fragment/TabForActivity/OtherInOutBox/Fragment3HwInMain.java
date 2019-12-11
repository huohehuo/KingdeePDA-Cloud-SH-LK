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
import com.fangzuo.assist.cloud.Dao.Org;
import com.fangzuo.assist.cloud.Dao.Storage;
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
import com.fangzuo.assist.cloud.widget.NumberClick;
import com.fangzuo.assist.cloud.widget.SpinnerCommon;
import com.fangzuo.assist.cloud.widget.SpinnerDepartMent;
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
public class Fragment3HwInMain extends BaseFragment {

    @BindView(R.id.cb_isStorage)
    CheckBox cbIsStorage;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.sp_org_in)
    SpinnerOrg spOrgIn;
    @BindView(R.id.sp_org_create)
    SpinnerHuozhu spOrgCreate;
    //    @BindView(R.id.sp_org_huozhu)
//    SpinnerOrg spOrgHuozhu;
    @BindView(R.id.sp_storeman)
    SpinnerStoreMan spStoreman;
    @BindView(R.id.ed_not)
    EditText edNot;
    @BindView(R.id.ed_ff_order)
    EditText edFfOrder;
    @BindView(R.id.sp_department_get)
    SpinnerDepartMent spDepartmentGet;
    @BindView(R.id.sp_which_storage)
    SpinnerStorage spWhichStorage;
    @BindView(R.id.ed_supplier)
    EditText edSupplier;
    @BindView(R.id.sp_hz_type)
    SpinnerCommon spHzType;
    @BindView(R.id.ll_supplier_hz)
    LinearLayout llSupplierHz;
    @BindView(R.id.ed_supplier_hz)
    EditText edSupplierHz;
    @BindView(R.id.search_supplier)
    RelativeLayout searchSupplier;
    @BindView(R.id.search_supplier_hz)
    RelativeLayout searchSupplierHz;
    @BindView(R.id.cb_num)
    NumberClick cbNum;
    private FragmentActivity mContext;
    private PagerForActivity activityPager;
    Unbinder unbinder;
    private Suppliers supplier;
    private Suppliers supplierHz;
    private CommonBean hzType;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.Supplier:
                supplier = (Suppliers) event.postEvent;
                Lg.e("获得供应商：", supplier);
                if (supplier.FItemID.equals("")){
                    activityPager.setSuppliers(null);
                    edSupplier.setText("");
                }else{
                    activityPager.setSuppliers(supplier);
                    edSupplier.setText(supplier.FName);
                    Hawk.put(Config.Supplier + activityPager.getActivityMain(), supplier.FName);//保存业务单号
                }
                break;
            case EventBusInfoCode.Supplier_Hz:
                supplierHz = (Suppliers) event.postEvent;
                Lg.e("获得供应商Hz：", supplierHz);
                if (supplierHz.FItemID.equals("")) {
                    activityPager.setOrgIn(null);
                    edSupplierHz.setText("");
                } else {
                    activityPager.setOrgIn(new Org(supplierHz.FMASTERID, supplierHz.FNumber, supplierHz.FName, supplierHz.FNote));
                    edSupplierHz.setText(supplierHz.FName);
                    Hawk.put(Config.SupplierHz + activityPager.getActivityMain(), supplierHz.FName);//保存业务单号
                }
                break;
            case EventBusInfoCode.UpdataStorage:
                String id = (String) event.postEvent;
                Lg.e("更改仓库数据" + id);
                spWhichStorage.setAuto(getString(R.string.spWhichStorage_oin3)+activityPager.getActivityMain(),id, activityPager.getOrgOut());
                break;
            case EventBusInfoCode.Lock_Main://是否锁住表头
                String lock = (String) event.postEvent;
                if (Config.Lock.equals(lock)) {          //锁住表头数据
                    activityPager.setHasLock(true);
                    spDepartmentGet.setEnable(false);
                    spOrgCreate.setEnable(false);
                    spHzType.setEnable(false);
                    spOrgIn.setEnable(false);
                    searchSupplier.setClickable(false);
                    searchSupplierHz.setClickable(false);
//                    spOrgHuozhu.setEnable(false);
                    spStoreman.setEnable(false);
                    edFfOrder.setFocusable(false);
//                    edFfOrder.setClickable(false);
                    edNot.setFocusable(false);

//                    edFfOrder.setText(Hawk.get(Config.OrderNo + activityPager.getActivityMain(), edFfOrder.getText().toString()));
//                    edNot.setText(Hawk.get(Config.Note + activityPager.getActivityMain(), edNot.getText().toString()));
//                    LocDataUtil.getSuppliers(Hawk.get(Config.Supplier + activityPager.getActivityMain(), ""),activityPager.getOrgOut(1),EventBusInfoCode.Supplier);
//                    if (hzType.FNumber.equals("BD_Supplier")){
//                        LocDataUtil.getSuppliers(Hawk.get(Config.SupplierHz + activityPager.getActivityMain(), ""),activityPager.getOrgOut(1),EventBusInfoCode.Supplier_Hz);
//                    }
//                    Hawk.put(Config.OrderNo + activityPager.getActivityMain(), edFfOrder.getText().toString());//保存业务单号
//                    Hawk.put(Config.Note + activityPager.getActivityMain(), edNot.getText().toString());//保存业务单号
                } else {
                    activityPager.setHasLock(false);
                    spDepartmentGet.setEnable(true);
                    spOrgCreate.setEnable(true);
                    spOrgIn.setEnable(true);
                    spHzType.setEnable(true);
                    searchSupplier.setClickable(true);
                    searchSupplierHz.setClickable(true);
//                    spOrgHuozhu.setEnable(true);
                    spStoreman.setEnable(true);
                    edFfOrder.setFocusable(true);
                    edFfOrder.setFocusableInTouchMode(true);
                    edNot.setFocusable(true);
                    edNot.setFocusableInTouchMode(true);
//                    edFfOrder.setText("");
//                    edNot.setText("");
//                    Hawk.put(Config.OrderNo + activityPager.getActivityMain(), "");//清空存储的业务单号
//                    Hawk.put(Config.Note + activityPager.getActivityMain(), "");//清空存储的业务单号
                }
                break;

        }
    }

    public Fragment3HwInMain() {
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
        View view = inflater.inflate(R.layout.fragment_oin_main, container, false);
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

        //判断是否有保存的业务单号，决定是否锁住表头
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
        cbNum.setSaveKey(activityPager.getActivityMain()+"printnum");
        spHzType.setData(Info.Type_Hz_type);
//        spHzType.setEnable(false);
        spHzType.setAutoSelection(getString(R.string.spHzType_oin3)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spHzType_oin3)+activityPager.getActivityMain(), ""));
        activityPager.setDate(tvDate.getText().toString());
        //第一个参数用于保存上一个值，第二个为自动跳转到该默认值
        spOrgIn.setAutoSelection(getString(R.string.spOrgIn_oin3)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spOrgIn_oin3)+activityPager.getActivityMain(), ""));//仓库，仓管员，部门都以组织id来过滤
//        spOrgCreate.setAutoSelection(getString(R.string.spOrgCreate_oin3)+activityPager.getActivityMain(), activityPager.getOrgOut(), Hawk.get(getString(R.string.spOrgCreate_oin3)+activityPager.getActivityMain(), ""));
//        spOrgHuozhu.setAutoSelection(getString(R.string.spOrgHuozhu_oin3)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spOrgHuozhu_oin3)+activityPager.getActivityMain(), ""));
        spStoreman.setAuto(getString(R.string.spStoreman_oin3)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spStoreman_oin3)+activityPager.getActivityMain(), ""), activityPager.getOrgOut());
        spDepartmentGet.setAuto(getString(R.string.spDepartmentGet_oin3)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spDepartmentGet_oin3)+activityPager.getActivityMain(), ""), activityPager.getOrgOut(), activityPager.getActivity());
        spWhichStorage.setAuto(getString(R.string.spWhichStorage_oin3)+activityPager.getActivityMain(),Hawk.get(getString(R.string.spWhichStorage_oin3)+activityPager.getActivityMain(), ""), activityPager.getOrgOut());
//        binding.spOrgIn.setEnable(false);
//        spOrgCreate.setEnable(false);
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
                activityPager.setDepartMent(spDepartmentGet.getDataNumber());
                activityPager.setPrintNum(cbNum.getNum());
//                Hawk.put(Config.OrderNo + activityPager.getActivityMain(), edFfOrder.getText().toString());//保存业务单号
//                Hawk.put(Config.Note + activityPager.getActivityMain(), edNot.getText().toString());//保存业务单号
            }
        }
    }


    @Override
    protected void initListener() {
        spHzType.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                hzType = (CommonBean) spHzType.getAdapter().getItem(i);
                Lg.e("货主类型：", hzType);
                activityPager.setDBType(hzType.FNumber);
                if ("BD_OwnerOrg".equals(hzType.FNumber)) {//业务组织
                    llSupplierHz.setVisibility(View.GONE);
                    spOrgCreate.setVisibility(View.VISIBLE);
                    spOrgCreate.setAutoSelection(getString(R.string.spOrgCreate_oin3)+activityPager.getActivityMain(), activityPager.getOrgOut(), Hawk.get(getString(R.string.spOrgCreate_oin3)+activityPager.getActivityMain(), ""));
                } else {//供应商
                    activityPager.setOrgIn(null);
                    spOrgCreate.setVisibility(View.GONE);
                    llSupplierHz.setVisibility(View.VISIBLE);
                    LocDataUtil.getSuppliers(Hawk.get(Config.SupplierHz + activityPager.getActivityMain(), ""),activityPager.getOrgOut(1),EventBusInfoCode.Supplier_Hz);
                }
                Hawk.put(getString(R.string.spHzType_oin3)+activityPager.getActivityMain(), hzType.FName);
            }
        });
        spWhichStorage.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                Storage storage = (Storage) spWhichStorage.getAdapter().getItem(i);
                activityPager.setStorage(storage);
                spWhichStorage.setTitleText(storage.FName);
                Hawk.put(getString(R.string.spWhichStorage_oin3)+activityPager.getActivityMain(), storage.FName);
                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.UpdataWaveHouse, storage));
                Lg.e("选中仓库：", storage);
            }
        });
        spOrgIn.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                activityPager.setOrgOut((Org) spOrgIn.getAdapter().getItem(i));
                Lg.e("库存组织",activityPager.getOrgOut());
                Hawk.put(getString(R.string.spOrgIn_oin3)+activityPager.getActivityMain(), activityPager.getOrgOut().FName);
                spStoreman.setAuto(getString(R.string.spStoreMan_pis), Hawk.get(getString(R.string.spStoreman_oin3)+activityPager.getActivityMain(), ""), activityPager.getOrgOut());
                LocDataUtil.getSuppliers(Hawk.get(Config.Supplier + activityPager.getActivityMain(), ""),activityPager.getOrgOut(1),EventBusInfoCode.Supplier);
                if ("BD_OwnerOrg".equals(hzType.FNumber)) {//业务组织
                    spOrgCreate.setAutoSelection(getString(R.string.spOrgCreate_oin3)+activityPager.getActivityMain(), activityPager.getOrgOut(), Hawk.get(getString(R.string.spOrgCreate_oin3)+activityPager.getActivityMain(), ""));
                }else{
                    LocDataUtil.getSuppliers(Hawk.get(Config.SupplierHz + activityPager.getActivityMain(), ""),activityPager.getOrgOut(1),EventBusInfoCode.Supplier_Hz);
                }
                spWhichStorage.setAuto(getString(R.string.spWhichStorage_oin3)+activityPager.getActivityMain(),Hawk.get(getString(R.string.spWhichStorage_oin3)+activityPager.getActivityMain(), ""), activityPager.getOrgOut());
                spDepartmentGet.setAuto(getString(R.string.spDepartmentGet_oin3)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spDepartmentGet_oin3)+activityPager.getActivityMain(), ""), activityPager.getOrgOut(), activityPager.getActivity());
                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.UpdataView, ""));

            }
        });
        spOrgCreate.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                activityPager.setOrgIn((Org) spOrgCreate.getAdapter().getItem(i));
                Lg.e("货主：", activityPager.getOrgIn());
                Hawk.put(getString(R.string.spOrgCreate_oin3)+activityPager.getActivityMain(), activityPager.getOrgIn().FName);
            }
        });
//        spOrgHuozhu.setOnItemSelectedListener(new ItemListener() {
//            @Override
//            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                activityPager.setHuozhuOut((Org) spOrgHuozhu.getAdapter().getItem(i));
//                Hawk.put(getString(R.string.spOrgHuozhu_oin3)+activityPager.getActivityMain(),activityPager.getHuozhuOut().FName);
//            }
//        });
        cbIsStorage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                activityPager.setStorage(b);
            }
        });

    }

    @OnClick({R.id.tv_date, R.id.sp_org_create, R.id.search_supplier, R.id.search_supplier_hz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_date:
                datePicker(tvDate);
//                activityPager.setNote(edNot.getText().toString());
                break;
            case R.id.search_supplier:
                Bundle b = new Bundle();
                b.putString("search", edSupplier.getText().toString());
                b.putInt("where", Info.SEARCHSUPPLIER);
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
