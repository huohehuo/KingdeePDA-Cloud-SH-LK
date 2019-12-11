package com.fangzuo.assist.cloud.Receiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.fangzuo.assist.cloud.Activity.SettingMenuActivity;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;

public class VersionReceiver extends BroadcastReceiver {
    private String id="com.fangzuo.version";
    private String name="升级通知";
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
//        Intent resultIntent = new Intent(context, SettingMenuActivity.class);
//        PendingIntent resultPendingIntent = PendingIntent.getActivity(
//                context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(id,name,NotificationManager.IMPORTANCE_LOW);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            mNotifyMgr.createNotificationChannel(channel);
            notification = new Notification.Builder(context)
                    .setChannelId(id)
                    .setContentTitle("升级通知")
                    .setTimeoutAfter(3600000)
                    .setContentText("程序有新的版本更新，请及时更新:"+intent.getStringExtra("version"))
//                    .setContentIntent(resultPendingIntent)
                    .setSmallIcon(R.mipmap.logo).build();
        }else{
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.mipmap.logo)
                            .setContentTitle("升级通知")
//                            .setContentIntent(resultPendingIntent)
                            .setContentText("程序有新的版本更新，请及时更新:"+intent.getStringExtra("version"));
            notification = mBuilder.build();
        }

        mNotifyMgr.notify(1, notification);
//        Toast.showText(context,"广播接收到信息"+intent.getStringExtra("version"));
    }
}
