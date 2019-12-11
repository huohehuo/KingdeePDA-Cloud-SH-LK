package com.fangzuo.assist.cloud.MVContract.view;


import com.fangzuo.assist.cloud.MVContract.base.IView;

public interface DataView extends IView {

    void getBackData(Object object, int type);
    void showError(String error, int type);
}
