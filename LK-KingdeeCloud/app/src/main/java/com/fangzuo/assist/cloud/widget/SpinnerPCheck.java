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
import com.fangzuo.assist.cloud.Adapter.PCheckSpAdapter;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.ProductTreeBeanList;
import com.fangzuo.assist.cloud.Dao.Product;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Utils.JsonCreater;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.WebApi;

import java.util.ArrayList;
import java.util.List;

public class SpinnerPCheck extends RelativeLayout {
    // 返回按钮控件
    private Spinner mSp;
    // 标题Tv
    private TextView mTitleTv;
    private ArrayList<ProductTreeBeanList.ProductTreeBean> container;
    private PCheckSpAdapter adapter;
    private String employeeId="";
    private String employeeName="";
    private String employeeNumber="";
    private String autoString="";
    private String FPARENTID="";
    private String T="物料选择：";     //3


    public SpinnerPCheck(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        LayoutInflater.from(context).inflate(R.layout.view_pcheck_spinner, this);

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
        adapter = new PCheckSpAdapter(context, container);
        mSp.setAdapter(adapter);
        mSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ProductTreeBeanList.ProductTreeBean employee = (ProductTreeBeanList.ProductTreeBean) adapter.getItem(i);
                employeeId = employee.FID;
                employeeName = employee.FName;
                employeeNumber = employee.FNumber;
                FPARENTID = employee.FPARENTID;
                Lg.e("选中"+T,employee);
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
    public PCheckSpAdapter getAdapter() {
        return adapter;
    }
    public String getDataName() {
        return employeeName == null ? "" : employeeName;
    }
    public String getDataNumber() {
        return employeeNumber == null ? "" : employeeNumber;
    }
    /**
     *
     * @param fid       递归查询的值，0时为所有
     * */
    public void setAutoSelection(String fid,String auto) {
        autoString=auto;
        if (null==fid)return;
        if ("".equals(fid))return;
//        container.clear();
        App.getRService().doIOAction(WebApi.ProductTreeGet,fid, new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);
                if (!commonResponse.state)return;
                ProductTreeBeanList dBean = JsonCreater.gson.fromJson(commonResponse.returnJson, ProductTreeBeanList.class);
                if (dBean != null && dBean.treeBeans != null && dBean.treeBeans.size() > 0) {
                        dealAuto(dBean.treeBeans);
                }
            }

            @Override
            public void onError(Throwable e) {
//                super.onError(e);
//                    LoadingUtil.dismiss();
//                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Updata_Error,e.toString()));
            }
        });

    }
    private void dealAuto(List<ProductTreeBeanList.ProductTreeBean> listData){
        container.clear();
        employeeId =        "";
        employeeName =      "";
        employeeNumber =    "";
        FPARENTID =    "";
        container.add(new ProductTreeBeanList().new ProductTreeBean("","","",""));
        container.addAll(listData);
        mSp.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if (container.size()>0){
            for (int j = 0; j < container.size(); j++) {
                if (container.get(j).FNumber.equals(autoString)) {
                    mSp.setSelection(j);
                    break;
                }
            }
        }
    }
    //二期逻辑,自动设置第一位数据
    public void setAutoSelection(String fid,String auto,String at) {
        autoString=auto;
        if (null==fid)return;
        if ("".equals(fid))return;
//        container.clear();
        App.getRService().doIOAction(WebApi.ProductTreeGet,fid, new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);
                if (!commonResponse.state)return;
                ProductTreeBeanList dBean = JsonCreater.gson.fromJson(commonResponse.returnJson, ProductTreeBeanList.class);
                if (dBean != null && dBean.treeBeans != null && dBean.treeBeans.size() > 0) {
                    dealAuto(dBean.treeBeans,"");
                }
            }

            @Override
            public void onError(Throwable e) {
//                super.onError(e);
//                    LoadingUtil.dismiss();
//                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Updata_Error,e.toString()));
            }
        });

    }
    //自动设置第一位
    private void dealAuto(List<ProductTreeBeanList.ProductTreeBean> listData,String at){
        container.clear();
        employeeId =        "";
        employeeName =      "";
        employeeNumber =    "";
        FPARENTID =    "";
//        container.add(new ProductTreeBeanList().new ProductTreeBean("","","",""));
        container.addAll(listData);
        mSp.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if (container.size()>0){
            for (int j = 0; j < container.size(); j++) {
                if (container.get(j).FNumber.equals(autoString)) {
                    mSp.setSelection(j);
                    break;
                }
            }
        }
    }


    public void addItems(List<Product> products){
        container.clear();
        for (Product bean:products) {
            ProductTreeBeanList.ProductTreeBean productTreeBean = new ProductTreeBeanList().new ProductTreeBean();
            if (bean.FModel.contains("*")){
                productTreeBean.FName=bean.FModel.substring(0,bean.FModel.indexOf("*"));
            }else{
                productTreeBean.FName=bean.FModel;
            }
            container.add(productTreeBean);
        }
        mSp.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    //清空
    public void clear() {
        container.clear();
        mSp.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }



















//     设置标题的方法
//    public void setTitleText(String title) {
//        mTitleTv.setText(title);
//    }

}
