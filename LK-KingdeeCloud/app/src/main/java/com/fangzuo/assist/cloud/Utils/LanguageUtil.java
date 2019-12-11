package com.fangzuo.assist.cloud.Utils;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.util.DisplayMetrics;

import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.orhanobut.hawk.Hawk;

import java.util.Locale;

public class LanguageUtil {
    /**
     * 更改应用语言，需要application和activity中都要设置更新语言，否则，重启后会错乱
     *
     * @param
     */
    public static void changeLanguage(Context context,boolean isClose){
            Locale locale;
            String language= Hawk.get(Config.PDA_Language,"CN");
            Lg.e(language);
            if (language.equals("CN")){
                locale=Locale.CHINA;
                Lg.e("设置中文");
            }else if (language.equals("EN")){
                locale=Locale.ENGLISH;
                Lg.e("设置英文");
            }else{
                locale= Locale.CHINA;
                Lg.e("设置默认中文");
            }
//        当前手机的语言
//        Resources res = this.getResources();
//        Configuration config = res.getConfiguration();
//        String phone_locale = config.locale.getCountry();

            App.PDA_Language = language;
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            Configuration configuration = context.getResources().getConfiguration();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                configuration.setLocale(locale);
            } else {
                configuration.locale = locale;
            }
            context.getResources().updateConfiguration(configuration, metrics);
//        //重新启动Activity
//        Intent intent = new Intent(this, WelcomeActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
    }
}
