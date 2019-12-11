package com.fangzuo.assist.cloud.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Adapter.StorageSpAdapter;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Dao.Department;
import com.fangzuo.assist.cloud.Dao.Org;
import com.fangzuo.assist.cloud.Dao.Storage;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Utils.BasicShareUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.GreedDaoUtil.GreenDaoManager;
import com.fangzuo.assist.cloud.Utils.JsonCreater;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.greendao.gen.DaoSession;
import com.fangzuo.greendao.gen.StorageDao;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

public class SpinnerStorage extends RelativeLayout {
    // 返回按钮控件
    private Spinner mSp;
    // 标题Tv
    private TextView mTitleTv;
    private boolean showEd = false;
    //    private SpinnerAdapter adapter;
    private DaoSession daoSession;
    private ArrayList<String> autoList;
    private BasicShareUtil share;
    private ArrayList<Storage> container;
    private StorageSpAdapter adapter;
    private String autoString="";//用于联网时，再次去自动设置值
    private String autoOrg="";//用于联网时，再次去自动设置值
    private String saveKeyString="";//用于保存数据的key
    private String Id="";
    private String Name="";
    private String T="仓库：";     //19


    public SpinnerStorage(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        LayoutInflater.from(context).inflate(R.layout.view_my_people_spinner, this);
        daoSession = GreenDaoManager.getmInstance(context).getDaoSession();

        autoList = new ArrayList<>();
        share = BasicShareUtil.getInstance(context);
        container = new ArrayList<>();
        // 获取控件
        mSp = (Spinner) findViewById(R.id.sp);
        mTitleTv = (TextView) findViewById(R.id.tv);
        adapter = new StorageSpAdapter(context, container);
        mSp.setAdapter(adapter);

//        if (share.getIsOL()) {
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
//            Asynchttp.post(context, share.getBaseURL() + WebApi.DOWNLOADDATA, json, new Asynchttp.Response() {
//                @Override
//                public void onSucceed(CommonResponse cBean, AsyncHttpClient client) {
//                    DownloadReturnBean dBean = JsonCreater.gson.fromJson(cBean.returnJson, DownloadReturnBean.class);
//                    StorageDao yuandanTypeDao = daoSession.getStorageDao();
//                    yuandanTypeDao.deleteAll();
//                    yuandanTypeDao.insertOrReplaceInTx(dBean.storage);
//                    yuandanTypeDao.detachAll();
//                    if (dBean.storage.size() > 0 && container.size()<=0){
//                        container.addAll(dBean.storage);
//                        adapter.notifyDataSetChanged();
//                        setAutoSelection(saveKeyString,autoString);
//                    }
//
//                }
//
//                @Override
//                public void onFailed(String Msg, AsyncHttpClient client) {
////                    Toast.showText(context, Msg);
//                }
//            });
//        }
////        else {
//            StorageDao storageDao = daoSession.getStorageDao();
//            List<Storage> storages = storageDao.loadAll();
//            container.addAll(storages);
//            adapter.notifyDataSetChanged();
//            if (autoString != null) {
//                setAutoSelection(saveKeyString,autoString);
//            }
//            Log.e("CommonMethod", "获取到本地数据：\n" + container.toString());
//        }


//        mSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                InStoreType employee = (InStoreType) adapter.getItem(i);
//                Id = employee.FID;
//                Name = employee.FName;
//                Lg.e("选中"+T+employee.toString());
//                Hawk.put(saveKeyString,employee.FName);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

        //点击文字时，触发spinner点击
        mTitleTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mSp.performClick();
            }
        });
    }

    //自动设置保存的值

//
//    public EmployeeSpAdapter getEmployeeSpAdapter() {
//        if (adapter.getCount() < 0) {
//            Lg.e("adapter初始化失败，重新更新adapter");
//            EmployeeDao employeeDao = daoSession.getEmployeeDao();
//            List<Employee> employees = employeeDao.loadAll();
//            container.addAll(employees);
//            adapter.notifyDataSetChanged();
//            return adapter;
//        } else {
//            return adapter;
//        }
//    }

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
            if (((Storage) adapter.getItem(j)).FItemID.equals(interid)) {
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

    /**
     *
     * @param saveKeyStr        用于保存的key
     * @param string            自动设置的z值
     * */
    public void setAutoSelection(String saveKeyStr,String string) {
        saveKeyString =saveKeyStr;
        autoString = string;
        Lg.e("自动"+T+autoString);
        if ("".equals(string)){
            autoString = Hawk.get(saveKeyString,"");
        }
        for (int j = 0; j < adapter.getCount(); j++) {
            if (((Storage) adapter.getItem(j)).FName.equals(autoString)
                    || ((Storage) adapter.getItem(j)).FItemID.equals(autoString)) {
                mSp.setSelection(j);
//                autoString = null;
                break;
            }
        }
    }
    //弃用
    public void setAuto(String autoStr, Org org) {
        Id="";
        Name="";
        autoString = autoStr;
        autoOrg = org==null?"":org.FOrgID;
        mTitleTv.setText("");
        final List<Storage> listTemp = getLocData(autoOrg);
        dealAuto(listTemp, false);

        ArrayList<Integer> choose = new ArrayList<>();
        choose.add(6);
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
                StorageDao yuandanTypeDao = daoSession.getStorageDao();
                yuandanTypeDao.deleteAll();
                yuandanTypeDao.insertOrReplaceInTx(dBean.storage);
                yuandanTypeDao.detachAll();
                if (dBean.storage.size() > 0 && container.size()<=0){
                    dealAuto(dBean.storage,true);
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
    private int activityTag=0;
    public void setAuto(String key, String autoStr, Org org, int activityT) {
        activityTag=activityT;
        setAuto(key,autoStr,org);
    }
    public void setAuto(String key,String autoStr, Org org) {
        Id="";
        Name="";
        autoString = autoStr;
        if ("".equals(autoStr)||"0".equals(autoStr)){
            autoString = Hawk.get(key,"");
        }
        autoOrg = org==null?"":org.FOrgID;
        mTitleTv.setText("");
        final List<Storage> listTemp = getLocData(autoOrg);
        dealAuto(listTemp, false);

        ArrayList<Integer> choose = new ArrayList<>();
        choose.add(6);
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
                Lg.e("得到仓库数量",dBean.storage.size());
                if (dBean.storage.size() > 0 && container.size()<=0){
                    StorageDao yuandanTypeDao = daoSession.getStorageDao();
                    yuandanTypeDao.deleteAll();
                    yuandanTypeDao.insertOrReplaceInTx(dBean.storage);
                    yuandanTypeDao.detachAll();
                    dealAuto(dBean.storage,true);
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

    private List<Storage> getLocData(String org) {
        StorageDao employeeDao = daoSession.getStorageDao();
        List<Storage> employees = employeeDao.queryBuilder().where(StorageDao.Properties.FOrg.eq(org)
        ).build().list();
        return employees;
    }

    private void dealAuto(List<Storage> listData, boolean check) {
        Lg.e("筛选仓库",autoString);
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
        //处理不同单据的再过滤条件
        dealFilter();
        if (container.size() > 0) {
            mSp.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            for (int j = 0; j < container.size(); j++) {
                if (container.get(j).FName.equals(autoString)|| container.get(j).FNumber.equals(autoString)
                        || container.get(j).FItemID.equals(autoString)) {
                    mSp.setSelection(j);
                    break;
                }
            }
        } else {
            adapter.notifyDataSetChanged();
        }

    }


    //过滤条件
    private void dealFilter(){
        switch (activityTag){
            case Config.CpWgInActivity:
            case Config.CpWgHunInActivity:
                for(int i = container.size()-1;i >= 0;i--) {
                    Storage department = container.get(i);
                    if(department.FName.contains("在途") || department.FName.contains("外存放")) {
//                        Lg.e("删除仓库",department);
                        container.remove(department);
                    }else{
                        Lg.e("保留仓库",department);
                    }
                }
                break;
//            case Config.Bg2CheJianDiGetActivity:
            case Config.Bg2CheJianHunInActivity:
            case Config.Bg2CheJianInActivity:
//            case Config.Bg1CheJianDiGetActivity:
            case Config.Bg1CheJianHunInActivity:
            case Config.Bg1CheJianInActivity:
//            case Config.ZbCheJianDiZGetActivity:
//            case Config.ZbCheJianDiGetActivity:
            case Config.ZbCheJianHunInActivity:
            case Config.ZbCheJianInActivity:
                for(int i = container.size()-1;i >= 0;i--) {
                    Storage department = container.get(i);
                    if(department.FName.contains("在途") || department.FName.contains("外存放")) {
//                        Lg.e("删除仓库",department);
                        container.remove(department);
                    }else{
                        Lg.e("保留仓库",department);
                    }
                }
                break;
//            case Config.SplitBoxGetActivity:
//            case Config.SplitBoxDiGetActivity:
            case Config.SplitBoxInActivity:
            case Config.SplitBoxHunInActivity:
                for(int i = container.size()-1;i >= 0;i--) {
                    Storage department = container.get(i);
                    if(department.FName.contains("在途") || department.FName.contains("外存放")) {
//                        Lg.e("删除仓库",department);
                        container.remove(department);
                    }else{
                        Lg.e("保留仓库",department);
                    }
                }
                break;
//            case Config.GbGetActivity:
            case Config.GbInActivity:
//            case Config.GbDiGetActivity:
            case Config.GbHunInActivity:
                for(int i = container.size()-1;i >= 0;i--) {
                    Storage department = container.get(i);
                    if(department.FName.contains("在途") || department.FName.contains("外存放")) {
//                        Lg.e("删除仓库",department);
                        container.remove(department);
                    }else{
                        Lg.e("保留仓库",department);
                    }
                }
                break;
            case Config.Tb3HunInActivity:
//            case Config.Tb3DiGetActivity:
            case Config.TbIn3Activity:
//            case Config.TbGet3Activity:
            case Config.Tb2HunInActivity:
//            case Config.Tb2DiGetActivity:
            case Config.TbIn2Activity:
//            case Config.TbGet2Activity:
            case Config.Tb1HunInActivity:
//            case Config.Tb1DiGetActivity:
            case Config.TbInActivity:
            case Config.ZbIn1Activity:
            case Config.ZbIn2Activity:
            case Config.ZbIn3Activity:
            case Config.ZbIn4Activity:
            case Config.ZbIn5Activity:
//            case Config.TbGetActivity:
                for(int i = container.size()-1;i >= 0;i--) {
                    Storage department = container.get(i);
                    if(department.FName.contains("在途") || department.FName.contains("外存放")) {
//                        Lg.e("删除仓库",department);
                        container.remove(department);
                    }else{
                        Lg.e("保留仓库",department);
                    }
                }
                break;
            case Config.ChangeModelInActivity:
//            case Config.ChangeModelGetActivity:
                for(int i = container.size()-1;i >= 0;i--) {
                    Storage department = container.get(i);
                    if(department.FName.contains("在途") || department.FName.contains("外存放")) {
//                        Lg.e("删除仓库",department);
                        container.remove(department);
                    }else{
                        Lg.e("保留仓库",department);
                    }
                }
                break;
//            case Config.ChangeLvGetActivity:
            case Config.ChangeLvInActivity:
                for(int i = container.size()-1;i >= 0;i--) {
                    Storage department = container.get(i);
                    if(department.FName.contains("在途") || department.FName.contains("外存放")) {
//                        Lg.e("删除仓库",department);
                        container.remove(department);
                    }else{
                        Lg.e("保留仓库",department);
                    }
                }
                break;
//            case Config.ChangeGetActivity:
            case Config.ChangeInActivity:
                for(int i = container.size()-1;i >= 0;i--) {
                    Storage department = container.get(i);
                    if(department.FName.contains("在途") || department.FName.contains("外存放")) {
//                        Lg.e("删除仓库",department);
                        container.remove(department);
                    }else{
                        Lg.e("保留仓库",department);
                    }
                }
                break;
            case Config.DhInActivity:
            case Config.DhIn2Activity:
                for(int i = container.size()-1;i >= 0;i--) {
                    Storage department = container.get(i);
                    if(department.FName.contains("在途") || department.FName.contains("外存放")) {
//                        Lg.e("删除仓库",department);
                        container.remove(department);
                    }else{
                        Lg.e("保留仓库",department);
                    }
                }
                break;
            case Config.DBClientActivity:
            case Config.DBStorageActivity:
                for(int i = container.size()-1;i >= 0;i--) {
                    Storage department = container.get(i);
                    if(!department.FName.contains("东北发")) {
//                        Lg.e("删除仓库",department);
                        container.remove(department);
                    }else{
                        Lg.e("保留仓库",department);
                    }
                }
                break;
            default:
                //        adapter = new OrgSpAdapter(context, container);
                mSp.setAdapter(adapter);
                adapter.notifyDataSetChanged();
        }
    }





    public StorageSpAdapter getAdapter() {
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

    public void setEnable(boolean b){
        mSp.setEnabled(b);
    }

}
