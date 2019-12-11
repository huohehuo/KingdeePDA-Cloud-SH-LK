package com.fangzuo.assist.cloud.MVContract.view;


import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.MVContract.base.IView;

public interface TestView extends IView {

    void getBackData(CommonResponse commonResponse, String type);
    void showError(String error, String type);
}
