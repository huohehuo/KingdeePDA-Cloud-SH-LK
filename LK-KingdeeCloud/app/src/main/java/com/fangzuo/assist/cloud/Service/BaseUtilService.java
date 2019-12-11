package com.fangzuo.assist.cloud.Service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Beans.BackDataLogin;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.RxSerivce.ToSubscribe;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.ShareUtil;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class BaseUtilService extends IntentService {
    private static final String Service_updateRegisterMsg = "Service_updateRegisterMsg";//更新注册表信息中的手机信息
    private static final String Service_ReLogin = "Service_ReLogin";//更新注册表信息中的手机信息

    private static final String EXTRA_PARAM1 = "com.fangzuo.assist.cloud.Service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.fangzuo.assist.cloud.Service.extra.PARAM2";

    public BaseUtilService() {
        super("BaseUtilService");
    }

    //更新注册表的手机信息：手机型号，imei码，当前版本号
    public static void updateRegisterMsg(Context context,String json) {
        Intent intent = new Intent(context, BaseUtilService.class);
        intent.setAction(Service_updateRegisterMsg);
        intent.putExtra(EXTRA_PARAM1, json);
        context.startService(intent);
    }
    //超时重登录
    public static void reLogin(Context context) {
        Intent intent = new Intent(context, BaseUtilService.class);
        intent.setAction(Service_ReLogin);
        context.startService(intent);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            switch (action){
                case Service_updateRegisterMsg:
                    final String json = intent.getStringExtra(EXTRA_PARAM1);
                    handleActionFoo(json);
                    break;
                case Service_ReLogin:
                    handleActionTT();
                    break;
            }
        }
    }
    //更新注册表的手机信息：手机型号，imei码，当前版本号
    private void handleActionFoo(String json) {
        App.getRService().doIOAction(WebApi.RegisterUpdateMsg,  json, new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
            }
            @Override
            public void onError(Throwable e) {
            }
        });
    }
    //重登录请求
    private void handleActionTT() {
        if ("".equals(Hawk.get(Info.user_name,"")))return;
        JSONArray jParas = new JSONArray();
        jParas.put(Hawk.get(Config.Cloud_ID, ""));// 帐套Id
        jParas.put(Hawk.get(Info.user_name,""));// 用户名
        jParas.put(Hawk.get(Info.user_pwd,""));// 密码
        jParas.put(2052);// 语言T
        App.CloudService().doIOActionLogin(Config.C_Login, jParas.toString(), new ToSubscribe<BackDataLogin>() {
            @Override
            public void onNext(BackDataLogin bean) {
                try {
                    if (bean.getLoginResultType() == 1 || bean.getLoginResultType() == -5) {
                        Hawk.put(Info.user_org, bean.getContext().getCurrentOrganizationInfo().getName());
                        Hawk.put(Info.user_id, bean.getContext().getUserId() + "");
                        Hawk.put(Info.user_data, bean.getContext().getDataCenterName() + "");
                        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Updata_Account,"更新主页的数据中心名称"));
//                        Toast.showText(App.getContext(),"重登录成功");
//                        Lg.e("登录成功：");
                    } else {
                        Toast.showTextLong(App.getContext(),"重登录失败"+bean.getMessage());
//                        Lg.e("登录错误：" + bean.toString());
                    }
                } catch (Exception e) {
//                        Lg.e("登录错误：" + bean.toString());
                }
            }

            @Override
            public void onError(Throwable e) {
//                Lg.e("登录错误：" + e.toString());
            }
        });
    }
}
