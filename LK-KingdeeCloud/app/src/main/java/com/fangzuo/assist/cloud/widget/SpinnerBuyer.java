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
import com.fangzuo.assist.cloud.Adapter.BuyerSpAdapter;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Dao.Buyer;
import com.fangzuo.assist.cloud.Dao.Org;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Utils.BasicShareUtil;
import com.fangzuo.assist.cloud.Utils.GreedDaoUtil.GreenDaoManager;
import com.fangzuo.assist.cloud.Utils.JsonCreater;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.greendao.gen.BuyerDao;
import com.fangzuo.greendao.gen.DaoSession;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

public class SpinnerBuyer extends RelativeLayout {
    // 返回按钮控件
    private Spinner mSp;
    // 标题Tv
    private TextView mTitleTv;
    private boolean showEd = false;
    //    private SpinnerAdapter adapter;
    private DaoSession daoSession;
    private ArrayList<String> autoList;
    private BasicShareUtil share;
    private ArrayList<Buyer> container;
    private BuyerSpAdapter adapter;
    private String autoString="";//用于联网时，再次去自动设置值
    private String autoOrg = "";//用于联网时，再次去自动设置值
    private String saveKeyString="";//用于保存数据的key
    private String employeeId="";
    private String employeeName="";
    private String employeeNumber="";
    private String T="采购员：";     //3


    public SpinnerBuyer(Context context, AttributeSet attributeSet) {
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
                case R.styleable.ManSpinner_manspinner_size:
                    mTitleTv.setText(attrArray.getString(R.styleable.ManSpinner_manspinner_name));
                    mTitleTv.setTextSize(attrArray.getDimension(R.styleable.ManSpinner_manspinner_size,15));
                    break;
            }
        }
        attrArray.recycle();
        adapter = new BuyerSpAdapter(context, container);
        mSp.setAdapter(adapter);
//        if (share.getIsOL()) {
//            ArrayList<Integer> choose = new ArrayList<>();
//            choose.add(1);
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
//                    Lg.e("得到buyer："+cBean.returnJson);
//                    DownloadReturnBean dBean = JsonCreater.gson.fromJson(cBean.returnJson, DownloadReturnBean.class);
////                    Lg.e("得到buyer：",dBean.buyers.get(0));
//                    BuyerDao payTypeDao = daoSession.getBuyerDao();
//                    payTypeDao.deleteAll();
//                    payTypeDao.insertOrReplaceInTx(dBean.buyers);
//                    payTypeDao.detachAll();
//                    if (dBean.buyers.size() > 0 && container.size()<=0){
//                        container.addAll(dBean.buyers);
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
//            BuyerDao employeeDao = daoSession.getBuyerDao();
//            List<Buyer> employees = employeeDao.loadAll();
//            container.addAll(employees);
//            adapter.notifyDataSetChanged();
//            setAutoSelection(saveKeyString,autoString);

//            Log.e("CommonMethod", "获取到本地数据：\n" + container.toString());
//        }


        mSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Buyer employee = (Buyer) adapter.getItem(i);
                employeeId = employee.FID;
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
    public BuyerSpAdapter getSpAdapter() {
        if (adapter.getCount() < 0) {
            Lg.e("adapter初始化失败，重新更新adapter");
            BuyerDao employeeDao = daoSession.getBuyerDao();
            List<Buyer> employees = employeeDao.loadAll();
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
            if (((Buyer) adapter.getItem(j)).FNumber.equals(autoString)
                    || ((Buyer) adapter.getItem(j)).FName.equals(autoString)) {
                mSp.setSelection(j);
//                autoString = null;
                break;
            }
        }
    }



    public void setAuto(String saveKeyStr,String autoStr, Org org) {
        employeeId = "";
        employeeName = "";
        employeeNumber = "";
        saveKeyString =saveKeyStr;
        autoString = autoStr;
        autoOrg = org==null?"":org.FOrgID;
        final List<Buyer> listTemp = getLocData(autoOrg);
        dealAuto(listTemp, false);

        ArrayList<Integer> choose = new ArrayList<>();
        choose.add(1);
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
                BuyerDao payTypeDao = daoSession.getBuyerDao();
                payTypeDao.deleteAll();
                payTypeDao.insertOrReplaceInTx(dBean.buyers);
                payTypeDao.detachAll();
                if (dBean.buyers.size() > 0 && container.size() <= 0) {
                    dealAuto(dBean.buyers,true);
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


    private List<Buyer> getLocData(String org) {
        BuyerDao employeeDao = daoSession.getBuyerDao();
        List<Buyer> employees = employeeDao.queryBuilder().where(BuyerDao.Properties.FOrg.eq(org)
        ).build().list();
        return employees;
    }

    private void dealAuto(List<Buyer> listData, boolean check) {
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
                employeeId = container.get(0).FID;
                employeeName = container.get(0).FName;
                employeeNumber = container.get(0).FNumber;
            } else {//过滤设定的值
                if (null == autoString || "".equals(autoString) || "0".equals(autoString)) {
                    employeeId = container.get(0).FID;
                    employeeName = container.get(0).FName;
                    employeeNumber = container.get(0).FNumber;
                    adapter.notifyDataSetChanged();
                } else {
                    for (int j = 0; j < container.size(); j++) {
                        if (container.get(j).FName.equals(autoString)) {
                            Lg.e("单位定位（自定义控件：" + autoString);
                            employeeId = container.get(j).FID;
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
//                                employeeId = container.get(j).FID;
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
//                                employeeId = container.get(j).FID;
//                                employeeName = container.get(j).FName;
//                                employeeNumber = container.get(j).FNumber;
//                                mSp.setSelection(j);
//                                break;
//                            }
//                        }
//                    } else if (Id.equals(type)) {
//                        for (int j = 0; j < container.size(); j++) {
//                            if (container.get(j).FID.equals(autoString)) {
//                                Lg.e("单位定位（自定义控件：" + autoString);
//                                employeeId = container.get(j).FID;
//                                employeeName = container.get(j).FName;
//                                employeeNumber = container.get(j).FNumber;
//                                mSp.setSelection(j);
//                                break;
//                            }
//                        }
//                    }
                    if ("".equals(employeeId) && "".equals(employeeName)) {
                        employeeId = container.get(0).FID;
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

    public BuyerSpAdapter getAdapter() {
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
