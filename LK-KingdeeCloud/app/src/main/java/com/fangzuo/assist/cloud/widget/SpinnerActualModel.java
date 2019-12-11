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
import com.fangzuo.assist.cloud.Adapter.AuxSignSpAdapter;
import com.fangzuo.assist.cloud.Beans.AuxSignFirstCheckBean;
import com.fangzuo.assist.cloud.Beans.AuxSignSecCheckBean;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Utils.BasicShareUtil;
import com.fangzuo.assist.cloud.Utils.GreedDaoUtil.GreenDaoManager;
import com.fangzuo.assist.cloud.Utils.JsonCreater;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.greendao.gen.DaoSession;

import java.util.ArrayList;
import java.util.List;


public class SpinnerActualModel extends RelativeLayout {
    // 返回按钮控件
    private Spinner mSp;
    // 标题Tv
    private TextView mTitleTv;
    private static BasicShareUtil share;
    private String autoString;//用于联网时，再次去自动设置值
    private String autoOrg="";//用于联网时，再次去自动设置值
    private AuxSignSpAdapter adapter;
    private ArrayList<AuxSignSecCheckBean> list;
    private DaoSession daoSession;
    private String AuxSignID = "";
    private String AuxSignNumber = "";
    public static final String Name = "name";
    public static final String Id = "id";
    public static final String Number = "number";
    public static final String TGP = "SpinnerActualModel+";     //7
    private boolean canLoad=true;

    public SpinnerActualModel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        LayoutInflater.from(context).inflate(R.layout.view_my_unit_spinner, this);
        daoSession = GreenDaoManager.getmInstance(context).getDaoSession();
        list = new ArrayList<>();
        adapter = new AuxSignSpAdapter(context, list);
        share = BasicShareUtil.getInstance(context);
        // 获取控件
        mSp = (Spinner) findViewById(R.id.sp);
        mTitleTv = (TextView) findViewById(R.id.tv);
        TypedArray attrArray = context.obtainStyledAttributes(attributeSet, R.styleable.Style_Spinner_Unit);
        int count = attrArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attrName = attrArray.getIndex(i);
            switch (attrName) {
                case R.styleable.Style_Spinner_Unit_Uspinner_name:
                    mTitleTv.setText(attrArray.getString(R.styleable.Style_Spinner_Unit_Uspinner_name));
                    break;
                case R.styleable.Style_Spinner_Unit_Uspinner_title:
                    mSp.setPrompt(attrArray.getString(R.styleable.Style_Spinner_Unit_Uspinner_title));
                    break;
                case R.styleable.Style_Spinner_Unit_Uspinner_focusable:
                    mSp.setEnabled(attrArray.getBoolean(R.styleable.Style_Spinner_Unit_Uspinner_focusable, true));
                    break;
                case R.styleable.Style_Spinner_Unit_Uspinner_name_size:
                    mTitleTv.setText(attrArray.getString(R.styleable.Style_Spinner_Unit_Uspinner_name));
                    mTitleTv.setTextSize(attrArray.getDimension(R.styleable.Style_Spinner_Unit_Uspinner_name_size, 10));
                    break;
            }
        }
        attrArray.recycle();

        mSp.setAdapter(adapter);
        mSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                AuxSignSecCheckBean unit = (AuxSignSecCheckBean) adapter.getItem(i);
                if (list.size()>0){
                    AuxSignSecCheckBean unit = list.get(i);
                    AuxSignID = unit.FAUXPTYID;
                    AuxSignNumber = unit.FNUMBER;
                    Lg.e("控件内实际规格选择：",unit);
//                    Log.e("AuxSignID", AuxSignID + "");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    // 为左侧返回按钮添加自定义点击事件
    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
        mSp.setOnItemSelectedListener(listener);
    }

    public void setAdapter(AuxSignSpAdapter adapter) {
        mSp.setAdapter(adapter);
        this.adapter = adapter;
    }

    public void setEnabled(boolean b) {
        mSp.setEnabled(b);
    }

    public String getDataId() {
        return AuxSignID;
    }

    public String getDataNumber() {
        return AuxSignNumber;
    }

    public AuxSignSpAdapter getAdapter() {
        return adapter;
    }

    public void setSelection(int i) {
        mSp.setSelection(i);
    }

    // 设置标题的方法
    public void setTitleText(String title) {
        mTitleTv.setText(title);
    }

    //自动设置保存的值
    //type: 根据什么字段定位：number，id，name
    public void getData(final String productID,String autoStr) {
        if (!canLoad)return;
        canLoad=false;
        AuxSignID = "";
        AuxSignNumber = "";
        autoString = autoStr;
//        final String UnitGroupID;
//        Lg.e(TGP+"setAuto:" + autoStr);
//        if (null==unitGroupIDTemp || "".equals(unitGroupIDTemp)){
//            UnitGroupID = "";
//            Lg.e("单位组不存在");
//        }else{
//            UnitGroupID=unitGroupIDTemp;
//        }
//        LoadingUtil.dismiss();
//        LoadingUtil.show(context,"正在调整单位...");
//        autoString = autoStr;
//        final List<AuxSignSecCheckBean> listTemp =getLocData();
//        dealAuto(listTemp,type,false);
        App.getRService().doIOAction(WebApi.ActualModelFirstCheck, productID, new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);
                if (!commonResponse.state)return;
                AuxSignFirstCheckBean dBean = JsonCreater.gson.fromJson(commonResponse.returnJson, AuxSignFirstCheckBean.class);
                if (null!=dBean.FISENABLE){
                    list.clear();
                    if ("0".equals(dBean.FISENABLE)){
                        canLoad=true;
                        list.clear();
                        mSp.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }else{
                        getResultData(productID);
                    }
                }else{
                    canLoad=true;
                    list.clear();
                    mSp.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(Throwable e) {
                canLoad=true;
                super.onError(e);
            }
        });

//        if (share.getIsOL()) {
//            ArrayList<Integer> choose = new ArrayList<>();
//            choose.add(7);
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
//                    if (dBean != null && dBean.units != null && dBean.units.size() > 0) {
//                        UnitDao unitDao = daoSession.getUnitDao();
//                        unitDao.deleteAll();
//                        unitDao.insertOrReplaceInTx(dBean.units);
//                        unitDao.detachAll();
//                        if (dBean.units.size()>0 && list.size()<=0){
//                            dealAuto(dBean.units,type,true);
//                        }
//
//                    }
////                    LoadingUtil.dismiss();
//                }
//
//                @Override
//                public void onFailed(String Msg, AsyncHttpClient client) {
//
//                }
//            });
//        }

    }

    private void getResultData(String string){
        App.getRService().doIOAction(WebApi.ActualModelSecCheck, string, new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);
                DownloadReturnBean dBean = JsonCreater.gson.fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                if (null != dBean && dBean.auxSignSecCheckBeans!=null && dBean.auxSignSecCheckBeans.size()>0){
                    dealAuto(dBean.auxSignSecCheckBeans);
                }else{
                    canLoad=true;
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(Throwable e) {
                canLoad=true;
                super.onError(e);
            }
        });
    }
    private void dealAuto(List<AuxSignSecCheckBean> listData){
//        Lg.e("实际规格自动：",listData);
//        Lg.e("实际规格自动：",autoString);
        if (listData.size()>0){
            list.addAll(listData);
            mSp.setAdapter(adapter);
            if (!"".equals(autoString)){
                for (int i = 0; i < listData.size(); i++) {
                    if (listData.get(i).FNUMBER.equals(autoString)){
                        mSp.setSelection(i);
                    }
                }
                canLoad=true;
            }else{
                canLoad=true;
            }

        }else{
            canLoad=true;
            autoString="";
            mSp.setAdapter(adapter);
        }
        adapter.notifyDataSetChanged();
    }

    //自动设置保存的值
    //type: 根据什么字段定位：number，id，name
//    public void setAuto(String autoStr, Org org, final String type) {
//        AuxSignID = "";
//        AuxSignNumber = "";
//        Lg.e(TGP+"setAuto:" + autoStr);
//        autoString = autoStr;
//        autoOrg = org==null?"":org.FOrgID;
//        final List<Unit> listTemp =getLocData(autoOrg);
//        dealAuto(listTemp,type,false);
//
////        if (share.getIsOL()) {
//            ArrayList<Integer> choose = new ArrayList<>();
//            choose.add(7);
//            String json = JsonCreater.DownLoadData(
//                    share.getDatabaseIp(),
//                    share.getDatabasePort(),
//                    share.getDataBaseUser(),
//                    share.getDataBasePass(),
//                    share.getDataBase(),
//                    share.getVersion(),
//                    choose
//            );
//        App.getRService().downloadData(json, new MySubscribe<CommonResponse>() {
//            @Override
//            public void onNext(CommonResponse commonResponse) {
//                DownloadReturnBean dBean = JsonCreater.gson.fromJson(commonResponse.returnJson, DownloadReturnBean.class);
//                if (dBean != null && dBean.units != null && dBean.units.size() > 0) {
//                    UnitDao unitDao = daoSession.getUnitDao();
//                    unitDao.deleteAll();
//                    unitDao.insertOrReplaceInTx(dBean.units);
//                    unitDao.detachAll();
//                    if (dBean.units.size()>0 && list.size()<=0){
//                        dealAuto(dBean.units,type,true);
//                    }
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
////                    LoadingUtil.dismiss();
////                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Updata_Error,e.toString()));
//            }
//        });
////        }
//
//    }
//    private List<AuxSignSecCheckBean> getLocData(){
//        UnitDao unitDao = daoSession.getUnitDao();
////        return unitDao.queryBuilder().where(
////                UnitDao.Properties.FMeasureUnitID.eq(unitGroupID)
////        ).build().list();
//        return unitDao.loadAll();
//    }
//    private List<AuxSignSecCheckBean> getLocData(String org){
//        UnitDao unitDao = daoSession.getUnitDao();
//        return unitDao.queryBuilder().where(
//                UnitDao.Properties.FOrg.eq(org)
//        ).build().list();
////        return unitDao.loadAll();
//    }

//    private void dealAuto(List<Unit> listData,final String type,boolean check){
//        list.clear();
//        if (check){
//            for (int i = 0; i < listData.size(); i++) {
//                if (listData.get(i).FMeasureUnitID.equals(autoString)){
//                    list.add(listData.get(i));
//                }
//            }
//        }else{
//            list.addAll(listData);
//        }
//
//        if (list.size()>0){
//                mSp.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
//            if (list.size()==1){//当只有一个的时候，重新适配器，为了spinner的监听能响应
//                AuxSignID = list.get(0).FMeasureUnitID;
//                unitName=list.get(0).FName;
//                AuxSignNumber=list.get(0).FNumber;
//            }else {//过滤设定的值
//                if (null==autoString || "".equals(autoString) || "0".equals(autoString)) {
//                    AuxSignID = list.get(0).FMeasureUnitID;
//                    unitName=list.get(0).FName;
//                    AuxSignNumber=list.get(0).FNumber;
//                    adapter.notifyDataSetChanged();
//                } else {
//                    if (Number.equals(type)) {
//                        for (int j = 0; j < list.size(); j++) {
//                            if (list.get(j).FNumber.equals(autoString)) {
//                                Lg.e("单位定位（自定义控件：" + autoString);
//                                AuxSignID = list.get(j).FMeasureUnitID;
//                                unitName=list.get(j).FName;
//                                AuxSignNumber=list.get(j).FNumber;
//                                mSp.setSelection(j);
//                                break;
//                            }
//                        }
//                    } else if (Name.equals(type)) {
//                        for (int j = 0; j < list.size(); j++) {
//                            if (list.get(j).FName.equals(autoString)) {
//                                Lg.e("单位定位（自定义控件：" + autoString);
//                                AuxSignID = list.get(j).FMeasureUnitID;
//                                unitName=list.get(j).FName;
//                                AuxSignNumber=list.get(j).FNumber;
//                                mSp.setSelection(j);
//                                break;
//                            }
//                        }
//                    } else if (Id.equals(type)) {
//                        for (int j = 0; j < list.size(); j++) {
//                            if (list.get(j).FMeasureUnitID.equals(autoString)) {
//                                Lg.e("单位定位（自定义控件：" + autoString);
//                                AuxSignID = list.get(j).FMeasureUnitID;
//                                unitName=list.get(j).FName;
//                                AuxSignNumber=list.get(j).FNumber;
//                                mSp.setSelection(j);
//                                break;
//                            }
//                        }
//                    }
//                    if ("".equals(AuxSignID) && "".equals(unitName)){
//                        AuxSignID = list.get(0).FMeasureUnitID;
//                        unitName=list.get(0).FName;
//                    }
//                    adapter.notifyDataSetChanged();
//                }
//            }
//        }else{
//            adapter.notifyDataSetChanged();
//        }
//
//    }

}
