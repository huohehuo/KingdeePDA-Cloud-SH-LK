package com.fangzuo.assist.cloud.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Adapter.EmployeeSpAdapter;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Dao.Employee;
import com.fangzuo.assist.cloud.Dao.Org;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Utils.BasicShareUtil;
import com.fangzuo.assist.cloud.Utils.GreedDaoUtil.GreenDaoManager;
import com.fangzuo.assist.cloud.Utils.JsonCreater;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.greendao.gen.DaoSession;
import com.fangzuo.greendao.gen.EmployeeDao;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

public class SpinnerEmployee extends RelativeLayout {
    // 返回按钮控件
    private Spinner mSp;
    // 标题Tv
    private TextView mTitleTv;
    private boolean showEd = false;
    //    private SpinnerAdapter adapter;
    private DaoSession daoSession;
    private ArrayList<String> autoList;
    private BasicShareUtil share;
    private ArrayList<Employee> container;
    private EmployeeSpAdapter adapter;
    private String autoString="";//用于联网时，再次去自动设置值
    private String autoOrg = "";//用于联网时，再次去自动设置值
    private String saveKeyString="";//用于保存数据的key
    private String employeeId="";
    private String employeeName="";
    private String employeeNumber="";
    private String T="Employee：";     //3


    public SpinnerEmployee(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        LayoutInflater.from(context).inflate(R.layout.view_buyer_spinner, this);
        daoSession = GreenDaoManager.getmInstance(context).getDaoSession();

        autoList = new ArrayList<>();
        share = BasicShareUtil.getInstance(context);
        container = new ArrayList<>();
        // 获取控件
        mSp = (Spinner) findViewById(R.id.sp);
        mTitleTv = (TextView) findViewById(R.id.tv);
        TypedArray attrArray = context.obtainStyledAttributes(attributeSet, R.styleable.ManSpinner);
        int count = attrArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attrName = attrArray.getIndex(i);
            switch (attrName) {
                case R.styleable.ManSpinner_manspinner_name:
                    mTitleTv.setText(attrArray.getString(R.styleable.ManSpinner_manspinner_name));
                    break;
                case R.styleable.ManSpinner_manspinner_title:
                    mSp.setPrompt(attrArray.getString(R.styleable.ManSpinner_manspinner_title));
                    break;
                case R.styleable.ManSpinner_manspinner_size:
                    mTitleTv.setText(attrArray.getString(R.styleable.ManSpinner_manspinner_name));
                    mTitleTv.setTextSize(attrArray.getDimension(R.styleable.ManSpinner_manspinner_size,15));
                    break;
            }
        }
        attrArray.recycle();
        adapter = new EmployeeSpAdapter(context, container);
        mSp.setAdapter(adapter);
//        if (share.getIsOL()) {
//            ArrayList<Integer> choose = new ArrayList<>();
//            choose.add(3);
//            String json = JsonCreater.DownLoadData(
//                    share.getDatabaseIp(),
//                    share.getDatabasePort(),
//                    share.getDataBaseUser(),
//                    share.getDataBasePass(),
//                    share.getDataBase(),
//                    share.getVersion(),
//                    choose
//            );
//            Asynchttp.post(context, share.getBaseURL() + WebApi.DOWNLOADDATA, json, new Asynchttp.Response() {
//                @Override
//                public void onSucceed(CommonResponse cBean, AsyncHttpClient client) {
//                    Lg.e("得到Employee："+cBean.returnJson);
//                    DownloadReturnBean dBean = JsonCreater.gson.fromJson(cBean.returnJson, DownloadReturnBean.class);
////                    Lg.e("得到buyer：",dBean.buyers.get(0));
//                    EmployeeDao payTypeDao = daoSession.getEmployeeDao();
//                    payTypeDao.deleteAll();
//                    payTypeDao.insertOrReplaceInTx(dBean.employee);
//                    payTypeDao.detachAll();
//                    if (dBean.employee.size() > 0 && container.size()<=0){
//                        container.addAll(dBean.employee);
//                        adapter.notifyDataSetChanged();
//                        setAutoSelection(saveKeyString,autoString);
//                    }
//                }
//
//                @Override
//                public void onFailed(String Msg, AsyncHttpClient client) {
////                    Toast.showText(context, Msg);
//                }
//            });
//        }
////        else {
//            EmployeeDao employeeDao = daoSession.getEmployeeDao();
//            List<Employee> employees = employeeDao.loadAll();
//            container.addAll(employees);
//            adapter.notifyDataSetChanged();
//            setAutoSelection(saveKeyString,autoString);
//
////            Log.e("CommonMethod", "获取到本地数据：\n" + container.toString());
////        }


        mSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Employee employee = (Employee) adapter.getItem(i);
                employeeId = employee.FItemID;
                employeeName = employee.FName;
                employeeNumber = employee.FNumber;
                Lg.e("选中"+T,employee);
                Hawk.put(saveKeyString,employee.FName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

//    //仓库Spinner
//    public StorageSpAdapter getStorageSpinner(AutoSpinner sp){
//        final ArrayList<Storage> container = new ArrayList<>();
//        final StorageSpAdapter adapter = new StorageSpAdapter(context, container);
//        sp.setAdapter(adapter);
//        if(share.getIsOL()){
//            Log.e("CommonMethod:","getStorageSpinner联网");
//            ArrayList<Integer> choose = new ArrayList<>();
//            choose.add(6);
//            String json = JsonCreater.DownLoadData(
//                    share.getDatabaseIp(),
//                    share.getDatabasePort(),
//                    share.getDataBaseUser(),
//                    share.getDataBasePass(),
//                    share.getDataBase(),
//                    share.getVersion(),
//                    choose
//            );
//            Asynchttp.post(context,share.getBaseURL()+ WebApi.DOWNLOADDATA, json, new Asynchttp.Response() {
//                @Override
//                public void onSucceed(CommonResponse cBean, AsyncHttpClient client) {
//                    Log.e("CommonMethod:","getStorageSpinner获得联网数据：\n"+cBean.returnJson);
//                    DownloadReturnBean dBean = JsonCreater.gson.fromJson(cBean.returnJson, DownloadReturnBean.class);
//                    container.addAll(dBean.storage);
//                    adapter.notifyDataSetChanged();
//                }
//
//                @Override
//                public void onFailed(String Msg, AsyncHttpClient client) {
//                    Toast.showText(context, Msg);
//                }
//            });
////            RetrofitUtil.getInstance(context).createReq(WebAPI.class).
////                    downloadData(RetrofitUtil.getParams(context,json)).enqueue(new CallBack() {
////                @Override
////                public void onSucceed(CommonResponse cBean) {
////                    Log.e("CommonMethod:","getStorageSpinner获得数据：\n"+cBean.returnJson);
////                    DownloadReturnBean dBean = JsonCreater.gson.fromJson(cBean.returnJson, DownloadReturnBean.class);
////                    container.addAll(dBean.storage);
////                    adapter.notifyDataSetChanged();
////                }
////
////                @Override
////                public void OnFail(String Msg) {
////                    Toast.showText(context, Msg);
////                }
////            });
//        }else{
//            Log.e("CommonMethod:","getStorageSpinner不-联网");
//            StorageDao storageDao = daoSession.getStorageDao();
//            List<Storage> storages = storageDao.loadAll();
//            container.addAll(storages);
//            Log.e("CommonMethod","获取到本地数据：\n"+container.toString());
//            adapter.notifyDataSetChanged();
//        }
//        return adapter;
//    }
    //自动设置保存的值


    public EmployeeSpAdapter getSpAdapter() {
        if (adapter.getCount() < 0) {
            Lg.e("adapter初始化失败，重新更新adapter");
            EmployeeDao employeeDao = daoSession.getEmployeeDao();
            List<Employee> employees = employeeDao.loadAll();
            container.addAll(employees);
            adapter.notifyDataSetChanged();
            return adapter;
        } else {
            return adapter;
        }
    }

    // 为左侧返回按钮添加自定义点击事件
    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
        mSp.setOnItemSelectedListener(listener);
    }
//    public void setAdapter(SpinnerAdapter adapter){
//        this.adapter = adapter;
//        mSp.setAdapter(adapter);
//    }
//    public void setSelection(int i){
//        mSp.setSelection(i);
//    }


    public String getDataId() {
        return employeeId == null ? "" : employeeId;
    }

    public String getDataName() {
        return employeeName == null ? "" : employeeName;
    }
    public String getDataNumber() {
        return employeeNumber == null ? "" : employeeNumber;
    }
    /**
     *
     * @param saveKeyStr        用于保存的key
     * @param string            自动设置的z值
     * */
    public void setAutoSelection(String saveKeyStr,String string) {
        saveKeyString =saveKeyStr;
        autoString = string;
        if ("".equals(string)&&!"".equals(saveKeyStr)){
            autoString = Hawk.get(saveKeyString,"");
        }
        for (int j = 0; j < adapter.getCount(); j++) {
            if (((Employee) adapter.getItem(j)).FNumber.equals(autoString)
                    || ((Employee) adapter.getItem(j)).FName.equals(autoString)) {
                mSp.setSelection(j);
//                autoString = null;
                break;
            }
        }
    }


    public void setAuto(String saveKeyStr,String autoStr, Org org) {
        employeeId="";
        employeeName="";
        employeeNumber="";
        saveKeyString =saveKeyStr;
        autoString = autoStr;
        autoOrg = org==null?"":org.FOrgID;
        final List<Employee> listTemp = getLocData(autoOrg);
        dealAuto(listTemp, false);

        ArrayList<Integer> choose = new ArrayList<>();
        choose.add(3);
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
                EmployeeDao payTypeDao = daoSession.getEmployeeDao();
                payTypeDao.deleteAll();
                payTypeDao.insertOrReplaceInTx(dBean.employee);
                payTypeDao.detachAll();
                if (dBean.employee.size() > 0 && container.size() <= 0) {
                    dealAuto(dBean.employee,true);
//                    setAuto(autoString,autoOrg);
//                    container.addAll(dBean.storeMans);
//                    adapter.notifyDataSetChanged();
//                    setAutoSelection(saveKeyString, autoString);
                }
            }

            @Override
            public void onError(Throwable e) {
//                    LoadingUtil.dismiss();
//                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Updata_Error,e.toString()));
            }
        });

    }


    private List<Employee> getLocData(String org) {
        EmployeeDao employeeDao = daoSession.getEmployeeDao();
        List<Employee> employees = employeeDao.queryBuilder().where(EmployeeDao.Properties.FOrg.eq(org)
        ).build().list();
        return employees;
    }

    private void dealAuto(List<Employee> listData, boolean check) {
        container.clear();
        if (check) {
            for (int i = 0; i < listData.size(); i++) {
                if (listData.get(i).FOrg.equals(autoOrg)) {
                    container.add(listData.get(i));
                }
            }
        } else {
            container.addAll(listData);
        }
        if ("".equals(autoString) && !"".equals(saveKeyString)) {
            autoString = Hawk.get(saveKeyString, "");
        }
        if (container.size() > 0) {
            mSp.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            if (container.size() == 1) {//当只有一个的时候，重新适配器，为了spinner的监听能响应
                employeeId = container.get(0).FItemID;
                employeeName = container.get(0).FName;
                employeeNumber = container.get(0).FNumber;
            } else {//过滤设定的值
                if (null == autoString || "".equals(autoString) || "0".equals(autoString)) {
                    employeeId = container.get(0).FItemID;
                    employeeName = container.get(0).FName;
                    employeeNumber = container.get(0).FNumber;
                    adapter.notifyDataSetChanged();
                } else {
                    for (int j = 0; j < container.size(); j++) {
                        if (container.get(j).FName.equals(autoString)) {
                            Lg.e("单位定位（自定义控件：" + autoString);
                            employeeId = container.get(j).FItemID;
                            employeeName = container.get(j).FName;
                            employeeNumber = container.get(j).FNumber;
                            mSp.setSelection(j);
                            break;
                        }
                    }
//                    if (Number.equals(type)) {
//                        for (int j = 0; j < container.size(); j++) {
//                            if (container.get(j).FNumber.equals(autoString)) {
//                                Lg.e("单位定位（自定义控件：" + autoString);
//                                employeeId = container.get(j).FItemID;
//                                employeeName = container.get(j).FName;
//                                employeeNumber = container.get(j).FNumber;
//                                mSp.setSelection(j);
//                                break;
//                            }
//                        }
//                    } else if (Name.equals(type)) {
//                        for (int j = 0; j < container.size(); j++) {
//                            if (container.get(j).FName.equals(autoString)) {
//                                Lg.e("单位定位（自定义控件：" + autoString);
//                                employeeId = container.get(j).FItemID;
//                                employeeName = container.get(j).FName;
//                                employeeNumber = container.get(j).FNumber;
//                                mSp.setSelection(j);
//                                break;
//                            }
//                        }
//                    } else if (Id.equals(type)) {
//                        for (int j = 0; j < container.size(); j++) {
//                            if (container.get(j).FItemID.equals(autoString)) {
//                                Lg.e("单位定位（自定义控件：" + autoString);
//                                employeeId = container.get(j).FItemID;
//                                employeeName = container.get(j).FName;
//                                employeeNumber = container.get(j).FNumber;
//                                mSp.setSelection(j);
//                                break;
//                            }
//                        }
//                    }
                    if ("".equals(employeeId) && "".equals(employeeName)) {
                        employeeId = container.get(0).FItemID;
                        employeeName = container.get(0).FName;
                        employeeNumber = container.get(0).FNumber;
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        } else {
            adapter.notifyDataSetChanged();
        }

    }


    public EmployeeSpAdapter getAdapter() {
        return adapter;
    }

    //清空
    private void clear() {
        container.clear();
    }
//     设置标题的方法
//    public void setTitleText(String title) {
//        mTitleTv.setText(title);
//    }

}
