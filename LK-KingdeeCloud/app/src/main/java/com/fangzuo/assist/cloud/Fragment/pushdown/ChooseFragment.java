package com.fangzuo.assist.cloud.Fragment.pushdown;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseFragment;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Activity.PagerForActivity;
import com.fangzuo.assist.cloud.Activity.PushDownPagerActivity;
import com.fangzuo.assist.cloud.Adapter.PushDownListAdapter;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.PurchaseInStoreUploadBean;
import com.fangzuo.assist.cloud.Dao.PushDownMain;
import com.fangzuo.assist.cloud.Dao.PushDownSub;
import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.Dao.T_main;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Utils.CommonUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.GreedDaoUtil.GreenDaoManager;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.fangzuo.assist.cloud.widget.SpinnerClientDlg;
import com.fangzuo.greendao.gen.DaoSession;
import com.fangzuo.greendao.gen.PGetDataDao;
import com.fangzuo.greendao.gen.PushDownMainDao;
import com.fangzuo.greendao.gen.PushDownSubDao;
import com.fangzuo.greendao.gen.T_DetailDao;
import com.fangzuo.greendao.gen.T_mainDao;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


//选择单据信息Fragment（所属：PushDownPagerActivity);
public class ChooseFragment extends BaseFragment {

    @BindView(R.id.ed_code)
    EditText edCode;
    @BindView(R.id.start_date)
    TextView startDate;
    @BindView(R.id.end_date)
    TextView endDate;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.lv_pushdown_download)
    ListView lvPush;
    Unbinder unbinder;
    @BindView(R.id.btn_getpush)
    Button btnGetpush;
    @BindView(R.id.rl1)
    LinearLayout rl1;
    @BindView(R.id.v1)
    View v1;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.sp_client)
    SpinnerClientDlg spClient;
    //    @BindView(R.id.sp_supplier)
//    SpinnerSupplier spSupplier;
    private FragmentActivity mContext;
    private DaoSession daosession;
    private ArrayList<Boolean> isCheck;
    private int year;
    private int month;
    private int day;
    private String enddate;
    private String startdate;
    private int tag;
    //    private SupplierSpAdapter supplierAdapter;
//    private ClientSpAdapter clientSpAdapter;
    private String supplierID;
    private boolean defaultsp = false;
    private List<PushDownMain> container;               //单据信息，用于存储查找到的单据数据
    private ArrayList<PushDownMain> downloadIDs;        //单据信息，用于存储选中的单据数据
    private PushDownListAdapter pushDownListAdapter;
    private Intent intent;


    public ChooseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        tag = ((PushDownPagerActivity) activity).getTitles();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choose, container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = getActivity();
        return view;
    }

    private T_DetailDao t_detailDao;
    private T_mainDao t_mainDao;
    private PushDownSubDao pushDownSubDao;
    private PushDownMainDao pushDownMainDao;
    private PGetDataDao pGetDataDao;
    private int activity;

    @Override
    protected void initView() {
        isCheck = new ArrayList<>();
        downloadIDs = new ArrayList<PushDownMain>();
        container = new ArrayList<>();
        daosession = GreenDaoManager.getmInstance(mContext).getDaoSession();
//        if (tag == 1) {
//            //供应商信息绑定
//            spClient.setVisibility(View.GONE);
//            spSupplier.setVisibility(View.VISIBLE);
//        } else {
//            //客户信息绑定
//            spClient.setVisibility(View.VISIBLE);
//            spSupplier.setVisibility(View.GONE);
//        }
        initList();
        if (tag == 1) activity = Config.PdCgOrder2WgrkActivity;
        if (tag == 32) activity = Config.FLInStoreP1Activity;
        if (tag == 2) activity = Config.PdSaleOrder2SaleOutActivity;
        if (tag == 31) activity = Config.PdSaleOrder2SaleOut4BoxActivity;
        if (tag == 21) activity = Config.PdSaleOrder2SaleOut2Activity;
        if (tag == 22) activity = Config.PdDbApply2DBActivity;
        if (tag == 23) activity = Config.PdDbApply2DB4VMIActivity;
        if (tag == 6) activity = Config.PdBackMsg2SaleBackActivity;
        if (tag == 25) activity = Config.P2ProductionInStoreActivity;
        if (tag == 27) activity = Config.P2ProductionInStore2Activity;
        if (tag == 26) activity = Config.P2PdCgrk2ProductGetActivity;
        if (tag == 28) activity = Config.P1PdCgrk2ProductGetActivity;
        if (tag == 29) activity = Config.P1PdProductGet2CprkActivity;
        if (tag == 30) activity = Config.P1PdProductGet2Cprk2Activity;
    }

    @Override
    protected void OnReceive(String barCode) {

    }

    @Override
    protected void initData() {

    }

    private void push() {
        boolean flag = true;
        ArrayList<String> container = new ArrayList<>();
        if (downloadIDs.size() == 0) {
            Toast.showText(mContext, "请选择单据");
            return;
        }

            for (int i = 0; i < downloadIDs.size(); i++) {
                container.add(downloadIDs.get(i).FBillNo);
//                if (tag != 28){//采购入库下推生产领料允许多订单下推20190816
                    if (i > 0 && !downloadIDs.get(i).FSupplyID.equals(downloadIDs.get(i - 1).FSupplyID)) {
                        flag = false;
                        break;
                    }
                    if (i > 0 && !downloadIDs.get(i).FBillNo.equals(downloadIDs.get(i - 1).FBillNo)) {
                        flag = false;
                        break;
                    }
//                }

            }


        if (flag && downloadIDs.size() > 0) {
            Bundle b = new Bundle();
            b.putStringArrayList("fid", container);
            Log.e("ChooseFragment", "跳转数据：" + container.toString());
            switch (tag) {
                case 1://采购订单下推外购入库单
                    PagerForActivity.start(mContext, Config.PdCgOrder2WgrkActivity, container);
//                    intent = new Intent(mContext, PdCgOrder2WgrkActivity.class);
                    break;
                case 32://方料入库
                    PagerForActivity.start(mContext, Config.FLInStoreP1Activity, container);
//                    intent = new Intent(mContext, PdCgOrder2WgrkActivity.class);
                    break;
                case 2://销售订单下推销售出库单
                    PagerForActivity.start(mContext, Config.PdSaleOrder2SaleOutActivity, container);
                    break;
                case 31://销售订单下推销售出库单
                    PagerForActivity.start(mContext, Config.PdSaleOrder2SaleOut4BoxActivity, container);
                    break;
                case 21://VMI销售订单下推销售出库单
                    PagerForActivity.start(mContext, Config.PdSaleOrder2SaleOut2Activity, container);
                    break;
                case 6://退货通知单下推销售退货单
                    PagerForActivity.start(mContext, Config.PdBackMsg2SaleBackActivity, container);
//                    intent = new Intent(mContext, PdBackMsg2SaleBackActivity.class);
                    break;
                case 22://调拨申请单下推直接调拨单
                    PagerForActivity.start(mContext, Config.PdDbApply2DBActivity, container);
//                    intent = new Intent(mContext, PdBackMsg2SaleBackActivity.class);
                    break;
                case 23://VMI调拨申请单下推直接调拨单
                    PagerForActivity.start(mContext, Config.PdDbApply2DB4VMIActivity, container);
//                    intent = new Intent(mContext, PdBackMsg2SaleBackActivity.class);
                    break;
                case 24://出库申请单下推其他出库单
                    PagerForActivity.start(mContext, Config.PdOutApply2OtherOutActivity, container);
//                    intent = new Intent(mContext, PdBackMsg2SaleBackActivity.class);
                    break;
                case 25://简单生产入库
                    PagerForActivity.start(mContext, Config.P2ProductionInStoreActivity, container);
//                    intent = new Intent(mContext, PdBackMsg2SaleBackActivity.class);
                    break;
                case 26://采购入库单下推简单生产领料
                    PagerForActivity.start(mContext, Config.P2PdCgrk2ProductGetActivity, container);
//                    intent = new Intent(mContext, PdBackMsg2SaleBackActivity.class);
                    break;
                case 28://采购入库单下推简单生产领料（一期）
                    PagerForActivity.start(mContext, Config.P1PdCgrk2ProductGetActivity, container);
//                    intent = new Intent(mContext, PdBackMsg2SaleBackActivity.class);
                    break;
                case 29://生产领料单下推产品入库单（一期）
                    PagerForActivity.start(mContext, Config.P1PdProductGet2CprkActivity, container);
//                    intent = new Intent(mContext, PdBackMsg2SaleBackActivity.class);
                    break;
                case 30://生产领料单下推产品入库单（一期）
                    PagerForActivity.start(mContext, Config.P1PdProductGet2Cprk2Activity, container);
//                    intent = new Intent(mContext, PdBackMsg2SaleBackActivity.class);
                    break;
                case 27://简单生产入库
                    PagerForActivity.start(mContext, Config.P2ProductionInStore2Activity, container);
//                    intent = new Intent(mContext, PdBackMsg2SaleBackActivity.class);
                    break;
                case 33://简单生产入库
                    PagerForActivity.start(mContext, Config.WgDryingInStoreActivity, container);
//                    intent = new Intent(mContext, PdBackMsg2SaleBackActivity.class);
                    break;

//                case 3://销售订单下推销售退货单
//                    intent = new Intent(mContext, PdSaleOrder2SaleBackActivity.class);
//                    break;
//                case 4://销售出库单下推销售退货单
//                    intent = new Intent(mContext, PdSaleOut2SaleBackActivity.class);
//                    break;
//                case 5://发货通知单下推销售出库单
//                    intent = new Intent(mContext, PdSendMsg2SaleOutActivity.class);
//                    break;
//                case 7://调拨申请单下推分布式调入单
//                    intent = new Intent(mContext, Db2FDinActivity.class);
//                    break;
//                case 8://调拨申请单下推分布式调出单
//                    intent = new Intent(mContext, Db2FDoutActivity.class);
//                    break;
            }


//            intent.putExtras(b);
//            startActivity(intent);
//            getActivity().finish();
        } else {
            if (!flag) Toast.showText(mContext, "不同单据、供应商不能同时做单");
            else if (downloadIDs.size() < 0) Toast.showText(mContext, "未选择下推单据");
        }

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume
            initList();
        } else {
            //相当于Fragment的onPause
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    private void initList() {
        isCheck.clear();
        container.clear();
        downloadIDs.clear();
        t_mainDao = daosession.getT_mainDao();
        t_detailDao = daosession.getT_DetailDao();
        pushDownSubDao = daosession.getPushDownSubDao();
        pushDownMainDao = daosession.getPushDownMainDao();
        if (tag == 30){
            pGetDataDao = daosession.getPGetDataDao();
        }
        //根据 tag 查找相应的单据
        List<PushDownMain> pushDownMains = pushDownMainDao.queryBuilder().where(
                PushDownMainDao.Properties.Tag.eq(tag),
                PushDownMainDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
        ).build().list();
        container.addAll(pushDownMains);
        for (int i = 0; i < pushDownMains.size(); i++) {
            isCheck.add(false);
        }
        pushDownListAdapter = new PushDownListAdapter(mContext, container, isCheck);
        lvPush.setAdapter(pushDownListAdapter);
        pushDownListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initListener() {
        //刷新
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initList();
                refresh.setRefreshing(false);
            }
        });

        //列表选中监听
        lvPush.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PushDownMain pushDownListReturnBean = (PushDownMain) pushDownListAdapter.getItem(i);
                if (isCheck.get(i)) {
                    Log.e("choose", "不--选中");
                    isCheck.set(i, false);
                    for (int j = 0; j < downloadIDs.size(); j++) {
                        if (downloadIDs.get(j).FBillNo.equals(pushDownListReturnBean.FBillNo)) {
                            downloadIDs.remove(j);
                        }
                    }
                } else {
                    Log.e("choose", "选中");
                    isCheck.set(i, true);
                    downloadIDs.add(pushDownListReturnBean);
                }
                pushDownListAdapter.notifyDataSetChanged();
            }
        });
        startDate.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startDate.setText("");
                return true;
            }
        });
        endDate.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                endDate.setText("");
                return true;
            }
        });
    }

    public String getTime(boolean b) {
        SimpleDateFormat format = new SimpleDateFormat(b ? "yyyy-MM-dd" : "yyyyMMdd");
        Date curDate = new Date();
        String str = format.format(curDate);
        return str;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_delete, R.id.btn_search, R.id.btn_getpush, R.id.start_date, R.id.end_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_delete:
                checkLocData();
                break;
            case R.id.btn_search:
                Search();
                break;
            case R.id.btn_getpush:
                push();
                break;
            case R.id.start_date:
                datePicker(startDate);
                break;
            case R.id.end_date:
                datePicker(endDate);
                break;
        }
    }


    private void checkLocData() {
        List<T_Detail> list = new ArrayList<>();
        for (PushDownMain bean : downloadIDs) {
            List<T_Detail> list1 = t_detailDao.queryBuilder().where(
                    T_DetailDao.Properties.Activity.eq(activity),
                    T_DetailDao.Properties.FAccountID.eq(CommonUtil.getAccountID()),
                    T_DetailDao.Properties.FID.eq(bean.FID)
            ).build().list();
            if (list1.size() > 0) {
                list.addAll(list1);
            }
        }
        if (list.size() > 0) {
            new AlertDialog.Builder(mContext)
                    .setTitle("本地存在未删除数据，")
                    .setPositiveButton("确认删除", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            delete();
                        }
                    })
                    .setNegativeButton("取消",null)
                    .create().show();
        }else{
            delete();
        }

    }

    private PurchaseInStoreUploadBean pBean;
    private PurchaseInStoreUploadBean.purchaseInStore listBean;
    private ArrayList<PurchaseInStoreUploadBean.purchaseInStore> data;
    private ArrayList<T_Detail> t_detailList;
    private ArrayList<T_main> t_mainsList;

    //删除本地数据
    private void delete() {
        List<T_Detail> list = new ArrayList<>();
        for (PushDownMain bean : downloadIDs) {
            List<T_Detail> list1 = t_detailDao.queryBuilder().where(
                    T_DetailDao.Properties.Activity.eq(activity),
                    T_DetailDao.Properties.FAccountID.eq(CommonUtil.getAccountID()),
                    T_DetailDao.Properties.FID.eq(bean.FID)
            ).build().list();
            if (list1.size() > 0) {
                list.addAll(list1);
            }
        }
        Lg.e("删除的明细dids：",downloadIDs);
        Lg.e("删除的明细：",list);
        if (list.size() > 0) {
//            t_detailList = new ArrayList<>();
//            t_mainsList = new ArrayList<>();
            pBean = new PurchaseInStoreUploadBean();
            listBean = pBean.new purchaseInStore();
            data = new ArrayList<>();
            String detail = "";
            listBean.main = "";
            ArrayList<String> detailContainer = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                if (j != 0 && j % 49 == 0) {
                    Log.e("j%49", j % 49 + "");
                    T_Detail t_detail = list.get(j);
                    detail = detail +
                            t_detail.FBarcode + "|" +
                            t_detail.FRealQty + "|" +
                            t_detail.IMIE + "|" +
                            t_detail.FOrderId + "|";
                    detail = detail.subSequence(0, detail.length() - 1).toString();
                    detailContainer.add(detail);
                    detail = "";
                } else {
                    Log.e("j", j + "");
                    T_Detail t_detail = list.get(j);
                    detail = detail +
                            t_detail.FBarcode + "|" +
                            t_detail.FRealQty + "|" +
                            t_detail.IMIE + "|" +
                            t_detail.FOrderId + "|";
                    Log.e("detail1", detail);
                }
            }
            if (detail.length() > 0) {
                detail = detail.subSequence(0, detail.length() - 1).toString();
            }
            Log.e("detail", detail);
            detailContainer.add(detail);
            listBean.detail = detailContainer;
            data.add(listBean);
            pBean.list = data;
            Gson gson = new Gson();
            App.getRService().doIOAction(WebApi.CodeOnlyDelete, gson.toJson(pBean), new MySubscribe<CommonResponse>() {
                @Override
                public void onNext(CommonResponse commonResponse) {
                    super.onNext(commonResponse);
                    if (!commonResponse.state) return;
                    Lg.e("删除请求成功");
                    for (int i = 0; i < downloadIDs.size(); i++) {
                        List<PushDownSub> pushDownSubs = pushDownSubDao.queryBuilder().where(
                                PushDownSubDao.Properties.FID.eq(downloadIDs.get(i).FID),
                                PushDownSubDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
                        ).build().list();
                        Lg.e("删除表体"+pushDownSubs.size(),pushDownSubs);
                        for (int j = 0; j < pushDownSubs.size(); j++) {
                            pushDownSubDao.delete(pushDownSubs.get(j));
                        }
                        //删掉与该单据相关的明细
                        t_detailDao.deleteInTx(t_detailDao.queryBuilder().where(
                                T_DetailDao.Properties.FID.eq(downloadIDs.get(i).FID),
                                T_DetailDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
                        ).build().list());
                        t_mainDao.deleteInTx(t_mainDao.queryBuilder().where(
                                T_mainDao.Properties.FID.eq(downloadIDs.get(i).FID),
                                T_mainDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
                        ).build().list());
                        pushDownMainDao.delete(downloadIDs.get(i));
                        if (tag == 30){
                            pGetDataDao.deleteInTx(pGetDataDao.queryBuilder().where(
                                    PGetDataDao.Properties.FID.eq(downloadIDs.get(i).FID),
                                    PGetDataDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
                            ).build().list());
                        }
                        Toast.showText(mContext, "删除成功");
                    }
                        initList();
                        Search();
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    LoadingUtil.dismiss();
                    Toast.showText(mContext, "删除失败：" + e.toString());
                }
            });

        } else {
            Lg.e("downLoadIDs",downloadIDs);
            for (int i = 0; i < downloadIDs.size(); i++) {
                List<PushDownSub> pushDownSubs = pushDownSubDao.queryBuilder().where(
                        PushDownSubDao.Properties.FID.eq(downloadIDs.get(i).FID),
                        PushDownSubDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
                ).build().list();
                for (int j = 0; j < pushDownSubs.size(); j++) {
                    pushDownSubDao.delete(pushDownSubs.get(j));
                }
                //删掉与该单据相关的明细
//                daosession.getT_DetailDao().deleteInTx(daosession.getT_DetailDao().queryBuilder().where(
//                        T_DetailDao.Properties.FID.eq(downloadIDs.get(i).FID)).build().list());
//                daosession.getT_mainDao().deleteInTx(daosession.getT_mainDao().queryBuilder().where(
//                        T_mainDao.Properties.FIndex.eq(downloadIDs.get(i).FID)).build().list());
                pushDownMainDao.deleteInTx(pushDownMainDao.queryBuilder().where(PushDownMainDao.Properties.FBillNo.eq(downloadIDs.get(i).FBillNo)).build().list());
                if (tag == 30){
                    pGetDataDao.deleteInTx(pGetDataDao.queryBuilder().where(
                            PGetDataDao.Properties.FID.eq(downloadIDs.get(i).FID),
                            PGetDataDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
                    ).build().list());
                }

                Toast.showText(mContext, "删除成功");
            }
            initList();
            Search();
        }



    }

    //查找本地数据
    private void Search() {
//        if (tag == 1) {
//            //供应商信息绑定
//            supplierID=spSupplier.getDataId();
//        } else {
        //客户信息绑定
        supplierID = spClient.getDataName();
//        }

        container.clear();
        isCheck.clear();
        String con = "";
        if (!"".equals(endDate.getText().toString()) && !"".equals(startDate.getText().toString())) {
            con += " and  FDATE between " + "\'" + startDate.getText().toString() + " 00:00:00.0" + "\'" + "and" + "\'" + endDate.getText().toString() + " 00:00:00.0" + "\'";
        }
        if (!"".equals(supplierID)) {
            con += " and FSUPPLY='" + supplierID + "'";
        }
        if (!"".equals(edCode.getText().toString())) {
            con += " and FBILL_NO='" + edCode.getText().toString() + "'";
        }
        String SQL = "SELECT * FROM PUSH_DOWN_MAIN WHERE 1=1 " + con + " and TAG=" + tag +" and FACCOUNT_ID = '"+CommonUtil.getAccountID()+"'";
        Lg.e("SQL:" + SQL);
        Cursor cursor = GreenDaoManager.getmInstance(mContext).getDaoSession().getDatabase().rawQuery(SQL, null);
        while (cursor.moveToNext()) {
            PushDownMain f = new PushDownMain();
            f.FBillNo = cursor.getString(cursor.getColumnIndex("FBILL_NO"));
            f.FID = cursor.getString(cursor.getColumnIndex("FID"));
            f.FDate = cursor.getString(cursor.getColumnIndex("FDATE"));
            f.FSupplyID = cursor.getString(cursor.getColumnIndex("FSUPPLY_ID"));
            f.FSupply = cursor.getString(cursor.getColumnIndex("FSUPPLY"));
            f.tag = cursor.getInt(cursor.getColumnIndex("TAG"));
            f.FAccountID = cursor.getString(cursor.getColumnIndex("FACCOUNT_ID"));
            container.add(f);
        }
        if (container.size() == 0) {
            Toast.showText(mContext, getString(R.string.find_nothing));
        } else {
            for (int i = 0; i < container.size(); i++) {
                isCheck.add(false);
            }
        }
        Lg.e("choose列表数据"+container.size(),container);
        pushDownListAdapter.notifyDataSetChanged();


//        String code = edCode.getText().toString();
//        String endtime = endDate.getText().toString();
//        String startTime = startDate.getText().toString();
//        List<PushDownMain> list = pushDownMainDao.queryBuilder().whereOr(
//                PushDownMainDao.Properties.Tag.eq(tag),
//                PushDownMainDao.Properties.FBillNo.like("%" + code + "%"),
//                PushDownMainDao.Properties.FSupplyID.like("%" + supplierID + "%"),
//                PushDownMainDao.Properties.FDate.between(startTime, endtime)
//        ).build().list();
//        if (list.size() > 0) {
//            container.clear();
//            container.addAll(list);
//            isCheck.clear();
//            for (int i = 0; i < container.size(); i++) {
//                isCheck.add(false);
//            }
//            pushDownListAdapter = new PushDownListAdapter(mContext, container, isCheck);
//            lvPush.setAdapter(pushDownListAdapter);
//        } else {
//            Toast.showText(mContext, "未查询到数据");
//        }

//        pushDownListAdapter.notifyDataSetChanged();
    }


}
