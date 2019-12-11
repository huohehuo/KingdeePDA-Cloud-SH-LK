package com.fangzuo.assist.cloud.Fragment.pushdown;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.device.ScanDevice;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseFragment;
import com.fangzuo.assist.cloud.Activity.AttrsPagerActivity;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Activity.PushDownPagerActivity;
import com.fangzuo.assist.cloud.Adapter.PushDownListAdapter;
import com.fangzuo.assist.cloud.Adapter.ReViewCheckP233Adapter;
import com.fangzuo.assist.cloud.Beans.CommonBean;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownLoadSubListBean;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Beans.PurchaseInStoreUploadBean;
import com.fangzuo.assist.cloud.Beans.PushDownDLBean;
import com.fangzuo.assist.cloud.Beans.PushDownListRequestBean;
import com.fangzuo.assist.cloud.Beans.PushDownListReturnBean;
import com.fangzuo.assist.cloud.Beans.ScanDLReturnBean;
import com.fangzuo.assist.cloud.Dao.PGetData;
import com.fangzuo.assist.cloud.Dao.PushDownMain;
import com.fangzuo.assist.cloud.Dao.PushDownSub;
import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.Dao.T_main;
import com.fangzuo.assist.cloud.Dao.TempDetil;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Utils.Asynchttp;
import com.fangzuo.assist.cloud.Utils.BasicShareUtil;
import com.fangzuo.assist.cloud.Utils.CommonUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.GreedDaoUtil.GreenDaoManager;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.MathUtil;
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
import com.fangzuo.greendao.gen.TempDetilDao;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 *
 *          //下载单据信息Fragment（所属：PushDownPagerActivity);
 */
public class CheckFragment extends BaseFragment {



    @BindView(R.id.lv_result)
    ListView lvResult;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_add_num)
    TextView tvAddNum;
    Unbinder unbinder;
//    @BindView(R.id.sp_supplier)
//    SpinnerSupplier spSupplier;
    private int tag;
    private int activityWg;
    private FragmentActivity mContext;
    private PushDownListAdapter pushDownListAdapter;
    private ArrayList<PushDownMain> downloadIDs;            //用于listview选择时，添加临时对象
    private PushDownListReturnBean puBean;
    private DaoSession daosession;
    private ArrayList<PushDownMain> container;
    private ScanDevice sm;
    private Intent intent;
    private String TAG="DownLoadPushFragment";
    private PushDownMainDao pushDownMainDao;
    private PushDownSubDao pushDownSubDao;
    private TempDetilDao tempDetilDao;
    private T_mainDao t_mainDao;
    private int activity;
    private List<TempDetil> list;
    private List<T_main> list_main;
    private ReViewCheckP233Adapter adapter;
    private List<Boolean> isCheck;
    private String billNo;
    private String interId;
    private String enterId;
    @Override
    protected void initView() {
        isCheck = new ArrayList<>();
        downloadIDs = new ArrayList<>();
        daosession = GreenDaoManager.getmInstance(mContext).getDaoSession();




//        if (tag == 1) {
            //供应商信息绑定
//            spClient.setVisibility(View.GONE);
//            spSupplier.setVisibility(View.VISIBLE);
//        } else {
//            //客户信息绑定
//            spClient.setVisibility(View.VISIBLE);
//            spSupplier.setVisibility(View.GONE);
//        }
    }

    @Override
    protected void OnReceive(String barCode) {

    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        tempDetilDao = daosession.getTempDetilDao();
        isCheck = new ArrayList<>();
        list = tempDetilDao.queryBuilder().where(
                TempDetilDao.Properties.FAccountID.eq(CommonUtil.getAccountID()),
                TempDetilDao.Properties.FID.eq(interId),
                TempDetilDao.Properties.FEntryID.eq(enterId),
                TempDetilDao.Properties.Activity.eq(activityWg)
        ).build().list();
        Lg.e("tmp数据",list);
        Double res = 0.0;
        Double resAdd = 0.0;
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                isCheck.add(false);
                res+= MathUtil.toD(list.get(i).FRealQty);
                resAdd+= MathUtil.toD(list.get(i).FNum);
            }
        }
        tvNum.setText("总数量："+MathUtil.D2saveInt(res));
        tvAddNum.setText("总支量："+MathUtil.D2saveInt(resAdd));
        adapter = new ReViewCheckP233Adapter(mContext, list, isCheck);
        lvResult.setAdapter(adapter);
//        adapter.setInnerListener(this);
        adapter.notifyDataSetChanged();


    }

    private ArrayList<TempDetil> tempDetils;
    @Override
    protected void initListener() {
        lvResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                if (isCheck.get(i)) {
                    isCheck.set(i, false);
                } else {
                    isCheck.set(i, true);
                }
                adapter.notifyDataSetChanged();

            }
        });
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder delete = new AlertDialog.Builder(mContext);
                delete.setTitle("确认删除");
                delete.setMessage("确认删除选中单据么？");
                delete.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LoadingUtil.showDialog(mContext, "正在删除...");
                        tempDetils = new ArrayList<>();

                        for (int j = 0; j < isCheck.size(); j++) {
                            if (isCheck.get(j)) {
//                                Log.e(i + "", isCheck.get(j) + "");
                                final TempDetil t_detail = tempDetilDao.queryBuilder().where(
                                        TempDetilDao.Properties.FIndex.eq(list.get(j).FIndex)
                                ).build().unique();
                                tempDetils.add(t_detail);
                            }
                        }
                        tempDetilDao.deleteInTx(tempDetils);
                        LoadingUtil.dismiss();
                        initData();
                    }
                });
                delete.setNegativeButton("取消", null);
                delete.create().show();
            }
        });

    }



    public CheckFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        tag = ((AttrsPagerActivity) activity).getTitles();
        activityWg = ((AttrsPagerActivity) activity).getActivity();
        billNo = ((AttrsPagerActivity) activity).getBillNo();
        interId = ((AttrsPagerActivity) activity).getInterId();
        enterId = ((AttrsPagerActivity) activity).getEnterId();
        Log.e("获取到--tag--",tag+"");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check, container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = getActivity();
        return view;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

//
//    //点击事件
//    @OnClick({R.id.btn_download, R.id.btn_search,R.id.start_date,R.id.end_date})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.btn_download:
//                if (downloadIDs.size() > 0) {
//                    download(downloadIDs);
//                } else {
//                    Toast.showText(mContext, "未选择单号");
//                }
//                break;
//            case R.id.btn_search:
////                searchList();
//                break;
//
//        }
//    }

    //在oncreateView之前使用 不要使用控件
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            initData();
            //相当于Fragment的onResume Lg.e("fragment显示");
        } else {
            //相当于Fragment的onPause Lg.e("fragment隐藏");
        }
    }

    //下载数据
    private void download(final ArrayList<PushDownMain> downloadIDs) {

    }

}
