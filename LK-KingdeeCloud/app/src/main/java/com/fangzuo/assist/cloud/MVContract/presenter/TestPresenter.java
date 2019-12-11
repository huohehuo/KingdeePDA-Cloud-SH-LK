package com.fangzuo.assist.cloud.MVContract.presenter;

import android.content.Context;

import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.MVContract.base.IPresenter;
import com.fangzuo.assist.cloud.MVContract.view.TestView;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Utils.Config;


public class TestPresenter implements IPresenter {

    private Context mContext;
    private TestView testView;
    public TestPresenter(TestView testView){
        this.testView = testView;
    }

    public void getTest(String json){
        App.getRService().getTest("", new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                testView.getBackData(commonResponse, Config.IO_type_Test);
            }
            @Override
            public void onError(Throwable e) {
                testView.showError(e.toString(), Config.IO_type_Test);
            }
        });
    }

    public void getTestDataBase(String json){
        App.getRService().getTestDataBase("", new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                testView.getBackData(commonResponse, Config.IO_type_TestDataBase);
            }
            @Override
            public void onError(Throwable e) {
                testView.showError(e.toString(), Config.IO_type_TestDataBase);
            }
        });

    }

    public void SetProp(String json){
        App.getRService().SetProp(json, new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                testView.getBackData(commonResponse, Config.IO_type_SetProp);
            }
            @Override
            public void onError(Throwable e) {
                testView.showError(e.toString(), Config.IO_type_SetProp);
            }
        });

    }

    public void connectToSQL(String json){
        App.getRService().connectToSQL(json, new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                testView.getBackData(commonResponse, Config.IO_type_connectToSQL);
            }
            @Override
            public void onError(Throwable e) {
                testView.showError(e.toString(), Config.IO_type_connectToSQL);
            }
        });

    }

    @Override
    public void onCreate() {

    }
}
