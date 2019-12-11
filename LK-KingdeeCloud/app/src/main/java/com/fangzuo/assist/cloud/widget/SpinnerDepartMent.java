package com.fangzuo.assist.cloud.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Activity.ProductGetActivity;
import com.fangzuo.assist.cloud.Activity.ProductInStoreActivity;
import com.fangzuo.assist.cloud.Adapter.DepartmentSpAdapter;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Dao.Department;
import com.fangzuo.assist.cloud.Dao.Org;
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

public class SpinnerDepartMent extends RelativeLayout {
    // 返回按钮控件
    private Spinner mSp;
    // 标题Tv
    private TextView mTitleTv;
    private boolean showEd = false;
    //    private SpinnerAdapter adapter;
    private DaoSession daoSession;
    private ArrayList<String> autoList;
    private BasicShareUtil share;
    private ArrayList<Department> container;
    private DepartmentSpAdapter adapter;
    private String autoString="";//用于联网时，再次去自动设置值
    private String autoOrg="";//用于联网时，再次去自动设置值
    private String saveKeyString="";//用于保存数据的key
    private String Id="";
    private String Name="";
    private String Number="";
    private Department Obj;
    private String T="部门：";     //2
    private Activity activity;
    private int activityTag=0;

    public SpinnerDepartMent(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        LayoutInflater.from(context).inflate(R.layout.view_my_people_spinner, this);
        daoSession = GreenDaoManager.getmInstance(context).getDaoSession();

        autoList = new ArrayList<>();
        share = BasicShareUtil.getInstance(context);
        container = new ArrayList<>();
        activity = findActivity(context);


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
        adapter = new DepartmentSpAdapter(context, container);
        mSp.setAdapter(adapter);
//        if (share.getIsOL()) {
//            ArrayList<Integer> choose = new ArrayList<>();
//            choose.add(2);
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
//                    DepartmentDao yuandanTypeDao = daoSession.getDepartmentDao();
//                    yuandanTypeDao.deleteAll();
//                    yuandanTypeDao.insertOrReplaceInTx(dBean.department);
//                    yuandanTypeDao.detachAll();
//                    if (dBean.department.size() > 0 && container.size()<=0){
//                        if (activity instanceof ProductInStoreActivity || activity instanceof ProductGetActivity){
//                            for (int i = 0; i < dBean.department.size(); i++) {
//                                if ("1".equals(dBean.department.get(i).FISSTOCK)){
//                                    container.add(dBean.department.get(i));
//                                }
//                            }
//                        }else{
//                            container.addAll(dBean.department);
//                        }
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
//        DepartmentDao employeeDao = daoSession.getDepartmentDao();
//        List<Department> employees;
//        if (activity instanceof ProductInStoreActivity || activity instanceof ProductGetActivity){
//            Lg.e("属于：ProductInStoreActivity");
//            employees= employeeDao.queryBuilder().where(DepartmentDao.Properties.FISSTOCK.eq("1")).build().list();
//        }else{
//           employees = employeeDao.loadAll();
//
//        }
//            container.addAll(employees);
//            adapter.notifyDataSetChanged();
////            if (autoString != null) {
//                setAutoSelection(saveKeyString,autoString);
//            }
//            Log.e("CommonMethod", "获取到本地数据：\n" + container.toString());
//        }


        mSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Department employee = (Department) adapter.getItem(i);
                Obj = employee;
                Id = employee.FItemID;
                Name = employee.FName;
                Number = employee.FNumber;
                Lg.e("选中"+T,employee);
                Hawk.put(saveKeyString,employee.FName);
                mTitleTv.setText(employee.FName);
//                share.setPOEmployee(i);
//                if (isFirst6){
//                    share.setPOEmployee(i);
//                    spEmployee.setSelection(i);
//                }
//                else{
//                    spEmployee.setSelection(share.getPOEmployee());
//                    isFirst6=true;
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mTitleTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mSp.performClick();
            }
        });

    }
    public void setEnable(boolean b){
        mTitleTv.setClickable(b);
        mSp.setEnabled(b);
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
        return Id == null ? "" : Id;
    }

    public String getDataName() {
        return Name == null ? "" : Name;
    }

    public String getDataNumber() {
        return Number == null ? "" : Number;
    }
    public Department getDataNumberObj() {
        return Obj;
    }

    /**
     *
     * @param saveKeyStr        用于保存的key
     * @param string            自动设置的z值  弃用
     * */
    public void setAutoSelection(String saveKeyStr,String string) {
        saveKeyString =saveKeyStr;
        autoString = string;
        Lg.e("自动"+T+autoString);
        if ("".equals(string)){
            autoString = Hawk.get(saveKeyString,"");
        }
        for (int j = 0; j < adapter.getCount(); j++) {
            if (((Department) adapter.getItem(j)).FName.equals(autoString)
                    || ((Department) adapter.getItem(j)).FItemID.equals(autoString)) {
                mSp.setSelection(j);
//                autoString = null;
                break;
            }
        }
    }

//    private int type=1000;
//    public void setAuto(String saveKeyStr, String autoStr, Org org, int activityT,int filtertype) {
//        type=filtertype;
//        setAuto(saveKeyStr,autoStr,org,activityT);
//    }
    public void setAuto(String saveKeyStr, String autoStr, Org org, int activityT) {
        Lg.e("部门过滤",autoStr);
        Id="";
        Name="";
        Number="";
        saveKeyString =saveKeyStr;
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
                Lg.e("下载的部门总数",dBean.department.size());
                if (dBean.department.size() > 0 && container.size()<=0){
                    DepartmentDao yuandanTypeDao = daoSession.getDepartmentDao();
                    yuandanTypeDao.deleteAll();
                    yuandanTypeDao.insertOrReplaceInTx(dBean.department);
                    yuandanTypeDao.detachAll();
                    dealAuto(dBean.department,true);
//                    if (activity instanceof ProductInStoreActivity || activity instanceof ProductGetActivity){
//                        for (int i = 0; i < dBean.department.size(); i++) {
//                            if ("1".equals(dBean.department.get(i).FISSTOCK)){
//                                container.add(dBean.department.get(i));
//                            }
//                        }
//                    }else{
//                        container.addAll(dBean.department);
//                    }
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
        List<Department> employees;
        Lg.e("部门所属组织",org);
        if (activity instanceof ProductInStoreActivity || activity instanceof ProductGetActivity){
            Lg.e("属于：ProductInStoreActivity过滤："+org);
            Lg.e("过滤",org);
            employees= employeeDao.queryBuilder().where(
                    DepartmentDao.Properties.FISSTOCK.eq("1"),
                    DepartmentDao.Properties.FOrg.eq(org)
            ).build().list();
        }else{
            employees= employeeDao.queryBuilder().where(
                    DepartmentDao.Properties.FOrg.eq(org)
            ).build().list();
//            employees = employeeDao.loadAll();
        }
        Lg.e(T,employees.size());
        return employees;
    }

    private void dealAuto(List<Department> listData, boolean check) {
        container.clear();
        Lg.e("得到部门"+listData.size()+check,listData);
        if (check) {
            //当为生产单据时，过滤
            if (checkIfInOut(activityTag)) {
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
        //处理不同单据的再过滤条件
        dealFilter();

        Lg.e("sp过滤后部门"+container.size(),container);
        if ("".equals(autoString)){
            autoString = Hawk.get(saveKeyString,"");
        }
        if (container.size() > 0) {
            mSp.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            for (int j = 0; j < container.size(); j++) {
                if (container.get(j).FName.equals(autoString)||
                        container.get(j).FItemID.equals(autoString) ||
                        container.get(j).FNumber.equals(autoString)) {
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
//    public void setTitleText(String title) {
//        mTitleTv.setText(title);
//    }

    @Nullable
    public static Activity findActivity(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            ContextWrapper wrapper = (ContextWrapper) context;
            return findActivity(wrapper.getBaseContext());
        } else {
            return null;
        }
    }
    //是否是出入库单据的过滤条件
    private boolean checkIfInOut(int activityTag){
        boolean isInOut=false;
        switch (activityTag){
            case Config.ProductInStoreActivity:
            case Config.ProductGetActivity:
            case Config.ProductGet4P24PihaoActivity:
            case Config.ProductGet4P2Activity:
            case Config.P1PdProductGet2CprkActivity:
            case Config.ChangeInActivity:
            case Config.TbGetActivity:
            case Config.TbGet2Activity:
            case Config.ProductGet4BoxP2Activity:
            case Config.Tb1DiGetActivity:
            case Config.Tb2HunInActivity:
            case Config.GbHunInActivity:
            case Config.Tb2DiGetActivity:
            case Config.Tb3HunInActivity:
            case Config.ZbCheJianDiZGetActivity:
            case Config.Tb3DiGetActivity:
            case Config.GbDiGetActivity:
            case Config.BoxReAddP2Activity:
            case Config.ZbCheJianDiGetActivity:
            case Config.Bg1CheJianHunInActivity:
            case Config.Bg1CheJianDiGetActivity:
            case Config.Bg1CheJianInActivity:
            case Config.Bg2CheJianHunInActivity:
            case Config.Bg2CheJianDiGetActivity:
            case Config.ProductInStore4P2Activity:
            case Config.BoxReAddP1Activity:
            case Config.ChangeGetActivity:
            case Config.TbGet3Activity:
            case Config.TbInActivity :
            case Config.ZbIn1Activity :
            case Config.ZbIn2Activity :
            case Config.ZbIn3Activity :
            case Config.ZbIn4Activity :
            case Config.ZbIn5Activity :
            case Config.WgDryingInStoreActivity:
            case Config.DryingInStoreActivity:
            case Config.P1PdProductGet2Cprk2Activity:
            case Config.TbIn2Activity :
            case Config.TbIn3Activity :
            case Config.ShuiBanGetActivity:
            case Config.ShuiBanGet2Activity:
            case Config.GbGetActivity :
            case Config.GbInActivity :
            case Config.DryingGetActivity:
            case Config.ProductGet4BoxActivity:
            case Config.P2ProductionInStoreActivity:
            case Config.P2ProductionInStore2Activity:
            case Config.WorkOrgIn4P2Activity:
            case Config.P2PdCgrk2ProductGetActivity:
            case Config.P1PdCgrk2ProductGetActivity:
            case Config.WortInStore4P2Activity:
            case Config.WorkOrgGet4P2Activity:
            case Config.DhInActivity:
            case Config.DhIn2Activity:
            case Config.ProductInStore4P2MpActivity:
            case Config.BoxReBoxP1Activity:
            case Config.OutKilnGetActivity:
            case Config.ChangeLvGetActivity:
            case Config.ChangeModelGetActivity:
            case Config.SplitBoxGetActivity:
            case Config.ZbGet1Activity:
            case Config.ZbGet2Activity:
            case Config.ZbGet3Activity:
            case Config.ZbGet4Activity:
            case Config.ZbGet5Activity:
            case Config.ChangeLvInActivity:
            case Config.ChangeModelInActivity:
            case Config.SplitBoxInActivity:
            case Config.ZbCheJianInActivity:
            case Config.ZbCheJianHunInActivity:
            case Config.Bg2CheJianInActivity:
            case Config.CpWgHunInActivity:
            case Config.CpWgInActivity:
            case Config.SplitBoxDiGetActivity:
            case Config.Tb1HunInActivity:
                isInOut = true;
                break;
        }

        return isInOut;
    }

    //过滤条件
    private void dealFilter(){
        switch (activityTag){
            case Config.CpWgInActivity:
            case Config.CpWgHunInActivity:
                for(int i = container.size()-1;i >= 0;i--) {
                    Department department = container.get(i);
                    if(!department.FName.contains("采购")) {
//                        Lg.e("删除车间",department);
                        container.remove(department);
                    }else{
                        Lg.e("保留车间",department);
                    }
                }
                break;
            case Config.Bg2CheJianDiGetActivity:
            case Config.Bg2CheJianHunInActivity:
            case Config.Bg2CheJianInActivity:
            case Config.Bg1CheJianDiGetActivity:
            case Config.Bg1CheJianHunInActivity:
            case Config.Bg1CheJianInActivity:
            case Config.ZbCheJianDiZGetActivity:
            case Config.ZbCheJianDiGetActivity:
            case Config.ZbCheJianHunInActivity:
            case Config.ZbCheJianInActivity:
                for(int i = container.size()-1;i >= 0;i--) {
                    Department department = container.get(i);
                    if(!department.FName.contains("车间")) {
//                        Lg.e("删除车间",department);
                        container.remove(department);
                    }else{
                        Lg.e("保留车间",department);
                    }
                }
                break;
            case Config.SplitBoxGetActivity:
            case Config.SplitBoxDiGetActivity:
            case Config.SplitBoxInActivity:
            case Config.SplitBoxHunInActivity:
                for(int i = container.size()-1;i >= 0;i--) {
                    Department department = container.get(i);
                    if(!department.FName.contains("理货")) {
//                        Lg.e("删除车间",department);
                        container.remove(department);
                    }else{
                        Lg.e("保留车间",department);
                    }
                }
                break;
            case Config.GbGetActivity:
            case Config.GbInActivity:
            case Config.GbDiGetActivity:
            case Config.GbHunInActivity:
                for(int i = container.size()-1;i >= 0;i--) {
                    Department department = container.get(i);
                    if(!department.FName.contains("改板")) {
//                        Lg.e("删除车间",department);
                        container.remove(department);
                    }else{
                        Lg.e("保留车间",department);
                    }
                }
                break;
            case Config.Tb3HunInActivity:
            case Config.Tb3DiGetActivity:
            case Config.TbIn3Activity:
            case Config.TbGet3Activity:
            case Config.Tb2HunInActivity:
            case Config.Tb2DiGetActivity:
            case Config.TbIn2Activity:
            case Config.TbGet2Activity:
            case Config.Tb1HunInActivity:
            case Config.Tb1DiGetActivity:
            case Config.TbInActivity:
            case Config.TbGetActivity:
                for(int i = container.size()-1;i >= 0;i--) {
                    Department department = container.get(i);
                    if(!department.FName.contains("挑板")) {
//                        Lg.e("删除车间",department);
                        container.remove(department);
                    }else{
                        Lg.e("保留车间",department);
                    }
                }
                break;
            case Config.ChangeModelInActivity:
            case Config.ChangeModelGetActivity:
                for(int i = container.size()-1;i >= 0;i--) {
                    Department department = container.get(i);
                    if(!department.FName.contains("规格调整")) {
//                        Lg.e("删除车间",department);
                        container.remove(department);
                    }else{
                        Lg.e("保留车间",department);
                    }
                }
                break;
            case Config.ChangeLvGetActivity:
            case Config.ChangeLvInActivity:
                for(int i = container.size()-1;i >= 0;i--) {
                    Department department = container.get(i);
                    if(!department.FName.contains("等级调整")) {
//                        Lg.e("删除车间",department);
                        container.remove(department);
                    }else{
                        Lg.e("保留车间",department);
                    }
                }
                break;
            case Config.ChangeGetActivity:
            case Config.ChangeInActivity:
                for(int i = container.size()-1;i >= 0;i--) {
                    Department department = container.get(i);
                    if(!department.FName.contains("批号调整")) {
//                        Lg.e("删除车间",department);
                        container.remove(department);
                    }else{
                        Lg.e("保留车间",department);
                    }
                }
                break;
            case Config.DhInActivity:
            case Config.DhIn2Activity:
                for(int i = container.size()-1;i >= 0;i--) {
                    Department department = container.get(i);
                    if(!department.FName.contains("到货")) {
//                        Lg.e("删除车间",department);
                        container.remove(department);
                    }else{
                        Lg.e("保留车间",department);
                    }
                }
                break;

            default:
                //        adapter = new OrgSpAdapter(context, container);
                    mSp.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
        }
    }


}
