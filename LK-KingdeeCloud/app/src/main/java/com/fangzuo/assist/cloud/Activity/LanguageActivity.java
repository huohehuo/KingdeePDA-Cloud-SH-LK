package com.fangzuo.assist.cloud.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.MainActivity;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.UpLoadModel;
import com.fangzuo.assist.cloud.databinding.ActivityLanguageBinding;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.orhanobut.hawk.Hawk;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {
    private ActivityLanguageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_language);
        binding.toolbar.tvTitle.setText(R.string.language_set_title);

        binding.cvCn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAppLanguage(Locale.CHINA);
            }
        });
        binding.cvEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeAppLanguage(Locale.ENGLISH);
            }
        });
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    /**
     * 更改应用语言
     *
     * @param locale
     */
    private void changeAppLanguage(final Locale locale) {
        new AlertDialog.Builder(LanguageActivity.this)
                .setTitle(R.string.if_change_language)
                .setMessage(R.string.str_app_will_restart)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DisplayMetrics metrics = App.getContext().getResources().getDisplayMetrics();
                        Configuration configuration = App.getContext().getResources().getConfiguration();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                            configuration.setLocale(locale);
                        } else {
                            configuration.locale = locale;
                        }
                        setLocaleLanguage(locale);
                        App.getContext().getResources().updateConfiguration(configuration, metrics);
                        getResources().updateConfiguration(configuration, metrics);
                        //重新启动Activity
                        Intent intent = new Intent(LanguageActivity.this, WelcomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                })
                .setNeutralButton(R.string.cancle,null)
                .create().show();

    }
    private void setLocaleLanguage(Locale locale){
        if (locale==null)return;
        if (locale.equals(Locale.CHINA)){
            Hawk.put(Config.PDA_Language,"CN");
            Lg.e("保存语言为中文");
        }
        if (locale.equals(Locale.ENGLISH)){
            Hawk.put(Config.PDA_Language,"EN");
            Lg.e("保存语言为英文");
        }
    }
}
