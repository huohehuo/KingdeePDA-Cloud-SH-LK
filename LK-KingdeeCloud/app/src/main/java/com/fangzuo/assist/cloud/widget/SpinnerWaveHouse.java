package com.fangzuo.assist.cloud.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Adapter.WaveHouseSpAdapter;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Dao.Storage;
import com.fangzuo.assist.cloud.Dao.WaveHouse;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Utils.BasicShareUtil;
import com.fangzuo.assist.cloud.Utils.GreedDaoUtil.GreenDaoManager;
import com.fangzuo.assist.cloud.Utils.JsonCreater;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.greendao.gen.DaoSession;
import com.fangzuo.greendao.gen.WaveHouseDao;

import java.util.ArrayList;
import java.util.List;


public class SpinnerWaveHouse extends RelativeLayout {
    public static String Number="number";
    public static String Name="name";
    public static String ID="id";
    // 返回按钮控件
    private Spinner mSp;
    // 标题Tv
    private TextView mTitleTv;
    private static BasicShareUtil share;
    private String autoString;//用于联网时，再次去自动设置值
    private WaveHouseSpAdapter waveHouseSpAdapter;
    private ArrayList<WaveHouse> waveHouses;
    private DaoSession daoSession;
    private String waveHouseId = "0";
    private String waveHouseName = "";
    private String waveHouseNumber = "";
    private WaveHouse waveHouseObject;//用于联网时，再次去自动设置值

    public SpinnerWaveHouse(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        LayoutInflater.from(context).inflate(R.layout.view_my_wave_spinner, this);
        daoSession = GreenDaoManager.getmInstance(context).getDaoSession();
        waveHouses = new ArrayList<>();
        waveHouseSpAdapter = new WaveHouseSpAdapter(context, waveHouses);
        share = BasicShareUtil.getInstance(context);
        // 获取控件
        mSp = (Spinner) findViewById(R.id.sp);
        mTitleTv = (TextView) findViewById(R.id.tv);
        TypedArray attrArray = context.obtainStyledAttributes(attributeSet, R.styleable.SpinnerWaveHouse);
        int count = attrArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attrName = attrArray.getIndex(i);
            switch (attrName) {
                case R.styleable.SpinnerWaveHouse_wspinner_name:
                    mTitleTv.setText(attrArray.getString(R.styleable.SpinnerWaveHouse_wspinner_name));
                    break;
                case R.styleable.SpinnerWaveHouse_wspinner_focusable:
                    mSp.setEnabled(attrArray.getBoolean(R.styleable.SpinnerWaveHouse_wspinner_focusable, true));
                    break;
                case R.styleable.SpinnerWaveHouse_wspinner_name_size:
                    mTitleTv.setText(attrArray.getString(R.styleable.SpinnerWaveHouse_wspinner_name));
                    mTitleTv.setTextSize(attrArray.getDimension(R.styleable.SpinnerWaveHouse_wspinner_name_size, 10));
                    break;
            }
        }
        attrArray.recycle();

        mSp.setAdapter(waveHouseSpAdapter);

//        mSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                WaveHouse waveHouse = (WaveHouse) waveHouseSpAdapter.getItem(i);
//                wavehouseID = waveHouse.FSPID;
//                wavehouseName = waveHouse.FName;
//                Log.e("wavehouseName", wavehouseName);
//                getInstorageNum(product);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

    }

    // 为左侧返回按钮添加自定义点击事件
    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
        mSp.setOnItemSelectedListener(listener);
    }

    public void setAdapter(WaveHouseSpAdapter adapter) {
        mSp.setAdapter(adapter);
        this.waveHouseSpAdapter = adapter;
    }

    public void setEnabled(boolean b) {
        mSp.setEnabled(b);
    }

    public String getWaveHouseId() {
        return waveHouseId;
    }
    public void clear() {
        waveHouseId = "0";
        waveHouseName = "";
        waveHouseNumber = "";
        waveHouseObject =null;
        waveHouses.clear();
        waveHouseSpAdapter.notifyDataSetChanged();
    }
    public String getWaveHouseName() {
        return waveHouseName;
    }

    public String getWaveHouseNumber() {
        return waveHouseNumber;
    }
    public WaveHouse getwaveHouseObject(){
        return waveHouseObject==null?new WaveHouse("0","","","","",""):waveHouseObject;
    }
    public WaveHouseSpAdapter getAdapter() {
        return waveHouseSpAdapter;
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
    public void setAuto(final Context context, final Storage storage, String autoStr, final String type) {
        waveHouseId = "0";
        waveHouseName = "";
        waveHouseNumber = "";
        waveHouseObject =null;
        if (storage == null) {
            return;
        }
        Lg.e("setAuto:" + autoStr);
//        LoadingUtil.dismiss();
        LoadingUtil.show(context, "请稍等...");
        autoString = autoStr;
        List<WaveHouse> list = getLocData(storage);
        dealAuto(list,storage, type,false);

        if (share.getIsOL()) {
            Log.e("CommonMethod:", "getWaveHouseAdapter联网");
            ArrayList<Integer> choose = new ArrayList<>();
            choose.add(4);
            String json = JsonCreater.DownLoadData(
                    share.getDatabaseIp(),
                    share.getDatabasePort(),
                    share.getDataBaseUser(),
                    share.getDataBasePass(),
                    share.getDataBase(),
                    share.getVersion(),
                    choose
            );
            App.getRService().doIOAction(WebApi.DOWNLOADDATA, json, new MySubscribe<CommonResponse>() {
                @Override
                public void onNext(CommonResponse cBean) {
                    super.onNext(cBean);
//                    Log.e("CommonMethod:","getWaveHouseAdapter获得数据：\n"+cBean.returnJson);
                    DownloadReturnBean dBean = JsonCreater.gson.fromJson(cBean.returnJson, DownloadReturnBean.class);
                    WaveHouseDao wavehouseDao = daoSession.getWaveHouseDao();
                    wavehouseDao.deleteAll();
                    wavehouseDao.insertOrReplaceInTx(dBean.wavehouse);
                    wavehouseDao.detachAll();
                    if (dBean.wavehouse.size()>0 && waveHouses.size() <= 0) {
                        waveHouses.addAll(dBean.wavehouse);
                        dealAuto(waveHouses,storage, type,true);
                    }
                }

                @Override
                public void onError(Throwable e) {
//                    super.onError(e);
                }
            });

        }
    }

    //根据仓库，过滤出仓位
    private List<WaveHouse> getLocData(Storage storage) {
        WaveHouseDao wavehousedao = daoSession.getWaveHouseDao();
        return wavehousedao.loadAll();
//        return wavehousedao.queryBuilder().where(
//                WaveHouseDao.Properties.FSPID.eq(storage.FToWaveHouse)
//        ).build().list();
    }

    //匹配自动值
    private void dealAuto(List<WaveHouse> list, Storage storage, final String type, boolean check) {
        waveHouses.clear();
        if (check) {//针对网络获取时，过滤
//            for (int i = 0; i < list.size(); i++) {
//                if (storage.FToWaveHouse.equals(list.get(i).FSPID)) {
//                    waveHouses.add(list.get(i));
//                }
//            }
            waveHouses.addAll(list);
        }else{
            waveHouses.addAll(list);
        }
        if (waveHouses.size() > 0) {
            mSp.setAdapter(waveHouseSpAdapter);
            waveHouseSpAdapter.notifyDataSetChanged();
            if (waveHouses.size()==1){
                waveHouseId = list.get(0).FSPID;
                waveHouseName = list.get(0).FName;
                waveHouseNumber = list.get(0).FNumber;
                waveHouseObject =list.get(0);
            }else{
                if (null==autoString || "".equals(autoString) || "0".equals(autoString)) {
                    waveHouseId = list.get(0).FSPID;
                    waveHouseName = list.get(0).FName;
                    waveHouseNumber = list.get(0).FNumber;
                    waveHouseObject =list.get(0);
                    LoadingUtil.dismiss();
//                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.check_Storage_waveHouse, autoString));
                } else {
                    if (Number.equals(type)) {
                        for (int j = 0; j < waveHouses.size(); j++) {
                            if (waveHouses.get(j).FNumber.equals(autoString)) {
                                Lg.e("仓位定位（自定义控件number：" + autoString);
                                waveHouseId=waveHouses.get(j).FSPID;
                                waveHouseName = waveHouses.get(j).FName;
                                waveHouseNumber = waveHouses.get(j).FNumber;
                                waveHouseObject =waveHouses.get(j);
                                mSp.setSelection(j);
                                break;
                            }
                        }
//                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.check_Storage_waveHouse, ""));
                    } else if (ID.equals(type)) {
                        for (int j = 0; j < waveHouses.size(); j++) {
                            if (waveHouses.get(j).FSPID.equals(autoString)) {
                                Lg.e("仓位定位（自定义控件id：" + autoString);
                                waveHouseId = autoString;
                                waveHouseName = waveHouses.get(j).FName;
                                waveHouseNumber = waveHouses.get(j).FNumber;
                                waveHouseObject =waveHouses.get(j);
                                mSp.setSelection(j);
                                break;
                            }
                        }

                    } else if (Name.equals(type)) {
                        for (int j = 0; j < waveHouses.size(); j++) {
                            if (waveHouses.get(j).FName.equals(autoString)) {
                                Lg.e("仓位定位（自定义控件name：" + autoString);
                                waveHouseId=waveHouses.get(j).FSPID;
                                waveHouseName = waveHouses.get(j).FName;
                                waveHouseNumber = waveHouses.get(j).FNumber;
                                waveHouseObject =waveHouses.get(j);
                                mSp.setSelection(j);
                                break;
                            }
                        }
                    }
                }
            }

            LoadingUtil.dismiss();
            waveHouseSpAdapter.notifyDataSetChanged();
        } else {
            LoadingUtil.dismiss();
            waveHouseSpAdapter.notifyDataSetChanged();
        }
    }

}
