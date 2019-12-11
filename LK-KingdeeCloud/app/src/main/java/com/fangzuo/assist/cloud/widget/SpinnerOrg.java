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

import com.fangzuo.assist.cloud.Adapter.OrgSpAdapter;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Dao.Org;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.Asynchttp;
import com.fangzuo.assist.cloud.Utils.BasicShareUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.GreedDaoUtil.GreenDaoManager;
import com.fangzuo.assist.cloud.Utils.JsonCreater;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.greendao.gen.DaoSession;
import com.fangzuo.greendao.gen.OrgDao;
import com.loopj.android.http.AsyncHttpClient;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

public class SpinnerOrg extends RelativeLayout {
    // 返回按钮控件
    private Spinner mSp;
    // 标题Tv
    private TextView mTitleTv;
    private boolean showEd = false;
    //    private SpinnerAdapter adapter;
    private DaoSession daoSession;
    private ArrayList<String> autoList;
    private BasicShareUtil share;
    private ArrayList<Org> container;
    private OrgSpAdapter adapter;
    private String autoString="";//用于联网时，再次去自动设置值
    private String saveKeyString="";//用于保存数据的key
    private String employeeId="";
    private String employeeName="";
    private String employeeNumber="";
    private String T="组织：";     //3


    public SpinnerOrg(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        LayoutInflater.from(context).inflate(R.layout.view_org_spinner, this);
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
        adapter = new OrgSpAdapter(context, container);
        mSp.setAdapter(adapter);
        if (share.getIsOL()) {
            ArrayList<Integer> choose = new ArrayList<>();
            choose.add(14);
            String json = JsonCreater.DownLoadData(
                    share.getDatabaseIp(),
                    share.getDatabasePort(),
                    share.getDataBaseUser(),
                    share.getDataBasePass(),
                    share.getDataBase(),
                    share.getVersion(),
                    choose
            );
            Asynchttp.post(context, share.getBaseURL() + WebApi.DOWNLOADDATA, json, new Asynchttp.Response() {
                @Override
                public void onSucceed(CommonResponse cBean, AsyncHttpClient client) {
                    DownloadReturnBean dBean = JsonCreater.gson.fromJson(cBean.returnJson, DownloadReturnBean.class);
                    OrgDao payTypeDao = daoSession.getOrgDao();
                    payTypeDao.deleteAll();
                    payTypeDao.insertOrReplaceInTx(dBean.orgs);
                    payTypeDao.detachAll();
                    if (dBean.orgs.size() > 0 && container.size()<=0){
                        container.addAll(dBean.orgs);
                        adapter.notifyDataSetChanged();
                        setAutoSelection(saveKeyString,autoString);
                    }
                }

                @Override
                public void onFailed(String Msg, AsyncHttpClient client) {
//                    Toast.showText(context, Msg);
                }
            });
        }
//        else {
            OrgDao employeeDao = daoSession.getOrgDao();
            List<Org> employees = employeeDao.loadAll();
            container.addAll(employees);
            adapter.notifyDataSetChanged();
            setAutoSelection(saveKeyString,autoString);

//            Log.e("CommonMethod", "获取到本地数据：\n" + container.toString());
//        }


        mSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Org employee = (Org) adapter.getItem(i);
                employeeId = employee.FOrgID;
                employeeName = employee.FName;
                employeeNumber = employee.FNumber;
                Lg.e("选中"+T+employee.toString());
                Hawk.put(saveKeyString,employee.FName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    //自动设置保存的值

    public OrgSpAdapter getSpAdapter() {
        if (adapter.getCount() < 0) {
            Lg.e("adapter初始化失败，重新更新adapter");
            OrgDao employeeDao = daoSession.getOrgDao();
            List<Org> employees = employeeDao.loadAll();
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

    public void setEnable(boolean b){
        mSp.setEnabled(b);
    }

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
        if ("".equals(string) && !"".equals(saveKeyStr)){
            autoString = Hawk.get(saveKeyString,"");
        }
        for (int j = 0; j < adapter.getCount(); j++) {
            if (((Org) adapter.getItem(j)).FNumber.equals(autoString)|| ((Org) adapter.getItem(j)).FOrgID.equals(autoString)
                    || ((Org) adapter.getItem(j)).FName.equals(autoString)) {
                mSp.setSelection(j);
//                autoString = null;
                break;
            }
        }
    }
    public void setAutoSelection(String saveKeyStr,String string,int activity,int type) {
        saveKeyString =saveKeyStr;
        autoString = string;
        if ("".equals(string) && !"".equals(saveKeyStr)){
            autoString = Hawk.get(saveKeyString,"");
        }
        if (checkFilter(activity)){
            if (type==0){//过滤条件：包含“事业部” 和“亿森”
                for(int i = container.size()-1;i >= 0;i--) {
                    Org org = container.get(i);
                    if(!org.FName.contains("事业部")&&!org.FName.contains("亿森")) {
//                        Lg.e("删除组织",org);
                        container.remove(org);
                    }else{
                        Lg.e("保留组织",org);
                    }
                }
            }
        }
//        adapter = new OrgSpAdapter(context, container);
        mSp.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        for (int j = 0; j < adapter.getCount(); j++) {
            if (((Org) adapter.getItem(j)).FNumber.equals(autoString)|| ((Org) adapter.getItem(j)).FOrgID.equals(autoString)
                    || ((Org) adapter.getItem(j)).FName.equals(autoString)) {
                mSp.setSelection(j);
//                autoString = null;
                break;
            }
        }
    }


    private boolean checkFilter(int activity){
        boolean should = false;
        switch (activity){
            case Config.CpWgInActivity:
            case Config.CpWgHunInActivity:
            case Config.Bg2CheJianInActivity:
            case Config.Bg2CheJianHunInActivity:
            case Config.Bg2CheJianDiGetActivity:
            case Config.Bg1CheJianInActivity:
            case Config.Bg1CheJianHunInActivity:
            case Config.Bg1CheJianDiGetActivity:
            case Config.ZbCheJianInActivity:
            case Config.ZbCheJianHunInActivity:
            case Config.ZbCheJianDiGetActivity:
            case Config.ZbCheJianDiZGetActivity:
            case Config.SplitBoxGetActivity:
            case Config.SplitBoxDiGetActivity:
            case Config.SplitBoxInActivity:
            case Config.ZbIn1Activity:
            case Config.ZbIn2Activity:
            case Config.ZbIn3Activity:
            case Config.ZbIn4Activity:
            case Config.ZbIn5Activity:
            case Config.SplitBoxHunInActivity:
            case Config.ChangeModelGetActivity:
            case Config.ChangeModelInActivity:
            case Config.ChangeLvGetActivity:
            case Config.ChangeLvInActivity:
            case Config.ChangeGetActivity:
            case Config.ChangeInActivity:
            case Config.GbHunInActivity:
            case Config.GbInActivity:
            case Config.GbDiGetActivity:
            case Config.GbGetActivity:
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
            case Config.ZbGet1Activity:
            case Config.ZbGet2Activity:
            case Config.ZbGet3Activity:
            case Config.ZbGet4Activity:
            case Config.ZbGet5Activity:
            case Config.DhInActivity:
            case Config.DhIn2Activity:
            case Config.DBClientActivity:
            case Config.DBStorageActivity:
                should = true;
                break;
        }
        return should;
    }







    public OrgSpAdapter getAdapter() {
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
