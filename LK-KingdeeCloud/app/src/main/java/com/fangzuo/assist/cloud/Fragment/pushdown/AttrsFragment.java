package com.fangzuo.assist.cloud.Fragment.pushdown;


import android.app.Activity;
import android.content.Intent;
import android.device.ScanDevice;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseFragment;
import com.fangzuo.assist.cloud.Activity.AttrsPagerActivity;
import com.fangzuo.assist.cloud.Adapter.NumRvAdapter;
import com.fangzuo.assist.cloud.Adapter.PushDownListAdapter;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.PushDownListReturnBean;
import com.fangzuo.assist.cloud.Dao.PushDownMain;
import com.fangzuo.assist.cloud.Dao.PushDownSub;
import com.fangzuo.assist.cloud.Dao.TempDetil;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.CommonUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.GreedDaoUtil.GreenDaoManager;
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.MathUtil;
import com.fangzuo.assist.cloud.Utils.MediaPlayer;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.VibratorUtil;
import com.fangzuo.assist.cloud.widget.RecyclerViewDivider;
import com.fangzuo.greendao.gen.DaoSession;
import com.fangzuo.greendao.gen.PushDownMainDao;
import com.fangzuo.greendao.gen.PushDownSubDao;
import com.fangzuo.greendao.gen.T_DetailDao;
import com.fangzuo.greendao.gen.T_mainDao;
import com.fangzuo.greendao.gen.TempDetilDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * <p>
 * //下载单据信息Fragment（所属：PushDownPagerActivity);
 */
public class AttrsFragment extends BaseFragment {


    @BindView(R.id.rv_numChoose)
    RecyclerView rvNumChoose;
    @BindView(R.id.sp_lenght)
    Spinner spLenght;
    Unbinder unbinder;
    @BindView(R.id.tv_pihao)
    TextView tvPihao;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.ed_num)
    EditText edNum;
    @BindView(R.id.tv_numing)
    TextView tvNuming;
    @BindView(R.id.tv_add_num)
    TextView tvAddNum;
    @BindView(R.id.progress)
    ProgressBar progress;
    private int tag;
    private int activityWg;
    private String billNo;
    private String interId;
    private String enterId;
    private AttrsPagerActivity attrsPagerActivity;
    private FragmentActivity mContext;
    private ArrayList<Boolean> isCheck;
    private PushDownListAdapter pushDownListAdapter;
//    private ArrayList<PushDownMain> downloadIDs;            //用于listview选择时，添加临时对象
    private PushDownListReturnBean puBean;
    private DaoSession daosession;
    private ArrayList<PushDownMain> container;
    private ScanDevice sm;
    private Intent intent;
    private String TAG = "DownLoadPushFragment";
    private PushDownMainDao pushDownMainDao;
    private PushDownSubDao pushDownSubDao;
    private T_DetailDao t_detailDao;
    private T_mainDao t_mainDao;
    private ArrayAdapter<String> lenghtAdapter;
    private String lenght;
    private String wide;
    private String numing;
    private ArrayList<String> container_Width;
    private NumRvAdapter numRvAdapter;
    private GridLayoutManager gridLayoutManager;
    private PushDownSub pushDownSub;
    private TempDetilDao tempDetilDao;

    @Override
    protected void initView() {
        isCheck = new ArrayList<>();
//        downloadIDs = new ArrayList<>();
        daosession = GreenDaoManager.getmInstance(mContext).getDaoSession();

        t_mainDao = daosession.getT_mainDao();
        t_detailDao = daosession.getT_DetailDao();
        tempDetilDao = daosession.getTempDetilDao();

        pushDownMainDao = daosession.getPushDownMainDao();
        pushDownSubDao = daosession.getPushDownSubDao();

        List<PushDownSub> list = pushDownSubDao.queryBuilder().where(
                PushDownSubDao.Properties.FAccountID.eq(CommonUtil.getAccountID()),
                PushDownSubDao.Properties.FBillNo.eq(billNo),
                PushDownSubDao.Properties.FID.eq(interId),
                PushDownSubDao.Properties.FEntryID.eq(enterId)
        ).build().list();
        Lg.e("定位数据", list);
        if (list.size() > 0) {
            pushDownSub = list.get(0);
            progress.setProgress((int) (MathUtil.toD(pushDownSub.FQtying) / MathUtil.toD(pushDownSub.FQty) * 100));
            numing = MathUtil.toD(pushDownSub.FQtying)+"";
            tvName.setText("物料名称："+pushDownSub.FName);
            tvNum.setText("数量："+MathUtil.D2saveInt(MathUtil.toD(pushDownSub.FQty)));
            tvNuming.setText("已验数量："+MathUtil.D2saveInt(MathUtil.toD(pushDownSub.FQtying)));
            tvPihao.setText("批号："+pushDownSub.FBatchNo);
        } else {
            Toast.showText(mContext, "查验数据有误，请重新选择");
        }
    }

    @Override
    protected void OnReceive(String barCode) {
    }

    @Override
    protected void initData() {
        gridLayoutManager = new GridLayoutManager(mContext, 5);
        container_Width = new ArrayList<>();
        numRvAdapter = new NumRvAdapter(mContext, container_Width);
        rvNumChoose.setAdapter(numRvAdapter);
        rvNumChoose.addItemDecoration(new RecyclerViewDivider(3));
        rvNumChoose.setLayoutManager(gridLayoutManager);
//        int max = Hawk.get(Config.Jingji_Max,58);
        int num = 2;
        for (int i = 0; i < 24; i++) {
            container_Width.add(num + "");
            num += 1;
//            if (i % 2 == 0) container_Width.add(i + "");
        }
        numRvAdapter.notifyDataSetChanged();
//        gridLayoutManager.scrollToPosition(24);

        //处理长度Spinner---------------------------------------------------
        String[] lenghts = new String[17];
        int numl = 4;
        for (int i = 0; i < lenghts.length; i++) {
            lenghts[i] = numl + "";
            numl++;
        }
        lenghtAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, lenghts);
        spLenght.setAdapter(lenghtAdapter);
        //处理长度Spinner---------------------------------------------------END

        uploadProgress();
    }

    @Override
    protected void initListener() {
        spLenght.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                lenght = lenghtAdapter.getItem(i);
            }
        });
        numRvAdapter.setOnItemClickListener(new NumRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
//                edDiameter.setText(numRvAdapter.getItem(position));
                VibratorUtil.Vibrate(mContext, Info.VibratorTime);
                wide = numRvAdapter.getItem(position);


            }
        });
    }




    //点击事件
    @OnClick({R.id.btn_download, R.id.btn_search, R.id.btn_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_download:
                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.AutoAdd433, ""));
                getActivity().finish();
                break;
            case R.id.btn_add:
                if (MathUtil.toD(wide)<=0 || MathUtil.toD(lenght)<=0 || MathUtil.toD(edNum.getText().toString())<=0 ) {
                    Toast.showText(mContext, "长、宽、数量必须大于 0 ");
                } else {
                    Addorder();
                }
                break;
            case R.id.btn_search:
                attrsPagerActivity.clickToCheckView();
                break;
        }
    }

    private void Addorder() {
        TempDetil tDetailTemp = new TempDetil();
        tDetailTemp.FAccountID = CommonUtil.getAccountID();
        tDetailTemp.FProductName = pushDownSub.FName;
        tDetailTemp.FID = pushDownSub.FID;
        tDetailTemp.FEntryID = pushDownSub.FEntryID;
        tDetailTemp.FBatch = pushDownSub.FBatchNo;
        tDetailTemp.activity = activityWg;
        tDetailTemp.FLenght = lenght;
        tDetailTemp.FWide = wide;
        tDetailTemp.FNum = edNum.getText().toString();
        tDetailTemp.FAllNum = pushDownSub.FQty;
        tDetailTemp.FRealQty = MathUtil.getWgBf(lenght,wide,edNum.getText().toString());
        Lg.e("添加数据",tDetailTemp);
        tempDetilDao.insert(tDetailTemp);
        MediaPlayer.getInstance(mContext).ok();
        //表面进度条，实际本地Sub数据没变化

        uploadProgress();
    }

    private void uploadProgress(){
        Double res = 0.0;
        Double resAdd = 0.0;
        List<TempDetil> detailTemps = tempDetilDao.queryBuilder().where(
                TempDetilDao.Properties.FAccountID.eq(CommonUtil.getAccountID()),
                TempDetilDao.Properties.FID.eq(pushDownSub.FID),
                TempDetilDao.Properties.FEntryID.eq(pushDownSub.FEntryID),
                TempDetilDao.Properties.Activity.eq(activityWg)
        ).build().list();
        Lg.e("更新进度条",detailTemps);
        if (detailTemps.size()>0){
            for (int i = 0; i < detailTemps.size(); i++) {
                res+=MathUtil.toD(detailTemps.get(i).FRealQty);
                resAdd+=MathUtil.toD(detailTemps.get(i).FNum);
            }
        }
        tvNuming.setText("已验数量:"+MathUtil.D2saveInt(res));
        tvAddNum.setText("添加支数:"+MathUtil.D2saveInt(resAdd));
        progress.setProgress((int) (MathUtil.sum(numing,res+"")/ MathUtil.toD(pushDownSub.FQty) * 100));

    }

    //在oncreateView之前使用 不要使用控件
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (tempDetilDao!=null)uploadProgress();
            //相当于Fragment的onResume Lg.e("fragment显示");
//            if (null!=activityPager){
//                spWhichStorage.setAuto("",activityPager.getOrgOut());
//                spDepartmentGet.setAuto(getString(R.string.spDepartmentGet_pis),"",activityPager.getOrgOut());
//                spUnit.setAuto("", activityPager.getOrgOut(),SpinnerUnit.Id);
//                spUnitAux.setAuto("", activityPager.getOrgOut(),SpinnerUnit.Id);
//            }
        } else {
            //相当于Fragment的onPause Lg.e("fragment隐藏");
        }
    }




    public AttrsFragment() {
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
        attrsPagerActivity = ((AttrsPagerActivity) activity).getAttrsPagerActivity();
        Log.e("获取到--tag--", tag + "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attrs, container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = getActivity();
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
