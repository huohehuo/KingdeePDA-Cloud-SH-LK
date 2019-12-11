package com.fangzuo.assist.cloud.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.PrintHistory;
import com.fangzuo.assist.cloud.Beans.RegisterBean;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.orhanobut.hawk.Hawk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import zpSDK.zpSDK.zpBluetoothPrinter;

/**
 * 用于管理注册逻辑类
 */
public class RegisterUtil {

    //获取本机Mac地址
    public static String getNewMac(){
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
        return "";
    }

    //获取服务器最大注册数
    public static void getRegiterMaxNum(final String lastRegister){
        App.getRService().doIOAction(WebApi.RegisterGetNum, "", new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);
                if (!commonResponse.state)return;
                Hawk.put(Config.PDA_RegisterMaxNum,commonResponse.returnJson);
                doRegisterCheck(lastRegister);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Register_Result,App.getContext().getString(R.string.error_get_app_usernum)));
            }
        });
    }
    //执行注册逻辑
    //1:检查是否超过用户数
    //2：检查是否存在用户
    //3：不存在进行注册
    public static void doRegisterCheck(final String lastRegister){
        //查询出注册表包含的用户数量
        App.getRService().doIOAction(WebApi.RegisterNumber, "获取用户数number", new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);
                if (!commonResponse.state) return;
                Lg.e("注册信息数量：", commonResponse.returnJson);
                if (MathUtil.toInt(commonResponse.returnJson) < MathUtil.toInt(Hawk.get(Config.PDA_RegisterMaxNum,"5"))) {
                    Lg.e("符合用户注册最低数量,当前"+commonResponse.returnJson);
                    App.getRService().doIOAction(WebApi.RegisterCheck, lastRegister, new MySubscribe<CommonResponse>() {
                        @Override
                        public void onNext(CommonResponse commonResponse) {
                            super.onNext(commonResponse);
                            if (!commonResponse.state)return;
                            if (commonResponse.returnJson.equals("OK")){
                                Lg.e("存在注册码");
                                //存在注册码
                                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Register_Result,"OK"));
                            }else{
                                //不存在注册码，进行注册
                                Lg.e("不存在注册码");
                                App.getRService().doIOAction(WebApi.RegisterCode, lastRegister, new MySubscribe<CommonResponse>() {
                                    @Override
                                    public void onNext(CommonResponse commonResponse) {
                                        super.onNext(commonResponse);
                                        if (!commonResponse.state) return;//注册成功
                                        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Register_Result,"OK"));
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        super.onError(e);
                                        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Register_Result,App.getContext().getString(R.string.register_error)));
//                                        LoadingUtil.showAlter(WelcomeActivity.this, "提示", "注册失败");
                                    }
                                });
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
//                            super.onError(e);
                            EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Register_Result,App.getContext().getString(R.string.error_check_user)+e.getMessage()));
                        }
                    });

                } else {
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Register_Result,App.getContext().getString(R.string.error_app_max_num)+Hawk.get(Config.PDA_RegisterMaxNum,"5")));
//                    LoadingUtil.showAlter(WelcomeActivity.this, "提示", "软件使用数量已达上限");
                }
            }

            @Override
            public void onError(Throwable e) {
//                super.onError(e);
                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Register_Result,App.getContext().getString(R.string.error_check_usernum)+e.getMessage()));
//                Toast.showText(WelcomeActivity.this,"查询用户数错误："+e.getMessage());
            }
        });
    }

    //检查dbother.db文件是否存在注册码
    public static void checkHasRegister(){
        //检查是否存在注册码
        App.getRService().doIOAction(WebApi.RegisterCheck,  Hawk.get(Config.PDA_IMIE,""), new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
//                super.onNext(commonResponse);
                if (commonResponse.returnJson.equals("OK")){
                    Lg.e("存在注册码");
                    //存在注册码
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Register_Result,"OK"));
                }else{
                    Lg.e("不存在注册码");
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Register_Result,App.getContext().getString(R.string.app_not_register)));
                }
            }

            @Override
            public void onError(Throwable e) {
//                super.onError(e);
                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Register_Result,App.getContext().getString(R.string.error_check_register)));

            }
        });
    }

    //下载版本号数据文件
    public static void downLoadVersion() {
        LoadingUtil.dismiss();
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {

            String target = Environment.getExternalStorageDirectory()
                    + "/ScanAppVision"+".txt";
            HttpUtils utils = new HttpUtils();
            utils.download(Config.getApk_Version(), target, new RequestCallBack<File>() {
                @Override
                public void onLoading(long total, long current,
                                      boolean isUploading) {
                    super.onLoading(total, current, isUploading);
//                    System.out.println("下载进度:" + current + "/" + total);
//                    pDialog.setProgress((int) (current*100/total));
                }

                @Override
                public void onSuccess(ResponseInfo<File> arg0) {
//                    pDialog.dismiss();
                    Lg.e("下载版本文件成功");
                    Lg.e("下载的文件数据："+arg0.result);
                    CommonUtil.getString4Version();

                }

                @Override
                public void onFailure(com.lidroid.xutils.exception.HttpException arg0, String arg1) {
                    Lg.e("下载版本文件失败");
//                    pDialog.dismiss();
//                    Toast.showText(mContext, "下载失败");
                }
            });
        }
    }

    //下载版本号数据文件
    public static void downLoadVersionExplain() {
        LoadingUtil.dismiss();
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {

            String target = Environment.getExternalStorageDirectory()
                    + "/ScanAppVisionExplain"+".txt";
            HttpUtils utils = new HttpUtils();
            utils.download("http://148.70.108.65:8080/AppFile/Cloud/version_explain.txt", target, new RequestCallBack<File>() {
                @Override
                public void onLoading(long total, long current,
                                      boolean isUploading) {
                    super.onLoading(total, current, isUploading);
//                    System.out.println("下载进度:" + current + "/" + total);
//                    pDialog.setProgress((int) (current*100/total));
                }

                @Override
                public void onSuccess(ResponseInfo<File> arg0) {
//                    pDialog.dismiss();
                    Lg.e("下载版本文件成功");
                    Lg.e("下载的文件数据："+arg0.result);
                    CommonUtil.getString();

                }

                @Override
                public void onFailure(com.lidroid.xutils.exception.HttpException arg0, String arg1) {
                    Lg.e("下载版本文件失败");
//                    pDialog.dismiss();
//                    Toast.showText(mContext, "下载失败");
                }
            });
        }
    }


}




















/*
String str = "abc,12,3yy98,0";
String[]  strs=str.split(",");//以，截取   或者\\^
for(int i=0,len=strs.length;i<len;i++){
    System.out.println(strs[i].toString());



 sb.substring(2);//索引号 2 到结尾

String barcode = code.substring(0, 12);//第一位到第十一位

3.通过StringUtils提供的方法
StringUtils.substringBefore(“dskeabcee”, “e”);
/结果是：dsk/
这里是以第一个”e”，为标准。

StringUtils.substringBeforeLast(“dskeabcee”, “e”)
结果为：dskeabce
这里以最后一个“e”为准。
* */