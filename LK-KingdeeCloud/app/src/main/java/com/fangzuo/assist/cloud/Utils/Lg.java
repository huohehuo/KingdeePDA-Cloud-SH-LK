package com.fangzuo.assist.cloud.Utils;

import android.util.Log;

import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.google.gson.Gson;

public class Lg {

    public static void e(String string){
        try {
            if (App.isDebug){
                if (string!=null){
                    Log.e("TEST","\n"+string);
                }else{
                    Log.e("TEST","对象为空");
                }
            }
        }catch (Exception e){
            Lg.e("打印错误",e.getMessage());

        }

    }
    public static void e(String tag,String string){
        try {
            if (App.isDebug){
                if (string!=null){
                    Log.e(tag,"\n"+string);
                }else{
                    Log.e(tag,"对象为空");
                }
            }
        }catch (Exception e){
            Lg.e("打印错误",e.getMessage());
        }

    }
    public static void e(String tag,Object string){
        try {
            if (App.isDebug){
                if (string!=null){
                    Log.e(tag,"\n"+new Gson().toJson(string));
                }else{
                    Log.e(tag,"\n"+"对象未空");
                }
            }
        }catch (Exception e){
            Lg.e("打印错误",e.getMessage());

        }

    }
}
