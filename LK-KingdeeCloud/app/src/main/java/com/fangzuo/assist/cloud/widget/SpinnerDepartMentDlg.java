package com.fangzuo.assist.cloud.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Adapter.DepartmentSpAdapter;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Dao.Org;
import com.fangzuo.assist.cloud.Dao.Department;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Utils.BasicShareUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.GreedDaoUtil.GreenDaoManager;
import com.fangzuo.assist.cloud.Utils.JsonCreater;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.greendao.gen.DaoSession;
import com.fangzuo.greendao.gen.DepartmentDao;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

public class SpinnerDepartMentDlg extends RelativeLayout {
    // 返回按钮控件
    private Spinner mSp;
    // 标题Tv
    private TextView mTitleTv;
    private ImageView ivFind;
    private boolean showEd = false;
    //    private SpinnerAdapter adapter;
    private DaoSession daoSession;
    private ArrayList<String> autoList;
    private BasicShareUtil share;
    private ArrayList<Department> container;
    private ArrayList<Department> containerTemp;
    private DepartmentSpAdapter adapter;
    private DepartmentSpAdapter adapterDlg;
    private String autoString="";//用于联网时，再次去自动设置值
    private String autoOrg="";//用于联网时，再次去自动设置值
    private String saveKeyString="";//用于保存数据的key
    private String stringOfEt="";//用于保存数据的key
    private int activityTag=0;
    private String Id="";
    private String Name="";
    private String T="部门：";     //19


    public SpinnerDepartMentDlg(final Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        LayoutInflater.from(context).inflate(R.layout.view_storage_spinner, this);
        daoSession = GreenDaoManager.getmInstance(context).getDaoSession();

        autoList = new ArrayList<>();
        share = BasicShareUtil.getInstance(context);
        container = new ArrayList<>();
        containerTemp = new ArrayList<>();
        // 获取控件
        mSp = (Spinner) findViewById(R.id.sp);
        mTitleTv = (TextView) findViewById(R.id.tv);
        ivFind = (ImageView) findViewById(R.id.iv_find);
        adapter = new DepartmentSpAdapter(context, container);
        adapterDlg = new DepartmentSpAdapter(context, container);
        mSp.setAdapter(adapter);

        mTitleTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showDlg(context);
//                mSp.performClick();
            }
        });
        ivFind.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showDlg(context);
            }
        });
    }
    AlertDialog alertDialog;
    private void showDlg(Context context){
        AlertDialog.Builder ab = new AlertDialog.Builder(context);
        ab.setTitle("仓库过滤");
        View v = LayoutInflater.from(context).inflate(R.layout.show_storage, null);
        final EditText etSearch     = v.findViewById(R.id.et_search);
        final ListView listView     = v.findViewById(R.id.lv_storage);
        etSearch.setText(stringOfEt);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Lg.e("变化前....");
                containerTemp.clear();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Lg.e("变化中....");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Lg.e("变化后....");
                stringOfEt = etSearch.getText().toString();
                if ("".equals(etSearch.getText().toString().trim())){
                    adapterDlg.clear();
                    container.addAll(getLocData(autoOrg));
                    listView.setAdapter(adapterDlg);
                    adapterDlg.notifyDataSetChanged();
                }else{
                    container.clear();
                    container.addAll(getLocData(autoOrg));
                    for (int i = 0; i < container.size(); i++) {
                        if (container.get(i).FName.contains(etSearch.getText().toString())
                                ||container.get(i).FNumber.contains(etSearch.getText().toString())){
                            containerTemp.add(container.get(i));
                        }
                    }
                    adapterDlg.addData(containerTemp);
                    listView.setAdapter(adapterDlg);
                }
//                if (adapter.getCount()<=0){
//                    employeeId = "";
//                    employeeName = "";
//                    employeeNumber = "";
//                    Lg.e("重置");
//                }
            }
        });
        listView.setAdapter(adapterDlg);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Department storage = (Department) adapterDlg.getItem(i);
                for (int j = 0; j < adapter.getCount(); j++) {
                    if (storage.FName.equals(((Department)adapter.getItem(j)).FName)){
                        mSp.setSelection(j);
                    }
                }
                alertDialog.dismiss();
            }
        });
        ab.setView(v);
        ab.setPositiveButton("返回", null);

        alertDialog = ab.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    // 为左侧返回按钮添加自定义点击事件
    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
        mSp.setOnItemSelectedListener(listener);
    }
//    public void setAdapter(SpinnerAdapter adapter){
//        this.adapter = adapter;
//        mSp.setAdapter(adapter);
//    }
    public void setSelection(int i){
        mSp.setSelection(i);
    }
    public void setSelectionById(String interid){
        for (int j = 0; j < adapter.getCount(); j++) {
            if (((Department) adapter.getItem(j)).FItemID.equals(interid)) {
                mSp.setSelection(j);
                break;
            }
        }
    }


//    public String getDataId() {
//        return Id == null ? "" : Id;
//    }
//
//    public String getDataName() {
//        return Name == null ? "" : Name;
//    }


    public void setAuto(String saveKeyStr,String autoStr, Org org, int activityT) {
        Id="";
        Name="";
        saveKeyString=saveKeyStr;
        autoString = autoStr;
        activityTag = activityT;
        autoOrg = org==null?"":org.FOrgID;
        mTitleTv.setText("");
        final List<Department> listTemp = getLocData(autoOrg);
        dealAuto(listTemp, true);

        ArrayList<Integer> choose = new ArrayList<>();
        choose.add(2);
        String json = JsonCreater.DownLoadData(
                share.getDatabaseIp(),
                share.getDatabasePort(),
                share.getDataBaseUser(),
                share.getDataBasePass(),
                share.getDataBase(),
                share.getVersion(),
                choose
        );
        App.getRService().downloadData(json, new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                DownloadReturnBean dBean = JsonCreater.gson.fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                DepartmentDao yuandanTypeDao = daoSession.getDepartmentDao();
                yuandanTypeDao.deleteAll();
                yuandanTypeDao.insertOrReplaceInTx(dBean.department);
                yuandanTypeDao.detachAll();
                if (dBean.department.size() > 0 && container.size()<=0){
                    dealAuto(dBean.department,true);
//                    container.addAll(dBean.storage);
//                    adapter.notifyDataSetChanged();
//                    setAutoSelection(saveKeyString,autoString);
                }
            }

            @Override
            public void onError(Throwable e) {
//                    LoadingUtil.dismiss();
//                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Updata_Error,e.toString()));
            }
        });

    }

    private List<Department> getLocData(String org) {
        DepartmentDao employeeDao = daoSession.getDepartmentDao();
        List<Department> employees = employeeDao.queryBuilder().where(DepartmentDao.Properties.FOrg.eq(org)
        ).build().list();
        Lg.e("本地部门"+employees.size());
        return employees;
    }

    private void dealAuto(List<Department> listData, boolean check) {
        Lg.e("得到部门："+listData.size(),listData);
        container.clear();
        if (check) {
//当为生产单据时，过滤
            if (activityTag== Config.ProductInStoreActivity||activityTag==Config.ProductGetActivity||activityTag==Config.ProductGet4P2Activity||
                    activityTag==Config.P1PdProductGet2CprkActivity||activityTag==Config.ChangeInActivity|| activityTag==Config.TbGetActivity ||
                    activityTag==Config.TbGet2Activity ||activityTag==Config.DryingGetActivity||activityTag==Config.ProductGet4BoxActivity||
                    activityTag==Config.ChangeGetActivity|| activityTag==Config.TbGet3Activity||activityTag==Config.TbInActivity ||
                    activityTag==Config.WorkOrgGet4P2Activity ||activityTag==Config.P1PdProductGet2Cprk2Activity || activityTag==Config.TbIn2Activity ||
                    activityTag==Config.TbIn3Activity ||activityTag==Config.WorkOrgIn4P2Activity || activityTag==Config.GbGetActivity ||
                    activityTag==Config.GbInActivity ||activityTag==Config.BoxReBoxP1Activity ||activityTag==Config.BoxReAddP1Activity ||
                    activityTag==Config.DhInActivity || activityTag== Config.DhIn2Activity || activityTag== Config.ChangeLvGetActivity ||
                    activityTag== Config.ChangeModelGetActivity|| activityTag== Config.SplitBoxGetActivity|| activityTag== Config.ChangeLvInActivity||
                    activityTag== Config.ChangeModelInActivity|| activityTag== Config.SplitBoxInActivity|| activityTag== Config.ZbCheJianInActivity||
                    activityTag== Config.ZbCheJianHunInActivity|| activityTag== Config.ZbCheJianDiGetActivity||activityTag== Config.Bg1CheJianInActivity||
                    activityTag== Config.Bg2CheJianInActivity|| activityTag== Config.Bg1CheJianHunInActivity||activityTag== Config.Bg2CheJianHunInActivity
                    || activityTag== Config.Bg1CheJianDiGetActivity||activityTag== Config.Bg2CheJianDiGetActivity||activityTag== Config.CpWgInActivity
                    || activityTag== Config.CpWgHunInActivity|| activityTag== Config.SplitBoxHunInActivity|| activityTag== Config.SplitBoxDiGetActivity
                    || activityTag== Config.Tb1HunInActivity|| activityTag== Config.Tb2HunInActivity|| activityTag== Config.Tb3HunInActivity
                    || activityTag== Config.Tb1DiGetActivity|| activityTag== Config.Tb2DiGetActivity|| activityTag== Config.Tb3DiGetActivity
                    || activityTag== Config.GbDiGetActivity|| activityTag== Config.GbHunInActivity|| activityTag== Config.ZbCheJianDiZGetActivity
                    || activityTag== Config.BoxReAddP2Activity|| activityTag== Config.ProductGet4BoxP2Activity
                    ) {
                for (int i = 0; i < listData.size(); i++) {
                    if (listData.get(i).FOrg.equals(autoOrg)) {
                        if (listData.get(i).FISSTOCK.equals("1")){
                            container.add(listData.get(i));
                        }
                    }
                }
            }else{
                for (int i = 0; i < listData.size(); i++) {
                    if (listData.get(i).FOrg.equals(autoOrg)) {
                        container.add(listData.get(i));
                    }
                }
            }
        } else {
            container.addAll(listData);
        }
        Lg.e("过滤后部门"+container.size());
        if ("".equals(autoString)){
            autoString = Hawk.get(saveKeyString,"");
        }
        if (container.size() > 0) {
            mSp.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            for (int j = 0; j < container.size(); j++) {
                if (container.get(j).FName.equals(autoString)
                        || container.get(j).FItemID.equals(autoString)) {
                    mSp.setSelection(j);
                    break;
                }
            }
        } else {
            adapter.notifyDataSetChanged();
        }

    }





    public DepartmentSpAdapter getAdapter() {
        return adapter;
    }

    //清空
    private void clear() {
        container.clear();
    }
//     设置标题的方法
    public void setTitleText(String title) {
        mTitleTv.setText(title);
    }
    public String getText(){
        return mTitleTv.getText().toString();
    }

}
