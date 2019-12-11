package com.fangzuo.assist.cloud.Fragment.TabForActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseFragment;
import com.fangzuo.assist.cloud.Activity.PagerForActivity;
import com.fangzuo.assist.cloud.Adapter.Pg4BoxP1Adapter;
import com.fangzuo.assist.cloud.Beans.CodeCheckBackDataBean;
import com.fangzuo.assist.cloud.Beans.CodeCheckBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.WortPrintData;
import com.fangzuo.assist.cloud.Dao.T_main;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.CommonUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.DataModel;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.LocDataUtil;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.fangzuo.assist.cloud.widget.NumberClick;
import com.fangzuo.assist.cloud.widget.SpinnerDepartMent;
import com.fangzuo.assist.cloud.widget.SpinnerStoreMan;
import com.fangzuo.assist.cloud.zxing.ScanManager;
import com.fangzuo.greendao.gen.T_mainDao;
import com.google.gson.Gson;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.orhanobut.hawk.Hawk;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


//选择单据信息Fragment（所属：PushDownPagerActivity);
public class FragmentBoxReBoxMain extends BaseFragment {


    @BindView(R.id.zxing_barcode_scanner)
    DecoratedBarcodeView zxingBarcodeScanner;
    //    @BindView(R.id.sp_org_in)
//    SpinnerOrg spOrgIn;
//    @BindView(R.id.sp_org_create)
//    SpinnerHuozhu spOrgCreate;
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
    //    @BindView(R.id.sp_which_storage)
//    SpinnerStorage spWhichStorage;
    @BindView(R.id.cb_num)
    NumberClick cbNum;
    @BindView(R.id.tv_Storage)
    TextView tvStorage;
    @BindView(R.id.tv_org)
    TextView tvOrg;
    @BindView(R.id.tv_huozhu)
    TextView tvHuozhu;
    private FragmentActivity mContext;
    private PagerForActivity activityPager;
    Unbinder unbinder;
    @BindView(R.id.ly_scan)
    RelativeLayout lyScan;

    @BindView(R.id.ry_data)
    EasyRecyclerView ryData;
    Pg4BoxP1Adapter adapter;
    private ScanManager mCaptureManager;
    private List<WortPrintData> wortPrintDataList;
    private CodeCheckBackDataBean codeCheckBackDataBean4Error;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.ScanResult:
                BarcodeResult res = (BarcodeResult) event.postEvent;
                mCaptureManager.onPause();
                lyScan.setVisibility(View.GONE);
                OnReceive(res.getResult().getText());
                break;
            case EventBusInfoCode.UpdataStorage:
                String id = (String) event.postEvent;
                Lg.e("更改仓库数据" + id);
//                spWhichStorage.setAuto(getString(R.string.spWhichStorage_pris)+activityPager.getActivityMain(), id, activityPager.getOrgOut());
                break;
            case EventBusInfoCode.Lock_Main://是否锁住表头
                String lock = (String) event.postEvent;
                if (Config.Lock.equals(lock)) {          //锁住表头数据
                    activityPager.setHasLock(true);
                    spDepartmentGet.setEnable(false);
//                    spOrgCreate.setEnable(false);
//                    spOrgIn.setEnable(false);
//                    spOrgHuozhu.setEnable(false);
                    spStoreman.setEnable(false);
                    edFfOrder.setFocusable(false);
//                    edFfOrder.setClickable(false);
                    edNot.setFocusable(false);

//                    edFfOrder.setText(Hawk.get(Config.OrderNo + activityPager.getActivityMain(), edFfOrder.getText().toString()));
//                    edNot.setText(Hawk.get(Config.Note + activityPager.getActivityMain(), edNot.getText().toString()));
//                    Hawk.put(Config.OrderNo + activityPager.getActivityMain(), edFfOrder.getText().toString());//保存业务单号
//                    Hawk.put(Config.Note + activityPager.getActivityMain(), edNot.getText().toString());//保存业务单号
                } else {
                    activityPager.setHasLock(false);
                    spDepartmentGet.setEnable(true);
//                    spOrgCreate.setEnable(true);
//                    spOrgIn.setEnable(true);
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
            case EventBusInfoCode.Code_Check://条码检测
                LoadingUtil.dismiss();
                wortPrintDataList = (List<WortPrintData>) event.postEvent;
                Lg.e("条码检测：" + wortPrintDataList.size(), wortPrintDataList);
                if (wortPrintDataList.size() > 0) {
                    if (wortPrintDataList.get(0).FTip.equals("OK")) {
//                    if ("1".equals(codeCheckBackDataBean.FCodeType)){
//                        Toast.showText(mContext,"请勿扫描原木条码");
//                        return;
//                    }
                        adapter.clear();
                        adapter.addAll(wortPrintDataList);
                        activityPager.setBoxCode(barcode);//设置箱码
                        activityPager.setBatch(wortPrintDataList.get(0).FBatch);//设置批号
                        dealMain(wortPrintDataList.get(0));
//                        LoadingUtil.showDialog(mContext, "正在查找物料信息");
//                        DataModel.getProductForId(wortPrintDataList.get(0).FItemID, activityPager.getOrgOut());//03/12:已改为FNumber查找，不分org
                    } else {
//                    activityPager.ReSetScan(cbScaning);
                        Toast.showText(mContext, wortPrintDataList.get(0).FTip);
                    }
                } else {
                    Toast.showText(mContext, "查询箱码数据错误");
                }

                break;
            case EventBusInfoCode.Code_Only_Insert://写入条码唯一临时表
                codeCheckBackDataBean4Error = (CodeCheckBackDataBean) event.postEvent;
                if (codeCheckBackDataBean4Error.FTip.equals("OK")) {
//                    Addorder();
                } else {
                    LoadingUtil.dismiss();
                    Toast.showText(mContext, codeCheckBackDataBean4Error.FTip);
                }
                break;

        }
    }

    private void dealMain(WortPrintData bean) {
        activityPager.setOrgOut(LocDataUtil.getOrg(bean.FOrgNumber, "number"));
        activityPager.setOrgIn(LocDataUtil.getOrg(bean.FHuozhuNumber, "number"));
        activityPager.setStorage(LocDataUtil.getStorage(bean.FStorageNumber, "number"));
        tvStorage.setText("仓库："+activityPager.getStorage().FName);
        tvOrg.setText("库存组织："+activityPager.getOrgOut().FName);
        tvHuozhu.setText("货主："+activityPager.getOrgIn().FName);
        spDepartmentGet.setAuto(getString(R.string.spDepartmentGet_pris) + activityPager.getActivityMain(), Hawk.get(getString(R.string.spDepartmentGet_pris) + activityPager.getActivityMain(), ""), activityPager.getOrgIn(), activityPager.getActivity());
        spStoreman.setAuto(getString(R.string.spStoreman_pris)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spStoreman_pris)+activityPager.getActivityMain(), ""), activityPager.getOrgOut());

    }

    public FragmentBoxReBoxMain() {
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
        View view = inflater.inflate(R.layout.fragment_boxrebox_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = getActivity();
        EventBusUtil.register(this);
        Lg.e("Fg_M:" + "onCreateView");
        return view;
    }

    @Override
    protected void initView() {
        Lg.e("Fg_M:" + "initView");
        ryData.setAdapter(adapter = new Pg4BoxP1Adapter(mContext));
        ryData.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //摄像头初始化
        mCaptureManager = new ScanManager(activityPager, zxingBarcodeScanner);
        mCaptureManager.initializeFromIntent(activityPager.getIntent(), activityPager.getSavedInstanceState());
        activityPager.setScanManager(mCaptureManager);
    }


    private String barcode = "";

    @Override
    protected void OnReceive(String code) {
        barcode = code;
        LoadingUtil.showDialog(mContext, "正在检测条码...");
        //查询条码唯一表
        CodeCheckBean bean = new CodeCheckBean(code);
        DataModel.codeCheck4DryOut(WebApi.CodeCheckForBoxReBox4P1, new Gson().toJson(bean));
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
//        setfocus(tvDate);

    }

    @Override
    public void onResume() {
        super.onResume();
//        tvDate.setText(CommonUtil.getTime(true));
        cbNum.setSaveKey(activityPager.getActivityMain() + "printnum");
//        activityPager.setDate(tvDate.getText().toString());
        //第一个参数用于保存上一个值，第二个为自动跳转到该默认值
//        spOrgIn.setAutoSelection(getString(R.string.spOrgIn_pris)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spOrgIn_pris)+activityPager.getActivityMain(), ""));//仓库，仓管员，部门都以组织id来过滤
//        spOrgCreate.setAutoSelection(getString(R.string.spOrgCreate_pris)+activityPager.getActivityMain(), activityPager.getOrgOut(), Hawk.get(getString(R.string.spOrgCreate_pris)+activityPager.getActivityMain(), ""));
//        spOrgHuozhu.setAutoSelection(getString(R.string.spOrgHuozhu_pris)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spOrgHuozhu_pris)+activityPager.getActivityMain(), ""));
//        spStoreman.setAuto(getString(R.string.spStoreman_pris)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spStoreman_pris)+activityPager.getActivityMain(), ""), activityPager.getOrgOut());
//        spDepartmentGet.setAuto(getString(R.string.spDepartmentGet_pris)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spDepartmentGet_pris)+activityPager.getActivityMain(), ""), activityPager.getOrgOut(), activityPager.getActivity());
//        spWhichStorage.setAuto(getString(R.string.spWhichStorage_pris)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spWhichStorage_pris)+activityPager.getActivityMain(), ""), activityPager.getOrgOut());
//        binding.spOrgIn.setEnable(false);
//        spOrgCreate.setEnable(false);
//        cbIsStorage.setChecked(Hawk.get(Info.Storage + activityPager.getActivityMain(), false));

        List<T_main> list = activityPager.getT_mainDao().queryBuilder().where(
                T_mainDao.Properties.FOrderId.eq(CommonUtil.createOrderCode(activityPager.getActivity())),
                T_mainDao.Properties.Activity.eq(activityPager.getActivity()),
                T_mainDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
        ).build().list();
        if (list.size() > 0) {
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
                activityPager.setDate(CommonUtil.getTime(true));
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
//        spWhichStorage.setOnItemSelectedListener(new ItemListener() {
//            @Override
//            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                Storage storage = (Storage) spWhichStorage.getAdapter().getItem(i);
//                activityPager.setStorage(storage);
//                spWhichStorage.setTitleText(storage.FName);
//                Hawk.put(getString(R.string.spWhichStorage_pris)+activityPager.getActivityMain(), storage.FName);
//                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.UpdataWaveHouse, storage));
//                Lg.e("选中仓库：", storage);
//            }
//        });
//        spOrgIn.setOnItemSelectedListener(new ItemListener() {
//            @Override
//            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                activityPager.setOrgOut((Org) spOrgIn.getAdapter().getItem(i));
//                Hawk.put(getString(R.string.spOrgIn_pris)+activityPager.getActivityMain(), activityPager.getOrgOut().FName);
//                spStoreman.setAuto(getString(R.string.spStoreman_pris)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spStoreman_pris)+activityPager.getActivityMain(), ""), activityPager.getOrgOut());
//                spOrgCreate.setAutoSelection(getString(R.string.spOrgCreate_pris)+activityPager.getActivityMain(), activityPager.getOrgOut(), "");
//                spWhichStorage.setAuto(getString(R.string.spWhichStorage_pris)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spWhichStorage_pris)+activityPager.getActivityMain(), ""), activityPager.getOrgOut());
//                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.UpdataView, ""));
//
//            }
//        });
//        spOrgCreate.setOnItemSelectedListener(new ItemListener() {
//            @Override
//            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                activityPager.setOrgIn((Org) spOrgCreate.getAdapter().getItem(i));
//                spDepartmentGet.setAuto(getString(R.string.spDepartmentGet_pris)+activityPager.getActivityMain(), Hawk.get(getString(R.string.spDepartmentGet_pris)+activityPager.getActivityMain(), ""), activityPager.getOrgIn(), activityPager.getActivity());
//                Hawk.put(getString(R.string.spOrgCreate_pris)+activityPager.getActivityMain(), activityPager.getOrgIn().FName);
//            }
//        });
//        spOrgHuozhu.setOnItemSelectedListener(new ItemListener() {
//            @Override
//            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                activityPager.setHuozhuOut((Org) spOrgHuozhu.getAdapter().getItem(i));
//                Hawk.put(getString(R.string.spOrgHuozhu_pris)+activityPager.getActivityMain(),activityPager.getHuozhuOut().FName);
//            }
//        });

    }

    @OnClick({R.id.search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search:
                if (lyScan.getVisibility() == View.VISIBLE) {
                    lyScan.setVisibility(View.GONE);
//                    mCaptureManager.onPause();
                } else {
                    mCaptureManager.onResume();
                    lyScan.setVisibility(View.VISIBLE);
                    mCaptureManager.decode();
                }
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
        unbinder.unbind();
    }


}
