package com.fangzuo.assist.cloud.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Beans.BackData;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Dao.PushDownMain;
import com.fangzuo.assist.cloud.Dao.PushDownSub;
import com.fangzuo.assist.cloud.Dao.T_main;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Service.DataService;
import com.fangzuo.assist.cloud.Utils.CommonUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.DataModel;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.JsonDealUtils;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.MediaPlayer;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.fangzuo.greendao.gen.PushDownMainDao;
import com.fangzuo.greendao.gen.PushDownSubDao;
import com.fangzuo.greendao.gen.T_DetailDao;
import com.fangzuo.greendao.gen.T_mainDao;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BackJsonActivity extends BaseActivity {

    @BindView(R.id.et_json)
    EditText etJson;
    @BindView(R.id.et_result)
    EditText etResult;
    @BindView(R.id.btn_upload)
    Button btnUpload;
    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    private List<String> listOrder;
    @Override
    protected void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.Upload_OK://回单成功
                BackData backData = (BackData) event.postEvent;
                if (backData.getResult().getResponseStatus().getIsSuccess()) {
                    listOrder = new ArrayList<>();
                    //获取生成的单号数据
                    for (int i = 0; i < backData.getResult().getResponseStatus().getSuccessEntitys().size(); i++) {
                        listOrder.add(backData.getResult().getResponseStatus().getSuccessEntitys().get(i).getNumber());
                    }
                    Lg.e("得到单号", listOrder);
                    Hawk.put("saveOrder",listOrder);
                    etResult.setText(gson.toJson(listOrder));
//                    final List<T_main> mains = t_mainDao.queryBuilder().where(
//                            T_mainDao.Properties.Activity.eq(activity),
//                            T_mainDao.Properties.FAccountID.eq(CommonUtil.getAccountID())
//                    ).build().list();
//                    for (int i = 0; i < mains.size(); i++) {
//                        final int pos = i;
//                        String reString = mains.get(i).FBillerID + "|" + listOrder.get(i) + "|" + mains.get(i).FOrderId + "|" + mains.get(i).IMIE;
//                        App.getRService().doIOAction(WebApi.OtherOutUpload, reString, new MySubscribe<CommonResponse>() {
//                            @Override
//                            public void onNext(CommonResponse commonResponse) {
//                                super.onNext(commonResponse);
////                                t_detailDao.deleteInTx(t_detailDao.queryBuilder().where(
////                                        T_DetailDao.Properties.Activity.eq(activity),
////                                        T_DetailDao.Properties.FAccountID.eq(CommonUtil.getAccountID()),
////                                        T_DetailDao.Properties.FOrderId.eq(mains.get(pos).FOrderId)
////                                ).build().list());
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                super.onError(e);
//                            }
//                        });
//                    }
                    Toast.showText(mContext, "上传成功");
//                btnBackorder.setClickable(true);
                    LoadingUtil.dismiss();


                } else {
                    LoadingUtil.dismiss();
                    List<BackData.ResultBean.ResponseStatusBean.ErrorsBean> errorsBeans = backData.getResult().getResponseStatus().getErrors();
                    StringBuilder builder = new StringBuilder();
                    for (BackData.ResultBean.ResponseStatusBean.ErrorsBean error : errorsBeans) {
                        builder.append(error.getFieldName() + "\n");
                        builder.append(error.getMessage() + "\n");
                    }
                    AlertDialog.Builder delete = new AlertDialog.Builder(this);
                    delete.setTitle("上传错误");
                    delete.setMessage(builder.toString());
                    delete.setPositiveButton("确定", null);
                    delete.setNegativeButton("反馈信息", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DataService.pushBackJson(mContext, this.getClass().getSimpleName(), Hawk.get(Config.Company, ""));
                        }
                    });
                    delete.create().show();
                }

                break;
            case EventBusInfoCode.Upload_Error://回单失败
                String error = (String) event.postEvent;
                Toast.showText(mContext, error);
//                btnBackorder.setClickable(true);
                LoadingUtil.dismiss();
                MediaPlayer.getInstance(mContext).error();
                break;
        }
    }
    @Override
    protected void initView() {
        setContentView(R.layout.activity_back_json);
        ButterKnife.bind(this);
        Lg.e("上次Order",Hawk.get("saveOrder",new ArrayList<String>()));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        btnUpload.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                new AlertDialog.Builder(mContext)
                        .setTitle("是否上传")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LoadingUtil.showDialog(mContext,"正在回单...");
                                DataModel.upload(Config.C_BatcnSave, etJson.getText().toString());
                            }
                        })
                        .create().show();
            }
        });
    }

    @Override
    protected void OnReceive(String code) {

    }

    @OnClick({R.id.et_json, R.id.btn_upload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_json:
                break;
            case R.id.btn_upload:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(mContext)
                .setTitle("是否退出")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .create().show();

    }

}
