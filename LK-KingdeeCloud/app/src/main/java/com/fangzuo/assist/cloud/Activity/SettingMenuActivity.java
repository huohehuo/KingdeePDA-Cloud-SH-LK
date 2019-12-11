package com.fangzuo.assist.cloud.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Utils.CommonUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.fangzuo.assist.cloud.zxing.CustomCaptureActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.orhanobut.hawk.Hawk;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingMenuActivity extends BaseActivity {
    @BindView(R.id.btn_back)
    RelativeLayout btnBack;
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.tv_ver_app)
    TextView tvVerApp;
    @BindView(R.id.tv_ver_web)
    TextView tvVerWeb;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.ll_update)
    LinearLayout llUpdate;


//    @BindView(R.id.lv_setting)
//    ListView lvSetting;
//    private ProgressDialog pDialog;
    @Override
    protected boolean isRegisterEventBus() {
    return true;
}
    @Override
    protected void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.ScanResult://
                BarcodeResult res = (BarcodeResult) event.postEvent;
                OnReceive(res.getResult().getText());
                break;
        }
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_setting_menu);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.set);
//        if (!"".equals(Hawk.get(Config.Apk_Version, ""))) {
//            if (Double.parseDouble(Hawk.get(Config.Apk_Version, "0")) > Double.parseDouble(Info.getAppNo())) {
//                tvVerApp.setTextColor(Color.RED);
//                tvVersion.setTextColor(Color.RED);
//                tvVersion.setText(R.string.new_version);
//                tvVerApp.setText("new app:" + Hawk.get(Config.Apk_Version, "0"));
//            } else {
//                tvVerApp.setTextColor(Color.BLACK);
//                tvVersion.setTextColor(Color.BLACK);
                tvVerApp.setText("app:" + Info.getAppNo());
//            }
//        }
        App.getRService().doIOAction(WebApi.ServiceVersion, "", new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
//                super.onNext(commonResponse);
                tvVerWeb.setText("web service:" + commonResponse.returnJson);
            }

            @Override
            public void onError(Throwable e) {
                tvVerWeb.setText("web service:服务器未响应");
//                super.onError(e);
            }
        });
    }

    @Override
    protected void initListener() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        llUpdate.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle(R.string.if_down_update_file)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DownLoad(Config.getApk_Url());
                            }
                        })
                        .create().show();
                return true;
            }
        });

    }

    @Override
    protected void OnReceive(String code) {
        if (code.contains("fangzuokeji^")){
            String url = code.replace("fangzuokeji^","");
            DownLoad(url);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @OnClick({R.id.ll_service, R.id.ll_download, R.id.ll_test, R.id.ll_wifi, R.id.ll_voice, R.id.ll_printest, R.id.ll_update, R.id.ll_language})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_service:
                startNewActivity(IpPortActivity.class, 0, 0, false, null);
                break;
            case R.id.ll_download:
                startNewActivity(SettingActivity.class, 0, 0, false, null);
                break;
            case R.id.ll_test:
                startNewActivity(TestingActivity.class, 0, 0, false, null);
                break;
            case R.id.ll_wifi:
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                break;
            case R.id.ll_voice:
                startActivity(new Intent(Settings.ACTION_SOUND_SETTINGS));
                break;
            case R.id.ll_printest:
                startNewActivity(PrintOutTestActivity.class, 0, 0, false, null);
                break;
            case R.id.ll_update:
                IntentIntegrator intentIntegrator = new IntentIntegrator(SettingMenuActivity.this);
                // 设置自定义扫描Activity
                intentIntegrator.setCaptureActivity(CustomCaptureActivity.class);
                intentIntegrator.initiateScan();
                break;
            case R.id.ll_language:
//                LoadingUtil.showAlter(mContext, "功能暂未开放");
                        startActivity(new Intent(mContext, LanguageActivity.class));
                break;
        }
    }

    private ProgressDialog pDialog;
    private void DownLoad(String downLoadURL) {
        LoadingUtil.dismiss();
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {

            pDialog = new ProgressDialog(mContext);
            pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pDialog.setTitle(getString(R.string.downing));
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();
            String target = Environment.getExternalStorageDirectory()
                    + "/NewApp"+getTimeLong(false)+".apk";
            HttpUtils utils = new HttpUtils();
            utils.download(downLoadURL, target, new RequestCallBack<File>() {
                @Override
                public void onLoading(long total, long current,
                                      boolean isUploading) {
                    super.onLoading(total, current, isUploading);
                    System.out.println("下载进度:" + current + "/" + total);
                    pDialog.setProgress((int) (current*100/total));
                }

                @Override
                public void onSuccess(ResponseInfo<File> arg0) {
                    pDialog.dismiss();
                    Lg.e("下载的文件数据："+arg0.toString());
                    Lg.e("下载的文件数据："+arg0.result);
                    System.out.println("下载完成"+arg0.result);
                    try{
                        CommonUtil.installApk(mContext,arg0.result+"");
//                        Intent intent = new Intent(Intent.ACTION_VIEW);
//                        intent.addCategory(Intent.CATEGORY_DEFAULT);
//                        intent.setDataAndType(Uri.fromFile(arg0.result),
//                                "application/vnd.android.package-archive");
//                        startActivityForResult(intent, 0);
                    }catch (Exception e){
                        try{
                            StringBuilder builder = new StringBuilder();
                            builder.append("请先退出本软件\n");
                            builder.append("进入PDA软件主页\n");
                            builder.append("选择文件管理器\n");
                            builder.append("找到文件NewApp\n");
                            builder.append("长按变色点击右下角重命名\n删去后面的数字\n");
                            builder.append("变成文件名：NewApp.apk\n");
                            builder.append("点击安装\n");
                            AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                            ab.setTitle("下载成功!\n请按操作重新安装APK");
                            ab.setMessage(builder.toString());
                            ab.setPositiveButton("确定", null);
                            ab.create().show();
                        }catch (Exception e1){

                        }
                    }

                }

                @Override
                public void onFailure(HttpException arg0, String arg1) {
                    pDialog.dismiss();
                    Toast.showText(mContext, getString(R.string.down_fail));
                }
            });
        } else {
            pDialog.dismiss();
            Toast.showText(mContext, "正在安装");

        }
    }
}
