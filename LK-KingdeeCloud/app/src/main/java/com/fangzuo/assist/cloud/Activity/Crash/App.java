package com.fangzuo.assist.cloud.Activity.Crash;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.fangzuo.assist.cloud.Activity.WelcomeActivity;
import com.fangzuo.assist.cloud.Beans.BlueToothBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.MainActivity;
import com.fangzuo.assist.cloud.RxSerivce.CloudService;
import com.fangzuo.assist.cloud.RxSerivce.RService;
import com.fangzuo.assist.cloud.Utils.BasicShareUtil;
import com.fangzuo.assist.cloud.Utils.CommonUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.LanguageUtil;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.orhanobut.hawk.Hawk;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import zpSDK.zpSDK.zpBluetoothPrinter;

/**
 //  ┏┓　　　┏┓
 //┏┛┻━━━┛┻┓
 //┃　　　　　　　┃
 //┃　　　━　　　┃
 //┃　┳┛　┗┳　┃
 //┃　　　　　　　┃
 //┃　　　┻　　　┃
 //┃　　　　　　　┃
 //┗━┓　　　┏━┛
 //    ┃　　　┃   神兽保佑
 //    ┃　　　┃   代码无BUG！
 //    ┃　　　┗━━━┓
 //    ┃　　　　　　　┣┓
 //    ┃　　　　　　　┏┛
 //    ┗┓┓┏━┳┓┏┛
 //      ┃┫┫　┃┫┫
 //      ┗┻┛　┗┻┛
 */


public class App extends MultiDexApplication {
    public static boolean isDebug=true;

    private static Context mContext;
    private String mCurDev = "";
    private boolean isIsDebug =true;
    private boolean hasRegister =false;
    static App instance = null;

    private static OkHttpClient           okHttpClient;
    private static HttpLoggingInterceptor interceptor;
//    private static Gson gson;

    private static Retrofit retrofit;
    public static String NowUrl;
    public static boolean isChangeIp=false;
    private static RService mService;//本地retrofit方法
    private static CloudService mCloudService;//本地retrofit方法

    public static int PDA_Choose;//{" 1 G02A设备","2 8000设备","3 5000设备"4 M60,"5手机端};
    public static String PDA_Language="CN";//{" 1 G02A设备","2 8000设备","3 5000设备"4 M60,"5手机端};
    public static String PDA_Time="";//用于重登录
    private zpBluetoothPrinter zpSDK;
    private BlueToothBean bean;
    public static String DataBaseSetting;//本地测试：K3DBConfiger201811123395555


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        mContext = this;
        instance = this;
        Hawk.init(mContext).build();
        DataBaseSetting = Hawk.get(Config.DataBase,"K3DBConfiger201910115049165");
        PDA_Choose= Hawk.get(Config.PDA,1);
        NowUrl = BasicShareUtil.getInstance(mContext).getBaseURL();
        PDA_Time= CommonUtil.getTime2Fen();//用于重登录
        //retrofit的基本初始化相关
//        gson = new Gson();
//        final GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(Book.class, new BookTypeAdapter());
//        final Gson gson = gsonBuilder.create();
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.connectTimeout(5000, TimeUnit.SECONDS);
//        builder.readTimeout(20, TimeUnit.SECONDS);
//        builder.writeTimeout(20, TimeUnit.SECONDS);
//        builder.retryOnConnectionFailure(true);
        interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Request request = chain.request();
//                        Request.Builder requestBuilder = request.newBuilder();
//                        request = requestBuilder
//                                .addHeader("Content-Type", "application/json;charset=UTF-8")
//                                .post(RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),
//                                        bodyToString(request.body())))//关键部分，设置requestBody的编码格式为json
//                                .build();
//                        return chain.proceed(request);
//                    }
//                })
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response originalResponse = chain.proceed(chain.request());
                        //这里获取请求返回的cookie
                        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                            final StringBuffer cookieBuffer = new StringBuffer();
                            //最近在学习RxJava,这里用了RxJava的相关API大家可以忽略,用自己逻辑实现即可.大家可以用别的方法保存cookie数据
                            Observable.from(originalResponse.headers("Set-Cookie"))
                                    .map(new Func1<String, String>() {
                                        @Override
                                        public String call(String s) {
                                            String[] cookieArray = s.split(";");
                                            return cookieArray[0];
                                        }
                                    })
                                    .subscribe(new Action1<String>() {
                                        @Override
                                        public void call(String cookie) {
                                            if (cookie.startsWith("kdservice-sessionid")){
                                                Hawk.put("cookie",cookie);
                                            }
//                                            cookieBuffer.append(cookie).append(";");
                                        }
                                    });
//                            Lg.e("cooking:"+cookieBuffer.toString());
                        }

                        return originalResponse;
                    }
                })
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        final Request.Builder builder = chain.request().newBuilder();
                        builder.addHeader("Cookie", Hawk.get("cookie",""));
                        return chain.proceed(builder.build());
                    }
                })
                .addInterceptor(interceptor)
                .connectTimeout(5000, TimeUnit.SECONDS)
                .readTimeout(200, TimeUnit.SECONDS)
                .writeTimeout(200, TimeUnit.SECONDS)
                .build();
        try {
            //这里的baseurl,注意要有实际格式的链接，不然会崩
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(BasicShareUtil.getInstance(mContext).getBaseURL())
                    .build();
        }catch (Exception e){
            //崩溃的时候，重置ip和端口
            BasicShareUtil.getInstance(mContext).setIP("192.168.0.0");
            BasicShareUtil.getInstance(mContext).setPort("8080");
        }
        mService = new RService();
        mCloudService = new CloudService();
        closeAndroidPDialog();
        //更新语言
        LanguageUtil.changeLanguage(getApplicationContext(),false);
        zpSDK = new zpBluetoothPrinter(this);

    }
    private static String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    public static Context getContext(){
        return mContext;
    }
    public static  App getInstance(){
        if(instance==null){
            instance =new App();
        }
        return instance;
    }

    //获取Service对象，当ip发生变化时，更换Serivce对象
    public static RService getRService() {
        if (TextUtils.equals(BasicShareUtil.getInstance(App.getContext()).getBaseURL(),App.NowUrl)){
            isChangeIp=false;
            return mService;
        }else{
            isChangeIp=true;
            RService mSerivce = new RService();
            setRService(mSerivce);
            String changeUrl=BasicShareUtil.getInstance(App.getContext()).getBaseURL();
            App.NowUrl=changeUrl;
            return mSerivce;
        }
    }

    //获取Service对象，当ip发生变化时，更换Serivce对象
    public static CloudService CloudService() {
//        if (TextUtils.equals(BasicShareUtil.getInstance(App.getContext()).getBaseURL(),App.NowUrl)){
//            isChangeIp=false;
            return new CloudService();
//        }else{
//            isChangeIp=true;
//            RService mSerivce = new RService();
//            setRService(mSerivce);
//            String changeUrl=BasicShareUtil.getInstance(App.getContext()).getBaseURL();
//            App.NowUrl=changeUrl;
//            return mCloudService;
//        }
    }

    public static void setRService(RService mService) {
        App.mService = mService;
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public static void setRetrofit(Retrofit retrofit) {
        App.retrofit = retrofit;
    }
    public static OkHttpClient getOkHttpClient(){
        return okHttpClient;
    }
    public static HttpLoggingInterceptor getInterceptor(){
        return interceptor;
    }


//    去掉在Android P上的提醒弹窗 （Detected problems with API compatibility(visit g.co/dev/appcompat for more info)
    private void closeAndroidPDialog(){
        try {
            Class aClass = Class.forName("android.content.pm.PackageParser$Package");
            Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread = declaredMethod.invoke(null);
            Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //连接打印机（每次连接都会断开上次的连接）
    public void connectPrint(){
        try {
            bean = Hawk.get(Config.OBJ_BLUETOOTH, new BlueToothBean("", ""));
            if (bean.address.equals("")) {
                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Print_Check, "NOOK"));
            } else {
                //当打印机已经连上，就不需要再次连接
//                if (!hasCon){
                    if (!zpSDK.connect(bean.address)) {
                        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Print_Check, "NOOK"));
                    } else {
                        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Print_Check, "OK"));
                    }
//                }
            }
        } catch (Exception e) {
        }
    }
    //获取打印sdk对象
    public zpBluetoothPrinter getZpk(){
        return zpSDK;
    }
    //断开打印机连接
    public void disPrint(){
        try {
            zpSDK.disconnect();
        } catch (Exception e) {

        }
    }
}
